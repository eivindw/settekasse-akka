package ske.prosess.steg;

import akka.actor.UntypedActor;
import ske.prosess.melding.Input;
import ske.prosess.melding.Resultat;

public abstract class AbstractSteg<T> extends UntypedActor implements Steg<T> {

   @Override
   public void onReceive(Object message) throws Exception {
      if(message instanceof Input) {
         T input = ((Input<T>) message).getData();
         System.out.println(navn() + " INPUT: " + input);
         behandleInput(input);
      } else if(message instanceof Resultat) {
         System.out.println(navn() + " RESULTAT: " + message);
         behandleResultat((Resultat) message);
      } else {
         unhandled(message);
      }
   }

   protected String navn() {
      return String.format("%s (%s)", this.getClass().getSimpleName(), getSelf().path().name());
   }

   protected void behandleResultat(Resultat resultat) {
      System.out.printf("[UBEHANDLET RESULTAT] %s: %s%n", navn(), resultat);
   }

   protected abstract void behandleInput(T input);
}
