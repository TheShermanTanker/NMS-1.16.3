/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectListIterator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class HeightMap {
/*     */   private static final Predicate<IBlockData> a;
/*     */   private static final Predicate<IBlockData> b;
/*     */   
/*     */   static {
/*  16 */     a = (iblockdata -> !iblockdata.isAir());
/*     */ 
/*     */     
/*  19 */     b = (iblockdata -> iblockdata.getMaterial().isSolid());
/*     */   }
/*     */ 
/*     */   
/*  23 */   private final char[] heightmap = new char[256]; private final Predicate<IBlockData> d; private final IChunkAccess e;
/*     */   public DataBits toDataBits() {
/*  25 */     DataBits ret = new DataBits(9, 256);
/*     */     
/*  27 */     for (int i = 0, len = this.heightmap.length; i < len; i++) {
/*  28 */       ret.set(i, this.heightmap[i]);
/*     */     }
/*     */     
/*  31 */     return ret;
/*     */   }
/*     */   
/*     */   public void copyFrom(HeightMap other) {
/*  35 */     if (other.heightmap.length != this.heightmap.length) {
/*  36 */       throw new IllegalStateException("Heightmap lengths must match");
/*     */     }
/*  38 */     System.arraycopy(other.heightmap, 0, this.heightmap, 0, this.heightmap.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HeightMap(IChunkAccess ichunkaccess, Type heightmap_type) {
/*  45 */     this.d = heightmap_type.e();
/*  46 */     this.e = ichunkaccess;
/*     */   }
/*     */   
/*     */   public static void a(IChunkAccess ichunkaccess, Set<Type> set) {
/*  50 */     int i = set.size();
/*  51 */     ObjectArrayList objectArrayList = new ObjectArrayList(i);
/*  52 */     ObjectListIterator<HeightMap> objectlistiterator = objectArrayList.iterator();
/*  53 */     int j = ichunkaccess.b() + 16;
/*  54 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/*     */     
/*  56 */     for (int k = 0; k < 16; k++) {
/*  57 */       for (int l = 0; l < 16; l++) {
/*  58 */         Iterator<Type> iterator = set.iterator();
/*     */         
/*  60 */         while (iterator.hasNext()) {
/*  61 */           Type heightmap_type = iterator.next();
/*     */           
/*  63 */           objectArrayList.add(ichunkaccess.a(heightmap_type));
/*     */         } 
/*     */         
/*  66 */         for (int i1 = j - 1; i1 >= 0; i1--) {
/*  67 */           blockposition_mutableblockposition.d(k, i1, l);
/*  68 */           IBlockData iblockdata = ichunkaccess.getType(blockposition_mutableblockposition);
/*     */           
/*  70 */           if (!iblockdata.a(Blocks.AIR)) {
/*  71 */             while (objectlistiterator.hasNext()) {
/*  72 */               HeightMap heightmap = (HeightMap)objectlistiterator.next();
/*     */               
/*  74 */               if (heightmap.d.test(iblockdata)) {
/*  75 */                 heightmap.a(k, l, i1 + 1);
/*  76 */                 objectlistiterator.remove();
/*     */               } 
/*     */             } 
/*     */             
/*  80 */             if (objectArrayList.isEmpty()) {
/*     */               break;
/*     */             }
/*     */             
/*  84 */             objectlistiterator.back(i);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(int i, int j, int k, IBlockData iblockdata) {
/*  93 */     int l = a(i, k);
/*     */     
/*  95 */     if (j <= l - 2) {
/*  96 */       return false;
/*     */     }
/*  98 */     if (this.d.test(iblockdata)) {
/*  99 */       if (j >= l) {
/* 100 */         a(i, k, j + 1);
/* 101 */         return true;
/*     */       } 
/* 103 */     } else if (l - 1 == j) {
/* 104 */       BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/*     */       
/* 106 */       for (int i1 = j - 1; i1 >= 0; i1--) {
/* 107 */         blockposition_mutableblockposition.d(i, i1, k);
/* 108 */         if (this.d.test(this.e.getType(blockposition_mutableblockposition))) {
/* 109 */           a(i, k, i1 + 1);
/* 110 */           return true;
/*     */         } 
/*     */       } 
/*     */       
/* 114 */       a(i, k, 0);
/* 115 */       return true;
/*     */     } 
/*     */     
/* 118 */     return false;
/*     */   }
/*     */   
/*     */   public final int get(int x, int z) {
/* 122 */     return a(x, z);
/*     */   } public int a(int i, int j) {
/* 124 */     return a(c(i, j));
/*     */   }
/*     */   
/*     */   private int a(int i) {
/* 128 */     return this.heightmap[i];
/*     */   }
/*     */   
/*     */   private void a(int i, int j, int k) {
/* 132 */     this.heightmap[c(i, j)] = (char)k;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(long[] along) {
/* 137 */     DataBits databits = new DataBits(9, 256, along);
/* 138 */     for (int i = 0, len = this.heightmap.length; i < len; i++) {
/* 139 */       this.heightmap[i] = (char)databits.get(i);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public long[] a() {
/* 145 */     return toDataBits().a();
/*     */   }
/*     */   
/*     */   private static int c(int i, int j) {
/* 149 */     return i + j * 16;
/*     */   }
/*     */   
/*     */   public enum Type implements INamable { MOTION_BLOCKING_NO_LEAVES,
/*     */     MOTION_BLOCKING,
/* 154 */     WORLD_SURFACE_WG("WORLD_SURFACE_WG", HeightMap.Use.WORLDGEN, (String)HeightMap.a), WORLD_SURFACE("WORLD_SURFACE", HeightMap.Use.CLIENT, (String)HeightMap.a), OCEAN_FLOOR_WG("OCEAN_FLOOR_WG", HeightMap.Use.WORLDGEN, (String)HeightMap.b), OCEAN_FLOOR("OCEAN_FLOOR", HeightMap.Use.LIVE_WORLD, (String)HeightMap.b); private static final Map<String, Type> k; private final Predicate<IBlockData> j; static { MOTION_BLOCKING = new Type("MOTION_BLOCKING", 4, "MOTION_BLOCKING", HeightMap.Use.CLIENT, iblockdata -> 
/* 155 */           (iblockdata.getMaterial().isSolid() || !iblockdata.getFluid().isEmpty()));
/* 156 */       MOTION_BLOCKING_NO_LEAVES = new Type("MOTION_BLOCKING_NO_LEAVES", 5, "MOTION_BLOCKING_NO_LEAVES", HeightMap.Use.LIVE_WORLD, iblockdata -> 
/* 157 */           ((iblockdata.getMaterial().isSolid() || !iblockdata.getFluid().isEmpty()) && !(iblockdata.getBlock() instanceof BlockLeaves))); }
/*     */      private final HeightMap.Use i; private final String h; public static final Codec<Type> g;
/*     */     static {
/* 160 */       g = INamable.a(Type::values, Type::a);
/*     */ 
/*     */ 
/*     */       
/* 164 */       k = SystemUtils.<Map<String, Type>>a(Maps.newHashMap(), hashmap -> {
/*     */             Type[] aheightmap_type = values();
/*     */             int i = aheightmap_type.length;
/*     */             for (int j = 0; j < i; j++) {
/*     */               Type heightmap_type = aheightmap_type[j];
/*     */               hashmap.put(heightmap_type.h, heightmap_type);
/*     */             } 
/*     */           });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     Type(String s, HeightMap.Use heightmap_use, Predicate<IBlockData> predicate) {
/* 177 */       this.h = s;
/* 178 */       this.i = heightmap_use;
/* 179 */       this.j = predicate;
/*     */     }
/*     */     
/*     */     public String b() {
/* 183 */       return this.h;
/*     */     }
/*     */     
/*     */     public boolean c() {
/* 187 */       return (this.i == HeightMap.Use.CLIENT);
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public static Type a(String s) {
/* 192 */       return k.get(s);
/*     */     }
/*     */     
/*     */     public Predicate<IBlockData> e() {
/* 196 */       return this.j;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 201 */       return this.h;
/*     */     } }
/*     */ 
/*     */   
/*     */   public enum Use
/*     */   {
/* 207 */     WORLDGEN, LIVE_WORLD, CLIENT;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\HeightMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */