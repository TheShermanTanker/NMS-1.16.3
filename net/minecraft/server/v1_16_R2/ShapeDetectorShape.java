/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftPortalEvent;
/*    */ 
/*    */ 
/*    */ public class ShapeDetectorShape
/*    */ {
/*    */   public final Vec3D position;
/*    */   public final Vec3D velocity;
/*    */   public final float yaw;
/*    */   public final float pitch;
/*    */   public final WorldServer world;
/*    */   public final CraftPortalEvent portalEventInfo;
/*    */   
/*    */   public ShapeDetectorShape(Vec3D vec3d, Vec3D vec3d1, float f, float f1, WorldServer world, CraftPortalEvent portalEventInfo) {
/* 16 */     this.world = world;
/* 17 */     this.portalEventInfo = portalEventInfo;
/*    */     
/* 19 */     this.position = vec3d;
/* 20 */     this.velocity = vec3d1;
/* 21 */     this.yaw = f;
/* 22 */     this.pitch = f1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ShapeDetectorShape.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */