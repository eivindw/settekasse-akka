package ske.prosess.leveransebehandling;

import akka.actor.ActorRef;
import akka.dispatch.Futures;
import ske.prosess.Steg;
import ske.prosess.domene.Avvik;
import ske.prosess.domene.Identifisering;
import ske.prosess.domene.Oppgave;
import ske.prosess.melding.Resultat;

import java.util.Random;
import java.util.concurrent.Callable;

public class IdentifisereOppgaveeier extends Steg<Oppgave, Identifisering> {

   @Override
   protected Identifisering behandle(final Oppgave oppgave) {
      final ActorRef sender = getSender();
      final String fnr = oppgave.getOppgaveeierFnr();

      Futures.future(new Callable<Void>() {
         @Override
         public Void call() throws Exception {
            Thread.sleep(500);
            if(new Random().nextBoolean()) {
               sender.tell(new Resultat(fnr, new Identifisering(134)));
            } else {
               sender.tell(new Resultat(fnr, new Identifisering(new Avvik("IDFEIL"))));
            }
            return null;
         }
      }, getContext().dispatcher());
      return null;
   }
}
