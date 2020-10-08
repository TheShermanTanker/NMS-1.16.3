/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.util.Objects;
/*     */ 
/*     */ public class NBTTagString
/*     */   implements NBTBase {
/*  10 */   public static final NBTTagType<NBTTagString> a = new NBTTagType<NBTTagString>()
/*     */     {
/*     */       public NBTTagString b(DataInput datainput, int i, NBTReadLimiter nbtreadlimiter) throws IOException {
/*  13 */         nbtreadlimiter.a(288L);
/*  14 */         String s = datainput.readUTF();
/*     */         
/*  16 */         nbtreadlimiter.a((16 * s.length()));
/*  17 */         return NBTTagString.a(s);
/*     */       }
/*     */ 
/*     */       
/*     */       public String a() {
/*  22 */         return "STRING";
/*     */       }
/*     */ 
/*     */       
/*     */       public String b() {
/*  27 */         return "TAG_String";
/*     */       }
/*     */ 
/*     */       
/*     */       public boolean c() {
/*  32 */         return true;
/*     */       }
/*     */     };
/*  35 */   private static final NBTTagString b = new NBTTagString("");
/*     */   private final String data;
/*     */   
/*     */   private NBTTagString(String s) {
/*  39 */     Objects.requireNonNull(s, "Null string not allowed");
/*  40 */     this.data = s;
/*     */   }
/*     */   public static NBTTagString create(String s) {
/*  43 */     return a(s);
/*     */   } public static NBTTagString a(String s) {
/*  45 */     return s.isEmpty() ? b : new NBTTagString(s);
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(DataOutput dataoutput) throws IOException {
/*  50 */     dataoutput.writeUTF(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getTypeId() {
/*  55 */     return 8;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagType<NBTTagString> b() {
/*  60 */     return a;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  65 */     return b(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagString clone() {
/*  70 */     return this;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/*  74 */     return (this == object) ? true : ((object instanceof NBTTagString && Objects.equals(this.data, ((NBTTagString)object).data)));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  78 */     return this.data.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public String asString() {
/*  83 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatBaseComponent a(String s, int i) {
/*  88 */     String s1 = b(this.data);
/*  89 */     String s2 = s1.substring(0, 1);
/*  90 */     IChatMutableComponent ichatmutablecomponent = (new ChatComponentText(s1.substring(1, s1.length() - 1))).a(e);
/*     */     
/*  92 */     return (new ChatComponentText(s2)).addSibling(ichatmutablecomponent).c(s2);
/*     */   }
/*     */   
/*     */   public static String b(String s) {
/*  96 */     StringBuilder stringbuilder = new StringBuilder(" ");
/*  97 */     int i = 0;
/*     */     
/*  99 */     for (int j = 0; j < s.length(); j++) {
/* 100 */       char c0 = s.charAt(j);
/*     */       
/* 102 */       if (c0 == '\\') {
/* 103 */         stringbuilder.append('\\');
/* 104 */       } else if (c0 == '"' || c0 == '\'') {
/* 105 */         if (i == 0) {
/* 106 */           i = (c0 == '"') ? 39 : 34;
/*     */         }
/*     */         
/* 109 */         if (i == c0) {
/* 110 */           stringbuilder.append('\\');
/*     */         }
/*     */       } 
/*     */       
/* 114 */       stringbuilder.append(c0);
/*     */     } 
/*     */     
/* 117 */     if (i == 0) {
/* 118 */       i = 34;
/*     */     }
/*     */     
/* 121 */     stringbuilder.setCharAt(0, (char)i);
/* 122 */     stringbuilder.append((char)i);
/* 123 */     return stringbuilder.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NBTTagString.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */