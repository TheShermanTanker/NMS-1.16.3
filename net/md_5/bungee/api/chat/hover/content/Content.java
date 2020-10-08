/*    */ package net.md_5.bungee.api.chat.hover.content;
/*    */ 
/*    */ import net.md_5.bungee.api.chat.HoverEvent;
/*    */ 
/*    */ public abstract class Content {
/*    */   public String toString() {
/*  7 */     return "Content()";
/*  8 */   } public boolean equals(Object o) { if (o == this) return true;  if (!(o instanceof Content)) return false;  Content other = (Content)o; return !!other.canEqual(this); } protected boolean canEqual(Object other) { return other instanceof Content; } public int hashCode() { int result = 1; return 1; }
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
/*    */ 
/*    */   
/*    */   public void assertAction(HoverEvent.Action input) throws UnsupportedOperationException {
/* 27 */     if (input != requiredAction())
/*    */     {
/* 29 */       throw new UnsupportedOperationException("Action " + input + " not compatible! Expected " + requiredAction());
/*    */     }
/*    */   }
/*    */   
/*    */   public abstract HoverEvent.Action requiredAction();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\md_5\bungee\api\chat\hover\content\Content.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */