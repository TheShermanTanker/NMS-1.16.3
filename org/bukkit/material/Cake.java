/*    */ package org.bukkit.material;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class Cake
/*    */   extends MaterialData
/*    */ {
/*    */   public Cake() {
/* 12 */     super(Material.LEGACY_CAKE_BLOCK);
/*    */   }
/*    */   
/*    */   public Cake(Material type) {
/* 16 */     super(type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public Cake(Material type, byte data) {
/* 26 */     super(type, data);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getSlicesEaten() {
/* 35 */     return getData();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getSlicesRemaining() {
/* 44 */     return 6 - getData();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSlicesEaten(int n) {
/* 53 */     if (n < 6) {
/* 54 */       setData((byte)n);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSlicesRemaining(int n) {
/* 64 */     if (n > 6) {
/* 65 */       n = 6;
/*    */     }
/* 67 */     setData((byte)(6 - n));
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 72 */     return super.toString() + " " + getSlicesEaten() + "/" + getSlicesRemaining() + " slices eaten/remaining";
/*    */   }
/*    */ 
/*    */   
/*    */   public Cake clone() {
/* 77 */     return (Cake)super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Cake.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */