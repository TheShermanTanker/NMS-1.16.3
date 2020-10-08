/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalRandomTargetNonTamed<T extends EntityLiving>
/*    */   extends PathfinderGoalNearestAttackableTarget<T>
/*    */ {
/*    */   private final EntityTameableAnimal i;
/*    */   
/*    */   public PathfinderGoalRandomTargetNonTamed(EntityTameableAnimal var0, Class<T> var1, boolean var2, @Nullable Predicate<EntityLiving> var3) {
/* 13 */     super(var0, var1, 10, var2, false, var3);
/* 14 */     this.i = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 19 */     return (!this.i.isTamed() && super.a());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/* 24 */     if (this.d != null) {
/* 25 */       return this.d.a(this.e, this.c);
/*    */     }
/* 27 */     return super.b();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalRandomTargetNonTamed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */