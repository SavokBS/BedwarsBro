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
/*     */ import net.minecraft.client.gui.GuiScreen;
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
/*     */ 
/*     */ public class OnMyTickEvent
/*     */ {
/*  48 */   private static ArrayList<Integer> myfps = new ArrayList<Integer>();
/*     */   
/*  50 */   public static String prevScoreboard = "";
/*     */   
/*     */   public static boolean isHintsRadarBaseActive = false;
/*     */   
/*     */   public static boolean isHintsRadarPlayersActive = false;
/*     */   public static boolean isDangerAlertActive = false;
/*     */   public static boolean isHintsItemCounterActive = false;
/*     */   public static boolean isBetterShopActive = false;
/*     */   public static boolean isWinEmoteActive = false;
/*     */   public static boolean isParticleTrailActive = false;
/*  60 */   public static int SCANNER_FREQUENCY = 10;
/*  61 */   public static int SCANNER_FREQUENCY_CNT = 0;
/*     */   public static boolean FINDER_IS_SEARCH_LOOP = false;
/*  63 */   public static String FIND_PLAYER_COMMAND_SEARCH = "";
/*  64 */   public static long FIND_PLAYER_COMMAND_SEARCH_TIME = 0L;
/*  65 */   public static long time_bwmanipulator_cant_sneak = -1L;
/*     */   Minecraft mc;
/*     */   private KeyBinding keyTab;
/*  68 */   public static GuiScreen gui2open = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean flag_rclick;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean flag_lclick;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private KeyBinding key_lclick;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private KeyBinding key_rclick;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OnMyTickEvent() {
/*  95 */     this.flag_rclick = false;
/*  96 */     this.flag_lclick = false;
/*     */     this.mc = Minecraft.func_71410_x();
/*     */     this.keyTab = this.mc.field_71474_y.field_74321_H;
/*     */     updateHintsBooleans();
/*     */   } @SubscribeEvent
/*     */   public void playerTick(TickEvent.ClientTickEvent event) {
/* 102 */     if (this.mc == null)
/* 103 */       return;  if (this.mc.field_71439_g == null) {
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
/* 114 */     String s = ScoreboardManager.readRawScoreboard();
/* 115 */     if (s != null && s.length() >= 0) {
/* 116 */       String current_scoreboard = ColorCodesManager.removeColorCodes(s);
/* 117 */       if (!current_scoreboard.equals(prevScoreboard)) {
/* 118 */         prevScoreboard = current_scoreboard;
/* 119 */         CustomScoreboard.updateScoreboard();
/*     */       } 
/*     */     } 
/*     */     
/* 123 */     if (gui2open != null) {
/* 124 */       Minecraft.func_71410_x().func_147108_a(gui2open);
/* 125 */       gui2open = null;
/*     */     } 
/*     */     
/* 128 */     Main.shopManager.scan(isBetterShopActive);
/*     */     
/* 130 */     if (MyChatListener.IS_IN_GAME) {
/* 131 */       SCANNER_FREQUENCY_CNT++;
/* 132 */       if (SCANNER_FREQUENCY_CNT > SCANNER_FREQUENCY) {
/* 133 */         SCANNER_FREQUENCY_CNT = 0;
/*     */         
/* 135 */         ArrayList<HintsPlayerScanner.BWPlayer> players = HintsPlayerScanner.scanPlayers();
/* 136 */         HintsBaseRadar.scan(players, isHintsRadarBaseActive, isHintsRadarPlayersActive);
/* 137 */         if (isDangerAlertActive) Main.dangerAlert.scan(players, this.mc.field_71439_g);
/*     */         
/* 139 */         Main.generatorTimers.onTick();
/*     */       } 
/*     */       
/* 142 */       Main.bedAutoTool.handleTools();
/*     */       
/* 144 */       if (isHintsItemCounterActive) Main.itemTracker.scan();
/*     */ 
/*     */       
/* 147 */       if (FINDER_IS_SEARCH_LOOP) {
/* 148 */         HintsFinder.findAll(false);
/*     */       }
/*     */ 
/*     */       
/* 152 */       if (time_bwmanipulator_cant_sneak > 0L) {
/* 153 */         long t = (new Date()).getTime();
/* 154 */         if (time_bwmanipulator_cant_sneak > t) {
/* 155 */           KeyBinding key = (Minecraft.func_71410_x()).field_71474_y.field_74311_E;
/* 156 */           KeyBinding.func_74510_a(key.func_151463_i(), false);
/*     */         } else {
/* 158 */           time_bwmanipulator_cant_sneak = -1L;
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 164 */       Main.guiOnScreen.setDiamonds(-1);
/* 165 */       Main.guiOnScreen.setEmeralds(-1);
/*     */     } 
/*     */     
/* 168 */     if (LobbyBlockPlacer.state == true) if ((!MyChatListener.IS_IN_GAME || Main.shopManager.findItemInHotbar("Наблюдение за") != -1) && 
/* 169 */         Main.shopManager.findItemInHotbar("Выбор коман") == -1) LobbyBlockPlacer.place();
/*     */     
/*     */     
/* 172 */     if (FIND_PLAYER_COMMAND_SEARCH.length() > 0) {
/* 173 */       long t = (new Date()).getTime();
/* 174 */       if (t - FIND_PLAYER_COMMAND_SEARCH_TIME > 30000L) {
/* 175 */         ChatSender.addText("&cНе удалось найти &e" + FIND_PLAYER_COMMAND_SEARCH);
/* 176 */         MyChatListener.playSound(MyChatListener.SOUND_REJECT);
/* 177 */         FIND_PLAYER_COMMAND_SEARCH = "";
/*     */       } else {
/* 179 */         Main.commandFindPlayerByName.work(FIND_PLAYER_COMMAND_SEARCH);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 184 */     if (isWinEmoteActive) WinEmote.handleEmote();
/*     */ 
/*     */ 
/*     */     
/* 188 */     if (isParticleTrailActive) Main.particleTrail.drawPlayerTrail();
/*     */     
/* 190 */     MyChatListener.handleBedwarsMeowMessagesQuee();
/*     */     
/* 192 */     if (this.keyTab.func_151470_d()) {
/* 193 */       Main.namePlateRenderer.updateGameTab();
/*     */     }
/*     */ 
/*     */     
/* 197 */     if (this.key_rclick.func_151470_d())
/* 198 */     { if (this.flag_rclick == true) {
/* 199 */         this.flag_rclick = false;
/*     */       
/*     */       } }
/*     */     
/* 203 */     else if (!this.flag_rclick) { this.flag_rclick = true; }
/*     */ 
/*     */ 
/*     */     
/* 207 */     if (this.key_lclick.func_151470_d())
/* 208 */     { if (this.flag_lclick == true) {
/* 209 */         this.flag_lclick = false;
/* 210 */         Main.particlesAlwaysSharpness.onMyLeftClick();
/*     */       
/*     */       }
/*     */        }
/*     */     
/* 215 */     else if (!this.flag_lclick) { this.flag_lclick = true; }
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


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\OnMyTickEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */