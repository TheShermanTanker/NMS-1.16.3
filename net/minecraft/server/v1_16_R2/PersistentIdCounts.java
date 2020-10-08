/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.objects.Object2IntMap;
/*    */ import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
/*    */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PersistentIdCounts
/*    */   extends PersistentBase
/*    */ {
/* 12 */   private final Object2IntMap<String> a = (Object2IntMap<String>)new Object2IntOpenHashMap();
/*    */   
/*    */   public PersistentIdCounts() {
/* 15 */     super("idcounts");
/* 16 */     this.a.defaultReturnValue(-1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(NBTTagCompound var0) {
/* 21 */     this.a.clear();
/*    */     
/* 23 */     for (String var2 : var0.getKeys()) {
/* 24 */       if (var0.hasKeyOfType(var2, 99)) {
/* 25 */         this.a.put(var2, var0.getInt(var2));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound b(NBTTagCompound var0) {
/* 32 */     for (ObjectIterator<Object2IntMap.Entry<String>> objectIterator = this.a.object2IntEntrySet().iterator(); objectIterator.hasNext(); ) { Object2IntMap.Entry<String> var2 = objectIterator.next();
/* 33 */       var0.setInt((String)var2.getKey(), var2.getIntValue()); }
/*    */     
/* 35 */     return var0;
/*    */   }
/*    */   
/*    */   public int a() {
/* 39 */     int var0 = this.a.getInt("map") + 1;
/* 40 */     this.a.put("map", var0);
/* 41 */     b();
/* 42 */     return var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PersistentIdCounts.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */