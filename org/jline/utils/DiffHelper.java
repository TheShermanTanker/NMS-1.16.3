/*     */ package org.jline.utils;
/*     */ 
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
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
/*     */ 
/*     */ public class DiffHelper
/*     */ {
/*     */   public enum Operation
/*     */   {
/*  28 */     DELETE, INSERT, EQUAL;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Diff
/*     */   {
/*     */     public final DiffHelper.Operation operation;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public final AttributedString text;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Diff(DiffHelper.Operation operation, AttributedString text) {
/*  51 */       this.operation = operation;
/*  52 */       this.text = text;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/*  60 */       return "Diff(" + this.operation + ",\"" + this.text + "\")";
/*     */     }
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
/*     */   
/*     */   public static List<Diff> diff(AttributedString text1, AttributedString text2) {
/*  77 */     int l1 = text1.length();
/*  78 */     int l2 = text2.length();
/*  79 */     int n = Math.min(l1, l2);
/*  80 */     int commonStart = 0;
/*     */ 
/*     */ 
/*     */     
/*  84 */     int startHiddenRange = -1;
/*  85 */     while (commonStart < n && text1
/*  86 */       .charAt(commonStart) == text2.charAt(commonStart) && text1
/*  87 */       .styleAt(commonStart).equals(text2.styleAt(commonStart))) {
/*  88 */       if (text1.isHidden(commonStart)) {
/*  89 */         if (startHiddenRange < 0)
/*  90 */           startHiddenRange = commonStart; 
/*     */       } else {
/*  92 */         startHiddenRange = -1;
/*  93 */       }  commonStart++;
/*     */     } 
/*  95 */     if (startHiddenRange >= 0 && ((l1 > commonStart && text1
/*  96 */       .isHidden(commonStart)) || (l2 > commonStart && text2
/*  97 */       .isHidden(commonStart)))) {
/*  98 */       commonStart = startHiddenRange;
/*     */     }
/* 100 */     startHiddenRange = -1;
/* 101 */     int commonEnd = 0;
/* 102 */     while (commonEnd < n - commonStart && text1
/* 103 */       .charAt(l1 - commonEnd - 1) == text2.charAt(l2 - commonEnd - 1) && text1
/* 104 */       .styleAt(l1 - commonEnd - 1).equals(text2.styleAt(l2 - commonEnd - 1))) {
/* 105 */       if (text1.isHidden(l1 - commonEnd - 1)) {
/* 106 */         if (startHiddenRange < 0)
/* 107 */           startHiddenRange = commonEnd; 
/*     */       } else {
/* 109 */         startHiddenRange = -1;
/* 110 */       }  commonEnd++;
/*     */     } 
/* 112 */     if (startHiddenRange >= 0)
/* 113 */       commonEnd = startHiddenRange; 
/* 114 */     LinkedList<Diff> diffs = new LinkedList<>();
/* 115 */     if (commonStart > 0) {
/* 116 */       diffs.add(new Diff(Operation.EQUAL, text1
/* 117 */             .subSequence(0, commonStart)));
/*     */     }
/* 119 */     if (l2 > commonStart + commonEnd) {
/* 120 */       diffs.add(new Diff(Operation.INSERT, text2
/* 121 */             .subSequence(commonStart, l2 - commonEnd)));
/*     */     }
/* 123 */     if (l1 > commonStart + commonEnd) {
/* 124 */       diffs.add(new Diff(Operation.DELETE, text1
/* 125 */             .subSequence(commonStart, l1 - commonEnd)));
/*     */     }
/* 127 */     if (commonEnd > 0) {
/* 128 */       diffs.add(new Diff(Operation.EQUAL, text1
/* 129 */             .subSequence(l1 - commonEnd, l1)));
/*     */     }
/* 131 */     return diffs;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jlin\\utils\DiffHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */