package org.bukkit.potion;

import java.util.Collection;
import org.jetbrains.annotations.NotNull;

public interface PotionBrewer {
  @NotNull
  PotionEffect createEffect(@NotNull PotionEffectType paramPotionEffectType, int paramInt1, int paramInt2);
  
  @Deprecated
  @NotNull
  Collection<PotionEffect> getEffectsFromDamage(int paramInt);
  
  @NotNull
  Collection<PotionEffect> getEffects(@NotNull PotionType paramPotionType, boolean paramBoolean1, boolean paramBoolean2);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\potion\PotionBrewer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */