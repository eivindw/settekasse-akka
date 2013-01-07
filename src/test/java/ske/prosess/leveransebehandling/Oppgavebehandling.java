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

   public Oppgavebehandling(ActorRef understeg) {
      this.understeg = understeg;
   }

   @Override
   protected void behandleInput(Leveranse input) {
      this.behandler = getSender();
      for(Oppgave oppgave : input.getOppgaver()) {
         understeg.tell(new Input<>(oppgave), getSelf());
      }
      antallResultater = input.getOppgaver().size();
   }

   @Override
   protected void behandleResultat(Resultat resultat) {
      if(--antallResultater == 0) {
         behandler.tell(new Resultat<>("oppgbeh", "hei"), getSelf());
      }
   }
}
