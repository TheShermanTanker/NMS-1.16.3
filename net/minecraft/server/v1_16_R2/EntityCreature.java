/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftCreature;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityUnleashEvent;
/*    */ 
/*    */ public abstract class EntityCreature extends EntityInsentient {
/*    */   public CraftCreature getBukkitCreature() {
/*  9 */     return (CraftCreature)getBukkitEntity();
/* 10 */   } public BlockPosition movingTarget = null; public BlockPosition getMovingTarget() { return this.movingTarget; }
/*    */   
/*    */   protected EntityCreature(EntityTypes<? extends EntityCreature> entitytypes, World world) {
/* 13 */     super((EntityTypes)entitytypes, world);
/*    */   }
/*    */   
/*    */   public float f(BlockPosition blockposition) {
/* 17 */     return a(blockposition, this.world);
/*    */   }
/*    */   
/*    */   public float a(BlockPosition blockposition, IWorldReader iworldreader) {
/* 21 */     return 0.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccess generatoraccess, EnumMobSpawn enummobspawn) {
/* 26 */     return (a(getChunkCoordinates(), generatoraccess) >= 0.0F);
/*    */   }
/*    */   
/*    */   public boolean eI() {
/* 30 */     return !getNavigation().m();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void eA() {
/* 35 */     super.eA();
/* 36 */     Entity entity = getLeashHolder();
/*    */     
/* 38 */     if (entity != null && entity.world == this.world) {
/* 39 */       a(entity.getChunkCoordinates(), 5);
/* 40 */       float f = g(entity);
/*    */       
/* 42 */       if (this instanceof EntityTameableAnimal && ((EntityTameableAnimal)this).isSitting()) {
/* 43 */         if (f > 10.0F) {
/* 44 */           this.world.getServer().getPluginManager().callEvent((Event)new EntityUnleashEvent((Entity)getBukkitEntity(), EntityUnleashEvent.UnleashReason.DISTANCE));
/* 45 */           unleash(true, true);
/*    */         } 
/*    */         
/*    */         return;
/*    */       } 
/*    */       
/* 51 */       x(f);
/* 52 */       if (f > 10.0F) {
/* 53 */         this.world.getServer().getPluginManager().callEvent((Event)new EntityUnleashEvent((Entity)getBukkitEntity(), EntityUnleashEvent.UnleashReason.DISTANCE));
/* 54 */         unleash(true, true);
/* 55 */         this.goalSelector.a(PathfinderGoal.Type.MOVE);
/* 56 */       } else if (f > 6.0F) {
/* 57 */         double d0 = (entity.locX() - locX()) / f;
/* 58 */         double d1 = (entity.locY() - locY()) / f;
/* 59 */         double d2 = (entity.locZ() - locZ()) / f;
/*    */         
/* 61 */         setMot(getMot().add(Math.copySign(d0 * d0 * 0.4D, d0), Math.copySign(d1 * d1 * 0.4D, d1), Math.copySign(d2 * d2 * 0.4D, d2)));
/*    */       } else {
/* 63 */         this.goalSelector.b(PathfinderGoal.Type.MOVE);
/* 64 */         float f1 = 2.0F;
/* 65 */         Vec3D vec3d = (new Vec3D(entity.locX() - locX(), entity.locY() - locY(), entity.locZ() - locZ())).d().a(Math.max(f - 2.0F, 0.0F));
/*    */         
/* 67 */         getNavigation().a(locX() + vec3d.x, locY() + vec3d.y, locZ() + vec3d.z, eJ());
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected double eJ() {
/* 74 */     return 1.0D;
/*    */   }
/*    */   
/*    */   protected void x(float f) {}
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityCreature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */