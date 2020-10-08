/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.google.common.collect.Sets;
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.GsonBuilder;
/*    */ import com.google.gson.JsonElement;
/*    */ import java.io.IOException;
/*    */ import java.nio.file.Path;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import java.util.function.Consumer;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ho
/*    */   implements DebugReportProvider
/*    */ {
/* 22 */   private static final Logger LOGGER = LogManager.getLogger();
/* 23 */   private static final Gson c = (new GsonBuilder()).setPrettyPrinting().create();
/*    */   
/*    */   private final DebugReportGenerator d;
/* 26 */   private final List<Consumer<Consumer<Advancement>>> e = (List<Consumer<Consumer<Advancement>>>)ImmutableList.of(new ht(), new hq(), new hp(), new hr(), new hs());
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ho(DebugReportGenerator var0) {
/* 35 */     this.d = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(HashCache var0) throws IOException {
/* 40 */     Path var1 = this.d.b();
/* 41 */     Set<MinecraftKey> var2 = Sets.newHashSet();
/* 42 */     Consumer<Advancement> var3 = var3 -> {
/*    */         if (!var0.add(var3.getName())) {
/*    */           throw new IllegalStateException("Duplicate advancement " + var3.getName());
/*    */         }
/*    */         
/*    */         Path var4 = a(var1, var3);
/*    */         try {
/*    */           DebugReportProvider.a(c, var2, (JsonElement)var3.a().b(), var4);
/* 50 */         } catch (IOException var5) {
/*    */           LOGGER.error("Couldn't save advancement {}", var4, var5);
/*    */         } 
/*    */       };
/*    */     
/* 55 */     for (Consumer<Consumer<Advancement>> var5 : this.e) {
/* 56 */       var5.accept(var3);
/*    */     }
/*    */   }
/*    */   
/*    */   private static Path a(Path var0, Advancement var1) {
/* 61 */     return var0.resolve("data/" + var1.getName().getNamespace() + "/advancements/" + var1.getName().getKey() + ".json");
/*    */   }
/*    */ 
/*    */   
/*    */   public String a() {
/* 66 */     return "Advancements";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ho.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */