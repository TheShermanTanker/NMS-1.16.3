/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityHorseAbstract;
/*    */ import net.minecraft.server.v1_16_R2.EntityHorseChestedAbstract;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityLlama;
/*    */ import net.minecraft.server.v1_16_R2.EntityLlamaTrader;
/*    */ 
/*    */ public class CraftTraderLlama extends CraftLlama implements TraderLlama {
/*    */   public CraftTraderLlama(CraftServer server, EntityLlamaTrader entity) {
/* 11 */     super(server, (EntityLlama)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityLlamaTrader getHandle() {
/* 16 */     return (EntityLlamaTrader)super.getHandle();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 21 */     return "CraftTraderLlama";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 26 */     return EntityType.TRADER_LLAMA;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftTraderLlama.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */