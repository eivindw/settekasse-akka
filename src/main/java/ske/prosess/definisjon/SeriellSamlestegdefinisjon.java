package ske.prosess.definisjon;

import akka.actor.*;
import ske.prosess.steg.SerieltSamlesteg;

public class SeriellSamlestegdefinisjon<T> extends Samlestegdefinisjon<T> {

   protected SeriellSamlestegdefinisjon(Stegdefinisjon... stegdefinisjoner) {
      super(stegdefinisjoner);
   }

   @Override
   public ActorRef tilActor(final ActorRefFactory context) {
      return context.actorOf(new Props(new UntypedActorFactory() {
         @Override
         public Actor create() throws Exception {
            return new SerieltSamlesteg<T>(lagUndersteg(context));
         }
      }));
   }
}
