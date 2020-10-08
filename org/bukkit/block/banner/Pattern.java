/*    */ package org.bukkit.block.banner;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ import java.util.NoSuchElementException;
/*    */ import org.bukkit.DyeColor;
/*    */ import org.bukkit.configuration.serialization.ConfigurationSerializable;
/*    */ import org.bukkit.configuration.serialization.SerializableAs;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SerializableAs("Pattern")
/*    */ public class Pattern
/*    */   implements ConfigurationSerializable
/*    */ {
/*    */   private static final String COLOR = "color";
/*    */   private static final String PATTERN = "pattern";
/*    */   private final DyeColor color;
/*    */   private final PatternType pattern;
/*    */   
/*    */   public Pattern(@NotNull DyeColor color, @NotNull PatternType pattern) {
/* 28 */     this.color = color;
/* 29 */     this.pattern = pattern;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Pattern(@NotNull Map<String, Object> map) {
/* 38 */     this.color = DyeColor.legacyValueOf(getString(map, "color"));
/* 39 */     this.pattern = PatternType.getByIdentifier(getString(map, "pattern"));
/*    */   }
/*    */   
/*    */   private static String getString(@NotNull Map<?, ?> map, @NotNull Object key) {
/* 43 */     Object str = map.get(key);
/* 44 */     if (str instanceof String) {
/* 45 */       return (String)str;
/*    */     }
/* 47 */     throw new NoSuchElementException(map + " does not contain " + key);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Map<String, Object> serialize() {
/* 53 */     return (Map<String, Object>)ImmutableMap.of("color", this.color
/* 54 */         .toString(), "pattern", this.pattern
/* 55 */         .getIdentifier());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public DyeColor getColor() {
/* 66 */     return this.color;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public PatternType getPattern() {
/* 76 */     return this.pattern;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 81 */     int hash = 3;
/* 82 */     hash = 97 * hash + ((this.color != null) ? this.color.hashCode() : 0);
/* 83 */     hash = 97 * hash + ((this.pattern != null) ? this.pattern.hashCode() : 0);
/* 84 */     return hash;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 89 */     if (obj == null) {
/* 90 */       return false;
/*    */     }
/* 92 */     if (getClass() != obj.getClass()) {
/* 93 */       return false;
/*    */     }
/* 95 */     Pattern other = (Pattern)obj;
/* 96 */     return (this.color == other.color && this.pattern == other.pattern);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\banner\Pattern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */