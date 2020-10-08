/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import com.google.common.collect.Streams;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ import java.util.Set;
/*    */ import java.util.function.BiConsumer;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Supplier;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ix
/*    */ {
/*    */   private final Optional<MinecraftKey> a;
/*    */   private final Set<ja> b;
/*    */   private Optional<String> c;
/*    */   
/*    */   public ix(Optional<MinecraftKey> var0, Optional<String> var1, ja... var2) {
/* 27 */     this.a = var0;
/* 28 */     this.c = var1;
/* 29 */     this.b = (Set<ja>)ImmutableSet.copyOf((Object[])var2);
/*    */   }
/*    */   
/*    */   public MinecraftKey a(Block var0, iz var1, BiConsumer<MinecraftKey, Supplier<JsonElement>> var2) {
/* 33 */     return a(iw.a(var0, this.c.orElse("")), var1, var2);
/*    */   }
/*    */   
/*    */   public MinecraftKey a(Block var0, String var1, iz var2, BiConsumer<MinecraftKey, Supplier<JsonElement>> var3) {
/* 37 */     return a(iw.a(var0, var1 + (String)this.c.orElse("")), var2, var3);
/*    */   }
/*    */   
/*    */   public MinecraftKey b(Block var0, String var1, iz var2, BiConsumer<MinecraftKey, Supplier<JsonElement>> var3) {
/* 41 */     return a(iw.a(var0, var1), var2, var3);
/*    */   }
/*    */   
/*    */   public MinecraftKey a(MinecraftKey var0, iz var1, BiConsumer<MinecraftKey, Supplier<JsonElement>> var2) {
/* 45 */     Map<ja, MinecraftKey> var3 = a(var1);
/*    */     
/* 47 */     var2.accept(var0, () -> {
/*    */           JsonObject var1 = new JsonObject();
/*    */           
/*    */           this.a.ifPresent(());
/*    */           if (!var0.isEmpty()) {
/*    */             JsonObject var2 = new JsonObject();
/*    */             var0.forEach(());
/*    */             var1.add("textures", (JsonElement)var2);
/*    */           } 
/*    */           return (JsonElement)var1;
/*    */         });
/* 58 */     return var0;
/*    */   }
/*    */   
/*    */   private Map<ja, MinecraftKey> a(iz var0) {
/* 62 */     return (Map<ja, MinecraftKey>)Streams.concat(new Stream[] { this.b.stream(), var0.a() }).collect(ImmutableMap.toImmutableMap(Function.identity(), var0::a));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ix.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */