package org.bukkit.plugin;

import java.io.File;
import java.util.Set;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface PluginManager {
  void registerInterface(@NotNull Class<? extends PluginLoader> paramClass) throws IllegalArgumentException;
  
  @Nullable
  Plugin getPlugin(@NotNull String paramString);
  
  @NotNull
  Plugin[] getPlugins();
  
  boolean isPluginEnabled(@NotNull String paramString);
  
  @Contract("null -> false")
  boolean isPluginEnabled(@Nullable Plugin paramPlugin);
  
  @Nullable
  Plugin loadPlugin(@NotNull File paramFile) throws InvalidPluginException, InvalidDescriptionException, UnknownDependencyException;
  
  @NotNull
  Plugin[] loadPlugins(@NotNull File paramFile);
  
  void disablePlugins();
  
  void clearPlugins();
  
  void callEvent(@NotNull Event paramEvent) throws IllegalStateException;
  
  void registerEvents(@NotNull Listener paramListener, @NotNull Plugin paramPlugin);
  
  void registerEvent(@NotNull Class<? extends Event> paramClass, @NotNull Listener paramListener, @NotNull EventPriority paramEventPriority, @NotNull EventExecutor paramEventExecutor, @NotNull Plugin paramPlugin);
  
  void registerEvent(@NotNull Class<? extends Event> paramClass, @NotNull Listener paramListener, @NotNull EventPriority paramEventPriority, @NotNull EventExecutor paramEventExecutor, @NotNull Plugin paramPlugin, boolean paramBoolean);
  
  void enablePlugin(@NotNull Plugin paramPlugin);
  
  void disablePlugin(@NotNull Plugin paramPlugin);
  
  void disablePlugin(@NotNull Plugin paramPlugin, boolean paramBoolean);
  
  @Nullable
  Permission getPermission(@NotNull String paramString);
  
  void addPermission(@NotNull Permission paramPermission);
  
  void removePermission(@NotNull Permission paramPermission);
  
  void removePermission(@NotNull String paramString);
  
  @NotNull
  Set<Permission> getDefaultPermissions(boolean paramBoolean);
  
  void recalculatePermissionDefaults(@NotNull Permission paramPermission);
  
  void subscribeToPermission(@NotNull String paramString, @NotNull Permissible paramPermissible);
  
  void unsubscribeFromPermission(@NotNull String paramString, @NotNull Permissible paramPermissible);
  
  @NotNull
  Set<Permissible> getPermissionSubscriptions(@NotNull String paramString);
  
  void subscribeToDefaultPerms(boolean paramBoolean, @NotNull Permissible paramPermissible);
  
  void unsubscribeFromDefaultPerms(boolean paramBoolean, @NotNull Permissible paramPermissible);
  
  @NotNull
  Set<Permissible> getDefaultPermSubscriptions(boolean paramBoolean);
  
  @NotNull
  Set<Permission> getPermissions();
  
  boolean useTimings();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\plugin\PluginManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */