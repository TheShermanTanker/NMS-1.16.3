/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public class LootContextParameter<T>
/*    */ {
/*    */   private final MinecraftKey a;
/*    */   
/*    */   public LootContextParameter(MinecraftKey var0) {
/*  9 */     this.a = var0;
/*    */   }
/*    */   
/*    */   public MinecraftKey a() {
/* 13 */     return this.a;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 18 */     return "<parameter " + this.a + ">";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootContextParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */