package ske.prosess.melding;

public final class ProsessInfo {

   private final Class stegklasse;
   private final Object input;

   public ProsessInfo(Class stegklasse, Object input) {
      this.stegklasse = stegklasse;
      this.input = input;
   }

   public Class getStegklasse() {
      return stegklasse;
   }

   public Input getInput() {
      return new Input<>(input);
   }
}
