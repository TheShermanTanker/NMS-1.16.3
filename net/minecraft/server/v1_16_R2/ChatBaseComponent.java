/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ChatBaseComponent
/*    */   implements IChatMutableComponent
/*    */ {
/* 12 */   protected final List<IChatBaseComponent> siblings = Lists.newArrayList();
/* 13 */   private FormattedString d = FormattedString.a;
/*    */ 
/*    */ 
/*    */   
/* 17 */   private ChatModifier f = ChatModifier.a;
/*    */ 
/*    */   
/*    */   public IChatMutableComponent addSibling(IChatBaseComponent var0) {
/* 21 */     this.siblings.add(var0);
/* 22 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getText() {
/* 27 */     return "";
/*    */   }
/*    */ 
/*    */   
/*    */   public List<IChatBaseComponent> getSiblings() {
/* 32 */     return this.siblings;
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatMutableComponent setChatModifier(ChatModifier var0) {
/* 37 */     this.f = var0;
/* 38 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public ChatModifier getChatModifier() {
/* 43 */     return this.f;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final IChatMutableComponent mutableCopy() {
/* 51 */     ChatBaseComponent var0 = g();
/* 52 */     var0.siblings.addAll(this.siblings);
/* 53 */     var0.setChatModifier(this.f);
/* 54 */     return var0;
/*    */   }
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
/*    */   public boolean equals(Object var0) {
/* 69 */     if (this == var0) {
/* 70 */       return true;
/*    */     }
/*    */     
/* 73 */     if (var0 instanceof ChatBaseComponent) {
/* 74 */       ChatBaseComponent var1 = (ChatBaseComponent)var0;
/* 75 */       return (this.siblings.equals(var1.siblings) && Objects.equals(getChatModifier(), var1.getChatModifier()));
/*    */     } 
/*    */     
/* 78 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 83 */     return Objects.hash(new Object[] { getChatModifier(), this.siblings });
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 88 */     return "BaseComponent{style=" + this.f + ", siblings=" + this.siblings + '}';
/*    */   }
/*    */   
/*    */   public abstract ChatBaseComponent g();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChatBaseComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */