/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.UUID;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PacketPlayOutBoss
/*     */   implements Packet<PacketListenerPlayOut>
/*     */ {
/*     */   private UUID a;
/*     */   private Action b;
/*     */   private IChatBaseComponent c;
/*     */   private float d;
/*     */   private BossBattle.BarColor e;
/*     */   private BossBattle.BarStyle f;
/*     */   private boolean g;
/*     */   private boolean h;
/*     */   private boolean i;
/*     */   
/*     */   public PacketPlayOutBoss() {}
/*     */   
/*     */   public PacketPlayOutBoss(Action var0, BossBattle var1) {
/*  30 */     this.b = var0;
/*  31 */     this.a = var1.i();
/*  32 */     this.c = var1.j();
/*  33 */     this.d = var1.getProgress();
/*  34 */     this.e = var1.l();
/*  35 */     this.f = var1.m();
/*  36 */     this.g = var1.isDarkenSky();
/*  37 */     this.h = var1.isPlayMusic();
/*  38 */     this.i = var1.isCreateFog();
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketDataSerializer var0) throws IOException {
/*  43 */     this.a = var0.k();
/*  44 */     this.b = var0.<Action>a(Action.class);
/*     */     
/*  46 */     switch (null.a[this.b.ordinal()]) {
/*     */       case 1:
/*  48 */         this.c = var0.h();
/*  49 */         this.d = var0.readFloat();
/*  50 */         this.e = var0.<BossBattle.BarColor>a(BossBattle.BarColor.class);
/*  51 */         this.f = var0.<BossBattle.BarStyle>a(BossBattle.BarStyle.class);
/*  52 */         a(var0.readUnsignedByte());
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/*  57 */         this.d = var0.readFloat();
/*     */         break;
/*     */       case 4:
/*  60 */         this.c = var0.h();
/*     */         break;
/*     */       case 5:
/*  63 */         this.e = var0.<BossBattle.BarColor>a(BossBattle.BarColor.class);
/*  64 */         this.f = var0.<BossBattle.BarStyle>a(BossBattle.BarStyle.class);
/*     */         break;
/*     */       case 6:
/*  67 */         a(var0.readUnsignedByte());
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a(int var0) {
/*  73 */     this.g = ((var0 & 0x1) > 0);
/*  74 */     this.h = ((var0 & 0x2) > 0);
/*  75 */     this.i = ((var0 & 0x4) > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(PacketDataSerializer var0) throws IOException {
/*  80 */     var0.a(this.a);
/*  81 */     var0.a(this.b);
/*     */     
/*  83 */     switch (null.a[this.b.ordinal()]) {
/*     */       case 1:
/*  85 */         var0.a(this.c);
/*  86 */         var0.writeFloat(this.d);
/*  87 */         var0.a(this.e);
/*  88 */         var0.a(this.f);
/*  89 */         var0.writeByte(k());
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/*  94 */         var0.writeFloat(this.d);
/*     */         break;
/*     */       case 4:
/*  97 */         var0.a(this.c);
/*     */         break;
/*     */       case 5:
/* 100 */         var0.a(this.e);
/* 101 */         var0.a(this.f);
/*     */         break;
/*     */       case 6:
/* 104 */         var0.writeByte(k());
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private int k() {
/* 110 */     int var0 = 0;
/* 111 */     if (this.g) {
/* 112 */       var0 |= 0x1;
/*     */     }
/* 114 */     if (this.h) {
/* 115 */       var0 |= 0x2;
/*     */     }
/* 117 */     if (this.i) {
/* 118 */       var0 |= 0x4;
/*     */     }
/* 120 */     return var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketListenerPlayOut var0) {
/* 125 */     var0.a(this);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum Action
/*     */   {
/* 165 */     ADD,
/* 166 */     REMOVE,
/* 167 */     UPDATE_PCT,
/* 168 */     UPDATE_NAME,
/* 169 */     UPDATE_STYLE,
/* 170 */     UPDATE_PROPERTIES;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutBoss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */