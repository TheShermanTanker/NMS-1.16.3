/*     */ package net.md_5.bungee.api.chat;
/*     */ 
/*     */ import net.md_5.bungee.api.ChatColor;
/*     */ 
/*     */ public abstract class BaseComponent {
/*     */   BaseComponent parent;
/*     */   private ChatColor color;
/*     */   private String font;
/*     */   private Boolean bold;
/*     */   private Boolean italic;
/*     */   private Boolean underlined;
/*     */   
/*  13 */   public void setColor(ChatColor color) { this.color = color; } private Boolean strikethrough; private Boolean obfuscated; private String insertion; private List<BaseComponent> extra; private ClickEvent clickEvent; private HoverEvent hoverEvent; public void setFont(String font) { this.font = font; } public void setBold(Boolean bold) { this.bold = bold; } public void setItalic(Boolean italic) { this.italic = italic; } public void setUnderlined(Boolean underlined) { this.underlined = underlined; } public void setStrikethrough(Boolean strikethrough) { this.strikethrough = strikethrough; } public void setObfuscated(Boolean obfuscated) { this.obfuscated = obfuscated; } public void setInsertion(String insertion) { this.insertion = insertion; } public void setClickEvent(ClickEvent clickEvent) { this.clickEvent = clickEvent; } public void setHoverEvent(HoverEvent hoverEvent) { this.hoverEvent = hoverEvent; }
/*  14 */   public String toString() { return "BaseComponent(color=" + getColor() + ", font=" + getFont() + ", bold=" + this.bold + ", italic=" + this.italic + ", underlined=" + this.underlined + ", strikethrough=" + this.strikethrough + ", obfuscated=" + this.obfuscated + ", insertion=" + getInsertion() + ", extra=" + getExtra() + ", clickEvent=" + getClickEvent() + ", hoverEvent=" + getHoverEvent() + ")"; }
/*  15 */   public boolean equals(Object o) { if (o == this) return true;  if (!(o instanceof BaseComponent)) return false;  BaseComponent other = (BaseComponent)o; if (!other.canEqual(this)) return false;  Object this$color = getColor(), other$color = other.getColor(); if ((this$color == null) ? (other$color != null) : !this$color.equals(other$color)) return false;  Object this$font = getFont(), other$font = other.getFont(); if ((this$font == null) ? (other$font != null) : !this$font.equals(other$font)) return false;  Object this$bold = this.bold, other$bold = other.bold; if ((this$bold == null) ? (other$bold != null) : !this$bold.equals(other$bold)) return false;  Object this$italic = this.italic, other$italic = other.italic; if ((this$italic == null) ? (other$italic != null) : !this$italic.equals(other$italic)) return false;  Object this$underlined = this.underlined, other$underlined = other.underlined; if ((this$underlined == null) ? (other$underlined != null) : !this$underlined.equals(other$underlined)) return false;  Object this$strikethrough = this.strikethrough, other$strikethrough = other.strikethrough; if ((this$strikethrough == null) ? (other$strikethrough != null) : !this$strikethrough.equals(other$strikethrough)) return false;  Object this$obfuscated = this.obfuscated, other$obfuscated = other.obfuscated; if ((this$obfuscated == null) ? (other$obfuscated != null) : !this$obfuscated.equals(other$obfuscated)) return false;  Object this$insertion = getInsertion(), other$insertion = other.getInsertion(); if ((this$insertion == null) ? (other$insertion != null) : !this$insertion.equals(other$insertion)) return false;  Object<BaseComponent> this$extra = (Object<BaseComponent>)getExtra(), other$extra = (Object<BaseComponent>)other.getExtra(); if ((this$extra == null) ? (other$extra != null) : !this$extra.equals(other$extra)) return false;  Object this$clickEvent = getClickEvent(), other$clickEvent = other.getClickEvent(); if ((this$clickEvent == null) ? (other$clickEvent != null) : !this$clickEvent.equals(other$clickEvent)) return false;  Object this$hoverEvent = getHoverEvent(), other$hoverEvent = other.getHoverEvent(); return !((this$hoverEvent == null) ? (other$hoverEvent != null) : !this$hoverEvent.equals(other$hoverEvent)); } protected boolean canEqual(Object other) { return other instanceof BaseComponent; } public int hashCode() { int PRIME = 59; result = 1; Object $color = getColor(); result = result * 59 + (($color == null) ? 43 : $color.hashCode()); Object $font = getFont(); result = result * 59 + (($font == null) ? 43 : $font.hashCode()); Object $bold = this.bold; result = result * 59 + (($bold == null) ? 43 : $bold.hashCode()); Object $italic = this.italic; result = result * 59 + (($italic == null) ? 43 : $italic.hashCode()); Object $underlined = this.underlined; result = result * 59 + (($underlined == null) ? 43 : $underlined.hashCode()); Object $strikethrough = this.strikethrough; result = result * 59 + (($strikethrough == null) ? 43 : $strikethrough.hashCode()); Object $obfuscated = this.obfuscated; result = result * 59 + (($obfuscated == null) ? 43 : $obfuscated.hashCode()); Object $insertion = getInsertion(); result = result * 59 + (($insertion == null) ? 43 : $insertion.hashCode()); Object<BaseComponent> $extra = (Object<BaseComponent>)getExtra(); result = result * 59 + (($extra == null) ? 43 : $extra.hashCode()); Object $clickEvent = getClickEvent(); result = result * 59 + (($clickEvent == null) ? 43 : $clickEvent.hashCode()); Object $hoverEvent = getHoverEvent(); return result * 59 + (($hoverEvent == null) ? 43 : $hoverEvent.hashCode()); }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getInsertion() {
/*  59 */     return this.insertion;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<BaseComponent> getExtra() {
/*  65 */     return this.extra;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClickEvent getClickEvent() {
/*  72 */     return this.clickEvent;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public HoverEvent getHoverEvent() {
/*  78 */     return this.hoverEvent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public BaseComponent() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   BaseComponent(BaseComponent old) {
/*  93 */     copyFormatting(old, ComponentBuilder.FormatRetention.ALL, true);
/*     */     
/*  95 */     if (old.getExtra() != null)
/*     */     {
/*  97 */       for (BaseComponent extra : old.getExtra())
/*     */       {
/*  99 */         addExtra(extra.duplicate());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void copyFormatting(BaseComponent component) {
/* 112 */     copyFormatting(component, ComponentBuilder.FormatRetention.ALL, true);
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
/*     */   public void copyFormatting(BaseComponent component, boolean replace) {
/* 124 */     copyFormatting(component, ComponentBuilder.FormatRetention.ALL, replace);
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
/*     */   public void copyFormatting(BaseComponent component, ComponentBuilder.FormatRetention retention, boolean replace) {
/* 137 */     if (retention == ComponentBuilder.FormatRetention.EVENTS || retention == ComponentBuilder.FormatRetention.ALL) {
/*     */       
/* 139 */       if (replace || this.clickEvent == null)
/*     */       {
/* 141 */         setClickEvent(component.getClickEvent());
/*     */       }
/* 143 */       if (replace || this.hoverEvent == null)
/*     */       {
/* 145 */         setHoverEvent(component.getHoverEvent());
/*     */       }
/*     */     } 
/* 148 */     if (retention == ComponentBuilder.FormatRetention.FORMATTING || retention == ComponentBuilder.FormatRetention.ALL) {
/*     */       
/* 150 */       if (replace || this.color == null)
/*     */       {
/* 152 */         setColor(component.getColorRaw());
/*     */       }
/* 154 */       if (replace || this.font == null)
/*     */       {
/* 156 */         setFont(component.getFontRaw());
/*     */       }
/* 158 */       if (replace || this.bold == null)
/*     */       {
/* 160 */         setBold(component.isBoldRaw());
/*     */       }
/* 162 */       if (replace || this.italic == null)
/*     */       {
/* 164 */         setItalic(component.isItalicRaw());
/*     */       }
/* 166 */       if (replace || this.underlined == null)
/*     */       {
/* 168 */         setUnderlined(component.isUnderlinedRaw());
/*     */       }
/* 170 */       if (replace || this.strikethrough == null)
/*     */       {
/* 172 */         setStrikethrough(component.isStrikethroughRaw());
/*     */       }
/* 174 */       if (replace || this.obfuscated == null)
/*     */       {
/* 176 */         setObfuscated(component.isObfuscatedRaw());
/*     */       }
/* 178 */       if (replace || this.insertion == null)
/*     */       {
/* 180 */         setInsertion(component.getInsertion());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void retain(ComponentBuilder.FormatRetention retention) {
/* 192 */     if (retention == ComponentBuilder.FormatRetention.FORMATTING || retention == ComponentBuilder.FormatRetention.NONE) {
/*     */       
/* 194 */       setClickEvent(null);
/* 195 */       setHoverEvent(null);
/*     */     } 
/* 197 */     if (retention == ComponentBuilder.FormatRetention.EVENTS || retention == ComponentBuilder.FormatRetention.NONE) {
/*     */       
/* 199 */       setColor(null);
/* 200 */       setBold(null);
/* 201 */       setItalic(null);
/* 202 */       setUnderlined(null);
/* 203 */       setStrikethrough(null);
/* 204 */       setObfuscated(null);
/* 205 */       setInsertion(null);
/*     */     } 
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
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public BaseComponent duplicateWithoutFormatting() {
/* 225 */     BaseComponent component = duplicate();
/* 226 */     component.retain(ComponentBuilder.FormatRetention.NONE);
/* 227 */     return component;
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
/*     */   public static String toLegacyText(BaseComponent... components) {
/* 239 */     StringBuilder builder = new StringBuilder();
/* 240 */     for (BaseComponent msg : components)
/*     */     {
/* 242 */       builder.append(msg.toLegacyText());
/*     */     }
/* 244 */     return builder.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String toPlainText(BaseComponent... components) {
/* 255 */     StringBuilder builder = new StringBuilder();
/* 256 */     for (BaseComponent msg : components)
/*     */     {
/* 258 */       builder.append(msg.toPlainText());
/*     */     }
/* 260 */     return builder.toString();
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
/*     */   public ChatColor getColor() {
/* 272 */     if (this.color == null) {
/*     */       
/* 274 */       if (this.parent == null)
/*     */       {
/* 276 */         return ChatColor.WHITE;
/*     */       }
/* 278 */       return this.parent.getColor();
/*     */     } 
/* 280 */     return this.color;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChatColor getColorRaw() {
/* 291 */     return this.color;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFont() {
/* 302 */     if (this.font == null) {
/*     */       
/* 304 */       if (this.parent == null)
/*     */       {
/* 306 */         return null;
/*     */       }
/* 308 */       return this.parent.getFont();
/*     */     } 
/* 310 */     return this.font;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFontRaw() {
/* 321 */     return this.font;
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
/*     */   public boolean isBold() {
/* 333 */     if (this.bold == null)
/*     */     {
/* 335 */       return (this.parent != null && this.parent.isBold());
/*     */     }
/* 337 */     return this.bold.booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean isBoldRaw() {
/* 348 */     return this.bold;
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
/*     */   public boolean isItalic() {
/* 360 */     if (this.italic == null)
/*     */     {
/* 362 */       return (this.parent != null && this.parent.isItalic());
/*     */     }
/* 364 */     return this.italic.booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean isItalicRaw() {
/* 375 */     return this.italic;
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
/*     */   public boolean isUnderlined() {
/* 387 */     if (this.underlined == null)
/*     */     {
/* 389 */       return (this.parent != null && this.parent.isUnderlined());
/*     */     }
/* 391 */     return this.underlined.booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean isUnderlinedRaw() {
/* 402 */     return this.underlined;
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
/*     */   public boolean isStrikethrough() {
/* 414 */     if (this.strikethrough == null)
/*     */     {
/* 416 */       return (this.parent != null && this.parent.isStrikethrough());
/*     */     }
/* 418 */     return this.strikethrough.booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean isStrikethroughRaw() {
/* 429 */     return this.strikethrough;
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
/*     */   public boolean isObfuscated() {
/* 441 */     if (this.obfuscated == null)
/*     */     {
/* 443 */       return (this.parent != null && this.parent.isObfuscated());
/*     */     }
/* 445 */     return this.obfuscated.booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean isObfuscatedRaw() {
/* 456 */     return this.obfuscated;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExtra(List<BaseComponent> components) {
/* 461 */     for (BaseComponent component : components)
/*     */     {
/* 463 */       component.parent = this;
/*     */     }
/* 465 */     this.extra = components;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addExtra(String text) {
/* 476 */     addExtra(new TextComponent(text));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addExtra(BaseComponent component) {
/* 487 */     if (this.extra == null)
/*     */     {
/* 489 */       this.extra = new ArrayList<>();
/*     */     }
/* 491 */     component.parent = this;
/* 492 */     this.extra.add(component);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasFormatting() {
/* 502 */     return (this.color != null || this.font != null || this.bold != null || this.italic != null || this.underlined != null || this.strikethrough != null || this.obfuscated != null || this.insertion != null || this.hoverEvent != null || this.clickEvent != null);
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
/*     */   public String toPlainText() {
/* 515 */     StringBuilder builder = new StringBuilder();
/* 516 */     toPlainText(builder);
/* 517 */     return builder.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   void toPlainText(StringBuilder builder) {
/* 522 */     if (this.extra != null)
/*     */     {
/* 524 */       for (BaseComponent e : this.extra)
/*     */       {
/* 526 */         e.toPlainText(builder);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toLegacyText() {
/* 539 */     StringBuilder builder = new StringBuilder();
/* 540 */     toLegacyText(builder);
/* 541 */     return builder.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   void toLegacyText(StringBuilder builder) {
/* 546 */     if (this.extra != null)
/*     */     {
/* 548 */       for (BaseComponent e : this.extra)
/*     */       {
/* 550 */         e.toLegacyText(builder);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void addFormat(StringBuilder builder) {
/* 557 */     builder.append(getColor());
/* 558 */     if (isBold())
/*     */     {
/* 560 */       builder.append(ChatColor.BOLD);
/*     */     }
/* 562 */     if (isItalic())
/*     */     {
/* 564 */       builder.append(ChatColor.ITALIC);
/*     */     }
/* 566 */     if (isUnderlined())
/*     */     {
/* 568 */       builder.append(ChatColor.UNDERLINE);
/*     */     }
/* 570 */     if (isStrikethrough())
/*     */     {
/* 572 */       builder.append(ChatColor.STRIKETHROUGH);
/*     */     }
/* 574 */     if (isObfuscated())
/*     */     {
/* 576 */       builder.append(ChatColor.MAGIC);
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract BaseComponent duplicate();
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\md_5\bungee\api\chat\BaseComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */