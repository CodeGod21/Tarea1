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

      //crearemos 1 dron para cada control.
      Drone drone = new Drone();
      Drone dronePilot = new Drone();
      
      //creacion del archivo de salida(dron.csv);
         File droneUSM= new File("DroneUSM.csv");
         droneUSM.createNewFile();
         File droneKeyboard= new File ("DroneKeyboard.csv");
         droneKeyboard.createNewFile();
         FileWriter droneUSMFileWriter= new FileWriter(droneUSM);
         FileWriter droneKeyboardFileWriter =new FileWriter(droneKeyboard);

   

      SkyController skyController = new SkyController(drone);
      SkyController skyControllerPilot = new SkyController(dronePilot);
      Joysticks joysticks = new Joysticks(skyController);
      Joysticks joysticksPilot= new Joysticks(skyControllerPilot);

      skyController.setInputDevice(joysticks);
      skyControllerPilot.setInputDevice(joysticksPilot);

      Keyboard keyboardPilot = new Keyboard(skyControllerPilot);
      
      
      Operator operator = new Operator(in, joysticks);
           
      ArrayList<Actionable> actionables = new ArrayList<Actionable>();
      ArrayList<Actionable> actionablesPilot = new ArrayList<Actionable>();
      actionables.add(operator);
      actionables.add(skyController);
      actionables.add(drone);
      
      actionablesPilot.add(keyboardPilot);
      actionablesPilot.add(skyControllerPilot);
      actionablesPilot.add(dronePilot);
      

      skyController.pushTakeOff_Land ();//aqui deberia subir hasta 10m 
      do {
         
         for( Actionable device: actionables){
            device.takeAction(time);
         }
         for(Actionable device: actionablesPilot){
            device.takeAction(time);
         }
         
         if (time >= nextPrintTime){
            System.out.println(String.format("%.1f",time)+ ",\t"+drone.toString());
            nextPrintTime+=0.5;
         }
         droneKeyboardFileWriter.write(String.format("%.1f",time)+ ",\t"+dronePilot.toString());
         droneUSMFileWriter.write(String.format("%.1f",time)+ ",\t"+drone.toString());

        // System.out.println(String.format("%.1f",time)+ ",\t"+drone.toString());
         sleepFor(0.1f);  // let 0.1 [s] pass to run at real time.
         time+=0.1;
         //System.out.println("EL TIEMPO ES: "+String.format("%.1f",time));
      } while (drone.getState()!=State.LANDED && dronePilot.getState()!=State.LANDED);
      droneKeyboardFileWriter.flush();
      droneKeyboardFileWriter.close();
     

     
      do {  // user flies the drone until the drone lands.
         for( Actionable device: actionables)
            device.takeAction(time);
           

         if (time >= nextPrintTime){
            System.out.print("\n" + time+ ",\t"+drone.toString() + "; move: " );
            nextPrintTime+=0.5;
         }
         droneUSMFileWriter.write(String.format("%.1f",time)+ ",\t"+drone.toString());
         sleepFor(0.1f);
         time=getCurrentTime();
      } while (drone.getState()!=State.LANDED);
      System.out.print("\n" + time+ ",\t"+drone.toString() + "; \n move: ");

    System.out.println("\n BUEN VUELO JOVEN");
      droneUSMFileWriter.flush();
      droneUSMFileWriter.close();
      
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

 /*

      System.out.println(time+ ",\t"+drone.toString());
      Keyboard keyboard = new Keyboard(skyController);
      skyController.setInputDevice(keyboard); // we switch to another input device
      actionables.remove(operator);  // stop reading automatically from file
      actionables.add(keyboard);  // start reading from keyboard
      System.out.println("Get ready to control the drone. Now you are the pilot.");
      do { // wait until the user hits space key (to take-off)
         for( Actionable device: actionables){
            //System.out.println("entro");
            device.takeAction(time);
            
         }
         sleepFor(0.1f);  // users need to run at real time (not at simulation time)
         time=getCurrentTime();
      } while (drone.getState()!=State.TAKING_OFF);

      nextPrintTime = time+0.5f;
      */