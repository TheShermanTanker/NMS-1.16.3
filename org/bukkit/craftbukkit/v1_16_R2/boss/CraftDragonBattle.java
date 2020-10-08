/*    */ package org.bukkit.craftbukkit.v1_16_R2.boss;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import net.minecraft.server.v1_16_R2.EnderDragonBattle;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EnumDragonRespawn;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.boss.BossBar;
/*    */ import org.bukkit.boss.DragonBattle;
/*    */ import org.bukkit.entity.EnderDragon;
/*    */ 
/*    */ public class CraftDragonBattle implements DragonBattle {
/*    */   private final EnderDragonBattle handle;
/*    */   
/*    */   public CraftDragonBattle(EnderDragonBattle handle) {
/* 17 */     this.handle = handle;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnderDragon getEnderDragon() {
/* 22 */     Entity entity = this.handle.world.getEntity(this.handle.dragonUUID);
/* 23 */     return (entity != null) ? (EnderDragon)entity.getBukkitEntity() : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public BossBar getBossBar() {
/* 28 */     return new CraftBossBar(this.handle.bossBattle);
/*    */   }
/*    */ 
/*    */   
/*    */   public Location getEndPortalLocation() {
/* 33 */     if (this.handle.exitPortalLocation == null) {
/* 34 */       return null;
/*    */     }
/*    */     
/* 37 */     return new Location((World)this.handle.world.getWorld(), this.handle.exitPortalLocation.getX(), this.handle.exitPortalLocation.getY(), this.handle.exitPortalLocation.getZ());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean generateEndPortal(boolean withPortals) {
/* 42 */     if (this.handle.exitPortalLocation != null || this.handle.getExitPortalShape() != null) {
/* 43 */       return false;
/*    */     }
/*    */     
/* 46 */     this.handle.generateExitPortal(withPortals);
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasBeenPreviouslyKilled() {
/* 52 */     return this.handle.isPreviouslyKilled();
/*    */   }
/*    */ 
/*    */   
/*    */   public void initiateRespawn() {
/* 57 */     this.handle.initiateRespawn();
/*    */   }
/*    */ 
/*    */   
/*    */   public DragonBattle.RespawnPhase getRespawnPhase() {
/* 62 */     return toBukkitRespawnPhase(this.handle.respawnPhase);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean setRespawnPhase(DragonBattle.RespawnPhase phase) {
/* 67 */     Preconditions.checkArgument((phase != null && phase != DragonBattle.RespawnPhase.NONE), "Invalid respawn phase provided: %s", phase);
/*    */     
/* 69 */     if (this.handle.respawnPhase == null) {
/* 70 */       return false;
/*    */     }
/*    */     
/* 73 */     this.handle.setRespawnPhase(toNMSRespawnPhase(phase));
/* 74 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void resetCrystals() {
/* 79 */     this.handle.resetCrystals();
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 84 */     return this.handle.hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 89 */     return (obj instanceof CraftDragonBattle && ((CraftDragonBattle)obj).handle == this.handle);
/*    */   }
/*    */   
/*    */   private DragonBattle.RespawnPhase toBukkitRespawnPhase(EnumDragonRespawn phase) {
/* 93 */     return (phase != null) ? DragonBattle.RespawnPhase.values()[phase.ordinal()] : DragonBattle.RespawnPhase.NONE;
/*    */   }
/*    */   
/*    */   private EnumDragonRespawn toNMSRespawnPhase(DragonBattle.RespawnPhase phase) {
/* 97 */     return (phase != DragonBattle.RespawnPhase.NONE) ? EnumDragonRespawn.values()[phase.ordinal()] : null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\boss\CraftDragonBattle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */