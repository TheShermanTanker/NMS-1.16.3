/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.brigadier.Message;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.function.Function;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChatComponentUtils
/*     */ {
/*     */   public static IChatMutableComponent a(IChatMutableComponent var0, ChatModifier var1) {
/*  18 */     if (var1.g()) {
/*  19 */       return var0;
/*     */     }
/*     */     
/*  22 */     ChatModifier var2 = var0.getChatModifier();
/*  23 */     if (var2.g()) {
/*  24 */       return var0.setChatModifier(var1);
/*     */     }
/*     */     
/*  27 */     if (var2.equals(var1)) {
/*  28 */       return var0;
/*     */     }
/*     */     
/*  31 */     return var0.setChatModifier(var2.setChatModifier(var1));
/*     */   }
/*     */   
/*     */   public static IChatMutableComponent filterForDisplay(@Nullable CommandListenerWrapper var0, IChatBaseComponent var1, @Nullable Entity var2, int var3) throws CommandSyntaxException {
/*  35 */     if (var3 > 100) {
/*  36 */       return var1.mutableCopy();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  41 */     IChatMutableComponent var4 = (var1 instanceof ChatComponentContextual) ? ((ChatComponentContextual)var1).a(var0, var2, var3 + 1) : var1.g();
/*     */     
/*  43 */     for (IChatBaseComponent var6 : var1.getSiblings()) {
/*  44 */       var4.addSibling(filterForDisplay(var0, var6, var2, var3 + 1));
/*     */     }
/*     */     
/*  47 */     return var4.c(a(var0, var1.getChatModifier(), var2, var3));
/*     */   }
/*     */   
/*     */   private static ChatModifier a(@Nullable CommandListenerWrapper var0, ChatModifier var1, @Nullable Entity var2, int var3) throws CommandSyntaxException {
/*  51 */     ChatHoverable var4 = var1.getHoverEvent();
/*  52 */     if (var4 != null) {
/*  53 */       IChatBaseComponent var5 = var4.<IChatBaseComponent>a(ChatHoverable.EnumHoverAction.SHOW_TEXT);
/*  54 */       if (var5 != null) {
/*  55 */         ChatHoverable var6 = new ChatHoverable((ChatHoverable.EnumHoverAction)ChatHoverable.EnumHoverAction.SHOW_TEXT, (T)filterForDisplay(var0, var5, var2, var3 + 1));
/*  56 */         return var1.setChatHoverable(var6);
/*     */       } 
/*     */     } 
/*     */     
/*  60 */     return var1;
/*     */   }
/*     */   
/*     */   public static IChatBaseComponent a(GameProfile var0) {
/*  64 */     if (var0.getName() != null)
/*  65 */       return new ChatComponentText(var0.getName()); 
/*  66 */     if (var0.getId() != null) {
/*  67 */       return new ChatComponentText(var0.getId().toString());
/*     */     }
/*  69 */     return new ChatComponentText("(unknown)");
/*     */   }
/*     */ 
/*     */   
/*     */   public static IChatBaseComponent a(Collection<String> var0) {
/*  74 */     return a(var0, var0 -> (new ChatComponentText(var0)).a(EnumChatFormat.GREEN));
/*     */   }
/*     */   
/*     */   public static <T extends Comparable<T>> IChatBaseComponent a(Collection<T> var0, Function<T, IChatBaseComponent> var1) {
/*  78 */     if (var0.isEmpty())
/*  79 */       return ChatComponentText.d; 
/*  80 */     if (var0.size() == 1) {
/*  81 */       return var1.apply(var0.iterator().next());
/*     */     }
/*     */     
/*  84 */     List<T> var2 = Lists.newArrayList(var0);
/*  85 */     var2.sort(Comparable::compareTo);
/*  86 */     return b(var2, var1);
/*     */   }
/*     */   
/*     */   public static <T> IChatMutableComponent b(Collection<T> var0, Function<T, IChatBaseComponent> var1) {
/*  90 */     if (var0.isEmpty())
/*  91 */       return new ChatComponentText(""); 
/*  92 */     if (var0.size() == 1) {
/*  93 */       return ((IChatBaseComponent)var1.apply(var0.iterator().next())).mutableCopy();
/*     */     }
/*     */     
/*  96 */     IChatMutableComponent var2 = new ChatComponentText("");
/*  97 */     boolean var3 = true;
/*  98 */     for (T var5 : var0) {
/*  99 */       if (!var3) {
/* 100 */         var2.addSibling((new ChatComponentText(", ")).a(EnumChatFormat.GRAY));
/*     */       }
/* 102 */       var2.addSibling(var1.apply(var5));
/* 103 */       var3 = false;
/*     */     } 
/*     */     
/* 106 */     return var2;
/*     */   }
/*     */   
/*     */   public static IChatMutableComponent a(IChatBaseComponent var0) {
/* 110 */     return new ChatMessage("chat.square_brackets", new Object[] { var0 });
/*     */   }
/*     */   
/*     */   public static IChatBaseComponent a(Message var0) {
/* 114 */     if (var0 instanceof IChatBaseComponent) {
/* 115 */       return (IChatBaseComponent)var0;
/*     */     }
/* 117 */     return new ChatComponentText(var0.getString());
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChatComponentUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */