package org.bukkit.advancement;

import java.util.Collection;
import java.util.Date;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface AdvancementProgress {
  @NotNull
  Advancement getAdvancement();
  
  boolean isDone();
  
  boolean awardCriteria(@NotNull String paramString);
  
  boolean revokeCriteria(@NotNull String paramString);
  
  @Nullable
  Date getDateAwarded(@NotNull String paramString);
  
  @NotNull
  Collection<String> getRemainingCriteria();
  
  @NotNull
  Collection<String> getAwardedCriteria();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\advancement\AdvancementProgress.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */