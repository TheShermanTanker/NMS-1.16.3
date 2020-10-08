/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutGameStateChange
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   public static class a
/*    */   {
/* 13 */     private static final Int2ObjectMap<a> a = (Int2ObjectMap<a>)new Int2ObjectOpenHashMap();
/*    */     
/*    */     private final int b;
/*    */     
/*    */     public a(int var0) {
/* 18 */       this.b = var0;
/* 19 */       a.put(var0, this);
/*    */     }
/*    */   }
/*    */   
/* 23 */   public static final a a = new a(0);
/* 24 */   public static final a b = new a(1);
/* 25 */   public static final a c = new a(2);
/* 26 */   public static final a d = new a(3);
/* 27 */   public static final a e = new a(4);
/* 28 */   public static final a f = new a(5);
/* 29 */   public static final a g = new a(6);
/* 30 */   public static final a h = new a(7);
/* 31 */   public static final a i = new a(8);
/* 32 */   public static final a j = new a(9);
/* 33 */   public static final a k = new a(10);
/* 34 */   public static final a l = new a(11);
/*    */ 
/*    */   
/*    */   private a m;
/*    */ 
/*    */   
/*    */   private float n;
/*    */ 
/*    */ 
/*    */   
/*    */   public PacketPlayOutGameStateChange() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public PacketPlayOutGameStateChange(a var0, float var1) {
/* 49 */     this.m = var0;
/* 50 */     this.n = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 55 */     this.m = (a)a.a().get(var0.readUnsignedByte());
/* 56 */     this.n = var0.readFloat();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 61 */     var0.writeByte(a.a(this.m));
/* 62 */     var0.writeFloat(this.n);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 67 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutGameStateChange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */