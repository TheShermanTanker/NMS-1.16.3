/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.hash.HashFunction;
/*    */ import com.google.common.hash.Hashing;
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.JsonElement;
/*    */ import java.io.BufferedWriter;
/*    */ import java.io.IOException;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Path;
/*    */ import java.nio.file.attribute.FileAttribute;
/*    */ import java.util.Objects;
/*    */ 
/*    */ public interface DebugReportProvider {
/* 15 */   public static final HashFunction a = Hashing.sha1();
/*    */   
/*    */   void a(HashCache paramHashCache) throws IOException;
/*    */   
/*    */   String a();
/*    */   
/*    */   static void a(Gson var0, HashCache var1, JsonElement var2, Path var3) throws IOException {
/* 22 */     String var4 = var0.toJson(var2);
/* 23 */     String var5 = a.hashUnencodedChars(var4).toString();
/*    */     
/* 25 */     if (!Objects.equals(var1.a(var3), var5) || !Files.exists(var3, new java.nio.file.LinkOption[0])) {
/* 26 */       Files.createDirectories(var3.getParent(), (FileAttribute<?>[])new FileAttribute[0]);
/* 27 */       try (BufferedWriter var6 = Files.newBufferedWriter(var3, new java.nio.file.OpenOption[0])) {
/* 28 */         var6.write(var4);
/*    */       } 
/*    */     } 
/* 31 */     var1.a(var3, var5);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DebugReportProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */