/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityAnimal;
/*    */ import net.minecraft.server.v1_16_R2.EntityCat;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityTameableAnimal;
/*    */ import org.bukkit.DyeColor;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.Cat;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftCat extends CraftTameableAnimal implements Cat {
/*    */   public CraftCat(CraftServer server, EntityCat entity) {
/* 14 */     super(server, (EntityTameableAnimal)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityCat getHandle() {
/* 19 */     return (EntityCat)super.getHandle();
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 24 */     return EntityType.CAT;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 29 */     return "CraftCat";
/*    */   }
/*    */ 
/*    */   
/*    */   public Cat.Type getCatType() {
/* 34 */     return Cat.Type.values()[getHandle().getCatType()];
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCatType(Cat.Type type) {
/* 39 */     Preconditions.checkArgument((type != null), "Cannot have null Type");
/*    */     
/* 41 */     getHandle().setCatType(type.ordinal());
/*    */   }
/*    */ 
/*    */   
/*    */   public DyeColor getCollarColor() {
/* 46 */     return DyeColor.getByWoolData((byte)getHandle().getCollarColor().getColorIndex());
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCollarColor(DyeColor color) {
/* 51 */     getHandle().setCollarColor(EnumColor.fromColorIndex(color.getWoolData()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftCat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */