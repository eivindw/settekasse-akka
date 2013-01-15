package ske.prosess;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public abstract class BaseTest {

   protected static ActorRef prosessmotor;

   protected static ActorSystem system;

   @BeforeClass
   public static void lagSystem() {
      system = ActorSystem.create();
      prosessmotor = system.actorOf(new Props(Prosessmotor.class), "Prosessmotor");
   }

   @AfterClass
   public static void stengSystem() {
      system.shutdown();
   }

   @After
   public void sleep() throws InterruptedException {
      Thread.sleep(50);
   }
}
