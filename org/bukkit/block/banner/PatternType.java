/*    */ package org.bukkit.block.banner;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.jetbrains.annotations.Contract;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public enum PatternType {
/* 10 */   BASE("b"),
/* 11 */   SQUARE_BOTTOM_LEFT("bl"),
/* 12 */   SQUARE_BOTTOM_RIGHT("br"),
/* 13 */   SQUARE_TOP_LEFT("tl"),
/* 14 */   SQUARE_TOP_RIGHT("tr"),
/* 15 */   STRIPE_BOTTOM("bs"),
/* 16 */   STRIPE_TOP("ts"),
/* 17 */   STRIPE_LEFT("ls"),
/* 18 */   STRIPE_RIGHT("rs"),
/* 19 */   STRIPE_CENTER("cs"),
/* 20 */   STRIPE_MIDDLE("ms"),
/* 21 */   STRIPE_DOWNRIGHT("drs"),
/* 22 */   STRIPE_DOWNLEFT("dls"),
/* 23 */   STRIPE_SMALL("ss"),
/* 24 */   CROSS("cr"),
/* 25 */   STRAIGHT_CROSS("sc"),
/* 26 */   TRIANGLE_BOTTOM("bt"),
/* 27 */   TRIANGLE_TOP("tt"),
/* 28 */   TRIANGLES_BOTTOM("bts"),
/* 29 */   TRIANGLES_TOP("tts"),
/* 30 */   DIAGONAL_LEFT("ld"),
/* 31 */   DIAGONAL_RIGHT("rd"),
/* 32 */   DIAGONAL_LEFT_MIRROR("lud"),
/* 33 */   DIAGONAL_RIGHT_MIRROR("rud"),
/* 34 */   CIRCLE_MIDDLE("mc"),
/* 35 */   RHOMBUS_MIDDLE("mr"),
/* 36 */   HALF_VERTICAL("vh"),
/* 37 */   HALF_HORIZONTAL("hh"),
/* 38 */   HALF_VERTICAL_MIRROR("vhr"),
/* 39 */   HALF_HORIZONTAL_MIRROR("hhb"),
/* 40 */   BORDER("bo"),
/* 41 */   CURLY_BORDER("cbo"),
/* 42 */   CREEPER("cre"),
/* 43 */   GRADIENT("gra"),
/* 44 */   GRADIENT_UP("gru"),
/* 45 */   BRICKS("bri"),
/* 46 */   SKULL("sku"),
/* 47 */   FLOWER("flo"),
/* 48 */   MOJANG("moj"),
/* 49 */   GLOBE("glb"),
/* 50 */   PIGLIN("pig"); private final String identifier;
/*    */   
/*    */   static {
/* 53 */     byString = new HashMap<>();
/*    */ 
/*    */     
/* 56 */     for (PatternType p : values())
/* 57 */       byString.put(p.identifier, p); 
/*    */   }
/*    */   private static final Map<String, PatternType> byString;
/*    */   
/*    */   PatternType(String key) {
/* 62 */     this.identifier = key;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public String getIdentifier() {
/* 73 */     return this.identifier;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Contract("null -> null")
/*    */   @Nullable
/*    */   public static PatternType getByIdentifier(@Nullable String identifier) {
/* 86 */     return byString.get(identifier);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\banner\PatternType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */