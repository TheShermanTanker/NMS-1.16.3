/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftHumanEntity;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ 
/*    */ public class PathfinderGoalTame
/*    */   extends PathfinderGoal
/*    */ {
/*    */   private final EntityHorseAbstract entity;
/*    */   private final double b;
/*    */   
/*    */   public PathfinderGoalTame(EntityHorseAbstract entityhorseabstract, double d0) {
/* 14 */     this.entity = entityhorseabstract;
/* 15 */     this.b = d0;
/* 16 */     a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*    */   }
/*    */   private double c; private double d; private double e;
/*    */   
/*    */   public boolean a() {
/* 21 */     if (!this.entity.isTamed() && this.entity.isVehicle()) {
/* 22 */       Vec3D vec3d = RandomPositionGenerator.a(this.entity, 5, 4);
/*    */       
/* 24 */       if (vec3d == null) {
/* 25 */         return false;
/*    */       }
/* 27 */       this.c = vec3d.x;
/* 28 */       this.d = vec3d.y;
/* 29 */       this.e = vec3d.z;
/* 30 */       return true;
/*    */     } 
/*    */     
/* 33 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void c() {
/* 39 */     this.entity.getNavigation().a(this.c, this.d, this.e, this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/* 44 */     return (!this.entity.isTamed() && !this.entity.getNavigation().m() && this.entity.isVehicle());
/*    */   }
/*    */ 
/*    */   
/*    */   public void e() {
/* 49 */     if (!this.entity.isTamed() && this.entity.getRandom().nextInt(50) == 0) {
/* 50 */       Entity entity = this.entity.getPassengers().get(0);
/*    */       
/* 52 */       if (entity == null) {
/*    */         return;
/*    */       }
/*    */       
/* 56 */       if (entity instanceof EntityHuman) {
/* 57 */         int i = this.entity.getTemper();
/* 58 */         int j = this.entity.getMaxDomestication();
/*    */ 
/*    */         
/* 61 */         if (j > 0 && this.entity.getRandom().nextInt(j) < i && !CraftEventFactory.callEntityTameEvent(this.entity, ((CraftHumanEntity)this.entity.getBukkitEntity().getPassenger()).getHandle()).isCancelled()) {
/* 62 */           this.entity.i((EntityHuman)entity);
/*    */           
/*    */           return;
/*    */         } 
/* 66 */         this.entity.v(5);
/*    */       } 
/*    */       
/* 69 */       this.entity.ejectPassengers();
/* 70 */       this.entity.fm();
/* 71 */       this.entity.world.broadcastEntityEffect(this.entity, (byte)6);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalTame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */