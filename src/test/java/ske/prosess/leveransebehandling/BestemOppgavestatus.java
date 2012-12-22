package ske.prosess.leveransebehandling;

import ske.prosess.Steg;
import ske.prosess.domene.Oppgave;

public class BestemOppgavestatus extends Steg<Oppgave, Oppgave> {

   @Override
   protected Oppgave behandle(Oppgave oppgave) {
      oppgave.bestemStatus();
      return oppgave;
   }
}
