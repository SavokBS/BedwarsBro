/*    */ package com.dimchig.bedwarsbro.commands;
/*    */ 
/*    */ import com.dimchig.bedwarsbro.ChatSender;
/*    */ import com.dimchig.bedwarsbro.Main;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.command.CommandBase;
/*    */ import net.minecraft.command.CommandException;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandModHelp
/*    */   extends CommandBase
/*    */ {
/* 27 */   public static String command_text = "/";
/*    */   Main main_instance;
/*    */   
/*    */   public CommandModHelp(Main main, String command) {
/* 31 */     this.main_instance = main;
/* 32 */     this; command_text = command.replace("/", "");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_71519_b(ICommandSender sender) {
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71517_b() {
/* 42 */     this; return command_text;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71518_a(ICommandSender sender) {
/* 47 */     return "Help mod";
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/* 52 */     String str = "";
/* 53 */     str = str + "&8<===============================================>\n";
/* 54 */     str = str + "                            &cBedwars&fBro &7v&a1.69\n\n";
/* 55 */     str = str + "                 &fВсе главные настройки находятся в &bконфиге\n";
/* 56 */     str = str + "                         &fТы можешь найти его тут\n";
/* 57 */     str = str + "   &eESC &7→ &eMod Options (Настройки Модов) &7→ &eBedwars Bro &7→ &b&lConfig\n\n";
/* 58 */     str = str + "                               &fНастройки клавиш\n";
/* 59 */     str = str + "                       &eESC &7→ &eНастройки &7→ &bУправление\n";
/*    */     
/* 61 */     if (Minecraft.func_71410_x() != null && (Minecraft.func_71410_x()).field_71439_g != null && Main.isPropUserBanned((Minecraft.func_71410_x()).field_71439_g.func_70005_c_())) {
/* 62 */       str = str + "               &c&l&k=&c&l Ты забанен администратором мода! &c&l&k=\n";
/*    */     }
/* 64 */     ChatSender.addText(str);
/* 65 */     ChatSender.addLinkAndHoverText("                        &fОбзор мода на &cютубе &f- &b&l&nЖМИ&r &b↗\n", "&fНажми, чтоб открыть &cролик", "" + Main.getPropModUpdateLink());
/* 66 */     ChatSender.addLinkAndHoverText("                 &9Discord &fсервер BedwarsBro - &b&l&nЖМИ&r &b↗\n", "&fНажми, чтоб присоединится к серверу", "" + Main.getPropDiscordLink());
/* 67 */     ChatSender.addText("&8<===============================================>");
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\commands\CommandModHelp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */