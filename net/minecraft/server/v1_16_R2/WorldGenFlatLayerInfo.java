/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.function.BiFunction;
/*    */ 
/*    */ public class WorldGenFlatLayerInfo
/*    */ {
/*    */   static {
/* 12 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)Codec.intRange(0, 256).fieldOf("height").forGetter(WorldGenFlatLayerInfo::a), (App)IRegistry.BLOCK.fieldOf("block").orElse(Blocks.AIR).forGetter(())).apply((Applicative)var0, WorldGenFlatLayerInfo::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<WorldGenFlatLayerInfo> a;
/*    */   private final IBlockData b;
/*    */   private final int c;
/*    */   private int d;
/*    */   
/*    */   public WorldGenFlatLayerInfo(int var0, Block var1) {
/* 22 */     this.c = var0;
/* 23 */     this.b = var1.getBlockData();
/*    */   }
/*    */   
/*    */   public int a() {
/* 27 */     return this.c;
/*    */   }
/*    */   
/*    */   public IBlockData b() {
/* 31 */     return this.b;
/*    */   }
/*    */   
/*    */   public int c() {
/* 35 */     return this.d;
/*    */   }
/*    */   
/*    */   public void a(int var0) {
/* 39 */     this.d = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 44 */     return ((this.c != 1) ? (this.c + "*") : "") + IRegistry.BLOCK.getKey(this.b.getBlock());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFlatLayerInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */