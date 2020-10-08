/*     */ package org.jline.terminal.impl.jansi.osx;public class OsXNativePty extends JansiNativePty { private static final int VEOF = 0; private static final int VEOL = 1; private static final int VEOL2 = 2; private static final int VERASE = 3; private static final int VWERASE = 4; private static final int VKILL = 5; private static final int VREPRINT = 6; private static final int VINTR = 8; private static final int VQUIT = 9; private static final int VSUSP = 10; private static final int VDSUSP = 11; private static final int VSTART = 12; private static final int VSTOP = 13; private static final int VLNEXT = 14; private static final int VDISCARD = 15; private static final int VMIN = 16; private static final int VTIME = 17; private static final int VSTATUS = 18; private static final int IGNBRK = 1; private static final int BRKINT = 2;
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
/*     */   private static final int IUTF8 = 16384;
/*     */   private static final int OPOST = 1;
/*     */   private static final int ONLCR = 2;
/*     */   private static final int OXTABS = 4;
/*     */   private static final int ONOEOT = 8;
/*     */   private static final int OCRNL = 16;
/*     */   private static final int ONOCR = 32;
/*     */   private static final int ONLRET = 64;
/*     */   private static final int OFILL = 128;
/*     */   
/*     */   public static OsXNativePty current() throws IOException {
/*     */     try {
/*  25 */       String name = ttyname();
/*  26 */       return new OsXNativePty(-1, null, 0, FileDescriptor.in, 1, FileDescriptor.out, name);
/*  27 */     } catch (IOException e) {
/*  28 */       throw new IOException("Not a tty", e);
/*     */     } 
/*     */   }
/*     */   private static final int NLDLY = 768; private static final int TABDLY = 3076; private static final int CRDLY = 12288; private static final int FFDLY = 16384; private static final int BSDLY = 32768; private static final int VTDLY = 65536; private static final int OFDEL = 131072; private static final int CIGNORE = 1; private static final int CS5 = 0; private static final int CS6 = 256; private static final int CS7 = 512; private static final int CS8 = 768; private static final int CSTOPB = 1024; private static final int CREAD = 2048; private static final int PARENB = 4096; private static final int PARODD = 8192; private static final int HUPCL = 16384; private static final int CLOCAL = 32768; private static final int CCTS_OFLOW = 65536; private static final int CRTS_IFLOW = 131072; private static final int CDTR_IFLOW = 262144; private static final int CDSR_OFLOW = 524288; private static final int CCAR_OFLOW = 1048576; private static final int ECHOKE = 1; private static final int ECHOE = 2; private static final int ECHOK = 4; private static final int ECHO = 8; private static final int ECHONL = 16; private static final int ECHOPRT = 32; private static final int ECHOCTL = 64; private static final int ISIG = 128; private static final int ICANON = 256; private static final int ALTWERASE = 512; private static final int IEXTEN = 1024; private static final int EXTPROC = 2048; private static final int TOSTOP = 4194304; private static final int FLUSHO = 8388608; private static final int NOKERNINFO = 33554432; private static final int PENDIN = 536870912; private static final int NOFLSH = -2147483648;
/*     */   public static OsXNativePty open(Attributes attr, Size size) throws IOException {
/*  33 */     int[] master = new int[1];
/*  34 */     int[] slave = new int[1];
/*  35 */     byte[] buf = new byte[64];
/*  36 */     CLibrary.openpty(master, slave, buf, (attr != null) ? 
/*  37 */         termios(attr) : null, (size != null) ? new CLibrary.WinSize(
/*  38 */           (short)size.getRows(), (short)size.getColumns()) : null);
/*  39 */     int len = 0;
/*  40 */     while (buf[len] != 0) {
/*  41 */       len++;
/*     */     }
/*  43 */     String name = new String(buf, 0, len);
/*  44 */     return new OsXNativePty(master[0], newDescriptor(master[0]), slave[0], newDescriptor(slave[0]), name);
/*     */   }
/*     */   
/*     */   public OsXNativePty(int master, FileDescriptor masterFD, int slave, FileDescriptor slaveFD, String name) {
/*  48 */     super(master, masterFD, slave, slaveFD, name);
/*     */   }
/*     */   
/*     */   public OsXNativePty(int master, FileDescriptor masterFD, int slave, FileDescriptor slaveFD, int slaveOut, FileDescriptor slaveOutFD, String name) {
/*  52 */     super(master, masterFD, slave, slaveFD, slaveOut, slaveOutFD, name);
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
/*     */   protected CLibrary.Termios toTermios(Attributes t) {
/* 142 */     return termios(t);
/*     */   }
/*     */   
/*     */   static CLibrary.Termios termios(Attributes t) {
/* 146 */     CLibrary.Termios tio = new CLibrary.Termios();
/* 147 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.IGNBRK), 1L, tio.c_iflag);
/* 148 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.BRKINT), 2L, tio.c_iflag);
/* 149 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.IGNPAR), 4L, tio.c_iflag);
/* 150 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.PARMRK), 8L, tio.c_iflag);
/* 151 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.INPCK), 16L, tio.c_iflag);
/* 152 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.ISTRIP), 32L, tio.c_iflag);
/* 153 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.INLCR), 64L, tio.c_iflag);
/* 154 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.IGNCR), 128L, tio.c_iflag);
/* 155 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.ICRNL), 256L, tio.c_iflag);
/* 156 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.IXON), 512L, tio.c_iflag);
/* 157 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.IXOFF), 1024L, tio.c_iflag);
/* 158 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.IXANY), 2048L, tio.c_iflag);
/* 159 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.IMAXBEL), 8192L, tio.c_iflag);
/* 160 */     tio.c_iflag = setFlag(t.getInputFlag(Attributes.InputFlag.IUTF8), 16384L, tio.c_iflag);
/*     */     
/* 162 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.OPOST), 1L, tio.c_oflag);
/* 163 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.ONLCR), 2L, tio.c_oflag);
/* 164 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.OXTABS), 4L, tio.c_oflag);
/* 165 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.ONOEOT), 8L, tio.c_oflag);
/* 166 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.OCRNL), 16L, tio.c_oflag);
/* 167 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.ONOCR), 32L, tio.c_oflag);
/* 168 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.ONLRET), 64L, tio.c_oflag);
/* 169 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.OFILL), 128L, tio.c_oflag);
/* 170 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.NLDLY), 768L, tio.c_oflag);
/* 171 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.TABDLY), 3076L, tio.c_oflag);
/* 172 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.CRDLY), 12288L, tio.c_oflag);
/* 173 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.FFDLY), 16384L, tio.c_oflag);
/* 174 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.BSDLY), 32768L, tio.c_oflag);
/* 175 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.VTDLY), 65536L, tio.c_oflag);
/* 176 */     tio.c_oflag = setFlag(t.getOutputFlag(Attributes.OutputFlag.OFDEL), 131072L, tio.c_oflag);
/*     */     
/* 178 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.CIGNORE), 1L, tio.c_cflag);
/* 179 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.CS5), 0L, tio.c_cflag);
/* 180 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.CS6), 256L, tio.c_cflag);
/* 181 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.CS7), 512L, tio.c_cflag);
/* 182 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.CS8), 768L, tio.c_cflag);
/* 183 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.CSTOPB), 1024L, tio.c_cflag);
/* 184 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.CREAD), 2048L, tio.c_cflag);
/* 185 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.PARENB), 4096L, tio.c_cflag);
/* 186 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.PARODD), 8192L, tio.c_cflag);
/* 187 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.HUPCL), 16384L, tio.c_cflag);
/* 188 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.CLOCAL), 32768L, tio.c_cflag);
/* 189 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.CCTS_OFLOW), 65536L, tio.c_cflag);
/* 190 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.CRTS_IFLOW), 131072L, tio.c_cflag);
/* 191 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.CDTR_IFLOW), 262144L, tio.c_cflag);
/* 192 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.CDSR_OFLOW), 524288L, tio.c_cflag);
/* 193 */     tio.c_cflag = setFlag(t.getControlFlag(Attributes.ControlFlag.CCAR_OFLOW), 1048576L, tio.c_cflag);
/*     */     
/* 195 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.ECHOKE), 1L, tio.c_lflag);
/* 196 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.ECHOE), 2L, tio.c_lflag);
/* 197 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.ECHOK), 4L, tio.c_lflag);
/* 198 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.ECHO), 8L, tio.c_lflag);
/* 199 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.ECHONL), 16L, tio.c_lflag);
/* 200 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.ECHOPRT), 32L, tio.c_lflag);
/* 201 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.ECHOCTL), 64L, tio.c_lflag);
/* 202 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.ISIG), 128L, tio.c_lflag);
/* 203 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.ICANON), 256L, tio.c_lflag);
/* 204 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.ALTWERASE), 512L, tio.c_lflag);
/* 205 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.IEXTEN), 1024L, tio.c_lflag);
/* 206 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.EXTPROC), 2048L, tio.c_lflag);
/* 207 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.TOSTOP), 4194304L, tio.c_lflag);
/* 208 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.FLUSHO), 8388608L, tio.c_lflag);
/* 209 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.NOKERNINFO), 33554432L, tio.c_lflag);
/* 210 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.PENDIN), 536870912L, tio.c_lflag);
/* 211 */     tio.c_lflag = setFlag(t.getLocalFlag(Attributes.LocalFlag.NOFLSH), -2147483648L, tio.c_lflag);
/*     */     
/* 213 */     tio.c_cc[0] = (byte)t.getControlChar(Attributes.ControlChar.VEOF);
/* 214 */     tio.c_cc[1] = (byte)t.getControlChar(Attributes.ControlChar.VEOL);
/* 215 */     tio.c_cc[2] = (byte)t.getControlChar(Attributes.ControlChar.VEOL2);
/* 216 */     tio.c_cc[3] = (byte)t.getControlChar(Attributes.ControlChar.VERASE);
/* 217 */     tio.c_cc[4] = (byte)t.getControlChar(Attributes.ControlChar.VWERASE);
/* 218 */     tio.c_cc[5] = (byte)t.getControlChar(Attributes.ControlChar.VKILL);
/* 219 */     tio.c_cc[6] = (byte)t.getControlChar(Attributes.ControlChar.VREPRINT);
/* 220 */     tio.c_cc[8] = (byte)t.getControlChar(Attributes.ControlChar.VINTR);
/* 221 */     tio.c_cc[9] = (byte)t.getControlChar(Attributes.ControlChar.VQUIT);
/* 222 */     tio.c_cc[10] = (byte)t.getControlChar(Attributes.ControlChar.VSUSP);
/* 223 */     tio.c_cc[11] = (byte)t.getControlChar(Attributes.ControlChar.VDSUSP);
/* 224 */     tio.c_cc[12] = (byte)t.getControlChar(Attributes.ControlChar.VSTART);
/* 225 */     tio.c_cc[13] = (byte)t.getControlChar(Attributes.ControlChar.VSTOP);
/* 226 */     tio.c_cc[14] = (byte)t.getControlChar(Attributes.ControlChar.VLNEXT);
/* 227 */     tio.c_cc[15] = (byte)t.getControlChar(Attributes.ControlChar.VDISCARD);
/* 228 */     tio.c_cc[16] = (byte)t.getControlChar(Attributes.ControlChar.VMIN);
/* 229 */     tio.c_cc[17] = (byte)t.getControlChar(Attributes.ControlChar.VTIME);
/* 230 */     tio.c_cc[18] = (byte)t.getControlChar(Attributes.ControlChar.VSTATUS);
/* 231 */     return tio;
/*     */   }
/*     */   
/*     */   protected Attributes toAttributes(CLibrary.Termios tio) {
/* 235 */     Attributes attr = new Attributes();
/*     */     
/* 237 */     EnumSet<Attributes.InputFlag> iflag = attr.getInputFlags();
/* 238 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.IGNBRK, 1);
/* 239 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.IGNBRK, 1);
/* 240 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.BRKINT, 2);
/* 241 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.IGNPAR, 4);
/* 242 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.PARMRK, 8);
/* 243 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.INPCK, 16);
/* 244 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.ISTRIP, 32);
/* 245 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.INLCR, 64);
/* 246 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.IGNCR, 128);
/* 247 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.ICRNL, 256);
/* 248 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.IXON, 512);
/* 249 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.IXOFF, 1024);
/* 250 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.IXANY, 2048);
/* 251 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.IMAXBEL, 8192);
/* 252 */     addFlag(tio.c_iflag, iflag, Attributes.InputFlag.IUTF8, 16384);
/*     */     
/* 254 */     EnumSet<Attributes.OutputFlag> oflag = attr.getOutputFlags();
/* 255 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.OPOST, 1);
/* 256 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.ONLCR, 2);
/* 257 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.OXTABS, 4);
/* 258 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.ONOEOT, 8);
/* 259 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.OCRNL, 16);
/* 260 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.ONOCR, 32);
/* 261 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.ONLRET, 64);
/* 262 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.OFILL, 128);
/* 263 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.NLDLY, 768);
/* 264 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.TABDLY, 3076);
/* 265 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.CRDLY, 12288);
/* 266 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.FFDLY, 16384);
/* 267 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.BSDLY, 32768);
/* 268 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.VTDLY, 65536);
/* 269 */     addFlag(tio.c_oflag, oflag, Attributes.OutputFlag.OFDEL, 131072);
/*     */     
/* 271 */     EnumSet<Attributes.ControlFlag> cflag = attr.getControlFlags();
/* 272 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.CIGNORE, 1);
/* 273 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.CS5, 0);
/* 274 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.CS6, 256);
/* 275 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.CS7, 512);
/* 276 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.CS8, 768);
/* 277 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.CSTOPB, 1024);
/* 278 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.CREAD, 2048);
/* 279 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.PARENB, 4096);
/* 280 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.PARODD, 8192);
/* 281 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.HUPCL, 16384);
/* 282 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.CLOCAL, 32768);
/* 283 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.CCTS_OFLOW, 65536);
/* 284 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.CRTS_IFLOW, 131072);
/* 285 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.CDSR_OFLOW, 524288);
/* 286 */     addFlag(tio.c_cflag, cflag, Attributes.ControlFlag.CCAR_OFLOW, 1048576);
/*     */     
/* 288 */     EnumSet<Attributes.LocalFlag> lflag = attr.getLocalFlags();
/* 289 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.ECHOKE, 1);
/* 290 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.ECHOE, 2);
/* 291 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.ECHOK, 4);
/* 292 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.ECHO, 8);
/* 293 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.ECHONL, 16);
/* 294 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.ECHOPRT, 32);
/* 295 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.ECHOCTL, 64);
/* 296 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.ISIG, 128);
/* 297 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.ICANON, 256);
/* 298 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.ALTWERASE, 512);
/* 299 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.IEXTEN, 1024);
/* 300 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.EXTPROC, 2048);
/* 301 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.TOSTOP, 4194304);
/* 302 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.FLUSHO, 8388608);
/* 303 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.NOKERNINFO, 33554432);
/* 304 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.PENDIN, 536870912);
/* 305 */     addFlag(tio.c_lflag, lflag, Attributes.LocalFlag.NOFLSH, -2147483648);
/*     */     
/* 307 */     EnumMap<Attributes.ControlChar, Integer> cc = attr.getControlChars();
/* 308 */     cc.put(Attributes.ControlChar.VEOF, Integer.valueOf(tio.c_cc[0]));
/* 309 */     cc.put(Attributes.ControlChar.VEOL, Integer.valueOf(tio.c_cc[1]));
/* 310 */     cc.put(Attributes.ControlChar.VEOL2, Integer.valueOf(tio.c_cc[2]));
/* 311 */     cc.put(Attributes.ControlChar.VERASE, Integer.valueOf(tio.c_cc[3]));
/* 312 */     cc.put(Attributes.ControlChar.VWERASE, Integer.valueOf(tio.c_cc[4]));
/* 313 */     cc.put(Attributes.ControlChar.VKILL, Integer.valueOf(tio.c_cc[5]));
/* 314 */     cc.put(Attributes.ControlChar.VREPRINT, Integer.valueOf(tio.c_cc[6]));
/* 315 */     cc.put(Attributes.ControlChar.VINTR, Integer.valueOf(tio.c_cc[8]));
/* 316 */     cc.put(Attributes.ControlChar.VQUIT, Integer.valueOf(tio.c_cc[9]));
/* 317 */     cc.put(Attributes.ControlChar.VSUSP, Integer.valueOf(tio.c_cc[10]));
/* 318 */     cc.put(Attributes.ControlChar.VDSUSP, Integer.valueOf(tio.c_cc[11]));
/* 319 */     cc.put(Attributes.ControlChar.VSTART, Integer.valueOf(tio.c_cc[12]));
/* 320 */     cc.put(Attributes.ControlChar.VSTOP, Integer.valueOf(tio.c_cc[13]));
/* 321 */     cc.put(Attributes.ControlChar.VLNEXT, Integer.valueOf(tio.c_cc[14]));
/* 322 */     cc.put(Attributes.ControlChar.VDISCARD, Integer.valueOf(tio.c_cc[15]));
/* 323 */     cc.put(Attributes.ControlChar.VMIN, Integer.valueOf(tio.c_cc[16]));
/* 324 */     cc.put(Attributes.ControlChar.VTIME, Integer.valueOf(tio.c_cc[17]));
/* 325 */     cc.put(Attributes.ControlChar.VSTATUS, Integer.valueOf(tio.c_cc[18]));
/*     */     
/* 327 */     return attr;
/*     */   }
/*     */   
/*     */   private static long setFlag(boolean flag, long value, long org) {
/* 331 */     return flag ? (org | value) : org;
/*     */   }
/*     */   
/*     */   private static <T extends Enum<T>> void addFlag(long value, EnumSet<T> flags, T flag, int v) {
/* 335 */     if ((value & v) != 0L)
/* 336 */       flags.add(flag); 
/*     */   } }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\terminal\impl\jansi\osx\OsXNativePty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */