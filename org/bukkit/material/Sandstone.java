/*    */ package org.bukkit.material;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.SandstoneType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class Sandstone
/*    */   extends MaterialData
/*    */ {
/*    */   public Sandstone() {
/* 15 */     super(Material.LEGACY_SANDSTONE);
/*    */   }
/*    */   
/*    */   public Sandstone(SandstoneType type) {
/* 19 */     this();
/* 20 */     setType(type);
/*    */   }
/*    */   
/*    */   public Sandstone(Material type) {
/* 24 */     super(type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public Sandstone(Material type, byte data) {
/* 34 */     super(type, data);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SandstoneType getType() {
/* 43 */     return SandstoneType.getByData(getData());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setType(SandstoneType type) {
/* 52 */     setData(type.getData());
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     return getType() + " " + super.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public Sandstone clone() {
/* 62 */     return (Sandstone)super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Sandstone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */