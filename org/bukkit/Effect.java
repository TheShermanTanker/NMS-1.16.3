/*     */ package org.bukkit;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.potion.Potion;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum Effect
/*     */ {
/*     */   private final int id;
/*     */   private final Type type;
/*     */   private final Class<?> data;
/*  17 */   CLICK2(1000, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/*  21 */   CLICK1(1001, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/*  25 */   BOW_FIRE(1002, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/*  29 */   DOOR_TOGGLE(1006, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/*  33 */   IRON_DOOR_TOGGLE(1005, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/*  37 */   TRAPDOOR_TOGGLE(1007, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/*  41 */   IRON_TRAPDOOR_TOGGLE(1037, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/*  45 */   FENCE_GATE_TOGGLE(1008, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/*  49 */   DOOR_CLOSE(1012, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/*  53 */   IRON_DOOR_CLOSE(1011, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/*  57 */   TRAPDOOR_CLOSE(1013, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/*  61 */   IRON_TRAPDOOR_CLOSE(1036, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/*  65 */   FENCE_GATE_CLOSE(1014, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/*  69 */   EXTINGUISH(1009, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/*  73 */   RECORD_PLAY(1010, Type.SOUND, Material.class),
/*     */ 
/*     */ 
/*     */   
/*  77 */   GHAST_SHRIEK(1015, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/*  81 */   GHAST_SHOOT(1016, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/*  85 */   BLAZE_SHOOT(1018, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/*  89 */   ZOMBIE_CHEW_WOODEN_DOOR(1019, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/*  93 */   ZOMBIE_CHEW_IRON_DOOR(1020, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/*  97 */   ZOMBIE_DESTROY_DOOR(1021, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/* 101 */   SMOKE(2000, Type.VISUAL, BlockFace.class),
/*     */ 
/*     */ 
/*     */   
/* 105 */   STEP_SOUND(2001, Type.SOUND, Material.class),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 110 */   POTION_BREAK(2002, Type.VISUAL, Potion.class),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   INSTANT_POTION_BREAK(2007, Type.VISUAL, Color.class),
/*     */ 
/*     */ 
/*     */   
/* 119 */   ENDER_SIGNAL(2003, Type.VISUAL),
/*     */ 
/*     */ 
/*     */   
/* 123 */   MOBSPAWNER_FLAMES(2004, Type.VISUAL),
/*     */ 
/*     */ 
/*     */   
/* 127 */   BREWING_STAND_BREW(1035, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/* 131 */   CHORUS_FLOWER_GROW(1033, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/* 135 */   CHORUS_FLOWER_DEATH(1034, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/* 139 */   PORTAL_TRAVEL(1032, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/* 143 */   ENDEREYE_LAUNCH(1003, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/* 147 */   FIREWORK_SHOOT(1004, Type.SOUND),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 152 */   VILLAGER_PLANT_GROW(2005, Type.VISUAL, Integer.class),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 157 */   DRAGON_BREATH(2006, Type.VISUAL),
/*     */ 
/*     */ 
/*     */   
/* 161 */   ANVIL_BREAK(1029, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/* 165 */   ANVIL_USE(1030, Type.SOUND),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 170 */   ANVIL_LAND(1031, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/* 174 */   ENDERDRAGON_SHOOT(1017, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/* 178 */   WITHER_BREAK_BLOCK(1022, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/* 182 */   WITHER_SHOOT(1024, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/* 186 */   ZOMBIE_INFECT(1026, Type.SOUND),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 191 */   ZOMBIE_CONVERTED_VILLAGER(1027, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/* 195 */   BAT_TAKEOFF(1025, Type.SOUND),
/*     */ 
/*     */ 
/*     */   
/* 199 */   END_GATEWAY_SPAWN(3000, Type.VISUAL),
/*     */ 
/*     */ 
/*     */   
/* 203 */   ENDERDRAGON_GROWL(3001, Type.SOUND);
/*     */   private static final Map<Integer, Effect> BY_ID;
/*     */   Effect(int id, Type type, Class<?> data) { this.id = id;
/*     */     this.type = type;
/*     */     this.data = data; }
/*     */   @Deprecated public int getId() { return this.id; } @NotNull
/* 209 */   public Type getType() { return this.type; } static { BY_ID = Maps.newHashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 263 */     for (Effect effect : values())
/* 264 */       BY_ID.put(Integer.valueOf(effect.id), effect);  } @Nullable
/*     */   public Class<?> getData() {
/*     */     return this.data;
/*     */   } @Deprecated
/*     */   @Nullable
/*     */   public static Effect getById(int id) {
/*     */     return BY_ID.get(Integer.valueOf(id));
/* 271 */   } public enum Type { SOUND, VISUAL; }
/*     */ 
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\Effect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */