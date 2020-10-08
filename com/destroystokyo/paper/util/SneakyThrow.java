/*    */ package com.destroystokyo.paper.util;
/*    */ 
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class SneakyThrow
/*    */ {
/*    */   public static void sneaky(@NotNull Throwable exception) {
/*  8 */     throwSneaky(exception);
/*    */   }
/*    */ 
/*    */   
/*    */   private static <T extends Throwable> void throwSneaky(@NotNull Throwable exception) throws T {
/* 13 */     throw (T)exception;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\pape\\util\SneakyThrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */