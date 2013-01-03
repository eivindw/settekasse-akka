package ske.prosess.teststeg;

import ske.prosess.steg.AsynkrontEndesteg;
import ske.prosess.melding.Resultat;

public class TregtIdSteg extends AsynkrontEndesteg<String, Long> {

   @Override
   protected Long behandleInput(String input) throws Exception {
      Thread.sleep(500);
      return 42L;
   }

   @Override
   protected Resultat<String> lagResultat(String input, Long resultat) {
      return new Resultat<>(input, resultat);
   }
}
