/*     */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*     */ import java.util.Set;
/*     */ import net.minecraft.server.v1_16_R2.Entity;
/*     */ import net.minecraft.server.v1_16_R2.EntityArmorStand;
/*     */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*     */ import net.minecraft.server.v1_16_R2.EnumItemSlot;
/*     */ import net.minecraft.server.v1_16_R2.Vector3f;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftEquipmentSlot;
/*     */ import org.bukkit.entity.ArmorStand;
/*     */ import org.bukkit.inventory.EquipmentSlot;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.util.EulerAngle;
/*     */ 
/*     */ public class CraftArmorStand extends CraftLivingEntity implements ArmorStand {
/*     */   public CraftArmorStand(CraftServer server, EntityArmorStand entity) {
/*  16 */     super(server, (EntityLiving)entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  21 */     return "CraftArmorStand";
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityType getType() {
/*  26 */     return EntityType.ARMOR_STAND;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityArmorStand getHandle() {
/*  31 */     return (EntityArmorStand)super.getHandle();
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItemInHand() {
/*  36 */     return getEquipment().getItemInHand();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemInHand(ItemStack item) {
/*  41 */     getEquipment().setItemInHand(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getBoots() {
/*  46 */     return getEquipment().getBoots();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBoots(ItemStack item) {
/*  51 */     getEquipment().setBoots(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getLeggings() {
/*  56 */     return getEquipment().getLeggings();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLeggings(ItemStack item) {
/*  61 */     getEquipment().setLeggings(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getChestplate() {
/*  66 */     return getEquipment().getChestplate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setChestplate(ItemStack item) {
/*  71 */     getEquipment().setChestplate(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getHelmet() {
/*  76 */     return getEquipment().getHelmet();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHelmet(ItemStack item) {
/*  81 */     getEquipment().setHelmet(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public EulerAngle getBodyPose() {
/*  86 */     return fromNMS((getHandle()).bodyPose);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBodyPose(EulerAngle pose) {
/*  91 */     getHandle().setBodyPose(toNMS(pose));
/*     */   }
/*     */ 
/*     */   
/*     */   public EulerAngle getLeftArmPose() {
/*  96 */     return fromNMS((getHandle()).leftArmPose);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLeftArmPose(EulerAngle pose) {
/* 101 */     getHandle().setLeftArmPose(toNMS(pose));
/*     */   }
/*     */ 
/*     */   
/*     */   public EulerAngle getRightArmPose() {
/* 106 */     return fromNMS((getHandle()).rightArmPose);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRightArmPose(EulerAngle pose) {
/* 111 */     getHandle().setRightArmPose(toNMS(pose));
/*     */   }
/*     */ 
/*     */   
/*     */   public EulerAngle getLeftLegPose() {
/* 116 */     return fromNMS((getHandle()).leftLegPose);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLeftLegPose(EulerAngle pose) {
/* 121 */     getHandle().setLeftLegPose(toNMS(pose));
/*     */   }
/*     */ 
/*     */   
/*     */   public EulerAngle getRightLegPose() {
/* 126 */     return fromNMS((getHandle()).rightLegPose);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRightLegPose(EulerAngle pose) {
/* 131 */     getHandle().setRightLegPose(toNMS(pose));
/*     */   }
/*     */ 
/*     */   
/*     */   public EulerAngle getHeadPose() {
/* 136 */     return fromNMS((getHandle()).headPose);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeadPose(EulerAngle pose) {
/* 141 */     getHandle().setHeadPose(toNMS(pose));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasBasePlate() {
/* 146 */     return !getHandle().hasBasePlate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBasePlate(boolean basePlate) {
/* 151 */     getHandle().setBasePlate(!basePlate);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGravity(boolean gravity) {
/* 156 */     super.setGravity(gravity);
/*     */     
/* 158 */     (getHandle()).noclip = !gravity;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisible() {
/* 163 */     return !getHandle().isInvisible();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisible(boolean visible) {
/* 168 */     getHandle().setInvisible(!visible);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasArms() {
/* 173 */     return getHandle().hasArms();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setArms(boolean arms) {
/* 178 */     getHandle().setArms(arms);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSmall() {
/* 183 */     return getHandle().isSmall();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSmall(boolean small) {
/* 188 */     getHandle().setSmall(small);
/*     */   }
/*     */   
/*     */   private static EulerAngle fromNMS(Vector3f old) {
/* 192 */     return new EulerAngle(
/* 193 */         Math.toRadians(old.getX()), 
/* 194 */         Math.toRadians(old.getY()), 
/* 195 */         Math.toRadians(old.getZ()));
/*     */   }
/*     */ 
/*     */   
/*     */   private static Vector3f toNMS(EulerAngle old) {
/* 200 */     return new Vector3f(
/* 201 */         (float)Math.toDegrees(old.getX()), 
/* 202 */         (float)Math.toDegrees(old.getY()), 
/* 203 */         (float)Math.toDegrees(old.getZ()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMarker() {
/* 209 */     return getHandle().isMarker();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMarker(boolean marker) {
/* 214 */     getHandle().setMarker(marker);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addEquipmentLock(EquipmentSlot equipmentSlot, ArmorStand.LockType lockType) {
/* 219 */     (getHandle()).disabledSlots |= 1 << CraftEquipmentSlot.getNMS(equipmentSlot).getSlotFlag() + lockType.ordinal() * 8;
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeEquipmentLock(EquipmentSlot equipmentSlot, ArmorStand.LockType lockType) {
/* 224 */     (getHandle()).disabledSlots &= 1 << CraftEquipmentSlot.getNMS(equipmentSlot).getSlotFlag() + lockType.ordinal() * 8 ^ 0xFFFFFFFF;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasEquipmentLock(EquipmentSlot equipmentSlot, ArmorStand.LockType lockType) {
/* 229 */     return (((getHandle()).disabledSlots & 1 << CraftEquipmentSlot.getNMS(equipmentSlot).getSlotFlag() + lockType.ordinal() * 8) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canMove() {
/* 234 */     return (getHandle()).canMove;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCanMove(boolean move) {
/* 239 */     (getHandle()).canMove = move;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItem(EquipmentSlot slot) {
/* 244 */     Preconditions.checkNotNull(slot, "slot");
/* 245 */     return getHandle().getEquipment(CraftEquipmentSlot.getNMS(slot)).asBukkitMirror();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItem(EquipmentSlot slot, ItemStack item) {
/* 250 */     Preconditions.checkNotNull(slot, "slot");
/* 251 */     switch (slot) {
/*     */       case HAND:
/* 253 */         getEquipment().setItemInMainHand(item);
/*     */         return;
/*     */       case OFF_HAND:
/* 256 */         getEquipment().setItemInOffHand(item);
/*     */         return;
/*     */       case FEET:
/* 259 */         setBoots(item);
/*     */         return;
/*     */       case LEGS:
/* 262 */         setLeggings(item);
/*     */         return;
/*     */       case CHEST:
/* 265 */         setChestplate(item);
/*     */         return;
/*     */       case HEAD:
/* 268 */         setHelmet(item);
/*     */         return;
/*     */     } 
/* 271 */     throw new UnsupportedOperationException(slot.name());
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<EquipmentSlot> getDisabledSlots() {
/* 276 */     Set<EquipmentSlot> disabled = new HashSet<>();
/* 277 */     for (EquipmentSlot slot : EquipmentSlot.values()) {
/* 278 */       if (isSlotDisabled(slot)) {
/* 279 */         disabled.add(slot);
/*     */       }
/*     */     } 
/* 282 */     return disabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisabledSlots(EquipmentSlot... slots) {
/* 287 */     int disabled = 0;
/* 288 */     for (EquipmentSlot slot : slots) {
/* 289 */       if (slot != EquipmentSlot.OFF_HAND) {
/* 290 */         EnumItemSlot nmsSlot = CraftEquipmentSlot.getNMS(slot);
/* 291 */         disabled += (1 << nmsSlot.getSlotFlag()) + (1 << nmsSlot.getSlotFlag() + 8) + (1 << nmsSlot.getSlotFlag() + 16);
/*     */       } 
/* 293 */     }  (getHandle()).disabledSlots = disabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addDisabledSlots(EquipmentSlot... slots) {
/* 298 */     Set<EquipmentSlot> disabled = getDisabledSlots();
/* 299 */     Collections.addAll(disabled, slots);
/* 300 */     setDisabledSlots(disabled.<EquipmentSlot>toArray(new EquipmentSlot[0]));
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeDisabledSlots(EquipmentSlot... slots) {
/* 305 */     Set<EquipmentSlot> disabled = getDisabledSlots();
/* 306 */     for (EquipmentSlot slot : slots) disabled.remove(slot); 
/* 307 */     setDisabledSlots(disabled.<EquipmentSlot>toArray(new EquipmentSlot[0]));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSlotDisabled(EquipmentSlot slot) {
/* 312 */     return getHandle().isSlotDisabled(CraftEquipmentSlot.getNMS(slot));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canTick() {
/* 317 */     return (getHandle()).canTick;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCanTick(boolean tick) {
/* 322 */     (getHandle()).canTick = tick;
/* 323 */     (getHandle()).canTickSetByAPI = true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftArmorStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */