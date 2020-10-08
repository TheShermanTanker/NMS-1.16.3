/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.OpticFinder;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.datafixers.types.templates.List;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.Optional;
/*    */ 
/*    */ 
/*    */ public class DataConverterVillagerLevelXp
/*    */   extends DataFix
/*    */ {
/* 18 */   private static final int[] a = new int[] { 0, 10, 50, 100, 150 };
/*    */   
/*    */   public static int a(int var0) {
/* 21 */     return a[MathHelper.clamp(var0 - 1, 0, a.length - 1)];
/*    */   }
/*    */   
/*    */   public DataConverterVillagerLevelXp(Schema var0, boolean var1) {
/* 25 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 30 */     Type<?> var0 = getInputSchema().getChoiceType(DataConverterTypes.ENTITY, "minecraft:villager");
/* 31 */     OpticFinder<?> var1 = DSL.namedChoice("minecraft:villager", var0);
/*    */     
/* 33 */     OpticFinder<?> var2 = var0.findField("Offers");
/* 34 */     Type<?> var3 = var2.type();
/* 35 */     OpticFinder<?> var4 = var3.findField("Recipes");
/* 36 */     List.ListType<?> var5 = (List.ListType)var4.type();
/* 37 */     OpticFinder<?> var6 = var5.getElement().finder();
/*    */     
/* 39 */     return fixTypeEverywhereTyped("Villager level and xp rebuild", getInputSchema().getType(DataConverterTypes.ENTITY), var5 -> var5.updateTyped(var0, var1, ()));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static Typed<?> a(Typed<?> var0, int var1) {
/* 72 */     return var0.update(DSL.remainderFinder(), var1 -> var1.update("VillagerData", ()));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static Typed<?> b(Typed<?> var0, int var1) {
/* 79 */     int var2 = a(var1);
/* 80 */     return var0.update(DSL.remainderFinder(), var1 -> var1.set("Xp", var1.createInt(var0)));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterVillagerLevelXp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */