/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.nio.file.InvalidPathException;
/*    */ import java.nio.file.Path;
/*    */ import java.nio.file.Paths;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FileUtils
/*    */ {
/* 13 */   private static final Pattern a = Pattern.compile("(<name>.*) \\((<count>\\d*)\\)", 66);
/*    */ 
/*    */   
/* 16 */   private static final Pattern b = Pattern.compile(".*\\.|(?:COM|CLOCK\\$|CON|PRN|AUX|NUL|COM[1-9]|LPT[1-9])(?:\\..*)?", 2);
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean a(Path var0) {
/* 66 */     Path var1 = var0.normalize();
/* 67 */     return var1.equals(var0);
/*    */   }
/*    */   
/*    */   public static boolean b(Path var0) {
/* 71 */     for (Path var2 : var0) {
/* 72 */       if (b.matcher(var2.toString()).matches()) {
/* 73 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 77 */     return true;
/*    */   }
/*    */   
/*    */   public static Path b(Path var0, String var1, String var2) {
/* 81 */     String var3 = var1 + var2;
/* 82 */     Path var4 = Paths.get(var3, new String[0]);
/*    */     
/* 84 */     if (var4.endsWith(var2)) {
/* 85 */       throw new InvalidPathException(var3, "empty resource name");
/*    */     }
/*    */     
/* 88 */     return var0.resolve(var4);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\FileUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */