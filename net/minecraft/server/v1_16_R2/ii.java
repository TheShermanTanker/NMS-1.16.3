/*      */ package net.minecraft.server.v1_16_R2;
/*      */ 
/*      */ import com.google.common.collect.ImmutableList;
/*      */ import com.google.gson.JsonElement;
/*      */ import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
/*      */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*      */ import java.util.Arrays;
/*      */ import java.util.List;
/*      */ import java.util.function.BiConsumer;
/*      */ import java.util.function.BiFunction;
/*      */ import java.util.function.Consumer;
/*      */ import java.util.function.Function;
/*      */ import java.util.function.Supplier;
/*      */ import java.util.function.UnaryOperator;
/*      */ import java.util.stream.Collectors;
/*      */ import java.util.stream.IntStream;
/*      */ import javax.annotation.Nullable;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ii
/*      */ {
/*      */   private final Consumer<il> a;
/*      */   private final BiConsumer<MinecraftKey, Supplier<JsonElement>> b;
/*      */   private final Consumer<Item> c;
/*      */   
/*      */   public ii(Consumer<il> var0, BiConsumer<MinecraftKey, Supplier<JsonElement>> var1, Consumer<Item> var2) {
/*   82 */     this.a = var0;
/*   83 */     this.b = var1;
/*   84 */     this.c = var2;
/*      */   }
/*      */   
/*      */   private void a(Block var0) {
/*   88 */     this.c.accept(var0.getItem());
/*      */   }
/*      */   
/*      */   private void c(Block var0, MinecraftKey var1) {
/*   92 */     this.b.accept(iw.a(var0.getItem()), new iv(var1));
/*      */   }
/*      */   
/*      */   private void a(Item var0, MinecraftKey var1) {
/*   96 */     this.b.accept(iw.a(var0), new iv(var1));
/*      */   }
/*      */   
/*      */   private void a(Item var0) {
/*  100 */     iy.aK.a(iw.a(var0), iz.b(var0), this.b);
/*      */   }
/*      */   
/*      */   private void b(Block var0) {
/*  104 */     Item var1 = var0.getItem();
/*  105 */     if (var1 != Items.AIR) {
/*  106 */       iy.aK.a(iw.a(var1), iz.B(var0), this.b);
/*      */     }
/*      */   }
/*      */   
/*      */   private void a(Block var0, String var1) {
/*  111 */     Item var2 = var0.getItem();
/*  112 */     iy.aK.a(iw.a(var2), iz.j(iz.a(var0, var1)), this.b);
/*      */   }
/*      */   
/*      */   private static ip b() {
/*  116 */     return ip.<EnumDirection>a(BlockProperties.O)
/*  117 */       .a(EnumDirection.EAST, ir.a().a(is.b, is.a.b))
/*  118 */       .a(EnumDirection.SOUTH, ir.a().a(is.b, is.a.c))
/*  119 */       .a(EnumDirection.WEST, ir.a().a(is.b, is.a.d))
/*  120 */       .a(EnumDirection.NORTH, ir.a());
/*      */   }
/*      */   
/*      */   private static ip c() {
/*  124 */     return ip.<EnumDirection>a(BlockProperties.O)
/*  125 */       .a(EnumDirection.SOUTH, ir.a())
/*  126 */       .a(EnumDirection.WEST, ir.a().a(is.b, is.a.b))
/*  127 */       .a(EnumDirection.NORTH, ir.a().a(is.b, is.a.c))
/*  128 */       .a(EnumDirection.EAST, ir.a().a(is.b, is.a.d));
/*      */   }
/*      */   
/*      */   private static ip d() {
/*  132 */     return ip.<EnumDirection>a(BlockProperties.O)
/*  133 */       .a(EnumDirection.EAST, ir.a())
/*  134 */       .a(EnumDirection.SOUTH, ir.a().a(is.b, is.a.b))
/*  135 */       .a(EnumDirection.WEST, ir.a().a(is.b, is.a.c))
/*  136 */       .a(EnumDirection.NORTH, ir.a().a(is.b, is.a.d));
/*      */   }
/*      */   
/*      */   private static ip e() {
/*  140 */     return ip.<EnumDirection>a(BlockProperties.M)
/*  141 */       .a(EnumDirection.DOWN, ir.a().a(is.a, is.a.b))
/*  142 */       .a(EnumDirection.UP, ir.a().a(is.a, is.a.d))
/*  143 */       .a(EnumDirection.NORTH, ir.a())
/*  144 */       .a(EnumDirection.SOUTH, ir.a().a(is.b, is.a.c))
/*  145 */       .a(EnumDirection.WEST, ir.a().a(is.b, is.a.d))
/*  146 */       .a(EnumDirection.EAST, ir.a().a(is.b, is.a.b));
/*      */   }
/*      */   
/*      */   private static io d(Block var0, MinecraftKey var1) {
/*  150 */     return io.a(var0, a(var1));
/*      */   }
/*      */   
/*      */   private static ir[] a(MinecraftKey var0) {
/*  154 */     return new ir[] { ir.a().a(is.c, var0), 
/*  155 */         ir.a().<MinecraftKey>a(is.c, var0).a(is.b, is.a.b), 
/*  156 */         ir.a().<MinecraftKey>a(is.c, var0).a(is.b, is.a.c), 
/*  157 */         ir.a().<MinecraftKey>a(is.c, var0).a(is.b, is.a.d) };
/*      */   }
/*      */   
/*      */   private static io e(Block var0, MinecraftKey var1, MinecraftKey var2) {
/*  161 */     return io.a(var0, new ir[] {
/*  162 */           ir.a().a(is.c, var1), 
/*  163 */           ir.a().a(is.c, var2), 
/*  164 */           ir.a().<MinecraftKey>a(is.c, var1).a(is.b, is.a.c), 
/*  165 */           ir.a().<MinecraftKey>a(is.c, var2).a(is.b, is.a.c)
/*      */         });
/*      */   }
/*      */   
/*      */   private static ip a(BlockStateBoolean var0, MinecraftKey var1, MinecraftKey var2) {
/*  170 */     return ip.<Boolean>a(var0)
/*  171 */       .a(Boolean.valueOf(true), ir.a().a(is.c, var1))
/*  172 */       .a(Boolean.valueOf(false), ir.a().a(is.c, var2));
/*      */   }
/*      */   
/*      */   private void c(Block var0) {
/*  176 */     MinecraftKey var1 = jb.a.a(var0, this.b);
/*  177 */     MinecraftKey var2 = jb.b.a(var0, this.b);
/*  178 */     this.a.accept(e(var0, var1, var2));
/*      */   }
/*      */   
/*      */   private void d(Block var0) {
/*  182 */     MinecraftKey var1 = jb.a.a(var0, this.b);
/*  183 */     this.a.accept(d(var0, var1));
/*      */   }
/*      */   
/*      */   private static il f(Block var0, MinecraftKey var1, MinecraftKey var2) {
/*  187 */     return io.a(var0)
/*  188 */       .a(
/*  189 */         ip.<Boolean>a(BlockProperties.w)
/*  190 */         .a(Boolean.valueOf(false), ir.a().a(is.c, var1))
/*  191 */         .a(Boolean.valueOf(true), ir.a().a(is.c, var2)))
/*      */       
/*  193 */       .a(
/*  194 */         ip.<BlockPropertyAttachPosition, EnumDirection>a(BlockProperties.Q, BlockProperties.O)
/*  195 */         .a(BlockPropertyAttachPosition.FLOOR, EnumDirection.EAST, ir.a().a(is.b, is.a.b))
/*  196 */         .a(BlockPropertyAttachPosition.FLOOR, EnumDirection.WEST, ir.a().a(is.b, is.a.d))
/*  197 */         .a(BlockPropertyAttachPosition.FLOOR, EnumDirection.SOUTH, ir.a().a(is.b, is.a.c))
/*  198 */         .a(BlockPropertyAttachPosition.FLOOR, EnumDirection.NORTH, ir.a())
/*      */         
/*  200 */         .a(BlockPropertyAttachPosition.WALL, EnumDirection.EAST, ir.a().<is.a>a(is.b, is.a.b).<is.a>a(is.a, is.a.b).a(is.d, Boolean.valueOf(true)))
/*  201 */         .a(BlockPropertyAttachPosition.WALL, EnumDirection.WEST, ir.a().<is.a>a(is.b, is.a.d).<is.a>a(is.a, is.a.b).a(is.d, Boolean.valueOf(true)))
/*  202 */         .a(BlockPropertyAttachPosition.WALL, EnumDirection.SOUTH, ir.a().<is.a>a(is.b, is.a.c).<is.a>a(is.a, is.a.b).a(is.d, Boolean.valueOf(true)))
/*  203 */         .a(BlockPropertyAttachPosition.WALL, EnumDirection.NORTH, ir.a().<is.a>a(is.a, is.a.b).a(is.d, Boolean.valueOf(true)))
/*      */         
/*  205 */         .a(BlockPropertyAttachPosition.CEILING, EnumDirection.EAST, ir.a().<is.a>a(is.b, is.a.d).a(is.a, is.a.c))
/*  206 */         .a(BlockPropertyAttachPosition.CEILING, EnumDirection.WEST, ir.a().<is.a>a(is.b, is.a.b).a(is.a, is.a.c))
/*  207 */         .a(BlockPropertyAttachPosition.CEILING, EnumDirection.SOUTH, ir.a().a(is.a, is.a.c))
/*  208 */         .a(BlockPropertyAttachPosition.CEILING, EnumDirection.NORTH, ir.a().<is.a>a(is.b, is.a.c).a(is.a, is.a.c)));
/*      */   }
/*      */ 
/*      */   
/*      */   private static ip.d<EnumDirection, BlockPropertyDoubleBlockHalf, BlockPropertyDoorHinge, Boolean> a(ip.d<EnumDirection, BlockPropertyDoubleBlockHalf, BlockPropertyDoorHinge, Boolean> var0, BlockPropertyDoubleBlockHalf var1, MinecraftKey var2, MinecraftKey var3) {
/*  213 */     return var0
/*  214 */       .a(EnumDirection.EAST, var1, BlockPropertyDoorHinge.LEFT, Boolean.valueOf(false), ir.a().a(is.c, var2))
/*  215 */       .a(EnumDirection.SOUTH, var1, BlockPropertyDoorHinge.LEFT, Boolean.valueOf(false), ir.a().<MinecraftKey>a(is.c, var2).a(is.b, is.a.b))
/*  216 */       .a(EnumDirection.WEST, var1, BlockPropertyDoorHinge.LEFT, Boolean.valueOf(false), ir.a().<MinecraftKey>a(is.c, var2).a(is.b, is.a.c))
/*  217 */       .a(EnumDirection.NORTH, var1, BlockPropertyDoorHinge.LEFT, Boolean.valueOf(false), ir.a().<MinecraftKey>a(is.c, var2).a(is.b, is.a.d))
/*      */       
/*  219 */       .a(EnumDirection.EAST, var1, BlockPropertyDoorHinge.RIGHT, Boolean.valueOf(false), ir.a().a(is.c, var3))
/*  220 */       .a(EnumDirection.SOUTH, var1, BlockPropertyDoorHinge.RIGHT, Boolean.valueOf(false), ir.a().<MinecraftKey>a(is.c, var3).a(is.b, is.a.b))
/*  221 */       .a(EnumDirection.WEST, var1, BlockPropertyDoorHinge.RIGHT, Boolean.valueOf(false), ir.a().<MinecraftKey>a(is.c, var3).a(is.b, is.a.c))
/*  222 */       .a(EnumDirection.NORTH, var1, BlockPropertyDoorHinge.RIGHT, Boolean.valueOf(false), ir.a().<MinecraftKey>a(is.c, var3).a(is.b, is.a.d))
/*      */       
/*  224 */       .a(EnumDirection.EAST, var1, BlockPropertyDoorHinge.LEFT, Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, var3).a(is.b, is.a.b))
/*  225 */       .a(EnumDirection.SOUTH, var1, BlockPropertyDoorHinge.LEFT, Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, var3).a(is.b, is.a.c))
/*  226 */       .a(EnumDirection.WEST, var1, BlockPropertyDoorHinge.LEFT, Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, var3).a(is.b, is.a.d))
/*  227 */       .a(EnumDirection.NORTH, var1, BlockPropertyDoorHinge.LEFT, Boolean.valueOf(true), ir.a().a(is.c, var3))
/*      */       
/*  229 */       .a(EnumDirection.EAST, var1, BlockPropertyDoorHinge.RIGHT, Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, var2).a(is.b, is.a.d))
/*  230 */       .a(EnumDirection.SOUTH, var1, BlockPropertyDoorHinge.RIGHT, Boolean.valueOf(true), ir.a().a(is.c, var2))
/*  231 */       .a(EnumDirection.WEST, var1, BlockPropertyDoorHinge.RIGHT, Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, var2).a(is.b, is.a.b))
/*  232 */       .a(EnumDirection.NORTH, var1, BlockPropertyDoorHinge.RIGHT, Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, var2).a(is.b, is.a.c));
/*      */   }
/*      */   
/*      */   private static il b(Block var0, MinecraftKey var1, MinecraftKey var2, MinecraftKey var3, MinecraftKey var4) {
/*  236 */     return io.a(var0)
/*  237 */       .a(
/*  238 */         a(
/*  239 */           a(ip.a(BlockProperties.O, BlockProperties.aa, BlockProperties.aH, BlockProperties.u), BlockPropertyDoubleBlockHalf.LOWER, var1, var2), BlockPropertyDoubleBlockHalf.UPPER, var3, var4));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static il g(Block var0, MinecraftKey var1, MinecraftKey var2) {
/*  246 */     return in.a(var0)
/*  247 */       .a(ir.a().a(is.c, var1))
/*  248 */       .a(im.a().a(BlockProperties.I, Boolean.valueOf(true)), ir.a().<MinecraftKey>a(is.c, var2).a(is.d, Boolean.valueOf(true)))
/*  249 */       .a(im.a().a(BlockProperties.J, Boolean.valueOf(true)), ir.a().<MinecraftKey>a(is.c, var2).<is.a>a(is.b, is.a.b).a(is.d, Boolean.valueOf(true)))
/*  250 */       .a(im.a().a(BlockProperties.K, Boolean.valueOf(true)), ir.a().<MinecraftKey>a(is.c, var2).<is.a>a(is.b, is.a.c).a(is.d, Boolean.valueOf(true)))
/*  251 */       .a(im.a().a(BlockProperties.L, Boolean.valueOf(true)), ir.a().<MinecraftKey>a(is.c, var2).<is.a>a(is.b, is.a.d).a(is.d, Boolean.valueOf(true)));
/*      */   }
/*      */   
/*      */   private static il d(Block var0, MinecraftKey var1, MinecraftKey var2, MinecraftKey var3) {
/*  255 */     return in.a(var0)
/*  256 */       .a(im.a().a(BlockProperties.G, Boolean.valueOf(true)), ir.a().a(is.c, var1))
/*      */       
/*  258 */       .a(im.a().a(BlockProperties.T, BlockPropertyWallHeight.LOW), ir.a().<MinecraftKey>a(is.c, var2).a(is.d, Boolean.valueOf(true)))
/*  259 */       .a(im.a().a(BlockProperties.S, BlockPropertyWallHeight.LOW), ir.a().<MinecraftKey>a(is.c, var2).<is.a>a(is.b, is.a.b).a(is.d, Boolean.valueOf(true)))
/*  260 */       .a(im.a().a(BlockProperties.U, BlockPropertyWallHeight.LOW), ir.a().<MinecraftKey>a(is.c, var2).<is.a>a(is.b, is.a.c).a(is.d, Boolean.valueOf(true)))
/*  261 */       .a(im.a().a(BlockProperties.V, BlockPropertyWallHeight.LOW), ir.a().<MinecraftKey>a(is.c, var2).<is.a>a(is.b, is.a.d).a(is.d, Boolean.valueOf(true)))
/*      */       
/*  263 */       .a(im.a().a(BlockProperties.T, BlockPropertyWallHeight.TALL), ir.a().<MinecraftKey>a(is.c, var3).a(is.d, Boolean.valueOf(true)))
/*  264 */       .a(im.a().a(BlockProperties.S, BlockPropertyWallHeight.TALL), ir.a().<MinecraftKey>a(is.c, var3).<is.a>a(is.b, is.a.b).a(is.d, Boolean.valueOf(true)))
/*  265 */       .a(im.a().a(BlockProperties.U, BlockPropertyWallHeight.TALL), ir.a().<MinecraftKey>a(is.c, var3).<is.a>a(is.b, is.a.c).a(is.d, Boolean.valueOf(true)))
/*  266 */       .a(im.a().a(BlockProperties.V, BlockPropertyWallHeight.TALL), ir.a().<MinecraftKey>a(is.c, var3).<is.a>a(is.b, is.a.d).a(is.d, Boolean.valueOf(true)));
/*      */   }
/*      */ 
/*      */   
/*      */   private static il c(Block var0, MinecraftKey var1, MinecraftKey var2, MinecraftKey var3, MinecraftKey var4) {
/*  271 */     return io.a(var0, ir.a().a(is.d, Boolean.valueOf(true)))
/*  272 */       .a(c())
/*  273 */       .a(
/*  274 */         ip.<Boolean, Boolean>a(BlockProperties.q, BlockProperties.u)
/*  275 */         .a(Boolean.valueOf(false), Boolean.valueOf(false), ir.a().a(is.c, var2))
/*  276 */         .a(Boolean.valueOf(true), Boolean.valueOf(false), ir.a().a(is.c, var4))
/*  277 */         .a(Boolean.valueOf(false), Boolean.valueOf(true), ir.a().a(is.c, var1))
/*  278 */         .a(Boolean.valueOf(true), Boolean.valueOf(true), ir.a().a(is.c, var3)));
/*      */   }
/*      */ 
/*      */   
/*      */   private static il e(Block var0, MinecraftKey var1, MinecraftKey var2, MinecraftKey var3) {
/*  283 */     return io.a(var0)
/*  284 */       .a(
/*  285 */         ip.<EnumDirection, BlockPropertyHalf, BlockPropertyStairsShape>a(BlockProperties.O, BlockProperties.ab, BlockProperties.aL)
/*  286 */         .a(EnumDirection.EAST, BlockPropertyHalf.BOTTOM, BlockPropertyStairsShape.STRAIGHT, ir.a().a(is.c, var2))
/*  287 */         .a(EnumDirection.WEST, BlockPropertyHalf.BOTTOM, BlockPropertyStairsShape.STRAIGHT, ir.a().<MinecraftKey>a(is.c, var2).<is.a>a(is.b, is.a.c).a(is.d, Boolean.valueOf(true)))
/*  288 */         .a(EnumDirection.SOUTH, BlockPropertyHalf.BOTTOM, BlockPropertyStairsShape.STRAIGHT, ir.a().<MinecraftKey>a(is.c, var2).<is.a>a(is.b, is.a.b).a(is.d, Boolean.valueOf(true)))
/*  289 */         .a(EnumDirection.NORTH, BlockPropertyHalf.BOTTOM, BlockPropertyStairsShape.STRAIGHT, ir.a().<MinecraftKey>a(is.c, var2).<is.a>a(is.b, is.a.d).a(is.d, Boolean.valueOf(true)))
/*  290 */         .a(EnumDirection.EAST, BlockPropertyHalf.BOTTOM, BlockPropertyStairsShape.OUTER_RIGHT, ir.a().a(is.c, var3))
/*  291 */         .a(EnumDirection.WEST, BlockPropertyHalf.BOTTOM, BlockPropertyStairsShape.OUTER_RIGHT, ir.a().<MinecraftKey>a(is.c, var3).<is.a>a(is.b, is.a.c).a(is.d, Boolean.valueOf(true)))
/*  292 */         .a(EnumDirection.SOUTH, BlockPropertyHalf.BOTTOM, BlockPropertyStairsShape.OUTER_RIGHT, ir.a().<MinecraftKey>a(is.c, var3).<is.a>a(is.b, is.a.b).a(is.d, Boolean.valueOf(true)))
/*  293 */         .a(EnumDirection.NORTH, BlockPropertyHalf.BOTTOM, BlockPropertyStairsShape.OUTER_RIGHT, ir.a().<MinecraftKey>a(is.c, var3).<is.a>a(is.b, is.a.d).a(is.d, Boolean.valueOf(true)))
/*  294 */         .a(EnumDirection.EAST, BlockPropertyHalf.BOTTOM, BlockPropertyStairsShape.OUTER_LEFT, ir.a().<MinecraftKey>a(is.c, var3).<is.a>a(is.b, is.a.d).a(is.d, Boolean.valueOf(true)))
/*  295 */         .a(EnumDirection.WEST, BlockPropertyHalf.BOTTOM, BlockPropertyStairsShape.OUTER_LEFT, ir.a().<MinecraftKey>a(is.c, var3).<is.a>a(is.b, is.a.b).a(is.d, Boolean.valueOf(true)))
/*  296 */         .a(EnumDirection.SOUTH, BlockPropertyHalf.BOTTOM, BlockPropertyStairsShape.OUTER_LEFT, ir.a().a(is.c, var3))
/*  297 */         .a(EnumDirection.NORTH, BlockPropertyHalf.BOTTOM, BlockPropertyStairsShape.OUTER_LEFT, ir.a().<MinecraftKey>a(is.c, var3).<is.a>a(is.b, is.a.c).a(is.d, Boolean.valueOf(true)))
/*  298 */         .a(EnumDirection.EAST, BlockPropertyHalf.BOTTOM, BlockPropertyStairsShape.INNER_RIGHT, ir.a().a(is.c, var1))
/*  299 */         .a(EnumDirection.WEST, BlockPropertyHalf.BOTTOM, BlockPropertyStairsShape.INNER_RIGHT, ir.a().<MinecraftKey>a(is.c, var1).<is.a>a(is.b, is.a.c).a(is.d, Boolean.valueOf(true)))
/*  300 */         .a(EnumDirection.SOUTH, BlockPropertyHalf.BOTTOM, BlockPropertyStairsShape.INNER_RIGHT, ir.a().<MinecraftKey>a(is.c, var1).<is.a>a(is.b, is.a.b).a(is.d, Boolean.valueOf(true)))
/*  301 */         .a(EnumDirection.NORTH, BlockPropertyHalf.BOTTOM, BlockPropertyStairsShape.INNER_RIGHT, ir.a().<MinecraftKey>a(is.c, var1).<is.a>a(is.b, is.a.d).a(is.d, Boolean.valueOf(true)))
/*  302 */         .a(EnumDirection.EAST, BlockPropertyHalf.BOTTOM, BlockPropertyStairsShape.INNER_LEFT, ir.a().<MinecraftKey>a(is.c, var1).<is.a>a(is.b, is.a.d).a(is.d, Boolean.valueOf(true)))
/*  303 */         .a(EnumDirection.WEST, BlockPropertyHalf.BOTTOM, BlockPropertyStairsShape.INNER_LEFT, ir.a().<MinecraftKey>a(is.c, var1).<is.a>a(is.b, is.a.b).a(is.d, Boolean.valueOf(true)))
/*  304 */         .a(EnumDirection.SOUTH, BlockPropertyHalf.BOTTOM, BlockPropertyStairsShape.INNER_LEFT, ir.a().a(is.c, var1))
/*  305 */         .a(EnumDirection.NORTH, BlockPropertyHalf.BOTTOM, BlockPropertyStairsShape.INNER_LEFT, ir.a().<MinecraftKey>a(is.c, var1).<is.a>a(is.b, is.a.c).a(is.d, Boolean.valueOf(true)))
/*  306 */         .a(EnumDirection.EAST, BlockPropertyHalf.TOP, BlockPropertyStairsShape.STRAIGHT, ir.a().<MinecraftKey>a(is.c, var2).<is.a>a(is.a, is.a.c).a(is.d, Boolean.valueOf(true)))
/*  307 */         .a(EnumDirection.WEST, BlockPropertyHalf.TOP, BlockPropertyStairsShape.STRAIGHT, ir.a().<MinecraftKey>a(is.c, var2).<is.a>a(is.a, is.a.c).<is.a>a(is.b, is.a.c).a(is.d, Boolean.valueOf(true)))
/*  308 */         .a(EnumDirection.SOUTH, BlockPropertyHalf.TOP, BlockPropertyStairsShape.STRAIGHT, ir.a().<MinecraftKey>a(is.c, var2).<is.a>a(is.a, is.a.c).<is.a>a(is.b, is.a.b).a(is.d, Boolean.valueOf(true)))
/*  309 */         .a(EnumDirection.NORTH, BlockPropertyHalf.TOP, BlockPropertyStairsShape.STRAIGHT, ir.a().<MinecraftKey>a(is.c, var2).<is.a>a(is.a, is.a.c).<is.a>a(is.b, is.a.d).a(is.d, Boolean.valueOf(true)))
/*  310 */         .a(EnumDirection.EAST, BlockPropertyHalf.TOP, BlockPropertyStairsShape.OUTER_RIGHT, ir.a().<MinecraftKey>a(is.c, var3).<is.a>a(is.a, is.a.c).<is.a>a(is.b, is.a.b).a(is.d, Boolean.valueOf(true)))
/*  311 */         .a(EnumDirection.WEST, BlockPropertyHalf.TOP, BlockPropertyStairsShape.OUTER_RIGHT, ir.a().<MinecraftKey>a(is.c, var3).<is.a>a(is.a, is.a.c).<is.a>a(is.b, is.a.d).a(is.d, Boolean.valueOf(true)))
/*  312 */         .a(EnumDirection.SOUTH, BlockPropertyHalf.TOP, BlockPropertyStairsShape.OUTER_RIGHT, ir.a().<MinecraftKey>a(is.c, var3).<is.a>a(is.a, is.a.c).<is.a>a(is.b, is.a.c).a(is.d, Boolean.valueOf(true)))
/*  313 */         .a(EnumDirection.NORTH, BlockPropertyHalf.TOP, BlockPropertyStairsShape.OUTER_RIGHT, ir.a().<MinecraftKey>a(is.c, var3).<is.a>a(is.a, is.a.c).a(is.d, Boolean.valueOf(true)))
/*  314 */         .a(EnumDirection.EAST, BlockPropertyHalf.TOP, BlockPropertyStairsShape.OUTER_LEFT, ir.a().<MinecraftKey>a(is.c, var3).<is.a>a(is.a, is.a.c).a(is.d, Boolean.valueOf(true)))
/*  315 */         .a(EnumDirection.WEST, BlockPropertyHalf.TOP, BlockPropertyStairsShape.OUTER_LEFT, ir.a().<MinecraftKey>a(is.c, var3).<is.a>a(is.a, is.a.c).<is.a>a(is.b, is.a.c).a(is.d, Boolean.valueOf(true)))
/*  316 */         .a(EnumDirection.SOUTH, BlockPropertyHalf.TOP, BlockPropertyStairsShape.OUTER_LEFT, ir.a().<MinecraftKey>a(is.c, var3).<is.a>a(is.a, is.a.c).<is.a>a(is.b, is.a.b).a(is.d, Boolean.valueOf(true)))
/*  317 */         .a(EnumDirection.NORTH, BlockPropertyHalf.TOP, BlockPropertyStairsShape.OUTER_LEFT, ir.a().<MinecraftKey>a(is.c, var3).<is.a>a(is.a, is.a.c).<is.a>a(is.b, is.a.d).a(is.d, Boolean.valueOf(true)))
/*  318 */         .a(EnumDirection.EAST, BlockPropertyHalf.TOP, BlockPropertyStairsShape.INNER_RIGHT, ir.a().<MinecraftKey>a(is.c, var1).<is.a>a(is.a, is.a.c).<is.a>a(is.b, is.a.b).a(is.d, Boolean.valueOf(true)))
/*  319 */         .a(EnumDirection.WEST, BlockPropertyHalf.TOP, BlockPropertyStairsShape.INNER_RIGHT, ir.a().<MinecraftKey>a(is.c, var1).<is.a>a(is.a, is.a.c).<is.a>a(is.b, is.a.d).a(is.d, Boolean.valueOf(true)))
/*  320 */         .a(EnumDirection.SOUTH, BlockPropertyHalf.TOP, BlockPropertyStairsShape.INNER_RIGHT, ir.a().<MinecraftKey>a(is.c, var1).<is.a>a(is.a, is.a.c).<is.a>a(is.b, is.a.c).a(is.d, Boolean.valueOf(true)))
/*  321 */         .a(EnumDirection.NORTH, BlockPropertyHalf.TOP, BlockPropertyStairsShape.INNER_RIGHT, ir.a().<MinecraftKey>a(is.c, var1).<is.a>a(is.a, is.a.c).a(is.d, Boolean.valueOf(true)))
/*  322 */         .a(EnumDirection.EAST, BlockPropertyHalf.TOP, BlockPropertyStairsShape.INNER_LEFT, ir.a().<MinecraftKey>a(is.c, var1).<is.a>a(is.a, is.a.c).a(is.d, Boolean.valueOf(true)))
/*  323 */         .a(EnumDirection.WEST, BlockPropertyHalf.TOP, BlockPropertyStairsShape.INNER_LEFT, ir.a().<MinecraftKey>a(is.c, var1).<is.a>a(is.a, is.a.c).<is.a>a(is.b, is.a.c).a(is.d, Boolean.valueOf(true)))
/*  324 */         .a(EnumDirection.SOUTH, BlockPropertyHalf.TOP, BlockPropertyStairsShape.INNER_LEFT, ir.a().<MinecraftKey>a(is.c, var1).<is.a>a(is.a, is.a.c).<is.a>a(is.b, is.a.b).a(is.d, Boolean.valueOf(true)))
/*  325 */         .a(EnumDirection.NORTH, BlockPropertyHalf.TOP, BlockPropertyStairsShape.INNER_LEFT, ir.a().<MinecraftKey>a(is.c, var1).<is.a>a(is.a, is.a.c).<is.a>a(is.b, is.a.d).a(is.d, Boolean.valueOf(true))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static il f(Block var0, MinecraftKey var1, MinecraftKey var2, MinecraftKey var3) {
/*  331 */     return io.a(var0)
/*  332 */       .a(
/*  333 */         ip.<EnumDirection, BlockPropertyHalf, Boolean>a(BlockProperties.O, BlockProperties.ab, BlockProperties.u)
/*  334 */         .a(EnumDirection.NORTH, BlockPropertyHalf.BOTTOM, Boolean.valueOf(false), ir.a().a(is.c, var2))
/*  335 */         .a(EnumDirection.SOUTH, BlockPropertyHalf.BOTTOM, Boolean.valueOf(false), ir.a().<MinecraftKey>a(is.c, var2).a(is.b, is.a.c))
/*  336 */         .a(EnumDirection.EAST, BlockPropertyHalf.BOTTOM, Boolean.valueOf(false), ir.a().<MinecraftKey>a(is.c, var2).a(is.b, is.a.b))
/*  337 */         .a(EnumDirection.WEST, BlockPropertyHalf.BOTTOM, Boolean.valueOf(false), ir.a().<MinecraftKey>a(is.c, var2).a(is.b, is.a.d))
/*  338 */         .a(EnumDirection.NORTH, BlockPropertyHalf.TOP, Boolean.valueOf(false), ir.a().a(is.c, var1))
/*  339 */         .a(EnumDirection.SOUTH, BlockPropertyHalf.TOP, Boolean.valueOf(false), ir.a().<MinecraftKey>a(is.c, var1).a(is.b, is.a.c))
/*  340 */         .a(EnumDirection.EAST, BlockPropertyHalf.TOP, Boolean.valueOf(false), ir.a().<MinecraftKey>a(is.c, var1).a(is.b, is.a.b))
/*  341 */         .a(EnumDirection.WEST, BlockPropertyHalf.TOP, Boolean.valueOf(false), ir.a().<MinecraftKey>a(is.c, var1).a(is.b, is.a.d))
/*  342 */         .a(EnumDirection.NORTH, BlockPropertyHalf.BOTTOM, Boolean.valueOf(true), ir.a().a(is.c, var3))
/*  343 */         .a(EnumDirection.SOUTH, BlockPropertyHalf.BOTTOM, Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, var3).a(is.b, is.a.c))
/*  344 */         .a(EnumDirection.EAST, BlockPropertyHalf.BOTTOM, Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, var3).a(is.b, is.a.b))
/*  345 */         .a(EnumDirection.WEST, BlockPropertyHalf.BOTTOM, Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, var3).a(is.b, is.a.d))
/*  346 */         .a(EnumDirection.NORTH, BlockPropertyHalf.TOP, Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, var3).<is.a>a(is.a, is.a.c).a(is.b, is.a.c))
/*  347 */         .a(EnumDirection.SOUTH, BlockPropertyHalf.TOP, Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, var3).<is.a>a(is.a, is.a.c).a(is.b, is.a.a))
/*  348 */         .a(EnumDirection.EAST, BlockPropertyHalf.TOP, Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, var3).<is.a>a(is.a, is.a.c).a(is.b, is.a.d))
/*  349 */         .a(EnumDirection.WEST, BlockPropertyHalf.TOP, Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, var3).<is.a>a(is.a, is.a.c).a(is.b, is.a.b)));
/*      */   }
/*      */ 
/*      */   
/*      */   private static il g(Block var0, MinecraftKey var1, MinecraftKey var2, MinecraftKey var3) {
/*  354 */     return io.a(var0)
/*  355 */       .a(
/*  356 */         ip.<EnumDirection, BlockPropertyHalf, Boolean>a(BlockProperties.O, BlockProperties.ab, BlockProperties.u)
/*  357 */         .a(EnumDirection.NORTH, BlockPropertyHalf.BOTTOM, Boolean.valueOf(false), ir.a().a(is.c, var2))
/*  358 */         .a(EnumDirection.SOUTH, BlockPropertyHalf.BOTTOM, Boolean.valueOf(false), ir.a().a(is.c, var2))
/*  359 */         .a(EnumDirection.EAST, BlockPropertyHalf.BOTTOM, Boolean.valueOf(false), ir.a().a(is.c, var2))
/*  360 */         .a(EnumDirection.WEST, BlockPropertyHalf.BOTTOM, Boolean.valueOf(false), ir.a().a(is.c, var2))
/*  361 */         .a(EnumDirection.NORTH, BlockPropertyHalf.TOP, Boolean.valueOf(false), ir.a().a(is.c, var1))
/*  362 */         .a(EnumDirection.SOUTH, BlockPropertyHalf.TOP, Boolean.valueOf(false), ir.a().a(is.c, var1))
/*  363 */         .a(EnumDirection.EAST, BlockPropertyHalf.TOP, Boolean.valueOf(false), ir.a().a(is.c, var1))
/*  364 */         .a(EnumDirection.WEST, BlockPropertyHalf.TOP, Boolean.valueOf(false), ir.a().a(is.c, var1))
/*  365 */         .a(EnumDirection.NORTH, BlockPropertyHalf.BOTTOM, Boolean.valueOf(true), ir.a().a(is.c, var3))
/*  366 */         .a(EnumDirection.SOUTH, BlockPropertyHalf.BOTTOM, Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, var3).a(is.b, is.a.c))
/*  367 */         .a(EnumDirection.EAST, BlockPropertyHalf.BOTTOM, Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, var3).a(is.b, is.a.b))
/*  368 */         .a(EnumDirection.WEST, BlockPropertyHalf.BOTTOM, Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, var3).a(is.b, is.a.d))
/*  369 */         .a(EnumDirection.NORTH, BlockPropertyHalf.TOP, Boolean.valueOf(true), ir.a().a(is.c, var3))
/*  370 */         .a(EnumDirection.SOUTH, BlockPropertyHalf.TOP, Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, var3).a(is.b, is.a.c))
/*  371 */         .a(EnumDirection.EAST, BlockPropertyHalf.TOP, Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, var3).a(is.b, is.a.b))
/*  372 */         .a(EnumDirection.WEST, BlockPropertyHalf.TOP, Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, var3).a(is.b, is.a.d)));
/*      */   }
/*      */ 
/*      */   
/*      */   private static io e(Block var0, MinecraftKey var1) {
/*  377 */     return io.a(var0, ir.a().a(is.c, var1));
/*      */   }
/*      */   
/*      */   private static ip f() {
/*  381 */     return ip.<EnumDirection.EnumAxis>a(BlockProperties.F)
/*  382 */       .a(EnumDirection.EnumAxis.Y, ir.a())
/*  383 */       .a(EnumDirection.EnumAxis.Z, ir.a().a(is.a, is.a.b))
/*  384 */       .a(EnumDirection.EnumAxis.X, ir.a().<is.a>a(is.a, is.a.b).a(is.b, is.a.b));
/*      */   }
/*      */   
/*      */   private static il f(Block var0, MinecraftKey var1) {
/*  388 */     return io.a(var0, ir.a().a(is.c, var1)).a(f());
/*      */   }
/*      */   
/*      */   private void g(Block var0, MinecraftKey var1) {
/*  392 */     this.a.accept(f(var0, var1));
/*      */   }
/*      */   
/*      */   private void a(Block var0, jb.a var1) {
/*  396 */     MinecraftKey var2 = var1.a(var0, this.b);
/*  397 */     this.a.accept(f(var0, var2));
/*      */   }
/*      */   
/*      */   private void b(Block var0, jb.a var1) {
/*  401 */     MinecraftKey var2 = var1.a(var0, this.b);
/*  402 */     this.a.accept(io.a(var0, ir.a().a(is.c, var2)).a(b()));
/*      */   }
/*      */   
/*      */   private static il h(Block var0, MinecraftKey var1, MinecraftKey var2) {
/*  406 */     return io.a(var0)
/*  407 */       .a(
/*  408 */         ip.<EnumDirection.EnumAxis>a(BlockProperties.F)
/*  409 */         .a(EnumDirection.EnumAxis.Y, ir.a().a(is.c, var1))
/*  410 */         .a(EnumDirection.EnumAxis.Z, ir.a().<MinecraftKey>a(is.c, var2).a(is.a, is.a.b))
/*  411 */         .a(EnumDirection.EnumAxis.X, ir.a().<MinecraftKey>a(is.c, var2).<is.a>a(is.a, is.a.b).a(is.b, is.a.b)));
/*      */   }
/*      */ 
/*      */   
/*      */   private void a(Block var0, jb.a var1, jb.a var2) {
/*  416 */     MinecraftKey var3 = var1.a(var0, this.b);
/*  417 */     MinecraftKey var4 = var2.a(var0, this.b);
/*  418 */     this.a.accept(h(var0, var3, var4));
/*      */   }
/*      */   
/*      */   private MinecraftKey a(Block var0, String var1, ix var2, Function<MinecraftKey, iz> var3) {
/*  422 */     return var2.a(var0, var1, var3.apply(iz.a(var0, var1)), this.b);
/*      */   }
/*      */   
/*      */   private static il i(Block var0, MinecraftKey var1, MinecraftKey var2) {
/*  426 */     return io.a(var0)
/*  427 */       .a(a(BlockProperties.w, var2, var1));
/*      */   }
/*      */   
/*      */   private static il h(Block var0, MinecraftKey var1, MinecraftKey var2, MinecraftKey var3) {
/*  431 */     return io.a(var0)
/*  432 */       .a(
/*  433 */         ip.<BlockPropertySlabType>a(BlockProperties.aK)
/*  434 */         .a(BlockPropertySlabType.BOTTOM, ir.a().a(is.c, var1))
/*  435 */         .a(BlockPropertySlabType.TOP, ir.a().a(is.c, var2))
/*  436 */         .a(BlockPropertySlabType.DOUBLE, ir.a().a(is.c, var3)));
/*      */   }
/*      */ 
/*      */   
/*      */   private void e(Block var0) {
/*  441 */     c(var0, jb.a);
/*      */   }
/*      */   
/*      */   private void c(Block var0, jb.a var1) {
/*  445 */     this.a.accept(e(var0, var1.a(var0, this.b)));
/*      */   }
/*      */   
/*      */   private void a(Block var0, iz var1, ix var2) {
/*  449 */     MinecraftKey var3 = var2.a(var0, var1, this.b);
/*  450 */     this.a.accept(e(var0, var3));
/*      */   }
/*      */   
/*      */   class b
/*      */   {
/*      */     private final iz b;
/*      */     @Nullable
/*      */     private MinecraftKey c;
/*      */     
/*      */     public b(ii this$0, iz var1) {
/*  460 */       this.b = var1;
/*      */     }
/*      */     
/*      */     public b a(Block var0, ix var1) {
/*  464 */       this.c = var1.a(var0, this.b, ii.a(this.a));
/*  465 */       ii.b(this.a).accept(ii.a(var0, this.c));
/*  466 */       return this;
/*      */     }
/*      */     
/*      */     public b a(Function<iz, MinecraftKey> var0) {
/*  470 */       this.c = var0.apply(this.b);
/*  471 */       return this;
/*      */     }
/*      */     
/*      */     public b a(Block var0) {
/*  475 */       MinecraftKey var1 = iy.l.a(var0, this.b, ii.a(this.a));
/*  476 */       MinecraftKey var2 = iy.m.a(var0, this.b, ii.a(this.a));
/*  477 */       ii.b(this.a).accept(ii.a(var0, var1, var2));
/*      */       
/*  479 */       MinecraftKey var3 = iy.n.a(var0, this.b, ii.a(this.a));
/*  480 */       ii.a(this.a, var0, var3);
/*  481 */       return this;
/*      */     }
/*      */     
/*      */     public b b(Block var0) {
/*  485 */       MinecraftKey var1 = iy.v.a(var0, this.b, ii.a(this.a));
/*  486 */       MinecraftKey var2 = iy.w.a(var0, this.b, ii.a(this.a));
/*  487 */       MinecraftKey var3 = iy.x.a(var0, this.b, ii.a(this.a));
/*  488 */       ii.b(this.a).accept(ii.a(var0, var1, var2, var3));
/*      */       
/*  490 */       MinecraftKey var4 = iy.y.a(var0, this.b, ii.a(this.a));
/*  491 */       ii.a(this.a, var0, var4);
/*  492 */       return this;
/*      */     }
/*      */     
/*      */     public b c(Block var0) {
/*  496 */       MinecraftKey var1 = iy.s.a(var0, this.b, ii.a(this.a));
/*  497 */       MinecraftKey var2 = iy.t.a(var0, this.b, ii.a(this.a));
/*  498 */       ii.b(this.a).accept(ii.b(var0, var1, var2));
/*      */       
/*  500 */       MinecraftKey var3 = iy.u.a(var0, this.b, ii.a(this.a));
/*  501 */       ii.a(this.a, var0, var3);
/*  502 */       return this;
/*      */     }
/*      */     
/*      */     public b d(Block var0) {
/*  506 */       MinecraftKey var1 = iy.A.a(var0, this.b, ii.a(this.a));
/*  507 */       MinecraftKey var2 = iy.z.a(var0, this.b, ii.a(this.a));
/*  508 */       MinecraftKey var3 = iy.C.a(var0, this.b, ii.a(this.a));
/*  509 */       MinecraftKey var4 = iy.B.a(var0, this.b, ii.a(this.a));
/*  510 */       ii.b(this.a).accept(ii.a(var0, var1, var2, var3, var4));
/*  511 */       return this;
/*      */     }
/*      */     
/*      */     public b e(Block var0) {
/*  515 */       MinecraftKey var1 = iy.D.a(var0, this.b, ii.a(this.a));
/*  516 */       MinecraftKey var2 = iy.E.a(var0, this.b, ii.a(this.a));
/*  517 */       ii.b(this.a).accept(ii.c(var0, var1, var2));
/*  518 */       return this;
/*      */     }
/*      */     
/*      */     public b a(Block var0, Block var1) {
/*  522 */       MinecraftKey var2 = iy.F.a(var0, this.b, ii.a(this.a));
/*  523 */       ii.b(this.a).accept(ii.a(var0, var2));
/*  524 */       ii.b(this.a).accept(ii.a(var1, var2));
/*  525 */       ii.a(this.a, var0.getItem());
/*  526 */       ii.a(this.a, var1);
/*  527 */       return this;
/*      */     }
/*      */     
/*      */     public b f(Block var0) {
/*  531 */       if (this.c == null) {
/*  532 */         throw new IllegalStateException("Full block not generated yet");
/*      */       }
/*  534 */       MinecraftKey var1 = iy.G.a(var0, this.b, ii.a(this.a));
/*  535 */       MinecraftKey var2 = iy.H.a(var0, this.b, ii.a(this.a));
/*  536 */       ii.b(this.a).accept(ii.b(var0, var1, var2, this.c));
/*  537 */       return this;
/*      */     }
/*      */     
/*      */     public b g(Block var0) {
/*  541 */       MinecraftKey var1 = iy.K.a(var0, this.b, ii.a(this.a));
/*  542 */       MinecraftKey var2 = iy.J.a(var0, this.b, ii.a(this.a));
/*  543 */       MinecraftKey var3 = iy.L.a(var0, this.b, ii.a(this.a));
/*      */       
/*  545 */       ii.b(this.a).accept(ii.c(var0, var1, var2, var3));
/*  546 */       return this;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private b a(Block var0, jb var1) {
/*  552 */     return (new b(this, var1.b())).a(var0, var1.a());
/*      */   }
/*      */   
/*      */   private b d(Block var0, jb.a var1) {
/*  556 */     jb var2 = var1.get(var0);
/*  557 */     return (new b(this, var2.b())).a(var0, var2.a());
/*      */   }
/*      */   
/*      */   private b f(Block var0) {
/*  561 */     return d(var0, jb.a);
/*      */   }
/*      */   
/*      */   private b a(iz var0) {
/*  565 */     return new b(this, var0);
/*      */   }
/*      */   
/*      */   private void g(Block var0) {
/*  569 */     iz var1 = iz.p(var0);
/*  570 */     MinecraftKey var2 = iy.o.a(var0, var1, this.b);
/*  571 */     MinecraftKey var3 = iy.p.a(var0, var1, this.b);
/*  572 */     MinecraftKey var4 = iy.q.a(var0, var1, this.b);
/*  573 */     MinecraftKey var5 = iy.r.a(var0, var1, this.b);
/*      */     
/*  575 */     a(var0.getItem());
/*  576 */     this.a.accept(b(var0, var2, var3, var4, var5));
/*      */   }
/*      */   
/*      */   private void h(Block var0) {
/*  580 */     iz var1 = iz.b(var0);
/*  581 */     MinecraftKey var2 = iy.P.a(var0, var1, this.b);
/*  582 */     MinecraftKey var3 = iy.Q.a(var0, var1, this.b);
/*  583 */     MinecraftKey var4 = iy.R.a(var0, var1, this.b);
/*      */     
/*  585 */     this.a.accept(f(var0, var2, var3, var4));
/*  586 */     c(var0, var3);
/*      */   }
/*      */   
/*      */   private void i(Block var0) {
/*  590 */     iz var1 = iz.b(var0);
/*  591 */     MinecraftKey var2 = iy.M.a(var0, var1, this.b);
/*  592 */     MinecraftKey var3 = iy.N.a(var0, var1, this.b);
/*  593 */     MinecraftKey var4 = iy.O.a(var0, var1, this.b);
/*      */     
/*  595 */     this.a.accept(g(var0, var2, var3, var4));
/*  596 */     c(var0, var3);
/*      */   }
/*      */   
/*      */   class d
/*      */   {
/*      */     private final iz b;
/*      */     
/*      */     public d(ii this$0, iz var1) {
/*  604 */       this.b = var1;
/*      */     }
/*      */     
/*      */     public d a(Block var0) {
/*  608 */       iz var1 = this.b.c(ja.d, this.b.a(ja.i));
/*  609 */       MinecraftKey var2 = iy.e.a(var0, var1, ii.a(this.a));
/*  610 */       ii.b(this.a).accept(ii.b(var0, var2));
/*  611 */       return this;
/*      */     }
/*      */     
/*      */     public d b(Block var0) {
/*  615 */       MinecraftKey var1 = iy.e.a(var0, this.b, ii.a(this.a));
/*  616 */       ii.b(this.a).accept(ii.b(var0, var1));
/*  617 */       return this;
/*      */     }
/*      */     
/*      */     public d c(Block var0) {
/*  621 */       MinecraftKey var1 = iy.e.a(var0, this.b, ii.a(this.a));
/*  622 */       MinecraftKey var2 = iy.f.a(var0, this.b, ii.a(this.a));
/*  623 */       ii.b(this.a).accept(ii.d(var0, var1, var2));
/*  624 */       return this;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private d j(Block var0) {
/*  630 */     return new d(this, iz.l(var0));
/*      */   }
/*      */   
/*      */   private void k(Block var0) {
/*  634 */     a(var0, var0);
/*      */   }
/*      */   
/*      */   private void a(Block var0, Block var1) {
/*  638 */     this.a.accept(e(var0, iw.a(var1)));
/*      */   }
/*      */   
/*      */   enum c {
/*  642 */     a, b;
/*      */     
/*      */     public ix a() {
/*  645 */       return (this == a) ? iy.T : iy.S;
/*      */     }
/*      */     
/*      */     public ix b() {
/*  649 */       return (this == a) ? iy.V : iy.U;
/*      */     }
/*      */   }
/*      */   
/*      */   private void a(Block var0, c var1) {
/*  654 */     b(var0);
/*  655 */     b(var0, var1);
/*      */   }
/*      */   
/*      */   private void a(Block var0, c var1, iz var2) {
/*  659 */     b(var0);
/*  660 */     b(var0, var1, var2);
/*      */   }
/*      */   
/*      */   private void b(Block var0, c var1) {
/*  664 */     iz var2 = iz.c(var0);
/*  665 */     b(var0, var1, var2);
/*      */   }
/*      */   
/*      */   private void b(Block var0, c var1, iz var2) {
/*  669 */     MinecraftKey var3 = var1.a().a(var0, var2, this.b);
/*  670 */     this.a.accept(e(var0, var3));
/*      */   }
/*      */   
/*      */   private void a(Block var0, Block var1, c var2) {
/*  674 */     a(var0, var2);
/*      */     
/*  676 */     iz var3 = iz.d(var0);
/*  677 */     MinecraftKey var4 = var2.b().a(var1, var3, this.b);
/*  678 */     this.a.accept(e(var1, var4));
/*      */   }
/*      */   
/*      */   private void b(Block var0, Block var1) {
/*  682 */     jb var2 = jb.k.get(var0);
/*      */     
/*  684 */     MinecraftKey var3 = var2.a(var0, this.b);
/*  685 */     this.a.accept(e(var0, var3));
/*      */     
/*  687 */     MinecraftKey var4 = iy.ac.a(var1, var2.b(), this.b);
/*  688 */     this.a.accept(io.a(var1, ir.a().a(is.c, var4)).a(b()));
/*      */     
/*  690 */     b(var0);
/*      */   }
/*      */   
/*      */   private void c(Block var0, Block var1) {
/*  694 */     a(var0.getItem());
/*  695 */     iz var2 = iz.g(var0);
/*  696 */     iz var3 = iz.a(var0, var1);
/*      */     
/*  698 */     MinecraftKey var4 = iy.ao.a(var1, var3, this.b);
/*  699 */     this.a.accept(
/*  700 */         io.a(var1, ir.a()
/*  701 */           .a(is.c, var4))
/*  702 */         .a(ip.<EnumDirection>a(BlockProperties.O)
/*  703 */           .a(EnumDirection.WEST, ir.a())
/*  704 */           .a(EnumDirection.SOUTH, ir.a().a(is.b, is.a.d))
/*  705 */           .a(EnumDirection.NORTH, ir.a().a(is.b, is.a.b))
/*  706 */           .a(EnumDirection.EAST, ir.a().a(is.b, is.a.c))));
/*      */ 
/*      */     
/*  709 */     this.a.accept(
/*  710 */         io.a(var0)
/*  711 */         .a(
/*  712 */           ip.<Comparable>a(BlockProperties.ai).a(var2 -> ir.a().a(is.c, iy.an[var2.intValue()].a(var0, var1, this.b)))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void a(Block var0, Block var1, Block var2, Block var3, Block var4, Block var5, Block var6, Block var7) {
/*  718 */     a(var0, c.b);
/*  719 */     a(var1, c.b);
/*      */     
/*  721 */     e(var2);
/*  722 */     e(var3);
/*      */     
/*  724 */     b(var4, var6);
/*  725 */     b(var5, var7);
/*      */   }
/*      */   
/*      */   private void c(Block var0, c var1) {
/*  729 */     a(var0, "_top");
/*  730 */     MinecraftKey var2 = a(var0, "_top", var1.a(), iz::c);
/*  731 */     MinecraftKey var3 = a(var0, "_bottom", var1.a(), iz::c);
/*  732 */     j(var0, var2, var3);
/*      */   }
/*      */   
/*      */   private void g() {
/*  736 */     a(Blocks.SUNFLOWER, "_front");
/*  737 */     MinecraftKey var0 = iw.a(Blocks.SUNFLOWER, "_top");
/*  738 */     MinecraftKey var1 = a(Blocks.SUNFLOWER, "_bottom", c.b.a(), iz::c);
/*  739 */     j(Blocks.SUNFLOWER, var0, var1);
/*      */   }
/*      */   
/*      */   private void h() {
/*  743 */     MinecraftKey var0 = a(Blocks.TALL_SEAGRASS, "_top", iy.aE, iz::a);
/*  744 */     MinecraftKey var1 = a(Blocks.TALL_SEAGRASS, "_bottom", iy.aE, iz::a);
/*  745 */     j(Blocks.TALL_SEAGRASS, var0, var1);
/*      */   }
/*      */   
/*      */   private void j(Block var0, MinecraftKey var1, MinecraftKey var2) {
/*  749 */     this.a.accept(io.a(var0)
/*  750 */         .a(
/*  751 */           ip.<BlockPropertyDoubleBlockHalf>a(BlockProperties.aa)
/*  752 */           .a(BlockPropertyDoubleBlockHalf.LOWER, ir.a().a(is.c, var2))
/*  753 */           .a(BlockPropertyDoubleBlockHalf.UPPER, ir.a().a(is.c, var1))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void l(Block var0) {
/*  759 */     iz var1 = iz.e(var0);
/*  760 */     iz var2 = iz.e(iz.a(var0, "_corner"));
/*      */     
/*  762 */     MinecraftKey var3 = iy.W.a(var0, var1, this.b);
/*  763 */     MinecraftKey var4 = iy.X.a(var0, var2, this.b);
/*  764 */     MinecraftKey var5 = iy.Y.a(var0, var1, this.b);
/*  765 */     MinecraftKey var6 = iy.Z.a(var0, var1, this.b);
/*      */     
/*  767 */     b(var0);
/*  768 */     this.a.accept(io.a(var0)
/*  769 */         .a(
/*  770 */           ip.<BlockPropertyTrackPosition>a(BlockProperties.ac)
/*  771 */           .a(BlockPropertyTrackPosition.NORTH_SOUTH, ir.a().a(is.c, var3))
/*  772 */           .a(BlockPropertyTrackPosition.EAST_WEST, ir.a().<MinecraftKey>a(is.c, var3).a(is.b, is.a.b))
/*      */           
/*  774 */           .a(BlockPropertyTrackPosition.ASCENDING_EAST, ir.a().<MinecraftKey>a(is.c, var5).a(is.b, is.a.b))
/*  775 */           .a(BlockPropertyTrackPosition.ASCENDING_WEST, ir.a().<MinecraftKey>a(is.c, var6).a(is.b, is.a.b))
/*  776 */           .a(BlockPropertyTrackPosition.ASCENDING_NORTH, ir.a().a(is.c, var5))
/*  777 */           .a(BlockPropertyTrackPosition.ASCENDING_SOUTH, ir.a().a(is.c, var6))
/*      */           
/*  779 */           .a(BlockPropertyTrackPosition.SOUTH_EAST, ir.a().a(is.c, var4))
/*  780 */           .a(BlockPropertyTrackPosition.SOUTH_WEST, ir.a().<MinecraftKey>a(is.c, var4).a(is.b, is.a.b))
/*  781 */           .a(BlockPropertyTrackPosition.NORTH_WEST, ir.a().<MinecraftKey>a(is.c, var4).a(is.b, is.a.c))
/*  782 */           .a(BlockPropertyTrackPosition.NORTH_EAST, ir.a().<MinecraftKey>a(is.c, var4).a(is.b, is.a.d))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void m(Block var0) {
/*  788 */     MinecraftKey var1 = a(var0, "", iy.W, iz::e);
/*  789 */     MinecraftKey var2 = a(var0, "", iy.Y, iz::e);
/*  790 */     MinecraftKey var3 = a(var0, "", iy.Z, iz::e);
/*      */     
/*  792 */     MinecraftKey var4 = a(var0, "_on", iy.W, iz::e);
/*  793 */     MinecraftKey var5 = a(var0, "_on", iy.Y, iz::e);
/*  794 */     MinecraftKey var6 = a(var0, "_on", iy.Z, iz::e);
/*      */ 
/*      */ 
/*      */     
/*  798 */     ip var7 = ip.<Comparable, BlockPropertyTrackPosition>a(BlockProperties.w, BlockProperties.ad).a((var6, var7) -> {
/*      */           switch (null.b[var7.ordinal()]) {
/*      */             case 1:
/*      */               return ir.a().a(is.c, var6.booleanValue() ? var0 : var1);
/*      */             
/*      */             case 2:
/*      */               return ir.a().<MinecraftKey>a(is.c, var6.booleanValue() ? var0 : var1).a(is.b, is.a.b);
/*      */             
/*      */             case 3:
/*      */               return ir.a().<MinecraftKey>a(is.c, var6.booleanValue() ? var2 : var3).a(is.b, is.a.b);
/*      */             case 4:
/*      */               return ir.a().<MinecraftKey>a(is.c, var6.booleanValue() ? var4 : var5).a(is.b, is.a.b);
/*      */             case 5:
/*      */               return ir.a().a(is.c, var6.booleanValue() ? var2 : var3);
/*      */             case 6:
/*      */               return ir.a().a(is.c, var6.booleanValue() ? var4 : var5);
/*      */           } 
/*      */           throw new UnsupportedOperationException("Fix you generator!");
/*      */         });
/*  817 */     b(var0);
/*  818 */     this.a.accept(io.a(var0).a(var7));
/*      */   }
/*      */   
/*      */   class a {
/*      */     private final MinecraftKey b;
/*      */     
/*      */     public a(ii this$0, MinecraftKey var1, Block var2) {
/*  825 */       this.b = iy.F.a(var1, iz.q(var2), ii.a(this$0));
/*      */     }
/*      */     
/*      */     public a a(Block... var0) {
/*  829 */       for (Block var4 : var0) {
/*  830 */         ii.b(this.a).accept(ii.a(var4, this.b));
/*      */       }
/*  832 */       return this;
/*      */     }
/*      */     
/*      */     public a b(Block... var0) {
/*  836 */       for (Block var4 : var0) {
/*  837 */         ii.a(this.a, var4);
/*      */       }
/*  839 */       return a(var0);
/*      */     }
/*      */     
/*      */     public a a(ix var0, Block... var1) {
/*  843 */       for (Block var5 : var1) {
/*  844 */         var0.a(iw.a(var5.getItem()), iz.q(var5), ii.a(this.a));
/*      */       }
/*  846 */       return a(var1);
/*      */     }
/*      */   }
/*      */   
/*      */   private a a(MinecraftKey var0, Block var1) {
/*  851 */     return new a(this, var0, var1);
/*      */   }
/*      */   
/*      */   private a d(Block var0, Block var1) {
/*  855 */     return new a(this, iw.a(var0), var1);
/*      */   }
/*      */   
/*      */   private void a(Block var0, Item var1) {
/*  859 */     MinecraftKey var2 = iy.F.a(var0, iz.a(var1), this.b);
/*  860 */     this.a.accept(e(var0, var2));
/*      */   }
/*      */   
/*      */   private void h(Block var0, MinecraftKey var1) {
/*  864 */     MinecraftKey var2 = iy.F.a(var0, iz.h(var1), this.b);
/*  865 */     this.a.accept(e(var0, var2));
/*      */   }
/*      */   
/*      */   private void e(Block var0, Block var1) {
/*  869 */     c(var0, jb.a);
/*      */ 
/*      */     
/*  872 */     MinecraftKey var2 = jb.i.get(var0).a(var1, this.b);
/*  873 */     this.a.accept(e(var1, var2));
/*      */   }
/*      */   
/*      */   private void a(jb.a var0, Block... var1) {
/*  877 */     for (Block var5 : var1) {
/*  878 */       MinecraftKey var6 = var0.a(var5, this.b);
/*  879 */       this.a.accept(d(var5, var6));
/*      */     } 
/*      */   }
/*      */   
/*      */   private void b(jb.a var0, Block... var1) {
/*  884 */     for (Block var5 : var1) {
/*  885 */       MinecraftKey var6 = var0.a(var5, this.b);
/*  886 */       this.a.accept(
/*  887 */           io.a(var5, ir.a().a(is.c, var6))
/*  888 */           .a(c()));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void f(Block var0, Block var1) {
/*  894 */     e(var0);
/*      */     
/*  896 */     iz var2 = iz.b(var0, var1);
/*  897 */     MinecraftKey var3 = iy.ai.a(var1, var2, this.b);
/*  898 */     MinecraftKey var4 = iy.aj.a(var1, var2, this.b);
/*  899 */     MinecraftKey var5 = iy.ak.a(var1, var2, this.b);
/*  900 */     MinecraftKey var6 = iy.ag.a(var1, var2, this.b);
/*  901 */     MinecraftKey var7 = iy.ah.a(var1, var2, this.b);
/*      */     
/*  903 */     Item var8 = var1.getItem();
/*  904 */     iy.aK.a(iw.a(var8), iz.B(var0), this.b);
/*      */     
/*  906 */     this.a.accept(
/*  907 */         in.a(var1)
/*  908 */         .a(ir.a().a(is.c, var3))
/*  909 */         .a(im.a().a(BlockProperties.I, Boolean.valueOf(true)), ir.a().a(is.c, var4))
/*  910 */         .a(im.a().a(BlockProperties.J, Boolean.valueOf(true)), ir.a().<MinecraftKey>a(is.c, var4).a(is.b, is.a.b))
/*  911 */         .a(im.a().a(BlockProperties.K, Boolean.valueOf(true)), ir.a().a(is.c, var5))
/*  912 */         .a(im.a().a(BlockProperties.L, Boolean.valueOf(true)), ir.a().<MinecraftKey>a(is.c, var5).a(is.b, is.a.b))
/*      */         
/*  914 */         .a(im.a().a(BlockProperties.I, Boolean.valueOf(false)), ir.a().a(is.c, var6))
/*  915 */         .a(im.a().a(BlockProperties.J, Boolean.valueOf(false)), ir.a().a(is.c, var7))
/*  916 */         .a(im.a().a(BlockProperties.K, Boolean.valueOf(false)), ir.a().<MinecraftKey>a(is.c, var7).a(is.b, is.a.b))
/*  917 */         .a(im.a().a(BlockProperties.L, Boolean.valueOf(false)), ir.a().<MinecraftKey>a(is.c, var6).a(is.b, is.a.d)));
/*      */   }
/*      */ 
/*      */   
/*      */   private void n(Block var0) {
/*  922 */     iz var1 = iz.v(var0);
/*      */     
/*  924 */     MinecraftKey var2 = iy.al.a(var0, var1, this.b);
/*  925 */     MinecraftKey var3 = a(var0, "_conditional", iy.al, var1 -> var0.c(ja.i, var1));
/*      */     
/*  927 */     this.a.accept(io.a(var0)
/*  928 */         .a(a(BlockProperties.c, var3, var2))
/*  929 */         .a(e()));
/*      */   }
/*      */ 
/*      */   
/*      */   private void o(Block var0) {
/*  934 */     MinecraftKey var1 = jb.m.a(var0, this.b);
/*  935 */     this.a.accept(e(var0, var1).a(c()));
/*      */   }
/*      */   
/*      */   private List<ir> a(int var0) {
/*  939 */     String var1 = "_age" + var0;
/*  940 */     return (List<ir>)IntStream.range(1, 5)
/*  941 */       .mapToObj(var1 -> ir.a().a(is.c, iw.a(Blocks.BAMBOO, var1 + var0)))
/*  942 */       .collect(Collectors.toList());
/*      */   }
/*      */   
/*      */   private void i() {
/*  946 */     a(Blocks.BAMBOO);
/*  947 */     this.a.accept(
/*  948 */         in.a(Blocks.BAMBOO)
/*  949 */         .a(im.a().a(BlockProperties.ae, Integer.valueOf(0)), a(0))
/*  950 */         .a(im.a().a(BlockProperties.ae, Integer.valueOf(1)), a(1))
/*  951 */         .a(im.a().a(BlockProperties.aN, BlockPropertyBambooSize.SMALL), 
/*  952 */           ir.a().a(is.c, iw.a(Blocks.BAMBOO, "_small_leaves")))
/*      */         
/*  954 */         .a(im.a().a(BlockProperties.aN, BlockPropertyBambooSize.LARGE), 
/*  955 */           ir.a().a(is.c, iw.a(Blocks.BAMBOO, "_large_leaves"))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private ip j() {
/*  961 */     return ip.<EnumDirection>a(BlockProperties.M)
/*  962 */       .a(EnumDirection.DOWN, ir.a().a(is.a, is.a.c))
/*  963 */       .a(EnumDirection.UP, ir.a())
/*  964 */       .a(EnumDirection.NORTH, ir.a().a(is.a, is.a.b))
/*  965 */       .a(EnumDirection.SOUTH, ir.a().<is.a>a(is.a, is.a.b).a(is.b, is.a.c))
/*  966 */       .a(EnumDirection.WEST, ir.a().<is.a>a(is.a, is.a.b).a(is.b, is.a.d))
/*  967 */       .a(EnumDirection.EAST, ir.a().<is.a>a(is.a, is.a.b).a(is.b, is.a.b));
/*      */   }
/*      */   
/*      */   private void k() {
/*  971 */     MinecraftKey var0 = iz.a(Blocks.BARREL, "_top_open");
/*      */     
/*  973 */     this.a.accept(
/*  974 */         io.a(Blocks.BARREL)
/*  975 */         .a(j())
/*  976 */         .a(ip.<Boolean>a(BlockProperties.u)
/*  977 */           .a(Boolean.valueOf(false), ir.a().a(is.c, jb.e.a(Blocks.BARREL, this.b)))
/*  978 */           .a(Boolean.valueOf(true), ir.a().a(is.c, jb.e.get(Blocks.BARREL).a(var1 -> var1.a(ja.f, var0)).a(Blocks.BARREL, "_open", this.b)))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static <T extends Comparable<T>> ip a(IBlockState<T> var0, T var1, MinecraftKey var2, MinecraftKey var3) {
/*  984 */     ir var4 = ir.a().a(is.c, var2);
/*  985 */     ir var5 = ir.a().a(is.c, var3);
/*      */     
/*  987 */     return ip.<T>a(var0).a(var3 -> {
/*      */           boolean var4 = (var3.compareTo(var0) >= 0);
/*      */           return var4 ? var1 : var2;
/*      */         });
/*      */   }
/*      */   
/*      */   private void a(Block var0, Function<Block, iz> var1) {
/*  994 */     iz var2 = ((iz)var1.apply(var0)).b(ja.i, ja.c);
/*  995 */     iz var3 = var2.c(ja.g, iz.a(var0, "_front_honey"));
/*      */     
/*  997 */     MinecraftKey var4 = iy.j.a(var0, var2, this.b);
/*  998 */     MinecraftKey var5 = iy.j.a(var0, "_honey", var3, this.b);
/*      */     
/* 1000 */     this.a.accept(
/* 1001 */         io.a(var0)
/* 1002 */         .a(b())
/* 1003 */         .a(a(BlockProperties.au, Integer.valueOf(5), var5, var4)));
/*      */   }
/*      */ 
/*      */   
/*      */   private void a(Block var0, IBlockState<Integer> var1, int... var2) {
/* 1008 */     if (var1.getValues().size() != var2.length) {
/* 1009 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/* 1012 */     Int2ObjectOpenHashMap int2ObjectOpenHashMap = new Int2ObjectOpenHashMap();
/*      */     
/* 1014 */     ip var4 = ip.<Integer>a(var1).a(var3 -> {
/*      */           int var4 = var0[var3.intValue()];
/*      */           
/*      */           MinecraftKey var5 = (MinecraftKey)var1.computeIfAbsent(var4, ());
/*      */           return ir.a().a(is.c, var5);
/*      */         });
/* 1020 */     a(var0.getItem());
/* 1021 */     this.a.accept(io.a(var0).a(var4));
/*      */   }
/*      */   
/*      */   private void l() {
/* 1025 */     MinecraftKey var0 = iw.a(Blocks.BELL, "_floor");
/* 1026 */     MinecraftKey var1 = iw.a(Blocks.BELL, "_ceiling");
/* 1027 */     MinecraftKey var2 = iw.a(Blocks.BELL, "_wall");
/* 1028 */     MinecraftKey var3 = iw.a(Blocks.BELL, "_between_walls");
/*      */     
/* 1030 */     a(Items.rj);
/* 1031 */     this.a.accept(
/* 1032 */         io.a(Blocks.BELL)
/* 1033 */         .a(
/* 1034 */           ip.<EnumDirection, BlockPropertyBellAttach>a(BlockProperties.O, BlockProperties.R)
/* 1035 */           .a(EnumDirection.NORTH, BlockPropertyBellAttach.FLOOR, ir.a().a(is.c, var0))
/* 1036 */           .a(EnumDirection.SOUTH, BlockPropertyBellAttach.FLOOR, ir.a().<MinecraftKey>a(is.c, var0).a(is.b, is.a.c))
/* 1037 */           .a(EnumDirection.EAST, BlockPropertyBellAttach.FLOOR, ir.a().<MinecraftKey>a(is.c, var0).a(is.b, is.a.b))
/* 1038 */           .a(EnumDirection.WEST, BlockPropertyBellAttach.FLOOR, ir.a().<MinecraftKey>a(is.c, var0).a(is.b, is.a.d))
/*      */           
/* 1040 */           .a(EnumDirection.NORTH, BlockPropertyBellAttach.CEILING, ir.a().a(is.c, var1))
/* 1041 */           .a(EnumDirection.SOUTH, BlockPropertyBellAttach.CEILING, ir.a().<MinecraftKey>a(is.c, var1).a(is.b, is.a.c))
/* 1042 */           .a(EnumDirection.EAST, BlockPropertyBellAttach.CEILING, ir.a().<MinecraftKey>a(is.c, var1).a(is.b, is.a.b))
/* 1043 */           .a(EnumDirection.WEST, BlockPropertyBellAttach.CEILING, ir.a().<MinecraftKey>a(is.c, var1).a(is.b, is.a.d))
/*      */           
/* 1045 */           .a(EnumDirection.NORTH, BlockPropertyBellAttach.SINGLE_WALL, ir.a().<MinecraftKey>a(is.c, var2).a(is.b, is.a.d))
/* 1046 */           .a(EnumDirection.SOUTH, BlockPropertyBellAttach.SINGLE_WALL, ir.a().<MinecraftKey>a(is.c, var2).a(is.b, is.a.b))
/* 1047 */           .a(EnumDirection.EAST, BlockPropertyBellAttach.SINGLE_WALL, ir.a().a(is.c, var2))
/* 1048 */           .a(EnumDirection.WEST, BlockPropertyBellAttach.SINGLE_WALL, ir.a().<MinecraftKey>a(is.c, var2).a(is.b, is.a.c))
/*      */           
/* 1050 */           .a(EnumDirection.SOUTH, BlockPropertyBellAttach.DOUBLE_WALL, ir.a().<MinecraftKey>a(is.c, var3).a(is.b, is.a.b))
/* 1051 */           .a(EnumDirection.NORTH, BlockPropertyBellAttach.DOUBLE_WALL, ir.a().<MinecraftKey>a(is.c, var3).a(is.b, is.a.d))
/* 1052 */           .a(EnumDirection.EAST, BlockPropertyBellAttach.DOUBLE_WALL, ir.a().a(is.c, var3))
/* 1053 */           .a(EnumDirection.WEST, BlockPropertyBellAttach.DOUBLE_WALL, ir.a().<MinecraftKey>a(is.c, var3).a(is.b, is.a.c))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void m() {
/* 1059 */     this.a.accept(
/* 1060 */         io.a(Blocks.GRINDSTONE, ir.a().a(is.c, iw.a(Blocks.GRINDSTONE)))
/* 1061 */         .a(
/* 1062 */           ip.<BlockPropertyAttachPosition, EnumDirection>a(BlockProperties.Q, BlockProperties.O)
/* 1063 */           .a(BlockPropertyAttachPosition.FLOOR, EnumDirection.NORTH, ir.a())
/* 1064 */           .a(BlockPropertyAttachPosition.FLOOR, EnumDirection.EAST, ir.a().a(is.b, is.a.b))
/* 1065 */           .a(BlockPropertyAttachPosition.FLOOR, EnumDirection.SOUTH, ir.a().a(is.b, is.a.c))
/* 1066 */           .a(BlockPropertyAttachPosition.FLOOR, EnumDirection.WEST, ir.a().a(is.b, is.a.d))
/*      */           
/* 1068 */           .a(BlockPropertyAttachPosition.WALL, EnumDirection.NORTH, ir.a().a(is.a, is.a.b))
/* 1069 */           .a(BlockPropertyAttachPosition.WALL, EnumDirection.EAST, ir.a().<is.a>a(is.a, is.a.b).a(is.b, is.a.b))
/* 1070 */           .a(BlockPropertyAttachPosition.WALL, EnumDirection.SOUTH, ir.a().<is.a>a(is.a, is.a.b).a(is.b, is.a.c))
/* 1071 */           .a(BlockPropertyAttachPosition.WALL, EnumDirection.WEST, ir.a().<is.a>a(is.a, is.a.b).a(is.b, is.a.d))
/*      */           
/* 1073 */           .a(BlockPropertyAttachPosition.CEILING, EnumDirection.SOUTH, ir.a().a(is.a, is.a.c))
/* 1074 */           .a(BlockPropertyAttachPosition.CEILING, EnumDirection.WEST, ir.a().<is.a>a(is.a, is.a.c).a(is.b, is.a.b))
/* 1075 */           .a(BlockPropertyAttachPosition.CEILING, EnumDirection.NORTH, ir.a().<is.a>a(is.a, is.a.c).a(is.b, is.a.c))
/* 1076 */           .a(BlockPropertyAttachPosition.CEILING, EnumDirection.EAST, ir.a().<is.a>a(is.a, is.a.c).a(is.b, is.a.d))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void e(Block var0, jb.a var1) {
/* 1082 */     MinecraftKey var2 = var1.a(var0, this.b);
/*      */     
/* 1084 */     MinecraftKey var3 = iz.a(var0, "_front_on");
/* 1085 */     MinecraftKey var4 = var1.get(var0).a(var1 -> var1.a(ja.g, var0)).a(var0, "_on", this.b);
/*      */     
/* 1087 */     this.a.accept(
/* 1088 */         io.a(var0)
/* 1089 */         .a(a(BlockProperties.r, var4, var2))
/* 1090 */         .a(b()));
/*      */   }
/*      */ 
/*      */   
/*      */   private void a(Block... var0) {
/* 1095 */     MinecraftKey var1 = iw.a("campfire_off");
/*      */     
/* 1097 */     for (Block var5 : var0) {
/* 1098 */       MinecraftKey var6 = iy.aw.a(var5, iz.A(var5), this.b);
/*      */       
/* 1100 */       a(var5.getItem());
/* 1101 */       this.a.accept(
/* 1102 */           io.a(var5)
/* 1103 */           .a(a(BlockProperties.r, var6, var1))
/* 1104 */           .a(c()));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void n() {
/* 1110 */     iz var0 = iz.a(iz.C(Blocks.BOOKSHELF), iz.C(Blocks.OAK_PLANKS));
/* 1111 */     MinecraftKey var1 = iy.e.a(Blocks.BOOKSHELF, var0, this.b);
/* 1112 */     this.a.accept(e(Blocks.BOOKSHELF, var1));
/*      */   }
/*      */   
/*      */   private void o() {
/* 1116 */     a(Items.REDSTONE);
/* 1117 */     this.a.accept(
/* 1118 */         in.a(Blocks.REDSTONE_WIRE)
/* 1119 */         .a(
/* 1120 */           im.b(new im[] {
/* 1121 */               im.a()
/* 1122 */               .<BlockPropertyRedstoneSide>a(BlockProperties.X, BlockPropertyRedstoneSide.NONE)
/* 1123 */               .<BlockPropertyRedstoneSide>a(BlockProperties.W, BlockPropertyRedstoneSide.NONE)
/* 1124 */               .<BlockPropertyRedstoneSide>a(BlockProperties.Y, BlockPropertyRedstoneSide.NONE)
/* 1125 */               .a(BlockProperties.Z, BlockPropertyRedstoneSide.NONE), 
/* 1126 */               im.a()
/* 1127 */               .<BlockPropertyRedstoneSide>a(BlockProperties.X, BlockPropertyRedstoneSide.SIDE, new BlockPropertyRedstoneSide[] { BlockPropertyRedstoneSide.UP
/* 1128 */                 }).a(BlockProperties.W, BlockPropertyRedstoneSide.SIDE, new BlockPropertyRedstoneSide[] { BlockPropertyRedstoneSide.UP
/* 1129 */                 }), im.a()
/* 1130 */               .<BlockPropertyRedstoneSide>a(BlockProperties.W, BlockPropertyRedstoneSide.SIDE, new BlockPropertyRedstoneSide[] { BlockPropertyRedstoneSide.UP
/* 1131 */                 }).a(BlockProperties.Y, BlockPropertyRedstoneSide.SIDE, new BlockPropertyRedstoneSide[] { BlockPropertyRedstoneSide.UP
/* 1132 */                 }), im.a()
/* 1133 */               .<BlockPropertyRedstoneSide>a(BlockProperties.Y, BlockPropertyRedstoneSide.SIDE, new BlockPropertyRedstoneSide[] { BlockPropertyRedstoneSide.UP
/* 1134 */                 }).a(BlockProperties.Z, BlockPropertyRedstoneSide.SIDE, new BlockPropertyRedstoneSide[] { BlockPropertyRedstoneSide.UP
/* 1135 */                 }), im.a()
/* 1136 */               .<BlockPropertyRedstoneSide>a(BlockProperties.Z, BlockPropertyRedstoneSide.SIDE, new BlockPropertyRedstoneSide[] { BlockPropertyRedstoneSide.UP
/* 1137 */                 }).a(BlockProperties.X, BlockPropertyRedstoneSide.SIDE, new BlockPropertyRedstoneSide[] { BlockPropertyRedstoneSide.UP
/*      */                 })
/* 1139 */             }), ir.a().a(is.c, iw.a("redstone_dust_dot")))
/*      */         
/* 1141 */         .a(
/* 1142 */           im.a()
/* 1143 */           .a(BlockProperties.X, BlockPropertyRedstoneSide.SIDE, new BlockPropertyRedstoneSide[] { BlockPropertyRedstoneSide.UP
/* 1144 */             }), ir.a().a(is.c, iw.a("redstone_dust_side0")))
/*      */         
/* 1146 */         .a(
/* 1147 */           im.a()
/* 1148 */           .a(BlockProperties.Y, BlockPropertyRedstoneSide.SIDE, new BlockPropertyRedstoneSide[] { BlockPropertyRedstoneSide.UP
/* 1149 */             }), ir.a().a(is.c, iw.a("redstone_dust_side_alt0")))
/*      */         
/* 1151 */         .a(
/* 1152 */           im.a()
/* 1153 */           .a(BlockProperties.W, BlockPropertyRedstoneSide.SIDE, new BlockPropertyRedstoneSide[] { BlockPropertyRedstoneSide.UP
/* 1154 */             }), ir.a().<MinecraftKey>a(is.c, iw.a("redstone_dust_side_alt1")).a(is.b, is.a.d))
/*      */         
/* 1156 */         .a(
/* 1157 */           im.a()
/* 1158 */           .a(BlockProperties.Z, BlockPropertyRedstoneSide.SIDE, new BlockPropertyRedstoneSide[] { BlockPropertyRedstoneSide.UP
/* 1159 */             }), ir.a().<MinecraftKey>a(is.c, iw.a("redstone_dust_side1")).a(is.b, is.a.d))
/*      */         
/* 1161 */         .a(
/* 1162 */           im.a().a(BlockProperties.X, BlockPropertyRedstoneSide.UP), 
/* 1163 */           ir.a().a(is.c, iw.a("redstone_dust_up")))
/*      */         
/* 1165 */         .a(
/* 1166 */           im.a().a(BlockProperties.W, BlockPropertyRedstoneSide.UP), 
/* 1167 */           ir.a().<MinecraftKey>a(is.c, iw.a("redstone_dust_up")).a(is.b, is.a.b))
/*      */         
/* 1169 */         .a(
/* 1170 */           im.a().a(BlockProperties.Y, BlockPropertyRedstoneSide.UP), 
/* 1171 */           ir.a().<MinecraftKey>a(is.c, iw.a("redstone_dust_up")).a(is.b, is.a.c))
/*      */         
/* 1173 */         .a(
/* 1174 */           im.a().a(BlockProperties.Z, BlockPropertyRedstoneSide.UP), 
/* 1175 */           ir.a().<MinecraftKey>a(is.c, iw.a("redstone_dust_up")).a(is.b, is.a.d)));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void p() {
/* 1181 */     a(Items.jV);
/* 1182 */     this.a.accept(
/* 1183 */         io.a(Blocks.COMPARATOR)
/* 1184 */         .a(c())
/* 1185 */         .a(
/* 1186 */           ip.<BlockPropertyComparatorMode, Boolean>a(BlockProperties.aG, BlockProperties.w)
/* 1187 */           .a(BlockPropertyComparatorMode.COMPARE, Boolean.valueOf(false), ir.a().a(is.c, iw.a(Blocks.COMPARATOR)))
/* 1188 */           .a(BlockPropertyComparatorMode.COMPARE, Boolean.valueOf(true), ir.a().a(is.c, iw.a(Blocks.COMPARATOR, "_on")))
/* 1189 */           .a(BlockPropertyComparatorMode.SUBTRACT, Boolean.valueOf(false), ir.a().a(is.c, iw.a(Blocks.COMPARATOR, "_subtract")))
/* 1190 */           .a(BlockPropertyComparatorMode.SUBTRACT, Boolean.valueOf(true), ir.a().a(is.c, iw.a(Blocks.COMPARATOR, "_on_subtract")))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void q() {
/* 1196 */     iz var0 = iz.a(Blocks.SMOOTH_STONE);
/* 1197 */     iz var1 = iz.a(
/* 1198 */         iz.a(Blocks.SMOOTH_STONE_SLAB, "_side"), var0
/* 1199 */         .a(ja.f));
/*      */     
/* 1201 */     MinecraftKey var2 = iy.G.a(Blocks.SMOOTH_STONE_SLAB, var1, this.b);
/* 1202 */     MinecraftKey var3 = iy.H.a(Blocks.SMOOTH_STONE_SLAB, var1, this.b);
/* 1203 */     MinecraftKey var4 = iy.e.b(Blocks.SMOOTH_STONE_SLAB, "_double", var1, this.b);
/* 1204 */     this.a.accept(h(Blocks.SMOOTH_STONE_SLAB, var2, var3, var4));
/* 1205 */     this.a.accept(e(Blocks.SMOOTH_STONE, iy.c.a(Blocks.SMOOTH_STONE, var0, this.b)));
/*      */   }
/*      */   
/*      */   private void r() {
/* 1209 */     a(Items.nB);
/* 1210 */     this.a.accept(
/* 1211 */         in.a(Blocks.BREWING_STAND)
/* 1212 */         .a(ir.a().a(is.c, iz.C(Blocks.BREWING_STAND)))
/* 1213 */         .a(im.a().a(BlockProperties.k, Boolean.valueOf(true)), ir.a().a(is.c, iz.a(Blocks.BREWING_STAND, "_bottle0")))
/* 1214 */         .a(im.a().a(BlockProperties.l, Boolean.valueOf(true)), ir.a().a(is.c, iz.a(Blocks.BREWING_STAND, "_bottle1")))
/* 1215 */         .a(im.a().a(BlockProperties.m, Boolean.valueOf(true)), ir.a().a(is.c, iz.a(Blocks.BREWING_STAND, "_bottle2")))
/*      */         
/* 1217 */         .a(im.a().a(BlockProperties.k, Boolean.valueOf(false)), ir.a().a(is.c, iz.a(Blocks.BREWING_STAND, "_empty0")))
/* 1218 */         .a(im.a().a(BlockProperties.l, Boolean.valueOf(false)), ir.a().a(is.c, iz.a(Blocks.BREWING_STAND, "_empty1")))
/* 1219 */         .a(im.a().a(BlockProperties.m, Boolean.valueOf(false)), ir.a().a(is.c, iz.a(Blocks.BREWING_STAND, "_empty2"))));
/*      */   }
/*      */ 
/*      */   
/*      */   private void p(Block var0) {
/* 1224 */     MinecraftKey var1 = iy.aJ.a(var0, iz.b(var0), this.b);
/* 1225 */     MinecraftKey var2 = iw.a("mushroom_block_inside");
/*      */     
/* 1227 */     this.a.accept(
/* 1228 */         in.a(var0)
/* 1229 */         .a(im.a().a(BlockProperties.I, Boolean.valueOf(true)), ir.a().a(is.c, var1))
/* 1230 */         .a(im.a().a(BlockProperties.J, Boolean.valueOf(true)), ir.a().<MinecraftKey>a(is.c, var1).<is.a>a(is.b, is.a.b).a(is.d, Boolean.valueOf(true)))
/* 1231 */         .a(im.a().a(BlockProperties.K, Boolean.valueOf(true)), ir.a().<MinecraftKey>a(is.c, var1).<is.a>a(is.b, is.a.c).a(is.d, Boolean.valueOf(true)))
/* 1232 */         .a(im.a().a(BlockProperties.L, Boolean.valueOf(true)), ir.a().<MinecraftKey>a(is.c, var1).<is.a>a(is.b, is.a.d).a(is.d, Boolean.valueOf(true)))
/* 1233 */         .a(im.a().a(BlockProperties.G, Boolean.valueOf(true)), ir.a().<MinecraftKey>a(is.c, var1).<is.a>a(is.a, is.a.d).a(is.d, Boolean.valueOf(true)))
/* 1234 */         .a(im.a().a(BlockProperties.H, Boolean.valueOf(true)), ir.a().<MinecraftKey>a(is.c, var1).<is.a>a(is.a, is.a.b).a(is.d, Boolean.valueOf(true)))
/*      */         
/* 1236 */         .a(im.a().a(BlockProperties.I, Boolean.valueOf(false)), ir.a().a(is.c, var2))
/* 1237 */         .a(im.a().a(BlockProperties.J, Boolean.valueOf(false)), ir.a().<MinecraftKey>a(is.c, var2).<is.a>a(is.b, is.a.b).a(is.d, Boolean.valueOf(false)))
/* 1238 */         .a(im.a().a(BlockProperties.K, Boolean.valueOf(false)), ir.a().<MinecraftKey>a(is.c, var2).<is.a>a(is.b, is.a.c).a(is.d, Boolean.valueOf(false)))
/* 1239 */         .a(im.a().a(BlockProperties.L, Boolean.valueOf(false)), ir.a().<MinecraftKey>a(is.c, var2).<is.a>a(is.b, is.a.d).a(is.d, Boolean.valueOf(false)))
/* 1240 */         .a(im.a().a(BlockProperties.G, Boolean.valueOf(false)), ir.a().<MinecraftKey>a(is.c, var2).<is.a>a(is.a, is.a.d).a(is.d, Boolean.valueOf(false)))
/* 1241 */         .a(im.a().a(BlockProperties.H, Boolean.valueOf(false)), ir.a().<MinecraftKey>a(is.c, var2).<is.a>a(is.a, is.a.b).a(is.d, Boolean.valueOf(false))));
/*      */ 
/*      */     
/* 1244 */     c(var0, jb.a.a(var0, "_inventory", this.b));
/*      */   }
/*      */   
/*      */   private void s() {
/* 1248 */     a(Items.mN);
/* 1249 */     this.a.accept(
/* 1250 */         io.a(Blocks.CAKE)
/* 1251 */         .a(
/* 1252 */           ip.<Integer>a(BlockProperties.al)
/* 1253 */           .a(Integer.valueOf(0), ir.a().a(is.c, iw.a(Blocks.CAKE)))
/* 1254 */           .a(Integer.valueOf(1), ir.a().a(is.c, iw.a(Blocks.CAKE, "_slice1")))
/* 1255 */           .a(Integer.valueOf(2), ir.a().a(is.c, iw.a(Blocks.CAKE, "_slice2")))
/* 1256 */           .a(Integer.valueOf(3), ir.a().a(is.c, iw.a(Blocks.CAKE, "_slice3")))
/* 1257 */           .a(Integer.valueOf(4), ir.a().a(is.c, iw.a(Blocks.CAKE, "_slice4")))
/* 1258 */           .a(Integer.valueOf(5), ir.a().a(is.c, iw.a(Blocks.CAKE, "_slice5")))
/* 1259 */           .a(Integer.valueOf(6), ir.a().a(is.c, iw.a(Blocks.CAKE, "_slice6")))));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void t() {
/* 1272 */     iz var0 = (new iz()).a(ja.c, iz.a(Blocks.CARTOGRAPHY_TABLE, "_side3")).a(ja.o, iz.C(Blocks.DARK_OAK_PLANKS)).a(ja.n, iz.a(Blocks.CARTOGRAPHY_TABLE, "_top")).a(ja.j, iz.a(Blocks.CARTOGRAPHY_TABLE, "_side3")).a(ja.l, iz.a(Blocks.CARTOGRAPHY_TABLE, "_side3")).a(ja.k, iz.a(Blocks.CARTOGRAPHY_TABLE, "_side1")).a(ja.m, iz.a(Blocks.CARTOGRAPHY_TABLE, "_side2"));
/*      */     
/* 1274 */     this.a.accept(e(Blocks.CARTOGRAPHY_TABLE, iy.a.a(Blocks.CARTOGRAPHY_TABLE, var0, this.b)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void u() {
/* 1285 */     iz var0 = (new iz()).a(ja.c, iz.a(Blocks.SMITHING_TABLE, "_front")).a(ja.o, iz.a(Blocks.SMITHING_TABLE, "_bottom")).a(ja.n, iz.a(Blocks.SMITHING_TABLE, "_top")).a(ja.j, iz.a(Blocks.SMITHING_TABLE, "_front")).a(ja.k, iz.a(Blocks.SMITHING_TABLE, "_front")).a(ja.l, iz.a(Blocks.SMITHING_TABLE, "_side")).a(ja.m, iz.a(Blocks.SMITHING_TABLE, "_side"));
/*      */     
/* 1287 */     this.a.accept(e(Blocks.SMITHING_TABLE, iy.a.a(Blocks.SMITHING_TABLE, var0, this.b)));
/*      */   }
/*      */   
/*      */   private void a(Block var0, Block var1, BiFunction<Block, Block, iz> var2) {
/* 1291 */     iz var3 = var2.apply(var0, var1);
/* 1292 */     this.a.accept(e(var0, iy.a.a(var0, var3, this.b)));
/*      */   }
/*      */   
/*      */   private void v() {
/* 1296 */     iz var0 = iz.j(Blocks.PUMPKIN);
/*      */     
/* 1298 */     this.a.accept(e(Blocks.PUMPKIN, iw.a(Blocks.PUMPKIN)));
/*      */     
/* 1300 */     a(Blocks.CARVED_PUMPKIN, var0);
/* 1301 */     a(Blocks.JACK_O_LANTERN, var0);
/*      */   }
/*      */   
/*      */   private void a(Block var0, iz var1) {
/* 1305 */     MinecraftKey var2 = iy.i.a(var0, var1.c(ja.g, iz.C(var0)), this.b);
/* 1306 */     this.a.accept(io.a(var0, ir.a().a(is.c, var2)).a(b()));
/*      */   }
/*      */   
/*      */   private void w() {
/* 1310 */     a(Items.nC);
/* 1311 */     this.a.accept(
/* 1312 */         io.a(Blocks.CAULDRON)
/* 1313 */         .a(
/* 1314 */           ip.<Integer>a(BlockProperties.ar)
/* 1315 */           .a(Integer.valueOf(0), ir.a().a(is.c, iw.a(Blocks.CAULDRON)))
/* 1316 */           .a(Integer.valueOf(1), ir.a().a(is.c, iw.a(Blocks.CAULDRON, "_level1")))
/* 1317 */           .a(Integer.valueOf(2), ir.a().a(is.c, iw.a(Blocks.CAULDRON, "_level2")))
/* 1318 */           .a(Integer.valueOf(3), ir.a().a(is.c, iw.a(Blocks.CAULDRON, "_level3")))));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void g(Block var0, Block var1) {
/* 1326 */     iz var2 = (new iz()).a(ja.d, iz.a(var1, "_top")).a(ja.i, iz.C(var0));
/*      */     
/* 1328 */     a(var0, var2, iy.e);
/*      */   }
/*      */   
/*      */   private void x() {
/* 1332 */     iz var0 = iz.b(Blocks.CHORUS_FLOWER);
/* 1333 */     MinecraftKey var1 = iy.ae.a(Blocks.CHORUS_FLOWER, var0, this.b);
/* 1334 */     MinecraftKey var2 = a(Blocks.CHORUS_FLOWER, "_dead", iy.ae, var1 -> var0.c(ja.b, var1));
/*      */     
/* 1336 */     this.a.accept(
/* 1337 */         io.a(Blocks.CHORUS_FLOWER)
/* 1338 */         .a(a(BlockProperties.ah, Integer.valueOf(5), var2, var1)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void q(Block var0) {
/* 1346 */     iz var1 = (new iz()).a(ja.f, iz.a(Blocks.FURNACE, "_top")).a(ja.i, iz.a(Blocks.FURNACE, "_side")).a(ja.g, iz.a(var0, "_front"));
/*      */ 
/*      */ 
/*      */     
/* 1350 */     iz var2 = (new iz()).a(ja.i, iz.a(Blocks.FURNACE, "_top")).a(ja.g, iz.a(var0, "_front_vertical"));
/*      */     
/* 1352 */     MinecraftKey var3 = iy.i.a(var0, var1, this.b);
/* 1353 */     MinecraftKey var4 = iy.k.a(var0, var2, this.b);
/*      */     
/* 1355 */     this.a.accept(
/* 1356 */         io.a(var0)
/* 1357 */         .a(
/* 1358 */           ip.<EnumDirection>a(BlockProperties.M)
/* 1359 */           .a(EnumDirection.DOWN, ir.a().<MinecraftKey>a(is.c, var4).a(is.a, is.a.c))
/* 1360 */           .a(EnumDirection.UP, ir.a().a(is.c, var4))
/*      */           
/* 1362 */           .a(EnumDirection.NORTH, ir.a().a(is.c, var3))
/* 1363 */           .a(EnumDirection.EAST, ir.a().<MinecraftKey>a(is.c, var3).a(is.b, is.a.b))
/* 1364 */           .a(EnumDirection.SOUTH, ir.a().<MinecraftKey>a(is.c, var3).a(is.b, is.a.c))
/* 1365 */           .a(EnumDirection.WEST, ir.a().<MinecraftKey>a(is.c, var3).a(is.b, is.a.d))));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void y() {
/* 1372 */     MinecraftKey var0 = iw.a(Blocks.END_PORTAL_FRAME);
/* 1373 */     MinecraftKey var1 = iw.a(Blocks.END_PORTAL_FRAME, "_filled");
/*      */     
/* 1375 */     this.a.accept(
/* 1376 */         io.a(Blocks.END_PORTAL_FRAME)
/* 1377 */         .a(
/* 1378 */           ip.<Boolean>a(BlockProperties.h)
/* 1379 */           .a(Boolean.valueOf(false), ir.a().a(is.c, var0))
/* 1380 */           .a(Boolean.valueOf(true), ir.a().a(is.c, var1)))
/*      */         
/* 1382 */         .a(
/* 1383 */           c()));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void z() {
/* 1389 */     MinecraftKey var0 = iw.a(Blocks.CHORUS_PLANT, "_side");
/* 1390 */     MinecraftKey var1 = iw.a(Blocks.CHORUS_PLANT, "_noside");
/* 1391 */     MinecraftKey var2 = iw.a(Blocks.CHORUS_PLANT, "_noside1");
/* 1392 */     MinecraftKey var3 = iw.a(Blocks.CHORUS_PLANT, "_noside2");
/* 1393 */     MinecraftKey var4 = iw.a(Blocks.CHORUS_PLANT, "_noside3");
/*      */     
/* 1395 */     this.a.accept(
/* 1396 */         in.a(Blocks.CHORUS_PLANT)
/* 1397 */         .a(im.a().a(BlockProperties.I, Boolean.valueOf(true)), ir.a().a(is.c, var0))
/* 1398 */         .a(im.a().a(BlockProperties.J, Boolean.valueOf(true)), ir.a().<MinecraftKey>a(is.c, var0).<is.a>a(is.b, is.a.b).a(is.d, Boolean.valueOf(true)))
/* 1399 */         .a(im.a().a(BlockProperties.K, Boolean.valueOf(true)), ir.a().<MinecraftKey>a(is.c, var0).<is.a>a(is.b, is.a.c).a(is.d, Boolean.valueOf(true)))
/* 1400 */         .a(im.a().a(BlockProperties.L, Boolean.valueOf(true)), ir.a().<MinecraftKey>a(is.c, var0).<is.a>a(is.b, is.a.d).a(is.d, Boolean.valueOf(true)))
/* 1401 */         .a(im.a().a(BlockProperties.G, Boolean.valueOf(true)), ir.a().<MinecraftKey>a(is.c, var0).<is.a>a(is.a, is.a.d).a(is.d, Boolean.valueOf(true)))
/* 1402 */         .a(im.a().a(BlockProperties.H, Boolean.valueOf(true)), ir.a().<MinecraftKey>a(is.c, var0).<is.a>a(is.a, is.a.b).a(is.d, Boolean.valueOf(true)))
/*      */         
/* 1404 */         .a(im.a().a(BlockProperties.I, Boolean.valueOf(false)), new ir[] {
/* 1405 */             ir.a().<MinecraftKey>a(is.c, var1).a(is.e, Integer.valueOf(2)), 
/* 1406 */             ir.a().a(is.c, var2), 
/* 1407 */             ir.a().a(is.c, var3), 
/* 1408 */             ir.a().a(is.c, var4)
/*      */ 
/*      */           
/* 1411 */           }).a(im.a().a(BlockProperties.J, Boolean.valueOf(false)), new ir[] {
/* 1412 */             ir.a().<MinecraftKey>a(is.c, var2).<is.a>a(is.b, is.a.b).a(is.d, Boolean.valueOf(true)), 
/* 1413 */             ir.a().<MinecraftKey>a(is.c, var3).<is.a>a(is.b, is.a.b).a(is.d, Boolean.valueOf(true)), 
/* 1414 */             ir.a().<MinecraftKey>a(is.c, var4).<is.a>a(is.b, is.a.b).a(is.d, Boolean.valueOf(true)), 
/* 1415 */             ir.a().<MinecraftKey>a(is.c, var1).<Integer>a(is.e, Integer.valueOf(2)).<is.a>a(is.b, is.a.b).a(is.d, Boolean.valueOf(true))
/*      */ 
/*      */           
/* 1418 */           }).a(im.a().a(BlockProperties.K, Boolean.valueOf(false)), new ir[] {
/* 1419 */             ir.a().<MinecraftKey>a(is.c, var3).<is.a>a(is.b, is.a.c).a(is.d, Boolean.valueOf(true)), 
/* 1420 */             ir.a().<MinecraftKey>a(is.c, var4).<is.a>a(is.b, is.a.c).a(is.d, Boolean.valueOf(true)), 
/* 1421 */             ir.a().<MinecraftKey>a(is.c, var1).<Integer>a(is.e, Integer.valueOf(2)).<is.a>a(is.b, is.a.c).a(is.d, Boolean.valueOf(true)), 
/* 1422 */             ir.a().<MinecraftKey>a(is.c, var2).<is.a>a(is.b, is.a.c).a(is.d, Boolean.valueOf(true))
/*      */ 
/*      */           
/* 1425 */           }).a(im.a().a(BlockProperties.L, Boolean.valueOf(false)), new ir[] {
/* 1426 */             ir.a().<MinecraftKey>a(is.c, var4).<is.a>a(is.b, is.a.d).a(is.d, Boolean.valueOf(true)), 
/* 1427 */             ir.a().<MinecraftKey>a(is.c, var1).<Integer>a(is.e, Integer.valueOf(2)).<is.a>a(is.b, is.a.d).a(is.d, Boolean.valueOf(true)), 
/* 1428 */             ir.a().<MinecraftKey>a(is.c, var2).<is.a>a(is.b, is.a.d).a(is.d, Boolean.valueOf(true)), 
/* 1429 */             ir.a().<MinecraftKey>a(is.c, var3).<is.a>a(is.b, is.a.d).a(is.d, Boolean.valueOf(true))
/*      */ 
/*      */           
/* 1432 */           }).a(im.a().a(BlockProperties.G, Boolean.valueOf(false)), new ir[] {
/* 1433 */             ir.a().<MinecraftKey>a(is.c, var1).<Integer>a(is.e, Integer.valueOf(2)).<is.a>a(is.a, is.a.d).a(is.d, Boolean.valueOf(true)), 
/* 1434 */             ir.a().<MinecraftKey>a(is.c, var4).<is.a>a(is.a, is.a.d).a(is.d, Boolean.valueOf(true)), 
/* 1435 */             ir.a().<MinecraftKey>a(is.c, var2).<is.a>a(is.a, is.a.d).a(is.d, Boolean.valueOf(true)), 
/* 1436 */             ir.a().<MinecraftKey>a(is.c, var3).<is.a>a(is.a, is.a.d).a(is.d, Boolean.valueOf(true))
/*      */ 
/*      */           
/* 1439 */           }).a(im.a().a(BlockProperties.H, Boolean.valueOf(false)), new ir[] {
/* 1440 */             ir.a().<MinecraftKey>a(is.c, var4).<is.a>a(is.a, is.a.b).a(is.d, Boolean.valueOf(true)), 
/* 1441 */             ir.a().<MinecraftKey>a(is.c, var3).<is.a>a(is.a, is.a.b).a(is.d, Boolean.valueOf(true)), 
/* 1442 */             ir.a().<MinecraftKey>a(is.c, var2).<is.a>a(is.a, is.a.b).a(is.d, Boolean.valueOf(true)), 
/* 1443 */             ir.a().<MinecraftKey>a(is.c, var1).<Integer>a(is.e, Integer.valueOf(2)).<is.a>a(is.a, is.a.b).a(is.d, Boolean.valueOf(true))
/*      */           }));
/*      */   }
/*      */ 
/*      */   
/*      */   private void A() {
/* 1449 */     this.a.accept(in.a(Blocks.COMPOSTER)
/* 1450 */         .a(ir.a().a(is.c, iz.C(Blocks.COMPOSTER)))
/* 1451 */         .a(im.a().a(BlockProperties.as, Integer.valueOf(1)), ir.a().a(is.c, iz.a(Blocks.COMPOSTER, "_contents1")))
/* 1452 */         .a(im.a().a(BlockProperties.as, Integer.valueOf(2)), ir.a().a(is.c, iz.a(Blocks.COMPOSTER, "_contents2")))
/* 1453 */         .a(im.a().a(BlockProperties.as, Integer.valueOf(3)), ir.a().a(is.c, iz.a(Blocks.COMPOSTER, "_contents3")))
/* 1454 */         .a(im.a().a(BlockProperties.as, Integer.valueOf(4)), ir.a().a(is.c, iz.a(Blocks.COMPOSTER, "_contents4")))
/* 1455 */         .a(im.a().a(BlockProperties.as, Integer.valueOf(5)), ir.a().a(is.c, iz.a(Blocks.COMPOSTER, "_contents5")))
/* 1456 */         .a(im.a().a(BlockProperties.as, Integer.valueOf(6)), ir.a().a(is.c, iz.a(Blocks.COMPOSTER, "_contents6")))
/* 1457 */         .a(im.a().a(BlockProperties.as, Integer.valueOf(7)), ir.a().a(is.c, iz.a(Blocks.COMPOSTER, "_contents7")))
/* 1458 */         .a(im.a().a(BlockProperties.as, Integer.valueOf(8)), ir.a().a(is.c, iz.a(Blocks.COMPOSTER, "_contents_ready"))));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void r(Block var0) {
/* 1467 */     iz var1 = (new iz()).a(ja.e, iz.C(Blocks.NETHERRACK)).a(ja.f, iz.C(var0)).a(ja.i, iz.a(var0, "_side"));
/*      */     
/* 1469 */     this.a.accept(e(var0, iy.h.a(var0, var1, this.b)));
/*      */   }
/*      */   
/*      */   private void B() {
/* 1473 */     MinecraftKey var0 = iz.a(Blocks.DAYLIGHT_DETECTOR, "_side");
/* 1474 */     iz var1 = (new iz()).a(ja.f, iz.a(Blocks.DAYLIGHT_DETECTOR, "_top")).a(ja.i, var0);
/* 1475 */     iz var2 = (new iz()).a(ja.f, iz.a(Blocks.DAYLIGHT_DETECTOR, "_inverted_top")).a(ja.i, var0);
/*      */     
/* 1477 */     this.a.accept(
/* 1478 */         io.a(Blocks.DAYLIGHT_DETECTOR)
/* 1479 */         .a(
/* 1480 */           ip.<Boolean>a(BlockProperties.p)
/* 1481 */           .a(Boolean.valueOf(false), ir.a().a(is.c, iy.af.a(Blocks.DAYLIGHT_DETECTOR, var1, this.b)))
/* 1482 */           .a(Boolean.valueOf(true), ir.a().a(is.c, iy.af.a(iw.a(Blocks.DAYLIGHT_DETECTOR, "_inverted"), var2, this.b)))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void s(Block var0) {
/* 1488 */     this.a.accept(
/* 1489 */         io.a(var0, ir.a().a(is.c, iw.a(var0)))
/* 1490 */         .a(j()));
/*      */   }
/*      */ 
/*      */   
/*      */   private void C() {
/* 1495 */     iz var0 = (new iz()).a(ja.B, iz.C(Blocks.DIRT)).a(ja.f, iz.C(Blocks.FARMLAND));
/* 1496 */     iz var1 = (new iz()).a(ja.B, iz.C(Blocks.DIRT)).a(ja.f, iz.a(Blocks.FARMLAND, "_moist"));
/*      */     
/* 1498 */     MinecraftKey var2 = iy.aq.a(Blocks.FARMLAND, var0, this.b);
/* 1499 */     MinecraftKey var3 = iy.aq.a(iz.a(Blocks.FARMLAND, "_moist"), var1, this.b);
/*      */     
/* 1501 */     this.a.accept(
/* 1502 */         io.a(Blocks.FARMLAND)
/* 1503 */         .a(a(BlockProperties.aw, Integer.valueOf(7), var3, var2)));
/*      */   }
/*      */ 
/*      */   
/*      */   private List<MinecraftKey> t(Block var0) {
/* 1508 */     MinecraftKey var1 = iy.ar.a(iw.a(var0, "_floor0"), iz.r(var0), this.b);
/* 1509 */     MinecraftKey var2 = iy.ar.a(iw.a(var0, "_floor1"), iz.s(var0), this.b);
/*      */     
/* 1511 */     return (List<MinecraftKey>)ImmutableList.of(var1, var2);
/*      */   }
/*      */   
/*      */   private List<MinecraftKey> u(Block var0) {
/* 1515 */     MinecraftKey var1 = iy.as.a(iw.a(var0, "_side0"), iz.r(var0), this.b);
/* 1516 */     MinecraftKey var2 = iy.as.a(iw.a(var0, "_side1"), iz.s(var0), this.b);
/*      */     
/* 1518 */     MinecraftKey var3 = iy.at.a(iw.a(var0, "_side_alt0"), iz.r(var0), this.b);
/* 1519 */     MinecraftKey var4 = iy.at.a(iw.a(var0, "_side_alt1"), iz.s(var0), this.b);
/*      */     
/* 1521 */     return (List<MinecraftKey>)ImmutableList.of(var1, var2, var3, var4);
/*      */   }
/*      */   
/*      */   private List<MinecraftKey> v(Block var0) {
/* 1525 */     MinecraftKey var1 = iy.au.a(iw.a(var0, "_up0"), iz.r(var0), this.b);
/* 1526 */     MinecraftKey var2 = iy.au.a(iw.a(var0, "_up1"), iz.s(var0), this.b);
/*      */     
/* 1528 */     MinecraftKey var3 = iy.av.a(iw.a(var0, "_up_alt0"), iz.r(var0), this.b);
/* 1529 */     MinecraftKey var4 = iy.av.a(iw.a(var0, "_up_alt1"), iz.s(var0), this.b);
/*      */     
/* 1531 */     return (List<MinecraftKey>)ImmutableList.of(var1, var2, var3, var4);
/*      */   }
/*      */   
/*      */   private static List<ir> a(List<MinecraftKey> var0, UnaryOperator<ir> var1) {
/* 1535 */     return (List<ir>)var0.stream().map(var0 -> ir.a().a(is.c, var0)).map(var1).collect(Collectors.toList());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void D() {
/* 1544 */     im var0 = im.a().<Boolean>a(BlockProperties.I, Boolean.valueOf(false)).<Boolean>a(BlockProperties.J, Boolean.valueOf(false)).<Boolean>a(BlockProperties.K, Boolean.valueOf(false)).<Boolean>a(BlockProperties.L, Boolean.valueOf(false)).a(BlockProperties.G, Boolean.valueOf(false));
/* 1545 */     List<MinecraftKey> var1 = t(Blocks.FIRE);
/* 1546 */     List<MinecraftKey> var2 = u(Blocks.FIRE);
/* 1547 */     List<MinecraftKey> var3 = v(Blocks.FIRE);
/*      */     
/* 1549 */     this.a.accept(
/* 1550 */         in.a(Blocks.FIRE)
/* 1551 */         .a(var0, 
/*      */           
/* 1553 */           a(var1, var0 -> var0))
/*      */         
/* 1555 */         .a(
/* 1556 */           im.b(new im[] { im.a().a(BlockProperties.I, Boolean.valueOf(true)), var0
/* 1557 */             }), a(var2, var0 -> var0))
/*      */         
/* 1559 */         .a(
/* 1560 */           im.b(new im[] { im.a().a(BlockProperties.J, Boolean.valueOf(true)), var0
/* 1561 */             }), a(var2, var0 -> var0.a(is.b, is.a.b)))
/*      */         
/* 1563 */         .a(
/* 1564 */           im.b(new im[] { im.a().a(BlockProperties.K, Boolean.valueOf(true)), var0
/* 1565 */             }), a(var2, var0 -> var0.a(is.b, is.a.c)))
/*      */         
/* 1567 */         .a(
/* 1568 */           im.b(new im[] { im.a().a(BlockProperties.L, Boolean.valueOf(true)), var0
/* 1569 */             }), a(var2, var0 -> var0.a(is.b, is.a.d)))
/*      */         
/* 1571 */         .a(
/* 1572 */           im.a().a(BlockProperties.G, Boolean.valueOf(true)), 
/* 1573 */           a(var3, var0 -> var0)));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void E() {
/* 1579 */     List<MinecraftKey> var0 = t(Blocks.SOUL_FIRE);
/* 1580 */     List<MinecraftKey> var1 = u(Blocks.SOUL_FIRE);
/*      */     
/* 1582 */     this.a.accept(
/* 1583 */         in.a(Blocks.SOUL_FIRE)
/* 1584 */         .a(a(var0, var0 -> var0))
/* 1585 */         .a(a(var1, var0 -> var0))
/* 1586 */         .a(a(var1, var0 -> var0.a(is.b, is.a.b)))
/* 1587 */         .a(a(var1, var0 -> var0.a(is.b, is.a.c)))
/* 1588 */         .a(a(var1, var0 -> var0.a(is.b, is.a.d))));
/*      */   }
/*      */ 
/*      */   
/*      */   private void w(Block var0) {
/* 1593 */     MinecraftKey var1 = jb.o.a(var0, this.b);
/* 1594 */     MinecraftKey var2 = jb.p.a(var0, this.b);
/*      */     
/* 1596 */     a(var0.getItem());
/* 1597 */     this.a.accept(
/* 1598 */         io.a(var0)
/* 1599 */         .a(a(BlockProperties.j, var2, var1)));
/*      */   }
/*      */ 
/*      */   
/*      */   private void F() {
/* 1604 */     this.a.accept(
/* 1605 */         io.a(Blocks.FROSTED_ICE)
/* 1606 */         .a(
/* 1607 */           ip.<Integer>a(BlockProperties.ag)
/* 1608 */           .a(Integer.valueOf(0), ir.a().a(is.c, a(Blocks.FROSTED_ICE, "_0", iy.c, iz::b)))
/* 1609 */           .a(Integer.valueOf(1), ir.a().a(is.c, a(Blocks.FROSTED_ICE, "_1", iy.c, iz::b)))
/* 1610 */           .a(Integer.valueOf(2), ir.a().a(is.c, a(Blocks.FROSTED_ICE, "_2", iy.c, iz::b)))
/* 1611 */           .a(Integer.valueOf(3), ir.a().a(is.c, a(Blocks.FROSTED_ICE, "_3", iy.c, iz::b)))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void G() {
/* 1617 */     MinecraftKey var0 = iz.C(Blocks.DIRT);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1622 */     iz var1 = (new iz()).a(ja.e, var0).b(ja.e, ja.c).a(ja.f, iz.a(Blocks.GRASS_BLOCK, "_top")).a(ja.i, iz.a(Blocks.GRASS_BLOCK, "_snow"));
/*      */     
/* 1624 */     ir var2 = ir.a().a(is.c, iy.h.a(Blocks.GRASS_BLOCK, "_snow", var1, this.b));
/*      */     
/* 1626 */     a(Blocks.GRASS_BLOCK, iw.a(Blocks.GRASS_BLOCK), var2);
/*      */     
/* 1628 */     MinecraftKey var3 = jb.e.get(Blocks.MYCELIUM).a(var1 -> var1.a(ja.e, var0)).a(Blocks.MYCELIUM, this.b);
/* 1629 */     a(Blocks.MYCELIUM, var3, var2);
/*      */     
/* 1631 */     MinecraftKey var4 = jb.e.get(Blocks.PODZOL).a(var1 -> var1.a(ja.e, var0)).a(Blocks.PODZOL, this.b);
/* 1632 */     a(Blocks.PODZOL, var4, var2);
/*      */   }
/*      */   
/*      */   private void a(Block var0, MinecraftKey var1, ir var2) {
/* 1636 */     List<ir> var3 = Arrays.asList(a(var1));
/* 1637 */     this.a.accept(
/* 1638 */         io.a(var0)
/* 1639 */         .a(
/* 1640 */           ip.<Boolean>a(BlockProperties.z)
/* 1641 */           .a(Boolean.valueOf(true), var2)
/* 1642 */           .a(Boolean.valueOf(false), var3)));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void H() {
/* 1648 */     a(Items.COCOA_BEANS);
/* 1649 */     this.a.accept(
/* 1650 */         io.a(Blocks.COCOA)
/* 1651 */         .a(
/* 1652 */           ip.<Integer>a(BlockProperties.af)
/* 1653 */           .a(Integer.valueOf(0), ir.a().a(is.c, iw.a(Blocks.COCOA, "_stage0")))
/* 1654 */           .a(Integer.valueOf(1), ir.a().a(is.c, iw.a(Blocks.COCOA, "_stage1")))
/* 1655 */           .a(Integer.valueOf(2), ir.a().a(is.c, iw.a(Blocks.COCOA, "_stage2"))))
/*      */         
/* 1657 */         .a(c()));
/*      */   }
/*      */ 
/*      */   
/*      */   private void I() {
/* 1662 */     this.a.accept(d(Blocks.GRASS_PATH, iw.a(Blocks.GRASS_PATH)));
/*      */   }
/*      */   
/*      */   private void h(Block var0, Block var1) {
/* 1666 */     iz var2 = iz.b(var1);
/* 1667 */     MinecraftKey var3 = iy.D.a(var0, var2, this.b);
/* 1668 */     MinecraftKey var4 = iy.E.a(var0, var2, this.b);
/*      */     
/* 1670 */     this.a.accept(
/* 1671 */         io.a(var0)
/* 1672 */         .a(a(BlockProperties.az, Integer.valueOf(1), var4, var3)));
/*      */   }
/*      */ 
/*      */   
/*      */   private void J() {
/* 1677 */     MinecraftKey var0 = iw.a(Blocks.HOPPER);
/* 1678 */     MinecraftKey var1 = iw.a(Blocks.HOPPER, "_side");
/*      */     
/* 1680 */     a(Items.fl);
/* 1681 */     this.a.accept(
/* 1682 */         io.a(Blocks.HOPPER)
/* 1683 */         .a(
/* 1684 */           ip.<EnumDirection>a(BlockProperties.N)
/* 1685 */           .a(EnumDirection.DOWN, ir.a().a(is.c, var0))
/* 1686 */           .a(EnumDirection.NORTH, ir.a().a(is.c, var1))
/* 1687 */           .a(EnumDirection.EAST, ir.a().<MinecraftKey>a(is.c, var1).a(is.b, is.a.b))
/* 1688 */           .a(EnumDirection.SOUTH, ir.a().<MinecraftKey>a(is.c, var1).a(is.b, is.a.c))
/* 1689 */           .a(EnumDirection.WEST, ir.a().<MinecraftKey>a(is.c, var1).a(is.b, is.a.d))));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void i(Block var0, Block var1) {
/* 1696 */     MinecraftKey var2 = iw.a(var0);
/* 1697 */     this.a.accept(io.a(var1, ir.a().a(is.c, var2)));
/* 1698 */     c(var1, var2);
/*      */   }
/*      */   
/*      */   private void K() {
/* 1702 */     MinecraftKey var0 = iw.a(Blocks.IRON_BARS, "_post_ends");
/* 1703 */     MinecraftKey var1 = iw.a(Blocks.IRON_BARS, "_post");
/* 1704 */     MinecraftKey var2 = iw.a(Blocks.IRON_BARS, "_cap");
/* 1705 */     MinecraftKey var3 = iw.a(Blocks.IRON_BARS, "_cap_alt");
/* 1706 */     MinecraftKey var4 = iw.a(Blocks.IRON_BARS, "_side");
/* 1707 */     MinecraftKey var5 = iw.a(Blocks.IRON_BARS, "_side_alt");
/*      */     
/* 1709 */     this.a.accept(
/* 1710 */         in.a(Blocks.IRON_BARS)
/* 1711 */         .a(ir.a().a(is.c, var0))
/* 1712 */         .a(
/* 1713 */           im.a()
/* 1714 */           .<Boolean>a(BlockProperties.I, Boolean.valueOf(false))
/* 1715 */           .<Boolean>a(BlockProperties.J, Boolean.valueOf(false))
/* 1716 */           .<Boolean>a(BlockProperties.K, Boolean.valueOf(false))
/* 1717 */           .a(BlockProperties.L, Boolean.valueOf(false)), 
/* 1718 */           ir.a().a(is.c, var1))
/*      */         
/* 1720 */         .a(
/* 1721 */           im.a()
/* 1722 */           .<Boolean>a(BlockProperties.I, Boolean.valueOf(true))
/* 1723 */           .<Boolean>a(BlockProperties.J, Boolean.valueOf(false))
/* 1724 */           .<Boolean>a(BlockProperties.K, Boolean.valueOf(false))
/* 1725 */           .a(BlockProperties.L, Boolean.valueOf(false)), 
/* 1726 */           ir.a().a(is.c, var2))
/*      */         
/* 1728 */         .a(
/* 1729 */           im.a()
/* 1730 */           .<Boolean>a(BlockProperties.I, Boolean.valueOf(false))
/* 1731 */           .<Boolean>a(BlockProperties.J, Boolean.valueOf(true))
/* 1732 */           .<Boolean>a(BlockProperties.K, Boolean.valueOf(false))
/* 1733 */           .a(BlockProperties.L, Boolean.valueOf(false)), 
/* 1734 */           ir.a().<MinecraftKey>a(is.c, var2).a(is.b, is.a.b))
/*      */         
/* 1736 */         .a(
/* 1737 */           im.a()
/* 1738 */           .<Boolean>a(BlockProperties.I, Boolean.valueOf(false))
/* 1739 */           .<Boolean>a(BlockProperties.J, Boolean.valueOf(false))
/* 1740 */           .<Boolean>a(BlockProperties.K, Boolean.valueOf(true))
/* 1741 */           .a(BlockProperties.L, Boolean.valueOf(false)), 
/* 1742 */           ir.a().a(is.c, var3))
/*      */         
/* 1744 */         .a(
/* 1745 */           im.a()
/* 1746 */           .<Boolean>a(BlockProperties.I, Boolean.valueOf(false))
/* 1747 */           .<Boolean>a(BlockProperties.J, Boolean.valueOf(false))
/* 1748 */           .<Boolean>a(BlockProperties.K, Boolean.valueOf(false))
/* 1749 */           .a(BlockProperties.L, Boolean.valueOf(true)), 
/* 1750 */           ir.a().<MinecraftKey>a(is.c, var3).a(is.b, is.a.b))
/*      */         
/* 1752 */         .a(im.a().a(BlockProperties.I, Boolean.valueOf(true)), ir.a().a(is.c, var4))
/* 1753 */         .a(im.a().a(BlockProperties.J, Boolean.valueOf(true)), ir.a().<MinecraftKey>a(is.c, var4).a(is.b, is.a.b))
/* 1754 */         .a(im.a().a(BlockProperties.K, Boolean.valueOf(true)), ir.a().a(is.c, var5))
/* 1755 */         .a(im.a().a(BlockProperties.L, Boolean.valueOf(true)), ir.a().<MinecraftKey>a(is.c, var5).a(is.b, is.a.b)));
/*      */     
/* 1757 */     b(Blocks.IRON_BARS);
/*      */   }
/*      */   
/*      */   private void x(Block var0) {
/* 1761 */     this.a.accept(
/* 1762 */         io.a(var0, ir.a().a(is.c, iw.a(var0)))
/* 1763 */         .a(b()));
/*      */   }
/*      */ 
/*      */   
/*      */   private void L() {
/* 1768 */     MinecraftKey var0 = iw.a(Blocks.LEVER);
/* 1769 */     MinecraftKey var1 = iw.a(Blocks.LEVER, "_on");
/*      */     
/* 1771 */     b(Blocks.LEVER);
/* 1772 */     this.a.accept(
/* 1773 */         io.a(Blocks.LEVER)
/* 1774 */         .a(
/* 1775 */           a(BlockProperties.w, var0, var1))
/*      */         
/* 1777 */         .a(
/* 1778 */           ip.<BlockPropertyAttachPosition, EnumDirection>a(BlockProperties.Q, BlockProperties.O)
/* 1779 */           .a(BlockPropertyAttachPosition.CEILING, EnumDirection.NORTH, ir.a().<is.a>a(is.a, is.a.c).a(is.b, is.a.c))
/* 1780 */           .a(BlockPropertyAttachPosition.CEILING, EnumDirection.EAST, ir.a().<is.a>a(is.a, is.a.c).a(is.b, is.a.d))
/* 1781 */           .a(BlockPropertyAttachPosition.CEILING, EnumDirection.SOUTH, ir.a().a(is.a, is.a.c))
/* 1782 */           .a(BlockPropertyAttachPosition.CEILING, EnumDirection.WEST, ir.a().<is.a>a(is.a, is.a.c).a(is.b, is.a.b))
/*      */           
/* 1784 */           .a(BlockPropertyAttachPosition.FLOOR, EnumDirection.NORTH, ir.a())
/* 1785 */           .a(BlockPropertyAttachPosition.FLOOR, EnumDirection.EAST, ir.a().a(is.b, is.a.b))
/* 1786 */           .a(BlockPropertyAttachPosition.FLOOR, EnumDirection.SOUTH, ir.a().a(is.b, is.a.c))
/* 1787 */           .a(BlockPropertyAttachPosition.FLOOR, EnumDirection.WEST, ir.a().a(is.b, is.a.d))
/*      */           
/* 1789 */           .a(BlockPropertyAttachPosition.WALL, EnumDirection.NORTH, ir.a().a(is.a, is.a.b))
/* 1790 */           .a(BlockPropertyAttachPosition.WALL, EnumDirection.EAST, ir.a().<is.a>a(is.a, is.a.b).a(is.b, is.a.b))
/* 1791 */           .a(BlockPropertyAttachPosition.WALL, EnumDirection.SOUTH, ir.a().<is.a>a(is.a, is.a.b).a(is.b, is.a.c))
/* 1792 */           .a(BlockPropertyAttachPosition.WALL, EnumDirection.WEST, ir.a().<is.a>a(is.a, is.a.b).a(is.b, is.a.d))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void M() {
/* 1798 */     b(Blocks.LILY_PAD);
/* 1799 */     this.a.accept(d(Blocks.LILY_PAD, iw.a(Blocks.LILY_PAD)));
/*      */   }
/*      */   
/*      */   private void N() {
/* 1803 */     this.a.accept(
/* 1804 */         io.a(Blocks.NETHER_PORTAL)
/* 1805 */         .a(
/* 1806 */           ip.<EnumDirection.EnumAxis>a(BlockProperties.E)
/* 1807 */           .a(EnumDirection.EnumAxis.X, ir.a().a(is.c, iw.a(Blocks.NETHER_PORTAL, "_ns")))
/* 1808 */           .a(EnumDirection.EnumAxis.Z, ir.a().a(is.c, iw.a(Blocks.NETHER_PORTAL, "_ew")))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void O() {
/* 1814 */     MinecraftKey var0 = jb.a.a(Blocks.NETHERRACK, this.b);
/* 1815 */     this.a.accept(
/* 1816 */         io.a(Blocks.NETHERRACK, new ir[] { 
/* 1817 */             ir.a().a(is.c, var0), 
/* 1818 */             ir.a().<MinecraftKey>a(is.c, var0).a(is.a, is.a.b), 
/* 1819 */             ir.a().<MinecraftKey>a(is.c, var0).a(is.a, is.a.c), 
/* 1820 */             ir.a().<MinecraftKey>a(is.c, var0).a(is.a, is.a.d), 
/*      */             
/* 1822 */             ir.a().<MinecraftKey>a(is.c, var0).a(is.b, is.a.b), 
/* 1823 */             ir.a().<MinecraftKey>a(is.c, var0).<is.a>a(is.b, is.a.b).a(is.a, is.a.b), 
/* 1824 */             ir.a().<MinecraftKey>a(is.c, var0).<is.a>a(is.b, is.a.b).a(is.a, is.a.c), 
/* 1825 */             ir.a().<MinecraftKey>a(is.c, var0).<is.a>a(is.b, is.a.b).a(is.a, is.a.d), 
/*      */             
/* 1827 */             ir.a().<MinecraftKey>a(is.c, var0).a(is.b, is.a.c), 
/* 1828 */             ir.a().<MinecraftKey>a(is.c, var0).<is.a>a(is.b, is.a.c).a(is.a, is.a.b), 
/* 1829 */             ir.a().<MinecraftKey>a(is.c, var0).<is.a>a(is.b, is.a.c).a(is.a, is.a.c), 
/* 1830 */             ir.a().<MinecraftKey>a(is.c, var0).<is.a>a(is.b, is.a.c).a(is.a, is.a.d), 
/*      */             
/* 1832 */             ir.a().<MinecraftKey>a(is.c, var0).a(is.b, is.a.d), 
/* 1833 */             ir.a().<MinecraftKey>a(is.c, var0).<is.a>a(is.b, is.a.d).a(is.a, is.a.b), 
/* 1834 */             ir.a().<MinecraftKey>a(is.c, var0).<is.a>a(is.b, is.a.d).a(is.a, is.a.c), 
/* 1835 */             ir.a().<MinecraftKey>a(is.c, var0).<is.a>a(is.b, is.a.d).a(is.a, is.a.d) }));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void P() {
/* 1842 */     MinecraftKey var0 = iw.a(Blocks.OBSERVER);
/* 1843 */     MinecraftKey var1 = iw.a(Blocks.OBSERVER, "_on");
/*      */     
/* 1845 */     this.a.accept(
/* 1846 */         io.a(Blocks.OBSERVER)
/* 1847 */         .a(a(BlockProperties.w, var1, var0))
/* 1848 */         .a(e()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void Q() {
/* 1855 */     iz var0 = (new iz()).a(ja.e, iz.a(Blocks.PISTON, "_bottom")).a(ja.i, iz.a(Blocks.PISTON, "_side"));
/*      */     
/* 1857 */     MinecraftKey var1 = iz.a(Blocks.PISTON, "_top_sticky");
/* 1858 */     MinecraftKey var2 = iz.a(Blocks.PISTON, "_top");
/*      */     
/* 1860 */     iz var3 = var0.c(ja.E, var1);
/* 1861 */     iz var4 = var0.c(ja.E, var2);
/*      */     
/* 1863 */     MinecraftKey var5 = iw.a(Blocks.PISTON, "_base");
/*      */     
/* 1865 */     a(Blocks.PISTON, var5, var4);
/* 1866 */     a(Blocks.STICKY_PISTON, var5, var3);
/*      */     
/* 1868 */     MinecraftKey var6 = iy.h.a(Blocks.PISTON, "_inventory", var0.c(ja.f, var2), this.b);
/* 1869 */     MinecraftKey var7 = iy.h.a(Blocks.STICKY_PISTON, "_inventory", var0.c(ja.f, var1), this.b);
/*      */     
/* 1871 */     c(Blocks.PISTON, var6);
/* 1872 */     c(Blocks.STICKY_PISTON, var7);
/*      */   }
/*      */   
/*      */   private void a(Block var0, MinecraftKey var1, iz var2) {
/* 1876 */     MinecraftKey var3 = iy.aB.a(var0, var2, this.b);
/* 1877 */     this.a.accept(
/* 1878 */         io.a(var0)
/* 1879 */         .a(a(BlockProperties.g, var1, var3))
/* 1880 */         .a(e()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void R() {
/* 1887 */     iz var0 = (new iz()).a(ja.F, iz.a(Blocks.PISTON, "_top")).a(ja.i, iz.a(Blocks.PISTON, "_side"));
/*      */     
/* 1889 */     iz var1 = var0.c(ja.E, iz.a(Blocks.PISTON, "_top_sticky"));
/* 1890 */     iz var2 = var0.c(ja.E, iz.a(Blocks.PISTON, "_top"));
/*      */     
/* 1892 */     this.a.accept(
/* 1893 */         io.a(Blocks.PISTON_HEAD)
/* 1894 */         .a(
/* 1895 */           ip.<Boolean, BlockPropertyPistonType>a(BlockProperties.x, BlockProperties.aJ)
/* 1896 */           .a(Boolean.valueOf(false), BlockPropertyPistonType.DEFAULT, ir.a().a(is.c, iy.aC.a(Blocks.PISTON, "_head", var2, this.b)))
/* 1897 */           .a(Boolean.valueOf(false), BlockPropertyPistonType.STICKY, ir.a().a(is.c, iy.aC.a(Blocks.PISTON, "_head_sticky", var1, this.b)))
/* 1898 */           .a(Boolean.valueOf(true), BlockPropertyPistonType.DEFAULT, ir.a().a(is.c, iy.aD.a(Blocks.PISTON, "_head_short", var2, this.b)))
/* 1899 */           .a(Boolean.valueOf(true), BlockPropertyPistonType.STICKY, ir.a().a(is.c, iy.aD.a(Blocks.PISTON, "_head_short_sticky", var1, this.b))))
/*      */         
/* 1901 */         .a(
/* 1902 */           e()));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void S() {
/* 1908 */     MinecraftKey var0 = iw.a(Blocks.SCAFFOLDING, "_stable");
/* 1909 */     MinecraftKey var1 = iw.a(Blocks.SCAFFOLDING, "_unstable");
/* 1910 */     c(Blocks.SCAFFOLDING, var0);
/* 1911 */     this.a.accept(
/* 1912 */         io.a(Blocks.SCAFFOLDING)
/* 1913 */         .a(a(BlockProperties.b, var1, var0)));
/*      */   }
/*      */ 
/*      */   
/*      */   private void T() {
/* 1918 */     MinecraftKey var0 = jb.a.a(Blocks.REDSTONE_LAMP, this.b);
/* 1919 */     MinecraftKey var1 = a(Blocks.REDSTONE_LAMP, "_on", iy.c, iz::b);
/*      */     
/* 1921 */     this.a.accept(
/* 1922 */         io.a(Blocks.REDSTONE_LAMP)
/* 1923 */         .a(a(BlockProperties.r, var1, var0)));
/*      */   }
/*      */ 
/*      */   
/*      */   private void j(Block var0, Block var1) {
/* 1928 */     iz var2 = iz.u(var0);
/*      */     
/* 1930 */     this.a.accept(e(var0, iy.az.a(var0, var2, this.b)));
/*      */     
/* 1932 */     this.a.accept(
/* 1933 */         io.a(var1, ir.a().a(is.c, iy.aA.a(var1, var2, this.b)))
/* 1934 */         .a(d()));
/*      */     
/* 1936 */     b(var0);
/* 1937 */     a(var1);
/*      */   }
/*      */   
/*      */   private void U() {
/* 1941 */     iz var0 = iz.u(Blocks.REDSTONE_TORCH);
/* 1942 */     iz var1 = iz.i(iz.a(Blocks.REDSTONE_TORCH, "_off"));
/*      */     
/* 1944 */     MinecraftKey var2 = iy.az.a(Blocks.REDSTONE_TORCH, var0, this.b);
/* 1945 */     MinecraftKey var3 = iy.az.a(Blocks.REDSTONE_TORCH, "_off", var1, this.b);
/*      */     
/* 1947 */     this.a.accept(
/* 1948 */         io.a(Blocks.REDSTONE_TORCH)
/* 1949 */         .a(a(BlockProperties.r, var2, var3)));
/*      */ 
/*      */     
/* 1952 */     MinecraftKey var4 = iy.aA.a(Blocks.REDSTONE_WALL_TORCH, var0, this.b);
/* 1953 */     MinecraftKey var5 = iy.aA.a(Blocks.REDSTONE_WALL_TORCH, "_off", var1, this.b);
/*      */     
/* 1955 */     this.a.accept(
/* 1956 */         io.a(Blocks.REDSTONE_WALL_TORCH)
/* 1957 */         .a(a(BlockProperties.r, var4, var5))
/* 1958 */         .a(d()));
/*      */     
/* 1960 */     b(Blocks.REDSTONE_TORCH);
/* 1961 */     a(Blocks.REDSTONE_WALL_TORCH);
/*      */   }
/*      */   
/*      */   private void V() {
/* 1965 */     a(Items.jU);
/* 1966 */     this.a.accept(
/* 1967 */         io.a(Blocks.REPEATER)
/* 1968 */         .a(
/* 1969 */           ip.<Comparable, Comparable, Comparable>a(BlockProperties.am, BlockProperties.s, BlockProperties.w)
/* 1970 */           .a((var0, var1, var2) -> {
/*      */               StringBuilder var3 = new StringBuilder();
/*      */               
/*      */               var3.append('_').append(var0).append("tick");
/*      */               
/*      */               if (var2.booleanValue()) {
/*      */                 var3.append("_on");
/*      */               }
/*      */               if (var1.booleanValue()) {
/*      */                 var3.append("_locked");
/*      */               }
/*      */               return ir.a().a(is.c, iz.a(Blocks.REPEATER, var3.toString()));
/* 1982 */             })).a(c()));
/*      */   }
/*      */ 
/*      */   
/*      */   private void W() {
/* 1987 */     a(Items.aP);
/*      */     
/* 1989 */     this.a.accept(
/* 1990 */         io.a(Blocks.SEA_PICKLE)
/* 1991 */         .a(
/* 1992 */           ip.<Integer, Boolean>a(BlockProperties.ay, BlockProperties.C)
/* 1993 */           .a(Integer.valueOf(1), Boolean.valueOf(false), Arrays.asList(a(iw.a("dead_sea_pickle"))))
/* 1994 */           .a(Integer.valueOf(2), Boolean.valueOf(false), Arrays.asList(a(iw.a("two_dead_sea_pickles"))))
/* 1995 */           .a(Integer.valueOf(3), Boolean.valueOf(false), Arrays.asList(a(iw.a("three_dead_sea_pickles"))))
/* 1996 */           .a(Integer.valueOf(4), Boolean.valueOf(false), Arrays.asList(a(iw.a("four_dead_sea_pickles"))))
/*      */           
/* 1998 */           .a(Integer.valueOf(1), Boolean.valueOf(true), Arrays.asList(a(iw.a("sea_pickle"))))
/* 1999 */           .a(Integer.valueOf(2), Boolean.valueOf(true), Arrays.asList(a(iw.a("two_sea_pickles"))))
/* 2000 */           .a(Integer.valueOf(3), Boolean.valueOf(true), Arrays.asList(a(iw.a("three_sea_pickles"))))
/* 2001 */           .a(Integer.valueOf(4), Boolean.valueOf(true), Arrays.asList(a(iw.a("four_sea_pickles"))))));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void X() {
/* 2008 */     iz var0 = iz.a(Blocks.SNOW);
/* 2009 */     MinecraftKey var1 = iy.c.a(Blocks.SNOW_BLOCK, var0, this.b);
/*      */     
/* 2011 */     this.a.accept(
/* 2012 */         io.a(Blocks.SNOW)
/* 2013 */         .a(
/* 2014 */           ip.<Comparable>a(BlockProperties.aq)
/* 2015 */           .a(var1 -> ir.a().a(is.c, (var1.intValue() < 8) ? iw.a(Blocks.SNOW, "_height" + (var1.intValue() * 2)) : var0))));
/*      */ 
/*      */ 
/*      */     
/* 2019 */     c(Blocks.SNOW, iw.a(Blocks.SNOW, "_height2"));
/* 2020 */     this.a.accept(e(Blocks.SNOW_BLOCK, var1));
/*      */   }
/*      */   
/*      */   private void Y() {
/* 2024 */     this.a.accept(
/* 2025 */         io.a(Blocks.STONECUTTER, ir.a().a(is.c, iw.a(Blocks.STONECUTTER)))
/* 2026 */         .a(b()));
/*      */   }
/*      */ 
/*      */   
/*      */   private void Z() {
/* 2031 */     MinecraftKey var0 = jb.a.a(Blocks.STRUCTURE_BLOCK, this.b);
/* 2032 */     c(Blocks.STRUCTURE_BLOCK, var0);
/*      */     
/* 2034 */     this.a.accept(
/* 2035 */         io.a(Blocks.STRUCTURE_BLOCK)
/* 2036 */         .a(
/* 2037 */           ip.<BlockPropertyStructureMode>a(BlockProperties.aM)
/* 2038 */           .a(var0 -> ir.a().a(is.c, a(Blocks.STRUCTURE_BLOCK, "_" + var0.getName(), iy.c, iz::b)))));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void aa() {
/* 2045 */     a(Items.SWEET_BERRIES);
/* 2046 */     this.a.accept(
/* 2047 */         io.a(Blocks.SWEET_BERRY_BUSH)
/* 2048 */         .a(
/* 2049 */           ip.<Comparable>a(BlockProperties.ag)
/* 2050 */           .a(var0 -> ir.a().a(is.c, a(Blocks.SWEET_BERRY_BUSH, "_stage" + var0, iy.S, iz::c)))));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void ab() {
/* 2058 */     a(Items.STRING);
/* 2059 */     this.a.accept(
/* 2060 */         io.a(Blocks.TRIPWIRE)
/* 2061 */         .a(
/* 2062 */           ip.<Boolean, Boolean, Boolean, Boolean, Boolean>a(BlockProperties.a, BlockProperties.J, BlockProperties.I, BlockProperties.K, BlockProperties.L)
/*      */           
/* 2064 */           .a(Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), ir.a().a(is.c, iw.a(Blocks.TRIPWIRE, "_ns")))
/*      */ 
/*      */           
/* 2067 */           .a(Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.TRIPWIRE, "_n")).a(is.b, is.a.b))
/* 2068 */           .a(Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false), ir.a().a(is.c, iw.a(Blocks.TRIPWIRE, "_n")))
/* 2069 */           .a(Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(false), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.TRIPWIRE, "_n")).a(is.b, is.a.c))
/* 2070 */           .a(Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.TRIPWIRE, "_n")).a(is.b, is.a.d))
/*      */ 
/*      */           
/* 2073 */           .a(Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false), ir.a().a(is.c, iw.a(Blocks.TRIPWIRE, "_ne")))
/* 2074 */           .a(Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(false), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.TRIPWIRE, "_ne")).a(is.b, is.a.b))
/* 2075 */           .a(Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.TRIPWIRE, "_ne")).a(is.b, is.a.c))
/* 2076 */           .a(Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.TRIPWIRE, "_ne")).a(is.b, is.a.d))
/* 2077 */           .a(Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(false), ir.a().a(is.c, iw.a(Blocks.TRIPWIRE, "_ns")))
/* 2078 */           .a(Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.TRIPWIRE, "_ns")).a(is.b, is.a.b))
/*      */ 
/*      */           
/* 2081 */           .a(Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(false), ir.a().a(is.c, iw.a(Blocks.TRIPWIRE, "_nse")))
/* 2082 */           .a(Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.TRIPWIRE, "_nse")).a(is.b, is.a.b))
/* 2083 */           .a(Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.TRIPWIRE, "_nse")).a(is.b, is.a.c))
/* 2084 */           .a(Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.TRIPWIRE, "_nse")).a(is.b, is.a.d))
/*      */ 
/*      */           
/* 2087 */           .a(Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true), ir.a().a(is.c, iw.a(Blocks.TRIPWIRE, "_nsew")))
/*      */ 
/*      */           
/* 2090 */           .a(Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), ir.a().a(is.c, iw.a(Blocks.TRIPWIRE, "_attached_ns")))
/*      */ 
/*      */           
/* 2093 */           .a(Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false), ir.a().a(is.c, iw.a(Blocks.TRIPWIRE, "_attached_n")))
/* 2094 */           .a(Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(false), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.TRIPWIRE, "_attached_n")).a(is.b, is.a.c))
/* 2095 */           .a(Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.TRIPWIRE, "_attached_n")).a(is.b, is.a.b))
/* 2096 */           .a(Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.TRIPWIRE, "_attached_n")).a(is.b, is.a.d))
/*      */ 
/*      */           
/* 2099 */           .a(Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false), ir.a().a(is.c, iw.a(Blocks.TRIPWIRE, "_attached_ne")))
/* 2100 */           .a(Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(false), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.TRIPWIRE, "_attached_ne")).a(is.b, is.a.b))
/* 2101 */           .a(Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.TRIPWIRE, "_attached_ne")).a(is.b, is.a.c))
/* 2102 */           .a(Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.TRIPWIRE, "_attached_ne")).a(is.b, is.a.d))
/* 2103 */           .a(Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(false), ir.a().a(is.c, iw.a(Blocks.TRIPWIRE, "_attached_ns")))
/* 2104 */           .a(Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.TRIPWIRE, "_attached_ns")).a(is.b, is.a.b))
/*      */ 
/*      */           
/* 2107 */           .a(Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(false), ir.a().a(is.c, iw.a(Blocks.TRIPWIRE, "_attached_nse")))
/* 2108 */           .a(Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.TRIPWIRE, "_attached_nse")).a(is.b, is.a.b))
/* 2109 */           .a(Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.TRIPWIRE, "_attached_nse")).a(is.b, is.a.c))
/* 2110 */           .a(Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.TRIPWIRE, "_attached_nse")).a(is.b, is.a.d))
/*      */ 
/*      */           
/* 2113 */           .a(Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true), ir.a().a(is.c, iw.a(Blocks.TRIPWIRE, "_attached_nsew")))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void ac() {
/* 2119 */     b(Blocks.TRIPWIRE_HOOK);
/* 2120 */     this.a.accept(
/* 2121 */         io.a(Blocks.TRIPWIRE_HOOK)
/* 2122 */         .a(
/* 2123 */           ip.<Comparable, Comparable>a(BlockProperties.a, BlockProperties.w)
/* 2124 */           .a((var0, var1) -> ir.a().a(is.c, iz.a(Blocks.TRIPWIRE_HOOK, (var0.booleanValue() ? "_attached" : "") + (var1.booleanValue() ? "_on" : "")))))
/*      */         
/* 2126 */         .a(b()));
/*      */   }
/*      */ 
/*      */   
/*      */   private MinecraftKey a(int var0, String var1, iz var2) {
/* 2131 */     switch (var0) {
/*      */       case 1:
/* 2133 */         return iy.aF.a(iw.a(var1 + "turtle_egg"), var2, this.b);
/*      */       case 2:
/* 2135 */         return iy.aG.a(iw.a("two_" + var1 + "turtle_eggs"), var2, this.b);
/*      */       case 3:
/* 2137 */         return iy.aH.a(iw.a("three_" + var1 + "turtle_eggs"), var2, this.b);
/*      */       case 4:
/* 2139 */         return iy.aI.a(iw.a("four_" + var1 + "turtle_eggs"), var2, this.b);
/*      */     } 
/* 2141 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */   
/*      */   private MinecraftKey a(Integer var0, Integer var1) {
/* 2146 */     switch (var1.intValue()) {
/*      */       case 0:
/* 2148 */         return a(var0.intValue(), "", iz.b(iz.C(Blocks.TURTLE_EGG)));
/*      */       case 1:
/* 2150 */         return a(var0.intValue(), "slightly_cracked_", iz.b(iz.a(Blocks.TURTLE_EGG, "_slightly_cracked")));
/*      */       case 2:
/* 2152 */         return a(var0.intValue(), "very_cracked_", iz.b(iz.a(Blocks.TURTLE_EGG, "_very_cracked")));
/*      */     } 
/* 2154 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */   
/*      */   private void ad() {
/* 2159 */     a(Items.iC);
/* 2160 */     this.a.accept(
/* 2161 */         io.a(Blocks.TURTLE_EGG)
/* 2162 */         .a(
/* 2163 */           ip.<Comparable, Comparable>a(BlockProperties.ao, BlockProperties.ap)
/* 2164 */           .b((var0, var1) -> Arrays.asList(a(a(var0, var1))))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void ae() {
/* 2170 */     b(Blocks.VINE);
/* 2171 */     this.a.accept(
/* 2172 */         io.a(Blocks.VINE)
/* 2173 */         .a(
/* 2174 */           ip.<Boolean, Boolean, Boolean, Boolean, Boolean>a(BlockProperties.J, BlockProperties.I, BlockProperties.K, BlockProperties.G, BlockProperties.L)
/* 2175 */           .a(Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), ir.a().a(is.c, iw.a(Blocks.VINE, "_1")))
/*      */           
/* 2177 */           .a(Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false), ir.a().a(is.c, iw.a(Blocks.VINE, "_1")))
/* 2178 */           .a(Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.VINE, "_1")).a(is.b, is.a.b))
/* 2179 */           .a(Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.VINE, "_1")).a(is.b, is.a.c))
/* 2180 */           .a(Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.VINE, "_1")).a(is.b, is.a.d))
/*      */           
/* 2182 */           .a(Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), ir.a().a(is.c, iw.a(Blocks.VINE, "_2")))
/* 2183 */           .a(Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.VINE, "_2")).a(is.b, is.a.b))
/* 2184 */           .a(Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.VINE, "_2")).a(is.b, is.a.c))
/* 2185 */           .a(Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.VINE, "_2")).a(is.b, is.a.d))
/* 2186 */           .a(Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true), ir.a().a(is.c, iw.a(Blocks.VINE, "_2_opposite")))
/* 2187 */           .a(Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.VINE, "_2_opposite")).a(is.b, is.a.b))
/*      */           
/* 2189 */           .a(Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false), ir.a().a(is.c, iw.a(Blocks.VINE, "_3")))
/* 2190 */           .a(Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.VINE, "_3")).a(is.b, is.a.b))
/* 2191 */           .a(Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.VINE, "_3")).a(is.b, is.a.c))
/* 2192 */           .a(Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.VINE, "_3")).a(is.b, is.a.d))
/*      */           
/* 2194 */           .a(Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(true), ir.a().a(is.c, iw.a(Blocks.VINE, "_4")))
/*      */           
/* 2196 */           .a(Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(false), ir.a().a(is.c, iw.a(Blocks.VINE, "_u")))
/*      */           
/* 2198 */           .a(Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(false), ir.a().a(is.c, iw.a(Blocks.VINE, "_1u")))
/* 2199 */           .a(Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.VINE, "_1u")).a(is.b, is.a.b))
/* 2200 */           .a(Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(false), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.VINE, "_1u")).a(is.b, is.a.c))
/* 2201 */           .a(Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(false), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.VINE, "_1u")).a(is.b, is.a.d))
/*      */           
/* 2203 */           .a(Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(false), ir.a().a(is.c, iw.a(Blocks.VINE, "_2u")))
/* 2204 */           .a(Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(false), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.VINE, "_2u")).a(is.b, is.a.b))
/* 2205 */           .a(Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.VINE, "_2u")).a(is.b, is.a.c))
/* 2206 */           .a(Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.VINE, "_2u")).a(is.b, is.a.d))
/* 2207 */           .a(Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(true), ir.a().a(is.c, iw.a(Blocks.VINE, "_2u_opposite")))
/* 2208 */           .a(Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(false), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.VINE, "_2u_opposite")).a(is.b, is.a.b))
/*      */           
/* 2210 */           .a(Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(false), ir.a().a(is.c, iw.a(Blocks.VINE, "_3u")))
/* 2211 */           .a(Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.VINE, "_3u")).a(is.b, is.a.b))
/* 2212 */           .a(Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.VINE, "_3u")).a(is.b, is.a.c))
/* 2213 */           .a(Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(true), Boolean.valueOf(true), ir.a().<MinecraftKey>a(is.c, iw.a(Blocks.VINE, "_3u")).a(is.b, is.a.d))
/*      */           
/* 2215 */           .a(Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true), ir.a().a(is.c, iw.a(Blocks.VINE, "_4u")))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void af() {
/* 2221 */     this.a.accept(e(Blocks.MAGMA_BLOCK, iy.c.a(Blocks.MAGMA_BLOCK, iz.b(iw.a("magma")), this.b)));
/*      */   }
/*      */   
/*      */   private void y(Block var0) {
/* 2225 */     c(var0, jb.l);
/* 2226 */     iy.aN.a(iw.a(var0.getItem()), iz.q(var0), this.b);
/*      */   }
/*      */   
/*      */   private void b(Block var0, Block var1, c var2) {
/* 2230 */     b(var0, var2);
/* 2231 */     b(var1, var2);
/*      */   }
/*      */   
/*      */   private void k(Block var0, Block var1) {
/* 2235 */     iy.aO.a(iw.a(var0.getItem()), iz.q(var1), this.b);
/*      */   }
/*      */   
/*      */   private void ag() {
/* 2239 */     MinecraftKey var0 = iw.a(Blocks.STONE);
/* 2240 */     MinecraftKey var1 = iw.a(Blocks.STONE, "_mirrored");
/* 2241 */     this.a.accept(e(Blocks.INFESTED_STONE, var0, var1));
/* 2242 */     c(Blocks.INFESTED_STONE, var0);
/*      */   }
/*      */   
/*      */   private void l(Block var0, Block var1) {
/* 2246 */     a(var0, c.b);
/* 2247 */     iz var2 = iz.d(iz.a(var0, "_pot"));
/* 2248 */     MinecraftKey var3 = c.b.b().a(var1, var2, this.b);
/* 2249 */     this.a.accept(e(var1, var3));
/*      */   }
/*      */   
/*      */   private void ah() {
/* 2253 */     MinecraftKey var0 = iz.a(Blocks.RESPAWN_ANCHOR, "_bottom");
/* 2254 */     MinecraftKey var1 = iz.a(Blocks.RESPAWN_ANCHOR, "_top_off");
/* 2255 */     MinecraftKey var2 = iz.a(Blocks.RESPAWN_ANCHOR, "_top");
/* 2256 */     MinecraftKey[] var3 = new MinecraftKey[5];
/* 2257 */     for (int var4 = 0; var4 < 5; var4++) {
/*      */ 
/*      */ 
/*      */       
/* 2261 */       iz var5 = (new iz()).a(ja.e, var0).a(ja.f, (var4 == 0) ? var1 : var2).a(ja.i, iz.a(Blocks.RESPAWN_ANCHOR, "_side" + var4));
/* 2262 */       var3[var4] = iy.h.a(Blocks.RESPAWN_ANCHOR, "_" + var4, var5, this.b);
/*      */     } 
/*      */     
/* 2265 */     this.a.accept(
/* 2266 */         io.a(Blocks.RESPAWN_ANCHOR)
/* 2267 */         .a(
/* 2268 */           ip.<Comparable>a(BlockProperties.aC)
/* 2269 */           .a(var1 -> ir.a().a(is.c, var0[var1.intValue()]))));
/*      */ 
/*      */     
/* 2272 */     a(Items.rN, var3[0]);
/*      */   }
/*      */   
/*      */   private ir a(BlockPropertyJigsawOrientation var0, ir var1) {
/* 2276 */     switch (null.a[var0.ordinal()]) {
/*      */       case 1:
/* 2278 */         return var1.a(is.a, is.a.b);
/*      */       case 2:
/* 2280 */         return var1.<is.a>a(is.a, is.a.b).a(is.b, is.a.c);
/*      */       case 3:
/* 2282 */         return var1.<is.a>a(is.a, is.a.b).a(is.b, is.a.d);
/*      */       case 4:
/* 2284 */         return var1.<is.a>a(is.a, is.a.b).a(is.b, is.a.b);
/*      */       case 5:
/* 2286 */         return var1.<is.a>a(is.a, is.a.d).a(is.b, is.a.c);
/*      */       case 6:
/* 2288 */         return var1.a(is.a, is.a.d);
/*      */       case 7:
/* 2290 */         return var1.<is.a>a(is.a, is.a.d).a(is.b, is.a.b);
/*      */       case 8:
/* 2292 */         return var1.<is.a>a(is.a, is.a.d).a(is.b, is.a.d);
/*      */       
/*      */       case 9:
/* 2295 */         return var1;
/*      */       case 10:
/* 2297 */         return var1.a(is.b, is.a.c);
/*      */       case 11:
/* 2299 */         return var1.a(is.b, is.a.d);
/*      */       case 12:
/* 2301 */         return var1.a(is.b, is.a.b);
/*      */     } 
/* 2303 */     throw new UnsupportedOperationException("Rotation " + var0 + " can't be expressed with existing x and y values");
/*      */   }
/*      */ 
/*      */   
/*      */   private void ai() {
/* 2308 */     MinecraftKey var0 = iz.a(Blocks.JIGSAW, "_top");
/* 2309 */     MinecraftKey var1 = iz.a(Blocks.JIGSAW, "_bottom");
/* 2310 */     MinecraftKey var2 = iz.a(Blocks.JIGSAW, "_side");
/* 2311 */     MinecraftKey var3 = iz.a(Blocks.JIGSAW, "_lock");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2322 */     iz var4 = (new iz()).a(ja.o, var2).a(ja.m, var2).a(ja.l, var2).a(ja.c, var0).a(ja.j, var0).a(ja.k, var1).a(ja.n, var3);
/*      */     
/* 2324 */     MinecraftKey var5 = iy.b.a(Blocks.JIGSAW, var4, this.b);
/* 2325 */     this.a.accept(
/* 2326 */         io.a(Blocks.JIGSAW, ir.a().a(is.c, var5))
/* 2327 */         .a(
/* 2328 */           ip.<BlockPropertyJigsawOrientation>a(BlockProperties.P)
/* 2329 */           .a(var0 -> a(var0, ir.a()))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void a() {
/* 2335 */     k(Blocks.AIR);
/* 2336 */     a(Blocks.CAVE_AIR, Blocks.AIR);
/* 2337 */     a(Blocks.VOID_AIR, Blocks.AIR);
/* 2338 */     k(Blocks.BEACON);
/* 2339 */     k(Blocks.CACTUS);
/* 2340 */     a(Blocks.BUBBLE_COLUMN, Blocks.WATER);
/* 2341 */     k(Blocks.DRAGON_EGG);
/* 2342 */     k(Blocks.DRIED_KELP_BLOCK);
/* 2343 */     k(Blocks.ENCHANTING_TABLE);
/* 2344 */     k(Blocks.FLOWER_POT);
/* 2345 */     a(Items.oX);
/* 2346 */     k(Blocks.HONEY_BLOCK);
/* 2347 */     k(Blocks.WATER);
/* 2348 */     k(Blocks.LAVA);
/* 2349 */     k(Blocks.SLIME_BLOCK);
/* 2350 */     a(Items.dO);
/*      */     
/* 2352 */     k(Blocks.POTTED_BAMBOO);
/* 2353 */     k(Blocks.POTTED_CACTUS);
/*      */     
/* 2355 */     a(Blocks.BARRIER, Items.fJ);
/* 2356 */     a(Items.fJ);
/* 2357 */     a(Blocks.STRUCTURE_VOID, Items.hn);
/* 2358 */     a(Items.hn);
/* 2359 */     h(Blocks.MOVING_PISTON, iz.a(Blocks.PISTON, "_side"));
/*      */     
/* 2361 */     c(Blocks.COAL_ORE, jb.a);
/* 2362 */     c(Blocks.COAL_BLOCK, jb.a);
/* 2363 */     c(Blocks.DIAMOND_ORE, jb.a);
/* 2364 */     c(Blocks.DIAMOND_BLOCK, jb.a);
/* 2365 */     c(Blocks.EMERALD_ORE, jb.a);
/* 2366 */     c(Blocks.EMERALD_BLOCK, jb.a);
/* 2367 */     c(Blocks.GOLD_ORE, jb.a);
/* 2368 */     c(Blocks.NETHER_GOLD_ORE, jb.a);
/* 2369 */     c(Blocks.GOLD_BLOCK, jb.a);
/* 2370 */     c(Blocks.IRON_ORE, jb.a);
/* 2371 */     c(Blocks.IRON_BLOCK, jb.a);
/* 2372 */     c(Blocks.ANCIENT_DEBRIS, jb.c);
/* 2373 */     c(Blocks.NETHERITE_BLOCK, jb.a);
/* 2374 */     c(Blocks.LAPIS_ORE, jb.a);
/* 2375 */     c(Blocks.LAPIS_BLOCK, jb.a);
/* 2376 */     c(Blocks.NETHER_QUARTZ_ORE, jb.a);
/* 2377 */     c(Blocks.REDSTONE_ORE, jb.a);
/* 2378 */     c(Blocks.REDSTONE_BLOCK, jb.a);
/* 2379 */     c(Blocks.GILDED_BLACKSTONE, jb.a);
/*      */     
/* 2381 */     c(Blocks.BLUE_ICE, jb.a);
/* 2382 */     c(Blocks.CHISELED_NETHER_BRICKS, jb.a);
/* 2383 */     c(Blocks.CLAY, jb.a);
/* 2384 */     c(Blocks.COARSE_DIRT, jb.a);
/* 2385 */     c(Blocks.CRACKED_NETHER_BRICKS, jb.a);
/* 2386 */     c(Blocks.CRACKED_STONE_BRICKS, jb.a);
/* 2387 */     c(Blocks.CRYING_OBSIDIAN, jb.a);
/* 2388 */     c(Blocks.END_STONE, jb.a);
/* 2389 */     c(Blocks.GLOWSTONE, jb.a);
/* 2390 */     c(Blocks.GRAVEL, jb.a);
/* 2391 */     c(Blocks.HONEYCOMB_BLOCK, jb.a);
/* 2392 */     c(Blocks.ICE, jb.a);
/* 2393 */     c(Blocks.JUKEBOX, jb.f);
/* 2394 */     c(Blocks.LODESTONE, jb.c);
/* 2395 */     c(Blocks.MELON, jb.c);
/* 2396 */     c(Blocks.NETHER_WART_BLOCK, jb.a);
/* 2397 */     c(Blocks.NOTE_BLOCK, jb.a);
/* 2398 */     c(Blocks.PACKED_ICE, jb.a);
/* 2399 */     c(Blocks.OBSIDIAN, jb.a);
/* 2400 */     c(Blocks.QUARTZ_BRICKS, jb.a);
/* 2401 */     c(Blocks.SEA_LANTERN, jb.a);
/* 2402 */     c(Blocks.SHROOMLIGHT, jb.a);
/* 2403 */     c(Blocks.SOUL_SAND, jb.a);
/* 2404 */     c(Blocks.SOUL_SOIL, jb.a);
/* 2405 */     c(Blocks.SPAWNER, jb.a);
/* 2406 */     c(Blocks.SPONGE, jb.a);
/* 2407 */     c(Blocks.SEAGRASS, jb.q);
/* 2408 */     a(Items.aO);
/* 2409 */     c(Blocks.TNT, jb.e);
/* 2410 */     c(Blocks.TARGET, jb.c);
/* 2411 */     c(Blocks.WARPED_WART_BLOCK, jb.a);
/* 2412 */     c(Blocks.WET_SPONGE, jb.a);
/* 2413 */     c(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS, jb.a);
/* 2414 */     c(Blocks.CHISELED_QUARTZ_BLOCK, jb.c.a(var0 -> var0.a(ja.i, iz.C(Blocks.CHISELED_QUARTZ_BLOCK))));
/* 2415 */     c(Blocks.CHISELED_STONE_BRICKS, jb.a);
/* 2416 */     g(Blocks.CHISELED_SANDSTONE, Blocks.SANDSTONE);
/* 2417 */     g(Blocks.CHISELED_RED_SANDSTONE, Blocks.RED_SANDSTONE);
/* 2418 */     c(Blocks.CHISELED_POLISHED_BLACKSTONE, jb.a);
/*      */     
/* 2420 */     h(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE, Blocks.GOLD_BLOCK);
/* 2421 */     h(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE, Blocks.IRON_BLOCK);
/*      */     
/* 2423 */     n();
/* 2424 */     r();
/* 2425 */     s();
/* 2426 */     a(new Block[] { Blocks.CAMPFIRE, Blocks.SOUL_CAMPFIRE });
/* 2427 */     t();
/* 2428 */     w();
/* 2429 */     x();
/* 2430 */     z();
/* 2431 */     A();
/* 2432 */     B();
/* 2433 */     y();
/* 2434 */     s(Blocks.END_ROD);
/* 2435 */     C();
/* 2436 */     D();
/* 2437 */     E();
/* 2438 */     F();
/* 2439 */     G();
/* 2440 */     H();
/* 2441 */     I();
/* 2442 */     m();
/* 2443 */     J();
/* 2444 */     K();
/* 2445 */     L();
/* 2446 */     M();
/* 2447 */     N();
/* 2448 */     O();
/* 2449 */     P();
/* 2450 */     Q();
/* 2451 */     R();
/* 2452 */     S();
/* 2453 */     U();
/* 2454 */     T();
/* 2455 */     V();
/* 2456 */     W();
/* 2457 */     u();
/* 2458 */     X();
/* 2459 */     Y();
/* 2460 */     Z();
/* 2461 */     aa();
/* 2462 */     ab();
/* 2463 */     ac();
/* 2464 */     ad();
/* 2465 */     ae();
/* 2466 */     af();
/* 2467 */     ai();
/*      */     
/* 2469 */     x(Blocks.LADDER);
/* 2470 */     b(Blocks.LADDER);
/* 2471 */     x(Blocks.LECTERN);
/*      */     
/* 2473 */     j(Blocks.TORCH, Blocks.WALL_TORCH);
/* 2474 */     j(Blocks.SOUL_TORCH, Blocks.SOUL_WALL_TORCH);
/*      */     
/* 2476 */     a(Blocks.CRAFTING_TABLE, Blocks.OAK_PLANKS, iz::c);
/* 2477 */     a(Blocks.FLETCHING_TABLE, Blocks.BIRCH_PLANKS, iz::d);
/*      */     
/* 2479 */     r(Blocks.CRIMSON_NYLIUM);
/* 2480 */     r(Blocks.WARPED_NYLIUM);
/*      */     
/* 2482 */     q(Blocks.DISPENSER);
/* 2483 */     q(Blocks.DROPPER);
/*      */     
/* 2485 */     w(Blocks.LANTERN);
/* 2486 */     w(Blocks.SOUL_LANTERN);
/*      */     
/* 2488 */     g(Blocks.CHAIN, iw.a(Blocks.CHAIN));
/* 2489 */     a(Blocks.BASALT, jb.c);
/* 2490 */     a(Blocks.cP, jb.c);
/* 2491 */     a(Blocks.BONE_BLOCK, jb.c);
/* 2492 */     d(Blocks.DIRT);
/* 2493 */     d(Blocks.SAND);
/* 2494 */     d(Blocks.RED_SAND);
/* 2495 */     c(Blocks.BEDROCK);
/*      */     
/* 2497 */     a(Blocks.HAY_BLOCK, jb.c, jb.d);
/* 2498 */     a(Blocks.PURPUR_PILLAR, jb.r, jb.s);
/* 2499 */     a(Blocks.QUARTZ_PILLAR, jb.r, jb.s);
/*      */     
/* 2501 */     b(Blocks.LOOM, jb.h);
/*      */     
/* 2503 */     v();
/* 2504 */     a(Blocks.BEE_NEST, iz::w);
/* 2505 */     a(Blocks.BEEHIVE, iz::y);
/*      */ 
/*      */     
/* 2508 */     a(Blocks.BEETROOTS, BlockProperties.ag, new int[] { 0, 1, 2, 3 });
/* 2509 */     a(Blocks.CARROTS, BlockProperties.ai, new int[] { 0, 0, 1, 1, 2, 2, 2, 3 });
/* 2510 */     a(Blocks.NETHER_WART, BlockProperties.ag, new int[] { 0, 1, 1, 2 });
/* 2511 */     a(Blocks.POTATOES, BlockProperties.ai, new int[] { 0, 0, 1, 1, 2, 2, 2, 3 });
/* 2512 */     a(Blocks.WHEAT, BlockProperties.ai, new int[] { 0, 1, 2, 3, 4, 5, 6, 7 });
/*      */     
/* 2514 */     a(iw.a("banner"), Blocks.OAK_PLANKS)
/* 2515 */       .a(iy.aP, new Block[] {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           Blocks.WHITE_BANNER, Blocks.ORANGE_BANNER, Blocks.MAGENTA_BANNER, Blocks.LIGHT_BLUE_BANNER, Blocks.YELLOW_BANNER, Blocks.LIME_BANNER, Blocks.PINK_BANNER, Blocks.GRAY_BANNER, Blocks.LIGHT_GRAY_BANNER, Blocks.CYAN_BANNER,
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           Blocks.PURPLE_BANNER, Blocks.BLUE_BANNER, Blocks.BROWN_BANNER, Blocks.GREEN_BANNER, Blocks.RED_BANNER, Blocks.BLACK_BANNER
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2533 */         }).b(new Block[] { 
/*      */           Blocks.WHITE_WALL_BANNER, Blocks.ORANGE_WALL_BANNER, Blocks.MAGENTA_WALL_BANNER, Blocks.LIGHT_BLUE_WALL_BANNER, Blocks.YELLOW_WALL_BANNER, Blocks.LIME_WALL_BANNER, Blocks.PINK_WALL_BANNER, Blocks.GRAY_WALL_BANNER, Blocks.LIGHT_GRAY_WALL_BANNER, Blocks.CYAN_WALL_BANNER, 
/*      */           Blocks.PURPLE_WALL_BANNER, Blocks.BLUE_WALL_BANNER, Blocks.BROWN_WALL_BANNER, Blocks.GREEN_WALL_BANNER, Blocks.RED_WALL_BANNER, Blocks.BLACK_WALL_BANNER });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2552 */     a(iw.a("bed"), Blocks.OAK_PLANKS)
/* 2553 */       .b(new Block[] { 
/*      */           Blocks.WHITE_BED, Blocks.ORANGE_BED, Blocks.MAGENTA_BED, Blocks.LIGHT_BLUE_BED, Blocks.YELLOW_BED, Blocks.LIME_BED, Blocks.PINK_BED, Blocks.GRAY_BED, Blocks.LIGHT_GRAY_BED, Blocks.CYAN_BED, 
/*      */           Blocks.PURPLE_BED, Blocks.BLUE_BED, Blocks.BROWN_BED, Blocks.GREEN_BED, Blocks.RED_BED, Blocks.BLACK_BED });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2572 */     k(Blocks.WHITE_BED, Blocks.WHITE_WOOL);
/* 2573 */     k(Blocks.ORANGE_BED, Blocks.ORANGE_WOOL);
/* 2574 */     k(Blocks.MAGENTA_BED, Blocks.MAGENTA_WOOL);
/* 2575 */     k(Blocks.LIGHT_BLUE_BED, Blocks.LIGHT_BLUE_WOOL);
/* 2576 */     k(Blocks.YELLOW_BED, Blocks.YELLOW_WOOL);
/* 2577 */     k(Blocks.LIME_BED, Blocks.LIME_WOOL);
/* 2578 */     k(Blocks.PINK_BED, Blocks.PINK_WOOL);
/* 2579 */     k(Blocks.GRAY_BED, Blocks.GRAY_WOOL);
/* 2580 */     k(Blocks.LIGHT_GRAY_BED, Blocks.LIGHT_GRAY_WOOL);
/* 2581 */     k(Blocks.CYAN_BED, Blocks.CYAN_WOOL);
/* 2582 */     k(Blocks.PURPLE_BED, Blocks.PURPLE_WOOL);
/* 2583 */     k(Blocks.BLUE_BED, Blocks.BLUE_WOOL);
/* 2584 */     k(Blocks.BROWN_BED, Blocks.BROWN_WOOL);
/* 2585 */     k(Blocks.GREEN_BED, Blocks.GREEN_WOOL);
/* 2586 */     k(Blocks.RED_BED, Blocks.RED_WOOL);
/* 2587 */     k(Blocks.BLACK_BED, Blocks.BLACK_WOOL);
/*      */     
/* 2589 */     a(iw.a("skull"), Blocks.SOUL_SAND)
/* 2590 */       .a(iy.aQ, new Block[] {
/*      */ 
/*      */           
/*      */           Blocks.CREEPER_HEAD, Blocks.PLAYER_HEAD, Blocks.ZOMBIE_HEAD, Blocks.SKELETON_SKULL, Blocks.WITHER_SKELETON_SKULL
/*      */ 
/*      */ 
/*      */         
/* 2597 */         }).a(new Block[] {
/*      */           
/*      */           Blocks.DRAGON_HEAD
/* 2600 */         }).b(new Block[] { Blocks.CREEPER_WALL_HEAD, Blocks.DRAGON_WALL_HEAD, Blocks.PLAYER_WALL_HEAD, Blocks.ZOMBIE_WALL_HEAD, Blocks.SKELETON_WALL_SKULL, Blocks.WITHER_SKELETON_WALL_SKULL });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2609 */     y(Blocks.SHULKER_BOX);
/* 2610 */     y(Blocks.WHITE_SHULKER_BOX);
/* 2611 */     y(Blocks.ORANGE_SHULKER_BOX);
/* 2612 */     y(Blocks.MAGENTA_SHULKER_BOX);
/* 2613 */     y(Blocks.LIGHT_BLUE_SHULKER_BOX);
/* 2614 */     y(Blocks.YELLOW_SHULKER_BOX);
/* 2615 */     y(Blocks.LIME_SHULKER_BOX);
/* 2616 */     y(Blocks.PINK_SHULKER_BOX);
/* 2617 */     y(Blocks.GRAY_SHULKER_BOX);
/* 2618 */     y(Blocks.LIGHT_GRAY_SHULKER_BOX);
/* 2619 */     y(Blocks.CYAN_SHULKER_BOX);
/* 2620 */     y(Blocks.PURPLE_SHULKER_BOX);
/* 2621 */     y(Blocks.BLUE_SHULKER_BOX);
/* 2622 */     y(Blocks.BROWN_SHULKER_BOX);
/* 2623 */     y(Blocks.GREEN_SHULKER_BOX);
/* 2624 */     y(Blocks.RED_SHULKER_BOX);
/* 2625 */     y(Blocks.BLACK_SHULKER_BOX);
/*      */     
/* 2627 */     c(Blocks.CONDUIT, jb.l);
/* 2628 */     a(Blocks.CONDUIT);
/*      */     
/* 2630 */     a(iw.a("chest"), Blocks.OAK_PLANKS)
/* 2631 */       .b(new Block[] { Blocks.CHEST, Blocks.TRAPPED_CHEST });
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2636 */     a(iw.a("ender_chest"), Blocks.OBSIDIAN)
/* 2637 */       .b(new Block[] { Blocks.ENDER_CHEST });
/*      */ 
/*      */ 
/*      */     
/* 2641 */     d(Blocks.END_PORTAL, Blocks.OBSIDIAN)
/* 2642 */       .a(new Block[] { Blocks.END_PORTAL, Blocks.END_GATEWAY });
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2647 */     e(Blocks.WHITE_CONCRETE);
/* 2648 */     e(Blocks.ORANGE_CONCRETE);
/* 2649 */     e(Blocks.MAGENTA_CONCRETE);
/* 2650 */     e(Blocks.LIGHT_BLUE_CONCRETE);
/* 2651 */     e(Blocks.YELLOW_CONCRETE);
/* 2652 */     e(Blocks.LIME_CONCRETE);
/* 2653 */     e(Blocks.PINK_CONCRETE);
/* 2654 */     e(Blocks.GRAY_CONCRETE);
/* 2655 */     e(Blocks.LIGHT_GRAY_CONCRETE);
/* 2656 */     e(Blocks.CYAN_CONCRETE);
/* 2657 */     e(Blocks.PURPLE_CONCRETE);
/* 2658 */     e(Blocks.BLUE_CONCRETE);
/* 2659 */     e(Blocks.BROWN_CONCRETE);
/* 2660 */     e(Blocks.GREEN_CONCRETE);
/* 2661 */     e(Blocks.RED_CONCRETE);
/* 2662 */     e(Blocks.BLACK_CONCRETE);
/*      */     
/* 2664 */     a(jb.a, new Block[] { Blocks.WHITE_CONCRETE_POWDER, Blocks.ORANGE_CONCRETE_POWDER, Blocks.MAGENTA_CONCRETE_POWDER, Blocks.LIGHT_BLUE_CONCRETE_POWDER, Blocks.YELLOW_CONCRETE_POWDER, Blocks.LIME_CONCRETE_POWDER, Blocks.PINK_CONCRETE_POWDER, Blocks.GRAY_CONCRETE_POWDER, Blocks.LIGHT_GRAY_CONCRETE_POWDER, Blocks.CYAN_CONCRETE_POWDER, Blocks.PURPLE_CONCRETE_POWDER, Blocks.BLUE_CONCRETE_POWDER, Blocks.BROWN_CONCRETE_POWDER, Blocks.GREEN_CONCRETE_POWDER, Blocks.RED_CONCRETE_POWDER, Blocks.BLACK_CONCRETE_POWDER });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2683 */     e(Blocks.TERRACOTTA);
/* 2684 */     e(Blocks.WHITE_TERRACOTTA);
/* 2685 */     e(Blocks.ORANGE_TERRACOTTA);
/* 2686 */     e(Blocks.MAGENTA_TERRACOTTA);
/* 2687 */     e(Blocks.LIGHT_BLUE_TERRACOTTA);
/* 2688 */     e(Blocks.YELLOW_TERRACOTTA);
/* 2689 */     e(Blocks.LIME_TERRACOTTA);
/* 2690 */     e(Blocks.PINK_TERRACOTTA);
/* 2691 */     e(Blocks.GRAY_TERRACOTTA);
/* 2692 */     e(Blocks.LIGHT_GRAY_TERRACOTTA);
/* 2693 */     e(Blocks.CYAN_TERRACOTTA);
/* 2694 */     e(Blocks.PURPLE_TERRACOTTA);
/* 2695 */     e(Blocks.BLUE_TERRACOTTA);
/* 2696 */     e(Blocks.BROWN_TERRACOTTA);
/* 2697 */     e(Blocks.GREEN_TERRACOTTA);
/* 2698 */     e(Blocks.RED_TERRACOTTA);
/* 2699 */     e(Blocks.BLACK_TERRACOTTA);
/*      */     
/* 2701 */     f(Blocks.GLASS, Blocks.GLASS_PANE);
/* 2702 */     f(Blocks.WHITE_STAINED_GLASS, Blocks.WHITE_STAINED_GLASS_PANE);
/* 2703 */     f(Blocks.ORANGE_STAINED_GLASS, Blocks.ORANGE_STAINED_GLASS_PANE);
/* 2704 */     f(Blocks.MAGENTA_STAINED_GLASS, Blocks.MAGENTA_STAINED_GLASS_PANE);
/* 2705 */     f(Blocks.LIGHT_BLUE_STAINED_GLASS, Blocks.LIGHT_BLUE_STAINED_GLASS_PANE);
/* 2706 */     f(Blocks.YELLOW_STAINED_GLASS, Blocks.YELLOW_STAINED_GLASS_PANE);
/* 2707 */     f(Blocks.LIME_STAINED_GLASS, Blocks.LIME_STAINED_GLASS_PANE);
/* 2708 */     f(Blocks.PINK_STAINED_GLASS, Blocks.PINK_STAINED_GLASS_PANE);
/* 2709 */     f(Blocks.GRAY_STAINED_GLASS, Blocks.GRAY_STAINED_GLASS_PANE);
/* 2710 */     f(Blocks.LIGHT_GRAY_STAINED_GLASS, Blocks.LIGHT_GRAY_STAINED_GLASS_PANE);
/* 2711 */     f(Blocks.CYAN_STAINED_GLASS, Blocks.CYAN_STAINED_GLASS_PANE);
/* 2712 */     f(Blocks.PURPLE_STAINED_GLASS, Blocks.PURPLE_STAINED_GLASS_PANE);
/* 2713 */     f(Blocks.BLUE_STAINED_GLASS, Blocks.BLUE_STAINED_GLASS_PANE);
/* 2714 */     f(Blocks.BROWN_STAINED_GLASS, Blocks.BROWN_STAINED_GLASS_PANE);
/* 2715 */     f(Blocks.GREEN_STAINED_GLASS, Blocks.GREEN_STAINED_GLASS_PANE);
/* 2716 */     f(Blocks.RED_STAINED_GLASS, Blocks.RED_STAINED_GLASS_PANE);
/* 2717 */     f(Blocks.BLACK_STAINED_GLASS, Blocks.BLACK_STAINED_GLASS_PANE);
/*      */     
/* 2719 */     b(jb.j, new Block[] { Blocks.WHITE_GLAZED_TERRACOTTA, Blocks.ORANGE_GLAZED_TERRACOTTA, Blocks.MAGENTA_GLAZED_TERRACOTTA, Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA, Blocks.YELLOW_GLAZED_TERRACOTTA, Blocks.LIME_GLAZED_TERRACOTTA, Blocks.PINK_GLAZED_TERRACOTTA, Blocks.GRAY_GLAZED_TERRACOTTA, Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA, Blocks.CYAN_GLAZED_TERRACOTTA, Blocks.PURPLE_GLAZED_TERRACOTTA, Blocks.BLUE_GLAZED_TERRACOTTA, Blocks.BROWN_GLAZED_TERRACOTTA, Blocks.GREEN_GLAZED_TERRACOTTA, Blocks.RED_GLAZED_TERRACOTTA, Blocks.BLACK_GLAZED_TERRACOTTA });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2738 */     e(Blocks.WHITE_WOOL, Blocks.WHITE_CARPET);
/* 2739 */     e(Blocks.ORANGE_WOOL, Blocks.ORANGE_CARPET);
/* 2740 */     e(Blocks.MAGENTA_WOOL, Blocks.MAGENTA_CARPET);
/* 2741 */     e(Blocks.LIGHT_BLUE_WOOL, Blocks.LIGHT_BLUE_CARPET);
/* 2742 */     e(Blocks.YELLOW_WOOL, Blocks.YELLOW_CARPET);
/* 2743 */     e(Blocks.LIME_WOOL, Blocks.LIME_CARPET);
/* 2744 */     e(Blocks.PINK_WOOL, Blocks.PINK_CARPET);
/* 2745 */     e(Blocks.GRAY_WOOL, Blocks.GRAY_CARPET);
/* 2746 */     e(Blocks.LIGHT_GRAY_WOOL, Blocks.LIGHT_GRAY_CARPET);
/* 2747 */     e(Blocks.CYAN_WOOL, Blocks.CYAN_CARPET);
/* 2748 */     e(Blocks.PURPLE_WOOL, Blocks.PURPLE_CARPET);
/* 2749 */     e(Blocks.BLUE_WOOL, Blocks.BLUE_CARPET);
/* 2750 */     e(Blocks.BROWN_WOOL, Blocks.BROWN_CARPET);
/* 2751 */     e(Blocks.GREEN_WOOL, Blocks.GREEN_CARPET);
/* 2752 */     e(Blocks.RED_WOOL, Blocks.RED_CARPET);
/* 2753 */     e(Blocks.BLACK_WOOL, Blocks.BLACK_CARPET);
/*      */     
/* 2755 */     a(Blocks.FERN, Blocks.POTTED_FERN, c.a);
/* 2756 */     a(Blocks.DANDELION, Blocks.POTTED_DANDELION, c.b);
/* 2757 */     a(Blocks.POPPY, Blocks.POTTED_POPPY, c.b);
/* 2758 */     a(Blocks.BLUE_ORCHID, Blocks.POTTED_BLUE_ORCHID, c.b);
/* 2759 */     a(Blocks.ALLIUM, Blocks.POTTED_ALLIUM, c.b);
/* 2760 */     a(Blocks.AZURE_BLUET, Blocks.POTTED_AZURE_BLUET, c.b);
/* 2761 */     a(Blocks.RED_TULIP, Blocks.POTTED_RED_TULIP, c.b);
/* 2762 */     a(Blocks.ORANGE_TULIP, Blocks.POTTED_ORANGE_TULIP, c.b);
/* 2763 */     a(Blocks.WHITE_TULIP, Blocks.POTTED_WHITE_TULIP, c.b);
/* 2764 */     a(Blocks.PINK_TULIP, Blocks.POTTED_PINK_TULIP, c.b);
/* 2765 */     a(Blocks.OXEYE_DAISY, Blocks.POTTED_OXEYE_DAISY, c.b);
/* 2766 */     a(Blocks.CORNFLOWER, Blocks.POTTED_CORNFLOWER, c.b);
/* 2767 */     a(Blocks.LILY_OF_THE_VALLEY, Blocks.POTTED_LILY_OF_THE_VALLEY, c.b);
/* 2768 */     a(Blocks.WITHER_ROSE, Blocks.POTTED_WITHER_ROSE, c.b);
/* 2769 */     a(Blocks.RED_MUSHROOM, Blocks.POTTED_RED_MUSHROOM, c.b);
/* 2770 */     a(Blocks.BROWN_MUSHROOM, Blocks.POTTED_BROWN_MUSHROOM, c.b);
/* 2771 */     a(Blocks.DEAD_BUSH, Blocks.POTTED_DEAD_BUSH, c.b);
/*      */     
/* 2773 */     p(Blocks.BROWN_MUSHROOM_BLOCK);
/* 2774 */     p(Blocks.RED_MUSHROOM_BLOCK);
/* 2775 */     p(Blocks.MUSHROOM_STEM);
/*      */     
/* 2777 */     a(Blocks.GRASS, c.a);
/* 2778 */     b(Blocks.SUGAR_CANE, c.a);
/* 2779 */     a(Items.bD);
/* 2780 */     b(Blocks.KELP, Blocks.KELP_PLANT, c.a);
/* 2781 */     a(Items.bE);
/* 2782 */     a(Blocks.KELP_PLANT);
/* 2783 */     b(Blocks.WEEPING_VINES, Blocks.WEEPING_VINES_PLANT, c.b);
/* 2784 */     b(Blocks.TWISTING_VINES, Blocks.TWISTING_VINES_PLANT, c.b);
/* 2785 */     a(Blocks.WEEPING_VINES, "_plant");
/* 2786 */     a(Blocks.WEEPING_VINES_PLANT);
/* 2787 */     a(Blocks.TWISTING_VINES, "_plant");
/* 2788 */     a(Blocks.TWISTING_VINES_PLANT);
/* 2789 */     a(Blocks.BAMBOO_SAPLING, c.a, iz.c(iz.a(Blocks.BAMBOO, "_stage0")));
/* 2790 */     i();
/*      */     
/* 2792 */     a(Blocks.COBWEB, c.b);
/*      */     
/* 2794 */     c(Blocks.LILAC, c.b);
/* 2795 */     c(Blocks.ROSE_BUSH, c.b);
/* 2796 */     c(Blocks.PEONY, c.b);
/* 2797 */     c(Blocks.TALL_GRASS, c.a);
/* 2798 */     c(Blocks.LARGE_FERN, c.a);
/*      */     
/* 2800 */     g();
/* 2801 */     h();
/*      */     
/* 2803 */     a(Blocks.TUBE_CORAL, Blocks.DEAD_TUBE_CORAL, Blocks.TUBE_CORAL_BLOCK, Blocks.DEAD_TUBE_CORAL_BLOCK, Blocks.TUBE_CORAL_FAN, Blocks.DEAD_TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN, Blocks.DEAD_TUBE_CORAL_WALL_FAN);
/* 2804 */     a(Blocks.BRAIN_CORAL, Blocks.DEAD_BRAIN_CORAL, Blocks.BRAIN_CORAL_BLOCK, Blocks.DEAD_BRAIN_CORAL_BLOCK, Blocks.BRAIN_CORAL_FAN, Blocks.DEAD_BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN, Blocks.DEAD_BRAIN_CORAL_WALL_FAN);
/* 2805 */     a(Blocks.BUBBLE_CORAL, Blocks.DEAD_BUBBLE_CORAL, Blocks.BUBBLE_CORAL_BLOCK, Blocks.DEAD_BUBBLE_CORAL_BLOCK, Blocks.BUBBLE_CORAL_FAN, Blocks.DEAD_BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN, Blocks.DEAD_BUBBLE_CORAL_WALL_FAN);
/* 2806 */     a(Blocks.FIRE_CORAL, Blocks.DEAD_FIRE_CORAL, Blocks.FIRE_CORAL_BLOCK, Blocks.DEAD_FIRE_CORAL_BLOCK, Blocks.FIRE_CORAL_FAN, Blocks.DEAD_FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN, Blocks.DEAD_FIRE_CORAL_WALL_FAN);
/* 2807 */     a(Blocks.HORN_CORAL, Blocks.DEAD_HORN_CORAL, Blocks.HORN_CORAL_BLOCK, Blocks.DEAD_HORN_CORAL_BLOCK, Blocks.HORN_CORAL_FAN, Blocks.DEAD_HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN, Blocks.DEAD_HORN_CORAL_WALL_FAN);
/*      */     
/* 2809 */     c(Blocks.MELON_STEM, Blocks.ATTACHED_MELON_STEM);
/* 2810 */     c(Blocks.PUMPKIN_STEM, Blocks.ATTACHED_PUMPKIN_STEM);
/*      */     
/* 2812 */     f(Blocks.ACACIA_PLANKS)
/* 2813 */       .a(Blocks.ACACIA_BUTTON)
/* 2814 */       .c(Blocks.ACACIA_FENCE)
/* 2815 */       .d(Blocks.ACACIA_FENCE_GATE)
/* 2816 */       .e(Blocks.ACACIA_PRESSURE_PLATE)
/* 2817 */       .a(Blocks.ACACIA_SIGN, Blocks.ACACIA_WALL_SIGN)
/* 2818 */       .f(Blocks.ACACIA_SLAB)
/* 2819 */       .g(Blocks.ACACIA_STAIRS);
/* 2820 */     g(Blocks.ACACIA_DOOR);
/* 2821 */     h(Blocks.ACACIA_TRAPDOOR);
/* 2822 */     j(Blocks.ACACIA_LOG).c(Blocks.ACACIA_LOG).a(Blocks.ACACIA_WOOD);
/* 2823 */     j(Blocks.STRIPPED_ACACIA_LOG).c(Blocks.STRIPPED_ACACIA_LOG).a(Blocks.STRIPPED_ACACIA_WOOD);
/* 2824 */     a(Blocks.ACACIA_SAPLING, Blocks.POTTED_ACACIA_SAPLING, c.b);
/* 2825 */     c(Blocks.ACACIA_LEAVES, jb.n);
/*      */     
/* 2827 */     f(Blocks.BIRCH_PLANKS)
/* 2828 */       .a(Blocks.BIRCH_BUTTON)
/* 2829 */       .c(Blocks.BIRCH_FENCE)
/* 2830 */       .d(Blocks.BIRCH_FENCE_GATE)
/* 2831 */       .e(Blocks.BIRCH_PRESSURE_PLATE)
/* 2832 */       .a(Blocks.BIRCH_SIGN, Blocks.BIRCH_WALL_SIGN)
/* 2833 */       .f(Blocks.BIRCH_SLAB)
/* 2834 */       .g(Blocks.BIRCH_STAIRS);
/* 2835 */     g(Blocks.BIRCH_DOOR);
/* 2836 */     h(Blocks.BIRCH_TRAPDOOR);
/* 2837 */     j(Blocks.BIRCH_LOG).c(Blocks.BIRCH_LOG).a(Blocks.BIRCH_WOOD);
/* 2838 */     j(Blocks.STRIPPED_BIRCH_LOG).c(Blocks.STRIPPED_BIRCH_LOG).a(Blocks.STRIPPED_BIRCH_WOOD);
/* 2839 */     a(Blocks.BIRCH_SAPLING, Blocks.POTTED_BIRCH_SAPLING, c.b);
/* 2840 */     c(Blocks.BIRCH_LEAVES, jb.n);
/*      */     
/* 2842 */     f(Blocks.OAK_PLANKS)
/* 2843 */       .a(Blocks.OAK_BUTTON)
/* 2844 */       .c(Blocks.OAK_FENCE)
/* 2845 */       .d(Blocks.OAK_FENCE_GATE)
/* 2846 */       .e(Blocks.OAK_PRESSURE_PLATE)
/* 2847 */       .a(Blocks.OAK_SIGN, Blocks.OAK_WALL_SIGN)
/* 2848 */       .f(Blocks.OAK_SLAB)
/* 2849 */       .f(Blocks.PETRIFIED_OAK_SLAB)
/* 2850 */       .g(Blocks.OAK_STAIRS);
/* 2851 */     g(Blocks.OAK_DOOR);
/* 2852 */     i(Blocks.OAK_TRAPDOOR);
/* 2853 */     j(Blocks.OAK_LOG).c(Blocks.OAK_LOG).a(Blocks.OAK_WOOD);
/* 2854 */     j(Blocks.STRIPPED_OAK_LOG).c(Blocks.STRIPPED_OAK_LOG).a(Blocks.STRIPPED_OAK_WOOD);
/* 2855 */     a(Blocks.OAK_SAPLING, Blocks.POTTED_OAK_SAPLING, c.b);
/* 2856 */     c(Blocks.OAK_LEAVES, jb.n);
/*      */     
/* 2858 */     f(Blocks.SPRUCE_PLANKS)
/* 2859 */       .a(Blocks.SPRUCE_BUTTON)
/* 2860 */       .c(Blocks.SPRUCE_FENCE)
/* 2861 */       .d(Blocks.SPRUCE_FENCE_GATE)
/* 2862 */       .e(Blocks.SPRUCE_PRESSURE_PLATE)
/* 2863 */       .a(Blocks.SPRUCE_SIGN, Blocks.SPRUCE_WALL_SIGN)
/* 2864 */       .f(Blocks.SPRUCE_SLAB)
/* 2865 */       .g(Blocks.SPRUCE_STAIRS);
/* 2866 */     g(Blocks.SPRUCE_DOOR);
/* 2867 */     h(Blocks.SPRUCE_TRAPDOOR);
/* 2868 */     j(Blocks.SPRUCE_LOG).c(Blocks.SPRUCE_LOG).a(Blocks.SPRUCE_WOOD);
/* 2869 */     j(Blocks.STRIPPED_SPRUCE_LOG).c(Blocks.STRIPPED_SPRUCE_LOG).a(Blocks.STRIPPED_SPRUCE_WOOD);
/* 2870 */     a(Blocks.SPRUCE_SAPLING, Blocks.POTTED_SPRUCE_SAPLING, c.b);
/* 2871 */     c(Blocks.SPRUCE_LEAVES, jb.n);
/*      */     
/* 2873 */     f(Blocks.DARK_OAK_PLANKS)
/* 2874 */       .a(Blocks.DARK_OAK_BUTTON)
/* 2875 */       .c(Blocks.DARK_OAK_FENCE)
/* 2876 */       .d(Blocks.DARK_OAK_FENCE_GATE)
/* 2877 */       .e(Blocks.DARK_OAK_PRESSURE_PLATE)
/* 2878 */       .a(Blocks.DARK_OAK_SIGN, Blocks.DARK_OAK_WALL_SIGN)
/* 2879 */       .f(Blocks.DARK_OAK_SLAB)
/* 2880 */       .g(Blocks.DARK_OAK_STAIRS);
/* 2881 */     g(Blocks.DARK_OAK_DOOR);
/* 2882 */     i(Blocks.DARK_OAK_TRAPDOOR);
/* 2883 */     j(Blocks.DARK_OAK_LOG).c(Blocks.DARK_OAK_LOG).a(Blocks.DARK_OAK_WOOD);
/* 2884 */     j(Blocks.STRIPPED_DARK_OAK_LOG).c(Blocks.STRIPPED_DARK_OAK_LOG).a(Blocks.STRIPPED_DARK_OAK_WOOD);
/* 2885 */     a(Blocks.DARK_OAK_SAPLING, Blocks.POTTED_DARK_OAK_SAPLING, c.b);
/* 2886 */     c(Blocks.DARK_OAK_LEAVES, jb.n);
/*      */     
/* 2888 */     f(Blocks.JUNGLE_PLANKS)
/* 2889 */       .a(Blocks.JUNGLE_BUTTON)
/* 2890 */       .c(Blocks.JUNGLE_FENCE)
/* 2891 */       .d(Blocks.JUNGLE_FENCE_GATE)
/* 2892 */       .e(Blocks.JUNGLE_PRESSURE_PLATE)
/* 2893 */       .a(Blocks.JUNGLE_SIGN, Blocks.JUNGLE_WALL_SIGN)
/* 2894 */       .f(Blocks.JUNGLE_SLAB)
/* 2895 */       .g(Blocks.JUNGLE_STAIRS);
/* 2896 */     g(Blocks.JUNGLE_DOOR);
/* 2897 */     h(Blocks.JUNGLE_TRAPDOOR);
/* 2898 */     j(Blocks.JUNGLE_LOG).c(Blocks.JUNGLE_LOG).a(Blocks.JUNGLE_WOOD);
/* 2899 */     j(Blocks.STRIPPED_JUNGLE_LOG).c(Blocks.STRIPPED_JUNGLE_LOG).a(Blocks.STRIPPED_JUNGLE_WOOD);
/* 2900 */     a(Blocks.JUNGLE_SAPLING, Blocks.POTTED_JUNGLE_SAPLING, c.b);
/* 2901 */     c(Blocks.JUNGLE_LEAVES, jb.n);
/*      */     
/* 2903 */     f(Blocks.CRIMSON_PLANKS)
/* 2904 */       .a(Blocks.CRIMSON_BUTTON)
/* 2905 */       .c(Blocks.CRIMSON_FENCE)
/* 2906 */       .d(Blocks.CRIMSON_FENCE_GATE)
/* 2907 */       .e(Blocks.CRIMSON_PRESSURE_PLATE)
/* 2908 */       .a(Blocks.CRIMSON_SIGN, Blocks.CRIMSON_WALL_SIGN)
/* 2909 */       .f(Blocks.CRIMSON_SLAB)
/* 2910 */       .g(Blocks.CRIMSON_STAIRS);
/* 2911 */     g(Blocks.CRIMSON_DOOR);
/* 2912 */     h(Blocks.CRIMSON_TRAPDOOR);
/* 2913 */     j(Blocks.CRIMSON_STEM).b(Blocks.CRIMSON_STEM).a(Blocks.CRIMSON_HYPHAE);
/* 2914 */     j(Blocks.STRIPPED_CRIMSON_STEM).b(Blocks.STRIPPED_CRIMSON_STEM).a(Blocks.STRIPPED_CRIMSON_HYPHAE);
/* 2915 */     a(Blocks.CRIMSON_FUNGUS, Blocks.POTTED_CRIMSON_FUNGUS, c.b);
/* 2916 */     l(Blocks.CRIMSON_ROOTS, Blocks.POTTED_CRIMSON_ROOTS);
/*      */     
/* 2918 */     f(Blocks.WARPED_PLANKS)
/* 2919 */       .a(Blocks.WARPED_BUTTON)
/* 2920 */       .c(Blocks.WARPED_FENCE)
/* 2921 */       .d(Blocks.WARPED_FENCE_GATE)
/* 2922 */       .e(Blocks.WARPED_PRESSURE_PLATE)
/* 2923 */       .a(Blocks.WARPED_SIGN, Blocks.WARPED_WALL_SIGN)
/* 2924 */       .f(Blocks.WARPED_SLAB)
/* 2925 */       .g(Blocks.WARPED_STAIRS);
/* 2926 */     g(Blocks.WARPED_DOOR);
/* 2927 */     h(Blocks.WARPED_TRAPDOOR);
/* 2928 */     j(Blocks.WARPED_STEM).b(Blocks.WARPED_STEM).a(Blocks.WARPED_HYPHAE);
/* 2929 */     j(Blocks.STRIPPED_WARPED_STEM).b(Blocks.STRIPPED_WARPED_STEM).a(Blocks.STRIPPED_WARPED_HYPHAE);
/* 2930 */     a(Blocks.WARPED_FUNGUS, Blocks.POTTED_WARPED_FUNGUS, c.b);
/* 2931 */     l(Blocks.WARPED_ROOTS, Blocks.POTTED_WARPED_ROOTS);
/*      */     
/* 2933 */     b(Blocks.NETHER_SPROUTS, c.b);
/* 2934 */     a(Items.bA);
/*      */     
/* 2936 */     a(iz.a(Blocks.STONE))
/* 2937 */       .a(var0 -> {
/*      */           MinecraftKey var1 = iy.c.a(Blocks.STONE, var0, this.b);
/*      */           
/*      */           MinecraftKey var2 = iy.d.a(Blocks.STONE, var0, this.b);
/*      */           this.a.accept(e(Blocks.STONE, var1, var2));
/*      */           return var1;
/* 2943 */         }).f(Blocks.STONE_SLAB)
/* 2944 */       .e(Blocks.STONE_PRESSURE_PLATE)
/* 2945 */       .a(Blocks.STONE_BUTTON)
/* 2946 */       .g(Blocks.STONE_STAIRS);
/*      */     
/* 2948 */     g(Blocks.IRON_DOOR);
/* 2949 */     i(Blocks.IRON_TRAPDOOR);
/*      */     
/* 2951 */     f(Blocks.STONE_BRICKS)
/* 2952 */       .b(Blocks.STONE_BRICK_WALL)
/* 2953 */       .g(Blocks.STONE_BRICK_STAIRS)
/* 2954 */       .f(Blocks.STONE_BRICK_SLAB);
/*      */     
/* 2956 */     f(Blocks.MOSSY_STONE_BRICKS)
/* 2957 */       .b(Blocks.MOSSY_STONE_BRICK_WALL)
/* 2958 */       .g(Blocks.MOSSY_STONE_BRICK_STAIRS)
/* 2959 */       .f(Blocks.MOSSY_STONE_BRICK_SLAB);
/*      */     
/* 2961 */     f(Blocks.COBBLESTONE)
/* 2962 */       .b(Blocks.COBBLESTONE_WALL)
/* 2963 */       .g(Blocks.COBBLESTONE_STAIRS)
/* 2964 */       .f(Blocks.COBBLESTONE_SLAB);
/*      */     
/* 2966 */     f(Blocks.MOSSY_COBBLESTONE)
/* 2967 */       .b(Blocks.MOSSY_COBBLESTONE_WALL)
/* 2968 */       .g(Blocks.MOSSY_COBBLESTONE_STAIRS)
/* 2969 */       .f(Blocks.MOSSY_COBBLESTONE_SLAB);
/*      */     
/* 2971 */     f(Blocks.PRISMARINE)
/* 2972 */       .b(Blocks.PRISMARINE_WALL)
/* 2973 */       .g(Blocks.PRISMARINE_STAIRS)
/* 2974 */       .f(Blocks.PRISMARINE_SLAB);
/*      */     
/* 2976 */     f(Blocks.PRISMARINE_BRICKS)
/* 2977 */       .g(Blocks.PRISMARINE_BRICK_STAIRS)
/* 2978 */       .f(Blocks.PRISMARINE_BRICK_SLAB);
/*      */     
/* 2980 */     f(Blocks.DARK_PRISMARINE)
/* 2981 */       .g(Blocks.DARK_PRISMARINE_STAIRS)
/* 2982 */       .f(Blocks.DARK_PRISMARINE_SLAB);
/*      */     
/* 2984 */     d(Blocks.SANDSTONE, jb.t)
/* 2985 */       .b(Blocks.SANDSTONE_WALL)
/* 2986 */       .g(Blocks.SANDSTONE_STAIRS)
/* 2987 */       .f(Blocks.SANDSTONE_SLAB);
/*      */ 
/*      */     
/* 2990 */     a(Blocks.SMOOTH_SANDSTONE, jb.a(iz.a(Blocks.SANDSTONE, "_top")))
/* 2991 */       .f(Blocks.SMOOTH_SANDSTONE_SLAB)
/* 2992 */       .g(Blocks.SMOOTH_SANDSTONE_STAIRS);
/*      */     
/* 2994 */     a(Blocks.CUT_SANDSTONE, jb.c.get(Blocks.SANDSTONE).a(var0 -> var0.a(ja.i, iz.C(Blocks.CUT_SANDSTONE))))
/* 2995 */       .f(Blocks.CUT_SANDSTONE_SLAB);
/*      */     
/* 2997 */     d(Blocks.RED_SANDSTONE, jb.t)
/* 2998 */       .b(Blocks.RED_SANDSTONE_WALL)
/* 2999 */       .g(Blocks.RED_SANDSTONE_STAIRS)
/* 3000 */       .f(Blocks.RED_SANDSTONE_SLAB);
/*      */     
/* 3002 */     a(Blocks.SMOOTH_RED_SANDSTONE, jb.a(iz.a(Blocks.RED_SANDSTONE, "_top")))
/* 3003 */       .f(Blocks.SMOOTH_RED_SANDSTONE_SLAB)
/* 3004 */       .g(Blocks.SMOOTH_RED_SANDSTONE_STAIRS);
/*      */     
/* 3006 */     a(Blocks.CUT_RED_SANDSTONE, jb.c.get(Blocks.RED_SANDSTONE).a(var0 -> var0.a(ja.i, iz.C(Blocks.CUT_RED_SANDSTONE))))
/* 3007 */       .f(Blocks.CUT_RED_SANDSTONE_SLAB);
/*      */     
/* 3009 */     f(Blocks.BRICKS)
/* 3010 */       .b(Blocks.BRICK_WALL)
/* 3011 */       .g(Blocks.BRICK_STAIRS)
/* 3012 */       .f(Blocks.BRICK_SLAB);
/*      */     
/* 3014 */     f(Blocks.NETHER_BRICKS)
/* 3015 */       .c(Blocks.NETHER_BRICK_FENCE)
/* 3016 */       .b(Blocks.NETHER_BRICK_WALL)
/* 3017 */       .g(Blocks.NETHER_BRICK_STAIRS)
/* 3018 */       .f(Blocks.NETHER_BRICK_SLAB);
/*      */     
/* 3020 */     f(Blocks.PURPUR_BLOCK)
/* 3021 */       .g(Blocks.PURPUR_STAIRS)
/* 3022 */       .f(Blocks.PURPUR_SLAB);
/*      */     
/* 3024 */     f(Blocks.DIORITE)
/* 3025 */       .b(Blocks.DIORITE_WALL)
/* 3026 */       .g(Blocks.DIORITE_STAIRS)
/* 3027 */       .f(Blocks.DIORITE_SLAB);
/*      */     
/* 3029 */     f(Blocks.POLISHED_DIORITE)
/* 3030 */       .g(Blocks.POLISHED_DIORITE_STAIRS)
/* 3031 */       .f(Blocks.POLISHED_DIORITE_SLAB);
/*      */     
/* 3033 */     f(Blocks.GRANITE)
/* 3034 */       .b(Blocks.GRANITE_WALL)
/* 3035 */       .g(Blocks.GRANITE_STAIRS)
/* 3036 */       .f(Blocks.GRANITE_SLAB);
/*      */     
/* 3038 */     f(Blocks.POLISHED_GRANITE)
/* 3039 */       .g(Blocks.POLISHED_GRANITE_STAIRS)
/* 3040 */       .f(Blocks.POLISHED_GRANITE_SLAB);
/*      */     
/* 3042 */     f(Blocks.ANDESITE)
/* 3043 */       .b(Blocks.ANDESITE_WALL)
/* 3044 */       .g(Blocks.ANDESITE_STAIRS)
/* 3045 */       .f(Blocks.ANDESITE_SLAB);
/*      */     
/* 3047 */     f(Blocks.POLISHED_ANDESITE)
/* 3048 */       .g(Blocks.POLISHED_ANDESITE_STAIRS)
/* 3049 */       .f(Blocks.POLISHED_ANDESITE_SLAB);
/*      */     
/* 3051 */     f(Blocks.END_STONE_BRICKS)
/* 3052 */       .b(Blocks.END_STONE_BRICK_WALL)
/* 3053 */       .g(Blocks.END_STONE_BRICK_STAIRS)
/* 3054 */       .f(Blocks.END_STONE_BRICK_SLAB);
/*      */     
/* 3056 */     d(Blocks.QUARTZ_BLOCK, jb.c)
/* 3057 */       .g(Blocks.QUARTZ_STAIRS)
/* 3058 */       .f(Blocks.QUARTZ_SLAB);
/*      */     
/* 3060 */     a(Blocks.SMOOTH_QUARTZ, jb.a(iz.a(Blocks.QUARTZ_BLOCK, "_bottom")))
/* 3061 */       .g(Blocks.SMOOTH_QUARTZ_STAIRS)
/* 3062 */       .f(Blocks.SMOOTH_QUARTZ_SLAB);
/*      */     
/* 3064 */     f(Blocks.RED_NETHER_BRICKS)
/* 3065 */       .f(Blocks.RED_NETHER_BRICK_SLAB)
/* 3066 */       .g(Blocks.RED_NETHER_BRICK_STAIRS)
/* 3067 */       .b(Blocks.RED_NETHER_BRICK_WALL);
/*      */     
/* 3069 */     d(Blocks.BLACKSTONE, jb.u)
/* 3070 */       .b(Blocks.BLACKSTONE_WALL)
/* 3071 */       .g(Blocks.BLACKSTONE_STAIRS)
/* 3072 */       .f(Blocks.BLACKSTONE_SLAB);
/*      */     
/* 3074 */     f(Blocks.POLISHED_BLACKSTONE_BRICKS)
/* 3075 */       .b(Blocks.POLISHED_BLACKSTONE_BRICK_WALL)
/* 3076 */       .g(Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS)
/* 3077 */       .f(Blocks.POLISHED_BLACKSTONE_BRICK_SLAB);
/*      */     
/* 3079 */     f(Blocks.POLISHED_BLACKSTONE)
/* 3080 */       .b(Blocks.POLISHED_BLACKSTONE_WALL)
/* 3081 */       .e(Blocks.POLISHED_BLACKSTONE_PRESSURE_PLATE)
/* 3082 */       .a(Blocks.POLISHED_BLACKSTONE_BUTTON)
/* 3083 */       .g(Blocks.POLISHED_BLACKSTONE_STAIRS)
/* 3084 */       .f(Blocks.POLISHED_BLACKSTONE_SLAB);
/*      */     
/* 3086 */     q();
/*      */     
/* 3088 */     l(Blocks.RAIL);
/* 3089 */     m(Blocks.POWERED_RAIL);
/* 3090 */     m(Blocks.DETECTOR_RAIL);
/* 3091 */     m(Blocks.ACTIVATOR_RAIL);
/*      */     
/* 3093 */     p();
/*      */     
/* 3095 */     n(Blocks.COMMAND_BLOCK);
/* 3096 */     n(Blocks.REPEATING_COMMAND_BLOCK);
/* 3097 */     n(Blocks.CHAIN_COMMAND_BLOCK);
/*      */     
/* 3099 */     o(Blocks.ANVIL);
/* 3100 */     o(Blocks.CHIPPED_ANVIL);
/* 3101 */     o(Blocks.DAMAGED_ANVIL);
/*      */     
/* 3103 */     k();
/* 3104 */     l();
/*      */     
/* 3106 */     e(Blocks.FURNACE, jb.g);
/* 3107 */     e(Blocks.BLAST_FURNACE, jb.g);
/* 3108 */     e(Blocks.SMOKER, jb.h);
/*      */     
/* 3110 */     o();
/*      */     
/* 3112 */     ah();
/*      */     
/* 3114 */     i(Blocks.CHISELED_STONE_BRICKS, Blocks.INFESTED_CHISELED_STONE_BRICKS);
/* 3115 */     i(Blocks.COBBLESTONE, Blocks.INFESTED_COBBLESTONE);
/* 3116 */     i(Blocks.CRACKED_STONE_BRICKS, Blocks.INFESTED_CRACKED_STONE_BRICKS);
/* 3117 */     i(Blocks.MOSSY_STONE_BRICKS, Blocks.INFESTED_MOSSY_STONE_BRICKS);
/* 3118 */     ag();
/* 3119 */     i(Blocks.STONE_BRICKS, Blocks.INFESTED_STONE_BRICKS);
/*      */     
/* 3121 */     ItemMonsterEgg.f().forEach(var0 -> a(var0, iw.b("template_spawn_egg")));
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ii.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */