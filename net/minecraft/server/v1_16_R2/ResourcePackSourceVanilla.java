/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.Consumer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ResourcePackSourceVanilla
/*    */   implements ResourcePackSource
/*    */ {
/* 11 */   private final ResourcePackVanilla a = new ResourcePackVanilla(new String[] { "minecraft" });
/*    */ 
/*    */   
/*    */   public void a(Consumer<ResourcePackLoader> var0, ResourcePackLoader.a var1) {
/* 15 */     ResourcePackLoader var2 = ResourcePackLoader.a("vanilla", false, () -> this.a, var1, ResourcePackLoader.Position.BOTTOM, PackSource.b);
/* 16 */     if (var2 != null)
/* 17 */       var0.accept(var2); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ResourcePackSourceVanilla.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */