/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.annotations.VisibleForTesting;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.brigadier.Message;
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
/*     */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import java.util.List;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public class MojangsonParser
/*     */ {
/*  16 */   public static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("argument.nbt.trailing"));
/*  17 */   public static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("argument.nbt.expected.key"));
/*  18 */   public static final SimpleCommandExceptionType c = new SimpleCommandExceptionType(new ChatMessage("argument.nbt.expected.value")); static {
/*  19 */     d = new Dynamic2CommandExceptionType((var0, var1) -> new ChatMessage("argument.nbt.list.mixed", new Object[] { var0, var1 }));
/*  20 */     e = new Dynamic2CommandExceptionType((var0, var1) -> new ChatMessage("argument.nbt.array.mixed", new Object[] { var0, var1 }));
/*  21 */     f = new DynamicCommandExceptionType(var0 -> new ChatMessage("argument.nbt.array.invalid", new Object[] { var0 }));
/*     */   }
/*     */ 
/*     */   
/*     */   public static final Dynamic2CommandExceptionType d;
/*     */   
/*     */   public static final Dynamic2CommandExceptionType e;
/*     */   
/*     */   public static final DynamicCommandExceptionType f;
/*  30 */   private static final Pattern g = Pattern.compile("[-+]?(?:[0-9]+[.]|[0-9]*[.][0-9]+)(?:e[-+]?[0-9]+)?", 2);
/*  31 */   private static final Pattern h = Pattern.compile("[-+]?(?:[0-9]+[.]?|[0-9]*[.][0-9]+)(?:e[-+]?[0-9]+)?d", 2);
/*  32 */   private static final Pattern i = Pattern.compile("[-+]?(?:[0-9]+[.]?|[0-9]*[.][0-9]+)(?:e[-+]?[0-9]+)?f", 2);
/*  33 */   private static final Pattern j = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)b", 2);
/*  34 */   private static final Pattern k = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)l", 2);
/*  35 */   private static final Pattern l = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)s", 2);
/*  36 */   private static final Pattern m = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)");
/*     */   
/*     */   private final StringReader n;
/*     */   
/*     */   public static NBTTagCompound parse(String var0) throws CommandSyntaxException {
/*  41 */     return (new MojangsonParser(new StringReader(var0))).a();
/*     */   }
/*     */   
/*     */   @VisibleForTesting
/*     */   NBTTagCompound a() throws CommandSyntaxException {
/*  46 */     NBTTagCompound var0 = f();
/*     */     
/*  48 */     this.n.skipWhitespace();
/*     */     
/*  50 */     if (this.n.canRead()) {
/*  51 */       throw a.createWithContext(this.n);
/*     */     }
/*  53 */     return var0;
/*     */   }
/*     */   
/*     */   public MojangsonParser(StringReader var0) {
/*  57 */     this.n = var0;
/*     */   }
/*     */   
/*     */   protected String b() throws CommandSyntaxException {
/*  61 */     this.n.skipWhitespace();
/*     */     
/*  63 */     if (!this.n.canRead()) {
/*  64 */       throw b.createWithContext(this.n);
/*     */     }
/*     */     
/*  67 */     return this.n.readString();
/*     */   }
/*     */   
/*     */   protected NBTBase c() throws CommandSyntaxException {
/*  71 */     this.n.skipWhitespace();
/*  72 */     int var0 = this.n.getCursor();
/*     */     
/*  74 */     if (StringReader.isQuotedStringStart(this.n.peek())) {
/*  75 */       return NBTTagString.a(this.n.readQuotedString());
/*     */     }
/*     */     
/*  78 */     String var1 = this.n.readUnquotedString();
/*  79 */     if (var1.isEmpty()) {
/*  80 */       this.n.setCursor(var0);
/*  81 */       throw c.createWithContext(this.n);
/*     */     } 
/*  83 */     return parseLiteral(var1);
/*     */   }
/*     */   
/*     */   public NBTBase parseLiteral(String var0) {
/*     */     try {
/*  88 */       if (i.matcher(var0).matches()) {
/*  89 */         return NBTTagFloat.a(Float.parseFloat(var0.substring(0, var0.length() - 1)));
/*     */       }
/*  91 */       if (j.matcher(var0).matches()) {
/*  92 */         return NBTTagByte.a(Byte.parseByte(var0.substring(0, var0.length() - 1)));
/*     */       }
/*  94 */       if (k.matcher(var0).matches()) {
/*  95 */         return NBTTagLong.a(Long.parseLong(var0.substring(0, var0.length() - 1)));
/*     */       }
/*  97 */       if (l.matcher(var0).matches()) {
/*  98 */         return NBTTagShort.a(Short.parseShort(var0.substring(0, var0.length() - 1)));
/*     */       }
/* 100 */       if (m.matcher(var0).matches()) {
/* 101 */         return NBTTagInt.a(Integer.parseInt(var0));
/*     */       }
/* 103 */       if (h.matcher(var0).matches()) {
/* 104 */         return NBTTagDouble.a(Double.parseDouble(var0.substring(0, var0.length() - 1)));
/*     */       }
/* 106 */       if (g.matcher(var0).matches()) {
/* 107 */         return NBTTagDouble.a(Double.parseDouble(var0));
/*     */       }
/* 109 */       if ("true".equalsIgnoreCase(var0)) {
/* 110 */         return NBTTagByte.c;
/*     */       }
/* 112 */       if ("false".equalsIgnoreCase(var0)) {
/* 113 */         return NBTTagByte.b;
/*     */       }
/* 115 */     } catch (NumberFormatException numberFormatException) {}
/*     */ 
/*     */     
/* 118 */     return NBTTagString.a(var0);
/*     */   }
/*     */   
/*     */   public NBTBase d() throws CommandSyntaxException {
/* 122 */     this.n.skipWhitespace();
/*     */     
/* 124 */     if (!this.n.canRead()) {
/* 125 */       throw c.createWithContext(this.n);
/*     */     }
/*     */     
/* 128 */     char var0 = this.n.peek();
/* 129 */     if (var0 == '{')
/* 130 */       return f(); 
/* 131 */     if (var0 == '[') {
/* 132 */       return e();
/*     */     }
/* 134 */     return c();
/*     */   }
/*     */   
/*     */   protected NBTBase e() throws CommandSyntaxException {
/* 138 */     if (this.n.canRead(3) && 
/* 139 */       !StringReader.isQuotedStringStart(this.n.peek(1)) && this.n.peek(2) == ';') {
/* 140 */       return parseArray();
/*     */     }
/*     */     
/* 143 */     return g();
/*     */   }
/*     */   
/*     */   public NBTTagCompound f() throws CommandSyntaxException {
/* 147 */     a('{');
/*     */     
/* 149 */     NBTTagCompound var0 = new NBTTagCompound();
/*     */     
/* 151 */     this.n.skipWhitespace();
/* 152 */     while (this.n.canRead() && this.n.peek() != '}') {
/* 153 */       int var1 = this.n.getCursor();
/* 154 */       String var2 = b();
/* 155 */       if (var2.isEmpty()) {
/* 156 */         this.n.setCursor(var1);
/* 157 */         throw b.createWithContext(this.n);
/*     */       } 
/*     */       
/* 160 */       a(':');
/*     */       
/* 162 */       var0.set(var2, d());
/*     */       
/* 164 */       if (!i())
/*     */         break; 
/* 166 */       if (!this.n.canRead()) {
/* 167 */         throw b.createWithContext(this.n);
/*     */       }
/*     */     } 
/* 170 */     a('}');
/*     */     
/* 172 */     return var0;
/*     */   }
/*     */   
/*     */   private NBTBase g() throws CommandSyntaxException {
/* 176 */     a('[');
/*     */     
/* 178 */     this.n.skipWhitespace();
/*     */     
/* 180 */     if (!this.n.canRead()) {
/* 181 */       throw c.createWithContext(this.n);
/*     */     }
/*     */     
/* 184 */     NBTTagList var0 = new NBTTagList();
/*     */     
/* 186 */     NBTTagType<?> var1 = null;
/* 187 */     while (this.n.peek() != ']') {
/* 188 */       int var2 = this.n.getCursor();
/* 189 */       NBTBase var3 = d();
/*     */       
/* 191 */       NBTTagType<?> var4 = var3.b();
/* 192 */       if (var1 == null) {
/* 193 */         var1 = var4;
/* 194 */       } else if (var4 != var1) {
/* 195 */         this.n.setCursor(var2);
/* 196 */         throw d.createWithContext(this.n, var4.b(), var1.b());
/*     */       } 
/*     */       
/* 199 */       var0.add(var3);
/*     */       
/* 201 */       if (!i())
/*     */         break; 
/* 203 */       if (!this.n.canRead()) {
/* 204 */         throw c.createWithContext(this.n);
/*     */       }
/*     */     } 
/* 207 */     a(']');
/*     */     
/* 209 */     return var0;
/*     */   }
/*     */   
/*     */   public NBTBase parseArray() throws CommandSyntaxException {
/* 213 */     a('[');
/* 214 */     int var0 = this.n.getCursor();
/* 215 */     char var1 = this.n.read();
/* 216 */     this.n.read();
/*     */     
/* 218 */     this.n.skipWhitespace();
/*     */     
/* 220 */     if (!this.n.canRead()) {
/* 221 */       throw c.createWithContext(this.n);
/*     */     }
/*     */     
/* 224 */     if (var1 == 'B')
/* 225 */       return new NBTTagByteArray(a(NBTTagByteArray.a, NBTTagByte.a)); 
/* 226 */     if (var1 == 'L')
/* 227 */       return new NBTTagLongArray(a(NBTTagLongArray.a, NBTTagLong.a)); 
/* 228 */     if (var1 == 'I') {
/* 229 */       return new NBTTagIntArray(a(NBTTagIntArray.a, NBTTagInt.a));
/*     */     }
/* 231 */     this.n.setCursor(var0);
/* 232 */     throw f.createWithContext(this.n, String.valueOf(var1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private <T extends Number> List<T> a(NBTTagType<?> var0, NBTTagType<?> var1) throws CommandSyntaxException {
/* 238 */     List<T> var2 = Lists.newArrayList();
/*     */     
/* 240 */     while (this.n.peek() != ']') {
/* 241 */       int var3 = this.n.getCursor();
/* 242 */       NBTBase var4 = d();
/*     */       
/* 244 */       NBTTagType<?> var5 = var4.b();
/* 245 */       if (var5 != var1) {
/* 246 */         this.n.setCursor(var3);
/* 247 */         throw e.createWithContext(this.n, var5.b(), var0.b());
/*     */       } 
/*     */       
/* 250 */       if (var1 == NBTTagByte.a) {
/* 251 */         var2.add((T)Byte.valueOf(((NBTNumber)var4).asByte()));
/* 252 */       } else if (var1 == NBTTagLong.a) {
/* 253 */         var2.add((T)Long.valueOf(((NBTNumber)var4).asLong()));
/*     */       } else {
/* 255 */         var2.add((T)Integer.valueOf(((NBTNumber)var4).asInt()));
/*     */       } 
/*     */       
/* 258 */       if (!i())
/*     */         break; 
/* 260 */       if (!this.n.canRead()) {
/* 261 */         throw c.createWithContext(this.n);
/*     */       }
/*     */     } 
/* 264 */     a(']');
/*     */     
/* 266 */     return var2;
/*     */   }
/*     */   
/*     */   private boolean i() {
/* 270 */     this.n.skipWhitespace();
/* 271 */     if (this.n.canRead() && this.n.peek() == ',') {
/* 272 */       this.n.skip();
/* 273 */       this.n.skipWhitespace();
/* 274 */       return true;
/*     */     } 
/* 276 */     return false;
/*     */   }
/*     */   
/*     */   private void a(char var0) throws CommandSyntaxException {
/* 280 */     this.n.skipWhitespace();
/*     */     
/* 282 */     this.n.expect(var0);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MojangsonParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */