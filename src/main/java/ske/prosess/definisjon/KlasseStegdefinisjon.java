package ske.prosess.definisjon;

import akka.actor.*;
import ske.prosess.steg.Steg;

public class KlasseStegdefinisjon<T> extends Stegdefinisjon<T> {

   private final Class<? extends Steg<T>> stegklasse;
   private Stegdefinisjon understeg;

   protected KlasseStegdefinisjon(Class<? extends Steg<T>> stegklasse) {
      this.stegklasse = stegklasse;
   }

   @Override
   public ActorRef tilActor(final ActorRefFactory context) {
      ActorRef ref = context.actorFor(stegklasse.getSimpleName());
      if(ref.isTerminated()) {
         if(understeg != null) {
            ref = context.actorOf(new Props(new UntypedActorFactory() {
               @Override
               public Actor create() throws Exception {
                  return stegklasse.getConstructor(ActorRef.class).newInstance(understeg.tilActor(context));
               }
            }), stegklasse.getSimpleName());
         } else {
            ref = context.actorOf(new Props(stegklasse), stegklasse.getSimpleName());
         }
      }
      return ref;
   }

   public <R> Stegdefinisjon<T> med(Stegdefinisjon<R> understeg) {
      this.understeg = understeg;
      return this;
   }
}
