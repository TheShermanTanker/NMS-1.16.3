/*    */ package com.destroystokyo.paper.event.executor.asm;
/*    */ 
/*    */ import java.lang.reflect.Method;
/*    */ import java.util.concurrent.atomic.AtomicInteger;
/*    */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.ClassWriter;
/*    */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Type;
/*    */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.commons.GeneratorAdapter;
/*    */ import org.bukkit.plugin.EventExecutor;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ASMEventExecutorGenerator
/*    */ {
/*    */   @NotNull
/*    */   public static byte[] generateEventExecutor(@NotNull Method m, @NotNull String name) {
/* 17 */     ClassWriter writer = new ClassWriter(3);
/* 18 */     writer.visit(52, 1, name.replace('.', '/'), null, Type.getInternalName(Object.class), new String[] { Type.getInternalName(EventExecutor.class) });
/*    */     
/* 20 */     GeneratorAdapter methodGenerator = new GeneratorAdapter(writer.visitMethod(1, "<init>", "()V", null, null), 1, "<init>", "()V");
/* 21 */     methodGenerator.loadThis();
/* 22 */     methodGenerator.visitMethodInsn(183, Type.getInternalName(Object.class), "<init>", "()V", false);
/* 23 */     methodGenerator.returnValue();
/* 24 */     methodGenerator.endMethod();
/*    */     
/* 26 */     methodGenerator = new GeneratorAdapter(writer.visitMethod(1, "execute", "(Lorg/bukkit/event/Listener;Lorg/bukkit/event/Event;)V", null, null), 1, "execute", "(Lorg/bukkit/event/Listener;Lorg/bukkit/event/Listener;)V");
/* 27 */     methodGenerator.loadArg(0);
/* 28 */     methodGenerator.checkCast(Type.getType(m.getDeclaringClass()));
/* 29 */     methodGenerator.loadArg(1);
/* 30 */     methodGenerator.checkCast(Type.getType(m.getParameterTypes()[0]));
/* 31 */     methodGenerator.visitMethodInsn(m.getDeclaringClass().isInterface() ? 185 : 182, Type.getInternalName(m.getDeclaringClass()), m.getName(), Type.getMethodDescriptor(m), m.getDeclaringClass().isInterface());
/* 32 */     if (m.getReturnType() != void.class) {
/* 33 */       methodGenerator.pop();
/*    */     }
/* 35 */     methodGenerator.returnValue();
/* 36 */     methodGenerator.endMethod();
/* 37 */     writer.visitEnd();
/* 38 */     return writer.toByteArray();
/*    */   }
/*    */   
/* 41 */   public static AtomicInteger NEXT_ID = new AtomicInteger(1);
/*    */   @NotNull
/*    */   public static String generateName() {
/* 44 */     int id = NEXT_ID.getAndIncrement();
/* 45 */     return "com.destroystokyo.paper.event.executor.asm.generated.GeneratedEventExecutor" + id;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\executor\asm\ASMEventExecutorGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */