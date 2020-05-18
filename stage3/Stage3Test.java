import java.io.*;
import java.util.Scanner;
import java.util.Locale;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class Stage3Test  {
   static {
      Locale.setDefault(Locale.US);
      t0=System.currentTimeMillis();  // time in ms since app 1970.
   }
   public static long t0; 
   public static void main (String[] arg) throws IOException {
      Scanner in = new Scanner(new File(arg[0]));
      float time, nextPrintTime;
            time=nextPrintTime = 0;
      Drone drone = new Drone();
      SkyController skyController = new SkyController(drone);
      Joysticks joysticks = new Joysticks(skyController);
      skyController.setInputDevice(joysticks);     
      Operator operator = new Operator(in, joysticks);
           
      ArrayList<Actionable> actionables = new ArrayList<Actionable>();
      actionables.add(operator);
      actionables.add(skyController);
      actionables.add(drone);

      skyController.pushTakeOff_Land ();//aqui deberia subir hasta 10m 


      do {
         for( Actionable device: actionables){
            device.takeAction(time);
         }
         
         System.out.println(String.format("%.1f",time)+ ",\t"+drone.toString());
         sleepFor(0.1f);  // let 0.1 [s] pass to run at real time.
         time+=0.1;
         
      } while (drone.getState()!=State.LANDED);


      System.out.println(time+ ",\t"+drone.toString());
      Keyboard keyboard = new Keyboard(skyController);
      skyController.setInputDevice(keyboard); // we switch to another input device
      actionables.remove(operator);  // stop reading automatically from file
      actionables.add(keyboard);  // start reading from keyboard
      System.out.println("Get ready to control the drone. Now you are the pilot.");
      do { // wait until the user hits space key (to take-off)
         for( Actionable device: actionables){
            
            device.takeAction(time);
         }
         sleepFor(0.1f);  // users need to run at real time (not at simulation time)
         time=getCurrentTime();
      } while (drone.getState()!=State.TAKING_OFF);

      nextPrintTime = time+0.5f;

      do {  // user flies the drone until the drone lands.
         for( Actionable device: actionables)
            device.takeAction(time);
         if (time >= nextPrintTime){
            System.out.print("\n" + time+ ",\t"+drone.toString() + "; move: " );
            nextPrintTime+=0.5;
         }
      
         sleepFor(0.1f);
         time=getCurrentTime();
      } while (drone.getState()!=State.LANDED);
      System.out.print("\n" + time+ ",\t"+drone.toString() + "; \n move: ");
   
    System.out.println("\n BUEN VUELO JOVEN");
   }


   public static float getCurrentTime(){  // time since program started in [s]
      return (float)(System.currentTimeMillis()-t0)/1000.0f;
   }
   public static void sleepFor(float time) { // to let user react
      try {
         Thread.sleep((int)(time*1000));
      } catch (InterruptedException e){
      }
   }
}