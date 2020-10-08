package org.bukkit.inventory.meta;

import java.util.List;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public interface KnowledgeBookMeta extends ItemMeta {
  boolean hasRecipes();
  
  @NotNull
  List<NamespacedKey> getRecipes();
  
  void setRecipes(@NotNull List<NamespacedKey> paramList);
  
  void addRecipe(@NotNull NamespacedKey... paramVarArgs);
  
  @NotNull
  KnowledgeBookMeta clone();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\meta\KnowledgeBookMeta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */