/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.destroystokyo.paper.loottable.PaperLootableInventory;
/*     */ import com.destroystokyo.paper.loottable.PaperLootableInventoryData;
/*     */ import com.destroystokyo.paper.loottable.PaperTileEntityLootableInventory;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public abstract class TileEntityLootable extends TileEntityContainer {
/*     */   @Nullable
/*     */   public MinecraftKey lootTable;
/*  11 */   public final PaperLootableInventoryData lootableData = new PaperLootableInventoryData((PaperLootableInventory)new PaperTileEntityLootableInventory(this)); public long lootTableSeed;
/*     */   
/*     */   protected TileEntityLootable(TileEntityTypes<?> tileentitytypes) {
/*  14 */     super(tileentitytypes);
/*     */   }
/*     */   
/*     */   public static void a(IBlockAccess iblockaccess, Random random, BlockPosition blockposition, MinecraftKey minecraftkey) {
/*  18 */     TileEntity tileentity = iblockaccess.getTileEntity(blockposition);
/*     */     
/*  20 */     if (tileentity instanceof TileEntityLootable) {
/*  21 */       ((TileEntityLootable)tileentity).setLootTable(minecraftkey, random.nextLong());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean b(NBTTagCompound nbttagcompound) {
/*  27 */     this.lootableData.loadNbt(nbttagcompound);
/*  28 */     if (nbttagcompound.hasKeyOfType("LootTable", 8)) {
/*  29 */       this.lootTable = new MinecraftKey(nbttagcompound.getString("LootTable"));
/*  30 */       this.lootTableSeed = nbttagcompound.getLong("LootTableSeed");
/*  31 */       return false;
/*     */     } 
/*  33 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean c(NBTTagCompound nbttagcompound) {
/*  38 */     this.lootableData.saveNbt(nbttagcompound);
/*  39 */     if (this.lootTable == null) {
/*  40 */       return false;
/*     */     }
/*  42 */     nbttagcompound.setString("LootTable", this.lootTable.toString());
/*  43 */     if (this.lootTableSeed != 0L) {
/*  44 */       nbttagcompound.setLong("LootTableSeed", this.lootTableSeed);
/*     */     }
/*     */     
/*  47 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void d(@Nullable EntityHuman entityhuman) {
/*  52 */     if (this.lootableData.shouldReplenish(entityhuman) && this.world.getMinecraftServer() != null) {
/*  53 */       LootTable loottable = this.world.getMinecraftServer().getLootTableRegistry().getLootTable(this.lootTable);
/*     */       
/*  55 */       if (entityhuman instanceof EntityPlayer) {
/*  56 */         CriterionTriggers.N.a((EntityPlayer)entityhuman, this.lootTable);
/*     */       }
/*     */ 
/*     */       
/*  60 */       this.lootableData.processRefill(entityhuman);
/*  61 */       LootTableInfo.Builder loottableinfo_builder = (new LootTableInfo.Builder((WorldServer)this.world)).<Vec3D>set(LootContextParameters.ORIGIN, Vec3D.a(this.position)).a(this.lootTableSeed);
/*     */       
/*  63 */       if (entityhuman != null) {
/*  64 */         loottableinfo_builder.a(entityhuman.eT()).set(LootContextParameters.THIS_ENTITY, entityhuman);
/*     */       }
/*     */       
/*  67 */       loottable.fillInventory(this, loottableinfo_builder.build(LootContextParameterSets.CHEST));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLootTable(MinecraftKey minecraftkey, long i) {
/*  73 */     this.lootTable = minecraftkey;
/*  74 */     this.lootTableSeed = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/*  79 */     d((EntityHuman)null);
/*     */     
/*  81 */     for (ItemStack itemStack : f()) {
/*  82 */       if (!itemStack.isEmpty()) {
/*  83 */         return false;
/*     */       }
/*     */     } 
/*     */     
/*  87 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItem(int i) {
/*  92 */     if (i == 0) d((EntityHuman)null); 
/*  93 */     return f().get(i);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack splitStack(int i, int j) {
/*  98 */     d((EntityHuman)null);
/*  99 */     ItemStack itemstack = ContainerUtil.a(f(), i, j);
/*     */     
/* 101 */     if (!itemstack.isEmpty()) {
/* 102 */       update();
/*     */     }
/*     */     
/* 105 */     return itemstack;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack splitWithoutUpdate(int i) {
/* 110 */     d((EntityHuman)null);
/* 111 */     return ContainerUtil.a(f(), i);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItem(int i, ItemStack itemstack) {
/* 116 */     d((EntityHuman)null);
/* 117 */     f().set(i, itemstack);
/* 118 */     if (itemstack.getCount() > getMaxStackSize()) {
/* 119 */       itemstack.setCount(getMaxStackSize());
/*     */     }
/*     */     
/* 122 */     update();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/* 127 */     return (this.world.getTileEntity(this.position) != this) ? false : ((entityhuman.h(this.position.getX() + 0.5D, this.position.getY() + 0.5D, this.position.getZ() + 0.5D) <= 64.0D));
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 132 */     f().clear();
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract NonNullList<ItemStack> f();
/*     */   
/*     */   protected abstract void a(NonNullList<ItemStack> paramNonNullList);
/*     */   
/*     */   public boolean e(EntityHuman entityhuman) {
/* 141 */     return (super.e(entityhuman) && (this.lootTable == null || !entityhuman.isSpectator()));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Container createMenu(int i, PlayerInventory playerinventory, EntityHuman entityhuman) {
/* 147 */     if (e(entityhuman)) {
/* 148 */       d(playerinventory.player);
/* 149 */       return createContainer(i, playerinventory);
/*     */     } 
/* 151 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityLootable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */