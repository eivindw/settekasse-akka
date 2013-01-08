package ske.prosess.definisjon;

import ske.prosess.steg.ParalleltSamlesteg;
import ske.prosess.steg.SerieltSamlesteg;
import ske.prosess.steg.Steg;

public abstract class Prosessdefinisjon<T> {

   public abstract Stegdefinisjon<T> lagToppsteg();

   protected <X> KlasseStegdefinisjon<X> steg(Class<? extends Steg<X>> stegklasse) {
      return new KlasseStegdefinisjon<>(stegklasse);
   }

   @SafeVarargs
   protected final <X> Samlestegdefinisjon<X> seriell(Stegdefinisjon<X>... stegdefinisjoner) {
      return new Samlestegdefinisjon<>(SerieltSamlesteg.class, stegdefinisjoner);
   }

   @SafeVarargs
   protected final <X> Samlestegdefinisjon<X> parallell(Stegdefinisjon<X>... stegdefinisjoner) {
      return new Samlestegdefinisjon<>(ParalleltSamlesteg.class, stegdefinisjoner);
   }
}
