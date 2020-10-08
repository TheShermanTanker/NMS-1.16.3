/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFixUtils;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.HashMap;
/*    */ import java.util.Locale;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ 
/*    */ public class DataConverterPainting
/*    */   extends DataConverterNamedEntity {
/*    */   public DataConverterPainting(Schema var0, boolean var1) {
/* 17 */     super(var0, var1, "EntityPaintingMotiveFix", DataConverterTypes.ENTITY, "minecraft:painting");
/*    */   } private static final Map<String, String> a;
/*    */   static {
/* 20 */     a = (Map<String, String>)DataFixUtils.make(Maps.newHashMap(), var0 -> {
/*    */           var0.put("donkeykong", "donkey_kong");
/*    */           var0.put("burningskull", "burning_skull");
/*    */           var0.put("skullandroses", "skull_and_roses");
/*    */         });
/*    */   }
/*    */   public Dynamic<?> a(Dynamic<?> var0) {
/* 27 */     Optional<String> var1 = var0.get("Motive").asString().result();
/* 28 */     if (var1.isPresent()) {
/* 29 */       String var2 = ((String)var1.get()).toLowerCase(Locale.ROOT);
/* 30 */       return var0.set("Motive", var0.createString((new MinecraftKey(a.getOrDefault(var2, var2))).toString()));
/*    */     } 
/* 32 */     return var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected Typed<?> a(Typed<?> var0) {
/* 37 */     return var0.update(DSL.remainderFinder(), this::a);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterPainting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */