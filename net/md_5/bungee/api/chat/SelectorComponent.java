/*    */ package net.md_5.bungee.api.chat;
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
/*    */ public final class SelectorComponent
/*    */   extends BaseComponent
/*    */ {
/*    */   private String selector;
/*    */   
/*    */   public void setSelector(String selector) {
/* 23 */     this.selector = selector;
/* 24 */   } public String toString() { return "SelectorComponent(selector=" + getSelector() + ")"; }
/* 25 */   public SelectorComponent(String selector) { this.selector = selector; }
/* 26 */   public boolean equals(Object o) { if (o == this) return true;  if (!(o instanceof SelectorComponent)) return false;  SelectorComponent other = (SelectorComponent)o; if (!other.canEqual(this)) return false;  if (!super.equals(o)) return false;  Object this$selector = getSelector(), other$selector = other.getSelector(); return !((this$selector == null) ? (other$selector != null) : !this$selector.equals(other$selector)); } protected boolean canEqual(Object other) { return other instanceof SelectorComponent; } public int hashCode() { int PRIME = 59; result = super.hashCode(); Object $selector = getSelector(); return result * 59 + (($selector == null) ? 43 : $selector.hashCode()); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getSelector() {
/* 34 */     return this.selector;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SelectorComponent(SelectorComponent original) {
/* 43 */     super(original);
/* 44 */     setSelector(original.getSelector());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public SelectorComponent duplicate() {
/* 50 */     return new SelectorComponent(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void toPlainText(StringBuilder builder) {
/* 56 */     builder.append(this.selector);
/* 57 */     super.toPlainText(builder);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void toLegacyText(StringBuilder builder) {
/* 63 */     addFormat(builder);
/* 64 */     builder.append(this.selector);
/* 65 */     super.toLegacyText(builder);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\md_5\bungee\api\chat\SelectorComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */