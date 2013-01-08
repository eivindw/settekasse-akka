package ske.prosess;

import org.junit.Test;
import ske.prosess.definisjon.Prosessdefinisjon;
import ske.prosess.definisjon.Stegdefinisjon;
import ske.prosess.domene.Leveranse;
import ske.prosess.domene.Oppgave;
import ske.prosess.leveransebehandling.*;
import ske.prosess.melding.ProsessInfo;

public class ProsessmotorTest extends BaseTest {

   @Test
   public void skalKjoereEnkelprosess() {
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
                           steg(OppgaveFeltkontroll.class)).router(4),
                        steg(BestemOppgavestatus.class)).router(4))),
               steg(BestemLeveransestatus.class));
         }
      }, lagLeveranse()), null);
   }

   private Leveranse lagLeveranse() {
      return new Leveranse("123456789", new Oppgave("123"), new Oppgave("321"));
   }
}
