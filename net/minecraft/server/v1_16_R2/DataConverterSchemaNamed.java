/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.datafixers.types.templates.Const;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.DataResult;
/*    */ import com.mojang.serialization.DynamicOps;
/*    */ import com.mojang.serialization.codecs.PrimitiveCodec;
/*    */ 
/*    */ public class DataConverterSchemaNamed extends Schema {
/*    */   public DataConverterSchemaNamed(int var0, Schema var1) {
/* 14 */     super(var0, var1);
/*    */   }
/*    */   
/*    */   public static String a(String var0) {
/* 18 */     MinecraftKey var1 = MinecraftKey.a(var0);
/* 19 */     if (var1 != null) {
/* 20 */       return var1.toString();
/*    */     }
/* 22 */     return var0;
/*    */   }
/*    */   
/* 25 */   public static final PrimitiveCodec<String> a = new PrimitiveCodec<String>()
/*    */     {
/*    */       public <T> DataResult<String> read(DynamicOps<T> var0, T var1) {
/* 28 */         return var0
/* 29 */           .getStringValue(var1)
/* 30 */           .map(DataConverterSchemaNamed::a);
/*    */       }
/*    */ 
/*    */       
/*    */       public <T> T write(DynamicOps<T> var0, String var1) {
/* 35 */         return (T)var0.createString(var1);
/*    */       }
/*    */ 
/*    */       
/*    */       public String toString() {
/* 40 */         return "NamespacedString";
/*    */       }
/*    */     };
/*    */   
/* 44 */   private static final Type<String> b = (Type<String>)new Const.PrimitiveType((Codec)a);
/*    */   
/*    */   public static Type<String> a() {
/* 47 */     return b;
/*    */   }
/*    */ 
/*    */   
/*    */   public Type<?> getChoiceType(DSL.TypeReference var0, String var1) {
/* 52 */     return super.getChoiceType(var0, a(var1));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSchemaNamed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */