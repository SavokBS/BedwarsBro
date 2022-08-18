/*     */ package com.dimchig.bedwarsbro.hints;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.ChatSender;
/*     */ import com.dimchig.bedwarsbro.ColorCodesManager;
/*     */ import com.dimchig.bedwarsbro.CustomScoreboard;
/*     */ import com.dimchig.bedwarsbro.FileManager;
/*     */ import com.dimchig.bedwarsbro.Main;
/*     */ import com.dimchig.bedwarsbro.MyChatListener;
/*     */ import com.dimchig.bedwarsbro.commands.CommandRainbowMessage;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.regex.Pattern;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import scala.util.Random;
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
/*     */ public class BedwarsMeow
/*     */ {
/*  31 */   public static String filename = "BedwarsBro_MeowMod_1.69.2.txt";
/*     */   
/*     */   public static ArrayList<MeowMsg> meowMessages;
/*  34 */   String string_for_bed_single = "(1 игрок)";
/*  35 */   String string_for_bed_multi = "(2 и больше игроков)";
/*  36 */   String category_kill_messages = "ПОСЛЕ УБИЙСТВА (заменится слово \"игрок\")";
/*  37 */   String category_final_kill_messages = "ПОСЛЕ ФИНАЛЬНОГО УБИЙСТВА (заменится слово \"игрок\")";
/*  38 */   String category_death_messages = "ПРИ СМЕРТИ (заменится слово \"игрок\" [тот, кто тебя убил])";
/*  39 */   String category_bed_messages = "СЛОМАНА КРОВАТЬ (заменятся слова \"команда\", \"команды\") Можешь использовать \"" + this.string_for_bed_single + "\", и \"" + this.string_for_bed_multi + "\" чтоб отделить сообщения для одиночной игры";
/*  40 */   String category_wins_messages = "ПРИ ПОБЕДЕ";
/*  41 */   String category_game_start_messages = "ПРИ ВХОДЕ В ИГРУ (Как только началась катка)";
/*  42 */   String prefix = MyChatListener.PREFIX_BEDWARS_MEOW;
/*     */   ArrayList<MeowMsg> meowMessagesQueue;
/*     */   public boolean IS_USE_COLORS = false;
/*     */   public boolean IS_ACTIVE = false;
/*     */   public static boolean IS_AUTO_RANDOM_ACTIVE = false;
/*     */   
/*     */   public enum MsgCase
/*     */   {
/*  50 */     KILL,
/*  51 */     FINAL_KILL,
/*  52 */     DEATH,
/*  53 */     BED_SINGLE,
/*  54 */     BED_MULTI,
/*  55 */     WIN,
/*  56 */     GAME_START; }
/*     */   
/*     */   public class MeowMsg {
/*     */     public BedwarsMeow.MsgCase msgcase;
/*     */     public String text;
/*     */     
/*     */     public MeowMsg(BedwarsMeow.MsgCase msgcase, String text) {
/*  63 */       this.msgcase = msgcase;
/*  64 */       this.text = text;
/*     */     }
/*     */     
/*     */     public String getText(String variable) {
/*  68 */       if (Main.namePlateRenderer.isBroAdmin(variable)) return null;
/*     */       
/*  70 */       String new_text = "&f" + this.text;
/*  71 */       if (BedwarsMeow.IS_AUTO_RANDOM_ACTIVE) Main.commandRainbowMessageSetter.setRandomPalitra();
/*     */       
/*  73 */       if (this.msgcase == BedwarsMeow.MsgCase.KILL || this.msgcase == BedwarsMeow.MsgCase.FINAL_KILL || this.msgcase == BedwarsMeow.MsgCase.DEATH) {
/*  74 */         String killer = "игрок";
/*  75 */         new_text = new_text.replace(killer, variable + "&f").replace(BedwarsMeow.this.upperCaseFirstLetter(killer), variable + "&f").trim();
/*  76 */       } else if (this.msgcase == BedwarsMeow.MsgCase.BED_SINGLE || this.msgcase == BedwarsMeow.MsgCase.BED_MULTI) {
/*  77 */         String team_normal = "команда";
/*  78 */         String team_edited = "команды";
/*     */         
/*  80 */         CustomScoreboard.TEAM_COLOR team_color = CustomScoreboard.getTeamColorByCode(variable);
/*  81 */         String replace_normal = "ботики";
/*  82 */         String replace_edited = "ботиков";
/*  83 */         String replace_normal_single = "ботик";
/*  84 */         String replace_edited_single = "ботика";
/*  85 */         String color_code = "&" + CustomScoreboard.getCodeByTeamColor(team_color);
/*     */         
/*  87 */         if (team_color == CustomScoreboard.TEAM_COLOR.RED) {
/*  88 */           replace_normal = "красные";
/*  89 */           replace_edited = "красных";
/*  90 */           replace_normal_single = "красный";
/*  91 */           replace_edited_single = "красного";
/*  92 */         } else if (team_color == CustomScoreboard.TEAM_COLOR.YELLOW) {
/*  93 */           replace_normal = "желтые";
/*  94 */           replace_edited = "желтых";
/*  95 */           replace_normal_single = "желтый";
/*  96 */           replace_edited_single = "желтого";
/*  97 */         } else if (team_color == CustomScoreboard.TEAM_COLOR.GREEN) {
/*  98 */           replace_normal = "зеленые";
/*  99 */           replace_edited = "зеленых";
/* 100 */           replace_normal_single = "зеленый";
/* 101 */           replace_edited_single = "зеленого";
/* 102 */         } else if (team_color == CustomScoreboard.TEAM_COLOR.AQUA) {
/* 103 */           replace_normal = "голубые";
/* 104 */           replace_edited = "голубых";
/* 105 */           replace_normal_single = "голубой";
/* 106 */           replace_edited_single = "голубого";
/* 107 */         } else if (team_color == CustomScoreboard.TEAM_COLOR.BLUE) {
/* 108 */           replace_normal = "синие";
/* 109 */           replace_edited = "синих";
/* 110 */           replace_normal_single = "синий";
/* 111 */           replace_edited_single = "синего";
/* 112 */         } else if (team_color == CustomScoreboard.TEAM_COLOR.PINK) {
/* 113 */           replace_normal = "розовые";
/* 114 */           replace_edited = "розовых";
/* 115 */           replace_normal_single = "розовый";
/* 116 */           replace_edited_single = "розового";
/* 117 */         } else if (team_color == CustomScoreboard.TEAM_COLOR.GRAY) {
/* 118 */           replace_normal = "серые";
/* 119 */           replace_edited = "серых";
/* 120 */           replace_normal_single = "серый";
/* 121 */           replace_edited_single = "серого";
/* 122 */         } else if (team_color == CustomScoreboard.TEAM_COLOR.WHITE) {
/* 123 */           replace_normal = "белые";
/* 124 */           replace_edited = "белых";
/* 125 */           replace_normal_single = "белый";
/* 126 */           replace_edited_single = "белого";
/*     */         } 
/*     */ 
/*     */         
/* 130 */         if (this.msgcase == BedwarsMeow.MsgCase.BED_SINGLE) {
/* 131 */           replace_normal = replace_normal_single;
/* 132 */           replace_edited = replace_edited_single;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 139 */         new_text = new_text.replace(team_normal, color_code + replace_normal + "&f").replace(BedwarsMeow.this.upperCaseFirstLetter(team_normal), color_code + BedwarsMeow.this.upperCaseFirstLetter(replace_normal) + "&f").replace(team_edited, color_code + replace_edited + "&f").replace(BedwarsMeow.this.upperCaseFirstLetter(team_edited), color_code + BedwarsMeow.this.upperCaseFirstLetter(replace_edited) + "&f");
/*     */       }
/* 141 */       else if (this.msgcase == BedwarsMeow.MsgCase.WIN || this.msgcase == BedwarsMeow.MsgCase.GAME_START) {
/* 142 */         new_text = this.text;
/*     */       } else {
/* 144 */         return null;
/*     */       } 
/*     */       
/* 147 */       new_text = "&f" + new_text;
/*     */       
/* 149 */       if (BedwarsMeow.this.IS_USE_COLORS)
/* 150 */       { String rainbow_text = CommandRainbowMessage.generateRainbowMessage(ColorCodesManager.removeColorCodes(new_text), 1, -1);
/* 151 */         if (rainbow_text != null) new_text = rainbow_text;  }
/* 152 */       else { new_text = ColorCodesManager.removeColorCodes(new_text); }
/*     */       
/* 154 */       if (new_text.length() >= 100) new_text = new_text.substring(0, 100);
/*     */       
/* 156 */       return new_text;
/*     */     }
/*     */   }
/*     */   
/*     */   public String upperCaseFirstLetter(String text) {
/* 161 */     return text.substring(0, 1).toUpperCase() + text.substring(1);
/*     */   }
/*     */   
/*     */   public BedwarsMeow() {
/* 165 */     meowMessages = new ArrayList<MeowMsg>();
/*     */ 
/*     */ 
/*     */     
/* 169 */     String readFile = FileManager.readFile(filename);
/* 170 */     if (readFile == null || readFile.length() < 10) {
/* 171 */       initMeowMessages();
/*     */     }
/*     */     
/* 174 */     readFile();
/*     */     
/* 176 */     updateBooleans();
/*     */   }
/*     */   
/*     */   public void updateBooleans() {
/* 180 */     this.IS_ACTIVE = HintsValidator.isBedwarsMeowActive();
/* 181 */     this.IS_USE_COLORS = HintsValidator.isBedwarsMeowColorsActive();
/*     */   }
/*     */   
/*     */   public void readFile() {
/* 185 */     meowMessages = new ArrayList<MeowMsg>();
/* 186 */     this.meowMessagesQueue = new ArrayList<MeowMsg>();
/* 187 */     String readFile = FileManager.readFile(filename);
/* 188 */     if (readFile == null || readFile.length() < 10) {
/* 189 */       initMeowMessages();
/* 190 */       readFile = FileManager.readFile(filename);
/*     */     } 
/*     */ 
/*     */     
/* 194 */     if (readFile == null) {
/* 195 */       ChatSender.addText(this.prefix + "&cФайл с текстом не найден! Проверь &e%appdata%\\Roaming\\.minecraft\\" + filename);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*     */     try {
/* 201 */       String[] splitters_text = { this.category_kill_messages, this.category_final_kill_messages, this.category_death_messages, this.category_bed_messages, this.category_wins_messages, this.category_game_start_messages, "randomtexttoletmycodeparselastline" };
/*     */ 
/*     */ 
/*     */       
/* 205 */       MsgCase[] splitters_cases = { MsgCase.KILL, MsgCase.FINAL_KILL, MsgCase.DEATH, MsgCase.BED_SINGLE, MsgCase.WIN, MsgCase.GAME_START };
/*     */ 
/*     */       
/*     */       int i;
/*     */ 
/*     */       
/* 211 */       for (i = 0; i < splitters_cases.length - 1; i++) {
/* 212 */         if (!readFile.contains(splitters_text[i])) {
/* 213 */           ChatSender.addText("\n" + MyChatListener.PREFIX_BEDWARS_MEOW + "&c&lОшибка файла, категории отсутствуют! Восстанови файл к &lзаводским настройкам &7(&e/meow&7)&c!\n");
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 218 */       for (i = 0; i < splitters_cases.length; i++) {
/* 219 */         String[] lines = readFile.split(Pattern.quote(splitters_text[i]))[1].trim().split(Pattern.quote(splitters_text[i + 1]))[0].trim().split("\n");
/* 220 */         String[] arrayOfString1 = lines; int j = arrayOfString1.length; byte b = 0; while (true) { MeowMsg m; if (b < j) { String line = arrayOfString1[b];
/* 221 */             if (line.contains("==="))
/* 222 */               continue;  String l = line.replace("\n", "").trim();
/* 223 */             if (l.length() <= 0)
/* 224 */               continue;  m = new MeowMsg(splitters_cases[i], l);
/*     */             
/* 226 */             if (m.msgcase == MsgCase.BED_SINGLE)
/* 227 */             { if (!m.text.contains(this.string_for_bed_single) && !m.text.contains(this.string_for_bed_multi))
/* 228 */               { meowMessages.add(new MeowMsg(MsgCase.BED_SINGLE, l));
/* 229 */                 meowMessages.add(new MeowMsg(MsgCase.BED_MULTI, l)); }
/*     */               else
/*     */               
/* 232 */               { if (m.text.contains(this.string_for_bed_single)) {
/* 233 */                   m.msgcase = MsgCase.BED_SINGLE;
/* 234 */                   m.text = m.text.replace(this.string_for_bed_single, "").trim();
/*     */                 } 
/* 236 */                 if (m.text.contains(this.string_for_bed_multi)) {
/* 237 */                   m.msgcase = MsgCase.BED_MULTI;
/* 238 */                   m.text = m.text.replace(this.string_for_bed_multi, "").trim();
/*     */                 } 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 244 */                 meowMessages.add(m); }  continue; }  } else { break; }  meowMessages.add(m); b++; }
/*     */       
/*     */       } 
/* 247 */     } catch (Exception ex) {
/* 248 */       ChatSender.addText(this.prefix + "&cФайл с текстом содержит ошибки! Проверь &e%appdata%\\Roaming\\.minecraft\\" + filename);
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void printMeows() {
/* 254 */     ChatSender.addText(this.prefix + "&7Загрузка...\n");
/* 255 */     ChatSender.addText("&fСообщения после &cкила&f:");
/* 256 */     String start_prefix = " &8• &f";
/* 257 */     for (MeowMsg m : meowMessages) {
/* 258 */       if (m.msgcase == MsgCase.KILL) ChatSender.addText(start_prefix + m.text); 
/*     */     } 
/* 260 */     ChatSender.addText("\n&fСообщения после &eфинального кила&f:");
/* 261 */     for (MeowMsg m : meowMessages) {
/* 262 */       if (m.msgcase == MsgCase.FINAL_KILL) ChatSender.addText(start_prefix + m.text); 
/*     */     } 
/* 264 */     ChatSender.addText("\n&fСообщения после &6смерти&f:");
/* 265 */     for (MeowMsg m : meowMessages) {
/* 266 */       if (m.msgcase == MsgCase.DEATH) ChatSender.addText(start_prefix + m.text); 
/*     */     } 
/* 268 */     ChatSender.addText("\n&fСообщения после &aкровати&f:");
/* 269 */     for (MeowMsg m : meowMessages) {
/* 270 */       if (m.msgcase == MsgCase.BED_SINGLE) ChatSender.addText(start_prefix + m.text + " &a" + this.string_for_bed_single); 
/* 271 */       if (m.msgcase == MsgCase.BED_MULTI) ChatSender.addText(start_prefix + m.text + " &c" + this.string_for_bed_multi); 
/*     */     } 
/* 273 */     ChatSender.addText("\n&fСообщения после &bвыиграша&f:");
/* 274 */     for (MeowMsg m : meowMessages) {
/* 275 */       if (m.msgcase == MsgCase.WIN) ChatSender.addText(start_prefix + m.text); 
/*     */     } 
/* 277 */     ChatSender.addText("\n&fСообщения при &9входе в игру&f:");
/* 278 */     for (MeowMsg m : meowMessages) {
/* 279 */       if (m.msgcase == MsgCase.GAME_START) ChatSender.addText(start_prefix + m.text); 
/*     */     } 
/* 281 */     ChatSender.addText(this.prefix + "&eСообщения считаны!");
/*     */   }
/*     */   
/*     */   public String getMeows(MsgCase msgcase) {
/* 285 */     String s = "";
/*     */     
/* 287 */     String variable = "";
/* 288 */     String player_name = (Minecraft.func_71410_x()).field_71439_g.func_70005_c_();
/* 289 */     if (Main.namePlateRenderer.isBroAdmin(player_name)) player_name = "Vanya1337"; 
/* 290 */     if (msgcase == MsgCase.KILL || msgcase == MsgCase.FINAL_KILL) {
/* 291 */       variable = "&e" + player_name;
/* 292 */     } else if (msgcase == MsgCase.DEATH) {
/* 293 */       variable = "&e" + player_name;
/* 294 */     } else if (msgcase == MsgCase.BED_SINGLE || msgcase == MsgCase.BED_MULTI) {
/* 295 */       String[] colors = { "c", "e", "a", "b", "d", "9" };
/* 296 */       Random rnd = new Random();
/* 297 */       variable = "&" + colors[rnd.nextInt(colors.length)];
/* 298 */     } else if (msgcase == MsgCase.WIN || msgcase == MsgCase.GAME_START) {
/* 299 */       variable = "";
/*     */     } 
/*     */     
/* 302 */     for (MeowMsg m : meowMessages) {
/* 303 */       if (m.msgcase == msgcase) s = s + " &8• &f" + m.getText(variable) + "\n"; 
/*     */     } 
/* 305 */     return s;
/*     */   }
/*     */   
/*     */   public void removeMeessagesWithBadWords() {
/* 309 */     initMeowMessages(true);
/* 310 */     readFile();
/*     */   }
/*     */   
/*     */   public void initMeowMessages() {
/* 314 */     initMeowMessages(false);
/*     */   }
/*     */   
/*     */   public void initMeowMessages(boolean isRemoveBadWords) {
/* 318 */     String[] bad_words = { "соси", "соса", "сосну", "ебал", "трах", "хуе", "ебат", "ебан", "сучк", "сук", "хуй", "пизд", "бля", "пидор", "ебло", "уебо" };
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
/* 498 */     String[] killMessages = { "Игрок, тебе нужно больше тренироваться!", "Дружок, тебе надо тренироваться", "Малыш, тебе надо тренироваться", "Игрок, ты не няшка <3", "Предупредил", "Игрок, попробуй во время пвп нажимать на мышку", "Игрок, суть игры не умерать, а убивать", "Спасибо, + килл в статистику", "Больше так не делай)" };
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
/* 510 */     String[] finalKillMessages = { "0", "L", "Не расстраивайся, игрок, в следующий раз получиться!", "Не расстраивайся, игрок, тебе повезет в следующий раз!", "Не грусти, игрок, потренируйся немного и все получиться!", "Не грусти, игрок, в следующий раз все получиться!", "Игрок, потренируйся и приходи в следующий раз!", "Игрок, у тебя скоро все получиться, главное верить в себя!", "Это нормально, игрок, нужно уметь принимать поражение", "Этот прицел просто имба", "В лобби, малыш)", "Игрок, только не плач", "Ой-ой-ой, не повезло тебе, игрок", "Что, игрок, без кроватки сложно получилось?", "Игрок, надо было кровать дефать", "Я гений этой игры", "Я сейчас в очень жосткой форме!", "Вот почему я лучший в мире!", "Игрок, просто я тренируюсь, пока все отдыхают", "Я просто бог в этой игре!", "Поймал на ашибке!" };
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
/* 536 */     String s = " " + this.string_for_bed_single;
/* 537 */     String m = " " + this.string_for_bed_multi;
/*     */     
/* 539 */     String[] bedMessages = { "Фуу... Какая не вкусная кроватка у команды", "Походу команда счас проиграет" + s, "Походу команда счас проиграют" + m, "Не повезло тебе, команда, попасть со мной в одну катку" + s, "Не повезло вам, команда, попасть со мной в одну катку" + m, "Команда скоро отправится в лобби)" + s, "Команда скоро отправятся в лобби)" + m, "Ой, команда, прости" + s, "Ой, команда, простите" + m, "Команда теперь без кроватки(" + s, "Команда теперь без кроватки(" + m, "Команда, только не плач" + s, "Команда, только не плачте" + m };
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
/* 555 */     String[] deathMessages = { "Игрок, тебе просто повезло)" };
/*     */ 
/*     */ 
/*     */     
/* 559 */     String[] winMessages = { "Ура, победа!", "GG" };
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 564 */     String[] gameStartMessages = { "Ребятки, всем удачной игры!", "Ребят, давайте играть без читов", "Народ, давайте не обзываться, ведь это очень обидно. Давайте играть дружно и мирно!", "Кто тоже играет с флюском + в чат", "Кто тоже играет с матиксом + в чат", "Кто тоже играет с модом &c&lBedwars &f&lБро?", "мод &eDexlandMeow &f< &c&lBedwars &f&lБро" };
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
/* 576 */     String str = "В этом файле ты можешь редактировать и добавлять свои сообщения\nПосле изменений сохрани файл и в майнкрафте напиши /meow, и там ОБНОВИТЬ СООБЩЕНИЯ\nЕсли возникла ошибка, или что-то не работает, удали этот файл и нажми ОБНОВИТЬ СООБЩЕНИЯ\nНе используй \"===\" и не используй перенос строки внутри сообщений!\n\n";
/*     */     
/* 578 */     String[] splitters_text = { this.category_kill_messages, this.category_final_kill_messages, this.category_death_messages, this.category_bed_messages, this.category_wins_messages, this.category_game_start_messages };
/*     */ 
/*     */ 
/*     */     
/* 582 */     String[][] splitters_messages = { killMessages, finalKillMessages, deathMessages, bedMessages, winMessages, gameStartMessages };
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 587 */     if (isRemoveBadWords) {
/* 588 */       for (String[] messages : splitters_messages) {
/* 589 */         for (int j = 0; j < messages.length; j++) {
/* 590 */           boolean isOk = true;
/*     */           
/* 592 */           for (String bad_word : bad_words) {
/* 593 */             if (messages[j].toLowerCase().contains(bad_word)) {
/* 594 */               isOk = false;
/*     */               break;
/*     */             } 
/*     */           } 
/* 598 */           if (!isOk) {
/* 599 */             ChatSender.addText(MyChatListener.PREFIX_BEDWARS_MEOW + "&fУдалено &c" + messages[j]);
/* 600 */             messages[j] = "";
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 607 */     for (int i = 0; i < splitters_text.length; i++) {
/* 608 */       str = str + "===" + splitters_text[i] + "===\n";
/* 609 */       for (String s2 : splitters_messages[i]) {
/* 610 */         if (s2.length() != 0)
/* 611 */           str = str + s2 + "\n"; 
/*     */       } 
/* 613 */       str = str + "\n\n";
/*     */     } 
/*     */     
/* 616 */     FileManager.initFile(filename);
/* 617 */     FileManager.writeToFile(str, filename, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNextMessage(MsgCase msgcase, String variable) {
/* 628 */     if (!this.IS_ACTIVE) return null;
/*     */ 
/*     */ 
/*     */     
/* 632 */     MeowMsg msg = null;
/* 633 */     if (this.meowMessagesQueue == null) this.meowMessagesQueue = new ArrayList<MeowMsg>(); 
/* 634 */     for (MeowMsg m : this.meowMessagesQueue) {
/* 635 */       if (m.msgcase == msgcase) {
/* 636 */         msg = m;
/* 637 */         this.meowMessagesQueue.remove(m);
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 642 */     boolean areMessagesLeft = false;
/* 643 */     for (MeowMsg m : this.meowMessagesQueue) {
/* 644 */       if (m.msgcase == msgcase) {
/* 645 */         areMessagesLeft = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 649 */     if (!areMessagesLeft) {
/*     */       
/* 651 */       ArrayList<MeowMsg> arr = new ArrayList<MeowMsg>();
/* 652 */       for (MeowMsg m : meowMessages) {
/* 653 */         if (m.msgcase == msgcase) arr.add(m); 
/*     */       } 
/* 655 */       if (arr.size() == 0) return null;
/*     */       
/* 657 */       Collections.shuffle(arr);
/* 658 */       if (msg == null) {
/* 659 */         msg = arr.get(0);
/* 660 */         arr.remove(0);
/* 661 */       } else if (arr.size() > 1) {
/* 662 */         for (; ((MeowMsg)arr.get(0)).text.equals(msg.text); Collections.shuffle(arr));
/*     */       } 
/* 664 */       this.meowMessagesQueue.addAll(arr);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 671 */     return msg.getText(variable);
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\hints\BedwarsMeow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */