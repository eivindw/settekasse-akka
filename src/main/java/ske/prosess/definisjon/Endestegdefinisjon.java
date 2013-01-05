package ske.prosess.definisjon;

import akka.actor.ActorRef;
import akka.actor.ActorRefFactory;
import akka.actor.Props;
import ske.prosess.steg.Steg;

public class Endestegdefinisjon<T> extends Stegdefinisjon<T> {

   private final Class<? extends Steg<T>> stegklasse;

   protected Endestegdefinisjon(Class<? extends Steg<T>> stegklasse) {
      this.stegklasse = stegklasse;
   }

   @Override
   public ActorRef tilActor(ActorRefFactory context) {
      ActorRef ref = context.actorFor(stegklasse.getSimpleName());
      if(ref.isTerminated()) {
         ref = context.actorOf(new Props(stegklasse), stegklasse.getSimpleName());
      }
      return ref;
   }
}
