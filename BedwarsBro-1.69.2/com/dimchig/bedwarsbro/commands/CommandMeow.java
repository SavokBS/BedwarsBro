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
/*     */ 
/*     */ 
/*     */ public class CommandMeow
/*     */   extends CommandBase
/*     */ {
/*  28 */   public static String command_text = "/meow";
/*     */   Main main_instance;
/*     */   
/*     */   public CommandMeow(Main main) {
/*  32 */     this; command_text = command_text.replace("/", "");
/*  33 */     this.main_instance = main;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_71519_b(ICommandSender sender) {
/*  38 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71517_b() {
/*  43 */     this; return command_text;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71518_a(ICommandSender sender) {
/*  48 */     return "Meow mod";
/*     */   }
/*     */ 
/*     */   
/*     */   void printHelpInfo() {
/*  53 */     String prefix = MyChatListener.PREFIX_BEDWARS_MEOW;
/*  54 */     ChatSender.addText("");
/*  55 */     ChatSender.addText(prefix + "&fПомощь по моду &f(Нажимай на сообщения)&f:");
/*     */     
/*     */     try {
/*  58 */       String path2file = (Minecraft.func_71410_x()).field_71412_D.getCanonicalPath() + "\\" + BedwarsMeow.filename;
/*     */       try {
/*  60 */         ChatSender.addHoverFileText("&7• &eДобавить/Изменить свои сообщения", "&eНажми&f, чтоб открыть &8" + path2file, path2file);
/*  61 */       } catch (IOException e) {
/*  62 */         ChatSender.addClickSuggestAndHoverText("&7• &eДобавить/Изменить свои сообщения", "&eНажми&f, чтоб скопировать путь", path2file);
/*     */       } 
/*  64 */     } catch (Exception e) {
/*  65 */       ChatSender.addText("&7• &cНе удалось найти файл с сообщениями");
/*     */     } 
/*     */     
/*  68 */     this; ChatSender.addClickText("&7• &bОбновить сообщения из файла", "/" + command_text + " update");
/*  69 */     this; ChatSender.addHoverText("&7• &cВосстановить файл к заводским", "Сбросит файл как при устоновке мода\n&fНапиши сам в чат эту команду:\n\n&c/" + command_text + " reset");
/*  70 */     this; ChatSender.addHoverText("&7• &6Убрать сообщения с матами", "Уберет сообщения с плохими словами\n&fНапиши сам в чат эту команду:\n\n&6/" + command_text + " remove_bad_words");
/*  71 */     ChatSender.addText("");
/*  72 */     this; String starting = "/" + command_text + " print ";
/*  73 */     ChatSender.addClickText("&7• &fСообщения после &cкила", starting + "kill");
/*  74 */     ChatSender.addClickText("&7• &fСообщения после &eфинального кила", starting + "final_kill");
/*  75 */     ChatSender.addClickText("&7• &fСообщения после &6смерти", starting + "death");
/*  76 */     ChatSender.addClickText("&7• &fСообщения после &aкровати &7(одиночные)", starting + "bed_single");
/*  77 */     ChatSender.addClickText("&7• &fСообщения после &aкровати &7(командные)", starting + "bed_multi");
/*  78 */     ChatSender.addClickText("&7• &fСообщения в &bначале игры", starting + "game_start");
/*  79 */     ChatSender.addClickText("&7• &fСообщения при &dпобеде", starting + "win");
/*  80 */     ChatSender.addText("");
/*     */     
/*  82 */     boolean is_mod_active = Main.getConfigBool(Main.CONFIG_MSG.BEDWARS_MEOW);
/*     */     
/*  84 */     String s_toggle_mod = "&cВыключен";
/*  85 */     String s_toggle_mod_hover = "&fНажми, чтоб &aвключить";
/*  86 */     if (is_mod_active) {
/*  87 */       s_toggle_mod = "&aВключен";
/*  88 */       s_toggle_mod_hover = "&fНажми, чтоб &cвыключить";
/*     */     } 
/*     */     
/*  91 */     String s_toggle_mod_colors = "&cНе используются";
/*  92 */     String s_toggle_mod_colors_hover = "&fНажми, чтоб &aиспользовать &fцветовую палитру &e/rs";
/*  93 */     if (Main.getConfigBool(Main.CONFIG_MSG.BEDWARS_MEOW_WITH_COLORS)) {
/*  94 */       s_toggle_mod_colors = "&aИспользуются";
/*  95 */       s_toggle_mod_colors_hover = "&fНажми, чтоб &cне использовать &fцветовую палитру &e/rs";
/*     */     } 
/*     */     
/*  98 */     this; ChatSender.addClickAndHoverText("&7• &fМод &8▸ " + s_toggle_mod, s_toggle_mod_hover, "/" + command_text + " toggle");
/*     */     
/* 100 */     if (is_mod_active) {
/* 101 */       this; ChatSender.addClickAndHoverText("&7• &fЦвета &8▸ " + s_toggle_mod_colors + " &7(только для &cд&eо&aн&bа&9т&dе&cр&6о&eв&f, настрой командой &e/rs&7)", s_toggle_mod_colors_hover, "/" + command_text + " toggleColors");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/* 107 */     String prefix = MyChatListener.PREFIX_BEDWARS_MEOW;
/*     */     
/* 109 */     if (args.length == 0) {
/* 110 */       printHelpInfo();
/*     */       return;
/*     */     } 
/* 113 */     if (args.length == 2 && args[0].trim().equals("print")) {
/* 114 */       String c = args[1].trim();
/* 115 */       Main.bedwarsMeow.IS_USE_COLORS = true;
/* 116 */       if (c.equals("kill")) {
/* 117 */         ChatSender.addText(prefix + "Сообщения после &bКИЛА:&f\n" + Main.bedwarsMeow.getMeows(BedwarsMeow.MsgCase.KILL));
/* 118 */       } else if (c.equals("final_kill")) {
/* 119 */         ChatSender.addText(prefix + "Сообщения после &bФИНАЛЬНОГО КИЛА:&f\n" + Main.bedwarsMeow.getMeows(BedwarsMeow.MsgCase.FINAL_KILL));
/* 120 */       } else if (c.equals("death")) {
/* 121 */         ChatSender.addText(prefix + "Сообщения после &bСМЕРТИ:&f\n" + Main.bedwarsMeow.getMeows(BedwarsMeow.MsgCase.DEATH));
/* 122 */       } else if (c.equals("bed_single")) {
/* 123 */         ChatSender.addText(prefix + "Сообщения про &bКРОВАТЬ &e(одиночный режим):&f\n" + Main.bedwarsMeow.getMeows(BedwarsMeow.MsgCase.BED_SINGLE));
/* 124 */       } else if (c.equals("bed_multi")) {
/* 125 */         ChatSender.addText(prefix + "Сообщения про &bКРОВАТЬ &e(командный режим):&f\n" + Main.bedwarsMeow.getMeows(BedwarsMeow.MsgCase.BED_MULTI));
/* 126 */       } else if (c.equals("win")) {
/* 127 */         ChatSender.addText(prefix + "Сообщения после &bВЫЙГРАША:&f\n" + Main.bedwarsMeow.getMeows(BedwarsMeow.MsgCase.WIN));
/* 128 */       } else if (c.equals("game_start")) {
/* 129 */         ChatSender.addText(prefix + "Сообщения в &bНАЧАЛЕ ИГРЫ:&f\n" + Main.bedwarsMeow.getMeows(BedwarsMeow.MsgCase.GAME_START));
/*     */       } 
/* 131 */       Main.bedwarsMeow.updateBooleans();
/* 132 */     } else if (args[0].trim().equals("update")) {
/* 133 */       Main.bedwarsMeow.readFile();
/*     */       
/* 135 */       if (BedwarsMeow.meowMessages != null) {
/* 136 */         int cnt = BedwarsMeow.meowMessages.size();
/* 137 */         ChatSender.addText(prefix + "&bОбновлено &l" + cnt + "&b сообщени" + MyChatListener.getNumberEnding(cnt, "е", "я", "й") + "!");
/* 138 */         MyChatListener.playSound(MyChatListener.SOUND_PARTY_CHAT);
/*     */       }
/*     */     
/* 141 */     } else if (args[0].trim().equals("toggle")) {
/*     */       
/* 143 */       Main.clientConfig.get(Main.CONFIG_MSG.BEDWARS_MEOW.text).set(!Main.clientConfig.get(Main.CONFIG_MSG.BEDWARS_MEOW.text).getBoolean());
/*     */       
/* 145 */       printHelpInfo();
/* 146 */     } else if (args[0].trim().equals("toggleColors")) {
/* 147 */       Main.clientConfig.get(Main.CONFIG_MSG.BEDWARS_MEOW_WITH_COLORS.text).set(!Main.clientConfig.get(Main.CONFIG_MSG.BEDWARS_MEOW_WITH_COLORS.text).getBoolean());
/*     */       
/* 149 */       printHelpInfo();
/* 150 */     } else if (args[0].trim().equals("reset")) {
/* 151 */       Main.bedwarsMeow.initMeowMessages();
/* 152 */       Main.bedwarsMeow.readFile();
/*     */       
/* 154 */       printHelpInfo();
/*     */       
/* 156 */       if (BedwarsMeow.meowMessages != null) {
/* 157 */         int cnt = BedwarsMeow.meowMessages.size();
/* 158 */         ChatSender.addText(prefix + "&cФайл восстановлен&7, &bсчитано &l" + cnt + "&b сообщени" + MyChatListener.getNumberEnding(cnt, "е", "я", "й") + "!");
/* 159 */         MyChatListener.playSound(MyChatListener.SOUND_PARTY_CHAT);
/*     */       } 
/* 161 */     } else if (args[0].trim().equals("remove_bad_words")) {
/* 162 */       Main.bedwarsMeow.removeMeessagesWithBadWords();
/*     */       
/* 164 */       printHelpInfo();
/*     */       
/* 166 */       if (BedwarsMeow.meowMessages != null) {
/* 167 */         int cnt = BedwarsMeow.meowMessages.size();
/* 168 */         ChatSender.addText(prefix + "&6Файл обновлен&7, &bсчитано &l" + cnt + "&b сообщени" + MyChatListener.getNumberEnding(cnt, "е", "я", "й") + "!");
/* 169 */         MyChatListener.playSound(MyChatListener.SOUND_PARTY_CHAT);
/*     */       } 
/*     */     } else {
/* 172 */       printHelpInfo();
/*     */       return;
/*     */     } 
/* 175 */     Main.saveConfig();
/* 176 */     Main.bedwarsMeow.updateBooleans();
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\commands\CommandMeow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */