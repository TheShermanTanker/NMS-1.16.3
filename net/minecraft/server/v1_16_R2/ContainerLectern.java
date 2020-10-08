/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryLectern;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryView;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.player.PlayerTakeLecternBookEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ 
/*     */ public class ContainerLectern extends Container {
/*  14 */   private CraftInventoryView bukkitEntity = null;
/*     */   
/*     */   private Player player;
/*     */   
/*     */   public CraftInventoryView getBukkitView() {
/*  19 */     if (this.bukkitEntity != null) {
/*  20 */       return this.bukkitEntity;
/*     */     }
/*     */     
/*  23 */     CraftInventoryLectern inventory = new CraftInventoryLectern(this.inventory);
/*  24 */     this.bukkitEntity = new CraftInventoryView((HumanEntity)this.player, (Inventory)inventory, this);
/*  25 */     return this.bukkitEntity;
/*     */   }
/*     */ 
/*     */   
/*     */   private final IInventory inventory;
/*     */   private final IContainerProperties containerProperties;
/*     */   
/*     */   public ContainerLectern(int i, PlayerInventory playerinventory) {
/*  33 */     this(i, new InventorySubcontainer(1), new ContainerProperties(1), playerinventory);
/*     */   }
/*     */ 
/*     */   
/*     */   public ContainerLectern(int i, IInventory iinventory, IContainerProperties icontainerproperties, PlayerInventory playerinventory) {
/*  38 */     super(Containers.LECTERN, i);
/*  39 */     a(iinventory, 1);
/*  40 */     a(icontainerproperties, 1);
/*  41 */     this.inventory = iinventory;
/*  42 */     this.containerProperties = icontainerproperties;
/*  43 */     a(new Slot(iinventory, 0, 0, 0)
/*     */         {
/*     */           public void d() {
/*  46 */             super.d();
/*  47 */             ContainerLectern.this.a(this.inventory);
/*     */           }
/*     */         });
/*  50 */     a(icontainerproperties);
/*  51 */     this.player = (Player)playerinventory.player.getBukkitEntity();
/*     */   }
/*     */   
/*     */   public boolean a(EntityHuman entityhuman, int i) {
/*     */     int j;
/*     */     PlayerTakeLecternBookEvent event;
/*     */     ItemStack itemstack;
/*  58 */     if (i >= 100) {
/*  59 */       int k = i - 100;
/*  60 */       a(0, k);
/*  61 */       return true;
/*     */     } 
/*  63 */     switch (i) {
/*     */       case 1:
/*  65 */         j = this.containerProperties.getProperty(0);
/*  66 */         a(0, j - 1);
/*  67 */         return true;
/*     */       case 2:
/*  69 */         j = this.containerProperties.getProperty(0);
/*  70 */         a(0, j + 1);
/*  71 */         return true;
/*     */       case 3:
/*  73 */         if (!entityhuman.eJ()) {
/*  74 */           return false;
/*     */         }
/*     */ 
/*     */         
/*  78 */         event = new PlayerTakeLecternBookEvent(this.player, ((CraftInventoryLectern)getBukkitView().getTopInventory()).getHolder());
/*  79 */         Bukkit.getServer().getPluginManager().callEvent((Event)event);
/*  80 */         if (event.isCancelled()) {
/*  81 */           return false;
/*     */         }
/*     */         
/*  84 */         itemstack = this.inventory.splitWithoutUpdate(0);
/*     */         
/*  86 */         this.inventory.update();
/*  87 */         if (!entityhuman.inventory.pickup(itemstack)) {
/*  88 */           entityhuman.drop(itemstack, false);
/*     */         }
/*     */         
/*  91 */         return true;
/*     */     } 
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(int i, int j) {
/* 100 */     super.a(i, j);
/* 101 */     c();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canUse(EntityHuman entityhuman) {
/* 106 */     if (!this.checkReachable) return true; 
/* 107 */     return this.inventory.a(entityhuman);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ContainerLectern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */