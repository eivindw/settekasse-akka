package ske.prosess;

import org.junit.Test;
import ske.prosess.domene.Leveranse;
import ske.prosess.domene.Oppgave;
import ske.prosess.melding.ProsessInfo;

public class ProsessTest extends BaseTest {

   @Test
   public void kjoerProsess() {
      Leveranse leveranse = new Leveranse(new Oppgave(), new Oppgave());

      prosess.tell(new ProsessInfo(TestProsessdefinisjon.class, leveranse));
   }
}
