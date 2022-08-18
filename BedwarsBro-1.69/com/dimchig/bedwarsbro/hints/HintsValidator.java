/*     */ package com.dimchig.bedwarsbro.hints;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.ChatSender;
/*     */ import com.dimchig.bedwarsbro.Main;
/*     */ import com.dimchig.bedwarsbro.MyChatListener;
/*     */ import net.minecraft.client.Minecraft;
/*     */ 
/*     */ 
/*     */ public class HintsValidator
/*     */ {
/*     */   public static boolean isPasswordCorrect() {
/*  12 */     String pwd = GetHintsPWD.getPWD();
/*  13 */     if (pwd == null) return false; 
/*  14 */     if (Minecraft.func_71410_x() == null || (Minecraft.func_71410_x()).field_71439_g == null) return false;
/*     */     
/*  16 */     if (Main.isPropUserBanned((Minecraft.func_71410_x()).field_71439_g.func_70005_c_())) return false;
/*     */     
/*  18 */     String mod_last_version = Main.getPropModLastVersion();
/*  19 */     if (mod_last_version != null && !"1.69".equals(mod_last_version)) {
/*  20 */       MyChatListener.sendUpdateModMessage();
/*  21 */       return false;
/*     */     } 
/*     */     
/*  24 */     if (pwd.equals("allowForAll")) {
/*  25 */       Main.clientConfig.get(Main.CONFIG_MSG.PASSWORD.text).set("Пока-что не надо, мод доступен всем!");
/*  26 */       Main.saveConfig();
/*  27 */       return true;
/*     */     } 
/*  29 */     boolean b = Main.getConfigString(Main.CONFIG_MSG.PASSWORD).equals(pwd);
/*  30 */     if (!b) {
/*  31 */       Main.clientConfig.get(Main.CONFIG_MSG.PASSWORD.text).set("");
/*  32 */       Main.saveConfig();
/*  33 */       ChatSender.addText("&cBedwars&fBro: &cНеверный пароль! Смотри config &7(&e/bwbro&7)");
/*     */     } 
/*  35 */     return b;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isBedScannerActive() {
/*  40 */     return isPasswordCorrect();
/*     */   }
/*     */   
/*     */   public static boolean isBedESPActive() {
/*  44 */     return (Main.getConfigBool(Main.CONFIG_MSG.BED_ESP) && isPasswordCorrect());
/*     */   }
/*     */   
/*     */   public static boolean isRadarActive() {
/*  48 */     return (Main.getConfigBool(Main.CONFIG_MSG.RADAR) && isPasswordCorrect());
/*     */   }
/*     */   
/*     */   public static boolean isHintsRadarPlayersActive() {
/*  52 */     return (Main.getConfigBool(Main.CONFIG_MSG.RADAR_PLAYERS) && isPasswordCorrect());
/*     */   }
/*     */   
/*     */   public static boolean isRadarIconActive() {
/*  56 */     return (Main.getConfigBool(Main.CONFIG_MSG.RADAR_ICON) && isPasswordCorrect());
/*     */   }
/*     */   
/*     */   public static boolean isRadarChatMessage() {
/*  60 */     return (Main.getConfigBool(Main.CONFIG_MSG.RADAR_MESSAGES) && isPasswordCorrect());
/*     */   }
/*     */   
/*     */   public static boolean isRadarChatMessageWithColors() {
/*  64 */     return (Main.getConfigBool(Main.CONFIG_MSG.RADAR_MESSAGES_WITH_COLORS) && isPasswordCorrect());
/*     */   }
/*     */   
/*     */   public static boolean isItemCounterActive() {
/*  68 */     return (Main.getConfigBool(Main.CONFIG_MSG.ITEM_COUNTER) && isPasswordCorrect());
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isFinderActive() {
/*  73 */     return isPasswordCorrect();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isResourceFinderActive() {
/*  78 */     return isPasswordCorrect();
/*     */   }
/*     */   
/*     */   public static boolean isWinEmoteActive() {
/*  82 */     return (Main.getConfigBool(Main.CONFIG_MSG.WIN_EMOTE) && isPasswordCorrect());
/*     */   }
/*     */   
/*     */   public static boolean isMinimapActive() {
/*  86 */     return (Main.getConfigBool(Main.CONFIG_MSG.MINIMAP) && isPasswordCorrect());
/*     */   }
/*     */   public static boolean isParticlesActive() {
/*  89 */     return (Main.getConfigBool(Main.CONFIG_MSG.CUSTOM_PARTICLES) && isPasswordCorrect());
/*     */   }
/*     */   public static boolean isPotionEffectsTrackerActive() {
/*  92 */     return (Main.getConfigBool(Main.CONFIG_MSG.POTION_TRACKER) && isPasswordCorrect());
/*     */   }
/*     */   public static boolean isPotionEffectsTrackerSoundsActive() {
/*  95 */     return (Main.getConfigBool(Main.CONFIG_MSG.POTION_TRACKER_SOUNDS) && isPasswordCorrect());
/*     */   }
/*     */   public static boolean isDangerAlertActive() {
/*  98 */     return (Main.getConfigBool(Main.CONFIG_MSG.DANGER_ALERT) && isPasswordCorrect());
/*     */   }
/*     */   
/*     */   public static boolean isAutoSprintActive() {
/* 102 */     return (Main.getConfigBool(Main.CONFIG_MSG.AUTO_SPRINT) && isPasswordCorrect());
/*     */   }
/*     */   
/*     */   public static boolean isAutoWaterDropActive() {
/* 106 */     return (Main.getConfigBool(Main.CONFIG_MSG.AUTO_WATER_DROP) && isPasswordCorrect());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isBedwarsMeowActive() {
/* 112 */     return (Main.getConfigBool(Main.CONFIG_MSG.BEDWARS_MEOW) && isPasswordCorrect());
/*     */   }
/*     */   public static boolean isBedwarsMeowColorsActive() {
/* 115 */     return (Main.getConfigBool(Main.CONFIG_MSG.BEDWARS_MEOW_WITH_COLORS) && isPasswordCorrect());
/*     */   }
/*     */   public static boolean isBedwarsMeowColorsRainbowModeActive() {
/* 118 */     return (Main.getConfigBool(Main.CONFIG_MSG.BEDWARS_MEOW_WITH_COLORS_RAINBOW) && isPasswordCorrect());
/*     */   }
/*     */   
/*     */   public static boolean isBetterShopActive() {
/* 122 */     return (Main.getConfigBool(Main.CONFIG_MSG.BETTER_SHOP) && isPasswordCorrect());
/*     */   }
/*     */   public static boolean isParticleTrailActive() {
/* 125 */     return Main.getConfigBool(Main.CONFIG_MSG.PARTICLE_TRAIL);
/*     */   }
/*     */   public static boolean isParticleTrailRainbowActive() {
/* 128 */     return Main.getConfigBool(Main.CONFIG_MSG.PARTICLE_TRAIL_RAINBOW);
/*     */   }
/*     */   
/*     */   public static boolean isInvulnerableTimerActive() {
/* 132 */     return (Main.getConfigBool(Main.CONFIG_MSG.INVULNERABLE_TIMER) && isPasswordCorrect());
/*     */   }
/*     */   public static boolean isInvulnerableTimerSoundsActive() {
/* 135 */     return (Main.getConfigBool(Main.CONFIG_MSG.INVULNERABLE_TIMER_SOUNDS) && isPasswordCorrect());
/*     */   }
/*     */   
/*     */   public static boolean isNamePlateActive() {
/* 139 */     return Main.getConfigBool(Main.CONFIG_MSG.NAMEPLATE);
/*     */   }
/*     */   public static boolean isNamePlateRainbowActive() {
/* 142 */     return Main.getConfigBool(Main.CONFIG_MSG.NAMEPLATE_RAINBOW);
/*     */   }
/*     */   
/*     */   public static boolean isResourcesHologramActive() {
/* 146 */     return Main.getConfigBool(Main.CONFIG_MSG.RESOURCES_HOLOGRAM);
/*     */   }
/*     */   
/*     */   public static int getRainbowSpeed() {
/* 150 */     return Main.getConfigInt(Main.CONFIG_MSG.RAINBOW_SPEED);
/*     */   }
/*     */   
/*     */   public static String getNamePlateCustomColor() {
/* 154 */     return Main.getConfigString(Main.CONFIG_MSG.NAMEPLATE_RAINBOW_CONSTANT_COLOR);
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\hints\HintsValidator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */