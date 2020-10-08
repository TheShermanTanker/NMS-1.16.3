/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.Objects;
/*    */ 
/*    */ public class DataConverterGuardian
/*    */   extends DataConverterEntityNameAbstract {
/*    */   public DataConverterGuardian(Schema var0, boolean var1) {
/* 11 */     super("EntityElderGuardianSplitFix", var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected Pair<String, Dynamic<?>> a(String var0, Dynamic<?> var1) {
/* 16 */     return Pair.of((Objects.equals(var0, "Guardian") && var1.get("Elder").asBoolean(false)) ? "ElderGuardian" : var0, var1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterGuardian.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */