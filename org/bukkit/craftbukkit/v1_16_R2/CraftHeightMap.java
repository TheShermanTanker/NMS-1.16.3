/*    */ package org.bukkit.craftbukkit.v1_16_R2;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.HeightMap;
/*    */ import org.bukkit.HeightMap;
/*    */ 
/*    */ 
/*    */ 
/*    */ final class CraftHeightMap
/*    */ {
/*    */   public static HeightMap.Type toNMS(HeightMap bukkitHeightMap) {
/* 11 */     switch (bukkitHeightMap) {
/*    */       case WORLD_SURFACE_WG:
/* 13 */         return HeightMap.Type.MOTION_BLOCKING_NO_LEAVES;
/*    */       case WORLD_SURFACE:
/* 15 */         return HeightMap.Type.OCEAN_FLOOR;
/*    */       case OCEAN_FLOOR_WG:
/* 17 */         return HeightMap.Type.OCEAN_FLOOR_WG;
/*    */       case OCEAN_FLOOR:
/* 19 */         return HeightMap.Type.WORLD_SURFACE;
/*    */       case MOTION_BLOCKING_NO_LEAVES:
/* 21 */         return HeightMap.Type.WORLD_SURFACE_WG;
/*    */       case MOTION_BLOCKING:
/* 23 */         return HeightMap.Type.MOTION_BLOCKING;
/*    */     } 
/* 25 */     throw new EnumConstantNotPresentException(HeightMap.Type.class, bukkitHeightMap.name());
/*    */   }
/*    */ 
/*    */   
/*    */   public static HeightMap fromNMS(HeightMap.Type nmsHeightMapType) {
/* 30 */     switch (nmsHeightMapType) {
/*    */       case WORLD_SURFACE_WG:
/* 32 */         return HeightMap.WORLD_SURFACE_WG;
/*    */       case WORLD_SURFACE:
/* 34 */         return HeightMap.WORLD_SURFACE;
/*    */       case OCEAN_FLOOR_WG:
/* 36 */         return HeightMap.OCEAN_FLOOR_WG;
/*    */       case OCEAN_FLOOR:
/* 38 */         return HeightMap.OCEAN_FLOOR;
/*    */       case MOTION_BLOCKING_NO_LEAVES:
/* 40 */         return HeightMap.MOTION_BLOCKING_NO_LEAVES;
/*    */       case MOTION_BLOCKING:
/* 42 */         return HeightMap.MOTION_BLOCKING;
/*    */     } 
/* 44 */     throw new EnumConstantNotPresentException(HeightMap.class, nmsHeightMapType.name());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\CraftHeightMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */