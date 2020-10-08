/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityAnimal;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityParrot;
/*    */ import net.minecraft.server.v1_16_R2.EntityTameableAnimal;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Parrot;
/*    */ 
/*    */ public class CraftParrot extends CraftTameableAnimal implements Parrot {
/*    */   public CraftParrot(CraftServer server, EntityParrot parrot) {
/* 12 */     super(server, (EntityTameableAnimal)parrot);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityParrot getHandle() {
/* 17 */     return (EntityParrot)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public Parrot.Variant getVariant() {
/* 22 */     return Parrot.Variant.values()[getHandle().getVariant()];
/*    */   }
/*    */ 
/*    */   
/*    */   public void setVariant(Parrot.Variant variant) {
/* 27 */     Preconditions.checkArgument((variant != null), "variant");
/*    */     
/* 29 */     getHandle().setVariant(variant.ordinal());
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 34 */     return "CraftParrot";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 39 */     return EntityType.PARROT;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftParrot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */