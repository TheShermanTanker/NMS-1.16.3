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
/*     */ public class BehaviorBedJump
/*     */   extends Behavior<EntityInsentient>
/*     */ {
/*     */   private final float b;
/*     */   @Nullable
/*     */   private BlockPosition c;
/*     */   private int d;
/*     */   private int e;
/*     */   private int f;
/*     */   
/*     */   public BehaviorBedJump(float var0) {
/*  35 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.NEAREST_BED, MemoryStatus.VALUE_PRESENT, MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT));
/*     */ 
/*     */ 
/*     */     
/*  39 */     this.b = var0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean a(WorldServer var0, EntityInsentient var1) {
/*  44 */     return (var1.isBaby() && b(var0, var1));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(WorldServer var0, EntityInsentient var1, long var2) {
/*  49 */     super.a(var0, var1, var2);
/*     */     
/*  51 */     a(var1).ifPresent(var2 -> {
/*     */           this.c = var2;
/*     */           this.d = 100;
/*     */           this.e = 3 + var0.random.nextInt(4);
/*     */           this.f = 0;
/*     */           a(var1, var2);
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   protected void c(WorldServer var0, EntityInsentient var1, long var2) {
/*  62 */     super.c(var0, var1, var2);
/*     */     
/*  64 */     this.c = null;
/*  65 */     this.d = 0;
/*  66 */     this.e = 0;
/*  67 */     this.f = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean b(WorldServer var0, EntityInsentient var1, long var2) {
/*  72 */     return (var1.isBaby() && this.c != null && 
/*     */       
/*  74 */       a(var0, this.c) && 
/*  75 */       !e(var0, var1) && 
/*  76 */       !f(var0, var1));
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean a(long var0) {
/*  81 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void d(WorldServer var0, EntityInsentient var1, long var2) {
/*  86 */     if (!c(var0, var1)) {
/*  87 */       this.d--;
/*     */       
/*     */       return;
/*     */     } 
/*  91 */     if (this.f > 0) {
/*  92 */       this.f--;
/*     */       
/*     */       return;
/*     */     } 
/*  96 */     if (d(var0, var1)) {
/*  97 */       var1.getControllerJump().jump();
/*  98 */       this.e--;
/*  99 */       this.f = 5;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a(EntityInsentient var0, BlockPosition var1) {
/* 104 */     var0.getBehaviorController().setMemory(MemoryModuleType.WALK_TARGET, new MemoryTarget(var1, this.b, 0));
/*     */   }
/*     */   
/*     */   private boolean b(WorldServer var0, EntityInsentient var1) {
/* 108 */     return (c(var0, var1) || a(var1).isPresent());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean c(WorldServer var0, EntityInsentient var1) {
/* 115 */     BlockPosition var2 = var1.getChunkCoordinates();
/* 116 */     BlockPosition var3 = var2.down();
/* 117 */     return (a(var0, var2) || a(var0, var3));
/*     */   }
/*     */   
/*     */   private boolean d(WorldServer var0, EntityInsentient var1) {
/* 121 */     return a(var0, var1.getChunkCoordinates());
/*     */   }
/*     */   
/*     */   private boolean a(WorldServer var0, BlockPosition var1) {
/* 125 */     return var0.getType(var1).a(TagsBlock.BEDS);
/*     */   }
/*     */   
/*     */   private Optional<BlockPosition> a(EntityInsentient var0) {
/* 129 */     return var0.getBehaviorController().getMemory(MemoryModuleType.NEAREST_BED);
/*     */   }
/*     */   
/*     */   private boolean e(WorldServer var0, EntityInsentient var1) {
/* 133 */     return (!c(var0, var1) && this.d <= 0);
/*     */   }
/*     */   
/*     */   private boolean f(WorldServer var0, EntityInsentient var1) {
/* 137 */     return (c(var0, var1) && this.e <= 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorBedJump.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */