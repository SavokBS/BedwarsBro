/*     */ package com.dimchig.bedwarsbro.commands;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.ChatSender;
/*     */ import com.dimchig.bedwarsbro.MyChatListener;
/*     */ import java.util.Collection;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.network.NetworkPlayerInfo;
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
/*     */ public class CommandDexlandMeowSpoof
/*     */   extends CommandBase
/*     */ {
/*  28 */   public static String command_text = "meowspoof";
/*     */   
/*     */   public CommandDexlandMeowSpoof() {
/*  31 */     this; this; command_text = command_text.replace("/", "");
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
/*  46 */     return "Makes message rainbow setter";
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/*  51 */     String[] cmds = { "kill", "death", "bed", "join", "win" };
/*     */     
/*  53 */     String av_cmds = "";
/*  54 */     for (String c : cmds) {
/*  55 */       av_cmds = av_cmds + "&b" + c + "&7/";
/*     */     }
/*  57 */     av_cmds = av_cmds.substring(0, av_cmds.length() - 3);
/*     */     
/*  59 */     if (args.length < 2) {
/*  60 */       this; ChatSender.addText("&dУстановить все 5 сообщений:\n  /" + command_text + " &eник &aсообщение");
/*  61 */       this; ChatSender.addText("&dУстановить одно сообщение:\n&f  /" + command_text + " " + av_cmds + " &eник &aсообщение\n     &7(можно юзать %player% при &bkill&7)");
/*  62 */       this; ChatSender.addText("&dЗаставить сказать сообщение:\n&f  /" + command_text + " &cfake&bdeath&7/&cfake&bjoin&7/&cfake&bwin &eник");
/*     */       
/*     */       return;
/*     */     } 
/*  66 */     int start_idx = 1;
/*  67 */     String type = "all";
/*  68 */     String nick = args[0];
/*     */ 
/*     */     
/*  71 */     String local_command = "";
/*  72 */     for (String c : cmds) {
/*  73 */       if (args[0].equals(c)) {
/*  74 */         if (args.length < 3)
/*  75 */           return;  type = c;
/*  76 */         nick = args[1];
/*  77 */         start_idx = 2;
/*  78 */       } else if (args[0].contains("fake") && args[0].contains(c)) {
/*  79 */         type = "fake" + c;
/*  80 */         local_command = c;
/*  81 */         nick = args[1];
/*  82 */         start_idx = -1;
/*     */       } 
/*     */     } 
/*     */     
/*  86 */     if (type.contains("fake")) {
/*  87 */       ChatSender.addText("Фейкую сообщение &e" + local_command + "&f...");
/*  88 */       String s = "";
/*  89 */       if (local_command.equals("death")) {
/*  90 */         s = "Ахах, " + nick + " упал в бездну, вы видели?";
/*  91 */       } else if (local_command.equals("join")) {
/*  92 */         s = "Всем привет! Победит только одна, сильнейшая команда!";
/*  93 */       } else if (local_command.equals("win")) {
/*  94 */         s = "Ребят, вы знали, что Перезагрузка сервера через 10 секунд!";
/*     */       } else {
/*  96 */         ChatSender.addText("&cЭто сообщение нельзя сфейковать!\n&cМожно только &bdeath&7, &bjoin&7, &bwin");
/*     */       } 
/*  98 */       if (s.length() == 0)
/*  99 */         return;  MyChatListener.addBedwarsMeowMessageToQuee(s, false);
/*     */       
/*     */       return;
/*     */     } 
/* 103 */     String msg = "";
/* 104 */     for (int i = start_idx; i < args.length; i++) {
/* 105 */       msg = msg + args[i] + " ";
/*     */     }
/* 107 */     msg = msg.trim();
/*     */     
/* 109 */     String[] commands = { "setkillmsg", "setdeathmsg", "setbedmsg", "setjoinmsg", "setwinmsg" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 117 */     for (String c : cmds) {
/* 118 */       if (type.equals(c)) commands = new String[] { "set" + c + "msg" };
/*     */     
/*     */     } 
/*     */     
/* 122 */     boolean isOnServer = false;
/* 123 */     Collection<NetworkPlayerInfo> players = Minecraft.func_71410_x().func_147114_u().func_175106_d();
/* 124 */     int cnt = 0;
/* 125 */     for (NetworkPlayerInfo info : players) {
/* 126 */       if (info == null || info.func_178845_a() == null)
/* 127 */         continue;  String name = info.func_178845_a().getName();
/* 128 */       if (name != null && 
/* 129 */         name.equals(nick)) {
/* 130 */         isOnServer = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 134 */     if (!isOnServer) {
/* 135 */       ChatSender.addText("&cИгрок &c\"&e" + nick + "&c\" &c не найден!");
/*     */       
/*     */       return;
/*     */     } 
/* 139 */     for (String cmd : commands) {
/* 140 */       String s = nick + " ." + cmd + " " + msg;
/* 141 */       MyChatListener.addBedwarsMeowMessageToQuee(s, false);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\commands\CommandDexlandMeowSpoof.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */