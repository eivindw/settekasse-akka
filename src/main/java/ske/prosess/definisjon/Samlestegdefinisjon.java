package ske.prosess.definisjon;

import akka.actor.*;
import ske.prosess.steg.SamlestegHub;

import java.util.ArrayList;
import java.util.List;

public abstract class Samlestegdefinisjon<T> extends Stegdefinisjon<T> {

   private final Stegdefinisjon[] stegdefinisjoner;

   protected Samlestegdefinisjon(Stegdefinisjon... stegdefinisjoner) {
      this.stegdefinisjoner = stegdefinisjoner;
   }

   protected List<ActorRef> lagUndersteg(ActorRefFactory context) {
      List<ActorRef> understeg = new ArrayList<>();
      for(Stegdefinisjon stegdefinisjon : stegdefinisjoner) {
         understeg.add(stegdefinisjon.tilActor(context));
      }
      return understeg;
   }

   @Override
   public ActorRef tilActor(ActorRefFactory context) {
      final List<ActorRef> stegliste = lagUndersteg(context);
      final Props samlestegProps = new Props(new UntypedActorFactory() {
         @Override
         public Actor create() throws Exception {
            return lagSteg(stegliste);
         }
      });
      return context.actorOf(new Props(new UntypedActorFactory() {
         @Override
         public Actor create() throws Exception {
            return new SamlestegHub<>(samlestegProps);
         }
      }));
   }

   protected abstract Actor lagSteg(List<ActorRef> stegliste);
}
