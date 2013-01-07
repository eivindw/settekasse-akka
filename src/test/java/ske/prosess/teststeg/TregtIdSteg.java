package ske.prosess.teststeg;

import ske.prosess.melding.Resultat;
import ske.prosess.steg.AsynkrontEndesteg;

public class TregtIdSteg extends AsynkrontEndesteg<String> {

   @Override
   protected Resultat<String> behandle(String input) throws Exception {
      Thread.sleep(500);
      return new Resultat<>(input, 42L);
   }
}
