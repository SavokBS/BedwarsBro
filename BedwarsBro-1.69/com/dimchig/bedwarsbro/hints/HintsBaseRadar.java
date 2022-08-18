/*     */ package com.dimchig.bedwarsbro.hints;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.ChatSender;
/*     */ import com.dimchig.bedwarsbro.ColorCodesManager;
/*     */ import com.dimchig.bedwarsbro.CustomScoreboard;
/*     */ import com.dimchig.bedwarsbro.Main;
/*     */ import com.dimchig.bedwarsbro.MyChatListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockStainedGlass;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.util.BlockPos;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HintsBaseRadar
/*     */ {
/*  30 */   public static int RADAR_RANGE_1 = 8;
/*  31 */   public static int RADAR_RANGE_2 = 20;
/*  32 */   public static int RADAR_RANGE_3 = -1;
/*  33 */   public static int basePosX = -99999;
/*  34 */   public static int basePosY = -99999;
/*  35 */   public static int basePosZ = -99999;
/*     */   
/*     */   public static ArrayList<RadarAlert> alerts;
/*  38 */   public static CustomScoreboard.TEAM_COLOR mod_team_color = CustomScoreboard.TEAM_COLOR.NONE;
/*  39 */   public static int RADAR_TIME_TRESHOLD = 10000;
/*  40 */   public static String prefix = MyChatListener.PREFIX_HINT_RADAR;
/*     */   
/*     */   public static boolean GAME_isBedBroken;
/*     */   public static List<CustomScoreboard.BedwarsTeam> game_bw_teams;
/*     */   
/*     */   public void setBase(int x, int y, int z) {
/*  46 */     this; basePosX = x;
/*  47 */     this; basePosY = y;
/*  48 */     this; basePosZ = z;
/*     */     
/*  50 */     alerts = new ArrayList<RadarAlert>();
/*  51 */     game_bw_teams = new ArrayList<CustomScoreboard.BedwarsTeam>();
/*  52 */     mod_team_color = CustomScoreboard.TEAM_COLOR.NONE;
/*  53 */     GAME_isBedBroken = false;
/*     */     
/*  55 */     recognizeTeamColor();
/*     */   }
/*     */   
/*     */   public static class RadarAlert
/*     */   {
/*     */     public HintsPlayerScanner.BWPlayer player;
/*     */     public int range_id;
/*     */     public double posY;
/*     */     public long time;
/*     */     
/*     */     public RadarAlert(HintsPlayerScanner.BWPlayer player, double posY, int range_id) {
/*  66 */       this.player = player;
/*  67 */       this.range_id = range_id;
/*  68 */       this.posY = posY;
/*  69 */       this.time = (new Date()).getTime();
/*     */     }
/*     */     
/*     */     public long getTimePassed() {
/*  73 */       return (new Date()).getTime() - this.time;
/*     */     }
/*     */   }
/*     */   
/*     */   public static void recognizeTeamColor() {
/*  78 */     List<CustomScoreboard.BedwarsTeam> teams = CustomScoreboard.readBedwarsGame();
/*  79 */     game_bw_teams = teams;
/*  80 */     mod_team_color = MyChatListener.getEntityTeamColor((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g);
/*  81 */     if (mod_team_color == CustomScoreboard.TEAM_COLOR.NONE) {
/*  82 */       ChatSender.addText(prefix + "&cНе удалось установить цвет твоей команды!");
/*     */     }
/*     */   }
/*     */   
/*     */   public static void checkBedState() {
/*  87 */     if (GAME_isBedBroken == true)
/*  88 */       return;  List<CustomScoreboard.BedwarsTeam> teams = CustomScoreboard.readBedwarsGame();
/*  89 */     for (CustomScoreboard.BedwarsTeam team : teams) {
/*  90 */       if (team.color == mod_team_color && 
/*  91 */         team.state != CustomScoreboard.TEAM_STATE.BED_ALIVE) {
/*  92 */         GAME_isBedBroken = true;
/*  93 */         ChatSender.addText(prefix + "&cКровать сломана!" + (Main.getConfigBool(Main.CONFIG_MSG.RADAR_PLAYERS) ? " Радар переключен в режим &lигроков&c!" : ""));
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void scan(ArrayList<HintsPlayerScanner.BWPlayer> players, boolean isRadarBaseActive, boolean isPlayerRadarActive) {
/* 101 */     if (basePosX == -99999 || alerts == null) {
/*     */       return;
/*     */     }
/* 104 */     boolean isBedAlert = true;
/* 105 */     if (GAME_isBedBroken == true) isBedAlert = false;
/*     */     
/* 107 */     if (isBedAlert && !isRadarBaseActive)
/* 108 */       return;  if (!isBedAlert && !isPlayerRadarActive)
/*     */       return; 
/* 110 */     if (mod_team_color == CustomScoreboard.TEAM_COLOR.NONE) {
/* 111 */       if ((Minecraft.func_71410_x()).field_71441_e.func_82737_E() % 50L == 0L) {
/* 112 */         recognizeTeamColor();
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 117 */     if ((new Date()).getTime() - MyChatListener.GAME_start_time < 3000L) {
/*     */       return;
/*     */     }
/*     */     
/* 121 */     if (isBedAlert && (Minecraft.func_71410_x()).field_71441_e.field_73012_v.nextInt(5) == 0) checkBedState();
/*     */ 
/*     */ 
/*     */     
/* 125 */     for (HintsPlayerScanner.BWPlayer player : players) {
/*     */       
/* 127 */       int player_cnt = 1;
/*     */ 
/*     */       
/* 130 */       for (RadarAlert alert : alerts) {
/* 131 */         if (alert.player.team_color == player.team_color && player.distToPlayer <= alert.player.distToPlayer && !player.name.equals(alert.player.name)) {
/* 132 */           player_cnt++;
/*     */         }
/*     */       } 
/*     */       
/* 136 */       int dist = 99999;
/* 137 */       if (isBedAlert) {
/* 138 */         dist = (int)Math.sqrt(Math.pow(player.posX - basePosX, 2.0D) + Math.pow(player.posZ - basePosZ, 2.0D));
/*     */       } else {
/* 140 */         dist = (int)Math.sqrt(Math.pow(player.posX - (Minecraft.func_71410_x()).field_71439_g.field_70165_t, 2.0D) + Math.pow(player.posZ - (Minecraft.func_71410_x()).field_71439_g.field_70161_v, 2.0D));
/*     */       } 
/*     */ 
/*     */       
/* 144 */       if ((Minecraft.func_71410_x()).field_71439_g == null)
/* 145 */         return;  double posY = isBedAlert ? (player.posY - basePosY) : (player.posY - (Minecraft.func_71410_x()).field_71439_g.field_70163_u);
/*     */       
/* 147 */       if (dist < RADAR_RANGE_1) {
/* 148 */         alertPlayerRange(player, posY, 1, player_cnt, isBedAlert); continue;
/* 149 */       }  if (dist < RADAR_RANGE_2) {
/* 150 */         alertPlayerRange(player, posY, 2, player_cnt, isBedAlert);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void alertPlayerRange(HintsPlayerScanner.BWPlayer player, double posY, int range_id, int count, boolean isBedAlert) {
/* 158 */     if (Main.shopManager.findItemInHotbar("Наблюдение за") != -1)
/*     */       return; 
/* 160 */     if (player.team_color == CustomScoreboard.TEAM_COLOR.NONE || player.team_color == mod_team_color)
/*     */       return; 
/* 162 */     if (basePosY - player.posY >= 5.0D)
/*     */       return; 
/* 164 */     for (int i = 0; i < alerts.size() && 
/* 165 */       i >= 0 && i < alerts.size(); i++) {
/*     */       
/* 167 */       RadarAlert alert = alerts.get(i);
/*     */       
/* 169 */       if (alert.getTimePassed() > RADAR_TIME_TRESHOLD) {
/* 170 */         alerts.remove(alert);
/* 171 */         i = 0;
/*     */       
/*     */       }
/* 174 */       else if (alert.player.name.equals(player.name)) {
/* 175 */         if (alert.range_id <= range_id)
/* 176 */           return;  alerts.remove(alert);
/* 177 */         i--;
/*     */       } 
/*     */     } 
/* 180 */     RadarAlert radarAlert = new RadarAlert(player, posY, range_id);
/* 181 */     alerts.add(radarAlert);
/*     */     
/* 183 */     String str = "";
/* 184 */     String team_color = CustomScoreboard.getCodeByTeamColor(player.team_color);
/* 185 */     String team_name_1_person = CustomScoreboard.getTeamNameSinglePlayerByTeamColor(player.team_color);
/*     */     
/* 187 */     double height_diff = posY;
/*     */     
/* 189 */     if (range_id == 1) {
/*     */ 
/*     */       
/* 192 */       if (height_diff > 5.0D) {
/* 193 */         str = str + (isBedAlert ? "&cСверху базы" : "&cСверху");
/*     */       } else {
/* 195 */         str = str + (isBedAlert ? "&cНас ломает" : "&cРядом");
/*     */       } 
/* 197 */       MyChatListener.playSound(MyChatListener.SOUND_RADAR_CLOSE);
/* 198 */       Main.guiRadarIcon.show(isBedAlert);
/* 199 */     } else if (range_id == 2) {
/* 200 */       if (height_diff > 7.0D) {
/* 201 */         str = str + (isBedAlert ? "&eНас сверху рашит" : "&eСверху");
/*     */       } else {
/* 203 */         str = str + (isBedAlert ? "&eНас рашит" : "&eРядом");
/*     */       } 
/* 205 */       MyChatListener.playSound(MyChatListener.SOUND_RADAR_FAR);
/*     */     } 
/*     */     
/* 208 */     str = str + "&" + team_color;
/* 209 */     if (count > 1) {
/* 210 */       str = str + " " + count + "-й";
/*     */     }
/*     */     
/* 213 */     str = str + " " + team_name_1_person;
/*     */ 
/*     */     
/* 216 */     if (HintsValidator.isRadarChatMessage() && isBedAlert) {
/* 217 */       String s = str;
/* 218 */       String str_final = str;
/*     */       
/* 220 */       if (!HintsValidator.isRadarChatMessageWithColors()) s = ColorCodesManager.removeColorCodes(s);
/*     */       
/* 222 */       (Minecraft.func_71410_x()).field_71439_g.func_71165_d(s);
/*     */     } 
/* 224 */     ChatSender.addText(prefix + str);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void visualizeZone() {
/* 229 */     drawZone(8, EnumDyeColor.RED);
/* 230 */     drawZone(20, EnumDyeColor.YELLOW);
/* 231 */     drawZone(35, EnumDyeColor.GREEN);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawZone(int range, EnumDyeColor block_color) {
/* 236 */     for (int yi = 2; yi <= 2; yi++) {
/* 237 */       for (int xi = -range * 2; xi < range * 2; xi++) {
/* 238 */         for (int zi = -range * 2; zi < range * 2; zi++) {
/* 239 */           int bx = basePosX + xi;
/* 240 */           int by = basePosY + yi;
/* 241 */           int bz = basePosZ + zi;
/*     */           
/* 243 */           int dist = (int)Math.sqrt(Math.pow((bx - basePosX), 2.0D) + Math.pow((bz - basePosZ), 2.0D));
/* 244 */           if (dist == range) {
/* 245 */             Block block = (Minecraft.func_71410_x()).field_71441_e.func_180495_p(new BlockPos(bx, by, bz)).func_177230_c();
/* 246 */             if (block.func_149732_F().contains("air"))
/* 247 */               (Minecraft.func_71410_x()).field_71441_e.func_175656_a(new BlockPos(bx, by, bz), Blocks.field_150399_cn.func_176223_P().func_177226_a((IProperty)BlockStainedGlass.field_176547_a, (Comparable)block_color)); 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\hints\HintsBaseRadar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */