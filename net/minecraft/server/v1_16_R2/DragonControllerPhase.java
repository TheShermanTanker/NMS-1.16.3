/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.lang.reflect.Constructor;
/*    */ import java.util.Arrays;
/*    */ 
/*    */ 
/*    */ public class DragonControllerPhase<T extends IDragonController>
/*    */ {
/*  9 */   private static DragonControllerPhase<?>[] l = (DragonControllerPhase<?>[])new DragonControllerPhase[0];
/* 10 */   public static final DragonControllerPhase<DragonControllerHold> HOLDING_PATTERN = a(DragonControllerHold.class, "HoldingPattern");
/* 11 */   public static final DragonControllerPhase<DragonControllerStrafe> STRAFE_PLAYER = a(DragonControllerStrafe.class, "StrafePlayer");
/* 12 */   public static final DragonControllerPhase<DragonControllerLandingFly> LANDING_APPROACH = a(DragonControllerLandingFly.class, "LandingApproach");
/* 13 */   public static final DragonControllerPhase<DragonControllerLanding> LANDING = a(DragonControllerLanding.class, "Landing");
/* 14 */   public static final DragonControllerPhase<DragonControllerFly> TAKEOFF = a(DragonControllerFly.class, "Takeoff");
/* 15 */   public static final DragonControllerPhase<DragonControllerLandedFlame> SITTING_FLAMING = a(DragonControllerLandedFlame.class, "SittingFlaming");
/* 16 */   public static final DragonControllerPhase<DragonControllerLandedSearch> SITTING_SCANNING = a(DragonControllerLandedSearch.class, "SittingScanning");
/* 17 */   public static final DragonControllerPhase<DragonControllerLandedAttack> SITTING_ATTACKING = a(DragonControllerLandedAttack.class, "SittingAttacking");
/* 18 */   public static final DragonControllerPhase<DragonControllerCharge> CHARGING_PLAYER = a(DragonControllerCharge.class, "ChargingPlayer");
/* 19 */   public static final DragonControllerPhase<DragonControllerDying> DYING = a(DragonControllerDying.class, "Dying");
/* 20 */   public static final DragonControllerPhase<DragonControllerHover> HOVER = a(DragonControllerHover.class, "Hover");
/*    */   
/*    */   private final Class<? extends IDragonController> m;
/*    */   private final int n;
/*    */   private final String o;
/*    */   
/*    */   private DragonControllerPhase(int var0, Class<? extends IDragonController> var1, String var2) {
/* 27 */     this.n = var0;
/* 28 */     this.m = var1;
/* 29 */     this.o = var2;
/*    */   }
/*    */   
/*    */   public IDragonController a(EntityEnderDragon var0) {
/*    */     try {
/* 34 */       Constructor<? extends IDragonController> var1 = a();
/* 35 */       return var1.newInstance(new Object[] { var0 });
/* 36 */     } catch (Exception var1) {
/* 37 */       throw new Error(var1);
/*    */     } 
/*    */   }
/*    */   
/*    */   protected Constructor<? extends IDragonController> a() throws NoSuchMethodException {
/* 42 */     return this.m.getConstructor(new Class[] { EntityEnderDragon.class });
/*    */   }
/*    */   
/*    */   public int b() {
/* 46 */     return this.n;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 51 */     return this.o + " (#" + this.n + ")";
/*    */   }
/*    */   
/*    */   public static DragonControllerPhase<?> getById(int var0) {
/* 55 */     if (var0 < 0 || var0 >= l.length) {
/* 56 */       return HOLDING_PATTERN;
/*    */     }
/* 58 */     return l[var0];
/*    */   }
/*    */   
/*    */   public static int c() {
/* 62 */     return l.length;
/*    */   }
/*    */   
/*    */   private static <T extends IDragonController> DragonControllerPhase<T> a(Class<T> var0, String var1) {
/* 66 */     DragonControllerPhase<T> var2 = new DragonControllerPhase<>(l.length, var0, var1);
/* 67 */     l = (DragonControllerPhase<?>[])Arrays.<DragonControllerPhase>copyOf((DragonControllerPhase[])l, l.length + 1);
/* 68 */     l[var2.b()] = var2;
/* 69 */     return var2;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DragonControllerPhase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */