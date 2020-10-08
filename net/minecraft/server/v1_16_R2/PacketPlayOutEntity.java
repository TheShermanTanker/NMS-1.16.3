/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PacketPlayOutEntity
/*     */   implements Packet<PacketListenerPlayOut>
/*     */ {
/*     */   protected int a;
/*     */   protected short b;
/*     */   protected short c;
/*     */   protected short d;
/*     */   protected byte e;
/*     */   protected byte f;
/*     */   protected boolean g;
/*     */   protected boolean h;
/*     */   protected boolean i;
/*     */   
/*     */   public static long a(double var0) {
/*  29 */     return MathHelper.d(var0 * 4096.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Vec3D a(long var0, long var2, long var4) {
/*  44 */     return (new Vec3D(var0, var2, var4)).a(2.44140625E-4D);
/*     */   }
/*     */   
/*     */   public static class PacketPlayOutRelEntityMoveLook extends PacketPlayOutEntity {
/*     */     public PacketPlayOutRelEntityMoveLook() {
/*  49 */       this.h = true;
/*  50 */       this.i = true;
/*     */     }
/*     */     
/*     */     public PacketPlayOutRelEntityMoveLook(int var0, short var1, short var2, short var3, byte var4, byte var5, boolean var6) {
/*  54 */       super(var0);
/*     */       
/*  56 */       this.b = var1;
/*  57 */       this.c = var2;
/*  58 */       this.d = var3;
/*  59 */       this.e = var4;
/*  60 */       this.f = var5;
/*  61 */       this.g = var6;
/*  62 */       this.h = true;
/*  63 */       this.i = true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(PacketDataSerializer var0) throws IOException {
/*  68 */       super.a(var0);
/*  69 */       this.b = var0.readShort();
/*  70 */       this.c = var0.readShort();
/*  71 */       this.d = var0.readShort();
/*  72 */       this.e = var0.readByte();
/*  73 */       this.f = var0.readByte();
/*  74 */       this.g = var0.readBoolean();
/*     */     }
/*     */ 
/*     */     
/*     */     public void b(PacketDataSerializer var0) throws IOException {
/*  79 */       super.b(var0);
/*  80 */       var0.writeShort(this.b);
/*  81 */       var0.writeShort(this.c);
/*  82 */       var0.writeShort(this.d);
/*  83 */       var0.writeByte(this.e);
/*  84 */       var0.writeByte(this.f);
/*  85 */       var0.writeBoolean(this.g);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class PacketPlayOutRelEntityMove extends PacketPlayOutEntity {
/*     */     public PacketPlayOutRelEntityMove() {
/*  91 */       this.i = true;
/*     */     }
/*     */     
/*     */     public PacketPlayOutRelEntityMove(int var0, short var1, short var2, short var3, boolean var4) {
/*  95 */       super(var0);
/*     */       
/*  97 */       this.b = var1;
/*  98 */       this.c = var2;
/*  99 */       this.d = var3;
/* 100 */       this.g = var4;
/* 101 */       this.i = true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(PacketDataSerializer var0) throws IOException {
/* 106 */       super.a(var0);
/* 107 */       this.b = var0.readShort();
/* 108 */       this.c = var0.readShort();
/* 109 */       this.d = var0.readShort();
/* 110 */       this.g = var0.readBoolean();
/*     */     }
/*     */ 
/*     */     
/*     */     public void b(PacketDataSerializer var0) throws IOException {
/* 115 */       super.b(var0);
/* 116 */       var0.writeShort(this.b);
/* 117 */       var0.writeShort(this.c);
/* 118 */       var0.writeShort(this.d);
/* 119 */       var0.writeBoolean(this.g);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class PacketPlayOutEntityLook extends PacketPlayOutEntity {
/*     */     public PacketPlayOutEntityLook() {
/* 125 */       this.h = true;
/*     */     }
/*     */     
/*     */     public PacketPlayOutEntityLook(int var0, byte var1, byte var2, boolean var3) {
/* 129 */       super(var0);
/* 130 */       this.e = var1;
/* 131 */       this.f = var2;
/* 132 */       this.h = true;
/* 133 */       this.g = var3;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(PacketDataSerializer var0) throws IOException {
/* 138 */       super.a(var0);
/* 139 */       this.e = var0.readByte();
/* 140 */       this.f = var0.readByte();
/* 141 */       this.g = var0.readBoolean();
/*     */     }
/*     */ 
/*     */     
/*     */     public void b(PacketDataSerializer var0) throws IOException {
/* 146 */       super.b(var0);
/* 147 */       var0.writeByte(this.e);
/* 148 */       var0.writeByte(this.f);
/* 149 */       var0.writeBoolean(this.g);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public PacketPlayOutEntity() {}
/*     */   
/*     */   public PacketPlayOutEntity(int var0) {
/* 157 */     this.a = var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketDataSerializer var0) throws IOException {
/* 162 */     this.a = var0.i();
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(PacketDataSerializer var0) throws IOException {
/* 167 */     var0.d(this.a);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketListenerPlayOut var0) {
/* 172 */     var0.a(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 177 */     return "Entity_" + super.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */