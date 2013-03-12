package ske.prosess.leveransebehandling;

import eivindw.messages.Result;
import eivindw.steps.SynchronousEndStep;
import ske.prosess.domene.Leveranse;

public class BestemLeveransestatus extends SynchronousEndStep<Leveranse> {

   @Override
   protected Result<Leveranse> handle(Leveranse input) {
      return new Result<Leveranse>("leveransestatus") {
         @Override
         public void applyTo(Leveranse value) {
            value.bestemStatus();
         }
      };
   }
}
