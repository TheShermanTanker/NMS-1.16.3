/*    */ package org.bukkit.permissions;
/*    */ 
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PermissionAttachmentInfo
/*    */ {
/*    */   private final Permissible permissible;
/*    */   private final String permission;
/*    */   private final PermissionAttachment attachment;
/*    */   private final boolean value;
/*    */   
/*    */   public PermissionAttachmentInfo(@NotNull Permissible permissible, @NotNull String permission, @Nullable PermissionAttachment attachment, boolean value) {
/* 17 */     if (permissible == null)
/* 18 */       throw new IllegalArgumentException("Permissible may not be null"); 
/* 19 */     if (permission == null) {
/* 20 */       throw new IllegalArgumentException("Permission may not be null");
/*    */     }
/*    */     
/* 23 */     this.permissible = permissible;
/* 24 */     this.permission = permission;
/* 25 */     this.attachment = attachment;
/* 26 */     this.value = value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Permissible getPermissible() {
/* 36 */     return this.permissible;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public String getPermission() {
/* 46 */     return this.permission;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public PermissionAttachment getAttachment() {
/* 57 */     return this.attachment;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean getValue() {
/* 66 */     return this.value;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\permissions\PermissionAttachmentInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */