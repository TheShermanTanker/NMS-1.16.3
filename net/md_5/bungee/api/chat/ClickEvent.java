/*    */ package net.md_5.bungee.api.chat;
/*    */ 
/*    */ public final class ClickEvent
/*    */ {
/*    */   private final Action action;
/*    */   private final String value;
/*    */   
/*    */   public String toString() {
/*  9 */     return "ClickEvent(action=" + getAction() + ", value=" + getValue() + ")"; }
/* 10 */   public boolean equals(Object o) { if (o == this) return true;  if (!(o instanceof ClickEvent)) return false;  ClickEvent other = (ClickEvent)o; Object this$action = getAction(), other$action = other.getAction(); if ((this$action == null) ? (other$action != null) : !this$action.equals(other$action)) return false;  Object this$value = getValue(), other$value = other.getValue(); return !((this$value == null) ? (other$value != null) : !this$value.equals(other$value)); } public int hashCode() { int PRIME = 59; result = 1; Object $action = getAction(); result = result * 59 + (($action == null) ? 43 : $action.hashCode()); Object $value = getValue(); return result * 59 + (($value == null) ? 43 : $value.hashCode()); } public ClickEvent(Action action, String value) {
/* 11 */     this.action = action; this.value = value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Action getAction() {
/* 18 */     return this.action;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getValue() {
/* 24 */     return this.value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public enum Action
/*    */   {
/* 33 */     OPEN_URL,
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 38 */     OPEN_FILE,
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 43 */     RUN_COMMAND,
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 49 */     SUGGEST_COMMAND,
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 54 */     CHANGE_PAGE,
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 60 */     COPY_TO_CLIPBOARD;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\md_5\bungee\api\chat\ClickEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */