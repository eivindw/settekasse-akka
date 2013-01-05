package ske.prosess.steg;

import akka.actor.UntypedActor;

public abstract class AbstractSteg<T> extends UntypedActor implements Steg<T> {

   protected String navn() {
      return String.format("%s (%s)", this.getClass().getSimpleName(), getSelf().path().name());
   }
}
