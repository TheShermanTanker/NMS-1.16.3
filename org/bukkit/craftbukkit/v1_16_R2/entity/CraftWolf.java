/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityAnimal;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityTameableAnimal;
/*    */ import net.minecraft.server.v1_16_R2.EntityWolf;
/*    */ import org.bukkit.DyeColor;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftWolf extends CraftTameableAnimal implements Wolf {
/*    */   public CraftWolf(CraftServer server, EntityWolf wolf) {
/* 12 */     super(server, (EntityTameableAnimal)wolf);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isAngry() {
/* 17 */     return getHandle().isAngry();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAngry(boolean angry) {
/* 22 */     if (angry) {
/* 23 */       getHandle().anger();
/*    */     } else {
/* 25 */       getHandle().pacify();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityWolf getHandle() {
/* 31 */     return (EntityWolf)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 36 */     return EntityType.WOLF;
/*    */   }
/*    */ 
/*    */   
/*    */   public DyeColor getCollarColor() {
/* 41 */     return DyeColor.getByWoolData((byte)getHandle().getCollarColor().getColorIndex());
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCollarColor(DyeColor color) {
/* 46 */     getHandle().setCollarColor(EnumColor.fromColorIndex(color.getWoolData()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftWolf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */