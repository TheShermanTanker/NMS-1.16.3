/*    */ package org.jline.reader.impl.completer;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import org.jline.reader.Candidate;
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
/*    */ public class EnumCompleter
/*    */   extends StringsCompleter
/*    */ {
/*    */   public EnumCompleter(Class<? extends Enum<?>> source) {
/* 25 */     Objects.requireNonNull(source);
/* 26 */     for (Enum<?> n : (Enum[])source.getEnumConstants())
/* 27 */       this.candidates.add(new Candidate(n.name().toLowerCase())); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\reader\impl\completer\EnumCompleter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */