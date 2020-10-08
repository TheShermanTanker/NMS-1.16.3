/*     */ package org.bukkit.map;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class MapCursorCollection
/*     */ {
/*  13 */   private List<MapCursor> cursors = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/*  21 */     return this.cursors.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MapCursor getCursor(int index) {
/*  32 */     return this.cursors.get(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeCursor(@NotNull MapCursor cursor) {
/*  42 */     return this.cursors.remove(cursor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MapCursor addCursor(@NotNull MapCursor cursor) {
/*  53 */     this.cursors.add(cursor);
/*  54 */     return cursor;
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
/*     */   @NotNull
/*     */   public MapCursor addCursor(int x, int y, byte direction) {
/*  67 */     return addCursor(x, y, direction, (byte)0, true);
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
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public MapCursor addCursor(int x, int y, byte direction, byte type) {
/*  83 */     return addCursor(x, y, direction, type, true);
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
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public MapCursor addCursor(int x, int y, byte direction, byte type, boolean visible) {
/* 100 */     return addCursor(new MapCursor((byte)x, (byte)y, direction, type, visible));
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
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public MapCursor addCursor(int x, int y, byte direction, byte type, boolean visible, @Nullable String caption) {
/* 118 */     return addCursor(new MapCursor((byte)x, (byte)y, direction, type, visible, caption));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\map\MapCursorCollection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */