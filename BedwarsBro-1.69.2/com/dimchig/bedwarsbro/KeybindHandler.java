/*     */ package com.dimchig.bedwarsbro;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.emotes.EmojiGui;
/*     */ import com.dimchig.bedwarsbro.emotes.EmotesDrawer;
/*     */ import com.dimchig.bedwarsbro.gui.GuiMinimap;
/*     */ import com.dimchig.bedwarsbro.gui.GuiPlayerFocus;
/*     */ import com.dimchig.bedwarsbro.gui.ManipulatorGui;
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
/*     */ import org.lwjgl.input.Keyboard;
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
/*  93 */   public static String filename = "BedwarsBro_Keybindings_1.69.2.txt";
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
/* 248 */     this.flagPlaceBlockUnderPlayer = false;
/*     */     
/* 250 */     this.mybeds = new ArrayList<GuiMinimap.Pos>();
/*     */     mc = Minecraft.func_71410_x();
/*     */     readKeys(); } @SubscribeEvent
/*     */   public void onKeyInput(InputEvent.KeyInputEvent e) throws Exception {
/* 254 */     if ((Minecraft.func_71410_x()).field_71439_g == null)
/*     */       return; 
/* 256 */     if (this.keyHintsBedScanner.func_151468_f())
/*     */     {
/* 258 */       if (HintsValidator.isBedScannerActive()) HintsBedScanner.scanBed();
/*     */     
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 268 */     if (this.keyHintsFinder.func_151468_f() && 
/* 269 */       HintsValidator.isFinderActive()) {
/* 270 */       ChatSender.addText(MyChatListener.PREFIX_HINT_FINDER + "&fПоиск игроков...");
/* 271 */       HintsFinder.findAll(true);
/*     */     } 
/*     */ 
/*     */     
/* 275 */     if (this.keyBridgeautoAngle.func_151468_f() && 
/* 276 */       HintsValidator.isPasswordCorrect()) BridgeAutoAngle.aim();
/*     */ 
/*     */     
/* 279 */     if (this.keyPlayerFocus.func_151468_f() && this.keyPlayerFocus.func_151463_i() == 17 && 
/* 280 */       HintsValidator.isPasswordCorrect()) {
/* 281 */       GuiPlayerFocus.STATE = !GuiPlayerFocus.STATE;
/* 282 */       this.keyPlayerFocus.func_151462_b(0);
/* 283 */       (Minecraft.func_71410_x()).field_71474_y.field_74351_w.func_151462_b(17);
/* 284 */       (Minecraft.func_71410_x()).field_71474_y.func_74303_b();
/*     */     } 
/*     */ 
/*     */     
/* 288 */     if (this.keyTNTJump.func_151468_f() && 
/* 289 */       HintsValidator.isPasswordCorrect()) {
/* 290 */       ChatSender.addText(MyChatListener.PREFIX_TNT_JUMP + "&6Не трогай клавиатуру и мышку...");
/* 291 */       TNTJump.lookAtNearestTNT();
/*     */     } 
/*     */ 
/*     */     
/* 295 */     if (this.keyCommandLeave.func_151468_f() && 
/* 296 */       HintsValidator.isPasswordCorrect()) ChatSender.sendText("/leave");
/*     */ 
/*     */     
/* 299 */     if (this.keyCommandRejoin.func_151468_f() && 
/* 300 */       HintsValidator.isPasswordCorrect()) ChatSender.sendText("/rejoin");
/*     */ 
/*     */     
/* 303 */     if (this.keyCommandPartyWarp.func_151468_f() && 
/* 304 */       HintsValidator.isPasswordCorrect()) ChatSender.sendText("/party warp");
/*     */ 
/*     */     
/* 307 */     if (this.keySuperJump.func_151468_f() && 
/* 308 */       HintsValidator.isPasswordCorrect()) mc.field_71439_g.func_70016_h(0.0D, 2.3D, 0.0D);
/*     */ 
/*     */     
/* 311 */     if (keyEmoteWheel.func_151468_f() && 
/* 312 */       HintsValidator.isPasswordCorrect()) mc.func_147108_a((GuiScreen)new EmojiGui());
/*     */ 
/*     */     
/* 315 */     if (HintsValidator.isPasswordCorrect()) {
/* 316 */       for (int i = 0; i < this.keyEmotes.length; i++) {
/* 317 */         KeyBinding keyEmote = this.keyEmotes[i];
/* 318 */         if (keyEmote.func_151468_f()) {
/* 319 */           Main.emotesDrawer.sendEmote(EmotesDrawer.EMOTE_TYPE.values()[i]);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 324 */     if (Keyboard.isKeyDown(25) && Main.commandManipulate.isOk()) {
/* 325 */       OnMyTickEvent.gui2open = (GuiScreen)new ManipulatorGui();
/*     */     }
/*     */     
/* 328 */     if (this.keyPlaceBlockUnderPlayer.func_151468_f()) {
/* 329 */       if (!this.flagPlaceBlockUnderPlayer) {
/* 330 */         this.flagPlaceBlockUnderPlayer = true;
/* 331 */         LobbyBlockPlacer.state = !LobbyBlockPlacer.state;
/*     */       } 
/* 333 */     } else if (this.flagPlaceBlockUnderPlayer == true) {
/* 334 */       this.flagPlaceBlockUnderPlayer = false;
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


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\KeybindHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */