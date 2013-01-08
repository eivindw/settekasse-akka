package ske.prosess.leveransebehandling;

import ske.prosess.domene.Avvik;
import ske.prosess.domene.Identifisering;
import ske.prosess.domene.Oppgave;
import ske.prosess.melding.Resultat;
import ske.prosess.steg.AsynkrontEndesteg;

import java.util.Random;

public class IdentifisereOppgaveeier extends AsynkrontEndesteg<Oppgave> {

   private static final Random RANDOM = new Random();

   @Override
   protected Resultat<Oppgave> behandle(Oppgave input) throws Exception {
      Thread.sleep(500);
      final Identifisering identifisering = RANDOM.nextBoolean() ? new Identifisering(RANDOM.nextInt(100)) : new Identifisering(new Avvik("IDFEIL"));
      return new Resultat<Oppgave>(identifisering) {
         @Override
         public void applyTo(Oppgave oppg) {
            oppg.leggTilIdentifisering(identifisering);
         }
      };
   }
}
