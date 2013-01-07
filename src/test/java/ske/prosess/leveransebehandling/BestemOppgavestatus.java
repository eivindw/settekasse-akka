package ske.prosess.leveransebehandling;

import ske.prosess.domene.Oppgave;
import ske.prosess.steg.SynkrontEndesteg;
import ske.prosess.melding.Resultat;

public class BestemOppgavestatus extends SynkrontEndesteg<Oppgave> {

   @Override
   public Resultat<Oppgave> behandle(Oppgave oppgave) {
      oppgave.bestemStatus();
      return new Resultat<>("", oppgave);
   }
}
