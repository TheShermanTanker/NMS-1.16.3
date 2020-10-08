/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class NBTReadLimiter {
/*  4 */   public static final NBTReadLimiter a = new NBTReadLimiter(0L)
/*    */     {
/*    */       public void a(long var0) {}
/*    */     };
/*    */ 
/*    */   
/*    */   private final long b;
/*    */   
/*    */   private long c;
/*    */   
/*    */   public NBTReadLimiter(long var0) {
/* 15 */     this.b = var0;
/*    */   }
/*    */   
/*    */   public void a(long var0) {
/* 19 */     this.c += var0 / 8L;
/* 20 */     if (this.c > this.b)
/* 21 */       throw new RuntimeException("Tried to read NBT tag that was too big; tried to allocate: " + this.c + "bytes where max allowed: " + this.b); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NBTReadLimiter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */