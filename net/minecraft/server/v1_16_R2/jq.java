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
/*    */ public class jq
/*    */   implements jp.a
/*    */ {
/* 14 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(String var0, NBTTagCompound var1) {
/* 18 */     if (var0.startsWith("data/minecraft/structures/")) {
/* 19 */       return b(var0, a(var1));
/*    */     }
/* 21 */     return var1;
/*    */   }
/*    */   
/*    */   private static NBTTagCompound a(NBTTagCompound var0) {
/* 25 */     if (!var0.hasKeyOfType("DataVersion", 99)) {
/* 26 */       var0.setInt("DataVersion", 500);
/*    */     }
/* 28 */     return var0;
/*    */   }
/*    */   
/*    */   private static NBTTagCompound b(String var0, NBTTagCompound var1) {
/* 32 */     DefinedStructure var2 = new DefinedStructure();
/* 33 */     int var3 = var1.getInt("DataVersion");
/* 34 */     int var4 = 2532;
/* 35 */     if (var3 < 2532) {
/* 36 */       LOGGER.warn("SNBT Too old, do not forget to update: " + var3 + " < " + '৤' + ": " + var0);
/*    */     }
/* 38 */     NBTTagCompound var5 = GameProfileSerializer.a(DataConverterRegistry.a(), DataFixTypes.STRUCTURE, var1, var3);
/* 39 */     var2.b(var5);
/* 40 */     return var2.a(new NBTTagCompound());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\jq.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */