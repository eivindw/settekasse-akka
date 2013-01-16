package ske.prosess.leveransebehandling;

import ske.prosess.domene.Avvik;
import ske.prosess.domene.Identifisering;
import ske.prosess.domene.Leveranse;
import ske.prosess.melding.Resultat;
import ske.prosess.steg.AsynkrontEndesteg;

import java.util.Random;

public class IdentifisereOppgavegiver extends AsynkrontEndesteg<Leveranse> {

   private static final Random RANDOM = new Random();

   @Override
   protected Resultat<Leveranse> behandle(Leveranse input) throws Exception {
      System.out.println(navn() + " startet!");
      Thread.sleep(2000);
      final Identifisering identifisering = RANDOM.nextBoolean() ? new Identifisering(RANDOM.nextInt(10)) : new Identifisering(new Avvik("IDFEIL"));
      return new Resultat<Leveranse>(identifisering) {
         @Override
         public void applyTo(Leveranse value) {
            value.leggTilIdentifisering(identifisering);
            System.out.println(navn() + " ferdig!");
         }
      };
   }
}
