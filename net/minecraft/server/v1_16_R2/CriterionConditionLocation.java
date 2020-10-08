/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonNull;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.mojang.serialization.DynamicOps;
/*     */ import com.mojang.serialization.JsonOps;
/*     */ import java.util.Optional;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
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
/*     */ public class CriterionConditionLocation
/*     */ {
/*  24 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*  26 */   public static final CriterionConditionLocation a = new CriterionConditionLocation(CriterionConditionValue.FloatRange.e, CriterionConditionValue.FloatRange.e, CriterionConditionValue.FloatRange.e, null, null, null, null, CriterionConditionLight.a, CriterionConditionBlock.a, CriterionConditionFluid.a);
/*     */   
/*     */   private final CriterionConditionValue.FloatRange c;
/*     */   private final CriterionConditionValue.FloatRange d;
/*     */   private final CriterionConditionValue.FloatRange e;
/*     */   @Nullable
/*     */   private final ResourceKey<BiomeBase> f;
/*     */   @Nullable
/*     */   private final StructureGenerator<?> g;
/*     */   @Nullable
/*     */   private final ResourceKey<World> h;
/*     */   @Nullable
/*     */   private final Boolean i;
/*     */   private final CriterionConditionLight j;
/*     */   private final CriterionConditionBlock k;
/*     */   private final CriterionConditionFluid l;
/*     */   
/*     */   public CriterionConditionLocation(CriterionConditionValue.FloatRange var0, CriterionConditionValue.FloatRange var1, CriterionConditionValue.FloatRange var2, @Nullable ResourceKey<BiomeBase> var3, @Nullable StructureGenerator<?> var4, @Nullable ResourceKey<World> var5, @Nullable Boolean var6, CriterionConditionLight var7, CriterionConditionBlock var8, CriterionConditionFluid var9) {
/*  44 */     this.c = var0;
/*  45 */     this.d = var1;
/*  46 */     this.e = var2;
/*  47 */     this.f = var3;
/*  48 */     this.g = var4;
/*  49 */     this.h = var5;
/*  50 */     this.i = var6;
/*  51 */     this.j = var7;
/*  52 */     this.k = var8;
/*  53 */     this.l = var9;
/*     */   }
/*     */   
/*     */   public static CriterionConditionLocation a(ResourceKey<BiomeBase> var0) {
/*  57 */     return new CriterionConditionLocation(CriterionConditionValue.FloatRange.e, CriterionConditionValue.FloatRange.e, CriterionConditionValue.FloatRange.e, var0, null, null, null, CriterionConditionLight.a, CriterionConditionBlock.a, CriterionConditionFluid.a);
/*     */   }
/*     */   
/*     */   public static CriterionConditionLocation b(ResourceKey<World> var0) {
/*  61 */     return new CriterionConditionLocation(CriterionConditionValue.FloatRange.e, CriterionConditionValue.FloatRange.e, CriterionConditionValue.FloatRange.e, null, null, var0, null, CriterionConditionLight.a, CriterionConditionBlock.a, CriterionConditionFluid.a);
/*     */   }
/*     */   
/*     */   public static CriterionConditionLocation a(StructureGenerator<?> var0) {
/*  65 */     return new CriterionConditionLocation(CriterionConditionValue.FloatRange.e, CriterionConditionValue.FloatRange.e, CriterionConditionValue.FloatRange.e, null, var0, null, null, CriterionConditionLight.a, CriterionConditionBlock.a, CriterionConditionFluid.a);
/*     */   }
/*     */   
/*     */   public boolean a(WorldServer var0, double var1, double var3, double var5) {
/*  69 */     return a(var0, (float)var1, (float)var3, (float)var5);
/*     */   }
/*     */   
/*     */   public boolean a(WorldServer var0, float var1, float var2, float var3) {
/*  73 */     if (!this.c.d(var1)) {
/*  74 */       return false;
/*     */     }
/*  76 */     if (!this.d.d(var2)) {
/*  77 */       return false;
/*     */     }
/*  79 */     if (!this.e.d(var3)) {
/*  80 */       return false;
/*     */     }
/*  82 */     if (this.h != null && this.h != var0.getDimensionKey()) {
/*  83 */       return false;
/*     */     }
/*     */     
/*  86 */     BlockPosition var4 = new BlockPosition(var1, var2, var3);
/*  87 */     boolean var5 = var0.p(var4);
/*     */     
/*  89 */     Optional<ResourceKey<BiomeBase>> var6 = var0.r().<BiomeBase>b(IRegistry.ay).c(var0.getBiome(var4));
/*  90 */     if (!var6.isPresent()) {
/*  91 */       return false;
/*     */     }
/*  93 */     if (this.f != null && (!var5 || this.f != var6.get())) {
/*  94 */       return false;
/*     */     }
/*  96 */     if (this.g != null && (!var5 || !var0.getStructureManager().a(var4, true, this.g).e())) {
/*  97 */       return false;
/*     */     }
/*  99 */     if (this.i != null && (!var5 || this.i.booleanValue() != BlockCampfire.a(var0, var4))) {
/* 100 */       return false;
/*     */     }
/* 102 */     if (!this.j.a(var0, var4)) {
/* 103 */       return false;
/*     */     }
/* 105 */     if (!this.k.a(var0, var4)) {
/* 106 */       return false;
/*     */     }
/* 108 */     if (!this.l.a(var0, var4)) {
/* 109 */       return false;
/*     */     }
/*     */     
/* 112 */     return true;
/*     */   }
/*     */   
/*     */   public JsonElement a() {
/* 116 */     if (this == a) {
/* 117 */       return (JsonElement)JsonNull.INSTANCE;
/*     */     }
/*     */     
/* 120 */     JsonObject var0 = new JsonObject();
/*     */     
/* 122 */     if (!this.c.c() || !this.d.c() || !this.e.c()) {
/* 123 */       JsonObject var1 = new JsonObject();
/* 124 */       var1.add("x", this.c.d());
/* 125 */       var1.add("y", this.d.d());
/* 126 */       var1.add("z", this.e.d());
/* 127 */       var0.add("position", (JsonElement)var1);
/*     */     } 
/* 129 */     if (this.h != null) {
/* 130 */       World.f.encodeStart((DynamicOps)JsonOps.INSTANCE, this.h).resultOrPartial(LOGGER::error).ifPresent(var1 -> var0.add("dimension", var1));
/*     */     }
/* 132 */     if (this.g != null) {
/* 133 */       var0.addProperty("feature", this.g.i());
/*     */     }
/* 135 */     if (this.f != null) {
/* 136 */       var0.addProperty("biome", this.f.a().toString());
/*     */     }
/* 138 */     if (this.i != null) {
/* 139 */       var0.addProperty("smokey", this.i);
/*     */     }
/* 141 */     var0.add("light", this.j.a());
/* 142 */     var0.add("block", this.k.a());
/* 143 */     var0.add("fluid", this.l.a());
/*     */     
/* 145 */     return (JsonElement)var0;
/*     */   }
/*     */   
/*     */   public static CriterionConditionLocation a(@Nullable JsonElement var0) {
/* 149 */     if (var0 == null || var0.isJsonNull()) {
/* 150 */       return a;
/*     */     }
/* 152 */     JsonObject var1 = ChatDeserializer.m(var0, "location");
/* 153 */     JsonObject var2 = ChatDeserializer.a(var1, "position", new JsonObject());
/* 154 */     CriterionConditionValue.FloatRange var3 = CriterionConditionValue.FloatRange.a(var2.get("x"));
/* 155 */     CriterionConditionValue.FloatRange var4 = CriterionConditionValue.FloatRange.a(var2.get("y"));
/* 156 */     CriterionConditionValue.FloatRange var5 = CriterionConditionValue.FloatRange.a(var2.get("z"));
/* 157 */     ResourceKey<World> var6 = var1.has("dimension") ? MinecraftKey.a.parse((DynamicOps)JsonOps.INSTANCE, var1.get("dimension")).resultOrPartial(LOGGER::error).map(var0 -> ResourceKey.a(IRegistry.L, var0)).orElse(null) : null;
/* 158 */     StructureGenerator<?> var7 = var1.has("feature") ? (StructureGenerator)StructureGenerator.a.get(ChatDeserializer.h(var1, "feature")) : null;
/* 159 */     ResourceKey<BiomeBase> var8 = null;
/* 160 */     if (var1.has("biome")) {
/* 161 */       MinecraftKey minecraftKey = new MinecraftKey(ChatDeserializer.h(var1, "biome"));
/* 162 */       var8 = ResourceKey.a(IRegistry.ay, minecraftKey);
/*     */     } 
/* 164 */     Boolean var9 = var1.has("smokey") ? Boolean.valueOf(var1.get("smokey").getAsBoolean()) : null;
/* 165 */     CriterionConditionLight var10 = CriterionConditionLight.a(var1.get("light"));
/* 166 */     CriterionConditionBlock var11 = CriterionConditionBlock.a(var1.get("block"));
/* 167 */     CriterionConditionFluid var12 = CriterionConditionFluid.a(var1.get("fluid"));
/* 168 */     return new CriterionConditionLocation(var3, var4, var5, var8, var7, var6, var9, var10, var11, var12);
/*     */   }
/*     */   
/*     */   public static class a {
/* 172 */     private CriterionConditionValue.FloatRange a = CriterionConditionValue.FloatRange.e;
/* 173 */     private CriterionConditionValue.FloatRange b = CriterionConditionValue.FloatRange.e;
/* 174 */     private CriterionConditionValue.FloatRange c = CriterionConditionValue.FloatRange.e;
/*     */     
/*     */     @Nullable
/*     */     private ResourceKey<BiomeBase> d;
/*     */     
/*     */     @Nullable
/*     */     private StructureGenerator<?> e;
/*     */     @Nullable
/*     */     private ResourceKey<World> f;
/*     */     @Nullable
/*     */     private Boolean g;
/* 185 */     private CriterionConditionLight h = CriterionConditionLight.a;
/* 186 */     private CriterionConditionBlock i = CriterionConditionBlock.a;
/* 187 */     private CriterionConditionFluid j = CriterionConditionFluid.a;
/*     */     
/*     */     public static a a() {
/* 190 */       return new a();
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
/*     */ 
/*     */ 
/*     */     
/*     */     public a a(@Nullable ResourceKey<BiomeBase> var0) {
/* 209 */       this.d = var0;
/* 210 */       return this;
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
/*     */ 
/*     */ 
/*     */     
/*     */     public a a(CriterionConditionBlock var0) {
/* 229 */       this.i = var0;
/* 230 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public a a(Boolean var0) {
/* 239 */       this.g = var0;
/* 240 */       return this;
/*     */     }
/*     */     
/*     */     public CriterionConditionLocation b() {
/* 244 */       return new CriterionConditionLocation(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionConditionLocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */