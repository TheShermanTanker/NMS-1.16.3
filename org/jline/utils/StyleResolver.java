/*     */ package org.jline.utils;
/*     */ 
/*     */ import java.util.Locale;
/*     */ import java.util.Objects;
/*     */ import java.util.function.Function;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
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
/*     */ public class StyleResolver
/*     */ {
/*  27 */   private static final Logger log = Logger.getLogger(StyleResolver.class.getName());
/*     */   
/*     */   private final Function<String, String> source;
/*     */   
/*     */   public StyleResolver(Function<String, String> source) {
/*  32 */     this.source = Objects.<Function<String, String>>requireNonNull(source);
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
/*     */   private static Integer color(String name) {
/*  46 */     int flags = 0;
/*  47 */     name = name.toLowerCase(Locale.US);
/*     */ 
/*     */     
/*  50 */     if (name.charAt(0) == '!') {
/*  51 */       name = name.substring(1, name.length());
/*  52 */       flags = 8;
/*  53 */     } else if (name.startsWith("bright-")) {
/*  54 */       name = name.substring(7, name.length());
/*  55 */       flags = 8;
/*  56 */     } else if (name.charAt(0) == '~') {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/*  61 */         name = name.substring(1, name.length());
/*  62 */         return Colors.rgbColor(name);
/*  63 */       } catch (IllegalArgumentException e) {
/*  64 */         log.warning("Invalid style-color name: " + name);
/*  65 */         return null;
/*     */       } 
/*     */     } 
/*     */     
/*  69 */     switch (name) {
/*     */       case "black":
/*     */       case "k":
/*  72 */         return Integer.valueOf(flags + 0);
/*     */       
/*     */       case "red":
/*     */       case "r":
/*  76 */         return Integer.valueOf(flags + 1);
/*     */       
/*     */       case "green":
/*     */       case "g":
/*  80 */         return Integer.valueOf(flags + 2);
/*     */       
/*     */       case "yellow":
/*     */       case "y":
/*  84 */         return Integer.valueOf(flags + 3);
/*     */       
/*     */       case "blue":
/*     */       case "b":
/*  88 */         return Integer.valueOf(flags + 4);
/*     */       
/*     */       case "magenta":
/*     */       case "m":
/*  92 */         return Integer.valueOf(flags + 5);
/*     */       
/*     */       case "cyan":
/*     */       case "c":
/*  96 */         return Integer.valueOf(flags + 6);
/*     */       
/*     */       case "white":
/*     */       case "w":
/* 100 */         return Integer.valueOf(flags + 7);
/*     */     } 
/*     */     
/* 103 */     return null;
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
/*     */   public AttributedStyle resolve(String spec) {
/* 117 */     Objects.requireNonNull(spec);
/*     */     
/* 119 */     if (log.isLoggable(Level.FINEST)) {
/* 120 */       log.finest("Resolve: " + spec);
/*     */     }
/*     */     
/* 123 */     int i = spec.indexOf(":-");
/* 124 */     if (i != -1) {
/* 125 */       String[] parts = spec.split(":-");
/* 126 */       return resolve(parts[0].trim(), parts[1].trim());
/*     */     } 
/*     */     
/* 129 */     return apply(AttributedStyle.DEFAULT, spec);
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
/*     */   public AttributedStyle resolve(String spec, String defaultSpec) {
/* 142 */     Objects.requireNonNull(spec);
/*     */     
/* 144 */     if (log.isLoggable(Level.FINEST)) {
/* 145 */       log.finest(String.format("Resolve: %s; default: %s", new Object[] { spec, defaultSpec }));
/*     */     }
/*     */     
/* 148 */     AttributedStyle style = apply(AttributedStyle.DEFAULT, spec);
/* 149 */     if (style == AttributedStyle.DEFAULT && defaultSpec != null) {
/* 150 */       style = apply(style, defaultSpec);
/*     */     }
/* 152 */     return style;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private AttributedStyle apply(AttributedStyle style, String spec) {
/* 163 */     if (log.isLoggable(Level.FINEST)) {
/* 164 */       log.finest("Apply: " + spec);
/*     */     }
/*     */     
/* 167 */     for (String item : spec.split(",")) {
/* 168 */       item = item.trim();
/* 169 */       if (!item.isEmpty())
/*     */       {
/*     */ 
/*     */         
/* 173 */         if (item.startsWith(".")) {
/* 174 */           style = applyReference(style, item);
/* 175 */         } else if (item.contains(":")) {
/* 176 */           style = applyColor(style, item);
/* 177 */         } else if (item.matches("[0-9]+(;[0-9]+)*")) {
/* 178 */           style = applyAnsi(style, item);
/*     */         } else {
/* 180 */           style = applyNamed(style, item);
/*     */         } 
/*     */       }
/*     */     } 
/* 184 */     return style;
/*     */   }
/*     */   
/*     */   private AttributedStyle applyAnsi(AttributedStyle style, String spec) {
/* 188 */     if (log.isLoggable(Level.FINEST)) {
/* 189 */       log.finest("Apply-ansi: " + spec);
/*     */     }
/*     */     
/* 192 */     return (new AttributedStringBuilder())
/* 193 */       .style(style)
/* 194 */       .ansiAppend("\033[" + spec + "m")
/* 195 */       .style();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private AttributedStyle applyReference(AttributedStyle style, String spec) {
/* 206 */     if (log.isLoggable(Level.FINEST)) {
/* 207 */       log.finest("Apply-reference: " + spec);
/*     */     }
/*     */     
/* 210 */     if (spec.length() == 1) {
/* 211 */       log.warning("Invalid style-reference; missing discriminator: " + spec);
/*     */     } else {
/* 213 */       String name = spec.substring(1, spec.length());
/* 214 */       String resolvedSpec = this.source.apply(name);
/* 215 */       if (resolvedSpec != null) {
/* 216 */         return apply(style, resolvedSpec);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 221 */     return style;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private AttributedStyle applyNamed(AttributedStyle style, String name) {
/* 232 */     if (log.isLoggable(Level.FINEST)) {
/* 233 */       log.finest("Apply-named: " + name);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 238 */     switch (name.toLowerCase(Locale.US)) {
/*     */       case "default":
/* 240 */         return AttributedStyle.DEFAULT;
/*     */       
/*     */       case "bold":
/* 243 */         return style.bold();
/*     */       
/*     */       case "faint":
/* 246 */         return style.faint();
/*     */       
/*     */       case "italic":
/* 249 */         return style.italic();
/*     */       
/*     */       case "underline":
/* 252 */         return style.underline();
/*     */       
/*     */       case "blink":
/* 255 */         return style.blink();
/*     */       
/*     */       case "inverse":
/* 258 */         return style.inverse();
/*     */       
/*     */       case "inverse-neg":
/*     */       case "inverseneg":
/* 262 */         return style.inverseNeg();
/*     */       
/*     */       case "conceal":
/* 265 */         return style.conceal();
/*     */       
/*     */       case "crossed-out":
/*     */       case "crossedout":
/* 269 */         return style.crossedOut();
/*     */       
/*     */       case "hidden":
/* 272 */         return style.hidden();
/*     */     } 
/*     */     
/* 275 */     log.warning("Unknown style: " + name);
/* 276 */     return style;
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
/*     */   private AttributedStyle applyColor(AttributedStyle style, String spec) {
/* 290 */     if (log.isLoggable(Level.FINEST)) {
/* 291 */       log.finest("Apply-color: " + spec);
/*     */     }
/*     */ 
/*     */     
/* 295 */     String[] parts = spec.split(":", 2);
/* 296 */     String colorMode = parts[0].trim();
/* 297 */     String colorName = parts[1].trim();
/*     */ 
/*     */     
/* 300 */     Integer color = color(colorName);
/* 301 */     if (color == null) {
/* 302 */       log.warning("Invalid color-name: " + colorName);
/*     */     } else {
/*     */       
/* 305 */       switch (colorMode.toLowerCase(Locale.US)) {
/*     */         case "foreground":
/*     */         case "fg":
/*     */         case "f":
/* 309 */           return style.foreground(color.intValue());
/*     */         
/*     */         case "background":
/*     */         case "bg":
/*     */         case "b":
/* 314 */           return style.background(color.intValue());
/*     */       } 
/*     */       
/* 317 */       log.warning("Invalid color-mode: " + colorMode);
/*     */     } 
/*     */     
/* 320 */     return style;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jlin\\utils\StyleResolver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */