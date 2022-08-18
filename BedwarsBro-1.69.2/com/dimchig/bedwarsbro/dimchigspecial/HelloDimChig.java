/*    */ package com.dimchig.bedwarsbro.dimchigspecial;
/*    */ 
/*    */ import com.dimchig.bedwarsbro.ChatSender;
/*    */ import com.dimchig.bedwarsbro.MyChatListener;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.Date;
/*    */ import java.util.TimerTask;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.network.NetworkPlayerInfo;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HelloDimChig
/*    */ {
/*    */   public static void handleGreeting() {}
/*    */   
/*    */   public static boolean isPlayerOnServer(String player_name) {
/* 81 */     if (Minecraft.func_71410_x() == null || Minecraft.func_71410_x().func_147114_u() == null) return false; 
/* 82 */     Collection<NetworkPlayerInfo> players = Minecraft.func_71410_x().func_147114_u().func_175106_d();
/* 83 */     int cnt = 0;
/* 84 */     for (NetworkPlayerInfo info : players) {
/* 85 */       cnt++;
/* 86 */       if (cnt > 1000) return false;
/*    */       
/* 88 */       if (info == null || info.func_178845_a() == null)
/* 89 */         continue;  String name = info.func_178845_a().getName();
/* 90 */       if (name != null && 
/* 91 */         name.equals(player_name)) {
/* 92 */         return true;
/*    */       }
/*    */     } 
/* 95 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\dimchigspecial\HelloDimChig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */