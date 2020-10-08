/*    */ package com.destroystokyo.paper.event.executor.asm;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import com.google.common.collect.MapMaker;
/*    */ import java.util.concurrent.ConcurrentMap;
/*    */ import java.util.function.Function;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class SafeClassDefiner
/*    */   implements ClassDefiner
/*    */ {
/* 13 */   static final SafeClassDefiner INSTANCE = new SafeClassDefiner();
/*    */ 
/*    */ 
/*    */   
/* 17 */   private final ConcurrentMap<ClassLoader, GeneratedClassLoader> loaders = (new MapMaker()).weakKeys().makeMap();
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Class<?> defineClass(@NotNull ClassLoader parentLoader, @NotNull String name, @NotNull byte[] data) {
/* 22 */     GeneratedClassLoader loader = this.loaders.computeIfAbsent(parentLoader, GeneratedClassLoader::new);
/* 23 */     synchronized (loader.getClassLoadingLock(name)) {
/* 24 */       Preconditions.checkState(!loader.hasClass(name), "%s already defined", name);
/* 25 */       Class<?> c = loader.define(name, data);
/* 26 */       assert c.getName().equals(name);
/* 27 */       return c;
/*    */     } 
/*    */   }
/*    */   
/*    */   private static class GeneratedClassLoader extends ClassLoader {
/*    */     static {
/* 33 */       ClassLoader.registerAsParallelCapable();
/*    */     }
/*    */     
/*    */     protected GeneratedClassLoader(@NotNull ClassLoader parent) {
/* 37 */       super(parent);
/*    */     }
/*    */     
/*    */     private Class<?> define(@NotNull String name, byte[] data) {
/* 41 */       synchronized (getClassLoadingLock(name)) {
/* 42 */         assert !hasClass(name);
/* 43 */         Class<?> c = defineClass(name, data, 0, data.length);
/* 44 */         resolveClass(c);
/* 45 */         return c;
/*    */       } 
/*    */     }
/*    */ 
/*    */     
/*    */     @NotNull
/*    */     public Object getClassLoadingLock(@NotNull String name) {
/* 52 */       return super.getClassLoadingLock(name);
/*    */     }
/*    */     
/*    */     public boolean hasClass(@NotNull String name) {
/* 56 */       synchronized (getClassLoadingLock(name)) {
/*    */         
/* 58 */         Class.forName(name);
/* 59 */         return true;
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\executor\asm\SafeClassDefiner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */