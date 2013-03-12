package ske.prosess.teststeg;

import eivindw.messages.Result;
import eivindw.steps.AsynchronousEndStep;

public class TregtIdSteg extends AsynchronousEndStep<String> {

   @Override
   protected Result<String> handle(String input) throws Exception {
      Thread.sleep(500);
      return new Result<>(42L);
   }
}
