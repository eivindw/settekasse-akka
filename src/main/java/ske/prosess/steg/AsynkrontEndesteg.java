package ske.prosess.steg;

import akka.actor.ActorRef;
import akka.dispatch.Futures;
import ske.prosess.melding.Input;
import ske.prosess.melding.Resultat;

import java.util.concurrent.Callable;

public abstract class AsynkrontEndesteg<T, R> extends AbstractSteg<T> {

   @Override
   public void onReceive(Object message) {
      if(message instanceof Input) {
         final T input = ((Input<T>) message).getData();
         final ActorRef sender = getSender();
         System.out.println(navn() + " BEHANDLER: " + input);

         Futures.future(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
               final R resultat = behandleInput(input);

               sender.tell(lagResultat(input, resultat), getSelf());

               return null;
            }
         }, getContext().dispatcher());
      } else {
         unhandled(message);
      }
   }

   protected abstract R behandleInput(final T input) throws Exception;

   protected abstract Resultat<T> lagResultat(final T input, final R resultat);
}
