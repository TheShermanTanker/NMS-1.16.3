/*     */ package org.bukkit.configuration;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.configuration.serialization.ConfigurationSerializable;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.util.NumberConversions;
/*     */ import org.bukkit.util.Vector;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ public class MemorySection
/*     */   implements ConfigurationSection
/*     */ {
/*  24 */   protected final Map<String, Object> map = new LinkedHashMap<>();
/*     */ 
/*     */   
/*     */   private final Configuration root;
/*     */ 
/*     */   
/*     */   private final ConfigurationSection parent;
/*     */ 
/*     */   
/*     */   private final String path;
/*     */ 
/*     */   
/*     */   private final String fullPath;
/*     */ 
/*     */ 
/*     */   
/*     */   protected MemorySection() {
/*  41 */     if (!(this instanceof Configuration)) {
/*  42 */       throw new IllegalStateException("Cannot construct a root MemorySection when not a Configuration");
/*     */     }
/*     */     
/*  45 */     this.path = "";
/*  46 */     this.fullPath = "";
/*  47 */     this.parent = null;
/*  48 */     this.root = (Configuration)this;
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
/*     */   protected MemorySection(@NotNull ConfigurationSection parent, @NotNull String path) {
/*  61 */     Validate.notNull(parent, "Parent cannot be null");
/*  62 */     Validate.notNull(path, "Path cannot be null");
/*     */     
/*  64 */     this.path = path;
/*  65 */     this.parent = parent;
/*  66 */     this.root = parent.getRoot();
/*     */     
/*  68 */     Validate.notNull(this.root, "Path cannot be orphaned");
/*     */     
/*  70 */     this.fullPath = createPath(parent, path);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Set<String> getKeys(boolean deep) {
/*  76 */     Set<String> result = new LinkedHashSet<>();
/*     */     
/*  78 */     Configuration root = getRoot();
/*  79 */     if (root != null && root.options().copyDefaults()) {
/*  80 */       ConfigurationSection defaults = getDefaultSection();
/*     */       
/*  82 */       if (defaults != null) {
/*  83 */         result.addAll(defaults.getKeys(deep));
/*     */       }
/*     */     } 
/*     */     
/*  87 */     mapChildrenKeys(result, this, deep);
/*     */     
/*  89 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Map<String, Object> getValues(boolean deep) {
/*  95 */     Map<String, Object> result = new LinkedHashMap<>();
/*     */     
/*  97 */     Configuration root = getRoot();
/*  98 */     if (root != null && root.options().copyDefaults()) {
/*  99 */       ConfigurationSection defaults = getDefaultSection();
/*     */       
/* 101 */       if (defaults != null) {
/* 102 */         result.putAll(defaults.getValues(deep));
/*     */       }
/*     */     } 
/*     */     
/* 106 */     mapChildrenValues(result, this, deep);
/*     */     
/* 108 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(@NotNull String path) {
/* 113 */     return contains(path, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(@NotNull String path, boolean ignoreDefault) {
/* 118 */     return ((ignoreDefault ? get(path, null) : get(path)) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSet(@NotNull String path) {
/* 123 */     Configuration root = getRoot();
/* 124 */     if (root == null) {
/* 125 */       return false;
/*     */     }
/* 127 */     if (root.options().copyDefaults()) {
/* 128 */       return contains(path);
/*     */     }
/* 130 */     return (get(path, null) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getCurrentPath() {
/* 136 */     return this.fullPath;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getName() {
/* 142 */     return this.path;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Configuration getRoot() {
/* 148 */     return this.root;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ConfigurationSection getParent() {
/* 154 */     return this.parent;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addDefault(@NotNull String path, @Nullable Object value) {
/* 159 */     Validate.notNull(path, "Path cannot be null");
/*     */     
/* 161 */     Configuration root = getRoot();
/* 162 */     if (root == null) {
/* 163 */       throw new IllegalStateException("Cannot add default without root");
/*     */     }
/* 165 */     if (root == this) {
/* 166 */       throw new UnsupportedOperationException("Unsupported addDefault(String, Object) implementation");
/*     */     }
/* 168 */     root.addDefault(createPath(this, path), value);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ConfigurationSection getDefaultSection() {
/* 174 */     Configuration root = getRoot();
/* 175 */     Configuration defaults = (root == null) ? null : root.getDefaults();
/*     */     
/* 177 */     if (defaults != null && 
/* 178 */       defaults.isConfigurationSection(getCurrentPath())) {
/* 179 */       return defaults.getConfigurationSection(getCurrentPath());
/*     */     }
/*     */ 
/*     */     
/* 183 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(@NotNull String path, @Nullable Object value) {
/* 188 */     Validate.notEmpty(path, "Cannot set to an empty path");
/*     */     
/* 190 */     Configuration root = getRoot();
/* 191 */     if (root == null) {
/* 192 */       throw new IllegalStateException("Cannot use section without a root");
/*     */     }
/*     */     
/* 195 */     char separator = root.options().pathSeparator();
/*     */ 
/*     */     
/* 198 */     int i1 = -1;
/* 199 */     ConfigurationSection section = this; int i2;
/* 200 */     while ((i1 = path.indexOf(separator, i2 = i1 + 1)) != -1) {
/* 201 */       String node = path.substring(i2, i1);
/* 202 */       ConfigurationSection subSection = section.getConfigurationSection(node);
/* 203 */       if (subSection == null) {
/* 204 */         if (value == null) {
/*     */           return;
/*     */         }
/*     */         
/* 208 */         section = section.createSection(node); continue;
/*     */       } 
/* 210 */       section = subSection;
/*     */     } 
/*     */ 
/*     */     
/* 214 */     String key = path.substring(i2);
/* 215 */     if (section == this) {
/* 216 */       if (value == null) {
/* 217 */         this.map.remove(key);
/*     */       } else {
/* 219 */         this.map.put(key, value);
/*     */       } 
/*     */     } else {
/* 222 */       section.set(key, value);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Object get(@NotNull String path) {
/* 229 */     return get(path, getDefault(path));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Object get(@NotNull String path, @Nullable Object def) {
/* 235 */     Validate.notNull(path, "Path cannot be null");
/*     */     
/* 237 */     if (path.length() == 0) {
/* 238 */       return this;
/*     */     }
/*     */     
/* 241 */     Configuration root = getRoot();
/* 242 */     if (root == null) {
/* 243 */       throw new IllegalStateException("Cannot access section without a root");
/*     */     }
/*     */     
/* 246 */     char separator = root.options().pathSeparator();
/*     */ 
/*     */     
/* 249 */     int i1 = -1;
/* 250 */     ConfigurationSection section = this; int i2;
/* 251 */     while ((i1 = path.indexOf(separator, i2 = i1 + 1)) != -1) {
/* 252 */       section = section.getConfigurationSection(path.substring(i2, i1));
/* 253 */       if (section == null) {
/* 254 */         return def;
/*     */       }
/*     */     } 
/*     */     
/* 258 */     String key = path.substring(i2);
/* 259 */     if (section == this) {
/* 260 */       Object result = this.map.get(key);
/* 261 */       return (result == null) ? def : result;
/*     */     } 
/* 263 */     return section.get(key, def);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ConfigurationSection createSection(@NotNull String path) {
/* 269 */     Validate.notEmpty(path, "Cannot create section at empty path");
/* 270 */     Configuration root = getRoot();
/* 271 */     if (root == null) {
/* 272 */       throw new IllegalStateException("Cannot create section without a root");
/*     */     }
/*     */     
/* 275 */     char separator = root.options().pathSeparator();
/*     */ 
/*     */     
/* 278 */     int i1 = -1;
/* 279 */     ConfigurationSection section = this; int i2;
/* 280 */     while ((i1 = path.indexOf(separator, i2 = i1 + 1)) != -1) {
/* 281 */       String node = path.substring(i2, i1);
/* 282 */       ConfigurationSection subSection = section.getConfigurationSection(node);
/* 283 */       if (subSection == null) {
/* 284 */         section = section.createSection(node); continue;
/*     */       } 
/* 286 */       section = subSection;
/*     */     } 
/*     */ 
/*     */     
/* 290 */     String key = path.substring(i2);
/* 291 */     if (section == this) {
/* 292 */       ConfigurationSection result = new MemorySection(this, key);
/* 293 */       this.map.put(key, result);
/* 294 */       return result;
/*     */     } 
/* 296 */     return section.createSection(key);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ConfigurationSection createSection(@NotNull String path, @NotNull Map<?, ?> map) {
/* 302 */     ConfigurationSection section = createSection(path);
/*     */     
/* 304 */     for (Map.Entry<?, ?> entry : map.entrySet()) {
/* 305 */       if (entry.getValue() instanceof Map) {
/* 306 */         section.createSection(entry.getKey().toString(), (Map<?, ?>)entry.getValue()); continue;
/*     */       } 
/* 308 */       section.set(entry.getKey().toString(), entry.getValue());
/*     */     } 
/*     */ 
/*     */     
/* 312 */     return section;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public String getString(@NotNull String path) {
/* 319 */     Object def = getDefault(path);
/* 320 */     return getString(path, (def != null) ? def.toString() : null);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public String getString(@NotNull String path, @Nullable String def) {
/* 326 */     Object val = get(path, def);
/* 327 */     return (val != null) ? val.toString() : def;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isString(@NotNull String path) {
/* 332 */     Object val = get(path);
/* 333 */     return val instanceof String;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInt(@NotNull String path) {
/* 338 */     Object def = getDefault(path);
/* 339 */     return getInt(path, (def instanceof Number) ? NumberConversions.toInt(def) : 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInt(@NotNull String path, int def) {
/* 344 */     Object val = get(path, Integer.valueOf(def));
/* 345 */     return (val instanceof Number) ? NumberConversions.toInt(val) : def;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInt(@NotNull String path) {
/* 350 */     Object val = get(path);
/* 351 */     return val instanceof Integer;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getBoolean(@NotNull String path) {
/* 356 */     Object def = getDefault(path);
/* 357 */     return getBoolean(path, (def instanceof Boolean) ? ((Boolean)def).booleanValue() : false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getBoolean(@NotNull String path, boolean def) {
/* 362 */     Object val = get(path, Boolean.valueOf(def));
/* 363 */     return (val instanceof Boolean) ? ((Boolean)val).booleanValue() : def;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBoolean(@NotNull String path) {
/* 368 */     Object val = get(path);
/* 369 */     return val instanceof Boolean;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDouble(@NotNull String path) {
/* 374 */     Object def = getDefault(path);
/* 375 */     return getDouble(path, (def instanceof Number) ? NumberConversions.toDouble(def) : 0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDouble(@NotNull String path, double def) {
/* 380 */     Object val = get(path, Double.valueOf(def));
/* 381 */     return (val instanceof Number) ? NumberConversions.toDouble(val) : def;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDouble(@NotNull String path) {
/* 386 */     Object val = get(path);
/* 387 */     return val instanceof Double;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLong(@NotNull String path) {
/* 392 */     Object def = getDefault(path);
/* 393 */     return getLong(path, (def instanceof Number) ? NumberConversions.toLong(def) : 0L);
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLong(@NotNull String path, long def) {
/* 398 */     Object val = get(path, Long.valueOf(def));
/* 399 */     return (val instanceof Number) ? NumberConversions.toLong(val) : def;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLong(@NotNull String path) {
/* 404 */     Object val = get(path);
/* 405 */     return val instanceof Long;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public List<?> getList(@NotNull String path) {
/* 412 */     Object def = getDefault(path);
/* 413 */     return getList(path, (def instanceof List) ? (List)def : null);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public List<?> getList(@NotNull String path, @Nullable List<?> def) {
/* 419 */     Object val = get(path, def);
/* 420 */     return (val instanceof List) ? (List)val : def;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isList(@NotNull String path) {
/* 425 */     Object val = get(path);
/* 426 */     return val instanceof List;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<String> getStringList(@NotNull String path) {
/* 432 */     List<?> list = getList(path);
/*     */     
/* 434 */     if (list == null) {
/* 435 */       return new ArrayList<>(0);
/*     */     }
/*     */     
/* 438 */     List<String> result = new ArrayList<>();
/*     */     
/* 440 */     for (Object object : list) {
/* 441 */       if (object instanceof String || isPrimitiveWrapper(object)) {
/* 442 */         result.add(String.valueOf(object));
/*     */       }
/*     */     } 
/*     */     
/* 446 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<Integer> getIntegerList(@NotNull String path) {
/* 452 */     List<?> list = getList(path);
/*     */     
/* 454 */     if (list == null) {
/* 455 */       return new ArrayList<>(0);
/*     */     }
/*     */     
/* 458 */     List<Integer> result = new ArrayList<>();
/*     */     
/* 460 */     for (Object object : list) {
/* 461 */       if (object instanceof Integer) {
/* 462 */         result.add((Integer)object); continue;
/* 463 */       }  if (object instanceof String) {
/*     */         try {
/* 465 */           result.add(Integer.valueOf((String)object));
/* 466 */         } catch (Exception exception) {} continue;
/*     */       } 
/* 468 */       if (object instanceof Character) {
/* 469 */         result.add(Integer.valueOf(((Character)object).charValue())); continue;
/* 470 */       }  if (object instanceof Number) {
/* 471 */         result.add(Integer.valueOf(((Number)object).intValue()));
/*     */       }
/*     */     } 
/*     */     
/* 475 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<Boolean> getBooleanList(@NotNull String path) {
/* 481 */     List<?> list = getList(path);
/*     */     
/* 483 */     if (list == null) {
/* 484 */       return new ArrayList<>(0);
/*     */     }
/*     */     
/* 487 */     List<Boolean> result = new ArrayList<>();
/*     */     
/* 489 */     for (Object object : list) {
/* 490 */       if (object instanceof Boolean) {
/* 491 */         result.add((Boolean)object); continue;
/* 492 */       }  if (object instanceof String) {
/* 493 */         if (Boolean.TRUE.toString().equals(object)) {
/* 494 */           result.add(Boolean.valueOf(true)); continue;
/* 495 */         }  if (Boolean.FALSE.toString().equals(object)) {
/* 496 */           result.add(Boolean.valueOf(false));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 501 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<Double> getDoubleList(@NotNull String path) {
/* 507 */     List<?> list = getList(path);
/*     */     
/* 509 */     if (list == null) {
/* 510 */       return new ArrayList<>(0);
/*     */     }
/*     */     
/* 513 */     List<Double> result = new ArrayList<>();
/*     */     
/* 515 */     for (Object object : list) {
/* 516 */       if (object instanceof Double) {
/* 517 */         result.add((Double)object); continue;
/* 518 */       }  if (object instanceof String) {
/*     */         try {
/* 520 */           result.add(Double.valueOf((String)object));
/* 521 */         } catch (Exception exception) {} continue;
/*     */       } 
/* 523 */       if (object instanceof Character) {
/* 524 */         result.add(Double.valueOf(((Character)object).charValue())); continue;
/* 525 */       }  if (object instanceof Number) {
/* 526 */         result.add(Double.valueOf(((Number)object).doubleValue()));
/*     */       }
/*     */     } 
/*     */     
/* 530 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<Float> getFloatList(@NotNull String path) {
/* 536 */     List<?> list = getList(path);
/*     */     
/* 538 */     if (list == null) {
/* 539 */       return new ArrayList<>(0);
/*     */     }
/*     */     
/* 542 */     List<Float> result = new ArrayList<>();
/*     */     
/* 544 */     for (Object object : list) {
/* 545 */       if (object instanceof Float) {
/* 546 */         result.add((Float)object); continue;
/* 547 */       }  if (object instanceof String) {
/*     */         try {
/* 549 */           result.add(Float.valueOf((String)object));
/* 550 */         } catch (Exception exception) {} continue;
/*     */       } 
/* 552 */       if (object instanceof Character) {
/* 553 */         result.add(Float.valueOf(((Character)object).charValue())); continue;
/* 554 */       }  if (object instanceof Number) {
/* 555 */         result.add(Float.valueOf(((Number)object).floatValue()));
/*     */       }
/*     */     } 
/*     */     
/* 559 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<Long> getLongList(@NotNull String path) {
/* 565 */     List<?> list = getList(path);
/*     */     
/* 567 */     if (list == null) {
/* 568 */       return new ArrayList<>(0);
/*     */     }
/*     */     
/* 571 */     List<Long> result = new ArrayList<>();
/*     */     
/* 573 */     for (Object object : list) {
/* 574 */       if (object instanceof Long) {
/* 575 */         result.add((Long)object); continue;
/* 576 */       }  if (object instanceof String) {
/*     */         try {
/* 578 */           result.add(Long.valueOf((String)object));
/* 579 */         } catch (Exception exception) {} continue;
/*     */       } 
/* 581 */       if (object instanceof Character) {
/* 582 */         result.add(Long.valueOf(((Character)object).charValue())); continue;
/* 583 */       }  if (object instanceof Number) {
/* 584 */         result.add(Long.valueOf(((Number)object).longValue()));
/*     */       }
/*     */     } 
/*     */     
/* 588 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<Byte> getByteList(@NotNull String path) {
/* 594 */     List<?> list = getList(path);
/*     */     
/* 596 */     if (list == null) {
/* 597 */       return new ArrayList<>(0);
/*     */     }
/*     */     
/* 600 */     List<Byte> result = new ArrayList<>();
/*     */     
/* 602 */     for (Object object : list) {
/* 603 */       if (object instanceof Byte) {
/* 604 */         result.add((Byte)object); continue;
/* 605 */       }  if (object instanceof String) {
/*     */         try {
/* 607 */           result.add(Byte.valueOf((String)object));
/* 608 */         } catch (Exception exception) {} continue;
/*     */       } 
/* 610 */       if (object instanceof Character) {
/* 611 */         result.add(Byte.valueOf((byte)((Character)object).charValue())); continue;
/* 612 */       }  if (object instanceof Number) {
/* 613 */         result.add(Byte.valueOf(((Number)object).byteValue()));
/*     */       }
/*     */     } 
/*     */     
/* 617 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<Character> getCharacterList(@NotNull String path) {
/* 623 */     List<?> list = getList(path);
/*     */     
/* 625 */     if (list == null) {
/* 626 */       return new ArrayList<>(0);
/*     */     }
/*     */     
/* 629 */     List<Character> result = new ArrayList<>();
/*     */     
/* 631 */     for (Object object : list) {
/* 632 */       if (object instanceof Character) {
/* 633 */         result.add((Character)object); continue;
/* 634 */       }  if (object instanceof String) {
/* 635 */         String str = (String)object;
/*     */         
/* 637 */         if (str.length() == 1)
/* 638 */           result.add(Character.valueOf(str.charAt(0)));  continue;
/*     */       } 
/* 640 */       if (object instanceof Number) {
/* 641 */         result.add(Character.valueOf((char)((Number)object).intValue()));
/*     */       }
/*     */     } 
/*     */     
/* 645 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<Short> getShortList(@NotNull String path) {
/* 651 */     List<?> list = getList(path);
/*     */     
/* 653 */     if (list == null) {
/* 654 */       return new ArrayList<>(0);
/*     */     }
/*     */     
/* 657 */     List<Short> result = new ArrayList<>();
/*     */     
/* 659 */     for (Object object : list) {
/* 660 */       if (object instanceof Short) {
/* 661 */         result.add((Short)object); continue;
/* 662 */       }  if (object instanceof String) {
/*     */         try {
/* 664 */           result.add(Short.valueOf((String)object));
/* 665 */         } catch (Exception exception) {} continue;
/*     */       } 
/* 667 */       if (object instanceof Character) {
/* 668 */         result.add(Short.valueOf((short)((Character)object).charValue())); continue;
/* 669 */       }  if (object instanceof Number) {
/* 670 */         result.add(Short.valueOf(((Number)object).shortValue()));
/*     */       }
/*     */     } 
/*     */     
/* 674 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<Map<?, ?>> getMapList(@NotNull String path) {
/* 680 */     List<?> list = getList(path);
/* 681 */     List<Map<?, ?>> result = new ArrayList<>();
/*     */     
/* 683 */     if (list == null) {
/* 684 */       return result;
/*     */     }
/*     */     
/* 687 */     for (Object object : list) {
/* 688 */       if (object instanceof Map) {
/* 689 */         result.add((Map<?, ?>)object);
/*     */       }
/*     */     } 
/*     */     
/* 693 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public <T> T getObject(@NotNull String path, @NotNull Class<T> clazz) {
/* 700 */     Validate.notNull(clazz, "Class cannot be null");
/* 701 */     Object def = getDefault(path);
/* 702 */     return getObject(path, clazz, (def != null && clazz.isInstance(def)) ? clazz.cast(def) : null);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public <T> T getObject(@NotNull String path, @NotNull Class<T> clazz, @Nullable T def) {
/* 708 */     Validate.notNull(clazz, "Class cannot be null");
/* 709 */     Object val = get(path, def);
/* 710 */     return (val != null && clazz.isInstance(val)) ? clazz.cast(val) : def;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public <T extends ConfigurationSerializable> T getSerializable(@NotNull String path, @NotNull Class<T> clazz) {
/* 716 */     return (T)getObject(path, clazz);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public <T extends ConfigurationSerializable> T getSerializable(@NotNull String path, @NotNull Class<T> clazz, @Nullable T def) {
/* 722 */     return (T)getObject(path, clazz, (ConfigurationSerializable)def);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Vector getVector(@NotNull String path) {
/* 728 */     return getSerializable(path, Vector.class);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Vector getVector(@NotNull String path, @Nullable Vector def) {
/* 734 */     return getSerializable(path, Vector.class, def);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVector(@NotNull String path) {
/* 739 */     return (getSerializable(path, Vector.class) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public OfflinePlayer getOfflinePlayer(@NotNull String path) {
/* 745 */     return getSerializable(path, OfflinePlayer.class);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public OfflinePlayer getOfflinePlayer(@NotNull String path, @Nullable OfflinePlayer def) {
/* 751 */     return getSerializable(path, OfflinePlayer.class, def);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOfflinePlayer(@NotNull String path) {
/* 756 */     return (getSerializable(path, OfflinePlayer.class) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ItemStack getItemStack(@NotNull String path) {
/* 762 */     return getSerializable(path, ItemStack.class);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ItemStack getItemStack(@NotNull String path, @Nullable ItemStack def) {
/* 768 */     return getSerializable(path, ItemStack.class, def);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isItemStack(@NotNull String path) {
/* 773 */     return (getSerializable(path, ItemStack.class) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Color getColor(@NotNull String path) {
/* 779 */     return getSerializable(path, Color.class);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Color getColor(@NotNull String path, @Nullable Color def) {
/* 785 */     return getSerializable(path, Color.class, def);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isColor(@NotNull String path) {
/* 790 */     return (getSerializable(path, Color.class) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Location getLocation(@NotNull String path) {
/* 796 */     return getSerializable(path, Location.class);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Location getLocation(@NotNull String path, @Nullable Location def) {
/* 802 */     return getSerializable(path, Location.class, def);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLocation(@NotNull String path) {
/* 807 */     return (getSerializable(path, Location.class) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ConfigurationSection getConfigurationSection(@NotNull String path) {
/* 813 */     Object val = get(path, null);
/* 814 */     if (val != null) {
/* 815 */       return (val instanceof ConfigurationSection) ? (ConfigurationSection)val : null;
/*     */     }
/*     */     
/* 818 */     val = get(path, getDefault(path));
/* 819 */     return (val instanceof ConfigurationSection) ? createSection(path) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isConfigurationSection(@NotNull String path) {
/* 824 */     Object val = get(path);
/* 825 */     return val instanceof ConfigurationSection;
/*     */   }
/*     */   
/*     */   protected boolean isPrimitiveWrapper(@Nullable Object input) {
/* 829 */     return (input instanceof Integer || input instanceof Boolean || input instanceof Character || input instanceof Byte || input instanceof Short || input instanceof Double || input instanceof Long || input instanceof Float);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected Object getDefault(@NotNull String path) {
/* 837 */     Validate.notNull(path, "Path cannot be null");
/*     */     
/* 839 */     Configuration root = getRoot();
/* 840 */     Configuration defaults = (root == null) ? null : root.getDefaults();
/* 841 */     return (defaults == null) ? null : defaults.get(createPath(this, path));
/*     */   }
/*     */   
/*     */   protected void mapChildrenKeys(@NotNull Set<String> output, @NotNull ConfigurationSection section, boolean deep) {
/* 845 */     if (section instanceof MemorySection) {
/* 846 */       MemorySection sec = (MemorySection)section;
/*     */       
/* 848 */       for (Map.Entry<String, Object> entry : sec.map.entrySet()) {
/* 849 */         output.add(createPath(section, entry.getKey(), this));
/*     */         
/* 851 */         if (deep && entry.getValue() instanceof ConfigurationSection) {
/* 852 */           ConfigurationSection subsection = (ConfigurationSection)entry.getValue();
/* 853 */           mapChildrenKeys(output, subsection, deep);
/*     */         } 
/*     */       } 
/*     */     } else {
/* 857 */       Set<String> keys = section.getKeys(deep);
/*     */       
/* 859 */       for (String key : keys) {
/* 860 */         output.add(createPath(section, key, this));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void mapChildrenValues(@NotNull Map<String, Object> output, @NotNull ConfigurationSection section, boolean deep) {
/* 866 */     if (section instanceof MemorySection) {
/* 867 */       MemorySection sec = (MemorySection)section;
/*     */       
/* 869 */       for (Map.Entry<String, Object> entry : sec.map.entrySet()) {
/*     */ 
/*     */ 
/*     */         
/* 873 */         String childPath = createPath(section, entry.getKey(), this);
/* 874 */         output.remove(childPath);
/* 875 */         output.put(childPath, entry.getValue());
/*     */         
/* 877 */         if (entry.getValue() instanceof ConfigurationSection && 
/* 878 */           deep) {
/* 879 */           mapChildrenValues(output, (ConfigurationSection)entry.getValue(), deep);
/*     */         }
/*     */       } 
/*     */     } else {
/*     */       
/* 884 */       Map<String, Object> values = section.getValues(deep);
/*     */       
/* 886 */       for (Map.Entry<String, Object> entry : values.entrySet()) {
/* 887 */         output.put(createPath(section, entry.getKey(), this), entry.getValue());
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static String createPath(@NotNull ConfigurationSection section, @Nullable String key) {
/* 905 */     return createPath(section, key, (section == null) ? null : section.getRoot());
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
/*     */   @NotNull
/*     */   public static String createPath(@NotNull ConfigurationSection section, @Nullable String key, @Nullable ConfigurationSection relativeTo) {
/* 922 */     Validate.notNull(section, "Cannot create path without a section");
/* 923 */     Configuration root = section.getRoot();
/* 924 */     if (root == null) {
/* 925 */       throw new IllegalStateException("Cannot create path without a root");
/*     */     }
/* 927 */     char separator = root.options().pathSeparator();
/*     */     
/* 929 */     StringBuilder builder = new StringBuilder();
/* 930 */     if (section != null) {
/* 931 */       for (ConfigurationSection parent = section; parent != null && parent != relativeTo; parent = parent.getParent()) {
/* 932 */         if (builder.length() > 0) {
/* 933 */           builder.insert(0, separator);
/*     */         }
/*     */         
/* 936 */         builder.insert(0, parent.getName());
/*     */       } 
/*     */     }
/*     */     
/* 940 */     if (key != null && key.length() > 0) {
/* 941 */       if (builder.length() > 0) {
/* 942 */         builder.append(separator);
/*     */       }
/*     */       
/* 945 */       builder.append(key);
/*     */     } 
/*     */     
/* 948 */     return builder.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 953 */     Configuration root = getRoot();
/* 954 */     return 
/* 955 */       getClass().getSimpleName() + "[path='" + 
/*     */       
/* 957 */       getCurrentPath() + "', root='" + (
/*     */       
/* 959 */       (root == null) ? null : root.getClass().getSimpleName()) + "']";
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\configuration\MemorySection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */