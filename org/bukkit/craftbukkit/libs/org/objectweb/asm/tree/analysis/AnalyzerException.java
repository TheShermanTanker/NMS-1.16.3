/*    */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.analysis;
/*    */ 
/*    */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.AbstractInsnNode;
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
/*    */ public class AnalyzerException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 3154190448018943333L;
/*    */   public final transient AbstractInsnNode node;
/*    */   
/*    */   public AnalyzerException(AbstractInsnNode insn, String message) {
/* 52 */     super(message);
/* 53 */     this.node = insn;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AnalyzerException(AbstractInsnNode insn, String message, Throwable cause) {
/* 65 */     super(message, cause);
/* 66 */     this.node = insn;
/*    */   }
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
/*    */   public AnalyzerException(AbstractInsnNode insn, String message, Object expected, Value actual) {
/* 82 */     super(((message == null) ? "Expected " : (message + ": expected ")) + expected + ", but found " + actual);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 87 */     this.node = insn;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\analysis\AnalyzerException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */