/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSyntaxException;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionSlideDownBlock
/*    */   extends CriterionTriggerAbstract<CriterionSlideDownBlock.a>
/*    */ {
/* 15 */   private static final MinecraftKey a = new MinecraftKey("slide_down_block");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 19 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 24 */     Block var3 = a(var0);
/* 25 */     CriterionTriggerProperties var4 = CriterionTriggerProperties.a(var0.get("state"));
/* 26 */     if (var3 != null) {
/* 27 */       var4.a(var3.getStates(), var1 -> {
/*    */             throw new JsonSyntaxException("Block " + var0 + " has no property " + var1);
/*    */           });
/*    */     }
/* 31 */     return new a(var1, var3, var4);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   private static Block a(JsonObject var0) {
/* 36 */     if (var0.has("block")) {
/* 37 */       MinecraftKey var1 = new MinecraftKey(ChatDeserializer.h(var0, "block"));
/* 38 */       return (Block)IRegistry.BLOCK.getOptional(var1).orElseThrow(() -> new JsonSyntaxException("Unknown block type '" + var0 + "'"));
/*    */     } 
/* 40 */     return null;
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0, IBlockData var1) {
/* 44 */     a(var0, var1 -> var1.a(var0));
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     private final Block a;
/*    */     private final CriterionTriggerProperties b;
/*    */     
/*    */     public a(CriterionConditionEntity.b var0, @Nullable Block var1, CriterionTriggerProperties var2) {
/* 52 */       super(CriterionSlideDownBlock.b(), var0);
/* 53 */       this.a = var1;
/* 54 */       this.b = var2;
/*    */     }
/*    */     
/*    */     public static a a(Block var0) {
/* 58 */       return new a(CriterionConditionEntity.b.a, var0, CriterionTriggerProperties.a);
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 63 */       JsonObject var1 = super.a(var0);
/* 64 */       if (this.a != null) {
/* 65 */         var1.addProperty("block", IRegistry.BLOCK.getKey(this.a).toString());
/*    */       }
/* 67 */       var1.add("state", this.b.a());
/* 68 */       return var1;
/*    */     }
/*    */     
/*    */     public boolean a(IBlockData var0) {
/* 72 */       if (this.a != null && !var0.a(this.a)) {
/* 73 */         return false;
/*    */       }
/* 75 */       if (!this.b.a(var0)) {
/* 76 */         return false;
/*    */       }
/* 78 */       return true;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionSlideDownBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */