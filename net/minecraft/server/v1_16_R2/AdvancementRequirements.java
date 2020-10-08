/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import java.util.Collection;
/*    */ 
/*    */ public interface AdvancementRequirements {
/*    */   static {
/*  6 */     AND = (var0 -> {
/*    */         String[][] var1 = new String[var0.size()][];
/*    */         int var2 = 0;
/*    */         for (String var4 : var0) {
/*    */           (new String[1])[0] = var4;
/*    */           var1[var2++] = new String[1];
/*    */         } 
/*    */         return var1;
/*    */       });
/* 15 */     OR = (var0 -> new String[][] { (String[])var0.toArray((Object[])new String[0]) });
/*    */   }
/*    */   
/*    */   public static final AdvancementRequirements AND;
/*    */   public static final AdvancementRequirements OR;
/*    */   
/*    */   String[][] createRequirements(Collection<String> paramCollection);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\AdvancementRequirements.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */