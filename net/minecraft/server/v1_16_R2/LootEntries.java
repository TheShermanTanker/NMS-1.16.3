/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootEntries
/*    */ {
/*  9 */   public static final LootEntryType a = a("empty", new LootSelectorEmpty.a());
/* 10 */   public static final LootEntryType b = a("item", new LootItem.a());
/* 11 */   public static final LootEntryType c = a("loot_table", new LootSelectorLootTable.a());
/* 12 */   public static final LootEntryType d = a("dynamic", new LootSelectorDynamic.a());
/* 13 */   public static final LootEntryType e = a("tag", new LootSelectorTag.a());
/*    */   
/* 15 */   public static final LootEntryType f = a("alternatives", LootEntryChildrenAbstract.a(LootEntryAlternatives::new));
/* 16 */   public static final LootEntryType g = a("sequence", LootEntryChildrenAbstract.a(LootEntrySequence::new));
/* 17 */   public static final LootEntryType h = a("group", LootEntryChildrenAbstract.a(LootEntryGroup::new));
/*    */   
/*    */   private static LootEntryType a(String var0, LootSerializer<? extends LootEntryAbstract> var1) {
/* 20 */     return IRegistry.<LootEntryType, LootEntryType>a(IRegistry.ao, new MinecraftKey(var0), new LootEntryType(var1));
/*    */   }
/*    */   
/*    */   public static Object a() {
/* 24 */     return JsonRegistry.a(IRegistry.ao, "entry", "type", LootEntryAbstract::a).a();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootEntries.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */