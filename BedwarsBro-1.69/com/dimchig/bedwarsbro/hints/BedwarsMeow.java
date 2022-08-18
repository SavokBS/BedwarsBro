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
/*  31 */   public static String filename = "BedwarsBro_MeowMod_1.69.txt";
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
/*     */   public boolean IS_USE_RAINBOW_MODE = false;
/*     */   public boolean IS_ACTIVE = false;
/*     */   public static boolean IS_AUTO_RANDOM_ACTIVE = false;
/*     */   
/*     */   public enum MsgCase
/*     */   {
/*  51 */     KILL,
/*  52 */     FINAL_KILL,
/*  53 */     DEATH,
/*  54 */     BED_SINGLE,
/*  55 */     BED_MULTI,
/*  56 */     WIN,
/*  57 */     GAME_START; }
/*     */   
/*     */   public class MeowMsg {
/*     */     public BedwarsMeow.MsgCase msgcase;
/*     */     public String text;
/*     */     
/*     */     public MeowMsg(BedwarsMeow.MsgCase msgcase, String text) {
/*  64 */       this.msgcase = msgcase;
/*  65 */       this.text = text;
/*     */     }
/*     */     
/*     */     public String getText(String variable) {
/*  69 */       if (variable.contains("DimChig")) return null;
/*     */       
/*  71 */       String new_text = "&f" + this.text;
/*  72 */       if (BedwarsMeow.IS_AUTO_RANDOM_ACTIVE) Main.commandRainbowMessageSetter.setRandomPalitra();
/*     */       
/*  74 */       if (this.msgcase == BedwarsMeow.MsgCase.KILL || this.msgcase == BedwarsMeow.MsgCase.FINAL_KILL || this.msgcase == BedwarsMeow.MsgCase.DEATH) {
/*  75 */         String killer = "игрок";
/*  76 */         new_text = new_text.replace(killer, variable + "&f").replace(BedwarsMeow.this.upperCaseFirstLetter(killer), variable + "&f").trim();
/*  77 */       } else if (this.msgcase == BedwarsMeow.MsgCase.BED_SINGLE || this.msgcase == BedwarsMeow.MsgCase.BED_MULTI) {
/*  78 */         String team_normal = "команда";
/*  79 */         String team_edited = "команды";
/*     */         
/*  81 */         CustomScoreboard.TEAM_COLOR team_color = CustomScoreboard.getTeamColorByCode(variable);
/*  82 */         String replace_normal = "ботики";
/*  83 */         String replace_edited = "ботиков";
/*  84 */         String replace_normal_single = "ботик";
/*  85 */         String replace_edited_single = "ботика";
/*  86 */         String color_code = "&" + CustomScoreboard.getCodeByTeamColor(team_color);
/*     */         
/*  88 */         if (team_color == CustomScoreboard.TEAM_COLOR.RED) {
/*  89 */           replace_normal = "красные";
/*  90 */           replace_edited = "красных";
/*  91 */           replace_normal_single = "красный";
/*  92 */           replace_edited_single = "красного";
/*  93 */         } else if (team_color == CustomScoreboard.TEAM_COLOR.YELLOW) {
/*  94 */           replace_normal = "желтые";
/*  95 */           replace_edited = "желтых";
/*  96 */           replace_normal_single = "желтый";
/*  97 */           replace_edited_single = "желтого";
/*  98 */         } else if (team_color == CustomScoreboard.TEAM_COLOR.GREEN) {
/*  99 */           replace_normal = "зеленые";
/* 100 */           replace_edited = "зеленых";
/* 101 */           replace_normal_single = "зеленый";
/* 102 */           replace_edited_single = "зеленого";
/* 103 */         } else if (team_color == CustomScoreboard.TEAM_COLOR.AQUA) {
/* 104 */           replace_normal = "голубые";
/* 105 */           replace_edited = "голубых";
/* 106 */           replace_normal_single = "голубой";
/* 107 */           replace_edited_single = "голубого";
/* 108 */         } else if (team_color == CustomScoreboard.TEAM_COLOR.BLUE) {
/* 109 */           replace_normal = "синие";
/* 110 */           replace_edited = "синих";
/* 111 */           replace_normal_single = "синий";
/* 112 */           replace_edited_single = "синего";
/* 113 */         } else if (team_color == CustomScoreboard.TEAM_COLOR.PINK) {
/* 114 */           replace_normal = "розовые";
/* 115 */           replace_edited = "розовых";
/* 116 */           replace_normal_single = "розовый";
/* 117 */           replace_edited_single = "розового";
/* 118 */         } else if (team_color == CustomScoreboard.TEAM_COLOR.GRAY) {
/* 119 */           replace_normal = "серые";
/* 120 */           replace_edited = "серых";
/* 121 */           replace_normal_single = "серый";
/* 122 */           replace_edited_single = "серого";
/* 123 */         } else if (team_color == CustomScoreboard.TEAM_COLOR.WHITE) {
/* 124 */           replace_normal = "белые";
/* 125 */           replace_edited = "белых";
/* 126 */           replace_normal_single = "белый";
/* 127 */           replace_edited_single = "белого";
/*     */         } 
/*     */ 
/*     */         
/* 131 */         if (this.msgcase == BedwarsMeow.MsgCase.BED_SINGLE) {
/* 132 */           replace_normal = replace_normal_single;
/* 133 */           replace_edited = replace_edited_single;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 140 */         new_text = new_text.replace(team_normal, color_code + replace_normal + "&f").replace(BedwarsMeow.this.upperCaseFirstLetter(team_normal), color_code + BedwarsMeow.this.upperCaseFirstLetter(replace_normal) + "&f").replace(team_edited, color_code + replace_edited + "&f").replace(BedwarsMeow.this.upperCaseFirstLetter(team_edited), color_code + BedwarsMeow.this.upperCaseFirstLetter(replace_edited) + "&f");
/*     */       }
/* 142 */       else if (this.msgcase == BedwarsMeow.MsgCase.WIN || this.msgcase == BedwarsMeow.MsgCase.GAME_START) {
/* 143 */         new_text = this.text;
/*     */       } else {
/* 145 */         return null;
/*     */       } 
/*     */       
/* 148 */       new_text = "&f" + new_text;
/*     */       
/* 150 */       if (BedwarsMeow.this.IS_USE_RAINBOW_MODE) {
/* 151 */         String rainbow_text = CommandRainbowMessage.generateRainbowMessage(ColorCodesManager.removeColorCodes(new_text), 1, -1);
/* 152 */         if (rainbow_text != null) new_text = rainbow_text;
/*     */       
/*     */       } 
/* 155 */       if (!BedwarsMeow.this.IS_USE_COLORS) new_text = ColorCodesManager.removeColorCodes(new_text);
/*     */       
/* 157 */       if (new_text.length() >= 100) new_text = new_text.substring(0, 100);
/*     */       
/* 159 */       return new_text;
/*     */     }
/*     */   }
/*     */   
/*     */   public String upperCaseFirstLetter(String text) {
/* 164 */     return text.substring(0, 1).toUpperCase() + text.substring(1);
/*     */   }
/*     */   
/*     */   public BedwarsMeow() {
/* 168 */     meowMessages = new ArrayList<MeowMsg>();
/*     */ 
/*     */ 
/*     */     
/* 172 */     String readFile = FileManager.readFile(filename);
/* 173 */     if (readFile == null || readFile.length() < 10) {
/* 174 */       initMeowMessages();
/*     */     }
/*     */     
/* 177 */     readFile();
/*     */     
/* 179 */     updateBooleans();
/*     */   }
/*     */   
/*     */   public void updateBooleans() {
/* 183 */     this.IS_ACTIVE = HintsValidator.isBedwarsMeowActive();
/* 184 */     this.IS_USE_COLORS = HintsValidator.isBedwarsMeowColorsActive();
/* 185 */     this.IS_USE_RAINBOW_MODE = HintsValidator.isBedwarsMeowColorsRainbowModeActive();
/*     */   }
/*     */   
/*     */   public void readFile() {
/* 189 */     meowMessages = new ArrayList<MeowMsg>();
/* 190 */     this.meowMessagesQueue = new ArrayList<MeowMsg>();
/* 191 */     String readFile = FileManager.readFile(filename);
/* 192 */     if (readFile == null || readFile.length() < 10) {
/* 193 */       initMeowMessages();
/* 194 */       readFile = FileManager.readFile(filename);
/*     */     } 
/*     */ 
/*     */     
/* 198 */     if (readFile == null) {
/* 199 */       ChatSender.addText(this.prefix + "&cФайл с текстом не найден! Проверь &e%appdata%\\Roaming\\.minecraft\\" + filename);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*     */     try {
/* 205 */       String[] splitters_text = { this.category_kill_messages, this.category_final_kill_messages, this.category_death_messages, this.category_bed_messages, this.category_wins_messages, this.category_game_start_messages, "randomtexttoletmycodeparselastline" };
/*     */ 
/*     */ 
/*     */       
/* 209 */       MsgCase[] splitters_cases = { MsgCase.KILL, MsgCase.FINAL_KILL, MsgCase.DEATH, MsgCase.BED_SINGLE, MsgCase.WIN, MsgCase.GAME_START };
/*     */ 
/*     */       
/*     */       int i;
/*     */ 
/*     */       
/* 215 */       for (i = 0; i < splitters_cases.length - 1; i++) {
/* 216 */         if (!readFile.contains(splitters_text[i])) {
/* 217 */           ChatSender.addText("\n" + MyChatListener.PREFIX_BEDWARS_MEOW + "&c&lОшибка файла, категории отсутствуют! Восстанови файл к &lзаводским настройкам &7(&e/meow&7)&c!\n");
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 222 */       for (i = 0; i < splitters_cases.length; i++) {
/* 223 */         String[] lines = readFile.split(Pattern.quote(splitters_text[i]))[1].trim().split(Pattern.quote(splitters_text[i + 1]))[0].trim().split("\n");
/* 224 */         String[] arrayOfString1 = lines; int j = arrayOfString1.length; byte b = 0; while (true) { MeowMsg m; if (b < j) { String line = arrayOfString1[b];
/* 225 */             if (line.contains("==="))
/* 226 */               continue;  String l = line.replace("\n", "").trim();
/* 227 */             if (l.length() <= 0)
/* 228 */               continue;  m = new MeowMsg(splitters_cases[i], l);
/*     */             
/* 230 */             if (m.msgcase == MsgCase.BED_SINGLE)
/* 231 */             { if (!m.text.contains(this.string_for_bed_single) && !m.text.contains(this.string_for_bed_multi))
/* 232 */               { meowMessages.add(new MeowMsg(MsgCase.BED_SINGLE, l));
/* 233 */                 meowMessages.add(new MeowMsg(MsgCase.BED_MULTI, l)); }
/*     */               else
/*     */               
/* 236 */               { if (m.text.contains(this.string_for_bed_single)) {
/* 237 */                   m.msgcase = MsgCase.BED_SINGLE;
/* 238 */                   m.text = m.text.replace(this.string_for_bed_single, "").trim();
/*     */                 } 
/* 240 */                 if (m.text.contains(this.string_for_bed_multi)) {
/* 241 */                   m.msgcase = MsgCase.BED_MULTI;
/* 242 */                   m.text = m.text.replace(this.string_for_bed_multi, "").trim();
/*     */                 } 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 248 */                 meowMessages.add(m); }  continue; }  } else { break; }  meowMessages.add(m); b++; }
/*     */       
/*     */       } 
/* 251 */     } catch (Exception ex) {
/* 252 */       ChatSender.addText(this.prefix + "&cФайл с текстом содержит ошибки! Проверь &e%appdata%\\Roaming\\.minecraft\\" + filename);
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void printMeows() {
/* 258 */     ChatSender.addText(this.prefix + "&7Загрузка...\n");
/* 259 */     ChatSender.addText("&fСообщения после &cкила&f:");
/* 260 */     String start_prefix = " &8• &f";
/* 261 */     for (MeowMsg m : meowMessages) {
/* 262 */       if (m.msgcase == MsgCase.KILL) ChatSender.addText(start_prefix + m.text); 
/*     */     } 
/* 264 */     ChatSender.addText("\n&fСообщения после &eфинального кила&f:");
/* 265 */     for (MeowMsg m : meowMessages) {
/* 266 */       if (m.msgcase == MsgCase.FINAL_KILL) ChatSender.addText(start_prefix + m.text); 
/*     */     } 
/* 268 */     ChatSender.addText("\n&fСообщения после &6смерти&f:");
/* 269 */     for (MeowMsg m : meowMessages) {
/* 270 */       if (m.msgcase == MsgCase.DEATH) ChatSender.addText(start_prefix + m.text); 
/*     */     } 
/* 272 */     ChatSender.addText("\n&fСообщения после &aкровати&f:");
/* 273 */     for (MeowMsg m : meowMessages) {
/* 274 */       if (m.msgcase == MsgCase.BED_SINGLE) ChatSender.addText(start_prefix + m.text + " &a" + this.string_for_bed_single); 
/* 275 */       if (m.msgcase == MsgCase.BED_MULTI) ChatSender.addText(start_prefix + m.text + " &c" + this.string_for_bed_multi); 
/*     */     } 
/* 277 */     ChatSender.addText("\n&fСообщения после &bвыиграша&f:");
/* 278 */     for (MeowMsg m : meowMessages) {
/* 279 */       if (m.msgcase == MsgCase.WIN) ChatSender.addText(start_prefix + m.text); 
/*     */     } 
/* 281 */     ChatSender.addText("\n&fСообщения при &9входе в игру&f:");
/* 282 */     for (MeowMsg m : meowMessages) {
/* 283 */       if (m.msgcase == MsgCase.GAME_START) ChatSender.addText(start_prefix + m.text); 
/*     */     } 
/* 285 */     ChatSender.addText(this.prefix + "&eСообщения считаны!");
/*     */   }
/*     */   
/*     */   public String getMeows(MsgCase msgcase) {
/* 289 */     String s = "";
/*     */     
/* 291 */     String variable = "";
/* 292 */     String player_name = (Minecraft.func_71410_x()).field_71439_g.func_70005_c_();
/* 293 */     if (player_name.contains("DimChig")) player_name = "Vanya1337";
/*     */     
/* 295 */     if (msgcase == MsgCase.KILL || msgcase == MsgCase.FINAL_KILL) {
/* 296 */       variable = "&e" + player_name;
/* 297 */     } else if (msgcase == MsgCase.DEATH) {
/* 298 */       variable = "&e" + player_name;
/* 299 */     } else if (msgcase == MsgCase.BED_SINGLE || msgcase == MsgCase.BED_MULTI) {
/* 300 */       String[] colors = { "c", "e", "a", "b", "d", "9" };
/* 301 */       Random rnd = new Random();
/* 302 */       variable = "&" + colors[rnd.nextInt(colors.length)];
/* 303 */     } else if (msgcase == MsgCase.WIN || msgcase == MsgCase.GAME_START) {
/* 304 */       variable = "";
/*     */     } 
/*     */     
/* 307 */     for (MeowMsg m : meowMessages) {
/* 308 */       if (m.msgcase == msgcase) s = s + " &8• &f" + m.getText(variable) + "\n"; 
/*     */     } 
/* 310 */     return s;
/*     */   }
/*     */   
/*     */   public void removeMeessagesWithBadWords() {
/* 314 */     initMeowMessages(true);
/* 315 */     readFile();
/*     */   }
/*     */   
/*     */   public void initMeowMessages() {
/* 319 */     initMeowMessages(false);
/*     */   }
/*     */   
/*     */   public void initMeowMessages(boolean isRemoveBadWords) {
/* 323 */     String[] bad_words = { "соси", "соса", "сосну", "ебал", "трах", "хуе", "ебат", "ебан", "сучк", "сук", "хуй", "пизд", "бля", "пидор", "ебло", "уебо" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 503 */     String[] killMessages = { "Игрок, тебе нужно больше тренироваться!", "Дружок, тебе надо тренироваться", "Малыш, тебе надо тренироваться", "Игрок, ты не няшка <3", "Предупредил", "Игрок, попробуй во время пвп нажимать на мышку", "Игрок, суть игры не умерать, а убивать", "Спасибо, + килл в статистику", "Больше так не делай)" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 515 */     String[] finalKillMessages = { "0", "L", "Не расстраивайся, игрок, в следующий раз получиться!", "Не расстраивайся, игрок, тебе повезет в следующий раз!", "Не грусти, игрок, потренируйся немного и все получиться!", "Не грусти, игрок, в следующий раз все получиться!", "Игрок, потренируйся и приходи в следующий раз!", "Игрок, у тебя скоро все получиться, главное верить в себя!", "Это нормально, игрок, нужно уметь принимать поражение", "Этот прицел просто имба", "В лобби, малыш)", "Игрок, только не плач", "Ой-ой-ой, не повезло тебе, игрок", "Что, игрок, без кроватки сложно получилось?", "Игрок, надо было кровать дефать", "Я гений этой игры", "Я сейчас в очень жосткой форме!", "Вот почему я лучший в мире!", "Игрок, просто я тренируюсь, пока все отдыхают", "Я просто бог в этой игре!", "Поймал на ашибке!" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 541 */     String s = " " + this.string_for_bed_single;
/* 542 */     String m = " " + this.string_for_bed_multi;
/*     */     
/* 544 */     String[] bedMessages = { "Фуу... Какая не вкусная кроватка у команды", "Походу команда счас проиграет" + s, "Походу команда счас проиграют" + m, "Не повезло тебе, команда, попасть со мной в одну катку" + s, "Не повезло вам, команда, попасть со мной в одну катку" + m, "Команда скоро отправится в лобби)" + s, "Команда скоро отправятся в лобби)" + m, "Ой, команда, прости" + s, "Ой, команда, простите" + m, "Команда теперь без кроватки(" + s, "Команда теперь без кроватки(" + m, "Команда, только не плач" + s, "Команда, только не плачте" + m };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 560 */     String[] deathMessages = { "Игрок, тебе просто повезло)" };
/*     */ 
/*     */ 
/*     */     
/* 564 */     String[] winMessages = { "Ура, победа!", "GG" };
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 569 */     String[] gameStartMessages = { "Ребятки, всем удачной игры!", "Ребят, давайте играть без читов", "Народ, давайте не обзываться, ведь это очень обидно. Давайте играть дружно и мирно!", "Кто тоже играет с флюском + в чат", "Кто тоже играет с матиксом + в чат", "Кто тоже играет с модом &c&lBedwars&f&lBro?", "мод &eDexlandMeow &f< &c&lBedwars&f&lBro" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 581 */     String str = "В этом файле ты можешь редактировать и добавлять свои сообщения\nПосле изменений сохрани файл и в майнкрафте напиши /meow, и там ОБНОВИТЬ СООБЩЕНИЯ\nЕсли возникла ошибка, или что-то не работает, удали этот файл и нажми ОБНОВИТЬ СООБЩЕНИЯ\nНе используй \"===\" и не используй перенос строки внутри сообщений!\n\n";
/*     */     
/* 583 */     String[] splitters_text = { this.category_kill_messages, this.category_final_kill_messages, this.category_death_messages, this.category_bed_messages, this.category_wins_messages, this.category_game_start_messages };
/*     */ 
/*     */ 
/*     */     
/* 587 */     String[][] splitters_messages = { killMessages, finalKillMessages, deathMessages, bedMessages, winMessages, gameStartMessages };
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 592 */     if (isRemoveBadWords) {
/* 593 */       for (String[] messages : splitters_messages) {
/* 594 */         for (int j = 0; j < messages.length; j++) {
/* 595 */           boolean isOk = true;
/*     */           
/* 597 */           for (String bad_word : bad_words) {
/* 598 */             if (messages[j].toLowerCase().contains(bad_word)) {
/* 599 */               isOk = false;
/*     */               break;
/*     */             } 
/*     */           } 
/* 603 */           if (!isOk) {
/* 604 */             ChatSender.addText(MyChatListener.PREFIX_BEDWARS_MEOW + "&fУдалено &c" + messages[j]);
/* 605 */             messages[j] = "";
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 612 */     for (int i = 0; i < splitters_text.length; i++) {
/* 613 */       str = str + "===" + splitters_text[i] + "===\n";
/* 614 */       for (String s2 : splitters_messages[i]) {
/* 615 */         if (s2.length() != 0)
/* 616 */           str = str + s2 + "\n"; 
/*     */       } 
/* 618 */       str = str + "\n\n";
/*     */     } 
/*     */     
/* 621 */     FileManager.initFile(filename);
/* 622 */     FileManager.writeToFile(str, filename, false);
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
/* 633 */     if (!this.IS_ACTIVE) return null;
/*     */ 
/*     */ 
/*     */     
/* 637 */     MeowMsg msg = null;
/* 638 */     if (this.meowMessagesQueue == null) this.meowMessagesQueue = new ArrayList<MeowMsg>(); 
/* 639 */     for (MeowMsg m : this.meowMessagesQueue) {
/* 640 */       if (m.msgcase == msgcase) {
/* 641 */         msg = m;
/* 642 */         this.meowMessagesQueue.remove(m);
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 647 */     boolean areMessagesLeft = false;
/* 648 */     for (MeowMsg m : this.meowMessagesQueue) {
/* 649 */       if (m.msgcase == msgcase) {
/* 650 */         areMessagesLeft = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 654 */     if (!areMessagesLeft) {
/*     */       
/* 656 */       ArrayList<MeowMsg> arr = new ArrayList<MeowMsg>();
/* 657 */       for (MeowMsg m : meowMessages) {
/* 658 */         if (m.msgcase == msgcase) arr.add(m); 
/*     */       } 
/* 660 */       if (arr.size() == 0) return null;
/*     */       
/* 662 */       Collections.shuffle(arr);
/* 663 */       if (msg == null) {
/* 664 */         msg = arr.get(0);
/* 665 */         arr.remove(0);
/* 666 */       } else if (arr.size() > 1) {
/* 667 */         for (; ((MeowMsg)arr.get(0)).text.equals(msg.text); Collections.shuffle(arr));
/*     */       } 
/* 669 */       this.meowMessagesQueue.addAll(arr);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 676 */     return msg.getText(variable);
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\hints\BedwarsMeow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */