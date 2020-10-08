/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.GsonBuilder;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.mojang.serialization.DataResult;
/*    */ import com.mojang.serialization.Encoder;
/*    */ import com.mojang.serialization.JsonOps;
/*    */ import java.io.IOException;
/*    */ import java.nio.file.Path;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Supplier;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ks
/*    */   implements DebugReportProvider
/*    */ {
/* 26 */   private static final Logger LOGGER = LogManager.getLogger();
/* 27 */   private static final Gson c = (new GsonBuilder()).setPrettyPrinting().create();
/*    */   
/*    */   private final DebugReportGenerator d;
/*    */   
/*    */   public ks(DebugReportGenerator var0) {
/* 32 */     this.d = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(HashCache var0) {
/* 37 */     Path var1 = this.d.b();
/*    */     
/* 39 */     for (Map.Entry<ResourceKey<BiomeBase>, BiomeBase> var3 : RegistryGeneration.WORLDGEN_BIOME.d()) {
/* 40 */       Path var4 = a(var1, ((ResourceKey)var3.getKey()).a());
/* 41 */       BiomeBase var5 = var3.getValue();
/* 42 */       Function<Supplier<BiomeBase>, DataResult<JsonElement>> var6 = JsonOps.INSTANCE.withEncoder((Encoder)BiomeBase.d);
/*    */       try {
/* 44 */         Optional<JsonElement> var7 = ((DataResult)var6.apply(() -> var0)).result();
/* 45 */         if (var7.isPresent()) {
/* 46 */           DebugReportProvider.a(c, var0, var7.get(), var4); continue;
/*    */         } 
/* 48 */         LOGGER.error("Couldn't serialize biome {}", var4);
/*    */       }
/* 50 */       catch (IOException var7) {
/* 51 */         LOGGER.error("Couldn't save biome {}", var4, var7);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private static Path a(Path var0, MinecraftKey var1) {
/* 57 */     return var0.resolve("reports/biomes/" + var1.getKey() + ".json");
/*    */   }
/*    */ 
/*    */   
/*    */   public String a() {
/* 62 */     return "Biomes";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */