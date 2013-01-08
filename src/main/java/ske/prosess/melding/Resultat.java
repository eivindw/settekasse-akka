package ske.prosess.melding;

public class Resultat<T> extends Datamelding<Object> {

   public Resultat(Object data) {
      super(data);
   }

   public void applyTo(T value) {
      System.out.println("[DEFAULT] Applying " + getData() + " to " + value);
   }

   public boolean isType(Class clazz) {
      return clazz.isInstance(getData());
   }
}
