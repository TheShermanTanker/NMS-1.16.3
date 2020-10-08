/*     */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*     */ import com.google.common.base.Preconditions;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import net.minecraft.server.v1_16_R2.Block;
/*     */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*     */ import net.minecraft.server.v1_16_R2.BlockWorkbench;
/*     */ import net.minecraft.server.v1_16_R2.Blocks;
/*     */ import net.minecraft.server.v1_16_R2.Container;
/*     */ import net.minecraft.server.v1_16_R2.Containers;
/*     */ import net.minecraft.server.v1_16_R2.CraftingManager;
/*     */ import net.minecraft.server.v1_16_R2.Entity;
/*     */ import net.minecraft.server.v1_16_R2.EntityHuman;
/*     */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*     */ import net.minecraft.server.v1_16_R2.EntityPlayer;
/*     */ import net.minecraft.server.v1_16_R2.EntityTypes;
/*     */ import net.minecraft.server.v1_16_R2.IBlockData;
/*     */ import net.minecraft.server.v1_16_R2.IChatBaseComponent;
/*     */ import net.minecraft.server.v1_16_R2.ICrafting;
/*     */ import net.minecraft.server.v1_16_R2.IRecipe;
/*     */ import net.minecraft.server.v1_16_R2.ITileInventory;
/*     */ import net.minecraft.server.v1_16_R2.ItemCooldown;
/*     */ import net.minecraft.server.v1_16_R2.ItemStack;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import net.minecraft.server.v1_16_R2.Packet;
/*     */ import net.minecraft.server.v1_16_R2.PacketPlayInCloseWindow;
/*     */ import net.minecraft.server.v1_16_R2.PacketPlayOutOpenWindow;
/*     */ import net.minecraft.server.v1_16_R2.TileEntity;
/*     */ import net.minecraft.server.v1_16_R2.TileEntitySign;
/*     */ import net.minecraft.server.v1_16_R2.WorldServer;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.Sign;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftSign;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftContainer;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventory;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryPlayer;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftMerchantCustom;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftMagicNumbers;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftNamespacedKey;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Villager;
/*     */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*     */ import org.bukkit.inventory.EntityEquipment;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.MainHand;
/*     */ import org.bukkit.inventory.Merchant;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ import org.bukkit.permissions.PermissibleBase;
/*     */ import org.bukkit.permissions.Permission;
/*     */ import org.bukkit.permissions.PermissionAttachment;
/*     */ import org.bukkit.permissions.PermissionAttachmentInfo;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.spigotmc.AsyncCatcher;
/*     */ 
/*     */ public class CraftHumanEntity extends CraftLivingEntity implements HumanEntity {
/*     */   private CraftInventoryPlayer inventory;
/*  70 */   protected final PermissibleBase perm = new PermissibleBase((ServerOperator)this); private final CraftInventory enderChest;
/*     */   private boolean op;
/*     */   private GameMode mode;
/*     */   
/*     */   public CraftHumanEntity(CraftServer server, EntityHuman entity) {
/*  75 */     super(server, (EntityLiving)entity);
/*  76 */     this.mode = server.getDefaultGameMode();
/*  77 */     this.inventory = new CraftInventoryPlayer(entity.inventory);
/*  78 */     this.enderChest = new CraftInventory((IInventory)entity.getEnderChest());
/*     */   }
/*     */ 
/*     */   
/*     */   public PlayerInventory getInventory() {
/*  83 */     return (PlayerInventory)this.inventory;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityEquipment getEquipment() {
/*  88 */     return (EntityEquipment)this.inventory;
/*     */   }
/*     */ 
/*     */   
/*     */   public Inventory getEnderChest() {
/*  93 */     return (Inventory)this.enderChest;
/*     */   }
/*     */ 
/*     */   
/*     */   public MainHand getMainHand() {
/*  98 */     return (getHandle().getMainHand() == EnumMainHand.LEFT) ? MainHand.LEFT : MainHand.RIGHT;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItemInHand() {
/* 103 */     return getInventory().getItemInHand();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemInHand(ItemStack item) {
/* 108 */     getInventory().setItemInHand(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItemOnCursor() {
/* 113 */     return (ItemStack)CraftItemStack.asCraftMirror((getHandle()).inventory.getCarried());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemOnCursor(ItemStack item) {
/* 118 */     ItemStack stack = CraftItemStack.asNMSCopy(item);
/* 119 */     (getHandle()).inventory.setCarried(stack);
/* 120 */     if (this instanceof CraftPlayer) {
/* 121 */       ((EntityPlayer)getHandle()).broadcastCarriedItem();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSleepTicks() {
/* 127 */     return (getHandle()).sleepTicks;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Location getPotentialBedLocation() {
/* 133 */     EntityPlayer handle = (EntityPlayer)getHandle();
/* 134 */     BlockPosition bed = handle.getSpawn();
/* 135 */     if (bed == null) {
/* 136 */       return null;
/*     */     }
/*     */     
/* 139 */     WorldServer worldServer = handle.server.getWorldServer(handle.getSpawnDimension());
/* 140 */     if (worldServer == null) {
/* 141 */       return null;
/*     */     }
/* 143 */     return new Location((World)worldServer.getWorld(), bed.getX(), bed.getY(), bed.getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean sleep(Location location, boolean force) {
/* 148 */     Preconditions.checkArgument((location != null), "Location cannot be null");
/* 149 */     Preconditions.checkArgument((location.getWorld() != null), "Location needs to be in a world");
/* 150 */     Preconditions.checkArgument(location.getWorld().equals(getWorld()), "Cannot sleep across worlds");
/*     */     
/* 152 */     BlockPosition blockposition = new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ());
/* 153 */     IBlockData iblockdata = (getHandle()).world.getType(blockposition);
/* 154 */     if (!(iblockdata.getBlock() instanceof BlockBed)) {
/* 155 */       return false;
/*     */     }
/*     */     
/* 158 */     if (getHandle().sleep(blockposition, force).left().isPresent()) {
/* 159 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 163 */     iblockdata = (IBlockData)iblockdata.set((IBlockState)BlockBed.OCCUPIED, Boolean.valueOf(true));
/* 164 */     (getHandle()).world.setTypeAndData(blockposition, iblockdata, 4);
/*     */     
/* 166 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void wakeup(boolean setSpawnLocation) {
/* 171 */     Preconditions.checkState(isSleeping(), "Cannot wakeup if not sleeping");
/*     */     
/* 173 */     getHandle().wakeup(true, setSpawnLocation);
/*     */   }
/*     */ 
/*     */   
/*     */   public Location getBedLocation() {
/* 178 */     Preconditions.checkState(isSleeping(), "Not sleeping");
/*     */     
/* 180 */     BlockPosition bed = getHandle().getBedPosition().get();
/* 181 */     return new Location(getWorld(), bed.getX(), bed.getY(), bed.getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 186 */     return getHandle().getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOp() {
/* 191 */     return this.op;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPermissionSet(String name) {
/* 196 */     return this.perm.isPermissionSet(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPermissionSet(Permission perm) {
/* 201 */     return this.perm.isPermissionSet(perm);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasPermission(String name) {
/* 206 */     return this.perm.hasPermission(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasPermission(Permission perm) {
/* 211 */     return this.perm.hasPermission(perm);
/*     */   }
/*     */ 
/*     */   
/*     */   public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
/* 216 */     return this.perm.addAttachment(plugin, name, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public PermissionAttachment addAttachment(Plugin plugin) {
/* 221 */     return this.perm.addAttachment(plugin);
/*     */   }
/*     */ 
/*     */   
/*     */   public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {
/* 226 */     return this.perm.addAttachment(plugin, name, value, ticks);
/*     */   }
/*     */ 
/*     */   
/*     */   public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
/* 231 */     return this.perm.addAttachment(plugin, ticks);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAttachment(PermissionAttachment attachment) {
/* 236 */     this.perm.removeAttachment(attachment);
/*     */   }
/*     */ 
/*     */   
/*     */   public void recalculatePermissions() {
/* 241 */     this.perm.recalculatePermissions();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOp(boolean value) {
/* 246 */     this.op = value;
/* 247 */     this.perm.recalculatePermissions();
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<PermissionAttachmentInfo> getEffectivePermissions() {
/* 252 */     return this.perm.getEffectivePermissions();
/*     */   }
/*     */ 
/*     */   
/*     */   public GameMode getGameMode() {
/* 257 */     return this.mode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGameMode(GameMode mode) {
/* 262 */     if (mode == null) {
/* 263 */       throw new IllegalArgumentException("Mode cannot be null");
/*     */     }
/*     */     
/* 266 */     this.mode = mode;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityHuman getHandle() {
/* 271 */     return (EntityHuman)this.entity;
/*     */   }
/*     */   
/*     */   public void setHandle(EntityHuman entity) {
/* 275 */     setHandle((EntityLiving)entity);
/* 276 */     this.inventory = new CraftInventoryPlayer(entity.inventory);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 281 */     return "CraftHumanEntity{id=" + getEntityId() + "name=" + getName() + '}';
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryView getOpenInventory() {
/* 286 */     return (getHandle()).activeContainer.getBukkitView();
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryView openInventory(Inventory inventory) {
/* 291 */     if (!(getHandle() instanceof EntityPlayer)) return null; 
/* 292 */     EntityPlayer player = (EntityPlayer)getHandle();
/* 293 */     Container formerContainer = (getHandle()).activeContainer;
/*     */     
/* 295 */     ITileInventory iinventory = null;
/* 296 */     if (inventory instanceof CraftInventoryDoubleChest) {
/* 297 */       iinventory = ((CraftInventoryDoubleChest)inventory).tile;
/* 298 */     } else if (inventory instanceof CraftInventory) {
/* 299 */       CraftInventory craft = (CraftInventory)inventory;
/* 300 */       if (craft.getInventory() instanceof ITileInventory) {
/* 301 */         iinventory = (ITileInventory)craft.getInventory();
/*     */       }
/*     */     } 
/*     */     
/* 305 */     if (iinventory instanceof ITileInventory && 
/* 306 */       iinventory instanceof TileEntity) {
/* 307 */       TileEntity te = (TileEntity)iinventory;
/* 308 */       if (!te.hasWorld()) {
/* 309 */         te.setLocation((getHandle()).world, getHandle().getChunkCoordinates());
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 314 */     Containers<?> container = CraftContainer.getNotchInventoryType(inventory);
/* 315 */     if (iinventory instanceof net.minecraft.server.v1_16_R2.TileEntityContainer) {
/* 316 */       getHandle().openContainer(iinventory);
/*     */     } else {
/* 318 */       openCustomInventory(inventory, player, container);
/*     */     } 
/*     */     
/* 321 */     if ((getHandle()).activeContainer == formerContainer) {
/* 322 */       return null;
/*     */     }
/* 324 */     (getHandle()).activeContainer.checkReachable = false;
/* 325 */     return (getHandle()).activeContainer.getBukkitView();
/*     */   }
/*     */   
/*     */   private void openCustomInventory(Inventory inventory, EntityPlayer player, Containers<?> windowType) {
/* 329 */     if (player.playerConnection == null)
/* 330 */       return;  Preconditions.checkArgument((windowType != null), "Unknown windowType");
/* 331 */     CraftContainer craftContainer = new CraftContainer(inventory, getHandle(), player.nextContainerCounter());
/*     */     
/* 333 */     Container container = CraftEventFactory.callInventoryOpenEvent(player, (Container)craftContainer);
/* 334 */     if (container == null)
/*     */       return; 
/* 336 */     String title = container.getBukkitView().getTitle();
/*     */     
/* 338 */     if (!player.isFrozen()) player.playerConnection.sendPacket((Packet)new PacketPlayOutOpenWindow(container.windowId, windowType, CraftChatMessage.fromString(title)[0])); 
/* 339 */     (getHandle()).activeContainer = container;
/* 340 */     (getHandle()).activeContainer.addSlotListener((ICrafting)player);
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryView openWorkbench(Location location, boolean force) {
/* 345 */     if (location == null) {
/* 346 */       location = getLocation();
/*     */     }
/* 348 */     if (!force) {
/* 349 */       Block block = location.getBlock();
/* 350 */       if (block.getType() != Material.CRAFTING_TABLE) {
/* 351 */         return null;
/*     */       }
/*     */     } 
/* 354 */     getHandle().openContainer(((BlockWorkbench)Blocks.CRAFTING_TABLE).getInventory(null, (getHandle()).world, new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ())));
/* 355 */     if (force) {
/* 356 */       (getHandle()).activeContainer.checkReachable = false;
/*     */     }
/* 358 */     return (getHandle()).activeContainer.getBukkitView();
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryView openEnchanting(Location location, boolean force) {
/* 363 */     if (location == null) {
/* 364 */       location = getLocation();
/*     */     }
/* 366 */     if (!force) {
/* 367 */       Block block = location.getBlock();
/* 368 */       if (block.getType() != Material.ENCHANTING_TABLE) {
/* 369 */         return null;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 374 */     BlockPosition pos = new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ());
/* 375 */     getHandle().openContainer(((BlockEnchantmentTable)Blocks.ENCHANTING_TABLE).getInventory(null, (getHandle()).world, pos));
/*     */     
/* 377 */     if (force) {
/* 378 */       (getHandle()).activeContainer.checkReachable = false;
/*     */     }
/* 380 */     return (getHandle()).activeContainer.getBukkitView();
/*     */   }
/*     */   
/*     */   public void openInventory(InventoryView inventory) {
/*     */     CraftContainer craftContainer;
/* 385 */     if (!(getHandle() instanceof EntityPlayer))
/* 386 */       return;  if (((EntityPlayer)getHandle()).playerConnection == null)
/* 387 */       return;  if ((getHandle()).activeContainer != (getHandle()).defaultContainer)
/*     */     {
/* 389 */       ((EntityPlayer)getHandle()).playerConnection.handleContainerClose(new PacketPlayInCloseWindow((getHandle()).activeContainer.windowId), InventoryCloseEvent.Reason.OPEN_NEW);
/*     */     }
/* 391 */     EntityPlayer player = (EntityPlayer)getHandle();
/*     */     
/* 393 */     if (inventory instanceof CraftInventoryView) {
/* 394 */       Container container1 = ((CraftInventoryView)inventory).getHandle();
/*     */     } else {
/* 396 */       craftContainer = new CraftContainer(inventory, getHandle(), player.nextContainerCounter());
/*     */     } 
/*     */ 
/*     */     
/* 400 */     Container container = CraftEventFactory.callInventoryOpenEvent(player, (Container)craftContainer);
/* 401 */     if (container == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 406 */     Containers<?> windowType = CraftContainer.getNotchInventoryType(inventory.getTopInventory());
/* 407 */     String title = inventory.getTitle();
/* 408 */     if (!player.isFrozen()) player.playerConnection.sendPacket((Packet)new PacketPlayOutOpenWindow(container.windowId, windowType, CraftChatMessage.fromString(title)[0])); 
/* 409 */     player.activeContainer = container;
/* 410 */     player.activeContainer.addSlotListener((ICrafting)player);
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryView openMerchant(Villager villager, boolean force) {
/* 415 */     Preconditions.checkNotNull(villager, "villager cannot be null");
/*     */     
/* 417 */     return openMerchant((Merchant)villager, force);
/*     */   }
/*     */   public InventoryView openMerchant(Merchant merchant, boolean force) {
/*     */     CraftMerchantCustom.MinecraftMerchant minecraftMerchant;
/*     */     IChatBaseComponent name;
/* 422 */     Preconditions.checkNotNull(merchant, "merchant cannot be null");
/*     */     
/* 424 */     if (!force && merchant.isTrading())
/* 425 */       return null; 
/* 426 */     if (merchant.isTrading())
/*     */     {
/* 428 */       merchant.getTrader().closeInventory();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 433 */     int level = 1;
/* 434 */     if (merchant instanceof CraftAbstractVillager) {
/* 435 */       EntityVillagerAbstract entityVillagerAbstract = ((CraftAbstractVillager)merchant).getHandle();
/* 436 */       name = ((CraftAbstractVillager)merchant).getHandle().getScoreboardDisplayName();
/* 437 */       if (merchant instanceof CraftVillager) {
/* 438 */         level = ((CraftVillager)merchant).getHandle().getVillagerData().getLevel();
/*     */       }
/* 440 */     } else if (merchant instanceof CraftMerchantCustom) {
/* 441 */       minecraftMerchant = ((CraftMerchantCustom)merchant).getMerchant();
/* 442 */       name = ((CraftMerchantCustom)merchant).getMerchant().getScoreboardDisplayName();
/*     */     } else {
/* 444 */       throw new IllegalArgumentException("Can't open merchant " + merchant.toString());
/*     */     } 
/*     */     
/* 447 */     minecraftMerchant.setTradingPlayer(getHandle());
/* 448 */     minecraftMerchant.openTrade(getHandle(), name, level);
/*     */     
/* 450 */     return (getHandle()).activeContainer.getBukkitView();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InventoryView openAnvil(Location location, boolean force) {
/* 456 */     return openInventory(location, force, Material.ANVIL);
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryView openCartographyTable(Location location, boolean force) {
/* 461 */     return openInventory(location, force, Material.CARTOGRAPHY_TABLE);
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryView openGrindstone(Location location, boolean force) {
/* 466 */     return openInventory(location, force, Material.GRINDSTONE);
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryView openLoom(Location location, boolean force) {
/* 471 */     return openInventory(location, force, Material.LOOM);
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryView openSmithingTable(Location location, boolean force) {
/* 476 */     return openInventory(location, force, Material.SMITHING_TABLE);
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryView openStonecutter(Location location, boolean force) {
/* 481 */     return openInventory(location, force, Material.STONECUTTER);
/*     */   }
/*     */   private InventoryView openInventory(Location location, boolean force, Material material) {
/*     */     Block block;
/* 485 */     AsyncCatcher.catchOp("open" + material);
/* 486 */     if (location == null) {
/* 487 */       location = getLocation();
/*     */     }
/* 489 */     if (!force) {
/* 490 */       Block block1 = location.getBlock();
/* 491 */       if (block1.getType() != material) {
/* 492 */         return null;
/*     */       }
/*     */     } 
/*     */     
/* 496 */     if (material == Material.ANVIL) {
/* 497 */       block = Blocks.ANVIL;
/* 498 */     } else if (material == Material.CARTOGRAPHY_TABLE) {
/* 499 */       block = Blocks.CARTOGRAPHY_TABLE;
/* 500 */     } else if (material == Material.GRINDSTONE) {
/* 501 */       block = Blocks.GRINDSTONE;
/* 502 */     } else if (material == Material.LOOM) {
/* 503 */       block = Blocks.LOOM;
/* 504 */     } else if (material == Material.SMITHING_TABLE) {
/* 505 */       block = Blocks.SMITHING_TABLE;
/* 506 */     } else if (material == Material.STONECUTTER) {
/* 507 */       block = Blocks.STONECUTTER;
/*     */     } else {
/* 509 */       throw new IllegalArgumentException("Unsupported inventory type: " + material);
/*     */     } 
/* 511 */     getHandle().openContainer(block.getInventory(null, (getHandle()).world, new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ())));
/* 512 */     (getHandle()).activeContainer.checkReachable = !force;
/* 513 */     return (getHandle()).activeContainer.getBukkitView();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void closeInventory() {
/* 520 */     getHandle().closeInventory(InventoryCloseEvent.Reason.PLUGIN);
/*     */   }
/*     */   public void closeInventory(InventoryCloseEvent.Reason reason) {
/* 523 */     getHandle().closeInventory(reason);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBlocking() {
/* 529 */     return getHandle().isBlocking();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHandRaised() {
/* 534 */     return getHandle().isHandRaised();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setWindowProperty(InventoryView.Property prop, int value) {
/* 539 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExpToLevel() {
/* 544 */     return getHandle().getExpToLevel();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAttackCooldown() {
/* 549 */     return getHandle().getAttackCooldown(0.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasCooldown(Material material) {
/* 554 */     Preconditions.checkArgument((material != null), "material");
/*     */     
/* 556 */     return getHandle().getCooldownTracker().hasCooldown(CraftMagicNumbers.getItem(material));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCooldown(Material material) {
/* 561 */     Preconditions.checkArgument((material != null), "material");
/*     */     
/* 563 */     ItemCooldown.Info cooldown = (ItemCooldown.Info)(getHandle().getCooldownTracker()).cooldowns.get(CraftMagicNumbers.getItem(material));
/* 564 */     return (cooldown == null) ? 0 : Math.max(0, cooldown.endTick - (getHandle().getCooldownTracker()).currentTick);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCooldown(Material material, int ticks) {
/* 569 */     Preconditions.checkArgument((material != null), "material");
/* 570 */     Preconditions.checkArgument((ticks >= 0), "Cannot have negative cooldown");
/*     */     
/* 572 */     getHandle().getCooldownTracker().setCooldown(CraftMagicNumbers.getItem(material), ticks);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity releaseLeftShoulderEntity() {
/* 578 */     if (!getHandle().getShoulderEntityLeft().isEmpty()) {
/* 579 */       Entity entity = getHandle().releaseLeftShoulderEntity();
/* 580 */       if (entity != null) {
/* 581 */         return entity.getBukkitEntity();
/*     */       }
/*     */     } 
/*     */     
/* 585 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity releaseRightShoulderEntity() {
/* 590 */     if (!getHandle().getShoulderEntityRight().isEmpty()) {
/* 591 */       Entity entity = getHandle().releaseRightShoulderEntity();
/* 592 */       if (entity != null) {
/* 593 */         return entity.getBukkitEntity();
/*     */       }
/*     */     } 
/*     */     
/* 597 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean discoverRecipe(NamespacedKey recipe) {
/* 603 */     return (discoverRecipes(Arrays.asList(new NamespacedKey[] { recipe })) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int discoverRecipes(Collection<NamespacedKey> recipes) {
/* 608 */     return getHandle().discoverRecipes(bukkitKeysToMinecraftRecipes(recipes));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean undiscoverRecipe(NamespacedKey recipe) {
/* 613 */     return (undiscoverRecipes(Arrays.asList(new NamespacedKey[] { recipe })) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int undiscoverRecipes(Collection<NamespacedKey> recipes) {
/* 618 */     return getHandle().undiscoverRecipes(bukkitKeysToMinecraftRecipes(recipes));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasDiscoveredRecipe(NamespacedKey recipe) {
/* 623 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<NamespacedKey> getDiscoveredRecipes() {
/* 628 */     return (Set<NamespacedKey>)ImmutableSet.of();
/*     */   }
/*     */   
/*     */   private Collection<IRecipe<?>> bukkitKeysToMinecraftRecipes(Collection<NamespacedKey> recipeKeys) {
/* 632 */     Collection<IRecipe<?>> recipes = new ArrayList<>();
/* 633 */     CraftingManager manager = (getHandle()).world.getMinecraftServer().getCraftingManager();
/*     */     
/* 635 */     for (NamespacedKey recipeKey : recipeKeys) {
/* 636 */       Optional<? extends IRecipe<?>> recipe = manager.getRecipe(CraftNamespacedKey.toMinecraft(recipeKey));
/* 637 */       if (!recipe.isPresent()) {
/*     */         continue;
/*     */       }
/*     */       
/* 641 */       recipes.add(recipe.get());
/*     */     } 
/*     */     
/* 644 */     return recipes;
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity getShoulderEntityLeft() {
/* 649 */     if (!getHandle().getShoulderEntityLeft().isEmpty()) {
/* 650 */       Optional<Entity> shoulder = EntityTypes.a(getHandle().getShoulderEntityLeft(), (getHandle()).world);
/*     */       
/* 652 */       return !shoulder.isPresent() ? null : ((Entity)shoulder.get()).getBukkitEntity();
/*     */     } 
/*     */     
/* 655 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShoulderEntityLeft(Entity entity) {
/* 660 */     getHandle().setShoulderEntityLeft((entity == null) ? new NBTTagCompound() : ((CraftEntity)entity).save());
/* 661 */     if (entity != null) {
/* 662 */       entity.remove();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity getShoulderEntityRight() {
/* 668 */     if (!getHandle().getShoulderEntityRight().isEmpty()) {
/* 669 */       Optional<Entity> shoulder = EntityTypes.a(getHandle().getShoulderEntityRight(), (getHandle()).world);
/*     */       
/* 671 */       return !shoulder.isPresent() ? null : ((Entity)shoulder.get()).getBukkitEntity();
/*     */     } 
/*     */     
/* 674 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShoulderEntityRight(Entity entity) {
/* 679 */     getHandle().setShoulderEntityRight((entity == null) ? new NBTTagCompound() : ((CraftEntity)entity).save());
/* 680 */     if (entity != null) {
/* 681 */       entity.remove();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void openSign(Sign sign) {
/* 688 */     Validate.isTrue(sign.getWorld().equals(getWorld()), "Sign must be in the same world as player is in");
/* 689 */     CraftSign craftSign = (CraftSign)sign;
/* 690 */     TileEntitySign teSign = (TileEntitySign)craftSign.getTileEntity();
/*     */     
/* 692 */     teSign.isEditable = true;
/* 693 */     getHandle().openSign(teSign);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean dropItem(boolean dropAll) {
/* 698 */     return getHandle().dropItem(dropAll);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftHumanEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */