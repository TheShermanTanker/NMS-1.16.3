/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalNearestAttackableTargetWitch<T extends EntityLiving>
/*    */   extends PathfinderGoalNearestAttackableTarget<T>
/*    */ {
/*    */   private boolean i;
/*    */   
/*    */   public PathfinderGoalNearestAttackableTargetWitch(EntityRaider var0, Class<T> var1, int var2, boolean var3, boolean var4, @Nullable Predicate<EntityLiving> var5) {
/* 13 */     super(var0, var1, var2, var3, var4, var5);
/* 14 */     this.i = true;
/*    */   }
/*    */   
/*    */   public void a(boolean var0) {
/* 18 */     this.i = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 23 */     return (this.i && super.a());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalNearestAttackableTargetWitch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */