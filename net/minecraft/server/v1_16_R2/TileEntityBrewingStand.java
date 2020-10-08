/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftHumanEntity;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.inventory.BrewEvent;
/*     */ import org.bukkit.event.inventory.BrewingStandFuelEvent;
/*     */ import org.bukkit.inventory.BrewerInventory;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class TileEntityBrewingStand extends TileEntityContainer implements IWorldInventory, ITickable {
/*  19 */   private static final int[] b = new int[] { 3 };
/*  20 */   private static final int[] c = new int[] { 0, 1, 2, 3 };
/*  21 */   private static final int[] g = new int[] { 0, 1, 2, 4 };
/*     */   
/*     */   private NonNullList<ItemStack> items;
/*     */   public int brewTime;
/*     */   private boolean[] j;
/*     */   private Item k;
/*     */   public int fuelLevel;
/*     */   protected final IContainerProperties a;
/*  29 */   private int lastTick = MinecraftServer.currentTick;
/*  30 */   public List<HumanEntity> transaction = new ArrayList<>();
/*  31 */   private int maxStack = 64;
/*     */   
/*     */   public void onOpen(CraftHumanEntity who) {
/*  34 */     this.transaction.add(who);
/*     */   }
/*     */   
/*     */   public void onClose(CraftHumanEntity who) {
/*  38 */     this.transaction.remove(who);
/*     */   }
/*     */   
/*     */   public List<HumanEntity> getViewers() {
/*  42 */     return this.transaction;
/*     */   }
/*     */   
/*     */   public List<ItemStack> getContents() {
/*  46 */     return this.items;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxStackSize() {
/*  51 */     return this.maxStack;
/*     */   }
/*     */   
/*     */   public void setMaxStackSize(int size) {
/*  55 */     this.maxStack = size;
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntityBrewingStand() {
/*  60 */     super(TileEntityTypes.BREWING_STAND);
/*  61 */     this.items = NonNullList.a(5, ItemStack.b);
/*  62 */     this.a = new IContainerProperties()
/*     */       {
/*     */         public int getProperty(int i) {
/*  65 */           switch (i) {
/*     */             case 0:
/*  67 */               return TileEntityBrewingStand.this.brewTime;
/*     */             case 1:
/*  69 */               return TileEntityBrewingStand.this.fuelLevel;
/*     */           } 
/*  71 */           return 0;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void setProperty(int i, int j) {
/*  77 */           switch (i) {
/*     */             case 0:
/*  79 */               TileEntityBrewingStand.this.brewTime = j;
/*     */               break;
/*     */             case 1:
/*  82 */               TileEntityBrewingStand.this.fuelLevel = j;
/*     */               break;
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/*     */         public int a() {
/*  89 */           return 2;
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   protected IChatBaseComponent getContainerName() {
/*  96 */     return new ChatMessage("container.brewing");
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSize() {
/* 101 */     return this.items.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*     */     ItemStack itemstack;
/* 106 */     Iterator<ItemStack> iterator = this.items.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 111 */       if (!iterator.hasNext()) {
/* 112 */         return true;
/*     */       }
/*     */       
/* 115 */       itemstack = iterator.next();
/* 116 */     } while (itemstack.isEmpty());
/*     */     
/* 118 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/* 123 */     ItemStack itemstack = this.items.get(4);
/*     */     
/* 125 */     if (this.fuelLevel <= 0 && itemstack.getItem() == Items.BLAZE_POWDER) {
/*     */       
/* 127 */       BrewingStandFuelEvent event = new BrewingStandFuelEvent(this.world.getWorld().getBlockAt(this.position.getX(), this.position.getY(), this.position.getZ()), (ItemStack)CraftItemStack.asCraftMirror(itemstack), 20);
/* 128 */       this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */       
/* 130 */       if (event.isCancelled()) {
/*     */         return;
/*     */       }
/*     */       
/* 134 */       this.fuelLevel = event.getFuelPower();
/* 135 */       if (this.fuelLevel > 0 && event.isConsuming()) {
/* 136 */         itemstack.subtract(1);
/*     */       }
/*     */       
/* 139 */       update();
/*     */     } 
/*     */     
/* 142 */     boolean flag = h();
/* 143 */     boolean flag1 = (this.brewTime > 0);
/* 144 */     ItemStack itemstack1 = this.items.get(3);
/*     */ 
/*     */     
/* 147 */     int elapsedTicks = MinecraftServer.currentTick - this.lastTick;
/* 148 */     this.lastTick = MinecraftServer.currentTick;
/*     */     
/* 150 */     if (flag1) {
/* 151 */       this.brewTime -= elapsedTicks;
/* 152 */       boolean flag2 = (this.brewTime <= 0);
/*     */ 
/*     */       
/* 155 */       if (flag2 && flag) {
/* 156 */         j();
/* 157 */         update();
/* 158 */       } else if (!flag) {
/* 159 */         this.brewTime = 0;
/* 160 */         update();
/* 161 */       } else if (this.k != itemstack1.getItem()) {
/* 162 */         this.brewTime = 0;
/* 163 */         update();
/*     */       } 
/* 165 */     } else if (flag && this.fuelLevel > 0) {
/* 166 */       this.fuelLevel--;
/* 167 */       this.brewTime = 400;
/* 168 */       this.k = itemstack1.getItem();
/* 169 */       update();
/*     */     } 
/*     */     
/* 172 */     if (!this.world.isClientSide) {
/* 173 */       boolean[] aboolean = f();
/*     */       
/* 175 */       if (!Arrays.equals(aboolean, this.j)) {
/* 176 */         this.j = aboolean;
/* 177 */         IBlockData iblockdata = this.world.getType(getPosition());
/*     */         
/* 179 */         if (!(iblockdata.getBlock() instanceof BlockBrewingStand)) {
/*     */           return;
/*     */         }
/*     */         
/* 183 */         for (int i = 0; i < BlockBrewingStand.HAS_BOTTLE.length; i++) {
/* 184 */           iblockdata = iblockdata.set(BlockBrewingStand.HAS_BOTTLE[i], Boolean.valueOf(aboolean[i]));
/*     */         }
/*     */         
/* 187 */         this.world.setTypeAndData(this.position, iblockdata, 2);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean[] f() {
/* 194 */     boolean[] aboolean = new boolean[3];
/*     */     
/* 196 */     for (int i = 0; i < 3; i++) {
/* 197 */       if (!((ItemStack)this.items.get(i)).isEmpty()) {
/* 198 */         aboolean[i] = true;
/*     */       }
/*     */     } 
/*     */     
/* 202 */     return aboolean;
/*     */   }
/*     */   
/*     */   private boolean h() {
/* 206 */     ItemStack itemstack = this.items.get(3);
/*     */     
/* 208 */     if (itemstack.isEmpty())
/* 209 */       return false; 
/* 210 */     if (!PotionBrewer.a(itemstack)) {
/* 211 */       return false;
/*     */     }
/* 213 */     for (int i = 0; i < 3; i++) {
/* 214 */       ItemStack itemstack1 = this.items.get(i);
/*     */       
/* 216 */       if (!itemstack1.isEmpty() && PotionBrewer.a(itemstack1, itemstack)) {
/* 217 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 221 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void j() {
/* 226 */     ItemStack itemstack = this.items.get(3);
/*     */     
/* 228 */     InventoryHolder owner = getOwner();
/* 229 */     if (owner != null) {
/* 230 */       BrewEvent event = new BrewEvent(this.world.getWorld().getBlockAt(this.position.getX(), this.position.getY(), this.position.getZ()), (BrewerInventory)owner.getInventory(), this.fuelLevel);
/* 231 */       Bukkit.getPluginManager().callEvent((Event)event);
/* 232 */       if (event.isCancelled()) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 238 */     for (int i = 0; i < 3; i++) {
/* 239 */       this.items.set(i, PotionBrewer.d(itemstack, this.items.get(i)));
/*     */     }
/*     */     
/* 242 */     itemstack.subtract(1);
/* 243 */     BlockPosition blockposition = getPosition();
/*     */     
/* 245 */     if (itemstack.getItem().p()) {
/* 246 */       ItemStack itemstack1 = new ItemStack(itemstack.getItem().getCraftingRemainingItem());
/*     */       
/* 248 */       if (itemstack.isEmpty()) {
/* 249 */         itemstack = itemstack1;
/* 250 */       } else if (!this.world.isClientSide) {
/* 251 */         InventoryUtils.dropItem(this.world, blockposition.getX(), blockposition.getY(), blockposition.getZ(), itemstack1);
/*     */       } 
/*     */     } 
/*     */     
/* 255 */     this.items.set(3, itemstack);
/* 256 */     this.world.triggerEffect(1035, blockposition, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(IBlockData iblockdata, NBTTagCompound nbttagcompound) {
/* 261 */     super.load(iblockdata, nbttagcompound);
/* 262 */     this.items = NonNullList.a(getSize(), ItemStack.b);
/* 263 */     ContainerUtil.b(nbttagcompound, this.items);
/* 264 */     this.brewTime = nbttagcompound.getShort("BrewTime");
/* 265 */     this.fuelLevel = nbttagcompound.getByte("Fuel");
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound save(NBTTagCompound nbttagcompound) {
/* 270 */     super.save(nbttagcompound);
/* 271 */     nbttagcompound.setShort("BrewTime", (short)this.brewTime);
/* 272 */     ContainerUtil.a(nbttagcompound, this.items);
/* 273 */     nbttagcompound.setByte("Fuel", (byte)this.fuelLevel);
/* 274 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItem(int i) {
/* 279 */     return (i >= 0 && i < this.items.size()) ? this.items.get(i) : ItemStack.b;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack splitStack(int i, int j) {
/* 284 */     return ContainerUtil.a(this.items, i, j);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack splitWithoutUpdate(int i) {
/* 289 */     return ContainerUtil.a(this.items, i);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItem(int i, ItemStack itemstack) {
/* 294 */     if (i >= 0 && i < this.items.size()) {
/* 295 */       this.items.set(i, itemstack);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/* 302 */     return (this.world.getTileEntity(this.position) != this) ? false : ((entityhuman.h(this.position.getX() + 0.5D, this.position.getY() + 0.5D, this.position.getZ() + 0.5D) <= 64.0D));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(int i, ItemStack itemstack) {
/* 307 */     if (i == 3) {
/* 308 */       return PotionBrewer.a(itemstack);
/*     */     }
/* 310 */     Item item = itemstack.getItem();
/*     */     
/* 312 */     return (i == 4) ? ((item == Items.BLAZE_POWDER)) : (((item == Items.POTION || item == Items.SPLASH_POTION || item == Items.LINGERING_POTION || item == Items.GLASS_BOTTLE) && getItem(i).isEmpty()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getSlotsForFace(EnumDirection enumdirection) {
/* 318 */     return (enumdirection == EnumDirection.UP) ? b : ((enumdirection == EnumDirection.DOWN) ? c : g);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlaceItemThroughFace(int i, ItemStack itemstack, @Nullable EnumDirection enumdirection) {
/* 323 */     return b(i, itemstack);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canTakeItemThroughFace(int i, ItemStack itemstack, EnumDirection enumdirection) {
/* 328 */     return (i == 3) ? ((itemstack.getItem() == Items.GLASS_BOTTLE)) : true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 333 */     this.items.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   protected Container createContainer(int i, PlayerInventory playerinventory) {
/* 338 */     return new ContainerBrewingStand(i, playerinventory, this, this.a);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityBrewingStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */