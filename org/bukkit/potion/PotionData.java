/*    */ package org.bukkit.potion;
/*    */ 
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ public final class PotionData
/*    */ {
/*    */   private final PotionType type;
/*    */   private final boolean extended;
/*    */   private final boolean upgraded;
/*    */   
/*    */   public PotionData(@NotNull PotionType type, boolean extended, boolean upgraded) {
/* 23 */     Validate.notNull(type, "Potion Type must not be null");
/* 24 */     Validate.isTrue((!upgraded || type.isUpgradeable()), "Potion Type is not upgradable");
/* 25 */     Validate.isTrue((!extended || type.isExtendable()), "Potion Type is not extendable");
/* 26 */     Validate.isTrue((!upgraded || !extended), "Potion cannot be both extended and upgraded");
/* 27 */     this.type = type;
/* 28 */     this.extended = extended;
/* 29 */     this.upgraded = upgraded;
/*    */   }
/*    */   
/*    */   public PotionData(@NotNull PotionType type) {
/* 33 */     this(type, false, false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public PotionType getType() {
/* 44 */     return this.type;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isUpgraded() {
/* 54 */     return this.upgraded;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isExtended() {
/* 64 */     return this.extended;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 69 */     int hash = 7;
/* 70 */     hash = 23 * hash + ((this.type != null) ? this.type.hashCode() : 0);
/* 71 */     hash = 23 * hash + (this.extended ? 1 : 0);
/* 72 */     hash = 23 * hash + (this.upgraded ? 1 : 0);
/* 73 */     return hash;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 78 */     if (this == obj) {
/* 79 */       return true;
/*    */     }
/* 81 */     if (obj == null || getClass() != obj.getClass()) {
/* 82 */       return false;
/*    */     }
/* 84 */     PotionData other = (PotionData)obj;
/* 85 */     return (this.upgraded == other.upgraded && this.extended == other.extended && this.type == other.type);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\potion\PotionData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */