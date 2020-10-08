/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import com.destroystokyo.paper.entity.CraftRangedEntity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityCreature;
/*    */ import net.minecraft.server.v1_16_R2.EntityInsentient;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityMonster;
/*    */ import net.minecraft.server.v1_16_R2.EntityWither;
/*    */ import net.minecraft.server.v1_16_R2.IRangedEntity;
/*    */ import org.bukkit.boss.BossBar;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftWither extends CraftMonster implements Wither, CraftRangedEntity<EntityWither> {
/*    */   public CraftWither(CraftServer server, EntityWither entity) {
/* 16 */     super(server, (EntityMonster)entity);
/*    */     
/* 18 */     if (entity.bossBattle != null)
/* 19 */       this.bossBar = (BossBar)new CraftBossBar(entity.bossBattle); 
/*    */   }
/*    */   
/*    */   private BossBar bossBar;
/*    */   
/*    */   public EntityWither getHandle() {
/* 25 */     return (EntityWither)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 30 */     return "CraftWither";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 35 */     return EntityType.WITHER;
/*    */   }
/*    */ 
/*    */   
/*    */   public BossBar getBossBar() {
/* 40 */     return this.bossBar;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftWither.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */