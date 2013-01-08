package ske.prosess.teststeg;

import ske.prosess.melding.Resultat;
import ske.prosess.steg.SynkrontEndesteg;

public class OrdLengde extends SynkrontEndesteg<String> {

   @Override
   public Resultat<String> behandle(String ord) {
      final int length = ord.length();

      return new Resultat<>(length);
   }
}
