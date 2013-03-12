package ske.prosess.leveransebehandling;

import eivindw.messages.Result;
import eivindw.steps.AsynchronousEndStep;
import ske.prosess.domene.Avvik;
import ske.prosess.domene.Identifisering;
import ske.prosess.domene.Oppgave;

import java.util.Random;

public class IdentifisereOppgaveeier extends AsynchronousEndStep<Oppgave> {

   private static final Random RANDOM = new Random();

   @Override
   protected Result<Oppgave> handle(Oppgave input) throws Exception {
      Thread.sleep(50);
      final Identifisering identifisering = RANDOM.nextBoolean() ? new Identifisering(RANDOM.nextInt(100)) : new Identifisering(new Avvik("IDFEIL"));
      return new Result<Oppgave>(identifisering) {
         @Override
         public void applyTo(Oppgave oppg) {
            oppg.leggTilIdentifisering(identifisering);
         }
      };
   }
}
