/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.GsonBuilder;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import javax.annotation.Nullable;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import org.spigotmc.SpigotConfig;
/*    */ 
/*    */ public class AdvancementDataWorld
/*    */   extends ResourceDataJson {
/* 18 */   private static final Logger LOGGER = LogManager.getLogger();
/* 19 */   public static final Gson DESERIALIZER = (new GsonBuilder()).create();
/* 20 */   public Advancements REGISTRY = new Advancements();
/*    */   private final LootPredicateManager d;
/*    */   
/*    */   public AdvancementDataWorld(LootPredicateManager lootpredicatemanager) {
/* 24 */     super(DESERIALIZER, "advancements");
/* 25 */     this.d = lootpredicatemanager;
/*    */   }
/*    */   
/*    */   protected void a(Map<MinecraftKey, JsonElement> map, IResourceManager iresourcemanager, GameProfilerFiller gameprofilerfiller) {
/* 29 */     Map<MinecraftKey, Advancement.SerializedAdvancement> map1 = Maps.newHashMap();
/*    */     
/* 31 */     map.forEach((minecraftkey, jsonelement) -> {
/*    */           if (SpigotConfig.disabledAdvancements != null && (SpigotConfig.disabledAdvancements.contains("*") || SpigotConfig.disabledAdvancements.contains(minecraftkey.toString()))) {
/*    */             return;
/*    */           }
/*    */ 
/*    */           
/*    */           try {
/*    */             JsonObject jsonobject = ChatDeserializer.m(jsonelement, "advancement");
/*    */             
/*    */             Advancement.SerializedAdvancement advancement_serializedadvancement = Advancement.SerializedAdvancement.a(jsonobject, new LootDeserializationContext(minecraftkey, this.d));
/*    */             
/*    */             map1.put(minecraftkey, advancement_serializedadvancement);
/* 43 */           } catch (IllegalArgumentException|com.google.gson.JsonParseException jsonparseexception) {
/*    */             LOGGER.error("Parsing error loading custom advancement {}: {}", minecraftkey, jsonparseexception.getMessage());
/*    */           } 
/*    */         });
/*    */     
/* 48 */     Advancements advancements = new Advancements();
/*    */     
/* 50 */     advancements.a(map1);
/* 51 */     Iterator<Advancement> iterator = advancements.b().iterator();
/*    */     
/* 53 */     while (iterator.hasNext()) {
/* 54 */       Advancement advancement = iterator.next();
/*    */       
/* 56 */       if (advancement.c() != null) {
/* 57 */         AdvancementTree.a(advancement);
/*    */       }
/*    */     } 
/*    */     
/* 61 */     this.REGISTRY = advancements;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public Advancement a(MinecraftKey minecraftkey) {
/* 66 */     return this.REGISTRY.a(minecraftkey);
/*    */   }
/*    */   
/*    */   public Collection<Advancement> getAdvancements() {
/* 70 */     return this.REGISTRY.c();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\AdvancementDataWorld.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */