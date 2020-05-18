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
      button=State.TAKING_OFF;
     // drone.takeAction(0);
      //drone.fly();//vuela
      
   }
   public void takeAction(float time) {
     // System.out.println("DEVICE.BUTTON: "+device.button);
      if(device.button==State.TAKING_OFF){
         this.drone.takeOff();
      }
      System.out.println("pepepeppe"+device.getForwardPos());
      this.drone.setFlySpeed(device.getVerticalPos(), device.getForwardPos(),device.getSidewaysPos() );
      this.drone.setRotationSpeed(device.getRotationPos());

   }

   public void pushLanding(){
      System.out.println("ELLLLLLLLL ESTADOOOOOOOO ES: "+device.button);
     // System.out.println("DEVICE.BUTTON: "+device.button);
     if(device.button==State.FLYING){
      device.button=State.LANDING;
      drone.land();
   }
      if (device.button==State.LANDED){
         device.button=State.FLYING;
         drone.takeOff();
      //Cambiara los bottones de State y tambien cambiara al drone

      }
      
   }
   public void pushLandingUSM(){
      //System.out.println("ELLLLLLLLL ESTADOOOOOOOO ES: "+device.button);
      
     
         System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
         device.button=State.LANDING;
         drone.land();
         device.button=State.LANDED;
     
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

