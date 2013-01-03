package ske.prosess.leveransebehandling;

import ske.prosess.domene.Oppgave;
import ske.prosess.melding.Resultat;
import ske.prosess.steg.SynkrontEndesteg;

public class OppgaveFeltkontroll extends SynkrontEndesteg<Oppgave, Oppgave> {

   @Override
   protected Resultat<Oppgave> behandle(Oppgave input) {
      return new Resultat<>("felt", "data");
   }
}
