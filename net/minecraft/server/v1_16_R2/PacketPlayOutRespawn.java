/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutRespawn
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private DimensionManager a;
/*    */   private ResourceKey<World> b;
/*    */   private long c;
/*    */   private EnumGamemode d;
/*    */   private EnumGamemode e;
/*    */   private boolean f;
/*    */   private boolean g;
/*    */   private boolean h;
/*    */   
/*    */   public PacketPlayOutRespawn() {}
/*    */   
/*    */   public PacketPlayOutRespawn(DimensionManager var0, ResourceKey<World> var1, long var2, EnumGamemode var4, EnumGamemode var5, boolean var6, boolean var7, boolean var8) {
/* 28 */     this.a = var0;
/* 29 */     this.b = var1;
/* 30 */     this.c = var2;
/* 31 */     this.d = var4;
/* 32 */     this.e = var5;
/* 33 */     this.f = var6;
/* 34 */     this.g = var7;
/* 35 */     this.h = var8;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 40 */     var0.a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 45 */     this.a = ((Supplier<DimensionManager>)var0.<Supplier<DimensionManager>>a(DimensionManager.n)).get();
/* 46 */     this.b = ResourceKey.a(IRegistry.L, var0.p());
/* 47 */     this.c = var0.readLong();
/* 48 */     this.d = EnumGamemode.getById(var0.readUnsignedByte());
/* 49 */     this.e = EnumGamemode.getById(var0.readUnsignedByte());
/* 50 */     this.f = var0.readBoolean();
/* 51 */     this.g = var0.readBoolean();
/* 52 */     this.h = var0.readBoolean();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 57 */     var0.a(DimensionManager.n, () -> this.a);
/* 58 */     var0.a(this.b.a());
/* 59 */     var0.writeLong(this.c);
/* 60 */     var0.writeByte(this.d.getId());
/* 61 */     var0.writeByte(this.e.getId());
/* 62 */     var0.writeBoolean(this.f);
/* 63 */     var0.writeBoolean(this.g);
/* 64 */     var0.writeBoolean(this.h);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutRespawn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */