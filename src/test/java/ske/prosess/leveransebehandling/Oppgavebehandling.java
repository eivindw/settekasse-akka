package ske.prosess.leveransebehandling;

import ske.prosess.Steg;
import ske.prosess.domene.Identifisering;
import ske.prosess.domene.Leveranse;
import ske.prosess.domene.Oppgave;
import ske.prosess.melding.Resultat;

public class Oppgavebehandling extends Steg<Leveranse, String> {

   private Leveranse leveranse;
   private int resultater = 0;

   @Override
   protected String behandle(Leveranse leveranse) {
      this.leveranse = leveranse;
      for(Oppgave oppgave : leveranse.getOppgaver()) {
         behandleDelsteg(IdentifisereOppgaveeier.class, oppgave);
         resultater++;
      }
      return null;
   }

   @Override
   protected boolean delresultat(Resultat resultat) {
      leveranse.leggTilOppgaveIdentifisering((String) resultat.getKey(), (Identifisering) resultat.getData());
      return --resultater == 0;
   }

   @Override
   protected String resultat() {
      return "OK";
   }
}
