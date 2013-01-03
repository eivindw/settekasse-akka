package ske.prosess.definisjon;

import akka.actor.ActorRef;
import akka.actor.ActorRefFactory;

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
}
