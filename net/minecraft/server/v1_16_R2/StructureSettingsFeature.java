/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.datafixers.util.Function3;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.DataResult;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ public class StructureSettingsFeature {
/*    */   public static final Codec<StructureSettingsFeature> a;
/*    */   
/*    */   static {
/* 14 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)Codec.intRange(0, 4096).fieldOf("spacing").forGetter(()), (App)Codec.intRange(0, 4096).fieldOf("separation").forGetter(()), (App)Codec.intRange(0, 2147483647).fieldOf("salt").forGetter(())).apply((Applicative)var0, StructureSettingsFeature::new)).comapFlatMap(var0 -> (var0.b <= var0.c) ? DataResult.error("Spacing has to be smaller than separation") : DataResult.success(var0), 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 19 */         Function.identity());
/*    */   }
/*    */   private final int b;
/*    */   private final int c;
/*    */   private final int d;
/*    */   
/*    */   public StructureSettingsFeature(int var0, int var1, int var2) {
/* 26 */     this.b = var0;
/* 27 */     this.c = var1;
/* 28 */     this.d = var2;
/*    */   }
/*    */   
/*    */   public int a() {
/* 32 */     return this.b;
/*    */   }
/*    */   
/*    */   public int b() {
/* 36 */     return this.c;
/*    */   }
/*    */   
/*    */   public int c() {
/* 40 */     return this.d;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\StructureSettingsFeature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */