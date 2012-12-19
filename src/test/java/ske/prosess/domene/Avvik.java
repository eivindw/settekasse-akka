package ske.prosess.domene;

public class Avvik {
   private final String melding;

   public Avvik(String melding) {
      this.melding = melding;
   }

   @Override
   public String toString() {
      return "Melding=" + melding;
   }
}
