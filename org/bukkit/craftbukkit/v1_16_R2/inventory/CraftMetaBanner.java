/*     */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nonnull;
/*     */ import net.md_5.bungee.api.chat.BaseComponent;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagList;
/*     */ import org.bukkit.DyeColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.attribute.Attribute;
/*     */ import org.bukkit.attribute.AttributeModifier;
/*     */ import org.bukkit.block.banner.Pattern;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.inventory.ItemFlag;
/*     */ 
/*     */ @DelegateDeserialization(CraftMetaItem.SerializableMeta.class)
/*     */ public class CraftMetaBanner extends CraftMetaItem implements BannerMeta {
/*  21 */   static final CraftMetaItem.ItemMetaKey BASE = new CraftMetaItem.ItemMetaKey("Base", "base-color");
/*  22 */   static final CraftMetaItem.ItemMetaKey PATTERNS = new CraftMetaItem.ItemMetaKey("Patterns", "patterns");
/*  23 */   static final CraftMetaItem.ItemMetaKey COLOR = new CraftMetaItem.ItemMetaKey("Color", "color");
/*  24 */   static final CraftMetaItem.ItemMetaKey PATTERN = new CraftMetaItem.ItemMetaKey("Pattern", "pattern");
/*     */   
/*     */   private DyeColor base;
/*  27 */   private List<Pattern> patterns = new ArrayList<>();
/*     */   
/*     */   CraftMetaBanner(CraftMetaItem meta) {
/*  30 */     super(meta);
/*     */     
/*  32 */     if (!(meta instanceof CraftMetaBanner)) {
/*     */       return;
/*     */     }
/*     */     
/*  36 */     CraftMetaBanner banner = (CraftMetaBanner)meta;
/*  37 */     this.base = banner.base;
/*  38 */     this.patterns = new ArrayList<>(banner.patterns);
/*     */   }
/*     */   
/*     */   CraftMetaBanner(NBTTagCompound tag) {
/*  42 */     super(tag);
/*     */     
/*  44 */     if (!tag.hasKey("BlockEntityTag")) {
/*     */       return;
/*     */     }
/*     */     
/*  48 */     NBTTagCompound entityTag = tag.getCompound("BlockEntityTag");
/*     */     
/*  50 */     this.base = entityTag.hasKey(BASE.NBT) ? DyeColor.getByWoolData((byte)entityTag.getInt(BASE.NBT)) : null;
/*     */     
/*  52 */     if (entityTag.hasKey(PATTERNS.NBT)) {
/*  53 */       NBTTagList patterns = entityTag.getList(PATTERNS.NBT, 10);
/*  54 */       for (int i = 0; i < Math.min(patterns.size(), 20); i++) {
/*  55 */         NBTTagCompound p = patterns.getCompound(i);
/*  56 */         DyeColor color = DyeColor.getByWoolData((byte)p.getInt(COLOR.NBT));
/*  57 */         PatternType pattern = PatternType.getByIdentifier(p.getString(PATTERN.NBT));
/*     */         
/*  59 */         if (color != null && pattern != null) {
/*  60 */           this.patterns.add(new Pattern(color, pattern));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   CraftMetaBanner(Map<String, Object> map) {
/*  67 */     super(map);
/*     */     
/*  69 */     String baseStr = CraftMetaItem.SerializableMeta.getString(map, BASE.BUKKIT, true);
/*  70 */     if (baseStr != null) {
/*  71 */       this.base = DyeColor.legacyValueOf(baseStr);
/*     */     }
/*     */     
/*  74 */     Iterable<?> rawPatternList = CraftMetaItem.SerializableMeta.<Iterable>getObject(Iterable.class, map, PATTERNS.BUKKIT, true);
/*  75 */     if (rawPatternList == null) {
/*     */       return;
/*     */     }
/*     */     
/*  79 */     for (Object obj : rawPatternList) {
/*  80 */       if (!(obj instanceof Pattern)) {
/*  81 */         throw new IllegalArgumentException("Object in pattern list is not valid. " + obj.getClass());
/*     */       }
/*  83 */       addPattern((Pattern)obj);
/*     */     } 
/*     */   }
/*     */   
/*     */   void applyToItem(NBTTagCompound tag) {
/*  88 */     super.applyToItem(tag);
/*     */     
/*  90 */     NBTTagCompound entityTag = new NBTTagCompound();
/*  91 */     if (this.base != null) {
/*  92 */       entityTag.setInt(BASE.NBT, this.base.getWoolData());
/*     */     }
/*     */     
/*  95 */     NBTTagList newPatterns = new NBTTagList();
/*     */     
/*  97 */     for (Pattern p : this.patterns) {
/*  98 */       NBTTagCompound compound = new NBTTagCompound();
/*  99 */       compound.setInt(COLOR.NBT, p.getColor().getWoolData());
/* 100 */       compound.setString(PATTERN.NBT, p.getPattern().getIdentifier());
/* 101 */       newPatterns.add(compound);
/*     */     } 
/* 103 */     entityTag.set(PATTERNS.NBT, (NBTBase)newPatterns);
/*     */     
/* 105 */     tag.set("BlockEntityTag", (NBTBase)entityTag);
/*     */   }
/*     */ 
/*     */   
/*     */   public DyeColor getBaseColor() {
/* 110 */     return this.base;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBaseColor(DyeColor color) {
/* 115 */     this.base = color;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Pattern> getPatterns() {
/* 120 */     return new ArrayList<>(this.patterns);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPatterns(List<Pattern> patterns) {
/* 125 */     this.patterns = new ArrayList<>(patterns);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addPattern(Pattern pattern) {
/* 130 */     this.patterns.add(pattern);
/*     */   }
/*     */ 
/*     */   
/*     */   public Pattern getPattern(int i) {
/* 135 */     return this.patterns.get(i);
/*     */   }
/*     */ 
/*     */   
/*     */   public Pattern removePattern(int i) {
/* 140 */     return this.patterns.remove(i);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPattern(int i, Pattern pattern) {
/* 145 */     this.patterns.set(i, pattern);
/*     */   }
/*     */ 
/*     */   
/*     */   public int numberOfPatterns() {
/* 150 */     return this.patterns.size();
/*     */   }
/*     */ 
/*     */   
/*     */   ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
/* 155 */     super.serialize(builder);
/*     */     
/* 157 */     if (this.base != null) {
/* 158 */       builder.put(BASE.BUKKIT, this.base.toString());
/*     */     }
/*     */     
/* 161 */     if (!this.patterns.isEmpty()) {
/* 162 */       builder.put(PATTERNS.BUKKIT, ImmutableList.copyOf(this.patterns));
/*     */     }
/*     */     
/* 165 */     return builder;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   int applyHash() {
/* 171 */     int original = super.applyHash(), hash = original;
/* 172 */     if (this.base != null) {
/* 173 */       hash = 31 * hash + this.base.hashCode();
/*     */     }
/* 175 */     if (!this.patterns.isEmpty()) {
/* 176 */       hash = 31 * hash + this.patterns.hashCode();
/*     */     }
/* 178 */     return (original != hash) ? (CraftMetaBanner.class.hashCode() ^ hash) : hash;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equalsCommon(CraftMetaItem meta) {
/* 183 */     if (!super.equalsCommon(meta)) {
/* 184 */       return false;
/*     */     }
/* 186 */     if (meta instanceof CraftMetaBanner) {
/* 187 */       CraftMetaBanner that = (CraftMetaBanner)meta;
/*     */       
/* 189 */       return (this.base == that.base && this.patterns.equals(that.patterns));
/*     */     } 
/* 191 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean notUncommon(CraftMetaItem meta) {
/* 196 */     return (super.notUncommon(meta) && (meta instanceof CraftMetaBanner || (this.patterns.isEmpty() && this.base == null)));
/*     */   }
/*     */ 
/*     */   
/*     */   boolean isEmpty() {
/* 201 */     return (super.isEmpty() && this.patterns.isEmpty() && this.base == null);
/*     */   }
/*     */ 
/*     */   
/*     */   boolean applicableTo(Material type) {
/* 206 */     switch (type) {
/*     */       case BLACK_BANNER:
/*     */       case BLACK_WALL_BANNER:
/*     */       case BLUE_BANNER:
/*     */       case BLUE_WALL_BANNER:
/*     */       case BROWN_BANNER:
/*     */       case BROWN_WALL_BANNER:
/*     */       case CYAN_BANNER:
/*     */       case CYAN_WALL_BANNER:
/*     */       case GRAY_BANNER:
/*     */       case GRAY_WALL_BANNER:
/*     */       case GREEN_BANNER:
/*     */       case GREEN_WALL_BANNER:
/*     */       case LIGHT_BLUE_BANNER:
/*     */       case LIGHT_BLUE_WALL_BANNER:
/*     */       case LIGHT_GRAY_BANNER:
/*     */       case LIGHT_GRAY_WALL_BANNER:
/*     */       case LIME_BANNER:
/*     */       case LIME_WALL_BANNER:
/*     */       case MAGENTA_BANNER:
/*     */       case MAGENTA_WALL_BANNER:
/*     */       case ORANGE_BANNER:
/*     */       case ORANGE_WALL_BANNER:
/*     */       case PINK_BANNER:
/*     */       case PINK_WALL_BANNER:
/*     */       case PURPLE_BANNER:
/*     */       case PURPLE_WALL_BANNER:
/*     */       case RED_BANNER:
/*     */       case RED_WALL_BANNER:
/*     */       case WHITE_BANNER:
/*     */       case WHITE_WALL_BANNER:
/*     */       case YELLOW_BANNER:
/*     */       case YELLOW_WALL_BANNER:
/* 239 */         return true;
/*     */     } 
/* 241 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CraftMetaBanner clone() {
/* 247 */     CraftMetaBanner meta = (CraftMetaBanner)super.clone();
/* 248 */     meta.patterns = new ArrayList<>(this.patterns);
/* 249 */     return meta;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftMetaBanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */