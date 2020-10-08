/*    */ package com.destroystokyo.paper;
/*    */ 
/*    */ import com.google.common.base.Objects;
/*    */ import java.util.StringJoiner;
/*    */ 
/*    */ public class PaperSkinParts
/*    */   implements SkinParts
/*    */ {
/*    */   private final int raw;
/*    */   
/*    */   public PaperSkinParts(int raw) {
/* 12 */     this.raw = raw;
/*    */   }
/*    */   
/*    */   public boolean hasCapeEnabled() {
/* 16 */     return ((this.raw & 0x1) == 1);
/*    */   }
/*    */   
/*    */   public boolean hasJacketEnabled() {
/* 20 */     return ((this.raw >> 1 & 0x1) == 1);
/*    */   }
/*    */   
/*    */   public boolean hasLeftSleeveEnabled() {
/* 24 */     return ((this.raw >> 2 & 0x1) == 1);
/*    */   }
/*    */   
/*    */   public boolean hasRightSleeveEnabled() {
/* 28 */     return ((this.raw >> 3 & 0x1) == 1);
/*    */   }
/*    */   
/*    */   public boolean hasLeftPantsEnabled() {
/* 32 */     return ((this.raw >> 4 & 0x1) == 1);
/*    */   }
/*    */   
/*    */   public boolean hasRightPantsEnabled() {
/* 36 */     return ((this.raw >> 5 & 0x1) == 1);
/*    */   }
/*    */   
/*    */   public boolean hasHatsEnabled() {
/* 40 */     return ((this.raw >> 6 & 0x1) == 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRaw() {
/* 45 */     return this.raw;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object o) {
/* 50 */     if (this == o) return true; 
/* 51 */     if (o == null || getClass() != o.getClass()) return false; 
/* 52 */     PaperSkinParts that = (PaperSkinParts)o;
/* 53 */     return (this.raw == that.raw);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 58 */     return Objects.hashCode(new Object[] { Integer.valueOf(this.raw) });
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 63 */     return (new StringJoiner(", ", PaperSkinParts.class.getSimpleName() + "[", "]"))
/* 64 */       .add("raw=" + this.raw)
/* 65 */       .add("cape=" + hasCapeEnabled())
/* 66 */       .add("jacket=" + hasJacketEnabled())
/* 67 */       .add("leftSleeve=" + hasLeftSleeveEnabled())
/* 68 */       .add("rightSleeve=" + hasRightSleeveEnabled())
/* 69 */       .add("leftPants=" + hasLeftPantsEnabled())
/* 70 */       .add("rightPants=" + hasRightPantsEnabled())
/* 71 */       .add("hats=" + hasHatsEnabled())
/* 72 */       .toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\PaperSkinParts.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */