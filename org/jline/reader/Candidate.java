/*     */ package org.jline.reader;
/*     */ 
/*     */ import java.util.Objects;
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
/*     */ 
/*     */ public class Candidate
/*     */   implements Comparable<Candidate>
/*     */ {
/*     */   private final String value;
/*     */   private final String displ;
/*     */   private final String group;
/*     */   private final String descr;
/*     */   private final String suffix;
/*     */   private final String key;
/*     */   private final boolean complete;
/*     */   
/*     */   public Candidate(String value) {
/*  34 */     this(value, value, null, null, null, null, true);
/*     */   }
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
/*     */   public Candidate(String value, String displ, String group, String descr, String suffix, String key, boolean complete) {
/*  49 */     Objects.requireNonNull(value);
/*  50 */     this.value = value;
/*  51 */     this.displ = displ;
/*  52 */     this.group = group;
/*  53 */     this.descr = descr;
/*  54 */     this.suffix = suffix;
/*  55 */     this.key = key;
/*  56 */     this.complete = complete;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String value() {
/*  65 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String displ() {
/*  74 */     return this.displ;
/*     */   }
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
/*     */   public String group() {
/*  87 */     return this.group;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String descr() {
/*  97 */     return this.descr;
/*     */   }
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
/*     */   public String suffix() {
/* 111 */     return this.suffix;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String key() {
/* 121 */     return this.key;
/*     */   }
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
/*     */   public boolean complete() {
/* 134 */     return this.complete;
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(Candidate o) {
/* 139 */     return this.value.compareTo(o.value);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\reader\Candidate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */