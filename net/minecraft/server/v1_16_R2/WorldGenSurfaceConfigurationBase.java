/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.datafixers.util.Function3;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ 
/*    */ public class WorldGenSurfaceConfigurationBase implements WorldGenSurfaceConfiguration {
/*    */   static {
/*  9 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)IBlockData.b.fieldOf("top_material").forGetter(()), (App)IBlockData.b.fieldOf("under_material").forGetter(()), (App)IBlockData.b.fieldOf("underwater_material").forGetter(())).apply((Applicative)var0, WorldGenSurfaceConfigurationBase::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<WorldGenSurfaceConfigurationBase> a;
/*    */   
/*    */   private final IBlockData b;
/*    */   private final IBlockData c;
/*    */   private final IBlockData d;
/*    */   
/*    */   public WorldGenSurfaceConfigurationBase(IBlockData var0, IBlockData var1, IBlockData var2) {
/* 20 */     this.b = var0;
/* 21 */     this.c = var1;
/* 22 */     this.d = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a() {
/* 27 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData b() {
/* 32 */     return this.c;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData c() {
/* 37 */     return this.d;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenSurfaceConfigurationBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */