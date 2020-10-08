/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ 
/*    */ 
/*    */ public class ArgumentNBTBase
/*    */   implements ArgumentType<NBTBase>
/*    */ {
/* 14 */   private static final Collection<String> a = Arrays.asList(new String[] { "0", "0b", "0l", "0.0", "\"foo\"", "{foo=bar}", "[0]" });
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ArgumentNBTBase a() {
/* 20 */     return new ArgumentNBTBase();
/*    */   }
/*    */   
/*    */   public static <S> NBTBase a(CommandContext<S> var0, String var1) {
/* 24 */     return (NBTBase)var0.getArgument(var1, NBTBase.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTBase parse(StringReader var0) throws CommandSyntaxException {
/* 29 */     return (new MojangsonParser(var0)).d();
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 34 */     return a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentNBTBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */