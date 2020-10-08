/*    */ package org.bukkit.craftbukkit.v1_16_R2.util;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.Vec3D;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public final class CraftVector
/*    */ {
/*    */   public static Vector toBukkit(Vec3D nms) {
/*  9 */     return new Vector(nms.x, nms.y, nms.z);
/*    */   }
/*    */   
/*    */   public static Vec3D toNMS(Vector bukkit) {
/* 13 */     return new Vec3D(bukkit.getX(), bukkit.getY(), bukkit.getZ());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R\\util\CraftVector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */