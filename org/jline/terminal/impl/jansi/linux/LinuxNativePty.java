/*     */ package org.jline.terminal.impl.jansi.linux;public class LinuxNativePty extends JansiNativePty { private static final int VINTR = 0; private static final int VQUIT = 1; private static final int VERASE = 2; private static final int VKILL = 3; private static final int VEOF = 4; private static final int VTIME = 5; private static final int VMIN = 6; private static final int VSWTC = 7; private static final int VSTART = 8; private static final int VSTOP = 9; private static final int VSUSP = 10; private static final int VEOL = 11; private static final int VREPRINT = 12; private static final int VDISCARD = 13; private static final int VWERASE = 14; private static final int VLNEXT = 15; private static final int VEOL2 = 16; private static final int IGNBRK = 1; private static final int BRKINT = 2; private static final int IGNPAR = 4; private static final int PARMRK = 8; private static final int INPCK = 16; private static final int ISTRIP = 32; private static final int INLCR = 64; private static final int IGNCR = 128; private static final int ICRNL = 256; private static final int IUCLC = 512; private static final int IXON = 1024; private static final int IXANY = 2048; private static final int IXOFF = 4096; private static final int IMAXBEL = 8192; private static final int IUTF8 = 16384; private static final int OPOST = 1;
/*     */   private static final int OLCUC = 2;
/*     */   private static final int ONLCR = 4;
/*     */   private static final int OCRNL = 8;
/*     */   private static final int ONOCR = 16;
/*     */   private static final int ONLRET = 32;
/*     */   private static final int OFILL = 64;
/*     */   private static final int OFDEL = 128;
/*     */   private static final int NLDLY = 256;
/*     */   private static final int NL0 = 0;
/*     */   private static final int NL1 = 256;
/*     */   private static final int CRDLY = 1536;
/*     */   private static final int CR0 = 0;
/*     */   private static final int CR1 = 512;
/*     */   private static final int CR2 = 1024;
/*     */   private static final int CR3 = 1536;
/*     */   private static final int TABDLY = 6144;
/*     */   private static final int TAB0 = 0;
/*     */   private static final int TAB1 = 2048;
/*     */   private static final int TAB2 = 4096;
/*     */   private static final int TAB3 = 6144;
/*     */   private static final int XTABS = 6144;
/*     */   
/*     */   public static LinuxNativePty current() throws IOException {
/*     */     try {
/*  26 */       String name = ttyname();
/*  27 */       return new LinuxNativePty(-1, null, 0, FileDescriptor.in, 1, FileDescriptor.out, name);
/*  28 */     } catch (IOException e) {
/*  29 */       throw new IOException("Not a tty", e);
/*     */     } 
/*     */   }
/*     */   private static final int BSDLY = 8192; private static final int BS0 = 0; private static final int BS1 = 8192; private static final int VTDLY = 16384; private static final int VT0 = 0; private static final int VT1 = 16384; private static final int FFDLY = 32768; private static final int FF0 = 0; private static final int FF1 = 32768; private static final int CBAUD = 4111; private static final int B0 = 0; private static final int B50 = 1; private static final int B75 = 2; private static final int B110 = 3; private static final int B134 = 4; private static final int B150 = 5; private static final int B200 = 6; private static final int B300 = 7; private static final int B600 = 8; private static final int B1200 = 9; private static final int B1800 = 10; private static final int B2400 = 11; private static final int B4800 = 12; private static final int B9600 = 13; private static final int B19200 = 14; private static final int B38400 = 15; private static final int EXTA = 14; private static final int EXTB = 15; private static final int CSIZE = 48; private static final int CS5 = 0; private static final int CS6 = 16; private static final int CS7 = 32; private static final int CS8 = 48; private static final int CSTOPB = 64; private static final int CREAD = 128; private static final int PARENB = 256; private static final int PARODD = 512; private static final int HUPCL = 1024; private static final int CLOCAL = 2048; private static final int ISIG = 1; private static final int ICANON = 2; private static final int XCASE = 4; private static final int ECHO = 8; private static final int ECHOE = 16; private static final int ECHOK = 32; private static final int ECHONL = 64; private static final int NOFLSH = 128; private static final int TOSTOP = 256; private static final int ECHOCTL = 512; private static final int ECHOPRT = 1024; private static final int ECHOKE = 2048; private static final int FLUSHO = 4096; private static final int PENDIN = 8192; private static final int IEXTEN = 32768; private static final int EXTPROC = 65536;
/*     */   public static LinuxNativePty open(Attributes attr, Size size) throws IOException {
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
/*  45 */     return new LinuxNativePty(master[0], newDescriptor(master[0]), slave[0], newDescriptor(slave[0]), name);
/*     */   }
/*     */   
/*     */   public LinuxNativePty(int master, FileDescriptor masterFD, int slave, FileDescriptor slaveFD, String name) {
/*  49 */     super(master, masterFD, slave, slaveFD, name);
/*     */   }
/*     */   
/*     */   public LinuxNativePty(int master, FileDescriptor masterFD, int slave, FileDescriptor slaveFD, int slaveOut, FileDescriptor slaveOutFD, String name) {
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 172 */     return termios(t);
/*     */   }
/*     */   
/*     */   static CLibrary.Termios termios(Attributes t) {
/* 176 */     CLibrary.Termios tio = new CLibrary.Termios();
/* 177 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.IGNBRK), 1L, tio.c_iflag);
/* 178 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.BRKINT), 2L, tio.c_iflag);
/* 179 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.IGNPAR), 4L, tio.c_iflag);
/* 180 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.PARMRK), 8L, tio.c_iflag);
/* 181 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.INPCK), 16L, tio.c_iflag);
/* 182 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.ISTRIP), 32L, tio.c_iflag);
/* 183 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.INLCR), 64L, tio.c_iflag);
/* 184 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.IGNCR), 128L, tio.c_iflag);
/* 185 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.ICRNL), 256L, tio.c_iflag);
/* 186 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.IXON), 1024L, tio.c_iflag);
/* 187 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.IXOFF), 4096L, tio.c_iflag);
/* 188 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.IXANY), 2048L, tio.c_iflag);
/* 189 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.IMAXBEL), 8192L, tio.c_iflag);
/* 190 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.IUTF8), 16384L, tio.c_iflag);
/*     */     
/* 192 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.OPOST), 1L, tio.c_oflag);
/* 193 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.ONLCR), 4L, tio.c_oflag);
/*     */ 
/*     */     
/* 196 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.OCRNL), 8L, tio.c_oflag);
/* 197 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.ONOCR), 16L, tio.c_oflag);
/* 198 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.ONLRET), 32L, tio.c_oflag);
/* 199 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.OFILL), 64L, tio.c_oflag);
/* 200 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.NLDLY), 256L, tio.c_oflag);
/* 201 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.TABDLY), 6144L, tio.c_oflag);
/* 202 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.CRDLY), 1536L, tio.c_oflag);
/* 203 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.FFDLY), 32768L, tio.c_oflag);
/* 204 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.BSDLY), 8192L, tio.c_oflag);
/* 205 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.VTDLY), 16384L, tio.c_oflag);
/* 206 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.OFDEL), 128L, tio.c_oflag);
/*     */ 
/*     */     
/* 209 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.CS5), 0L, tio.c_cflag);
/* 210 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.CS6), 16L, tio.c_cflag);
/* 211 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.CS7), 32L, tio.c_cflag);
/* 212 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.CS8), 48L, tio.c_cflag);
/* 213 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.CSTOPB), 64L, tio.c_cflag);
/* 214 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.CREAD), 128L, tio.c_cflag);
/* 215 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.PARENB), 256L, tio.c_cflag);
/* 216 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.PARODD), 512L, tio.c_cflag);
/* 217 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.HUPCL), 1024L, tio.c_cflag);
/* 218 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.CLOCAL), 2048L, tio.c_cflag);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 225 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.ECHOKE), 2048L, tio.c_lflag);
/* 226 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.ECHOE), 16L, tio.c_lflag);
/* 227 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.ECHOK), 32L, tio.c_lflag);
/* 228 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.ECHO), 8L, tio.c_lflag);
/* 229 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.ECHONL), 64L, tio.c_lflag);
/* 230 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.ECHOPRT), 1024L, tio.c_lflag);
/* 231 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.ECHOCTL), 512L, tio.c_lflag);
/* 232 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.ISIG), 1L, tio.c_lflag);
/* 233 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.ICANON), 2L, tio.c_lflag);
/*     */     
/* 235 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.IEXTEN), 32768L, tio.c_lflag);
/* 236 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.EXTPROC), 65536L, tio.c_lflag);
/* 237 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.TOSTOP), 256L, tio.c_lflag);
/* 238 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.FLUSHO), 4096L, tio.c_lflag);
/*     */     
/* 240 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.PENDIN), 8192L, tio.c_lflag);
/* 241 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.NOFLSH), 128L, tio.c_lflag);
/*     */     
/* 243 */     tio.c_cc[4] = (byte)t.getControlChar(Attributes.ControlChar.VEOF);
/* 244 */     tio.c_cc[11] = (byte)t.getControlChar(Attributes.ControlChar.VEOL);
/* 245 */     tio.c_cc[16] = (byte)t.getControlChar(Attributes.ControlChar.VEOL2);
/* 246 */     tio.c_cc[2] = (byte)t.getControlChar(Attributes.ControlChar.VERASE);
/* 247 */     tio.c_cc[14] = (byte)t.getControlChar(Attributes.ControlChar.VWERASE);
/* 248 */     tio.c_cc[3] = (byte)t.getControlChar(Attributes.ControlChar.VKILL);
/* 249 */     tio.c_cc[12] = (byte)t.getControlChar(Attributes.ControlChar.VREPRINT);
/* 250 */     tio.c_cc[0] = (byte)t.getControlChar(Attributes.ControlChar.VINTR);
/* 251 */     tio.c_cc[1] = (byte)t.getControlChar(Attributes.ControlChar.VQUIT);
/* 252 */     tio.c_cc[10] = (byte)t.getControlChar(Attributes.ControlChar.VSUSP);
/* 253 */     tio.c_cc[8] = (byte)t.getControlChar(Attributes.ControlChar.VSTART);
/* 254 */     tio.c_cc[9] = (byte)t.getControlChar(Attributes.ControlChar.VSTOP);
/* 255 */     tio.c_cc[15] = (byte)t.getControlChar(Attributes.ControlChar.VLNEXT);
/* 256 */     tio.c_cc[13] = (byte)t.getControlChar(Attributes.ControlChar.VDISCARD);
/* 257 */     tio.c_cc[6] = (byte)t.getControlChar(Attributes.ControlChar.VMIN);
/* 258 */     tio.c_cc[5] = (byte)t.getControlChar(Attributes.ControlChar.VTIME);
/*     */     
/* 260 */     return tio;
/*     */   }
/*     */   
/*     */   protected Attributes toAttributes(CLibrary.Termios tio) {
/* 264 */     Attributes attr = new Attributes();
/*     */     
/* 266 */     EnumSet<Attributes.InputFlag> iflag = attr.getInputFlags();
/* 267 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.IGNBRK, 1);
/* 268 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.IGNBRK, 1);
/* 269 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.BRKINT, 2);
/* 270 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.IGNPAR, 4);
/* 271 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.PARMRK, 8);
/* 272 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.INPCK, 16);
/* 273 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.ISTRIP, 32);
/* 274 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.INLCR, 64);
/* 275 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.IGNCR, 128);
/* 276 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.ICRNL, 256);
/* 277 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.IXON, 1024);
/* 278 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.IXOFF, 4096);
/* 279 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.IXANY, 2048);
/* 280 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.IMAXBEL, 8192);
/* 281 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.IUTF8, 16384);
/*     */     
/* 283 */     EnumSet<Attributes.OutputFlag> oflag = attr.getOutputFlags();
/* 284 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.OPOST, 1);
/* 285 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.ONLCR, 4);
/*     */ 
/*     */     
/* 288 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.OCRNL, 8);
/* 289 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.ONOCR, 16);
/* 290 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.ONLRET, 32);
/* 291 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.OFILL, 64);
/* 292 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.NLDLY, 256);
/* 293 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.TABDLY, 6144);
/* 294 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.CRDLY, 1536);
/* 295 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.FFDLY, 32768);
/* 296 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.BSDLY, 8192);
/* 297 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.VTDLY, 16384);
/* 298 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.OFDEL, 128);
/*     */     
/* 300 */     EnumSet<Attributes.ControlFlag> cflag = attr.getControlFlags();
/*     */     
/* 302 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.CS5, 0);
/* 303 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.CS6, 16);
/* 304 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.CS7, 32);
/* 305 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.CS8, 48);
/* 306 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.CSTOPB, 64);
/* 307 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.CREAD, 128);
/* 308 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.PARENB, 256);
/* 309 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.PARODD, 512);
/* 310 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.HUPCL, 1024);
/* 311 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.CLOCAL, 2048);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 317 */     EnumSet<Attributes.LocalFlag> lflag = attr.getLocalFlags();
/* 318 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.ECHOKE, 2048);
/* 319 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.ECHOE, 16);
/* 320 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.ECHOK, 32);
/* 321 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.ECHO, 8);
/* 322 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.ECHONL, 64);
/* 323 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.ECHOPRT, 1024);
/* 324 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.ECHOCTL, 512);
/* 325 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.ISIG, 1);
/* 326 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.ICANON, 2);
/*     */     
/* 328 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.IEXTEN, 32768);
/* 329 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.EXTPROC, 65536);
/* 330 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.TOSTOP, 256);
/* 331 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.FLUSHO, 4096);
/*     */     
/* 333 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.PENDIN, 8192);
/* 334 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.NOFLSH, 128);
/*     */     
/* 336 */     EnumMap<Attributes.ControlChar, Integer> cc = attr.getControlChars();
/* 337 */     cc.put(Attributes.ControlChar.VEOF, Integer.valueOf(tio.c_cc[4]));
/* 338 */     cc.put(Attributes.ControlChar.VEOL, Integer.valueOf(tio.c_cc[11]));
/* 339 */     cc.put(Attributes.ControlChar.VEOL2, Integer.valueOf(tio.c_cc[16]));
/* 340 */     cc.put(Attributes.ControlChar.VERASE, Integer.valueOf(tio.c_cc[2]));
/* 341 */     cc.put(Attributes.ControlChar.VWERASE, Integer.valueOf(tio.c_cc[14]));
/* 342 */     cc.put(Attributes.ControlChar.VKILL, Integer.valueOf(tio.c_cc[3]));
/* 343 */     cc.put(Attributes.ControlChar.VREPRINT, Integer.valueOf(tio.c_cc[12]));
/* 344 */     cc.put(Attributes.ControlChar.VINTR, Integer.valueOf(tio.c_cc[0]));
/* 345 */     cc.put(Attributes.ControlChar.VQUIT, Integer.valueOf(tio.c_cc[1]));
/* 346 */     cc.put(Attributes.ControlChar.VSUSP, Integer.valueOf(tio.c_cc[10]));
/* 347 */     cc.put(Attributes.ControlChar.VSTART, Integer.valueOf(tio.c_cc[8]));
/* 348 */     cc.put(Attributes.ControlChar.VSTOP, Integer.valueOf(tio.c_cc[9]));
/* 349 */     cc.put(Attributes.ControlChar.VLNEXT, Integer.valueOf(tio.c_cc[15]));
/* 350 */     cc.put(Attributes.ControlChar.VDISCARD, Integer.valueOf(tio.c_cc[13]));
/* 351 */     cc.put(Attributes.ControlChar.VMIN, Integer.valueOf(tio.c_cc[6]));
/* 352 */     cc.put(Attributes.ControlChar.VTIME, Integer.valueOf(tio.c_cc[5]));
/*     */ 
/*     */     
/* 355 */     return attr;
/*     */   }
/*     */   
/*     */   private static long setFlag(boolean flag, long value, long org) {
/* 359 */     return flag ? (org | value) : org;
/*     */   }
/*     */   
/*     */   private static <T extends Enum<T>> void addFlag(long value, EnumSet<T> flags, T flag, int v) {
/* 363 */     if ((value & v) != 0L)
/* 364 */       flags.add(flag); 
/*     */   } }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\terminal\impl\jansi\linux\LinuxNativePty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */