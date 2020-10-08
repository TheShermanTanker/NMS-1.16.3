/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.base.Function;
/*    */ import com.google.common.base.Functions;
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.common.collect.Sets;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.function.Function;
/*    */ import javax.annotation.Nullable;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class Advancements
/*    */ {
/* 18 */   private static final Logger LOGGER = LogManager.getLogger();
/* 19 */   public final Map<MinecraftKey, Advancement> advancements = Maps.newHashMap();
/* 20 */   private final Set<Advancement> c = Sets.newLinkedHashSet();
/* 21 */   private final Set<Advancement> d = Sets.newLinkedHashSet();
/*    */   
/*    */   private a e;
/*    */ 
/*    */   
/*    */   public void a(Map<MinecraftKey, Advancement.SerializedAdvancement> map) {
/* 27 */     Function function = Functions.forMap(this.advancements, null);
/*    */ 
/*    */     
/* 30 */     while (!map.isEmpty()) {
/* 31 */       boolean flag = false;
/* 32 */       Iterator<Map.Entry> iterator = map.entrySet().iterator();
/*    */ 
/*    */ 
/*    */       
/* 36 */       while (iterator.hasNext()) {
/* 37 */         Map.Entry entry = iterator.next();
/* 38 */         MinecraftKey minecraftkey = (MinecraftKey)entry.getKey();
/* 39 */         Advancement.SerializedAdvancement advancement_serializedadvancement = (Advancement.SerializedAdvancement)entry.getValue();
/*    */         
/* 41 */         if (advancement_serializedadvancement.a((Function<MinecraftKey, Advancement>)function)) {
/* 42 */           Advancement advancement = advancement_serializedadvancement.b(minecraftkey);
/*    */           
/* 44 */           this.advancements.put(minecraftkey, advancement);
/* 45 */           flag = true;
/* 46 */           iterator.remove();
/* 47 */           if (advancement.b() == null) {
/* 48 */             this.c.add(advancement);
/* 49 */             if (this.e != null)
/* 50 */               this.e.a(advancement); 
/*    */             continue;
/*    */           } 
/* 53 */           this.d.add(advancement);
/* 54 */           if (this.e != null) {
/* 55 */             this.e.c(advancement);
/*    */           }
/*    */         } 
/*    */       } 
/*    */ 
/*    */       
/* 61 */       if (!flag) {
/* 62 */         iterator = map.entrySet().iterator();
/*    */ 
/*    */         
/* 65 */         while (iterator.hasNext()) {
/*    */ 
/*    */ 
/*    */           
/* 69 */           Map.Entry entry = iterator.next();
/* 70 */           LOGGER.error("Couldn't load advancement {}: {}", entry.getKey(), entry.getValue());
/*    */         } 
/*    */         break;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Iterable<Advancement> b() {
/* 79 */     return this.c;
/*    */   }
/*    */   
/*    */   public Collection<Advancement> c() {
/* 83 */     return this.advancements.values();
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public Advancement a(MinecraftKey minecraftkey) {
/* 88 */     return this.advancements.get(minecraftkey);
/*    */   }
/*    */   
/*    */   public static interface a {
/*    */     void a(Advancement param1Advancement);
/*    */     
/*    */     void c(Advancement param1Advancement);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Advancements.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */