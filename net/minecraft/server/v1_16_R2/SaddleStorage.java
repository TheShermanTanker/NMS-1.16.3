/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public class SaddleStorage
/*    */ {
/*    */   private final DataWatcher dataWatcher;
/*    */   private final DataWatcherObject<Integer> dataWatcherBoostTicks;
/*    */   private final DataWatcherObject<Boolean> f;
/*    */   public boolean boosting;
/*    */   public int currentBoostTicks;
/*    */   public int boostTicks;
/*    */   
/*    */   public SaddleStorage(DataWatcher datawatcher, DataWatcherObject<Integer> datawatcherobject, DataWatcherObject<Boolean> datawatcherobject1) {
/* 15 */     this.dataWatcher = datawatcher;
/* 16 */     this.dataWatcherBoostTicks = datawatcherobject;
/* 17 */     this.f = datawatcherobject1;
/*    */   }
/*    */   
/*    */   public void a() {
/* 21 */     this.boosting = true;
/* 22 */     this.currentBoostTicks = 0;
/* 23 */     this.boostTicks = ((Integer)this.dataWatcher.<Integer>get(this.dataWatcherBoostTicks)).intValue();
/*    */   }
/*    */   
/*    */   public boolean a(Random random) {
/* 27 */     if (this.boosting) {
/* 28 */       return false;
/*    */     }
/* 30 */     this.boosting = true;
/* 31 */     this.currentBoostTicks = 0;
/* 32 */     this.boostTicks = random.nextInt(841) + 140;
/* 33 */     this.dataWatcher.set(this.dataWatcherBoostTicks, Integer.valueOf(this.boostTicks));
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setBoostTicks(int ticks) {
/* 40 */     this.boosting = true;
/* 41 */     this.currentBoostTicks = 0;
/* 42 */     this.boostTicks = ticks;
/* 43 */     this.dataWatcher.set(this.dataWatcherBoostTicks, Integer.valueOf(this.boostTicks));
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(NBTTagCompound nbttagcompound) {
/* 48 */     nbttagcompound.setBoolean("Saddle", hasSaddle());
/*    */   }
/*    */   
/*    */   public void b(NBTTagCompound nbttagcompound) {
/* 52 */     setSaddle(nbttagcompound.getBoolean("Saddle"));
/*    */   }
/*    */   
/*    */   public void setSaddle(boolean flag) {
/* 56 */     this.dataWatcher.set(this.f, Boolean.valueOf(flag));
/*    */   }
/*    */   
/*    */   public boolean hasSaddle() {
/* 60 */     return ((Boolean)this.dataWatcher.<Boolean>get(this.f)).booleanValue();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SaddleStorage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */