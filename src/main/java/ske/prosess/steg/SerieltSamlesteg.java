package ske.prosess.steg;

import akka.actor.ActorRef;
import ske.prosess.melding.Input;

import java.util.List;

public class SerieltSamlesteg<T> extends Samlesteg<T> {

   private int stegpeker = 0;

   public SerieltSamlesteg(List<ActorRef> stegliste) {
      super(stegliste);
   }

   @Override
   protected void kallUndersteg() {
      getStegliste().get(stegpeker).tell(new Input<>(getInput()), getSelf());
      stegpeker++;
   }

   @Override
   protected void manglendeResultater() {
      kallUndersteg();
   }
}
