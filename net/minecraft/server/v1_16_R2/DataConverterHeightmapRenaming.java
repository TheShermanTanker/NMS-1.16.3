/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.OpticFinder;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.Optional;
/*    */ 
/*    */ public class DataConverterHeightmapRenaming extends DataFix {
/*    */   public DataConverterHeightmapRenaming(Schema var0, boolean var1) {
/* 15 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected TypeRewriteRule makeRule() {
/* 20 */     Type<?> var0 = getInputSchema().getType(DataConverterTypes.CHUNK);
/* 21 */     OpticFinder<?> var1 = var0.findField("Level");
/* 22 */     return fixTypeEverywhereTyped("HeightmapRenamingFix", var0, var1 -> var1.updateTyped(var0, ()));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private Dynamic<?> a(Dynamic<?> var0) {
/* 28 */     Optional<? extends Dynamic<?>> var1 = var0.get("Heightmaps").result();
/* 29 */     if (!var1.isPresent()) {
/* 30 */       return var0;
/*    */     }
/*    */     
/* 33 */     Dynamic<?> var2 = var1.get();
/*    */     
/* 35 */     Optional<? extends Dynamic<?>> var3 = var2.get("LIQUID").result();
/* 36 */     if (var3.isPresent()) {
/* 37 */       var2 = var2.remove("LIQUID");
/* 38 */       var2 = var2.set("WORLD_SURFACE_WG", var3.get());
/*    */     } 
/*    */     
/* 41 */     Optional<? extends Dynamic<?>> var4 = var2.get("SOLID").result();
/* 42 */     if (var4.isPresent()) {
/* 43 */       var2 = var2.remove("SOLID");
/* 44 */       var2 = var2.set("OCEAN_FLOOR_WG", var4.get());
/* 45 */       var2 = var2.set("OCEAN_FLOOR", var4.get());
/*    */     } 
/*    */     
/* 48 */     Optional<? extends Dynamic<?>> var5 = var2.get("LIGHT").result();
/* 49 */     if (var5.isPresent()) {
/* 50 */       var2 = var2.remove("LIGHT");
/* 51 */       var2 = var2.set("LIGHT_BLOCKING", var5.get());
/*    */     } 
/*    */     
/* 54 */     Optional<? extends Dynamic<?>> var6 = var2.get("RAIN").result();
/* 55 */     if (var6.isPresent()) {
/* 56 */       var2 = var2.remove("RAIN");
/* 57 */       var2 = var2.set("MOTION_BLOCKING", var6.get());
/* 58 */       var2 = var2.set("MOTION_BLOCKING_NO_LEAVES", var6.get());
/*    */     } 
/*    */     
/* 61 */     return var0.set("Heightmaps", var2);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterHeightmapRenaming.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */