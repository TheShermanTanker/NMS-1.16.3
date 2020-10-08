/*    */ package org.jline.reader.impl.completer;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import org.jline.reader.Candidate;
/*    */ import org.jline.reader.Completer;
/*    */ import org.jline.reader.LineReader;
/*    */ import org.jline.reader.ParsedLine;
/*    */ import org.jline.utils.AttributedString;
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
/*    */ public class StringsCompleter
/*    */   implements Completer
/*    */ {
/* 30 */   protected final Collection<Candidate> candidates = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public StringsCompleter() {}
/*    */   
/*    */   public StringsCompleter(String... strings) {
/* 36 */     this(Arrays.asList(strings));
/*    */   }
/*    */   
/*    */   public StringsCompleter(Iterable<String> strings) {
/* 40 */     assert strings != null;
/* 41 */     for (String string : strings) {
/* 42 */       this.candidates.add(new Candidate(AttributedString.stripAnsi(string), string, null, null, null, null, true));
/*    */     }
/*    */   }
/*    */   
/*    */   public StringsCompleter(Candidate... candidates) {
/* 47 */     assert candidates != null;
/* 48 */     this.candidates.addAll(Arrays.asList(candidates));
/*    */   }
/*    */   
/*    */   public void complete(LineReader reader, ParsedLine commandLine, List<Candidate> candidates) {
/* 52 */     assert commandLine != null;
/* 53 */     assert candidates != null;
/* 54 */     candidates.addAll(this.candidates);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\reader\impl\completer\StringsCompleter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */