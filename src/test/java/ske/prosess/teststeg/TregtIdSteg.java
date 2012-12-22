package ske.prosess.teststeg;

import akka.dispatch.Futures;
import ske.prosess.Steg;
import ske.prosess.melding.Resultat;

import java.util.concurrent.Callable;

public class TregtIdSteg extends Steg<String, Long> {

   private Long id;

   @Override
   protected Long behandle(final String context) {
      Futures.future(new Callable<Void>() {
         @Override
         public Void call() throws Exception {
            Thread.sleep(500);
            getSelf().tell(new Resultat(context, 42L));
            return null;
         }
      }, getContext().dispatcher());
      return null;
   }

   @Override
   protected boolean delresultat(Resultat resultat) {
      id = (Long) resultat.getData();
      return true;
   }

   @Override
   protected Long resultat() {
      return id;
   }
}
