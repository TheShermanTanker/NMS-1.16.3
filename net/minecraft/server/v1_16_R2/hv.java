/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.UnmodifiableIterator;
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.GsonBuilder;
/*    */ import com.google.gson.JsonArray;
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
/*    */ 
/*    */ 
/*    */ public class hv
/*    */   implements DebugReportProvider
/*    */ {
/* 22 */   private static final Gson b = (new GsonBuilder()).setPrettyPrinting().create();
/*    */   private final DebugReportGenerator c;
/*    */   
/*    */   public hv(DebugReportGenerator var0) {
/* 26 */     this.c = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(HashCache var0) throws IOException {
/* 31 */     JsonObject var1 = new JsonObject();
/*    */     
/* 33 */     for (Block var3 : IRegistry.BLOCK) {
/* 34 */       MinecraftKey var4 = IRegistry.BLOCK.getKey(var3);
/* 35 */       JsonObject var5 = new JsonObject();
/* 36 */       BlockStateList<Block, IBlockData> var6 = var3.getStates();
/*    */       
/* 38 */       if (!var6.d().isEmpty()) {
/* 39 */         JsonObject jsonObject = new JsonObject();
/* 40 */         for (IBlockState<?> var9 : var6.d()) {
/* 41 */           JsonArray var10 = new JsonArray();
/* 42 */           for (Comparable<?> var12 : var9.getValues()) {
/* 43 */             var10.add(SystemUtils.a(var9, var12));
/*    */           }
/* 45 */           jsonObject.add(var9.getName(), (JsonElement)var10);
/*    */         } 
/*    */         
/* 48 */         var5.add("properties", (JsonElement)jsonObject);
/*    */       } 
/*    */       
/* 51 */       JsonArray var7 = new JsonArray();
/* 52 */       for (UnmodifiableIterator<IBlockData> unmodifiableIterator = var6.a().iterator(); unmodifiableIterator.hasNext(); ) { IBlockData var9 = unmodifiableIterator.next();
/* 53 */         JsonObject var10 = new JsonObject();
/* 54 */         JsonObject var11 = new JsonObject();
/* 55 */         for (IBlockState<?> var13 : var6.d()) {
/* 56 */           var11.addProperty(var13.getName(), SystemUtils.a(var13, var9.get(var13)));
/*    */         }
/* 58 */         if (var11.size() > 0) {
/* 59 */           var10.add("properties", (JsonElement)var11);
/*    */         }
/* 61 */         var10.addProperty("id", Integer.valueOf(Block.getCombinedId(var9)));
/* 62 */         if (var9 == var3.getBlockData()) {
/* 63 */           var10.addProperty("default", Boolean.valueOf(true));
/*    */         }
/* 65 */         var7.add((JsonElement)var10); }
/*    */ 
/*    */       
/* 68 */       var5.add("states", (JsonElement)var7);
/* 69 */       var1.add(var4.toString(), (JsonElement)var5);
/*    */     } 
/*    */     
/* 72 */     Path var2 = this.c.b().resolve("reports/blocks.json");
/* 73 */     DebugReportProvider.a(b, var0, (JsonElement)var1, var2);
/*    */   }
/*    */ 
/*    */   
/*    */   public String a() {
/* 78 */     return "Block List";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\hv.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */