package ske.prosess.definisjon;

import akka.actor.*;
import akka.routing.RoundRobinRouter;
import ske.prosess.steg.SerieltSamlesteg;

import java.util.List;

public class SeriellSamlestegdefinisjon<T> extends Samlestegdefinisjon<T> {

   protected SeriellSamlestegdefinisjon(Stegdefinisjon... stegdefinisjoner) {
      super(stegdefinisjoner);
   }

   @Override
   public ActorRef tilActor(final ActorRefFactory context) {
      final List<ActorRef> stegliste = lagUndersteg(context);
      return context.actorOf(new Props(new UntypedActorFactory() {
         @Override
         public Actor create() throws Exception {
            return new SerieltSamlesteg<T>(stegliste);
         }
      }).withRouter(new RoundRobinRouter(antallRoutere)));
   }
}
