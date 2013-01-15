package ske.prosess.melding;

public class Resultat<T> extends Datamelding<Object> {

   public Resultat(Object data) {
      super(data);
   }

   public void applyTo(T value) {
      throw new RuntimeException("[DEFAULT] Applying " + getData() + " to " + value + " has no effect!");
   }

   public boolean isType(Class clazz) {
      return clazz.isInstance(getData());
   }
}
