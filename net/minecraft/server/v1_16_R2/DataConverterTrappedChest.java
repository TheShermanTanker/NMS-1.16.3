/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.datafixers.DSL;
/*     */ import com.mojang.datafixers.DataFix;
/*     */ import com.mojang.datafixers.OpticFinder;
/*     */ import com.mojang.datafixers.TypeRewriteRule;
/*     */ import com.mojang.datafixers.Typed;
/*     */ import com.mojang.datafixers.schemas.Schema;
/*     */ import com.mojang.datafixers.types.Type;
/*     */ import com.mojang.datafixers.types.templates.List;
/*     */ import com.mojang.datafixers.types.templates.TaggedChoice;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
/*     */ import it.unimi.dsi.fastutil.ints.IntSet;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ public class DataConverterTrappedChest
/*     */   extends DataFix
/*     */ {
/*  27 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataConverterTrappedChest(Schema var0, boolean var1) {
/*  33 */     super(var0, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public TypeRewriteRule makeRule() {
/*  38 */     Type<?> var0 = getOutputSchema().getType(DataConverterTypes.CHUNK);
/*  39 */     Type<?> var1 = var0.findFieldType("Level");
/*  40 */     Type<?> var2 = var1.findFieldType("TileEntities");
/*  41 */     if (!(var2 instanceof List.ListType)) {
/*  42 */       throw new IllegalStateException("Tile entity type is not a list type.");
/*     */     }
/*  44 */     List.ListType<?> var3 = (List.ListType)var2;
/*     */     
/*  46 */     OpticFinder<? extends List<?>> var4 = DSL.fieldFinder("TileEntities", (Type)var3);
/*     */     
/*  48 */     Type<?> var5 = getInputSchema().getType(DataConverterTypes.CHUNK);
/*     */     
/*  50 */     OpticFinder<?> var6 = var5.findField("Level");
/*  51 */     OpticFinder<?> var7 = var6.type().findField("Sections");
/*  52 */     Type<?> var8 = var7.type();
/*  53 */     if (!(var8 instanceof List.ListType)) {
/*  54 */       throw new IllegalStateException("Expecting sections to be a list.");
/*     */     }
/*  56 */     Type<?> var9 = ((List.ListType)var8).getElement();
/*  57 */     OpticFinder<?> var10 = DSL.typeFinder(var9);
/*     */     
/*  59 */     return TypeRewriteRule.seq((new DataConverterAddChoices(
/*  60 */           getOutputSchema(), "AddTrappedChestFix", DataConverterTypes.BLOCK_ENTITY)).makeRule(), 
/*  61 */         fixTypeEverywhereTyped("Trapped Chest fix", var5, var4 -> var4.updateTyped(var0, ())));
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
/*     */   public static final class a
/*     */     extends DataConverterLeaves.b
/*     */   {
/*     */     @Nullable
/*     */     private IntSet e;
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
/*     */     public a(Typed<?> var0, Schema var1) {
/* 117 */       super(var0, var1);
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean a() {
/* 122 */       this.e = (IntSet)new IntOpenHashSet();
/*     */       
/* 124 */       for (int var0 = 0; var0 < this.b.size(); var0++) {
/* 125 */         Dynamic<?> var1 = this.b.get(var0);
/* 126 */         String var2 = var1.get("Name").asString("");
/* 127 */         if (Objects.equals(var2, "minecraft:trapped_chest")) {
/* 128 */           this.e.add(var0);
/*     */         }
/*     */       } 
/*     */       
/* 132 */       return this.e.isEmpty();
/*     */     }
/*     */     
/*     */     public boolean a(int var0) {
/* 136 */       return this.e.contains(var0);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterTrappedChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */