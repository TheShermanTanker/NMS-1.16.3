/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import com.google.common.collect.Lists;
/*    */ import com.google.common.collect.Sets;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import java.util.function.Function;
/*    */ import java.util.stream.Collectors;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ public class TagUtil<T>
/*    */ {
/* 15 */   private Tags<T> a = Tags.c();
/* 16 */   private final List<a<T>> b = Lists.newArrayList();
/*    */   private final Function<ITagRegistry, Tags<T>> c;
/*    */   
/*    */   public TagUtil(Function<ITagRegistry, Tags<T>> var0) {
/* 20 */     this.c = var0;
/*    */   }
/*    */   
/*    */   public Tag.e<T> a(String var0) {
/* 24 */     a<T> var1 = new a<>(new MinecraftKey(var0));
/* 25 */     this.b.add(var1);
/* 26 */     return var1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(ITagRegistry var0) {
/* 36 */     Tags<T> var1 = this.c.apply(var0);
/* 37 */     this.a = var1;
/* 38 */     this.b.forEach(var1 -> var1.a(var0::a));
/*    */   }
/*    */   
/*    */   public Tags<T> b() {
/* 42 */     return this.a;
/*    */   }
/*    */   
/*    */   public List<? extends Tag.e<T>> c() {
/* 46 */     return (List)this.b;
/*    */   }
/*    */   
/*    */   public Set<MinecraftKey> b(ITagRegistry var0) {
/* 50 */     Tags<T> var1 = this.c.apply(var0);
/* 51 */     Set<MinecraftKey> var2 = (Set<MinecraftKey>)this.b.stream().map(a::a).collect(Collectors.toSet());
/* 52 */     ImmutableSet<MinecraftKey> var3 = ImmutableSet.copyOf(var1.b());
/* 53 */     return (Set<MinecraftKey>)Sets.difference(var2, (Set)var3);
/*    */   }
/*    */   
/*    */   static class a<T> implements Tag.e<T> {
/*    */     @Nullable
/*    */     private Tag<T> b;
/*    */     protected final MinecraftKey a;
/*    */     
/*    */     private a(MinecraftKey var0) {
/* 62 */       this.a = var0;
/*    */     }
/*    */ 
/*    */     
/*    */     public MinecraftKey a() {
/* 67 */       return this.a;
/*    */     }
/*    */     
/*    */     private Tag<T> c() {
/* 71 */       if (this.b == null) {
/* 72 */         throw new IllegalStateException("Tag " + this.a + " used before it was bound");
/*    */       }
/* 74 */       return this.b;
/*    */     }
/*    */     
/*    */     void a(Function<MinecraftKey, Tag<T>> var0) {
/* 78 */       this.b = var0.apply(this.a);
/*    */     }
/*    */ 
/*    */     
/*    */     public boolean isTagged(T var0) {
/* 83 */       return c().isTagged(var0);
/*    */     }
/*    */ 
/*    */     
/*    */     public List<T> getTagged() {
/* 88 */       return c().getTagged();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TagUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */