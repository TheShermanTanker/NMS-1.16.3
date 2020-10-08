/*    */ package org.bukkit.craftbukkit.v1_16_R2;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.EnumItemSlot;
/*    */ import org.bukkit.inventory.EquipmentSlot;
/*    */ 
/*    */ public class CraftEquipmentSlot
/*    */ {
/*  8 */   private static final EnumItemSlot[] slots = new EnumItemSlot[(EquipmentSlot.values()).length];
/*  9 */   private static final EquipmentSlot[] enums = new EquipmentSlot[(EnumItemSlot.values()).length];
/*    */   
/*    */   static {
/* 12 */     set(EquipmentSlot.HAND, EnumItemSlot.MAINHAND);
/* 13 */     set(EquipmentSlot.OFF_HAND, EnumItemSlot.OFFHAND);
/* 14 */     set(EquipmentSlot.FEET, EnumItemSlot.FEET);
/* 15 */     set(EquipmentSlot.LEGS, EnumItemSlot.LEGS);
/* 16 */     set(EquipmentSlot.CHEST, EnumItemSlot.CHEST);
/* 17 */     set(EquipmentSlot.HEAD, EnumItemSlot.HEAD);
/*    */   }
/*    */   
/*    */   private static void set(EquipmentSlot type, EnumItemSlot value) {
/* 21 */     slots[type.ordinal()] = value;
/* 22 */     enums[value.ordinal()] = type;
/*    */   }
/*    */   
/*    */   public static EquipmentSlot getSlot(EnumItemSlot nms) {
/* 26 */     return enums[nms.ordinal()];
/*    */   }
/*    */   
/*    */   public static EnumItemSlot getNMS(EquipmentSlot slot) {
/* 30 */     return slots[slot.ordinal()];
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\CraftEquipmentSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */