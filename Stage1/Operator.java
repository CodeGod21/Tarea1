import java.util.Scanner;

import javax.management.openmbean.OpenDataException;

public class Operator {
   public Operator (Scanner in, Joystick l_Joy, Joystick r_Joy){
      inFile = in;
      l_Joystick = l_Joy;
      r_Joystick = r_Joy;
      inFile.nextLine(); // skip description line
      t = inFile.nextFloat();
   }
   public boolean takeAction(float time){
      float f;
      if (time > t) {
         //se entregan los valores de las distintas possiciones a los joysticks respectivamente
         l_Joystick.setHorPos(f=inFile.nextFloat());
         l_Joystick.setVerPos(f=inFile.nextFloat());
         r_Joystick.setHorPos(f=inFile.nextFloat());
         r_Joystick.setVerPos(f=inFile.nextFloat());
         if (l_Joystick.getHorPos()==0.0&&l_Joystick.getVerPos()==0.0 && r_Joystick.getHorPos()==0.0 && r_Joystick.getVerPos()==0.0&&t>2){
            //si el documento se acaba y todas son 0 0 0 0 se cierra
            return false;  
           }
         t=inFile.nextFloat();
         
       }
     
      return true;
   }
   private float t;
   private Scanner inFile;
   private Joystick l_Joystick, r_Joystick;
}