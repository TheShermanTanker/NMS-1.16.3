/*     */ package net.md_5.bungee.api.chat;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import net.md_5.bungee.api.ChatColor;
/*     */ 
/*     */ 
/*     */ public final class TextComponent
/*     */   extends BaseComponent
/*     */ {
/*     */   public void setText(String text) {
/*  14 */     this.text = text;
/*  15 */   } public TextComponent(String text) { this.text = text; }
/*  16 */   public boolean equals(Object o) { if (o == this) return true;  if (!(o instanceof TextComponent)) return false;  TextComponent other = (TextComponent)o; if (!other.canEqual(this)) return false;  if (!super.equals(o)) return false;  Object this$text = getText(), other$text = other.getText(); return !((this$text == null) ? (other$text != null) : !this$text.equals(other$text)); } protected boolean canEqual(Object other) { return other instanceof TextComponent; } public int hashCode() { int PRIME = 59; result = super.hashCode(); Object $text = getText(); return result * 59 + (($text == null) ? 43 : $text.hashCode()); }
/*     */ 
/*     */ 
/*     */   
/*  20 */   private static final Pattern url = Pattern.compile("^(?:(https?)://)?([-\\w_\\.]{2,}\\.[a-z]{2,4})(/\\S*)?$");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String text;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BaseComponent[] fromLegacyText(String message) {
/*  32 */     return fromLegacyText(message, ChatColor.WHITE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BaseComponent[] fromLegacyText(String message, ChatColor defaultColor) {
/*  47 */     ArrayList<BaseComponent> components = new ArrayList<>();
/*  48 */     StringBuilder builder = new StringBuilder();
/*  49 */     TextComponent component = new TextComponent();
/*  50 */     Matcher matcher = url.matcher(message);
/*     */     
/*  52 */     for (int i = 0; i < message.length(); i++) {
/*     */       
/*  54 */       char c = message.charAt(i);
/*  55 */       if (c == '§') {
/*     */         ChatColor format;
/*  57 */         if (++i >= message.length()) {
/*     */           break;
/*     */         }
/*     */         
/*  61 */         c = message.charAt(i);
/*  62 */         if (c >= 'A' && c <= 'Z')
/*     */         {
/*  64 */           c = (char)(c + 32);
/*     */         }
/*     */         
/*  67 */         if (c == 'x' && i + 12 < message.length()) {
/*     */           
/*  69 */           StringBuilder hex = new StringBuilder("#");
/*  70 */           for (int j = 0; j < 6; j++)
/*     */           {
/*  72 */             hex.append(message.charAt(i + 2 + j * 2));
/*     */           }
/*     */           
/*     */           try {
/*  76 */             format = ChatColor.of(hex.toString());
/*  77 */           } catch (IllegalArgumentException ex) {
/*     */             
/*  79 */             format = null;
/*     */           } 
/*     */           
/*  82 */           i += 12;
/*     */         } else {
/*     */           
/*  85 */           format = ChatColor.getByChar(c);
/*     */         } 
/*  87 */         if (format != null) {
/*     */ 
/*     */ 
/*     */           
/*  91 */           if (builder.length() > 0) {
/*     */             
/*  93 */             TextComponent old = component;
/*  94 */             component = new TextComponent(old);
/*  95 */             old.setText(builder.toString());
/*  96 */             builder = new StringBuilder();
/*  97 */             components.add(old);
/*     */           } 
/*  99 */           if (format == ChatColor.BOLD) {
/*     */             
/* 101 */             component.setBold(Boolean.valueOf(true));
/* 102 */           } else if (format == ChatColor.ITALIC) {
/*     */             
/* 104 */             component.setItalic(Boolean.valueOf(true));
/* 105 */           } else if (format == ChatColor.UNDERLINE) {
/*     */             
/* 107 */             component.setUnderlined(Boolean.valueOf(true));
/* 108 */           } else if (format == ChatColor.STRIKETHROUGH) {
/*     */             
/* 110 */             component.setStrikethrough(Boolean.valueOf(true));
/* 111 */           } else if (format == ChatColor.MAGIC) {
/*     */             
/* 113 */             component.setObfuscated(Boolean.valueOf(true));
/* 114 */           } else if (format == ChatColor.RESET) {
/*     */             
/* 116 */             format = defaultColor;
/* 117 */             component = new TextComponent();
/* 118 */             component.setColor(format);
/*     */           } else {
/*     */             
/* 121 */             component = new TextComponent();
/* 122 */             component.setColor(format);
/*     */           } 
/*     */         } 
/*     */       } else {
/* 126 */         int pos = message.indexOf(' ', i);
/* 127 */         if (pos == -1)
/*     */         {
/* 129 */           pos = message.length();
/*     */         }
/* 131 */         if (matcher.region(i, pos).find()) {
/*     */ 
/*     */           
/* 134 */           if (builder.length() > 0) {
/*     */             
/* 136 */             TextComponent textComponent = component;
/* 137 */             component = new TextComponent(textComponent);
/* 138 */             textComponent.setText(builder.toString());
/* 139 */             builder = new StringBuilder();
/* 140 */             components.add(textComponent);
/*     */           } 
/*     */           
/* 143 */           TextComponent old = component;
/* 144 */           component = new TextComponent(old);
/* 145 */           String urlString = message.substring(i, pos);
/* 146 */           component.setText(urlString);
/* 147 */           component.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, 
/* 148 */                 urlString.startsWith("http") ? urlString : ("http://" + urlString)));
/* 149 */           components.add(component);
/* 150 */           i += pos - i - 1;
/* 151 */           component = old;
/*     */         } else {
/*     */           
/* 154 */           builder.append(c);
/*     */         } 
/*     */       } 
/* 157 */     }  component.setText(builder.toString());
/* 158 */     components.add(component);
/*     */     
/* 160 */     return components.<BaseComponent>toArray(new BaseComponent[components.size()]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getText() {
/* 166 */     return this.text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TextComponent() {
/* 173 */     this.text = "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TextComponent(TextComponent textComponent) {
/* 184 */     super(textComponent);
/* 185 */     setText(textComponent.getText());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TextComponent(BaseComponent... extras) {
/* 196 */     this();
/* 197 */     if (extras.length == 0) {
/*     */       return;
/*     */     }
/*     */     
/* 201 */     setExtra(new ArrayList<>(Arrays.asList(extras)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TextComponent duplicate() {
/* 212 */     return new TextComponent(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void toPlainText(StringBuilder builder) {
/* 218 */     builder.append(this.text);
/* 219 */     super.toPlainText(builder);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void toLegacyText(StringBuilder builder) {
/* 225 */     addFormat(builder);
/* 226 */     builder.append(this.text);
/* 227 */     super.toLegacyText(builder);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 233 */     return String.format("TextComponent{text=%s, %s}", new Object[] { this.text, super.toString() });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\md_5\bungee\api\chat\TextComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */