package ske.prosess.teststeg;

import eivindw.messages.Result;
import eivindw.steps.SynchronousEndStep;
import scala.Option;

import java.util.Random;

public class FeilendeSteg extends SynchronousEndStep<String> {

   @Override
   public Result<String> handle(String context) {
      if(new Random().nextBoolean()) {
         throw new RuntimeException("Uups!");
      } else {
         return new Result<>("OK");
      }
   }

   @Override
   public void preRestart(Throwable reason, Option<Object> message) {
      getSelf().forward(message.get(), getContext());
   }
}
