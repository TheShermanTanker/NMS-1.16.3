/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorOutsideCelebrate
/*    */   extends BehaviorOutside
/*    */ {
/*    */   public BehaviorOutsideCelebrate(float var0) {
/*  9 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, EntityLiving var1) {
/* 14 */     Raid var2 = var0.b_(var1.getChunkCoordinates());
/* 15 */     return (var2 != null && var2.isVictory() && super.a(var0, var1));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorOutsideCelebrate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */