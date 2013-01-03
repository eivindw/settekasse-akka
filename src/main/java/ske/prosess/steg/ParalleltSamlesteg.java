package ske.prosess.steg;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import ske.prosess.melding.Input;
import ske.prosess.melding.Resultat;

import java.util.ArrayList;
import java.util.List;

public class ParalleltSamlesteg<T> extends UntypedActor implements Steg<T> {

   private List<ActorRef> stegliste = new ArrayList<>();
   private ActorRef behandler;
   private int antallResultater = 0;
   private T input;

   public ParalleltSamlesteg(List<ActorRef> stegliste) {
      this.stegliste = stegliste;
   }

   @Override
   public void onReceive(Object message) {
      if (message instanceof Input) {
         Input<T> input = (Input<T>) message;
         System.out.println(navn() + " BEHANDLER: " + input.getData());
         behandler = getSender();
         this.input = input.getData();
         for(ActorRef steg : stegliste) {
            steg.tell(input, getSelf());
         }
      } else if (message instanceof Resultat) {
         Resultat resultat = (Resultat) message;
         System.out.println(navn() + " RESULTAT: " + resultat);
         if (delresultat(resultat)) {
            behandler.tell(new Resultat(resultat.getKey(), input), getSelf());
         }
      } else {
         unhandled(message);
      }
   }

   protected boolean delresultat(Resultat resultat) {
      resultat.applyTo(input);
      return ++antallResultater == stegliste.size();
   }

   protected String navn() {
      return String.format("%s (%s)", this.getClass().getSimpleName(), getSelf().path().name());
   }
}
