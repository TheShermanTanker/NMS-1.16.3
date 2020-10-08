/*    */ package org.bukkit.metadata;
/*    */ 
/*    */ import org.bukkit.plugin.Plugin;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FixedMetadataValue
/*    */   extends LazyMetadataValue
/*    */ {
/*    */   private final Object internalValue;
/*    */   
/*    */   public FixedMetadataValue(@NotNull Plugin owningPlugin, @Nullable Object value) {
/* 30 */     super(owningPlugin);
/* 31 */     this.internalValue = value;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void invalidate() {}
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Object value() {
/* 42 */     return this.internalValue;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\metadata\FixedMetadataValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */