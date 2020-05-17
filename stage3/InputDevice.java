public abstract class InputDevice {
   public InputDevice (SkyController controller) {
      button=State.LANDED;
      this.controller=controller;
   }
   public abstract float getForwardPos();
   public abstract float getSidewaysPos();
   public abstract float getVerticalPos();
   public abstract float getRotationPos();

   public void pushTakeOff_Land () {
      System.out.println("entra pushtake off inputdevice");
      System.out.println("boton estaba: "+button);
      switch (button) {
         case LANDED: button=State.TAKING_OFF;
         System.out.println("boton cambió a: "+button);/* to be coded */
         case FLYING: button=State.LANDED; /* to be coded */
      }
      //button = button==State.TAKE_OFF?State.LANDING:State.TAKE_OFF;
   }
 //  public boolean isWaitingToTakeOff () {
 //     return button == State.TAKE_OFF;
 //  }
   protected State button;
   private SkyController controller;
}