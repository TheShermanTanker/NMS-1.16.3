/*     */ package org.yaml.snakeyaml;
/*     */ 
/*     */ import java.util.Map;
/*     */ import java.util.TimeZone;
/*     */ import org.yaml.snakeyaml.error.YAMLException;
/*     */ import org.yaml.snakeyaml.serializer.AnchorGenerator;
/*     */ import org.yaml.snakeyaml.serializer.NumberAnchorGenerator;
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
/*     */ public class DumperOptions
/*     */ {
/*     */   public enum ScalarStyle
/*     */   {
/*  39 */     DOUBLE_QUOTED((String)Character.valueOf('"')), SINGLE_QUOTED((String)Character.valueOf('\'')), LITERAL(
/*  40 */       (String)Character.valueOf('|')), FOLDED((String)Character.valueOf('>')), PLAIN(null);
/*     */     private Character styleChar;
/*     */     
/*     */     ScalarStyle(Character style) {
/*  44 */       this.styleChar = style;
/*     */     }
/*     */     
/*     */     public Character getChar() {
/*  48 */       return this.styleChar;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  53 */       return "Scalar style: '" + this.styleChar + "'";
/*     */     }
/*     */     
/*     */     public static ScalarStyle createStyle(Character style) {
/*  57 */       if (style == null) {
/*  58 */         return PLAIN;
/*     */       }
/*  60 */       switch (style.charValue()) {
/*     */         case '"':
/*  62 */           return DOUBLE_QUOTED;
/*     */         case '\'':
/*  64 */           return SINGLE_QUOTED;
/*     */         case '|':
/*  66 */           return LITERAL;
/*     */         case '>':
/*  68 */           return FOLDED;
/*     */       } 
/*  70 */       throw new YAMLException("Unknown scalar style character: " + style);
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
/*     */   public enum FlowStyle
/*     */   {
/*  85 */     FLOW((String)Boolean.TRUE), BLOCK((String)Boolean.FALSE), AUTO(null);
/*     */     
/*     */     private Boolean styleBoolean;
/*     */     
/*     */     FlowStyle(Boolean flowStyle) {
/*  90 */       this.styleBoolean = flowStyle;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @Deprecated
/*     */     public static FlowStyle fromBoolean(Boolean flowStyle) {
/* 100 */       return (flowStyle == null) ? AUTO : (
/* 101 */         flowStyle.booleanValue() ? FLOW : BLOCK);
/*     */     }
/*     */ 
/*     */     
/*     */     public Boolean getStyleBoolean() {
/* 106 */       return this.styleBoolean;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 111 */       return "Flow style: '" + this.styleBoolean + "'";
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public enum LineBreak
/*     */   {
/* 119 */     WIN("\r\n"), MAC("\r"), UNIX("\n");
/*     */     
/*     */     private String lineBreak;
/*     */     
/*     */     LineBreak(String lineBreak) {
/* 124 */       this.lineBreak = lineBreak;
/*     */     }
/*     */     
/*     */     public String getString() {
/* 128 */       return this.lineBreak;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 133 */       return "Line break: " + name();
/*     */     }
/*     */     
/*     */     public static LineBreak getPlatformLineBreak() {
/* 137 */       String platformLineBreak = System.getProperty("line.separator");
/* 138 */       for (LineBreak lb : values()) {
/* 139 */         if (lb.lineBreak.equals(platformLineBreak)) {
/* 140 */           return lb;
/*     */         }
/*     */       } 
/* 143 */       return UNIX;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public enum Version
/*     */   {
/* 151 */     V1_0((String)new Integer[] { Integer.valueOf(1), Integer.valueOf(0) }), V1_1((String)new Integer[] { Integer.valueOf(1), Integer.valueOf(1) });
/*     */     
/*     */     private Integer[] version;
/*     */     
/*     */     Version(Integer[] version) {
/* 156 */       this.version = version;
/*     */     }
/*     */     
/* 159 */     public int major() { return this.version[0].intValue(); } public int minor() {
/* 160 */       return this.version[1].intValue();
/*     */     }
/*     */     public String getRepresentation() {
/* 163 */       return this.version[0] + "." + this.version[1];
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 168 */       return "Version: " + getRepresentation();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public enum NonPrintableStyle
/*     */   {
/* 176 */     BINARY,
/*     */ 
/*     */ 
/*     */     
/* 180 */     ESCAPE;
/*     */   }
/*     */   
/* 183 */   private ScalarStyle defaultStyle = ScalarStyle.PLAIN;
/* 184 */   private FlowStyle defaultFlowStyle = FlowStyle.AUTO;
/*     */   private boolean canonical = false;
/*     */   private boolean allowUnicode = true;
/*     */   private boolean allowReadOnlyProperties = false;
/* 188 */   private int indent = 2;
/* 189 */   private int indicatorIndent = 0;
/* 190 */   private int bestWidth = 80;
/*     */   private boolean splitLines = true;
/* 192 */   private LineBreak lineBreak = LineBreak.UNIX;
/*     */   private boolean explicitStart = false;
/*     */   private boolean explicitEnd = false;
/* 195 */   private TimeZone timeZone = null;
/* 196 */   private int maxSimpleKeyLength = 128;
/* 197 */   private NonPrintableStyle nonPrintableStyle = NonPrintableStyle.BINARY;
/*     */   
/* 199 */   private Version version = null;
/* 200 */   private Map<String, String> tags = null;
/* 201 */   private Boolean prettyFlow = Boolean.valueOf(false);
/* 202 */   private AnchorGenerator anchorGenerator = (AnchorGenerator)new NumberAnchorGenerator(0);
/*     */   
/*     */   public boolean isAllowUnicode() {
/* 205 */     return this.allowUnicode;
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
/*     */   public void setAllowUnicode(boolean allowUnicode) {
/* 219 */     this.allowUnicode = allowUnicode;
/*     */   }
/*     */   
/*     */   public ScalarStyle getDefaultScalarStyle() {
/* 223 */     return this.defaultStyle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefaultScalarStyle(ScalarStyle defaultStyle) {
/* 234 */     if (defaultStyle == null) {
/* 235 */       throw new NullPointerException("Use ScalarStyle enum.");
/*     */     }
/* 237 */     this.defaultStyle = defaultStyle;
/*     */   }
/*     */   
/*     */   public void setIndent(int indent) {
/* 241 */     if (indent < 1) {
/* 242 */       throw new YAMLException("Indent must be at least 1");
/*     */     }
/* 244 */     if (indent > 10) {
/* 245 */       throw new YAMLException("Indent must be at most 10");
/*     */     }
/* 247 */     this.indent = indent;
/*     */   }
/*     */   
/*     */   public int getIndent() {
/* 251 */     return this.indent;
/*     */   }
/*     */   
/*     */   public void setIndicatorIndent(int indicatorIndent) {
/* 255 */     if (indicatorIndent < 0) {
/* 256 */       throw new YAMLException("Indicator indent must be non-negative.");
/*     */     }
/* 258 */     if (indicatorIndent > 9) {
/* 259 */       throw new YAMLException("Indicator indent must be at most Emitter.MAX_INDENT-1: 9");
/*     */     }
/* 261 */     this.indicatorIndent = indicatorIndent;
/*     */   }
/*     */   
/*     */   public int getIndicatorIndent() {
/* 265 */     return this.indicatorIndent;
/*     */   }
/*     */   
/*     */   public void setVersion(Version version) {
/* 269 */     this.version = version;
/*     */   }
/*     */   
/*     */   public Version getVersion() {
/* 273 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCanonical(boolean canonical) {
/* 283 */     this.canonical = canonical;
/*     */   }
/*     */   
/*     */   public boolean isCanonical() {
/* 287 */     return this.canonical;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPrettyFlow(boolean prettyFlow) {
/* 298 */     this.prettyFlow = Boolean.valueOf(prettyFlow);
/*     */   }
/*     */   
/*     */   public boolean isPrettyFlow() {
/* 302 */     return this.prettyFlow.booleanValue();
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
/*     */   public void setWidth(int bestWidth) {
/* 314 */     this.bestWidth = bestWidth;
/*     */   }
/*     */   
/*     */   public int getWidth() {
/* 318 */     return this.bestWidth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSplitLines(boolean splitLines) {
/* 329 */     this.splitLines = splitLines;
/*     */   }
/*     */   
/*     */   public boolean getSplitLines() {
/* 333 */     return this.splitLines;
/*     */   }
/*     */   
/*     */   public LineBreak getLineBreak() {
/* 337 */     return this.lineBreak;
/*     */   }
/*     */   
/*     */   public void setDefaultFlowStyle(FlowStyle defaultFlowStyle) {
/* 341 */     if (defaultFlowStyle == null) {
/* 342 */       throw new NullPointerException("Use FlowStyle enum.");
/*     */     }
/* 344 */     this.defaultFlowStyle = defaultFlowStyle;
/*     */   }
/*     */   
/*     */   public FlowStyle getDefaultFlowStyle() {
/* 348 */     return this.defaultFlowStyle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLineBreak(LineBreak lineBreak) {
/* 358 */     if (lineBreak == null) {
/* 359 */       throw new NullPointerException("Specify line break.");
/*     */     }
/* 361 */     this.lineBreak = lineBreak;
/*     */   }
/*     */   
/*     */   public boolean isExplicitStart() {
/* 365 */     return this.explicitStart;
/*     */   }
/*     */   
/*     */   public void setExplicitStart(boolean explicitStart) {
/* 369 */     this.explicitStart = explicitStart;
/*     */   }
/*     */   
/*     */   public boolean isExplicitEnd() {
/* 373 */     return this.explicitEnd;
/*     */   }
/*     */   
/*     */   public void setExplicitEnd(boolean explicitEnd) {
/* 377 */     this.explicitEnd = explicitEnd;
/*     */   }
/*     */   
/*     */   public Map<String, String> getTags() {
/* 381 */     return this.tags;
/*     */   }
/*     */   
/*     */   public void setTags(Map<String, String> tags) {
/* 385 */     this.tags = tags;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAllowReadOnlyProperties() {
/* 395 */     return this.allowReadOnlyProperties;
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
/*     */   public void setAllowReadOnlyProperties(boolean allowReadOnlyProperties) {
/* 407 */     this.allowReadOnlyProperties = allowReadOnlyProperties;
/*     */   }
/*     */   
/*     */   public TimeZone getTimeZone() {
/* 411 */     return this.timeZone;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTimeZone(TimeZone timeZone) {
/* 420 */     this.timeZone = timeZone;
/*     */   }
/*     */ 
/*     */   
/*     */   public AnchorGenerator getAnchorGenerator() {
/* 425 */     return this.anchorGenerator;
/*     */   }
/*     */   
/*     */   public void setAnchorGenerator(AnchorGenerator anchorGenerator) {
/* 429 */     this.anchorGenerator = anchorGenerator;
/*     */   }
/*     */   
/*     */   public int getMaxSimpleKeyLength() {
/* 433 */     return this.maxSimpleKeyLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxSimpleKeyLength(int maxSimpleKeyLength) {
/* 442 */     if (maxSimpleKeyLength > 1024) {
/* 443 */       throw new YAMLException("The simple key must not span more than 1024 stream characters. See https://yaml.org/spec/1.1/#id934537");
/*     */     }
/* 445 */     this.maxSimpleKeyLength = maxSimpleKeyLength;
/*     */   }
/*     */   
/*     */   public NonPrintableStyle getNonPrintableStyle() {
/* 449 */     return this.nonPrintableStyle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNonPrintableStyle(NonPrintableStyle style) {
/* 458 */     this.nonPrintableStyle = style;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\yaml\snakeyaml\DumperOptions.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */