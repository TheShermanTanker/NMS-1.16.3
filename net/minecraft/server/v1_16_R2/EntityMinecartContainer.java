/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftEntity;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftHumanEntity;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ 
/*     */ 
/*     */ public abstract class EntityMinecartContainer
/*     */   extends EntityMinecartAbstract
/*     */   implements IInventory, ITileInventory
/*     */ {
/*     */   private NonNullList<ItemStack> items;
/*     */   private boolean c;
/*     */   @Nullable
/*     */   public MinecraftKey lootTable;
/*     */   public long lootTableSeed;
/*  23 */   public List<HumanEntity> transaction = new ArrayList<>();
/*  24 */   private int maxStack = 64;
/*     */   
/*     */   public List<ItemStack> getContents() {
/*  27 */     return this.items;
/*     */   }
/*     */   
/*     */   public void onOpen(CraftHumanEntity who) {
/*  31 */     this.transaction.add(who);
/*     */   }
/*     */   
/*     */   public void onClose(CraftHumanEntity who) {
/*  35 */     this.transaction.remove(who);
/*     */   }
/*     */   
/*     */   public List<HumanEntity> getViewers() {
/*  39 */     return this.transaction;
/*     */   }
/*     */   
/*     */   public InventoryHolder getOwner() {
/*  43 */     CraftEntity craftEntity = getBukkitEntity();
/*  44 */     if (craftEntity instanceof InventoryHolder) return (InventoryHolder)craftEntity; 
/*  45 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxStackSize() {
/*  50 */     return this.maxStack;
/*     */   }
/*     */   
/*     */   public void setMaxStackSize(int size) {
/*  54 */     this.maxStack = size;
/*     */   }
/*     */ 
/*     */   
/*     */   public Location getLocation() {
/*  59 */     return getBukkitEntity().getLocation();
/*     */   }
/*     */ 
/*     */   
/*     */   protected EntityMinecartContainer(EntityTypes<?> entitytypes, World world) {
/*  64 */     super(entitytypes, world);
/*  65 */     this.items = NonNullList.a(getSize(), ItemStack.b);
/*  66 */     this.c = true;
/*     */   }
/*     */   
/*     */   protected EntityMinecartContainer(EntityTypes<?> entitytypes, double d0, double d1, double d2, World world) {
/*  70 */     super(entitytypes, world, d0, d1, d2);
/*  71 */     this.items = NonNullList.a(getSize(), ItemStack.b);
/*  72 */     this.c = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(DamageSource damagesource) {
/*  77 */     super.a(damagesource);
/*  78 */     if (this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
/*  79 */       InventoryUtils.dropEntity(this.world, this, this);
/*  80 */       if (!this.world.isClientSide) {
/*  81 */         Entity entity = damagesource.j();
/*     */         
/*  83 */         if (entity != null && entity.getEntityType() == EntityTypes.PLAYER) {
/*  84 */           PiglinAI.a((EntityHuman)entity, true);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/*     */     ItemStack itemstack;
/*  93 */     Iterator<ItemStack> iterator = this.items.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/*  98 */       if (!iterator.hasNext()) {
/*  99 */         return true;
/*     */       }
/*     */       
/* 102 */       itemstack = iterator.next();
/* 103 */     } while (itemstack.isEmpty());
/*     */     
/* 105 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItem(int i) {
/* 110 */     d((EntityHuman)null);
/* 111 */     return this.items.get(i);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack splitStack(int i, int j) {
/* 116 */     d((EntityHuman)null);
/* 117 */     return ContainerUtil.a(this.items, i, j);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack splitWithoutUpdate(int i) {
/* 122 */     d((EntityHuman)null);
/* 123 */     ItemStack itemstack = this.items.get(i);
/*     */     
/* 125 */     if (itemstack.isEmpty()) {
/* 126 */       return ItemStack.b;
/*     */     }
/* 128 */     this.items.set(i, ItemStack.b);
/* 129 */     return itemstack;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setItem(int i, ItemStack itemstack) {
/* 135 */     d((EntityHuman)null);
/* 136 */     this.items.set(i, itemstack);
/* 137 */     if (!itemstack.isEmpty() && itemstack.getCount() > getMaxStackSize()) {
/* 138 */       itemstack.setCount(getMaxStackSize());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a_(int i, ItemStack itemstack) {
/* 145 */     if (i >= 0 && i < getSize()) {
/* 146 */       setItem(i, itemstack);
/* 147 */       return true;
/*     */     } 
/* 149 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {}
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/* 158 */     return this.dead ? false : ((entityhuman.h(this) <= 64.0D));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity b(WorldServer worldserver) {
/* 164 */     this.c = false;
/* 165 */     return super.b(worldserver);
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 170 */     if (!this.world.isClientSide && this.c) {
/* 171 */       InventoryUtils.dropEntity(this.world, this, this);
/*     */     }
/*     */     
/* 174 */     super.die();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void saveData(NBTTagCompound nbttagcompound) {
/* 179 */     super.saveData(nbttagcompound);
/* 180 */     this.lootableData.saveNbt(nbttagcompound);
/* 181 */     if (this.lootTable != null) {
/* 182 */       nbttagcompound.setString("LootTable", this.lootTable.toString());
/* 183 */       if (this.lootTableSeed != 0L) {
/* 184 */         nbttagcompound.setLong("LootTableSeed", this.lootTableSeed);
/*     */       }
/*     */     } 
/* 187 */     ContainerUtil.a(nbttagcompound, this.items);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void loadData(NBTTagCompound nbttagcompound) {
/* 194 */     super.loadData(nbttagcompound);
/* 195 */     this.lootableData.loadNbt(nbttagcompound);
/* 196 */     this.items = NonNullList.a(getSize(), ItemStack.b);
/* 197 */     if (nbttagcompound.hasKeyOfType("LootTable", 8)) {
/* 198 */       this.lootTable = new MinecraftKey(nbttagcompound.getString("LootTable"));
/* 199 */       this.lootTableSeed = nbttagcompound.getLong("LootTableSeed");
/*     */     } 
/* 201 */     ContainerUtil.b(nbttagcompound, this.items);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumInteractionResult a(EntityHuman entityhuman, EnumHand enumhand) {
/* 208 */     entityhuman.openContainer(this);
/* 209 */     if (!entityhuman.world.isClientSide) {
/* 210 */       PiglinAI.a(entityhuman, true);
/* 211 */       return EnumInteractionResult.CONSUME;
/*     */     } 
/* 213 */     return EnumInteractionResult.SUCCESS;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void decelerate() {
/* 219 */     float f = 0.98F;
/*     */     
/* 221 */     if (this.lootTable == null) {
/* 222 */       int i = 15 - Container.b(this);
/*     */       
/* 224 */       f += i * 0.001F;
/*     */     } 
/*     */     
/* 227 */     setMot(getMot().d(f, 0.0D, f));
/*     */   }
/*     */   
/*     */   public void d(@Nullable EntityHuman entityhuman) {
/* 231 */     if (this.lootableData.shouldReplenish(entityhuman) && this.world.getMinecraftServer() != null) {
/* 232 */       LootTable loottable = this.world.getMinecraftServer().getLootTableRegistry().getLootTable(this.lootTable);
/*     */       
/* 234 */       if (entityhuman instanceof EntityPlayer) {
/* 235 */         CriterionTriggers.N.a((EntityPlayer)entityhuman, this.lootTable);
/*     */       }
/*     */ 
/*     */       
/* 239 */       this.lootableData.processRefill(entityhuman);
/* 240 */       LootTableInfo.Builder loottableinfo_builder = (new LootTableInfo.Builder((WorldServer)this.world)).<Vec3D>set(LootContextParameters.ORIGIN, getPositionVector()).a(this.lootTableSeed);
/*     */       
/* 242 */       if (entityhuman != null) {
/* 243 */         loottableinfo_builder.a(entityhuman.eT()).set(LootContextParameters.THIS_ENTITY, entityhuman);
/*     */       }
/*     */       
/* 246 */       loottable.fillInventory(this, loottableinfo_builder.build(LootContextParameterSets.CHEST));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 253 */     d((EntityHuman)null);
/* 254 */     this.items.clear();
/*     */   }
/*     */   
/*     */   public void setLootTable(MinecraftKey minecraftkey, long i) {
/* 258 */     this.lootTable = minecraftkey;
/* 259 */     this.lootTableSeed = i;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Container createMenu(int i, PlayerInventory playerinventory, EntityHuman entityhuman) {
/* 265 */     if (this.lootTable != null && entityhuman.isSpectator()) {
/* 266 */       return null;
/*     */     }
/* 268 */     d(playerinventory.player);
/* 269 */     return a(i, playerinventory);
/*     */   }
/*     */   
/*     */   protected abstract Container a(int paramInt, PlayerInventory paramPlayerInventory);
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityMinecartContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */