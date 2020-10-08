/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.Optional;
/*    */ 
/*    */ public class DataConverterOminousBannerBlockEntityRename
/*    */   extends DataConverterNamedEntity {
/*    */   public DataConverterOminousBannerBlockEntityRename(Schema var0, boolean var1) {
/* 12 */     super(var0, var1, "OminousBannerBlockEntityRenameFix", DataConverterTypes.BLOCK_ENTITY, "minecraft:banner");
/*    */   }
/*    */ 
/*    */   
/*    */   protected Typed<?> a(Typed<?> var0) {
/* 17 */     return var0.update(DSL.remainderFinder(), this::a);
/*    */   }
/*    */   
/*    */   private Dynamic<?> a(Dynamic<?> var0) {
/* 21 */     Optional<String> var1 = var0.get("CustomName").asString().result();
/* 22 */     if (var1.isPresent()) {
/* 23 */       String var2 = var1.get();
/* 24 */       var2 = var2.replace("\"translate\":\"block.minecraft.illager_banner\"", "\"translate\":\"block.minecraft.ominous_banner\"");
/* 25 */       return var0.set("CustomName", var0.createString(var2));
/*    */     } 
/* 27 */     return var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterOminousBannerBlockEntityRename.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */