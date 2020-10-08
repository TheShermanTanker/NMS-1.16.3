/*    */ package org.bukkit.material;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public abstract class TexturedMaterial
/*    */   extends MaterialData
/*    */ {
/*    */   public TexturedMaterial(Material m) {
/* 16 */     super(m);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public TexturedMaterial(Material type, byte data) {
/* 26 */     super(type, data);
/*    */   }
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
/*    */   public Material getMaterial() {
/* 43 */     int n = getTextureIndex();
/* 44 */     if (n > getTextures().size() - 1) {
/* 45 */       n = 0;
/*    */     }
/*    */     
/* 48 */     return getTextures().get(n);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setMaterial(Material material) {
/* 58 */     if (getTextures().contains(material)) {
/* 59 */       setTextureIndex(getTextures().indexOf(material));
/*    */     } else {
/* 61 */       setTextureIndex(0);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   protected int getTextureIndex() {
/* 73 */     return getData();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   protected void setTextureIndex(int idx) {
/* 84 */     setData((byte)idx);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 89 */     return getMaterial() + " " + super.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public TexturedMaterial clone() {
/* 94 */     return (TexturedMaterial)super.clone();
/*    */   }
/*    */   
/*    */   public abstract List<Material> getTextures();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\TexturedMaterial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */