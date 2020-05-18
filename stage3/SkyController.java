public class SkyController implements Actionable {
   public SkyController (Drone drone) {
      this.drone = drone;
      button = State.TAKING_OFF;
     
   }

   public void setInputDevice(InputDevice device){
      this.device = device;
   }

   public Joystick getLeftStick(){
      return lStick;
   }
   public Joystick getRightStick(){
      return rStick;
   }
   public void pushTakeOff_Land () {
      drone.takeOff();
      button=State.TAKING_OFF;
      drone.takeAction(0);

      
   }
   public void takeAction(float time) {
     
      if(device.button==State.TAKING_OFF){
         this.drone.takeOff();
      }
      this.drone.setFlySpeed(device.getVerticalPos(), device.getForwardPos(),device.getSidewaysPos() );
      this.drone.setRotationSpeed(device.getRotationPos());

   }

   public void pushLanding(){
     
      if (device.button==State.LANDED){
         device.button=State.FLYING;
         drone.takeOff();
         //Cambiara los bottones de State y tambien cambiara al drone

      }
      else if(device.button==State.FLYING){
         device.button=State.LANDING;
         drone.land();
      }
   }


   private Drone drone;
   private Joystick lStick, rStick;
   private State button;
   private InputDevice device;
}

