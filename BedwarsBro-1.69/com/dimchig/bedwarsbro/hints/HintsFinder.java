/*     */ package com.dimchig.bedwarsbro.hints;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.ChatSender;
/*     */ import com.dimchig.bedwarsbro.ColorCodesManager;
/*     */ import com.dimchig.bedwarsbro.CustomScoreboard;
/*     */ import com.dimchig.bedwarsbro.MyChatListener;
/*     */ import com.dimchig.bedwarsbro.OnMyTickEvent;
/*     */ import com.dimchig.bedwarsbro.commands.CommandHintsFinderLookAtPlayer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.event.ClickEvent;
/*     */ import net.minecraft.event.HoverEvent;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.IChatComponent;
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
/*     */ public class HintsFinder
/*     */ {
/*     */   public static void findAll(boolean withMessageOnFail) {
/*  32 */     ArrayList<HintsPlayerScanner.BWPlayer> players = HintsPlayerScanner.scanPlayers();
/*  33 */     if (players == null) {
/*  34 */       ChatSender.addText(MyChatListener.PREFIX_HINT_FINDER + "&cОшибка"); return;
/*     */     } 
/*  36 */     if (players.size() <= 1) {
/*  37 */       OnMyTickEvent.FINDER_IS_SEARCH_LOOP = true;
/*     */       
/*     */       return;
/*     */     } 
/*  41 */     String mod_player_name = ColorCodesManager.removeColorCodes((Minecraft.func_71410_x()).field_71439_g.func_70005_c_());
/*     */     
/*  43 */     List<CustomScoreboard.BedwarsTeam> teams = CustomScoreboard.readBedwarsGame();
/*     */ 
/*     */     
/*  46 */     CustomScoreboard.TEAM_COLOR mod_team_color = CustomScoreboard.TEAM_COLOR.NONE;
/*  47 */     double mod_pos_x = (Minecraft.func_71410_x()).field_71439_g.field_70165_t;
/*  48 */     double mod_pos_y = (Minecraft.func_71410_x()).field_71439_g.field_70163_u;
/*  49 */     double mod_pos_z = (Minecraft.func_71410_x()).field_71439_g.field_70161_v;
/*     */ 
/*     */     
/*  52 */     label88: for (CustomScoreboard.BedwarsTeam team : teams) {
/*  53 */       for (CustomScoreboard.BedwarsPlayer p : team.players) {
/*  54 */         if (p.name.equals(mod_player_name)) {
/*  55 */           mod_team_color = team.color;
/*     */           
/*     */           break label88;
/*     */         } 
/*     */       } 
/*     */     } 
/*  61 */     String str = "";
/*  62 */     int cnt_found = 0;
/*  63 */     HintsPlayerScanner.BWPlayer closest_player = null;
/*  64 */     boolean isFirst = true;
/*  65 */     int min_distance = 9999;
/*  66 */     for (HintsPlayerScanner.BWPlayer player : players) {
/*  67 */       if (player.name.equals(mod_player_name)) {
/*     */         continue;
/*     */       }
/*  70 */       boolean isTeamFound = false;
/*  71 */       for (CustomScoreboard.BedwarsTeam team : teams) {
/*  72 */         for (CustomScoreboard.BedwarsPlayer p : team.players) {
/*  73 */           if (p.name.equals(player.name)) {
/*  74 */             player.team_color = team.color;
/*  75 */             isTeamFound = true;
/*     */             break;
/*     */           } 
/*     */         } 
/*  79 */         if (isTeamFound)
/*     */           break; 
/*     */       } 
/*  82 */       if (player.team_color == CustomScoreboard.TEAM_COLOR.NONE || player.team_color == mod_team_color) {
/*     */         continue;
/*     */       }
/*  85 */       int dist = (int)Math.sqrt(Math.pow(mod_pos_x - player.posX, 2.0D) + Math.pow(mod_pos_z - player.posZ, 2.0D));
/*  86 */       if (dist < min_distance) {
/*  87 */         min_distance = dist;
/*  88 */         closest_player = player;
/*     */       } 
/*     */       
/*  91 */       if (isFirst) {
/*  92 */         isFirst = false;
/*     */       }
/*     */       
/*  95 */       String stars = "";
/*  96 */       String player_color_code = "&" + CustomScoreboard.getCodeByTeamColor(player.team_color);
/*  97 */       str = MyChatListener.PREFIX_HINT_FINDER + player_color_code + "" + player.name;
/*  98 */       String hoverText = "&7(&f" + (int)player.posX + "&7, &f" + (int)player.posY + "&7, &f" + (int)player.posY + "&7, &c" + dist + "&7)";
/*     */       
/* 100 */       if (player.armourLevel == BWItemsHandler.BWItemArmourLevel.LEATHER) hoverText = hoverText + " &7Без брони"; 
/* 101 */       if (player.armourLevel == BWItemsHandler.BWItemArmourLevel.CHAIN) hoverText = hoverText + " &7Кольчуга"; 
/* 102 */       if (player.armourLevel == BWItemsHandler.BWItemArmourLevel.IRON) hoverText = hoverText + " &fЖелезник"; 
/* 103 */       if (player.armourLevel == BWItemsHandler.BWItemArmourLevel.DIAMOND) {
/* 104 */         hoverText = hoverText + " &bАлмазник";
/* 105 */         stars = stars + "&b&l*";
/*     */       } 
/*     */       
/* 108 */       if (player.item_in_hand != null) {
/* 109 */         if (player.item_in_hand.type == BWItemsHandler.BWItemType.BOW) {
/* 110 */           hoverText = hoverText + "&8, &cЛучник";
/* 111 */           stars = stars + "&c&l*";
/*     */         } 
/* 113 */         if (player.item_in_hand.type == BWItemsHandler.BWItemType.SWORD) {
/* 114 */           if (player.item_in_hand.toolLevel == BWItemsHandler.BWItemToolLevel.WOOD) hoverText = hoverText + "&8, &7Деревянный меч"; 
/* 115 */           if (player.item_in_hand.toolLevel == BWItemsHandler.BWItemToolLevel.STONE) hoverText = hoverText + "&8, &7Каменный меч"; 
/* 116 */           if (player.item_in_hand.toolLevel == BWItemsHandler.BWItemToolLevel.IRON) hoverText = hoverText + "&8, &fЖелезный меч"; 
/* 117 */           if (player.item_in_hand.toolLevel == BWItemsHandler.BWItemToolLevel.DIAMOND) {
/* 118 */             hoverText = hoverText + "&8, &bАлмазный меч";
/* 119 */             stars = stars + "&6&l*";
/*     */           } 
/*     */         } 
/* 122 */         if (player.item_in_hand.type == BWItemsHandler.BWItemType.POTION_STRENGTH) {
/* 123 */           hoverText = hoverText + "&8, &cСилка";
/* 124 */           stars = stars + "&4&l*";
/*     */         } 
/* 126 */         if (player.item_in_hand.type == BWItemsHandler.BWItemType.PEARL) {
/* 127 */           hoverText = hoverText + "&8, &aПерл";
/* 128 */           stars = stars + "&9&l*";
/*     */         } 
/*     */       } 
/*     */       
/* 132 */       str = str + stars;
/*     */       
/* 134 */       EntityPlayerSP mod_player = (Minecraft.func_71410_x()).field_71439_g;
/* 135 */       float angle_diff = (float)Math.toDegrees(Math.atan2(player.posZ - mod_player.field_70161_v, player.posX - mod_player.field_70165_t));
/* 136 */       angle_diff += (180.0F - mod_player.field_70177_z) % 360.0F;
/* 137 */       angle_diff = (angle_diff + 90.0F + 720.0F) % 360.0F;
/*     */       
/* 139 */       int[] angles = { 0, 45, 90, 135, 180, 225, 270, 315 };
/* 140 */       String[] angle_strings = { "↑", "↗", "→", "↘", "↓", "↙", "←", "↖" };
/* 141 */       double min_diff = 1000.0D;
/*     */       
/* 143 */       String angle_str = "-";
/* 144 */       for (int i = 0; i < angles.length; i++) {
/* 145 */         double diff = Math.abs(angles[i] - angle_diff);
/* 146 */         if (diff < min_diff) {
/* 147 */           min_diff = diff;
/* 148 */           angle_str = angle_strings[i];
/*     */         } 
/*     */       } 
/*     */       
/* 152 */       str = str + " " + player_color_code + angle_str;
/*     */       
/* 154 */       String commandText = "/" + CommandHintsFinderLookAtPlayer.command_text + " " + player.posX + " " + player.posY + " " + player.posZ + " " + player.name;
/*     */       
/* 156 */       ChatComponentText chatComponentText1 = new ChatComponentText(ColorCodesManager.replaceColorCodesInString(str));
/* 157 */       ChatComponentText chatComponentText2 = new ChatComponentText(ColorCodesManager.replaceColorCodesInString(hoverText));
/* 158 */       HoverEvent hover = new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)chatComponentText2);
/* 159 */       ClickEvent click = new ClickEvent(ClickEvent.Action.RUN_COMMAND, commandText);
/* 160 */       chatComponentText1.func_150256_b().func_150209_a(hover);
/* 161 */       chatComponentText1.func_150256_b().func_150241_a(click);
/* 162 */       (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)chatComponentText1);
/*     */       
/* 164 */       cnt_found++;
/*     */     } 
/* 166 */     if (cnt_found > 0) {
/* 167 */       OnMyTickEvent.FINDER_IS_SEARCH_LOOP = false;
/* 168 */       if (!withMessageOnFail) {
/* 169 */         MyChatListener.playSound(MyChatListener.SOUND_PARTY_CHAT);
/*     */       }
/*     */     } else {
/* 172 */       OnMyTickEvent.FINDER_IS_SEARCH_LOOP = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void findAndlookAtPlayer(double posX, double posY, double posZ, String p_name) {
/* 177 */     EntityPlayerSP entityPlayerSP = (Minecraft.func_71410_x()).field_71439_g;
/*     */     
/* 179 */     ArrayList<HintsPlayerScanner.BWPlayer> players = HintsPlayerScanner.scanPlayers();
/* 180 */     if (players != null && players.size() > 1)
/*     */     {
/* 182 */       for (HintsPlayerScanner.BWPlayer p : players) {
/* 183 */         if (p.name.equals(p_name)) {
/* 184 */           lookAtPlayer(((Entity)entityPlayerSP).field_70165_t, ((Entity)entityPlayerSP).field_70163_u, ((Entity)entityPlayerSP).field_70161_v, p.posX, p.posY, p.posZ);
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     }
/* 189 */     lookAtPlayer(((Entity)entityPlayerSP).field_70165_t, ((Entity)entityPlayerSP).field_70163_u, ((Entity)entityPlayerSP).field_70161_v, posX, posY, posZ);
/*     */   }
/*     */   
/*     */   public static void lookAtPlayer(double x1, double y1, double z1, double x2, double y2, double z2) {
/* 193 */     double dX = x1 - x2;
/* 194 */     double dY = y1 - y2;
/* 195 */     double dZ = z1 - z2;
/* 196 */     float yaw = (float)Math.atan2(dZ, dX);
/* 197 */     float pitch = (float)(Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI);
/*     */ 
/*     */     
/* 200 */     float t_yaw = myMap(yaw, -3.1415927F, 3.1415927F, -180.0F, 180.0F);
/* 201 */     float t_pitch = myMap(pitch, 3.1415927F, 6.2831855F, 90.0F, -90.0F);
/* 202 */     rotateTo((Entity)(Minecraft.func_71410_x()).field_71439_g, t_yaw + 90.0F, t_pitch);
/*     */   }
/*     */   
/*     */   public static float myMap(float value, float leftMin, float leftMax, float rightMin, float rightMax) {
/* 206 */     float leftSpan = leftMax - leftMin;
/* 207 */     float rightSpan = rightMax - rightMin;
/* 208 */     float valueScaled = (value - leftMin) / leftSpan;
/* 209 */     return rightMin + valueScaled * rightSpan;
/*     */   }
/*     */   
/*     */   public static void rotateTo(Entity player, float target_angle_yaw, float target_angle_pitch) {
/* 213 */     float prev_rot_yaw = player.field_70177_z;
/* 214 */     float prev_rot_pitch = player.field_70125_A;
/*     */     
/* 216 */     float angle_yaw = target_angle_yaw - prev_rot_yaw;
/* 217 */     float angle_pitch = target_angle_pitch - prev_rot_pitch;
/*     */     
/* 219 */     rotateAngles(player, angle_yaw, angle_pitch);
/*     */     
/* 221 */     double delta_yaw = (player.field_70177_z - prev_rot_yaw);
/* 222 */     double delta_pitch = (player.field_70125_A - prev_rot_pitch);
/*     */     
/* 224 */     if (Math.abs(target_angle_pitch) > 90.0F)
/*     */       return; 
/* 226 */     if (target_angle_yaw != player.field_70177_z || target_angle_pitch != player.field_70125_A) {
/* 227 */       rotateTo(player, target_angle_yaw, target_angle_pitch);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void rotateAngles(Entity player, float angle_yaw, float angle_pitch) {
/* 232 */     player.func_70082_c(angle_yaw / 0.15F, angle_pitch / -0.15F);
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\hints\HintsFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */