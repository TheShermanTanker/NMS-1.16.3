/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.HashMultimap;
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.common.collect.Multimap;
/*    */ import java.util.Map;
/*    */ import java.util.function.Function;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ public class TagStatic
/*    */ {
/* 13 */   private static final Map<MinecraftKey, TagUtil<?>> a = Maps.newHashMap();
/*    */   
/*    */   public static <T> TagUtil<T> a(MinecraftKey var0, Function<ITagRegistry, Tags<T>> var1) {
/* 16 */     TagUtil<T> var2 = new TagUtil<>(var1);
/* 17 */     TagUtil<?> var3 = a.putIfAbsent(var0, var2);
/* 18 */     if (var3 != null) {
/* 19 */       throw new IllegalStateException("Duplicate entry for static tag collection: " + var0);
/*    */     }
/* 21 */     return var2;
/*    */   }
/*    */   
/*    */   public static void a(ITagRegistry var0) {
/* 25 */     a.values().forEach(var1 -> var1.a(var0));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Multimap<MinecraftKey, MinecraftKey> b(ITagRegistry var0) {
/* 33 */     HashMultimap hashMultimap = HashMultimap.create();
/* 34 */     a.forEach((var2, var3) -> var0.putAll(var2, var3.b(var1)));
/* 35 */     return (Multimap<MinecraftKey, MinecraftKey>)hashMultimap;
/*    */   }
/*    */   
/*    */   public static void b() {
/* 39 */     TagUtil[] var0 = { TagsBlock.a, TagsItem.a, TagsFluid.a, TagsEntity.a };
/* 40 */     boolean var1 = Stream.<TagUtil>of(var0).anyMatch(var0 -> !a.containsValue(var0));
/* 41 */     if (var1)
/* 42 */       throw new IllegalStateException("Missing helper registrations"); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TagStatic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */