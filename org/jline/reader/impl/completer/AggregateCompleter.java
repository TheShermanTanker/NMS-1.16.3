/*    */ package org.jline.reader.impl.completer;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import org.jline.reader.Candidate;
/*    */ import org.jline.reader.Completer;
/*    */ import org.jline.reader.LineReader;
/*    */ import org.jline.reader.ParsedLine;
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
/*    */ public class AggregateCompleter
/*    */   implements Completer
/*    */ {
/*    */   private final Collection<Completer> completers;
/*    */   
/*    */   public AggregateCompleter(Completer... completers) {
/* 39 */     this(Arrays.asList(completers));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AggregateCompleter(Collection<Completer> completers) {
/* 49 */     assert completers != null;
/* 50 */     this.completers = completers;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Collection<Completer> getCompleters() {
/* 59 */     return this.completers;
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
/*    */   public void complete(LineReader reader, ParsedLine line, List<Candidate> candidates) {
/* 71 */     Objects.requireNonNull(line);
/* 72 */     Objects.requireNonNull(candidates);
/* 73 */     this.completers.forEach(c -> c.complete(reader, line, candidates));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 81 */     return getClass().getSimpleName() + "{completers=" + this.completers + '}';
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\reader\impl\completer\AggregateCompleter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */