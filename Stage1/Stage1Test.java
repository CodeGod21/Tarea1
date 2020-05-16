import java.io.*;
import java.util.Scanner;
import java.util.Locale;
import java.text.DecimalFormat;

public class Stage1Test  {
   public static void main (String[] arg) throws IOException {
      Locale.setDefault(Locale.US);  // to read number in US format, like 1.5 (not like 1,5)
      Scanner in = new Scanner(new File(arg[0]));
      File archivo= new File(arg[1]);
      archivo.createNewFile();
      FileWriter doc= new FileWriter(archivo);
      
      float time = 0.0f;
      Joystick l_Joy = new Joystick();
      Joystick r_Joy = new Joystick();
      Operator operator = new Operator(in, l_Joy, r_Joy);
      DecimalFormat num= new DecimalFormat("#0.0");
      while(operator.takeAction(time)) {
         
         System.out.println(String.format("%.1f",time)+" "+l_Joy.toString()+" "+r_Joy.toString());
         doc.write(String.format("%.1f",time)+" "+l_Joy.toString()+" "+r_Joy.toString()+"\n");
         time= (float) (time + 0.1);
      }
      doc.flush();
      doc.close();

   }
}