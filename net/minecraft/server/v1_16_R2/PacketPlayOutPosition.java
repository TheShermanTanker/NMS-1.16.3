/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PacketPlayOutPosition
/*     */   implements Packet<PacketListenerPlayOut>
/*     */ {
/*     */   private double a;
/*     */   private double b;
/*     */   private double c;
/*     */   private float d;
/*     */   private float e;
/*     */   private Set<EnumPlayerTeleportFlags> f;
/*     */   private int g;
/*     */   
/*     */   public PacketPlayOutPosition() {}
/*     */   
/*     */   public PacketPlayOutPosition(double var0, double var2, double var4, float var6, float var7, Set<EnumPlayerTeleportFlags> var8, int var9) {
/*  23 */     this.a = var0;
/*  24 */     this.b = var2;
/*  25 */     this.c = var4;
/*  26 */     this.d = var6;
/*  27 */     this.e = var7;
/*  28 */     this.f = var8;
/*  29 */     this.g = var9;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketDataSerializer var0) throws IOException {
/*  34 */     this.a = var0.readDouble();
/*  35 */     this.b = var0.readDouble();
/*  36 */     this.c = var0.readDouble();
/*  37 */     this.d = var0.readFloat();
/*  38 */     this.e = var0.readFloat();
/*  39 */     this.f = EnumPlayerTeleportFlags.a(var0.readUnsignedByte());
/*  40 */     this.g = var0.i();
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(PacketDataSerializer var0) throws IOException {
/*  45 */     var0.writeDouble(this.a);
/*  46 */     var0.writeDouble(this.b);
/*  47 */     var0.writeDouble(this.c);
/*  48 */     var0.writeFloat(this.d);
/*  49 */     var0.writeFloat(this.e);
/*  50 */     var0.writeByte(EnumPlayerTeleportFlags.a(this.f));
/*  51 */     var0.d(this.g);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketListenerPlayOut var0) {
/*  56 */     var0.a(this);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum EnumPlayerTeleportFlags
/*     */   {
/*  88 */     X(0),
/*  89 */     Y(1),
/*  90 */     Z(2),
/*  91 */     Y_ROT(3),
/*  92 */     X_ROT(4);
/*     */     
/*     */     private final int f;
/*     */ 
/*     */     
/*     */     EnumPlayerTeleportFlags(int var2) {
/*  98 */       this.f = var2;
/*     */     }
/*     */     
/*     */     private int a() {
/* 102 */       return 1 << this.f;
/*     */     }
/*     */     
/*     */     private boolean b(int var0) {
/* 106 */       return ((var0 & a()) == a());
/*     */     }
/*     */     
/*     */     public static Set<EnumPlayerTeleportFlags> a(int var0) {
/* 110 */       Set<EnumPlayerTeleportFlags> var1 = EnumSet.noneOf(EnumPlayerTeleportFlags.class);
/*     */       
/* 112 */       for (EnumPlayerTeleportFlags var5 : values()) {
/* 113 */         if (var5.b(var0)) {
/* 114 */           var1.add(var5);
/*     */         }
/*     */       } 
/*     */       
/* 118 */       return var1;
/*     */     }
/*     */     
/*     */     public static int a(Set<EnumPlayerTeleportFlags> var0) {
/* 122 */       int var1 = 0;
/*     */       
/* 124 */       for (EnumPlayerTeleportFlags var3 : var0) {
/* 125 */         var1 |= var3.a();
/*     */       }
/*     */       
/* 128 */       return var1;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutPosition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */