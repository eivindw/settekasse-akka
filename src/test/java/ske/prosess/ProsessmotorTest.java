package ske.prosess;

import akka.actor.*;
import eivindw.definition.ProcessDefinition;
import eivindw.definition.StepDefinition;
import eivindw.messages.ProsessInfo;
import eivindw.messages.Result;
import eivindw.steps.SynchronousEndStep;
import org.junit.Test;
import ske.prosess.domene.Leveranse;
import ske.prosess.domene.Oppgave;
import ske.prosess.leveransebehandling.*;

import java.util.concurrent.CountDownLatch;

public class ProsessmotorTest extends BaseTest {

   private static final int ANTALL_OPPGAVER = 1000;

   @Test
   public void skalKjoereEnkelprosess() throws InterruptedException {
      final CountDownLatch waiter = new CountDownLatch(1);

      prosessmotor.tell(new ProsessInfo(new ProcessDefinition<Leveranse>() {
         @Override
         public StepDefinition<Leveranse> createTopLevelStep() {
            return
               seriell(
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
      return new Leveranse(oppgaver);
   }

   private static class WaiterStegdefinisjon extends StepDefinition<Leveranse> {
      private final CountDownLatch waiter;

      private WaiterStegdefinisjon(CountDownLatch waiter) {
         this.waiter = waiter;
      }

      @Override
      public ActorRef toActor(ActorRefFactory context) {
         return context.actorOf(new Props(new UntypedActorFactory() {
            @Override
            public Actor create() throws Exception {
               return new SynchronousEndStep<Leveranse>() {
                  @Override
                  protected Result<Leveranse> handle(Leveranse input) {
                     return new Result<Leveranse>("Done!") {
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
