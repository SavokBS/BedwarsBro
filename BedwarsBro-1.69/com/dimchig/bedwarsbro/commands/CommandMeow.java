/*     */ package com.dimchig.bedwarsbro.commands;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.ChatSender;
/*     */ import com.dimchig.bedwarsbro.Main;
/*     */ import com.dimchig.bedwarsbro.MyChatListener;
/*     */ import com.dimchig.bedwarsbro.hints.BedwarsMeow;
/*     */ import java.io.IOException;
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
/*     */ 
/*     */ 
/*     */ public class CommandMeow
/*     */   extends CommandBase
/*     */ {
/*  26 */   public static String command_text = "/meow";
/*     */   Main main_instance;
/*     */   
/*     */   public CommandMeow(Main main) {
/*  30 */     this; command_text = command_text.replace("/", "");
/*  31 */     this.main_instance = main;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_71519_b(ICommandSender sender) {
/*  36 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71517_b() {
/*  41 */     this; return command_text;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71518_a(ICommandSender sender) {
/*  46 */     return "Meow mod";
/*     */   }
/*     */   
/*     */   void printHelpInfo() {
/*  50 */     String prefix = MyChatListener.PREFIX_BEDWARS_MEOW;
/*  51 */     ChatSender.addText("");
/*  52 */     ChatSender.addText(prefix + "&fПомощь по моду &f(Нажимай на сообщения)&f:");
/*     */     
/*     */     try {
/*  55 */       String path2file = (Minecraft.func_71410_x()).field_71412_D.getCanonicalPath() + "\\" + BedwarsMeow.filename;
/*     */       try {
/*  57 */         ChatSender.addHoverFileText("&7• &eДобавить/Изменить свои сообщения", "&eНажми&f, чтоб открыть &8" + path2file, path2file);
/*  58 */       } catch (IOException e) {
/*  59 */         ChatSender.addClickSuggestAndHoverText("&7• &eДобавить/Изменить свои сообщения", "&eНажми&f, чтоб скопировать путь", path2file);
/*     */       } 
/*  61 */     } catch (Exception e) {
/*  62 */       ChatSender.addText("&7• &cНе удалось найти файл с сообщениями");
/*     */     } 
/*     */     
/*  65 */     this; ChatSender.addClickText("&7• &bОбновить сообщения из файла", "/" + command_text + " update");
/*  66 */     this; ChatSender.addHoverText("&7• &cВосстановить файл к заводским", "Сбросит файл как при устоновке мода\n&fНапиши сам в чат эту команду:\n\n&c/" + command_text + " reset");
/*  67 */     this; ChatSender.addHoverText("&7• &6Убрать сообщения с матами", "Уберет сообщения с плохими словами\n&fНапиши сам в чат эту команду:\n\n&6/" + command_text + " remove_bad_words");
/*  68 */     ChatSender.addText("");
/*  69 */     this; String starting = "/" + command_text + " print ";
/*  70 */     ChatSender.addClickText("&7• &fСообщения после &cкила", starting + "kill");
/*  71 */     ChatSender.addClickText("&7• &fСообщения после &eфинального кила", starting + "final_kill");
/*  72 */     ChatSender.addClickText("&7• &fСообщения после &6смерти", starting + "death");
/*  73 */     ChatSender.addClickText("&7• &fСообщения после &aкровати &7(одиночные)", starting + "bed_single");
/*  74 */     ChatSender.addClickText("&7• &fСообщения после &aкровати &7(командные)", starting + "bed_multi");
/*  75 */     ChatSender.addClickText("&7• &fСообщения в &bначале игры", starting + "game_start");
/*  76 */     ChatSender.addClickText("&7• &fСообщения при &dпобеде", starting + "win");
/*  77 */     ChatSender.addText("");
/*     */     
/*  79 */     boolean is_mod_active = Main.getConfigBool(Main.CONFIG_MSG.BEDWARS_MEOW);
/*     */     
/*  81 */     String s_toggle_mod = "&cВыключен";
/*  82 */     String s_toggle_mod_hover = "&fНажми, чтоб &aвключить";
/*  83 */     if (is_mod_active) {
/*  84 */       s_toggle_mod = "&aВключен";
/*  85 */       s_toggle_mod_hover = "&fНажми, чтоб &cвыключить";
/*     */     } 
/*     */     
/*  88 */     String s_toggle_mod_colors = "&cНе используются";
/*  89 */     String s_toggle_mod_colors_hover = "&fНажми, чтоб &aиспользовать &fцвета в чате";
/*  90 */     if (Main.getConfigBool(Main.CONFIG_MSG.BEDWARS_MEOW_WITH_COLORS)) {
/*  91 */       s_toggle_mod_colors = "&aИспользуются";
/*  92 */       s_toggle_mod_colors_hover = "&fНажми, чтоб &cне использовать &fцвета в чате";
/*     */     } 
/*     */     
/*  95 */     String s_toggle_mod_rainbow_colors = "&cВыключен";
/*  96 */     String s_toggle_mod_rainbow_colors_hover = "&fНажми, чтоб &aвключить &fрадужные сообщения";
/*  97 */     if (Main.getConfigBool(Main.CONFIG_MSG.BEDWARS_MEOW_WITH_COLORS_RAINBOW)) {
/*  98 */       s_toggle_mod_rainbow_colors = "&aВключен";
/*  99 */       s_toggle_mod_rainbow_colors_hover = "&fНажми, чтоб &cвыключить &fрадужные сообщения";
/*     */     } 
/*     */     
/* 102 */     this; ChatSender.addClickAndHoverText("&7• &fМод &8▸ " + s_toggle_mod, s_toggle_mod_hover, "/" + command_text + " toggle");
/*     */     
/* 104 */     if (is_mod_active) {
/* 105 */       this; ChatSender.addClickAndHoverText("&7• &fЦвета &8▸ " + s_toggle_mod_colors + " &7(только для &cд&eо&aн&bа&9т&dа &fLEGEND&7)", s_toggle_mod_colors_hover, "/" + command_text + " toggleColors");
/* 106 */       this; ChatSender.addClickAndHoverText("&7• &fРежим радуги &8▸ " + s_toggle_mod_rainbow_colors + " &7(только для &cд&eо&aн&bа&9т&dа &fLEGEND&7)", s_toggle_mod_rainbow_colors_hover, "/" + command_text + " toggleRainbowColors");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/* 112 */     String prefix = MyChatListener.PREFIX_BEDWARS_MEOW;
/*     */     
/* 114 */     if (args.length == 0) {
/* 115 */       printHelpInfo();
/*     */       return;
/*     */     } 
/* 118 */     if (args.length == 2 && args[0].trim().equals("print")) {
/* 119 */       String c = args[1].trim();
/* 120 */       Main.bedwarsMeow.IS_USE_COLORS = true;
/* 121 */       if (c.equals("kill")) {
/* 122 */         ChatSender.addText(prefix + "Сообщения после &bКИЛА:&f\n" + Main.bedwarsMeow.getMeows(BedwarsMeow.MsgCase.KILL));
/* 123 */       } else if (c.equals("final_kill")) {
/* 124 */         ChatSender.addText(prefix + "Сообщения после &bФИНАЛЬНОГО КИЛА:&f\n" + Main.bedwarsMeow.getMeows(BedwarsMeow.MsgCase.FINAL_KILL));
/* 125 */       } else if (c.equals("death")) {
/* 126 */         ChatSender.addText(prefix + "Сообщения после &bСМЕРТИ:&f\n" + Main.bedwarsMeow.getMeows(BedwarsMeow.MsgCase.DEATH));
/* 127 */       } else if (c.equals("bed_single")) {
/* 128 */         ChatSender.addText(prefix + "Сообщения про &bКРОВАТЬ &e(одиночный режим):&f\n" + Main.bedwarsMeow.getMeows(BedwarsMeow.MsgCase.BED_SINGLE));
/* 129 */       } else if (c.equals("bed_multi")) {
/* 130 */         ChatSender.addText(prefix + "Сообщения про &bКРОВАТЬ &e(командный режим):&f\n" + Main.bedwarsMeow.getMeows(BedwarsMeow.MsgCase.BED_MULTI));
/* 131 */       } else if (c.equals("win")) {
/* 132 */         ChatSender.addText(prefix + "Сообщения после &bВЫЙГРАША:&f\n" + Main.bedwarsMeow.getMeows(BedwarsMeow.MsgCase.WIN));
/* 133 */       } else if (c.equals("game_start")) {
/* 134 */         ChatSender.addText(prefix + "Сообщения в &bНАЧАЛЕ ИГРЫ:&f\n" + Main.bedwarsMeow.getMeows(BedwarsMeow.MsgCase.GAME_START));
/*     */       } 
/* 136 */       Main.bedwarsMeow.updateBooleans();
/* 137 */     } else if (args[0].trim().equals("update")) {
/* 138 */       Main.bedwarsMeow.readFile();
/*     */       
/* 140 */       if (BedwarsMeow.meowMessages != null) {
/* 141 */         int cnt = BedwarsMeow.meowMessages.size();
/* 142 */         ChatSender.addText(prefix + "&bОбновлено &l" + cnt + "&b сообщени" + MyChatListener.getNumberEnding(cnt, "е", "я", "й") + "!");
/* 143 */         MyChatListener.playSound(MyChatListener.SOUND_PARTY_CHAT);
/*     */       }
/*     */     
/* 146 */     } else if (args[0].trim().equals("toggle")) {
/*     */       
/* 148 */       Main.clientConfig.get(Main.CONFIG_MSG.BEDWARS_MEOW.text).set(!Main.clientConfig.get(Main.CONFIG_MSG.BEDWARS_MEOW.text).getBoolean());
/*     */       
/* 150 */       printHelpInfo();
/* 151 */     } else if (args[0].trim().equals("toggleColors")) {
/* 152 */       Main.clientConfig.get(Main.CONFIG_MSG.BEDWARS_MEOW_WITH_COLORS.text).set(!Main.clientConfig.get(Main.CONFIG_MSG.BEDWARS_MEOW_WITH_COLORS.text).getBoolean());
/*     */       
/* 154 */       printHelpInfo();
/* 155 */     } else if (args[0].trim().equals("toggleRainbowColors")) {
/* 156 */       Main.clientConfig.get(Main.CONFIG_MSG.BEDWARS_MEOW_WITH_COLORS_RAINBOW.text).set(!Main.clientConfig.get(Main.CONFIG_MSG.BEDWARS_MEOW_WITH_COLORS_RAINBOW.text).getBoolean());
/*     */       
/* 158 */       printHelpInfo();
/* 159 */     } else if (args[0].trim().equals("reset")) {
/* 160 */       Main.bedwarsMeow.initMeowMessages();
/* 161 */       Main.bedwarsMeow.readFile();
/*     */       
/* 163 */       printHelpInfo();
/*     */       
/* 165 */       if (BedwarsMeow.meowMessages != null) {
/* 166 */         int cnt = BedwarsMeow.meowMessages.size();
/* 167 */         ChatSender.addText(prefix + "&cФайл восстановлен&7, &bсчитано &l" + cnt + "&b сообщени" + MyChatListener.getNumberEnding(cnt, "е", "я", "й") + "!");
/* 168 */         MyChatListener.playSound(MyChatListener.SOUND_PARTY_CHAT);
/*     */       } 
/* 170 */     } else if (args[0].trim().equals("remove_bad_words")) {
/* 171 */       Main.bedwarsMeow.removeMeessagesWithBadWords();
/*     */       
/* 173 */       printHelpInfo();
/*     */       
/* 175 */       if (BedwarsMeow.meowMessages != null) {
/* 176 */         int cnt = BedwarsMeow.meowMessages.size();
/* 177 */         ChatSender.addText(prefix + "&6Файл обновлен&7, &bсчитано &l" + cnt + "&b сообщени" + MyChatListener.getNumberEnding(cnt, "е", "я", "й") + "!");
/* 178 */         MyChatListener.playSound(MyChatListener.SOUND_PARTY_CHAT);
/*     */       } 
/*     */     } else {
/* 181 */       printHelpInfo();
/*     */       return;
/*     */     } 
/* 184 */     Main.saveConfig();
/* 185 */     Main.bedwarsMeow.updateBooleans();
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\commands\CommandMeow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */