/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ public class ChatComponentKeybind
/*    */   extends ChatBaseComponent {
/*    */   private static Function<String, Supplier<IChatBaseComponent>> d = var0 -> ();
/*    */   private final String e;
/*    */   private Supplier<IChatBaseComponent> f;
/*    */   
/*    */   public ChatComponentKeybind(String var0) {
/* 14 */     this.e = var0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private IChatBaseComponent j() {
/* 22 */     if (this.f == null) {
/* 23 */       this.f = d.apply(this.e);
/*    */     }
/*    */     
/* 26 */     return this.f.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public <T> Optional<T> b(IChatFormatted.a<T> var0) {
/* 31 */     return j().a(var0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ChatComponentKeybind g() {
/* 41 */     return new ChatComponentKeybind(this.e);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object var0) {
/* 46 */     if (this == var0) {
/* 47 */       return true;
/*    */     }
/*    */     
/* 50 */     if (var0 instanceof ChatComponentKeybind) {
/* 51 */       ChatComponentKeybind var1 = (ChatComponentKeybind)var0;
/* 52 */       return (this.e.equals(var1.e) && super.equals(var0));
/*    */     } 
/*    */     
/* 55 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 60 */     return "KeybindComponent{keybind='" + this.e + '\'' + ", siblings=" + this.siblings + ", style=" + 
/*    */ 
/*    */       
/* 63 */       getChatModifier() + '}';
/*    */   }
/*    */ 
/*    */   
/*    */   public String i() {
/* 68 */     return this.e;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChatComponentKeybind.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */