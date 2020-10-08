/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Multimap;
/*    */ import java.util.Map;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import java.util.concurrent.Executor;
/*    */ import java.util.stream.Collectors;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TagRegistry
/*    */   implements IReloadListener
/*    */ {
/* 20 */   private final TagDataPack<Block> blockTags = new TagDataPack<>(IRegistry.BLOCK::getOptional, "tags/blocks", "block");
/* 21 */   private final TagDataPack<Item> itemTags = new TagDataPack<>(IRegistry.ITEM::getOptional, "tags/items", "item");
/* 22 */   private final TagDataPack<FluidType> fluidTags = new TagDataPack<>(IRegistry.FLUID::getOptional, "tags/fluids", "fluid");
/* 23 */   private final TagDataPack<EntityTypes<?>> entityTags = new TagDataPack<>(IRegistry.ENTITY_TYPE::getOptional, "tags/entity_types", "entity_type");
/*    */   
/* 25 */   private ITagRegistry e = ITagRegistry.a;
/*    */   
/*    */   public ITagRegistry a() {
/* 28 */     return this.e;
/*    */   }
/*    */ 
/*    */   
/*    */   public CompletableFuture<Void> a(IReloadListener.a var0, IResourceManager var1, GameProfilerFiller var2, GameProfilerFiller var3, Executor var4, Executor var5) {
/* 33 */     CompletableFuture<Map<MinecraftKey, Tag.a>> var6 = this.blockTags.a(var1, var4);
/* 34 */     CompletableFuture<Map<MinecraftKey, Tag.a>> var7 = this.itemTags.a(var1, var4);
/* 35 */     CompletableFuture<Map<MinecraftKey, Tag.a>> var8 = this.fluidTags.a(var1, var4);
/* 36 */     CompletableFuture<Map<MinecraftKey, Tag.a>> var9 = this.entityTags.a(var1, var4);
/* 37 */     return CompletableFuture.allOf((CompletableFuture<?>[])new CompletableFuture[] { var6, var7, var8, var9
/* 38 */         }).thenCompose(var0::a)
/* 39 */       .thenAcceptAsync(var4 -> {
/*    */           Tags<Block> var5 = this.blockTags.a(var0.join());
/*    */           Tags<Item> var6 = this.itemTags.a(var1.join());
/*    */           Tags<FluidType> var7 = this.fluidTags.a(var2.join());
/*    */           Tags<EntityTypes<?>> var8 = this.entityTags.a(var3.join());
/*    */           ITagRegistry var9 = ITagRegistry.a(var5, var6, var7, var8);
/*    */           Multimap<MinecraftKey, MinecraftKey> var10 = TagStatic.b(var9);
/*    */           if (!var10.isEmpty())
/*    */             throw new IllegalStateException("Missing required tags: " + (String)var10.entries().stream().map(()).sorted().collect(Collectors.joining(","))); 
/*    */           TagsInstance.a(var9);
/*    */           this.e = var9;
/*    */         }var5);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TagRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */