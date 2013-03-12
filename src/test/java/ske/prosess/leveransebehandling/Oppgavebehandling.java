package ske.prosess.leveransebehandling;

import akka.actor.ActorRef;
import eivindw.messages.Input;
import eivindw.messages.Result;
import eivindw.steps.AbstractStep;
import ske.prosess.domene.Leveranse;
import ske.prosess.domene.Oppgave;

public class Oppgavebehandling extends AbstractStep<Leveranse> {

   private ActorRef behandler;
   private final ActorRef understeg;
   private int antallResultater;
   private Leveranse input;

   public Oppgavebehandling(ActorRef understeg) {
      this.understeg = understeg;
   }

   @Override
   protected void handleInput(Leveranse input) {
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
   protected void handleResult(Result resultat) {
      input.getOppgaver().add((Oppgave) resultat.getData());
      if(--antallResultater == 0) {
         behandler.tell(new Result<Leveranse>("oppgavebehandling") {
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
