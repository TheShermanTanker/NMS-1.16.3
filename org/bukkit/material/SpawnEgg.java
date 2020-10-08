/*    */ package org.bukkit.material;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class SpawnEgg
/*    */   extends MaterialData
/*    */ {
/*    */   public SpawnEgg() {
/* 15 */     super(Material.LEGACY_MONSTER_EGG);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public SpawnEgg(Material type, byte data) {
/* 25 */     super(type, data);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public SpawnEgg(byte data) {
/* 34 */     super(Material.LEGACY_MONSTER_EGG, data);
/*    */   }
/*    */   
/*    */   public SpawnEgg(EntityType type) {
/* 38 */     this();
/* 39 */     setSpawnedType(type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public EntityType getSpawnedType() {
/* 50 */     return EntityType.fromId(getData());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public void setSpawnedType(EntityType type) {
/* 61 */     setData((byte)type.getTypeId());
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 66 */     return "SPAWN EGG{" + getSpawnedType() + "}";
/*    */   }
/*    */ 
/*    */   
/*    */   public SpawnEgg clone() {
/* 71 */     return (SpawnEgg)super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\SpawnEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */