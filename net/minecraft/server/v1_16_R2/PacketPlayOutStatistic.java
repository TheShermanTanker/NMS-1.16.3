/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.objects.Object2IntMap;
/*    */ import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
/*    */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutStatistic
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private Object2IntMap<Statistic<?>> a;
/*    */   
/*    */   public PacketPlayOutStatistic() {}
/*    */   
/*    */   public PacketPlayOutStatistic(Object2IntMap<Statistic<?>> var0) {
/* 21 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 26 */     var0.a(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 33 */     int var1 = var0.i();
/* 34 */     this.a = (Object2IntMap<Statistic<?>>)new Object2IntOpenHashMap(var1);
/*    */     
/* 36 */     for (int var2 = 0; var2 < var1; var2++) {
/* 37 */       a(IRegistry.STATS.fromId(var0.i()), var0);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   private <T> void a(StatisticWrapper<T> var0, PacketDataSerializer var1) {
/* 43 */     int var2 = var1.i();
/* 44 */     int var3 = var1.i();
/* 45 */     this.a.put(var0.b(var0.getRegistry().fromId(var2)), var3);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 50 */     var0.d(this.a.size());
/* 51 */     for (ObjectIterator<Object2IntMap.Entry<Statistic<?>>> objectIterator = this.a.object2IntEntrySet().iterator(); objectIterator.hasNext(); ) { Object2IntMap.Entry<Statistic<?>> var2 = objectIterator.next();
/* 52 */       Statistic<?> var3 = (Statistic)var2.getKey();
/* 53 */       var0.d(IRegistry.STATS.a(var3.getWrapper()));
/* 54 */       var0.d(a(var3));
/* 55 */       var0.d(var2.getIntValue()); }
/*    */   
/*    */   }
/*    */   
/*    */   private <T> int a(Statistic<T> var0) {
/* 60 */     return var0.getWrapper().getRegistry().a(var0.b());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutStatistic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */