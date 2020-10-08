/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import org.bukkit.entity.Explosive;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.ExplosionPrimeEvent;
/*    */ 
/*    */ public class EntityLargeFireball extends EntityFireballFireball {
/*  7 */   public int yield = 1;
/*    */   
/*    */   public EntityLargeFireball(EntityTypes<? extends EntityLargeFireball> entitytypes, World world) {
/* 10 */     super((EntityTypes)entitytypes, world);
/* 11 */     this.isIncendiary = this.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING);
/*    */   }
/*    */   
/*    */   public EntityLargeFireball(World world, EntityLiving entityliving, double d0, double d1, double d2) {
/* 15 */     super((EntityTypes)EntityTypes.FIREBALL, entityliving, d0, d1, d2, world);
/* 16 */     this.isIncendiary = this.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(MovingObjectPosition movingobjectposition) {
/* 21 */     super.a(movingobjectposition);
/* 22 */     if (!this.world.isClientSide) {
/* 23 */       boolean flag = this.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING);
/*    */ 
/*    */       
/* 26 */       ExplosionPrimeEvent event = new ExplosionPrimeEvent((Explosive)getBukkitEntity());
/* 27 */       this.world.getServer().getPluginManager().callEvent((Event)event);
/*    */       
/* 29 */       if (!event.isCancelled())
/*    */       {
/* 31 */         this.world.createExplosion(this, locX(), locY(), locZ(), event.getRadius(), event.getFire(), flag ? Explosion.Effect.DESTROY : Explosion.Effect.NONE);
/*    */       }
/*    */       
/* 34 */       die();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void a(MovingObjectPositionEntity movingobjectpositionentity) {
/* 41 */     super.a(movingobjectpositionentity);
/* 42 */     if (!this.world.isClientSide) {
/* 43 */       Entity entity = movingobjectpositionentity.getEntity();
/* 44 */       Entity entity1 = getShooter();
/*    */       
/* 46 */       entity.damageEntity(DamageSource.fireball(this, entity1), 6.0F);
/* 47 */       if (entity1 instanceof EntityLiving) {
/* 48 */         a((EntityLiving)entity1, entity);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void saveData(NBTTagCompound nbttagcompound) {
/* 56 */     super.saveData(nbttagcompound);
/* 57 */     nbttagcompound.setInt("ExplosionPower", this.yield);
/*    */   }
/*    */ 
/*    */   
/*    */   public void loadData(NBTTagCompound nbttagcompound) {
/* 62 */     super.loadData(nbttagcompound);
/* 63 */     if (nbttagcompound.hasKeyOfType("ExplosionPower", 99))
/*    */     {
/* 65 */       this.bukkitYield = (this.yield = nbttagcompound.getInt("ExplosionPower"));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityLargeFireball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */