/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.nio.file.Path;
/*    */ import java.util.function.UnaryOperator;
/*    */ import joptsimple.OptionSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DedicatedServerSettings
/*    */ {
/*    */   private final Path path;
/*    */   private DedicatedServerProperties properties;
/*    */   
/*    */   public DedicatedServerSettings(IRegistryCustom iregistrycustom, OptionSet optionset) {
/* 16 */     this.path = ((File)optionset.valueOf("config")).toPath();
/* 17 */     this.properties = DedicatedServerProperties.load(iregistrycustom, this.path, optionset);
/*    */   }
/*    */ 
/*    */   
/*    */   public DedicatedServerProperties getProperties() {
/* 22 */     return this.properties;
/*    */   }
/*    */   
/*    */   public void save() {
/* 26 */     this.properties.savePropertiesFile(this.path);
/*    */   }
/*    */   
/*    */   public DedicatedServerSettings setProperty(UnaryOperator<DedicatedServerProperties> unaryoperator) {
/* 30 */     (this.properties = unaryoperator.apply(this.properties)).savePropertiesFile(this.path);
/* 31 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DedicatedServerSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */