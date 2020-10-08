/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ public class TileEntityEnderChest
/*     */   extends TileEntity {
/*     */   public float a;
/*     */   public float b;
/*     */   public int c;
/*     */   private int g;
/*     */   
/*     */   public TileEntityEnderChest() {
/*  11 */     super(TileEntityTypes.ENDER_CHEST);
/*     */   }
/*     */   
/*     */   public void tick() {
/*  15 */     if (++this.g % 20 * 4 == 0) {
/*  16 */       this.world.playBlockAction(this.position, Blocks.ENDER_CHEST, 1, this.c);
/*     */     }
/*     */     
/*  19 */     this.b = this.a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void doOpenLogic() {
/*  31 */     int i = this.position.getX();
/*  32 */     int j = this.position.getY();
/*  33 */     int k = this.position.getZ();
/*     */ 
/*     */ 
/*     */     
/*  37 */     if (this.c > 0 && this.a == 0.0F) {
/*  38 */       double d1 = i + 0.5D;
/*     */       
/*  40 */       double d0 = k + 0.5D;
/*  41 */       this.world.playSound((EntityHuman)null, d1, j + 0.5D, d0, SoundEffects.BLOCK_ENDER_CHEST_OPEN, SoundCategory.BLOCKS, 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void doCloseLogic() {
/*  47 */     int i = this.position.getX();
/*  48 */     int j = this.position.getY();
/*  49 */     int k = this.position.getZ();
/*     */ 
/*     */     
/*  52 */     if (this.c == 0) {
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
/*  71 */       double d0 = i + 0.5D;
/*  72 */       double d2 = k + 0.5D;
/*     */       
/*  74 */       MCUtil.scheduleTask(10, () -> this.world.playSound((EntityHuman)null, d0, j + 0.5D, d2, SoundEffects.BLOCK_ENDER_CHEST_CLOSE, SoundCategory.BLOCKS, 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F), "Chest Sounds");
/*     */ 
/*     */ 
/*     */       
/*  78 */       if (this.a < 0.0F) {
/*  79 */         this.a = 0.0F;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setProperty(int i, int j) {
/*  87 */     if (i == 1) {
/*  88 */       this.c = j;
/*  89 */       return true;
/*     */     } 
/*  91 */     return super.setProperty(i, j);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void al_() {
/*  97 */     invalidateBlockCache();
/*  98 */     super.al_();
/*     */   }
/*     */   
/*     */   public void d() {
/* 102 */     this.c++;
/* 103 */     this.world.playBlockAction(this.position, Blocks.ENDER_CHEST, 1, this.c);
/* 104 */     doOpenLogic();
/*     */   }
/*     */   
/*     */   public void f() {
/* 108 */     this.c--;
/* 109 */     this.world.playBlockAction(this.position, Blocks.ENDER_CHEST, 1, this.c);
/* 110 */     doCloseLogic();
/*     */   }
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/* 114 */     return (this.world.getTileEntity(this.position) != this) ? false : ((entityhuman.h(this.position.getX() + 0.5D, this.position.getY() + 0.5D, this.position.getZ() + 0.5D) <= 64.0D));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityEnderChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */