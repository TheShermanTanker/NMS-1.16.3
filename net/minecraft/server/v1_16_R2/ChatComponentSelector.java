/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import javax.annotation.Nullable;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChatComponentSelector
/*    */   extends ChatBaseComponent
/*    */   implements ChatComponentContextual
/*    */ {
/* 15 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   private final String e;
/*    */   @Nullable
/*    */   private final EntitySelector f;
/*    */   
/*    */   public ChatComponentSelector(String var0) {
/* 22 */     this.e = var0;
/*    */     
/* 24 */     EntitySelector var1 = null;
/*    */     try {
/* 26 */       ArgumentParserSelector var2 = new ArgumentParserSelector(new StringReader(var0));
/* 27 */       var1 = var2.parse();
/* 28 */     } catch (CommandSyntaxException var2) {
/* 29 */       LOGGER.warn("Invalid selector component: {}", var0, var2.getMessage());
/*    */     } 
/* 31 */     this.f = var1;
/*    */   }
/*    */   
/*    */   public String h() {
/* 35 */     return this.e;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IChatMutableComponent a(@Nullable CommandListenerWrapper var0, @Nullable Entity var1, int var2) throws CommandSyntaxException {
/* 45 */     if (var0 == null || this.f == null) {
/* 46 */       return new ChatComponentText("");
/*    */     }
/* 48 */     return EntitySelector.a(this.f.getEntities(var0));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getText() {
/* 54 */     return this.e;
/*    */   }
/*    */ 
/*    */   
/*    */   public ChatComponentSelector g() {
/* 59 */     return new ChatComponentSelector(this.e);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object var0) {
/* 64 */     if (this == var0) {
/* 65 */       return true;
/*    */     }
/*    */     
/* 68 */     if (var0 instanceof ChatComponentSelector) {
/* 69 */       ChatComponentSelector var1 = (ChatComponentSelector)var0;
/* 70 */       return (this.e.equals(var1.e) && super.equals(var0));
/*    */     } 
/*    */     
/* 73 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 78 */     return "SelectorComponent{pattern='" + this.e + '\'' + ", siblings=" + this.siblings + ", style=" + 
/*    */ 
/*    */       
/* 81 */       getChatModifier() + '}';
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChatComponentSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */