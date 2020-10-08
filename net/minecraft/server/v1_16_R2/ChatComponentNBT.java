/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.base.Joiner;
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
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
/*     */ public abstract class ChatComponentNBT
/*     */   extends ChatBaseComponent
/*     */   implements ChatComponentContextual
/*     */ {
/*  29 */   private static final Logger LOGGER = LogManager.getLogger(); protected final boolean d;
/*     */   protected final String e;
/*     */   @Nullable
/*     */   protected final ArgumentNBTKey.h f;
/*     */   
/*     */   public static class b extends ChatComponentNBT { private final String g;
/*     */     @Nullable
/*     */     private final EntitySelector h;
/*     */     
/*     */     public b(String var0, boolean var1, String var2) {
/*  39 */       super(var0, var1);
/*  40 */       this.g = var2;
/*  41 */       this.h = d(var2);
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     private static EntitySelector d(String var0) {
/*     */       try {
/*  47 */         ArgumentParserSelector var1 = new ArgumentParserSelector(new StringReader(var0));
/*  48 */         return var1.parse();
/*  49 */       } catch (CommandSyntaxException var1) {
/*  50 */         return null;
/*     */       } 
/*     */     }
/*     */     
/*     */     private b(String var0, @Nullable ArgumentNBTKey.h var1, boolean var2, String var3, @Nullable EntitySelector var4) {
/*  55 */       super(var0, var1, var2);
/*  56 */       this.g = var3;
/*  57 */       this.h = var4;
/*     */     }
/*     */     
/*     */     public String j() {
/*  61 */       return this.g;
/*     */     }
/*     */ 
/*     */     
/*     */     public b g() {
/*  66 */       return new b(this.e, this.f, this.d, this.g, this.h);
/*     */     }
/*     */ 
/*     */     
/*     */     protected Stream<NBTTagCompound> a(CommandListenerWrapper var0) throws CommandSyntaxException {
/*  71 */       if (this.h != null) {
/*  72 */         List<? extends Entity> var1 = this.h.getEntities(var0);
/*  73 */         return var1.stream().map(CriterionConditionNBT::b);
/*     */       } 
/*     */       
/*  76 */       return Stream.empty();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object var0) {
/*  81 */       if (this == var0) {
/*  82 */         return true;
/*     */       }
/*     */       
/*  85 */       if (var0 instanceof b) {
/*  86 */         b var1 = (b)var0;
/*  87 */         return (Objects.equals(this.g, var1.g) && 
/*  88 */           Objects.equals(this.e, var1.e) && super
/*  89 */           .equals(var0));
/*     */       } 
/*     */       
/*  92 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  97 */       return "EntityNbtComponent{selector='" + this.g + '\'' + "path='" + this.e + '\'' + ", siblings=" + this.siblings + ", style=" + 
/*     */ 
/*     */ 
/*     */         
/* 101 */         getChatModifier() + '}';
/*     */     } }
/*     */ 
/*     */   
/*     */   public static class a
/*     */     extends ChatComponentNBT
/*     */   {
/*     */     private final String g;
/*     */     @Nullable
/*     */     private final IVectorPosition h;
/*     */     
/*     */     public a(String var0, boolean var1, String var2) {
/* 113 */       super(var0, var1);
/* 114 */       this.g = var2;
/* 115 */       this.h = d(this.g);
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     private IVectorPosition d(String var0) {
/*     */       try {
/* 121 */         return ArgumentPosition.a().parse(new StringReader(var0));
/* 122 */       } catch (CommandSyntaxException var1) {
/* 123 */         return null;
/*     */       } 
/*     */     }
/*     */     
/*     */     private a(String var0, @Nullable ArgumentNBTKey.h var1, boolean var2, String var3, @Nullable IVectorPosition var4) {
/* 128 */       super(var0, var1, var2);
/* 129 */       this.g = var3;
/* 130 */       this.h = var4;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public String j() {
/* 135 */       return this.g;
/*     */     }
/*     */ 
/*     */     
/*     */     public a g() {
/* 140 */       return new a(this.e, this.f, this.d, this.g, this.h);
/*     */     }
/*     */ 
/*     */     
/*     */     protected Stream<NBTTagCompound> a(CommandListenerWrapper var0) {
/* 145 */       if (this.h != null) {
/* 146 */         WorldServer var1 = var0.getWorld();
/* 147 */         BlockPosition var2 = this.h.c(var0);
/* 148 */         if (var1.p(var2)) {
/* 149 */           TileEntity var3 = var1.getTileEntity(var2);
/*     */           
/* 151 */           if (var3 != null) {
/* 152 */             return Stream.of(var3.save(new NBTTagCompound()));
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 157 */       return Stream.empty();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object var0) {
/* 162 */       if (this == var0) {
/* 163 */         return true;
/*     */       }
/*     */       
/* 166 */       if (var0 instanceof a) {
/* 167 */         a var1 = (a)var0;
/* 168 */         return (Objects.equals(this.g, var1.g) && 
/* 169 */           Objects.equals(this.e, var1.e) && super
/* 170 */           .equals(var0));
/*     */       } 
/*     */       
/* 173 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 178 */       return "BlockPosArgument{pos='" + this.g + '\'' + "path='" + this.e + '\'' + ", siblings=" + this.siblings + ", style=" + 
/*     */ 
/*     */ 
/*     */         
/* 182 */         getChatModifier() + '}';
/*     */     }
/*     */   }
/*     */   
/*     */   public static class c
/*     */     extends ChatComponentNBT {
/*     */     private final MinecraftKey g;
/*     */     
/*     */     public c(String var0, boolean var1, MinecraftKey var2) {
/* 191 */       super(var0, var1);
/* 192 */       this.g = var2;
/*     */     }
/*     */     
/*     */     public c(String var0, @Nullable ArgumentNBTKey.h var1, boolean var2, MinecraftKey var3) {
/* 196 */       super(var0, var1, var2);
/* 197 */       this.g = var3;
/*     */     }
/*     */     
/*     */     public MinecraftKey j() {
/* 201 */       return this.g;
/*     */     }
/*     */ 
/*     */     
/*     */     public c g() {
/* 206 */       return new c(this.e, this.f, this.d, this.g);
/*     */     }
/*     */ 
/*     */     
/*     */     protected Stream<NBTTagCompound> a(CommandListenerWrapper var0) {
/* 211 */       NBTTagCompound var1 = var0.getServer().aH().a(this.g);
/* 212 */       return Stream.of(var1);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object var0) {
/* 217 */       if (this == var0) {
/* 218 */         return true;
/*     */       }
/*     */       
/* 221 */       if (var0 instanceof c) {
/* 222 */         c var1 = (c)var0;
/* 223 */         return (Objects.equals(this.g, var1.g) && 
/* 224 */           Objects.equals(this.e, var1.e) && super
/* 225 */           .equals(var0));
/*     */       } 
/*     */       
/* 228 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 233 */       return "StorageNbtComponent{id='" + this.g + '\'' + "path='" + this.e + '\'' + ", siblings=" + this.siblings + ", style=" + 
/*     */ 
/*     */ 
/*     */         
/* 237 */         getChatModifier() + '}';
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
/*     */   @Nullable
/*     */   private static ArgumentNBTKey.h d(String var0) {
/*     */     try {
/* 252 */       return (new ArgumentNBTKey()).parse(new StringReader(var0));
/* 253 */     } catch (CommandSyntaxException var1) {
/* 254 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public ChatComponentNBT(String var0, boolean var1) {
/* 259 */     this(var0, d(var0), var1);
/*     */   }
/*     */   
/*     */   protected ChatComponentNBT(String var0, @Nullable ArgumentNBTKey.h var1, boolean var2) {
/* 263 */     this.e = var0;
/* 264 */     this.f = var1;
/* 265 */     this.d = var2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String h() {
/* 271 */     return this.e;
/*     */   }
/*     */   
/*     */   public boolean i() {
/* 275 */     return this.d;
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatMutableComponent a(@Nullable CommandListenerWrapper var0, @Nullable Entity var1, int var2) throws CommandSyntaxException {
/* 280 */     if (var0 == null || this.f == null) {
/* 281 */       return new ChatComponentText("");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 291 */     Stream<String> var3 = a(var0).flatMap(var0 -> { try { return this.f.a(var0).stream(); } catch (CommandSyntaxException var1) { return Stream.empty(); }  }).map(NBTBase::asString);
/*     */     
/* 293 */     if (this.d) {
/* 294 */       return var3.<IChatMutableComponent>flatMap(var3 -> {
/*     */             try {
/*     */               IChatMutableComponent var4 = IChatBaseComponent.ChatSerializer.a(var3);
/*     */               return Stream.of(ChatComponentUtils.filterForDisplay(var0, var4, var1, var2));
/* 298 */             } catch (Exception var4) {
/*     */               LOGGER.warn("Failed to parse component: " + var3, var4);
/*     */               
/*     */               return Stream.of(new IChatMutableComponent[0]);
/*     */             } 
/* 303 */           }).reduce((var0, var1) -> var0.c(", ").addSibling(var1))
/* 304 */         .orElse(new ChatComponentText(""));
/*     */     }
/* 306 */     return new ChatComponentText(Joiner.on(", ").join(var3.iterator()));
/*     */   }
/*     */   
/*     */   protected abstract Stream<NBTTagCompound> a(CommandListenerWrapper paramCommandListenerWrapper) throws CommandSyntaxException;
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChatComponentNBT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */