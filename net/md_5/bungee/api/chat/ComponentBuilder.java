/*     */ package net.md_5.bungee.api.chat;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.md_5.bungee.api.ChatColor;
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
/*     */ public final class ComponentBuilder
/*     */ {
/*  37 */   private int cursor = -1; public int getCursor() { return this.cursor; }
/*     */   
/*  39 */   private final List<BaseComponent> parts = new ArrayList<>(); public List<BaseComponent> getParts() { return this.parts; }
/*     */ 
/*     */   
/*     */   private BaseComponent dummy;
/*     */   
/*     */   private ComponentBuilder(BaseComponent[] parts) {
/*  45 */     for (BaseComponent baseComponent : parts)
/*     */     {
/*  47 */       this.parts.add(baseComponent.duplicate());
/*     */     }
/*  49 */     resetCursor();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentBuilder(ComponentBuilder original) {
/*  60 */     this(original.parts.<BaseComponent>toArray(new BaseComponent[original.parts.size()]));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentBuilder(String text) {
/*  70 */     this(new TextComponent(text));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentBuilder(BaseComponent component) {
/*  81 */     this(new BaseComponent[] { component });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BaseComponent getDummy() {
/*  89 */     if (this.dummy == null)
/*     */     {
/*  91 */       this.dummy = new BaseComponent()
/*     */         {
/*     */           
/*     */           public BaseComponent duplicate()
/*     */           {
/*  96 */             return this;
/*     */           }
/*     */         };
/*     */     }
/* 100 */     return this.dummy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentBuilder resetCursor() {
/* 110 */     this.cursor = this.parts.size() - 1;
/* 111 */     return this;
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
/*     */   public ComponentBuilder setCursor(int pos) throws IndexOutOfBoundsException {
/* 125 */     if (this.cursor != pos && (pos < 0 || pos >= this.parts.size()))
/*     */     {
/* 127 */       throw new IndexOutOfBoundsException("Cursor out of bounds (expected between 0 + " + (this.parts.size() - 1) + ")");
/*     */     }
/*     */     
/* 130 */     this.cursor = pos;
/* 131 */     return this;
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
/*     */   public ComponentBuilder append(BaseComponent component) {
/* 144 */     return append(component, FormatRetention.ALL);
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
/*     */   public ComponentBuilder append(BaseComponent component, FormatRetention retention) {
/* 158 */     BaseComponent previous = this.parts.isEmpty() ? null : this.parts.get(this.parts.size() - 1);
/* 159 */     if (previous == null) {
/*     */       
/* 161 */       previous = this.dummy;
/* 162 */       this.dummy = null;
/*     */     } 
/* 164 */     if (previous != null)
/*     */     {
/* 166 */       component.copyFormatting(previous, retention, false);
/*     */     }
/* 168 */     this.parts.add(component);
/* 169 */     resetCursor();
/* 170 */     return this;
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
/*     */   public ComponentBuilder append(BaseComponent[] components) {
/* 183 */     return append(components, FormatRetention.ALL);
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
/*     */   public ComponentBuilder append(BaseComponent[] components, FormatRetention retention) {
/* 197 */     Preconditions.checkArgument((components.length != 0), "No components to append");
/*     */     
/* 199 */     for (BaseComponent component : components)
/*     */     {
/* 201 */       append(component, retention);
/*     */     }
/*     */     
/* 204 */     return this;
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
/*     */   public ComponentBuilder append(String text) {
/* 216 */     return append(text, FormatRetention.ALL);
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
/*     */   public ComponentBuilder appendLegacy(String text) {
/* 229 */     return append(TextComponent.fromLegacyText(text));
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
/*     */   public ComponentBuilder append(String text, FormatRetention retention) {
/* 243 */     return append(new TextComponent(text), retention);
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
/*     */   public ComponentBuilder append(Joiner joiner) {
/* 258 */     return joiner.join(this, FormatRetention.ALL);
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
/*     */   public ComponentBuilder append(Joiner joiner, FormatRetention retention) {
/* 274 */     return joiner.join(this, retention);
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
/*     */   public void removeComponent(int pos) throws IndexOutOfBoundsException {
/* 286 */     if (this.parts.remove(pos) != null)
/*     */     {
/* 288 */       resetCursor();
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
/*     */   public BaseComponent getComponent(int pos) throws IndexOutOfBoundsException {
/* 302 */     return this.parts.get(pos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BaseComponent getCurrentComponent() {
/* 312 */     return (this.cursor == -1) ? getDummy() : this.parts.get(this.cursor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentBuilder color(ChatColor color) {
/* 323 */     getCurrentComponent().setColor(color);
/* 324 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentBuilder font(String font) {
/* 335 */     getCurrentComponent().setFont(font);
/* 336 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentBuilder bold(boolean bold) {
/* 347 */     getCurrentComponent().setBold(Boolean.valueOf(bold));
/* 348 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentBuilder italic(boolean italic) {
/* 359 */     getCurrentComponent().setItalic(Boolean.valueOf(italic));
/* 360 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentBuilder underlined(boolean underlined) {
/* 371 */     getCurrentComponent().setUnderlined(Boolean.valueOf(underlined));
/* 372 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentBuilder strikethrough(boolean strikethrough) {
/* 383 */     getCurrentComponent().setStrikethrough(Boolean.valueOf(strikethrough));
/* 384 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentBuilder obfuscated(boolean obfuscated) {
/* 395 */     getCurrentComponent().setObfuscated(Boolean.valueOf(obfuscated));
/* 396 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentBuilder insertion(String insertion) {
/* 407 */     getCurrentComponent().setInsertion(insertion);
/* 408 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentBuilder event(ClickEvent clickEvent) {
/* 419 */     getCurrentComponent().setClickEvent(clickEvent);
/* 420 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentBuilder event(HoverEvent hoverEvent) {
/* 431 */     getCurrentComponent().setHoverEvent(hoverEvent);
/* 432 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentBuilder reset() {
/* 442 */     return retain(FormatRetention.NONE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentBuilder retain(FormatRetention retention) {
/* 453 */     getCurrentComponent().retain(retention);
/* 454 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BaseComponent[] create() {
/* 465 */     BaseComponent[] cloned = new BaseComponent[this.parts.size()];
/* 466 */     int i = 0;
/* 467 */     for (BaseComponent part : this.parts)
/*     */     {
/* 469 */       cloned[i++] = part.duplicate();
/*     */     }
/* 471 */     return cloned;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentBuilder() {}
/*     */ 
/*     */   
/*     */   public enum FormatRetention
/*     */   {
/* 481 */     NONE,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 486 */     FORMATTING,
/*     */ 
/*     */ 
/*     */     
/* 490 */     EVENTS,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 495 */     ALL;
/*     */   }
/*     */   
/*     */   public static interface Joiner {
/*     */     ComponentBuilder join(ComponentBuilder param1ComponentBuilder, ComponentBuilder.FormatRetention param1FormatRetention);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\md_5\bungee\api\chat\ComponentBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */