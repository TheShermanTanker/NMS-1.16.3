/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class Sensor<E extends EntityLiving>
/*    */ {
/* 18 */   private static final Random a = new Random();
/*    */ 
/*    */ 
/*    */   
/* 22 */   private static final PathfinderTargetCondition b = (new PathfinderTargetCondition()).a(16.0D).b().d();
/* 23 */   private static final PathfinderTargetCondition c = (new PathfinderTargetCondition()).a(16.0D).b().d().e();
/*    */   
/*    */   private final int d;
/*    */   private long e;
/*    */   
/*    */   public Sensor(int var0) {
/* 29 */     this.d = var0;
/* 30 */     this.e = a.nextInt(var0);
/*    */   }
/*    */   
/*    */   public Sensor() {
/* 34 */     this(20);
/*    */   }
/*    */   
/*    */   public final void b(WorldServer var0, E var1) {
/* 38 */     if (--this.e <= 0L) {
/* 39 */       this.e = this.d;
/* 40 */       a(var0, var1);
/*    */     } 
/*    */   }
/*    */   
/*    */   protected abstract void a(WorldServer paramWorldServer, E paramE);
/*    */   
/*    */   public abstract Set<MemoryModuleType<?>> a();
/*    */   
/*    */   protected static boolean a(EntityLiving var0, EntityLiving var1) {
/* 49 */     if (var0.getBehaviorController().b(MemoryModuleType.ATTACK_TARGET, var1))
/*    */     {
/* 51 */       return c.a(var0, var1);
/*    */     }
/* 53 */     return b.a(var0, var1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Sensor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */