/*    */ package org.bukkit.material;
/*    */ 
/*    */ import org.bukkit.CoalType;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class Coal
/*    */   extends MaterialData
/*    */ {
/*    */   public Coal() {
/* 15 */     super(Material.LEGACY_COAL);
/*    */   }
/*    */   
/*    */   public Coal(CoalType type) {
/* 19 */     this();
/* 20 */     setType(type);
/*    */   }
/*    */   
/*    */   public Coal(Material type) {
/* 24 */     super(type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public Coal(Material type, byte data) {
/* 34 */     super(type, data);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CoalType getType() {
/* 43 */     return CoalType.getByData(getData());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setType(CoalType type) {
/* 52 */     setData(type.getData());
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     return getType() + " " + super.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public Coal clone() {
/* 62 */     return (Coal)super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Coal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */