/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ import java.util.function.Function;
/*    */ import java.util.stream.Stream;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public final class ChatHexColor {
/*    */   private static final Map<EnumChatFormat, ChatHexColor> a;
/*    */   
/*    */   static {
/* 12 */     a = (Map<EnumChatFormat, ChatHexColor>)Stream.<EnumChatFormat>of(EnumChatFormat.values()).filter(EnumChatFormat::d).collect(ImmutableMap.toImmutableMap(Function.identity(), enumchatformat -> new ChatHexColor(enumchatformat.e().intValue(), enumchatformat.f(), enumchatformat)));
/*    */ 
/*    */     
/* 15 */     b = (Map<String, ChatHexColor>)a.values().stream().collect(ImmutableMap.toImmutableMap(chathexcolor -> chathexcolor.name, 
/*    */           
/* 17 */           Function.identity()));
/*    */   }
/*    */   private static final Map<String, ChatHexColor> b; private final int rgb;
/*    */   @Nullable
/*    */   public final String name;
/*    */   @Nullable
/*    */   public final EnumChatFormat format;
/*    */   
/*    */   private ChatHexColor(int i, String s, EnumChatFormat format) {
/* 26 */     this.rgb = i;
/* 27 */     this.name = s;
/* 28 */     this.format = format;
/*    */   }
/*    */   
/*    */   private ChatHexColor(int i) {
/* 32 */     this.rgb = i;
/* 33 */     this.name = null;
/* 34 */     this.format = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String b() {
/* 39 */     return (this.name != null) ? this.name : c();
/*    */   }
/*    */   
/*    */   private String c() {
/* 43 */     return String.format("#%06X", new Object[] { Integer.valueOf(this.rgb) });
/*    */   }
/*    */   
/*    */   public boolean equals(Object object) {
/* 47 */     if (this == object)
/* 48 */       return true; 
/* 49 */     if (object != null && getClass() == object.getClass()) {
/* 50 */       ChatHexColor chathexcolor = (ChatHexColor)object;
/*    */       
/* 52 */       return (this.rgb == chathexcolor.rgb);
/*    */     } 
/* 54 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 59 */     return Objects.hash(new Object[] { Integer.valueOf(this.rgb), this.name });
/*    */   }
/*    */   
/*    */   public String toString() {
/* 63 */     return (this.name != null) ? this.name : c();
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public static ChatHexColor a(EnumChatFormat enumchatformat) {
/* 68 */     return a.get(enumchatformat);
/*    */   }
/*    */   
/*    */   public static ChatHexColor a(int i) {
/* 72 */     return new ChatHexColor(i);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public static ChatHexColor a(String s) {
/* 77 */     if (s.startsWith("#")) {
/*    */       try {
/* 79 */         int i = Integer.parseInt(s.substring(1), 16);
/*    */         
/* 81 */         return a(i);
/* 82 */       } catch (NumberFormatException numberformatexception) {
/* 83 */         return null;
/*    */       } 
/*    */     }
/* 86 */     return b.get(s);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChatHexColor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */