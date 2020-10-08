/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.UUID;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutSpawnEntityLiving
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private UUID b;
/*    */   private int c;
/*    */   private double d;
/*    */   private double e;
/*    */   private double f;
/*    */   private int g;
/*    */   private int h;
/*    */   private int i;
/*    */   private byte j;
/*    */   private byte k;
/*    */   private byte l;
/*    */   
/*    */   public PacketPlayOutSpawnEntityLiving() {}
/*    */   
/*    */   public PacketPlayOutSpawnEntityLiving(EntityLiving var0) {
/* 32 */     this.a = var0.getId();
/* 33 */     this.b = var0.getUniqueID();
/*    */     
/* 35 */     this.c = IRegistry.ENTITY_TYPE.a(var0.getEntityType());
/* 36 */     this.d = var0.locX();
/* 37 */     this.e = var0.locY();
/* 38 */     this.f = var0.locZ();
/* 39 */     this.j = (byte)(int)(var0.yaw * 256.0F / 360.0F);
/* 40 */     this.k = (byte)(int)(var0.pitch * 256.0F / 360.0F);
/* 41 */     this.l = (byte)(int)(var0.aC * 256.0F / 360.0F);
/*    */ 
/*    */     
/* 44 */     double var1 = 3.9D;
/*    */     
/* 46 */     Vec3D var3 = var0.getMot();
/* 47 */     double var4 = MathHelper.a(var3.x, -3.9D, 3.9D);
/* 48 */     double var6 = MathHelper.a(var3.y, -3.9D, 3.9D);
/* 49 */     double var8 = MathHelper.a(var3.z, -3.9D, 3.9D);
/*    */     
/* 51 */     this.g = (int)(var4 * 8000.0D);
/* 52 */     this.h = (int)(var6 * 8000.0D);
/* 53 */     this.i = (int)(var8 * 8000.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 58 */     this.a = var0.i();
/* 59 */     this.b = var0.k();
/* 60 */     this.c = var0.i();
/* 61 */     this.d = var0.readDouble();
/* 62 */     this.e = var0.readDouble();
/* 63 */     this.f = var0.readDouble();
/* 64 */     this.j = var0.readByte();
/* 65 */     this.k = var0.readByte();
/* 66 */     this.l = var0.readByte();
/* 67 */     this.g = var0.readShort();
/* 68 */     this.h = var0.readShort();
/* 69 */     this.i = var0.readShort();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 74 */     var0.d(this.a);
/* 75 */     var0.a(this.b);
/* 76 */     var0.d(this.c);
/* 77 */     var0.writeDouble(this.d);
/* 78 */     var0.writeDouble(this.e);
/* 79 */     var0.writeDouble(this.f);
/* 80 */     var0.writeByte(this.j);
/* 81 */     var0.writeByte(this.k);
/* 82 */     var0.writeByte(this.l);
/* 83 */     var0.writeShort(this.g);
/* 84 */     var0.writeShort(this.h);
/* 85 */     var0.writeShort(this.i);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 90 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutSpawnEntityLiving.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */