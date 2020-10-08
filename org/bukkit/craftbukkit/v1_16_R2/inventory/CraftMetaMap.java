/*     */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.server.v1_16_R2.NBTBase;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagInt;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagString;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.serialization.DelegateDeserialization;
/*     */ import org.bukkit.inventory.meta.Damageable;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.MapMeta;
/*     */ import org.bukkit.inventory.meta.Repairable;
/*     */ import org.bukkit.map.MapView;
/*     */ 
/*     */ @DelegateDeserialization(CraftMetaItem.SerializableMeta.class)
/*     */ class CraftMetaMap extends CraftMetaItem implements MapMeta {
/*  20 */   static final CraftMetaItem.ItemMetaKey MAP_SCALING = new CraftMetaItem.ItemMetaKey("map_is_scaling", "scaling");
/*  21 */   static final CraftMetaItem.ItemMetaKey MAP_LOC_NAME = new CraftMetaItem.ItemMetaKey("LocName", "display-loc-name");
/*  22 */   static final CraftMetaItem.ItemMetaKey MAP_COLOR = new CraftMetaItem.ItemMetaKey("MapColor", "display-map-color");
/*  23 */   static final CraftMetaItem.ItemMetaKey MAP_ID = new CraftMetaItem.ItemMetaKey("map", "map-id");
/*     */   
/*     */   static final byte SCALING_EMPTY = 0;
/*     */   static final byte SCALING_TRUE = 1;
/*     */   static final byte SCALING_FALSE = 2;
/*     */   private Integer mapId;
/*  29 */   private byte scaling = 0;
/*     */   private String locName;
/*     */   private Color color;
/*     */   
/*     */   CraftMetaMap(CraftMetaItem meta) {
/*  34 */     super(meta);
/*     */     
/*  36 */     if (!(meta instanceof CraftMetaMap)) {
/*     */       return;
/*     */     }
/*     */     
/*  40 */     CraftMetaMap map = (CraftMetaMap)meta;
/*  41 */     this.mapId = map.mapId;
/*  42 */     this.scaling = map.scaling;
/*  43 */     this.locName = map.locName;
/*  44 */     this.color = map.color;
/*     */   }
/*     */   
/*     */   CraftMetaMap(NBTTagCompound tag) {
/*  48 */     super(tag);
/*     */     
/*  50 */     if (tag.hasKeyOfType(MAP_ID.NBT, 99)) {
/*  51 */       this.mapId = Integer.valueOf(tag.getInt(MAP_ID.NBT));
/*     */     }
/*     */     
/*  54 */     if (tag.hasKey(MAP_SCALING.NBT)) {
/*  55 */       this.scaling = tag.getBoolean(MAP_SCALING.NBT) ? 1 : 2;
/*     */     }
/*     */     
/*  58 */     if (tag.hasKey(DISPLAY.NBT)) {
/*  59 */       NBTTagCompound display = tag.getCompound(DISPLAY.NBT);
/*     */       
/*  61 */       if (display.hasKey(MAP_LOC_NAME.NBT)) {
/*  62 */         this.locName = display.getString(MAP_LOC_NAME.NBT);
/*     */       }
/*     */       
/*  65 */       if (display.hasKey(MAP_COLOR.NBT)) {
/*     */         try {
/*  67 */           this.color = Color.fromRGB(display.getInt(MAP_COLOR.NBT));
/*  68 */         } catch (IllegalArgumentException illegalArgumentException) {}
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   CraftMetaMap(Map<String, Object> map) {
/*  76 */     super(map);
/*     */     
/*  78 */     Integer id = CraftMetaItem.SerializableMeta.<Integer>getObject(Integer.class, map, MAP_ID.BUKKIT, true);
/*  79 */     if (id != null) {
/*  80 */       setMapId(id.intValue());
/*     */     }
/*     */     
/*  83 */     Boolean scaling = CraftMetaItem.SerializableMeta.<Boolean>getObject(Boolean.class, map, MAP_SCALING.BUKKIT, true);
/*  84 */     if (scaling != null) {
/*  85 */       setScaling(scaling.booleanValue());
/*     */     }
/*     */     
/*  88 */     String locName = CraftMetaItem.SerializableMeta.getString(map, MAP_LOC_NAME.BUKKIT, true);
/*  89 */     if (locName != null) {
/*  90 */       setLocationName(locName);
/*     */     }
/*     */     
/*  93 */     Color color = CraftMetaItem.SerializableMeta.<Color>getObject(Color.class, map, MAP_COLOR.BUKKIT, true);
/*  94 */     if (color != null) {
/*  95 */       setColor(color);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void applyToItem(NBTTagCompound tag) {
/* 101 */     super.applyToItem(tag);
/*     */     
/* 103 */     if (hasMapId()) {
/* 104 */       tag.setInt(MAP_ID.NBT, getMapId());
/*     */     }
/*     */     
/* 107 */     if (hasScaling()) {
/* 108 */       tag.setBoolean(MAP_SCALING.NBT, isScaling());
/*     */     }
/*     */     
/* 111 */     if (hasLocationName()) {
/* 112 */       setDisplayTag(tag, MAP_LOC_NAME.NBT, (NBTBase)NBTTagString.a(getLocationName()));
/*     */     }
/*     */     
/* 115 */     if (hasColor()) {
/* 116 */       setDisplayTag(tag, MAP_COLOR.NBT, (NBTBase)NBTTagInt.a(this.color.asRGB()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   boolean applicableTo(Material type) {
/* 122 */     switch (type) {
/*     */       case FILLED_MAP:
/* 124 */         return true;
/*     */     } 
/* 126 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isEmpty() {
/* 132 */     return (super.isEmpty() && isMapEmpty());
/*     */   }
/*     */   
/*     */   boolean isMapEmpty() {
/* 136 */     return (!hasMapId() && (hasScaling() | hasLocationName()) == 0 && !hasColor());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasMapId() {
/* 141 */     return (this.mapId != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMapId() {
/* 146 */     return this.mapId.intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMapId(int id) {
/* 151 */     this.mapId = Integer.valueOf(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasMapView() {
/* 156 */     return (this.mapId != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public MapView getMapView() {
/* 161 */     Preconditions.checkState(hasMapView(), "Item does not have map associated - check hasMapView() first!");
/* 162 */     return Bukkit.getMap(this.mapId.intValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMapView(MapView map) {
/* 167 */     this.mapId = (map != null) ? Integer.valueOf(map.getId()) : null;
/*     */   }
/*     */   
/*     */   boolean hasScaling() {
/* 171 */     return (this.scaling != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isScaling() {
/* 176 */     return (this.scaling == 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setScaling(boolean scaling) {
/* 181 */     this.scaling = scaling ? 1 : 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasLocationName() {
/* 186 */     return (this.locName != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLocationName() {
/* 191 */     return this.locName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocationName(String name) {
/* 196 */     this.locName = name;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasColor() {
/* 201 */     return (this.color != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getColor() {
/* 206 */     return this.color;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColor(Color color) {
/* 211 */     this.color = color;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean equalsCommon(CraftMetaItem meta) {
/* 216 */     if (!super.equalsCommon(meta)) {
/* 217 */       return false;
/*     */     }
/* 219 */     if (meta instanceof CraftMetaMap) {
/* 220 */       CraftMetaMap that = (CraftMetaMap)meta;
/*     */       
/* 222 */       return (this.scaling == that.scaling && (
/* 223 */         hasMapId() ? (that.hasMapId() && this.mapId.equals(that.mapId)) : !that.hasMapId()) && (
/* 224 */         hasLocationName() ? (that.hasLocationName() && this.locName.equals(that.locName)) : !that.hasLocationName()) && (
/* 225 */         hasColor() ? (that.hasColor() && this.color.equals(that.color)) : !that.hasColor()));
/*     */     } 
/* 227 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean notUncommon(CraftMetaItem meta) {
/* 232 */     return (super.notUncommon(meta) && (meta instanceof CraftMetaMap || isMapEmpty()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   int applyHash() {
/* 238 */     int original = super.applyHash(), hash = original;
/*     */     
/* 240 */     if (hasMapId()) {
/* 241 */       hash = 61 * hash + this.mapId.hashCode();
/*     */     }
/* 243 */     if (hasScaling()) {
/* 244 */       hash ^= 572662306 << (isScaling() ? 1 : -1);
/*     */     }
/* 246 */     if (hasLocationName()) {
/* 247 */       hash = 61 * hash + this.locName.hashCode();
/*     */     }
/* 249 */     if (hasColor()) {
/* 250 */       hash = 61 * hash + this.color.hashCode();
/*     */     }
/*     */     
/* 253 */     return (original != hash) ? (CraftMetaMap.class.hashCode() ^ hash) : hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CraftMetaMap clone() {
/* 259 */     return (CraftMetaMap)super.clone();
/*     */   }
/*     */ 
/*     */   
/*     */   ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
/* 264 */     super.serialize(builder);
/*     */     
/* 266 */     if (hasMapId()) {
/* 267 */       builder.put(MAP_ID.BUKKIT, Integer.valueOf(getMapId()));
/*     */     }
/*     */     
/* 270 */     if (hasScaling()) {
/* 271 */       builder.put(MAP_SCALING.BUKKIT, Boolean.valueOf(isScaling()));
/*     */     }
/*     */     
/* 274 */     if (hasLocationName()) {
/* 275 */       builder.put(MAP_LOC_NAME.BUKKIT, getLocationName());
/*     */     }
/*     */     
/* 278 */     if (hasColor()) {
/* 279 */       builder.put(MAP_COLOR.BUKKIT, getColor());
/*     */     }
/*     */     
/* 282 */     return builder;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftMetaMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */