/*    */ package org.bukkit.metadata;
/*    */ 
/*    */ import java.lang.ref.WeakReference;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.util.NumberConversions;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class MetadataValueAdapter
/*    */   implements MetadataValue
/*    */ {
/*    */   protected final WeakReference<Plugin> owningPlugin;
/*    */   
/*    */   protected MetadataValueAdapter(@NotNull Plugin owningPlugin) {
/* 21 */     Validate.notNull(owningPlugin, "owningPlugin cannot be null");
/* 22 */     this.owningPlugin = new WeakReference<>(owningPlugin);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Plugin getOwningPlugin() {
/* 28 */     return this.owningPlugin.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public int asInt() {
/* 33 */     return NumberConversions.toInt(value());
/*    */   }
/*    */ 
/*    */   
/*    */   public float asFloat() {
/* 38 */     return NumberConversions.toFloat(value());
/*    */   }
/*    */ 
/*    */   
/*    */   public double asDouble() {
/* 43 */     return NumberConversions.toDouble(value());
/*    */   }
/*    */ 
/*    */   
/*    */   public long asLong() {
/* 48 */     return NumberConversions.toLong(value());
/*    */   }
/*    */ 
/*    */   
/*    */   public short asShort() {
/* 53 */     return NumberConversions.toShort(value());
/*    */   }
/*    */ 
/*    */   
/*    */   public byte asByte() {
/* 58 */     return NumberConversions.toByte(value());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean asBoolean() {
/* 63 */     Object value = value();
/* 64 */     if (value instanceof Boolean) {
/* 65 */       return ((Boolean)value).booleanValue();
/*    */     }
/*    */     
/* 68 */     if (value instanceof Number) {
/* 69 */       return (((Number)value).intValue() != 0);
/*    */     }
/*    */     
/* 72 */     if (value instanceof String) {
/* 73 */       return Boolean.parseBoolean((String)value);
/*    */     }
/*    */     
/* 76 */     return (value != null);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public String asString() {
/* 82 */     Object value = value();
/*    */     
/* 84 */     if (value == null) {
/* 85 */       return "";
/*    */     }
/* 87 */     return value.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\metadata\MetadataValueAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */