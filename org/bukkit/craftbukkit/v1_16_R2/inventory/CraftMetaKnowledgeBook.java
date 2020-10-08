/*     */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nonnull;
/*     */ import net.md_5.bungee.api.chat.BaseComponent;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagList;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.bukkit.attribute.Attribute;
/*     */ import org.bukkit.attribute.AttributeModifier;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.inventory.EquipmentSlot;
/*     */ import org.bukkit.inventory.ItemFlag;
/*     */ 
/*     */ @DelegateDeserialization(CraftMetaItem.SerializableMeta.class)
/*     */ public class CraftMetaKnowledgeBook extends CraftMetaItem implements KnowledgeBookMeta {
/*  21 */   static final CraftMetaItem.ItemMetaKey BOOK_RECIPES = new CraftMetaItem.ItemMetaKey("Recipes");
/*     */   
/*     */   static final int MAX_RECIPES = 32767;
/*  24 */   protected List<NamespacedKey> recipes = new ArrayList<>();
/*     */   
/*     */   CraftMetaKnowledgeBook(CraftMetaItem meta) {
/*  27 */     super(meta);
/*     */     
/*  29 */     if (meta instanceof CraftMetaKnowledgeBook) {
/*  30 */       CraftMetaKnowledgeBook bookMeta = (CraftMetaKnowledgeBook)meta;
/*  31 */       this.recipes.addAll(bookMeta.recipes);
/*     */     } 
/*     */   }
/*     */   
/*     */   CraftMetaKnowledgeBook(NBTTagCompound tag) {
/*  36 */     super(tag);
/*     */     
/*  38 */     if (tag.hasKey(BOOK_RECIPES.NBT)) {
/*  39 */       NBTTagList pages = tag.getList(BOOK_RECIPES.NBT, 8);
/*     */       
/*  41 */       for (int i = 0; i < pages.size(); i++) {
/*  42 */         String recipe = pages.getString(i);
/*     */         
/*  44 */         addRecipe(new NamespacedKey[] { CraftNamespacedKey.fromString(recipe) });
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   CraftMetaKnowledgeBook(Map<String, Object> map) {
/*  50 */     super(map);
/*     */     
/*  52 */     Iterable<?> pages = CraftMetaItem.SerializableMeta.<Iterable>getObject(Iterable.class, map, BOOK_RECIPES.BUKKIT, true);
/*  53 */     if (pages != null) {
/*  54 */       for (Object page : pages) {
/*  55 */         if (page instanceof String) {
/*  56 */           addRecipe(new NamespacedKey[] { CraftNamespacedKey.fromString((String)page) });
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void applyToItem(NBTTagCompound itemData) {
/*  64 */     super.applyToItem(itemData);
/*     */     
/*  66 */     if (hasRecipes()) {
/*  67 */       NBTTagList list = new NBTTagList();
/*  68 */       for (NamespacedKey recipe : this.recipes) {
/*  69 */         list.add(NBTTagString.a(recipe.toString()));
/*     */       }
/*  71 */       itemData.set(BOOK_RECIPES.NBT, (NBTBase)list);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   boolean isEmpty() {
/*  77 */     return (super.isEmpty() && isBookEmpty());
/*     */   }
/*     */   
/*     */   boolean isBookEmpty() {
/*  81 */     return !hasRecipes();
/*     */   }
/*     */ 
/*     */   
/*     */   boolean applicableTo(Material type) {
/*  86 */     switch (type) {
/*     */       case KNOWLEDGE_BOOK:
/*  88 */         return true;
/*     */     } 
/*  90 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasRecipes() {
/*  96 */     return !this.recipes.isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addRecipe(NamespacedKey... recipes) {
/* 101 */     for (NamespacedKey recipe : recipes) {
/* 102 */       if (recipe != null) {
/* 103 */         if (this.recipes.size() >= 32767) {
/*     */           return;
/*     */         }
/*     */         
/* 107 */         this.recipes.add(recipe);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List<NamespacedKey> getRecipes() {
/* 114 */     return Collections.unmodifiableList(this.recipes);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRecipes(List<NamespacedKey> recipes) {
/* 119 */     this.recipes.clear();
/* 120 */     for (NamespacedKey recipe : recipes) {
/* 121 */       addRecipe(new NamespacedKey[] { recipe });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public CraftMetaKnowledgeBook clone() {
/* 127 */     CraftMetaKnowledgeBook meta = (CraftMetaKnowledgeBook)super.clone();
/* 128 */     meta.recipes = new ArrayList<>(this.recipes);
/* 129 */     return meta;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   int applyHash() {
/* 135 */     int original = super.applyHash(), hash = original;
/* 136 */     if (hasRecipes()) {
/* 137 */       hash = 61 * hash + 17 * this.recipes.hashCode();
/*     */     }
/* 139 */     return (original != hash) ? (CraftMetaKnowledgeBook.class.hashCode() ^ hash) : hash;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean equalsCommon(CraftMetaItem meta) {
/* 144 */     if (!super.equalsCommon(meta)) {
/* 145 */       return false;
/*     */     }
/* 147 */     if (meta instanceof CraftMetaKnowledgeBook) {
/* 148 */       CraftMetaKnowledgeBook that = (CraftMetaKnowledgeBook)meta;
/*     */       
/* 150 */       return hasRecipes() ? ((that.hasRecipes() && this.recipes.equals(that.recipes))) : (!that.hasRecipes());
/*     */     } 
/* 152 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean notUncommon(CraftMetaItem meta) {
/* 157 */     return (super.notUncommon(meta) && (meta instanceof CraftMetaKnowledgeBook || isBookEmpty()));
/*     */   }
/*     */ 
/*     */   
/*     */   ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
/* 162 */     super.serialize(builder);
/*     */     
/* 164 */     if (hasRecipes()) {
/* 165 */       List<String> recipesString = new ArrayList<>();
/* 166 */       for (NamespacedKey recipe : this.recipes) {
/* 167 */         recipesString.add(recipe.toString());
/*     */       }
/* 169 */       builder.put(BOOK_RECIPES.BUKKIT, recipesString);
/*     */     } 
/*     */     
/* 172 */     return builder;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftMetaKnowledgeBook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */