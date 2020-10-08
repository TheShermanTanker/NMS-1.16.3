/*     */ package org.bukkit.attribute;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.UUID;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.configuration.serialization.ConfigurationSerializable;
/*     */ import org.bukkit.inventory.EquipmentSlot;
/*     */ import org.bukkit.util.NumberConversions;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AttributeModifier
/*     */   implements ConfigurationSerializable
/*     */ {
/*     */   private final UUID uuid;
/*     */   private final String name;
/*     */   private final double amount;
/*     */   private final Operation operation;
/*     */   private final EquipmentSlot slot;
/*     */   
/*     */   public AttributeModifier(@NotNull String name, double amount, @NotNull Operation operation) {
/*  26 */     this(UUID.randomUUID(), name, amount, operation);
/*     */   }
/*     */   
/*     */   public AttributeModifier(@NotNull UUID uuid, @NotNull String name, double amount, @NotNull Operation operation) {
/*  30 */     this(uuid, name, amount, operation, null);
/*     */   }
/*     */   
/*     */   public AttributeModifier(@NotNull UUID uuid, @NotNull String name, double amount, @NotNull Operation operation, @Nullable EquipmentSlot slot) {
/*  34 */     Validate.notNull(uuid, "UUID cannot be null");
/*  35 */     Validate.notNull(name, "Name cannot be null");
/*  36 */     Validate.notNull(operation, "Operation cannot be null");
/*  37 */     this.uuid = uuid;
/*  38 */     this.name = name;
/*  39 */     this.amount = amount;
/*  40 */     this.operation = operation;
/*  41 */     this.slot = slot;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public UUID getUniqueId() {
/*  51 */     return this.uuid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getName() {
/*  61 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getAmount() {
/*  70 */     return this.amount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Operation getOperation() {
/*  80 */     return this.operation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public EquipmentSlot getSlot() {
/*  91 */     return this.slot;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Map<String, Object> serialize() {
/*  97 */     Map<String, Object> data = new HashMap<>();
/*  98 */     data.put("uuid", this.uuid.toString());
/*  99 */     data.put("name", this.name);
/* 100 */     data.put("operation", Integer.valueOf(this.operation.ordinal()));
/* 101 */     data.put("amount", Double.valueOf(this.amount));
/* 102 */     if (this.slot != null) {
/* 103 */       data.put("slot", this.slot.name());
/*     */     }
/* 105 */     return data;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object other) {
/* 110 */     if (!(other instanceof AttributeModifier)) {
/* 111 */       return false;
/*     */     }
/* 113 */     AttributeModifier mod = (AttributeModifier)other;
/* 114 */     boolean slots = (this.slot != null) ? ((this.slot == mod.slot)) : ((mod.slot == null));
/* 115 */     return (this.uuid.equals(mod.uuid) && this.name.equals(mod.name) && this.amount == mod.amount && this.operation == mod.operation && slots);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 120 */     int hash = 5;
/* 121 */     hash = 17 * hash + Objects.hashCode(this.uuid);
/* 122 */     hash = 17 * hash + Objects.hashCode(this.name);
/* 123 */     hash = 17 * hash + (int)(Double.doubleToLongBits(this.amount) ^ Double.doubleToLongBits(this.amount) >>> 32L);
/* 124 */     hash = 17 * hash + Objects.hashCode(this.operation);
/* 125 */     hash = 17 * hash + Objects.hashCode(this.slot);
/* 126 */     return hash;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 131 */     return "AttributeModifier{uuid=" + this.uuid
/* 132 */       .toString() + ", name=" + this.name + ", operation=" + this.operation
/*     */       
/* 134 */       .name() + ", amount=" + this.amount + ", slot=" + (
/*     */       
/* 136 */       (this.slot != null) ? this.slot.name() : "") + "}";
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static AttributeModifier deserialize(@NotNull Map<String, Object> args) {
/* 142 */     if (args.containsKey("slot")) {
/* 143 */       return new AttributeModifier(UUID.fromString((String)args.get("uuid")), (String)args.get("name"), NumberConversions.toDouble(args.get("amount")), Operation.values()[NumberConversions.toInt(args.get("operation"))], EquipmentSlot.valueOf(args.get("slot").toString().toUpperCase()));
/*     */     }
/* 145 */     return new AttributeModifier(UUID.fromString((String)args.get("uuid")), (String)args.get("name"), NumberConversions.toDouble(args.get("amount")), Operation.values()[NumberConversions.toInt(args.get("operation"))]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum Operation
/*     */   {
/* 156 */     ADD_NUMBER,
/*     */ 
/*     */ 
/*     */     
/* 160 */     ADD_SCALAR,
/*     */ 
/*     */ 
/*     */     
/* 164 */     MULTIPLY_SCALAR_1;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\attribute\AttributeModifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */