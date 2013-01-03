package ske.prosess.leveransebehandling;

import ske.prosess.domene.Avvik;
import ske.prosess.domene.Identifisering;
import ske.prosess.domene.Oppgave;
import ske.prosess.steg.AsynkrontEndesteg;
import ske.prosess.melding.Resultat;

import java.util.Random;

public class IdentifisereOppgaveeier extends AsynkrontEndesteg<Oppgave, Identifisering> {

   @Override
   protected Identifisering behandleInput(final Oppgave input) throws Exception {
      Thread.sleep(500);
      return new Random().nextBoolean() ? new Identifisering(134) : new Identifisering(new Avvik("IDFEIL"));
   }

   @Override
   protected Resultat<Oppgave> lagResultat(final Oppgave input, final Identifisering resultat) {
      return new Resultat<Oppgave>(input.getOppgaveeierFnr(), resultat) {
         @Override
         public void applyTo(Oppgave oppg) {
            oppg.leggTilIdentifisering(resultat);
         }
      };
   }
}
