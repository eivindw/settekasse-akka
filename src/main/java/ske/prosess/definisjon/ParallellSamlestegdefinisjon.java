package ske.prosess.definisjon;

import akka.actor.ActorRef;
import akka.actor.ActorRefFactory;

public final class ParallellSamlestegdefinisjon<T> extends Samlestegdefinisjon<T> {

   protected ParallellSamlestegdefinisjon(Stegdefinisjon... stegdefinisjoner) {
      super(stegdefinisjoner);
   }

   @Override
   public ActorRef tilActor(ActorRefFactory context) {
      return null;
   }
}
