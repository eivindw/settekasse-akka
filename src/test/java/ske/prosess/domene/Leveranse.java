package ske.prosess.domene;

import scala.actors.threadpool.Arrays;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Leveranse {

   private UUID id;
   private String oppgavegiverOrgnr;
   private Long oppgavegiverId;
   private Collection<Oppgave> oppgaver = new ArrayList<>();
   private Collection<Avvik> avviksliste = new ArrayList<>();

   public Collection<Oppgave> getOppgaver() {
      return oppgaver;
   }

   public Leveranse(String oppgavegiverOrgnr, Oppgave... oppgaver) {
      id = UUID.randomUUID();
      this.oppgavegiverOrgnr = oppgavegiverOrgnr;
      this.oppgaver.addAll(Arrays.asList(oppgaver));
   }

   public String getOppgavegiverOrgnr() {
      return oppgavegiverOrgnr;
   }

   public void leggTilIdentifisering(Identifisering identifisering) {
      if(identifisering.harAvvik()) {
         avviksliste.add(identifisering.getAvvik());
      } else {
         oppgavegiverId = identifisering.getNr();
      }
   }

   @Override
   public String toString() {
      String out = "Leveranse@" + id.toString().substring(0, 8);
      if(oppgavegiverId != null) {
         out += " oppgavegiverId=" + oppgavegiverId;
      }
      out += " avviksliste=" + avviksliste;
      out += " oppgaver=" + oppgaver;
      return out;
   }

   public void leggTilOppgaveIdentifisering(String fnr, Identifisering identifisering) {
      for(Oppgave oppgave : oppgaver) {
         if(oppgave.getOppgaveeierFnr().equals(fnr)) {
            oppgave.leggTilIdentifisering(identifisering);
            return;
         }
      }
   }
}
