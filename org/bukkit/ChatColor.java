/*     */ package org.bukkit;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Pattern;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.jetbrains.annotations.Contract;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum ChatColor
/*     */ {
/*  18 */   BLACK('0', 0)
/*     */   {
/*     */     @NotNull
/*     */     public net.md_5.bungee.api.ChatColor asBungee() {
/*  22 */       return net.md_5.bungee.api.ChatColor.BLACK;
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */   
/*  28 */   DARK_BLUE('1', 1)
/*     */   {
/*     */     @NotNull
/*     */     public net.md_5.bungee.api.ChatColor asBungee() {
/*  32 */       return net.md_5.bungee.api.ChatColor.DARK_BLUE;
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */   
/*  38 */   DARK_GREEN('2', 2)
/*     */   {
/*     */     @NotNull
/*     */     public net.md_5.bungee.api.ChatColor asBungee() {
/*  42 */       return net.md_5.bungee.api.ChatColor.DARK_GREEN;
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */   
/*  48 */   DARK_AQUA('3', 3)
/*     */   {
/*     */     @NotNull
/*     */     public net.md_5.bungee.api.ChatColor asBungee() {
/*  52 */       return net.md_5.bungee.api.ChatColor.DARK_AQUA;
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */   
/*  58 */   DARK_RED('4', 4)
/*     */   {
/*     */     @NotNull
/*     */     public net.md_5.bungee.api.ChatColor asBungee() {
/*  62 */       return net.md_5.bungee.api.ChatColor.DARK_RED;
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */   
/*  68 */   DARK_PURPLE('5', 5)
/*     */   {
/*     */     @NotNull
/*     */     public net.md_5.bungee.api.ChatColor asBungee() {
/*  72 */       return net.md_5.bungee.api.ChatColor.DARK_PURPLE;
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */   
/*  78 */   GOLD('6', 6)
/*     */   {
/*     */     @NotNull
/*     */     public net.md_5.bungee.api.ChatColor asBungee() {
/*  82 */       return net.md_5.bungee.api.ChatColor.GOLD;
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */   
/*  88 */   GRAY('7', 7)
/*     */   {
/*     */     @NotNull
/*     */     public net.md_5.bungee.api.ChatColor asBungee() {
/*  92 */       return net.md_5.bungee.api.ChatColor.GRAY;
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */   
/*  98 */   DARK_GRAY('8', 8)
/*     */   {
/*     */     @NotNull
/*     */     public net.md_5.bungee.api.ChatColor asBungee() {
/* 102 */       return net.md_5.bungee.api.ChatColor.DARK_GRAY;
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */   
/* 108 */   BLUE('9', 9)
/*     */   {
/*     */     @NotNull
/*     */     public net.md_5.bungee.api.ChatColor asBungee() {
/* 112 */       return net.md_5.bungee.api.ChatColor.BLUE;
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */   
/* 118 */   GREEN('a', 10)
/*     */   {
/*     */     @NotNull
/*     */     public net.md_5.bungee.api.ChatColor asBungee() {
/* 122 */       return net.md_5.bungee.api.ChatColor.GREEN;
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */   
/* 128 */   AQUA('b', 11)
/*     */   {
/*     */     @NotNull
/*     */     public net.md_5.bungee.api.ChatColor asBungee() {
/* 132 */       return net.md_5.bungee.api.ChatColor.AQUA;
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */   
/* 138 */   RED('c', 12)
/*     */   {
/*     */     @NotNull
/*     */     public net.md_5.bungee.api.ChatColor asBungee() {
/* 142 */       return net.md_5.bungee.api.ChatColor.RED;
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */   
/* 148 */   LIGHT_PURPLE('d', 13)
/*     */   {
/*     */     @NotNull
/*     */     public net.md_5.bungee.api.ChatColor asBungee() {
/* 152 */       return net.md_5.bungee.api.ChatColor.LIGHT_PURPLE;
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */   
/* 158 */   YELLOW('e', 14)
/*     */   {
/*     */     @NotNull
/*     */     public net.md_5.bungee.api.ChatColor asBungee() {
/* 162 */       return net.md_5.bungee.api.ChatColor.YELLOW;
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */   
/* 168 */   WHITE('f', 15)
/*     */   {
/*     */     @NotNull
/*     */     public net.md_5.bungee.api.ChatColor asBungee() {
/* 172 */       return net.md_5.bungee.api.ChatColor.WHITE;
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */   
/* 178 */   MAGIC('k', 16, true)
/*     */   {
/*     */     @NotNull
/*     */     public net.md_5.bungee.api.ChatColor asBungee() {
/* 182 */       return net.md_5.bungee.api.ChatColor.MAGIC;
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */   
/* 188 */   BOLD('l', 17, true)
/*     */   {
/*     */     @NotNull
/*     */     public net.md_5.bungee.api.ChatColor asBungee() {
/* 192 */       return net.md_5.bungee.api.ChatColor.BOLD;
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */   
/* 198 */   STRIKETHROUGH('m', 18, true)
/*     */   {
/*     */     @NotNull
/*     */     public net.md_5.bungee.api.ChatColor asBungee() {
/* 202 */       return net.md_5.bungee.api.ChatColor.STRIKETHROUGH;
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */   
/* 208 */   UNDERLINE('n', 19, true)
/*     */   {
/*     */     @NotNull
/*     */     public net.md_5.bungee.api.ChatColor asBungee() {
/* 212 */       return net.md_5.bungee.api.ChatColor.UNDERLINE;
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */   
/* 218 */   ITALIC('o', 20, true)
/*     */   {
/*     */     @NotNull
/*     */     public net.md_5.bungee.api.ChatColor asBungee() {
/* 222 */       return net.md_5.bungee.api.ChatColor.ITALIC;
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */   
/* 228 */   RESET('r', 21)
/*     */   {
/*     */     @NotNull
/*     */     public net.md_5.bungee.api.ChatColor asBungee() {
/* 232 */       return net.md_5.bungee.api.ChatColor.RESET;
/*     */     } };
/*     */   
/*     */   public static final char COLOR_CHAR = '§';
/*     */   private static final Pattern STRIP_COLOR_PATTERN;
/*     */   private final int intCode;
/*     */   private final char code;
/*     */   
/*     */   static {
/* 241 */     STRIP_COLOR_PATTERN = Pattern.compile("(?i)" + String.valueOf('§') + "[0-9A-FK-ORX]");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 247 */     BY_ID = Maps.newHashMap();
/* 248 */     BY_CHAR = Maps.newHashMap();
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
/* 366 */     HEX_COLOR_PATTERN = Pattern.compile("§x(?>§[0-9a-f]){6}", 2);
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
/* 411 */     for (ChatColor color : values()) {
/* 412 */       BY_ID.put(Integer.valueOf(color.intCode), color);
/* 413 */       BY_CHAR.put(Character.valueOf(color.code), color);
/*     */     } 
/*     */   }
/*     */   
/*     */   private final boolean isFormat;
/*     */   private final String toString;
/*     */   private static final Map<Integer, ChatColor> BY_ID;
/*     */   private static final Map<Character, ChatColor> BY_CHAR;
/*     */   private static final Pattern HEX_COLOR_PATTERN;
/*     */   
/*     */   ChatColor(char code, int intCode, boolean isFormat) {
/*     */     this.code = code;
/*     */     this.intCode = intCode;
/*     */     this.isFormat = isFormat;
/*     */     this.toString = new String(new char[] { '§', code });
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public net.md_5.bungee.api.ChatColor asBungee() {
/*     */     return net.md_5.bungee.api.ChatColor.RESET;
/*     */   }
/*     */   
/*     */   public char getChar() {
/*     */     return this.code;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public String toString() {
/*     */     return this.toString;
/*     */   }
/*     */   
/*     */   public boolean isFormat() {
/*     */     return this.isFormat;
/*     */   }
/*     */   
/*     */   public boolean isColor() {
/*     */     return (!this.isFormat && this != RESET);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static ChatColor getByChar(char code) {
/*     */     return BY_CHAR.get(Character.valueOf(code));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static ChatColor getByChar(@NotNull String code) {
/*     */     Validate.notNull(code, "Code cannot be null");
/*     */     Validate.isTrue((code.length() > 0), "Code must have at least one char");
/*     */     return BY_CHAR.get(Character.valueOf(code.charAt(0)));
/*     */   }
/*     */   
/*     */   @Contract("!null -> !null; null -> null")
/*     */   @Nullable
/*     */   public static String stripColor(@Nullable String input) {
/*     */     if (input == null)
/*     */       return null; 
/*     */     return STRIP_COLOR_PATTERN.matcher(input).replaceAll("");
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static String translateAlternateColorCodes(char altColorChar, @NotNull String textToTranslate) {
/*     */     Validate.notNull(textToTranslate, "Cannot translate null text");
/*     */     char[] b = textToTranslate.toCharArray();
/*     */     for (int i = 0; i < b.length - 1; i++) {
/*     */       if (b[i] == altColorChar && "0123456789AaBbCcDdEeFfKkLlMmNnOoRrXx".indexOf(b[i + 1]) > -1) {
/*     */         b[i] = '§';
/*     */         b[i + 1] = Character.toLowerCase(b[i + 1]);
/*     */       } 
/*     */     } 
/*     */     return new String(b);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static String getLastColors(@NotNull String input) {
/*     */     Validate.notNull(input, "Cannot get last colors from null text");
/*     */     String result = "";
/*     */     int length = input.length();
/*     */     for (int index = length - 1; index > -1; index--) {
/*     */       char section = input.charAt(index);
/*     */       if (section == '§' && index < length - 1) {
/*     */         if (index > 11 && input.charAt(index - 12) == '§' && (input.charAt(index - 11) == 'x' || input.charAt(index - 11) == 'X')) {
/*     */           String str = input.substring(index - 12, index + 2);
/*     */           if (HEX_COLOR_PATTERN.matcher(str).matches()) {
/*     */             result = str + result;
/*     */             break;
/*     */           } 
/*     */         } 
/*     */         char c = input.charAt(index + 1);
/*     */         ChatColor color = getByChar(c);
/*     */         if (color != null) {
/*     */           result = color.toString() + result;
/*     */           if (color.isColor() || color.equals(RESET))
/*     */             break; 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     return result;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\ChatColor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */