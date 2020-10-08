/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.bukkit.event.entity.EntityTargetEvent;
/*    */ 
/*    */ public class PathfinderGoalDefendVillage extends PathfinderGoalTarget {
/*    */   private final EntityIronGolem a;
/*    */   private EntityLiving b;
/* 11 */   private final PathfinderTargetCondition c = (new PathfinderTargetCondition()).a(64.0D);
/*    */   
/*    */   public PathfinderGoalDefendVillage(EntityIronGolem entityirongolem) {
/* 14 */     super(entityirongolem, false, true);
/* 15 */     this.a = entityirongolem;
/* 16 */     a(EnumSet.of(PathfinderGoal.Type.TARGET));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 21 */     AxisAlignedBB axisalignedbb = this.a.getBoundingBox().grow(10.0D, 8.0D, 10.0D);
/* 22 */     List<EntityLiving> list = this.a.world.a((Class)EntityVillager.class, this.c, this.a, axisalignedbb);
/* 23 */     List<EntityHuman> list1 = this.a.world.a(this.c, this.a, axisalignedbb);
/* 24 */     Iterator<EntityLiving> iterator = list.iterator();
/*    */     
/* 26 */     while (iterator.hasNext()) {
/* 27 */       EntityLiving entityliving = iterator.next();
/* 28 */       EntityVillager entityvillager = (EntityVillager)entityliving;
/* 29 */       Iterator<EntityHuman> iterator1 = list1.iterator();
/*    */       
/* 31 */       while (iterator1.hasNext()) {
/* 32 */         EntityHuman entityhuman = iterator1.next();
/* 33 */         int i = entityvillager.g(entityhuman);
/*    */         
/* 35 */         if (i <= -100) {
/* 36 */           this.b = entityhuman;
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 41 */     if (this.b == null)
/* 42 */       return false; 
/* 43 */     if (this.b instanceof EntityHuman && (this.b.isSpectator() || ((EntityHuman)this.b).isCreative())) {
/* 44 */       return false;
/*    */     }
/* 46 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void c() {
/* 52 */     this.a.setGoalTarget(this.b, EntityTargetEvent.TargetReason.DEFEND_VILLAGE, true);
/* 53 */     super.c();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalDefendVillage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */