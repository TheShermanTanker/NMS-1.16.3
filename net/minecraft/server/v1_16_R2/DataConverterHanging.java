/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.OpticFinder;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ 
/*    */ public class DataConverterHanging extends DataFix {
/* 12 */   private static final int[][] a = new int[][] { { 0, 0, 1 }, { -1, 0, 0 }, { 0, 0, -1 }, { 1, 0, 0 } };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DataConverterHanging(Schema var0, boolean var1) {
/* 20 */     super(var0, var1);
/*    */   }
/*    */   
/*    */   private Dynamic<?> a(Dynamic<?> var0, boolean var1, boolean var2) {
/* 24 */     if ((var1 || var2) && !var0.get("Facing").asNumber().result().isPresent()) {
/*    */       int var3;
/* 26 */       if (var0.get("Direction").asNumber().result().isPresent()) {
/* 27 */         var3 = var0.get("Direction").asByte((byte)0) % a.length;
/* 28 */         int[] var4 = a[var3];
/*    */         
/* 30 */         var0 = var0.set("TileX", var0.createInt(var0.get("TileX").asInt(0) + var4[0]));
/* 31 */         var0 = var0.set("TileY", var0.createInt(var0.get("TileY").asInt(0) + var4[1]));
/* 32 */         var0 = var0.set("TileZ", var0.createInt(var0.get("TileZ").asInt(0) + var4[2]));
/*    */         
/* 34 */         var0 = var0.remove("Direction");
/*    */         
/* 36 */         if (var2 && var0.get("ItemRotation").asNumber().result().isPresent()) {
/* 37 */           var0 = var0.set("ItemRotation", var0.createByte((byte)(var0.get("ItemRotation").asByte((byte)0) * 2)));
/*    */         }
/*    */       } else {
/* 40 */         var3 = var0.get("Dir").asByte((byte)0) % a.length;
/* 41 */         var0 = var0.remove("Dir");
/*    */       } 
/* 43 */       var0 = var0.set("Facing", var0.createByte((byte)var3));
/*    */     } 
/*    */     
/* 46 */     return var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 51 */     Type<?> var0 = getInputSchema().getChoiceType(DataConverterTypes.ENTITY, "Painting");
/* 52 */     OpticFinder<?> var1 = DSL.namedChoice("Painting", var0);
/*    */     
/* 54 */     Type<?> var2 = getInputSchema().getChoiceType(DataConverterTypes.ENTITY, "ItemFrame");
/* 55 */     OpticFinder<?> var3 = DSL.namedChoice("ItemFrame", var2);
/*    */     
/* 57 */     Type<?> var4 = getInputSchema().getType(DataConverterTypes.ENTITY);
/*    */     
/* 59 */     TypeRewriteRule var5 = fixTypeEverywhereTyped("EntityPaintingFix", var4, var2 -> var2.updateTyped(var0, var1, ()));
/*    */ 
/*    */     
/* 62 */     TypeRewriteRule var6 = fixTypeEverywhereTyped("EntityItemFrameFix", var4, var2 -> var2.updateTyped(var0, var1, ()));
/*    */ 
/*    */ 
/*    */     
/* 66 */     return TypeRewriteRule.seq(var5, var6);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterHanging.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */