package ske.prosess.leveransebehandling;

import akka.actor.ActorRef;
import akka.dispatch.Futures;
import ske.prosess.Steg;
import ske.prosess.domene.Avvik;
import ske.prosess.domene.Identifisering;
import ske.prosess.domene.Leveranse;
import ske.prosess.melding.Resultat;

import java.util.Random;
import java.util.concurrent.Callable;

public class IdentifisereOppgavegiver extends Steg<Leveranse, Identifisering> {

   @Override
   protected Identifisering behandle(final Leveranse leveranse) {
      final ActorRef sender = getSender();
      final String orgnr = leveranse.getOppgavegiverOrgnr();

      Futures.future(new Callable<Void>() {
         @Override
         public Void call() throws Exception {
            Thread.sleep(500);
            if(new Random().nextBoolean()) {
               sender.tell(new Resultat(orgnr, new Identifisering(42)));
            } else {
               sender.tell(new Resultat(orgnr, new Identifisering(new Avvik("IDFEIL"))));
            }
            return null;
         }
      }, getContext().dispatcher());
      return null;
   }
}
