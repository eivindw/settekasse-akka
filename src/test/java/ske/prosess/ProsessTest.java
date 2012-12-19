package ske.prosess;

import org.junit.Test;
import ske.prosess.domene.Leveranse;
import ske.prosess.domene.Oppgave;
import ske.prosess.leveransebehandling.TestProsessdefinisjon;
import ske.prosess.melding.ProsessInfo;

public class ProsessTest extends BaseTest {

   @Test
   public void kjoerProsess() {
      prosess.tell(new ProsessInfo(TestProsessdefinisjon.class, lagLeveranse()));
      prosess.tell(new ProsessInfo(TestProsessdefinisjon.class, lagLeveranse()));
      prosess.tell(new ProsessInfo(TestProsessdefinisjon.class, lagLeveranse()));
   }

   private Leveranse lagLeveranse() {
      return new Leveranse("123456789", new Oppgave("123"), new Oppgave("321"));
   }
}
