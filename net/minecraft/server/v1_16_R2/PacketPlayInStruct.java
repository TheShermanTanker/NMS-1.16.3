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
/*     */ public class PacketPlayInStruct
/*     */   implements Packet<PacketListenerPlayIn>
/*     */ {
/*     */   private BlockPosition a;
/*     */   private TileEntityStructure.UpdateType b;
/*     */   private BlockPropertyStructureMode c;
/*     */   private String d;
/*     */   private BlockPosition e;
/*     */   private BlockPosition f;
/*     */   private EnumBlockMirror g;
/*     */   private EnumBlockRotation h;
/*     */   private String i;
/*     */   private boolean j;
/*     */   private boolean k;
/*     */   private boolean l;
/*     */   private float m;
/*     */   private long n;
/*     */   
/*     */   public void a(PacketDataSerializer var0) throws IOException {
/*  56 */     this.a = var0.e();
/*  57 */     this.b = var0.<TileEntityStructure.UpdateType>a(TileEntityStructure.UpdateType.class);
/*  58 */     this.c = var0.<BlockPropertyStructureMode>a(BlockPropertyStructureMode.class);
/*  59 */     this.d = var0.e(32767);
/*  60 */     int var1 = 48;
/*  61 */     this.e = new BlockPosition(MathHelper.clamp(var0.readByte(), -48, 48), MathHelper.clamp(var0.readByte(), -48, 48), MathHelper.clamp(var0.readByte(), -48, 48));
/*  62 */     int var2 = 48;
/*  63 */     this.f = new BlockPosition(MathHelper.clamp(var0.readByte(), 0, 48), MathHelper.clamp(var0.readByte(), 0, 48), MathHelper.clamp(var0.readByte(), 0, 48));
/*  64 */     this.g = var0.<EnumBlockMirror>a(EnumBlockMirror.class);
/*  65 */     this.h = var0.<EnumBlockRotation>a(EnumBlockRotation.class);
/*  66 */     this.i = var0.e(12);
/*  67 */     this.m = MathHelper.a(var0.readFloat(), 0.0F, 1.0F);
/*  68 */     this.n = var0.j();
/*  69 */     int var3 = var0.readByte();
/*  70 */     this.j = ((var3 & 0x1) != 0);
/*  71 */     this.k = ((var3 & 0x2) != 0);
/*  72 */     this.l = ((var3 & 0x4) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(PacketDataSerializer var0) throws IOException {
/*  77 */     var0.a(this.a);
/*  78 */     var0.a(this.b);
/*  79 */     var0.a(this.c);
/*  80 */     var0.a(this.d);
/*  81 */     var0.writeByte(this.e.getX());
/*  82 */     var0.writeByte(this.e.getY());
/*  83 */     var0.writeByte(this.e.getZ());
/*  84 */     var0.writeByte(this.f.getX());
/*  85 */     var0.writeByte(this.f.getY());
/*  86 */     var0.writeByte(this.f.getZ());
/*  87 */     var0.a(this.g);
/*  88 */     var0.a(this.h);
/*  89 */     var0.a(this.i);
/*  90 */     var0.writeFloat(this.m);
/*  91 */     var0.b(this.n);
/*     */     
/*  93 */     int var1 = 0;
/*  94 */     if (this.j) {
/*  95 */       var1 |= 0x1;
/*     */     }
/*  97 */     if (this.k) {
/*  98 */       var1 |= 0x2;
/*     */     }
/* 100 */     if (this.l) {
/* 101 */       var1 |= 0x4;
/*     */     }
/* 103 */     var0.writeByte(var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketListenerPlayIn var0) {
/* 108 */     var0.a(this);
/*     */   }
/*     */   
/*     */   public BlockPosition b() {
/* 112 */     return this.a;
/*     */   }
/*     */   
/*     */   public TileEntityStructure.UpdateType c() {
/* 116 */     return this.b;
/*     */   }
/*     */   
/*     */   public BlockPropertyStructureMode d() {
/* 120 */     return this.c;
/*     */   }
/*     */   
/*     */   public String e() {
/* 124 */     return this.d;
/*     */   }
/*     */   
/*     */   public BlockPosition f() {
/* 128 */     return this.e;
/*     */   }
/*     */   
/*     */   public BlockPosition g() {
/* 132 */     return this.f;
/*     */   }
/*     */   
/*     */   public EnumBlockMirror h() {
/* 136 */     return this.g;
/*     */   }
/*     */   
/*     */   public EnumBlockRotation i() {
/* 140 */     return this.h;
/*     */   }
/*     */   
/*     */   public String j() {
/* 144 */     return this.i;
/*     */   }
/*     */   
/*     */   public boolean k() {
/* 148 */     return this.j;
/*     */   }
/*     */   
/*     */   public boolean l() {
/* 152 */     return this.k;
/*     */   }
/*     */   
/*     */   public boolean m() {
/* 156 */     return this.l;
/*     */   }
/*     */   
/*     */   public float n() {
/* 160 */     return this.m;
/*     */   }
/*     */   
/*     */   public long o() {
/* 164 */     return this.n;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInStruct.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */