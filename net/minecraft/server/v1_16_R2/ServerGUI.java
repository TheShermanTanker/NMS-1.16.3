/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.destroystokyo.paper.gui.GuiStatsComponent;
/*     */ import com.mojang.util.QueueLogAppender;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.event.FocusAdapter;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.util.Collection;
/*     */ import java.util.Objects;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollBar;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.EtchedBorder;
/*     */ import javax.swing.border.TitledBorder;
/*     */ import javax.swing.text.AttributeSet;
/*     */ import javax.swing.text.BadLocationException;
/*     */ import javax.swing.text.Document;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class ServerGUI extends JComponent {
/*  35 */   private static final Font a = new Font("Monospaced", 0, 12);
/*  36 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   private final DedicatedServer c;
/*     */   private Thread d;
/*  39 */   private final Collection<Runnable> e = Lists.newArrayList();
/*  40 */   private final AtomicBoolean f = new AtomicBoolean();
/*     */   
/*     */   public static ServerGUI a(final DedicatedServer dedicatedserver) {
/*     */     try {
/*  44 */       UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
/*  45 */     } catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */     
/*  49 */     final JFrame jframe = new JFrame("Minecraft server");
/*  50 */     final ServerGUI servergui = new ServerGUI(dedicatedserver);
/*     */     
/*  52 */     jframe.setDefaultCloseOperation(2);
/*  53 */     jframe.add(servergui);
/*  54 */     jframe.pack();
/*  55 */     jframe.setLocationRelativeTo((Component)null);
/*  56 */     jframe.setVisible(true);
/*  57 */     jframe.addWindowListener(new WindowAdapter() {
/*     */           public void windowClosing(WindowEvent windowevent) {
/*  59 */             if (!servergui.f.getAndSet(true)) {
/*  60 */               jframe.setTitle("Minecraft server - shutting down!");
/*  61 */               dedicatedserver.safeShutdown(true);
/*  62 */               servergui.f();
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/*  67 */     Objects.requireNonNull(jframe); servergui.a(jframe::dispose);
/*  68 */     servergui.a();
/*  69 */     return servergui;
/*     */   }
/*     */   
/*     */   private ServerGUI(DedicatedServer dedicatedserver) {
/*  73 */     this.c = dedicatedserver;
/*  74 */     setPreferredSize(new Dimension(854, 480));
/*  75 */     setLayout(new BorderLayout());
/*     */     
/*     */     try {
/*  78 */       add(e(), "Center");
/*  79 */       add(c(), "West");
/*  80 */     } catch (Exception exception) {
/*  81 */       LOGGER.error("Couldn't build server GUI", exception);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(Runnable runnable) {
/*  87 */     this.e.add(runnable);
/*     */   }
/*     */   
/*     */   private JComponent c() {
/*  91 */     JPanel jpanel = new JPanel(new BorderLayout());
/*  92 */     GuiStatsComponent guistatscomponent = new GuiStatsComponent(this.c);
/*     */     
/*  94 */     Objects.requireNonNull(guistatscomponent); this.e.add(guistatscomponent::a);
/*  95 */     jpanel.add((Component)guistatscomponent, "North");
/*  96 */     jpanel.add(d(), "Center");
/*  97 */     jpanel.setBorder(new TitledBorder(new EtchedBorder(), "Stats"));
/*  98 */     return jpanel;
/*     */   }
/*     */   
/*     */   private JComponent d() {
/* 102 */     JList<?> jlist = new PlayerListBox(this.c);
/* 103 */     JScrollPane jscrollpane = new JScrollPane(jlist, 22, 30);
/*     */     
/* 105 */     jscrollpane.setBorder(new TitledBorder(new EtchedBorder(), "Players"));
/* 106 */     return jscrollpane;
/*     */   }
/*     */   
/*     */   private JComponent e() {
/* 110 */     JPanel jpanel = new JPanel(new BorderLayout());
/* 111 */     JTextArea jtextarea = new JTextArea();
/* 112 */     JScrollPane jscrollpane = new JScrollPane(jtextarea, 22, 30);
/*     */     
/* 114 */     jtextarea.setEditable(false);
/* 115 */     jtextarea.setFont(a);
/* 116 */     JTextField jtextfield = new JTextField();
/*     */     
/* 118 */     jtextfield.addActionListener(actionevent -> {
/*     */           String s = jtextfield.getText().trim();
/*     */           
/*     */           if (!s.isEmpty()) {
/*     */             this.c.issueCommand(s, this.c.getServerCommandListener());
/*     */           }
/*     */           
/*     */           jtextfield.setText("");
/*     */         });
/* 127 */     jtextarea.addFocusListener(new FocusAdapter() {
/*     */           public void focusGained(FocusEvent focusevent) {}
/*     */         });
/* 130 */     jpanel.add(jscrollpane, "Center");
/* 131 */     jpanel.add(jtextfield, "South");
/* 132 */     jpanel.setBorder(new TitledBorder(new EtchedBorder(), "Log and chat"));
/* 133 */     this.d = new Thread(() -> {
/*     */           String s;
/*     */           
/*     */           while ((s = QueueLogAppender.getNextLogEvent("ServerGuiConsole")) != null) {
/*     */             a(jtextarea, jscrollpane, s);
/*     */           }
/*     */         });
/*     */     
/* 141 */     this.d.setUncaughtExceptionHandler(new DefaultUncaughtExceptionHandler(LOGGER));
/* 142 */     this.d.setDaemon(true);
/* 143 */     return jpanel;
/*     */   }
/*     */   
/*     */   public void a() {
/* 147 */     this.d.start();
/*     */   }
/*     */   
/*     */   public void b() {
/* 151 */     if (!this.f.getAndSet(true)) {
/* 152 */       f();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void f() {
/* 158 */     this.e.forEach(Runnable::run);
/*     */   }
/*     */   
/* 161 */   private static final Pattern ANSI = Pattern.compile("\\x1B\\[([0-9]{1,2}(;[0-9]{1,2})*)?[m|K]");
/*     */   public void a(JTextArea jtextarea, JScrollPane jscrollpane, String s) {
/* 163 */     if (!SwingUtilities.isEventDispatchThread()) {
/* 164 */       SwingUtilities.invokeLater(() -> a(jtextarea, jscrollpane, s));
/*     */     }
/*     */     else {
/*     */       
/* 168 */       Document document = jtextarea.getDocument();
/* 169 */       JScrollBar jscrollbar = jscrollpane.getVerticalScrollBar();
/* 170 */       boolean flag = false;
/*     */       
/* 172 */       if (jscrollpane.getViewport().getView() == jtextarea) {
/* 173 */         flag = (jscrollbar.getValue() + jscrollbar.getSize().getHeight() + (a.getSize() * 4) > jscrollbar.getMaximum());
/*     */       }
/*     */       
/*     */       try {
/* 177 */         document.insertString(document.getLength(), ANSI.matcher(s).replaceAll(""), (AttributeSet)null);
/* 178 */       } catch (BadLocationException badLocationException) {}
/*     */ 
/*     */ 
/*     */       
/* 182 */       if (flag)
/* 183 */         jscrollbar.setValue(2147483647); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ServerGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */