/*    */ package org.bukkit.permissions;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Locale;
/*    */ import java.util.Map;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ public enum PermissionDefault
/*    */ {
/* 12 */   TRUE(new String[] { "true" }),
/* 13 */   FALSE(new String[] { "false" }),
/* 14 */   OP(new String[] { "op", "isop", "operator", "isoperator", "admin", "isadmin" }),
/* 15 */   NOT_OP(new String[] { "!op", "notop", "!operator", "notoperator", "!admin", "notadmin" });
/*    */   
/*    */   static {
/* 18 */     lookup = new HashMap<>();
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
/* 63 */     for (PermissionDefault value : values()) {
/* 64 */       for (String name : value.names)
/* 65 */         lookup.put(name, value); 
/*    */     } 
/*    */   }
/*    */   
/*    */   private final String[] names;
/*    */   private static final Map<String, PermissionDefault> lookup;
/*    */   
/*    */   PermissionDefault(String... names) {
/*    */     this.names = names;
/*    */   }
/*    */   
/*    */   public boolean getValue(boolean op) {
/*    */     switch (this) {
/*    */       case TRUE:
/*    */         return true;
/*    */       case FALSE:
/*    */         return false;
/*    */       case OP:
/*    */         return op;
/*    */       case NOT_OP:
/*    */         return !op;
/*    */     } 
/*    */     return false;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public static PermissionDefault getByName(@NotNull String name) {
/*    */     return lookup.get(name.toLowerCase(Locale.ENGLISH).replaceAll("[^a-z!]", ""));
/*    */   }
/*    */   
/*    */   public String toString() {
/*    */     return this.names[0];
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\permissions\PermissionDefault.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */