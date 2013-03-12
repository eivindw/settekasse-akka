package ske.prosess;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import eivindw.ProcessEngine;
import ske.prosess.domene.Leveranse;
import ske.prosess.domene.Oppgave;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Server {

   public static void main(String[] args) throws IOException {
      System.out.println("Running server!");

      ActorSystem system = ActorSystem.create();

      ActorRef prosessmotor = system.actorOf(new Props(ProcessEngine.class), "Prosessmotor");

      System.out.print("Commands: q, l!\n> ");
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      while(!br.readLine().equals("q")) {
         Leveranse leveranse = new Leveranse(new Oppgave("1234"), new Oppgave("4321"));

         //prosessmotor.tell(new ProsessInfo(TestProsessdefinisjon.class, leveranse));
      }
      System.out.println("Exiting server!");

      system.shutdown();
   }
}
