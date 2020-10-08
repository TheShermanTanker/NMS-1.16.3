/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.destroystokyo.paper.event.entity.SkeletonHorseTrapEvent;
/*    */ import org.bukkit.entity.SkeletonHorse;
/*    */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*    */ 
/*    */ public class PathfinderGoalHorseTrap extends PathfinderGoal {
/*    */   public PathfinderGoalHorseTrap(EntityHorseSkeleton entityhorseskeleton) {
/*  8 */     this.a = entityhorseskeleton;
/*    */   }
/*    */   private final EntityHorseSkeleton a;
/*    */   
/*    */   public boolean a() {
/* 13 */     return this.a.world.isPlayerNearby(this.a.locX(), this.a.locY(), this.a.locZ(), 10.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public void e() {
/* 18 */     WorldServer worldserver = (WorldServer)this.a.world;
/* 19 */     if (!(new SkeletonHorseTrapEvent((SkeletonHorse)this.a.getBukkitEntity())).callEvent())
/* 20 */       return;  DifficultyDamageScaler difficultydamagescaler = worldserver.getDamageScaler(this.a.getChunkCoordinates());
/*    */     
/* 22 */     this.a.t(false);
/* 23 */     this.a.setTamed(true);
/* 24 */     this.a.setAgeRaw(0);
/* 25 */     EntityLightning entitylightning = EntityTypes.LIGHTNING_BOLT.a(worldserver);
/*    */     
/* 27 */     entitylightning.teleportAndSync(this.a.locX(), this.a.locY(), this.a.locZ());
/* 28 */     entitylightning.setEffect(true);
/* 29 */     worldserver.strikeLightning(entitylightning, LightningStrikeEvent.Cause.TRAP);
/* 30 */     EntitySkeleton entityskeleton = a(difficultydamagescaler, this.a);
/*    */     
/* 32 */     if (entityskeleton != null) entityskeleton.startRiding(this.a); 
/* 33 */     worldserver.addAllEntities(entityskeleton, CreatureSpawnEvent.SpawnReason.TRAP);
/*    */     
/* 35 */     for (int i = 0; i < 3; i++) {
/* 36 */       EntityHorseAbstract entityhorseabstract = a(difficultydamagescaler);
/* 37 */       if (entityhorseabstract != null) {
/* 38 */         EntitySkeleton entityskeleton1 = a(difficultydamagescaler, entityhorseabstract);
/*    */         
/* 40 */         if (entityskeleton1 != null) entityskeleton1.startRiding(entityhorseabstract); 
/* 41 */         entityhorseabstract.i(this.a.getRandom().nextGaussian() * 0.5D, 0.0D, this.a.getRandom().nextGaussian() * 0.5D);
/* 42 */         worldserver.addAllEntities(entityhorseabstract, CreatureSpawnEvent.SpawnReason.JOCKEY);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private EntityHorseAbstract a(DifficultyDamageScaler difficultydamagescaler) {
/* 48 */     EntityHorseSkeleton entityhorseskeleton = EntityTypes.SKELETON_HORSE.a(this.a.world);
/*    */     
/* 50 */     entityhorseskeleton.prepare((WorldServer)this.a.world, difficultydamagescaler, EnumMobSpawn.TRIGGERED, (GroupDataEntity)null, (NBTTagCompound)null);
/* 51 */     entityhorseskeleton.setPosition(this.a.locX(), this.a.locY(), this.a.locZ());
/* 52 */     entityhorseskeleton.noDamageTicks = 60;
/* 53 */     entityhorseskeleton.setPersistent();
/* 54 */     entityhorseskeleton.setTamed(true);
/* 55 */     entityhorseskeleton.setAgeRaw(0);
/* 56 */     return entityhorseskeleton;
/*    */   }
/*    */   
/*    */   private EntitySkeleton a(DifficultyDamageScaler difficultydamagescaler, EntityHorseAbstract entityhorseabstract) {
/* 60 */     EntitySkeleton entityskeleton = EntityTypes.SKELETON.a(entityhorseabstract.world);
/*    */     
/* 62 */     entityskeleton.prepare((WorldServer)entityhorseabstract.world, difficultydamagescaler, EnumMobSpawn.TRIGGERED, (GroupDataEntity)null, (NBTTagCompound)null);
/* 63 */     entityskeleton.setPosition(entityhorseabstract.locX(), entityhorseabstract.locY(), entityhorseabstract.locZ());
/* 64 */     entityskeleton.noDamageTicks = 60;
/* 65 */     entityskeleton.setPersistent();
/* 66 */     if (entityskeleton.getEquipment(EnumItemSlot.HEAD).isEmpty()) {
/* 67 */       entityskeleton.setSlot(EnumItemSlot.HEAD, new ItemStack(Items.IRON_HELMET));
/*    */     }
/*    */     
/* 70 */     entityskeleton.setSlot(EnumItemSlot.MAINHAND, EnchantmentManager.a(entityskeleton.getRandom(), a(entityskeleton.getItemInMainHand()), (int)(5.0F + difficultydamagescaler.d() * entityskeleton.getRandom().nextInt(18)), false));
/* 71 */     entityskeleton.setSlot(EnumItemSlot.HEAD, EnchantmentManager.a(entityskeleton.getRandom(), a(entityskeleton.getEquipment(EnumItemSlot.HEAD)), (int)(5.0F + difficultydamagescaler.d() * entityskeleton.getRandom().nextInt(18)), false));
/* 72 */     return entityskeleton;
/*    */   }
/*    */   
/*    */   private ItemStack a(ItemStack itemstack) {
/* 76 */     itemstack.removeTag("Enchantments");
/* 77 */     return itemstack;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalHorseTrap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */