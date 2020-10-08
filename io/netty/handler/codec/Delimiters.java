/*    */ package io.netty.handler.codec;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.buffer.Unpooled;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class Delimiters
/*    */ {
/*    */   public static ByteBuf[] nulDelimiter() {
/* 31 */     return new ByteBuf[] {
/* 32 */         Unpooled.wrappedBuffer(new byte[] { 0 })
/*    */       };
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ByteBuf[] lineDelimiter() {
/* 40 */     return new ByteBuf[] {
/* 41 */         Unpooled.wrappedBuffer(new byte[] { 13, 10
/* 42 */           }), Unpooled.wrappedBuffer(new byte[] { 10 })
/*    */       };
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\codec\Delimiters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */