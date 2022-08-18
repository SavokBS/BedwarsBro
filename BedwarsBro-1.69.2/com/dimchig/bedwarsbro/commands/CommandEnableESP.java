/*    */ package com.dimchig.bedwarsbro.commands;
/*    */ 
/*    */ import com.dimchig.bedwarsbro.ChatSender;
/*    */ import com.dimchig.bedwarsbro.gui.GuiPlayerFocus;
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
/*    */ 
/*    */ public class CommandEnableESP
/*    */   extends CommandBase
/*    */ {
/* 29 */   public static String command_text = "bwesp";
/*    */   
/*    */   public CommandEnableESP() {
/* 32 */     this; this; command_text = command_text.replace("/", "");
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
/* 47 */     return "Toggle bwesp";
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/* 52 */     GuiPlayerFocus.STATE = !GuiPlayerFocus.STATE;
/* 53 */     GuiPlayerFocus.isT_Active = false;
/* 54 */     ChatSender.addText((GuiPlayerFocus.STATE ? "&a" : "&c") + "ESP " + (GuiPlayerFocus.STATE ? "включен" : "выключен"));
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\commands\CommandEnableESP.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */