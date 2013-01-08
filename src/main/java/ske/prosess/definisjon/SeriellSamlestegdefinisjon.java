package ske.prosess.definisjon;

import akka.actor.Actor;
import akka.actor.ActorRef;
import ske.prosess.steg.SerieltSamlesteg;

import java.util.List;

public class SeriellSamlestegdefinisjon<T> extends Samlestegdefinisjon<T> {

   protected SeriellSamlestegdefinisjon(Stegdefinisjon... stegdefinisjoner) {
      super(stegdefinisjoner);
   }

   @Override
   protected Actor lagSteg(List<ActorRef> stegliste) {
      return new SerieltSamlesteg<>(stegliste);
   }
}
