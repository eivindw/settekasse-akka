package ske.prosess.leveransebehandling;

import ske.prosess.domene.Avvik;
import ske.prosess.domene.Identifisering;
import ske.prosess.domene.Leveranse;
import ske.prosess.steg.AsynkrontEndesteg;
import ske.prosess.melding.Resultat;

import java.util.Random;

public class IdentifisereOppgavegiver extends AsynkrontEndesteg<Leveranse, Identifisering> {

   @Override
   protected Identifisering behandleInput(Leveranse leveranse) throws Exception {
      Thread.sleep(500);
      return new Random().nextBoolean() ? new Identifisering(42) : new Identifisering(new Avvik("IDFEIL"));
   }

   @Override
   protected Resultat<Leveranse> lagResultat(final Leveranse leveranse, final Identifisering identifisering) {
      return new Resultat<Leveranse>(leveranse.getOppgavegiverOrgnr(), identifisering) {
         @Override
         public void applyTo(Leveranse value) {
            value.leggTilIdentifisering(identifisering);
         }
      };
   }
}
