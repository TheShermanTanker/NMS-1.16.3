/*     */ package org.bukkit.event.inventory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum ClickType
/*     */ {
/*  11 */   LEFT,
/*     */ 
/*     */ 
/*     */   
/*  15 */   SHIFT_LEFT,
/*     */ 
/*     */ 
/*     */   
/*  19 */   RIGHT,
/*     */ 
/*     */ 
/*     */   
/*  23 */   SHIFT_RIGHT,
/*     */ 
/*     */ 
/*     */   
/*  27 */   WINDOW_BORDER_LEFT,
/*     */ 
/*     */ 
/*     */   
/*  31 */   WINDOW_BORDER_RIGHT,
/*     */ 
/*     */ 
/*     */   
/*  35 */   MIDDLE,
/*     */ 
/*     */ 
/*     */   
/*  39 */   NUMBER_KEY,
/*     */ 
/*     */ 
/*     */   
/*  43 */   DOUBLE_CLICK,
/*     */ 
/*     */ 
/*     */   
/*  47 */   DROP,
/*     */ 
/*     */ 
/*     */   
/*  51 */   CONTROL_DROP,
/*     */ 
/*     */ 
/*     */   
/*  55 */   CREATIVE,
/*     */ 
/*     */ 
/*     */   
/*  59 */   SWAP_OFFHAND,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   UNKNOWN;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isKeyboardClick() {
/*  78 */     return (this == NUMBER_KEY || this == DROP || this == CONTROL_DROP);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCreativeAction() {
/*  89 */     return (this == MIDDLE || this == CREATIVE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRightClick() {
/*  98 */     return (this == RIGHT || this == SHIFT_RIGHT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLeftClick() {
/* 107 */     return (this == LEFT || this == SHIFT_LEFT || this == DOUBLE_CLICK || this == CREATIVE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isShiftClick() {
/* 117 */     return (this == SHIFT_LEFT || this == SHIFT_RIGHT || this == CONTROL_DROP);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\inventory\ClickType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */