/*     */ package org.fusesource.hawtjni.runtime;
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
/*     */ public enum MethodFlag
/*     */ {
/*  21 */   METHOD_SKIP,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  28 */   DYNAMIC,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   CONSTANT_GETTER,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   CAST,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   JNI,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   ADDRESS,
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   CPP_METHOD,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   CPP_NEW,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   CPP_DELETE,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   CS_NEW,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   CS_OBJECT,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   SETTER,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   GETTER,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   ADDER,
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   POINTER_RETURN,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   CONSTANT_INITIALIZER;
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\fusesource\hawtjni\runtime\MethodFlag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */