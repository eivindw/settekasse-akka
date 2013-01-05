package ske.prosess.definisjon;

import akka.actor.*;
import ske.prosess.steg.ParalleltSamlesteg;

public final class ParallellSamlestegdefinisjon<T> extends Samlestegdefinisjon<T> {

   protected ParallellSamlestegdefinisjon(Stegdefinisjon... stegdefinisjoner) {
      super(stegdefinisjoner);
   }

   @Override
   public ActorRef tilActor(final ActorRefFactory context) {
      return context.actorOf(new Props(new UntypedActorFactory() {
         @Override
         public Actor create() throws Exception {
            return new ParalleltSamlesteg<T>(lagUndersteg(context));
         }
      }));
   }
}
