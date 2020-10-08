/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutExplosion
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private double a;
/*    */   private double b;
/*    */   private double c;
/*    */   private float d;
/*    */   private List<BlockPosition> e;
/*    */   private float f;
/*    */   private float g;
/*    */   private float h;
/*    */   
/*    */   public PacketPlayOutExplosion() {}
/*    */   
/*    */   public PacketPlayOutExplosion(double var0, double var2, double var4, float var6, List<BlockPosition> var7, Vec3D var8) {
/* 28 */     this.a = var0;
/* 29 */     this.b = var2;
/* 30 */     this.c = var4;
/* 31 */     this.d = var6;
/* 32 */     this.e = Lists.newArrayList(var7);
/*    */     
/* 34 */     if (var8 != null) {
/* 35 */       this.f = (float)var8.x;
/* 36 */       this.g = (float)var8.y;
/* 37 */       this.h = (float)var8.z;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 43 */     this.a = var0.readFloat();
/* 44 */     this.b = var0.readFloat();
/* 45 */     this.c = var0.readFloat();
/* 46 */     this.d = var0.readFloat();
/* 47 */     int var1 = var0.readInt();
/*    */     
/* 49 */     this.e = Lists.newArrayListWithCapacity(var1);
/*    */     
/* 51 */     int var2 = MathHelper.floor(this.a);
/* 52 */     int var3 = MathHelper.floor(this.b);
/* 53 */     int var4 = MathHelper.floor(this.c);
/* 54 */     for (int var5 = 0; var5 < var1; var5++) {
/* 55 */       int var6 = var0.readByte() + var2;
/* 56 */       int var7 = var0.readByte() + var3;
/* 57 */       int var8 = var0.readByte() + var4;
/* 58 */       this.e.add(new BlockPosition(var6, var7, var8));
/*    */     } 
/*    */     
/* 61 */     this.f = var0.readFloat();
/* 62 */     this.g = var0.readFloat();
/* 63 */     this.h = var0.readFloat();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 68 */     var0.writeFloat((float)this.a);
/* 69 */     var0.writeFloat((float)this.b);
/* 70 */     var0.writeFloat((float)this.c);
/* 71 */     var0.writeFloat(this.d);
/* 72 */     var0.writeInt(this.e.size());
/*    */     
/* 74 */     int var1 = MathHelper.floor(this.a);
/* 75 */     int var2 = MathHelper.floor(this.b);
/* 76 */     int var3 = MathHelper.floor(this.c);
/* 77 */     for (BlockPosition var5 : this.e) {
/* 78 */       int var6 = var5.getX() - var1;
/* 79 */       int var7 = var5.getY() - var2;
/* 80 */       int var8 = var5.getZ() - var3;
/* 81 */       var0.writeByte(var6);
/* 82 */       var0.writeByte(var7);
/* 83 */       var0.writeByte(var8);
/*    */     } 
/*    */     
/* 86 */     var0.writeFloat(this.f);
/* 87 */     var0.writeFloat(this.g);
/* 88 */     var0.writeFloat(this.h);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 93 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutExplosion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */