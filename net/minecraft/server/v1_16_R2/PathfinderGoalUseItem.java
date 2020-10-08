/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalUseItem<T extends EntityInsentient>
/*    */   extends PathfinderGoal
/*    */ {
/*    */   private final T a;
/*    */   private final ItemStack b;
/*    */   private final Predicate<? super T> c;
/*    */   private final SoundEffect d;
/*    */   
/*    */   public PathfinderGoalUseItem(T var0, ItemStack var1, @Nullable SoundEffect var2, Predicate<? super T> var3) {
/* 19 */     this.a = var0;
/* 20 */     this.b = var1;
/* 21 */     this.d = var2;
/* 22 */     this.c = var3;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 27 */     return this.c.test(this.a);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/* 32 */     return this.a.isHandRaised();
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 37 */     this.a.setSlot(EnumItemSlot.MAINHAND, this.b.cloneItemStack());
/* 38 */     this.a.c(EnumHand.MAIN_HAND);
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 43 */     this.a.setSlot(EnumItemSlot.MAINHAND, ItemStack.b);
/*    */     
/* 45 */     if (this.d != null)
/* 46 */       this.a.playSound(this.d, 1.0F, this.a.getRandom().nextFloat() * 0.2F + 0.9F); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalUseItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */