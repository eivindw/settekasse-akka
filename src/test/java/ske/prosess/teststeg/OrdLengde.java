package ske.prosess.teststeg;

import ske.prosess.Steg;

public class OrdLengde extends Steg<String, Integer> {

   @Override
   protected Integer behandle(String ord) {
      return ord.length();
   }
}
