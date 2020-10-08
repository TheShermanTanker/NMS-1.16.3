/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorVictory
/*    */   extends BehaviorStrollRandom
/*    */ {
/*    */   public BehaviorVictory(float var0) {
/*  9 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, EntityCreature var1) {
/* 14 */     Raid var2 = var0.b_(var1.getChunkCoordinates());
/* 15 */     return (var2 != null && var2.isVictory() && super.a(var0, var1));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorVictory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */