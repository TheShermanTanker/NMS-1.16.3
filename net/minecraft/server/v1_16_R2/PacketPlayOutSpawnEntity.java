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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutSpawnEntity
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private UUID b;
/*    */   private double c;
/*    */   private double d;
/*    */   private double e;
/*    */   private int f;
/*    */   private int g;
/*    */   private int h;
/*    */   private int i;
/*    */   private int j;
/*    */   private EntityTypes<?> k;
/*    */   private int l;
/*    */   
/*    */   public PacketPlayOutSpawnEntity() {}
/*    */   
/*    */   public PacketPlayOutSpawnEntity(int var0, UUID var1, double var2, double var4, double var6, float var8, float var9, EntityTypes<?> var10, int var11, Vec3D var12) {
/* 35 */     this.a = var0;
/* 36 */     this.b = var1;
/* 37 */     this.c = var2;
/* 38 */     this.d = var4;
/* 39 */     this.e = var6;
/* 40 */     this.i = MathHelper.d(var8 * 256.0F / 360.0F);
/* 41 */     this.j = MathHelper.d(var9 * 256.0F / 360.0F);
/* 42 */     this.k = var10;
/* 43 */     this.l = var11;
/*    */     
/* 45 */     this.f = (int)(MathHelper.a(var12.x, -3.9D, 3.9D) * 8000.0D);
/* 46 */     this.g = (int)(MathHelper.a(var12.y, -3.9D, 3.9D) * 8000.0D);
/* 47 */     this.h = (int)(MathHelper.a(var12.z, -3.9D, 3.9D) * 8000.0D);
/*    */   }
/*    */   
/*    */   public PacketPlayOutSpawnEntity(Entity var0) {
/* 51 */     this(var0, 0);
/*    */   }
/*    */   
/*    */   public PacketPlayOutSpawnEntity(Entity var0, int var1) {
/* 55 */     this(var0.getId(), var0.getUniqueID(), var0.locX(), var0.locY(), var0.locZ(), var0.pitch, var0.yaw, var0.getEntityType(), var1, var0.getMot());
/*    */   }
/*    */   
/*    */   public PacketPlayOutSpawnEntity(Entity var0, EntityTypes<?> var1, int var2, BlockPosition var3) {
/* 59 */     this(var0.getId(), var0.getUniqueID(), var3.getX(), var3.getY(), var3.getZ(), var0.pitch, var0.yaw, var1, var2, var0.getMot());
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 64 */     this.a = var0.i();
/* 65 */     this.b = var0.k();
/* 66 */     this.k = IRegistry.ENTITY_TYPE.fromId(var0.i());
/* 67 */     this.c = var0.readDouble();
/* 68 */     this.d = var0.readDouble();
/* 69 */     this.e = var0.readDouble();
/* 70 */     this.i = var0.readByte();
/* 71 */     this.j = var0.readByte();
/* 72 */     this.l = var0.readInt();
/*    */     
/* 74 */     this.f = var0.readShort();
/* 75 */     this.g = var0.readShort();
/* 76 */     this.h = var0.readShort();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 81 */     var0.d(this.a);
/* 82 */     var0.a(this.b);
/* 83 */     var0.d(IRegistry.ENTITY_TYPE.a(this.k));
/* 84 */     var0.writeDouble(this.c);
/* 85 */     var0.writeDouble(this.d);
/* 86 */     var0.writeDouble(this.e);
/* 87 */     var0.writeByte(this.i);
/* 88 */     var0.writeByte(this.j);
/* 89 */     var0.writeInt(this.l);
/*    */     
/* 91 */     var0.writeShort(this.f);
/* 92 */     var0.writeShort(this.g);
/* 93 */     var0.writeShort(this.h);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 98 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutSpawnEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */