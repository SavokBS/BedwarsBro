/*    */ package com.dimchig.bedwarsbro;
/*    */ 
/*    */ import com.dimchig.bedwarsbro.serializer.MySerializer;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class BaseProps
/*    */ {
/*    */   public enum ENCODING_FORMAT {
/*  9 */     utf,
/* 10 */     iso8859,
/* 11 */     ib8;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 22 */   private String mod_pwd = null;
/* 23 */   private ENCODING_FORMAT senderType = ENCODING_FORMAT.ib8;
/* 24 */   private String mod_last_version = null;
/* 25 */   private String mod_update_link = null; private String discord_link;
/* 26 */   private ArrayList<String> banned_users = new ArrayList<String>();
/*    */   
/*    */   public String getModPwd() {
/* 29 */     return this.mod_pwd;
/* 30 */   } public ENCODING_FORMAT getSenderType() { return this.senderType; }
/* 31 */   public String getModLastVersion() { return this.mod_last_version; }
/* 32 */   public String getModUpdateLink() { return this.mod_update_link; }
/* 33 */   public String getDiscordLink() { return this.discord_link; } public ArrayList<String> getModBannedUsers() {
/* 34 */     return this.banned_users;
/*    */   } public boolean isUserBanned(String player_name) {
/* 36 */     for (String n : this.banned_users) { if (n.equals(player_name)) return true;  }
/* 37 */      return false;
/*    */   }
/*    */   
/*    */   public void readProps() {
/* 41 */     Thread t1 = new Thread(new Runnable()
/*    */         {
/*    */           public void run() {
/*    */             try {
/* 45 */               String s = MySerializer.readProps();
/* 46 */               if (s == null)
/*    */                 return; 
/* 48 */               String[] split = s.split(MySerializer.separator);
/*    */               
/* 50 */               BaseProps.this.mod_pwd = split[0].trim();
/*    */               
/* 52 */               int senderTypeIdx = Integer.parseInt(split[1].trim());
/* 53 */               if (senderTypeIdx == 0) {
/* 54 */                 BaseProps.this.senderType = BaseProps.ENCODING_FORMAT.utf;
/* 55 */               } else if (senderTypeIdx == 1) {
/* 56 */                 BaseProps.this.senderType = BaseProps.ENCODING_FORMAT.iso8859;
/*    */               } else {
/* 58 */                 BaseProps.this.senderType = BaseProps.ENCODING_FORMAT.ib8;
/*    */               } 
/*    */               
/* 61 */               BaseProps.this.mod_last_version = split[2].trim();
/* 62 */               BaseProps.this.mod_update_link = split[3].trim();
/*    */               
/* 64 */               BaseProps.this.banned_users.clear();
/* 65 */               if (split.length == 6) {
/* 66 */                 String[] mod_banned_users = split[4].trim().split(";=&=;");
/* 67 */                 for (String l : mod_banned_users) {
/* 68 */                   if (l.length() > 2) {
/* 69 */                     BaseProps.this.banned_users.add(l);
/*    */                   }
/*    */                 } 
/*    */               } 
/* 73 */               BaseProps.this.discord_link = split[5].trim();
/*    */               
/* 75 */               Main.updateAllBooleans();
/*    */             }
/* 77 */             catch (Exception e) {
/* 78 */               e.printStackTrace();
/*    */             } 
/*    */           }
/*    */         });
/* 82 */     t1.start();
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\BaseProps.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */