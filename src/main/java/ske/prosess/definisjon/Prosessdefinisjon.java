package ske.prosess.definisjon;

import ske.prosess.steg.Steg;

public abstract class Prosessdefinisjon<T> {

   public abstract Stegdefinisjon<T> lagToppsteg();

   protected <X> Stegdefinisjon<X> endesteg(Class<? extends Steg<X>> stegklasse) {
      return new Endestegdefinisjon<>(stegklasse);
   }

   protected <X> Oppdelingsstegdefinisjon<X> oppdeling(Class<? extends Steg<X>> stegklasse) {
      return new Oppdelingsstegdefinisjon<>(stegklasse);
   }

   @SafeVarargs
   protected final <X> Stegdefinisjon<X> seriell(Stegdefinisjon<X>... stegdefinisjoner) {
      return new SeriellSamlestegdefinisjon<>(stegdefinisjoner);
   }

   @SafeVarargs
   protected final <X> Stegdefinisjon<X> parallell(Stegdefinisjon<X>... stegdefinisjoner) {
      return new ParallellSamlestegdefinisjon<>(stegdefinisjoner);
   }
}
