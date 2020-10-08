/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import java.io.IOException;
/*     */ import java.util.Set;
/*     */ import java.util.function.Supplier;
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
/*     */ public class PacketPlayOutLogin
/*     */   implements Packet<PacketListenerPlayOut>
/*     */ {
/*     */   private int a;
/*     */   private long b;
/*     */   private boolean c;
/*     */   private EnumGamemode d;
/*     */   private EnumGamemode e;
/*     */   private Set<ResourceKey<World>> f;
/*     */   private IRegistryCustom.Dimension g;
/*     */   private DimensionManager h;
/*     */   private ResourceKey<World> i;
/*     */   private int j;
/*     */   private int k;
/*     */   private boolean l;
/*     */   private boolean m;
/*     */   private boolean n;
/*     */   private boolean o;
/*     */   
/*     */   public PacketPlayOutLogin() {}
/*     */   
/*     */   public PacketPlayOutLogin(int var0, EnumGamemode var1, EnumGamemode var2, long var3, boolean var5, Set<ResourceKey<World>> var6, IRegistryCustom.Dimension var7, DimensionManager var8, ResourceKey<World> var9, int var10, int var11, boolean var12, boolean var13, boolean var14, boolean var15) {
/*  41 */     this.a = var0;
/*  42 */     this.f = var6;
/*  43 */     this.g = var7;
/*  44 */     this.h = var8;
/*  45 */     this.i = var9;
/*  46 */     this.b = var3;
/*  47 */     this.d = var1;
/*  48 */     this.e = var2;
/*  49 */     this.j = var10;
/*  50 */     this.c = var5;
/*  51 */     this.k = var11;
/*  52 */     this.l = var12;
/*  53 */     this.m = var13;
/*  54 */     this.n = var14;
/*  55 */     this.o = var15;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketDataSerializer var0) throws IOException {
/*  60 */     this.a = var0.readInt();
/*     */     
/*  62 */     this.c = var0.readBoolean();
/*  63 */     this.d = EnumGamemode.getById(var0.readByte());
/*  64 */     this.e = EnumGamemode.getById(var0.readByte());
/*     */     
/*  66 */     int var1 = var0.i();
/*  67 */     this.f = Sets.newHashSet();
/*  68 */     for (int var2 = 0; var2 < var1; var2++) {
/*  69 */       this.f.add(ResourceKey.a(IRegistry.L, var0.p()));
/*     */     }
/*  71 */     this.g = var0.<IRegistryCustom.Dimension>a(IRegistryCustom.Dimension.a);
/*     */     
/*  73 */     this.h = ((Supplier<DimensionManager>)var0.<Supplier<DimensionManager>>a(DimensionManager.n)).get();
/*  74 */     this.i = ResourceKey.a(IRegistry.L, var0.p());
/*  75 */     this.b = var0.readLong();
/*  76 */     this.j = var0.i();
/*  77 */     this.k = var0.i();
/*  78 */     this.l = var0.readBoolean();
/*  79 */     this.m = var0.readBoolean();
/*  80 */     this.n = var0.readBoolean();
/*  81 */     this.o = var0.readBoolean();
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(PacketDataSerializer var0) throws IOException {
/*  86 */     var0.writeInt(this.a);
/*  87 */     var0.writeBoolean(this.c);
/*  88 */     var0.writeByte(this.d.getId());
/*  89 */     var0.writeByte(this.e.getId());
/*     */     
/*  91 */     var0.d(this.f.size());
/*  92 */     for (ResourceKey<World> var2 : this.f) {
/*  93 */       var0.a(var2.a());
/*     */     }
/*  95 */     var0.a(IRegistryCustom.Dimension.a, this.g);
/*     */     
/*  97 */     var0.a(DimensionManager.n, () -> this.h);
/*  98 */     var0.a(this.i.a());
/*  99 */     var0.writeLong(this.b);
/* 100 */     var0.d(this.j);
/* 101 */     var0.d(this.k);
/* 102 */     var0.writeBoolean(this.l);
/* 103 */     var0.writeBoolean(this.m);
/* 104 */     var0.writeBoolean(this.n);
/* 105 */     var0.writeBoolean(this.o);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketListenerPlayOut var0) {
/* 110 */     var0.a(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutLogin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */