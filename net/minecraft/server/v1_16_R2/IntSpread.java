/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.datafixers.util.Either;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.DataResult;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.Objects;
/*    */ import java.util.Random;
/*    */ import java.util.function.BiFunction;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IntSpread
/*    */ {
/*    */   public static final Codec<IntSpread> a;
/*    */   private final int b;
/*    */   private final int c;
/*    */   
/*    */   static {
/* 27 */     a = Codec.either((Codec)Codec.INT, RecordCodecBuilder.create(var0 -> var0.group((App)Codec.INT.fieldOf("base").forGetter(()), (App)Codec.INT.fieldOf("spread").forGetter(())).apply((Applicative)var0, IntSpread::new)).comapFlatMap(var0 -> (var0.c < 0) ? DataResult.error("Spread must be non-negative, got: " + var0.c) : DataResult.success(var0), Function.identity())).xmap(var0 -> (IntSpread)var0.map(IntSpread::a, ()), var0 -> (var0.c == 0) ? Either.left(Integer.valueOf(var0.b)) : Either.right(var0));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static Codec<IntSpread> a(int var0, int var1, int var2) {
/* 33 */     Function<IntSpread, DataResult<IntSpread>> var3 = var3 -> 
/* 34 */       (var3.b >= var0 && var3.b <= var1) ? ((var3.c <= var2) ? DataResult.success(var3) : DataResult.error("Spread too big: " + var3.c + " > " + var2)) : DataResult.error("Base value out of range: " + var3.b + " [" + var0 + "-" + var1 + "]");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 42 */     return a.flatXmap(var3, var3);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private IntSpread(int var0, int var1) {
/* 49 */     this.b = var0;
/* 50 */     this.c = var1;
/*    */   }
/*    */   
/*    */   public static IntSpread a(int var0) {
/* 54 */     return new IntSpread(var0, 0);
/*    */   }
/*    */   
/*    */   public static IntSpread a(int var0, int var1) {
/* 58 */     return new IntSpread(var0, var1);
/*    */   }
/*    */   
/*    */   public int a(Random var0) {
/* 62 */     if (this.c == 0) {
/* 63 */       return this.b;
/*    */     }
/* 65 */     return this.b + var0.nextInt(this.c + 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object var0) {
/* 70 */     if (this == var0) {
/* 71 */       return true;
/*    */     }
/* 73 */     if (var0 == null || getClass() != var0.getClass()) {
/* 74 */       return false;
/*    */     }
/* 76 */     IntSpread var1 = (IntSpread)var0;
/* 77 */     return (this.b == var1.b && this.c == var1.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 82 */     return Objects.hash(new Object[] { Integer.valueOf(this.b), Integer.valueOf(this.c) });
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 87 */     return "[" + this.b + '-' + (this.b + this.c) + ']';
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IntSpread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */