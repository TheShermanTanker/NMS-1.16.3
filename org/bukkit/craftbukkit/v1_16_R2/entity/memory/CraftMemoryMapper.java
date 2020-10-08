/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity.memory;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*    */ import net.minecraft.server.v1_16_R2.GlobalPos;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class CraftMemoryMapper
/*    */ {
/*    */   public static Object fromNms(Object object) {
/* 16 */     if (object instanceof GlobalPos)
/* 17 */       return fromNms((GlobalPos)object); 
/* 18 */     if (object instanceof Long)
/* 19 */       return object; 
/* 20 */     if (object instanceof java.util.UUID)
/* 21 */       return object; 
/* 22 */     if (object instanceof Boolean) {
/* 23 */       return object;
/*    */     }
/*    */     
/* 26 */     throw new UnsupportedOperationException("Do not know how to map " + object);
/*    */   }
/*    */   
/*    */   public static Object toNms(Object object) {
/* 30 */     if (object == null)
/* 31 */       return null; 
/* 32 */     if (object instanceof Location)
/* 33 */       return toNms((Location)object); 
/* 34 */     if (object instanceof Long)
/* 35 */       return object; 
/* 36 */     if (object instanceof java.util.UUID)
/* 37 */       return object; 
/* 38 */     if (object instanceof Boolean) {
/* 39 */       return object;
/*    */     }
/*    */     
/* 42 */     throw new UnsupportedOperationException("Do not know how to map " + object);
/*    */   }
/*    */   
/*    */   public static Location fromNms(GlobalPos globalPos) {
/* 46 */     return new Location((World)((CraftServer)Bukkit.getServer()).getServer().getWorldServer(globalPos.getDimensionManager()).getWorld(), globalPos.getBlockPosition().getX(), globalPos.getBlockPosition().getY(), globalPos.getBlockPosition().getZ());
/*    */   }
/*    */   
/*    */   public static GlobalPos toNms(Location location) {
/* 50 */     return GlobalPos.create(((CraftWorld)location.getWorld()).getHandle().getDimensionKey(), new BlockPosition(location.getX(), location.getY(), location.getZ()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\memory\CraftMemoryMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */