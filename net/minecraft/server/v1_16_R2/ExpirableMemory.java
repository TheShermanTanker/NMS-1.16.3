/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.Optional;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExpirableMemory<T>
/*    */ {
/*    */   private final T a;
/*    */   private long b;
/*    */   
/*    */   public ExpirableMemory(T var0, long var1) {
/* 18 */     this.a = var0;
/* 19 */     this.b = var1;
/*    */   }
/*    */   
/*    */   public void a() {
/* 23 */     if (e()) {
/* 24 */       this.b--;
/*    */     }
/*    */   }
/*    */   
/*    */   public static <T> ExpirableMemory<T> a(T var0) {
/* 29 */     return new ExpirableMemory<>(var0, Long.MAX_VALUE);
/*    */   }
/*    */   
/*    */   public static <T> ExpirableMemory<T> a(T var0, long var1) {
/* 33 */     return new ExpirableMemory<>(var0, var1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public T c() {
/* 41 */     return this.a;
/*    */   }
/*    */   
/*    */   public boolean d() {
/* 45 */     return (this.b <= 0L);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 50 */     return this.a.toString() + (e() ? (" (ttl: " + this.b + ")") : "");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean e() {
/* 56 */     return (this.b != Long.MAX_VALUE);
/*    */   }
/*    */   
/*    */   public static <T> Codec<ExpirableMemory<T>> a(Codec<T> var0) {
/* 60 */     return RecordCodecBuilder.create(var1 -> var1.group((App)var0.fieldOf("value").forGetter(()), (App)Codec.LONG.optionalFieldOf("ttl").forGetter(())).apply((Applicative)var1, ()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ExpirableMemory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */