package org.bukkit.inventory;

import java.util.List;
import org.bukkit.entity.HumanEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Merchant {
  @NotNull
  List<MerchantRecipe> getRecipes();
  
  void setRecipes(@NotNull List<MerchantRecipe> paramList);
  
  @NotNull
  MerchantRecipe getRecipe(int paramInt) throws IndexOutOfBoundsException;
  
  void setRecipe(int paramInt, @NotNull MerchantRecipe paramMerchantRecipe) throws IndexOutOfBoundsException;
  
  int getRecipeCount();
  
  boolean isTrading();
  
  @Nullable
  HumanEntity getTrader();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\Merchant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */