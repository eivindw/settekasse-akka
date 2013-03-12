package ske.prosess.leveransebehandling;

import eivindw.messages.Result;
import eivindw.steps.AsynchronousEndStep;
import ske.prosess.domene.Avvik;
import ske.prosess.domene.Identifisering;
import ske.prosess.domene.Leveranse;

import java.util.Random;

public class IdentifisereOppgavegiver extends AsynchronousEndStep<Leveranse> {

   private static final Random RANDOM = new Random();

   @Override
   protected Result<Leveranse> handle(Leveranse input) throws Exception {
      System.out.println(navn() + " startet!");
      Thread.sleep(2000);
      final Identifisering identifisering = RANDOM.nextBoolean() ? new Identifisering(RANDOM.nextInt(10)) : new Identifisering(new Avvik("IDFEIL"));
      return new Result<Leveranse>(identifisering) {
         @Override
         public void applyTo(Leveranse value) {
            value.leggTilIdentifisering(identifisering);
            System.out.println(navn() + " ferdig!");
         }
      };
   }
}
