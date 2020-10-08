/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ public interface NBTTagType<T extends NBTBase> {
/*    */   T b(DataInput paramDataInput, int paramInt, NBTReadLimiter paramNBTReadLimiter) throws IOException;
/*    */   
/*    */   default boolean c() {
/* 10 */     return false;
/*    */   }
/*    */   
/*    */   String a();
/*    */   
/*    */   String b();
/*    */   
/*    */   static NBTTagType<NBTTagEnd> a(int var0) {
/* 18 */     return new NBTTagType<NBTTagEnd>(var0)
/*    */       {
/*    */         public NBTTagEnd b(DataInput var0, int var1, NBTReadLimiter var2) throws IOException {
/* 21 */           throw new IllegalArgumentException("Invalid tag id: " + this.a);
/*    */         }
/*    */ 
/*    */         
/*    */         public String a() {
/* 26 */           return "INVALID[" + this.a + "]";
/*    */         }
/*    */ 
/*    */         
/*    */         public String b() {
/* 31 */           return "UNKNOWN_" + this.a;
/*    */         }
/*    */       };
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NBTTagType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */