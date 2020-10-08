/*     */ package net.md_5.bungee.api.chat;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import net.md_5.bungee.api.chat.hover.content.Content;
/*     */ import net.md_5.bungee.api.chat.hover.content.Entity;
/*     */ import net.md_5.bungee.api.chat.hover.content.Item;
/*     */ import net.md_5.bungee.api.chat.hover.content.Text;
/*     */ import net.md_5.bungee.chat.ComponentSerializer;
/*     */ 
/*     */ public final class HoverEvent {
/*     */   private final Action action;
/*     */   private final List<Content> contents;
/*     */   
/*     */   public String toString() {
/*  19 */     return "HoverEvent(action=" + getAction() + ", contents=" + getContents() + ", legacy=" + isLegacy() + ")";
/*  20 */   } public boolean equals(Object o) { if (o == this) return true;  if (!(o instanceof HoverEvent)) return false;  HoverEvent other = (HoverEvent)o; Object this$action = getAction(), other$action = other.getAction(); if ((this$action == null) ? (other$action != null) : !this$action.equals(other$action)) return false;  Object<Content> this$contents = (Object<Content>)getContents(), other$contents = (Object<Content>)other.getContents(); return ((this$contents == null) ? (other$contents != null) : !this$contents.equals(other$contents)) ? false : (!(isLegacy() != other.isLegacy())); } public int hashCode() { int PRIME = 59; result = 1; Object $action = getAction(); result = result * 59 + (($action == null) ? 43 : $action.hashCode()); Object<Content> $contents = (Object<Content>)getContents(); result = result * 59 + (($contents == null) ? 43 : $contents.hashCode()); return result * 59 + (isLegacy() ? 79 : 97); } public HoverEvent(Action action, List<Content> contents) {
/*  21 */     this.action = action; this.contents = contents;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Action getAction() {
/*  28 */     return this.action;
/*     */   }
/*     */   
/*     */   public List<Content> getContents() {
/*  32 */     return this.contents;
/*     */   }
/*     */   
/*     */   private boolean legacy = false;
/*  36 */   public void setLegacy(boolean legacy) { this.legacy = legacy; } public boolean isLegacy() {
/*  37 */     return this.legacy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HoverEvent(Action action, Content... contents) {
/*  47 */     Preconditions.checkArgument((contents.length != 0), "Must contain at least one content");
/*     */     
/*  49 */     this.action = action;
/*  50 */     this.contents = new ArrayList<>();
/*  51 */     for (Content it : contents)
/*     */     {
/*  53 */       addContent(it);
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
/*     */   @Deprecated
/*     */   public HoverEvent(Action action, BaseComponent[] value) {
/*  69 */     this.action = action;
/*  70 */     this.contents = new ArrayList<>((Collection)Collections.singletonList(new Text(value)));
/*  71 */     this.legacy = true;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public BaseComponent[] getValue() {
/*  77 */     Content content = this.contents.get(0);
/*  78 */     if (content instanceof Text && ((Text)content).getValue() instanceof BaseComponent[])
/*     */     {
/*  80 */       return (BaseComponent[])((Text)content).getValue();
/*     */     }
/*     */     
/*  83 */     TextComponent component = new TextComponent(ComponentSerializer.toString(content));
/*  84 */     return new BaseComponent[] { component };
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
/*     */   public void addContent(Content content) throws UnsupportedOperationException {
/* 101 */     Preconditions.checkArgument((!this.legacy || this.contents.size() == 0), "Legacy HoverEvent may not have more than one content");
/*     */     
/* 103 */     content.assertAction(this.action);
/* 104 */     this.contents.add(content);
/*     */   }
/*     */ 
/*     */   
/*     */   public enum Action
/*     */   {
/* 110 */     SHOW_TEXT,
/* 111 */     SHOW_ITEM,
/* 112 */     SHOW_ENTITY,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 118 */     SHOW_ACHIEVEMENT;
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
/*     */   public static Class<?> getClass(Action action, boolean array) {
/* 132 */     Preconditions.checkArgument((action != null), "action");
/*     */     
/* 134 */     switch (action) {
/*     */       
/*     */       case SHOW_TEXT:
/* 137 */         return array ? Text[].class : Text.class;
/*     */       case SHOW_ENTITY:
/* 139 */         return array ? Entity[].class : Entity.class;
/*     */       case SHOW_ITEM:
/* 141 */         return array ? Item[].class : Item.class;
/*     */     } 
/* 143 */     throw new UnsupportedOperationException("Action '" + action.name() + " not supported");
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\md_5\bungee\api\chat\HoverEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */