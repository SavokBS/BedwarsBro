/*    */ package com.dimchig.bedwarsbro.dimchigspecial;
/*    */ 
/*    */ import com.dimchig.bedwarsbro.ChatSender;
/*    */ import com.dimchig.bedwarsbro.MyChatListener;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.Date;
/*    */ import java.util.Timer;
/*    */ import java.util.TimerTask;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.network.NetworkPlayerInfo;
/*    */ 
/*    */ 
/*    */ public class HelloDimChig
/*    */ {
/*    */   public static void handleGreeting() {
/* 17 */     if (Minecraft.func_71410_x() == null || (Minecraft.func_71410_x()).field_71441_e == null)
/*    */       return; 
/* 19 */     String[] available_texts = { "DimChig привет", "DimChig прив", "DimChig q", "DimChig qq", "Здарова DimChig", "DimChig!", "DimChig кинь пати", "DimChig го играть?", "DimChig го в бв", "го в бв DimChig", "DimChig дай дс", "DimChig как получить ют?", "откуда админ, DimChig?" };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 35 */     String[] availbale_nicks = { "DimChig", "Дима", "Димчик", "Димчиг" };
/*    */ 
/*    */ 
/*    */     
/* 39 */     final ArrayList<String> messages = new ArrayList<String>();
/* 40 */     for (String origin : available_texts) {
/* 41 */       for (String nick : availbale_nicks) {
/* 42 */         messages.add(origin.replace("DimChig", nick));
/* 43 */         messages.add(origin.replace("DimChig", nick.toLowerCase()));
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 48 */     if ((Minecraft.func_71410_x()).field_71439_g.func_70005_c_().equals("DimChig")) {
/*    */       return;
/*    */     }
/*    */     
/* 52 */     int delay = 1000 + (Minecraft.func_71410_x()).field_71441_e.field_73012_v.nextInt(4000);
/*    */     
/* 54 */     (new Timer()).schedule(new TimerTask()
/*    */         {
/*    */           
/*    */           public void run()
/*    */           {
/* 59 */             int idx = (int)((new Date()).getTime() / 20L % messages.size());
/* 60 */             String random_message = messages.get(idx);
/*    */ 
/*    */ 
/*    */ 
/*    */             
/* 65 */             if (!HelloDimChig.isPlayerOnServer("DimChig")) {
/*    */               return;
/*    */             }
/*    */ 
/*    */             
/* 70 */             MyChatListener.removeNextMessage = true;
/* 71 */             ChatSender.sendText(random_message);
/*    */           }
/*    */         },  delay);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isPlayerOnServer(String player_name) {
/* 79 */     if (Minecraft.func_71410_x() == null || Minecraft.func_71410_x().func_147114_u() == null) return false; 
/* 80 */     Collection<NetworkPlayerInfo> players = Minecraft.func_71410_x().func_147114_u().func_175106_d();
/* 81 */     int cnt = 0;
/* 82 */     for (NetworkPlayerInfo info : players) {
/* 83 */       cnt++;
/* 84 */       if (cnt > 1000) return false;
/*    */       
/* 86 */       if (info == null || info.func_178845_a() == null)
/* 87 */         continue;  String name = info.func_178845_a().getName();
/* 88 */       if (name != null && 
/* 89 */         name.equals(player_name)) {
/* 90 */         return true;
/*    */       }
/*    */     } 
/* 93 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\dimchigspecial\HelloDimChig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */