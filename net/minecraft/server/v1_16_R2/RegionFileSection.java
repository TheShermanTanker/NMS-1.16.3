/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.datafixers.DataFixer;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.DataResult;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import com.mojang.serialization.DynamicOps;
/*     */ import com.mojang.serialization.OptionalDynamic;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.longs.LongLinkedOpenHashSet;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.function.BooleanSupplier;
/*     */ import java.util.function.Function;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class RegionFileSection<R> extends RegionFileCache implements AutoCloseable {
/*  26 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*  28 */   private final Long2ObjectMap<Optional<R>> c = (Long2ObjectMap<Optional<R>>)new Long2ObjectOpenHashMap();
/*  29 */   protected final LongLinkedOpenHashSet d = new LongLinkedOpenHashSet();
/*     */   private final Function<Runnable, Codec<R>> e;
/*     */   private final Function<Runnable, R> f;
/*     */   private final DataFixer g;
/*     */   private final DataFixTypes h;
/*     */   
/*     */   public RegionFileSection(File file, Function<Runnable, Codec<R>> function, Function<Runnable, R> function1, DataFixer datafixer, DataFixTypes datafixtypes, boolean flag) {
/*  36 */     super(file, flag);
/*  37 */     this.e = function;
/*  38 */     this.f = function1;
/*  39 */     this.g = datafixer;
/*  40 */     this.h = datafixtypes;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BooleanSupplier booleansupplier) {
/*  45 */     while (!this.d.isEmpty() && booleansupplier.getAsBoolean()) {
/*  46 */       ChunkCoordIntPair chunkcoordintpair = SectionPosition.a(this.d.firstLong()).r();
/*     */       
/*  48 */       d(chunkcoordintpair);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected Optional<R> c(long i) {
/*  55 */     return (Optional<R>)this.c.get(i);
/*     */   }
/*     */   
/*     */   protected Optional<R> d(long i) {
/*  59 */     SectionPosition sectionposition = SectionPosition.a(i);
/*     */     
/*  61 */     if (b(sectionposition)) {
/*  62 */       return Optional.empty();
/*     */     }
/*  64 */     Optional<R> optional = c(i);
/*     */     
/*  66 */     if (optional != null) {
/*  67 */       return optional;
/*     */     }
/*  69 */     b(sectionposition.r());
/*  70 */     optional = c(i);
/*  71 */     if (optional == null) {
/*  72 */       throw (IllegalStateException)SystemUtils.c(new IllegalStateException());
/*     */     }
/*  74 */     return optional;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean b(SectionPosition sectionposition) {
/*  81 */     return World.b(SectionPosition.c(sectionposition.b()));
/*     */   }
/*     */   
/*     */   protected R e(long i) {
/*  85 */     Optional<R> optional = d(i);
/*     */     
/*  87 */     if (optional.isPresent()) {
/*  88 */       return optional.get();
/*     */     }
/*  90 */     R r0 = this.f.apply(() -> a(i));
/*     */ 
/*     */ 
/*     */     
/*  94 */     this.c.put(i, Optional.of(r0));
/*  95 */     return r0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void b(ChunkCoordIntPair chunkcoordintpair) {
/* 101 */     loadInData(chunkcoordintpair, c(chunkcoordintpair));
/*     */   }
/*     */   public void loadInData(ChunkCoordIntPair chunkPos, NBTTagCompound compound) {
/* 104 */     a(chunkPos, DynamicOpsNBT.a, compound);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private NBTTagCompound c(ChunkCoordIntPair chunkcoordintpair) {
/*     */     try {
/* 111 */       return read(chunkcoordintpair);
/* 112 */     } catch (IOException ioexception) {
/* 113 */       LOGGER.error("Error reading chunk {} data from disk", chunkcoordintpair, ioexception);
/* 114 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private <T> void a(ChunkCoordIntPair chunkcoordintpair, DynamicOps<T> dynamicops, @Nullable T t0) {
/* 119 */     if (t0 == null) {
/* 120 */       for (int i = 0; i < 16; i++) {
/* 121 */         this.c.put(SectionPosition.a(chunkcoordintpair, i).s(), Optional.empty());
/*     */       }
/*     */     } else {
/* 124 */       Dynamic<T> dynamic = new Dynamic(dynamicops, t0);
/* 125 */       int j = a(dynamic);
/* 126 */       int k = SharedConstants.getGameVersion().getWorldVersion();
/* 127 */       boolean flag = (j != k);
/* 128 */       Dynamic<T> dynamic1 = this.g.update(this.h.a(), dynamic, j, k);
/* 129 */       OptionalDynamic<T> optionaldynamic = dynamic1.get("Sections");
/*     */       
/* 131 */       for (int l = 0; l < 16; l++) {
/* 132 */         long i1 = SectionPosition.a(chunkcoordintpair, l).s();
/* 133 */         Optional<R> optional = optionaldynamic.get(Integer.toString(l)).result().flatMap(dynamic2 -> {
/*     */               DataResult dataresult = ((Codec)this.e.apply(())).parse(dynamic2);
/*     */               
/*     */               Logger logger = LOGGER;
/*     */               
/*     */               logger.getClass();
/*     */               
/*     */               Objects.requireNonNull(logger);
/*     */               return dataresult.resultOrPartial(logger::error);
/*     */             });
/* 143 */         this.c.put(i1, optional);
/* 144 */         optional.ifPresent(object -> {
/*     */               b(i1);
/*     */               if (flag) {
/*     */                 a(i1);
/*     */               }
/*     */             });
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void d(ChunkCoordIntPair chunkcoordintpair) {
/* 157 */     Dynamic<NBTBase> dynamic = a(chunkcoordintpair, DynamicOpsNBT.a);
/* 158 */     NBTBase nbtbase = (NBTBase)dynamic.getValue();
/*     */     
/* 160 */     if (nbtbase instanceof NBTTagCompound) { 
/* 161 */       try { write(chunkcoordintpair, (NBTTagCompound)nbtbase); } catch (IOException ioexception) { LOGGER.error("Error writing data to disk", ioexception); }
/*     */        }
/* 163 */     else { LOGGER.error("Expected compound tag, got {}", nbtbase); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private NBTTagCompound getDataInternal(ChunkCoordIntPair chunkcoordintpair) {
/* 170 */     Dynamic<NBTBase> dynamic = a(chunkcoordintpair, DynamicOpsNBT.a);
/* 171 */     NBTBase nbtbase = (NBTBase)dynamic.getValue();
/*     */     
/* 173 */     if (nbtbase instanceof NBTTagCompound) {
/* 174 */       return (NBTTagCompound)nbtbase;
/*     */     }
/* 176 */     LOGGER.error("Expected compound tag, got {}", nbtbase);
/*     */     
/* 178 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private <T> Dynamic<T> a(ChunkCoordIntPair chunkcoordintpair, DynamicOps<T> dynamicops) {
/* 183 */     Map<T, T> map = Maps.newHashMap();
/*     */     
/* 185 */     for (int i = 0; i < 16; i++) {
/* 186 */       long j = SectionPosition.a(chunkcoordintpair, i).s();
/*     */       
/* 188 */       this.d.remove(j);
/* 189 */       Optional<R> optional = (Optional<R>)this.c.get(j);
/*     */       
/* 191 */       if (optional != null && optional.isPresent()) {
/*     */ 
/*     */         
/* 194 */         DataResult<T> dataresult = ((Codec)this.e.apply(() -> a(j))).encodeStart(dynamicops, optional.get());
/* 195 */         String s = Integer.toString(i);
/* 196 */         Logger logger = LOGGER;
/*     */         
/* 198 */         logger.getClass();
/* 199 */         Objects.requireNonNull(logger); dataresult.resultOrPartial(logger::error).ifPresent(object -> map.put(dynamicops.createString(s), object));
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 205 */     return new Dynamic(dynamicops, dynamicops.createMap((Map)ImmutableMap.of(dynamicops.createString("Sections"), dynamicops.createMap(map), dynamicops.createString("DataVersion"), dynamicops.createInt(SharedConstants.getGameVersion().getWorldVersion()))));
/*     */   }
/*     */   
/*     */   protected void b(long i) {}
/*     */   
/*     */   protected void a(long i) {
/* 211 */     Optional<R> optional = (Optional<R>)this.c.get(i);
/*     */     
/* 213 */     if (optional != null && optional.isPresent()) {
/* 214 */       this.d.add(i);
/*     */     } else {
/* 216 */       LOGGER.warn("No data for position: {}", SectionPosition.a(i));
/*     */     } 
/*     */   }
/*     */   
/*     */   private static int a(Dynamic<?> dynamic) {
/* 221 */     return dynamic.get("DataVersion").asInt(1945);
/*     */   }
/*     */   
/*     */   public void a(ChunkCoordIntPair chunkcoordintpair) {
/* 225 */     if (!this.d.isEmpty()) {
/* 226 */       for (int i = 0; i < 16; i++) {
/* 227 */         long j = SectionPosition.a(chunkcoordintpair, i).s();
/*     */         
/* 229 */         if (this.d.contains(j)) {
/* 230 */           d(chunkcoordintpair);
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     }
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
/*     */   public NBTTagCompound getData(ChunkCoordIntPair chunkcoordintpair) {
/* 248 */     if (!this.d.isEmpty()) {
/* 249 */       for (int i = 0; i < 16; i++) {
/* 250 */         long j = SectionPosition.a(chunkcoordintpair, i).s();
/*     */         
/* 252 */         if (this.d.contains(j)) {
/* 253 */           return getDataInternal(chunkcoordintpair);
/*     */         }
/*     */       } 
/*     */     }
/* 257 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RegionFileSection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */