/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.brigadier.Message;
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*     */ import com.mojang.brigadier.suggestion.Suggestions;
/*     */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArgumentItemPredicate
/*     */   implements ArgumentType<ArgumentItemPredicate.b>
/*     */ {
/*     */   private static final DynamicCommandExceptionType b;
/*  27 */   private static final Collection<String> a = Arrays.asList(new String[] { "stick", "minecraft:stick", "#stick", "#stick{foo=bar}" }); static {
/*  28 */     b = new DynamicCommandExceptionType(var0 -> new ChatMessage("arguments.item.tag.unknown", new Object[] { var0 }));
/*     */   }
/*     */   public static ArgumentItemPredicate a() {
/*  31 */     return new ArgumentItemPredicate();
/*     */   }
/*     */ 
/*     */   
/*     */   public b parse(StringReader var0) throws CommandSyntaxException {
/*  36 */     ArgumentParserItemStack var1 = (new ArgumentParserItemStack(var0, true)).h();
/*     */     
/*  38 */     if (var1.b() != null) {
/*  39 */       a a = new a(var1.b(), var1.c());
/*  40 */       return var1 -> var0;
/*     */     } 
/*  42 */     MinecraftKey var2 = var1.d();
/*  43 */     return var2 -> {
/*     */         Tag<Item> var3 = ((CommandListenerWrapper)var2.getSource()).getServer().getTagRegistry().getItemTags().a(var0);
/*     */         if (var3 == null) {
/*     */           throw b.create(var0.toString());
/*     */         }
/*     */         return new c(var3, var1.c());
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   public static Predicate<ItemStack> a(CommandContext<CommandListenerWrapper> var0, String var1) throws CommandSyntaxException {
/*  54 */     return ((b)var0.getArgument(var1, b.class)).create(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var0, SuggestionsBuilder var1) {
/*  59 */     StringReader var2 = new StringReader(var1.getInput());
/*  60 */     var2.setCursor(var1.getStart());
/*  61 */     ArgumentParserItemStack var3 = new ArgumentParserItemStack(var2, true);
/*     */     try {
/*  63 */       var3.h();
/*  64 */     } catch (CommandSyntaxException commandSyntaxException) {}
/*     */     
/*  66 */     return var3.a(var1, TagsItem.a());
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<String> getExamples() {
/*  71 */     return a;
/*     */   }
/*     */   
/*     */   public static interface b {
/*     */     Predicate<ItemStack> create(CommandContext<CommandListenerWrapper> param1CommandContext) throws CommandSyntaxException;
/*     */   }
/*     */   
/*     */   static class a implements Predicate<ItemStack> {
/*     */     private final Item a;
/*     */     @Nullable
/*     */     private final NBTTagCompound b;
/*     */     
/*     */     public a(Item var0, @Nullable NBTTagCompound var1) {
/*  84 */       this.a = var0;
/*  85 */       this.b = var1;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean test(ItemStack var0) {
/*  90 */       return (var0.getItem() == this.a && GameProfileSerializer.a(this.b, var0.getTag(), true));
/*     */     }
/*     */   }
/*     */   
/*     */   static class c implements Predicate<ItemStack> {
/*     */     private final Tag<Item> a;
/*     */     @Nullable
/*     */     private final NBTTagCompound b;
/*     */     
/*     */     public c(Tag<Item> var0, @Nullable NBTTagCompound var1) {
/* 100 */       this.a = var0;
/* 101 */       this.b = var1;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean test(ItemStack var0) {
/* 106 */       return (this.a.isTagged(var0.getItem()) && GameProfileSerializer.a(this.b, var0.getTag(), true));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentItemPredicate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */