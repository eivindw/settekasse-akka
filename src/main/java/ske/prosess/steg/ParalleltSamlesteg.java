package ske.prosess.steg;

import akka.actor.ActorRef;
import ske.prosess.melding.Input;

import java.util.List;

public class ParalleltSamlesteg<T> extends Samlesteg<T> {

   public ParalleltSamlesteg(List<ActorRef> stegliste) {
      super(stegliste);
   }

   @Override
   protected void kallUndersteg() {
      for(ActorRef steg : getStegliste()) {
         steg.tell(new Input<>(getInput()), getSelf());
      }
   }
}
