package ske.prosess.leveransebehandling;

import ske.prosess.domene.Avvik;
import ske.prosess.domene.Identifisering;
import ske.prosess.domene.Leveranse;
import ske.prosess.melding.Resultat;
import ske.prosess.steg.AsynkrontEndesteg;

import java.util.Random;

public class IdentifisereOppgavegiver extends AsynkrontEndesteg<Leveranse> {

   @Override
   protected Resultat<Leveranse> behandle(Leveranse input) throws Exception {
      Thread.sleep(500);
      final Identifisering identifisering = new Random().nextBoolean() ? new Identifisering(42) : new Identifisering(new Avvik("IDFEIL"));
      return new Resultat<Leveranse>(input.getOppgavegiverOrgnr(), identifisering) {
         @Override
         public void applyTo(Leveranse value) {
            value.leggTilIdentifisering(identifisering);
         }
      };
   }
}
