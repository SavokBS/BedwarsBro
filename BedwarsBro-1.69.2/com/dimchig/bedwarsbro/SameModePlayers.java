/*    */ package com.dimchig.bedwarsbro;
/*    */ 
/*    */ import com.dimchig.bedwarsbro.serializer.MySerializer;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class SameModePlayers {
/*  8 */   private static long time_last_check = -1L;
/*  9 */   private static int TIME_CHECK = 300000;
/*    */   public static boolean isActive = false;
/*    */   private static ArrayList<BroPlayer> active_players;
/*    */   
/*    */   public static class BroPlayer
/*    */   {
/*    */     public String name;
/*    */     
/*    */     public BroPlayer(String name, String prefix) {
/* 18 */       this.name = ColorCodesManager.removeColorCodes(name);
/* 19 */       this.prefix = ColorCodesManager.removeColorCodes(prefix);
/* 20 */       this.isAdmin = false;
/*    */     }
/*    */     
/*    */     public String prefix;
/*    */     public boolean isAdmin; }
/*    */   
/*    */   public SameModePlayers() {
/* 27 */     active_players = new ArrayList<BroPlayer>();
/* 28 */     time_last_check = (new Date()).getTime();
/* 29 */     isActive = false;
/*    */   }
/*    */   
/*    */   public ArrayList<BroPlayer> getActivePlayers() {
/* 33 */     this; return active_players;
/*    */   }
/*    */   
/*    */   public boolean isPlayerWithMod(String player_name) {
/* 37 */     for (BroPlayer bro : active_players) {
/* 38 */       if (bro.name.equals(player_name)) return true; 
/*    */     } 
/* 40 */     return false;
/*    */   }
/*    */   
/*    */   public void handle() {
/* 44 */     if (!isActive)
/*    */       return; 
/* 46 */     TIME_CHECK = 300000;
/* 47 */     long t = (new Date()).getTime();
/* 48 */     if (t - time_last_check < TIME_CHECK)
/* 49 */       return;  time_last_check = t;
/* 50 */     loadActivePlayers();
/*    */   }
/*    */   
/*    */   public void loadActivePlayers() {
/* 54 */     Thread t1 = new Thread(new Runnable()
/*    */         {
/*    */           public void run() {
/*    */             try {
/* 58 */               String s = MySerializer.readActivePlayers();
/* 59 */               if (s == null)
/* 60 */                 return;  String[] split = s.split(MySerializer.separator);
/* 61 */               if (split == null)
/* 62 */                 return;  SameModePlayers.active_players.clear();
/* 63 */               for (String line : split) {
/* 64 */                 String[] split2 = line.split(MySerializer.separator_secondary);
/* 65 */                 String player_name = split2[0].trim();
/* 66 */                 String player_prefix = "";
/* 67 */                 boolean isAdmin = false;
/* 68 */                 if (split2.length == 3) {
/* 69 */                   player_prefix = split2[1];
/* 70 */                   if (split2[2].toLowerCase().equals("true")) isAdmin = true;
/*    */                 
/*    */                 } 
/*    */                 
/* 74 */                 boolean isFound = false;
/* 75 */                 for (SameModePlayers.BroPlayer b : SameModePlayers.active_players) {
/* 76 */                   if (b.name.equals(player_name)) {
/* 77 */                     isFound = true;
/*    */                     break;
/*    */                   } 
/*    */                 } 
/* 81 */                 if (!isFound)
/*    */                 
/* 83 */                 { SameModePlayers.BroPlayer bro = new SameModePlayers.BroPlayer(player_name, player_prefix);
/* 84 */                   bro.isAdmin = isAdmin;
/* 85 */                   SameModePlayers.active_players.add(bro); } 
/*    */               } 
/* 87 */             } catch (Exception e) {
/* 88 */               e.printStackTrace();
/*    */             } 
/*    */           }
/*    */         });
/* 92 */     t1.start();
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\SameModePlayers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */