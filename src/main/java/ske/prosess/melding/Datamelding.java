package ske.prosess.melding;

public abstract class Datamelding<D> {

   private D data;

   protected Datamelding(D data) {
      this.data = data;
   }

   public D getData() {
      return data;
   }

   @Override
   public String toString() {
      return data.toString();
   }
}
