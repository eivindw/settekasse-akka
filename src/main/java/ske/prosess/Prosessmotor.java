package ske.prosess;

import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.SupervisorStrategy;
import akka.actor.UntypedActor;
import akka.japi.Function;
import scala.concurrent.duration.Duration;
import ske.prosess.definisjon.Stegdefinisjon;
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
         Stegdefinisjon startSteg = prosessInfo.getProsessdefinisjon().lagToppsteg();
         System.out.println("Jobbinfo START: " + prosessInfo.getInput());
         ActorRef actor = startSteg.tilActor(getContext());
         actor.tell(prosessInfo.getInput(), getSelf());
      } else if(message instanceof Resultat) {
         System.out.println("Jobbinfo SUCCESS: " + ((Resultat) message).getData());
      } else {
         unhandled(message);
      }
   }
}
