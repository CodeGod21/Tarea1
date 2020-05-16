import java.io.*;
import java.util.Scanner;
import java.util.Locale;
import java.text.DecimalFormat;

public class Stage2Test  {
   public static void main (String[] arg) throws IOException {
      Locale.setDefault(Locale.US);  // to read number in US format, like 1.5 (not like 1,5)
      Scanner in = new Scanner(new File(arg[0]));
      float time = 0;
      Drone drone = new Drone();
      SkyController skyController = new SkyController(drone);
      Operator operator = new Operator(in, skyController);
      
      while(drone.getHeight()<10){
         skyController.pushTakeOff_Land(); // to take-off
         System.out.println(String.format("%.1f",time)+ ",\t"+drone.toString());
         time+=0.1;
      }
      drone.fly();//vuela
     
      time=0;
      while(operator.takeAction(time)) {
         //System.out.println("pen1");
         skyController.takeAction(time);
         drone.takeAction(time);
         System.out.println(String.format("%.1f",time)+ ",\t"+drone.toString());
         time+=0.1;
      }
    
      
      skyController.pushLanding(); // to land
      while (drone.getHeight() >=0) {
         drone.takeAction(time);
         System.out.println(String.format("%.1f",time)+ "\t"+drone.toString());
         time+=0.1;
      }
      

   }
}