package ske.prosess.definisjon;

import akka.actor.Actor;
import akka.actor.ActorRef;
import ske.prosess.steg.ParalleltSamlesteg;

import java.util.List;

public final class ParallellSamlestegdefinisjon<T> extends Samlestegdefinisjon<T> {

   protected ParallellSamlestegdefinisjon(Stegdefinisjon... stegdefinisjoner) {
      super(stegdefinisjoner);
   }

   @Override
   protected Actor lagSteg(List<ActorRef> stegliste) {
      return new ParalleltSamlesteg<>(stegliste);
   }
}
