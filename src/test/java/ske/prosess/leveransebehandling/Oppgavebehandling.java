package ske.prosess.leveransebehandling;

import akka.actor.ActorRef;
import ske.prosess.domene.Leveranse;
import ske.prosess.domene.Oppgave;
import ske.prosess.melding.Input;
import ske.prosess.melding.Resultat;
import ske.prosess.steg.AbstractSteg;

public class Oppgavebehandling extends AbstractSteg<Leveranse> {

   private ActorRef behandler;
   private final ActorRef understeg;
   private int antallResultater;
   private Leveranse input;

   public Oppgavebehandling(ActorRef understeg) {
      this.understeg = understeg;
   }

   @Override
   protected void behandleInput(Leveranse input) {
      System.out.println(navn() + " startet!");
      this.behandler = getSender();
      this.input = input;
      for(Oppgave oppgave : input.getOppgaver()) {
         understeg.tell(new Input<>(oppgave), getSelf());
      }
      antallResultater = input.getOppgaver().size();
      input.getOppgaver().clear();
   }

   @Override
   protected void behandleResultat(Resultat resultat) {
      input.getOppgaver().add((Oppgave) resultat.getData());
      if(--antallResultater == 0) {
         behandler.tell(new Resultat<Leveranse>("oppgavebehandling") {
            @Override
            public void applyTo(Leveranse value) {
               // Legg til oppsummering fra oppgavebehandling
               System.out.println(navn() + " ferdig!");
            }
         }, getSelf());
      }
      if(antallResultater % 50 == 0) {
         System.out.println("Gjenstående oppgaver: " + antallResultater);
      }
   }
}
