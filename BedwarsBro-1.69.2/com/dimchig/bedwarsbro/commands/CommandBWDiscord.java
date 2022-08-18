/*    */ package com.dimchig.bedwarsbro.commands;
/*    */ 
/*    */ import com.dimchig.bedwarsbro.ChatSender;
/*    */ import com.dimchig.bedwarsbro.Main;
/*    */ import com.dimchig.bedwarsbro.OnMyTickEvent;
/*    */ import com.dimchig.bedwarsbro.gui.GuiPlayer;
/*    */ import net.minecraft.client.gui.GuiScreen;
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
/*    */ 
/*    */ public class CommandBWDiscord
/*    */   extends CommandBase
/*    */ {
/* 31 */   public static String command_text = "bwdiscord";
/*    */   
/*    */   public CommandBWDiscord() {
/* 34 */     this; this; command_text = command_text.replace("/", "");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_71519_b(ICommandSender sender) {
/* 39 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71517_b() {
/* 44 */     this; return command_text;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71518_a(ICommandSender sender) {
/* 49 */     return "Discord";
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/* 54 */     ChatSender.addText("&8<=====================================>\n");
/* 55 */     ChatSender.addLinkAndHoverText("        &9Discord &fсервер BedwarsBro - &b&l&nЖМИ&r &b↗\n", "&fНажми, чтоб присоединится к серверу", "" + 
/*    */         
/* 57 */         Main.getPropDiscordLink());
/*    */ 
/*    */     
/* 60 */     if (Main.commandManipulate.isOk()) {
/* 61 */       ChatSender.addClickText("&8<=====================================>", "/bwdiscord link");
/* 62 */       if (args.length > 0 && args[0].equals("link")) {
/* 63 */         OnMyTickEvent.gui2open = (GuiScreen)new GuiPlayer();
/*    */       }
/*    */       return;
/*    */     } 
/* 67 */     ChatSender.addText("&8<=====================================>");
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\commands\CommandBWDiscord.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */