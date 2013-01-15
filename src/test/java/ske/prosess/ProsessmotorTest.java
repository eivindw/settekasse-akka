package ske.prosess;

import akka.actor.*;
import org.junit.Test;
import ske.prosess.definisjon.Prosessdefinisjon;
import ske.prosess.definisjon.Stegdefinisjon;
import ske.prosess.domene.Leveranse;
import ske.prosess.domene.Oppgave;
import ske.prosess.leveransebehandling.*;
import ske.prosess.melding.ProsessInfo;
import ske.prosess.melding.Resultat;
import ske.prosess.steg.SynkrontEndesteg;

import java.util.concurrent.CountDownLatch;

public class ProsessmotorTest extends BaseTest {

   private static final int ANTALL_OPPGAVER = 1000;

   @Test
   public void skalKjoereEnkelprosess() throws InterruptedException {
      final CountDownLatch waiter = new CountDownLatch(1);

      prosessmotor.tell(new ProsessInfo(new Prosessdefinisjon<Leveranse>() {
         @Override
         public Stegdefinisjon<Leveranse> lagToppsteg() {
            return seriell(
               parallell(
                  steg(IdentifisereOppgavegiver.class),
                  steg(LeveranseFeltkontroll.class),
                  steg(Oppgavebehandling.class).med(
                     seriell(
                        parallell(
                           steg(IdentifisereOppgaveeier.class),
                           steg(OppgaveFeltkontroll.class)),
                        steg(BestemOppgavestatus.class)))),
               steg(BestemLeveransestatus.class),
               new WaiterStegdefinisjon(waiter));
         }
      }, lagLeveranse()), null);

      waiter.await();
   }

   private Leveranse lagLeveranse() {
      Oppgave[] oppgaver = new Oppgave[ANTALL_OPPGAVER];
      for(int i = 0; i < ANTALL_OPPGAVER; i++) {
         oppgaver[i] = new Oppgave(String.valueOf(i));
      }
      return new Leveranse("123456789", oppgaver);
   }

   private static class WaiterStegdefinisjon extends Stegdefinisjon<Leveranse> {
      private final CountDownLatch waiter;

      private WaiterStegdefinisjon(CountDownLatch waiter) {
         this.waiter = waiter;
      }

      @Override
      public ActorRef tilActor(ActorRefFactory context) {
         return context.actorOf(new Props(new UntypedActorFactory() {
            @Override
            public Actor create() throws Exception {
               return new SynkrontEndesteg<Leveranse>() {
                  @Override
                  protected Resultat<Leveranse> behandle(Leveranse input) {
                     return new Resultat<Leveranse>("Done!") {
                        @Override
                        public void applyTo(Leveranse value) {
                           System.out.println("Waiter " + getData());
                           waiter.countDown();
                        }
                     };
                  }
               };
            }
         }));
      }
   }
}
