/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class IChunkProvider
/*    */   implements ILightAccess, AutoCloseable
/*    */ {
/*    */   @Nullable
/*    */   public Chunk getChunkAt(int var0, int var1, boolean var2) {
/* 16 */     return (Chunk)getChunkAt(var0, var1, ChunkStatus.FULL, var2);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public Chunk a(int var0, int var1) {
/* 21 */     return getChunkAt(var0, var1, false);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public IBlockAccess c(int var0, int var1) {
/* 27 */     return getChunkAt(var0, var1, ChunkStatus.EMPTY, false);
/*    */   }
/*    */   
/*    */   public boolean b(int var0, int var1) {
/* 31 */     return (getChunkAt(var0, var1, ChunkStatus.FULL, false) != null);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public abstract IChunkAccess getChunkAt(int paramInt1, int paramInt2, ChunkStatus paramChunkStatus, boolean paramBoolean);
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract String getName();
/*    */ 
/*    */ 
/*    */   
/*    */   public void close() throws IOException {}
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract LightEngine getLightEngine();
/*    */ 
/*    */   
/*    */   public void a(boolean var0, boolean var1) {}
/*    */ 
/*    */   
/*    */   public void a(ChunkCoordIntPair var0, boolean var1) {}
/*    */ 
/*    */   
/*    */   public boolean a(Entity var0) {
/* 59 */     return true;
/*    */   }
/*    */   
/*    */   public boolean a(ChunkCoordIntPair var0) {
/* 63 */     return true;
/*    */   }
/*    */   
/*    */   public boolean a(BlockPosition var0) {
/* 67 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IChunkProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */