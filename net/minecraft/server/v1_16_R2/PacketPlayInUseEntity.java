/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public class PacketPlayInUseEntity implements Packet<PacketListenerPlayIn> {
/*    */   public int getEntityId() {
/*  8 */     return this.a;
/*    */   }
/*    */   
/*    */   private int a;
/*    */   private EnumEntityUseAction action;
/*    */   private Vec3D c;
/*    */   private EnumHand d;
/*    */   private boolean e;
/*    */   
/*    */   public void a(PacketDataSerializer packetdataserializer) throws IOException {
/* 18 */     this.a = packetdataserializer.i();
/* 19 */     this.action = packetdataserializer.<EnumEntityUseAction>a(EnumEntityUseAction.class);
/* 20 */     if (this.action == EnumEntityUseAction.INTERACT_AT) {
/* 21 */       this.c = new Vec3D(packetdataserializer.readFloat(), packetdataserializer.readFloat(), packetdataserializer.readFloat());
/*    */     }
/*    */     
/* 24 */     if (this.action == EnumEntityUseAction.INTERACT || this.action == EnumEntityUseAction.INTERACT_AT) {
/* 25 */       this.d = packetdataserializer.<EnumHand>a(EnumHand.class);
/*    */     }
/*    */     
/* 28 */     this.e = packetdataserializer.readBoolean();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer packetdataserializer) throws IOException {
/* 33 */     packetdataserializer.d(this.a);
/* 34 */     packetdataserializer.a(this.action);
/* 35 */     if (this.action == EnumEntityUseAction.INTERACT_AT) {
/* 36 */       packetdataserializer.writeFloat((float)this.c.x);
/* 37 */       packetdataserializer.writeFloat((float)this.c.y);
/* 38 */       packetdataserializer.writeFloat((float)this.c.z);
/*    */     } 
/*    */     
/* 41 */     if (this.action == EnumEntityUseAction.INTERACT || this.action == EnumEntityUseAction.INTERACT_AT) {
/* 42 */       packetdataserializer.a(this.d);
/*    */     }
/*    */     
/* 45 */     packetdataserializer.writeBoolean(this.e);
/*    */   }
/*    */   
/*    */   public void a(PacketListenerPlayIn packetlistenerplayin) {
/* 49 */     packetlistenerplayin.a(this);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public Entity a(World world) {
/* 54 */     return world.getEntity(this.a);
/*    */   }
/*    */   
/*    */   public EnumEntityUseAction b() {
/* 58 */     return this.action;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public EnumHand c() {
/* 63 */     return this.d;
/*    */   }
/*    */   
/*    */   public Vec3D d() {
/* 67 */     return this.c;
/*    */   }
/*    */   
/*    */   public boolean e() {
/* 71 */     return this.e;
/*    */   }
/*    */   
/*    */   public enum EnumEntityUseAction
/*    */   {
/* 76 */     INTERACT, ATTACK, INTERACT_AT;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInUseEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */