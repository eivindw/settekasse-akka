package ske.prosess;

import org.junit.Test;
import ske.prosess.melding.ProsessInfo;
import ske.prosess.teststeg.FeilendeSteg;
import ske.prosess.teststeg.OrdLengde;
import ske.prosess.teststeg.SetningOrdLengder;
import ske.prosess.teststeg.TregtIdSteg;

public class StegTest extends BaseTest {

   @Test
   public void enkeltsteg() {
      prosessmotor.tell(new ProsessInfo(OrdLengde.class, "hei"));
   }

   @Test
   public void enkelProsess() {
      prosessmotor.tell(new ProsessInfo(SetningOrdLengder.class, "Hei alle sammen! Denne setningen blir lang!!"));
   }

   @Test
   public void feilendeStegMedRekjoring() {
      prosessmotor.tell(new ProsessInfo(FeilendeSteg.class, "hmm"));
   }

   @Test
   public void tregtAsynkrontSteg() {
      prosessmotor.tell(new ProsessInfo(TregtIdSteg.class, "19107612345"));
   }
}
