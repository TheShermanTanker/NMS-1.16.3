/*    */ package net.minecraft.server.v1_16_R2;
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
/*    */ public interface ILightEngine
/*    */ {
/*    */   default void a(BlockPosition var0, boolean var1) {
/* 17 */     a(SectionPosition.a(var0), var1);
/*    */   }
/*    */   
/*    */   void a(SectionPosition paramSectionPosition, boolean paramBoolean);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ILightEngine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */