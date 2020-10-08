/*    */ package org.bukkit.configuration;
/*    */ 
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MemoryConfigurationOptions
/*    */   extends ConfigurationOptions
/*    */ {
/*    */   protected MemoryConfigurationOptions(@NotNull MemoryConfiguration configuration) {
/* 11 */     super(configuration);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public MemoryConfiguration configuration() {
/* 17 */     return (MemoryConfiguration)super.configuration();
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public MemoryConfigurationOptions copyDefaults(boolean value) {
/* 23 */     super.copyDefaults(value);
/* 24 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public MemoryConfigurationOptions pathSeparator(char value) {
/* 30 */     super.pathSeparator(value);
/* 31 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\configuration\MemoryConfigurationOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */