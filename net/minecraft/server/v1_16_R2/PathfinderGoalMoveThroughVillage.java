/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.EnumSet;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.function.BooleanSupplier;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PathfinderGoalMoveThroughVillage
/*     */   extends PathfinderGoal
/*     */ {
/*     */   protected final EntityCreature a;
/*     */   private final double b;
/*     */   private PathEntity c;
/*     */   private BlockPosition d;
/*     */   private final boolean e;
/*  29 */   private final List<BlockPosition> f = Lists.newArrayList();
/*     */   private final int g;
/*     */   private final BooleanSupplier h;
/*     */   
/*     */   public PathfinderGoalMoveThroughVillage(EntityCreature var0, double var1, boolean var3, int var4, BooleanSupplier var5) {
/*  34 */     this.a = var0;
/*  35 */     this.b = var1;
/*  36 */     this.e = var3;
/*  37 */     this.g = var4;
/*  38 */     this.h = var5;
/*  39 */     a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*     */     
/*  41 */     if (!PathfinderGoalUtil.a(var0)) {
/*  42 */       throw new IllegalArgumentException("Unsupported mob for MoveThroughVillageGoal");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a() {
/*  48 */     if (!PathfinderGoalUtil.a(this.a)) {
/*  49 */       return false;
/*     */     }
/*  51 */     g();
/*     */     
/*  53 */     if (this.e && this.a.world.isDay()) {
/*  54 */       return false;
/*     */     }
/*     */     
/*  57 */     WorldServer var0 = (WorldServer)this.a.world;
/*  58 */     BlockPosition var1 = this.a.getChunkCoordinates();
/*     */     
/*  60 */     if (!var0.a(var1, 6)) {
/*  61 */       return false;
/*     */     }
/*     */     
/*  64 */     Vec3D var2 = RandomPositionGenerator.a(this.a, 15, 7, var2 -> {
/*     */           if (!var0.a_(var2)) {
/*     */             return Double.NEGATIVE_INFINITY;
/*     */           }
/*     */           
/*     */           Optional<BlockPosition> var3 = var0.y().c(VillagePlaceType.b, this::a, var2, 10, VillagePlace.Occupancy.IS_OCCUPIED);
/*     */           
/*     */           return !var3.isPresent() ? Double.NEGATIVE_INFINITY : -((BlockPosition)var3.get()).j(var1);
/*     */         });
/*     */     
/*  74 */     if (var2 == null) {
/*  75 */       return false;
/*     */     }
/*  77 */     Optional<BlockPosition> var3 = var0.y().c(VillagePlaceType.b, this::a, new BlockPosition(var2), 10, VillagePlace.Occupancy.IS_OCCUPIED);
/*  78 */     if (!var3.isPresent()) {
/*  79 */       return false;
/*     */     }
/*  81 */     this.d = ((BlockPosition)var3.get()).immutableCopy();
/*     */     
/*  83 */     Navigation var4 = (Navigation)this.a.getNavigation();
/*  84 */     boolean var5 = var4.f();
/*  85 */     var4.a(this.h.getAsBoolean());
/*  86 */     this.c = var4.a(this.d, 0);
/*  87 */     var4.a(var5);
/*  88 */     if (this.c == null) {
/*  89 */       Vec3D vec3D = RandomPositionGenerator.b(this.a, 10, 7, Vec3D.c(this.d));
/*  90 */       if (vec3D == null) {
/*  91 */         return false;
/*     */       }
/*  93 */       var4.a(this.h.getAsBoolean());
/*  94 */       this.c = this.a.getNavigation().a(vec3D.x, vec3D.y, vec3D.z, 0);
/*  95 */       var4.a(var5);
/*  96 */       if (this.c == null) {
/*  97 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 101 */     for (int var6 = 0; var6 < this.c.e(); var6++) {
/* 102 */       PathPoint var7 = this.c.a(var6);
/* 103 */       BlockPosition var8 = new BlockPosition(var7.a, var7.b + 1, var7.c);
/* 104 */       if (BlockDoor.a(this.a.world, var8)) {
/*     */         
/* 106 */         this.c = this.a.getNavigation().a(var7.a, var7.b, var7.c, 0);
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 111 */     return (this.c != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b() {
/* 116 */     if (this.a.getNavigation().m()) {
/* 117 */       return false;
/*     */     }
/* 119 */     return !this.d.a(this.a.getPositionVector(), (this.a.getWidth() + this.g));
/*     */   }
/*     */ 
/*     */   
/*     */   public void c() {
/* 124 */     this.a.getNavigation().a(this.c, this.b);
/*     */   }
/*     */ 
/*     */   
/*     */   public void d() {
/* 129 */     if (this.a.getNavigation().m() || this.d.a(this.a.getPositionVector(), this.g)) {
/* 130 */       this.f.add(this.d);
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean a(BlockPosition var0) {
/* 135 */     for (BlockPosition var2 : this.f) {
/* 136 */       if (Objects.equals(var0, var2)) {
/* 137 */         return false;
/*     */       }
/*     */     } 
/* 140 */     return true;
/*     */   }
/*     */   
/*     */   private void g() {
/* 144 */     if (this.f.size() > 15)
/* 145 */       this.f.remove(0); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalMoveThroughVillage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */