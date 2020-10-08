/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.io.IOException;
/*     */ 
/*     */ public class PacketPlayOutWorldBorder
/*     */   implements Packet<PacketListenerPlayOut> {
/*     */   private EnumWorldBorderAction a;
/*     */   private int b;
/*     */   private double c;
/*     */   private double d;
/*     */   private double e;
/*     */   private double f;
/*     */   private long g;
/*     */   private int h;
/*     */   private int i;
/*     */   
/*     */   public PacketPlayOutWorldBorder() {}
/*     */   
/*     */   public PacketPlayOutWorldBorder(WorldBorder worldborder, EnumWorldBorderAction packetplayoutworldborder_enumworldborderaction) {
/*  20 */     this.a = packetplayoutworldborder_enumworldborderaction;
/*     */     
/*  22 */     this.c = worldborder.getCenterX() * worldborder.world.getDimensionManager().getCoordinateScale();
/*  23 */     this.d = worldborder.getCenterZ() * worldborder.world.getDimensionManager().getCoordinateScale();
/*     */     
/*  25 */     this.f = worldborder.getSize();
/*  26 */     this.e = worldborder.k();
/*  27 */     this.g = worldborder.j();
/*  28 */     this.b = worldborder.m();
/*  29 */     this.i = worldborder.getWarningDistance();
/*  30 */     this.h = worldborder.getWarningTime();
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketDataSerializer packetdataserializer) throws IOException {
/*  35 */     this.a = packetdataserializer.<EnumWorldBorderAction>a(EnumWorldBorderAction.class);
/*  36 */     switch (this.a) {
/*     */       case SET_SIZE:
/*  38 */         this.e = packetdataserializer.readDouble();
/*     */         break;
/*     */       case LERP_SIZE:
/*  41 */         this.f = packetdataserializer.readDouble();
/*  42 */         this.e = packetdataserializer.readDouble();
/*  43 */         this.g = packetdataserializer.j();
/*     */         break;
/*     */       case SET_CENTER:
/*  46 */         this.c = packetdataserializer.readDouble();
/*  47 */         this.d = packetdataserializer.readDouble();
/*     */         break;
/*     */       case SET_WARNING_BLOCKS:
/*  50 */         this.i = packetdataserializer.i();
/*     */         break;
/*     */       case SET_WARNING_TIME:
/*  53 */         this.h = packetdataserializer.i();
/*     */         break;
/*     */       case INITIALIZE:
/*  56 */         this.c = packetdataserializer.readDouble();
/*  57 */         this.d = packetdataserializer.readDouble();
/*  58 */         this.f = packetdataserializer.readDouble();
/*  59 */         this.e = packetdataserializer.readDouble();
/*  60 */         this.g = packetdataserializer.j();
/*  61 */         this.b = packetdataserializer.i();
/*  62 */         this.i = packetdataserializer.i();
/*  63 */         this.h = packetdataserializer.i();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(PacketDataSerializer packetdataserializer) throws IOException {
/*  70 */     packetdataserializer.a(this.a);
/*  71 */     switch (this.a) {
/*     */       case SET_SIZE:
/*  73 */         packetdataserializer.writeDouble(this.e);
/*     */         break;
/*     */       case LERP_SIZE:
/*  76 */         packetdataserializer.writeDouble(this.f);
/*  77 */         packetdataserializer.writeDouble(this.e);
/*  78 */         packetdataserializer.b(this.g);
/*     */         break;
/*     */       case SET_CENTER:
/*  81 */         packetdataserializer.writeDouble(this.c);
/*  82 */         packetdataserializer.writeDouble(this.d);
/*     */         break;
/*     */       case SET_WARNING_BLOCKS:
/*  85 */         packetdataserializer.d(this.i);
/*     */         break;
/*     */       case SET_WARNING_TIME:
/*  88 */         packetdataserializer.d(this.h);
/*     */         break;
/*     */       case INITIALIZE:
/*  91 */         packetdataserializer.writeDouble(this.c);
/*  92 */         packetdataserializer.writeDouble(this.d);
/*  93 */         packetdataserializer.writeDouble(this.f);
/*  94 */         packetdataserializer.writeDouble(this.e);
/*  95 */         packetdataserializer.b(this.g);
/*  96 */         packetdataserializer.d(this.b);
/*  97 */         packetdataserializer.d(this.i);
/*  98 */         packetdataserializer.d(this.h);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a(PacketListenerPlayOut packetlistenerplayout) {
/* 104 */     packetlistenerplayout.a(this);
/*     */   }
/*     */   
/*     */   public enum EnumWorldBorderAction
/*     */   {
/* 109 */     SET_SIZE, LERP_SIZE, SET_CENTER, INITIALIZE, SET_WARNING_TIME, SET_WARNING_BLOCKS;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutWorldBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */