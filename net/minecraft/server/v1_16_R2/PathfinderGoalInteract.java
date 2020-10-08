/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalInteract
/*    */   extends PathfinderGoalLookAtPlayer
/*    */ {
/*    */   public PathfinderGoalInteract(EntityInsentient var0, Class<? extends EntityLiving> var1, float var2, float var3) {
/* 15 */     super(var0, var1, var2, var3);
/* 16 */     a(EnumSet.of(PathfinderGoal.Type.LOOK, PathfinderGoal.Type.MOVE));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalInteract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */