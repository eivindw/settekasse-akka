package ske.prosess.leveransebehandling;

import ske.prosess.domene.Avvik;
import ske.prosess.domene.Identifisering;
import ske.prosess.domene.Oppgave;
import ske.prosess.melding.Resultat;
import ske.prosess.steg.AsynkrontEndesteg;

import java.util.Random;

public class IdentifisereOppgaveeier extends AsynkrontEndesteg<Oppgave> {

   @Override
   protected Resultat<Oppgave> behandle(Oppgave input) throws Exception {
      Thread.sleep(500);
      final Identifisering identifisering = new Random().nextBoolean() ? new Identifisering(134) : new Identifisering(new Avvik("IDFEIL"));
      return new Resultat<Oppgave>(identifisering) {
         @Override
         public void applyTo(Oppgave oppg) {
            oppg.leggTilIdentifisering(identifisering);
         }
      };
   }
}
