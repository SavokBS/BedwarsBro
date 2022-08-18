/*     */ package com.dimchig.bedwarsbro;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.hints.HintsBaseRadar;
/*     */ import com.dimchig.bedwarsbro.hints.HintsFinder;
/*     */ import com.dimchig.bedwarsbro.hints.HintsPlayerScanner;
/*     */ import com.dimchig.bedwarsbro.hints.HintsValidator;
/*     */ import com.dimchig.bedwarsbro.hints.LobbyBlockPlacer;
/*     */ import com.dimchig.bedwarsbro.hints.WinEmote;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.gameevent.TickEvent;
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
/*     */ public class OnMyTickEvent
/*     */ {
/*  46 */   private static ArrayList<Integer> myfps = new ArrayList<Integer>();
/*     */   
/*  48 */   public static String prevScoreboard = "";
/*     */   
/*     */   public static boolean isHintsRadarBaseActive = false;
/*     */   
/*     */   public static boolean isHintsRadarPlayersActive = false;
/*     */   public static boolean isDangerAlertActive = false;
/*     */   public static boolean isHintsItemCounterActive = false;
/*     */   public static boolean isBetterShopActive = false;
/*     */   public static boolean isWinEmoteActive = false;
/*     */   public static boolean isParticleTrailActive = false;
/*  58 */   public static int SCANNER_FREQUENCY = 20;
/*  59 */   public static int SCANNER_FREQUENCY_CNT = 0;
/*     */   public static boolean FINDER_IS_SEARCH_LOOP = false;
/*  61 */   public static String FIND_PLAYER_COMMAND_SEARCH = "";
/*  62 */   public static long FIND_PLAYER_COMMAND_SEARCH_TIME = 0L;
/*  63 */   public static long time_bwmanipulator_cant_sneak = -1L;
/*     */ 
/*     */ 
/*     */   
/*     */   Minecraft mc;
/*     */ 
/*     */ 
/*     */   
/*     */   private KeyBinding keyTab;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean flag_rclick;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean flag_lclick;
/*     */ 
/*     */ 
/*     */   
/*     */   private KeyBinding key_lclick;
/*     */ 
/*     */ 
/*     */   
/*     */   private KeyBinding key_rclick;
/*     */ 
/*     */ 
/*     */   
/*     */   public OnMyTickEvent() {
/*  92 */     this.flag_rclick = false;
/*  93 */     this.flag_lclick = false;
/*     */     this.mc = Minecraft.func_71410_x();
/*     */     this.keyTab = this.mc.field_71474_y.field_74321_H;
/*     */     updateHintsBooleans();
/*     */   } @SubscribeEvent
/*     */   public void playerTick(TickEvent.ClientTickEvent event) {
/*  99 */     if (this.mc == null)
/* 100 */       return;  if (this.mc.field_71439_g == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 111 */     String s = ScoreboardManager.readRawScoreboard();
/* 112 */     if (s != null && s.length() >= 0) {
/* 113 */       String current_scoreboard = ColorCodesManager.removeColorCodes(s);
/* 114 */       if (!current_scoreboard.equals(prevScoreboard)) {
/* 115 */         prevScoreboard = current_scoreboard;
/* 116 */         CustomScoreboard.updateScoreboard();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 121 */     Main.shopManager.scan(isBetterShopActive);
/*     */     
/* 123 */     if (MyChatListener.IS_IN_GAME) {
/*     */       
/* 125 */       SCANNER_FREQUENCY_CNT++;
/* 126 */       if (SCANNER_FREQUENCY_CNT > SCANNER_FREQUENCY) {
/* 127 */         SCANNER_FREQUENCY_CNT = 0;
/*     */         
/* 129 */         ArrayList<HintsPlayerScanner.BWPlayer> players = HintsPlayerScanner.scanPlayers();
/* 130 */         HintsBaseRadar.scan(players, isHintsRadarBaseActive, isHintsRadarPlayersActive);
/* 131 */         if (isDangerAlertActive) Main.dangerAlert.scan(players, this.mc.field_71439_g);
/*     */         
/* 133 */         Main.generatorTimers.onTick();
/*     */       } 
/*     */       
/* 136 */       if (isHintsItemCounterActive) Main.itemTracker.scan();
/*     */ 
/*     */       
/* 139 */       if (FINDER_IS_SEARCH_LOOP) {
/* 140 */         HintsFinder.findAll(false);
/*     */       }
/*     */ 
/*     */       
/* 144 */       if (time_bwmanipulator_cant_sneak > 0L) {
/* 145 */         long t = (new Date()).getTime();
/* 146 */         if (time_bwmanipulator_cant_sneak > t) {
/* 147 */           KeyBinding key = (Minecraft.func_71410_x()).field_71474_y.field_74311_E;
/* 148 */           KeyBinding.func_74510_a(key.func_151463_i(), false);
/*     */         } else {
/* 150 */           time_bwmanipulator_cant_sneak = -1L;
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 156 */       Main.guiOnScreen.setDiamonds(-1);
/* 157 */       Main.guiOnScreen.setEmeralds(-1);
/*     */     } 
/*     */     
/* 160 */     if (LobbyBlockPlacer.state == true) if ((!MyChatListener.IS_IN_GAME || Main.shopManager.findItemInHotbar("Наблюдение за") != -1) && 
/* 161 */         Main.shopManager.findItemInHotbar("Выбор коман") == -1) LobbyBlockPlacer.place();
/*     */     
/*     */     
/* 164 */     if (FIND_PLAYER_COMMAND_SEARCH.length() > 0) {
/* 165 */       long t = (new Date()).getTime();
/* 166 */       if (t - FIND_PLAYER_COMMAND_SEARCH_TIME > 30000L) {
/* 167 */         ChatSender.addText("&cНе удалось найти &e" + FIND_PLAYER_COMMAND_SEARCH);
/* 168 */         MyChatListener.playSound(MyChatListener.SOUND_REJECT);
/* 169 */         FIND_PLAYER_COMMAND_SEARCH = "";
/*     */       } else {
/* 171 */         Main.commandFindPlayerByName.work(FIND_PLAYER_COMMAND_SEARCH);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 176 */     if (isWinEmoteActive) WinEmote.handleEmote();
/*     */ 
/*     */ 
/*     */     
/* 180 */     if (isParticleTrailActive) Main.particleTrail.drawPlayerTrail();
/*     */     
/* 182 */     MyChatListener.handleBedwarsMeowMessagesQuee();
/*     */     
/* 184 */     if (this.keyTab.func_151470_d()) {
/* 185 */       Main.namePlateRenderer.updateGameTab();
/*     */     }
/*     */ 
/*     */     
/* 189 */     if (this.key_rclick.func_151470_d())
/* 190 */     { if (this.flag_rclick == true) {
/* 191 */         this.flag_rclick = false;
/*     */       
/*     */       } }
/*     */     
/* 195 */     else if (!this.flag_rclick) { this.flag_rclick = true; }
/*     */ 
/*     */ 
/*     */     
/* 199 */     if (this.key_lclick.func_151470_d())
/* 200 */     { if (this.flag_lclick == true) {
/* 201 */         this.flag_lclick = false;
/* 202 */         Main.particlesAlwaysSharpness.onMyLeftClick();
/*     */       
/*     */       }
/*     */        }
/*     */     
/* 207 */     else if (!this.flag_lclick) { this.flag_lclick = true; }
/*     */   
/*     */   }
/*     */   
/*     */   public void updateHintsBooleans() {
/*     */     this;
/*     */     isHintsRadarBaseActive = HintsValidator.isRadarActive();
/*     */     this;
/*     */     isHintsRadarPlayersActive = HintsValidator.isHintsRadarPlayersActive();
/*     */     this;
/*     */     isDangerAlertActive = HintsValidator.isDangerAlertActive();
/*     */     this;
/*     */     isHintsItemCounterActive = HintsValidator.isItemCounterActive();
/*     */     this;
/*     */     isBetterShopActive = HintsValidator.isBetterShopActive();
/*     */     this;
/*     */     isWinEmoteActive = HintsValidator.isWinEmoteActive();
/*     */     this;
/*     */     isParticleTrailActive = HintsValidator.isParticleTrailActive();
/*     */     this;
/*     */     if (!isHintsItemCounterActive) {
/*     */       Main.guiOnScreen.setDiamonds(-1);
/*     */       Main.guiOnScreen.setEmeralds(-1);
/*     */     } 
/*     */     this.key_lclick = this.mc.field_71474_y.field_74312_F;
/*     */     this.key_rclick = this.mc.field_71474_y.field_74313_G;
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\OnMyTickEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */