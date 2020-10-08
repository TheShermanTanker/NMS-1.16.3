package org.bukkit.plugin;

import java.util.Collection;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ServicesManager {
  <T> void register(@NotNull Class<T> paramClass, @NotNull T paramT, @NotNull Plugin paramPlugin, @NotNull ServicePriority paramServicePriority);
  
  void unregisterAll(@NotNull Plugin paramPlugin);
  
  void unregister(@NotNull Class<?> paramClass, @NotNull Object paramObject);
  
  void unregister(@NotNull Object paramObject);
  
  @Nullable
  <T> T load(@NotNull Class<T> paramClass);
  
  @Nullable
  <T> RegisteredServiceProvider<T> getRegistration(@NotNull Class<T> paramClass);
  
  @NotNull
  List<RegisteredServiceProvider<?>> getRegistrations(@NotNull Plugin paramPlugin);
  
  @NotNull
  <T> Collection<RegisteredServiceProvider<T>> getRegistrations(@NotNull Class<T> paramClass);
  
  @NotNull
  Collection<Class<?>> getKnownServices();
  
  <T> boolean isProvidedFor(@NotNull Class<T> paramClass);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\plugin\ServicesManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */