/*    */ package com.mojang.brigadier.suggestion;
/*    */ 
/*    */ import com.mojang.brigadier.Message;
/*    */ import com.mojang.brigadier.context.StringRange;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ 
/*    */ public class SuggestionsBuilder
/*    */ {
/*    */   private final String input;
/*    */   private final int start;
/*    */   private final String remaining;
/*    */   private String remainingLowercase;
/*    */   
/*    */   public final String getRemainingLowercase() {
/* 17 */     return (this.remainingLowercase == null) ? (this.remainingLowercase = this.remaining.toLowerCase()) : this.remainingLowercase;
/* 18 */   } private final List<Suggestion> result = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public SuggestionsBuilder(String input, int start) {
/* 22 */     this(input, start, null);
/*    */   }
/*    */   public SuggestionsBuilder(String input, int start, String remainingLowercase) {
/* 25 */     this.remainingLowercase = remainingLowercase;
/*    */     
/* 27 */     this.input = input;
/* 28 */     this.start = start;
/* 29 */     this.remaining = input.substring(start);
/*    */   }
/*    */   
/*    */   public String getInput() {
/* 33 */     return this.input;
/*    */   }
/*    */   
/*    */   public int getStart() {
/* 37 */     return this.start;
/*    */   }
/*    */   
/*    */   public String getRemaining() {
/* 41 */     return this.remaining;
/*    */   }
/*    */   
/*    */   public Suggestions build() {
/* 45 */     return Suggestions.create(this.input, this.result);
/*    */   }
/*    */   
/*    */   public CompletableFuture<Suggestions> buildFuture() {
/* 49 */     return CompletableFuture.completedFuture(build());
/*    */   }
/*    */   
/*    */   public SuggestionsBuilder suggest(String text) {
/* 53 */     if (text.equals(this.remaining)) {
/* 54 */       return this;
/*    */     }
/* 56 */     this.result.add(new Suggestion(StringRange.between(this.start, this.input.length()), text));
/* 57 */     return this;
/*    */   }
/*    */   
/*    */   public SuggestionsBuilder suggest(String text, Message tooltip) {
/* 61 */     if (text.equals(this.remaining)) {
/* 62 */       return this;
/*    */     }
/* 64 */     this.result.add(new Suggestion(StringRange.between(this.start, this.input.length()), text, tooltip));
/* 65 */     return this;
/*    */   }
/*    */   
/*    */   public SuggestionsBuilder suggest(int value) {
/* 69 */     this.result.add(new IntegerSuggestion(StringRange.between(this.start, this.input.length()), value));
/* 70 */     return this;
/*    */   }
/*    */   
/*    */   public SuggestionsBuilder suggest(int value, Message tooltip) {
/* 74 */     this.result.add(new IntegerSuggestion(StringRange.between(this.start, this.input.length()), value, tooltip));
/* 75 */     return this;
/*    */   }
/*    */   
/*    */   public SuggestionsBuilder add(SuggestionsBuilder other) {
/* 79 */     this.result.addAll(other.result);
/* 80 */     return this;
/*    */   }
/*    */   
/*    */   public SuggestionsBuilder createOffset(int start) {
/* 84 */     return new SuggestionsBuilder(this.input, start);
/*    */   }
/*    */   
/*    */   public SuggestionsBuilder restart() {
/* 88 */     return new SuggestionsBuilder(this.input, this.start);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mojang\brigadier\suggestion\SuggestionsBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */