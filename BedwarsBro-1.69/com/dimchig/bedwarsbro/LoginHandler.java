/*     */ package com.dimchig.bedwarsbro;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.serializer.MySerializer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.network.FMLNetworkEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LoginHandler
/*     */ {
/*  23 */   Minecraft mc = Minecraft.func_71410_x();
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onJoinServer(FMLNetworkEvent.ClientConnectedToServerEvent e) {
/*  28 */     handleInGame("server_join");
/*     */     
/*  30 */     Main.updateAllBooleans();
/*     */     
/*  32 */     sendPlayersWithSameMod();
/*     */   }
/*     */   
/*     */   void sendPlayersWithSameMod() {
/*  36 */     (new Timer()).schedule(new TimerTask()
/*     */         {
/*     */           public void run()
/*     */           {
/*  40 */             Main.sameModePlayers.loadActivePlayers();
/*  41 */             ChatSender.addText(MyChatListener.PREFIX_BEDWARSBRO + "&fВсе настройки мода - &c/bwbro");
/*  42 */             ChatSender.addText(MyChatListener.PREFIX_BEDWARSBRO + "&fВсе настройки австосообщений - &e/meow");
/*  43 */             ChatSender.addText(MyChatListener.PREFIX_BEDWARSBRO + "&fПоявились вопросы? Заходи в дискорд\n" + MyChatListener.PREFIX_BEDWARSBRO + "сервер мода - &9/bwdiscord");
/*  44 */             Main.updateAllBooleans();
/*     */           }
/*     */         },  3000L);
/*     */ 
/*     */ 
/*     */     
/*  50 */     (new Timer()).schedule(new TimerTask()
/*     */         {
/*     */           public void run()
/*     */           {
/*  54 */             if (Minecraft.func_71410_x() == null || (Minecraft.func_71410_x()).field_71439_g == null) {
/*     */               return;
/*     */             }
/*  57 */             String mod_last_version = Main.getPropModLastVersion();
/*  58 */             if (mod_last_version != null && !"1.69".equals(mod_last_version)) {
/*  59 */               MyChatListener.sendUpdateModMessage();
/*     */               
/*     */               return;
/*     */             } 
/*  63 */             ArrayList<SameModePlayers.BroPlayer> active_players = new ArrayList<SameModePlayers.BroPlayer>();
/*  64 */             active_players.addAll(Main.sameModePlayers.getActivePlayers());
/*  65 */             if (active_players.size() == 0)
/*     */               return; 
/*  67 */             String mod_player_name = (Minecraft.func_71410_x()).field_71439_g.func_70005_c_();
/*  68 */             for (SameModePlayers.BroPlayer bro : active_players) {
/*  69 */               if (bro.name.equals(mod_player_name)) {
/*  70 */                 active_players.remove(bro);
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } 
/*  75 */             int cnt = 0;
/*  76 */             if (active_players.size() < 1)
/*     */               return; 
/*  78 */             String hover = "&fСписок игроков:\n";
/*  79 */             for (SameModePlayers.BroPlayer bro : active_players) {
/*  80 */               if (bro.name.length() <= 3)
/*  81 */                 continue;  hover = hover + " &8• &e" + bro.name + "\n";
/*  82 */               cnt++;
/*     */             } 
/*     */             
/*  85 */             if (cnt > 0) {
/*  86 */               String text = MyChatListener.PREFIX_BEDWARSBRO + "Сейчас на сервере с модом игра" + MyChatListener.getNumberEnding(cnt, "ет", "ет", "ют") + " &a&l" + cnt + " &fигрок" + MyChatListener.getNumberEnding(cnt, "", "а", "ов") + "!";
/*  87 */               hover = hover.substring(0, hover.length() - 1);
/*  88 */               ChatSender.addHoverText(text, hover);
/*     */             } 
/*     */           }
/*     */         }6000L);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  96 */   public static long last_time_query = -1L;
/*  97 */   public static int QUERY_FREQUENCY = 300000;
/*     */   
/*     */   public void handleInGame(String event) {
/* 100 */     long t = (new Date()).getTime();
/* 101 */     if (t - last_time_query < QUERY_FREQUENCY)
/* 102 */       return;  last_time_query = t;
/* 103 */     generateQuery(event);
/*     */   }
/*     */   
/*     */   void generateQuery(String event) {
/* 107 */     if (this.mc == null || this.mc.func_110432_I() == null || this.mc.func_110432_I().func_148256_e() == null)
/*     */       return; 
/* 109 */     String uuid = "" + this.mc.func_110432_I().func_148256_e().getId();
/* 110 */     String player_name = this.mc.func_110432_I().func_111285_a();
/* 111 */     String server_ip = "null";
/* 112 */     String player_prefix = Main.getConfigString(Main.CONFIG_MSG.BRO_PREFIX);
/*     */     
/* 114 */     if (this.mc.func_147104_D() == null)
/*     */       return; 
/* 116 */     server_ip = (this.mc.func_147104_D()).field_78845_b;
/* 117 */     sendQuery(uuid, player_name, player_prefix, "1.69", server_ip, event);
/*     */   }
/*     */ 
/*     */   
/*     */   void sendQuery(String uuid, String player_name, String player_prefix, String mod_version, String server_ip, String login_event) {
/* 122 */     MySerializer.addLoginMessage(uuid, player_name, player_prefix, mod_version, server_ip, login_event);
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\LoginHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */