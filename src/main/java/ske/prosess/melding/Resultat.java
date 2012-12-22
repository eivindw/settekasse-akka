package ske.prosess.melding;

public class Resultat<T> extends Datamelding<Object> {

   private final Object key;

   public Resultat(Object key, Object data) {
      super(data);
      this.key = key;
   }

   public Object getKey() {
      return key;
   }

   public void applyTo(T value) {
      System.out.println("Applying " + toString() + " to " + value);
   }

   @Override
   public String toString() {
      return key.toString() + "-" + super.toString();
   }
}
