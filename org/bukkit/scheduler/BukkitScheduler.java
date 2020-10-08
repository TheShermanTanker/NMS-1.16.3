package org.bukkit.scheduler;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public interface BukkitScheduler {
  int scheduleSyncDelayedTask(@NotNull Plugin paramPlugin, @NotNull Runnable paramRunnable, long paramLong);
  
  @Deprecated
  int scheduleSyncDelayedTask(@NotNull Plugin paramPlugin, @NotNull BukkitRunnable paramBukkitRunnable, long paramLong);
  
  int scheduleSyncDelayedTask(@NotNull Plugin paramPlugin, @NotNull Runnable paramRunnable);
  
  @Deprecated
  int scheduleSyncDelayedTask(@NotNull Plugin paramPlugin, @NotNull BukkitRunnable paramBukkitRunnable);
  
  int scheduleSyncRepeatingTask(@NotNull Plugin paramPlugin, @NotNull Runnable paramRunnable, long paramLong1, long paramLong2);
  
  @Deprecated
  int scheduleSyncRepeatingTask(@NotNull Plugin paramPlugin, @NotNull BukkitRunnable paramBukkitRunnable, long paramLong1, long paramLong2);
  
  @Deprecated
  int scheduleAsyncDelayedTask(@NotNull Plugin paramPlugin, @NotNull Runnable paramRunnable, long paramLong);
  
  @Deprecated
  int scheduleAsyncDelayedTask(@NotNull Plugin paramPlugin, @NotNull Runnable paramRunnable);
  
  @Deprecated
  int scheduleAsyncRepeatingTask(@NotNull Plugin paramPlugin, @NotNull Runnable paramRunnable, long paramLong1, long paramLong2);
  
  @NotNull
  <T> Future<T> callSyncMethod(@NotNull Plugin paramPlugin, @NotNull Callable<T> paramCallable);
  
  void cancelTask(int paramInt);
  
  void cancelTasks(@NotNull Plugin paramPlugin);
  
  boolean isCurrentlyRunning(int paramInt);
  
  boolean isQueued(int paramInt);
  
  @NotNull
  List<BukkitWorker> getActiveWorkers();
  
  @NotNull
  List<BukkitTask> getPendingTasks();
  
  @NotNull
  BukkitTask runTask(@NotNull Plugin paramPlugin, @NotNull Runnable paramRunnable) throws IllegalArgumentException;
  
  void runTask(@NotNull Plugin paramPlugin, @NotNull Consumer<BukkitTask> paramConsumer) throws IllegalArgumentException;
  
  @Deprecated
  @NotNull
  BukkitTask runTask(@NotNull Plugin paramPlugin, @NotNull BukkitRunnable paramBukkitRunnable) throws IllegalArgumentException;
  
  @NotNull
  BukkitTask runTaskAsynchronously(@NotNull Plugin paramPlugin, @NotNull Runnable paramRunnable) throws IllegalArgumentException;
  
  void runTaskAsynchronously(@NotNull Plugin paramPlugin, @NotNull Consumer<BukkitTask> paramConsumer) throws IllegalArgumentException;
  
  @Deprecated
  @NotNull
  BukkitTask runTaskAsynchronously(@NotNull Plugin paramPlugin, @NotNull BukkitRunnable paramBukkitRunnable) throws IllegalArgumentException;
  
  @NotNull
  BukkitTask runTaskLater(@NotNull Plugin paramPlugin, @NotNull Runnable paramRunnable, long paramLong) throws IllegalArgumentException;
  
  void runTaskLater(@NotNull Plugin paramPlugin, @NotNull Consumer<BukkitTask> paramConsumer, long paramLong) throws IllegalArgumentException;
  
  @Deprecated
  @NotNull
  BukkitTask runTaskLater(@NotNull Plugin paramPlugin, @NotNull BukkitRunnable paramBukkitRunnable, long paramLong) throws IllegalArgumentException;
  
  @NotNull
  BukkitTask runTaskLaterAsynchronously(@NotNull Plugin paramPlugin, @NotNull Runnable paramRunnable, long paramLong) throws IllegalArgumentException;
  
  void runTaskLaterAsynchronously(@NotNull Plugin paramPlugin, @NotNull Consumer<BukkitTask> paramConsumer, long paramLong) throws IllegalArgumentException;
  
  @Deprecated
  @NotNull
  BukkitTask runTaskLaterAsynchronously(@NotNull Plugin paramPlugin, @NotNull BukkitRunnable paramBukkitRunnable, long paramLong) throws IllegalArgumentException;
  
  @NotNull
  BukkitTask runTaskTimer(@NotNull Plugin paramPlugin, @NotNull Runnable paramRunnable, long paramLong1, long paramLong2) throws IllegalArgumentException;
  
  void runTaskTimer(@NotNull Plugin paramPlugin, @NotNull Consumer<BukkitTask> paramConsumer, long paramLong1, long paramLong2) throws IllegalArgumentException;
  
  @Deprecated
  @NotNull
  BukkitTask runTaskTimer(@NotNull Plugin paramPlugin, @NotNull BukkitRunnable paramBukkitRunnable, long paramLong1, long paramLong2) throws IllegalArgumentException;
  
  @NotNull
  BukkitTask runTaskTimerAsynchronously(@NotNull Plugin paramPlugin, @NotNull Runnable paramRunnable, long paramLong1, long paramLong2) throws IllegalArgumentException;
  
  void runTaskTimerAsynchronously(@NotNull Plugin paramPlugin, @NotNull Consumer<BukkitTask> paramConsumer, long paramLong1, long paramLong2) throws IllegalArgumentException;
  
  @Deprecated
  @NotNull
  BukkitTask runTaskTimerAsynchronously(@NotNull Plugin paramPlugin, @NotNull BukkitRunnable paramBukkitRunnable, long paramLong1, long paramLong2) throws IllegalArgumentException;
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\scheduler\BukkitScheduler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */