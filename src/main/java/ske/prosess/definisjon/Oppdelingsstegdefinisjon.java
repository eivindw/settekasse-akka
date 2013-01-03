package ske.prosess.definisjon;

import ske.prosess.steg.Steg;

public final class Oppdelingsstegdefinisjon<T> extends Endestegdefinisjon<T> {

   protected Oppdelingsstegdefinisjon(Class<? extends Steg<T>> stegklasse) {
      super(stegklasse);
   }

   public Stegdefinisjon<T> med(Stegdefinisjon... stegdefinisjoner) {
      return this;
   }
}
