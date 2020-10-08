/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftHumanEntity;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileEntityBarrel
/*     */   extends TileEntityLootable
/*     */ {
/*  19 */   public List<HumanEntity> transaction = new ArrayList<>();
/*  20 */   private int maxStack = 64;
/*     */   
/*     */   public boolean opened;
/*     */   
/*     */   public List<ItemStack> getContents() {
/*  25 */     return this.items;
/*     */   }
/*     */   private NonNullList<ItemStack> items; private int b;
/*     */   
/*     */   public void onOpen(CraftHumanEntity who) {
/*  30 */     this.transaction.add(who);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onClose(CraftHumanEntity who) {
/*  35 */     this.transaction.remove(who);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<HumanEntity> getViewers() {
/*  40 */     return this.transaction;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxStackSize() {
/*  45 */     return this.maxStack;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaxStackSize(int i) {
/*  50 */     this.maxStack = i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private TileEntityBarrel(TileEntityTypes<?> tileentitytypes) {
/*  57 */     super(tileentitytypes);
/*  58 */     this.items = NonNullList.a(27, ItemStack.b);
/*     */   }
/*     */   
/*     */   public TileEntityBarrel() {
/*  62 */     this(TileEntityTypes.BARREL);
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound save(NBTTagCompound nbttagcompound) {
/*  67 */     super.save(nbttagcompound);
/*  68 */     if (!c(nbttagcompound)) {
/*  69 */       ContainerUtil.a(nbttagcompound, this.items);
/*     */     }
/*     */     
/*  72 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(IBlockData iblockdata, NBTTagCompound nbttagcompound) {
/*  77 */     super.load(iblockdata, nbttagcompound);
/*  78 */     this.items = NonNullList.a(getSize(), ItemStack.b);
/*  79 */     if (!b(nbttagcompound)) {
/*  80 */       ContainerUtil.b(nbttagcompound, this.items);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSize() {
/*  87 */     return 27;
/*     */   }
/*     */ 
/*     */   
/*     */   protected NonNullList<ItemStack> f() {
/*  92 */     return this.items;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(NonNullList<ItemStack> nonnulllist) {
/*  97 */     this.items = nonnulllist;
/*     */   }
/*     */ 
/*     */   
/*     */   protected IChatBaseComponent getContainerName() {
/* 102 */     return new ChatMessage("container.barrel");
/*     */   }
/*     */ 
/*     */   
/*     */   protected Container createContainer(int i, PlayerInventory playerinventory) {
/* 107 */     return ContainerChest.a(i, playerinventory, this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void startOpen(EntityHuman entityhuman) {
/* 112 */     if (!entityhuman.isSpectator()) {
/* 113 */       if (this.b < 0) {
/* 114 */         this.b = 0;
/*     */       }
/*     */       
/* 117 */       this.b++;
/* 118 */       IBlockData iblockdata = getBlock();
/* 119 */       boolean flag = ((Boolean)iblockdata.get(BlockBarrel.OPEN)).booleanValue();
/*     */       
/* 121 */       if (!flag) {
/* 122 */         playOpenSound(iblockdata, SoundEffects.BLOCK_BARREL_OPEN);
/* 123 */         setOpenFlag(iblockdata, true);
/*     */       } 
/*     */       
/* 126 */       j();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void j() {
/* 132 */     this.world.getBlockTickList().a(getPosition(), getBlock().getBlock(), 5);
/*     */   }
/*     */   
/*     */   public void h() {
/* 136 */     int i = this.position.getX();
/* 137 */     int j = this.position.getY();
/* 138 */     int k = this.position.getZ();
/*     */     
/* 140 */     this.b = TileEntityChest.a(this.world, this, i, j, k);
/* 141 */     if (this.b > 0) {
/* 142 */       j();
/*     */     } else {
/* 144 */       IBlockData iblockdata = getBlock();
/*     */       
/* 146 */       if (!iblockdata.a(Blocks.BARREL)) {
/* 147 */         al_();
/*     */         
/*     */         return;
/*     */       } 
/* 151 */       boolean flag = (((Boolean)iblockdata.get(BlockBarrel.OPEN)).booleanValue() && !this.opened);
/*     */       
/* 153 */       if (flag) {
/* 154 */         playOpenSound(iblockdata, SoundEffects.BLOCK_BARREL_CLOSE);
/* 155 */         setOpenFlag(iblockdata, false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void closeContainer(EntityHuman entityhuman) {
/* 163 */     if (!entityhuman.isSpectator()) {
/* 164 */       this.b--;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOpenFlag(IBlockData iblockdata, boolean flag) {
/* 170 */     this.world.setTypeAndData(getPosition(), iblockdata.set(BlockBarrel.OPEN, Boolean.valueOf(flag)), 3);
/*     */   }
/*     */   
/*     */   public void playOpenSound(IBlockData iblockdata, SoundEffect soundeffect) {
/* 174 */     BaseBlockPosition baseblockposition = ((EnumDirection)iblockdata.get(BlockBarrel.a)).p();
/* 175 */     double d0 = this.position.getX() + 0.5D + baseblockposition.getX() / 2.0D;
/* 176 */     double d1 = this.position.getY() + 0.5D + baseblockposition.getY() / 2.0D;
/* 177 */     double d2 = this.position.getZ() + 0.5D + baseblockposition.getZ() / 2.0D;
/*     */     
/* 179 */     this.world.playSound((EntityHuman)null, d0, d1, d2, soundeffect, SoundCategory.BLOCKS, 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityBarrel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */