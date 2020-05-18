public class SkyController {
   public SkyController (Drone drone) {
      this.drone = drone;
      lStick=new Joystick();
      rStick=new Joystick();
   }
   public Joystick getLeftStick(){
      return lStick;
   }
   public Joystick getRightStick(){
      return rStick;
   }
   public void pushTakeOff_Land () {
      //hacemos que el drone empiece a funcionar.
      drone.takeOff();
      drone.takeAction(0);
      
      
   }
   public void takeAction(float time) {
      drone.setFlySpeed(lStick.getVerPos(), rStick.getVerPos(),rStick.getHorPos() );
      drone.setRotationSpeed(lStick.getHorPos());

   }

   public void pushLanding(){
      drone.land();
   }

   private Drone drone;
   private Joystick lStick, rStick;
}

