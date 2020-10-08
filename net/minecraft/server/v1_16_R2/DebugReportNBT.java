/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.BufferedWriter;
/*    */ import java.io.IOException;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Path;
/*    */ import java.nio.file.attribute.FileAttribute;
/*    */ import java.util.Iterator;
/*    */ import javax.annotation.Nullable;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DebugReportNBT
/*    */   implements DebugReportProvider
/*    */ {
/* 19 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   private final DebugReportGenerator c;
/*    */   
/*    */   public DebugReportNBT(DebugReportGenerator var0) {
/* 24 */     this.c = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(HashCache var0) throws IOException {
/* 29 */     Path var1 = this.c.b();
/*    */     
/* 31 */     for (Iterator<Path> iterator = this.c.a().iterator(); iterator.hasNext(); ) { Path var3 = iterator.next();
/* 32 */       Files.walk(var3, new java.nio.file.FileVisitOption[0]).filter(var0 -> var0.toString().endsWith(".nbt")).forEach(var2 -> a(var2, a(var0, var2), var1)); }
/*    */   
/*    */   }
/*    */ 
/*    */   
/*    */   public String a() {
/* 38 */     return "NBT to SNBT";
/*    */   }
/*    */   
/*    */   private String a(Path var0, Path var1) {
/* 42 */     String var2 = var0.relativize(var1).toString().replaceAll("\\\\", "/");
/* 43 */     return var2.substring(0, var2.length() - ".nbt".length());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public static Path a(Path var0, String var1, Path var2) {
/*    */     try {
/* 53 */       NBTTagCompound var3 = NBTCompressedStreamTools.a(Files.newInputStream(var0, new java.nio.file.OpenOption[0]));
/* 54 */       IChatBaseComponent var4 = var3.a("    ", 0);
/* 55 */       String var5 = var4.getString() + "\n";
/* 56 */       Path var6 = var2.resolve(var1 + ".snbt");
/* 57 */       Files.createDirectories(var6.getParent(), (FileAttribute<?>[])new FileAttribute[0]);
/* 58 */       try (BufferedWriter var7 = Files.newBufferedWriter(var6, new java.nio.file.OpenOption[0])) {
/* 59 */         var7.write(var5);
/*    */       } 
/* 61 */       LOGGER.info("Converted {} from NBT to SNBT", var1);
/* 62 */       return var6;
/* 63 */     } catch (IOException var3) {
/* 64 */       LOGGER.error("Couldn't convert {} from NBT to SNBT at {}", var1, var0, var3);
/* 65 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DebugReportNBT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */