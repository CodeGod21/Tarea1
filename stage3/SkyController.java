public class SkyController implements Actionable {
   public SkyController (Drone drone) {
      this.drone = drone;
      button = State.TAKING_OFF;
     // lStick=new Joystick();
     // rStick=new Joystick();
     // button=false;
      // to be coded
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
      //drone.takeAction(0);
      //drone.fly();//vuela
      
   }
   public void takeAction(float time) {
      if(device.button==State.TAKING_OFF){
         this.drone.takeOff();
      }
      this.drone.setFlySpeed(device.getVerticalPos(), device.getForwardPos(),device.getSidewaysPos() );
      this.drone.setRotationSpeed(device.getRotationPos());

   }

   public void pushLanding(){
      drone.land();
   }

//   public void setButtomTrue(){
//      //cambiar estado dron a Takeoff
//      drone.takeOff();
//      button=true;
//   }
//   public void setButtomFalse(){
      //cambair a aterrizar
 //     drone.land();
 //     button=false;
 //  }

// public Boolean getButton(){
//   return button;
//   }
   private Drone drone;
   private Joystick lStick, rStick;
   private State button;
   private InputDevice device;
}

