package ske.prosess.leveransebehandling;

import ske.prosess.Steg;
import ske.prosess.domene.Identifisering;
import ske.prosess.domene.Leveranse;
import ske.prosess.melding.Resultat;

import java.util.ArrayList;
import java.util.List;

public class TestProsessdefinisjon extends Steg<Leveranse, Leveranse> {

   private Leveranse leveranse;
   private List<Class<? extends Steg<Leveranse, ?>>> stegliste = new ArrayList<>();
   private int resultater = 0;

   public TestProsessdefinisjon() {
      stegliste.add(IdentifisereOppgavegiver.class);
      stegliste.add(Oppgavebehandling.class);
   }

   @Override
   protected Leveranse behandle(Leveranse leveranse) {
      this.leveranse = leveranse;

      for(Class<? extends Steg<Leveranse, ?>> stegklasse : stegliste) {
         behandleDelsteg(stegklasse, leveranse);
      }

      return null;
   }

   @Override
   protected boolean delresultat(Resultat resultat) {
      if(resultat.getData() instanceof Identifisering) {
         leveranse.leggTilIdentifisering((Identifisering) resultat.getData());
      }
      return ++resultater == stegliste.size();
   }

   @Override
   protected Leveranse resultat() {
      return leveranse;
   }
}
