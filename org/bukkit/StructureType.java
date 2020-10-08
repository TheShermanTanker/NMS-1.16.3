/*     */ package org.bukkit;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.map.MapCursor;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class StructureType
/*     */ {
/*  24 */   private static final Map<String, StructureType> structureTypeMap = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  33 */   public static final StructureType MINESHAFT = register(new StructureType("mineshaft", MapCursor.Type.RED_X));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   public static final StructureType VILLAGE = register(new StructureType("village", MapCursor.Type.MANSION));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   public static final StructureType NETHER_FORTRESS = register(new StructureType("fortress", MapCursor.Type.RED_X));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   public static final StructureType STRONGHOLD = register(new StructureType("stronghold", MapCursor.Type.MANSION));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   public static final StructureType JUNGLE_PYRAMID = register(new StructureType("jungle_pyramid", MapCursor.Type.RED_X));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   public static final StructureType OCEAN_RUIN = register(new StructureType("ocean_ruin", MapCursor.Type.TEMPLE));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public static final StructureType DESERT_PYRAMID = register(new StructureType("desert_pyramid", MapCursor.Type.RED_X));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   public static final StructureType IGLOO = register(new StructureType("igloo", MapCursor.Type.RED_X));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   public static final StructureType SWAMP_HUT = register(new StructureType("swamp_hut", MapCursor.Type.RED_X));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   public static final StructureType OCEAN_MONUMENT = register(new StructureType("monument", MapCursor.Type.TEMPLE));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   public static final StructureType END_CITY = register(new StructureType("endcity", MapCursor.Type.RED_X));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   public static final StructureType WOODLAND_MANSION = register(new StructureType("mansion", MapCursor.Type.MANSION));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 129 */   public static final StructureType BURIED_TREASURE = register(new StructureType("buried_treasure", MapCursor.Type.RED_X));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public static final StructureType SHIPWRECK = register(new StructureType("shipwreck", MapCursor.Type.RED_X));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 143 */   public static final StructureType PILLAGER_OUTPOST = register(new StructureType("pillager_outpost", MapCursor.Type.RED_X));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   public static final StructureType NETHER_FOSSIL = register(new StructureType("nether_fossil", MapCursor.Type.RED_X));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 153 */   public static final StructureType RUINED_PORTAL = register(new StructureType("ruined_portal", MapCursor.Type.RED_X));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 158 */   public static final StructureType BASTION_REMNANT = register(new StructureType("bastion_remnant", MapCursor.Type.RED_X));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final String name;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final MapCursor.Type mapCursor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private StructureType(@NotNull String name, @Nullable MapCursor.Type mapIcon) {
/* 178 */     Validate.notEmpty(name, "Structure name cannot be empty");
/* 179 */     this.name = name;
/* 180 */     this.mapCursor = mapIcon;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getName() {
/* 191 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public MapCursor.Type getMapIcon() {
/* 202 */     return this.mapCursor;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object other) {
/* 207 */     if (this == other) {
/* 208 */       return true;
/*     */     }
/* 210 */     if (!(other instanceof StructureType)) {
/* 211 */       return false;
/*     */     }
/* 213 */     StructureType that = (StructureType)other;
/* 214 */     return (this.name.equals(that.name) && this.mapCursor == that.mapCursor);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 219 */     int hash = 7;
/* 220 */     hash = 71 * hash + Objects.hashCode(this.name);
/* 221 */     hash = 71 * hash + Objects.hashCode(this.mapCursor);
/* 222 */     return hash;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 227 */     return "StructureType{name=" + this.name + ", cursor=" + this.mapCursor + "}";
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   private static <T extends StructureType> T register(@NotNull T type) {
/* 232 */     Preconditions.checkNotNull(type, "Cannot register null StructureType.");
/* 233 */     Preconditions.checkArgument(!structureTypeMap.containsKey(type.getName()), "Cannot register same StructureType twice. %s", type.getName());
/* 234 */     structureTypeMap.put(type.getName(), (StructureType)type);
/* 235 */     return type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static Map<String, StructureType> getStructureTypes() {
/* 245 */     return (Map<String, StructureType>)ImmutableMap.copyOf(structureTypeMap);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\StructureType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */