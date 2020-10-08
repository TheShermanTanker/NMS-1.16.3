/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*    */ import org.bukkit.event.player.PlayerBucketFillEvent;
/*    */ 
/*    */ public class EntityCow
/*    */   extends EntityAnimal
/*    */ {
/*    */   public EntityCow(EntityTypes<? extends EntityCow> entitytypes, World world) {
/* 11 */     super((EntityTypes)entitytypes, world);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void initPathfinder() {
/* 16 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/* 17 */     this.goalSelector.a(1, new PathfinderGoalPanic(this, 2.0D));
/* 18 */     this.goalSelector.a(2, new PathfinderGoalBreed(this, 1.0D));
/* 19 */     this.goalSelector.a(3, new PathfinderGoalTempt(this, 1.25D, RecipeItemStack.a(new IMaterial[] { Items.WHEAT }, ), false));
/* 20 */     this.goalSelector.a(4, new PathfinderGoalFollowParent(this, 1.25D));
/* 21 */     this.goalSelector.a(5, new PathfinderGoalRandomStrollLand(this, 1.0D));
/* 22 */     this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 6.0F));
/* 23 */     this.goalSelector.a(7, new PathfinderGoalRandomLookaround(this));
/*    */   }
/*    */   
/*    */   public static AttributeProvider.Builder eK() {
/* 27 */     return EntityInsentient.p().a(GenericAttributes.MAX_HEALTH, 10.0D).a(GenericAttributes.MOVEMENT_SPEED, 0.20000000298023224D);
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundAmbient() {
/* 32 */     return SoundEffects.ENTITY_COW_AMBIENT;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/* 37 */     return SoundEffects.ENTITY_COW_HURT;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundDeath() {
/* 42 */     return SoundEffects.ENTITY_COW_DEATH;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void b(BlockPosition blockposition, IBlockData iblockdata) {
/* 47 */     playSound(SoundEffects.ENTITY_COW_STEP, 0.15F, 1.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected float getSoundVolume() {
/* 52 */     return 0.4F;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult b(EntityHuman entityhuman, EnumHand enumhand) {
/* 57 */     ItemStack itemstack = entityhuman.b(enumhand);
/*    */     
/* 59 */     if (itemstack.getItem() == Items.BUCKET && !isBaby()) {
/*    */       
/* 61 */       PlayerBucketFillEvent event = CraftEventFactory.callPlayerBucketFillEvent(entityhuman.world, entityhuman, getChunkCoordinates(), getChunkCoordinates(), null, itemstack, Items.MILK_BUCKET, enumhand);
/*    */       
/* 63 */       if (event.isCancelled()) {
/* 64 */         return EnumInteractionResult.PASS;
/*    */       }
/*    */ 
/*    */       
/* 68 */       entityhuman.playSound(SoundEffects.ENTITY_COW_MILK, 1.0F, 1.0F);
/* 69 */       ItemStack itemstack1 = ItemLiquidUtil.a(itemstack, entityhuman, CraftItemStack.asNMSCopy(event.getItemStack()));
/*    */       
/* 71 */       entityhuman.a(enumhand, itemstack1);
/* 72 */       return EnumInteractionResult.a(this.world.isClientSide);
/*    */     } 
/* 74 */     return super.b(entityhuman, enumhand);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EntityCow createChild(WorldServer worldserver, EntityAgeable entityageable) {
/* 80 */     return EntityTypes.COW.a(worldserver);
/*    */   }
/*    */ 
/*    */   
/*    */   protected float b(EntityPose entitypose, EntitySize entitysize) {
/* 85 */     return isBaby() ? (entitysize.height * 0.95F) : 1.3F;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityCow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */