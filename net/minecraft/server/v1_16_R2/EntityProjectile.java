/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.destroystokyo.paper.event.entity.ProjectileCollideEvent;
/*    */ 
/*    */ public abstract class EntityProjectile extends IProjectile {
/*    */   protected EntityProjectile(EntityTypes<? extends EntityProjectile> entitytypes, World world) {
/*  6 */     super((EntityTypes)entitytypes, world);
/*    */   }
/*    */   
/*    */   protected EntityProjectile(EntityTypes<? extends EntityProjectile> entitytypes, double d0, double d1, double d2, World world) {
/* 10 */     this(entitytypes, world);
/* 11 */     setPosition(d0, d1, d2);
/*    */   }
/*    */   
/*    */   protected EntityProjectile(EntityTypes<? extends EntityProjectile> entitytypes, EntityLiving entityliving, World world) {
/* 15 */     this(entitytypes, entityliving.locX(), entityliving.getHeadY() - 0.10000000149011612D, entityliving.locZ(), world);
/* 16 */     setShooter(entityliving);
/*    */   }
/*    */   
/*    */   public void tick() {
/*    */     float f;
/* 21 */     super.tick();
/* 22 */     MovingObjectPosition movingobjectposition = ProjectileHelper.a(this, this::a);
/* 23 */     boolean flag = false;
/*    */     
/* 25 */     if (movingobjectposition.getType() == MovingObjectPosition.EnumMovingObjectType.BLOCK) {
/* 26 */       BlockPosition blockposition = ((MovingObjectPositionBlock)movingobjectposition).getBlockPosition();
/* 27 */       IBlockData iblockdata = this.world.getType(blockposition);
/*    */       
/* 29 */       if (iblockdata.a(Blocks.NETHER_PORTAL)) {
/* 30 */         d(blockposition);
/* 31 */         flag = true;
/* 32 */       } else if (iblockdata.a(Blocks.END_GATEWAY)) {
/* 33 */         TileEntity tileentity = this.world.getTileEntity(blockposition);
/*    */         
/* 35 */         if (tileentity instanceof TileEntityEndGateway && TileEntityEndGateway.a(this)) {
/* 36 */           ((TileEntityEndGateway)tileentity).b(this);
/*    */         }
/*    */         
/* 39 */         flag = true;
/*    */       } 
/*    */     } 
/*    */     
/* 43 */     if (movingobjectposition.getType() != MovingObjectPosition.EnumMovingObjectType.MISS && !flag) {
/*    */       
/* 45 */       if (movingobjectposition instanceof MovingObjectPositionEntity) {
/* 46 */         ProjectileCollideEvent event = CraftEventFactory.callProjectileCollideEvent(this, (MovingObjectPositionEntity)movingobjectposition);
/* 47 */         if (event.isCancelled()) {
/* 48 */           movingobjectposition = null;
/*    */         }
/*    */       } 
/* 51 */       if (movingobjectposition != null)
/*    */       {
/* 53 */         a(movingobjectposition);
/*    */       }
/*    */     } 
/*    */     
/* 57 */     checkBlockCollisions();
/* 58 */     Vec3D vec3d = getMot();
/* 59 */     double d0 = locX() + vec3d.x;
/* 60 */     double d1 = locY() + vec3d.y;
/* 61 */     double d2 = locZ() + vec3d.z;
/*    */     
/* 63 */     x();
/*    */ 
/*    */     
/* 66 */     if (isInWater()) {
/* 67 */       for (int i = 0; i < 4; i++) {
/* 68 */         float f1 = 0.25F;
/*    */         
/* 70 */         this.world.addParticle(Particles.BUBBLE, d0 - vec3d.x * 0.25D, d1 - vec3d.y * 0.25D, d2 - vec3d.z * 0.25D, vec3d.x, vec3d.y, vec3d.z);
/*    */       } 
/*    */       
/* 73 */       f = 0.8F;
/*    */     } else {
/* 75 */       f = 0.99F;
/*    */     } 
/*    */     
/* 78 */     setMot(vec3d.a(f));
/* 79 */     if (!isNoGravity()) {
/* 80 */       Vec3D vec3d1 = getMot();
/*    */       
/* 82 */       setMot(vec3d1.x, vec3d1.y - k(), vec3d1.z);
/*    */     } 
/*    */     
/* 85 */     setPosition(d0, d1, d2);
/*    */   }
/*    */   
/*    */   protected float k() {
/* 89 */     return 0.03F;
/*    */   }
/*    */ 
/*    */   
/*    */   public Packet<?> P() {
/* 94 */     return new PacketPlayOutSpawnEntity(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */