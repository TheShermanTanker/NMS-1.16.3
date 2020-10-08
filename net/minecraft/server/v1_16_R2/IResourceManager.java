/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.IOException;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import java.util.function.Predicate;
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
/*    */ public interface IResourceManager
/*    */ {
/*    */   IResource a(MinecraftKey paramMinecraftKey) throws IOException;
/*    */   
/*    */   List<IResource> c(MinecraftKey paramMinecraftKey) throws IOException;
/*    */   
/*    */   Collection<MinecraftKey> a(String paramString, Predicate<String> paramPredicate);
/*    */   
/*    */   public enum Empty
/*    */     implements IResourceManager
/*    */   {
/* 37 */     INSTANCE;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public IResource a(MinecraftKey var0) throws IOException {
/* 46 */       throw new FileNotFoundException(var0.toString());
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public List<IResource> c(MinecraftKey var0) {
/* 56 */       return (List<IResource>)ImmutableList.of();
/*    */     }
/*    */ 
/*    */     
/*    */     public Collection<MinecraftKey> a(String var0, Predicate<String> var1) {
/* 61 */       return (Collection<MinecraftKey>)ImmutableSet.of();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IResourceManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */