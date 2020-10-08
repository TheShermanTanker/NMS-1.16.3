/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
/*    */ import it.unimi.dsi.fastutil.longs.LongSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PersistentIndexed
/*    */   extends PersistentBase
/*    */ {
/* 11 */   private LongSet a = (LongSet)new LongOpenHashSet();
/* 12 */   private LongSet b = (LongSet)new LongOpenHashSet();
/*    */   
/*    */   public PersistentIndexed(String var0) {
/* 15 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(NBTTagCompound var0) {
/* 20 */     this.a = (LongSet)new LongOpenHashSet(var0.getLongArray("All"));
/* 21 */     this.b = (LongSet)new LongOpenHashSet(var0.getLongArray("Remaining"));
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound b(NBTTagCompound var0) {
/* 26 */     var0.a("All", this.a.toLongArray());
/* 27 */     var0.a("Remaining", this.b.toLongArray());
/* 28 */     return var0;
/*    */   }
/*    */   
/*    */   public void a(long var0) {
/* 32 */     this.a.add(var0);
/* 33 */     this.b.add(var0);
/*    */   }
/*    */   
/*    */   public boolean b(long var0) {
/* 37 */     return this.a.contains(var0);
/*    */   }
/*    */   
/*    */   public boolean c(long var0) {
/* 41 */     return this.b.contains(var0);
/*    */   }
/*    */   
/*    */   public void d(long var0) {
/* 45 */     this.b.remove(var0);
/*    */   }
/*    */   
/*    */   public LongSet a() {
/* 49 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PersistentIndexed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */