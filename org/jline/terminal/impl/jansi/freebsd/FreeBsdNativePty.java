/*     */ package org.jline.terminal.impl.jansi.freebsd;public class FreeBsdNativePty extends JansiNativePty { private static final int VEOF = 0; private static final int VEOL = 1; private static final int VEOL2 = 2; private static final int VERASE = 3; private static final int VWERASE = 4; private static final int VKILL = 5; private static final int VREPRINT = 6; private static final int VERASE2 = 7; private static final int VINTR = 8; private static final int VQUIT = 9; private static final int VSUSP = 10; private static final int VDSUSP = 11; private static final int VSTART = 12;
/*     */   private static final int VSTOP = 13;
/*     */   private static final int VLNEXT = 14;
/*     */   private static final int VDISCARD = 15;
/*     */   private static final int VMIN = 16;
/*     */   private static final int VTIME = 17;
/*     */   private static final int VSTATUS = 18;
/*     */   private static final int IGNBRK = 1;
/*     */   private static final int BRKINT = 2;
/*     */   private static final int IGNPAR = 4;
/*     */   private static final int PARMRK = 8;
/*     */   private static final int INPCK = 16;
/*     */   private static final int ISTRIP = 32;
/*     */   private static final int INLCR = 64;
/*     */   private static final int IGNCR = 128;
/*     */   private static final int ICRNL = 256;
/*     */   private static final int IXON = 512;
/*     */   private static final int IXOFF = 1024;
/*     */   private static final int IXANY = 2048;
/*     */   private static final int IMAXBEL = 8192;
/*     */   private static final int OPOST = 1;
/*     */   private static final int ONLCR = 2;
/*     */   
/*     */   public static FreeBsdNativePty current() throws IOException {
/*     */     try {
/*  26 */       String name = ttyname();
/*  27 */       return new FreeBsdNativePty(-1, null, 0, FileDescriptor.in, 1, FileDescriptor.out, name);
/*  28 */     } catch (IOException e) {
/*  29 */       throw new IOException("Not a tty", e);
/*     */     } 
/*     */   }
/*     */   private static final int TABDLY = 4; private static final int TAB0 = 0; private static final int TAB3 = 4; private static final int ONOEOT = 8; private static final int OCRNL = 16; private static final int ONLRET = 64; private static final int CIGNORE = 1; private static final int CSIZE = 768; private static final int CS5 = 0; private static final int CS6 = 256; private static final int CS7 = 512; private static final int CS8 = 768; private static final int CSTOPB = 1024; private static final int CREAD = 2048; private static final int PARENB = 4096; private static final int PARODD = 8192; private static final int HUPCL = 16384; private static final int CLOCAL = 32768; private static final int ECHOKE = 1; private static final int ECHOE = 2; private static final int ECHOK = 4; private static final int ECHO = 8; private static final int ECHONL = 16; private static final int ECHOPRT = 32; private static final int ECHOCTL = 64; private static final int ISIG = 128; private static final int ICANON = 256; private static final int ALTWERASE = 512; private static final int IEXTEN = 1024; private static final int EXTPROC = 2048; private static final int TOSTOP = 4194304; private static final int FLUSHO = 8388608; private static final int PENDIN = 33554432; private static final int NOFLSH = 134217728;
/*     */   public static FreeBsdNativePty open(Attributes attr, Size size) throws IOException {
/*  34 */     int[] master = new int[1];
/*  35 */     int[] slave = new int[1];
/*  36 */     byte[] buf = new byte[64];
/*  37 */     CLibrary.openpty(master, slave, buf, (attr != null) ? 
/*  38 */         termios(attr) : null, (size != null) ? new CLibrary.WinSize(
/*  39 */           (short)size.getRows(), (short)size.getColumns()) : null);
/*  40 */     int len = 0;
/*  41 */     while (buf[len] != 0) {
/*  42 */       len++;
/*     */     }
/*  44 */     String name = new String(buf, 0, len);
/*  45 */     return new FreeBsdNativePty(master[0], newDescriptor(master[0]), slave[0], newDescriptor(slave[0]), name);
/*     */   }
/*     */   
/*     */   public FreeBsdNativePty(int master, FileDescriptor masterFD, int slave, FileDescriptor slaveFD, String name) {
/*  49 */     super(master, masterFD, slave, slaveFD, name);
/*     */   }
/*     */   
/*     */   public FreeBsdNativePty(int master, FileDescriptor masterFD, int slave, FileDescriptor slaveFD, int slaveOut, FileDescriptor slaveOutFD, String name) {
/*  53 */     super(master, masterFD, slave, slaveFD, slaveOut, slaveOutFD, name);
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
/*     */   protected CLibrary.Termios toTermios(Attributes t) {
/* 131 */     return termios(t);
/*     */   }
/*     */   
/*     */   static CLibrary.Termios termios(Attributes t) {
/* 135 */     CLibrary.Termios tio = new CLibrary.Termios();
/* 136 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.IGNBRK), 1L, tio.c_iflag);
/* 137 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.BRKINT), 2L, tio.c_iflag);
/* 138 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.IGNPAR), 4L, tio.c_iflag);
/* 139 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.PARMRK), 8L, tio.c_iflag);
/* 140 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.INPCK), 16L, tio.c_iflag);
/* 141 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.ISTRIP), 32L, tio.c_iflag);
/* 142 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.INLCR), 64L, tio.c_iflag);
/* 143 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.IGNCR), 128L, tio.c_iflag);
/* 144 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.ICRNL), 256L, tio.c_iflag);
/* 145 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.IXON), 512L, tio.c_iflag);
/* 146 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.IXOFF), 1024L, tio.c_iflag);
/* 147 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.IXANY), 2048L, tio.c_iflag);
/* 148 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.IMAXBEL), 8192L, tio.c_iflag);
/*     */ 
/*     */     
/* 151 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.OPOST), 1L, tio.c_oflag);
/* 152 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.ONLCR), 2L, tio.c_oflag);
/*     */     
/* 154 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.ONOEOT), 8L, tio.c_oflag);
/* 155 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.OCRNL), 16L, tio.c_oflag);
/*     */     
/* 157 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.ONLRET), 64L, tio.c_oflag);
/*     */ 
/*     */     
/* 160 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.TABDLY), 4L, tio.c_oflag);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 167 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.CIGNORE), 1L, tio.c_cflag);
/* 168 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.CS5), 0L, tio.c_cflag);
/* 169 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.CS6), 256L, tio.c_cflag);
/* 170 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.CS7), 512L, tio.c_cflag);
/* 171 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.CS8), 768L, tio.c_cflag);
/* 172 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.CSTOPB), 1024L, tio.c_cflag);
/* 173 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.CREAD), 2048L, tio.c_cflag);
/* 174 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.PARENB), 4096L, tio.c_cflag);
/* 175 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.PARODD), 8192L, tio.c_cflag);
/* 176 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.HUPCL), 16384L, tio.c_cflag);
/* 177 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.CLOCAL), 32768L, tio.c_cflag);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 184 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.ECHOKE), 1L, tio.c_lflag);
/* 185 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.ECHOE), 2L, tio.c_lflag);
/* 186 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.ECHOK), 4L, tio.c_lflag);
/* 187 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.ECHO), 8L, tio.c_lflag);
/* 188 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.ECHONL), 16L, tio.c_lflag);
/* 189 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.ECHOPRT), 32L, tio.c_lflag);
/* 190 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.ECHOCTL), 64L, tio.c_lflag);
/* 191 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.ISIG), 128L, tio.c_lflag);
/* 192 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.ICANON), 256L, tio.c_lflag);
/* 193 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.ALTWERASE), 512L, tio.c_lflag);
/* 194 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.IEXTEN), 1024L, tio.c_lflag);
/* 195 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.EXTPROC), 2048L, tio.c_lflag);
/* 196 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.TOSTOP), 4194304L, tio.c_lflag);
/* 197 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.FLUSHO), 8388608L, tio.c_lflag);
/*     */     
/* 199 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.PENDIN), 33554432L, tio.c_lflag);
/* 200 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.NOFLSH), 134217728L, tio.c_lflag);
/*     */     
/* 202 */     tio.c_cc[0] = (byte)t.getControlChar(Attributes.ControlChar.VEOF);
/* 203 */     tio.c_cc[1] = (byte)t.getControlChar(Attributes.ControlChar.VEOL);
/* 204 */     tio.c_cc[2] = (byte)t.getControlChar(Attributes.ControlChar.VEOL2);
/* 205 */     tio.c_cc[3] = (byte)t.getControlChar(Attributes.ControlChar.VERASE);
/* 206 */     tio.c_cc[4] = (byte)t.getControlChar(Attributes.ControlChar.VWERASE);
/* 207 */     tio.c_cc[5] = (byte)t.getControlChar(Attributes.ControlChar.VKILL);
/* 208 */     tio.c_cc[6] = (byte)t.getControlChar(Attributes.ControlChar.VREPRINT);
/* 209 */     tio.c_cc[8] = (byte)t.getControlChar(Attributes.ControlChar.VINTR);
/* 210 */     tio.c_cc[9] = (byte)t.getControlChar(Attributes.ControlChar.VQUIT);
/* 211 */     tio.c_cc[10] = (byte)t.getControlChar(Attributes.ControlChar.VSUSP);
/*     */     
/* 213 */     tio.c_cc[12] = (byte)t.getControlChar(Attributes.ControlChar.VSTART);
/* 214 */     tio.c_cc[13] = (byte)t.getControlChar(Attributes.ControlChar.VSTOP);
/* 215 */     tio.c_cc[14] = (byte)t.getControlChar(Attributes.ControlChar.VLNEXT);
/* 216 */     tio.c_cc[15] = (byte)t.getControlChar(Attributes.ControlChar.VDISCARD);
/* 217 */     tio.c_cc[16] = (byte)t.getControlChar(Attributes.ControlChar.VMIN);
/* 218 */     tio.c_cc[17] = (byte)t.getControlChar(Attributes.ControlChar.VTIME);
/*     */     
/* 220 */     return tio;
/*     */   }
/*     */   
/*     */   protected Attributes toAttributes(CLibrary.Termios tio) {
/* 224 */     Attributes attr = new Attributes();
/*     */     
/* 226 */     EnumSet<Attributes.InputFlag> iflag = attr.getInputFlags();
/* 227 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.IGNBRK, 1);
/* 228 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.IGNBRK, 1);
/* 229 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.BRKINT, 2);
/* 230 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.IGNPAR, 4);
/* 231 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.PARMRK, 8);
/* 232 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.INPCK, 16);
/* 233 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.ISTRIP, 32);
/* 234 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.INLCR, 64);
/* 235 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.IGNCR, 128);
/* 236 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.ICRNL, 256);
/* 237 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.IXON, 512);
/* 238 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.IXOFF, 1024);
/* 239 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.IXANY, 2048);
/* 240 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.IMAXBEL, 8192);
/*     */ 
/*     */     
/* 243 */     EnumSet<Attributes.OutputFlag> oflag = attr.getOutputFlags();
/* 244 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.OPOST, 1);
/* 245 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.ONLCR, 2);
/*     */     
/* 247 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.ONOEOT, 8);
/* 248 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.OCRNL, 16);
/*     */     
/* 250 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.ONLRET, 64);
/*     */ 
/*     */     
/* 253 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.TABDLY, 4);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 260 */     EnumSet<Attributes.ControlFlag> cflag = attr.getControlFlags();
/* 261 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.CIGNORE, 1);
/* 262 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.CS5, 0);
/* 263 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.CS6, 256);
/* 264 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.CS7, 512);
/* 265 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.CS8, 768);
/* 266 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.CSTOPB, 1024);
/* 267 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.CREAD, 2048);
/* 268 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.PARENB, 4096);
/* 269 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.PARODD, 8192);
/* 270 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.HUPCL, 16384);
/* 271 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.CLOCAL, 32768);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 277 */     EnumSet<Attributes.LocalFlag> lflag = attr.getLocalFlags();
/* 278 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.ECHOKE, 1);
/* 279 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.ECHOE, 2);
/* 280 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.ECHOK, 4);
/* 281 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.ECHO, 8);
/* 282 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.ECHONL, 16);
/* 283 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.ECHOPRT, 32);
/* 284 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.ECHOCTL, 64);
/* 285 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.ISIG, 128);
/* 286 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.ICANON, 256);
/* 287 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.ALTWERASE, 512);
/* 288 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.IEXTEN, 1024);
/* 289 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.EXTPROC, 2048);
/* 290 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.TOSTOP, 4194304);
/* 291 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.FLUSHO, 8388608);
/*     */     
/* 293 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.PENDIN, 33554432);
/* 294 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.NOFLSH, 134217728);
/*     */     
/* 296 */     EnumMap<Attributes.ControlChar, Integer> cc = attr.getControlChars();
/* 297 */     cc.put(Attributes.ControlChar.VEOF, Integer.valueOf(tio.c_cc[0]));
/* 298 */     cc.put(Attributes.ControlChar.VEOL, Integer.valueOf(tio.c_cc[1]));
/* 299 */     cc.put(Attributes.ControlChar.VEOL2, Integer.valueOf(tio.c_cc[2]));
/* 300 */     cc.put(Attributes.ControlChar.VERASE, Integer.valueOf(tio.c_cc[3]));
/* 301 */     cc.put(Attributes.ControlChar.VWERASE, Integer.valueOf(tio.c_cc[4]));
/* 302 */     cc.put(Attributes.ControlChar.VKILL, Integer.valueOf(tio.c_cc[5]));
/* 303 */     cc.put(Attributes.ControlChar.VREPRINT, Integer.valueOf(tio.c_cc[6]));
/* 304 */     cc.put(Attributes.ControlChar.VINTR, Integer.valueOf(tio.c_cc[8]));
/* 305 */     cc.put(Attributes.ControlChar.VQUIT, Integer.valueOf(tio.c_cc[9]));
/* 306 */     cc.put(Attributes.ControlChar.VSUSP, Integer.valueOf(tio.c_cc[10]));
/*     */     
/* 308 */     cc.put(Attributes.ControlChar.VSTART, Integer.valueOf(tio.c_cc[12]));
/* 309 */     cc.put(Attributes.ControlChar.VSTOP, Integer.valueOf(tio.c_cc[13]));
/* 310 */     cc.put(Attributes.ControlChar.VLNEXT, Integer.valueOf(tio.c_cc[14]));
/* 311 */     cc.put(Attributes.ControlChar.VDISCARD, Integer.valueOf(tio.c_cc[15]));
/* 312 */     cc.put(Attributes.ControlChar.VMIN, Integer.valueOf(tio.c_cc[16]));
/* 313 */     cc.put(Attributes.ControlChar.VTIME, Integer.valueOf(tio.c_cc[17]));
/*     */ 
/*     */     
/* 316 */     return attr;
/*     */   }
/*     */   
/*     */   private static long setFlag(boolean flag, long value, long org) {
/* 320 */     return flag ? (org | value) : org;
/*     */   }
/*     */   
/*     */   private static <T extends Enum<T>> void addFlag(long value, EnumSet<T> flags, T flag, int v) {
/* 324 */     if ((value & v) != 0L)
/* 325 */       flags.add(flag); 
/*     */   } }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\terminal\impl\jansi\freebsd\FreeBsdNativePty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */