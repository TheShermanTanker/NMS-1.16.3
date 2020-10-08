/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.regex.Pattern;
/*    */ import javax.annotation.Nullable;
/*    */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.StringUtils;
/*    */ 
/*    */ 
/*    */ public class UtilColor
/*    */ {
/* 10 */   private static final Pattern a = Pattern.compile("(?i)\\u00A7[0-9A-FK-OR]");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean b(@Nullable String var0) {
/* 28 */     return StringUtils.isEmpty(var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\UtilColor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */