/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.destroystokyo.paper.event.entity.ThrownEggHatchEvent;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Egg;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*    */ import org.bukkit.event.player.PlayerEggThrowEvent;
/*    */ 
/*    */ public class EntityEgg extends EntityProjectileThrowable {
/*    */   public EntityEgg(EntityTypes<? extends EntityEgg> entitytypes, World world) {
/* 13 */     super((EntityTypes)entitytypes, world);
/*    */   }
/*    */   
/*    */   public EntityEgg(World world, EntityLiving entityliving) {
/* 17 */     super((EntityTypes)EntityTypes.EGG, entityliving, world);
/*    */   }
/*    */   
/*    */   public EntityEgg(World world, double d0, double d1, double d2) {
/* 21 */     super((EntityTypes)EntityTypes.EGG, d0, d1, d2, world);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(MovingObjectPositionEntity movingobjectpositionentity) {
/* 26 */     super.a(movingobjectpositionentity);
/* 27 */     movingobjectpositionentity.getEntity().damageEntity(DamageSource.projectile(this, getShooter()), 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(MovingObjectPosition movingobjectposition) {
/* 32 */     super.a(movingobjectposition);
/* 33 */     if (!this.world.isClientSide) {
/* 34 */       boolean hatching = (this.random.nextInt(8) == 0);
/*    */       
/* 36 */       byte b0 = 1;
/*    */       
/* 38 */       if (this.random.nextInt(32) == 0) {
/* 39 */         b0 = 4;
/*    */       }
/*    */ 
/*    */       
/* 43 */       if (!hatching) {
/* 44 */         b0 = 0;
/*    */       }
/* 46 */       EntityType hatchingType = EntityType.CHICKEN;
/*    */       
/* 48 */       Entity shooter = getShooter();
/* 49 */       if (shooter instanceof EntityPlayer) {
/* 50 */         PlayerEggThrowEvent playerEggThrowEvent = new PlayerEggThrowEvent((Player)shooter.getBukkitEntity(), (Egg)getBukkitEntity(), hatching, b0, hatchingType);
/* 51 */         this.world.getServer().getPluginManager().callEvent((Event)playerEggThrowEvent);
/*    */         
/* 53 */         b0 = playerEggThrowEvent.getNumHatches();
/* 54 */         hatching = playerEggThrowEvent.isHatching();
/* 55 */         hatchingType = playerEggThrowEvent.getHatchingType();
/*    */       } 
/*    */ 
/*    */       
/* 59 */       ThrownEggHatchEvent event = new ThrownEggHatchEvent((Egg)getBukkitEntity(), hatching, b0, hatchingType);
/* 60 */       event.callEvent();
/*    */       
/* 62 */       b0 = event.getNumHatches();
/* 63 */       hatching = event.isHatching();
/* 64 */       hatchingType = event.getHatchingType();
/*    */ 
/*    */ 
/*    */       
/* 68 */       if (hatching) {
/* 69 */         for (int i = 0; i < b0; i++) {
/* 70 */           Entity entity = this.world.getWorld().createEntity(new Location((World)this.world.getWorld(), locX(), locY(), locZ(), this.yaw, 0.0F), hatchingType.getEntityClass());
/* 71 */           if (entity.getBukkitEntity() instanceof Ageable) {
/* 72 */             ((Ageable)entity.getBukkitEntity()).setBaby();
/*    */           }
/* 74 */           this.world.getWorld().addEntity(entity, CreatureSpawnEvent.SpawnReason.EGG);
/*    */         } 
/*    */       }
/*    */ 
/*    */ 
/*    */       
/* 80 */       this.world.broadcastEntityEffect(this, (byte)3);
/* 81 */       die();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Item getDefaultItem() {
/* 88 */     return Items.EGG;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */