import java.io.*;
import java.util.Scanner;
import java.util.Locale;
<<<<<<< HEAD
import java.text.DecimalFormat;
=======
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.math.*;
>>>>>>> 6ba7710d014d35b2e437cad724d0afcee5cf2362

public class Stage1Test  {
   public static void main (String[] arg) throws IOException {
      Locale.setDefault(Locale.US);  // to read number in US format, like 1.5 (not like 1,5)
      Scanner in = new Scanner(new File(arg[0]));
<<<<<<< HEAD
      File archivo= new File(arg[1]);
      archivo.createNewFile();
      FileWriter doc= new FileWriter(archivo);
      
=======
      BufferedWriter writer = new BufferedWriter(new FileWriter("salida.csv"));
      writer.append("t,jih,jiv,jdh,jdv");
      writer.newLine();
>>>>>>> 6ba7710d014d35b2e437cad724d0afcee5cf2362
      float time = 0.0f;
      Joystick l_Joy = new Joystick();
      Joystick r_Joy = new Joystick();
      Operator operator = new Operator(in, l_Joy, r_Joy);
<<<<<<< HEAD
      DecimalFormat num= new DecimalFormat("#0.0");
      while(operator.takeAction(time)) {
         
         System.out.println(String.format("%.1f",time)+" "+l_Joy.toString()+" "+r_Joy.toString());
         doc.write(String.format("%.1f",time)+" "+l_Joy.toString()+" "+r_Joy.toString()+"\n");
         time= (float) (time + 0.1);
      }
      doc.flush();
      doc.close();

=======
      while(operator.takeAction(time)) { 

         writer.append( Math.floor(time*10)/10 +","+l_Joy.toString()+","+r_Joy.toString());
         writer.newLine();

         System.out.println(Math.floor(time*10)/10 +" "+l_Joy.toString()+" "+r_Joy.toString());
         time= (float) (time + 0.1);
      }
      
      writer.close();
      System.out.println("pen0");
      
>>>>>>> 6ba7710d014d35b2e437cad724d0afcee5cf2362
   }
   
   
}