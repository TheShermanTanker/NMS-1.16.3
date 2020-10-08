/*    */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.commons;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.Map;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SimpleRemapper
/*    */   extends Remapper
/*    */ {
/*    */   private final Map<String, String> mapping;
/*    */   
/*    */   public SimpleRemapper(Map<String, String> mapping) {
/* 60 */     this.mapping = mapping;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SimpleRemapper(String oldName, String newName) {
/* 71 */     this.mapping = Collections.singletonMap(oldName, newName);
/*    */   }
/*    */ 
/*    */   
/*    */   public String mapMethodName(String owner, String name, String descriptor) {
/* 76 */     String remappedName = map(owner + '.' + name + descriptor);
/* 77 */     return (remappedName == null) ? name : remappedName;
/*    */   }
/*    */ 
/*    */   
/*    */   public String mapInvokeDynamicMethodName(String name, String descriptor) {
/* 82 */     String remappedName = map('.' + name + descriptor);
/* 83 */     return (remappedName == null) ? name : remappedName;
/*    */   }
/*    */ 
/*    */   
/*    */   public String mapFieldName(String owner, String name, String descriptor) {
/* 88 */     String remappedName = map(owner + '.' + name);
/* 89 */     return (remappedName == null) ? name : remappedName;
/*    */   }
/*    */ 
/*    */   
/*    */   public String map(String key) {
/* 94 */     return this.mapping.get(key);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\commons\SimpleRemapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */