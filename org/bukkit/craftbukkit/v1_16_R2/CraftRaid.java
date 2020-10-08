/*    */ package org.bukkit.craftbukkit.v1_16_R2;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import java.util.UUID;
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*    */ import net.minecraft.server.v1_16_R2.EntityRaider;
/*    */ import net.minecraft.server.v1_16_R2.Raid;
/*    */ import net.minecraft.server.v1_16_R2.World;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Raid;
/*    */ import org.bukkit.entity.Raider;
/*    */ 
/*    */ public final class CraftRaid implements Raid {
/*    */   private final Raid handle;
/*    */   
/*    */   public CraftRaid(Raid handle) {
/* 22 */     this.handle = handle;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isStarted() {
/* 27 */     return this.handle.isStarted();
/*    */   }
/*    */ 
/*    */   
/*    */   public long getActiveTicks() {
/* 32 */     return this.handle.ticksActive;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBadOmenLevel() {
/* 37 */     return this.handle.badOmenLevel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBadOmenLevel(int badOmenLevel) {
/* 42 */     int max = this.handle.getMaxBadOmenLevel();
/* 43 */     Preconditions.checkArgument((0 <= badOmenLevel && badOmenLevel <= max), "Bad Omen level must be between 0 and %s", max);
/* 44 */     this.handle.badOmenLevel = badOmenLevel;
/*    */   }
/*    */ 
/*    */   
/*    */   public Location getLocation() {
/* 49 */     BlockPosition pos = this.handle.getCenter();
/* 50 */     World world = this.handle.getWorld();
/* 51 */     return new Location(world.getWorld(), pos.getX(), pos.getY(), pos.getZ());
/*    */   }
/*    */ 
/*    */   
/*    */   public Raid.RaidStatus getStatus() {
/* 56 */     if (this.handle.isStopped())
/* 57 */       return Raid.RaidStatus.STOPPED; 
/* 58 */     if (this.handle.isVictory())
/* 59 */       return Raid.RaidStatus.VICTORY; 
/* 60 */     if (this.handle.isLoss()) {
/* 61 */       return Raid.RaidStatus.LOSS;
/*    */     }
/* 63 */     return Raid.RaidStatus.ONGOING;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getSpawnedGroups() {
/* 69 */     return this.handle.getGroupsSpawned();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getTotalGroups() {
/* 74 */     return this.handle.numGroups + ((this.handle.badOmenLevel > 1) ? 1 : 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getTotalWaves() {
/* 79 */     return this.handle.numGroups;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getTotalHealth() {
/* 84 */     return this.handle.sumMobHealth();
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<UUID> getHeroes() {
/* 89 */     return Collections.unmodifiableSet(this.handle.heroes);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Raider> getRaiders() {
/* 94 */     return (List<Raider>)this.handle.getRaiders().stream().map(new Function<EntityRaider, Raider>()
/*    */         {
/*    */           public Raider apply(EntityRaider entityRaider) {
/* 97 */             return (Raider)entityRaider.getBukkitEntity();
/*    */           }
/* 99 */         }).collect(ImmutableList.toImmutableList());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\CraftRaid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */