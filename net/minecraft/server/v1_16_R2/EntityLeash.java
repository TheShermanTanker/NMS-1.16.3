/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public class EntityLeash
/*     */   extends EntityHanging
/*     */ {
/*     */   public EntityLeash(EntityTypes<? extends EntityLeash> entitytypes, World world) {
/*  12 */     super((EntityTypes)entitytypes, world);
/*     */   }
/*     */   
/*     */   public EntityLeash(World world, BlockPosition blockposition) {
/*  16 */     super((EntityTypes)EntityTypes.LEASH_KNOT, world, blockposition);
/*  17 */     setPosition(blockposition.getX() + 0.5D, blockposition.getY() + 0.5D, blockposition.getZ() + 0.5D);
/*  18 */     float f = 0.125F;
/*  19 */     float f1 = 0.1875F;
/*  20 */     float f2 = 0.25F;
/*     */     
/*  22 */     a(new AxisAlignedBB(locX() - 0.1875D, locY() - 0.25D + 0.125D, locZ() - 0.1875D, locX() + 0.1875D, locY() + 0.25D + 0.125D, locZ() + 0.1875D));
/*  23 */     this.attachedToPlayer = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPosition(double d0, double d1, double d2) {
/*  28 */     super.setPosition(MathHelper.floor(d0) + 0.5D, MathHelper.floor(d1) + 0.5D, MathHelper.floor(d2) + 0.5D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateBoundingBox() {
/*  33 */     setPositionRaw(this.blockPosition.getX() + 0.5D, this.blockPosition.getY() + 0.5D, this.blockPosition.getZ() + 0.5D);
/*  34 */     if (this.valid) ((WorldServer)this.world).chunkCheck(this);
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDirection(EnumDirection enumdirection) {}
/*     */   
/*     */   public int getHangingWidth() {
/*  42 */     return 9;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHangingHeight() {
/*  47 */     return 9;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getHeadHeight(EntityPose entitypose, EntitySize entitysize) {
/*  52 */     return -0.0625F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(@Nullable Entity entity) {
/*  57 */     playSound(SoundEffects.ENTITY_LEASH_KNOT_BREAK, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {}
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {}
/*     */ 
/*     */   
/*     */   public EnumInteractionResult a(EntityHuman entityhuman, EnumHand enumhand) {
/*  68 */     if (this.world.isClientSide) {
/*  69 */       return EnumInteractionResult.SUCCESS;
/*     */     }
/*  71 */     boolean flag = false;
/*  72 */     double d0 = 7.0D;
/*  73 */     List<EntityInsentient> list = this.world.a(EntityInsentient.class, new AxisAlignedBB(locX() - 7.0D, locY() - 7.0D, locZ() - 7.0D, locX() + 7.0D, locY() + 7.0D, locZ() + 7.0D));
/*  74 */     Iterator<EntityInsentient> iterator = list.iterator();
/*     */ 
/*     */ 
/*     */     
/*  78 */     while (iterator.hasNext()) {
/*  79 */       EntityInsentient entityinsentient = iterator.next();
/*  80 */       if (entityinsentient.getLeashHolder() == entityhuman) {
/*     */         
/*  82 */         if (CraftEventFactory.callPlayerLeashEntityEvent(entityinsentient, this, entityhuman).isCancelled()) {
/*  83 */           ((EntityPlayer)entityhuman).playerConnection.sendPacket(new PacketPlayOutAttachEntity(entityinsentient, entityinsentient.getLeashHolder()));
/*     */           
/*     */           continue;
/*     */         } 
/*  87 */         entityinsentient.setLeashHolder(this, true);
/*  88 */         flag = true;
/*     */       } 
/*     */     } 
/*     */     
/*  92 */     if (!flag) {
/*     */ 
/*     */       
/*  95 */       boolean die = true;
/*     */ 
/*     */       
/*  98 */       iterator = list.iterator();
/*     */       
/* 100 */       while (iterator.hasNext()) {
/* 101 */         EntityInsentient entityinsentient = iterator.next();
/* 102 */         if (entityinsentient.isLeashed() && entityinsentient.getLeashHolder() == this) {
/*     */           
/* 104 */           if (CraftEventFactory.callPlayerUnleashEntityEvent(entityinsentient, entityhuman).isCancelled()) {
/* 105 */             die = false;
/*     */             continue;
/*     */           } 
/* 108 */           entityinsentient.unleash(true, !entityhuman.abilities.canInstantlyBuild);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 113 */       if (die) {
/* 114 */         die();
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 120 */     return EnumInteractionResult.CONSUME;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean survives() {
/* 126 */     return this.world.getType(this.blockPosition).getBlock().a(TagsBlock.FENCES);
/*     */   }
/*     */   public static EntityLeash a(World world, BlockPosition blockposition) {
/*     */     EntityLeash entityleash;
/* 130 */     int i = blockposition.getX();
/* 131 */     int j = blockposition.getY();
/* 132 */     int k = blockposition.getZ();
/* 133 */     List<EntityLeash> list = world.a(EntityLeash.class, new AxisAlignedBB(i - 1.0D, j - 1.0D, k - 1.0D, i + 1.0D, j + 1.0D, k + 1.0D));
/* 134 */     Iterator<EntityLeash> iterator = list.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 139 */       if (!iterator.hasNext()) {
/* 140 */         EntityLeash entityleash1 = new EntityLeash(world, blockposition);
/*     */         
/* 142 */         world.addEntity(entityleash1);
/* 143 */         entityleash1.playPlaceSound();
/* 144 */         return entityleash1;
/*     */       } 
/*     */       
/* 147 */       entityleash = iterator.next();
/* 148 */     } while (!entityleash.getBlockPosition().equals(blockposition));
/*     */     
/* 150 */     return entityleash;
/*     */   }
/*     */ 
/*     */   
/*     */   public void playPlaceSound() {
/* 155 */     playSound(SoundEffects.ENTITY_LEASH_KNOT_PLACE, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet<?> P() {
/* 160 */     return new PacketPlayOutSpawnEntity(this, getEntityType(), 0, getBlockPosition());
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityLeash.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */