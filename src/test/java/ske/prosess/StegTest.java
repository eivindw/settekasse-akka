package ske.prosess;

import eivindw.messages.ProsessInfo;
import org.junit.Test;
import ske.prosess.teststeg.FeilendeSteg;
import ske.prosess.teststeg.OrdLengde;
import ske.prosess.teststeg.TregtIdSteg;

public class StegTest extends BaseTest {

   @Test
   public void enkeltsteg() {
      prosessmotor.tell(new ProsessInfo(OrdLengde.class, "hei"), null);
   }

   @Test
   public void feilendeStegMedRekjoring() {
      prosessmotor.tell(new ProsessInfo(FeilendeSteg.class, "hmm"), null);
   }

   @Test
   public void tregtAsynkrontSteg() throws InterruptedException {
      prosessmotor.tell(new ProsessInfo(TregtIdSteg.class, "19107612345"), null);
      Thread.sleep(550);
   }
}
