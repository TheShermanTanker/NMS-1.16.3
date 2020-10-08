/*    */ package com.destroystokyo.paper.event.executor.asm;
/*    */ 
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface ClassDefiner
/*    */ {
/*    */   default boolean isBypassAccessChecks() {
/* 14 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   Class<?> defineClass(@NotNull ClassLoader paramClassLoader, @NotNull String paramString, @NotNull byte[] paramArrayOfbyte);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   static ClassDefiner getInstance() {
/* 32 */     return SafeClassDefiner.INSTANCE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\executor\asm\ClassDefiner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */