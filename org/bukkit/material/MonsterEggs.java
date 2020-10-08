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
/*    */ public class MonsterEggs
/*    */   extends TexturedMaterial
/*    */ {
/* 16 */   private static final List<Material> textures = new ArrayList<>();
/*    */   static {
/* 18 */     textures.add(Material.LEGACY_STONE);
/* 19 */     textures.add(Material.LEGACY_COBBLESTONE);
/* 20 */     textures.add(Material.LEGACY_SMOOTH_BRICK);
/*    */   }
/*    */   
/*    */   public MonsterEggs() {
/* 24 */     super(Material.LEGACY_MONSTER_EGGS);
/*    */   }
/*    */   
/*    */   public MonsterEggs(Material type) {
/* 28 */     super(textures.contains(type) ? Material.LEGACY_MONSTER_EGGS : type);
/* 29 */     if (textures.contains(type)) {
/* 30 */       setMaterial(type);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public MonsterEggs(Material type, byte data) {
/* 41 */     super(type, data);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Material> getTextures() {
/* 46 */     return textures;
/*    */   }
/*    */ 
/*    */   
/*    */   public MonsterEggs clone() {
/* 51 */     return (MonsterEggs)super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\MonsterEggs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */