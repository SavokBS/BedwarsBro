/*     */ package com.dimchig.bedwarsbro;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.emotes.EmojiGui;
/*     */ import com.dimchig.bedwarsbro.emotes.EmotesDrawer;
/*     */ import com.dimchig.bedwarsbro.gui.GuiMinimap;
/*     */ import com.dimchig.bedwarsbro.gui.GuiPlayerFocus;
/*     */ import com.dimchig.bedwarsbro.hints.BridgeAutoAngle;
/*     */ import com.dimchig.bedwarsbro.hints.HintsBedScanner;
/*     */ import com.dimchig.bedwarsbro.hints.HintsFinder;
/*     */ import com.dimchig.bedwarsbro.hints.HintsValidator;
/*     */ import com.dimchig.bedwarsbro.hints.LobbyBlockPlacer;
/*     */ import com.dimchig.bedwarsbro.hints.TNTJump;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraftforge.client.event.GuiScreenEvent;
/*     */ import net.minecraftforge.fml.client.registry.ClientRegistry;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.gameevent.InputEvent;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KeybindHandler
/*     */ {
/*     */   static Minecraft mc;
/*     */   KeyBinding keyHintsBedScanner;
/*     */   KeyBinding keyHintsFinder;
/*     */   KeyBinding keyBridgeautoAngle;
/*     */   KeyBinding keyTNTJump;
/*     */   KeyBinding keyPlayerFocus;
/*     */   KeyBinding keyCommandLeave;
/*     */   KeyBinding keyCommandRejoin;
/*     */   KeyBinding keyCommandPartyWarp;
/*     */   KeyBinding keyPlaceBlockUnderPlayer;
/*     */   KeyBinding keySuperJump;
/*     */   public static KeyBinding keyEmoteWheel;
/*     */   KeyBinding keyEmote1;
/*     */   KeyBinding keyEmote2;
/*     */   KeyBinding keyEmote3;
/*     */   KeyBinding keyEmote4;
/*     */   KeyBinding keyEmote5;
/*     */   KeyBinding keyEmote6;
/*     */   KeyBinding keyEmote7;
/*     */   KeyBinding keyEmote8;
/*     */   KeyBinding[] keyEmotes;
/*     */   KeyBinding keyMCAttack;
/*     */   KeyBinding keyMCUseItem;
/*  92 */   public static String filename = "BedwarsBro_Keybindings_1.69.txt";
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean flagPlaceBlockUnderPlayer;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ArrayList<GuiMinimap.Pos> mybeds;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public KeybindHandler(Main asInstance) {
/* 247 */     this.flagPlaceBlockUnderPlayer = false;
/*     */     
/* 249 */     this.mybeds = new ArrayList<GuiMinimap.Pos>();
/*     */     mc = Minecraft.func_71410_x();
/*     */     readKeys(); } @SubscribeEvent
/*     */   public void onKeyInput(InputEvent.KeyInputEvent e) throws Exception {
/* 253 */     if ((Minecraft.func_71410_x()).field_71439_g == null)
/*     */       return; 
/* 255 */     if (this.keyHintsBedScanner.func_151468_f())
/*     */     {
/* 257 */       if (HintsValidator.isBedScannerActive()) HintsBedScanner.scanBed();
/*     */     
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 267 */     if (this.keyHintsFinder.func_151468_f() && 
/* 268 */       HintsValidator.isFinderActive()) {
/* 269 */       ChatSender.addText(MyChatListener.PREFIX_HINT_FINDER + "&fПоиск игроков...");
/* 270 */       HintsFinder.findAll(true);
/*     */     } 
/*     */ 
/*     */     
/* 274 */     if (this.keyBridgeautoAngle.func_151468_f() && 
/* 275 */       HintsValidator.isPasswordCorrect()) BridgeAutoAngle.aim();
/*     */ 
/*     */     
/* 278 */     if (this.keyPlayerFocus.func_151468_f() && this.keyPlayerFocus.func_151463_i() == 17 && 
/* 279 */       HintsValidator.isPasswordCorrect()) {
/* 280 */       GuiPlayerFocus.STATE = !GuiPlayerFocus.STATE;
/* 281 */       this.keyPlayerFocus.func_151462_b(0);
/* 282 */       (Minecraft.func_71410_x()).field_71474_y.field_74351_w.func_151462_b(17);
/* 283 */       (Minecraft.func_71410_x()).field_71474_y.func_74303_b();
/*     */     } 
/*     */ 
/*     */     
/* 287 */     if (this.keyTNTJump.func_151468_f() && 
/* 288 */       HintsValidator.isPasswordCorrect()) {
/* 289 */       if (!(Minecraft.func_71410_x()).field_71439_g.func_70005_c_().contains("DimChig")) ChatSender.addText(MyChatListener.PREFIX_TNT_JUMP + "&6Не трогай клавиатуру и мышку..."); 
/* 290 */       TNTJump.lookAtNearestTNT();
/*     */     } 
/*     */ 
/*     */     
/* 294 */     if (this.keyCommandLeave.func_151468_f() && 
/* 295 */       HintsValidator.isPasswordCorrect()) ChatSender.sendText("/leave");
/*     */ 
/*     */     
/* 298 */     if (this.keyCommandRejoin.func_151468_f() && 
/* 299 */       HintsValidator.isPasswordCorrect()) ChatSender.sendText("/rejoin");
/*     */ 
/*     */     
/* 302 */     if (this.keyCommandPartyWarp.func_151468_f() && 
/* 303 */       HintsValidator.isPasswordCorrect()) ChatSender.sendText("/party warp");
/*     */ 
/*     */     
/* 306 */     if (this.keySuperJump.func_151468_f() && 
/* 307 */       HintsValidator.isPasswordCorrect()) mc.field_71439_g.func_70016_h(0.0D, 2.3D, 0.0D);
/*     */ 
/*     */     
/* 310 */     if (keyEmoteWheel.func_151468_f() && 
/* 311 */       HintsValidator.isPasswordCorrect()) mc.func_147108_a((GuiScreen)new EmojiGui());
/*     */ 
/*     */     
/* 314 */     if (HintsValidator.isPasswordCorrect()) {
/* 315 */       for (int i = 0; i < this.keyEmotes.length; i++) {
/* 316 */         KeyBinding keyEmote = this.keyEmotes[i];
/* 317 */         if (keyEmote.func_151468_f()) {
/* 318 */           Main.emotesDrawer.sendEmote(EmotesDrawer.EMOTE_TYPE.values()[i]);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 323 */     if (this.keyPlaceBlockUnderPlayer.func_151468_f()) {
/* 324 */       if (!this.flagPlaceBlockUnderPlayer) {
/* 325 */         this.flagPlaceBlockUnderPlayer = true;
/* 326 */         LobbyBlockPlacer.state = !LobbyBlockPlacer.state;
/*     */       } 
/* 328 */     } else if (this.flagPlaceBlockUnderPlayer == true) {
/* 329 */       this.flagPlaceBlockUnderPlayer = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   void readKeys() {
/*     */     String readFile = FileManager.readFile(filename);
/*     */     if (readFile == null || readFile.length() < 3)
/*     */       initKeys(); 
/*     */     readFile = FileManager.readFile(filename);
/*     */     if (readFile == null || readFile.length() < 3)
/*     */       return; 
/*     */     String category = ColorCodesManager.replaceColorCodesInString(" &c&lBedwars&f&lBro ");
/*     */     String categoryEmotes = ColorCodesManager.replaceColorCodesInString(" &c&lBedwars&f&lBro &7→ &aЭмоции ");
/*     */     try {
/*     */       String[] keys = readFile.split(";");
/*     */       int key1 = Integer.parseInt(keys[0]);
/*     */       int key2 = Integer.parseInt(keys[1]);
/*     */       int key3 = Integer.parseInt(keys[2]);
/*     */       int key4 = Integer.parseInt(keys[3]);
/*     */       int key5 = Integer.parseInt(keys[4]);
/*     */       int key6 = Integer.parseInt(keys[5]);
/*     */       int key7 = Integer.parseInt(keys[6]);
/*     */       int key8 = Integer.parseInt(keys[7]);
/*     */       int key9 = Integer.parseInt(keys[8]);
/*     */       int key10 = Integer.parseInt(keys[9]);
/*     */       int key_e1 = Integer.parseInt(keys[10]);
/*     */       int key_e2 = Integer.parseInt(keys[11]);
/*     */       int key_e3 = Integer.parseInt(keys[12]);
/*     */       int key_e4 = Integer.parseInt(keys[13]);
/*     */       int key_e5 = Integer.parseInt(keys[14]);
/*     */       int key_e6 = Integer.parseInt(keys[15]);
/*     */       int key_e7 = Integer.parseInt(keys[16]);
/*     */       int key_e8 = Integer.parseInt(keys[17]);
/*     */       int k = 57344;
/*     */       this.keyHintsBedScanner = new KeyBinding(ColorCodesManager.replaceColorCodesInString((char)(k + 1) + "&fСканер &cкровати"), key1, category);
/*     */       this.keyHintsFinder = new KeyBinding(ColorCodesManager.replaceColorCodesInString((char)(k + 3) + "&fНайти &eигроков"), key2, category);
/*     */       this.keyCommandLeave = new KeyBinding(ColorCodesManager.replaceColorCodesInString((char)(k + 4) + "&fКоманда &9/leave"), key3, category);
/*     */       this.keyCommandRejoin = new KeyBinding(ColorCodesManager.replaceColorCodesInString((char)(k + 5) + "&fКоманда &d/rejoin"), key4, category);
/*     */       this.keyCommandPartyWarp = new KeyBinding(ColorCodesManager.replaceColorCodesInString((char)(k + 6) + "&fКоманда &c/party warp"), key5, category);
/*     */       this.keyBridgeautoAngle = new KeyBinding(ColorCodesManager.replaceColorCodesInString((char)(k + 7) + "&fУстановить &6угол для GodBridge"), key6, category);
/*     */       this.keyPlaceBlockUnderPlayer = new KeyBinding(ColorCodesManager.replaceColorCodesInString((char)(k + 8) + "&fПрыжки по воздуху в &eлобби &7(Не чит)"), key7, category);
/*     */       this.keySuperJump = new KeyBinding(ColorCodesManager.replaceColorCodesInString((char)(k + 9) + "&fСупер-&fПрыжок в &eлобби"), key8, category);
/*     */       this.keyTNTJump = new KeyBinding(ColorCodesManager.replaceColorCodesInString((char)(k + 10) + "&fАвто &cT&4N&cT &fПрыжок"), key9, category);
/*     */       keyEmoteWheel = new KeyBinding(ColorCodesManager.replaceColorCodesInString((char)(k + 1) + "&fКолесо &aэмоций"), key10, categoryEmotes);
/*     */       this.keyEmote1 = new KeyBinding(ColorCodesManager.replaceColorCodesInString((char)(k + 2) + "&fЭмоция &f\"&cДизлайк&f\""), key_e1, categoryEmotes);
/*     */       this.keyEmote2 = new KeyBinding(ColorCodesManager.replaceColorCodesInString((char)(k + 3) + "&fЭмоция &f\"&dСердечко&f\""), key_e2, categoryEmotes);
/*     */       this.keyEmote3 = new KeyBinding(ColorCodesManager.replaceColorCodesInString((char)(k + 4) + "&fЭмоция &f\"&eСмех&f\""), key_e3, categoryEmotes);
/*     */       this.keyEmote4 = new KeyBinding(ColorCodesManager.replaceColorCodesInString((char)(k + 5) + "&fЭмоция &f\"&cЗлость&f\""), key_e4, categoryEmotes);
/*     */       this.keyEmote5 = new KeyBinding(ColorCodesManager.replaceColorCodesInString((char)(k + 6) + "&fЭмоция &f\"&6Какашка&f\""), key_e5, categoryEmotes);
/*     */       this.keyEmote6 = new KeyBinding(ColorCodesManager.replaceColorCodesInString((char)(k + 7) + "&fЭмоция &f\"&aРвота&f\""), key_e6, categoryEmotes);
/*     */       this.keyEmote7 = new KeyBinding(ColorCodesManager.replaceColorCodesInString((char)(k + 8) + "&fЭмоция &f\"&bЛайк&f\""), key_e7, categoryEmotes);
/*     */       this.keyEmote8 = new KeyBinding(ColorCodesManager.replaceColorCodesInString((char)(k + 9) + "&fЭмоция &f\"&eУдивление&f\""), key_e8, categoryEmotes);
/*     */       this.keyEmotes = new KeyBinding[] { this.keyEmote1, this.keyEmote2, this.keyEmote3, this.keyEmote4, this.keyEmote5, this.keyEmote6, this.keyEmote7, this.keyEmote8 };
/*     */       ClientRegistry.registerKeyBinding(this.keyHintsBedScanner);
/*     */       ClientRegistry.registerKeyBinding(this.keyHintsFinder);
/*     */       ClientRegistry.registerKeyBinding(this.keyBridgeautoAngle);
/*     */       ClientRegistry.registerKeyBinding(this.keyTNTJump);
/*     */       ClientRegistry.registerKeyBinding(this.keyCommandLeave);
/*     */       ClientRegistry.registerKeyBinding(this.keyCommandRejoin);
/*     */       ClientRegistry.registerKeyBinding(this.keyCommandPartyWarp);
/*     */       ClientRegistry.registerKeyBinding(this.keyPlaceBlockUnderPlayer);
/*     */       ClientRegistry.registerKeyBinding(this.keySuperJump);
/*     */       this;
/*     */       ClientRegistry.registerKeyBinding(keyEmoteWheel);
/*     */       for (KeyBinding keyEmote : this.keyEmotes)
/*     */         ClientRegistry.registerKeyBinding(keyEmote); 
/*     */       this.keyPlayerFocus = mc.field_71474_y.field_152398_ap;
/*     */       this.keyMCAttack = mc.field_71474_y.field_74312_F;
/*     */       this.keyMCUseItem = mc.field_71474_y.field_74313_G;
/*     */       System.out.println("SUCCESFULLY REGISTERED");
/*     */     } catch (Exception ex) {
/*     */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   void initKeys() {
/*     */     String s = "";
/*     */     s = s + "19;";
/*     */     s = s + "82;";
/*     */     s = s + "71;";
/*     */     s = s + "72;";
/*     */     s = s + "73;";
/*     */     s = s + "48;";
/*     */     s = s + "33;";
/*     */     s = s + "38;";
/*     */     s = s + "36;";
/*     */     s = s + "50;";
/*     */     s = s + "0;";
/*     */     s = s + "0;";
/*     */     s = s + "0;";
/*     */     s = s + "0;";
/*     */     s = s + "0;";
/*     */     s = s + "0;";
/*     */     s = s + "0;";
/*     */     s = s + "0;";
/*     */     FileManager.writeToFile(s, filename, false);
/*     */   }
/*     */   
/*     */   void saveKeybindings() {
/*     */     try {
/*     */       String s = "";
/*     */       s = s + this.keyHintsBedScanner.func_151463_i() + ";";
/*     */       s = s + this.keyHintsFinder.func_151463_i() + ";";
/*     */       s = s + this.keyCommandLeave.func_151463_i() + ";";
/*     */       s = s + this.keyCommandRejoin.func_151463_i() + ";";
/*     */       s = s + this.keyCommandPartyWarp.func_151463_i() + ";";
/*     */       s = s + this.keyBridgeautoAngle.func_151463_i() + ";";
/*     */       s = s + this.keyPlaceBlockUnderPlayer.func_151463_i() + ";";
/*     */       s = s + this.keySuperJump.func_151463_i() + ";";
/*     */       s = s + this.keyTNTJump.func_151463_i() + ";";
/*     */       s = s + keyEmoteWheel.func_151463_i() + ";";
/*     */       s = s + this.keyEmote1.func_151463_i() + ";";
/*     */       s = s + this.keyEmote2.func_151463_i() + ";";
/*     */       s = s + this.keyEmote3.func_151463_i() + ";";
/*     */       s = s + this.keyEmote4.func_151463_i() + ";";
/*     */       s = s + this.keyEmote5.func_151463_i() + ";";
/*     */       s = s + this.keyEmote6.func_151463_i() + ";";
/*     */       s = s + this.keyEmote7.func_151463_i() + ";";
/*     */       s = s + this.keyEmote8.func_151463_i() + ";";
/*     */       FileManager.writeToFile(s, filename, false);
/*     */     } catch (Exception exception) {}
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void changeKeybind(GuiScreenEvent.KeyboardInputEvent.Post event) {
/*     */     if (event.gui instanceof net.minecraft.client.gui.GuiControls)
/*     */       saveKeybindings(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\KeybindHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */