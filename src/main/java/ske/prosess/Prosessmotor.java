package ske.prosess;

import akka.actor.*;
import akka.japi.Function;
import akka.util.Duration;
import ske.prosess.melding.ProsessInfo;
import ske.prosess.melding.Resultat;

public class Prosessmotor extends UntypedActor {

   private static SupervisorStrategy strategy = new OneForOneStrategy(2, Duration.Inf(), new Function<Throwable, SupervisorStrategy.Directive>() {
      @Override
      public SupervisorStrategy.Directive apply(Throwable param) {
         System.out.println("Jobbinfo ERROR: " + param.getMessage());
         return SupervisorStrategy.restart();
      }
   });

   @Override
   public SupervisorStrategy supervisorStrategy() {
      return strategy;
   }

   @Override
   public void onReceive(Object message) {
      if(message instanceof ProsessInfo) {
         ProsessInfo prosessInfo = (ProsessInfo) message;
         System.out.println("Jobbinfo START: " + prosessInfo.getStegklasse().getSimpleName());
         ActorRef actor = getContext().actorOf(new Props(prosessInfo.getStegklasse()));
         actor.tell(prosessInfo.getInput(), getSelf());
      } else if(message instanceof Resultat) {
         System.out.println("Jobbinfo SUCCESS: " + ((Resultat) message).getData());
      } else {
         unhandled(message);
      }
   }
}
