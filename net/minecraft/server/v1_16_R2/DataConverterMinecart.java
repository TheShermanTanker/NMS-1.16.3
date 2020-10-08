/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.google.common.collect.Lists;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.datafixers.types.templates.TaggedChoice;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.DataResult;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import com.mojang.serialization.DynamicOps;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ public class DataConverterMinecart extends DataFix {
/*    */   public DataConverterMinecart(Schema var0, boolean var1) {
/* 18 */     super(var0, var1);
/*    */   }
/*    */   
/* 21 */   private static final List<String> a = Lists.newArrayList((Object[])new String[] { "MinecartRideable", "MinecartChest", "MinecartFurnace" });
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 30 */     TaggedChoice.TaggedChoiceType<String> var0 = getInputSchema().findChoiceType(DataConverterTypes.ENTITY);
/* 31 */     TaggedChoice.TaggedChoiceType<String> var1 = getOutputSchema().findChoiceType(DataConverterTypes.ENTITY);
/*    */     
/* 33 */     return fixTypeEverywhere("EntityMinecartIdentifiersFix", (Type)var0, (Type)var1, var2 -> ());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterMinecart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */