package ske.prosess.definisjon;

import eivindw.definition.ProcessDefinition;
import eivindw.definition.StepDefinition;
import org.junit.Test;
import ske.prosess.domene.Leveranse;
import ske.prosess.leveransebehandling.*;

import static org.junit.Assert.assertNotNull;

public class DefinisjonTest {

   @Test
   public void skalKunneDefinereProsessMedSteg() {
      ProcessDefinition def = new TestProsessDefinisjon();

      StepDefinition toppsteg = def.createTopLevelStep();

      assertNotNull(toppsteg);
   }

   private static class TestProsessDefinisjon extends ProcessDefinition<Leveranse> {
      @Override
      public StepDefinition<Leveranse> createTopLevelStep() {
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
