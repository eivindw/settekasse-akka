package ske.prosess.domene;

import scala.Either;
import scala.Left;
import scala.Right;

public class Identifisering {

   private Either<Long, Avvik> verdi;

   public Identifisering(long nr) {
      verdi = new Left<>(nr);
   }

   public Identifisering(Avvik avvik) {
      verdi = new Right<>(avvik);
   }

   public long getNr() {
      return verdi.left().get();
   }

   public Avvik getAvvik() {
      return verdi.right().get();
   }

   public boolean harAvvik() {
      return verdi.isRight();
   }

   @Override
   public String toString() {
      return "Verdi=" + verdi.toString();
   }
}
