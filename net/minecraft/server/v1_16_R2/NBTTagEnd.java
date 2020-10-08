/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NBTTagEnd
/*    */   implements NBTBase
/*    */ {
/* 15 */   public static final NBTTagType<NBTTagEnd> a = new NBTTagType<NBTTagEnd>()
/*    */     {
/*    */       public NBTTagEnd b(DataInput var0, int var1, NBTReadLimiter var2) {
/* 18 */         var2.a(64L);
/* 19 */         return NBTTagEnd.b;
/*    */       }
/*    */ 
/*    */       
/*    */       public String a() {
/* 24 */         return "END";
/*    */       }
/*    */ 
/*    */       
/*    */       public String b() {
/* 29 */         return "TAG_End";
/*    */       }
/*    */ 
/*    */       
/*    */       public boolean c() {
/* 34 */         return true;
/*    */       }
/*    */     };
/*    */   
/* 38 */   public static final NBTTagEnd b = new NBTTagEnd();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void write(DataOutput var0) throws IOException {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public byte getTypeId() {
/* 49 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagType<NBTTagEnd> b() {
/* 54 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 59 */     return "END";
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagEnd clone() {
/* 64 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatBaseComponent a(String var0, int var1) {
/* 69 */     return ChatComponentText.d;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NBTTagEnd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */