/*    */ package org.bukkit.material;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class FurnaceAndDispenser
/*    */   extends DirectionalContainer
/*    */ {
/*    */   public FurnaceAndDispenser(Material type) {
/* 15 */     super(type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public FurnaceAndDispenser(Material type, byte data) {
/* 25 */     super(type, data);
/*    */   }
/*    */ 
/*    */   
/*    */   public FurnaceAndDispenser clone() {
/* 30 */     return (FurnaceAndDispenser)super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\FurnaceAndDispenser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */