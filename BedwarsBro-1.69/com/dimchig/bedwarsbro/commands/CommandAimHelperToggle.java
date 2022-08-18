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
/*    */ 
/*    */ public class CommandAimHelperToggle
/*    */   extends CommandBase
/*    */ {
/* 28 */   public static String command_text = "bwaimhelper";
/*    */   
/*    */   public CommandAimHelperToggle() {
/* 31 */     this; this; command_text = command_text.replace("/", "");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_71519_b(ICommandSender sender) {
/* 36 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71517_b() {
/* 41 */     this; return command_text;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71518_a(ICommandSender sender) {
/* 46 */     return "Toggle aim helper";
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/* 51 */     if (args.length == 0) {
/* 52 */       this; ChatSender.addText("/" + command_text + " &etoggle &7включить/выключить");
/* 53 */     } else if (args[0].equals("toggle")) {
/* 54 */       Main.aimHelper.toggle();
/*    */       return;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\commands\CommandAimHelperToggle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */