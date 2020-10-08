/*     */ package net.md_5.bungee.api.chat;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import net.md_5.bungee.chat.TranslationRegistry;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class TranslatableComponent
/*     */   extends BaseComponent
/*     */ {
/*     */   public void setTranslate(String translate) {
/*  15 */     this.translate = translate; } public String toString() {
/*  16 */     return "TranslatableComponent(format=" + getFormat() + ", translate=" + getTranslate() + ", with=" + getWith() + ")";
/*     */   }
/*  18 */   public boolean equals(Object o) { if (o == this) return true;  if (!(o instanceof TranslatableComponent)) return false;  TranslatableComponent other = (TranslatableComponent)o; if (!other.canEqual(this)) return false;  if (!super.equals(o)) return false;  Object this$format = getFormat(), other$format = other.getFormat(); if ((this$format == null) ? (other$format != null) : !this$format.equals(other$format)) return false;  Object this$translate = getTranslate(), other$translate = other.getTranslate(); if ((this$translate == null) ? (other$translate != null) : !this$translate.equals(other$translate)) return false;  Object<BaseComponent> this$with = (Object<BaseComponent>)getWith(), other$with = (Object<BaseComponent>)other.getWith(); return !((this$with == null) ? (other$with != null) : !this$with.equals(other$with)); } protected boolean canEqual(Object other) { return other instanceof TranslatableComponent; } public int hashCode() { int PRIME = 59; result = super.hashCode(); Object $format = getFormat(); result = result * 59 + (($format == null) ? 43 : $format.hashCode()); Object $translate = getTranslate(); result = result * 59 + (($translate == null) ? 43 : $translate.hashCode()); Object<BaseComponent> $with = (Object<BaseComponent>)getWith(); return result * 59 + (($with == null) ? 43 : $with.hashCode()); }
/*     */ 
/*     */ 
/*     */   
/*  22 */   private final Pattern format = Pattern.compile("%(?:(\\d+)\\$)?([A-Za-z%]|$)"); private String translate; public Pattern getFormat() { return this.format; }
/*     */ 
/*     */   
/*     */   private List<BaseComponent> with;
/*     */   
/*     */   public String getTranslate() {
/*  28 */     return this.translate;
/*     */   }
/*     */   
/*     */   public List<BaseComponent> getWith() {
/*  32 */     return this.with;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TranslatableComponent(TranslatableComponent original) {
/*  41 */     super(original);
/*  42 */     setTranslate(original.getTranslate());
/*     */     
/*  44 */     if (original.getWith() != null) {
/*     */       
/*  46 */       List<BaseComponent> temp = new ArrayList<>();
/*  47 */       for (BaseComponent baseComponent : original.getWith())
/*     */       {
/*  49 */         temp.add(baseComponent.duplicate());
/*     */       }
/*  51 */       setWith(temp);
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
/*     */   public TranslatableComponent(String translate, Object... with) {
/*  67 */     setTranslate(translate);
/*  68 */     if (with != null && with.length != 0) {
/*     */       
/*  70 */       List<BaseComponent> temp = new ArrayList<>();
/*  71 */       for (Object w : with) {
/*     */         
/*  73 */         if (w instanceof BaseComponent) {
/*     */           
/*  75 */           temp.add((BaseComponent)w);
/*     */         } else {
/*     */           
/*  78 */           temp.add(new TextComponent(String.valueOf(w)));
/*     */         } 
/*     */       } 
/*  81 */       setWith(temp);
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
/*     */   public TranslatableComponent duplicate() {
/*  93 */     return new TranslatableComponent(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWith(List<BaseComponent> components) {
/* 104 */     for (BaseComponent component : components)
/*     */     {
/* 106 */       component.parent = this;
/*     */     }
/* 108 */     this.with = components;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addWith(String text) {
/* 119 */     addWith(new TextComponent(text));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addWith(BaseComponent component) {
/* 130 */     if (this.with == null)
/*     */     {
/* 132 */       this.with = new ArrayList<>();
/*     */     }
/* 134 */     component.parent = this;
/* 135 */     this.with.add(component);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void toPlainText(StringBuilder builder) {
/* 141 */     convert(builder, false);
/* 142 */     super.toPlainText(builder);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void toLegacyText(StringBuilder builder) {
/* 148 */     convert(builder, true);
/* 149 */     super.toLegacyText(builder);
/*     */   }
/*     */ 
/*     */   
/*     */   private void convert(StringBuilder builder, boolean applyFormat) {
/* 154 */     String trans = TranslationRegistry.INSTANCE.translate(this.translate);
/*     */     
/* 156 */     Matcher matcher = this.format.matcher(trans);
/* 157 */     int position = 0;
/* 158 */     int i = 0;
/* 159 */     while (matcher.find(position)) {
/*     */       String withIndex; BaseComponent withComponent;
/* 161 */       int pos = matcher.start();
/* 162 */       if (pos != position) {
/*     */         
/* 164 */         if (applyFormat)
/*     */         {
/* 166 */           addFormat(builder);
/*     */         }
/* 168 */         builder.append(trans.substring(position, pos));
/*     */       } 
/* 170 */       position = matcher.end();
/*     */       
/* 172 */       String formatCode = matcher.group(2);
/* 173 */       switch (formatCode.charAt(0)) {
/*     */         
/*     */         case 'd':
/*     */         case 's':
/* 177 */           withIndex = matcher.group(1);
/*     */           
/* 179 */           withComponent = this.with.get((withIndex != null) ? (Integer.parseInt(withIndex) - 1) : i++);
/* 180 */           if (applyFormat) {
/*     */             
/* 182 */             withComponent.toLegacyText(builder);
/*     */             continue;
/*     */           } 
/* 185 */           withComponent.toPlainText(builder);
/*     */ 
/*     */         
/*     */         case '%':
/* 189 */           if (applyFormat)
/*     */           {
/* 191 */             addFormat(builder);
/*     */           }
/* 193 */           builder.append('%');
/*     */       } 
/*     */     
/*     */     } 
/* 197 */     if (trans.length() != position) {
/*     */       
/* 199 */       if (applyFormat)
/*     */       {
/* 201 */         addFormat(builder);
/*     */       }
/* 203 */       builder.append(trans.substring(position, trans.length()));
/*     */     } 
/*     */   }
/*     */   
/*     */   public TranslatableComponent() {}
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\md_5\bungee\api\chat\TranslatableComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */