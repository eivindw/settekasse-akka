package ske.prosess;

import ske.prosess.domene.Leveranse;

public class TestProsessdefinisjon extends Steg<Leveranse, String> {

   @Override
   protected String behandle(Leveranse leveranse) {
      /*super(
         Innsendingbehandling.med(
            IdentifisereInnsender.class),
         Filbehandling.med(
            Filkontroll.class,
            OpprettLeveranserOgOppgaver.class),
         Leveransebehandling.med(
            IdentifisereOppgavegiver.class,
            Oppgavebehandling.med(
               IdentifisereOppgaveeier.class,
               Oppgavekontroll.class,
               BestemOppgavestatus.class),
            Avstemmingskontroll.class,
            Sannsynlighetskontroll.class,
            BestemLeveransestatus.class)
      );*/
      return "OK";
   }
}
