/*    */ package org.bukkit.block.data.type;
/*    */ 
/*    */ import org.bukkit.block.data.Directional;
/*    */ import org.bukkit.block.data.Powerable;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface Comparator
/*    */   extends Directional, Powerable
/*    */ {
/*    */   @NotNull
/*    */   Mode getMode();
/*    */   
/*    */   void setMode(@NotNull Mode paramMode);
/*    */   
/*    */   public enum Mode
/*    */   {
/* 36 */     COMPARE,
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 41 */     SUBTRACT;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\type\Comparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */