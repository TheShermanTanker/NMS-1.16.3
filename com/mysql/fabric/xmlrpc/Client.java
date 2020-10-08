/*     */ package com.mysql.fabric.xmlrpc;
/*     */ 
/*     */ import com.mysql.fabric.xmlrpc.base.MethodCall;
/*     */ import com.mysql.fabric.xmlrpc.base.MethodResponse;
/*     */ import com.mysql.fabric.xmlrpc.base.ResponseParser;
/*     */ import com.mysql.fabric.xmlrpc.exceptions.MySQLFabricException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Client
/*     */ {
/*     */   private URL url;
/*  52 */   private Map<String, String> headers = new HashMap<String, String>();
/*     */   
/*     */   public Client(String url) throws MalformedURLException {
/*  55 */     this.url = new URL(url);
/*     */   }
/*     */   
/*     */   public void setHeader(String name, String value) {
/*  59 */     this.headers.put(name, value);
/*     */   }
/*     */   
/*     */   public void clearHeader(String name) {
/*  63 */     this.headers.remove(name);
/*     */   }
/*     */   
/*     */   public MethodResponse execute(MethodCall methodCall) throws IOException, ParserConfigurationException, SAXException, MySQLFabricException {
/*  67 */     HttpURLConnection connection = null;
/*     */     try {
/*  69 */       connection = (HttpURLConnection)this.url.openConnection();
/*  70 */       connection.setRequestMethod("POST");
/*  71 */       connection.setRequestProperty("User-Agent", "MySQL XML-RPC");
/*  72 */       connection.setRequestProperty("Content-Type", "text/xml");
/*  73 */       connection.setUseCaches(false);
/*  74 */       connection.setDoInput(true);
/*  75 */       connection.setDoOutput(true);
/*     */ 
/*     */       
/*  78 */       for (Map.Entry<String, String> entry : this.headers.entrySet()) {
/*  79 */         connection.setRequestProperty(entry.getKey(), entry.getValue());
/*     */       }
/*     */       
/*  82 */       String out = methodCall.toString();
/*     */ 
/*     */       
/*  85 */       OutputStream os = connection.getOutputStream();
/*  86 */       os.write(out.getBytes());
/*  87 */       os.flush();
/*  88 */       os.close();
/*     */ 
/*     */       
/*  91 */       InputStream is = connection.getInputStream();
/*  92 */       SAXParserFactory factory = SAXParserFactory.newInstance();
/*  93 */       factory.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
/*  94 */       factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
/*  95 */       SAXParser parser = factory.newSAXParser();
/*  96 */       ResponseParser saxp = new ResponseParser();
/*     */       
/*  98 */       parser.parse(is, (DefaultHandler)saxp);
/*     */       
/* 100 */       is.close();
/*     */       
/* 102 */       MethodResponse resp = saxp.getMethodResponse();
/* 103 */       if (resp.getFault() != null) {
/* 104 */         throw new MySQLFabricException(resp.getFault());
/*     */       }
/*     */       
/* 107 */       return resp;
/*     */     } finally {
/*     */       
/* 110 */       if (connection != null)
/* 111 */         connection.disconnect(); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\fabric\xmlrpc\Client.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */