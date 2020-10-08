/*     */ package org.bukkit.configuration.file;
/*     */ 
/*     */ import org.bukkit.configuration.Configuration;
/*     */ import org.bukkit.configuration.ConfigurationOptions;
/*     */ import org.bukkit.configuration.MemoryConfiguration;
/*     */ import org.bukkit.configuration.MemoryConfigurationOptions;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class FileConfigurationOptions
/*     */   extends MemoryConfigurationOptions
/*     */ {
/*  13 */   private String header = null;
/*     */   private boolean copyHeader = true;
/*     */   
/*     */   protected FileConfigurationOptions(@NotNull MemoryConfiguration configuration) {
/*  17 */     super(configuration);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public FileConfiguration configuration() {
/*  23 */     return (FileConfiguration)super.configuration();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public FileConfigurationOptions copyDefaults(boolean value) {
/*  29 */     super.copyDefaults(value);
/*  30 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public FileConfigurationOptions pathSeparator(char value) {
/*  36 */     super.pathSeparator(value);
/*  37 */     return this;
/*     */   }
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
/*     */   @Nullable
/*     */   public String header() {
/*  56 */     return this.header;
/*     */   }
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
/*     */   @NotNull
/*     */   public FileConfigurationOptions header(@Nullable String value) {
/*  76 */     this.header = value;
/*  77 */     return this;
/*     */   }
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
/*     */   public boolean copyHeader() {
/*  99 */     return this.copyHeader;
/*     */   }
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
/*     */   @NotNull
/*     */   public FileConfigurationOptions copyHeader(boolean value) {
/* 123 */     this.copyHeader = value;
/*     */     
/* 125 */     return this;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\configuration\file\FileConfigurationOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */