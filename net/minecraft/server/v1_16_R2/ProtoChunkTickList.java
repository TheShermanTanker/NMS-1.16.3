/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.shorts.ShortList;
/*    */ import it.unimi.dsi.fastutil.shorts.ShortListIterator;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Predicate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ProtoChunkTickList<T>
/*    */   implements TickList<T>
/*    */ {
/*    */   protected final Predicate<T> a;
/*    */   private final ChunkCoordIntPair b;
/* 19 */   private final ShortList[] c = new ShortList[16];
/*    */   
/*    */   public ProtoChunkTickList(Predicate<T> var0, ChunkCoordIntPair var1) {
/* 22 */     this(var0, var1, new NBTTagList());
/*    */   }
/*    */   
/*    */   public ProtoChunkTickList(Predicate<T> var0, ChunkCoordIntPair var1, NBTTagList var2) {
/* 26 */     this.a = var0;
/* 27 */     this.b = var1;
/* 28 */     for (int var3 = 0; var3 < var2.size(); var3++) {
/* 29 */       NBTTagList var4 = var2.b(var3);
/* 30 */       for (int var5 = 0; var5 < var4.size(); var5++) {
/* 31 */         IChunkAccess.a(this.c, var3).add(var4.d(var5));
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public NBTTagList b() {
/* 37 */     return ChunkRegionLoader.a(this.c);
/*    */   }
/*    */   
/*    */   public void a(TickList<T> var0, Function<BlockPosition, T> var1) {
/* 41 */     for (int var2 = 0; var2 < this.c.length; var2++) {
/* 42 */       if (this.c[var2] != null) {
/* 43 */         for (ShortListIterator<Short> shortListIterator = this.c[var2].iterator(); shortListIterator.hasNext(); ) { Short var4 = shortListIterator.next();
/* 44 */           BlockPosition var5 = ProtoChunk.a(var4.shortValue(), var2, this.b);
/* 45 */           var0.a(var5, var1.apply(var5), 0); }
/*    */         
/* 47 */         this.c[var2].clear();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(BlockPosition var0, T var1) {
/* 54 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(BlockPosition var0, T var1, int var2, TickListPriority var3) {
/* 59 */     IChunkAccess.a(this.c, var0.getY() >> 4).add(ProtoChunk.l(var0));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b(BlockPosition var0, T var1) {
/* 64 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ProtoChunkTickList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */