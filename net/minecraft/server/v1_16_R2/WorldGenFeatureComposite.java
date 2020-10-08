/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.mutable.MutableBoolean;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureComposite
/*    */   extends WorldGenerator<WorldGenFeatureCompositeConfiguration>
/*    */ {
/*    */   public WorldGenFeatureComposite(Codec<WorldGenFeatureCompositeConfiguration> var0) {
/* 16 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureCompositeConfiguration var4) {
/* 21 */     MutableBoolean var5 = new MutableBoolean();
/* 22 */     var4.c.a(new WorldGenDecoratorContext(var0, var1), var2, var3).forEach(var5 -> {
/*    */           if (((WorldGenFeatureConfigured)var0.b.get()).a(var1, var2, var3, var5)) {
/*    */             var4.setTrue();
/*    */           }
/*    */         });
/* 27 */     return var5.isTrue();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 32 */     return String.format("< %s [%s] >", new Object[] { getClass().getSimpleName(), IRegistry.FEATURE.getKey(this) });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureComposite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */