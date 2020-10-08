/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BehaviorCrossbowAttack<E extends EntityInsentient & ICrossbow, T extends EntityLiving>
/*     */   extends Behavior<E>
/*     */ {
/*     */   private int b;
/*     */   
/*     */   enum BowState
/*     */   {
/*  24 */     UNCHARGED,
/*  25 */     CHARGING,
/*  26 */     CHARGED,
/*  27 */     READY_TO_ATTACK;
/*     */   }
/*     */   
/*  30 */   private BowState c = BowState.UNCHARGED;
/*     */   
/*     */   public BehaviorCrossbowAttack() {
/*  33 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_PRESENT), 1200);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean a(WorldServer var0, E var1) {
/*  41 */     EntityLiving var2 = a((EntityLiving)var1);
/*  42 */     return (var1.a(Items.CROSSBOW) && BehaviorUtil.c((EntityLiving)var1, var2) && BehaviorUtil.a((EntityInsentient)var1, var2, 0));
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean b(WorldServer var0, E var1, long var2) {
/*  47 */     return (var1.getBehaviorController().hasMemory(MemoryModuleType.ATTACK_TARGET) && a(var0, var1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void d(WorldServer var0, E var1, long var2) {
/*  53 */     EntityLiving var4 = a((EntityLiving)var1);
/*  54 */     b((EntityInsentient)var1, var4);
/*  55 */     a(var1, var4);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void c(WorldServer var0, E var1, long var2) {
/*  60 */     if (var1.isHandRaised()) {
/*  61 */       var1.clearActiveItem();
/*     */     }
/*  63 */     if (var1.a(Items.CROSSBOW)) {
/*  64 */       ((ICrossbow)var1).b(false);
/*  65 */       ItemCrossbow.a(var1.getActiveItem(), false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(E var0, EntityLiving var1) {
/*  71 */     if (this.c == BowState.UNCHARGED) {
/*  72 */       var0.c(ProjectileHelper.a((EntityLiving)var0, Items.CROSSBOW));
/*  73 */       this.c = BowState.CHARGING;
/*  74 */       ((ICrossbow)var0).b(true);
/*     */     }
/*  76 */     else if (this.c == BowState.CHARGING) {
/*  77 */       if (!var0.isHandRaised()) {
/*  78 */         this.c = BowState.UNCHARGED;
/*     */       }
/*  80 */       int var2 = var0.dZ();
/*  81 */       ItemStack var3 = var0.getActiveItem();
/*  82 */       if (var2 >= ItemCrossbow.g(var3)) {
/*  83 */         var0.releaseActiveItem();
/*  84 */         this.c = BowState.CHARGED;
/*  85 */         this.b = 20 + var0.getRandom().nextInt(20);
/*  86 */         ((ICrossbow)var0).b(false);
/*     */       }
/*     */     
/*  89 */     } else if (this.c == BowState.CHARGED) {
/*  90 */       this.b--;
/*  91 */       if (this.b == 0) {
/*  92 */         this.c = BowState.READY_TO_ATTACK;
/*     */       }
/*     */     }
/*  95 */     else if (this.c == BowState.READY_TO_ATTACK) {
/*  96 */       ((IRangedEntity)var0).a(var1, 1.0F);
/*     */       
/*  98 */       ItemStack var2 = var0.b(ProjectileHelper.a((EntityLiving)var0, Items.CROSSBOW));
/*  99 */       ItemCrossbow.a(var2, false);
/* 100 */       this.c = BowState.UNCHARGED;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void b(EntityInsentient var0, EntityLiving var1) {
/* 105 */     var0.getBehaviorController().setMemory(MemoryModuleType.LOOK_TARGET, new BehaviorPositionEntity(var1, true));
/*     */   }
/*     */   
/*     */   private static EntityLiving a(EntityLiving var0) {
/* 109 */     return var0.getBehaviorController().<EntityLiving>getMemory(MemoryModuleType.ATTACK_TARGET).get();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorCrossbowAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */