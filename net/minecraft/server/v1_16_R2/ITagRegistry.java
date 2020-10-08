/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface ITagRegistry
/*    */ {
/* 12 */   public static final ITagRegistry a = a(Tags.c(), Tags.c(), Tags.c(), Tags.c());
/*    */   
/*    */   Tags<Block> getBlockTags();
/*    */   
/*    */   Tags<Item> getItemTags();
/*    */   
/*    */   Tags<FluidType> getFluidTags();
/*    */   
/*    */   Tags<EntityTypes<?>> getEntityTags();
/*    */   
/*    */   default void bind() {
/* 23 */     TagStatic.a(this);
/* 24 */     Blocks.a();
/*    */   }
/*    */   
/*    */   default void a(PacketDataSerializer var0) {
/* 28 */     getBlockTags().a(var0, IRegistry.BLOCK);
/* 29 */     getItemTags().a(var0, IRegistry.ITEM);
/* 30 */     getFluidTags().a(var0, IRegistry.FLUID);
/* 31 */     getEntityTags().a(var0, IRegistry.ENTITY_TYPE);
/*    */   }
/*    */   
/*    */   static ITagRegistry b(PacketDataSerializer var0) {
/* 35 */     Tags<Block> var1 = Tags.a(var0, IRegistry.BLOCK);
/* 36 */     Tags<Item> var2 = Tags.a(var0, IRegistry.ITEM);
/* 37 */     Tags<FluidType> var3 = Tags.a(var0, IRegistry.FLUID);
/* 38 */     Tags<EntityTypes<?>> var4 = Tags.a(var0, IRegistry.ENTITY_TYPE);
/* 39 */     return a(var1, var2, var3, var4);
/*    */   }
/*    */   
/*    */   static ITagRegistry a(Tags<Block> var0, Tags<Item> var1, Tags<FluidType> var2, Tags<EntityTypes<?>> var3) {
/* 43 */     return new ITagRegistry(var0, var1, var2, var3)
/*    */       {
/*    */         public Tags<Block> getBlockTags() {
/* 46 */           return this.b;
/*    */         }
/*    */ 
/*    */         
/*    */         public Tags<Item> getItemTags() {
/* 51 */           return this.c;
/*    */         }
/*    */ 
/*    */         
/*    */         public Tags<FluidType> getFluidTags() {
/* 56 */           return this.d;
/*    */         }
/*    */ 
/*    */         
/*    */         public Tags<EntityTypes<?>> getEntityTags() {
/* 61 */           return this.e;
/*    */         }
/*    */       };
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ITagRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */