package ske.prosess.steg;

import akka.actor.ActorRef;
import ske.prosess.melding.Input;
import ske.prosess.melding.Resultat;

import java.util.ArrayList;
import java.util.List;

public class SerieltSamlesteg<T> extends AbstractSteg<T> {

   private List<ActorRef> stegliste = new ArrayList<>();
   private ActorRef behandler;
   private int stegpeker = 0;
   private int antallResultater = 0;
   private T input;

   public SerieltSamlesteg(List<ActorRef> stegliste) {
      this.stegliste = stegliste;
   }

   @Override
   public void onReceive(Object message) {
      if (message instanceof Input) {
         input = ((Input<T>) message).getData();
         System.out.println(navn() + " BEHANDLER: " + input);
         behandler = getSender();
         kjoerNesteSteg();
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
      if(++antallResultater == stegliste.size()) {
         return true;
      } else {
         kjoerNesteSteg();
         return false;
      }
   }

   protected void kjoerNesteSteg() {
      stegliste.get(stegpeker).tell(new Input<>(input), getSelf());
      stegpeker++;
   }
}
