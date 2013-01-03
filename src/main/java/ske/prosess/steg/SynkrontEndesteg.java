package ske.prosess.steg;

import akka.actor.UntypedActor;
import ske.prosess.melding.Input;
import ske.prosess.melding.Resultat;

public abstract class SynkrontEndesteg<T, R> extends UntypedActor implements Steg<T> {

   @Override
   public void onReceive(Object message) {
      if(message instanceof Input) {
         final T input = ((Input<T>) message).getData();
         getSender().tell(behandle(input), getSelf());
      }
   }

   protected abstract Resultat<R> behandle(T input);
}
