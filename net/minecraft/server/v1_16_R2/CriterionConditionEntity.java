/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonNull;
/*     */ import com.google.gson.JsonObject;
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
/*     */ public class CriterionConditionEntity
/*     */ {
/*  29 */   public static final CriterionConditionEntity a = new CriterionConditionEntity(CriterionConditionEntityType.a, CriterionConditionDistance.a, CriterionConditionLocation.a, CriterionConditionMobEffect.a, CriterionConditionNBT.a, CriterionConditionEntityFlags.a, CriterionConditionEntityEquipment.a, CriterionConditionPlayer.a, CriterionConditionInOpenWater.a, null, null);
/*     */   
/*     */   private final CriterionConditionEntityType b;
/*     */   
/*     */   private final CriterionConditionDistance c;
/*     */   
/*     */   private final CriterionConditionLocation d;
/*     */   
/*     */   private final CriterionConditionMobEffect e;
/*     */   private final CriterionConditionNBT f;
/*     */   private final CriterionConditionEntityFlags g;
/*     */   private final CriterionConditionEntityEquipment h;
/*     */   private final CriterionConditionPlayer i;
/*     */   private final CriterionConditionInOpenWater j;
/*     */   private final CriterionConditionEntity k;
/*     */   private final CriterionConditionEntity l;
/*     */   @Nullable
/*     */   private final String m;
/*     */   @Nullable
/*     */   private final MinecraftKey n;
/*     */   
/*     */   private CriterionConditionEntity(CriterionConditionEntityType var0, CriterionConditionDistance var1, CriterionConditionLocation var2, CriterionConditionMobEffect var3, CriterionConditionNBT var4, CriterionConditionEntityFlags var5, CriterionConditionEntityEquipment var6, CriterionConditionPlayer var7, CriterionConditionInOpenWater var8, @Nullable String var9, @Nullable MinecraftKey var10) {
/*  51 */     this.b = var0;
/*  52 */     this.c = var1;
/*  53 */     this.d = var2;
/*  54 */     this.e = var3;
/*  55 */     this.f = var4;
/*  56 */     this.g = var5;
/*  57 */     this.h = var6;
/*  58 */     this.i = var7;
/*  59 */     this.j = var8;
/*  60 */     this.k = this;
/*  61 */     this.l = this;
/*  62 */     this.m = var9;
/*  63 */     this.n = var10;
/*     */   }
/*     */   
/*     */   private CriterionConditionEntity(CriterionConditionEntityType var0, CriterionConditionDistance var1, CriterionConditionLocation var2, CriterionConditionMobEffect var3, CriterionConditionNBT var4, CriterionConditionEntityFlags var5, CriterionConditionEntityEquipment var6, CriterionConditionPlayer var7, CriterionConditionInOpenWater var8, CriterionConditionEntity var9, CriterionConditionEntity var10, @Nullable String var11, @Nullable MinecraftKey var12) {
/*  67 */     this.b = var0;
/*  68 */     this.c = var1;
/*  69 */     this.d = var2;
/*  70 */     this.e = var3;
/*  71 */     this.f = var4;
/*  72 */     this.g = var5;
/*  73 */     this.h = var6;
/*  74 */     this.i = var7;
/*  75 */     this.j = var8;
/*  76 */     this.k = var9;
/*  77 */     this.l = var10;
/*  78 */     this.m = var11;
/*  79 */     this.n = var12;
/*     */   }
/*     */   
/*     */   public boolean a(EntityPlayer var0, @Nullable Entity var1) {
/*  83 */     return a(var0.getWorldServer(), var0.getPositionVector(), var1);
/*     */   }
/*     */   
/*     */   public boolean a(WorldServer var0, @Nullable Vec3D var1, @Nullable Entity var2) {
/*  87 */     if (this == a) {
/*  88 */       return true;
/*     */     }
/*  90 */     if (var2 == null) {
/*  91 */       return false;
/*     */     }
/*  93 */     if (!this.b.a(var2.getEntityType())) {
/*  94 */       return false;
/*     */     }
/*  96 */     if (var1 == null) {
/*  97 */       if (this.c != CriterionConditionDistance.a) {
/*  98 */         return false;
/*     */       }
/*     */     }
/* 101 */     else if (!this.c.a(var1.x, var1.y, var1.z, var2.locX(), var2.locY(), var2.locZ())) {
/* 102 */       return false;
/*     */     } 
/*     */     
/* 105 */     if (!this.d.a(var0, var2.locX(), var2.locY(), var2.locZ())) {
/* 106 */       return false;
/*     */     }
/* 108 */     if (!this.e.a(var2)) {
/* 109 */       return false;
/*     */     }
/* 111 */     if (!this.f.a(var2)) {
/* 112 */       return false;
/*     */     }
/* 114 */     if (!this.g.a(var2)) {
/* 115 */       return false;
/*     */     }
/*     */     
/* 118 */     if (!this.h.a(var2)) {
/* 119 */       return false;
/*     */     }
/*     */     
/* 122 */     if (!this.i.a(var2)) {
/* 123 */       return false;
/*     */     }
/*     */     
/* 126 */     if (!this.j.a(var2)) {
/* 127 */       return false;
/*     */     }
/*     */     
/* 130 */     if (!this.k.a(var0, var1, var2.getVehicle())) {
/* 131 */       return false;
/*     */     }
/*     */     
/* 134 */     if (!this.l.a(var0, var1, (var2 instanceof EntityInsentient) ? ((EntityInsentient)var2).getGoalTarget() : null)) {
/* 135 */       return false;
/*     */     }
/*     */     
/* 138 */     if (this.m != null) {
/* 139 */       ScoreboardTeamBase var3 = var2.getScoreboardTeam();
/* 140 */       if (var3 == null || !this.m.equals(var3.getName())) {
/* 141 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 145 */     if (this.n != null && (
/* 146 */       !(var2 instanceof EntityCat) || !((EntityCat)var2).eU().equals(this.n))) {
/* 147 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 151 */     return true;
/*     */   }
/*     */   
/*     */   public static CriterionConditionEntity a(@Nullable JsonElement var0) {
/* 155 */     if (var0 == null || var0.isJsonNull()) {
/* 156 */       return a;
/*     */     }
/*     */     
/* 159 */     JsonObject var1 = ChatDeserializer.m(var0, "entity");
/*     */     
/* 161 */     CriterionConditionEntityType var2 = CriterionConditionEntityType.a(var1.get("type"));
/* 162 */     CriterionConditionDistance var3 = CriterionConditionDistance.a(var1.get("distance"));
/* 163 */     CriterionConditionLocation var4 = CriterionConditionLocation.a(var1.get("location"));
/* 164 */     CriterionConditionMobEffect var5 = CriterionConditionMobEffect.a(var1.get("effects"));
/* 165 */     CriterionConditionNBT var6 = CriterionConditionNBT.a(var1.get("nbt"));
/* 166 */     CriterionConditionEntityFlags var7 = CriterionConditionEntityFlags.a(var1.get("flags"));
/* 167 */     CriterionConditionEntityEquipment var8 = CriterionConditionEntityEquipment.a(var1.get("equipment"));
/* 168 */     CriterionConditionPlayer var9 = CriterionConditionPlayer.a(var1.get("player"));
/* 169 */     CriterionConditionInOpenWater var10 = CriterionConditionInOpenWater.a(var1.get("fishing_hook"));
/* 170 */     CriterionConditionEntity var11 = a(var1.get("vehicle"));
/* 171 */     CriterionConditionEntity var12 = a(var1.get("targeted_entity"));
/* 172 */     String var13 = ChatDeserializer.a(var1, "team", (String)null);
/* 173 */     MinecraftKey var14 = var1.has("catType") ? new MinecraftKey(ChatDeserializer.h(var1, "catType")) : null;
/*     */     
/* 175 */     return (new a())
/* 176 */       .a(var2)
/* 177 */       .a(var3)
/* 178 */       .a(var4)
/* 179 */       .a(var5)
/* 180 */       .a(var6)
/* 181 */       .a(var7)
/* 182 */       .a(var8)
/* 183 */       .a(var9)
/* 184 */       .a(var10)
/* 185 */       .a(var13)
/* 186 */       .a(var11)
/* 187 */       .b(var12)
/* 188 */       .b(var14)
/* 189 */       .b();
/*     */   }
/*     */   
/*     */   public JsonElement a() {
/* 193 */     if (this == a) {
/* 194 */       return (JsonElement)JsonNull.INSTANCE;
/*     */     }
/*     */     
/* 197 */     JsonObject var0 = new JsonObject();
/*     */     
/* 199 */     var0.add("type", this.b.a());
/* 200 */     var0.add("distance", this.c.a());
/* 201 */     var0.add("location", this.d.a());
/* 202 */     var0.add("effects", this.e.b());
/* 203 */     var0.add("nbt", this.f.a());
/* 204 */     var0.add("flags", this.g.a());
/* 205 */     var0.add("equipment", this.h.a());
/* 206 */     var0.add("player", this.i.a());
/* 207 */     var0.add("fishing_hook", this.j.a());
/* 208 */     var0.add("vehicle", this.k.a());
/* 209 */     var0.add("targeted_entity", this.l.a());
/* 210 */     var0.addProperty("team", this.m);
/* 211 */     if (this.n != null) {
/* 212 */       var0.addProperty("catType", this.n.toString());
/*     */     }
/*     */     
/* 215 */     return (JsonElement)var0;
/*     */   }
/*     */   
/*     */   public static LootTableInfo b(EntityPlayer var0, Entity var1) {
/* 219 */     return (new LootTableInfo.Builder(var0.getWorldServer()))
/* 220 */       .<Entity>set(LootContextParameters.THIS_ENTITY, var1)
/* 221 */       .<Vec3D>set(LootContextParameters.ORIGIN, var0.getPositionVector())
/* 222 */       .a(var0.getRandom())
/* 223 */       .build(LootContextParameterSets.j);
/*     */   }
/*     */   
/*     */   public static class a {
/* 227 */     private CriterionConditionEntityType a = CriterionConditionEntityType.a;
/* 228 */     private CriterionConditionDistance b = CriterionConditionDistance.a;
/* 229 */     private CriterionConditionLocation c = CriterionConditionLocation.a;
/* 230 */     private CriterionConditionMobEffect d = CriterionConditionMobEffect.a;
/* 231 */     private CriterionConditionNBT e = CriterionConditionNBT.a;
/* 232 */     private CriterionConditionEntityFlags f = CriterionConditionEntityFlags.a;
/* 233 */     private CriterionConditionEntityEquipment g = CriterionConditionEntityEquipment.a;
/* 234 */     private CriterionConditionPlayer h = CriterionConditionPlayer.a;
/* 235 */     private CriterionConditionInOpenWater i = CriterionConditionInOpenWater.a;
/* 236 */     private CriterionConditionEntity j = CriterionConditionEntity.a;
/* 237 */     private CriterionConditionEntity k = CriterionConditionEntity.a;
/*     */     private String l;
/*     */     private MinecraftKey m;
/*     */     
/*     */     public static a a() {
/* 242 */       return new a();
/*     */     }
/*     */     
/*     */     public a a(EntityTypes<?> var0) {
/* 246 */       this.a = CriterionConditionEntityType.b(var0);
/* 247 */       return this;
/*     */     }
/*     */     
/*     */     public a a(Tag<EntityTypes<?>> var0) {
/* 251 */       this.a = CriterionConditionEntityType.a(var0);
/* 252 */       return this;
/*     */     }
/*     */     
/*     */     public a a(MinecraftKey var0) {
/* 256 */       this.m = var0;
/* 257 */       return this;
/*     */     }
/*     */     
/*     */     public a a(CriterionConditionEntityType var0) {
/* 261 */       this.a = var0;
/* 262 */       return this;
/*     */     }
/*     */     
/*     */     public a a(CriterionConditionDistance var0) {
/* 266 */       this.b = var0;
/* 267 */       return this;
/*     */     }
/*     */     
/*     */     public a a(CriterionConditionLocation var0) {
/* 271 */       this.c = var0;
/* 272 */       return this;
/*     */     }
/*     */     
/*     */     public a a(CriterionConditionMobEffect var0) {
/* 276 */       this.d = var0;
/* 277 */       return this;
/*     */     }
/*     */     
/*     */     public a a(CriterionConditionNBT var0) {
/* 281 */       this.e = var0;
/* 282 */       return this;
/*     */     }
/*     */     
/*     */     public a a(CriterionConditionEntityFlags var0) {
/* 286 */       this.f = var0;
/* 287 */       return this;
/*     */     }
/*     */     
/*     */     public a a(CriterionConditionEntityEquipment var0) {
/* 291 */       this.g = var0;
/* 292 */       return this;
/*     */     }
/*     */     
/*     */     public a a(CriterionConditionPlayer var0) {
/* 296 */       this.h = var0;
/* 297 */       return this;
/*     */     }
/*     */     
/*     */     public a a(CriterionConditionInOpenWater var0) {
/* 301 */       this.i = var0;
/* 302 */       return this;
/*     */     }
/*     */     
/*     */     public a a(CriterionConditionEntity var0) {
/* 306 */       this.j = var0;
/* 307 */       return this;
/*     */     }
/*     */     
/*     */     public a b(CriterionConditionEntity var0) {
/* 311 */       this.k = var0;
/* 312 */       return this;
/*     */     }
/*     */     
/*     */     public a a(@Nullable String var0) {
/* 316 */       this.l = var0;
/* 317 */       return this;
/*     */     }
/*     */     
/*     */     public a b(@Nullable MinecraftKey var0) {
/* 321 */       this.m = var0;
/* 322 */       return this;
/*     */     }
/*     */     
/*     */     public CriterionConditionEntity b() {
/* 326 */       return new CriterionConditionEntity(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class b {
/* 331 */     public static final b a = new b(new LootItemCondition[0]);
/*     */     
/*     */     private final LootItemCondition[] b;
/*     */     private final Predicate<LootTableInfo> c;
/*     */     
/*     */     private b(LootItemCondition[] var0) {
/* 337 */       this.b = var0;
/* 338 */       this.c = LootItemConditions.a((Predicate<LootTableInfo>[])var0);
/*     */     }
/*     */     
/*     */     public static b a(LootItemCondition... var0) {
/* 342 */       return new b(var0);
/*     */     }
/*     */     
/*     */     public static b a(JsonObject var0, String var1, LootDeserializationContext var2) {
/* 346 */       JsonElement var3 = var0.get(var1);
/* 347 */       return a(var1, var2, var3);
/*     */     }
/*     */     
/*     */     public static b[] b(JsonObject var0, String var1, LootDeserializationContext var2) {
/* 351 */       JsonElement var3 = var0.get(var1);
/* 352 */       if (var3 == null || var3.isJsonNull()) {
/* 353 */         return new b[0];
/*     */       }
/* 355 */       JsonArray var4 = ChatDeserializer.n(var3, var1);
/* 356 */       b[] var5 = new b[var4.size()];
/*     */       
/* 358 */       for (int var6 = 0; var6 < var4.size(); var6++) {
/* 359 */         var5[var6] = a(var1 + "[" + var6 + "]", var2, var4.get(var6));
/*     */       }
/*     */       
/* 362 */       return var5;
/*     */     }
/*     */     
/*     */     private static b a(String var0, LootDeserializationContext var1, @Nullable JsonElement var2) {
/* 366 */       if (var2 != null && var2.isJsonArray()) {
/* 367 */         LootItemCondition[] arrayOfLootItemCondition = var1.a(var2.getAsJsonArray(), var1.a().toString() + "/" + var0, LootContextParameterSets.j);
/* 368 */         return new b(arrayOfLootItemCondition);
/*     */       } 
/*     */ 
/*     */       
/* 372 */       CriterionConditionEntity var3 = CriterionConditionEntity.a(var2);
/* 373 */       return a(var3);
/*     */     }
/*     */     
/*     */     public static b a(CriterionConditionEntity var0) {
/* 377 */       if (var0 == CriterionConditionEntity.a) {
/* 378 */         return a;
/*     */       }
/* 380 */       LootItemCondition var1 = LootItemConditionEntityProperty.a(LootTableInfo.EntityTarget.THIS, var0).build();
/* 381 */       return new b(new LootItemCondition[] { var1 });
/*     */     }
/*     */     
/*     */     public boolean a(LootTableInfo var0) {
/* 385 */       return this.c.test(var0);
/*     */     }
/*     */     
/*     */     public JsonElement a(LootSerializationContext var0) {
/* 389 */       if (this.b.length == 0) {
/* 390 */         return (JsonElement)JsonNull.INSTANCE;
/*     */       }
/*     */       
/* 393 */       return var0.a(this.b);
/*     */     }
/*     */     
/*     */     public static JsonElement a(b[] var0, LootSerializationContext var1) {
/* 397 */       if (var0.length == 0) {
/* 398 */         return (JsonElement)JsonNull.INSTANCE;
/*     */       }
/*     */       
/* 401 */       JsonArray var2 = new JsonArray();
/* 402 */       for (b var6 : var0) {
/* 403 */         var2.add(var6.a(var1));
/*     */       }
/* 405 */       return (JsonElement)var2;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionConditionEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */