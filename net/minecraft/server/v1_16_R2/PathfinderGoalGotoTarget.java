/*     */ package net.minecraft.server.v1_16_R2;
/*     */ public abstract class PathfinderGoalGotoTarget extends PathfinderGoal {
/*     */   protected final EntityCreature a;
/*     */   public final double b;
/*     */   
/*     */   public EntityCreature getEntity() {
/*   7 */     return this.a;
/*     */   }
/*     */   protected int c; protected int d; private int g; protected BlockPosition e; private boolean h; private final int i; private final int j; protected int f;
/*     */   
/*     */   public final BlockPosition getTargetPosition() {
/*  12 */     return this.e; } public void setTargetPosition(BlockPosition pos) { this.e = pos; (getEntity()).movingTarget = (pos != BlockPosition.ZERO) ? pos : null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PathfinderGoalGotoTarget(EntityCreature entitycreature, double d0, int i) {
/*  19 */     this(entitycreature, d0, i, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onTaskReset() {
/*  24 */     super.onTaskReset();
/*  25 */     setTargetPosition(BlockPosition.ZERO);
/*     */   }
/*     */ 
/*     */   
/*     */   public PathfinderGoalGotoTarget(EntityCreature entitycreature, double d0, int i, int j) {
/*  30 */     this.e = BlockPosition.ZERO;
/*  31 */     this.a = entitycreature;
/*  32 */     this.b = d0;
/*  33 */     this.i = i;
/*  34 */     this.f = 0;
/*  35 */     this.j = j;
/*  36 */     a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.JUMP));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a() {
/*  41 */     if (this.c > 0) {
/*  42 */       this.c--;
/*  43 */       return false;
/*     */     } 
/*  45 */     this.c = a(this.a);
/*  46 */     return m();
/*     */   }
/*     */ 
/*     */   
/*     */   protected int a(EntityCreature entitycreature) {
/*  51 */     return 200 + entitycreature.getRandom().nextInt(200);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b() {
/*  56 */     return (this.d >= -this.g && this.d <= 1200 && a(this.a.world, this.e));
/*     */   }
/*     */ 
/*     */   
/*     */   public void c() {
/*  61 */     g();
/*  62 */     this.d = 0;
/*  63 */     this.g = this.a.getRandom().nextInt(this.a.getRandom().nextInt(1200) + 1200) + 1200;
/*     */   }
/*     */   
/*     */   protected void g() {
/*  67 */     this.a.getNavigation().a(this.e.getX() + 0.5D, (this.e.getY() + 1), this.e.getZ() + 0.5D, this.b);
/*     */   }
/*     */   
/*     */   public double h() {
/*  71 */     return 1.0D;
/*     */   }
/*     */   
/*     */   protected BlockPosition j() {
/*  75 */     return this.e.up();
/*     */   }
/*     */ 
/*     */   
/*     */   public void e() {
/*  80 */     BlockPosition blockposition = j();
/*     */     
/*  82 */     if (!blockposition.a(this.a.getPositionVector(), h())) {
/*  83 */       this.h = false;
/*  84 */       this.d++;
/*  85 */       if (k()) {
/*  86 */         this.a.getNavigation().a(blockposition.getX() + 0.5D, blockposition.getY(), blockposition.getZ() + 0.5D, this.b);
/*     */       }
/*     */     } else {
/*  89 */       this.h = true;
/*  90 */       this.d--;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean k() {
/*  96 */     return (this.d % 40 == 0);
/*     */   }
/*     */   
/*     */   protected boolean l() {
/* 100 */     return this.h;
/*     */   }
/*     */   
/*     */   protected boolean m() {
/* 104 */     int i = this.i;
/* 105 */     int j = this.j;
/* 106 */     BlockPosition blockposition = this.a.getChunkCoordinates();
/* 107 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/*     */     int k;
/* 109 */     for (k = this.f; k <= j; k = (k > 0) ? -k : (1 - k)) {
/* 110 */       for (int l = 0; l < i; l++) {
/* 111 */         int i1; for (i1 = 0; i1 <= l; i1 = (i1 > 0) ? -i1 : (1 - i1)) {
/* 112 */           int j1; for (j1 = (i1 < l && i1 > -l) ? l : 0; j1 <= l; j1 = (j1 > 0) ? -j1 : (1 - j1)) {
/* 113 */             blockposition_mutableblockposition.a(blockposition, i1, k - 1, j1);
/* 114 */             if (this.a.a(blockposition_mutableblockposition) && a(this.a.world, blockposition_mutableblockposition)) {
/* 115 */               this.e = blockposition_mutableblockposition;
/* 116 */               setTargetPosition(blockposition_mutableblockposition.immutableCopy());
/* 117 */               return true;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 124 */     return false;
/*     */   }
/*     */   
/*     */   protected abstract boolean a(IWorldReader paramIWorldReader, BlockPosition paramBlockPosition);
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalGotoTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */