package ske.prosess.domene;

import java.util.ArrayList;
import java.util.Collection;

public class Oppgave {

   private enum Status {
      OPPRETTET,
      GODKJENT,
      AVVIST
   }

   private String oppgaveeierFnr;
   private Long oppgaveeierId;
   private Collection<Avvik> avviksliste = new ArrayList<>();
   private Status status = Status.OPPRETTET;

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

   public void bestemStatus() {
      if(avviksliste.isEmpty()) {
         status = Status.GODKJENT;
      } else {
         status = Status.AVVIST;
      }
   }

   @Override
   public String toString() {
      String out = "Oppgave@" + Integer.toHexString(hashCode());
      out += " status=" + status;
      out += " oppgaveeierId=" + oppgaveeierId;
      out += " avviksliste=" + avviksliste;
      return out;
   }
}
