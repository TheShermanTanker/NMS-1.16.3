/*    */ package org.bukkit;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Locale;
/*    */ import java.util.Map;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ public enum WorldType
/*    */ {
/* 12 */   NORMAL("DEFAULT"),
/* 13 */   FLAT("FLAT"),
/* 14 */   LARGE_BIOMES("LARGEBIOMES"),
/* 15 */   AMPLIFIED("AMPLIFIED");
/*    */   static {
/* 17 */     BY_NAME = Maps.newHashMap();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 46 */     for (WorldType type : values())
/* 47 */       BY_NAME.put(type.name, type); 
/*    */   }
/*    */   
/*    */   private static final Map<String, WorldType> BY_NAME;
/*    */   private final String name;
/*    */   
/*    */   WorldType(String name) {
/*    */     this.name = name;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public String getName() {
/*    */     return this.name;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public static WorldType getByName(@NotNull String name) {
/*    */     return BY_NAME.get(name.toUpperCase(Locale.ENGLISH));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\WorldType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */