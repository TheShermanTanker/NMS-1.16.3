/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.Maps;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.longs.LongArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class PersistentStructureLegacy {
/*     */   private static final Map<String, String> a;
/*     */   
/*     */   static {
/*  17 */     a = SystemUtils.<Map<String, String>>a(Maps.newHashMap(), hashmap -> {
/*     */           hashmap.put("Village", "Village");
/*     */           hashmap.put("Mineshaft", "Mineshaft");
/*     */           hashmap.put("Mansion", "Mansion");
/*     */           hashmap.put("Igloo", "Temple");
/*     */           hashmap.put("Desert_Pyramid", "Temple");
/*     */           hashmap.put("Jungle_Pyramid", "Temple");
/*     */           hashmap.put("Swamp_Hut", "Temple");
/*     */           hashmap.put("Stronghold", "Stronghold");
/*     */           hashmap.put("Monument", "Monument");
/*     */           hashmap.put("Fortress", "Fortress");
/*     */           hashmap.put("EndCity", "EndCity");
/*     */         });
/*  30 */     b = SystemUtils.<Map<String, String>>a(Maps.newHashMap(), hashmap -> {
/*     */           hashmap.put("Iglu", "Igloo");
/*     */           hashmap.put("TeDP", "Desert_Pyramid");
/*     */           hashmap.put("TeJP", "Jungle_Pyramid");
/*     */           hashmap.put("TeSH", "Swamp_Hut");
/*     */         });
/*     */   }
/*  37 */   private static final Map<String, String> b; private final boolean c; private final Map<String, Long2ObjectMap<NBTTagCompound>> d = Maps.newHashMap();
/*  38 */   private final Map<String, PersistentIndexed> e = Maps.newHashMap();
/*     */   private final List<String> f;
/*     */   private final List<String> g;
/*     */   
/*     */   public PersistentStructureLegacy(@Nullable WorldPersistentData worldpersistentdata, List<String> list, List<String> list1) {
/*  43 */     this.f = list;
/*  44 */     this.g = list1;
/*  45 */     a(worldpersistentdata);
/*  46 */     boolean flag = false;
/*     */     
/*     */     int i;
/*     */     
/*  50 */     for (Iterator<String> iterator = this.g.iterator(); iterator.hasNext(); i = flag | ((this.d.get(s) != null) ? 1 : 0)) {
/*  51 */       String s = iterator.next();
/*     */     }
/*     */     
/*  54 */     this.c = i;
/*     */   }
/*     */   
/*     */   public void a(long i) {
/*  58 */     Iterator<String> iterator = this.f.iterator();
/*     */     
/*  60 */     while (iterator.hasNext()) {
/*  61 */       String s = iterator.next();
/*  62 */       PersistentIndexed persistentindexed = this.e.get(s);
/*     */       
/*  64 */       if (persistentindexed != null && persistentindexed.c(i)) {
/*  65 */         persistentindexed.d(i);
/*  66 */         persistentindexed.b();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound a(NBTTagCompound nbttagcompound) {
/*  73 */     NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Level");
/*  74 */     ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(nbttagcompound1.getInt("xPos"), nbttagcompound1.getInt("zPos"));
/*     */     
/*  76 */     if (a(chunkcoordintpair.x, chunkcoordintpair.z)) {
/*  77 */       nbttagcompound = a(nbttagcompound, chunkcoordintpair);
/*     */     }
/*     */     
/*  80 */     NBTTagCompound nbttagcompound2 = nbttagcompound1.getCompound("Structures");
/*  81 */     NBTTagCompound nbttagcompound3 = nbttagcompound2.getCompound("References");
/*  82 */     Iterator<String> iterator = this.g.iterator();
/*     */     
/*  84 */     while (iterator.hasNext()) {
/*  85 */       String s = iterator.next();
/*  86 */       StructureGenerator<?> structuregenerator = (StructureGenerator)StructureGenerator.a.get(s.toLowerCase(Locale.ROOT));
/*     */       
/*  88 */       if (!nbttagcompound3.hasKeyOfType(s, 12) && structuregenerator != null) {
/*  89 */         boolean flag = true;
/*  90 */         LongArrayList longarraylist = new LongArrayList();
/*     */         
/*  92 */         for (int i = chunkcoordintpair.x - 8; i <= chunkcoordintpair.x + 8; i++) {
/*  93 */           for (int j = chunkcoordintpair.z - 8; j <= chunkcoordintpair.z + 8; j++) {
/*  94 */             if (a(i, j, s)) {
/*  95 */               longarraylist.add(ChunkCoordIntPair.pair(i, j));
/*     */             }
/*     */           } 
/*     */         } 
/*     */         
/* 100 */         nbttagcompound3.c(s, (List<Long>)longarraylist);
/*     */       } 
/*     */     } 
/*     */     
/* 104 */     nbttagcompound2.set("References", nbttagcompound3);
/* 105 */     nbttagcompound1.set("Structures", nbttagcompound2);
/* 106 */     nbttagcompound.set("Level", nbttagcompound1);
/* 107 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   private boolean a(int i, int j, String s) {
/* 111 */     return !this.c ? false : ((this.d.get(s) != null && ((PersistentIndexed)this.e.get(a.get(s))).b(ChunkCoordIntPair.pair(i, j))));
/*     */   }
/*     */   private boolean a(int i, int j) {
/*     */     String s;
/* 115 */     if (!this.c) {
/* 116 */       return false;
/*     */     }
/* 118 */     Iterator<String> iterator = this.g.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 123 */       if (!iterator.hasNext()) {
/* 124 */         return false;
/*     */       }
/*     */       
/* 127 */       s = iterator.next();
/* 128 */     } while (this.d.get(s) == null || !((PersistentIndexed)this.e.get(a.get(s))).c(ChunkCoordIntPair.pair(i, j)));
/*     */     
/* 130 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private NBTTagCompound a(NBTTagCompound nbttagcompound, ChunkCoordIntPair chunkcoordintpair) {
/* 135 */     NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Level");
/* 136 */     NBTTagCompound nbttagcompound2 = nbttagcompound1.getCompound("Structures");
/* 137 */     NBTTagCompound nbttagcompound3 = nbttagcompound2.getCompound("Starts");
/* 138 */     Iterator<String> iterator = this.g.iterator();
/*     */     
/* 140 */     while (iterator.hasNext()) {
/* 141 */       String s = iterator.next();
/* 142 */       Long2ObjectMap<NBTTagCompound> long2objectmap = this.d.get(s);
/*     */       
/* 144 */       if (long2objectmap != null) {
/* 145 */         long i = chunkcoordintpair.pair();
/*     */         
/* 147 */         if (((PersistentIndexed)this.e.get(a.get(s))).c(i)) {
/* 148 */           NBTTagCompound nbttagcompound4 = (NBTTagCompound)long2objectmap.get(i);
/*     */           
/* 150 */           if (nbttagcompound4 != null) {
/* 151 */             nbttagcompound3.set(s, nbttagcompound4);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 157 */     nbttagcompound2.set("Starts", nbttagcompound3);
/* 158 */     nbttagcompound1.set("Structures", nbttagcompound2);
/* 159 */     nbttagcompound.set("Level", nbttagcompound1);
/* 160 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   private void a(@Nullable WorldPersistentData worldpersistentdata) {
/* 164 */     if (worldpersistentdata != null) {
/* 165 */       Iterator<String> iterator = this.f.iterator();
/*     */       
/* 167 */       while (iterator.hasNext()) {
/* 168 */         String s = iterator.next();
/* 169 */         NBTTagCompound nbttagcompound = new NBTTagCompound();
/*     */         
/*     */         try {
/* 172 */           nbttagcompound = worldpersistentdata.a(s, 1493).getCompound("data").getCompound("Features");
/* 173 */           if (nbttagcompound.isEmpty()) {
/*     */             continue;
/*     */           }
/* 176 */         } catch (IOException iOException) {}
/*     */ 
/*     */ 
/*     */         
/* 180 */         Iterator<String> iterator1 = nbttagcompound.getKeys().iterator();
/*     */         
/* 182 */         while (iterator1.hasNext()) {
/* 183 */           String s1 = iterator1.next();
/* 184 */           NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound(s1);
/* 185 */           long i = ChunkCoordIntPair.pair(nbttagcompound1.getInt("ChunkX"), nbttagcompound1.getInt("ChunkZ"));
/* 186 */           NBTTagList nbttaglist = nbttagcompound1.getList("Children", 10);
/*     */ 
/*     */           
/* 189 */           if (!nbttaglist.isEmpty()) {
/* 190 */             String str1 = nbttaglist.getCompound(0).getString("id");
/* 191 */             String s3 = b.get(str1);
/*     */             
/* 193 */             if (s3 != null) {
/* 194 */               nbttagcompound1.setString("id", s3);
/*     */             }
/*     */           } 
/*     */           
/* 198 */           String s2 = nbttagcompound1.getString("id");
/* 199 */           ((Long2ObjectMap)this.d.computeIfAbsent(s2, s4 -> new Long2ObjectOpenHashMap()))
/*     */             
/* 201 */             .put(i, nbttagcompound1);
/*     */         } 
/*     */         
/* 204 */         String s4 = s + "_index";
/* 205 */         PersistentIndexed persistentindexed = worldpersistentdata.<PersistentIndexed>a(() -> new PersistentIndexed(s4), s4);
/*     */ 
/*     */ 
/*     */         
/* 209 */         if (!persistentindexed.a().isEmpty()) {
/* 210 */           this.e.put(s, persistentindexed); continue;
/*     */         } 
/* 212 */         PersistentIndexed persistentindexed1 = new PersistentIndexed(s4);
/*     */         
/* 214 */         this.e.put(s, persistentindexed1);
/* 215 */         Iterator<String> iterator2 = nbttagcompound.getKeys().iterator();
/*     */         
/* 217 */         while (iterator2.hasNext()) {
/* 218 */           String s5 = iterator2.next();
/* 219 */           NBTTagCompound nbttagcompound2 = nbttagcompound.getCompound(s5);
/*     */           
/* 221 */           persistentindexed1.a(ChunkCoordIntPair.pair(nbttagcompound2.getInt("ChunkX"), nbttagcompound2.getInt("ChunkZ")));
/*     */         } 
/*     */         
/* 224 */         persistentindexed1.b();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static PersistentStructureLegacy a(ResourceKey<DimensionManager> resourcekey, @Nullable WorldPersistentData worldpersistentdata) {
/* 232 */     if (resourcekey == DimensionManager.OVERWORLD) {
/* 233 */       return new PersistentStructureLegacy(worldpersistentdata, (List<String>)ImmutableList.of("Monument", "Stronghold", "Village", "Mineshaft", "Temple", "Mansion"), (List<String>)ImmutableList.of("Village", "Mineshaft", "Mansion", "Igloo", "Desert_Pyramid", "Jungle_Pyramid", "Swamp_Hut", "Stronghold", "Monument"));
/*     */     }
/*     */ 
/*     */     
/* 237 */     if (resourcekey == DimensionManager.THE_NETHER) {
/* 238 */       ImmutableList immutablelist = ImmutableList.of("Fortress");
/* 239 */       return new PersistentStructureLegacy(worldpersistentdata, (List<String>)immutablelist, (List<String>)immutablelist);
/* 240 */     }  if (resourcekey == DimensionManager.THE_END) {
/* 241 */       ImmutableList immutablelist = ImmutableList.of("EndCity");
/* 242 */       return new PersistentStructureLegacy(worldpersistentdata, (List<String>)immutablelist, (List<String>)immutablelist);
/*     */     } 
/* 244 */     throw new RuntimeException(String.format("Unknown dimension type : %s", new Object[] { resourcekey }));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PersistentStructureLegacy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */