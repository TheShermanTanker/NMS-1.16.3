package org.bukkit.entity;

import java.util.Collection;
import java.util.Set;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Sign;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface HumanEntity extends LivingEntity, AnimalTamer, InventoryHolder {
  @NotNull
  String getName();
  
  @NotNull
  PlayerInventory getInventory();
  
  @NotNull
  Inventory getEnderChest();
  
  @NotNull
  MainHand getMainHand();
  
  boolean setWindowProperty(@NotNull InventoryView.Property paramProperty, int paramInt);
  
  @NotNull
  InventoryView getOpenInventory();
  
  @Nullable
  InventoryView openInventory(@NotNull Inventory paramInventory);
  
  @Nullable
  InventoryView openWorkbench(@Nullable Location paramLocation, boolean paramBoolean);
  
  @Nullable
  InventoryView openEnchanting(@Nullable Location paramLocation, boolean paramBoolean);
  
  void openInventory(@NotNull InventoryView paramInventoryView);
  
  @Nullable
  InventoryView openMerchant(@NotNull Villager paramVillager, boolean paramBoolean);
  
  @Nullable
  InventoryView openMerchant(@NotNull Merchant paramMerchant, boolean paramBoolean);
  
  @Nullable
  InventoryView openAnvil(@Nullable Location paramLocation, boolean paramBoolean);
  
  @Nullable
  InventoryView openCartographyTable(@Nullable Location paramLocation, boolean paramBoolean);
  
  @Nullable
  InventoryView openGrindstone(@Nullable Location paramLocation, boolean paramBoolean);
  
  @Nullable
  InventoryView openLoom(@Nullable Location paramLocation, boolean paramBoolean);
  
  @Nullable
  InventoryView openSmithingTable(@Nullable Location paramLocation, boolean paramBoolean);
  
  @Nullable
  InventoryView openStonecutter(@Nullable Location paramLocation, boolean paramBoolean);
  
  void closeInventory();
  
  void closeInventory(@NotNull InventoryCloseEvent.Reason paramReason);
  
  @Deprecated
  @NotNull
  ItemStack getItemInHand();
  
  @Deprecated
  void setItemInHand(@Nullable ItemStack paramItemStack);
  
  @NotNull
  ItemStack getItemOnCursor();
  
  void setItemOnCursor(@Nullable ItemStack paramItemStack);
  
  boolean hasCooldown(@NotNull Material paramMaterial);
  
  int getCooldown(@NotNull Material paramMaterial);
  
  void setCooldown(@NotNull Material paramMaterial, int paramInt);
  
  int getSleepTicks();
  
  @Nullable
  Location getPotentialBedLocation();
  
  boolean sleep(@NotNull Location paramLocation, boolean paramBoolean);
  
  void wakeup(boolean paramBoolean);
  
  @NotNull
  Location getBedLocation();
  
  @NotNull
  GameMode getGameMode();
  
  void setGameMode(@NotNull GameMode paramGameMode);
  
  boolean isBlocking();
  
  boolean isHandRaised();
  
  int getExpToLevel();
  
  @Nullable
  Entity releaseLeftShoulderEntity();
  
  @Nullable
  Entity releaseRightShoulderEntity();
  
  float getAttackCooldown();
  
  boolean discoverRecipe(@NotNull NamespacedKey paramNamespacedKey);
  
  int discoverRecipes(@NotNull Collection<NamespacedKey> paramCollection);
  
  boolean undiscoverRecipe(@NotNull NamespacedKey paramNamespacedKey);
  
  int undiscoverRecipes(@NotNull Collection<NamespacedKey> paramCollection);
  
  boolean hasDiscoveredRecipe(@NotNull NamespacedKey paramNamespacedKey);
  
  @NotNull
  Set<NamespacedKey> getDiscoveredRecipes();
  
  @Deprecated
  @Nullable
  Entity getShoulderEntityLeft();
  
  @Deprecated
  void setShoulderEntityLeft(@Nullable Entity paramEntity);
  
  @Deprecated
  @Nullable
  Entity getShoulderEntityRight();
  
  @Deprecated
  void setShoulderEntityRight(@Nullable Entity paramEntity);
  
  void openSign(@NotNull Sign paramSign);
  
  boolean dropItem(boolean paramBoolean);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\HumanEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */