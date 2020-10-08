/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.AbstractMap;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Pathfinder
/*     */ {
/*     */   private final int b;
/*     */   private final PathfinderAbstract c;
/*  18 */   private final PathPoint[] a = new PathPoint[32];
/*     */   public PathfinderAbstract getPathfinder() {
/*  20 */     return this.c;
/*  21 */   } private final Path d = new Path();
/*     */   
/*     */   public Pathfinder(PathfinderAbstract pathfinderabstract, int i) {
/*  24 */     this.c = pathfinderabstract;
/*  25 */     this.b = i;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public PathEntity a(ChunkCache chunkcache, EntityInsentient entityinsentient, Set<BlockPosition> set, float f, int i, float f1) {
/*  30 */     this.d.a();
/*  31 */     this.c.a(chunkcache, entityinsentient);
/*  32 */     PathPoint pathpoint = this.c.b();
/*     */     
/*  34 */     List<Map.Entry<PathDestination, BlockPosition>> map = Lists.newArrayList();
/*  35 */     for (BlockPosition blockposition : set) {
/*  36 */       map.add(new AbstractMap.SimpleEntry<>(this.c.a(blockposition.getX(), blockposition.getY(), blockposition.getZ()), blockposition));
/*     */     }
/*     */     
/*  39 */     PathEntity pathentity = a(pathpoint, map, f, i, f1);
/*     */     
/*  41 */     this.c.a();
/*  42 */     return pathentity;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private PathEntity a(PathPoint pathpoint, List<Map.Entry<PathDestination, BlockPosition>> list, float f, int i, float f1) {
/*  49 */     pathpoint.e = 0.0F;
/*  50 */     pathpoint.f = a(pathpoint, list);
/*  51 */     pathpoint.g = pathpoint.f;
/*  52 */     this.d.a();
/*  53 */     this.d.a(pathpoint);
/*  54 */     ImmutableSet immutableSet = ImmutableSet.of();
/*  55 */     int j = 0;
/*  56 */     List<Map.Entry<PathDestination, BlockPosition>> set2 = Lists.newArrayListWithExpectedSize(list.size());
/*  57 */     int k = (int)(this.b * f1);
/*     */ 
/*     */     
/*  60 */     j++;
/*  61 */     while (!this.d.e() && j < k) {
/*     */ 
/*     */ 
/*     */       
/*  65 */       PathPoint pathpoint1 = this.d.c();
/*     */       
/*  67 */       pathpoint1.i = true;
/*     */       
/*  69 */       for (int i1 = 0; i1 < list.size(); i1++) {
/*  70 */         Map.Entry<PathDestination, BlockPosition> entry = list.get(i1);
/*  71 */         PathDestination pathdestination = entry.getKey();
/*     */         
/*  73 */         if (pathpoint1.c(pathdestination) <= i) {
/*  74 */           pathdestination.e();
/*  75 */           set2.add(entry);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/*  80 */       if (!set2.isEmpty()) {
/*     */         break;
/*     */       }
/*     */       
/*  84 */       if (pathpoint1.a(pathpoint) < f) {
/*  85 */         int l = this.c.a(this.a, pathpoint1);
/*     */         
/*  87 */         for (int m = 0; m < l; m++) {
/*  88 */           PathPoint pathpoint2 = this.a[m];
/*  89 */           float f2 = pathpoint1.a(pathpoint2);
/*     */           
/*  91 */           pathpoint1.j += f2;
/*  92 */           float f3 = pathpoint1.e + f2 + pathpoint2.k;
/*     */           
/*  94 */           if (pathpoint2.j < f && (!pathpoint2.c() || f3 < pathpoint2.e)) {
/*  95 */             pathpoint2.h = pathpoint1;
/*  96 */             pathpoint2.e = f3;
/*  97 */             pathpoint2.f = a(pathpoint2, list) * 1.5F;
/*  98 */             if (pathpoint2.c()) {
/*  99 */               this.d.a(pathpoint2, pathpoint2.e + pathpoint2.f);
/*     */             } else {
/* 101 */               pathpoint2.g = pathpoint2.e + pathpoint2.f;
/* 102 */               this.d.a(pathpoint2);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 110 */     PathEntity best = null;
/* 111 */     boolean useSet1 = set2.isEmpty();
/*     */     
/* 113 */     Comparator<PathEntity> comparator = useSet1 ? Comparator.<PathEntity>comparingInt(PathEntity::e) : Comparator.<PathEntity>comparingDouble(PathEntity::n).thenComparingInt(PathEntity::e);
/* 114 */     for (Map.Entry<PathDestination, BlockPosition> entry : useSet1 ? list : set2) {
/* 115 */       PathEntity pathEntity = a(((PathDestination)entry.getKey()).d(), entry.getValue(), !useSet1);
/* 116 */       if (best == null || comparator.compare(pathEntity, best) < 0)
/* 117 */         best = pathEntity; 
/*     */     } 
/* 119 */     return best;
/*     */   }
/*     */ 
/*     */   
/*     */   private float a(PathPoint pathpoint, List<Map.Entry<PathDestination, BlockPosition>> list) {
/* 124 */     float f = Float.MAX_VALUE;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     for (int i = 0, listSize = list.size(); i < listSize; f = Math.min(f1, f), i++) {
/* 130 */       PathDestination pathdestination = (PathDestination)((Map.Entry)list.get(i)).getKey();
/*     */ 
/*     */       
/* 133 */       float f1 = pathpoint.a(pathdestination);
/* 134 */       pathdestination.a(f1, pathpoint);
/*     */     } 
/*     */     
/* 137 */     return f;
/*     */   }
/*     */   
/*     */   private PathEntity a(PathPoint pathpoint, BlockPosition blockposition, boolean flag) {
/* 141 */     List<PathPoint> list = Lists.newArrayList();
/* 142 */     PathPoint pathpoint1 = pathpoint;
/*     */     
/* 144 */     list.add(0, pathpoint);
/*     */     
/* 146 */     while (pathpoint1.h != null) {
/* 147 */       pathpoint1 = pathpoint1.h;
/* 148 */       list.add(0, pathpoint1);
/*     */     } 
/*     */     
/* 151 */     return new PathEntity(list, blockposition, flag);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Pathfinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */