/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.tuinity.tuinity.config.TuinityConfig;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockState;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlock;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.block.BlockBreakEvent;
/*     */ import org.bukkit.event.block.BlockDamageEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ 
/*     */ public class PlayerInteractManager {
/*  18 */   private static final Logger LOGGER = LogManager.getLogger(); public WorldServer world; public EntityPlayer player; private EnumGamemode gamemode;
/*     */   private EnumGamemode e;
/*     */   private boolean f;
/*     */   private int lastDigTick;
/*     */   private long lastDigTime;
/*     */   private BlockPosition h;
/*     */   private int currentTick;
/*     */   
/*     */   private final boolean hasDestroyedTooFast() {
/*  27 */     return this.j;
/*     */   } private boolean j; private BlockPosition k; private int l; private long hasDestroyedTooFastStartTime; private int m; public boolean interactResult; public boolean firedInteract; public BlockPosition interactPosition; public EnumHand interactHand; public ItemStack interactItemStack; private final int getHasDestroyedTooFastStartTick() {
/*  29 */     return this.l;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int getTimeDiggingLagCompensate() {
/*  35 */     int lagCompensated = (int)((System.nanoTime() - this.lastDigTime) / 50000000L);
/*  36 */     int tickDiff = this.currentTick - this.lastDigTick;
/*  37 */     return (TuinityConfig.lagCompensateBlockBreaking && lagCompensated > tickDiff + 1) ? lagCompensated : tickDiff;
/*     */   }
/*     */   
/*     */   private int getTimeDiggingTooFastLagCompensate() {
/*  41 */     int lagCompensated = (int)((System.nanoTime() - this.hasDestroyedTooFastStartTime) / 50000000L);
/*  42 */     int tickDiff = this.currentTick - getHasDestroyedTooFastStartTick();
/*  43 */     return (TuinityConfig.lagCompensateBlockBreaking && lagCompensated > tickDiff + 1) ? lagCompensated : tickDiff;
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
/*     */   public void setGameMode(EnumGamemode enumgamemode) {
/*     */     a(enumgamemode, (enumgamemode != this.gamemode) ? this.gamemode : this.e);
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
/*     */   public void a(EnumGamemode enumgamemode, EnumGamemode enumgamemode1) {
/*     */     this.e = enumgamemode1;
/*     */     this.gamemode = enumgamemode;
/*     */     enumgamemode.a(this.player.abilities);
/*     */     this.player.updateAbilities();
/*     */     this.player.server.getPlayerList().sendAll(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.UPDATE_GAME_MODE, new EntityPlayer[] { this.player }), this.player);
/*     */     this.world.everyoneSleeping();
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
/*     */   public EnumGamemode getGameMode() {
/*     */     return this.gamemode;
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
/*     */   public EnumGamemode c() {
/*     */     return this.e;
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
/*     */   public PlayerInteractManager(WorldServer worldserver) {
/* 477 */     this.interactResult = false;
/* 478 */     this.firedInteract = false; this.gamemode = EnumGamemode.NOT_SET; this.e = EnumGamemode.NOT_SET;
/*     */     this.h = BlockPosition.ZERO;
/*     */     this.k = BlockPosition.ZERO;
/*     */     this.m = -1;
/*     */     this.world = worldserver;
/* 483 */   } public boolean d() { return this.gamemode.f(); } public EnumInteractionResult a(EntityPlayer entityplayer, World world, ItemStack itemstack, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) { BlockPosition blockposition = movingobjectpositionblock.getBlockPosition();
/* 484 */     IBlockData iblockdata = world.getType(blockposition);
/* 485 */     EnumInteractionResult enuminteractionresult = EnumInteractionResult.PASS;
/* 486 */     boolean cancelledBlock = false;
/*     */     
/* 488 */     if (this.gamemode == EnumGamemode.SPECTATOR) {
/* 489 */       ITileInventory itileinventory = iblockdata.b(world, blockposition);
/* 490 */       cancelledBlock = !(itileinventory instanceof ITileInventory);
/*     */     } 
/*     */     
/* 493 */     if (entityplayer.getCooldownTracker().hasCooldown(itemstack.getItem())) {
/* 494 */       cancelledBlock = true;
/*     */     }
/*     */     
/* 497 */     PlayerInteractEvent event = CraftEventFactory.callPlayerInteractEvent(entityplayer, Action.RIGHT_CLICK_BLOCK, blockposition, movingobjectpositionblock.getDirection(), itemstack, cancelledBlock, enumhand);
/* 498 */     this.firedInteract = true;
/* 499 */     this.interactResult = (event.useItemInHand() == Event.Result.DENY);
/* 500 */     this.interactPosition = blockposition.immutableCopy();
/* 501 */     this.interactHand = enumhand;
/* 502 */     this.interactItemStack = itemstack.cloneItemStack();
/*     */     
/* 504 */     if (event.useInteractedBlock() == Event.Result.DENY)
/*     */     
/*     */     { 
/* 507 */       if (iblockdata.getBlock() instanceof BlockDoor) {
/* 508 */         boolean bottom = (iblockdata.get(BlockDoor.HALF) == BlockPropertyDoubleBlockHalf.LOWER);
/* 509 */         entityplayer.playerConnection.sendPacket(new PacketPlayOutBlockChange(world, bottom ? blockposition.up() : blockposition.down()));
/* 510 */       } else if (iblockdata.getBlock() instanceof BlockCake) {
/* 511 */         entityplayer.getBukkitEntity().sendHealthUpdate();
/*     */       }
/* 513 */       else if (iblockdata.getBlock() instanceof BlockStructure) {
/* 514 */         entityplayer.playerConnection.sendPacket(new PacketPlayOutCloseWindow());
/* 515 */       } else if (iblockdata.getBlock() instanceof BlockCommand) {
/* 516 */         entityplayer.playerConnection.sendPacket(new PacketPlayOutCloseWindow());
/* 517 */       } else if (iblockdata.getBlock() instanceof BlockFlowerPot) {
/*     */         
/* 519 */         PacketPlayOutBlockChange packet = new PacketPlayOutBlockChange(this.world, blockposition);
/* 520 */         packet.block = Blocks.AIR.getBlockData();
/* 521 */         this.player.playerConnection.sendPacket(packet);
/*     */         
/* 523 */         this.player.playerConnection.sendPacket(new PacketPlayOutBlockChange(this.world, blockposition));
/*     */         
/* 525 */         TileEntity tileentity = this.world.getTileEntity(blockposition);
/* 526 */         if (tileentity != null) {
/* 527 */           this.player.playerConnection.sendPacket(tileentity.getUpdatePacket());
/*     */         }
/*     */       } 
/*     */       
/* 531 */       entityplayer.getBukkitEntity().updateInventory();
/* 532 */       enuminteractionresult = (event.useItemInHand() != Event.Result.ALLOW) ? EnumInteractionResult.SUCCESS : EnumInteractionResult.PASS; }
/* 533 */     else { if (this.gamemode == EnumGamemode.SPECTATOR) {
/* 534 */         ITileInventory itileinventory = iblockdata.b(world, blockposition);
/*     */         
/* 536 */         if (itileinventory != null) {
/* 537 */           entityplayer.openContainer(itileinventory);
/* 538 */           return EnumInteractionResult.SUCCESS;
/*     */         } 
/* 540 */         return EnumInteractionResult.PASS;
/*     */       } 
/*     */       
/* 543 */       boolean flag = (!entityplayer.getItemInMainHand().isEmpty() || !entityplayer.getItemInOffHand().isEmpty());
/* 544 */       boolean flag1 = (entityplayer.ep() && flag);
/* 545 */       ItemStack itemstack1 = itemstack.cloneItemStack();
/*     */       
/* 547 */       if (!flag1) {
/* 548 */         enuminteractionresult = iblockdata.interact(world, entityplayer, enumhand, movingobjectpositionblock);
/*     */         
/* 550 */         if (enuminteractionresult.a()) {
/* 551 */           CriterionTriggers.M.a(entityplayer, blockposition, itemstack1);
/* 552 */           return enuminteractionresult;
/*     */         } 
/*     */       } 
/*     */       
/* 556 */       if (!itemstack.isEmpty() && enuminteractionresult != EnumInteractionResult.SUCCESS && !this.interactResult) {
/* 557 */         EnumInteractionResult enuminteractionresult1; ItemActionContext itemactioncontext = new ItemActionContext(entityplayer, enumhand, movingobjectpositionblock);
/*     */ 
/*     */         
/* 560 */         if (isCreative()) {
/* 561 */           int i = itemstack.getCount();
/*     */           
/* 563 */           enuminteractionresult1 = itemstack.placeItem(itemactioncontext, enumhand);
/* 564 */           itemstack.setCount(i);
/*     */         } else {
/* 566 */           enuminteractionresult1 = itemstack.placeItem(itemactioncontext, enumhand);
/*     */         } 
/*     */         
/* 569 */         if (enuminteractionresult1.a()) {
/* 570 */           CriterionTriggers.M.a(entityplayer, blockposition, itemstack1);
/*     */         }
/*     */         
/* 573 */         return enuminteractionresult1;
/*     */       }  }
/*     */     
/* 576 */     return enuminteractionresult; } public boolean isCreative() { return this.gamemode.isCreative(); }
/*     */   public void b(EnumGamemode enumgamemode) { if (this.gamemode == EnumGamemode.NOT_SET)
/*     */       this.gamemode = enumgamemode; 
/*     */     setGameMode(this.gamemode); }
/*     */   public void a(WorldServer worldserver) {
/* 581 */     this.world = worldserver;
/*     */   }
/*     */   
/*     */   public void a() {
/*     */     this.currentTick = MinecraftServer.currentTick;
/*     */     if (this.j) {
/*     */       IBlockData iblockdata = this.world.getTypeIfLoaded(this.k);
/*     */       if (iblockdata == null || iblockdata.isAir()) {
/*     */         this.j = false;
/*     */       } else {
/*     */         float f = updateBlockBreakAnimation(iblockdata, this.k, getTimeDiggingTooFastLagCompensate());
/*     */         if (f >= 1.0F) {
/*     */           this.j = false;
/*     */           breakBlock(this.k);
/*     */         } 
/*     */       } 
/*     */     } else if (this.f) {
/*     */       IBlockData iblockdata = this.world.getTypeIfLoaded(this.h);
/*     */       if (iblockdata == null) {
/*     */         this.f = false;
/*     */         return;
/*     */       } 
/*     */       if (iblockdata.isAir()) {
/*     */         this.world.a(this.player.getId(), this.h, -1);
/*     */         this.m = -1;
/*     */         this.f = false;
/*     */       } else {
/*     */         updateBlockBreakAnimation(iblockdata, this.h, getTimeDiggingLagCompensate());
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private float a(IBlockData iblockdata, BlockPosition blockposition, int i) {
/*     */     int j = this.currentTick - i;
/*     */     return updateBlockBreakAnimation(iblockdata, blockposition, j);
/*     */   }
/*     */   
/*     */   private float updateBlockBreakAnimation(IBlockData iblockdata, BlockPosition blockposition, int totalTime) {
/*     */     int j = totalTime;
/*     */     float f = iblockdata.getDamage(this.player, this.player.world, blockposition) * (j + 1);
/*     */     int k = (int)(f * 10.0F);
/*     */     if (k != this.m) {
/*     */       this.world.a(this.player.getId(), blockposition, k);
/*     */       this.m = k;
/*     */     } 
/*     */     return f;
/*     */   }
/*     */   
/*     */   public void a(BlockPosition blockposition, PacketPlayInBlockDig.EnumPlayerDigType packetplayinblockdig_enumplayerdigtype, EnumDirection enumdirection, int i) {
/*     */     double d0 = this.player.locX() - blockposition.getX() + 0.5D;
/*     */     double d1 = this.player.locY() - blockposition.getY() + 0.5D + 1.5D;
/*     */     double d2 = this.player.locZ() - blockposition.getZ() + 0.5D;
/*     */     double d3 = d0 * d0 + d1 * d1 + d2 * d2;
/*     */     if (d3 > 36.0D) {
/*     */       this.player.playerConnection.sendPacket(new PacketPlayOutBlockBreak(blockposition, this.world.getType(blockposition), packetplayinblockdig_enumplayerdigtype, false, "too far"));
/*     */     } else if (blockposition.getY() >= i) {
/*     */       this.player.playerConnection.sendPacket(new PacketPlayOutBlockBreak(blockposition, this.world.getType(blockposition), packetplayinblockdig_enumplayerdigtype, false, "too high"));
/*     */     } else if (packetplayinblockdig_enumplayerdigtype == PacketPlayInBlockDig.EnumPlayerDigType.START_DESTROY_BLOCK) {
/*     */       if (!this.world.a(this.player, blockposition)) {
/*     */         CraftEventFactory.callPlayerInteractEvent(this.player, Action.LEFT_CLICK_BLOCK, blockposition, enumdirection, this.player.inventory.getItemInHand(), EnumHand.MAIN_HAND);
/*     */         this.player.playerConnection.sendPacket(new PacketPlayOutBlockBreak(blockposition, this.world.getType(blockposition), packetplayinblockdig_enumplayerdigtype, false, "may not interact"));
/*     */         TileEntity tileentity = this.world.getTileEntity(blockposition);
/*     */         if (tileentity != null)
/*     */           this.player.playerConnection.sendPacket(tileentity.getUpdatePacket()); 
/*     */         return;
/*     */       } 
/*     */       PlayerInteractEvent event = CraftEventFactory.callPlayerInteractEvent(this.player, Action.LEFT_CLICK_BLOCK, blockposition, enumdirection, this.player.inventory.getItemInHand(), EnumHand.MAIN_HAND);
/*     */       if (event.isCancelled()) {
/*     */         for (EnumDirection dir : EnumDirection.values())
/*     */           this.player.playerConnection.sendPacket(new PacketPlayOutBlockChange(this.world, blockposition.shift(dir))); 
/*     */         this.player.playerConnection.sendPacket(new PacketPlayOutBlockChange(this.world, blockposition));
/*     */         TileEntity tileentity = this.world.getTileEntity(blockposition);
/*     */         if (tileentity != null)
/*     */           this.player.playerConnection.sendPacket(tileentity.getUpdatePacket()); 
/*     */         return;
/*     */       } 
/*     */       if (isCreative()) {
/*     */         a(blockposition, packetplayinblockdig_enumplayerdigtype, "creative destroy");
/*     */         return;
/*     */       } 
/*     */       if (this.player.a(this.world, blockposition, this.gamemode)) {
/*     */         this.player.playerConnection.sendPacket(new PacketPlayOutBlockBreak(blockposition, this.world.getType(blockposition), packetplayinblockdig_enumplayerdigtype, false, "block action restricted"));
/*     */         return;
/*     */       } 
/*     */       this.lastDigTick = this.currentTick;
/*     */       this.lastDigTime = System.nanoTime();
/*     */       float f = 1.0F;
/*     */       IBlockData iblockdata = this.world.getType(blockposition);
/*     */       if (event.useInteractedBlock() == Event.Result.DENY) {
/*     */         IBlockData data = this.world.getType(blockposition);
/*     */         if (data.getBlock() instanceof BlockDoor) {
/*     */           boolean bottom = (data.get(BlockDoor.HALF) == BlockPropertyDoubleBlockHalf.LOWER);
/*     */           this.player.playerConnection.sendPacket(new PacketPlayOutBlockChange(this.world, blockposition));
/*     */           this.player.playerConnection.sendPacket(new PacketPlayOutBlockChange(this.world, bottom ? blockposition.up() : blockposition.down()));
/*     */         } else if (data.getBlock() instanceof BlockTrapdoor) {
/*     */           this.player.playerConnection.sendPacket(new PacketPlayOutBlockChange(this.world, blockposition));
/*     */         } 
/*     */       } else if (!iblockdata.isAir()) {
/*     */         iblockdata.attack(this.world, blockposition, this.player);
/*     */         f = iblockdata.getDamage(this.player, this.player.world, blockposition);
/*     */       } 
/*     */       if (event.useItemInHand() == Event.Result.DENY) {
/*     */         if (f > 1.0F)
/*     */           this.player.playerConnection.sendPacket(new PacketPlayOutBlockChange(this.world, blockposition)); 
/*     */         return;
/*     */       } 
/*     */       BlockDamageEvent blockEvent = CraftEventFactory.callBlockDamageEvent(this.player, blockposition.getX(), blockposition.getY(), blockposition.getZ(), this.player.inventory.getItemInHand(), (f >= 1.0F));
/*     */       if (blockEvent.isCancelled()) {
/*     */         this.player.playerConnection.sendPacket(new PacketPlayOutBlockChange(this.world, blockposition));
/*     */         return;
/*     */       } 
/*     */       if (blockEvent.getInstaBreak())
/*     */         f = 2.0F; 
/*     */       if (!iblockdata.isAir() && f >= 1.0F) {
/*     */         a(blockposition, packetplayinblockdig_enumplayerdigtype, "insta mine");
/*     */       } else {
/*     */         if (this.f)
/*     */           this.player.playerConnection.sendPacket(new PacketPlayOutBlockBreak(this.h, this.world.getType(this.h), PacketPlayInBlockDig.EnumPlayerDigType.START_DESTROY_BLOCK, false, "abort destroying since another started (client insta mine, server disagreed)")); 
/*     */         this.f = true;
/*     */         this.h = blockposition.immutableCopy();
/*     */         int j = (int)(f * 10.0F);
/*     */         this.world.a(this.player.getId(), blockposition, j);
/*     */         if (!TuinityConfig.lagCompensateBlockBreaking)
/*     */           this.player.playerConnection.sendPacket(new PacketPlayOutBlockBreak(blockposition, this.world.getType(blockposition), packetplayinblockdig_enumplayerdigtype, true, "actual start of destroying")); 
/*     */         this.m = j;
/*     */       } 
/*     */     } else if (packetplayinblockdig_enumplayerdigtype == PacketPlayInBlockDig.EnumPlayerDigType.STOP_DESTROY_BLOCK) {
/*     */       if (blockposition.equals(this.h)) {
/*     */         int k = getTimeDiggingLagCompensate();
/*     */         IBlockData iblockdata = this.world.getType(blockposition);
/*     */         if (!iblockdata.isAir()) {
/*     */           float f1 = iblockdata.getDamage(this.player, this.player.world, blockposition) * (k + 1);
/*     */           if (f1 >= 0.7F) {
/*     */             this.f = false;
/*     */             this.world.a(this.player.getId(), blockposition, -1);
/*     */             a(blockposition, packetplayinblockdig_enumplayerdigtype, "destroyed");
/*     */             return;
/*     */           } 
/*     */           if (!this.j) {
/*     */             this.f = false;
/*     */             this.j = true;
/*     */             this.k = blockposition;
/*     */             this.l = this.lastDigTick;
/*     */             this.hasDestroyedTooFastStartTime = this.lastDigTime;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       if (TuinityConfig.lagCompensateBlockBreaking) {
/*     */         this.player.playerConnection.sendPacket(new PacketPlayOutBlockChange(this.world, blockposition));
/*     */       } else {
/*     */         this.player.playerConnection.sendPacket(new PacketPlayOutBlockBreak(blockposition, this.world.getType(blockposition), packetplayinblockdig_enumplayerdigtype, true, "stopped destroying"));
/*     */       } 
/*     */     } else if (packetplayinblockdig_enumplayerdigtype == PacketPlayInBlockDig.EnumPlayerDigType.ABORT_DESTROY_BLOCK) {
/*     */       this.f = false;
/*     */       if (!Objects.equals(this.h, blockposition) && !BlockPosition.ZERO.equals(this.h)) {
/*     */         LOGGER.debug("Mismatch in destroy block pos: " + this.h + " " + blockposition);
/*     */         IBlockData type = this.world.getTypeIfLoaded(this.h);
/*     */         if (type != null)
/*     */           this.world.a(this.player.getId(), this.h, -1); 
/*     */         if (type != null)
/*     */           this.player.playerConnection.sendPacket(new PacketPlayOutBlockBreak(this.h, type, packetplayinblockdig_enumplayerdigtype, true, "aborted mismatched destroying")); 
/*     */         this.h = BlockPosition.ZERO;
/*     */       } 
/*     */       this.world.a(this.player.getId(), blockposition, -1);
/*     */       if (!TuinityConfig.lagCompensateBlockBreaking)
/*     */         this.player.playerConnection.sendPacket(new PacketPlayOutBlockBreak(blockposition, this.world.getType(blockposition), packetplayinblockdig_enumplayerdigtype, true, "aborted destroying")); 
/*     */     } 
/*     */     this.world.chunkPacketBlockController.onPlayerLeftClickBlock(this, blockposition, enumdirection);
/*     */   }
/*     */   
/*     */   public void a(BlockPosition blockposition, PacketPlayInBlockDig.EnumPlayerDigType packetplayinblockdig_enumplayerdigtype, String s) {
/*     */     if (breakBlock(blockposition)) {
/*     */       if (TuinityConfig.lagCompensateBlockBreaking) {
/*     */         this.player.playerConnection.sendPacket(new PacketPlayOutBlockChange(this.world, blockposition));
/*     */       } else {
/*     */         this.player.playerConnection.sendPacket(new PacketPlayOutBlockBreak(blockposition, this.world.getType(blockposition), packetplayinblockdig_enumplayerdigtype, true, s));
/*     */       } 
/*     */     } else {
/*     */       this.player.playerConnection.sendPacket(new PacketPlayOutBlockChange(this.world, blockposition));
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean breakBlock(BlockPosition blockposition) {
/*     */     IBlockData iblockdata = this.world.getType(blockposition);
/*     */     CraftBlock craftBlock = CraftBlock.at(this.world, blockposition);
/*     */     BlockBreakEvent event = null;
/*     */     if (this.player instanceof EntityPlayer) {
/*     */       boolean isSwordNoBreak = !this.player.getItemInMainHand().getItem().a(iblockdata, this.world, blockposition, this.player);
/*     */       if (this.world.getTileEntity(blockposition) == null && !isSwordNoBreak) {
/*     */         PacketPlayOutBlockChange packet = new PacketPlayOutBlockChange(this.world, blockposition);
/*     */         packet.block = Blocks.AIR.getBlockData();
/*     */         this.player.playerConnection.sendPacket(packet);
/*     */       } 
/*     */       event = new BlockBreakEvent((Block)craftBlock, (Player)this.player.getBukkitEntity());
/*     */       event.setCancelled(isSwordNoBreak);
/*     */       IBlockData nmsData = this.world.getType(blockposition);
/*     */       Block nmsBlock = nmsData.getBlock();
/*     */       ItemStack itemstack = this.player.getEquipment(EnumItemSlot.MAINHAND);
/*     */       if (nmsBlock != null && !event.isCancelled() && !isCreative() && this.player.hasBlock(nmsBlock.getBlockData()))
/*     */         event.setExpToDrop(nmsBlock.getExpDrop(nmsData, this.world, blockposition, itemstack)); 
/*     */       this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */       if (event.isCancelled()) {
/*     */         if (isSwordNoBreak)
/*     */           return false; 
/*     */         this.player.playerConnection.sendPacket(new PacketPlayOutBlockChange(this.world, blockposition));
/*     */         for (EnumDirection dir : EnumDirection.values())
/*     */           this.player.playerConnection.sendPacket(new PacketPlayOutBlockChange(this.world, blockposition.shift(dir))); 
/*     */         TileEntity tileEntity = this.world.getTileEntity(blockposition);
/*     */         if (tileEntity != null)
/*     */           this.player.playerConnection.sendPacket(tileEntity.getUpdatePacket()); 
/*     */         return false;
/*     */       } 
/*     */     } 
/*     */     iblockdata = this.world.getType(blockposition);
/*     */     if (iblockdata.isAir())
/*     */       return false; 
/*     */     TileEntity tileentity = this.world.getTileEntity(blockposition);
/*     */     Block block = iblockdata.getBlock();
/*     */     if ((block instanceof BlockCommand || block instanceof BlockStructure || block instanceof BlockJigsaw) && !this.player.isCreativeAndOp() && (!(block instanceof BlockCommand) || !this.player.isCreative() || !this.player.getBukkitEntity().hasPermission("minecraft.commandblock"))) {
/*     */       this.world.notify(blockposition, iblockdata, iblockdata, 3);
/*     */       return false;
/*     */     } 
/*     */     if (this.player.a(this.world, blockposition, this.gamemode))
/*     */       return false; 
/*     */     BlockState state = craftBlock.getState();
/*     */     this.world.captureDrops = new ArrayList<>();
/*     */     block.a(this.world, blockposition, iblockdata, this.player);
/*     */     boolean flag = this.world.a(blockposition, false);
/*     */     if (flag)
/*     */       block.postBreak(this.world, blockposition, iblockdata); 
/*     */     if (!isCreative()) {
/*     */       ItemStack itemstack = this.player.getItemInMainHand();
/*     */       ItemStack itemstack1 = itemstack.cloneItemStack();
/*     */       boolean flag1 = this.player.hasBlock(iblockdata);
/*     */       itemstack.a(this.world, iblockdata, blockposition, this.player);
/*     */       if (flag && flag1 && event.isDropItems())
/*     */         block.a(this.world, this.player, blockposition, iblockdata, tileentity, itemstack1); 
/*     */     } 
/*     */     List<EntityItem> itemsToDrop = this.world.captureDrops;
/*     */     this.world.captureDrops = null;
/*     */     if (event.isDropItems())
/*     */       CraftEventFactory.handleBlockDropItemEvent((Block)craftBlock, state, this.player, itemsToDrop); 
/*     */     if (flag && event != null)
/*     */       iblockdata.getBlock().dropExperience(this.world, blockposition, event.getExpToDrop(), this.player); 
/*     */     return true;
/*     */   }
/*     */   
/*     */   public EnumInteractionResult a(EntityPlayer entityplayer, World world, ItemStack itemstack, EnumHand enumhand) {
/*     */     if (this.gamemode == EnumGamemode.SPECTATOR)
/*     */       return EnumInteractionResult.PASS; 
/*     */     if (entityplayer.getCooldownTracker().hasCooldown(itemstack.getItem()))
/*     */       return EnumInteractionResult.PASS; 
/*     */     int i = itemstack.getCount();
/*     */     int j = itemstack.getDamage();
/*     */     InteractionResultWrapper<ItemStack> interactionresultwrapper = itemstack.a(world, entityplayer, enumhand);
/*     */     ItemStack itemstack1 = interactionresultwrapper.b();
/*     */     if (itemstack1 == itemstack && itemstack1.getCount() == i && itemstack1.k() <= 0 && itemstack1.getDamage() == j)
/*     */       return interactionresultwrapper.a(); 
/*     */     if (interactionresultwrapper.a() == EnumInteractionResult.FAIL && itemstack1.k() > 0 && !entityplayer.isHandRaised())
/*     */       return interactionresultwrapper.a(); 
/*     */     entityplayer.a(enumhand, itemstack1);
/*     */     if (isCreative()) {
/*     */       itemstack1.setCount(i);
/*     */       if (itemstack1.e() && itemstack1.getDamage() != j)
/*     */         itemstack1.setDamage(j); 
/*     */     } 
/*     */     if (itemstack1.isEmpty())
/*     */       entityplayer.a(enumhand, ItemStack.b); 
/*     */     if (!entityplayer.isHandRaised())
/*     */       entityplayer.updateInventory(entityplayer.defaultContainer); 
/*     */     return interactionresultwrapper.a();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PlayerInteractManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */