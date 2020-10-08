/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class EntityWaterAnimal
/*    */   extends EntityCreature
/*    */ {
/*    */   protected EntityWaterAnimal(EntityTypes<? extends EntityWaterAnimal> var0, World var1) {
/* 14 */     super((EntityTypes)var0, var1);
/*    */     
/* 16 */     a(PathType.WATER, 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean cL() {
/* 21 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumMonsterType getMonsterType() {
/* 26 */     return EnumMonsterType.WATER_MOB;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IWorldReader var0) {
/* 31 */     return var0.j(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int D() {
/* 36 */     return 120;
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getExpValue(EntityHuman var0) {
/* 41 */     return 1 + this.world.random.nextInt(3);
/*    */   }
/*    */   
/*    */   protected void a(int var0) {
/* 45 */     if (isAlive() && !aG()) {
/* 46 */       setAirTicks(var0 - 1);
/* 47 */       if (getAirTicks() == -20) {
/* 48 */         setAirTicks(0);
/* 49 */         damageEntity(DamageSource.DROWN, 2.0F);
/*    */       } 
/*    */     } else {
/* 52 */       setAirTicks(300);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void entityBaseTick() {
/* 58 */     int var0 = getAirTicks();
/* 59 */     super.entityBaseTick();
/* 60 */     a(var0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean bU() {
/* 66 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(EntityHuman var0) {
/* 71 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityWaterAnimal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */