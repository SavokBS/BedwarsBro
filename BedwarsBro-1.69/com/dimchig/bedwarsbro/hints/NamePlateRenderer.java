/*     */ package com.dimchig.bedwarsbro.hints;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.ChatSender;
/*     */ import com.dimchig.bedwarsbro.ColorCodesManager;
/*     */ import com.dimchig.bedwarsbro.CustomScoreboard;
/*     */ import com.dimchig.bedwarsbro.Main;
/*     */ import com.dimchig.bedwarsbro.MyChatListener;
/*     */ import com.dimchig.bedwarsbro.SameModePlayers;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.network.NetworkPlayerInfo;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NamePlateRenderer
/*     */ {
/*     */   static Minecraft mc;
/*     */   
/*     */   public NamePlateRenderer() {
/*  27 */     mc = Minecraft.func_71410_x();
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onMyRender(PlayerEvent.NameFormat event) {
/*  32 */     String color = "&f";
/*  33 */     CustomScoreboard.TEAM_COLOR team_color = MyChatListener.getEntityTeamColor(event.entityPlayer);
/*  34 */     if (team_color != CustomScoreboard.TEAM_COLOR.NONE) color = "&" + CustomScoreboard.getCodeByTeamColor(team_color);
/*     */     
/*  36 */     String prefix = getPrefixByName(event.username);
/*     */     
/*  38 */     event.displayname = ColorCodesManager.replaceColorCodesInString(prefix + color + event.displayname);
/*     */     
/*  40 */     updateGameTab();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateGameTab() {
/*  46 */     if (mc.func_147114_u() == null || mc.func_147114_u().func_175106_d() == null)
/*  47 */       return;  Collection<NetworkPlayerInfo> players = mc.func_147114_u().func_175106_d();
/*  48 */     int cnt = 0;
/*  49 */     boolean isInGame = MyChatListener.IS_IN_GAME;
/*  50 */     for (NetworkPlayerInfo info : players) {
/*  51 */       cnt++;
/*  52 */       if (cnt > 500)
/*     */         return; 
/*  54 */       if (info.func_178845_a() == null || info.func_178850_i() == null) {
/*     */         continue;
/*     */       }
/*  57 */       String player_name = info.func_178845_a().getName();
/*  58 */       String player_prefix = getPrefixByName(player_name);
/*     */       
/*  60 */       String color_code = "&7";
/*  61 */       String donation = "";
/*  62 */       boolean hasFlag = ((Minecraft.func_71410_x()).field_71456_v.func_175181_h().func_175243_a(info).contains("⚑") || info.func_178850_i().func_96668_e().contains("⚑"));
/*     */       
/*  64 */       if (hasFlag);
/*     */ 
/*     */       
/*  67 */       if (isInGame || hasFlag) {
/*  68 */         color_code = "&7&m";
/*  69 */         if (info.func_178850_i() == null)
/*  70 */           continue;  String team_name = info.func_178850_i().func_96661_b();
/*  71 */         CustomScoreboard.TEAM_COLOR c = MyChatListener.getEntityTeamColorByTeam(team_name);
/*     */ 
/*     */         
/*  74 */         if (c != CustomScoreboard.TEAM_COLOR.NONE) color_code = "&" + CustomScoreboard.getCodeByTeamColor(c);
/*     */       
/*     */       } else {
/*     */         
/*  78 */         donation = info.func_178850_i().func_96668_e().trim();
/*  79 */         if (donation.length() > 4) {
/*  80 */           donation = donation.replace("§0", "&f");
/*  81 */           color_code = donation.substring(donation.length() - 2, donation.length());
/*  82 */           donation = donation + " ";
/*     */         } else {
/*  84 */           donation = "";
/*     */         } 
/*     */       } 
/*  87 */       String new_name = player_prefix + donation + color_code + player_name;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  92 */       info.func_178859_a((IChatComponent)new ChatComponentText(ColorCodesManager.replaceColorCodesInString(new_name)));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void printSameUsersInGame() {
/*  97 */     if (mc == null || mc.field_71439_g == null)
/*  98 */       return;  if (mc.func_147114_u() == null || mc.func_147114_u().func_175106_d() == null)
/*  99 */       return;  Collection<NetworkPlayerInfo> players = mc.func_147114_u().func_175106_d();
/* 100 */     if (players == null || players.size() == 0)
/*     */       return; 
/* 102 */     EntityPlayerSP mod_player = mc.field_71439_g;
/*     */     
/* 104 */     int cnt = 0;
/* 105 */     ArrayList<String> arr = new ArrayList<String>();
/* 106 */     for (NetworkPlayerInfo info : players) {
/* 107 */       if (info.func_178845_a() == null || info.func_178850_i() == null)
/*     */         continue; 
/* 109 */       String player_name = info.func_178845_a().getName();
/* 110 */       String player_prefix = getPrefixByName(player_name);
/*     */ 
/*     */       
/* 113 */       if (player_prefix.length() == 0 || mod_player.func_70005_c_().equals(player_name)) {
/*     */         continue;
/*     */       }
/*     */       
/* 117 */       String team_name = info.func_178850_i().func_96661_b();
/* 118 */       CustomScoreboard.TEAM_COLOR c = MyChatListener.getEntityTeamColorByTeam(team_name);
/*     */       
/* 120 */       String color_code = "&f";
/* 121 */       if (c != CustomScoreboard.TEAM_COLOR.NONE) color_code = "&" + CustomScoreboard.getCodeByTeamColor(c);
/*     */       
/* 123 */       cnt++;
/* 124 */       arr.add(color_code + ColorCodesManager.removeColorCodes(player_name));
/*     */     } 
/*     */     
/* 127 */     if (cnt <= 0 || arr.size() == 0)
/* 128 */       return;  String text = MyChatListener.PREFIX_BEDWARSBRO + "Сейчас в катке с модом игра" + MyChatListener.getNumberEnding(cnt, "ет", "ет", "ют") + " &a&l" + cnt + " &fигрок" + MyChatListener.getNumberEnding(cnt, "", "а", "ов") + "!";
/* 129 */     String hover = "";
/* 130 */     for (String s : arr) {
/* 131 */       hover = hover + "&8• &r" + s + "\n";
/*     */     }
/* 133 */     if (hover.length() > 2) hover = hover.substring(0, hover.length() - 1); 
/* 134 */     ChatSender.addHoverText(text, hover);
/*     */   }
/*     */   
/*     */   public SameModePlayers.BroPlayer findBroPlayerByName(String player_name) {
/* 138 */     for (SameModePlayers.BroPlayer bro : Main.sameModePlayers.getActivePlayers()) {
/* 139 */       if (bro.name.equals(player_name)) return bro; 
/*     */     } 
/* 141 */     return null;
/*     */   }
/*     */   
/*     */   public String getPrefixByName(String player_name) {
/* 145 */     SameModePlayers.BroPlayer bro = findBroPlayerByName(player_name);
/* 146 */     if (bro == null) return ""; 
/* 147 */     return getPrefixForBro(bro);
/*     */   }
/*     */   
/* 150 */   public static String DEFAULT_PREFIX = "&c&l[&6&lB&e&lr&a&lo&b&l]&r ";
/*     */   
/*     */   public String getPrefixForBro(SameModePlayers.BroPlayer bro) {
/* 153 */     if (!Main.sameModePlayers.isPlayerWithMod(bro.name)) return ""; 
/* 154 */     if (bro.isAdmin) return "&c&l[&6&lA&e&lD&a&lM&b&lI&d&lN&c&l]&r "; 
/* 155 */     if (isPrefixValid(bro.prefix))
/*     */     {
/* 157 */       return generateColourfulPrefix(bro.prefix);
/*     */     }
/* 159 */     return DEFAULT_PREFIX;
/*     */   }
/*     */   
/*     */   public String generateColourfulPrefix(String p) {
/* 163 */     String[] colors = { "c", "6", "e", "a", "b", "d" };
/* 164 */     String origin = "[" + p + "]";
/* 165 */     String real_prefix = "";
/* 166 */     for (int i = 0; i < origin.length(); i++) {
/* 167 */       real_prefix = real_prefix + "&" + colors[i % colors.length] + "&l" + origin.charAt(i);
/*     */     }
/* 169 */     if (real_prefix.length() < 3) return DEFAULT_PREFIX; 
/* 170 */     real_prefix = real_prefix.trim();
/* 171 */     return real_prefix + "&r ";
/*     */   }
/*     */   
/*     */   public boolean isPrefixValid(String p) {
/* 175 */     if (p.length() <= 0 || p.length() > 10 || p.contains(" ")) return false;
/*     */     
/* 177 */     for (int i = 0; i < p.length(); i++) {
/* 178 */       if (!Character.isLetter(p.charAt(i))) return false;
/*     */     
/*     */     } 
/* 181 */     if (isPrefixBad(p)) return false;
/*     */     
/* 183 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isPrefixBad(String p) {
/* 187 */     String[] forbidden_words = { "созд", "здате", "стаф", "адми", "creat", "admi", "stuf", "xui", "xuy", "bedwar", "govn", "porn", "negr", "nigg", "niger", "niga", "негр", "пидр", "ниг", "соса", "шлюх", "щлюх", "peni", "gay", "lesbi", "trans", "lgbt" };
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
/* 218 */     String check = p.toLowerCase();
/* 219 */     for (String word : forbidden_words) {
/* 220 */       if (check.contains(word)) {
/* 221 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 225 */     return SwearDictionary.isTextContainsBedWord(check);
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\hints\NamePlateRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */