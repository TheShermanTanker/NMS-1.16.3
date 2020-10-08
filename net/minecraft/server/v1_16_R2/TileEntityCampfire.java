/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlock;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockCookEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class TileEntityCampfire extends TileEntity implements Clearable, ITickable {
/*     */   private final NonNullList<ItemStack> items;
/*     */   public final int[] cookingTimes;
/*     */   public final int[] cookingTotalTimes;
/*     */   
/*     */   public TileEntityCampfire() {
/*  19 */     super(TileEntityTypes.CAMPFIRE);
/*  20 */     this.items = NonNullList.a(4, ItemStack.b);
/*  21 */     this.cookingTimes = new int[4];
/*  22 */     this.cookingTotalTimes = new int[4];
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  27 */     boolean flag = ((Boolean)getBlock().get(BlockCampfire.b)).booleanValue();
/*  28 */     boolean flag1 = this.world.isClientSide;
/*     */     
/*  30 */     if (flag1) {
/*  31 */       if (flag) {
/*  32 */         j();
/*     */       
/*     */       }
/*     */     }
/*  36 */     else if (flag) {
/*  37 */       h();
/*     */     } else {
/*  39 */       for (int i = 0; i < this.items.size(); i++) {
/*  40 */         if (this.cookingTimes[i] > 0) {
/*  41 */           this.cookingTimes[i] = MathHelper.clamp(this.cookingTimes[i] - 2, 0, this.cookingTotalTimes[i]);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void h() {
/*  50 */     for (int i = 0; i < this.items.size(); i++) {
/*  51 */       ItemStack itemstack = this.items.get(i);
/*     */       
/*  53 */       if (!itemstack.isEmpty()) {
/*  54 */         this.cookingTimes[i] = this.cookingTimes[i] + 1; int j = this.cookingTimes[i];
/*     */         
/*  56 */         if (this.cookingTimes[i] >= this.cookingTotalTimes[i]) {
/*  57 */           InventorySubcontainer inventorysubcontainer = new InventorySubcontainer(new ItemStack[] { itemstack });
/*     */ 
/*     */           
/*  60 */           ItemStack itemstack1 = this.world.getCraftingManager().<InventorySubcontainer, RecipeCampfire>craft(Recipes.CAMPFIRE_COOKING, inventorysubcontainer, this.world).map(recipecampfire -> recipecampfire.a(inventorysubcontainer)).orElse(itemstack);
/*  61 */           BlockPosition blockposition = getPosition();
/*     */ 
/*     */           
/*  64 */           CraftItemStack source = CraftItemStack.asCraftMirror(itemstack);
/*  65 */           ItemStack result = CraftItemStack.asBukkitCopy(itemstack1);
/*     */           
/*  67 */           BlockCookEvent blockCookEvent = new BlockCookEvent((Block)CraftBlock.at(this.world, this.position), (ItemStack)source, result);
/*  68 */           this.world.getServer().getPluginManager().callEvent((Event)blockCookEvent);
/*     */           
/*  70 */           if (blockCookEvent.isCancelled()) {
/*     */             return;
/*     */           }
/*     */           
/*  74 */           result = blockCookEvent.getResult();
/*  75 */           itemstack1 = CraftItemStack.asNMSCopy(result);
/*     */           
/*  77 */           InventoryUtils.dropItem(this.world, blockposition.getX(), blockposition.getY(), blockposition.getZ(), itemstack1);
/*  78 */           this.items.set(i, ItemStack.b);
/*  79 */           k();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void j() {
/*  87 */     World world = getWorld();
/*     */     
/*  89 */     if (world != null) {
/*  90 */       BlockPosition blockposition = getPosition();
/*  91 */       Random random = world.random;
/*     */ 
/*     */       
/*  94 */       if (random.nextFloat() < 0.11F) {
/*  95 */         for (int k = 0; k < random.nextInt(2) + 2; k++) {
/*  96 */           BlockCampfire.a(world, blockposition, ((Boolean)getBlock().get(BlockCampfire.c)).booleanValue(), false);
/*     */         }
/*     */       }
/*     */       
/* 100 */       int i = ((EnumDirection)getBlock().get(BlockCampfire.e)).get2DRotationValue();
/*     */       
/* 102 */       for (int j = 0; j < this.items.size(); j++) {
/* 103 */         if (!((ItemStack)this.items.get(j)).isEmpty() && random.nextFloat() < 0.2F) {
/* 104 */           EnumDirection enumdirection = EnumDirection.fromType2(Math.floorMod(j + i, 4));
/* 105 */           float f = 0.3125F;
/* 106 */           double d0 = blockposition.getX() + 0.5D - (enumdirection.getAdjacentX() * 0.3125F) + (enumdirection.g().getAdjacentX() * 0.3125F);
/* 107 */           double d1 = blockposition.getY() + 0.5D;
/* 108 */           double d2 = blockposition.getZ() + 0.5D - (enumdirection.getAdjacentZ() * 0.3125F) + (enumdirection.g().getAdjacentZ() * 0.3125F);
/*     */           
/* 110 */           for (int k = 0; k < 4; k++) {
/* 111 */             world.addParticle(Particles.SMOKE, d0, d1, d2, 0.0D, 5.0E-4D, 0.0D);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public NonNullList<ItemStack> getItems() {
/* 120 */     return this.items;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(IBlockData iblockdata, NBTTagCompound nbttagcompound) {
/* 125 */     super.load(iblockdata, nbttagcompound);
/* 126 */     this.items.clear();
/* 127 */     ContainerUtil.b(nbttagcompound, this.items);
/*     */ 
/*     */     
/* 130 */     if (nbttagcompound.hasKeyOfType("CookingTimes", 11)) {
/* 131 */       int[] aint = nbttagcompound.getIntArray("CookingTimes");
/* 132 */       System.arraycopy(aint, 0, this.cookingTimes, 0, Math.min(this.cookingTotalTimes.length, aint.length));
/*     */     } 
/*     */     
/* 135 */     if (nbttagcompound.hasKeyOfType("CookingTotalTimes", 11)) {
/* 136 */       int[] aint = nbttagcompound.getIntArray("CookingTotalTimes");
/* 137 */       System.arraycopy(aint, 0, this.cookingTotalTimes, 0, Math.min(this.cookingTotalTimes.length, aint.length));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound save(NBTTagCompound nbttagcompound) {
/* 144 */     b(nbttagcompound);
/* 145 */     nbttagcompound.setIntArray("CookingTimes", this.cookingTimes);
/* 146 */     nbttagcompound.setIntArray("CookingTotalTimes", this.cookingTotalTimes);
/* 147 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   private NBTTagCompound b(NBTTagCompound nbttagcompound) {
/* 151 */     super.save(nbttagcompound);
/* 152 */     ContainerUtil.a(nbttagcompound, this.items, true);
/* 153 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public PacketPlayOutTileEntityData getUpdatePacket() {
/* 159 */     return new PacketPlayOutTileEntityData(this.position, 13, b());
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound b() {
/* 164 */     return b(new NBTTagCompound());
/*     */   }
/*     */   
/*     */   public Optional<RecipeCampfire> a(ItemStack itemstack) {
/* 168 */     return this.items.stream().noneMatch(ItemStack::isEmpty) ? Optional.<RecipeCampfire>empty() : this.world.getCraftingManager().<InventorySubcontainer, RecipeCampfire>craft(Recipes.CAMPFIRE_COOKING, new InventorySubcontainer(new ItemStack[] { itemstack }, ), this.world);
/*     */   }
/*     */   
/*     */   public boolean a(ItemStack itemstack, int i) {
/* 172 */     for (int j = 0; j < this.items.size(); j++) {
/* 173 */       ItemStack itemstack1 = this.items.get(j);
/*     */       
/* 175 */       if (itemstack1.isEmpty()) {
/* 176 */         this.cookingTotalTimes[j] = i;
/* 177 */         this.cookingTimes[j] = 0;
/* 178 */         this.items.set(j, itemstack.cloneAndSubtract(1));
/* 179 */         k();
/* 180 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 184 */     return false;
/*     */   }
/*     */   
/*     */   private void k() {
/* 188 */     update();
/* 189 */     getWorld().notify(getPosition(), getBlock(), getBlock(), 3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 194 */     this.items.clear();
/*     */   }
/*     */   
/*     */   public void f() {
/* 198 */     if (this.world != null) {
/* 199 */       if (!this.world.isClientSide) {
/* 200 */         InventoryUtils.a(this.world, getPosition(), getItems());
/*     */       }
/*     */       
/* 203 */       k();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityCampfire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */