/*     */ package org.fusesource.jansi.internal;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.fusesource.hawtjni.runtime.ArgFlag;
/*     */ import org.fusesource.hawtjni.runtime.ClassFlag;
/*     */ import org.fusesource.hawtjni.runtime.FieldFlag;
/*     */ import org.fusesource.hawtjni.runtime.JniArg;
/*     */ import org.fusesource.hawtjni.runtime.JniClass;
/*     */ import org.fusesource.hawtjni.runtime.JniField;
/*     */ import org.fusesource.hawtjni.runtime.JniMethod;
/*     */ import org.fusesource.hawtjni.runtime.Library;
/*     */ import org.fusesource.hawtjni.runtime.MethodFlag;
/*     */ import org.fusesource.hawtjni.runtime.PointerMath;
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
/*     */ @JniClass(conditional = "defined(_WIN32) || defined(_WIN64)")
/*     */ public class Kernel32
/*     */ {
/*  35 */   private static final Library LIBRARY = new Library("jansi", Kernel32.class); @JniField(flags = {FieldFlag.CONSTANT})
/*     */   public static short FOREGROUND_BLUE; static {
/*  37 */     LIBRARY.load();
/*  38 */     init();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JniField(flags = {FieldFlag.CONSTANT})
/*     */   public static short FOREGROUND_GREEN;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JniField(flags = {FieldFlag.CONSTANT})
/*     */   public static short FOREGROUND_RED;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JniField(flags = {FieldFlag.CONSTANT})
/*     */   public static short FOREGROUND_INTENSITY;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JniField(flags = {FieldFlag.CONSTANT})
/*     */   public static short BACKGROUND_BLUE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JniField(flags = {FieldFlag.CONSTANT})
/*     */   public static short BACKGROUND_GREEN;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JniField(flags = {FieldFlag.CONSTANT})
/*     */   public static short BACKGROUND_RED;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JniField(flags = {FieldFlag.CONSTANT})
/*     */   public static short BACKGROUND_INTENSITY;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JniField(flags = {FieldFlag.CONSTANT})
/*     */   public static short COMMON_LVB_LEADING_BYTE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JniField(flags = {FieldFlag.CONSTANT})
/*     */   public static short COMMON_LVB_TRAILING_BYTE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JniField(flags = {FieldFlag.CONSTANT})
/*     */   public static short COMMON_LVB_GRID_HORIZONTAL;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JniField(flags = {FieldFlag.CONSTANT})
/*     */   public static short COMMON_LVB_GRID_LVERTICAL;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JniField(flags = {FieldFlag.CONSTANT})
/*     */   public static short COMMON_LVB_GRID_RVERTICAL;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JniField(flags = {FieldFlag.CONSTANT})
/*     */   public static short COMMON_LVB_REVERSE_VIDEO;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JniField(flags = {FieldFlag.CONSTANT})
/*     */   public static short COMMON_LVB_UNDERSCORE;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JniField(flags = {FieldFlag.CONSTANT})
/*     */   public static int FORMAT_MESSAGE_FROM_SYSTEM;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JniField(flags = {FieldFlag.CONSTANT})
/*     */   public static int STD_INPUT_HANDLE;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JniField(flags = {FieldFlag.CONSTANT})
/*     */   public static int STD_OUTPUT_HANDLE;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JniField(flags = {FieldFlag.CONSTANT})
/*     */   public static int STD_ERROR_HANDLE;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JniField(flags = {FieldFlag.CONSTANT})
/*     */   public static int INVALID_HANDLE_VALUE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JniClass(flags = {ClassFlag.STRUCT, ClassFlag.TYPEDEF}, conditional = "defined(_WIN32) || defined(_WIN64)")
/*     */   public static class SMALL_RECT
/*     */   {
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "sizeof(SMALL_RECT)")
/*     */     public static int SIZEOF;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @JniField(accessor = "Left")
/*     */     public short left;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @JniField(accessor = "Top")
/*     */     public short top;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @JniField(accessor = "Right")
/*     */     public short right;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @JniField(accessor = "Bottom")
/*     */     public short bottom;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     static {
/* 205 */       Kernel32.LIBRARY.load();
/* 206 */       init();
/*     */     }
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
/*     */     public short width() {
/* 224 */       return (short)(this.right - this.left);
/*     */     }
/*     */     public short height() {
/* 227 */       return (short)(this.bottom - this.top);
/*     */     }
/*     */     public SMALL_RECT copy() {
/* 230 */       SMALL_RECT rc = new SMALL_RECT();
/* 231 */       rc.left = this.left;
/* 232 */       rc.top = this.top;
/* 233 */       rc.right = this.right;
/* 234 */       rc.bottom = this.bottom;
/* 235 */       return rc;
/*     */     }
/*     */     
/*     */     @JniMethod(flags = {MethodFlag.CONSTANT_INITIALIZER})
/*     */     private static final native void init();
/*     */   }
/*     */   
/*     */   @JniClass(flags = {ClassFlag.STRUCT, ClassFlag.TYPEDEF}, conditional = "defined(_WIN32) || defined(_WIN64)")
/*     */   public static class COORD
/*     */   {
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "sizeof(COORD)")
/*     */     public static int SIZEOF;
/*     */     @JniField(accessor = "X")
/*     */     public short x;
/*     */     @JniField(accessor = "Y")
/*     */     public short y;
/*     */     
/*     */     static {
/* 253 */       Kernel32.LIBRARY.load();
/* 254 */       init();
/*     */     }
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
/*     */     public COORD copy() {
/* 268 */       COORD rc = new COORD();
/* 269 */       rc.x = this.x;
/* 270 */       rc.y = this.y;
/* 271 */       return rc;
/*     */     }
/*     */     
/*     */     @JniMethod(flags = {MethodFlag.CONSTANT_INITIALIZER})
/*     */     private static final native void init(); }
/*     */   
/*     */   @JniClass(flags = {ClassFlag.STRUCT, ClassFlag.TYPEDEF}, conditional = "defined(_WIN32) || defined(_WIN64)")
/*     */   public static class CONSOLE_SCREEN_BUFFER_INFO { @JniField(flags = {FieldFlag.CONSTANT}, accessor = "sizeof(CONSOLE_SCREEN_BUFFER_INFO)")
/*     */     public static int SIZEOF;
/*     */     
/*     */     static {
/* 282 */       Kernel32.LIBRARY.load();
/* 283 */       init();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @JniField(accessor = "dwSize")
/* 291 */     public Kernel32.COORD size = new Kernel32.COORD();
/*     */     @JniField(accessor = "dwCursorPosition")
/* 293 */     public Kernel32.COORD cursorPosition = new Kernel32.COORD();
/*     */     @JniField(accessor = "wAttributes")
/*     */     public short attributes;
/*     */     @JniField(accessor = "srWindow")
/* 297 */     public Kernel32.SMALL_RECT window = new Kernel32.SMALL_RECT();
/*     */     @JniField(accessor = "dwMaximumWindowSize")
/* 299 */     public Kernel32.COORD maximumWindowSize = new Kernel32.COORD();
/*     */ 
/*     */     
/*     */     public int windowWidth() {
/* 303 */       return this.window.width() + 1;
/*     */     }
/*     */     
/*     */     public int windowHeight() {
/* 307 */       return this.window.height() + 1;
/*     */     }
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
/*     */     @JniMethod(flags = {MethodFlag.CONSTANT_INITIALIZER})
/*     */     private static final native void init(); }
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
/*     */   @JniClass(flags = {ClassFlag.STRUCT, ClassFlag.TYPEDEF}, conditional = "defined(_WIN32) || defined(_WIN64)")
/*     */   public static class CHAR_INFO
/*     */   {
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "sizeof(CHAR_INFO)")
/*     */     public static int SIZEOF;
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
/*     */     @JniField(accessor = "Attributes")
/*     */     public short attributes;
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
/*     */     @JniField(accessor = "Char.UnicodeChar")
/*     */     public char unicodeChar;
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
/*     */     @JniMethod(flags = {MethodFlag.CONSTANT_INITIALIZER})
/*     */     private static final native void init();
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
/*     */     static {
/* 491 */       Kernel32.LIBRARY.load();
/* 492 */       init();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @JniClass(flags = {ClassFlag.STRUCT, ClassFlag.TYPEDEF}, conditional = "defined(_WIN32) || defined(_WIN64)")
/*     */   public static class KEY_EVENT_RECORD
/*     */   {
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "sizeof(KEY_EVENT_RECORD)")
/*     */     public static int SIZEOF;
/*     */     
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "CAPSLOCK_ON")
/*     */     public static int CAPSLOCK_ON;
/*     */     
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "NUMLOCK_ON")
/*     */     public static int NUMLOCK_ON;
/*     */     
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "SCROLLLOCK_ON")
/*     */     public static int SCROLLLOCK_ON;
/*     */     
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "ENHANCED_KEY")
/*     */     public static int ENHANCED_KEY;
/*     */     
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "LEFT_ALT_PRESSED")
/*     */     public static int LEFT_ALT_PRESSED;
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "LEFT_CTRL_PRESSED")
/*     */     public static int LEFT_CTRL_PRESSED;
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "RIGHT_ALT_PRESSED")
/*     */     public static int RIGHT_ALT_PRESSED;
/*     */     
/*     */     static {
/* 523 */       Kernel32.LIBRARY.load();
/* 524 */       init();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "RIGHT_CTRL_PRESSED")
/*     */     public static int RIGHT_CTRL_PRESSED;
/*     */ 
/*     */     
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "SHIFT_PRESSED")
/*     */     public static int SHIFT_PRESSED;
/*     */ 
/*     */     
/*     */     @JniField(accessor = "bKeyDown")
/*     */     public boolean keyDown;
/*     */ 
/*     */     
/*     */     @JniField(accessor = "wRepeatCount")
/*     */     public short repeatCount;
/*     */ 
/*     */     
/*     */     @JniField(accessor = "wVirtualKeyCode")
/*     */     public short keyCode;
/*     */ 
/*     */     
/*     */     @JniField(accessor = "wVirtualScanCode")
/*     */     public short scanCode;
/*     */ 
/*     */     
/*     */     @JniField(accessor = "uChar.UnicodeChar")
/*     */     public char uchar;
/*     */ 
/*     */     
/*     */     @JniField(accessor = "dwControlKeyState")
/*     */     public int controlKeyState;
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 564 */       return "KEY_EVENT_RECORD{keyDown=" + this.keyDown + ", repeatCount=" + this.repeatCount + ", keyCode=" + this.keyCode + ", scanCode=" + this.scanCode + ", uchar=" + this.uchar + ", controlKeyState=" + this.controlKeyState + '}';
/*     */     }
/*     */     
/*     */     @JniMethod(flags = {MethodFlag.CONSTANT_INITIALIZER})
/*     */     private static final native void init(); }
/*     */   
/*     */   @JniClass(flags = {ClassFlag.STRUCT, ClassFlag.TYPEDEF}, conditional = "defined(_WIN32) || defined(_WIN64)")
/*     */   public static class MOUSE_EVENT_RECORD {
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "sizeof(MOUSE_EVENT_RECORD)")
/*     */     public static int SIZEOF;
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "FROM_LEFT_1ST_BUTTON_PRESSED")
/*     */     public static int FROM_LEFT_1ST_BUTTON_PRESSED;
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "FROM_LEFT_2ND_BUTTON_PRESSED")
/*     */     public static int FROM_LEFT_2ND_BUTTON_PRESSED;
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "FROM_LEFT_3RD_BUTTON_PRESSED")
/*     */     public static int FROM_LEFT_3RD_BUTTON_PRESSED;
/*     */     
/*     */     static {
/* 582 */       Kernel32.LIBRARY.load();
/* 583 */       init();
/*     */     }
/*     */ 
/*     */     
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "FROM_LEFT_4TH_BUTTON_PRESSED")
/*     */     public static int FROM_LEFT_4TH_BUTTON_PRESSED;
/*     */     
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "RIGHTMOST_BUTTON_PRESSED")
/*     */     public static int RIGHTMOST_BUTTON_PRESSED;
/*     */     
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "CAPSLOCK_ON")
/*     */     public static int CAPSLOCK_ON;
/*     */     
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "NUMLOCK_ON")
/*     */     public static int NUMLOCK_ON;
/*     */     
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "SCROLLLOCK_ON")
/*     */     public static int SCROLLLOCK_ON;
/*     */     
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "ENHANCED_KEY")
/*     */     public static int ENHANCED_KEY;
/*     */     
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "LEFT_ALT_PRESSED")
/*     */     public static int LEFT_ALT_PRESSED;
/*     */     
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "LEFT_CTRL_PRESSED")
/*     */     public static int LEFT_CTRL_PRESSED;
/*     */     
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "RIGHT_ALT_PRESSED")
/*     */     public static int RIGHT_ALT_PRESSED;
/*     */     
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "RIGHT_CTRL_PRESSED")
/*     */     public static int RIGHT_CTRL_PRESSED;
/*     */     
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "SHIFT_PRESSED")
/*     */     public static int SHIFT_PRESSED;
/*     */     
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "DOUBLE_CLICK")
/*     */     public static int DOUBLE_CLICK;
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "MOUSE_HWHEELED")
/*     */     public static int MOUSE_HWHEELED;
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "MOUSE_MOVED")
/*     */     public static int MOUSE_MOVED;
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "MOUSE_WHEELED")
/*     */     public static int MOUSE_WHEELED;
/*     */     @JniField(accessor = "dwMousePosition")
/* 629 */     public Kernel32.COORD mousePosition = new Kernel32.COORD();
/*     */     
/*     */     @JniField(accessor = "dwButtonState")
/*     */     public int buttonState;
/*     */     @JniField(accessor = "dwControlKeyState")
/*     */     public int controlKeyState;
/*     */     @JniField(accessor = "dwEventFlags")
/*     */     public int eventFlags;
/*     */     
/*     */     public String toString() {
/* 639 */       return "MOUSE_EVENT_RECORD{mousePosition=" + this.mousePosition + ", buttonState=" + this.buttonState + ", controlKeyState=" + this.controlKeyState + ", eventFlags=" + this.eventFlags + '}';
/*     */     }
/*     */ 
/*     */     
/*     */     @JniMethod(flags = {MethodFlag.CONSTANT_INITIALIZER})
/*     */     private static final native void init();
/*     */   }
/*     */ 
/*     */   
/*     */   @JniClass(flags = {ClassFlag.STRUCT, ClassFlag.TYPEDEF}, conditional = "defined(_WIN32) || defined(_WIN64)")
/*     */   public static class WINDOW_BUFFER_SIZE_RECORD
/*     */   {
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "sizeof(WINDOW_BUFFER_SIZE_RECORD)")
/*     */     public static int SIZEOF;
/*     */     
/*     */     static {
/* 655 */       Kernel32.LIBRARY.load();
/* 656 */       init();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @JniField(accessor = "dwSize")
/* 664 */     public Kernel32.COORD size = new Kernel32.COORD();
/*     */     @JniMethod(flags = {MethodFlag.CONSTANT_INITIALIZER})
/*     */     private static final native void init();
/*     */     public String toString() {
/* 668 */       return "WINDOW_BUFFER_SIZE_RECORD{size=" + this.size + '}';
/*     */     }
/*     */   }
/*     */   
/*     */   @JniClass(flags = {ClassFlag.STRUCT, ClassFlag.TYPEDEF}, conditional = "defined(_WIN32) || defined(_WIN64)")
/*     */   public static class FOCUS_EVENT_RECORD {
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "sizeof(WINDOW_BUFFER_SIZE_RECORD)")
/*     */     public static int SIZEOF;
/*     */     
/*     */     static {
/* 678 */       Kernel32.LIBRARY.load();
/* 679 */       init();
/*     */     }
/*     */     
/*     */     @JniField(accessor = "bSetFocus")
/*     */     public boolean setFocus;
/*     */     
/*     */     @JniMethod(flags = {MethodFlag.CONSTANT_INITIALIZER})
/*     */     private static final native void init();
/*     */   }
/*     */   
/*     */   @JniClass(flags = {ClassFlag.STRUCT, ClassFlag.TYPEDEF}, conditional = "defined(_WIN32) || defined(_WIN64)")
/*     */   public static class MENU_EVENT_RECORD {
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "sizeof(MENU_EVENT_RECORD)")
/*     */     public static int SIZEOF;
/*     */     
/*     */     static {
/* 695 */       Kernel32.LIBRARY.load();
/* 696 */       init();
/*     */     }
/*     */     @JniField(accessor = "dwCommandId")
/*     */     public int commandId;
/*     */     @JniMethod(flags = {MethodFlag.CONSTANT_INITIALIZER})
/*     */     private static final native void init(); }
/*     */   @JniClass(flags = {ClassFlag.STRUCT, ClassFlag.TYPEDEF}, conditional = "defined(_WIN32) || defined(_WIN64)")
/*     */   public static class INPUT_RECORD { @JniField(flags = {FieldFlag.CONSTANT}, accessor = "sizeof(INPUT_RECORD)")
/*     */     public static int SIZEOF;
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "KEY_EVENT")
/*     */     public static short KEY_EVENT;
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "MOUSE_EVENT")
/*     */     public static short MOUSE_EVENT;
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "WINDOW_BUFFER_SIZE_EVENT")
/*     */     public static short WINDOW_BUFFER_SIZE_EVENT;
/*     */     
/*     */     static {
/* 713 */       Kernel32.LIBRARY.load();
/* 714 */       init();
/*     */     }
/*     */ 
/*     */     
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "FOCUS_EVENT")
/*     */     public static short FOCUS_EVENT;
/*     */     
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "MENU_EVENT")
/*     */     public static short MENU_EVENT;
/*     */     
/*     */     @JniField(accessor = "EventType")
/*     */     public short eventType;
/*     */     
/*     */     @JniMethod(flags = {MethodFlag.CONSTANT_INITIALIZER})
/*     */     private static final native void init();
/*     */     
/*     */     public static final native void memmove(@JniArg(cast = "void *", flags = {ArgFlag.NO_IN, ArgFlag.CRITICAL}) INPUT_RECORD param1INPUT_RECORD, @JniArg(cast = "const void *", flags = {ArgFlag.NO_OUT, ArgFlag.CRITICAL}) long param1Long1, @JniArg(cast = "size_t") long param1Long2);
/*     */     
/*     */     @JniField(accessor = "Event.KeyEvent")
/* 733 */     public Kernel32.KEY_EVENT_RECORD keyEvent = new Kernel32.KEY_EVENT_RECORD();
/*     */     @JniField(accessor = "Event.MouseEvent")
/* 735 */     public Kernel32.MOUSE_EVENT_RECORD mouseEvent = new Kernel32.MOUSE_EVENT_RECORD();
/*     */     @JniField(accessor = "Event.WindowBufferSizeEvent")
/* 737 */     public Kernel32.WINDOW_BUFFER_SIZE_RECORD windowBufferSizeEvent = new Kernel32.WINDOW_BUFFER_SIZE_RECORD();
/*     */     @JniField(accessor = "Event.MenuEvent")
/* 739 */     public Kernel32.MENU_EVENT_RECORD menuEvent = new Kernel32.MENU_EVENT_RECORD();
/*     */     @JniField(accessor = "Event.FocusEvent")
/* 741 */     public Kernel32.FOCUS_EVENT_RECORD focusEvent = new Kernel32.FOCUS_EVENT_RECORD(); }
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
/*     */   public static INPUT_RECORD[] readConsoleInputHelper(long handle, int count, boolean peek) throws IOException {
/* 804 */     int[] length = new int[1];
/*     */     
/* 806 */     long inputRecordPtr = 0L;
/*     */     try {
/* 808 */       inputRecordPtr = malloc((INPUT_RECORD.SIZEOF * count));
/* 809 */       if (inputRecordPtr == 0L) {
/* 810 */         throw new IOException("cannot allocate memory with JNI");
/*     */       }
/*     */ 
/*     */       
/* 814 */       int res = peek ? PeekConsoleInputW(handle, inputRecordPtr, count, length) : ReadConsoleInputW(handle, inputRecordPtr, count, length);
/* 815 */       if (res == 0) {
/* 816 */         throw new IOException("ReadConsoleInputW failed");
/*     */       }
/* 818 */       if (length[0] <= 0) {
/* 819 */         return new INPUT_RECORD[0];
/*     */       }
/* 821 */       INPUT_RECORD[] records = new INPUT_RECORD[length[0]];
/* 822 */       for (int i = 0; i < records.length; i++) {
/* 823 */         records[i] = new INPUT_RECORD();
/* 824 */         INPUT_RECORD.memmove(records[i], PointerMath.add(inputRecordPtr, (i * INPUT_RECORD.SIZEOF)), INPUT_RECORD.SIZEOF);
/*     */       } 
/* 826 */       return records;
/*     */     } finally {
/* 828 */       if (inputRecordPtr != 0L) {
/* 829 */         free(inputRecordPtr);
/*     */       }
/*     */     } 
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
/*     */   public static INPUT_RECORD[] readConsoleKeyInput(long handle, int count, boolean peek) throws IOException {
/*     */     while (true) {
/* 845 */       INPUT_RECORD[] evts = readConsoleInputHelper(handle, count, peek);
/* 846 */       int keyEvtCount = 0;
/* 847 */       for (INPUT_RECORD evt : evts) {
/* 848 */         if (evt.eventType == INPUT_RECORD.KEY_EVENT) keyEvtCount++; 
/*     */       } 
/* 850 */       if (keyEvtCount > 0) {
/* 851 */         INPUT_RECORD[] res = new INPUT_RECORD[keyEvtCount];
/* 852 */         int i = 0;
/* 853 */         for (INPUT_RECORD evt : evts) {
/* 854 */           if (evt.eventType == INPUT_RECORD.KEY_EVENT) {
/* 855 */             res[i++] = evt;
/*     */           }
/*     */         } 
/* 858 */         return res;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @JniMethod(flags = {MethodFlag.CONSTANT_INITIALIZER})
/*     */   private static final native void init();
/*     */   
/*     */   @JniMethod(cast = "void *")
/*     */   public static final native long malloc(@JniArg(cast = "size_t") long paramLong);
/*     */   
/*     */   public static final native void free(@JniArg(cast = "void *") long paramLong);
/*     */   
/*     */   public static final native int SetConsoleTextAttribute(@JniArg(cast = "HANDLE") long paramLong, short paramShort);
/*     */   
/*     */   public static final native int WaitForSingleObject(@JniArg(cast = "HANDLE") long paramLong, int paramInt);
/*     */   
/*     */   public static final native int CloseHandle(@JniArg(cast = "HANDLE") long paramLong);
/*     */   
/*     */   public static final native int GetLastError();
/*     */   
/*     */   public static final native int FormatMessageW(int paramInt1, @JniArg(cast = "void *") long paramLong, int paramInt2, int paramInt3, @JniArg(cast = "void *", flags = {ArgFlag.NO_IN, ArgFlag.CRITICAL}) byte[] paramArrayOfbyte, int paramInt4, @JniArg(cast = "void *", flags = {ArgFlag.NO_IN, ArgFlag.CRITICAL, ArgFlag.SENTINEL}) long[] paramArrayOflong);
/*     */   
/*     */   public static final native int GetConsoleScreenBufferInfo(@JniArg(cast = "HANDLE", flags = {ArgFlag.POINTER_ARG}) long paramLong, @JniArg(flags = {ArgFlag.NO_IN}) CONSOLE_SCREEN_BUFFER_INFO paramCONSOLE_SCREEN_BUFFER_INFO);
/*     */   
/*     */   @JniMethod(cast = "HANDLE", flags = {MethodFlag.POINTER_RETURN})
/*     */   public static final native long GetStdHandle(int paramInt);
/*     */   
/*     */   public static final native int SetConsoleCursorPosition(@JniArg(cast = "HANDLE", flags = {ArgFlag.POINTER_ARG}) long paramLong, @JniArg(flags = {ArgFlag.BY_VALUE, ArgFlag.NO_OUT}) COORD paramCOORD);
/*     */   
/*     */   public static final native int FillConsoleOutputCharacterW(@JniArg(cast = "HANDLE", flags = {ArgFlag.POINTER_ARG}) long paramLong, char paramChar, int paramInt, @JniArg(flags = {ArgFlag.BY_VALUE, ArgFlag.NO_OUT}) COORD paramCOORD, @JniArg(flags = {ArgFlag.NO_IN}) int[] paramArrayOfint);
/*     */   
/*     */   public static final native int FillConsoleOutputAttribute(@JniArg(cast = "HANDLE", flags = {ArgFlag.POINTER_ARG}) long paramLong, short paramShort, int paramInt, @JniArg(flags = {ArgFlag.BY_VALUE, ArgFlag.NO_OUT}) COORD paramCOORD, @JniArg(flags = {ArgFlag.NO_IN}) int[] paramArrayOfint);
/*     */   
/*     */   public static final native int WriteConsoleW(@JniArg(cast = "HANDLE", flags = {ArgFlag.POINTER_ARG}) long paramLong1, @JniArg(flags = {ArgFlag.NO_OUT}) char[] paramArrayOfchar, int paramInt, @JniArg(flags = {ArgFlag.NO_IN}) int[] paramArrayOfint, @JniArg(cast = "LPVOID", flags = {ArgFlag.POINTER_ARG}) long paramLong2);
/*     */   
/*     */   public static final native int GetConsoleMode(@JniArg(cast = "HANDLE", flags = {ArgFlag.POINTER_ARG}) long paramLong, @JniArg(flags = {ArgFlag.NO_IN}) int[] paramArrayOfint);
/*     */   
/*     */   public static final native int SetConsoleMode(@JniArg(cast = "HANDLE", flags = {ArgFlag.POINTER_ARG}) long paramLong, int paramInt);
/*     */   
/*     */   public static final native int _getch();
/*     */   
/*     */   public static final native int SetConsoleTitle(@JniArg(flags = {ArgFlag.UNICODE}) String paramString);
/*     */   
/*     */   public static final native int GetConsoleOutputCP();
/*     */   
/*     */   public static final native int SetConsoleOutputCP(int paramInt);
/*     */   
/*     */   public static final native int ScrollConsoleScreenBuffer(@JniArg(cast = "HANDLE", flags = {ArgFlag.POINTER_ARG}) long paramLong, @JniArg(flags = {ArgFlag.NO_OUT}) SMALL_RECT paramSMALL_RECT1, @JniArg(flags = {ArgFlag.NO_OUT}) SMALL_RECT paramSMALL_RECT2, @JniArg(flags = {ArgFlag.BY_VALUE, ArgFlag.NO_OUT}) COORD paramCOORD, @JniArg(flags = {ArgFlag.NO_OUT}) CHAR_INFO paramCHAR_INFO);
/*     */   
/*     */   private static final native int ReadConsoleInputW(@JniArg(cast = "HANDLE", flags = {ArgFlag.POINTER_ARG}) long paramLong1, @JniArg(cast = "PINPUT_RECORD", flags = {ArgFlag.POINTER_ARG}) long paramLong2, int paramInt, @JniArg(flags = {ArgFlag.NO_IN}) int[] paramArrayOfint);
/*     */   
/*     */   private static final native int PeekConsoleInputW(@JniArg(cast = "HANDLE", flags = {ArgFlag.POINTER_ARG}) long paramLong1, @JniArg(cast = "PINPUT_RECORD", flags = {ArgFlag.POINTER_ARG}) long paramLong2, int paramInt, @JniArg(flags = {ArgFlag.NO_IN}) int[] paramArrayOfint);
/*     */   
/*     */   public static final native int GetNumberOfConsoleInputEvents(@JniArg(cast = "HANDLE", flags = {ArgFlag.POINTER_ARG}) long paramLong, @JniArg(flags = {ArgFlag.NO_IN}) int[] paramArrayOfint);
/*     */   
/*     */   public static final native int FlushConsoleInputBuffer(@JniArg(cast = "HANDLE", flags = {ArgFlag.POINTER_ARG}) long paramLong);
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\fusesource\jansi\internal\Kernel32.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */