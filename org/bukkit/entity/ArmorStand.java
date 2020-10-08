/*     */ package org.bukkit.entity;
/*     */ 
/*     */ import java.util.Set;
/*     */ import org.bukkit.inventory.EquipmentSlot;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.util.EulerAngle;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface ArmorStand
/*     */   extends LivingEntity
/*     */ {
/*     */   @Deprecated
/*     */   @NotNull
/*     */   ItemStack getItemInHand();
/*     */   
/*     */   @Deprecated
/*     */   void setItemInHand(@Nullable ItemStack paramItemStack);
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   ItemStack getBoots();
/*     */   
/*     */   @Deprecated
/*     */   void setBoots(@Nullable ItemStack paramItemStack);
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   ItemStack getLeggings();
/*     */   
/*     */   @Deprecated
/*     */   void setLeggings(@Nullable ItemStack paramItemStack);
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   ItemStack getChestplate();
/*     */   
/*     */   @Deprecated
/*     */   void setChestplate(@Nullable ItemStack paramItemStack);
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   ItemStack getHelmet();
/*     */   
/*     */   @Deprecated
/*     */   void setHelmet(@Nullable ItemStack paramItemStack);
/*     */   
/*     */   @NotNull
/*     */   EulerAngle getBodyPose();
/*     */   
/*     */   void setBodyPose(@NotNull EulerAngle paramEulerAngle);
/*     */   
/*     */   @NotNull
/*     */   EulerAngle getLeftArmPose();
/*     */   
/*     */   void setLeftArmPose(@NotNull EulerAngle paramEulerAngle);
/*     */   
/*     */   @NotNull
/*     */   EulerAngle getRightArmPose();
/*     */   
/*     */   void setRightArmPose(@NotNull EulerAngle paramEulerAngle);
/*     */   
/*     */   @NotNull
/*     */   EulerAngle getLeftLegPose();
/*     */   
/*     */   void setLeftLegPose(@NotNull EulerAngle paramEulerAngle);
/*     */   
/*     */   @NotNull
/*     */   EulerAngle getRightLegPose();
/*     */   
/*     */   void setRightLegPose(@NotNull EulerAngle paramEulerAngle);
/*     */   
/*     */   @NotNull
/*     */   EulerAngle getHeadPose();
/*     */   
/*     */   void setHeadPose(@NotNull EulerAngle paramEulerAngle);
/*     */   
/*     */   boolean hasBasePlate();
/*     */   
/*     */   void setBasePlate(boolean paramBoolean);
/*     */   
/*     */   boolean isVisible();
/*     */   
/*     */   void setVisible(boolean paramBoolean);
/*     */   
/*     */   boolean hasArms();
/*     */   
/*     */   void setArms(boolean paramBoolean);
/*     */   
/*     */   boolean isSmall();
/*     */   
/*     */   void setSmall(boolean paramBoolean);
/*     */   
/*     */   boolean isMarker();
/*     */   
/*     */   void setMarker(boolean paramBoolean);
/*     */   
/*     */   void addEquipmentLock(@NotNull EquipmentSlot paramEquipmentSlot, @NotNull LockType paramLockType);
/*     */   
/*     */   void removeEquipmentLock(@NotNull EquipmentSlot paramEquipmentSlot, @NotNull LockType paramLockType);
/*     */   
/*     */   boolean hasEquipmentLock(@NotNull EquipmentSlot paramEquipmentSlot, @NotNull LockType paramLockType);
/*     */   
/*     */   boolean canMove();
/*     */   
/*     */   void setCanMove(boolean paramBoolean);
/*     */   
/*     */   boolean canTick();
/*     */   
/*     */   void setCanTick(boolean paramBoolean);
/*     */   
/*     */   @NotNull
/*     */   ItemStack getItem(@NotNull EquipmentSlot paramEquipmentSlot);
/*     */   
/*     */   void setItem(@NotNull EquipmentSlot paramEquipmentSlot, @Nullable ItemStack paramItemStack);
/*     */   
/*     */   @NotNull
/*     */   Set<EquipmentSlot> getDisabledSlots();
/*     */   
/*     */   void setDisabledSlots(@NotNull EquipmentSlot... paramVarArgs);
/*     */   
/*     */   void addDisabledSlots(@NotNull EquipmentSlot... paramVarArgs);
/*     */   
/*     */   void removeDisabledSlots(@NotNull EquipmentSlot... paramVarArgs);
/*     */   
/*     */   boolean isSlotDisabled(@NotNull EquipmentSlot paramEquipmentSlot);
/*     */   
/*     */   public enum LockType
/*     */   {
/* 333 */     ADDING_OR_CHANGING,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 339 */     REMOVING_OR_CHANGING,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 345 */     ADDING;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\ArmorStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */