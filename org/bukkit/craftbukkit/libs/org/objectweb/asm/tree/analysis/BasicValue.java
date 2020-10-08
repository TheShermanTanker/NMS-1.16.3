/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.analysis;
/*     */ 
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Type;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BasicValue
/*     */   implements Value
/*     */ {
/*  41 */   public static final BasicValue UNINITIALIZED_VALUE = new BasicValue(null);
/*     */ 
/*     */   
/*  44 */   public static final BasicValue INT_VALUE = new BasicValue(Type.INT_TYPE);
/*     */ 
/*     */   
/*  47 */   public static final BasicValue FLOAT_VALUE = new BasicValue(Type.FLOAT_TYPE);
/*     */ 
/*     */   
/*  50 */   public static final BasicValue LONG_VALUE = new BasicValue(Type.LONG_TYPE);
/*     */ 
/*     */   
/*  53 */   public static final BasicValue DOUBLE_VALUE = new BasicValue(Type.DOUBLE_TYPE);
/*     */ 
/*     */   
/*  56 */   public static final BasicValue REFERENCE_VALUE = new BasicValue(
/*  57 */       Type.getObjectType("java/lang/Object"));
/*     */ 
/*     */   
/*  60 */   public static final BasicValue RETURNADDRESS_VALUE = new BasicValue(Type.VOID_TYPE);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Type type;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicValue(Type type) {
/*  71 */     this.type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type getType() {
/*  80 */     return this.type;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSize() {
/*  85 */     return (this.type == Type.LONG_TYPE || this.type == Type.DOUBLE_TYPE) ? 2 : 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReference() {
/*  94 */     return (this.type != null && (this.type.getSort() == 10 || this.type.getSort() == 9));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object value) {
/*  99 */     if (value == this)
/* 100 */       return true; 
/* 101 */     if (value instanceof BasicValue) {
/* 102 */       if (this.type == null) {
/* 103 */         return (((BasicValue)value).type == null);
/*     */       }
/* 105 */       return this.type.equals(((BasicValue)value).type);
/*     */     } 
/*     */     
/* 108 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 114 */     return (this.type == null) ? 0 : this.type.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 119 */     if (this == UNINITIALIZED_VALUE)
/* 120 */       return "."; 
/* 121 */     if (this == RETURNADDRESS_VALUE)
/* 122 */       return "A"; 
/* 123 */     if (this == REFERENCE_VALUE) {
/* 124 */       return "R";
/*     */     }
/* 126 */     return this.type.getDescriptor();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\analysis\BasicValue.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */