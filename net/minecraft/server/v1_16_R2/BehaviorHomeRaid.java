/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorHomeRaid
/*    */   extends BehaviorHome
/*    */ {
/*    */   public BehaviorHomeRaid(int var0, float var1) {
/*  9 */     super(var0, var1, 1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, EntityLiving var1) {
/* 14 */     Raid var2 = var0.b_(var1.getChunkCoordinates());
/* 15 */     return (super.a(var0, var1) && var2 != null && var2.v() && !var2.isVictory() && !var2.isLoss());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorHomeRaid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */