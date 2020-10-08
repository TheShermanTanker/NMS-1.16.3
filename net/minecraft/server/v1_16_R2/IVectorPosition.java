/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IVectorPosition
/*    */ {
/*    */   Vec3D a(CommandListenerWrapper paramCommandListenerWrapper);
/*    */   
/*    */   Vec2F b(CommandListenerWrapper paramCommandListenerWrapper);
/*    */   
/*    */   default BlockPosition c(CommandListenerWrapper var0) {
/* 14 */     return new BlockPosition(a(var0));
/*    */   }
/*    */   
/*    */   boolean a();
/*    */   
/*    */   boolean b();
/*    */   
/*    */   boolean c();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IVectorPosition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */