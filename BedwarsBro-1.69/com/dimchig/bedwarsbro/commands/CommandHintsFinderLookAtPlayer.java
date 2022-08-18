/*    */ package com.dimchig.bedwarsbro.commands;
/*    */ 
/*    */ import com.dimchig.bedwarsbro.ChatSender;
/*    */ import com.dimchig.bedwarsbro.hints.HintsFinder;
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
/*    */ public class CommandHintsFinderLookAtPlayer
/*    */   extends CommandBase
/*    */ {
/* 21 */   public static String command_text = "/bedwarsChatModLookAtPlayer";
/*    */   
/*    */   public CommandHintsFinderLookAtPlayer() {
/* 24 */     this; command_text = command_text.replace("/", "");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_71519_b(ICommandSender sender) {
/* 29 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71517_b() {
/* 34 */     this; return command_text;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71518_a(ICommandSender sender) {
/* 39 */     return "Makes message rainbow";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/* 45 */     if (args.length != 4)
/*    */       return;  try {
/* 47 */       double posX = Double.parseDouble(args[0]);
/* 48 */       double posY = Double.parseDouble(args[1]);
/* 49 */       double posZ = Double.parseDouble(args[2]);
/* 50 */       String name = args[3];
/* 51 */       HintsFinder.findAndlookAtPlayer(posX, posY, posZ, name);
/* 52 */     } catch (Exception ex) {
/* 53 */       ChatSender.addText("&cInvalid arguments!");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\commands\CommandHintsFinderLookAtPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */