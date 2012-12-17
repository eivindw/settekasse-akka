package ske.prosess;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import ske.prosess.domene.Leveranse;
import ske.prosess.domene.Oppgave;
import ske.prosess.melding.ProsessInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Server {

   public static void main(String[] args) throws IOException {
      System.out.println("Running server!");

      ActorSystem system = ActorSystem.create();

      ActorRef prosess = system.actorOf(new Props(Prosess.class), "Prosess");

      System.out.println("Commands: q, l!");
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      while(!br.readLine().equals("q")) {
         Leveranse leveranse = new Leveranse(new Oppgave(), new Oppgave());

         prosess.tell(new ProsessInfo(TestProsessdefinisjon.class, leveranse));
      }
      System.out.println("Exiting server!");

      system.shutdown();
   }
}
