/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftHumanEntity;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ 
/*     */ 
/*     */ public class TileEntityChest
/*     */   extends TileEntityLootable
/*     */ {
/*     */   private NonNullList<ItemStack> items;
/*     */   protected float a;
/*     */   protected float b;
/*     */   public int viewingCount;
/*     */   private int j;
/*  19 */   public List<HumanEntity> transaction = new ArrayList<>();
/*  20 */   private int maxStack = 64;
/*     */   public boolean opened;
/*     */   
/*     */   public List<ItemStack> getContents() {
/*  24 */     return this.items;
/*     */   }
/*     */   
/*     */   public void onOpen(CraftHumanEntity who) {
/*  28 */     this.transaction.add(who);
/*     */   }
/*     */   
/*     */   public void onClose(CraftHumanEntity who) {
/*  32 */     this.transaction.remove(who);
/*     */   }
/*     */   
/*     */   public List<HumanEntity> getViewers() {
/*  36 */     return this.transaction;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxStackSize() {
/*  41 */     return this.maxStack;
/*     */   }
/*     */   
/*     */   public void setMaxStackSize(int size) {
/*  45 */     this.maxStack = size;
/*     */   }
/*     */ 
/*     */   
/*     */   protected TileEntityChest(TileEntityTypes<?> tileentitytypes) {
/*  50 */     super(tileentitytypes);
/*  51 */     this.items = NonNullList.a(27, ItemStack.b);
/*     */   }
/*     */   
/*     */   public TileEntityChest() {
/*  55 */     this(TileEntityTypes.CHEST);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSize() {
/*  60 */     return 27;
/*     */   }
/*     */ 
/*     */   
/*     */   protected IChatBaseComponent getContainerName() {
/*  65 */     return new ChatMessage("container.chest");
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(IBlockData iblockdata, NBTTagCompound nbttagcompound) {
/*  70 */     super.load(iblockdata, nbttagcompound);
/*  71 */     this.items = NonNullList.a(getSize(), ItemStack.b);
/*  72 */     if (!b(nbttagcompound)) {
/*  73 */       ContainerUtil.b(nbttagcompound, this.items);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound save(NBTTagCompound nbttagcompound) {
/*  80 */     super.save(nbttagcompound);
/*  81 */     if (!c(nbttagcompound)) {
/*  82 */       ContainerUtil.a(nbttagcompound, this.items);
/*     */     }
/*     */     
/*  85 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   public void tick() {
/*  89 */     int i = this.position.getX();
/*  90 */     int j = this.position.getY();
/*  91 */     int k = this.position.getZ();
/*     */     
/*  93 */     this.j++;
/*     */   }
/*     */   
/*     */   public void doOpenLogic() {
/*  97 */     int i = this.position.getX();
/*  98 */     int j = this.position.getY();
/*  99 */     int k = this.position.getZ();
/*     */ 
/*     */     
/* 102 */     this.b = this.a;
/* 103 */     float f = 0.1F;
/*     */ 
/*     */     
/* 106 */     if (this.opened) {
/* 107 */       this.viewingCount--;
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 112 */     if (this.viewingCount > 0 && this.a == 0.0F) {
/* 113 */       playOpenSound(SoundEffects.BLOCK_CHEST_OPEN);
/*     */     }
/*     */   }
/*     */   
/*     */   public void doCloseLogic() {
/* 118 */     if (this.viewingCount == 0) {
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
/* 136 */       MCUtil.scheduleTask(10, () -> playOpenSound(SoundEffects.BLOCK_CHEST_CLOSE), "Chest Sounds");
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 141 */       if (this.a < 0.0F) {
/* 142 */         this.a = 0.0F;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static int a(World world, TileEntityContainer tileentitycontainer, int i, int j, int k, int l, int i1) {
/* 149 */     if (!world.isClientSide && i1 != 0 && (i + j + k + l) % 200 == 0) {
/* 150 */       i1 = a(world, tileentitycontainer, j, k, l);
/*     */     }
/*     */     
/* 153 */     return i1;
/*     */   }
/*     */   
/*     */   public static int a(World world, TileEntityContainer tileentitycontainer, int i, int j, int k) {
/* 157 */     int l = 0;
/* 158 */     float f = 5.0F;
/* 159 */     List<EntityHuman> list = world.a(EntityHuman.class, new AxisAlignedBB((i - 5.0F), (j - 5.0F), (k - 5.0F), ((i + 1) + 5.0F), ((j + 1) + 5.0F), ((k + 1) + 5.0F)));
/* 160 */     Iterator<EntityHuman> iterator = list.iterator();
/*     */     
/* 162 */     while (iterator.hasNext()) {
/* 163 */       EntityHuman entityhuman = iterator.next();
/*     */       
/* 165 */       if (entityhuman.activeContainer instanceof ContainerChest) {
/* 166 */         IInventory iinventory = ((ContainerChest)entityhuman.activeContainer).e();
/*     */         
/* 168 */         if (iinventory == tileentitycontainer || (iinventory instanceof InventoryLargeChest && ((InventoryLargeChest)iinventory).a(tileentitycontainer))) {
/* 169 */           l++;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 174 */     return l;
/*     */   }
/*     */   
/*     */   public void playOpenSound(SoundEffect soundeffect) {
/* 178 */     if (!getBlock().contains(BlockChest.CHEST_TYPE_PROPERTY))
/* 179 */       return;  BlockPropertyChestType blockpropertychesttype = (BlockPropertyChestType)getBlock().get(BlockChest.c);
/*     */     
/* 181 */     if (blockpropertychesttype != BlockPropertyChestType.LEFT) {
/* 182 */       double d0 = this.position.getX() + 0.5D;
/* 183 */       double d1 = this.position.getY() + 0.5D;
/* 184 */       double d2 = this.position.getZ() + 0.5D;
/*     */       
/* 186 */       if (blockpropertychesttype == BlockPropertyChestType.RIGHT) {
/* 187 */         EnumDirection enumdirection = BlockChest.h(getBlock());
/*     */         
/* 189 */         d0 += enumdirection.getAdjacentX() * 0.5D;
/* 190 */         d2 += enumdirection.getAdjacentZ() * 0.5D;
/*     */       } 
/*     */       
/* 193 */       this.world.playSound((EntityHuman)null, d0, d1, d2, soundeffect, SoundCategory.BLOCKS, 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setProperty(int i, int j) {
/* 199 */     if (i == 1) {
/* 200 */       this.viewingCount = j;
/* 201 */       return true;
/*     */     } 
/* 203 */     return super.setProperty(i, j);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void startOpen(EntityHuman entityhuman) {
/* 209 */     if (!entityhuman.isSpectator()) {
/* 210 */       if (this.viewingCount < 0) {
/* 211 */         this.viewingCount = 0;
/*     */       }
/* 213 */       int oldPower = Math.max(0, Math.min(15, this.viewingCount));
/*     */       
/* 215 */       this.viewingCount++;
/* 216 */       if (this.world == null)
/* 217 */         return;  doOpenLogic();
/*     */ 
/*     */       
/* 220 */       if (getBlock().getBlock() == Blocks.TRAPPED_CHEST) {
/* 221 */         int newPower = Math.max(0, Math.min(15, this.viewingCount));
/*     */         
/* 223 */         if (oldPower != newPower) {
/* 224 */           CraftEventFactory.callRedstoneChange(this.world, this.position, oldPower, newPower);
/*     */         }
/*     */       } 
/*     */       
/* 228 */       onOpen();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void closeContainer(EntityHuman entityhuman) {
/* 235 */     if (!entityhuman.isSpectator()) {
/* 236 */       int oldPower = Math.max(0, Math.min(15, this.viewingCount));
/* 237 */       this.viewingCount--;
/*     */ 
/*     */       
/* 240 */       doCloseLogic();
/* 241 */       if (getBlock().getBlock() == Blocks.TRAPPED_CHEST) {
/* 242 */         int newPower = Math.max(0, Math.min(15, this.viewingCount));
/*     */         
/* 244 */         if (oldPower != newPower) {
/* 245 */           CraftEventFactory.callRedstoneChange(this.world, this.position, oldPower, newPower);
/*     */         }
/*     */       } 
/*     */       
/* 249 */       onOpen();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onOpen() {
/* 255 */     Block block = getBlock().getBlock();
/*     */     
/* 257 */     if (block instanceof BlockChest) {
/* 258 */       if (!this.opened) this.world.playBlockAction(this.position, block, 1, this.viewingCount); 
/* 259 */       this.world.applyPhysics(this.position, block);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected NonNullList<ItemStack> f() {
/* 266 */     return this.items;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(NonNullList<ItemStack> nonnulllist) {
/* 271 */     this.items = nonnulllist;
/*     */   }
/*     */   
/*     */   public static int a(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 275 */     IBlockData iblockdata = iblockaccess.getType(blockposition);
/*     */     
/* 277 */     if (iblockdata.getBlock().isTileEntity()) {
/* 278 */       TileEntity tileentity = iblockaccess.getTileEntity(blockposition);
/*     */       
/* 280 */       if (tileentity instanceof TileEntityChest) {
/* 281 */         return ((TileEntityChest)tileentity).viewingCount;
/*     */       }
/*     */     } 
/*     */     
/* 285 */     return 0;
/*     */   }
/*     */   
/*     */   public static void a(TileEntityChest tileentitychest, TileEntityChest tileentitychest1) {
/* 289 */     NonNullList<ItemStack> nonnulllist = tileentitychest.f();
/*     */     
/* 291 */     tileentitychest.a(tileentitychest1.f());
/* 292 */     tileentitychest1.a(nonnulllist);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Container createContainer(int i, PlayerInventory playerinventory) {
/* 297 */     return ContainerChest.a(i, playerinventory, this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFilteredNBT() {
/* 303 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */