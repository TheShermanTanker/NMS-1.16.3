/*    */ package org.bukkit.material;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class Command
/*    */   extends MaterialData
/*    */   implements Redstone
/*    */ {
/*    */   public Command() {
/* 14 */     super(Material.LEGACY_COMMAND);
/*    */   }
/*    */   
/*    */   public Command(Material type) {
/* 18 */     super(type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public Command(Material type, byte data) {
/* 28 */     super(type, data);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isPowered() {
/* 39 */     return ((getData() & 0x1) != 0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setPowered(boolean bool) {
/* 49 */     setData((byte)(bool ? (getData() | 0x1) : (getData() & 0xFFFFFFFE)));
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     return super.toString() + " " + (isPowered() ? "" : "NOT ") + "POWERED";
/*    */   }
/*    */ 
/*    */   
/*    */   public Command clone() {
/* 59 */     return (Command)super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Command.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */