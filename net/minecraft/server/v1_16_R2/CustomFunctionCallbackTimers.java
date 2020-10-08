/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ import javax.annotation.Nullable;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CustomFunctionCallbackTimers<C>
/*    */ {
/* 15 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/* 17 */   public static final CustomFunctionCallbackTimers<MinecraftServer> a = (new CustomFunctionCallbackTimers())
/* 18 */     .a(new CustomFunctionCallback.a())
/* 19 */     .a(new CustomFunctionCallbackTag.a());
/*    */   
/* 21 */   private final Map<MinecraftKey, CustomFunctionCallbackTimer.a<C, ?>> c = Maps.newHashMap();
/*    */   
/* 23 */   private final Map<Class<?>, CustomFunctionCallbackTimer.a<C, ?>> d = Maps.newHashMap();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CustomFunctionCallbackTimers<C> a(CustomFunctionCallbackTimer.a<C, ?> var0) {
/* 30 */     this.c.put(var0.a(), var0);
/* 31 */     this.d.put(var0.b(), var0);
/* 32 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   private <T extends CustomFunctionCallbackTimer<C>> CustomFunctionCallbackTimer.a<C, T> a(Class<?> var0) {
/* 37 */     return (CustomFunctionCallbackTimer.a<C, T>)this.d.get(var0);
/*    */   }
/*    */   
/*    */   public <T extends CustomFunctionCallbackTimer<C>> NBTTagCompound a(T var0) {
/* 41 */     CustomFunctionCallbackTimer.a<C, T> var1 = a(var0.getClass());
/* 42 */     NBTTagCompound var2 = new NBTTagCompound();
/* 43 */     var1.a(var2, var0);
/* 44 */     var2.setString("Type", var1.a().toString());
/* 45 */     return var2;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public CustomFunctionCallbackTimer<C> a(NBTTagCompound var0) {
/* 50 */     MinecraftKey var1 = MinecraftKey.a(var0.getString("Type"));
/* 51 */     CustomFunctionCallbackTimer.a<C, ?> var2 = this.c.get(var1);
/* 52 */     if (var2 == null) {
/* 53 */       LOGGER.error("Failed to deserialize timer callback: " + var0);
/* 54 */       return null;
/*    */     } 
/*    */     try {
/* 57 */       return (CustomFunctionCallbackTimer<C>)var2.b(var0);
/* 58 */     } catch (Exception var3) {
/* 59 */       LOGGER.error("Failed to deserialize timer callback: " + var0, var3);
/* 60 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CustomFunctionCallbackTimers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */