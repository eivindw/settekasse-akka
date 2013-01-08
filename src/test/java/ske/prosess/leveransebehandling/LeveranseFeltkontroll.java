package ske.prosess.leveransebehandling;

import ske.prosess.domene.Leveranse;
import ske.prosess.melding.Resultat;
import ske.prosess.steg.SynkrontEndesteg;

public class LeveranseFeltkontroll extends SynkrontEndesteg<Leveranse> {

   @Override
   protected Resultat<Leveranse> behandle(Leveranse input) {
      return new Resultat<Leveranse>("leveranse feltkontroll") {
         @Override
         public void applyTo(Leveranse value) {
            // Legg til evt avvik;
         }
      };
   }
}
