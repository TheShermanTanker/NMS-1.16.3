/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ 
/*    */ public class DataConverterBeehive extends DataConverterPOIRename {
/*    */   public DataConverterBeehive(Schema var0) {
/*  7 */     super(var0, false);
/*    */   }
/*    */ 
/*    */   
/*    */   protected String a(String var0) {
/* 12 */     return var0.equals("minecraft:bee_hive") ? "minecraft:beehive" : var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterBeehive.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */