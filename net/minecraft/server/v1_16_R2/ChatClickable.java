/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.Map;
/*    */ import java.util.stream.Collectors;
/*    */ 
/*    */ public class ChatClickable {
/*    */   private final EnumClickAction a;
/*    */   private final String b;
/*    */   
/*    */   public ChatClickable(EnumClickAction var0, String var1) {
/* 12 */     this.a = var0;
/* 13 */     this.b = var1;
/*    */   }
/*    */   
/*    */   public EnumClickAction a() {
/* 17 */     return this.a;
/*    */   }
/*    */   
/*    */   public String b() {
/* 21 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object var0) {
/* 26 */     if (this == var0) {
/* 27 */       return true;
/*    */     }
/* 29 */     if (var0 == null || getClass() != var0.getClass()) {
/* 30 */       return false;
/*    */     }
/*    */     
/* 33 */     ChatClickable var1 = (ChatClickable)var0;
/*    */     
/* 35 */     if (this.a != var1.a) {
/* 36 */       return false;
/*    */     }
/* 38 */     if ((this.b != null) ? !this.b.equals(var1.b) : (var1.b != null)) {
/* 39 */       return false;
/*    */     }
/*    */     
/* 42 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 47 */     return "ClickEvent{action=" + this.a + ", value='" + this.b + '\'' + '}';
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 55 */     int var0 = this.a.hashCode();
/* 56 */     var0 = 31 * var0 + ((this.b != null) ? this.b.hashCode() : 0);
/* 57 */     return var0;
/*    */   }
/*    */   
/*    */   public enum EnumClickAction { private static final Map<String, EnumClickAction> g;
/* 61 */     OPEN_URL("open_url", true),
/* 62 */     OPEN_FILE("open_file", false),
/* 63 */     RUN_COMMAND("run_command", true),
/* 64 */     SUGGEST_COMMAND("suggest_command", true),
/* 65 */     CHANGE_PAGE("change_page", true),
/* 66 */     COPY_TO_CLIPBOARD("copy_to_clipboard", true);
/*    */     
/*    */     static {
/* 69 */       g = (Map<String, EnumClickAction>)Arrays.<EnumClickAction>stream(values()).collect(Collectors.toMap(EnumClickAction::b, var0 -> var0));
/*    */     }
/*    */     private final boolean h;
/*    */     private final String i;
/*    */     
/*    */     EnumClickAction(String var2, boolean var3) {
/* 75 */       this.i = var2;
/* 76 */       this.h = var3;
/*    */     }
/*    */     
/*    */     public boolean a() {
/* 80 */       return this.h;
/*    */     }
/*    */     
/*    */     public String b() {
/* 84 */       return this.i;
/*    */     }
/*    */     
/*    */     public static EnumClickAction a(String var0) {
/* 88 */       return g.get(var0);
/*    */     } }
/*    */ 
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChatClickable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */