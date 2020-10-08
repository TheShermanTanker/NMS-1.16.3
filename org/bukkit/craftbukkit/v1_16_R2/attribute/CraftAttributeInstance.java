/*    */ package org.bukkit.craftbukkit.v1_16_R2.attribute;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import net.minecraft.server.v1_16_R2.AttributeModifiable;
/*    */ import net.minecraft.server.v1_16_R2.AttributeModifier;
/*    */ import org.bukkit.attribute.Attribute;
/*    */ import org.bukkit.attribute.AttributeInstance;
/*    */ import org.bukkit.attribute.AttributeModifier;
/*    */ 
/*    */ public class CraftAttributeInstance implements AttributeInstance {
/*    */   private final AttributeModifiable handle;
/*    */   
/*    */   public CraftAttributeInstance(AttributeModifiable handle, Attribute attribute) {
/* 17 */     this.handle = handle;
/* 18 */     this.attribute = attribute;
/*    */   }
/*    */   private final Attribute attribute;
/*    */   
/*    */   public Attribute getAttribute() {
/* 23 */     return this.attribute;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getBaseValue() {
/* 28 */     return this.handle.getBaseValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBaseValue(double d) {
/* 33 */     this.handle.setValue(d);
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<AttributeModifier> getModifiers() {
/* 38 */     List<AttributeModifier> result = new ArrayList<>();
/* 39 */     for (AttributeModifier nms : this.handle.getModifiers()) {
/* 40 */       result.add(convert(nms));
/*    */     }
/*    */     
/* 43 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addModifier(AttributeModifier modifier) {
/* 48 */     Preconditions.checkArgument((modifier != null), "modifier");
/* 49 */     this.handle.addModifier(convert(modifier));
/*    */   }
/*    */ 
/*    */   
/*    */   public void removeModifier(AttributeModifier modifier) {
/* 54 */     Preconditions.checkArgument((modifier != null), "modifier");
/* 55 */     this.handle.removeModifier(convert(modifier));
/*    */   }
/*    */ 
/*    */   
/*    */   public double getValue() {
/* 60 */     return this.handle.getValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public double getDefaultValue() {
/* 65 */     return this.handle.getAttribute().getDefault();
/*    */   }
/*    */   
/*    */   public static AttributeModifier convert(AttributeModifier bukkit) {
/* 69 */     return new AttributeModifier(bukkit.getUniqueId(), bukkit.getName(), bukkit.getAmount(), AttributeModifier.Operation.values()[bukkit.getOperation().ordinal()]);
/*    */   }
/*    */   
/*    */   public static AttributeModifier convert(AttributeModifier nms) {
/* 73 */     return new AttributeModifier(nms.getUniqueId(), nms.getName(), nms.getAmount(), AttributeModifier.Operation.values()[nms.getOperation().ordinal()]);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\attribute\CraftAttributeInstance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */