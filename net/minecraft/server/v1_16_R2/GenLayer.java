/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GenLayer
/*    */ {
/* 15 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   private final AreaLazy b;
/*    */   
/*    */   public GenLayer(AreaFactory<AreaLazy> var0) {
/* 19 */     this.b = var0.make();
/*    */   }
/*    */   
/*    */   public BiomeBase a(IRegistry<BiomeBase> var0, int var1, int var2) {
/* 23 */     int var3 = this.b.a(var1, var2);
/* 24 */     ResourceKey<BiomeBase> var4 = BiomeRegistry.a(var3);
/* 25 */     if (var4 == null) {
/* 26 */       throw new IllegalStateException("Unknown biome id emitted by layers: " + var3);
/*    */     }
/*    */     
/* 29 */     BiomeBase var5 = var0.a(var4);
/*    */     
/* 31 */     if (var5 == null) {
/* 32 */       if (SharedConstants.d) {
/* 33 */         throw (IllegalStateException)SystemUtils.c(new IllegalStateException("Unknown biome id: " + var3));
/*    */       }
/* 35 */       LOGGER.warn("Unknown biome id: ", Integer.valueOf(var3));
/* 36 */       return var0.a(BiomeRegistry.a(0));
/*    */     } 
/*    */     
/* 39 */     return var5;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GenLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */