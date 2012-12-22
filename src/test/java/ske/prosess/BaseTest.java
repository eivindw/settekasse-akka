package ske.prosess;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.junit.After;
import org.junit.Before;

public abstract class BaseTest {

   protected ActorRef prosessmotor;

   @Before
   public void startAkkaOgLagProsess() {
      ActorSystem system = ActorSystem.create();

      prosessmotor = system.actorOf(new Props(Prosessmotor.class), "Prosessmotor");
   }

   @After
   public void sleep() throws InterruptedException {
      Thread.sleep(1000);
   }
}
