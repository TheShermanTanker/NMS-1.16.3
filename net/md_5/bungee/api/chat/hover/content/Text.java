/*    */ package net.md_5.bungee.api.chat.hover.content;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import net.md_5.bungee.api.chat.BaseComponent;
/*    */ import net.md_5.bungee.api.chat.HoverEvent;
/*    */ 
/*    */ public class Text
/*    */   extends Content {
/*    */   public String toString() {
/* 10 */     return "Text(value=" + getValue() + ")";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private final Object value;
/*    */ 
/*    */   
/*    */   public Object getValue() {
/* 19 */     return this.value;
/*    */   }
/*    */   
/*    */   public Text(BaseComponent[] value) {
/* 23 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public Text(String value) {
/* 28 */     this.value = value;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public HoverEvent.Action requiredAction() {
/* 34 */     return HoverEvent.Action.SHOW_TEXT;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object o) {
/* 40 */     if (this.value instanceof BaseComponent[])
/*    */     {
/* 42 */       return (o instanceof Text && ((Text)o).value instanceof BaseComponent[] && 
/*    */         
/* 44 */         Arrays.equals((Object[])this.value, (Object[])((Text)o).value));
/*    */     }
/*    */     
/* 47 */     return this.value.equals(o);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 54 */     return (this.value instanceof BaseComponent[]) ? Arrays.hashCode((Object[])this.value) : this.value.hashCode();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\md_5\bungee\api\chat\hover\content\Text.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */