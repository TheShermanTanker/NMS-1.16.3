/*    */ package org.jline.terminal;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ 
/*    */ 
/*    */ public class MouseEvent
/*    */ {
/*    */   private final Type type;
/*    */   private final Button button;
/*    */   private final EnumSet<Modifier> modifiers;
/*    */   private final int x;
/*    */   private final int y;
/*    */   
/*    */   public enum Type
/*    */   {
/* 16 */     Released,
/* 17 */     Pressed,
/* 18 */     Wheel,
/* 19 */     Moved,
/* 20 */     Dragged;
/*    */   }
/*    */   
/*    */   public enum Button {
/* 24 */     NoButton,
/* 25 */     Button1,
/* 26 */     Button2,
/* 27 */     Button3,
/* 28 */     WheelUp,
/* 29 */     WheelDown;
/*    */   }
/*    */   
/*    */   public enum Modifier {
/* 33 */     Shift,
/* 34 */     Alt,
/* 35 */     Control;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MouseEvent(Type type, Button button, EnumSet<Modifier> modifiers, int x, int y) {
/* 45 */     this.type = type;
/* 46 */     this.button = button;
/* 47 */     this.modifiers = modifiers;
/* 48 */     this.x = x;
/* 49 */     this.y = y;
/*    */   }
/*    */   
/*    */   public Type getType() {
/* 53 */     return this.type;
/*    */   }
/*    */   
/*    */   public Button getButton() {
/* 57 */     return this.button;
/*    */   }
/*    */   
/*    */   public EnumSet<Modifier> getModifiers() {
/* 61 */     return this.modifiers;
/*    */   }
/*    */   
/*    */   public int getX() {
/* 65 */     return this.x;
/*    */   }
/*    */   
/*    */   public int getY() {
/* 69 */     return this.y;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 74 */     return "MouseEvent[type=" + this.type + ", button=" + this.button + ", modifiers=" + this.modifiers + ", x=" + this.x + ", y=" + this.y + ']';
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\terminal\MouseEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */