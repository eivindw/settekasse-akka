package ske.prosess.domene;

import scala.actors.threadpool.Arrays;

import java.util.ArrayList;
import java.util.Collection;

public class Leveranse {

   private Collection<Oppgave> oppgaver = new ArrayList<>();

   public Collection<Oppgave> getOppgaver() {
      return oppgaver;
   }

   public Leveranse(Oppgave... oppgaver) {
      this.oppgaver.addAll(Arrays.asList(oppgaver));
   }
}
