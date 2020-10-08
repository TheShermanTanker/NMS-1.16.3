/*     */ package com.destroystokyo.paper.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nonnull;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.server.v1_16_R2.Entity;
/*     */ import net.minecraft.server.v1_16_R2.EntityInsentient;
/*     */ import net.minecraft.server.v1_16_R2.PathEntity;
/*     */ import net.minecraft.server.v1_16_R2.PathPoint;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftLivingEntity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Mob;
/*     */ 
/*     */ public class PaperPathfinder implements Pathfinder {
/*     */   private final EntityInsentient entity;
/*     */   
/*     */   public PaperPathfinder(EntityInsentient entity) {
/*  22 */     this.entity = entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public Mob getEntity() {
/*  27 */     return (Mob)this.entity.getBukkitMob();
/*     */   }
/*     */ 
/*     */   
/*     */   public void stopPathfinding() {
/*  32 */     this.entity.getNavigation().stopPathfinding();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasPath() {
/*  37 */     return (this.entity.getNavigation().getPathEntity() != null);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Pathfinder.PathResult getCurrentPath() {
/*  43 */     PathEntity path = this.entity.getNavigation().getPathEntity();
/*  44 */     return (path != null) ? new PaperPathResult(path) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Pathfinder.PathResult findPath(Location loc) {
/*  50 */     Validate.notNull(loc, "Location can not be null");
/*  51 */     PathEntity path = this.entity.getNavigation().calculateDestination(loc.getX(), loc.getY(), loc.getZ());
/*  52 */     return (path != null) ? new PaperPathResult(path) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Pathfinder.PathResult findPath(LivingEntity target) {
/*  58 */     Validate.notNull(target, "Target can not be null");
/*  59 */     PathEntity path = this.entity.getNavigation().calculateDestination((Entity)((CraftLivingEntity)target).getHandle());
/*  60 */     return (path != null) ? new PaperPathResult(path) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean moveTo(@Nonnull Pathfinder.PathResult path, double speed) {
/*  65 */     Validate.notNull(path, "PathResult can not be null");
/*  66 */     PathEntity pathEntity = ((PaperPathResult)path).path;
/*  67 */     return this.entity.getNavigation().setDestination(pathEntity, speed);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canOpenDoors() {
/*  72 */     return this.entity.getNavigation().getPathfinder().getPathfinder().shouldOpenDoors();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCanOpenDoors(boolean canOpenDoors) {
/*  77 */     this.entity.getNavigation().getPathfinder().getPathfinder().setShouldOpenDoors(canOpenDoors);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPassDoors() {
/*  82 */     return this.entity.getNavigation().getPathfinder().getPathfinder().shouldPassDoors();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCanPassDoors(boolean canPassDoors) {
/*  87 */     this.entity.getNavigation().getPathfinder().getPathfinder().setShouldPassDoors(canPassDoors);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canFloat() {
/*  92 */     return this.entity.getNavigation().getPathfinder().getPathfinder().shouldFloat();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCanFloat(boolean canFloat) {
/*  97 */     this.entity.getNavigation().getPathfinder().getPathfinder().setShouldFloat(canFloat);
/*     */   }
/*     */   
/*     */   public class PaperPathResult implements Pathfinder.PathResult {
/*     */     private final PathEntity path;
/*     */     
/*     */     PaperPathResult(PathEntity path) {
/* 104 */       this.path = path;
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public Location getFinalPoint() {
/* 110 */       PathPoint point = this.path.getFinalPoint();
/* 111 */       return (point != null) ? PaperPathfinder.this.toLoc(point) : null;
/*     */     }
/*     */ 
/*     */     
/*     */     public List<Location> getPoints() {
/* 116 */       List<Location> points = new ArrayList<>();
/* 117 */       for (PathPoint point : this.path.getPoints()) {
/* 118 */         points.add(PaperPathfinder.this.toLoc(point));
/*     */       }
/* 120 */       return points;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getNextPointIndex() {
/* 125 */       return this.path.getNextIndex();
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public Location getNextPoint() {
/* 131 */       if (!this.path.hasNext()) {
/* 132 */         return null;
/*     */       }
/* 134 */       return PaperPathfinder.this.toLoc(this.path.getPoints().get(this.path.getNextIndex()));
/*     */     }
/*     */   }
/*     */   
/*     */   private Location toLoc(PathPoint point) {
/* 139 */     return new Location((World)this.entity.world.getWorld(), point.getX(), point.getY(), point.getZ());
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\entity\PaperPathfinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */