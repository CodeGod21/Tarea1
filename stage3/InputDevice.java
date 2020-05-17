public abstract class InputDevice {
   public InputDevice (SkyController controller) {
      button=State.TAKE_OFF;
      this.controller=controller;
   }
   public abstract float getForwardPos();
   public abstract float getSidewaysPos();
   public abstract float getVerticalPos();
   public abstract float getRotationPos();

   public void pushTakeOff_Land () {
      switch (button) {
         case LANDED: button=State.FLYING; /* to be coded */
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