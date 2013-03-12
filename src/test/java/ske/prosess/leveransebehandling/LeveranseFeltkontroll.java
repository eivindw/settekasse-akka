package ske.prosess.leveransebehandling;

import eivindw.messages.Result;
import eivindw.steps.SynchronousEndStep;
import ske.prosess.domene.Leveranse;

public class LeveranseFeltkontroll extends SynchronousEndStep<Leveranse> {

   @Override
   protected Result<Leveranse> handle(Leveranse input) {
      System.out.println(navn() + " startet!");
      return new Result<Leveranse>("leveranse feltkontroll") {
         @Override
         public void applyTo(Leveranse value) {
            // Legg til evt avvik;
            System.out.println(navn() + " ferdig!");
         }
      };
   }
}
