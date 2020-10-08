/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.Collection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutMap
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private byte b;
/*    */   private boolean c;
/*    */   private boolean d;
/*    */   private MapIcon[] e;
/*    */   private int f;
/*    */   private int g;
/*    */   private int h;
/*    */   private int i;
/*    */   private byte[] j;
/*    */   
/*    */   public PacketPlayOutMap() {}
/*    */   
/*    */   public PacketPlayOutMap(int var0, byte var1, boolean var2, boolean var3, Collection<MapIcon> var4, byte[] var5, int var6, int var7, int var8, int var9) {
/* 28 */     this.a = var0;
/* 29 */     this.b = var1;
/* 30 */     this.c = var2;
/* 31 */     this.d = var3;
/* 32 */     this.e = var4.<MapIcon>toArray(new MapIcon[var4.size()]);
/* 33 */     this.f = var6;
/* 34 */     this.g = var7;
/* 35 */     this.h = var8;
/* 36 */     this.i = var9;
/*    */     
/* 38 */     this.j = new byte[var8 * var9];
/* 39 */     for (int var10 = 0; var10 < var8; var10++) {
/* 40 */       for (int var11 = 0; var11 < var9; var11++) {
/* 41 */         this.j[var10 + var11 * var8] = var5[var6 + var10 + (var7 + var11) * 128];
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 48 */     this.a = var0.i();
/* 49 */     this.b = var0.readByte();
/* 50 */     this.c = var0.readBoolean();
/* 51 */     this.d = var0.readBoolean();
/* 52 */     this.e = new MapIcon[var0.i()];
/* 53 */     for (int var1 = 0; var1 < this.e.length; var1++) {
/* 54 */       MapIcon.Type var2 = var0.<MapIcon.Type>a(MapIcon.Type.class);
/* 55 */       this.e[var1] = new MapIcon(var2, var0.readByte(), var0.readByte(), (byte)(var0.readByte() & 0xF), var0.readBoolean() ? var0.h() : null);
/*    */     } 
/* 57 */     this.h = var0.readUnsignedByte();
/* 58 */     if (this.h > 0) {
/* 59 */       this.i = var0.readUnsignedByte();
/* 60 */       this.f = var0.readUnsignedByte();
/* 61 */       this.g = var0.readUnsignedByte();
/* 62 */       this.j = var0.a();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 68 */     var0.d(this.a);
/* 69 */     var0.writeByte(this.b);
/* 70 */     var0.writeBoolean(this.c);
/* 71 */     var0.writeBoolean(this.d);
/* 72 */     var0.d(this.e.length);
/* 73 */     for (MapIcon var4 : this.e) {
/* 74 */       var0.a(var4.getType());
/* 75 */       var0.writeByte(var4.getX());
/* 76 */       var0.writeByte(var4.getY());
/* 77 */       var0.writeByte(var4.getRotation() & 0xF);
/* 78 */       if (var4.getName() != null) {
/* 79 */         var0.writeBoolean(true);
/* 80 */         var0.a(var4.getName());
/*    */       } else {
/* 82 */         var0.writeBoolean(false);
/*    */       } 
/*    */     } 
/* 85 */     var0.writeByte(this.h);
/* 86 */     if (this.h > 0) {
/* 87 */       var0.writeByte(this.i);
/* 88 */       var0.writeByte(this.f);
/* 89 */       var0.writeByte(this.g);
/* 90 */       var0.a(this.j);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 96 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */