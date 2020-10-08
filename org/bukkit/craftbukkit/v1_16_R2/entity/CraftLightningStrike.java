/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityLightning;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.LightningStrike;
/*    */ 
/*    */ public class CraftLightningStrike extends CraftEntity implements LightningStrike {
/*    */   public CraftLightningStrike(CraftServer server, EntityLightning entity) {
/* 10 */     super(server, (Entity)entity);
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 34 */     this.spigot = new LightningStrike.Spigot()
/*    */       {
/*    */         
/*    */         public boolean isSilent()
/*    */         {
/* 39 */           return (CraftLightningStrike.this.getHandle()).isSilent;
/*    */         }
/*    */       };
/*    */   } private final LightningStrike.Spigot spigot; public boolean isEffect() {
/*    */     return (getHandle()).isEffect;
/*    */   } public LightningStrike.Spigot spigot() {
/* 45 */     return this.spigot;
/*    */   }
/*    */   
/*    */   public EntityLightning getHandle() {
/*    */     return (EntityLightning)this.entity;
/*    */   }
/*    */   
/*    */   public String toString() {
/*    */     return "CraftLightningStrike";
/*    */   }
/*    */   
/*    */   public EntityType getType() {
/*    */     return EntityType.LIGHTNING;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftLightningStrike.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */