package ske.prosess.domene;

import java.util.ArrayList;
import java.util.Collection;

public class Oppgave {

   private String oppgaveeierFnr;
   private Long oppgaveeierId;
   private Collection<Avvik> avviksliste = new ArrayList<>();

   public Oppgave(String oppgaveeierFnr) {
      this.oppgaveeierFnr = oppgaveeierFnr;
   }

   public String getOppgaveeierFnr() {
      return oppgaveeierFnr;
   }

   public void leggTilIdentifisering(Identifisering identifisering) {
      if(identifisering.harAvvik()) {
         avviksliste.add(identifisering.getAvvik());
      } else {
         oppgaveeierId = identifisering.getNr();
      }
   }

   @Override
   public String toString() {
      String out = "Oppgave@" + Integer.toHexString(hashCode());
      if(oppgaveeierId != null) {
         out += " oppgaveeierId=" + oppgaveeierId;
      }
      out += " avviksliste=" + avviksliste;
      return out;
   }
}
