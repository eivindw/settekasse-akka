package ske.prosess.leveransebehandling;

import eivindw.messages.Result;
import eivindw.steps.SynchronousEndStep;
import ske.prosess.domene.Oppgave;

public class OppgaveFeltkontroll extends SynchronousEndStep<Oppgave> {

   @Override
   protected Result<Oppgave> handle(Oppgave input) {
      return new Result<Oppgave>("oppgave feltkontroll") {
         @Override
         public void applyTo(Oppgave value) {
            // Legg til evt. avvik
         }
      };
   }
}
