package ske.prosess.definisjon;

import org.junit.Test;
import ske.prosess.domene.Leveranse;
import ske.prosess.leveransebehandling.*;

import static junit.framework.Assert.assertNotNull;

public class DefinisjonTest {

   @Test
   public void skalKunneDefinereProsessMedSteg() {
      Prosessdefinisjon def = new TestProsessDefinisjon();

      Stegdefinisjon toppsteg = def.lagToppsteg();

      assertNotNull(toppsteg);
   }

   private static class TestProsessDefinisjon extends Prosessdefinisjon<Leveranse> {
      @Override
      public Stegdefinisjon<Leveranse> lagToppsteg() {
         return
            seriell(
               parallell(
                  steg(IdentifisereOppgavegiver.class),
                  steg(Oppgavebehandling.class).med(
                     seriell(
                        parallell(
                           steg(IdentifisereOppgaveeier.class),
                           steg(OppgaveFeltkontroll.class)
                        ),
                        steg(BestemOppgavestatus.class)
                     )
                  )
               ),
               steg(BestemLeveransestatus.class)
            );
      }
   }
}
