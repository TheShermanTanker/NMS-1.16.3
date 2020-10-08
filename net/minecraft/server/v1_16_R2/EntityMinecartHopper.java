/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.List;
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
/*     */ public class EntityMinecartHopper
/*     */   extends EntityMinecartContainer
/*     */   implements IHopper
/*     */ {
/*     */   private boolean d = true;
/*  24 */   private int e = -1;
/*  25 */   private final BlockPosition f = BlockPosition.ZERO;
/*     */   
/*     */   public EntityMinecartHopper(EntityTypes<? extends EntityMinecartHopper> var0, World var1) {
/*  28 */     super(var0, var1);
/*     */   }
/*     */   
/*     */   public EntityMinecartHopper(World var0, double var1, double var3, double var5) {
/*  32 */     super(EntityTypes.HOPPER_MINECART, var1, var3, var5, var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityMinecartAbstract.EnumMinecartType getMinecartType() {
/*  37 */     return EntityMinecartAbstract.EnumMinecartType.HOPPER;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData q() {
/*  42 */     return Blocks.HOPPER.getBlockData();
/*     */   }
/*     */ 
/*     */   
/*     */   public int s() {
/*  47 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSize() {
/*  52 */     return 5;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(int var0, int var1, int var2, boolean var3) {
/*  57 */     boolean var4 = !var3;
/*     */     
/*  59 */     if (var4 != isEnabled()) {
/*  60 */       setEnabled(var4);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isEnabled() {
/*  65 */     return this.d;
/*     */   }
/*     */   
/*     */   public void setEnabled(boolean var0) {
/*  69 */     this.d = var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public World getWorld() {
/*  74 */     return this.world;
/*     */   }
/*     */ 
/*     */   
/*     */   public double x() {
/*  79 */     return locX();
/*     */   }
/*     */ 
/*     */   
/*     */   public double z() {
/*  84 */     return locY() + 0.5D;
/*     */   }
/*     */ 
/*     */   
/*     */   public double A() {
/*  89 */     return locZ();
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  94 */     super.tick();
/*     */     
/*  96 */     if (!this.world.isClientSide && isAlive() && isEnabled()) {
/*  97 */       BlockPosition var0 = getChunkCoordinates();
/*  98 */       if (var0.equals(this.f)) {
/*  99 */         this.e--;
/*     */       } else {
/* 101 */         setCooldown(0);
/*     */       } 
/*     */       
/* 104 */       if (!C()) {
/* 105 */         setCooldown(0);
/*     */         
/* 107 */         if (B()) {
/* 108 */           setCooldown(4);
/* 109 */           update();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean B() {
/* 116 */     if (TileEntityHopper.a(this)) {
/* 117 */       return true;
/*     */     }
/*     */     
/* 120 */     List<EntityItem> var0 = (List)this.world.a((Class)EntityItem.class, getBoundingBox().grow(0.25D, 0.0D, 0.25D), IEntitySelector.a);
/*     */     
/* 122 */     if (!var0.isEmpty()) {
/* 123 */       TileEntityHopper.a(this, var0.get(0));
/*     */     }
/*     */     
/* 126 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(DamageSource var0) {
/* 131 */     super.a(var0);
/*     */     
/* 133 */     if (this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
/* 134 */       a(Blocks.HOPPER);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void saveData(NBTTagCompound var0) {
/* 140 */     super.saveData(var0);
/* 141 */     var0.setInt("TransferCooldown", this.e);
/* 142 */     var0.setBoolean("Enabled", this.d);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void loadData(NBTTagCompound var0) {
/* 147 */     super.loadData(var0);
/* 148 */     this.e = var0.getInt("TransferCooldown");
/* 149 */     this.d = var0.hasKey("Enabled") ? var0.getBoolean("Enabled") : true;
/*     */   }
/*     */   
/*     */   public void setCooldown(int var0) {
/* 153 */     this.e = var0;
/*     */   }
/*     */   
/*     */   public boolean C() {
/* 157 */     return (this.e > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public Container a(int var0, PlayerInventory var1) {
/* 162 */     return new ContainerHopper(var0, var1, this);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityMinecartHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */