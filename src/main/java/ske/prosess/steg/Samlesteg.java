package ske.prosess.steg;

import akka.actor.ActorRef;
import ske.prosess.melding.Resultat;

import java.util.ArrayList;
import java.util.List;

public abstract class Samlesteg<T> extends AbstractSteg<T> {

   private List<ActorRef> stegliste = new ArrayList<>();
   private ActorRef behandler;
   private int antallResultater = 0;
   private T input;

   public Samlesteg(List<ActorRef> stegliste) {
      this.stegliste = stegliste;
   }

   @Override
   protected void behandleInput(T input) {
      this.input = input;
      behandler = getSender();
      kallUndersteg();
   }

   @Override
   protected void behandleResultat(Resultat resultat) {
      if(resultat.isType(input.getClass())) {
         input = (T) resultat.getData();
      } else {
         resultat.applyTo(input);
      }
      if (++antallResultater == stegliste.size()) {
         behandler.tell(new Resultat(input), getSelf());
      } else {
         manglendeResultater();
      }
   }

   public List<ActorRef> getStegliste() {
      return stegliste;
   }

   public T getInput() {
      return input;
   }

   protected abstract void kallUndersteg();

   protected void manglendeResultater() {
   }
}
