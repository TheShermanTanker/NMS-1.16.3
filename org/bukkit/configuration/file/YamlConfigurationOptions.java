/*    */ package org.bukkit.configuration.file;
/*    */ 
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.configuration.Configuration;
/*    */ import org.bukkit.configuration.ConfigurationOptions;
/*    */ import org.bukkit.configuration.MemoryConfiguration;
/*    */ import org.bukkit.configuration.MemoryConfigurationOptions;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class YamlConfigurationOptions extends FileConfigurationOptions {
/* 12 */   private int indent = 2;
/*    */   
/*    */   protected YamlConfigurationOptions(@NotNull YamlConfiguration configuration) {
/* 15 */     super(configuration);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public YamlConfiguration configuration() {
/* 21 */     return (YamlConfiguration)super.configuration();
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public YamlConfigurationOptions copyDefaults(boolean value) {
/* 27 */     super.copyDefaults(value);
/* 28 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public YamlConfigurationOptions pathSeparator(char value) {
/* 34 */     super.pathSeparator(value);
/* 35 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public YamlConfigurationOptions header(@Nullable String value) {
/* 41 */     super.header(value);
/* 42 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public YamlConfigurationOptions copyHeader(boolean value) {
/* 48 */     super.copyHeader(value);
/* 49 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int indent() {
/* 60 */     return this.indent;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public YamlConfigurationOptions indent(int value) {
/* 73 */     Validate.isTrue((value >= 2), "Indent must be at least 2 characters");
/* 74 */     Validate.isTrue((value <= 9), "Indent cannot be greater than 9 characters");
/*    */     
/* 76 */     this.indent = value;
/* 77 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\configuration\file\YamlConfigurationOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */