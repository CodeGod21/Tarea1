public class SkyController {
   public SkyController (Drone drone) {
      this.drone = drone;
      lStick=new Joystick();
      rStick=new Joystick();
      //button=State.LANDED();
      // to be coded
   }
   public Joystick getLeftStick(){
      return lStick;
   }
   public Joystick getRightStick(){
      return rStick;
   }
   public void pushTakeOff_Land () {
      drone.takeOff();
      drone.takeAction(0);
      //drone.fly();//vuela
      
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
   //private State button;
}

