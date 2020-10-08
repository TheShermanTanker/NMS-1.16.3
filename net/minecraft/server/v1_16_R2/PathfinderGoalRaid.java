/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Sets;
/*    */ import java.util.EnumSet;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalRaid<T extends EntityRaider>
/*    */   extends PathfinderGoal
/*    */ {
/*    */   private final T a;
/*    */   
/*    */   public PathfinderGoalRaid(T var0) {
/* 21 */     this.a = var0;
/* 22 */     a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 27 */     return (this.a.getGoalTarget() == null && 
/* 28 */       !this.a.isVehicle() && this.a
/* 29 */       .fb() && 
/* 30 */       !this.a.fa().a() && 
/* 31 */       !((WorldServer)((EntityRaider)this.a).world).a_(this.a.getChunkCoordinates()));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/* 36 */     return (this.a.fb() && 
/* 37 */       !this.a.fa().a() && ((EntityRaider)this.a).world instanceof WorldServer && 
/*    */       
/* 39 */       !((WorldServer)((EntityRaider)this.a).world).a_(this.a.getChunkCoordinates()));
/*    */   }
/*    */ 
/*    */   
/*    */   public void e() {
/* 44 */     if (this.a.fb()) {
/* 45 */       Raid var0 = this.a.fa();
/* 46 */       if (((EntityRaider)this.a).ticksLived % 20 == 0) {
/* 47 */         a(var0);
/*    */       }
/*    */       
/* 50 */       if (!this.a.eI()) {
/* 51 */         Vec3D var1 = RandomPositionGenerator.b((EntityCreature)this.a, 15, 4, Vec3D.c(var0.getCenter()));
/* 52 */         if (var1 != null) {
/* 53 */           this.a.getNavigation().a(var1.x, var1.y, var1.z, 1.0D);
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private void a(Raid var0) {
/* 60 */     if (var0.v()) {
/* 61 */       Set<EntityRaider> var1 = Sets.newHashSet();
/*    */       
/* 63 */       List<EntityRaider> var2 = ((EntityRaider)this.a).world.a(EntityRaider.class, this.a.getBoundingBox().g(16.0D), var1 -> (!var1.fb() && PersistentRaid.a(var1, var0)));
/* 64 */       var1.addAll(var2);
/*    */       
/* 66 */       for (EntityRaider var4 : var1)
/* 67 */         var0.a(var0.getGroupsSpawned(), var4, null, true); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalRaid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */