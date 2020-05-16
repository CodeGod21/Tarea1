import java.io.*;
import java.util.Scanner;
import java.util.Locale;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.math.*;

public class Stage1Test  {
   public static void main (String[] arg) throws IOException {
      Locale.setDefault(Locale.US);  // to read number in US format, like 1.5 (not like 1,5)
      Scanner in = new Scanner(new File(arg[0]));
      BufferedWriter writer = new BufferedWriter(new FileWriter("salida.csv"));
      writer.append("t,jih,jiv,jdh,jdv");
      writer.newLine();
      float time = 0.0f;
      Joystick l_Joy = new Joystick();
      Joystick r_Joy = new Joystick();
      Operator operator = new Operator(in, l_Joy, r_Joy);
      while(operator.takeAction(time)) { 

         writer.append( Math.floor(time*10)/10 +","+l_Joy.toString()+","+r_Joy.toString());
         writer.newLine();

         System.out.println(Math.floor(time*10)/10 +" "+l_Joy.toString()+" "+r_Joy.toString());
         time= (float) (time + 0.1);
      }
      
      writer.close();
      System.out.println("pen0");
      
   }
   
   
}