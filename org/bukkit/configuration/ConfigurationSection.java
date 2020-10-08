package org.bukkit.configuration;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ConfigurationSection {
  @NotNull
  Set<String> getKeys(boolean paramBoolean);
  
  @NotNull
  Map<String, Object> getValues(boolean paramBoolean);
  
  boolean contains(@NotNull String paramString);
  
  boolean contains(@NotNull String paramString, boolean paramBoolean);
  
  boolean isSet(@NotNull String paramString);
  
  @Nullable
  String getCurrentPath();
  
  @NotNull
  String getName();
  
  @Nullable
  Configuration getRoot();
  
  @Nullable
  ConfigurationSection getParent();
  
  @Nullable
  Object get(@NotNull String paramString);
  
  @Nullable
  Object get(@NotNull String paramString, @Nullable Object paramObject);
  
  void set(@NotNull String paramString, @Nullable Object paramObject);
  
  @NotNull
  ConfigurationSection createSection(@NotNull String paramString);
  
  @NotNull
  ConfigurationSection createSection(@NotNull String paramString, @NotNull Map<?, ?> paramMap);
  
  @Nullable
  String getString(@NotNull String paramString);
  
  @Nullable
  String getString(@NotNull String paramString1, @Nullable String paramString2);
  
  boolean isString(@NotNull String paramString);
  
  int getInt(@NotNull String paramString);
  
  int getInt(@NotNull String paramString, int paramInt);
  
  boolean isInt(@NotNull String paramString);
  
  boolean getBoolean(@NotNull String paramString);
  
  boolean getBoolean(@NotNull String paramString, boolean paramBoolean);
  
  boolean isBoolean(@NotNull String paramString);
  
  double getDouble(@NotNull String paramString);
  
  double getDouble(@NotNull String paramString, double paramDouble);
  
  boolean isDouble(@NotNull String paramString);
  
  long getLong(@NotNull String paramString);
  
  long getLong(@NotNull String paramString, long paramLong);
  
  boolean isLong(@NotNull String paramString);
  
  @Nullable
  List<?> getList(@NotNull String paramString);
  
  @Nullable
  List<?> getList(@NotNull String paramString, @Nullable List<?> paramList);
  
  boolean isList(@NotNull String paramString);
  
  @NotNull
  List<String> getStringList(@NotNull String paramString);
  
  @NotNull
  List<Integer> getIntegerList(@NotNull String paramString);
  
  @NotNull
  List<Boolean> getBooleanList(@NotNull String paramString);
  
  @NotNull
  List<Double> getDoubleList(@NotNull String paramString);
  
  @NotNull
  List<Float> getFloatList(@NotNull String paramString);
  
  @NotNull
  List<Long> getLongList(@NotNull String paramString);
  
  @NotNull
  List<Byte> getByteList(@NotNull String paramString);
  
  @NotNull
  List<Character> getCharacterList(@NotNull String paramString);
  
  @NotNull
  List<Short> getShortList(@NotNull String paramString);
  
  @NotNull
  List<Map<?, ?>> getMapList(@NotNull String paramString);
  
  @Nullable
  <T> T getObject(@NotNull String paramString, @NotNull Class<T> paramClass);
  
  @Nullable
  <T> T getObject(@NotNull String paramString, @NotNull Class<T> paramClass, @Nullable T paramT);
  
  @Nullable
  <T extends org.bukkit.configuration.serialization.ConfigurationSerializable> T getSerializable(@NotNull String paramString, @NotNull Class<T> paramClass);
  
  @Nullable
  <T extends org.bukkit.configuration.serialization.ConfigurationSerializable> T getSerializable(@NotNull String paramString, @NotNull Class<T> paramClass, @Nullable T paramT);
  
  @Nullable
  Vector getVector(@NotNull String paramString);
  
  @Nullable
  Vector getVector(@NotNull String paramString, @Nullable Vector paramVector);
  
  boolean isVector(@NotNull String paramString);
  
  @Nullable
  OfflinePlayer getOfflinePlayer(@NotNull String paramString);
  
  @Nullable
  OfflinePlayer getOfflinePlayer(@NotNull String paramString, @Nullable OfflinePlayer paramOfflinePlayer);
  
  boolean isOfflinePlayer(@NotNull String paramString);
  
  @Nullable
  ItemStack getItemStack(@NotNull String paramString);
  
  @Nullable
  ItemStack getItemStack(@NotNull String paramString, @Nullable ItemStack paramItemStack);
  
  boolean isItemStack(@NotNull String paramString);
  
  @Nullable
  Color getColor(@NotNull String paramString);
  
  @Nullable
  Color getColor(@NotNull String paramString, @Nullable Color paramColor);
  
  boolean isColor(@NotNull String paramString);
  
  @Nullable
  Location getLocation(@NotNull String paramString);
  
  @Nullable
  Location getLocation(@NotNull String paramString, @Nullable Location paramLocation);
  
  boolean isLocation(@NotNull String paramString);
  
  @Nullable
  ConfigurationSection getConfigurationSection(@NotNull String paramString);
  
  boolean isConfigurationSection(@NotNull String paramString);
  
  @Nullable
  ConfigurationSection getDefaultSection();
  
  void addDefault(@NotNull String paramString, @Nullable Object paramObject);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\configuration\ConfigurationSection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */