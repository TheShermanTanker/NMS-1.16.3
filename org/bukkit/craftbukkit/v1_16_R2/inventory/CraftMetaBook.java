/*     */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*     */ import com.destroystokyo.paper.Namespaced;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nonnull;
/*     */ import javax.annotation.Nullable;
/*     */ import net.md_5.bungee.api.chat.BaseComponent;
/*     */ import net.md_5.bungee.chat.ComponentSerializer;
/*     */ import net.minecraft.server.v1_16_R2.IChatBaseComponent;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagList;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.attribute.Attribute;
/*     */ import org.bukkit.attribute.AttributeModifier;
/*     */ import org.bukkit.block.data.BlockData;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.inventory.EquipmentSlot;
/*     */ import org.bukkit.inventory.ItemFlag;
/*     */ import org.bukkit.inventory.meta.BookMeta;
/*     */ import org.spigotmc.ValidateUtils;
/*     */ 
/*     */ @DelegateDeserialization(CraftMetaItem.SerializableMeta.class)
/*     */ public class CraftMetaBook extends CraftMetaItem implements BookMeta {
/*  30 */   static final CraftMetaItem.ItemMetaKey BOOK_TITLE = new CraftMetaItem.ItemMetaKey("title");
/*  31 */   static final CraftMetaItem.ItemMetaKey BOOK_AUTHOR = new CraftMetaItem.ItemMetaKey("author");
/*  32 */   static final CraftMetaItem.ItemMetaKey BOOK_PAGES = new CraftMetaItem.ItemMetaKey("pages");
/*  33 */   static final CraftMetaItem.ItemMetaKey RESOLVED = new CraftMetaItem.ItemMetaKey("resolved");
/*  34 */   static final CraftMetaItem.ItemMetaKey GENERATION = new CraftMetaItem.ItemMetaKey("generation");
/*     */   static final int MAX_PAGES = 100;
/*     */   static final int MAX_PAGE_LENGTH = 320;
/*     */   static final int MAX_TITLE_LENGTH = 32;
/*  38 */   private static final boolean OVERRIDE_CHECKS = Boolean.getBoolean("disable.book-limits");
/*     */   
/*     */   protected String title;
/*     */   protected String author;
/*  42 */   public List<IChatBaseComponent> pages = new ArrayList<>();
/*     */   protected Integer generation;
/*     */   private BookMeta.Spigot spigot;
/*     */   
/*  46 */   CraftMetaBook(CraftMetaItem meta) { super(meta);
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
/* 367 */     this.spigot = new SpigotMeta(); if (meta instanceof CraftMetaBook) { CraftMetaBook bookMeta = (CraftMetaBook)meta; this.title = bookMeta.title; this.author = bookMeta.author; this.pages.addAll(bookMeta.pages); this.generation = bookMeta.generation; }  } CraftMetaBook(NBTTagCompound tag) { this(tag, true); } CraftMetaBook(NBTTagCompound tag, boolean handlePages) { super(tag); this.spigot = new SpigotMeta(); if (tag.hasKey(BOOK_TITLE.NBT)) this.title = ValidateUtils.limit(tag.getString(BOOK_TITLE.NBT), 8192);  if (tag.hasKey(BOOK_AUTHOR.NBT)) this.author = ValidateUtils.limit(tag.getString(BOOK_AUTHOR.NBT), 8192);  boolean resolved = false; if (tag.hasKey(RESOLVED.NBT)) resolved = tag.getBoolean(RESOLVED.NBT);  if (tag.hasKey(GENERATION.NBT)) this.generation = Integer.valueOf(tag.getInt(GENERATION.NBT));  if (tag.hasKey(BOOK_PAGES.NBT) && handlePages) { NBTTagList pages = tag.getList(BOOK_PAGES.NBT, 8); int i = 0; while (true) { String page; if (i < Math.min(pages.size(), 100)) { page = pages.getString(i); if (resolved) { try { this.pages.add(IChatBaseComponent.ChatSerializer.a(page)); } catch (Exception exception) { addPage(new String[] { ValidateUtils.limit(page, 16384) }); }  continue; }  } else { break; }  addPage(new String[] { ValidateUtils.limit(page, 16384) }); i++; }  }  } void applyToItem(NBTTagCompound itemData) { applyToItem(itemData, true); } void applyToItem(NBTTagCompound itemData, boolean handlePages) { super.applyToItem(itemData); if (hasTitle()) itemData.setString(BOOK_TITLE.NBT, this.title);  if (hasAuthor()) itemData.setString(BOOK_AUTHOR.NBT, this.author);  if (handlePages) { if (hasPages()) { NBTTagList list = new NBTTagList(); for (IChatBaseComponent page : this.pages) list.add(NBTTagString.a((page == null) ? "" : CraftChatMessage.fromComponent(page)));  itemData.set(BOOK_PAGES.NBT, (NBTBase)list); }  itemData.remove(RESOLVED.NBT); }  if (this.generation != null) itemData.setInt(GENERATION.NBT, this.generation.intValue());  } boolean isEmpty() { return (super.isEmpty() && isBookEmpty()); } boolean isBookEmpty() { return (!hasPages() && !hasAuthor() && !hasTitle()); } boolean applicableTo(Material type) { switch (type) { case WRITTEN_BOOK: case WRITABLE_BOOK: return true; }  return false; } public boolean hasAuthor() { return (this.author != null); } public boolean hasTitle() { return (this.title != null); } public boolean hasPages() { return !this.pages.isEmpty(); } public boolean hasGeneration() { return (this.generation != null); } public String getTitle() { return this.title; } public boolean setTitle(String title) { if (title == null) { this.title = null; return true; }  if (title.length() > 32 && !OVERRIDE_CHECKS) return false;  this.title = title; return true; } public String getAuthor() { return this.author; } public void setAuthor(String author) { this.author = author; } public BookMeta.Generation getGeneration() { return (this.generation == null) ? null : BookMeta.Generation.values()[this.generation.intValue()]; } CraftMetaBook(Map<String, Object> map) { super(map); this.spigot = new SpigotMeta(); setAuthor(CraftMetaItem.SerializableMeta.getString(map, BOOK_AUTHOR.BUKKIT, true)); setTitle(CraftMetaItem.SerializableMeta.getString(map, BOOK_TITLE.BUKKIT, true)); Iterable<?> pages = CraftMetaItem.SerializableMeta.<Iterable>getObject(Iterable.class, map, BOOK_PAGES.BUKKIT, true); if (pages != null) for (Object page : pages) { if (page instanceof String) addPage(new String[] { (String)page });  }   this.generation = CraftMetaItem.SerializableMeta.<Integer>getObject(Integer.class, map, GENERATION.BUKKIT, true); }
/*     */   public void setGeneration(BookMeta.Generation generation) { this.generation = (generation == null) ? null : Integer.valueOf(generation.ordinal()); }
/*     */   public String getPage(int page) { Validate.isTrue(isValidPage(page), "Invalid page number"); return CraftChatMessage.fromComponent(this.pages.get(page - 1)); }
/*     */   public void setPage(int page, String text) { if (!isValidPage(page)) throw new IllegalArgumentException("Invalid page number " + page + "/" + this.pages.size());  String newText = (text == null) ? "" : ((text.length() > 320 && !OVERRIDE_CHECKS) ? text.substring(0, 320) : text); this.pages.set(page - 1, CraftChatMessage.fromString(newText, true)[0]); } public void setPages(String... pages) { this.pages.clear(); addPage(pages); } public void addPage(String... pages) { for (String page : pages) { if (this.pages.size() >= 100 && !OVERRIDE_CHECKS) return;  if (page == null) { page = ""; } else if (page.length() > 320 && !OVERRIDE_CHECKS) { page = page.substring(0, 320); }  this.pages.add(CraftChatMessage.fromString(page, true)[0]); }  } public int getPageCount() { return this.pages.size(); } public List<String> getPages() { return (List<String>)this.pages.stream().map(CraftChatMessage::fromComponent).collect(ImmutableList.toImmutableList()); } public void setPages(List<String> pages) { this.pages.clear(); for (String page : pages) { addPage(new String[] { page }); }  } private boolean isValidPage(int page) { return (page > 0 && page <= this.pages.size()); } public CraftMetaBook clone() { CraftMetaBook meta = (CraftMetaBook)super.clone(); meta.pages = new ArrayList<>(this.pages); meta.spigot = new SpigotMeta(); return meta; } int applyHash() { int original = super.applyHash(), hash = original; if (hasTitle()) hash = 61 * hash + this.title.hashCode();  if (hasAuthor()) hash = 61 * hash + 13 * this.author.hashCode();  if (hasPages()) hash = 61 * hash + 17 * this.pages.hashCode();  if (hasGeneration()) hash = 61 * hash + 19 * this.generation.hashCode();  return (original != hash) ? (CraftMetaBook.class.hashCode() ^ hash) : hash; } boolean equalsCommon(CraftMetaItem meta) { if (!super.equalsCommon(meta)) return false;  if (meta instanceof CraftMetaBook) { CraftMetaBook that = (CraftMetaBook)meta; if (hasTitle() ? (that.hasTitle() && this.title.equals(that.title)) : !that.hasTitle()) if ((hasAuthor() ? (that.hasAuthor() && this.author.equals(that.author)) : !that.hasAuthor()) && (hasPages() ? (that.hasPages() && this.pages.equals(that.pages)) : !that.hasPages()) && (hasGeneration() ? (that.hasGeneration() && this.generation.equals(that.generation)) : !that.hasGeneration()));  return false; }  return true; } boolean notUncommon(CraftMetaItem meta) { return (super.notUncommon(meta) && (meta instanceof CraftMetaBook || isBookEmpty())); } ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) { super.serialize(builder); if (hasTitle()) builder.put(BOOK_TITLE.BUKKIT, this.title);  if (hasAuthor()) builder.put(BOOK_AUTHOR.BUKKIT, this.author);  if (hasPages()) { List<String> pagesString = new ArrayList<>(); for (IChatBaseComponent comp : this.pages) pagesString.add(CraftChatMessage.fromComponent(comp));  builder.put(BOOK_PAGES.BUKKIT, pagesString); }  if (this.generation != null) builder.put(GENERATION.BUKKIT, this.generation);  return builder; } private class SpigotMeta extends BookMeta.Spigot
/*     */   {
/* 372 */     public BaseComponent[] getPage(int page) { Validate.isTrue(CraftMetaBook.this.isValidPage(page), "Invalid page number");
/* 373 */       return ComponentSerializer.parse(IChatBaseComponent.ChatSerializer.a(CraftMetaBook.this.pages.get(page - 1))); }
/*     */     
/*     */     private SpigotMeta() {}
/*     */     
/*     */     public void setPage(int page, BaseComponent... text) {
/* 378 */       if (!CraftMetaBook.this.isValidPage(page)) {
/* 379 */         throw new IllegalArgumentException("Invalid page number " + page + "/" + CraftMetaBook.this.pages.size());
/*     */       }
/*     */       
/* 382 */       BaseComponent[] newText = (text == null) ? new BaseComponent[0] : text;
/* 383 */       CraftMetaBook.this.pages.set(page - 1, IChatBaseComponent.ChatSerializer.a(ComponentSerializer.toString(newText)));
/*     */     }
/*     */ 
/*     */     
/*     */     public void setPages(BaseComponent[]... pages) {
/* 388 */       CraftMetaBook.this.pages.clear();
/*     */       
/* 390 */       addPage(pages);
/*     */     }
/*     */ 
/*     */     
/*     */     public void addPage(BaseComponent[]... pages) {
/* 395 */       for (BaseComponent[] page : pages) {
/* 396 */         if (CraftMetaBook.this.pages.size() >= 100) {
/*     */           return;
/*     */         }
/*     */         
/* 400 */         if (page == null) {
/* 401 */           page = new BaseComponent[0];
/*     */         }
/*     */         
/* 404 */         CraftMetaBook.this.pages.add(IChatBaseComponent.ChatSerializer.a(ComponentSerializer.toString(page)));
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public List<BaseComponent[]> getPages() {
/* 410 */       final ImmutableList copy = ImmutableList.copyOf(CraftMetaBook.this.pages);
/* 411 */       return (List<BaseComponent[]>)new AbstractList<BaseComponent[]>()
/*     */         {
/*     */           public BaseComponent[] get(int index)
/*     */           {
/* 415 */             return ComponentSerializer.parse(IChatBaseComponent.ChatSerializer.a(copy.get(index)));
/*     */           }
/*     */ 
/*     */           
/*     */           public int size() {
/* 420 */             return copy.size();
/*     */           }
/*     */         };
/*     */     }
/*     */ 
/*     */     
/*     */     public void setPages(List<BaseComponent[]> pages) {
/* 427 */       CraftMetaBook.this.pages.clear();
/* 428 */       for (BaseComponent[] page : pages) {
/* 429 */         addPage(new BaseComponent[][] { page });
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public BookMeta.Spigot spigot() {
/* 436 */     return this.spigot;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftMetaBook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */