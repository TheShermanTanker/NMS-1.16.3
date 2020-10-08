/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.io.IOException;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutRecipeUpdate
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private List<IRecipe<?>> a;
/*    */   
/*    */   public PacketPlayOutRecipeUpdate() {}
/*    */   
/*    */   public PacketPlayOutRecipeUpdate(Collection<IRecipe<?>> var0) {
/* 22 */     this.a = Lists.newArrayList(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 27 */     var0.a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 32 */     this.a = Lists.newArrayList();
/* 33 */     int var1 = var0.i();
/* 34 */     for (int var2 = 0; var2 < var1; var2++) {
/* 35 */       this.a.add(c(var0));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 41 */     var0.d(this.a.size());
/* 42 */     for (IRecipe<?> var2 : this.a) {
/* 43 */       a(var2, var0);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static IRecipe<?> c(PacketDataSerializer var0) {
/* 52 */     MinecraftKey var1 = var0.p();
/* 53 */     MinecraftKey var2 = var0.p();
/*    */     
/* 55 */     return ((RecipeSerializer<IRecipe<?>>)IRegistry.RECIPE_SERIALIZER.getOptional(var1)
/* 56 */       .orElseThrow(() -> new IllegalArgumentException("Unknown recipe serializer " + var0)))
/* 57 */       .a(var2, var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public static <T extends IRecipe<?>> void a(T var0, PacketDataSerializer var1) {
/* 62 */     var1.a(IRegistry.RECIPE_SERIALIZER.getKey(var0.getRecipeSerializer()));
/* 63 */     var1.a(var0.getKey());
/* 64 */     var0.getRecipeSerializer().a(var1, var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutRecipeUpdate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */