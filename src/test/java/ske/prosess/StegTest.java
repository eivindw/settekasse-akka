package ske.prosess;

import org.junit.Test;
import ske.prosess.melding.ProsessInfo;
import ske.prosess.steg.FeilendeSteg;
import ske.prosess.steg.OrdLengde;
import ske.prosess.steg.SetningOrdLengder;
import ske.prosess.steg.TregtIdSteg;

public class StegTest extends BaseTest {

   @Test
   public void enkeltsteg() {
      prosess.tell(new ProsessInfo(OrdLengde.class, "hei"));
   }

   @Test
   public void enkelProsess() {
      prosess.tell(new ProsessInfo(SetningOrdLengder.class, "Hei alle sammen! Denne setningen blir lang!!"));
   }

   @Test
   public void feilendeStegMedRekjoring() {
      prosess.tell(new ProsessInfo(FeilendeSteg.class, "hmm"));
   }

   @Test
   public void tregtAsynkrontSteg() {
      prosess.tell(new ProsessInfo(TregtIdSteg.class, "19107612345"));
   }
}
