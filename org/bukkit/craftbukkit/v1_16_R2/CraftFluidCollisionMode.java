/*    */ package org.bukkit.craftbukkit.v1_16_R2;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.RayTrace;
/*    */ import org.bukkit.FluidCollisionMode;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class CraftFluidCollisionMode
/*    */ {
/*    */   public static RayTrace.FluidCollisionOption toNMS(FluidCollisionMode fluidCollisionMode) {
/* 11 */     if (fluidCollisionMode == null) return null;
/*    */     
/* 13 */     switch (fluidCollisionMode) {
/*    */       case ALWAYS:
/* 15 */         return RayTrace.FluidCollisionOption.ANY;
/*    */       case SOURCE_ONLY:
/* 17 */         return RayTrace.FluidCollisionOption.SOURCE_ONLY;
/*    */       case NEVER:
/* 19 */         return RayTrace.FluidCollisionOption.NONE;
/*    */     } 
/* 21 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\CraftFluidCollisionMode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */