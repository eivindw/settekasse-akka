package ske.prosess.steg;

import ske.prosess.Steg;

import java.util.Random;

public class FeilendeSteg extends Steg<String, String> {

   @Override
   protected String behandle(String context) {
      if(new Random().nextBoolean()) {
         throw new RuntimeException("Uups!");
      } else {
         return "OK";
      }
   }

   @Override
   protected boolean kanRekjores() {
      return true;
   }
}
