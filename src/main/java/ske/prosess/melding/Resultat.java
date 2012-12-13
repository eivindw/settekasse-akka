package ske.prosess.melding;

public final class Resultat extends Datamelding<Object> {

   private final Object key;

   public Resultat(Object key, Object data) {
      super(data);
      this.key = key;
   }

   public Object getKey() {
      return key;
   }

   @Override
   public String toString() {
      return key.toString() + "-" + super.toString();
   }
}
