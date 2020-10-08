/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.GsonBuilder;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import java.io.IOException;
/*    */ import java.nio.file.Path;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class hx
/*    */   implements DebugReportProvider
/*    */ {
/* 18 */   private static final Gson b = (new GsonBuilder()).setPrettyPrinting().create();
/*    */   private final DebugReportGenerator c;
/*    */   
/*    */   public hx(DebugReportGenerator var0) {
/* 22 */     this.c = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(HashCache var0) throws IOException {
/* 27 */     JsonObject var1 = new JsonObject();
/*    */     
/* 29 */     IRegistry.f.keySet().forEach(var1 -> var0.add(var1.toString(), a(IRegistry.f.get(var1))));
/*    */     
/* 31 */     Path var2 = this.c.b().resolve("reports/registries.json");
/* 32 */     DebugReportProvider.a(b, var0, (JsonElement)var1, var2);
/*    */   }
/*    */ 
/*    */   
/*    */   private static <T> JsonElement a(IRegistry<T> var0) {
/* 37 */     JsonObject var1 = new JsonObject();
/*    */     
/* 39 */     if (var0 instanceof RegistryBlocks) {
/* 40 */       MinecraftKey minecraftKey = ((RegistryBlocks)var0).a();
/* 41 */       var1.addProperty("default", minecraftKey.toString());
/*    */     } 
/*    */     
/* 44 */     int var2 = IRegistry.f.a(var0);
/* 45 */     var1.addProperty("protocol_id", Integer.valueOf(var2));
/*    */     
/* 47 */     JsonObject var3 = new JsonObject();
/* 48 */     for (MinecraftKey var5 : var0.keySet()) {
/* 49 */       T var6 = var0.get(var5);
/* 50 */       int var7 = var0.a(var6);
/*    */       
/* 52 */       JsonObject var8 = new JsonObject();
/* 53 */       var8.addProperty("protocol_id", Integer.valueOf(var7));
/*    */       
/* 55 */       var3.add(var5.toString(), (JsonElement)var8);
/*    */     } 
/* 57 */     var1.add("entries", (JsonElement)var3);
/* 58 */     return (JsonElement)var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public String a() {
/* 63 */     return "Registry Dump";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\hx.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */