package ske.prosess.definisjon;

import akka.actor.ActorRef;
import akka.actor.ActorRefFactory;

public abstract class Stegdefinisjon<T> {
   public abstract ActorRef tilActor(ActorRefFactory context);
}
