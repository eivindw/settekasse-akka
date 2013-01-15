package ske.prosess.steg;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class SamlestegHub<T> extends UntypedActor implements Steg<T> {

   private final Props samlestegProps;

   public SamlestegHub(Props samlestegProps) {
      this.samlestegProps = samlestegProps;
   }

   @Override
   public void onReceive(Object message) throws Exception {
      final ActorRef samlesteg = getContext().actorOf(samlestegProps);
      //System.out.printf("## Hub %s created %s%n", getSelf().path(), samlesteg.path());
      samlesteg.forward(message, getContext());
   }
}
