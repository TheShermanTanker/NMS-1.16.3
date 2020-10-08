/*     */ package org.jline.utils;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Levenshtein
/*     */ {
/*     */   public static int distance(CharSequence lhs, CharSequence rhs) {
/*  49 */     return distance(lhs, rhs, 1, 1, 1, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int distance(CharSequence source, CharSequence target, int deleteCost, int insertCost, int replaceCost, int swapCost) {
/*  59 */     if (2 * swapCost < insertCost + deleteCost) {
/*  60 */       throw new IllegalArgumentException("Unsupported cost assignment");
/*     */     }
/*  62 */     if (source.length() == 0) {
/*  63 */       return target.length() * insertCost;
/*     */     }
/*  65 */     if (target.length() == 0) {
/*  66 */       return source.length() * deleteCost;
/*     */     }
/*  68 */     int[][] table = new int[source.length()][target.length()];
/*  69 */     Map<Character, Integer> sourceIndexByCharacter = new HashMap<>();
/*  70 */     if (source.charAt(0) != target.charAt(0)) {
/*  71 */       table[0][0] = Math.min(replaceCost, deleteCost + insertCost);
/*     */     }
/*  73 */     sourceIndexByCharacter.put(Character.valueOf(source.charAt(0)), Integer.valueOf(0));
/*  74 */     for (int k = 1; k < source.length(); k++) {
/*  75 */       int deleteDistance = table[k - 1][0] + deleteCost;
/*  76 */       int insertDistance = (k + 1) * deleteCost + insertCost;
/*  77 */       int matchDistance = k * deleteCost + ((source.charAt(k) == target.charAt(0)) ? 0 : replaceCost);
/*  78 */       table[k][0] = Math.min(Math.min(deleteDistance, insertDistance), matchDistance);
/*     */     } 
/*  80 */     for (int j = 1; j < target.length(); j++) {
/*  81 */       int deleteDistance = (j + 1) * insertCost + deleteCost;
/*  82 */       int insertDistance = table[0][j - 1] + insertCost;
/*  83 */       int matchDistance = j * insertCost + ((source.charAt(0) == target.charAt(j)) ? 0 : replaceCost);
/*  84 */       table[0][j] = Math.min(Math.min(deleteDistance, insertDistance), matchDistance);
/*     */     } 
/*  86 */     for (int i = 1; i < source.length(); i++) {
/*  87 */       int maxSourceLetterMatchIndex = (source.charAt(i) == target.charAt(0)) ? 0 : -1;
/*  88 */       for (int m = 1; m < target.length(); m++) {
/*  89 */         int swapDistance; Integer candidateSwapIndex = sourceIndexByCharacter.get(Character.valueOf(target.charAt(m)));
/*  90 */         int jSwap = maxSourceLetterMatchIndex;
/*  91 */         int deleteDistance = table[i - 1][m] + deleteCost;
/*  92 */         int insertDistance = table[i][m - 1] + insertCost;
/*  93 */         int matchDistance = table[i - 1][m - 1];
/*  94 */         if (source.charAt(i) != target.charAt(m)) {
/*  95 */           matchDistance += replaceCost;
/*     */         } else {
/*  97 */           maxSourceLetterMatchIndex = m;
/*     */         } 
/*     */         
/* 100 */         if (candidateSwapIndex != null && jSwap != -1) {
/* 101 */           int preSwapCost, iSwap = candidateSwapIndex.intValue();
/*     */           
/* 103 */           if (iSwap == 0 && jSwap == 0) {
/* 104 */             preSwapCost = 0;
/*     */           } else {
/* 106 */             preSwapCost = table[Math.max(0, iSwap - 1)][Math.max(0, jSwap - 1)];
/*     */           } 
/* 108 */           swapDistance = preSwapCost + (i - iSwap - 1) * deleteCost + (m - jSwap - 1) * insertCost + swapCost;
/*     */         } else {
/* 110 */           swapDistance = Integer.MAX_VALUE;
/*     */         } 
/* 112 */         table[i][m] = Math.min(Math.min(Math.min(deleteDistance, insertDistance), matchDistance), swapDistance);
/*     */       } 
/* 114 */       sourceIndexByCharacter.put(Character.valueOf(source.charAt(i)), Integer.valueOf(i));
/*     */     } 
/* 116 */     return table[source.length() - 1][target.length() - 1];
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jlin\\utils\Levenshtein.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */