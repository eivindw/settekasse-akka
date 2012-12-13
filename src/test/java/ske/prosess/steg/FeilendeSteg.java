package ske.prosess.steg;

import ske.prosess.Steg;

import java.util.Random;

public class FeilendeSteg extends Steg<String, String> {

   @Override
   protected String behandle(String context) {
      if(new Random().nextBoolean()) {
         System.out.println(navn() + " feiler!");
         throw new RuntimeException("Uups!");
      } else {
         System.out.println(navn() + " feilet ikke!");
         return "resultat";
      }
   }

   @Override
   protected boolean kanRekjores() {
      return true;
   }
}
