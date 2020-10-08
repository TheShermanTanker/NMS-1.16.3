/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.datafixers.kinds.App;
/*     */ import com.mojang.datafixers.kinds.Applicative;
/*     */ import com.mojang.datafixers.util.Function4;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.function.BiFunction;
/*     */ import java.util.stream.Stream;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class BiomeSettingsMobs {
/*  21 */   public static final Logger LOGGER = LogManager.getLogger(); public static final BiomeSettingsMobs b; public static final MapCodec<BiomeSettingsMobs> c; private final float d; private final Map<EnumCreatureType, List<c>> e;
/*     */   private final Map<EntityTypes<?>, b> f;
/*     */   private final boolean g;
/*     */   
/*     */   static {
/*  26 */     b = new BiomeSettingsMobs(0.1F, (Map<EnumCreatureType, List<c>>)Stream.<EnumCreatureType>of(EnumCreatureType.values()).collect(ImmutableMap.toImmutableMap(enumcreaturetype -> enumcreaturetype, enumcreaturetype -> ImmutableList.of())), (Map<EntityTypes<?>, b>)ImmutableMap.of(), false);
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
/*  39 */     c = RecordCodecBuilder.mapCodec(instance -> {
/*     */           RecordCodecBuilder<BiomeSettingsMobs, Float> recordcodecbuilder = Codec.FLOAT.optionalFieldOf("creature_spawn_probability", Float.valueOf(0.1F)).forGetter(());
/*     */           Objects.requireNonNull(LOGGER);
/*     */           return instance.group((App)recordcodecbuilder, (App)Codec.simpleMap(EnumCreatureType.g, cProxy.b.listOf().promotePartial(SystemUtils.a("Spawn data: ", LOGGER::error)), INamable.a((INamable[])EnumCreatureType.values())).fieldOf("spawners").forGetter(()), (App)Codec.simpleMap(IRegistry.ENTITY_TYPE, bProxy.a, IRegistry.ENTITY_TYPE).fieldOf("spawn_costs").forGetter(()), (App)Codec.BOOL.fieldOf("player_spawn_friendly").orElse(Boolean.valueOf(false)).forGetter(BiomeSettingsMobs::b)).apply((Applicative)instance, BiomeSettingsMobs::new);
/*     */         });
/*     */   }
/*     */   
/*     */   private static class bProxy extends b {
/*     */     private bProxy(double d0, double d1) {
/*     */       super(d0, d1);
/*     */     } }
/*     */   
/*     */   private static class cProxy extends c {
/*     */     public cProxy(EntityTypes<?> entitytypes, int i, int j, int k) {
/*     */       super(entitytypes, i, j, k);
/*     */     } }
/*     */   
/*     */   private BiomeSettingsMobs(float f, Map<EnumCreatureType, List<c>> map, Map<EntityTypes<?>, b> map1, boolean flag) {
/*  57 */     this.d = f;
/*  58 */     this.e = map;
/*  59 */     this.f = map1;
/*  60 */     this.g = flag;
/*     */   }
/*     */   
/*     */   public List<c> a(EnumCreatureType enumcreaturetype) {
/*  64 */     return (List<c>)this.e.getOrDefault(enumcreaturetype, ImmutableList.of());
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public b a(EntityTypes<?> entitytypes) {
/*  69 */     return this.f.get(entitytypes);
/*     */   }
/*     */   
/*     */   public float a() {
/*  73 */     return this.d;
/*     */   }
/*     */   
/*     */   public boolean b() {
/*  77 */     return this.g;
/*     */   }
/*     */   public static class a { private final Map<EnumCreatureType, List<BiomeSettingsMobs.c>> a;
/*     */     private final Map<EntityTypes<?>, BiomeSettingsMobs.b> b;
/*     */     private float c;
/*     */     private boolean d;
/*     */     
/*  84 */     public static class MobList extends ArrayList<BiomeSettingsMobs.c> { Set<BiomeSettingsMobs.c> biomes = new HashSet<>();
/*     */ 
/*     */       
/*     */       public boolean contains(Object o) {
/*  88 */         return this.biomes.contains(o);
/*     */       }
/*     */ 
/*     */       
/*     */       public boolean add(BiomeSettingsMobs.c BiomeSettingsMobs) {
/*  93 */         this.biomes.add(BiomeSettingsMobs);
/*  94 */         return super.add(BiomeSettingsMobs);
/*     */       }
/*     */ 
/*     */       
/*     */       public BiomeSettingsMobs.c remove(int index) {
/*  99 */         BiomeSettingsMobs.c removed = super.remove(index);
/* 100 */         if (removed != null) {
/* 101 */           this.biomes.remove(removed);
/*     */         }
/* 103 */         return removed;
/*     */       }
/*     */ 
/*     */       
/*     */       public void clear() {
/* 108 */         this.biomes.clear();
/* 109 */         super.clear();
/*     */       } }
/*     */     
/*     */     public a() {
/* 113 */       this.a = (Map<EnumCreatureType, List<BiomeSettingsMobs.c>>)Stream.<EnumCreatureType>of(EnumCreatureType.values()).collect(Maps.toImmutableEnumMap(enumcreaturetype -> enumcreaturetype, enumcreaturetype -> new MobList()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 119 */       this.b = Maps.newLinkedHashMap();
/* 120 */       this.c = 0.1F;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public a a(EnumCreatureType enumcreaturetype, BiomeSettingsMobs.c biomesettingsmobs_c) {
/* 126 */       ((List<BiomeSettingsMobs.c>)this.a.get(enumcreaturetype)).add(biomesettingsmobs_c);
/* 127 */       return this;
/*     */     }
/*     */     
/*     */     public a a(EntityTypes<?> entitytypes, double d0, double d1) {
/* 131 */       this.b.put(entitytypes, new BiomeSettingsMobs.b(d1, d0));
/* 132 */       return this;
/*     */     }
/*     */     
/*     */     public a a(float f) {
/* 136 */       this.c = f;
/* 137 */       return this;
/*     */     }
/*     */     
/*     */     public a a() {
/* 141 */       this.d = true;
/* 142 */       return this;
/*     */     }
/*     */     
/*     */     public BiomeSettingsMobs b() {
/* 146 */       return new BiomeSettingsMobs(this.c, (Map)this.a.entrySet().stream().collect(ImmutableMap.toImmutableMap(Map.Entry::getKey, entry -> ImmutableList.copyOf((Collection)entry.getValue()))), 
/*     */           
/* 148 */           (Map)ImmutableMap.copyOf(this.b), this.d);
/*     */     } }
/*     */   public static class b { public static final Codec<b> a; private final double b;
/*     */     private final double c;
/*     */     
/*     */     static {
/* 154 */       a = RecordCodecBuilder.create(instance -> instance.group((App)Codec.DOUBLE.fieldOf("energy_budget").forGetter(()), (App)Codec.DOUBLE.fieldOf("charge").forGetter(())).apply((Applicative)instance, b::new));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private b(double d0, double d1) {
/* 165 */       this.b = d0;
/* 166 */       this.c = d1;
/*     */     }
/*     */     
/*     */     public double a() {
/* 170 */       return this.b;
/*     */     }
/*     */     
/*     */     public double b() {
/* 174 */       return this.c;
/*     */     } }
/*     */   public static class c extends WeightedRandom.WeightedRandomChoice { public static final Codec<c> b; public final EntityTypes<?> c; public final int d;
/*     */     public final int e;
/*     */     
/*     */     static {
/* 180 */       b = RecordCodecBuilder.create(instance -> instance.group((App)IRegistry.ENTITY_TYPE.fieldOf("type").forGetter(()), (App)Codec.INT.fieldOf("weight").forGetter(()), (App)Codec.INT.fieldOf("minCount").forGetter(()), (App)Codec.INT.fieldOf("maxCount").forGetter(())).apply((Applicative)instance, c::new));
/*     */     }
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
/*     */     public c(EntityTypes<?> entitytypes, int i, int j, int k) {
/* 196 */       super(i);
/* 197 */       this.c = (entitytypes.e() == EnumCreatureType.MISC) ? EntityTypes.PIG : entitytypes;
/* 198 */       this.d = j;
/* 199 */       this.e = k;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 203 */       return EntityTypes.getName(this.c) + "*(" + this.d + "-" + this.e + "):" + this.a;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BiomeSettingsMobs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */