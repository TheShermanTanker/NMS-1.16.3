/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.google.common.collect.Lists;
/*    */ import com.google.common.collect.Sets;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.TreeMap;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class io
/*    */   implements il
/*    */ {
/*    */   private final Block a;
/*    */   private final List<ir> b;
/* 22 */   private final Set<IBlockState<?>> c = Sets.newHashSet();
/* 23 */   private final List<ip> d = Lists.newArrayList();
/*    */   
/*    */   private io(Block var0, List<ir> var1) {
/* 26 */     this.a = var0;
/* 27 */     this.b = var1;
/*    */   }
/*    */   
/*    */   public io a(ip var0) {
/* 31 */     var0.b().forEach(var0 -> {
/*    */           if (this.a.getStates().a(var0.getName()) != var0) {
/*    */             throw new IllegalStateException("Property " + var0 + " is not defined for block " + this.a);
/*    */           }
/*    */           
/*    */           if (!this.c.add(var0)) {
/*    */             throw new IllegalStateException("Values of property " + var0 + " already defined for block " + this.a);
/*    */           }
/*    */         });
/* 40 */     this.d.add(var0);
/* 41 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonElement get() {
/* 46 */     Stream<Pair<iq, List<ir>>> var0 = Stream.of(Pair.of(iq.a(), this.b));
/*    */     
/* 48 */     for (ip ip : this.d) {
/* 49 */       Map<iq, List<ir>> var3 = ip.a();
/* 50 */       var0 = var0.flatMap(var1 -> var0.entrySet().stream().map(()));
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 57 */     Map<String, JsonElement> var1 = new TreeMap<>();
/* 58 */     var0.forEach(var1 -> (JsonElement)var0.put(((iq)var1.getFirst()).b(), ir.a((List<ir>)var1.getSecond())));
/*    */ 
/*    */ 
/*    */     
/* 62 */     JsonObject var2 = new JsonObject();
/* 63 */     var2.add("variants", (JsonElement)SystemUtils.a(new JsonObject(), var1 -> var0.forEach(var1::add)));
/* 64 */     return (JsonElement)var2;
/*    */   }
/*    */   
/*    */   private static List<ir> a(List<ir> var0, List<ir> var1) {
/* 68 */     ImmutableList.Builder<ir> var2 = ImmutableList.builder();
/*    */     
/* 70 */     var0.forEach(var2 -> var0.forEach(()));
/* 71 */     return (List<ir>)var2.build();
/*    */   }
/*    */ 
/*    */   
/*    */   public Block a() {
/* 76 */     return this.a;
/*    */   }
/*    */   
/*    */   public static io a(Block var0) {
/* 80 */     return new io(var0, (List<ir>)ImmutableList.of(ir.a()));
/*    */   }
/*    */   
/*    */   public static io a(Block var0, ir var1) {
/* 84 */     return new io(var0, (List<ir>)ImmutableList.of(var1));
/*    */   }
/*    */   
/*    */   public static io a(Block var0, ir... var1) {
/* 88 */     return new io(var0, (List<ir>)ImmutableList.copyOf((Object[])var1));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\io.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */