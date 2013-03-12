package ske.prosess.teststeg;

import eivindw.messages.Result;
import eivindw.steps.SynchronousEndStep;

public class OrdLengde extends SynchronousEndStep<String> {

   @Override
   public Result<String> handle(String ord) {
      final int length = ord.length();

      return new Result<>(length);
   }
}
