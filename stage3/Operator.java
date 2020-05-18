import java.util.Scanner;

import javax.management.openmbean.OpenDataException;

public class Operator implements Actionable {
   public Operator (Scanner in, Joysticks joysticks){
      inFile = in;
      this.joysticks=joysticks;
      l_Joystick = joysticks.getLeftStick();
      r_Joystick = joysticks.getRightStick();
      inFile.nextLine(); // skip description line
      t = inFile.nextFloat();
      b=0;
     
   }
   public void takeAction(float time){
      float f;
      
      if (time > t ) {

         //si se acaba el documento con 0 0 0 0 se termina la lectura del documento
        if(l_Joystick.getHorPos()==0.0&&l_Joystick.getVerPos()==0.0 && r_Joystick.getHorPos()==0.0 && r_Joystick.getVerPos()==0.0&&time>2){
            
            joysticks.cambiobuttonaLANDING();
            return;  
        }
        
         l_Joystick.setHorPos(f=inFile.nextFloat());
         l_Joystick.setVerPos(f=inFile.nextFloat());
         r_Joystick.setHorPos(f=inFile.nextFloat());
         r_Joystick.setVerPos(f=inFile.nextFloat());
        
        //aceptara datos unicamente si uno de los 4 parametros es igual distinto de 0.
         if(l_Joystick.getHorPos()!=0.0||l_Joystick.getVerPos()!=0.0 || r_Joystick.getHorPos()!=0.0 || r_Joystick.getVerPos()!=0.0){
            t=inFile.nextFloat();

         } 
         
         
       }
      return;
   }
   private float t;
   private Scanner inFile;
   private Joystick l_Joystick, r_Joystick;
   private Joysticks joysticks;
   private int b;
  
}