/*    */ package net.md_5.bungee.api.chat;
/*    */ 
/*    */ 
/*    */ public final class KeybindComponent
/*    */   extends BaseComponent
/*    */ {
/*    */   private String keybind;
/*    */   
/*    */   public void setKeybind(String keybind) {
/* 10 */     this.keybind = keybind; } public KeybindComponent() {} public String toString() {
/* 11 */     return "KeybindComponent(keybind=" + getKeybind() + ")";
/*    */   } public boolean equals(Object o) {
/* 13 */     if (o == this) return true;  if (!(o instanceof KeybindComponent)) return false;  KeybindComponent other = (KeybindComponent)o; if (!other.canEqual(this)) return false;  if (!super.equals(o)) return false;  Object this$keybind = getKeybind(), other$keybind = other.getKeybind(); return !((this$keybind == null) ? (other$keybind != null) : !this$keybind.equals(other$keybind)); } protected boolean canEqual(Object other) { return other instanceof KeybindComponent; } public int hashCode() { int PRIME = 59; result = super.hashCode(); Object $keybind = getKeybind(); return result * 59 + (($keybind == null) ? 43 : $keybind.hashCode()); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getKeybind() {
/* 22 */     return this.keybind;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public KeybindComponent(KeybindComponent original) {
/* 31 */     super(original);
/* 32 */     setKeybind(original.getKeybind());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public KeybindComponent(String keybind) {
/* 43 */     setKeybind(keybind);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public KeybindComponent duplicate() {
/* 49 */     return new KeybindComponent(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void toPlainText(StringBuilder builder) {
/* 55 */     builder.append(getKeybind());
/* 56 */     super.toPlainText(builder);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void toLegacyText(StringBuilder builder) {
/* 62 */     addFormat(builder);
/* 63 */     builder.append(getKeybind());
/* 64 */     super.toLegacyText(builder);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\md_5\bungee\api\chat\KeybindComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */