/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityFishingHook;
/*    */ import net.minecraft.server.v1_16_R2.IProjectile;
/*    */ import net.minecraft.server.v1_16_R2.MathHelper;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftFishHook extends CraftProjectile implements FishHook {
/* 12 */   private double biteChance = -1.0D;
/*    */   
/*    */   public CraftFishHook(CraftServer server, EntityFishingHook entity) {
/* 15 */     super(server, (IProjectile)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityFishingHook getHandle() {
/* 20 */     return (EntityFishingHook)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 25 */     return "CraftFishingHook";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 30 */     return EntityType.FISHING_HOOK;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getBiteChance() {
/* 35 */     EntityFishingHook hook = getHandle();
/*    */     
/* 37 */     if (this.biteChance == -1.0D) {
/* 38 */       if (hook.world.isRainingAt(new BlockPosition(MathHelper.floor(hook.locX()), MathHelper.floor(hook.locY()) + 1, MathHelper.floor(hook.locZ())))) {
/* 39 */         return 0.0033333333333333335D;
/*    */       }
/* 41 */       return 0.002D;
/*    */     } 
/* 43 */     return this.biteChance;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBiteChance(double chance) {
/* 48 */     Validate.isTrue((chance >= 0.0D && chance <= 1.0D), "The bite chance must be between 0 and 1.");
/* 49 */     this.biteChance = chance;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void remove() {
/* 55 */     super.remove();
/* 56 */     if (getHandle().getOwner() != null)
/* 57 */       (getHandle().getOwner()).hookedFish = null; 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftFishHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */