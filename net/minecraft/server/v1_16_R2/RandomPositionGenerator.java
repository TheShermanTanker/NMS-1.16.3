/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Objects;
/*     */ import java.util.Random;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.ToDoubleFunction;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class RandomPositionGenerator {
/*     */   @Nullable
/*     */   public static Vec3D a(EntityCreature entitycreature, int i, int j) {
/*  12 */     Objects.requireNonNull(entitycreature); return a(entitycreature, i, j, 0, (Vec3D)null, true, 1.5707963705062866D, entitycreature::f, false, 0, 0, true);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static Vec3D a(EntityCreature entitycreature, int i, int j, int k, @Nullable Vec3D vec3d, double d0) {
/*  17 */     Objects.requireNonNull(entitycreature); return a(entitycreature, i, j, k, vec3d, true, d0, entitycreature::f, true, 0, 0, false);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static Vec3D b(EntityCreature entitycreature, int i, int j) {
/*  22 */     entitycreature.getClass();
/*  23 */     Objects.requireNonNull(entitycreature); return a(entitycreature, i, j, entitycreature::f);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static Vec3D a(EntityCreature entitycreature, int i, int j, ToDoubleFunction<BlockPosition> todoublefunction) {
/*  28 */     return a(entitycreature, i, j, 0, (Vec3D)null, false, 0.0D, todoublefunction, true, 0, 0, true);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static Vec3D a(EntityCreature entitycreature, int i, int j, Vec3D vec3d, float f, int k, int l) {
/*  33 */     Objects.requireNonNull(entitycreature); return a(entitycreature, i, j, 0, vec3d, false, f, entitycreature::f, true, k, l, true);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static Vec3D a(EntityCreature entitycreature, int i, int j, Vec3D vec3d) {
/*  38 */     Vec3D vec3d1 = vec3d.a(entitycreature.locX(), entitycreature.locY(), entitycreature.locZ());
/*     */     
/*  40 */     Objects.requireNonNull(entitycreature); return a(entitycreature, i, j, 0, vec3d1, false, 1.5707963705062866D, entitycreature::f, true, 0, 0, true);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static Vec3D b(EntityCreature entitycreature, int i, int j, Vec3D vec3d) {
/*  45 */     Vec3D vec3d1 = vec3d.a(entitycreature.locX(), entitycreature.locY(), entitycreature.locZ());
/*     */     
/*  47 */     Objects.requireNonNull(entitycreature); return a(entitycreature, i, j, 0, vec3d1, true, 1.5707963705062866D, entitycreature::f, false, 0, 0, true);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static Vec3D a(EntityCreature entitycreature, int i, int j, Vec3D vec3d, double d0) {
/*  52 */     Vec3D vec3d1 = vec3d.a(entitycreature.locX(), entitycreature.locY(), entitycreature.locZ());
/*     */     
/*  54 */     Objects.requireNonNull(entitycreature); return a(entitycreature, i, j, 0, vec3d1, true, d0, entitycreature::f, false, 0, 0, true);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static Vec3D b(EntityCreature entitycreature, int i, int j, int k, Vec3D vec3d, double d0) {
/*  59 */     Vec3D vec3d1 = vec3d.a(entitycreature.locX(), entitycreature.locY(), entitycreature.locZ());
/*     */     
/*  61 */     Objects.requireNonNull(entitycreature); return a(entitycreature, i, j, k, vec3d1, false, d0, entitycreature::f, true, 0, 0, false);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static Vec3D c(EntityCreature entitycreature, int i, int j, Vec3D vec3d) {
/*  66 */     Vec3D vec3d1 = entitycreature.getPositionVector().d(vec3d);
/*     */     
/*  68 */     Objects.requireNonNull(entitycreature); return a(entitycreature, i, j, 0, vec3d1, true, 1.5707963705062866D, entitycreature::f, false, 0, 0, true);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static Vec3D d(EntityCreature entitycreature, int i, int j, Vec3D vec3d) {
/*  73 */     Vec3D vec3d1 = entitycreature.getPositionVector().d(vec3d);
/*     */     
/*  75 */     Objects.requireNonNull(entitycreature); return a(entitycreature, i, j, 0, vec3d1, false, 1.5707963705062866D, entitycreature::f, true, 0, 0, true);
/*     */   }
/*     */   @Nullable
/*     */   private static Vec3D a(EntityCreature entitycreature, int i, int j, int k, @Nullable Vec3D vec3d, boolean flag, double d0, ToDoubleFunction<BlockPosition> todoublefunction, boolean flag1, int l, int i1, boolean flag2) {
/*     */     boolean flag3;
/*  80 */     NavigationAbstract navigationabstract = entitycreature.getNavigation();
/*  81 */     Random random = entitycreature.getRandom();
/*     */ 
/*     */     
/*  84 */     if (entitycreature.ez()) {
/*  85 */       flag3 = entitycreature.ew().a(entitycreature.getPositionVector(), (entitycreature.ex() + i) + 1.0D);
/*     */     } else {
/*  87 */       flag3 = false;
/*     */     } 
/*     */     
/*  90 */     boolean flag4 = false;
/*  91 */     double d1 = Double.NEGATIVE_INFINITY;
/*  92 */     BlockPosition blockposition = entitycreature.getChunkCoordinates();
/*     */     
/*  94 */     for (int j1 = 0; j1 < 10; j1++) {
/*  95 */       BlockPosition blockposition1 = a(random, i, j, k, vec3d, d0);
/*     */       
/*  97 */       if (blockposition1 != null) {
/*  98 */         int k1 = blockposition1.getX();
/*  99 */         int l1 = blockposition1.getY();
/* 100 */         int i2 = blockposition1.getZ();
/*     */ 
/*     */         
/* 103 */         if (entitycreature.ez() && i > 1) {
/* 104 */           BlockPosition blockPosition = entitycreature.ew();
/* 105 */           if (entitycreature.locX() > blockPosition.getX()) {
/* 106 */             k1 -= random.nextInt(i / 2);
/*     */           } else {
/* 108 */             k1 += random.nextInt(i / 2);
/*     */           } 
/*     */           
/* 111 */           if (entitycreature.locZ() > blockPosition.getZ()) {
/* 112 */             i2 -= random.nextInt(i / 2);
/*     */           } else {
/* 114 */             i2 += random.nextInt(i / 2);
/*     */           } 
/*     */         } 
/*     */         
/* 118 */         BlockPosition blockposition2 = new BlockPosition(k1 + entitycreature.locX(), l1 + entitycreature.locY(), i2 + entitycreature.locZ());
/* 119 */         if (entitycreature.world.isLoaded(blockposition2) && 
/* 120 */           blockposition2.getY() >= 0 && blockposition2.getY() <= entitycreature.world.getBuildHeight() && (!flag3 || entitycreature.a(blockposition2)) && (!flag2 || navigationabstract.a(blockposition2))) {
/* 121 */           if (flag1) {
/* 122 */             blockposition2 = a(blockposition2, random.nextInt(l + 1) + i1, entitycreature.world.getBuildHeight(), blockposition3 -> entitycreature.world.getType(blockposition3).getMaterial().isBuildable());
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 127 */           Fluid fluid = entitycreature.world.getFluidIfLoaded(blockposition2);
/* 128 */           if (flag || (fluid != null && !fluid.a(TagsFluid.WATER))) {
/* 129 */             PathType pathtype = PathfinderNormal.a(entitycreature.world, blockposition2.i());
/*     */             
/* 131 */             if (entitycreature.a(pathtype) == 0.0F) {
/* 132 */               double d2 = todoublefunction.applyAsDouble(blockposition2);
/*     */               
/* 134 */               if (d2 > d1) {
/* 135 */                 d1 = d2;
/* 136 */                 blockposition = blockposition2;
/* 137 */                 flag4 = true;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 145 */     if (flag4) {
/* 146 */       return Vec3D.c(blockposition);
/*     */     }
/* 148 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private static BlockPosition a(Random random, int i, int j, int k, @Nullable Vec3D vec3d, double d0) {
/* 154 */     if (vec3d != null && d0 < Math.PI) {
/* 155 */       double d1 = MathHelper.d(vec3d.z, vec3d.x) - 1.5707963705062866D;
/* 156 */       double d2 = d1 + (2.0F * random.nextFloat() - 1.0F) * d0;
/* 157 */       double d3 = Math.sqrt(random.nextDouble()) * MathHelper.a * i;
/* 158 */       double d4 = -d3 * Math.sin(d2);
/* 159 */       double d5 = d3 * Math.cos(d2);
/*     */       
/* 161 */       if (Math.abs(d4) <= i && Math.abs(d5) <= i) {
/* 162 */         int l = random.nextInt(2 * j + 1) - j + k;
/*     */         
/* 164 */         return new BlockPosition(d4, l, d5);
/*     */       } 
/* 166 */       return null;
/*     */     } 
/*     */     
/* 169 */     int i1 = random.nextInt(2 * i + 1) - i;
/* 170 */     int j1 = random.nextInt(2 * j + 1) - j + k;
/* 171 */     int k1 = random.nextInt(2 * i + 1) - i;
/*     */     
/* 173 */     return new BlockPosition(i1, j1, k1);
/*     */   }
/*     */ 
/*     */   
/*     */   static BlockPosition a(BlockPosition blockposition, int i, int j, Predicate<BlockPosition> predicate) {
/* 178 */     if (i < 0)
/* 179 */       throw new IllegalArgumentException("aboveSolidAmount was " + i + ", expected >= 0"); 
/* 180 */     if (!predicate.test(blockposition)) {
/* 181 */       return blockposition;
/*     */     }
/*     */     
/*     */     BlockPosition blockposition1;
/* 185 */     for (blockposition1 = blockposition.up(); blockposition1.getY() < j && predicate.test(blockposition1); blockposition1 = blockposition1.up());
/*     */ 
/*     */ 
/*     */     
/*     */     BlockPosition blockposition3;
/*     */ 
/*     */     
/* 192 */     for (blockposition3 = blockposition1; blockposition3.getY() < j && blockposition3.getY() - blockposition1.getY() < i; blockposition3 = blockposition2) {
/* 193 */       BlockPosition blockposition2 = blockposition3.up();
/* 194 */       if (predicate.test(blockposition2)) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */     
/* 199 */     return blockposition3;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RandomPositionGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */