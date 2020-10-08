/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class NBTTagTypes {
/*  4 */   private static final NBTTagType<?>[] a = new NBTTagType[] { NBTTagEnd.a, NBTTagByte.a, NBTTagShort.a, NBTTagInt.a, NBTTagLong.a, NBTTagFloat.b, NBTTagDouble.b, NBTTagByteArray.a, NBTTagString.a, NBTTagList.a, NBTTagCompound.b, NBTTagIntArray.a, NBTTagLongArray.a };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static NBTTagType<?> a(int var0) {
/* 21 */     if (var0 < 0 || var0 >= a.length) {
/* 22 */       return NBTTagType.a(var0);
/*    */     }
/*    */     
/* 25 */     return a[var0];
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NBTTagTypes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */