/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.google.gson.TypeAdapter;
/*     */ import com.google.gson.stream.JsonReader;
/*     */ import com.google.gson.stream.JsonWriter;
/*     */ import java.io.IOException;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Function;
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
/*     */ public class LootTableInfo
/*     */ {
/*     */   private final Random a;
/*     */   private final float luck;
/*     */   private final WorldServer world;
/*     */   private final Function<MinecraftKey, LootTable> d;
/*  39 */   private final Set<LootTable> e = Sets.newLinkedHashSet();
/*     */   
/*     */   private final Function<MinecraftKey, LootItemCondition> f;
/*  42 */   private final Set<LootItemCondition> g = Sets.newLinkedHashSet();
/*     */   
/*     */   private final Map<LootContextParameter<?>, Object> h;
/*     */   
/*     */   private final Map<MinecraftKey, b> i;
/*     */   
/*     */   private LootTableInfo(Random var0, float var1, WorldServer var2, Function<MinecraftKey, LootTable> var3, Function<MinecraftKey, LootItemCondition> var4, Map<LootContextParameter<?>, Object> var5, Map<MinecraftKey, b> var6) {
/*  49 */     this.a = var0;
/*  50 */     this.luck = var1;
/*  51 */     this.world = var2;
/*  52 */     this.d = var3;
/*  53 */     this.f = var4;
/*  54 */     this.h = (Map<LootContextParameter<?>, Object>)ImmutableMap.copyOf(var5);
/*  55 */     this.i = (Map<MinecraftKey, b>)ImmutableMap.copyOf(var6);
/*     */   }
/*     */   
/*     */   public boolean hasContextParameter(LootContextParameter<?> var0) {
/*  59 */     return this.h.containsKey(var0);
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
/*     */   public void a(MinecraftKey var0, Consumer<ItemStack> var1) {
/*  72 */     b var2 = this.i.get(var0);
/*  73 */     if (var2 != null) {
/*  74 */       var2.add(this, var1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public <T> T getContextParameter(LootContextParameter<T> var0) {
/*  81 */     return (T)this.h.get(var0);
/*     */   }
/*     */   
/*     */   public boolean a(LootTable var0) {
/*  85 */     return this.e.add(var0);
/*     */   }
/*     */   
/*     */   public void b(LootTable var0) {
/*  89 */     this.e.remove(var0);
/*     */   }
/*     */   
/*     */   public boolean a(LootItemCondition var0) {
/*  93 */     return this.g.add(var0);
/*     */   }
/*     */   
/*     */   public void b(LootItemCondition var0) {
/*  97 */     this.g.remove(var0);
/*     */   }
/*     */   
/*     */   public LootTable a(MinecraftKey var0) {
/* 101 */     return this.d.apply(var0);
/*     */   }
/*     */   
/*     */   public LootItemCondition b(MinecraftKey var0) {
/* 105 */     return this.f.apply(var0);
/*     */   }
/*     */   
/*     */   public Random a() {
/* 109 */     return this.a;
/*     */   }
/*     */   
/*     */   public float getLuck() {
/* 113 */     return this.luck;
/*     */   }
/*     */   
/*     */   public WorldServer getWorld() {
/* 117 */     return this.world;
/*     */   }
/*     */   @FunctionalInterface
/*     */   public static interface b {
/*     */     void add(LootTableInfo param1LootTableInfo, Consumer<ItemStack> param1Consumer); }
/* 122 */   public static class Builder { private final Map<LootContextParameter<?>, Object> b = Maps.newIdentityHashMap(); private final WorldServer a;
/* 123 */     private final Map<MinecraftKey, LootTableInfo.b> c = Maps.newHashMap();
/*     */     
/*     */     private Random d;
/*     */     private float e;
/*     */     
/*     */     public Builder(WorldServer var0) {
/* 129 */       this.a = var0;
/*     */     }
/*     */     
/*     */     public Builder a(Random var0) {
/* 133 */       this.d = var0;
/* 134 */       return this;
/*     */     }
/*     */     
/*     */     public Builder a(long var0) {
/* 138 */       if (var0 != 0L) {
/* 139 */         this.d = new Random(var0);
/*     */       }
/* 141 */       return this;
/*     */     }
/*     */     
/*     */     public Builder a(long var0, Random var2) {
/* 145 */       if (var0 == 0L) {
/* 146 */         this.d = var2;
/*     */       } else {
/* 148 */         this.d = new Random(var0);
/*     */       } 
/* 150 */       return this;
/*     */     }
/*     */     
/*     */     public Builder a(float var0) {
/* 154 */       this.e = var0;
/* 155 */       return this;
/*     */     }
/*     */     
/*     */     public <T> Builder set(LootContextParameter<T> var0, T var1) {
/* 159 */       this.b.put(var0, var1);
/* 160 */       return this;
/*     */     }
/*     */     
/*     */     public <T> Builder setOptional(LootContextParameter<T> var0, @Nullable T var1) {
/* 164 */       if (var1 == null) {
/* 165 */         this.b.remove(var0);
/*     */       } else {
/* 167 */         this.b.put(var0, var1);
/*     */       } 
/* 169 */       return this;
/*     */     }
/*     */     
/*     */     public Builder a(MinecraftKey var0, LootTableInfo.b var1) {
/* 173 */       LootTableInfo.b var2 = this.c.put(var0, var1);
/*     */       
/* 175 */       if (var2 != null) {
/* 176 */         throw new IllegalStateException("Duplicated dynamic drop '" + this.c + "'");
/*     */       }
/*     */       
/* 179 */       return this;
/*     */     }
/*     */     
/*     */     public WorldServer a() {
/* 183 */       return this.a;
/*     */     }
/*     */     
/*     */     public <T> T a(LootContextParameter<T> var0) {
/* 187 */       T var1 = (T)this.b.get(var0);
/* 188 */       if (var1 == null) {
/* 189 */         throw new IllegalArgumentException("No parameter " + var0);
/*     */       }
/* 191 */       return var1;
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public <T> T b(LootContextParameter<T> var0) {
/* 197 */       return (T)this.b.get(var0);
/*     */     }
/*     */     
/*     */     public LootTableInfo build(LootContextParameterSet var0) {
/* 201 */       Sets.SetView setView1 = Sets.difference(this.b.keySet(), var0.getOptional());
/* 202 */       if (!setView1.isEmpty()) {
/* 203 */         throw new IllegalArgumentException("Parameters not allowed in this parameter set: " + setView1);
/*     */       }
/*     */       
/* 206 */       Sets.SetView setView2 = Sets.difference(var0.getRequired(), this.b.keySet());
/* 207 */       if (!setView2.isEmpty()) {
/* 208 */         throw new IllegalArgumentException("Missing required parameters: " + setView2);
/*     */       }
/*     */       
/* 211 */       Random var3 = this.d;
/* 212 */       if (var3 == null) {
/* 213 */         var3 = new Random();
/*     */       }
/*     */       
/* 216 */       MinecraftServer var4 = this.a.getMinecraftServer();
/* 217 */       return new LootTableInfo(var3, this.e, this.a, var4.getLootTableRegistry()::getLootTable, var4.getLootPredicateManager()::a, this.b, this.c);
/*     */     } }
/*     */ 
/*     */   
/*     */   public enum EntityTarget {
/* 222 */     THIS("this", LootContextParameters.THIS_ENTITY),
/* 223 */     KILLER("killer", LootContextParameters.KILLER_ENTITY),
/* 224 */     DIRECT_KILLER("direct_killer", LootContextParameters.DIRECT_KILLER_ENTITY),
/* 225 */     KILLER_PLAYER("killer_player", LootContextParameters.LAST_DAMAGE_PLAYER);
/*     */     
/*     */     private final String e;
/*     */     
/*     */     private final LootContextParameter<? extends Entity> f;
/*     */ 
/*     */     
/*     */     EntityTarget(String var2, LootContextParameter<? extends Entity> var3) {
/* 233 */       this.e = var2;
/* 234 */       this.f = var3;
/*     */     }
/*     */     
/*     */     public LootContextParameter<? extends Entity> a() {
/* 238 */       return this.f;
/*     */     }
/*     */     
/*     */     public static EntityTarget a(String var0) {
/* 242 */       for (EntityTarget var4 : values()) {
/* 243 */         if (var4.e.equals(var0)) {
/* 244 */           return var4;
/*     */         }
/*     */       } 
/* 247 */       throw new IllegalArgumentException("Invalid entity target " + var0);
/*     */     }
/*     */     
/*     */     public static class a
/*     */       extends TypeAdapter<EntityTarget> {
/*     */       public void write(JsonWriter var0, LootTableInfo.EntityTarget var1) throws IOException {
/* 253 */         var0.value(LootTableInfo.EntityTarget.a(var1));
/*     */       }
/*     */ 
/*     */       
/*     */       public LootTableInfo.EntityTarget read(JsonReader var0) throws IOException {
/* 258 */         return LootTableInfo.EntityTarget.a(var0.nextString());
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootTableInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */