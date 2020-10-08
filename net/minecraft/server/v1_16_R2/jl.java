/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.function.Consumer;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class jl
/*    */ {
/*    */   private final RecipeSerializerComplex<?> a;
/*    */   
/*    */   public jl(RecipeSerializerComplex<?> var0) {
/* 15 */     this.a = var0;
/*    */   }
/*    */   
/*    */   public static jl a(RecipeSerializerComplex<?> var0) {
/* 19 */     return new jl(var0);
/*    */   }
/*    */   
/*    */   public void a(Consumer<jf> var0, String var1) {
/* 23 */     var0.accept(new jf(this, var1)
/*    */         {
/*    */           public void a(JsonObject var0) {}
/*    */ 
/*    */ 
/*    */           
/*    */           public RecipeSerializer<?> c() {
/* 30 */             return jl.a(this.b);
/*    */           }
/*    */ 
/*    */           
/*    */           public MinecraftKey b() {
/* 35 */             return new MinecraftKey(this.a);
/*    */           }
/*    */ 
/*    */           
/*    */           @Nullable
/*    */           public JsonObject d() {
/* 41 */             return null;
/*    */           }
/*    */ 
/*    */           
/*    */           public MinecraftKey e() {
/* 46 */             return new MinecraftKey("");
/*    */           }
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\jl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */