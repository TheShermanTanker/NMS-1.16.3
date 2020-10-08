/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityAnimal;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntitySheep;
/*    */ import org.bukkit.DyeColor;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftSheep extends CraftAnimals implements Sheep {
/*    */   public CraftSheep(CraftServer server, EntitySheep entity) {
/* 12 */     super(server, (EntityAnimal)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public DyeColor getColor() {
/* 17 */     return DyeColor.getByWoolData((byte)getHandle().getColor().getColorIndex());
/*    */   }
/*    */ 
/*    */   
/*    */   public void setColor(DyeColor color) {
/* 22 */     getHandle().setColor(EnumColor.fromColorIndex(color.getWoolData()));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isSheared() {
/* 27 */     return getHandle().isSheared();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSheared(boolean flag) {
/* 32 */     getHandle().setSheared(flag);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntitySheep getHandle() {
/* 37 */     return (EntitySheep)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 42 */     return "CraftSheep";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 47 */     return EntityType.SHEEP;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftSheep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */