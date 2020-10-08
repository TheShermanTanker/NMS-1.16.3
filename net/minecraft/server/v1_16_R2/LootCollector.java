/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.HashMultimap;
/*    */ import com.google.common.collect.ImmutableMultimap;
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import com.google.common.collect.Multimap;
/*    */ import java.util.Set;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Supplier;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootCollector
/*    */ {
/*    */   private final Multimap<String, String> a;
/*    */   private final Supplier<String> b;
/*    */   private final LootContextParameterSet c;
/*    */   private final Function<MinecraftKey, LootItemCondition> d;
/*    */   private final Set<MinecraftKey> e;
/*    */   private final Function<MinecraftKey, LootTable> f;
/*    */   private final Set<MinecraftKey> g;
/*    */   private String h;
/*    */   
/*    */   public LootCollector(LootContextParameterSet var0, Function<MinecraftKey, LootItemCondition> var1, Function<MinecraftKey, LootTable> var2) {
/* 31 */     this((Multimap<String, String>)HashMultimap.create(), () -> "", var0, var1, (Set<MinecraftKey>)ImmutableSet.of(), var2, (Set<MinecraftKey>)ImmutableSet.of());
/*    */   }
/*    */   
/*    */   public LootCollector(Multimap<String, String> var0, Supplier<String> var1, LootContextParameterSet var2, Function<MinecraftKey, LootItemCondition> var3, Set<MinecraftKey> var4, Function<MinecraftKey, LootTable> var5, Set<MinecraftKey> var6) {
/* 35 */     this.a = var0;
/* 36 */     this.b = var1;
/* 37 */     this.c = var2;
/* 38 */     this.d = var3;
/* 39 */     this.e = var4;
/* 40 */     this.f = var5;
/* 41 */     this.g = var6;
/*    */   }
/*    */   
/*    */   private String b() {
/* 45 */     if (this.h == null) {
/* 46 */       this.h = this.b.get();
/*    */     }
/*    */     
/* 49 */     return this.h;
/*    */   }
/*    */   
/*    */   public void a(String var0) {
/* 53 */     this.a.put(b(), var0);
/*    */   }
/*    */   
/*    */   public LootCollector b(String var0) {
/* 57 */     return new LootCollector(this.a, () -> b() + var0, this.c, this.d, this.e, this.f, this.g);
/*    */   }
/*    */   
/*    */   public LootCollector a(String var0, MinecraftKey var1) {
/* 61 */     ImmutableSet<MinecraftKey> var2 = ImmutableSet.builder().addAll(this.g).add(var1).build();
/* 62 */     return new LootCollector(this.a, () -> b() + var0, this.c, this.d, this.e, this.f, (Set<MinecraftKey>)var2);
/*    */   }
/*    */   
/*    */   public LootCollector b(String var0, MinecraftKey var1) {
/* 66 */     ImmutableSet<MinecraftKey> var2 = ImmutableSet.builder().addAll(this.e).add(var1).build();
/* 67 */     return new LootCollector(this.a, () -> b() + var0, this.c, this.d, (Set<MinecraftKey>)var2, this.f, this.g);
/*    */   }
/*    */   
/*    */   public boolean a(MinecraftKey var0) {
/* 71 */     return this.g.contains(var0);
/*    */   }
/*    */   
/*    */   public boolean b(MinecraftKey var0) {
/* 75 */     return this.e.contains(var0);
/*    */   }
/*    */   
/*    */   public Multimap<String, String> a() {
/* 79 */     return (Multimap<String, String>)ImmutableMultimap.copyOf(this.a);
/*    */   }
/*    */   
/*    */   public void a(LootItemUser var0) {
/* 83 */     this.c.a(this, var0);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public LootTable c(MinecraftKey var0) {
/* 88 */     return this.f.apply(var0);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public LootItemCondition d(MinecraftKey var0) {
/* 93 */     return this.d.apply(var0);
/*    */   }
/*    */   
/*    */   public LootCollector a(LootContextParameterSet var0) {
/* 97 */     return new LootCollector(this.a, this.b, var0, this.d, this.e, this.f, this.g);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootCollector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */