/*    */ package org.bukkit.configuration;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MemoryConfiguration
/*    */   extends MemorySection
/*    */   implements Configuration
/*    */ {
/*    */   protected Configuration defaults;
/*    */   protected MemoryConfigurationOptions options;
/*    */   
/*    */   public MemoryConfiguration() {}
/*    */   
/*    */   public MemoryConfiguration(@Nullable Configuration defaults) {
/* 30 */     this.defaults = defaults;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addDefault(@NotNull String path, @Nullable Object value) {
/* 35 */     Validate.notNull(path, "Path may not be null");
/*    */     
/* 37 */     if (this.defaults == null) {
/* 38 */       this.defaults = new MemoryConfiguration();
/*    */     }
/*    */     
/* 41 */     this.defaults.set(path, value);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addDefaults(@NotNull Map<String, Object> defaults) {
/* 46 */     Validate.notNull(defaults, "Defaults may not be null");
/*    */     
/* 48 */     for (Map.Entry<String, Object> entry : defaults.entrySet()) {
/* 49 */       addDefault(entry.getKey(), entry.getValue());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void addDefaults(@NotNull Configuration defaults) {
/* 55 */     Validate.notNull(defaults, "Defaults may not be null");
/*    */     
/* 57 */     addDefaults(defaults.getValues(true));
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDefaults(@NotNull Configuration defaults) {
/* 62 */     Validate.notNull(defaults, "Defaults may not be null");
/*    */     
/* 64 */     this.defaults = defaults;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Configuration getDefaults() {
/* 70 */     return this.defaults;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public ConfigurationSection getParent() {
/* 76 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public MemoryConfigurationOptions options() {
/* 82 */     if (this.options == null) {
/* 83 */       this.options = new MemoryConfigurationOptions(this);
/*    */     }
/*    */     
/* 86 */     return this.options;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\configuration\MemoryConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */