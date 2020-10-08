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
/*     */ import java.util.Map;
/*     */ import java.util.Set;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArgumentBlockPredicate
/*     */   implements ArgumentType<ArgumentBlockPredicate.b>
/*     */ {
/*     */   private static final DynamicCommandExceptionType b;
/*  33 */   private static final Collection<String> a = Arrays.asList(new String[] { "stone", "minecraft:stone", "stone[foo=bar]", "#stone", "#stone[foo=bar]{baz=nbt}" }); static {
/*  34 */     b = new DynamicCommandExceptionType(var0 -> new ChatMessage("arguments.block.tag.unknown", new Object[] { var0 }));
/*     */   }
/*     */   public static ArgumentBlockPredicate a() {
/*  37 */     return new ArgumentBlockPredicate();
/*     */   }
/*     */ 
/*     */   
/*     */   public b parse(StringReader var0) throws CommandSyntaxException {
/*  42 */     ArgumentBlock var1 = (new ArgumentBlock(var0, true)).a(true);
/*     */     
/*  44 */     if (var1.getBlockData() != null) {
/*  45 */       a a = new a(var1.getBlockData(), var1.getStateMap().keySet(), var1.c());
/*  46 */       return var1 -> var0;
/*     */     } 
/*  48 */     MinecraftKey var2 = var1.d();
/*  49 */     return var2 -> {
/*     */         Tag<Block> var3 = var2.getBlockTags().a(var0);
/*     */         if (var3 == null) {
/*     */           throw b.create(var0.toString());
/*     */         }
/*     */         return new c(var3, var1.j(), var1.c());
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   public static Predicate<ShapeDetectorBlock> a(CommandContext<CommandListenerWrapper> var0, String var1) throws CommandSyntaxException {
/*  60 */     return ((b)var0.getArgument(var1, b.class)).create(((CommandListenerWrapper)var0.getSource()).getServer().getTagRegistry());
/*     */   }
/*     */ 
/*     */   
/*     */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var0, SuggestionsBuilder var1) {
/*  65 */     StringReader var2 = new StringReader(var1.getInput());
/*  66 */     var2.setCursor(var1.getStart());
/*  67 */     ArgumentBlock var3 = new ArgumentBlock(var2, true);
/*     */     try {
/*  69 */       var3.a(true);
/*  70 */     } catch (CommandSyntaxException commandSyntaxException) {}
/*     */     
/*  72 */     return var3.a(var1, TagsBlock.a());
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<String> getExamples() {
/*  77 */     return a;
/*     */   }
/*     */   
/*     */   public static interface b {
/*     */     Predicate<ShapeDetectorBlock> create(ITagRegistry param1ITagRegistry) throws CommandSyntaxException;
/*     */   }
/*     */   
/*     */   static class a implements Predicate<ShapeDetectorBlock> {
/*     */     private final IBlockData a;
/*     */     private final Set<IBlockState<?>> b;
/*     */     @Nullable
/*     */     private final NBTTagCompound c;
/*     */     
/*     */     public a(IBlockData var0, Set<IBlockState<?>> var1, @Nullable NBTTagCompound var2) {
/*  91 */       this.a = var0;
/*  92 */       this.b = var1;
/*  93 */       this.c = var2;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean test(ShapeDetectorBlock var0) {
/*  98 */       IBlockData var1 = var0.a();
/*     */       
/* 100 */       if (!var1.a(this.a.getBlock())) {
/* 101 */         return false;
/*     */       }
/*     */       
/* 104 */       for (IBlockState<?> var3 : this.b) {
/* 105 */         if (var1.get(var3) != this.a.get(var3)) {
/* 106 */           return false;
/*     */         }
/*     */       } 
/*     */       
/* 110 */       if (this.c != null) {
/* 111 */         TileEntity var2 = var0.b();
/* 112 */         return (var2 != null && GameProfileSerializer.a(this.c, var2.save(new NBTTagCompound()), true));
/*     */       } 
/*     */       
/* 115 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   static class c implements Predicate<ShapeDetectorBlock> {
/*     */     private final Tag<Block> a;
/*     */     @Nullable
/*     */     private final NBTTagCompound b;
/*     */     private final Map<String, String> c;
/*     */     
/*     */     private c(Tag<Block> var0, Map<String, String> var1, @Nullable NBTTagCompound var2) {
/* 126 */       this.a = var0;
/* 127 */       this.c = var1;
/* 128 */       this.b = var2;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean test(ShapeDetectorBlock var0) {
/* 133 */       IBlockData var1 = var0.a();
/*     */       
/* 135 */       if (!var1.a(this.a)) {
/* 136 */         return false;
/*     */       }
/*     */       
/* 139 */       for (Map.Entry<String, String> var3 : this.c.entrySet()) {
/* 140 */         IBlockState<?> var4 = var1.getBlock().getStates().a(var3.getKey());
/* 141 */         if (var4 == null) {
/* 142 */           return false;
/*     */         }
/* 144 */         Comparable<?> var5 = var4.b(var3.getValue()).orElse(null);
/* 145 */         if (var5 == null) {
/* 146 */           return false;
/*     */         }
/* 148 */         if (var1.get(var4) != var5) {
/* 149 */           return false;
/*     */         }
/*     */       } 
/*     */       
/* 153 */       if (this.b != null) {
/* 154 */         TileEntity var2 = var0.b();
/* 155 */         return (var2 != null && GameProfileSerializer.a(this.b, var2.save(new NBTTagCompound()), true));
/*     */       } 
/*     */       
/* 158 */       return true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentBlockPredicate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */