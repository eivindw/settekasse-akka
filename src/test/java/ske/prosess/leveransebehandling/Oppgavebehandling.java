package ske.prosess.leveransebehandling;

import akka.actor.UntypedActor;
import ske.prosess.domene.Leveranse;
import ske.prosess.steg.Steg;

public class Oppgavebehandling extends UntypedActor implements Steg<Leveranse> {

   @Override
   public void onReceive(Object message) {
   }
}
