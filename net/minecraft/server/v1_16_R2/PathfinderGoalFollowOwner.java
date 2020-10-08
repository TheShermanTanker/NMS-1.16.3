/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftEntity;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityTeleportEvent;
/*     */ 
/*     */ public class PathfinderGoalFollowOwner
/*     */   extends PathfinderGoal {
/*     */   private final EntityTameableAnimal a;
/*     */   private EntityLiving b;
/*     */   private final IWorldReader c;
/*     */   private final double d;
/*     */   private final NavigationAbstract e;
/*     */   private int f;
/*     */   private final float g;
/*     */   private final float h;
/*     */   private float i;
/*     */   private final boolean j;
/*     */   
/*     */   public PathfinderGoalFollowOwner(EntityTameableAnimal entitytameableanimal, double d0, float f, float f1, boolean flag) {
/*  24 */     this.a = entitytameableanimal;
/*  25 */     this.c = entitytameableanimal.world;
/*  26 */     this.d = d0;
/*  27 */     this.e = entitytameableanimal.getNavigation();
/*  28 */     this.h = f;
/*  29 */     this.g = f1;
/*  30 */     this.j = flag;
/*  31 */     a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK));
/*  32 */     if (!(entitytameableanimal.getNavigation() instanceof Navigation) && !(entitytameableanimal.getNavigation() instanceof NavigationFlying)) {
/*  33 */       throw new IllegalArgumentException("Unsupported mob type for FollowOwnerGoal");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a() {
/*  39 */     EntityLiving entityliving = this.a.getOwner();
/*     */     
/*  41 */     if (entityliving == null)
/*  42 */       return false; 
/*  43 */     if (entityliving.isSpectator())
/*  44 */       return false; 
/*  45 */     if (this.a.isWillSit())
/*  46 */       return false; 
/*  47 */     if (this.a.h(entityliving) < (this.h * this.h)) {
/*  48 */       return false;
/*     */     }
/*  50 */     this.b = entityliving;
/*  51 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean b() {
/*  57 */     return this.e.m() ? false : (this.a.isWillSit() ? false : ((this.a.h(this.b) > (this.g * this.g))));
/*     */   }
/*     */ 
/*     */   
/*     */   public void c() {
/*  62 */     this.f = 0;
/*  63 */     this.i = this.a.a(PathType.WATER);
/*  64 */     this.a.a(PathType.WATER, 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void d() {
/*  69 */     this.b = null;
/*  70 */     this.e.o();
/*  71 */     this.a.a(PathType.WATER, this.i);
/*     */   }
/*     */ 
/*     */   
/*     */   public void e() {
/*  76 */     this.a.getControllerLook().a(this.b, 10.0F, this.a.O());
/*  77 */     if (--this.f <= 0) {
/*  78 */       this.f = 10;
/*  79 */       if (!this.a.isLeashed() && !this.a.isPassenger()) {
/*  80 */         if (this.a.h(this.b) >= 144.0D) {
/*  81 */           g();
/*     */         } else {
/*  83 */           this.e.a(this.b, this.d);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void g() {
/*  91 */     BlockPosition blockposition = this.b.getChunkCoordinates();
/*     */     
/*  93 */     for (int i = 0; i < 10; i++) {
/*  94 */       int j = a(-3, 3);
/*  95 */       int k = a(-1, 1);
/*  96 */       int l = a(-3, 3);
/*  97 */       boolean flag = a(blockposition.getX() + j, blockposition.getY() + k, blockposition.getZ() + l);
/*     */       
/*  99 */       if (flag) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean a(int i, int j, int k) {
/* 107 */     if (Math.abs(i - this.b.locX()) < 2.0D && Math.abs(k - this.b.locZ()) < 2.0D)
/* 108 */       return false; 
/* 109 */     if (!a(new BlockPosition(i, j, k))) {
/* 110 */       return false;
/*     */     }
/*     */     
/* 113 */     CraftEntity entity = this.a.getBukkitEntity();
/* 114 */     Location to = new Location(entity.getWorld(), i + 0.5D, j, k + 0.5D, this.a.yaw, this.a.pitch);
/* 115 */     EntityTeleportEvent event = new EntityTeleportEvent((Entity)entity, entity.getLocation(), to);
/* 116 */     this.a.world.getServer().getPluginManager().callEvent((Event)event);
/* 117 */     if (event.isCancelled()) {
/* 118 */       return false;
/*     */     }
/* 120 */     to = event.getTo();
/*     */     
/* 122 */     this.a.setPositionRotation(to.getX(), to.getY(), to.getZ(), to.getYaw(), to.getPitch());
/*     */     
/* 124 */     this.e.o();
/* 125 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean a(BlockPosition blockposition) {
/* 130 */     PathType pathtype = PathfinderNormal.a(this.c, blockposition.i());
/*     */     
/* 132 */     if (pathtype != PathType.WALKABLE) {
/* 133 */       return false;
/*     */     }
/* 135 */     IBlockData iblockdata = this.c.getType(blockposition.down());
/*     */     
/* 137 */     if (!this.j && iblockdata.getBlock() instanceof BlockLeaves) {
/* 138 */       return false;
/*     */     }
/* 140 */     BlockPosition blockposition1 = blockposition.b(this.a.getChunkCoordinates());
/*     */     
/* 142 */     return this.c.getCubes(this.a, this.a.getBoundingBox().a(blockposition1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int a(int i, int j) {
/* 148 */     return this.a.getRandom().nextInt(j - i + 1) + i;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalFollowOwner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */