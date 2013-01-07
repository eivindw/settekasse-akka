package ske.prosess.steg;

import ske.prosess.melding.Resultat;

public abstract class SynkrontEndesteg<T> extends AbstractSteg<T> {

   @Override
   protected void behandleInput(T input) {
      getSender().tell(behandle(input), getSelf());
   }

   protected abstract Resultat<T> behandle(T input);
}
