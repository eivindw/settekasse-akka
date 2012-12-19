package ske.prosess;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import scala.Option;
import ske.prosess.melding.Input;
import ske.prosess.melding.Resultat;

public abstract class Steg<T, R> extends UntypedActor {

   private ActorRef behandler;

   @Override
   public void onReceive(Object message) {
      if (message instanceof Input) {
         Input<T> input = (Input<T>) message;
         System.out.println(navn() + " BEHANDLER: " + input.getData());
         R resultat = behandle(input.getData());
         if (resultat != null) {
            getSender().tell(new Resultat(input.getData(), resultat));
         } else {
            behandler = getSender();
         }
      } else if (message instanceof Resultat) {
         Resultat resultat = (Resultat) message;
         System.out.println(navn() + " RESULTAT: " + resultat);
         if (delresultat(resultat)) {
            behandler.tell(new Resultat(resultat.getKey(), resultat()));
         }
      } else {
         unhandled(message);
      }
   }

   @Override
   public void preRestart(Throwable reason, Option<Object> message) {
      if (kanRekjores()) {
         if (message.isDefined()) {
            getSelf().forward(message.get(), getContext());
         }
      }
   }

   public String navn() {
      return String.format("%s (%s)", this.getClass().getSimpleName(), getSelf().path().name());
   }

   protected R resultat() {
      return null;
   }

   protected <T2> void behandleDelsteg(Class<? extends Steg> stegklasse, T2 data) {
      ActorRef delsteg = getContext().actorOf(new Props(stegklasse));
      delsteg.tell(new Input<>(data), getSelf());
   }

   protected boolean delresultat(Resultat resultat) {
      return true;
   }

   protected boolean kanRekjores() {
      return false;
   }

   protected abstract R behandle(T context);
}
