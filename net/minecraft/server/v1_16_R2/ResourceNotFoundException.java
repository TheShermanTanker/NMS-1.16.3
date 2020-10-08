/*   */ package net.minecraft.server.v1_16_R2;
/*   */ 
/*   */ import java.io.File;
/*   */ import java.io.FileNotFoundException;
/*   */ 
/*   */ public class ResourceNotFoundException extends FileNotFoundException {
/*   */   public ResourceNotFoundException(File var0, String var1) {
/* 8 */     super(String.format("'%s' in ResourcePack '%s'", new Object[] { var1, var0 }));
/*   */   }
/*   */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ResourceNotFoundException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */