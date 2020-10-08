/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Streams;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.Stream;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.mutable.MutableInt;
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
/*     */ public class GameTestHarnessRunner
/*     */ {
/*  40 */   public static GameTestHarnessITestReporter a = new GameTestHarnessLogger();
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
/*     */   public static void a(GameTestHarnessInfo var0, BlockPosition var1, GameTestHarnessTicker var2) {
/*  52 */     var0.a();
/*  53 */     var2.a(var0);
/*     */     
/*  55 */     var0.a(new GameTestHarnessListener()
/*     */         {
/*     */           public void a(GameTestHarnessInfo var0) {
/*  58 */             GameTestHarnessRunner.a(var0, Blocks.LIGHT_GRAY_STAINED_GLASS);
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void c(GameTestHarnessInfo var0) {
/*  69 */             GameTestHarnessRunner.a(var0, var0.q() ? Blocks.RED_STAINED_GLASS : Blocks.ORANGE_STAINED_GLASS);
/*  70 */             GameTestHarnessRunner.a(var0, SystemUtils.d(var0.n()));
/*  71 */             GameTestHarnessRunner.b(var0);
/*     */           }
/*     */         });
/*  74 */     var0.a(var1, 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Collection<GameTestHarnessInfo> a(Collection<GameTestHarnessBatch> var0, BlockPosition var1, EnumBlockRotation var2, WorldServer var3, GameTestHarnessTicker var4, int var5) {
/*  83 */     GameTestHarnessBatchRunner var6 = new GameTestHarnessBatchRunner(var0, var1, var2, var3, var4, var5);
/*  84 */     var6.b();
/*  85 */     return var6.a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Collection<GameTestHarnessInfo> b(Collection<GameTestHarnessTestFunction> var0, BlockPosition var1, EnumBlockRotation var2, WorldServer var3, GameTestHarnessTicker var4, int var5) {
/*  92 */     return a(a(var0), var1, var2, var3, var4, var5);
/*     */   }
/*     */   
/*     */   public static Collection<GameTestHarnessBatch> a(Collection<GameTestHarnessTestFunction> var0) {
/*  96 */     Map<String, Collection<GameTestHarnessTestFunction>> var1 = Maps.newHashMap();
/*     */ 
/*     */     
/*  99 */     var0.forEach(var1 -> {
/*     */           String var2 = var1.e();
/*     */           
/*     */           Collection<GameTestHarnessTestFunction> var3 = var0.computeIfAbsent(var2, ());
/*     */           
/*     */           var3.add(var1);
/*     */         });
/* 106 */     return (Collection<GameTestHarnessBatch>)var1.keySet().stream().flatMap(var1 -> {
/*     */           Collection<GameTestHarnessTestFunction> var2 = (Collection<GameTestHarnessTestFunction>)var0.get(var1);
/*     */           
/*     */           Consumer<WorldServer> var3 = GameTestHarnessRegistry.c(var1);
/*     */           MutableInt var4 = new MutableInt();
/*     */           return Streams.stream(Iterables.partition(var2, 100)).map(());
/* 112 */         }).collect(Collectors.toList());
/*     */   }
/*     */   
/*     */   private static void c(GameTestHarnessInfo var0) {
/* 116 */     Throwable var1 = var0.n();
/* 117 */     String var2 = (var0.q() ? "" : "(optional) ") + var0.c() + " failed! " + SystemUtils.d(var1);
/*     */     
/* 119 */     a(var0.g(), var0.q() ? EnumChatFormat.RED : EnumChatFormat.YELLOW, var2);
/*     */     
/* 121 */     if (var1 instanceof GameTestHarnessAssertionPosition) {
/* 122 */       GameTestHarnessAssertionPosition var3 = (GameTestHarnessAssertionPosition)var1;
/* 123 */       a(var0.g(), var3.c(), var3.a());
/*     */     } 
/*     */     
/* 126 */     a.a(var0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void b(GameTestHarnessInfo var0, Block var1) {
/* 136 */     WorldServer var2 = var0.g();
/* 137 */     BlockPosition var3 = var0.d();
/* 138 */     BlockPosition var4 = new BlockPosition(-1, -1, -1);
/* 139 */     BlockPosition var5 = DefinedStructure.a(var3.a(var4), EnumBlockMirror.NONE, var0.t(), var3);
/* 140 */     var2.setTypeUpdate(var5, Blocks.BEACON.getBlockData().a(var0.t()));
/*     */     
/* 142 */     BlockPosition var6 = var5.b(0, 1, 0);
/* 143 */     var2.setTypeUpdate(var6, var1.getBlockData());
/*     */     
/* 145 */     for (int var7 = -1; var7 <= 1; var7++) {
/* 146 */       for (int var8 = -1; var8 <= 1; var8++) {
/* 147 */         BlockPosition var9 = var5.b(var7, -1, var8);
/* 148 */         var2.setTypeUpdate(var9, Blocks.IRON_BLOCK.getBlockData());
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void b(GameTestHarnessInfo var0, String var1) {
/* 154 */     WorldServer var2 = var0.g();
/* 155 */     BlockPosition var3 = var0.d();
/* 156 */     BlockPosition var4 = new BlockPosition(-1, 1, -1);
/* 157 */     BlockPosition var5 = DefinedStructure.a(var3.a(var4), EnumBlockMirror.NONE, var0.t(), var3);
/*     */     
/* 159 */     var2.setTypeUpdate(var5, Blocks.LECTERN.getBlockData().a(var0.t()));
/*     */     
/* 161 */     IBlockData var6 = var2.getType(var5);
/*     */     
/* 163 */     ItemStack var7 = a(var0.c(), var0.q(), var1);
/*     */     
/* 165 */     BlockLectern.a(var2, var5, var6, var7);
/*     */   }
/*     */   
/*     */   private static ItemStack a(String var0, boolean var1, String var2) {
/* 169 */     ItemStack var3 = new ItemStack(Items.WRITABLE_BOOK);
/* 170 */     NBTTagList var4 = new NBTTagList();
/*     */     
/* 172 */     StringBuffer var5 = new StringBuffer();
/* 173 */     Arrays.<String>stream(var0.split("\\.")).forEach(var1 -> var0.append(var1).append('\n'));
/*     */ 
/*     */     
/* 176 */     if (!var1) {
/* 177 */       var5.append("(optional)\n");
/*     */     }
/*     */     
/* 180 */     var5.append("-------------------\n");
/*     */     
/* 182 */     var4.add(NBTTagString.a(var5.toString() + var2));
/* 183 */     var3.a("pages", var4);
/* 184 */     return var3;
/*     */   }
/*     */   
/*     */   private static void a(WorldServer var0, EnumChatFormat var1, String var2) {
/* 188 */     var0.a(var0 -> true).forEach(var2 -> var2.sendMessage((new ChatComponentText(var0)).a(var1), SystemUtils.b));
/*     */   }
/*     */   
/*     */   public static void a(WorldServer var0) {
/* 192 */     PacketDebug.a(var0);
/*     */   }
/*     */   
/*     */   private static void a(WorldServer var0, BlockPosition var1, String var2) {
/* 196 */     PacketDebug.a(var0, var1, var2, -2130771968, 2147483647);
/*     */   }
/*     */   
/*     */   public static void a(WorldServer var0, BlockPosition var1, GameTestHarnessTicker var2, int var3) {
/* 200 */     var2.a();
/* 201 */     BlockPosition var4 = var1.b(-var3, 0, -var3);
/* 202 */     BlockPosition var5 = var1.b(var3, 0, var3);
/* 203 */     BlockPosition.b(var4, var5)
/* 204 */       .filter(var1 -> var0.getType(var1).a(Blocks.STRUCTURE_BLOCK))
/* 205 */       .forEach(var1 -> {
/*     */           TileEntityStructure var2 = (TileEntityStructure)var0.getTileEntity(var1);
/*     */           BlockPosition var3 = var2.getPosition();
/*     */           StructureBoundingBox var4 = GameTestHarnessStructures.b(var2);
/*     */           GameTestHarnessStructures.a(var4, var3.getY(), var0);
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GameTestHarnessRunner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */