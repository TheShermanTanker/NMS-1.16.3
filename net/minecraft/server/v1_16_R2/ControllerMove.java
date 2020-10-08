/*     */ package net.minecraft.server.v1_16_R2;public class ControllerMove {
/*     */   protected final EntityInsentient a;
/*     */   
/*     */   public final EntityInsentient getEntity() {
/*   5 */     return this.a;
/*     */   }
/*     */   protected double b; protected double c;
/*     */   protected double d;
/*     */   protected double e;
/*     */   protected float f;
/*     */   protected float g;
/*     */   protected Operation h;
/*     */   
/*     */   public ControllerMove(EntityInsentient entityinsentient) {
/*  15 */     this.h = Operation.WAIT;
/*  16 */     this.a = entityinsentient;
/*     */   }
/*     */   
/*     */   public boolean b() {
/*  20 */     return (this.h == Operation.MOVE_TO);
/*     */   }
/*     */   
/*     */   public double c() {
/*  24 */     return this.e;
/*     */   }
/*     */   
/*     */   public void a(double d0, double d1, double d2, double d3) {
/*  28 */     this.b = d0;
/*  29 */     this.c = d1;
/*  30 */     this.d = d2;
/*  31 */     this.e = d3;
/*  32 */     if (this.h != Operation.JUMPING) {
/*  33 */       this.h = Operation.MOVE_TO;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(float f, float f1) {
/*  39 */     this.h = Operation.STRAFE;
/*  40 */     this.f = f;
/*  41 */     this.g = f1;
/*  42 */     this.e = 0.25D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a() {
/*  48 */     if (this.h == Operation.STRAFE) {
/*  49 */       float f1 = (float)this.a.b(GenericAttributes.MOVEMENT_SPEED);
/*  50 */       float f2 = (float)this.e * f1;
/*  51 */       float f3 = this.f;
/*  52 */       float f4 = this.g;
/*  53 */       float f5 = MathHelper.c(f3 * f3 + f4 * f4);
/*     */       
/*  55 */       if (f5 < 1.0F) {
/*  56 */         f5 = 1.0F;
/*     */       }
/*     */       
/*  59 */       f5 = f2 / f5;
/*  60 */       f3 *= f5;
/*  61 */       f4 *= f5;
/*  62 */       float f6 = MathHelper.sin(this.a.yaw * 0.017453292F);
/*  63 */       float f7 = MathHelper.cos(this.a.yaw * 0.017453292F);
/*  64 */       float f8 = f3 * f7 - f4 * f6;
/*     */       
/*  66 */       float f = f4 * f7 + f3 * f6;
/*  67 */       if (!b(f8, f)) {
/*  68 */         this.f = 1.0F;
/*  69 */         this.g = 0.0F;
/*     */       } 
/*     */       
/*  72 */       this.a.q(f2);
/*  73 */       this.a.t(this.f);
/*  74 */       this.a.v(this.g);
/*  75 */       this.h = Operation.WAIT;
/*  76 */     } else if (this.h == Operation.MOVE_TO) {
/*  77 */       this.h = Operation.WAIT;
/*  78 */       double d0 = this.b - this.a.locX();
/*  79 */       double d1 = this.d - this.a.locZ();
/*  80 */       double d2 = this.c - this.a.locY();
/*  81 */       double d3 = d0 * d0 + d2 * d2 + d1 * d1;
/*     */       
/*  83 */       if (d3 < 2.500000277905201E-7D) {
/*  84 */         this.a.t(0.0F);
/*     */         
/*     */         return;
/*     */       } 
/*  88 */       float f = (float)(MathHelper.d(d1, d0) * 57.2957763671875D) - 90.0F;
/*  89 */       this.a.yaw = a(this.a.yaw, f, 90.0F);
/*  90 */       this.a.q((float)(this.e * this.a.b(GenericAttributes.MOVEMENT_SPEED)));
/*  91 */       BlockPosition blockposition = this.a.getChunkCoordinates();
/*  92 */       IBlockData iblockdata = this.a.world.getType(blockposition);
/*  93 */       Block block = iblockdata.getBlock();
/*  94 */       VoxelShape voxelshape = iblockdata.getCollisionShape(this.a.world, blockposition);
/*     */       
/*  96 */       if ((d2 > this.a.G && d0 * d0 + d1 * d1 < Math.max(1.0F, this.a.getWidth())) || (!voxelshape.isEmpty() && this.a.locY() < voxelshape.c(EnumDirection.EnumAxis.Y) + blockposition.getY() && !block.a(TagsBlock.DOORS) && !block.a(TagsBlock.FENCES))) {
/*  97 */         this.a.getControllerJump().jump();
/*  98 */         this.h = Operation.JUMPING;
/*     */       } 
/* 100 */     } else if (this.h == Operation.JUMPING) {
/* 101 */       this.a.q((float)(this.e * this.a.b(GenericAttributes.MOVEMENT_SPEED)));
/* 102 */       if (this.a.isOnGround()) {
/* 103 */         this.h = Operation.WAIT;
/*     */       }
/*     */     } else {
/* 106 */       this.a.t(0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean b(float f, float f1) {
/* 112 */     NavigationAbstract navigationabstract = this.a.getNavigation();
/*     */     
/* 114 */     if (navigationabstract != null) {
/* 115 */       PathfinderAbstract pathfinderabstract = navigationabstract.q();
/*     */       
/* 117 */       if (pathfinderabstract != null && pathfinderabstract.a(this.a.world, MathHelper.floor(this.a.locX() + f), MathHelper.floor(this.a.locY()), MathHelper.floor(this.a.locZ() + f1)) != PathType.WALKABLE) {
/* 118 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 122 */     return true;
/*     */   }
/*     */   
/*     */   protected float a(float f, float f1, float f2) {
/* 126 */     float f3 = MathHelper.g(f1 - f);
/*     */     
/* 128 */     if (f3 > f2) {
/* 129 */       f3 = f2;
/*     */     }
/*     */     
/* 132 */     if (f3 < -f2) {
/* 133 */       f3 = -f2;
/*     */     }
/*     */     
/* 136 */     float f4 = f + f3;
/*     */     
/* 138 */     if (f4 < 0.0F) {
/* 139 */       f4 += 360.0F;
/* 140 */     } else if (f4 > 360.0F) {
/* 141 */       f4 -= 360.0F;
/*     */     } 
/*     */     
/* 144 */     return f4;
/*     */   }
/*     */   
/*     */   public double d() {
/* 148 */     return this.b;
/*     */   }
/*     */   
/*     */   public double e() {
/* 152 */     return this.c;
/*     */   }
/*     */   
/*     */   public double f() {
/* 156 */     return this.d;
/*     */   }
/*     */   
/*     */   public enum Operation
/*     */   {
/* 161 */     WAIT, MOVE_TO, STRAFE, JUMPING;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ControllerMove.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */