/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.nio.file.Path;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ju
/*    */   extends jw<FluidType>
/*    */ {
/*    */   public ju(DebugReportGenerator var0) {
/* 14 */     super(var0, IRegistry.FLUID);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void b() {
/* 19 */     a(TagsFluid.WATER).a(new FluidType[] { FluidTypes.WATER, FluidTypes.FLOWING_WATER });
/* 20 */     a(TagsFluid.LAVA).a(new FluidType[] { FluidTypes.LAVA, FluidTypes.FLOWING_LAVA });
/*    */   }
/*    */ 
/*    */   
/*    */   protected Path a(MinecraftKey var0) {
/* 25 */     return this.b.b().resolve("data/" + var0.getNamespace() + "/tags/fluids/" + var0.getKey() + ".json");
/*    */   }
/*    */ 
/*    */   
/*    */   public String a() {
/* 30 */     return "Fluid Tags";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ju.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */