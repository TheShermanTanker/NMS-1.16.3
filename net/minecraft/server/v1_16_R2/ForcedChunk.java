/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
/*    */ import it.unimi.dsi.fastutil.longs.LongSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ForcedChunk
/*    */   extends PersistentBase
/*    */ {
/* 11 */   private LongSet a = (LongSet)new LongOpenHashSet();
/*    */   
/*    */   public ForcedChunk() {
/* 14 */     super("chunks");
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(NBTTagCompound var0) {
/* 19 */     this.a = (LongSet)new LongOpenHashSet(var0.getLongArray("Forced"));
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound b(NBTTagCompound var0) {
/* 24 */     var0.a("Forced", this.a.toLongArray());
/* 25 */     return var0;
/*    */   }
/*    */   
/*    */   public LongSet a() {
/* 29 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ForcedChunk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */