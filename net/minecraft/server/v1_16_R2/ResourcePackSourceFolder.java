/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileFilter;
/*    */ import java.util.function.Consumer;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ public class ResourcePackSourceFolder
/*    */   implements ResourcePackSource {
/*    */   private static final FileFilter a;
/*    */   
/*    */   static {
/* 13 */     a = (var0 -> {
/* 14 */         boolean var1 = (var0.isFile() && var0.getName().endsWith(".zip"));
/* 15 */         boolean var2 = (var0.isDirectory() && (new File(var0, "pack.mcmeta")).isFile());
/*    */         
/* 17 */         return (var1 || var2);
/*    */       });
/*    */   }
/*    */   private final File file;
/*    */   private final PackSource c;
/*    */   
/*    */   public ResourcePackSourceFolder(File var0, PackSource var1) {
/* 24 */     this.file = var0;
/* 25 */     this.c = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(Consumer<ResourcePackLoader> var0, ResourcePackLoader.a var1) {
/* 30 */     if (!this.file.isDirectory())
/*    */     {
/* 32 */       this.file.mkdirs();
/*    */     }
/* 34 */     File[] var2 = this.file.listFiles(a);
/* 35 */     if (var2 == null) {
/*    */       return;
/*    */     }
/* 38 */     for (File var6 : var2) {
/* 39 */       String var7 = "file/" + var6.getName();
/* 40 */       ResourcePackLoader var8 = ResourcePackLoader.a(var7, false, a(var6), var1, ResourcePackLoader.Position.TOP, this.c);
/* 41 */       if (var8 != null) {
/* 42 */         var0.accept(var8);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   private Supplier<IResourcePack> a(File var0) {
/* 48 */     if (var0.isDirectory()) {
/* 49 */       return () -> new ResourcePackFolder(var0);
/*    */     }
/* 51 */     return () -> new ResourcePackFile(var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ResourcePackSourceFolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */