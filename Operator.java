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
      f=t;
      if (time > t) {
         l_Joystick.setHorPos(f=inFile.nextFloat());
         l_Joystick.setVerPos(f=inFile.nextFloat());
         r_Joystick.setHorPos(f=inFile.nextFloat());
         r_Joystick.setVerPos(f=inFile.nextFloat());
         inFile.nextLine(); // skip description line
       }
      return true;
   }
   private float t;
   private Scanner inFile;
   private Joystick l_Joystick, r_Joystick;
}