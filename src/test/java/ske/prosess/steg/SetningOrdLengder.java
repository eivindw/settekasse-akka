package ske.prosess.steg;

import ske.prosess.melding.Resultat;
import ske.prosess.Steg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

public class SetningOrdLengder extends Steg<String, Collection> {

   private Collection<String> coll = new ArrayList<>();
   private int expectedCount = 0;

   @Override
   protected Collection behandle(String tekst) {
      StringTokenizer tokenizer = new StringTokenizer(tekst, " ");
      while(tokenizer.hasMoreTokens()) {
         String ord = tokenizer.nextToken();
         behandleDelsteg(OrdLengde.class, ord);
         expectedCount++;
      }
      return null;
   }

   @Override
   protected boolean delresultat(Resultat resultat) {
      coll.add(resultat.getKey() + ": " + resultat.getData());
      return coll.size() == expectedCount;
   }

   @Override
   public Collection resultat() {
      return coll;
   }
}
