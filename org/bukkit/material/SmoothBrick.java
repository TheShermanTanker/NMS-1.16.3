/*    */ package org.bukkit.material;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class SmoothBrick
/*    */   extends TexturedMaterial
/*    */ {
/* 16 */   private static final List<Material> textures = new ArrayList<>();
/*    */   static {
/* 18 */     textures.add(Material.LEGACY_STONE);
/* 19 */     textures.add(Material.LEGACY_MOSSY_COBBLESTONE);
/* 20 */     textures.add(Material.LEGACY_COBBLESTONE);
/* 21 */     textures.add(Material.LEGACY_SMOOTH_BRICK);
/*    */   }
/*    */   
/*    */   public SmoothBrick() {
/* 25 */     super(Material.LEGACY_SMOOTH_BRICK);
/*    */   }
/*    */   
/*    */   public SmoothBrick(Material type) {
/* 29 */     super(textures.contains(type) ? Material.LEGACY_SMOOTH_BRICK : type);
/* 30 */     if (textures.contains(type)) {
/* 31 */       setMaterial(type);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public SmoothBrick(Material type, byte data) {
/* 42 */     super(type, data);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Material> getTextures() {
/* 47 */     return textures;
/*    */   }
/*    */ 
/*    */   
/*    */   public SmoothBrick clone() {
/* 52 */     return (SmoothBrick)super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\SmoothBrick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */