/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.mojang.brigadier.Message;
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*    */ import com.mojang.brigadier.suggestion.Suggestions;
/*    */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArgumentInventorySlot
/*    */   implements ArgumentType<Integer>
/*    */ {
/* 27 */   private static final Collection<String> a = Arrays.asList(new String[] { "container.5", "12", "weapon" }); static {
/* 28 */     b = new DynamicCommandExceptionType(var0 -> new ChatMessage("slot.unknown", new Object[] { var0 }));
/* 29 */     c = SystemUtils.<Map<String, Integer>>a(Maps.newHashMap(), var0 -> {
/*    */           int var1;
/*    */           for (var1 = 0; var1 < 54; var1++) {
/*    */             var0.put("container." + var1, Integer.valueOf(var1));
/*    */           }
/*    */           for (var1 = 0; var1 < 9; var1++)
/*    */             var0.put("hotbar." + var1, Integer.valueOf(var1)); 
/*    */           for (var1 = 0; var1 < 27; var1++)
/*    */             var0.put("inventory." + var1, Integer.valueOf(9 + var1)); 
/*    */           for (var1 = 0; var1 < 27; var1++)
/*    */             var0.put("enderchest." + var1, Integer.valueOf(200 + var1)); 
/*    */           for (var1 = 0; var1 < 8; var1++)
/*    */             var0.put("villager." + var1, Integer.valueOf(300 + var1)); 
/*    */           for (var1 = 0; var1 < 15; var1++)
/*    */             var0.put("horse." + var1, Integer.valueOf(500 + var1)); 
/*    */           var0.put("weapon", Integer.valueOf(98));
/*    */           var0.put("weapon.mainhand", Integer.valueOf(98));
/*    */           var0.put("weapon.offhand", Integer.valueOf(99));
/*    */           var0.put("armor.head", Integer.valueOf(100 + EnumItemSlot.HEAD.b()));
/*    */           var0.put("armor.chest", Integer.valueOf(100 + EnumItemSlot.CHEST.b()));
/*    */           var0.put("armor.legs", Integer.valueOf(100 + EnumItemSlot.LEGS.b()));
/*    */           var0.put("armor.feet", Integer.valueOf(100 + EnumItemSlot.FEET.b()));
/*    */           var0.put("horse.saddle", Integer.valueOf(400));
/*    */           var0.put("horse.armor", Integer.valueOf(401));
/*    */           var0.put("horse.chest", Integer.valueOf(499));
/*    */         });
/*    */   }
/*    */   
/*    */   private static final DynamicCommandExceptionType b;
/*    */   private static final Map<String, Integer> c;
/*    */   
/*    */   public static ArgumentInventorySlot a() {
/* 61 */     return new ArgumentInventorySlot();
/*    */   }
/*    */   
/*    */   public static int a(CommandContext<CommandListenerWrapper> var0, String var1) {
/* 65 */     return ((Integer)var0.getArgument(var1, Integer.class)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer parse(StringReader var0) throws CommandSyntaxException {
/* 70 */     String var1 = var0.readUnquotedString();
/* 71 */     if (!c.containsKey(var1)) {
/* 72 */       throw b.create(var1);
/*    */     }
/* 74 */     return c.get(var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var0, SuggestionsBuilder var1) {
/* 79 */     return ICompletionProvider.b(c.keySet(), var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 84 */     return a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentInventorySlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */