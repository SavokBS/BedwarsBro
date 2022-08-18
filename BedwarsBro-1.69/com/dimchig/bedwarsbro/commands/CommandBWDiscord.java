/*    */ package com.dimchig.bedwarsbro.commands;
/*    */ 
/*    */ import com.dimchig.bedwarsbro.ChatSender;
/*    */ import com.dimchig.bedwarsbro.Main;
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
/*    */ 
/*    */ public class CommandBWDiscord
/*    */   extends CommandBase
/*    */ {
/* 27 */   public static String command_text = "bwdiscord";
/*    */   
/*    */   public CommandBWDiscord() {
/* 30 */     this; this; command_text = command_text.replace("/", "");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_71519_b(ICommandSender sender) {
/* 35 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71517_b() {
/* 40 */     this; return command_text;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71518_a(ICommandSender sender) {
/* 45 */     return "Discord";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/* 52 */     ChatSender.addLinkAndHoverText("&8<=====================================>\n\n        &9Discord &fсервер BedwarsBro - &b&l&nЖМИ&r &b↗\n\n&8<=====================================>", "&fНажми, чтоб присоединится к серверу", "" + 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 57 */         Main.getPropDiscordLink());
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\commands\CommandBWDiscord.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */