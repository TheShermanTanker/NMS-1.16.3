/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.UnmodifiableIterator;
/*    */ 
/*    */ public class FluidTypes {
/*  6 */   public static final FluidType EMPTY = a("empty", new FluidTypeEmpty());
/*  7 */   public static final FluidTypeFlowing FLOWING_WATER = a("flowing_water", new FluidTypeWater.a());
/*  8 */   public static final FluidTypeFlowing WATER = a("water", new FluidTypeWater.b());
/*  9 */   public static final FluidTypeFlowing FLOWING_LAVA = a("flowing_lava", new FluidTypeLava.a());
/* 10 */   public static final FluidTypeFlowing LAVA = a("lava", new FluidTypeLava.b());
/*    */   
/*    */   private static <T extends FluidType> T a(String var0, T var1) {
/* 13 */     return (T)IRegistry.<FluidType>a(IRegistry.FLUID, var0, (FluidType)var1);
/*    */   }
/*    */   
/*    */   static {
/* 17 */     for (FluidType var1 : IRegistry.FLUID) {
/* 18 */       for (UnmodifiableIterator<Fluid> unmodifiableIterator = var1.g().a().iterator(); unmodifiableIterator.hasNext(); ) { Fluid var3 = unmodifiableIterator.next();
/* 19 */         FluidType.c.b(var3); }
/*    */     
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\FluidTypes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */