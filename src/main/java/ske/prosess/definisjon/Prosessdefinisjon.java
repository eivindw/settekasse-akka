package ske.prosess.definisjon;

import ske.prosess.steg.Steg;

public abstract class Prosessdefinisjon<T> {

   public abstract Stegdefinisjon<T> lagToppsteg();

   protected <X> KlasseStegdefinisjon<X> steg(Class<? extends Steg<X>> stegklasse) {
      return new KlasseStegdefinisjon<>(stegklasse);
   }

   @SafeVarargs
   protected final <X> SeriellSamlestegdefinisjon<X> seriell(Stegdefinisjon<X>... stegdefinisjoner) {
      return new SeriellSamlestegdefinisjon<>(stegdefinisjoner);
   }

   @SafeVarargs
   protected final <X> ParallellSamlestegdefinisjon<X> parallell(Stegdefinisjon<X>... stegdefinisjoner) {
      return new ParallellSamlestegdefinisjon<>(stegdefinisjoner);
   }
}
