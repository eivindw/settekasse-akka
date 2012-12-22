package ske.prosess;

import org.junit.Test;
import scala.actors.threadpool.Arrays;
import ske.prosess.domene.Leveranse;
import ske.prosess.leveransebehandling.IdentifisereOppgavegiver;
import ske.prosess.melding.ProsessInfo;
import ske.prosess.melding.Resultat;

import java.util.ArrayList;
import java.util.List;

public class ProsessdefinisjonsTest extends BaseTest {

   @Test
   public void skalKunneDefinereProsesser() {
      prosessmotor.tell(new ProsessInfo(LeveranseProsessdefinisjon.class, new Leveranse("123")));
   }

   public static class LeveranseProsessdefinisjon extends Prosessdefinisjon<Leveranse> {

      public LeveranseProsessdefinisjon() {
         super(
            IdentifisereOppgavegiver.class
         );
      }
   }

   public static class Prosessdefinisjon<T> extends Steg<T, String> {

      private List<Class<? extends Steg<T, ?>>> stegklasser = new ArrayList<>();
      private int forventetAntallResultater = 0;
      private T context;

      @SafeVarargs
      protected Prosessdefinisjon(Class<? extends Steg<T, ?>>... steg) {
         stegklasser.addAll(Arrays.asList(steg));
      }

      @Override
      protected String behandle(T context) {
         this.context = context;
         for(Class<? extends Steg<T, ?>> stegklasse : stegklasser) {
            behandleDelsteg(stegklasse, context);
            forventetAntallResultater++;
         }
         return null;
      }

      @Override
      protected boolean delresultat(Resultat resultat) {
         System.out.println("delresultat: " + resultat);
         return --forventetAntallResultater == 0;
      }

      @Override
      protected String resultat() {
         return "OK";
      }
   }
}
