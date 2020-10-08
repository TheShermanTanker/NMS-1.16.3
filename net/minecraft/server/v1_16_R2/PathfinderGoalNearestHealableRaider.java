/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalNearestHealableRaider<T extends EntityLiving>
/*    */   extends PathfinderGoalNearestAttackableTarget<T>
/*    */ {
/*    */   private int i;
/*    */   
/*    */   public PathfinderGoalNearestHealableRaider(EntityRaider var0, Class<T> var1, boolean var2, @Nullable Predicate<EntityLiving> var3) {
/* 15 */     super(var0, var1, 500, var2, false, var3);
/* 16 */     this.i = 0;
/*    */   }
/*    */   
/*    */   public int h() {
/* 20 */     return this.i;
/*    */   }
/*    */   
/*    */   public void j() {
/* 24 */     this.i--;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 29 */     if (this.i > 0 || !this.e.getRandom().nextBoolean()) {
/* 30 */       return false;
/*    */     }
/* 32 */     if (!((EntityRaider)this.e).fb()) {
/* 33 */       return false;
/*    */     }
/*    */     
/* 36 */     g();
/* 37 */     return (this.c != null);
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 42 */     this.i = 200;
/* 43 */     super.c();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalNearestHealableRaider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */