package org.bukkit.inventory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Inventory extends Iterable<ItemStack> {
  int getSize();
  
  int getMaxStackSize();
  
  void setMaxStackSize(int paramInt);
  
  @Nullable
  ItemStack getItem(int paramInt);
  
  void setItem(int paramInt, @Nullable ItemStack paramItemStack);
  
  @NotNull
  HashMap<Integer, ItemStack> addItem(@NotNull ItemStack... paramVarArgs) throws IllegalArgumentException;
  
  @NotNull
  HashMap<Integer, ItemStack> removeItem(@NotNull ItemStack... paramVarArgs) throws IllegalArgumentException;
  
  @NotNull
  HashMap<Integer, ItemStack> removeItemAnySlot(@NotNull ItemStack... paramVarArgs) throws IllegalArgumentException;
  
  @NotNull
  ItemStack[] getContents();
  
  void setContents(@NotNull ItemStack[] paramArrayOfItemStack) throws IllegalArgumentException;
  
  @NotNull
  ItemStack[] getStorageContents();
  
  void setStorageContents(@NotNull ItemStack[] paramArrayOfItemStack) throws IllegalArgumentException;
  
  boolean contains(@NotNull Material paramMaterial) throws IllegalArgumentException;
  
  @Contract("null -> false")
  boolean contains(@Nullable ItemStack paramItemStack);
  
  boolean contains(@NotNull Material paramMaterial, int paramInt) throws IllegalArgumentException;
  
  @Contract("null, _ -> false")
  boolean contains(@Nullable ItemStack paramItemStack, int paramInt);
  
  @Contract("null, _ -> false")
  boolean containsAtLeast(@Nullable ItemStack paramItemStack, int paramInt);
  
  @NotNull
  HashMap<Integer, ? extends ItemStack> all(@NotNull Material paramMaterial) throws IllegalArgumentException;
  
  @NotNull
  HashMap<Integer, ? extends ItemStack> all(@Nullable ItemStack paramItemStack);
  
  int first(@NotNull Material paramMaterial) throws IllegalArgumentException;
  
  int first(@NotNull ItemStack paramItemStack);
  
  int firstEmpty();
  
  boolean isEmpty();
  
  void remove(@NotNull Material paramMaterial) throws IllegalArgumentException;
  
  void remove(@NotNull ItemStack paramItemStack);
  
  void clear(int paramInt);
  
  void clear();
  
  @NotNull
  List<HumanEntity> getViewers();
  
  @NotNull
  InventoryType getType();
  
  @Nullable
  InventoryHolder getHolder();
  
  @Nullable
  InventoryHolder getHolder(boolean paramBoolean);
  
  @NotNull
  ListIterator<ItemStack> iterator();
  
  @NotNull
  ListIterator<ItemStack> iterator(int paramInt);
  
  @Nullable
  Location getLocation();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\Inventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */