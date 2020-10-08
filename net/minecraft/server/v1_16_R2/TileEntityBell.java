/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.mutable.MutableInt;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileEntityBell
/*     */   extends TileEntity
/*     */   implements ITickable
/*     */ {
/*     */   private long g;
/*     */   public int a;
/*     */   public boolean b;
/*     */   public EnumDirection c;
/*     */   private List<EntityLiving> h;
/*     */   private boolean i;
/*     */   private int j;
/*     */   
/*     */   public TileEntityBell() {
/*  44 */     super(TileEntityTypes.BELL);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setProperty(int var0, int var1) {
/*  49 */     if (var0 == 1) {
/*  50 */       f();
/*  51 */       this.j = 0;
/*  52 */       this.c = EnumDirection.fromType1(var1);
/*  53 */       this.a = 0;
/*  54 */       this.b = true;
/*  55 */       return true;
/*     */     } 
/*  57 */     return super.setProperty(var0, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  62 */     if (this.b) {
/*  63 */       this.a++;
/*     */     }
/*     */     
/*  66 */     if (this.a >= 50) {
/*  67 */       this.b = false;
/*  68 */       this.a = 0;
/*     */     } 
/*     */     
/*  71 */     if (this.a >= 5 && this.j == 0 && h()) {
/*  72 */       this.i = true;
/*  73 */       d();
/*     */     } 
/*     */     
/*  76 */     if (this.i) {
/*  77 */       if (this.j < 40) {
/*  78 */         this.j++;
/*     */       } else {
/*  80 */         a(this.world);
/*  81 */         b(this.world);
/*  82 */         this.i = false;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private void d() {
/*  88 */     this.world.playSound((EntityHuman)null, getPosition(), SoundEffects.BLOCK_BELL_RESONATE, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public void a(EnumDirection var0) {
/*  92 */     BlockPosition var1 = getPosition();
/*     */     
/*  94 */     this.c = var0;
/*  95 */     if (this.b) {
/*  96 */       this.a = 0;
/*     */     } else {
/*  98 */       this.b = true;
/*     */     } 
/*     */     
/* 101 */     this.world.playBlockAction(var1, getBlock().getBlock(), 1, var0.c());
/*     */   }
/*     */   
/*     */   private void f() {
/* 105 */     BlockPosition var0 = getPosition();
/*     */     
/* 107 */     if (this.world.getTime() > this.g + 60L || this.h == null) {
/* 108 */       this.g = this.world.getTime();
/* 109 */       AxisAlignedBB var1 = (new AxisAlignedBB(var0)).g(48.0D);
/* 110 */       this.h = this.world.a(EntityLiving.class, var1);
/*     */     } 
/*     */     
/* 113 */     if (!this.world.isClientSide) {
/* 114 */       for (EntityLiving var2 : this.h) {
/* 115 */         if (!var2.isAlive() || var2.dead) {
/*     */           continue;
/*     */         }
/* 118 */         if (var0.a(var2.getPositionVector(), 32.0D)) {
/* 119 */           var2.getBehaviorController().setMemory(MemoryModuleType.HEARD_BELL_TIME, Long.valueOf(this.world.getTime()));
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean h() {
/* 126 */     BlockPosition var0 = getPosition();
/* 127 */     for (EntityLiving var2 : this.h) {
/* 128 */       if (!var2.isAlive() || var2.dead) {
/*     */         continue;
/*     */       }
/* 131 */       if (var0.a(var2.getPositionVector(), 32.0D) && 
/* 132 */         var2.getEntityType().a(TagsEntity.RADIERS)) {
/* 133 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 137 */     return false;
/*     */   }
/*     */   
/*     */   private void a(World var0) {
/* 141 */     if (var0.isClientSide) {
/*     */       return;
/*     */     }
/*     */     
/* 145 */     this.h.stream()
/* 146 */       .filter(this::a)
/* 147 */       .forEach(this::b);
/*     */   }
/*     */   
/*     */   private void b(World var0) {
/* 151 */     if (!var0.isClientSide) {
/*     */       return;
/*     */     }
/*     */     
/* 155 */     BlockPosition var1 = getPosition();
/* 156 */     MutableInt var2 = new MutableInt(16700985);
/*     */     
/* 158 */     int var3 = (int)this.h.stream().filter(var1 -> var0.a(var1.getPositionVector(), 48.0D)).count();
/*     */     
/* 160 */     this.h.stream()
/* 161 */       .filter(this::a)
/* 162 */       .forEach(var4 -> {
/*     */           float var5 = 1.0F;
/*     */           float var6 = MathHelper.sqrt((var4.locX() - var0.getX()) * (var4.locX() - var0.getX()) + (var4.locZ() - var0.getZ()) * (var4.locZ() - var0.getZ()));
/*     */           double var7 = (var0.getX() + 0.5F) + (1.0F / var6) * (var4.locX() - var0.getX());
/*     */           double var9 = (var0.getZ() + 0.5F) + (1.0F / var6) * (var4.locZ() - var0.getZ());
/*     */           int var11 = MathHelper.clamp((var1 - 21) / -2, 3, 15);
/*     */           for (int var12 = 0; var12 < var11; var12++) {
/*     */             int var13 = var2.addAndGet(5);
/*     */             double var14 = ColorUtil.a.b(var13) / 255.0D;
/*     */             double var16 = ColorUtil.a.c(var13) / 255.0D;
/*     */             double var18 = ColorUtil.a.d(var13) / 255.0D;
/*     */             var3.addParticle(Particles.ENTITY_EFFECT, var7, (var0.getY() + 0.5F), var9, var14, var16, var18);
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean a(EntityLiving var0) {
/* 180 */     return (var0.isAlive() && !var0.dead && 
/*     */       
/* 182 */       getPosition().a(var0.getPositionVector(), 48.0D) && var0
/* 183 */       .getEntityType().a(TagsEntity.RADIERS));
/*     */   }
/*     */   
/*     */   private void b(EntityLiving var0) {
/* 187 */     var0.addEffect(new MobEffect(MobEffects.GLOWING, 60));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityBell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */