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
<<<<<<< HEAD
      if (time > t) {
=======
      if (time >= t) {
>>>>>>> 6ba7710d014d35b2e437cad724d0afcee5cf2362
         l_Joystick.setHorPos(f=inFile.nextFloat());
         l_Joystick.setVerPos(f=inFile.nextFloat());
         r_Joystick.setHorPos(f=inFile.nextFloat());
         r_Joystick.setVerPos(f=inFile.nextFloat());
<<<<<<< HEAD
         if (l_Joystick.getHorPos()==0.0&&l_Joystick.getVerPos()==0.0 && r_Joystick.getHorPos()==0.0 && r_Joystick.getVerPos()==0.0&&t>2){
            return false;  
           }
=======
         //inFile.nextLine(); // skip description line
         if (l_Joystick.getHorPos()==0.0&&l_Joystick.getVerPos()==0.0 && r_Joystick.getHorPos()==0.0 && r_Joystick.getVerPos()==0.0&&t>2){
            return false;  
           }  
>>>>>>> 6ba7710d014d35b2e437cad724d0afcee5cf2362
         t=inFile.nextFloat();
         
       }
       
      return true;
   }
   private float t;
   private Scanner inFile;
   private Joystick l_Joystick, r_Joystick;
}