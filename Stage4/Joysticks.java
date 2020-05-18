public class Joysticks extends InputDevice {
   public Joysticks (SkyController controller) {
      super(controller);
      this.controller=controller;
      rStick = new Joystick();
      lStick = new Joystick();
   }
  
   public float getForwardPos() {
      return rStick.getVerPos();
   }
   public float getSidewaysPos() {
      return rStick.getHorPos();
   }
   public float getVerticalPos() {
      return lStick.getVerPos();
   }
   public float getRotationPos(){
      return lStick.getHorPos();
   }
   public Joystick getLeftStick(){
      return lStick;
   }
   public Joystick getRightStick(){
      return rStick;
   }
   public void pushTakeOff (){
      this.controller.pushTakeOff_Land();
      
   }
   public void cambiobuttonaLANDING(){
      this.controller.pushLandingUSM();
   }
   private Joystick lStick, rStick;
   private SkyController controller;
}