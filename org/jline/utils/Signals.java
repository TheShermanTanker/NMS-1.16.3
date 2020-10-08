/*     */ package org.jline.utils;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Proxy;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Signals
/*     */ {
/*     */   public static Object register(String name, Runnable handler) {
/*  35 */     Objects.requireNonNull(handler);
/*  36 */     return register(name, handler, handler.getClass().getClassLoader());
/*     */   }
/*     */   
/*     */   public static Object register(String name, Runnable handler, ClassLoader loader) {
/*     */     try {
/*  41 */       Class<?> signalHandlerClass = Class.forName("sun.misc.SignalHandler");
/*     */       
/*  43 */       Object signalHandler = Proxy.newProxyInstance(loader, new Class[] { signalHandlerClass }, (proxy, method, args) -> {
/*     */             if (method.getDeclaringClass() == Object.class) {
/*     */               if ("toString".equals(method.getName())) {
/*     */                 return handler.toString();
/*     */               }
/*     */             } else if (method.getDeclaringClass() == signalHandlerClass) {
/*     */               Log.trace(());
/*     */               
/*     */               handler.run();
/*     */             } 
/*     */             
/*     */             return null;
/*     */           });
/*  56 */       return doRegister(name, signalHandler);
/*  57 */     } catch (Exception e) {
/*     */       
/*  59 */       Log.debug(new Object[] { "Error registering handler for signal ", name, e });
/*  60 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Object registerDefault(String name) {
/*     */     try {
/*  66 */       Class<?> signalHandlerClass = Class.forName("sun.misc.SignalHandler");
/*  67 */       return doRegister(name, signalHandlerClass.getField("SIG_DFL").get(null));
/*  68 */     } catch (Exception e) {
/*     */       
/*  70 */       Log.debug(new Object[] { "Error registering default handler for signal ", name, e });
/*  71 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void unregister(String name, Object previous) {
/*     */     try {
/*  78 */       if (previous != null) {
/*  79 */         doRegister(name, previous);
/*     */       }
/*  81 */     } catch (Exception e) {
/*     */       
/*  83 */       Log.debug(new Object[] { "Error unregistering handler for signal ", name, e });
/*     */     } 
/*     */   }
/*     */   private static Object doRegister(String name, Object handler) throws Exception {
/*     */     Object signal;
/*  88 */     Log.trace(() -> "Registering signal " + name + " with handler " + toString(handler));
/*  89 */     Class<?> signalClass = Class.forName("sun.misc.Signal");
/*  90 */     Constructor<?> constructor = signalClass.getConstructor(new Class[] { String.class });
/*     */     
/*     */     try {
/*  93 */       signal = constructor.newInstance(new Object[] { name });
/*  94 */     } catch (IllegalArgumentException e) {
/*  95 */       Log.trace(() -> "Ignoring unsupported signal " + name);
/*  96 */       return null;
/*     */     } 
/*  98 */     Class<?> signalHandlerClass = Class.forName("sun.misc.SignalHandler");
/*  99 */     return signalClass.getMethod("handle", new Class[] { signalClass, signalHandlerClass
/* 100 */         }).invoke(null, new Object[] { signal, handler });
/*     */   }
/*     */ 
/*     */   
/*     */   private static String toString(Object handler) {
/*     */     try {
/* 106 */       Class<?> signalHandlerClass = Class.forName("sun.misc.SignalHandler");
/* 107 */       if (handler == signalHandlerClass.getField("SIG_DFL").get(null)) {
/* 108 */         return "SIG_DFL";
/*     */       }
/* 110 */       if (handler == signalHandlerClass.getField("SIG_IGN").get(null)) {
/* 111 */         return "SIG_IGN";
/*     */       }
/* 113 */     } catch (Throwable throwable) {}
/*     */ 
/*     */     
/* 116 */     return (handler != null) ? handler.toString() : "null";
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jlin\\utils\Signals.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */