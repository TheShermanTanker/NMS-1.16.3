/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface IEntityAccess
/*     */ {
/*     */   default <T extends Entity> List<T> b(Class<? extends T> oclass, AxisAlignedBB axisalignedbb, @Nullable Predicate<? super T> predicate) {
/*  18 */     return a(oclass, axisalignedbb, predicate);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   default List<Entity> getEntities(@Nullable Entity entity, AxisAlignedBB axisalignedbb) {
/*  24 */     return getEntities(entity, axisalignedbb, IEntitySelector.g);
/*     */   }
/*     */   default boolean a(@Nullable Entity entity, VoxelShape voxelshape) {
/*     */     Entity entity1;
/*  28 */     if (voxelshape.isEmpty()) {
/*  29 */       return true;
/*     */     }
/*  31 */     Iterator<Entity> iterator = getEntities(entity, voxelshape.getBoundingBox()).iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/*  36 */       if (!iterator.hasNext()) {
/*  37 */         return true;
/*     */       }
/*     */       
/*  40 */       entity1 = iterator.next();
/*  41 */     } while (entity1.dead || !entity1.i || (entity != null && entity1.isSameVehicle(entity)) || !VoxelShapes.c(voxelshape, VoxelShapes.a(entity1.getBoundingBox()), OperatorBoolean.AND));
/*     */     
/*  43 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   default <T extends Entity> List<T> a(Class<? extends T> oclass, AxisAlignedBB axisalignedbb) {
/*  48 */     return a(oclass, axisalignedbb, (Predicate)IEntitySelector.g);
/*     */   }
/*     */   
/*     */   default <T extends Entity> List<T> b(Class<? extends T> oclass, AxisAlignedBB axisalignedbb) {
/*  52 */     return b(oclass, axisalignedbb, (Predicate)IEntitySelector.g);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default List<Entity> getHardCollidingEntities(@Nullable Entity entity, AxisAlignedBB axisalignedbb, Predicate<Entity> predicate) {
/*  60 */     return getEntities(entity, axisalignedbb, predicate);
/*     */   }
/*     */ 
/*     */   
/*     */   default Stream<VoxelShape> c(@Nullable Entity entity, AxisAlignedBB axisalignedbb, Predicate<Entity> predicate) {
/*  65 */     if (axisalignedbb.a() < 1.0E-7D) {
/*  66 */       return Stream.empty();
/*     */     }
/*  68 */     AxisAlignedBB axisalignedbb1 = axisalignedbb.g(1.0E-7D);
/*     */     
/*  70 */     if (predicate == null) predicate = (e -> true); 
/*  71 */     predicate = predicate.and(entity1 -> 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  77 */         !((entity == null) ? !entity1.aY() : !entity.j(entity1)));
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
/*  92 */     return ((entity != null && entity.hardCollides()) ? getEntities(entity, axisalignedbb1, predicate) : getHardCollidingEntities(entity, axisalignedbb1, predicate)).stream().map(Entity::getBoundingBox).map(VoxelShapes::a);
/*     */   }
/*     */   
/*     */   default EntityHuman findNearbyPlayer(Entity entity, double d0, @Nullable Predicate<Entity> predicate) {
/*  96 */     return findNearbyPlayer(entity.locX(), entity.locY(), entity.locZ(), d0, predicate); } @Nullable
/*  97 */   default EntityHuman findNearbyPlayer(double d0, double d1, double d2, double d3, @Nullable Predicate<Entity> predicate) { return a(d0, d1, d2, d3, predicate); } @Nullable
/*     */   default EntityHuman a(double d0, double d1, double d2, double d3, @Nullable Predicate<Entity> predicate) {
/*  99 */     double d4 = -1.0D;
/* 100 */     EntityHuman entityhuman = null;
/* 101 */     Iterator<? extends EntityHuman> iterator = getPlayers().iterator();
/*     */     
/* 103 */     while (iterator.hasNext()) {
/* 104 */       EntityHuman entityhuman1 = iterator.next();
/*     */       
/* 106 */       if (predicate == null || predicate.test(entityhuman1)) {
/* 107 */         double d5 = entityhuman1.h(d0, d1, d2);
/*     */         
/* 109 */         if ((d3 < 0.0D || d5 < d3 * d3) && (d4 == -1.0D || d5 < d4)) {
/* 110 */           d4 = d5;
/* 111 */           entityhuman = entityhuman1;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 116 */     return entityhuman;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   default EntityHuman findNearbyPlayer(Entity entity, double d0) {
/* 121 */     return a(entity.locX(), entity.locY(), entity.locZ(), d0, false);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   default EntityHuman a(double d0, double d1, double d2, double d3, boolean flag) {
/* 126 */     Predicate<Entity> predicate = flag ? IEntitySelector.e : IEntitySelector.g;
/*     */     
/* 128 */     return a(d0, d1, d2, d3, predicate);
/*     */   }
/*     */ 
/*     */   
/*     */   default boolean isAffectsSpawningPlayerNearby(double d0, double d1, double d2, double d3) {
/* 133 */     Iterator<? extends EntityHuman> iterator = getPlayers().iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     while (true) {
/* 138 */       if (!iterator.hasNext()) {
/* 139 */         return false;
/*     */       }
/*     */       
/* 142 */       EntityHuman entityhuman = iterator.next();
/* 143 */       if (IEntitySelector.affectsSpawning.test(entityhuman))
/*     */       
/* 145 */       { double d4 = entityhuman.getDistanceSquared(d0, d1, d2);
/* 146 */         if (d3 < 0.0D || d4 < d3 * d3)
/*     */           break;  } 
/* 148 */     }  return true;
/*     */   }
/*     */ 
/*     */   
/*     */   default boolean isPlayerNearby(double d0, double d1, double d2, double d3) {
/* 153 */     Iterator<? extends EntityHuman> iterator = getPlayers().iterator();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     while (true) {
/* 162 */       if (!iterator.hasNext()) {
/* 163 */         return false;
/*     */       }
/*     */       
/* 166 */       EntityHuman entityhuman = iterator.next();
/* 167 */       if (IEntitySelector.g.test(entityhuman) && 
/* 168 */         IEntitySelector.b.test(entityhuman))
/*     */       
/* 170 */       { double d4 = entityhuman.h(d0, d1, d2);
/* 171 */         if (d3 < 0.0D || d4 < d3 * d3)
/*     */           break;  } 
/* 173 */     }  return true;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   default EntityHuman a(PathfinderTargetCondition pathfindertargetcondition, EntityLiving entityliving) {
/* 178 */     return a(getPlayers(), pathfindertargetcondition, entityliving, entityliving.locX(), entityliving.locY(), entityliving.locZ());
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   default EntityHuman a(PathfinderTargetCondition pathfindertargetcondition, EntityLiving entityliving, double d0, double d1, double d2) {
/* 183 */     return a(getPlayers(), pathfindertargetcondition, entityliving, d0, d1, d2);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   default EntityHuman a(PathfinderTargetCondition pathfindertargetcondition, double d0, double d1, double d2) {
/* 188 */     return a(getPlayers(), pathfindertargetcondition, (EntityLiving)null, d0, d1, d2);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   default <T extends EntityLiving> T a(Class<? extends T> oclass, PathfinderTargetCondition pathfindertargetcondition, @Nullable EntityLiving entityliving, double d0, double d1, double d2, AxisAlignedBB axisalignedbb) {
/* 193 */     return a(a(oclass, axisalignedbb, (Predicate<? super T>)null), pathfindertargetcondition, entityliving, d0, d1, d2);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   default <T extends EntityLiving> T b(Class<? extends T> oclass, PathfinderTargetCondition pathfindertargetcondition, @Nullable EntityLiving entityliving, double d0, double d1, double d2, AxisAlignedBB axisalignedbb) {
/* 198 */     return a(b(oclass, axisalignedbb, null), pathfindertargetcondition, entityliving, d0, d1, d2);
/*     */   }
/*     */   @Nullable
/*     */   default <T extends EntityLiving> T a(List<? extends T> list, PathfinderTargetCondition pathfindertargetcondition, @Nullable EntityLiving entityliving, double d0, double d1, double d2) {
/*     */     EntityLiving entityLiving;
/* 203 */     double d3 = -1.0D;
/* 204 */     T t0 = null;
/* 205 */     Iterator<? extends T> iterator = list.iterator();
/*     */     
/* 207 */     while (iterator.hasNext()) {
/* 208 */       EntityLiving entityLiving1 = (EntityLiving)iterator.next();
/*     */       
/* 210 */       if (pathfindertargetcondition.a(entityliving, entityLiving1)) {
/* 211 */         double d4 = entityLiving1.h(d0, d1, d2);
/*     */         
/* 213 */         if (d3 == -1.0D || d4 < d3) {
/* 214 */           d3 = d4;
/* 215 */           entityLiving = entityLiving1;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 220 */     return (T)entityLiving;
/*     */   }
/*     */   
/*     */   default List<EntityHuman> a(PathfinderTargetCondition pathfindertargetcondition, EntityLiving entityliving, AxisAlignedBB axisalignedbb) {
/* 224 */     List<EntityHuman> list = Lists.newArrayList();
/* 225 */     Iterator<? extends EntityHuman> iterator = getPlayers().iterator();
/*     */     
/* 227 */     while (iterator.hasNext()) {
/* 228 */       EntityHuman entityhuman = iterator.next();
/*     */       
/* 230 */       if (axisalignedbb.e(entityhuman.locX(), entityhuman.locY(), entityhuman.locZ()) && pathfindertargetcondition.a(entityliving, entityhuman)) {
/* 231 */         list.add(entityhuman);
/*     */       }
/*     */     } 
/*     */     
/* 235 */     return list;
/*     */   }
/*     */   
/*     */   default <T extends EntityLiving> List<T> a(Class<? extends T> oclass, PathfinderTargetCondition pathfindertargetcondition, EntityLiving entityliving, AxisAlignedBB axisalignedbb) {
/* 239 */     List<T> list = (List)a((Class)oclass, axisalignedbb, (Predicate<? super Entity>)null);
/* 240 */     List<T> list1 = Lists.newArrayList();
/* 241 */     Iterator<T> iterator = list.iterator();
/*     */     
/* 243 */     while (iterator.hasNext()) {
/* 244 */       EntityLiving entityLiving = (EntityLiving)iterator.next();
/*     */       
/* 246 */       if (pathfindertargetcondition.a(entityliving, entityLiving)) {
/* 247 */         list1.add((T)entityLiving);
/*     */       }
/*     */     } 
/*     */     
/* 251 */     return list1;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   default EntityHuman b(UUID uuid) {
/* 257 */     return getPlayerByUUID(uuid);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   default EntityHuman getPlayerByUUID(UUID uuid) {
/* 262 */     for (int i = 0; i < getPlayers().size(); i++) {
/* 263 */       EntityHuman entityhuman = getPlayers().get(i);
/*     */       
/* 265 */       if (uuid.equals(entityhuman.getUniqueID())) {
/* 266 */         return entityhuman;
/*     */       }
/*     */     } 
/*     */     
/* 270 */     return null;
/*     */   }
/*     */   
/*     */   List<Entity> getEntities(@Nullable Entity paramEntity, AxisAlignedBB paramAxisAlignedBB, @Nullable Predicate<? super Entity> paramPredicate);
/*     */   
/*     */   <T extends Entity> List<T> a(Class<? extends T> paramClass, AxisAlignedBB paramAxisAlignedBB, @Nullable Predicate<? super T> paramPredicate);
/*     */   
/*     */   List<? extends EntityHuman> getPlayers();
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IEntityAccess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */