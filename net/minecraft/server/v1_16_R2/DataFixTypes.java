/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ 
/*    */ public enum DataFixTypes
/*    */ {
/*  7 */   LEVEL(DataConverterTypes.LEVEL),
/*  8 */   PLAYER(DataConverterTypes.PLAYER),
/*  9 */   CHUNK(DataConverterTypes.CHUNK),
/* 10 */   HOTBAR(DataConverterTypes.HOTBAR),
/* 11 */   OPTIONS(DataConverterTypes.OPTIONS),
/* 12 */   STRUCTURE(DataConverterTypes.STRUCTURE),
/* 13 */   STATS(DataConverterTypes.STATS),
/* 14 */   SAVED_DATA(DataConverterTypes.SAVED_DATA),
/* 15 */   ADVANCEMENTS(DataConverterTypes.ADVANCEMENTS),
/* 16 */   POI_CHUNK(DataConverterTypes.POI_CHUNK),
/* 17 */   WORLD_GEN_SETTINGS(DataConverterTypes.WORLD_GEN_SETTINGS);
/*    */   
/*    */   private final DSL.TypeReference l;
/*    */ 
/*    */   
/*    */   DataFixTypes(DSL.TypeReference var2) {
/* 23 */     this.l = var2;
/*    */   }
/*    */   
/*    */   public DSL.TypeReference a() {
/* 27 */     return this.l;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataFixTypes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */