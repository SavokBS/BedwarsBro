/*     */ package com.dimchig.bedwarsbro.commands;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.ChatSender;
/*     */ import com.dimchig.bedwarsbro.CustomScoreboard;
/*     */ import com.dimchig.bedwarsbro.Main;
/*     */ import com.dimchig.bedwarsbro.MyChatListener;
/*     */ import com.dimchig.bedwarsbro.hints.BedwarsMeow;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import java.util.regex.Pattern;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.command.CommandBase;
/*     */ import net.minecraft.command.CommandException;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommandRainbowMessageSetter
/*     */   extends CommandBase
/*     */ {
/*  27 */   public static String command_text = "rs";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String randomFilterString;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_71519_b(ICommandSender sender) {
/*     */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CommandRainbowMessageSetter()
/*     */   {
/*  48 */     this.randomFilterString = "lno";
/*     */     this;
/*     */     this;
/*  51 */     command_text = command_text.replace("/", ""); } public String setRandomPalitra() { String[] available_colors = { "2", "3", "5", "6", "9", "a", "b", "c", "d", "e", "f" };
/*  52 */     Random rnd = new Random();
/*  53 */     int min_size = 2;
/*  54 */     int max_size = 5;
/*  55 */     int count = Math.min(min_size + rnd.nextInt(max_size - min_size + 1), max_size);
/*  56 */     ArrayList<String> generated_colors = new ArrayList<String>();
/*  57 */     for (int i = 0; i < count; i++) {
/*  58 */       String rnd_color = "0";
/*     */       do {
/*  60 */         rnd_color = available_colors[rnd.nextInt(available_colors.length)];
/*  61 */       } while (generated_colors.contains(rnd_color));
/*     */       
/*  63 */       generated_colors.add(rnd_color);
/*     */     } 
/*  65 */     String output_str = "";
/*  66 */     for (String s : generated_colors) {
/*  67 */       output_str = output_str + s;
/*     */     }
/*  69 */     output_str = output_str + "+l";
/*  70 */     for (String s : this.randomFilterString.split("")) {
/*  71 */       if (rnd.nextBoolean() && !output_str.contains(s)) output_str = output_str + s;
/*     */     
/*     */     } 
/*  74 */     Main.clientConfig.get(Main.CONFIG_MSG.RAINBOW_MESSAGE_COLORS.text).set(output_str);
/*  75 */     return output_str; }
/*     */    public String func_71517_b() {
/*     */     this;
/*     */     return command_text;
/*     */   } public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/*  80 */     String current_palitra = Main.getConfigString(Main.CONFIG_MSG.RAINBOW_MESSAGE_COLORS);
/*  81 */     String tutorial_colorcodes = "&fЦвета:\n &11 &22 &33 &44 &55 &66 &77 &88 &99 &aa &bb &cc &dd &ee &ff \n&fФорматирвока:\n &fl &7- &f&lжирный&7\n &fn&7 - &f&nподчеркнутый&7\n &fo &7- &f&oкурсив\n &fm &7- &f&mзачеркнутый&7\n&e&l&n&oМожно совмещать&7 (e+lno)";
/*     */ 
/*     */     
/*  84 */     if (args.length <= 0 || args[0].equals("help")) {
/*  85 */       ChatSender.addText("&aТекущаяя палитра: &f" + current_palitra);
/*  86 */       ChatSender.addText("&e/rs &bпалитра &7- &fустановить палитру &7(&e/rs &bade+ln&7)");
/*  87 */       ChatSender.addText("&e/rs rainbow &7- &cр&6а&eд&aу&bг&dа");
/*  88 */       ChatSender.addText("&e/rs add &aкод &7- &fдобавить что-то");
/*  89 */       ChatSender.addText("&e/rs remove &cкод &7- &fубрать символы из существующего");
/*  90 */       ChatSender.addText("&e/rs mirror &7- &fотзеркалить палитру");
/*  91 */       ChatSender.addText("&e/rs team &7- &fустановить цвет под команду");
/*  92 */       ChatSender.addHoverText("&e/rs mode &7[&b0&7/&b1&7] &7- &fустановить режим&c*", "&e0 &7- &fадаптивная длина\n&e1 &7- &fцвета меняются через каждое слово");
/*  93 */       ChatSender.addText("&e/rs global &7[&bon&7/&boff&7]&7- &fписать всегда в глобальный чат &7(в лобби не будет \"!\"");
/*  94 */       ChatSender.addText("&e/rs random &7- &fрандомная палитра");
/*  95 */       ChatSender.addText("&e/rs randomstyle &7[lnom&7] &7- &fУстановить доступные рандомные форматировки");
/*  96 */       ChatSender.addText("&e/rs autorandom &7[&bon&7/&boff&7]&7- &fпосле каждого сообщения ставить рандомную палитру");
/*     */       
/*  98 */       ChatSender.addHoverText("&aНаведи &a&nсюда&a, чтоб увидеть все цвет-коды", tutorial_colorcodes);
/*     */       
/*     */       return;
/*     */     } 
/* 102 */     String[] available_colors_to_set = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "l", "m", "n", "o", "k", "+" };
/*     */ 
/*     */ 
/*     */     
/* 106 */     String output_str = "f";
/* 107 */     String arg0 = args[0];
/*     */     
/* 109 */     BedwarsMeow.IS_AUTO_RANDOM_ACTIVE = false;
/*     */     
/* 111 */     if (arg0.equals("random"))
/* 112 */     { output_str = setRandomPalitra(); }
/* 113 */     else { if (arg0.equals("randomstyle") && args.length == 2) {
/* 114 */         this.randomFilterString = args[1];
/* 115 */         ChatSender.addText("Стили установлены!"); return;
/*     */       } 
/* 117 */       if (arg0.equals("rainbow")) {
/* 118 */         output_str = "c6eabd+l";
/* 119 */       } else if (arg0.equals("add") && args.length == 2) {
/* 120 */         output_str = current_palitra;
/*     */         
/* 122 */         for (int i = 0; i < args[1].length(); i++) {
/* 123 */           String s = "" + args[1].charAt(i);
/* 124 */           if (s.equals("l") || s.equals("n") || s.equals("o") || s.equals("m"))
/* 125 */           { if (!output_str.contains("+")) output_str = output_str + "+";
/*     */             
/* 127 */             output_str = output_str + s; }
/*     */           
/* 129 */           else if (output_str.contains("+"))
/* 130 */           { output_str = output_str.split(Pattern.quote("+"))[0] + s + "+" + output_str.split(Pattern.quote("+"))[1]; }
/* 131 */           else { output_str = output_str + s; }
/*     */ 
/*     */         
/*     */         } 
/* 135 */       } else if (arg0.equals("remove") && args.length == 2) {
/* 136 */         output_str = current_palitra.replace(args[1], "");
/* 137 */         if (output_str.endsWith("+")) {
/* 138 */           output_str = output_str.substring(0, output_str.length() - 1);
/*     */         }
/* 140 */       } else if (arg0.equals("mirror") && args.length == 1) {
/* 141 */         String[] split = current_palitra.split(Pattern.quote("+"));
/* 142 */         String colors = split[0];
/*     */         
/* 144 */         String reverse = (new StringBuilder(colors)).reverse().toString();
/*     */         
/* 146 */         output_str = colors + reverse;
/* 147 */         if (split.length > 1) output_str = output_str + "+" + split[1]; 
/*     */       } else {
/* 149 */         if (arg0.equals("global") && args.length == 2) {
/* 150 */           output_str = current_palitra;
/* 151 */           boolean b = false;
/* 152 */           if (args[1].equals("on")) b = true; 
/* 153 */           Main.clientConfig.get(Main.CONFIG_MSG.RAINBOW_MESSAGE_GLOBAL.text).set(b);
/* 154 */           ChatSender.addText("Писать в глобальный чат - " + (b ? "&aвключено" : "&cвыключено")); return;
/*     */         } 
/* 156 */         if (arg0.equals("autorandom") && args.length == 2) {
/* 157 */           output_str = current_palitra;
/* 158 */           boolean b = false;
/* 159 */           if (args[1].equals("on")) b = true; 
/* 160 */           BedwarsMeow.IS_AUTO_RANDOM_ACTIVE = b;
/* 161 */           ChatSender.addText("Рандомная палитра - " + (b ? "&aвключена" : "&cвыключена")); return;
/*     */         } 
/* 163 */         if (arg0.equals("mode")) {
/* 164 */           output_str = current_palitra;
/*     */           try {
/* 166 */             int x = Integer.parseInt(args[1]);
/* 167 */             if (x != 0 && x != 1) {
/* 168 */               ChatSender.addText("&cСтавь только 1 или 0!");
/*     */               return;
/*     */             } 
/* 171 */             Main.clientConfig.get(Main.CONFIG_MSG.RAINBOW_MESSAGE_MODE.text).set(x);
/* 172 */           } catch (Exception ex) {
/* 173 */             ChatSender.addText("&cСтавь только 1 или 0!");
/*     */             return;
/*     */           } 
/* 176 */         } else if (arg0.equals("team")) {
/*     */           try {
/* 178 */             CustomScoreboard.TEAM_COLOR tc = MyChatListener.getEntityTeamColorByTeam((Minecraft.func_71410_x()).field_71439_g.func_96124_cp().func_96661_b());
/* 179 */             if (tc == CustomScoreboard.TEAM_COLOR.RED) { output_str = "c4"; }
/* 180 */             else if (tc == CustomScoreboard.TEAM_COLOR.YELLOW) { output_str = "e6"; }
/* 181 */             else if (tc == CustomScoreboard.TEAM_COLOR.GREEN) { output_str = "a2"; }
/* 182 */             else if (tc == CustomScoreboard.TEAM_COLOR.AQUA) { output_str = "b"; }
/* 183 */             else if (tc == CustomScoreboard.TEAM_COLOR.BLUE) { output_str = "b"; }
/* 184 */             else if (tc == CustomScoreboard.TEAM_COLOR.PINK) { output_str = "5d"; }
/* 185 */             else if (tc == CustomScoreboard.TEAM_COLOR.GRAY) { output_str = "f7"; }
/* 186 */             else if (tc == CustomScoreboard.TEAM_COLOR.WHITE) { output_str = "f"; }
/*     */             else
/* 188 */             { ChatSender.addText("&cКоманда не разпознана!");
/*     */               return; }
/*     */             
/* 191 */             output_str = output_str + "+l";
/* 192 */           } catch (Exception e) {
/* 193 */             ChatSender.addText("&cКоманда не разпознана!");
/*     */             return;
/*     */           } 
/* 196 */         } else if (arg0.length() > 0) {
/*     */           
/* 198 */           String new_str = arg0;
/* 199 */           for (int i = 0; i < new_str.length(); i++) {
/* 200 */             boolean contains = false;
/* 201 */             for (int j = 0; j < available_colors_to_set.length; j++) {
/* 202 */               if (available_colors_to_set[j].equals("" + new_str.charAt(i))) {
/* 203 */                 contains = true;
/*     */                 break;
/*     */               } 
/*     */             } 
/* 207 */             if (!contains) {
/* 208 */               ChatSender.addText("&cКод \"&e" + new_str.charAt(i) + "&c\" не существует!\n" + tutorial_colorcodes);
/*     */               return;
/*     */             } 
/*     */           } 
/* 212 */           output_str = arg0;
/*     */         } 
/*     */       }  }
/* 215 */      ChatSender.addText("&7Сгенерированная строка: &f" + output_str + "&7, примеры:");
/*     */     
/* 217 */     Main.clientConfig.get(Main.CONFIG_MSG.RAINBOW_MESSAGE_COLORS.text).set(output_str);
/*     */     
/* 219 */     String msg = CommandRainbowMessage.generateRainbowMessage("Бро, тебе надо больше тренироваться!", 1, -1);
/* 220 */     if (msg != null) {
/* 221 */       ChatSender.addText(msg);
/*     */     }
/* 223 */     msg = CommandRainbowMessage.generateRainbowMessage("Унизил лоха", 1, -1);
/* 224 */     if (msg != null) {
/* 225 */       ChatSender.addText(msg);
/*     */     }
/*     */     
/* 228 */     Main.saveConfig();
/*     */   }
/*     */   
/*     */   public String func_71518_a(ICommandSender sender) {
/*     */     return "Makes message rainbow setter";
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\commands\CommandRainbowMessageSetter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */