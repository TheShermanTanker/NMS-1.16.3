/*    */ package org.bukkit.material;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class Cauldron
/*    */   extends MaterialData
/*    */ {
/*    */   private static final int CAULDRON_FULL = 3;
/*    */   private static final int CAULDRON_EMPTY = 0;
/*    */   
/*    */   public Cauldron() {
/* 17 */     super(Material.LEGACY_CAULDRON);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public Cauldron(Material type, byte data) {
/* 27 */     super(type, data);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public Cauldron(byte data) {
/* 36 */     super(Material.LEGACY_CAULDRON, data);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isFull() {
/* 45 */     return (getData() >= 3);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isEmpty() {
/* 54 */     return (getData() <= 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 59 */     return (isEmpty() ? "EMPTY" : (isFull() ? "FULL" : (getData() + "/3 FULL"))) + " CAULDRON";
/*    */   }
/*    */ 
/*    */   
/*    */   public Cauldron clone() {
/* 64 */     return (Cauldron)super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Cauldron.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */