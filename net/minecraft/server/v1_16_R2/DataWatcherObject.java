/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class DataWatcherObject<T> {
/*    */   private final int a;
/*    */   private final DataWatcherSerializer<T> b;
/*    */   
/*    */   public DataWatcherObject(int var0, DataWatcherSerializer<T> var1) {
/*  8 */     this.a = var0;
/*  9 */     this.b = var1;
/*    */   }
/*    */   
/*    */   public int a() {
/* 13 */     return this.a;
/*    */   }
/*    */   
/*    */   public DataWatcherSerializer<T> b() {
/* 17 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object var0) {
/* 22 */     if (this == var0) {
/* 23 */       return true;
/*    */     }
/* 25 */     if (var0 == null || getClass() != var0.getClass()) {
/* 26 */       return false;
/*    */     }
/*    */     
/* 29 */     DataWatcherObject<?> var1 = (DataWatcherObject)var0;
/*    */     
/* 31 */     return (this.a == var1.a);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 36 */     return this.a;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 41 */     return "<entity data: " + this.a + ">";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataWatcherObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */