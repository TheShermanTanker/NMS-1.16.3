/*     */ package org.bukkit.craftbukkit.v1_16_R2.inventory.util;
/*     */ 
/*     */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*     */ import net.minecraft.server.v1_16_R2.IInventory;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityBlastFurnace;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityBrewingStand;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityDispenser;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityDropper;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityFurnace;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityFurnaceFurnace;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityHopper;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityLectern;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityLootable;
/*     */ import net.minecraft.server.v1_16_R2.TileEntitySmoker;
/*     */ import net.minecraft.server.v1_16_R2.World;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventory;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryBrewer;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryFurnace;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
/*     */ import org.bukkit.event.inventory.InventoryType;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ 
/*     */ public abstract class CraftTileInventoryConverter
/*     */   implements CraftInventoryCreator.InventoryConverter
/*     */ {
/*     */   public abstract IInventory getTileEntity();
/*     */   
/*     */   public Inventory createInventory(InventoryHolder holder, InventoryType type) {
/*  31 */     return getInventory(getTileEntity());
/*     */   }
/*     */ 
/*     */   
/*     */   public Inventory createInventory(InventoryHolder holder, InventoryType type, String title) {
/*  36 */     IInventory te = getTileEntity();
/*  37 */     if (te instanceof TileEntityLootable) {
/*  38 */       ((TileEntityLootable)te).setCustomName(CraftChatMessage.fromStringOrNull(title));
/*     */     }
/*     */     
/*  41 */     return getInventory(te);
/*     */   }
/*     */   
/*     */   public Inventory getInventory(IInventory tileEntity) {
/*  45 */     return (Inventory)new CraftInventory(tileEntity);
/*     */   }
/*     */   
/*     */   public static class Furnace
/*     */     extends CraftTileInventoryConverter
/*     */   {
/*     */     public IInventory getTileEntity() {
/*  52 */       TileEntityFurnaceFurnace tileEntityFurnaceFurnace = new TileEntityFurnaceFurnace();
/*  53 */       tileEntityFurnaceFurnace.setLocation((World)MinecraftServer.getServer().getWorldServer(World.OVERWORLD), BlockPosition.ZERO);
/*  54 */       return (IInventory)tileEntityFurnaceFurnace;
/*     */     }
/*     */ 
/*     */     
/*     */     public Inventory createInventory(InventoryHolder owner, InventoryType type, String title) {
/*  59 */       IInventory tileEntity = getTileEntity();
/*  60 */       ((TileEntityFurnace)tileEntity).setCustomName(CraftChatMessage.fromStringOrNull(title));
/*  61 */       return getInventory(tileEntity);
/*     */     }
/*     */ 
/*     */     
/*     */     public Inventory getInventory(IInventory tileEntity) {
/*  66 */       return (Inventory)new CraftInventoryFurnace((TileEntityFurnace)tileEntity);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BrewingStand
/*     */     extends CraftTileInventoryConverter
/*     */   {
/*     */     public IInventory getTileEntity() {
/*  74 */       return (IInventory)new TileEntityBrewingStand();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Inventory createInventory(InventoryHolder holder, InventoryType type, String title) {
/*  80 */       IInventory tileEntity = getTileEntity();
/*  81 */       if (tileEntity instanceof TileEntityBrewingStand) {
/*  82 */         ((TileEntityBrewingStand)tileEntity).setCustomName(CraftChatMessage.fromStringOrNull(title));
/*     */       }
/*  84 */       return getInventory(tileEntity);
/*     */     }
/*     */ 
/*     */     
/*     */     public Inventory getInventory(IInventory tileEntity) {
/*  89 */       return (Inventory)new CraftInventoryBrewer(tileEntity);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Dispenser
/*     */     extends CraftTileInventoryConverter
/*     */   {
/*     */     public IInventory getTileEntity() {
/*  97 */       return (IInventory)new TileEntityDispenser();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Dropper
/*     */     extends CraftTileInventoryConverter
/*     */   {
/*     */     public IInventory getTileEntity() {
/* 105 */       return (IInventory)new TileEntityDropper();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Hopper
/*     */     extends CraftTileInventoryConverter
/*     */   {
/*     */     public IInventory getTileEntity() {
/* 113 */       return (IInventory)new TileEntityHopper();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BlastFurnace
/*     */     extends CraftTileInventoryConverter
/*     */   {
/*     */     public IInventory getTileEntity() {
/* 121 */       return (IInventory)new TileEntityBlastFurnace();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Lectern
/*     */     extends CraftTileInventoryConverter
/*     */   {
/*     */     public IInventory getTileEntity() {
/* 129 */       return (new TileEntityLectern()).inventory;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Smoker
/*     */     extends CraftTileInventoryConverter
/*     */   {
/*     */     public IInventory getTileEntity() {
/* 137 */       return (IInventory)new TileEntitySmoker();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventor\\util\CraftTileInventoryConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */