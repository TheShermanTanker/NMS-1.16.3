/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import java.util.concurrent.Executor;
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
/*    */ public class DataPackResources
/*    */   implements AutoCloseable
/*    */ {
/* 21 */   private static final CompletableFuture<Unit> a = CompletableFuture.completedFuture(Unit.INSTANCE);
/*    */   
/* 23 */   private final IReloadableResourceManager b = new ResourceManager(EnumResourcePackType.SERVER_DATA);
/*    */   
/*    */   public CommandDispatcher commandDispatcher;
/* 26 */   private final CraftingManager d = new CraftingManager();
/* 27 */   private final TagRegistry e = new TagRegistry();
/* 28 */   private final LootPredicateManager f = new LootPredicateManager();
/* 29 */   private final LootTableRegistry g = new LootTableRegistry(this.f);
/* 30 */   private final AdvancementDataWorld h = new AdvancementDataWorld(this.f);
/*    */   private final CustomFunctionManager i;
/*    */   
/*    */   public DataPackResources(CommandDispatcher.ServerType var0, int var1) {
/* 34 */     this.commandDispatcher = new CommandDispatcher(var0);
/* 35 */     this.i = new CustomFunctionManager(var1, this.commandDispatcher.a());
/*    */     
/* 37 */     this.b.a(this.e);
/* 38 */     this.b.a(this.f);
/* 39 */     this.b.a(this.d);
/* 40 */     this.b.a(this.g);
/* 41 */     this.b.a(this.i);
/* 42 */     this.b.a(this.h);
/*    */   }
/*    */   
/*    */   public CustomFunctionManager a() {
/* 46 */     return this.i;
/*    */   }
/*    */   
/*    */   public LootPredicateManager b() {
/* 50 */     return this.f;
/*    */   }
/*    */   
/*    */   public LootTableRegistry c() {
/* 54 */     return this.g;
/*    */   }
/*    */   
/*    */   public ITagRegistry d() {
/* 58 */     return this.e.a();
/*    */   }
/*    */   
/*    */   public CraftingManager e() {
/* 62 */     return this.d;
/*    */   }
/*    */   
/*    */   public CommandDispatcher f() {
/* 66 */     return this.commandDispatcher;
/*    */   }
/*    */   
/*    */   public AdvancementDataWorld g() {
/* 70 */     return this.h;
/*    */   }
/*    */   
/*    */   public IResourceManager h() {
/* 74 */     return this.b;
/*    */   }
/*    */   
/*    */   public static CompletableFuture<DataPackResources> a(List<IResourcePack> var0, CommandDispatcher.ServerType var1, int var2, Executor var3, Executor var4) {
/* 78 */     DataPackResources var5 = new DataPackResources(var1, var2);
/* 79 */     CompletableFuture<Unit> var6 = var5.b.a(var3, var4, var0, a);
/* 80 */     return var6.whenComplete((var1, var2) -> {
/*    */           if (var2 != null) {
/*    */             var0.close();
/*    */           }
/* 84 */         }).thenApply(var1 -> var0);
/*    */   }
/*    */   
/*    */   public void i() {
/* 88 */     this.e.a().bind();
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() {
/* 93 */     this.b.close();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataPackResources.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */