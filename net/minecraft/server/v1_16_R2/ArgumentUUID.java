/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ import java.util.UUID;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ 
/*    */ public class ArgumentUUID
/*    */   implements ArgumentType<UUID>
/*    */ {
/* 18 */   public static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("argument.uuid.invalid"));
/*    */   
/* 20 */   private static final Collection<String> b = Arrays.asList(new String[] { "dd12be42-52a9-4a91-a8a1-11c01849e498" });
/*    */   
/* 22 */   private static final Pattern c = Pattern.compile("^([-A-Fa-f0-9]+)");
/*    */   
/*    */   public static UUID a(CommandContext<CommandListenerWrapper> var0, String var1) {
/* 25 */     return (UUID)var0.getArgument(var1, UUID.class);
/*    */   }
/*    */   
/*    */   public static ArgumentUUID a() {
/* 29 */     return new ArgumentUUID();
/*    */   }
/*    */ 
/*    */   
/*    */   public UUID parse(StringReader var0) throws CommandSyntaxException {
/* 34 */     String var1 = var0.getRemaining();
/* 35 */     Matcher var2 = c.matcher(var1);
/* 36 */     if (var2.find()) {
/* 37 */       String var3 = var2.group(1);
/*    */       try {
/* 39 */         UUID var4 = UUID.fromString(var3);
/* 40 */         var0.setCursor(var0.getCursor() + var3.length());
/* 41 */         return var4;
/* 42 */       } catch (IllegalArgumentException illegalArgumentException) {}
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 47 */     throw a.create();
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 52 */     return b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentUUID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */