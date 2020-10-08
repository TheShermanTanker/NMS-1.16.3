/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PathfinderGoalStrollVillageGolem
/*     */   extends PathfinderGoalRandomStroll
/*     */ {
/*     */   public PathfinderGoalStrollVillageGolem(EntityCreature var0, double var1) {
/*  25 */     super(var0, var1, 240, false);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected Vec3D g() {
/*     */     Vec3D var0;
/*  32 */     float var1 = this.a.world.random.nextFloat();
/*  33 */     if (this.a.world.random.nextFloat() < 0.3F) {
/*  34 */       return j();
/*     */     }
/*     */     
/*  37 */     if (var1 < 0.7F) {
/*  38 */       var0 = k();
/*  39 */       if (var0 == null) {
/*  40 */         var0 = l();
/*     */       }
/*     */     } else {
/*  43 */       var0 = l();
/*  44 */       if (var0 == null) {
/*  45 */         var0 = k();
/*     */       }
/*     */     } 
/*     */     
/*  49 */     return (var0 == null) ? j() : var0;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private Vec3D j() {
/*  54 */     return RandomPositionGenerator.b(this.a, 10, 7);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private Vec3D k() {
/*  59 */     WorldServer var0 = (WorldServer)this.a.world;
/*  60 */     List<EntityVillager> var1 = var0.a(EntityTypes.VILLAGER, this.a.getBoundingBox().g(32.0D), this::a);
/*  61 */     if (var1.isEmpty()) {
/*  62 */       return null;
/*     */     }
/*  64 */     EntityVillager var2 = var1.get(this.a.world.random.nextInt(var1.size()));
/*  65 */     Vec3D var3 = var2.getPositionVector();
/*  66 */     return RandomPositionGenerator.a(this.a, 10, 7, var3);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private Vec3D l() {
/*  71 */     SectionPosition var0 = m();
/*  72 */     if (var0 == null) {
/*  73 */       return null;
/*     */     }
/*     */     
/*  76 */     BlockPosition var1 = a(var0);
/*  77 */     if (var1 == null)
/*     */     {
/*  79 */       return null;
/*     */     }
/*     */     
/*  82 */     return RandomPositionGenerator.a(this.a, 10, 7, Vec3D.c(var1));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private SectionPosition m() {
/*  87 */     WorldServer var0 = (WorldServer)this.a.world;
/*     */ 
/*     */ 
/*     */     
/*  91 */     List<SectionPosition> var1 = (List<SectionPosition>)SectionPosition.a(SectionPosition.a(this.a), 2).filter(var1 -> (var0.b(var1) == 0)).collect(Collectors.toList());
/*     */     
/*  93 */     if (var1.isEmpty()) {
/*  94 */       return null;
/*     */     }
/*  96 */     return var1.get(var0.random.nextInt(var1.size()));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private BlockPosition a(SectionPosition var0) {
/* 102 */     WorldServer var1 = (WorldServer)this.a.world;
/* 103 */     VillagePlace var2 = var1.y();
/*     */ 
/*     */     
/* 106 */     List<BlockPosition> var3 = (List<BlockPosition>)var2.c(var0 -> true, var0.q(), 8, VillagePlace.Occupancy.IS_OCCUPIED).map(VillagePlaceRecord::f).collect(Collectors.toList());
/*     */     
/* 108 */     if (var3.isEmpty()) {
/* 109 */       return null;
/*     */     }
/* 111 */     return var3.get(var1.random.nextInt(var3.size()));
/*     */   }
/*     */   
/*     */   private boolean a(EntityVillager var0) {
/* 115 */     return var0.a(this.a.world.getTime());
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalStrollVillageGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */