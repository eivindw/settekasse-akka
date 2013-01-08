package ske.prosess.definisjon;

import akka.actor.*;
import akka.routing.RoundRobinRouter;
import ske.prosess.steg.ParalleltSamlesteg;

import java.util.List;

public final class ParallellSamlestegdefinisjon<T> extends Samlestegdefinisjon<T> {

   protected ParallellSamlestegdefinisjon(Stegdefinisjon... stegdefinisjoner) {
      super(stegdefinisjoner);
   }

   @Override
   public ActorRef tilActor(final ActorRefFactory context) {
      final List<ActorRef> stegliste = lagUndersteg(context);
      return context.actorOf(new Props(new UntypedActorFactory() {
         @Override
         public Actor create() throws Exception {
            return new ParalleltSamlesteg<T>(stegliste);
         }
      }).withRouter(new RoundRobinRouter(antallRoutere)));
   }
}
