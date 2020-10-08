/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.mojang.datafixers.kinds.App;
/*     */ import com.mojang.datafixers.kinds.Applicative;
/*     */ import com.mojang.datafixers.util.Function3;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*     */ import it.unimi.dsi.fastutil.shorts.Short2ObjectMap;
/*     */ import it.unimi.dsi.fastutil.shorts.Short2ObjectOpenHashMap;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.stream.Stream;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.apache.logging.log4j.util.Supplier;
/*     */ 
/*     */ public class VillagePlaceSection {
/*  27 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Codec<VillagePlaceSection> a(Runnable var0) {
/*  34 */     return RecordCodecBuilder.create(var1 -> var1.group((App)RecordCodecBuilder.point(var0), (App)Codec.BOOL.optionalFieldOf("Valid", Boolean.valueOf(false)).forGetter(()), (App)VillagePlaceRecord.a(var0).listOf().fieldOf("Records").forGetter(())).apply((Applicative)var1, VillagePlaceSection::new))
/*     */ 
/*     */ 
/*     */       
/*  38 */       .orElseGet(SystemUtils.a("Failed to read POI section: ", LOGGER::error), () -> new VillagePlaceSection(var0, false, (List<VillagePlaceRecord>)ImmutableList.of()));
/*     */   }
/*     */   
/*  41 */   private final Short2ObjectMap<VillagePlaceRecord> b = (Short2ObjectMap<VillagePlaceRecord>)new Short2ObjectOpenHashMap();
/*  42 */   private final Map<VillagePlaceType, Set<VillagePlaceRecord>> c = Maps.newHashMap();
/*     */   private final Runnable d;
/*     */   private boolean e;
/*     */   
/*     */   public VillagePlaceSection(Runnable var0) {
/*  47 */     this(var0, true, (List<VillagePlaceRecord>)ImmutableList.of());
/*     */   }
/*     */   
/*     */   private VillagePlaceSection(Runnable var0, boolean var1, List<VillagePlaceRecord> var2) {
/*  51 */     this.d = var0;
/*  52 */     this.e = var1;
/*  53 */     var2.forEach(this::a);
/*     */   }
/*     */   
/*     */   public Stream<VillagePlaceRecord> a(Predicate<VillagePlaceType> var0, VillagePlace.Occupancy var1) {
/*  57 */     return this.c.entrySet()
/*  58 */       .stream()
/*  59 */       .filter(var1 -> var0.test(var1.getKey()))
/*  60 */       .flatMap(var0 -> ((Set)var0.getValue()).stream())
/*  61 */       .filter(var1.a());
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(BlockPosition var0, VillagePlaceType var1) {
/*  66 */     if (a(new VillagePlaceRecord(var0, var1, this.d))) {
/*  67 */       LOGGER.debug("Added POI of type {} @ {}", new Supplier[] { () -> var0, () -> var0 });
/*  68 */       this.d.run();
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean a(VillagePlaceRecord var0) {
/*  73 */     BlockPosition var1 = var0.f();
/*  74 */     VillagePlaceType var2 = var0.g();
/*  75 */     short var3 = SectionPosition.b(var1);
/*  76 */     VillagePlaceRecord var4 = (VillagePlaceRecord)this.b.get(var3);
/*     */     
/*  78 */     if (var4 != null) {
/*  79 */       if (var2.equals(var4.g())) {
/*  80 */         return false;
/*     */       }
/*  82 */       throw (IllegalStateException)SystemUtils.c(new IllegalStateException("POI data mismatch: already registered at " + var1));
/*     */     } 
/*     */ 
/*     */     
/*  86 */     this.b.put(var3, var0);
/*  87 */     ((Set<VillagePlaceRecord>)this.c.computeIfAbsent(var2, var0 -> Sets.newHashSet())).add(var0);
/*  88 */     return true;
/*     */   }
/*     */   
/*     */   public void a(BlockPosition var0) {
/*  92 */     VillagePlaceRecord var1 = (VillagePlaceRecord)this.b.remove(SectionPosition.b(var0));
/*  93 */     if (var1 == null) {
/*  94 */       LOGGER.error("POI data mismatch: never registered at " + var0);
/*     */       return;
/*     */     } 
/*  97 */     ((Set)this.c.get(var1.g())).remove(var1);
/*     */     
/*  99 */     LOGGER.debug("Removed POI of type {} @ {}", new Supplier[] { var1::g, var1::f });
/* 100 */     this.d.run();
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
/*     */   public boolean c(BlockPosition var0) {
/* 112 */     VillagePlaceRecord var1 = (VillagePlaceRecord)this.b.get(SectionPosition.b(var0));
/* 113 */     if (var1 == null) {
/* 114 */       throw (IllegalStateException)SystemUtils.c(new IllegalStateException("POI never registered at " + var0));
/*     */     }
/* 116 */     boolean var2 = var1.c();
/* 117 */     this.d.run();
/* 118 */     return var2;
/*     */   }
/*     */   
/*     */   public boolean a(BlockPosition var0, Predicate<VillagePlaceType> var1) {
/* 122 */     short var2 = SectionPosition.b(var0);
/* 123 */     VillagePlaceRecord var3 = (VillagePlaceRecord)this.b.get(var2);
/* 124 */     return (var3 != null && var1.test(var3.g()));
/*     */   }
/*     */   
/*     */   public Optional<VillagePlaceType> d(BlockPosition var0) {
/* 128 */     short var1 = SectionPosition.b(var0);
/* 129 */     VillagePlaceRecord var2 = (VillagePlaceRecord)this.b.get(var1);
/* 130 */     return (var2 != null) ? Optional.<VillagePlaceType>of(var2.g()) : Optional.<VillagePlaceType>empty();
/*     */   }
/*     */   
/*     */   public void a(Consumer<BiConsumer<BlockPosition, VillagePlaceType>> var0) {
/* 134 */     if (!this.e) {
/* 135 */       Short2ObjectOpenHashMap short2ObjectOpenHashMap = new Short2ObjectOpenHashMap(this.b);
/* 136 */       b();
/* 137 */       var0.accept((var1, var2) -> {
/*     */             short var3 = SectionPosition.b(var1);
/*     */             VillagePlaceRecord var4 = (VillagePlaceRecord)var0.computeIfAbsent(var3, ());
/*     */             a(var4);
/*     */           });
/* 142 */       this.e = true;
/* 143 */       this.d.run();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void b() {
/* 148 */     this.b.clear();
/* 149 */     this.c.clear();
/*     */   }
/*     */   
/*     */   boolean a() {
/* 153 */     return this.e;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\VillagePlaceSection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */