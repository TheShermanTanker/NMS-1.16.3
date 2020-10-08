/*     */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.server.v1_16_R2.IChatBaseComponent;
/*     */ import net.minecraft.server.v1_16_R2.NBTBase;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagList;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagString;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.serialization.DelegateDeserialization;
/*     */ import org.bukkit.inventory.meta.BookMeta;
/*     */ import org.bukkit.inventory.meta.Damageable;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.Repairable;
/*     */ 
/*     */ @DelegateDeserialization(CraftMetaItem.SerializableMeta.class)
/*     */ class CraftMetaBookSigned extends CraftMetaBook implements BookMeta {
/*     */   CraftMetaBookSigned(CraftMetaItem meta) {
/*  20 */     super(meta);
/*     */   }
/*     */   
/*     */   CraftMetaBookSigned(NBTTagCompound tag) {
/*  24 */     super(tag, false);
/*     */     
/*  26 */     boolean resolved = true;
/*  27 */     if (tag.hasKey(RESOLVED.NBT)) {
/*  28 */       resolved = tag.getBoolean(RESOLVED.NBT);
/*     */     }
/*     */     
/*  31 */     if (tag.hasKey(BOOK_PAGES.NBT)) {
/*  32 */       NBTTagList pages = tag.getList(BOOK_PAGES.NBT, 8);
/*     */       
/*  34 */       int i = 0; while (true) { String page; if (i < Math.min(pages.size(), 100))
/*  35 */         { page = pages.getString(i);
/*  36 */           if (resolved)
/*     */           { 
/*  38 */             try { this.pages.add(IChatBaseComponent.ChatSerializer.a(page)); }
/*     */             
/*  40 */             catch (Exception exception)
/*     */             
/*     */             { 
/*     */               
/*  44 */               addPage(new String[] { page }); }  continue; }  } else { break; }  addPage(new String[] { page });
/*     */         i++; }
/*     */     
/*     */     } 
/*     */   }
/*     */   CraftMetaBookSigned(Map<String, Object> map) {
/*  50 */     super(map);
/*     */   }
/*     */ 
/*     */   
/*     */   void applyToItem(NBTTagCompound itemData) {
/*  55 */     applyToItem(itemData, false);
/*     */     
/*  57 */     if (hasTitle()) {
/*  58 */       itemData.setString(BOOK_TITLE.NBT, this.title);
/*     */     }
/*     */     
/*  61 */     if (hasAuthor()) {
/*  62 */       itemData.setString(BOOK_AUTHOR.NBT, this.author);
/*     */     }
/*     */     
/*  65 */     if (hasPages()) {
/*  66 */       NBTTagList list = new NBTTagList();
/*  67 */       for (IChatBaseComponent page : this.pages) {
/*  68 */         list.add(NBTTagString.a(
/*  69 */               IChatBaseComponent.ChatSerializer.a(page)));
/*     */       }
/*     */       
/*  72 */       itemData.set(BOOK_PAGES.NBT, (NBTBase)list);
/*     */     } 
/*  74 */     itemData.setBoolean(RESOLVED.NBT, true);
/*     */     
/*  76 */     if (this.generation != null) {
/*  77 */       itemData.setInt(GENERATION.NBT, this.generation.intValue());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   boolean isEmpty() {
/*  83 */     return super.isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   boolean applicableTo(Material type) {
/*  88 */     switch (type) {
/*     */       case WRITTEN_BOOK:
/*     */       case WRITABLE_BOOK:
/*  91 */         return true;
/*     */     } 
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CraftMetaBookSigned clone() {
/*  99 */     CraftMetaBookSigned meta = (CraftMetaBookSigned)super.clone();
/* 100 */     return meta;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   int applyHash() {
/* 106 */     int original = super.applyHash(), hash = original;
/* 107 */     return (original != hash) ? (CraftMetaBookSigned.class.hashCode() ^ hash) : hash;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean equalsCommon(CraftMetaItem meta) {
/* 112 */     return super.equalsCommon(meta);
/*     */   }
/*     */ 
/*     */   
/*     */   boolean notUncommon(CraftMetaItem meta) {
/* 117 */     return (super.notUncommon(meta) && (meta instanceof CraftMetaBookSigned || isBookEmpty()));
/*     */   }
/*     */ 
/*     */   
/*     */   ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
/* 122 */     super.serialize(builder);
/* 123 */     return builder;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftMetaBookSigned.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */