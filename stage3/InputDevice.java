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
     
      switch (button) {
         case LANDED: button=State.TAKING_OFF;
         case FLYING: button=State.LANDED; 
      }
     
   }
 
   protected State button;
   private SkyController controller;
}