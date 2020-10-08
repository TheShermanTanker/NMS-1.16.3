/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.bukkit.event.entity.EntityTargetEvent;
/*     */ 
/*     */ public class PathfinderGoalHurtByTarget extends PathfinderGoalTarget {
/*   9 */   private static final PathfinderTargetCondition a = (new PathfinderTargetCondition()).c().e();
/*     */   private boolean b;
/*     */   private int c;
/*     */   private final Class<?>[] d;
/*     */   private Class<?>[] i;
/*     */   
/*     */   public PathfinderGoalHurtByTarget(EntityCreature entitycreature, Class<?>... aclass) {
/*  16 */     super(entitycreature, true);
/*  17 */     this.d = aclass;
/*  18 */     a(EnumSet.of(PathfinderGoal.Type.TARGET));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a() {
/*  23 */     int i = this.e.cZ();
/*  24 */     EntityLiving entityliving = this.e.getLastDamager();
/*     */     
/*  26 */     if (i != this.c && entityliving != null) {
/*  27 */       if (entityliving.getEntityType() == EntityTypes.PLAYER && this.e.world.getGameRules().getBoolean(GameRules.UNIVERSAL_ANGER)) {
/*  28 */         return false;
/*     */       }
/*  30 */       Class[] aclass = this.d;
/*  31 */       int j = aclass.length;
/*     */       
/*  33 */       for (int k = 0; k < j; k++) {
/*  34 */         Class<?> oclass = aclass[k];
/*     */         
/*  36 */         if (oclass.isAssignableFrom(entityliving.getClass())) {
/*  37 */           return false;
/*     */         }
/*     */       } 
/*     */       
/*  41 */       return a(entityliving, a);
/*     */     } 
/*     */     
/*  44 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public PathfinderGoalHurtByTarget a(Class<?>... aclass) {
/*  49 */     this.b = true;
/*  50 */     this.i = aclass;
/*  51 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void c() {
/*  56 */     this.e.setGoalTarget(this.e.getLastDamager(), EntityTargetEvent.TargetReason.TARGET_ATTACKED_ENTITY, true);
/*  57 */     this.g = this.e.getGoalTarget();
/*  58 */     this.c = this.e.cZ();
/*  59 */     this.h = 300;
/*  60 */     if (this.b) {
/*  61 */       g();
/*     */     }
/*     */     
/*  64 */     super.c();
/*     */   }
/*     */   
/*     */   protected void g() {
/*  68 */     double d0 = k();
/*  69 */     AxisAlignedBB axisalignedbb = AxisAlignedBB.a(this.e.getPositionVector()).grow(d0, 10.0D, d0);
/*  70 */     List<EntityInsentient> list = (List)this.e.world.b(this.e.getClass(), axisalignedbb);
/*  71 */     Iterator<EntityInsentient> iterator = list.iterator();
/*     */     
/*  73 */     while (iterator.hasNext()) {
/*  74 */       EntityInsentient entityinsentient = iterator.next();
/*     */       
/*  76 */       if (this.e != entityinsentient && entityinsentient.getGoalTarget() == null && (!(this.e instanceof EntityTameableAnimal) || ((EntityTameableAnimal)this.e).getOwner() == ((EntityTameableAnimal)entityinsentient).getOwner()) && !entityinsentient.r(this.e.getLastDamager())) {
/*  77 */         if (this.i != null) {
/*  78 */           boolean flag = false;
/*  79 */           Class[] aclass = this.i;
/*  80 */           int i = aclass.length;
/*     */           
/*  82 */           for (int j = 0; j < i; j++) {
/*  83 */             Class<?> oclass = aclass[j];
/*     */             
/*  85 */             if (entityinsentient.getClass() == oclass) {
/*  86 */               flag = true;
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*  91 */           if (flag) {
/*     */             continue;
/*     */           }
/*     */         } 
/*     */         
/*  96 */         a(entityinsentient, this.e.getLastDamager());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(EntityInsentient entityinsentient, EntityLiving entityliving) {
/* 103 */     entityinsentient.setGoalTarget(entityliving, EntityTargetEvent.TargetReason.TARGET_ATTACKED_NEARBY_ENTITY, true);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalHurtByTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */