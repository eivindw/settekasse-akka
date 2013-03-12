package ske.prosess.leveransebehandling;

import eivindw.messages.Result;
import eivindw.steps.SynchronousEndStep;
import ske.prosess.domene.Oppgave;

public class BestemOppgavestatus extends SynchronousEndStep<Oppgave> {

   @Override
   public Result<Oppgave> handle(Oppgave oppgave) {
      return new Result<Oppgave>("oppgavestatus") {
         @Override
         public void applyTo(Oppgave value) {
            value.bestemStatus();
         }
      };
   }
}
