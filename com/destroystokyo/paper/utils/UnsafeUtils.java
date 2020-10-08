/*    */ package com.destroystokyo.paper.utils;
/*    */ 
/*    */ import java.lang.reflect.Field;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ import sun.misc.Unsafe;
/*    */ 
/*    */ 
/*    */ public class UnsafeUtils
/*    */ {
/*    */   private static final Unsafe UNSAFE;
/*    */   
/*    */   static {
/*    */     Unsafe unsafe;
/*    */     try {
/* 15 */       Class<?> c = Class.forName("sun.misc.Unsafe");
/* 16 */       Field f = c.getDeclaredField("theUnsafe");
/* 17 */       f.setAccessible(true);
/* 18 */       unsafe = (Unsafe)f.get(null);
/* 19 */     } catch (ClassNotFoundException|NoSuchFieldException|SecurityException e) {
/* 20 */       unsafe = null;
/* 21 */     } catch (IllegalAccessException e) {
/* 22 */       throw new AssertionError(e);
/*    */     } 
/* 24 */     UNSAFE = unsafe;
/*    */   }
/*    */   
/*    */   public static boolean isUnsafeSupported() {
/* 28 */     return (UNSAFE != null);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public static Unsafe getUnsafe() {
/* 33 */     return UNSAFE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\pape\\utils\UnsafeUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */