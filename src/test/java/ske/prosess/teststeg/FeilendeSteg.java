package ske.prosess.teststeg;

import scala.Option;
import ske.prosess.steg.SynkrontEndesteg;
import ske.prosess.melding.Resultat;

import java.util.Random;

public class FeilendeSteg extends SynkrontEndesteg<String, String> {

   @Override
   public Resultat<String> behandle(String context) {
      if(new Random().nextBoolean()) {
         throw new RuntimeException("Uups!");
      } else {
         return new Resultat<>("", "OK");
      }
   }

   @Override
   public void preRestart(Throwable reason, Option<Object> message) {
      getSelf().forward(message.get(), getContext());
   }
}
