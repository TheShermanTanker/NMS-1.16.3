/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.brigadier.arguments.StringArgumentType;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Supplier;
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
/*     */ public class ResourcePackLoader
/*     */   implements AutoCloseable
/*     */ {
/*  23 */   private static final Logger LOGGER = LogManager.getLogger();
/*  24 */   private static final ResourcePackInfo b = new ResourcePackInfo((new ChatMessage("resourcePack.broken_assets")).a(new EnumChatFormat[] { EnumChatFormat.RED, EnumChatFormat.ITALIC }, ), SharedConstants.getGameVersion().getPackVersion());
/*     */   
/*     */   private final String c;
/*     */   private final Supplier<IResourcePack> d;
/*     */   private final IChatBaseComponent e;
/*     */   private final IChatBaseComponent f;
/*     */   private final EnumResourcePackVersion g;
/*     */   private final Position h;
/*     */   private final boolean i;
/*     */   private final boolean j;
/*     */   private final PackSource k;
/*     */   
/*     */   @Nullable
/*     */   public static ResourcePackLoader a(String var0, boolean var1, Supplier<IResourcePack> var2, a var3, Position var4, PackSource var5) {
/*  38 */     try (IResourcePack var6 = (IResourcePack)var2.get()) {
/*  39 */       ResourcePackInfo var8 = var6.<ResourcePackInfo>a(ResourcePackInfo.a);
/*  40 */       if (var1 && var8 == null) {
/*  41 */         LOGGER.error("Broken/missing pack.mcmeta detected, fudging it into existance. Please check that your launcher has downloaded all assets for the game correctly!");
/*  42 */         var8 = b;
/*     */       } 
/*     */       
/*  45 */       if (var8 != null) {
/*  46 */         return var3.create(var0, var1, var2, var6, var8, var4, var5);
/*     */       }
/*  48 */       LOGGER.warn("Couldn't find pack meta for pack {}", var0);
/*     */     }
/*  50 */     catch (IOException var6) {
/*  51 */       LOGGER.warn("Couldn't get pack info for: {}", var6.toString());
/*     */     } 
/*  53 */     return null;
/*     */   }
/*     */   
/*     */   public ResourcePackLoader(String var0, boolean var1, Supplier<IResourcePack> var2, IChatBaseComponent var3, IChatBaseComponent var4, EnumResourcePackVersion var5, Position var6, boolean var7, PackSource var8) {
/*  57 */     this.c = var0;
/*  58 */     this.d = var2;
/*  59 */     this.e = var3;
/*  60 */     this.f = var4;
/*  61 */     this.g = var5;
/*  62 */     this.i = var1;
/*  63 */     this.h = var6;
/*  64 */     this.j = var7;
/*  65 */     this.k = var8;
/*     */   }
/*     */   
/*     */   public ResourcePackLoader(String var0, boolean var1, Supplier<IResourcePack> var2, IResourcePack var3, ResourcePackInfo var4, Position var5, PackSource var6) {
/*  69 */     this(var0, var1, var2, new ChatComponentText(var3.a()), var4.a(), EnumResourcePackVersion.a(var4.b()), var5, false, var6);
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
/*     */   public IChatBaseComponent a(boolean var0) {
/*  81 */     return ChatComponentUtils.a(this.k.decorate(new ChatComponentText(this.c))).format(var1 -> var1.setColor(var0 ? EnumChatFormat.GREEN : EnumChatFormat.RED).setInsertion(StringArgumentType.escapeIfRequired(this.c)).setChatHoverable(new ChatHoverable((ChatHoverable.EnumHoverAction)ChatHoverable.EnumHoverAction.SHOW_TEXT, (T)(new ChatComponentText("")).addSibling(this.e).c("\n").addSibling(this.f))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumResourcePackVersion c() {
/*  89 */     return this.g;
/*     */   }
/*     */   
/*     */   public IResourcePack d() {
/*  93 */     return this.d.get();
/*     */   }
/*     */   
/*     */   public String e() {
/*  97 */     return this.c;
/*     */   }
/*     */   
/*     */   public boolean f() {
/* 101 */     return this.i;
/*     */   }
/*     */   
/*     */   public boolean g() {
/* 105 */     return this.j;
/*     */   }
/*     */   
/*     */   public Position h() {
/* 109 */     return this.h;
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
/*     */   
/*     */   public boolean equals(Object var0) {
/* 124 */     if (this == var0) {
/* 125 */       return true;
/*     */     }
/* 127 */     if (!(var0 instanceof ResourcePackLoader)) {
/* 128 */       return false;
/*     */     }
/*     */     
/* 131 */     ResourcePackLoader var1 = (ResourcePackLoader)var0;
/*     */     
/* 133 */     return this.c.equals(var1.c);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 138 */     return this.c.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() {}
/*     */   
/*     */   public enum Position
/*     */   {
/* 146 */     TOP,
/* 147 */     BOTTOM;
/*     */ 
/*     */     
/*     */     public <T> int a(List<T> var0, T var1, Function<T, ResourcePackLoader> var2, boolean var3) {
/* 151 */       Position var4 = var3 ? a() : this;
/* 152 */       if (var4 == BOTTOM) {
/* 153 */         int i = 0;
/* 154 */         while (i < var0.size()) {
/* 155 */           ResourcePackLoader var6 = var2.apply(var0.get(i));
/* 156 */           if (var6.g() && var6.h() == this) {
/* 157 */             i++;
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 162 */         var0.add(i, var1);
/* 163 */         return i;
/*     */       } 
/* 165 */       int var5 = var0.size() - 1;
/* 166 */       while (var5 >= 0) {
/* 167 */         ResourcePackLoader var6 = var2.apply(var0.get(var5));
/* 168 */         if (var6.g() && var6.h() == this) {
/* 169 */           var5--;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 174 */       var0.add(var5 + 1, var1);
/* 175 */       return var5 + 1;
/*     */     }
/*     */ 
/*     */     
/*     */     public Position a() {
/* 180 */       return (this == TOP) ? BOTTOM : TOP;
/*     */     }
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface a {
/*     */     @Nullable
/*     */     ResourcePackLoader create(String param1String, boolean param1Boolean, Supplier<IResourcePack> param1Supplier, IResourcePack param1IResourcePack, ResourcePackInfo param1ResourcePackInfo, ResourcePackLoader.Position param1Position, PackSource param1PackSource);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ResourcePackLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */