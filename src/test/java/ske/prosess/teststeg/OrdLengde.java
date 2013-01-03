package ske.prosess.teststeg;

import ske.prosess.steg.SynkrontEndesteg;
import ske.prosess.melding.Resultat;

import java.util.Collection;

public class OrdLengde extends SynkrontEndesteg<String, Collection> {

   @Override
   public Resultat<Collection> behandle(String ord) {
      final int length = ord.length();

      return new Resultat<Collection>(ord, length) {
         @Override
         public void applyTo(Collection coll) {
            coll.add(getKey() + ": " + getData());
         }
      };
   }
}
