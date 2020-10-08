package co.aikar.timings;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Timing extends AutoCloseable {
  @NotNull
  Timing startTiming();
  
  void stopTiming();
  
  @NotNull
  Timing startTimingIfSync();
  
  void stopTimingIfSync();
  
  @Deprecated
  void abort();
  
  @Nullable
  TimingHandler getTimingHandler();
  
  void close();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\co\aikar\timings\Timing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */