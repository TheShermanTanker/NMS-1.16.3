/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ 
/*    */ public class RecipeSerializerComplex<T extends IRecipe<?>>
/*    */   implements RecipeSerializer<T>
/*    */ {
/*    */   private final Function<MinecraftKey, T> v;
/*    */   
/*    */   public RecipeSerializerComplex(Function<MinecraftKey, T> var0) {
/* 13 */     this.v = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public T a(MinecraftKey var0, JsonObject var1) {
/* 18 */     return this.v.apply(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public T a(MinecraftKey var0, PacketDataSerializer var1) {
/* 23 */     return this.v.apply(var0);
/*    */   }
/*    */   
/*    */   public void a(PacketDataSerializer var0, T var1) {}
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RecipeSerializerComplex.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */