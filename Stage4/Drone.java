//import sun.java2d.loops.MaskBlit;
import java.lang.Math;
import java.io.*;
import java.util.Scanner;
import java.util.Locale;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class Drone implements Actionable{
   public Drone () {
      state=state.LANDED;
      time=0.0f;
      fSpeed=0.0f;
      vSpeed=0.0f;
      sSpeed=0.0f;
      rSpeed=0.0f;
      x=0;
      y=0;
      h=0;
      direction=0.0f;
      b=0;
   }
   static {
      MAX_F_SPEED = MAX_S_SPEED = 5; // [m/s]
      MAX_V_SPEED = 2;    // [m/s]
      MAX_R_SPEED = (float)(0.25*Math.PI/2); // [rad/s]
      TAKEOFF_LANDING_SPEED = 1; // [m/s]
   }
   public void takeAction(float t){
      float delta_t = t-time;
      
      switch (state) {
      case TAKING_OFF:
            while(h<=10){
            h+=MAX_V_SPEED*0.1;
            sleepFor(0.1f);
            System.out.println(toString());

            }
            state=State.FLYING;
            
            break;
      case FLYING:
          direction=delta_t*rSpeed;
          x+=delta_t*fSpeed*Math.cos(direction)+delta_t*fSpeed*Math.sin(direction);
          y+=delta_t*sSpeed*Math.sin(direction)+delta_t*sSpeed*Math.cos(direction);
          h+=delta_t*vSpeed;
          
          break;
      case LANDING: //drone moves only downwards in this stage
          h-=TAKEOFF_LANDING_SPEED*0.2;

          if(h<=0){
            state=state.LANDED;
         }
         break;
      }
      time = t;
   }
   public void setRotationSpeed(float percentage) {
      
      rSpeed=MAX_R_SPEED*percentage;

   }
   public void setFlySpeed(float verPer, float forwPer, float sidePer) {
      fSpeed=forwPer*MAX_F_SPEED;
      vSpeed=verPer*MAX_V_SPEED;
      sSpeed=sidePer*MAX_S_SPEED;
   }
   public float getHeight() {
      return h;
   }
   public String toString() {
      return (String.format("%.2f",x) + "," +String.format("%.2f",y)  + "," +String.format("%.2f",h));
   }
   public void takeOff() {
      if (state==State.LANDED)
         state = State.TAKING_OFF;
   }
   public void land() {
      if (state==State.FLYING)
         state = State.LANDING;
   }
   public void fly(){  //se pone a volar
      if(state==State.TAKING_OFF){
            state=State.FLYING;
      }
   }

   public State getState(){
      return state;
   }

   public static void sleepFor(float time) { // to let user react
      try {
         Thread.sleep((int)(time*1000));
      } catch (InterruptedException e){
      }
   }
   private State state;
   private int b;
   private float time;
   private float fSpeed, vSpeed, sSpeed, rSpeed;
   private float direction; // angle
   private float x,y,h;
   private static float MAX_F_SPEED;
   private static float MAX_V_SPEED;
   private static float MAX_S_SPEED;
   private static float MAX_R_SPEED;
   private static float TAKEOFF_LANDING_SPEED;
}