/*    */ package org.bukkit;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.lang.annotation.ElementType;
/*    */ import java.lang.annotation.Retention;
/*    */ import java.lang.annotation.RetentionPolicy;
/*    */ import java.lang.annotation.Target;
/*    */ import java.util.Map;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.TYPE})
/*    */ @Retention(RetentionPolicy.RUNTIME)
/*    */ public @interface Warning
/*    */ {
/*    */   boolean value() default false;
/*    */   
/*    */   String reason() default "";
/*    */   
/*    */   public enum WarningState
/*    */   {
/* 30 */     ON,
/*    */ 
/*    */ 
/*    */     
/* 34 */     OFF,
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 39 */     DEFAULT;
/*    */     
/* 41 */     private static final Map<String, WarningState> values = (Map<String, WarningState>)ImmutableMap.builder()
/* 42 */       .put("off", OFF)
/* 43 */       .put("false", OFF)
/* 44 */       .put("f", OFF)
/* 45 */       .put("no", OFF)
/* 46 */       .put("n", OFF)
/* 47 */       .put("on", ON)
/* 48 */       .put("true", ON)
/* 49 */       .put("t", ON)
/* 50 */       .put("yes", ON)
/* 51 */       .put("y", ON)
/* 52 */       .put("", DEFAULT)
/* 53 */       .put("d", DEFAULT)
/* 54 */       .put("default", DEFAULT)
/* 55 */       .build();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     static {
/*    */     
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public boolean printFor(@Nullable Warning warning) {
/* 70 */       if (this == DEFAULT) {
/* 71 */         return (warning == null || warning.value());
/*    */       }
/* 73 */       return (this == ON);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     @NotNull
/*    */     public static WarningState value(@Nullable String value) {
/* 86 */       if (value == null) {
/* 87 */         return DEFAULT;
/*    */       }
/* 89 */       WarningState state = values.get(value.toLowerCase());
/* 90 */       if (state == null) {
/* 91 */         return DEFAULT;
/*    */       }
/* 93 */       return state;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\Warning.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */