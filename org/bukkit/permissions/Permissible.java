package org.bukkit.permissions;

import java.util.Set;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Permissible extends ServerOperator {
  boolean isPermissionSet(@NotNull String paramString);
  
  boolean isPermissionSet(@NotNull Permission paramPermission);
  
  boolean hasPermission(@NotNull String paramString);
  
  boolean hasPermission(@NotNull Permission paramPermission);
  
  @NotNull
  PermissionAttachment addAttachment(@NotNull Plugin paramPlugin, @NotNull String paramString, boolean paramBoolean);
  
  @NotNull
  PermissionAttachment addAttachment(@NotNull Plugin paramPlugin);
  
  @Nullable
  PermissionAttachment addAttachment(@NotNull Plugin paramPlugin, @NotNull String paramString, boolean paramBoolean, int paramInt);
  
  @Nullable
  PermissionAttachment addAttachment(@NotNull Plugin paramPlugin, int paramInt);
  
  void removeAttachment(@NotNull PermissionAttachment paramPermissionAttachment);
  
  void recalculatePermissions();
  
  @NotNull
  Set<PermissionAttachmentInfo> getEffectivePermissions();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\permissions\Permissible.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */