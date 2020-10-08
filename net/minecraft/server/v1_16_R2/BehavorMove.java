/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import javax.annotation.Nullable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BehavorMove
/*     */   extends Behavior<EntityInsentient>
/*     */ {
/*     */   private int b;
/*     */   @Nullable
/*     */   private PathEntity c;
/*     */   @Nullable
/*     */   private BlockPosition d;
/*     */   private float e;
/*     */   
/*     */   public BehavorMove() {
/*  39 */     this(150, 250);
/*     */   }
/*     */   
/*     */   public BehavorMove(int var0, int var1) {
/*  43 */     super(
/*  44 */         (Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryStatus.REGISTERED, MemoryModuleType.PATH, MemoryStatus.VALUE_ABSENT, MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_PRESENT), var0, var1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean a(WorldServer var0, EntityInsentient var1) {
/*  55 */     if (this.b > 0) {
/*  56 */       this.b--;
/*  57 */       return false;
/*     */     } 
/*     */     
/*  60 */     BehaviorController<?> var2 = var1.getBehaviorController();
/*  61 */     MemoryTarget var3 = var2.<MemoryTarget>getMemory(MemoryModuleType.WALK_TARGET).get();
/*     */     
/*  63 */     boolean var4 = a(var1, var3);
/*  64 */     if (!var4 && a(var1, var3, var0.getTime())) {
/*  65 */       this.d = var3.a().b();
/*  66 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  71 */     var2.removeMemory(MemoryModuleType.WALK_TARGET);
/*  72 */     if (var4) {
/*  73 */       var2.removeMemory(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE);
/*     */     }
/*  75 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean b(WorldServer var0, EntityInsentient var1, long var2) {
/*  80 */     if (this.c == null || this.d == null) {
/*  81 */       return false;
/*     */     }
/*     */     
/*  84 */     Optional<MemoryTarget> var4 = var1.getBehaviorController().getMemory(MemoryModuleType.WALK_TARGET);
/*  85 */     NavigationAbstract var5 = var1.getNavigation();
/*  86 */     return (!var5.m() && var4.isPresent() && !a(var1, var4.get()));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void c(WorldServer var0, EntityInsentient var1, long var2) {
/*  91 */     if (var1.getBehaviorController().hasMemory(MemoryModuleType.WALK_TARGET) && !a(var1, var1.getBehaviorController().<MemoryTarget>getMemory(MemoryModuleType.WALK_TARGET).get()) && var1.getNavigation().t())
/*     */     {
/*  93 */       this.b = var0.getRandom().nextInt(40);
/*     */     }
/*     */     
/*  96 */     var1.getNavigation().o();
/*  97 */     var1.getBehaviorController().removeMemory(MemoryModuleType.WALK_TARGET);
/*  98 */     var1.getBehaviorController().removeMemory(MemoryModuleType.PATH);
/*  99 */     this.c = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(WorldServer var0, EntityInsentient var1, long var2) {
/* 104 */     var1.getBehaviorController().setMemory(MemoryModuleType.PATH, this.c);
/* 105 */     var1.getNavigation().a(this.c, this.e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void d(WorldServer var0, EntityInsentient var1, long var2) {
/* 111 */     PathEntity var4 = var1.getNavigation().k();
/* 112 */     BehaviorController<?> var5 = var1.getBehaviorController();
/* 113 */     if (this.c != var4) {
/* 114 */       this.c = var4;
/* 115 */       var5.setMemory(MemoryModuleType.PATH, var4);
/*     */     } 
/*     */     
/* 118 */     if (var4 == null || this.d == null) {
/*     */       return;
/*     */     }
/*     */     
/* 122 */     MemoryTarget var6 = var5.<MemoryTarget>getMemory(MemoryModuleType.WALK_TARGET).get();
/*     */     
/* 124 */     if (var6.a().b().j(this.d) > 4.0D && 
/* 125 */       a(var1, var6, var0.getTime())) {
/* 126 */       this.d = var6.a().b();
/* 127 */       a(var0, var1, var2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean a(EntityInsentient var0, MemoryTarget var1, long var2) {
/* 134 */     BlockPosition var4 = var1.a().b();
/* 135 */     this.c = var0.getNavigation().a(var4, 0);
/* 136 */     this.e = var1.b();
/*     */     
/* 138 */     BehaviorController<?> var5 = var0.getBehaviorController();
/* 139 */     if (a(var0, var1)) {
/* 140 */       var5.removeMemory(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE);
/*     */     } else {
/* 142 */       boolean var6 = (this.c != null && this.c.j());
/* 143 */       if (var6) {
/* 144 */         var5.removeMemory(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE);
/* 145 */       } else if (!var5.hasMemory(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE)) {
/* 146 */         var5.setMemory(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, Long.valueOf(var2));
/*     */       } 
/*     */       
/* 149 */       if (this.c != null) {
/* 150 */         return true;
/*     */       }
/*     */       
/* 153 */       Vec3D var7 = RandomPositionGenerator.b((EntityCreature)var0, 10, 7, Vec3D.c(var4));
/* 154 */       if (var7 != null) {
/* 155 */         this.c = var0.getNavigation().a(var7.x, var7.y, var7.z, 0);
/* 156 */         return (this.c != null);
/*     */       } 
/*     */     } 
/* 159 */     return false;
/*     */   }
/*     */   
/*     */   private boolean a(EntityInsentient var0, MemoryTarget var1) {
/* 163 */     return (var1.a().b().k(var0.getChunkCoordinates()) <= var1.c());
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehavorMove.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */