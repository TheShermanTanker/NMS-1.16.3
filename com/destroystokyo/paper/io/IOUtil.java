/*    */ package com.destroystokyo.paper.io;
/*    */ 
/*    */ import org.bukkit.Bukkit;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class IOUtil
/*    */ {
/*    */   public static long getCoordinateKey(int x, int z) {
/* 10 */     return z << 32L | x & 0xFFFFFFFFL;
/*    */   }
/*    */   
/*    */   public static int getCoordinateX(long key) {
/* 14 */     return (int)key;
/*    */   }
/*    */   
/*    */   public static int getCoordinateZ(long key) {
/* 18 */     return (int)(key >>> 32L);
/*    */   }
/*    */   
/*    */   public static int getRegionCoordinate(int chunkCoordinate) {
/* 22 */     return chunkCoordinate >> 5;
/*    */   }
/*    */   
/*    */   public static int getChunkInRegion(int chunkCoordinate) {
/* 26 */     return chunkCoordinate & 0x1F;
/*    */   }
/*    */   
/*    */   public static String genericToString(Object object) {
/* 30 */     return (object == null) ? "null" : (object.getClass().getName() + ":" + object.toString());
/*    */   }
/*    */   
/*    */   public static <T> T notNull(T obj) {
/* 34 */     if (obj == null) {
/* 35 */       throw new NullPointerException();
/*    */     }
/* 37 */     return obj;
/*    */   }
/*    */   
/*    */   public static <T> T notNull(T obj, String msgIfNull) {
/* 41 */     if (obj == null) {
/* 42 */       throw new NullPointerException(msgIfNull);
/*    */     }
/* 44 */     return obj;
/*    */   }
/*    */   
/*    */   public static void arrayBounds(int off, int len, int arrayLength, String msgPrefix) {
/* 48 */     if (off < 0 || len < 0 || arrayLength - off < len) {
/* 49 */       throw new ArrayIndexOutOfBoundsException(msgPrefix + ": off: " + off + ", len: " + len + ", array length: " + arrayLength);
/*    */     }
/*    */   }
/*    */   
/*    */   public static int getPriorityForCurrentThread() {
/* 54 */     return Bukkit.isPrimaryThread() ? 0 : 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public static <T extends Throwable> void rethrow(Throwable throwable) throws T {
/* 59 */     throw (T)throwable;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\io\IOUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */