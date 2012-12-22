package ske.prosess;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import org.junit.Test;
import ske.prosess.melding.Input;
import ske.prosess.melding.ProsessInfo;
import ske.prosess.melding.Resultat;

public class StegInputResultatTest extends BaseTest {

   @Test
   public void skalKunneTrekkeUtInput() {
      prosessmotor.tell(new ProsessInfo(Valuebehandler.class, new Value("hei")));
   }

   public static class Value {
      private String strval;

      public Value(String strval) {
         this.strval = strval;
      }

      @Override
      public String toString() {
         return "Value=" + strval;
      }
   }

   public static class Valuebehandler extends UntypedActor {

      private Value currentValue;
      private ActorRef sender;

      @Override
      public void onReceive(Object message) throws Exception {
         if(message instanceof Input) {
            Input<Value> input = (Input<Value>) message;
            currentValue = input.getData();
            ActorRef steg = getContext().actorOf(new Props(InputResultatSteg.class));
            steg.tell(input, getSelf());
            sender = getSender();
         } else if (message instanceof Resultat) {
            Resultat<Value> resultat = (Resultat<Value>) message;
            resultat.applyTo(currentValue);
            sender.tell(new Resultat("1", currentValue));
         } else {
            System.out.println("Ukjent melding: " + message);
         }
      }
   }

   public static class InputResultatSteg extends UntypedActor {

      private Character behandle(String ctx) {
         return ctx.substring(0, 1).toUpperCase().charAt(0);
      }

      private String hentInput(Value value) {
         return value.strval;
      }

      private Resultat<Value> lagResultat(final Character resultat) {
         return new Resultat<Value>("1", resultat) {
            @Override
            public void applyTo(Value value) {
               value.strval = getData() + value.strval.substring(1, value.strval.length());
            }
         };
      }

      @Override
      public void onReceive(Object message) throws Exception {
         if(message instanceof Input) {
            System.out.println("Input: " + message);
            Input<Value> input = (Input<Value>) message;
            getSender().tell(lagResultat(behandle(hentInput(input.getData()))));
         } else {
            unhandled(message);
         }
      }
   }
}
