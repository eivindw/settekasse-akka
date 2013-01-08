package ske.prosess.leveransebehandling;

import ske.prosess.domene.Leveranse;
import ske.prosess.steg.SynkrontEndesteg;
import ske.prosess.melding.Resultat;

public class BestemLeveransestatus extends SynkrontEndesteg<Leveranse> {

   @Override
   protected Resultat<Leveranse> behandle(Leveranse input) {
      return new Resultat<Leveranse>("leveransestatus") {
         @Override
         public void applyTo(Leveranse value) {
            value.bestemStatus();
         }
      };
   }
}
