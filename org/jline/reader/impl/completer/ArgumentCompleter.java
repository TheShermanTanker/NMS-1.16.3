/*     */ package org.jline.reader.impl.completer;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import org.jline.reader.Candidate;
/*     */ import org.jline.reader.Completer;
/*     */ import org.jline.reader.LineReader;
/*     */ import org.jline.reader.ParsedLine;
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
/*     */ public class ArgumentCompleter
/*     */   implements Completer
/*     */ {
/*  34 */   private final List<Completer> completers = new ArrayList<>();
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean strict = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArgumentCompleter(Collection<Completer> completers) {
/*  44 */     Objects.requireNonNull(completers);
/*  45 */     this.completers.addAll(completers);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArgumentCompleter(Completer... completers) {
/*  54 */     this(Arrays.asList(completers));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStrict(boolean strict) {
/*  64 */     this.strict = strict;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStrict() {
/*  75 */     return this.strict;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Completer> getCompleters() {
/*  84 */     return this.completers;
/*     */   }
/*     */   public void complete(LineReader reader, ParsedLine line, List<Candidate> candidates) {
/*     */     Completer completer;
/*  88 */     Objects.requireNonNull(line);
/*  89 */     Objects.requireNonNull(candidates);
/*     */     
/*  91 */     if (line.wordIndex() < 0) {
/*     */       return;
/*     */     }
/*     */     
/*  95 */     List<Completer> completers = getCompleters();
/*     */ 
/*     */ 
/*     */     
/*  99 */     if (line.wordIndex() >= completers.size()) {
/* 100 */       completer = completers.get(completers.size() - 1);
/*     */     } else {
/*     */       
/* 103 */       completer = completers.get(line.wordIndex());
/*     */     } 
/*     */ 
/*     */     
/* 107 */     for (int i = 0; isStrict() && i < line.wordIndex(); i++) {
/* 108 */       Completer sub = completers.get((i >= completers.size()) ? (completers.size() - 1) : i);
/* 109 */       List<? extends CharSequence> args = line.words();
/* 110 */       String arg = (args == null || i >= args.size()) ? "" : ((CharSequence)args.get(i)).toString();
/*     */       
/* 112 */       List<Candidate> subCandidates = new LinkedList<>();
/* 113 */       sub.complete(reader, new ArgumentLine(arg, arg.length()), subCandidates);
/*     */       
/* 115 */       boolean found = false;
/* 116 */       for (Candidate cand : subCandidates) {
/* 117 */         if (cand.value().equals(arg)) {
/* 118 */           found = true;
/*     */           break;
/*     */         } 
/*     */       } 
/* 122 */       if (!found) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */     
/* 127 */     completer.complete(reader, line, candidates);
/*     */   }
/*     */   
/*     */   public static class ArgumentLine implements ParsedLine {
/*     */     private final String word;
/*     */     private final int cursor;
/*     */     
/*     */     public ArgumentLine(String word, int cursor) {
/* 135 */       this.word = word;
/* 136 */       this.cursor = cursor;
/*     */     }
/*     */ 
/*     */     
/*     */     public String word() {
/* 141 */       return this.word;
/*     */     }
/*     */ 
/*     */     
/*     */     public int wordCursor() {
/* 146 */       return this.cursor;
/*     */     }
/*     */ 
/*     */     
/*     */     public int wordIndex() {
/* 151 */       return 0;
/*     */     }
/*     */ 
/*     */     
/*     */     public List<String> words() {
/* 156 */       return Collections.singletonList(this.word);
/*     */     }
/*     */ 
/*     */     
/*     */     public String line() {
/* 161 */       return this.word;
/*     */     }
/*     */ 
/*     */     
/*     */     public int cursor() {
/* 166 */       return this.cursor;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\reader\impl\completer\ArgumentCompleter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */