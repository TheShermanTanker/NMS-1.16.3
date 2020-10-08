/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ public interface NBTBase
/*    */ {
/*  8 */   public static final EnumChatFormat d = EnumChatFormat.AQUA;
/*  9 */   public static final EnumChatFormat e = EnumChatFormat.GREEN;
/* 10 */   public static final EnumChatFormat f = EnumChatFormat.GOLD;
/* 11 */   public static final EnumChatFormat g = EnumChatFormat.RED;
/*    */   
/*    */   void write(DataOutput paramDataOutput) throws IOException;
/*    */   
/*    */   String toString();
/*    */   
/*    */   byte getTypeId();
/*    */   
/*    */   NBTTagType<?> b();
/*    */   
/*    */   NBTBase clone();
/*    */   
/*    */   default String asString() {
/* 24 */     return toString();
/*    */   }
/*    */   
/*    */   default IChatBaseComponent l() {
/* 28 */     return a("", 0);
/*    */   }
/*    */   
/*    */   IChatBaseComponent a(String paramString, int paramInt);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NBTBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */