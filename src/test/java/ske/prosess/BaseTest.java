package ske.prosess;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.junit.After;
import org.junit.Before;

public abstract class BaseTest {

   protected ActorRef prosess;

   @Before
   public void startAkkaOgLagProsess() {
      ActorSystem system = ActorSystem.create();

      prosess = system.actorOf(new Props(Prosess.class), "Prosess");
   }

   @After
   public void sleep() throws InterruptedException {
      Thread.sleep(1000);
   }
}
