import java.io.*;
import java.util.Scanner;
import java.util.Locale;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class Stage4Test  {
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

      

      Keyboard keyboardPilot = new Keyboard(skyControllerPilot);

      skyController.setInputDevice(joysticks);
      skyControllerPilot.setInputDevice(keyboardPilot);
      
      
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
            System.out.println("Dron:      "+String.format("%.1f",time)+ ",\t"+drone.toString());
            System.out.println("DronPilot: "+String.format("%.1f",time)+ ",\t"+dronePilot.toString());
            nextPrintTime+=0.5;
         }
         droneKeyboardFileWriter.write("\n"+String.format("%.1f",time)+ ",\t"+dronePilot.toString());
         droneUSMFileWriter.write("\n"+String.format("%.1f",time)+ ",\t"+drone.toString());

        // System.out.println(String.format("%.1f",time)+ ",\t"+drone.toString());
         sleepFor(0.1f);  // let 0.1 [s] pass to run at real time.
         time+=0.1;
   
      } while (drone.getState()!=State.LANDED || dronePilot.getState()!=State.LANDED);
     
   
     

     
      do {  // user flies the drone until the drone lands.
         for( Actionable device: actionablesPilot)
            device.takeAction(time);
           

         if (time >= nextPrintTime){
            System.out.print("\n" + time+ ",\t"+drone.toString() + "; move: " );
            nextPrintTime+=0.5;
         }
         droneKeyboardFileWriter.write(String.format("%.1f",time)+ ",\t"+drone.toString());
         sleepFor(0.1f);
         time=getCurrentTime();
      } while (dronePilot.getState()!=State.LANDED);
    System.out.println("\nAmbos vuelos han sido generados correctamente, que tenga un buen dia.");
      droneUSMFileWriter.flush();
      droneUSMFileWriter.close();
      droneKeyboardFileWriter.flush();
      droneKeyboardFileWriter.close();
      
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

