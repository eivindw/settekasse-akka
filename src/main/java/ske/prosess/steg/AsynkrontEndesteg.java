package ske.prosess.steg;

import akka.actor.ActorRef;
import akka.dispatch.Futures;
import ske.prosess.melding.Resultat;

import java.util.concurrent.Callable;

public abstract class AsynkrontEndesteg<T, R> extends AbstractSteg<T> {

   @Override
   protected void behandleInput(final T input) {
      final ActorRef sender = getSender();

      Futures.future(new Callable<Void>() {
         @Override
         public Void call() throws Exception {
            sender.tell(behandle(input), getSelf());

            return null;
         }
      }, getContext().dispatcher());
   }

   protected abstract Resultat<R> behandle(final T input) throws Exception;
}
