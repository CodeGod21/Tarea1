import java.util.Scanner;
import java.io.InputStream;
import java.lang.ModuleLayer.Controller;
import java.io.IOException;

public class Keyboard extends InputDevice implements Actionable {
   public Keyboard(SkyController controller) {
      super(controller);
      this.controller=controller;
      rPos=vPos=fPos=sPos=0;
      //l_Joystick = controller.getLeftStick();
      //r_Joystick = controller.getRightStick();
      in = System.in;
   }
   public float getForwardPos() {
      return fPos;
   }
   public float getSidewaysPos(){
      return sPos;
   }
   public float getVerticalPos(){
      return vPos;
   }
   public float getRotationPos(){
      return rPos;
   }
   public void takeAction(float time) {
      //System.out.println("ENTRA AL TAKEACTION KEYBOARD");
      int c;
      try {  // reading from "in" could cause an error.
              // We will see instruction try...catch ahead in this course.
         if (in.available()>0) {// there are bytes to read without being blocked
            c=in.read();
            switch (c) {
               case 'w': vPos+=sensibility; 
                         if (vPos > 1) vPos=1;
                         //l_Joystick.setVerPos(vPos);
                         break;
               case 'a': rPos-=sensibility;
                        if (rPos < -1) rPos=-1;
                        //l_Joystick.setHorPos(rPos);
                        break;
               case 'z': vPos-=sensibility;
                        if (vPos < -1) vPos=-1;
                        //l_Joystick.setVerPos(vPos);
                        break;
               case 's': rPos+=sensibility;
                        if (rPos > 1) rPos=1;
                        //l_Joystick.setHorPos(rPos);
                        break;
               case 'i': fPos+=sensibility;
                        if (fPos > 1) fPos=1;
                        //r_Joystick.setVerPos(fPos);
                        break;
               case 'j': sPos-=sensibility;
                        if (sPos < -1) sPos=-1;
                        //r_Joystick.setHorPos(sPos);
                        break;
               case 'm': fPos-=sensibility;
                        if (fPos < -1) fPos=-1;
                        //r_Joystick.setVerPos(fPos);
                        break;
               case 'k': sPos+=sensibility;
                        if (sPos > 1) sPos=1;
                        //r_Joystick.setHorPos(sPos);
                        break;
               case ' ':controller.pushLanding();
                        break;      
              /* others cases to be coded */
            }
         }
         //System.out.println("soy un inutil");
      } catch ( IOException e ) { 
         System.out.println("Input error");
         return;
      }
   }
   private InputStream in;
   private float rPos, vPos, fPos, sPos;
   private static float sensibility=0.2f;
   private SkyController controller;
  // private Joystick l_Joystick, r_Joystick;
}