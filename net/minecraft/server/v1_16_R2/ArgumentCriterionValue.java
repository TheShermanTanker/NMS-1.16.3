/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ 
/*    */ public interface ArgumentCriterionValue<T extends CriterionConditionValue<?>>
/*    */   extends ArgumentType<T>
/*    */ {
/*    */   public static class b
/*    */     implements ArgumentCriterionValue<CriterionConditionValue.IntegerRange> {
/* 15 */     private static final Collection<String> a = Arrays.asList(new String[] { "0..5", "0", "-5", "-100..", "..100" });
/*    */     
/*    */     public static CriterionConditionValue.IntegerRange a(CommandContext<CommandListenerWrapper> var0, String var1) {
/* 18 */       return (CriterionConditionValue.IntegerRange)var0.getArgument(var1, CriterionConditionValue.IntegerRange.class);
/*    */     }
/*    */ 
/*    */     
/*    */     public CriterionConditionValue.IntegerRange parse(StringReader var0) throws CommandSyntaxException {
/* 23 */       return CriterionConditionValue.IntegerRange.a(var0);
/*    */     }
/*    */ 
/*    */     
/*    */     public Collection<String> getExamples() {
/* 28 */       return a;
/*    */     }
/*    */   }
/*    */   
/*    */   public static class a implements ArgumentCriterionValue<CriterionConditionValue.FloatRange> {
/* 33 */     private static final Collection<String> a = Arrays.asList(new String[] { "0..5.2", "0", "-5.4", "-100.76..", "..100" });
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public CriterionConditionValue.FloatRange parse(StringReader var0) throws CommandSyntaxException {
/* 41 */       return CriterionConditionValue.FloatRange.a(var0);
/*    */     }
/*    */ 
/*    */     
/*    */     public Collection<String> getExamples() {
/* 46 */       return a;
/*    */     }
/*    */   }
/*    */   
/*    */   static b a() {
/* 51 */     return new b();
/*    */   }
/*    */   
/*    */   static a b() {
/* 55 */     return new a();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentCriterionValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */