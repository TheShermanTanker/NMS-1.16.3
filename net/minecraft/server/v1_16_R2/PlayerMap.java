/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
/*    */ import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ public final class PlayerMap
/*    */ {
/* 10 */   private final Object2BooleanMap<EntityPlayer> a = (Object2BooleanMap<EntityPlayer>)new Object2BooleanOpenHashMap();
/*    */   
/*    */   public Stream<EntityPlayer> a(long var0) {
/* 13 */     return this.a.keySet().stream();
/*    */   }
/*    */   
/*    */   public void a(long var0, EntityPlayer var2, boolean var3) {
/* 17 */     this.a.put(var2, var3);
/*    */   }
/*    */   
/*    */   public void a(long var0, EntityPlayer var2) {
/* 21 */     this.a.removeBoolean(var2);
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0) {
/* 25 */     this.a.replace(var0, true);
/*    */   }
/*    */   
/*    */   public void b(EntityPlayer var0) {
/* 29 */     this.a.replace(var0, false);
/*    */   }
/*    */   
/*    */   public boolean c(EntityPlayer var0) {
/* 33 */     return this.a.getOrDefault(var0, true);
/*    */   }
/*    */   
/*    */   public boolean d(EntityPlayer var0) {
/* 37 */     return this.a.getBoolean(var0);
/*    */   }
/*    */   
/*    */   public void a(long var0, long var2, EntityPlayer var4) {}
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PlayerMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */