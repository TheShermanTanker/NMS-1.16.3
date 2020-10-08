/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface LightEngineLayerEventListener
/*    */   extends ILightEngine
/*    */ {
/*    */   @Nullable
/*    */   NibbleArray a(SectionPosition paramSectionPosition);
/*    */   
/*    */   int b(BlockPosition paramBlockPosition);
/*    */   
/*    */   public enum Void
/*    */     implements LightEngineLayerEventListener
/*    */   {
/* 18 */     INSTANCE;
/*    */ 
/*    */     
/*    */     @Nullable
/*    */     public NibbleArray a(SectionPosition var0) {
/* 23 */       return null;
/*    */     }
/*    */ 
/*    */     
/*    */     public int b(BlockPosition var0) {
/* 28 */       return 0;
/*    */     }
/*    */     
/*    */     public void a(SectionPosition var0, boolean var1) {}
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LightEngineLayerEventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */