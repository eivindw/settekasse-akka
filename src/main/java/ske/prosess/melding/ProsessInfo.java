package ske.prosess.melding;

import ske.prosess.definisjon.Prosessdefinisjon;
import ske.prosess.definisjon.Stegdefinisjon;

public final class ProsessInfo {

   private final Prosessdefinisjon prosessdefinisjon;
   private final Object input;

   public ProsessInfo(final Prosessdefinisjon prosessdefinisjon, final Object input) {
      this.prosessdefinisjon = prosessdefinisjon;
      this.input = input;
   }

   public ProsessInfo(final Class stegklasse, final Object input) {
      prosessdefinisjon = new Prosessdefinisjon() {
         @Override
         public Stegdefinisjon lagToppsteg() {
            return endesteg(stegklasse);
         }
      };
      this.input = input;
   }

   public Prosessdefinisjon getProsessdefinisjon() {
      return prosessdefinisjon;
   }

   public Input getInput() {
      return new Input<>(input);
   }
}
