/*     */ package net.md_5.bungee.api;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import java.awt.Color;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ChatColor
/*     */ {
/*     */   public static final char COLOR_CHAR = '§';
/*     */   public static final String ALL_CODES = "0123456789AaBbCcDdEeFfKkLlMmNnOoRrXx";
/*  27 */   public static final Pattern STRIP_COLOR_PATTERN = Pattern.compile("(?i)" + String.valueOf('§') + "[0-9A-FK-ORX]");
/*     */ 
/*     */ 
/*     */   
/*  31 */   private static final Map<Character, ChatColor> BY_CHAR = new HashMap<>();
/*     */ 
/*     */ 
/*     */   
/*  35 */   private static final Map<String, ChatColor> BY_NAME = new HashMap<>();
/*     */ 
/*     */ 
/*     */   
/*  39 */   public static final ChatColor BLACK = new ChatColor('0', "black", new Color(0));
/*     */ 
/*     */ 
/*     */   
/*  43 */   public static final ChatColor DARK_BLUE = new ChatColor('1', "dark_blue", new Color(170));
/*     */ 
/*     */ 
/*     */   
/*  47 */   public static final ChatColor DARK_GREEN = new ChatColor('2', "dark_green", new Color(43520));
/*     */ 
/*     */ 
/*     */   
/*  51 */   public static final ChatColor DARK_AQUA = new ChatColor('3', "dark_aqua", new Color(43690));
/*     */ 
/*     */ 
/*     */   
/*  55 */   public static final ChatColor DARK_RED = new ChatColor('4', "dark_red", new Color(11141120));
/*     */ 
/*     */ 
/*     */   
/*  59 */   public static final ChatColor DARK_PURPLE = new ChatColor('5', "dark_purple", new Color(11141290));
/*     */ 
/*     */ 
/*     */   
/*  63 */   public static final ChatColor GOLD = new ChatColor('6', "gold", new Color(16755200));
/*     */ 
/*     */ 
/*     */   
/*  67 */   public static final ChatColor GRAY = new ChatColor('7', "gray", new Color(11184810));
/*     */ 
/*     */ 
/*     */   
/*  71 */   public static final ChatColor DARK_GRAY = new ChatColor('8', "dark_gray", new Color(5592405));
/*     */ 
/*     */ 
/*     */   
/*  75 */   public static final ChatColor BLUE = new ChatColor('9', "blue", new Color(5592575));
/*     */ 
/*     */ 
/*     */   
/*  79 */   public static final ChatColor GREEN = new ChatColor('a', "green", new Color(5635925));
/*     */ 
/*     */ 
/*     */   
/*  83 */   public static final ChatColor AQUA = new ChatColor('b', "aqua", new Color(5636095));
/*     */ 
/*     */ 
/*     */   
/*  87 */   public static final ChatColor RED = new ChatColor('c', "red", new Color(16733525));
/*     */ 
/*     */ 
/*     */   
/*  91 */   public static final ChatColor LIGHT_PURPLE = new ChatColor('d', "light_purple", new Color(16733695));
/*     */ 
/*     */ 
/*     */   
/*  95 */   public static final ChatColor YELLOW = new ChatColor('e', "yellow", new Color(16777045));
/*     */ 
/*     */ 
/*     */   
/*  99 */   public static final ChatColor WHITE = new ChatColor('f', "white", new Color(16777215));
/*     */ 
/*     */ 
/*     */   
/* 103 */   public static final ChatColor MAGIC = new ChatColor('k', "obfuscated");
/*     */ 
/*     */ 
/*     */   
/* 107 */   public static final ChatColor BOLD = new ChatColor('l', "bold");
/*     */ 
/*     */ 
/*     */   
/* 111 */   public static final ChatColor STRIKETHROUGH = new ChatColor('m', "strikethrough");
/*     */ 
/*     */ 
/*     */   
/* 115 */   public static final ChatColor UNDERLINE = new ChatColor('n', "underline");
/*     */ 
/*     */ 
/*     */   
/* 119 */   public static final ChatColor ITALIC = new ChatColor('o', "italic");
/*     */ 
/*     */ 
/*     */   
/* 123 */   public static final ChatColor RESET = new ChatColor('r', "reset");
/*     */ 
/*     */ 
/*     */   
/* 127 */   private static int count = 0;
/*     */   private final String toString;
/*     */   private final String name;
/*     */   
/*     */   public String getName() {
/* 132 */     return this.name;
/*     */   }
/*     */   
/*     */   private final int ordinal;
/*     */   
/*     */   public Color getColor() {
/* 138 */     return this.color;
/*     */   }
/*     */   private final Color color;
/*     */   
/*     */   private ChatColor(char code, String name) {
/* 143 */     this(code, name, (Color)null);
/*     */   }
/*     */ 
/*     */   
/*     */   private ChatColor(char code, String name, Color color) {
/* 148 */     this.name = name;
/* 149 */     this.toString = new String(new char[] { '§', code });
/*     */ 
/*     */ 
/*     */     
/* 153 */     this.ordinal = count++;
/* 154 */     this.color = color;
/*     */     
/* 156 */     BY_CHAR.put(Character.valueOf(code), this);
/* 157 */     BY_NAME.put(name.toUpperCase(Locale.ROOT), this);
/*     */   }
/*     */ 
/*     */   
/*     */   private ChatColor(String name, String toString, int rgb) {
/* 162 */     this.name = name;
/* 163 */     this.toString = toString;
/* 164 */     this.ordinal = -1;
/* 165 */     this.color = new Color(rgb);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 171 */     int hash = 7;
/* 172 */     hash = 53 * hash + Objects.hashCode(this.toString);
/* 173 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 179 */     if (this == obj)
/*     */     {
/* 181 */       return true;
/*     */     }
/* 183 */     if (obj == null || getClass() != obj.getClass())
/*     */     {
/* 185 */       return false;
/*     */     }
/* 187 */     ChatColor other = (ChatColor)obj;
/*     */     
/* 189 */     return Objects.equals(this.toString, other.toString);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 195 */     return this.toString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String stripColor(String input) {
/* 206 */     if (input == null)
/*     */     {
/* 208 */       return null;
/*     */     }
/*     */     
/* 211 */     return STRIP_COLOR_PATTERN.matcher(input).replaceAll("");
/*     */   }
/*     */ 
/*     */   
/*     */   public static String translateAlternateColorCodes(char altColorChar, String textToTranslate) {
/* 216 */     char[] b = textToTranslate.toCharArray();
/* 217 */     for (int i = 0; i < b.length - 1; i++) {
/*     */       
/* 219 */       if (b[i] == altColorChar && "0123456789AaBbCcDdEeFfKkLlMmNnOoRrXx".indexOf(b[i + 1]) > -1) {
/*     */         
/* 221 */         b[i] = '§';
/* 222 */         b[i + 1] = Character.toLowerCase(b[i + 1]);
/*     */       } 
/*     */     } 
/* 225 */     return new String(b);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ChatColor getByChar(char code) {
/* 236 */     return BY_CHAR.get(Character.valueOf(code));
/*     */   }
/*     */ 
/*     */   
/*     */   public static ChatColor of(Color color) {
/* 241 */     return of("#" + Integer.toHexString(color.getRGB()).substring(2));
/*     */   }
/*     */ 
/*     */   
/*     */   public static ChatColor of(String string) {
/* 246 */     Preconditions.checkArgument((string != null), "string cannot be null");
/* 247 */     if (string.startsWith("#") && string.length() == 7) {
/*     */       int rgb;
/*     */ 
/*     */       
/*     */       try {
/* 252 */         rgb = Integer.parseInt(string.substring(1), 16);
/* 253 */       } catch (NumberFormatException ex) {
/*     */         
/* 255 */         throw new IllegalArgumentException("Illegal hex string " + string);
/*     */       } 
/*     */       
/* 258 */       StringBuilder magic = new StringBuilder("§x");
/* 259 */       for (char c : string.substring(1).toCharArray())
/*     */       {
/* 261 */         magic.append('§').append(c);
/*     */       }
/*     */       
/* 264 */       return new ChatColor(string, magic.toString(), rgb);
/*     */     } 
/*     */     
/* 267 */     ChatColor defined = BY_NAME.get(string.toUpperCase(Locale.ROOT));
/* 268 */     if (defined != null)
/*     */     {
/* 270 */       return defined;
/*     */     }
/*     */     
/* 273 */     throw new IllegalArgumentException("Could not parse ChatColor " + string);
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
/*     */   @Deprecated
/*     */   public static ChatColor valueOf(String name) {
/* 286 */     Preconditions.checkNotNull(name, "Name is null");
/*     */     
/* 288 */     ChatColor defined = BY_NAME.get(name);
/* 289 */     Preconditions.checkArgument((defined != null), "No enum constant " + ChatColor.class.getName() + "." + name);
/*     */     
/* 291 */     return defined;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static ChatColor[] values() {
/* 303 */     return (ChatColor[])BY_CHAR.values().toArray((Object[])new ChatColor[BY_CHAR.values().size()]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public String name() {
/* 315 */     return getName().toUpperCase(Locale.ROOT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public int ordinal() {
/* 327 */     Preconditions.checkArgument((this.ordinal >= 0), "Cannot get ordinal of hex color");
/* 328 */     return this.ordinal;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\md_5\bungee\api\ChatColor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */