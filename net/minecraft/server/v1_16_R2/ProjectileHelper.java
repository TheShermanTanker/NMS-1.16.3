/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Optional;
/*     */ import java.util.function.Predicate;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ProjectileHelper
/*     */ {
/*     */   public static MovingObjectPosition a(Entity var0, Predicate<Entity> var1) {
/*  27 */     Vec3D var2 = var0.getMot();
/*  28 */     World var3 = var0.world;
/*     */     
/*  30 */     Vec3D var4 = var0.getPositionVector();
/*  31 */     Vec3D var5 = var4.e(var2);
/*  32 */     MovingObjectPosition var6 = var3.rayTrace(new RayTrace(var4, var5, RayTrace.BlockCollisionOption.COLLIDER, RayTrace.FluidCollisionOption.NONE, var0));
/*     */     
/*  34 */     if (var6.getType() != MovingObjectPosition.EnumMovingObjectType.MISS) {
/*  35 */       var5 = var6.getPos();
/*     */     }
/*  37 */     MovingObjectPosition var7 = a(var3, var0, var4, var5, var0.getBoundingBox().b(var0.getMot()).g(1.0D), var1);
/*     */     
/*  39 */     if (var7 != null) {
/*  40 */       var6 = var7;
/*     */     }
/*     */     
/*  43 */     return var6;
/*     */   }
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
/*     */   @Nullable
/*     */   public static MovingObjectPositionEntity a(World var0, Entity var1, Vec3D var2, Vec3D var3, AxisAlignedBB var4, Predicate<Entity> var5) {
/*  90 */     double var6 = Double.MAX_VALUE;
/*  91 */     Entity var8 = null;
/*     */     
/*  93 */     for (Entity var10 : var0.getEntities(var1, var4, var5)) {
/*  94 */       AxisAlignedBB var11 = var10.getBoundingBox().g(0.30000001192092896D);
/*  95 */       Optional<Vec3D> var12 = var11.b(var2, var3);
/*  96 */       if (var12.isPresent()) {
/*  97 */         double var13 = var2.distanceSquared(var12.get());
/*  98 */         if (var13 < var6) {
/*  99 */           var8 = var10;
/* 100 */           var6 = var13;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 105 */     if (var8 == null) {
/* 106 */       return null;
/*     */     }
/* 108 */     return new MovingObjectPositionEntity(var8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final void a(Entity var0, float var1) {
/* 118 */     Vec3D var2 = var0.getMot();
/*     */     
/* 120 */     if (var2.g() == 0.0D) {
/*     */       return;
/*     */     }
/*     */     
/* 124 */     float var3 = MathHelper.sqrt(Entity.c(var2));
/* 125 */     var0.yaw = (float)(MathHelper.d(var2.z, var2.x) * 57.2957763671875D) + 90.0F;
/* 126 */     var0.pitch = (float)(MathHelper.d(var3, var2.y) * 57.2957763671875D) - 90.0F;
/*     */     
/* 128 */     while (var0.pitch - var0.lastPitch < -180.0F) {
/* 129 */       var0.lastPitch -= 360.0F;
/*     */     }
/* 131 */     while (var0.pitch - var0.lastPitch >= 180.0F) {
/* 132 */       var0.lastPitch += 360.0F;
/*     */     }
/*     */     
/* 135 */     while (var0.yaw - var0.lastYaw < -180.0F) {
/* 136 */       var0.lastYaw -= 360.0F;
/*     */     }
/* 138 */     while (var0.yaw - var0.lastYaw >= 180.0F) {
/* 139 */       var0.lastYaw += 360.0F;
/*     */     }
/*     */     
/* 142 */     var0.pitch = MathHelper.g(var1, var0.lastPitch, var0.pitch);
/* 143 */     var0.yaw = MathHelper.g(var1, var0.lastYaw, var0.yaw);
/*     */   }
/*     */   
/*     */   public static EnumHand a(EntityLiving var0, Item var1) {
/* 147 */     return (var0.getItemInMainHand().getItem() == var1) ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
/*     */   }
/*     */   
/*     */   public static EntityArrow a(EntityLiving var0, ItemStack var1, float var2) {
/* 151 */     ItemArrow var3 = (var1.getItem() instanceof ItemArrow) ? (ItemArrow)var1.getItem() : (ItemArrow)Items.ARROW;
/* 152 */     EntityArrow var4 = var3.a(var0.world, var1, var0);
/* 153 */     var4.a(var0, var2);
/*     */     
/* 155 */     if (var1.getItem() == Items.TIPPED_ARROW && 
/* 156 */       var4 instanceof EntityTippedArrow) {
/* 157 */       ((EntityTippedArrow)var4).b(var1);
/*     */     }
/*     */ 
/*     */     
/* 161 */     return var4;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ProjectileHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */