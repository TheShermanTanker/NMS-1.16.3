/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.bridge.game.GameVersion;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import io.netty.util.ResourceLeakDetector;
/*     */ import java.time.Duration;
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
/*     */ public class SharedConstants
/*     */ {
/*  85 */   public static final ResourceLeakDetector.Level a = ResourceLeakDetector.Level.DISABLED;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   public static final long b = Duration.ofMillis(300L).toNanos();
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean c = true;
/*     */ 
/*     */   
/*     */   public static boolean d;
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isAllowedChatCharacter(char var0) {
/* 102 */     return (var0 != '§' && var0 >= ' ' && var0 != '');
/*     */   }
/*     */   
/* 105 */   public static final char[] allowedCharacters = new char[] { '/', '\n', '\r', '\t', Character.MIN_VALUE, '\f', '`', '?', '*', '\\', '<', '>', '|', '"', ':' };
/*     */ 
/*     */ 
/*     */   
/*     */   private static GameVersion f;
/*     */ 
/*     */ 
/*     */   
/*     */   public static String a(String var0) {
/* 114 */     StringBuilder var1 = new StringBuilder();
/*     */     
/* 116 */     for (char var5 : var0.toCharArray()) {
/* 117 */       if (isAllowedChatCharacter(var5)) {
/* 118 */         var1.append(var5);
/*     */       }
/*     */     } 
/*     */     
/* 122 */     return var1.toString();
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
/*     */   public static GameVersion getGameVersion() {
/* 136 */     if (f == null) {
/* 137 */       f = MinecraftVersion.a();
/*     */     }
/* 139 */     return f;
/*     */   }
/*     */   
/*     */   static {
/* 143 */     ResourceLeakDetector.setLevel(a);
/* 144 */     CommandSyntaxException.ENABLE_COMMAND_STACK_TRACES = false;
/* 145 */     CommandSyntaxException.BUILT_IN_EXCEPTIONS = new CommandExceptionProvider();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SharedConstants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */