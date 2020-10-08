/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class TagsFluid
/*    */ {
/*  9 */   protected static final TagUtil<FluidType> a = TagStatic.a(new MinecraftKey("fluid"), ITagRegistry::getFluidTags);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 14 */   public static final Tag.e<FluidType> WATER = a("water");
/* 15 */   public static final Tag.e<FluidType> LAVA = a("lava");
/*    */   
/*    */   private static Tag.e<FluidType> a(String var0) {
/* 18 */     return a.a(var0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static List<? extends Tag.e<FluidType>> b() {
/* 26 */     return a.c();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TagsFluid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */