/*     */ package com.dimchig.bedwarsbro;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.commands.CommandAimHelperToggle;
/*     */ import com.dimchig.bedwarsbro.commands.CommandBWDiscord;
/*     */ import com.dimchig.bedwarsbro.commands.CommandBowAimbotToggle;
/*     */ import com.dimchig.bedwarsbro.commands.CommandDexlandMeowSpoof;
/*     */ import com.dimchig.bedwarsbro.commands.CommandFindPlayerByName;
/*     */ import com.dimchig.bedwarsbro.commands.CommandHintsFinderLookAt;
/*     */ import com.dimchig.bedwarsbro.commands.CommandHintsFinderLookAtPlayer;
/*     */ import com.dimchig.bedwarsbro.commands.CommandHistoryShop;
/*     */ import com.dimchig.bedwarsbro.commands.CommandMeow;
/*     */ import com.dimchig.bedwarsbro.commands.CommandModHelp;
/*     */ import com.dimchig.bedwarsbro.commands.CommandPrefix;
/*     */ import com.dimchig.bedwarsbro.commands.CommandRainbowMessage;
/*     */ import com.dimchig.bedwarsbro.commands.CommandRainbowMessageSetter;
/*     */ import com.dimchig.bedwarsbro.dimchigspecial.CantHitDimChig;
/*     */ import com.dimchig.bedwarsbro.dimchigspecial.CommandManipulate;
/*     */ import com.dimchig.bedwarsbro.dimchigspecial.PlayerManipulator;
/*     */ import com.dimchig.bedwarsbro.emotes.CommandEmote;
/*     */ import com.dimchig.bedwarsbro.emotes.EmojiGui;
/*     */ import com.dimchig.bedwarsbro.emotes.EmotesDrawer;
/*     */ import com.dimchig.bedwarsbro.gui.Draw3DText;
/*     */ import com.dimchig.bedwarsbro.gui.GuiBedESP;
/*     */ import com.dimchig.bedwarsbro.gui.GuiMinimap;
/*     */ import com.dimchig.bedwarsbro.gui.GuiOnScreen;
/*     */ import com.dimchig.bedwarsbro.gui.GuiPlayerFocus;
/*     */ import com.dimchig.bedwarsbro.gui.GuiRadarIcon;
/*     */ import com.dimchig.bedwarsbro.gui.GuiResourceHologram;
/*     */ import com.dimchig.bedwarsbro.hints.BedwarsMeow;
/*     */ import com.dimchig.bedwarsbro.hints.DangerAlert;
/*     */ import com.dimchig.bedwarsbro.hints.GeneratorTimers;
/*     */ import com.dimchig.bedwarsbro.hints.GetHintsPWD;
/*     */ import com.dimchig.bedwarsbro.hints.HintsBaseRadar;
/*     */ import com.dimchig.bedwarsbro.hints.HintsItemTracker;
/*     */ import com.dimchig.bedwarsbro.hints.HintsPlayerScanner;
/*     */ import com.dimchig.bedwarsbro.hints.InvulnerableTime;
/*     */ import com.dimchig.bedwarsbro.hints.NamePlate;
/*     */ import com.dimchig.bedwarsbro.hints.NamePlateRenderer;
/*     */ import com.dimchig.bedwarsbro.hints.RainbowColorSynchronizer;
/*     */ import com.dimchig.bedwarsbro.hints.ShopManager;
/*     */ import com.dimchig.bedwarsbro.hints.TNTJump;
/*     */ import com.dimchig.bedwarsbro.particles.ParticleController;
/*     */ import com.dimchig.bedwarsbro.particles.ParticleTrail;
/*     */ import com.dimchig.bedwarsbro.particles.ParticlesAlwaysSharpness;
/*     */ import com.dimchig.bedwarsbro.serializer.MySerializer;
/*     */ import com.dimchig.nolegit.AimHelper;
/*     */ import com.dimchig.nolegit.BowAimbot;
/*     */ import java.io.File;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import net.minecraft.command.ICommand;
/*     */ import net.minecraftforge.client.ClientCommandHandler;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.common.config.ConfigCategory;
/*     */ import net.minecraftforge.common.config.Configuration;
/*     */ import net.minecraftforge.common.config.Property;
/*     */ import net.minecraftforge.fml.client.event.ConfigChangedEvent;
/*     */ import net.minecraftforge.fml.common.Loader;
/*     */ import net.minecraftforge.fml.common.Mod;
/*     */ import net.minecraftforge.fml.common.Mod.EventHandler;
/*     */ import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
/*     */ import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Mod(modid = "bedwarsbro", name = "BedwarsBro", version = "1.69", clientSideOnly = true, acceptedMinecraftVersions = "[1.8, 1.12.2]", guiFactory = "com.dimchig.bedwarsbro.gui.GuiFactory")
/*     */ public class Main
/*     */ {
/*     */   public static final String MODID = "bedwarsbro";
/*     */   public static final String NAME = "BedwarsBro";
/*     */   public static final String VERSION = "1.69";
/*     */   public static final String MCVERSIONS = "[1.8, 1.12.2]";
/*     */   public static Main instance;
/*     */   private static Configuration config;
/*     */   public static ConfigCategory clientConfig;
/*     */   public static KeybindHandler keybindHandler;
/*     */   public static MyChatListener chatListener;
/*     */   public static FileManager fileManager;
/*     */   public static OnMyTickEvent myTickEvent;
/*     */   public static BaseProps baseProps;
/*     */   public static HintsBaseRadar hintsBaseRadar;
/*     */   public static HintsPlayerScanner hintsPlayerScanner;
/*     */   public static ScoreboardManager scoreboardManager;
/*     */   public static CustomScoreboard customScoreboard;
/*     */   public static GuiOnScreen guiOnScreen;
/*     */   public static HintsItemTracker itemTracker;
/*     */   public static ParticleController particleController;
/*     */   public static ParticleTrail particleTrail;
/*     */   public static GuiMinimap minimap;
/*     */   public static GeneratorTimers generatorTimers;
/*     */   public static GuiPlayerFocus playerFocus;
/*     */   public static TNTJump tntjump;
/*     */   public static ParticlesAlwaysSharpness particlesAlwaysSharpness;
/*     */   public static DangerAlert dangerAlert;
/*     */   public static AutoSprint autoSprint;
/*     */   public static BedwarsMeow bedwarsMeow;
/*     */   public static AutoWaterDrop autoWaterDrop;
/*     */   public static ChatReader chatReader;
/*     */   private Field configChangedEventModIDField;
/*     */   public static CommandHintsFinderLookAtPlayer commandHintsFinderLookAtPlayer;
/*     */   public static CommandHintsFinderLookAt commandHintsFinderLookAt;
/*     */   public static CommandRainbowMessage commandRainbowMessage;
/*     */   public static CommandMeow commandMeow;
/*     */   public static CommandHistoryShop commandHistoryShop;
/*     */   public static CommandFindPlayerByName commandFindPlayerByName;
/*     */   public static CommandRainbowMessageSetter commandRainbowMessageSetter;
/*     */   public static CommandDexlandMeowSpoof commandDexlandMeowSpoof;
/*     */   public static CommandManipulate commandManipulate;
/*     */   public static CommandBowAimbotToggle commandBowAimbotToggle;
/*     */   public static CommandAimHelperToggle commandAimHelperToggle;
/*     */   public static CommandBWDiscord commandBWDiscord;
/*     */   public static CommandPrefix commandPrefix;
/*     */   public static CommandEmote commandEmote;
/*     */   public static MySerializer mySerializer;
/*     */   public static ShopManager shopManager;
/*     */   public static LoginHandler loginHandler;
/*     */   public static InvulnerableTime invulnerableTime;
/*     */   public static NamePlate namePlate;
/*     */   public static Draw3DText draw3DText;
/*     */   public static RainbowColorSynchronizer rainbowColorSynchronizer;
/*     */   public static NamePlateRenderer namePlateRenderer;
/*     */   public static SameModePlayers sameModePlayers;
/*     */   public static GuiResourceHologram guiResourceHologram;
/*     */   public static PlayerManipulator playerManipulator;
/*     */   public static BowAimbot bowAimbot;
/*     */   public static AimHelper aimHelper;
/*     */   public static CantHitDimChig cantHitDimChig;
/*     */   public static GuiBedESP guiBedESP;
/*     */   public static GuiRadarIcon guiRadarIcon;
/*     */   public static EmotesDrawer emotesDrawer;
/*     */   public static EmojiGui emojiGui;
/*     */   private boolean state = false;
/*     */   
/*     */   public Main() {
/* 153 */     this; this; String config_name = "bedwarsbro" + "_" + "1.69" + "_CONFIG.cfg";
/* 154 */     File configFile = new File(Loader.instance().getConfigDir(), config_name);
/*     */ 
/*     */     
/*     */     try {
/* 158 */       this.configChangedEventModIDField = ConfigChangedEvent.class.getDeclaredField("modID");
/* 159 */       this.configChangedEventModIDField.setAccessible(true);
/* 160 */     } catch (NoSuchFieldException e) {
/* 161 */       throw new RuntimeException("Cannot find field", e);
/*     */     } 
/*     */     
/* 164 */     config = new Configuration(configFile);
/* 165 */     initConfig();
/*     */     
/* 167 */     clientConfig = config.getCategory("client");
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean getConfigBool(CONFIG_MSG type) {
/* 172 */     return clientConfig.get(type.text).getBoolean();
/*     */   }
/*     */   public static String getConfigString(CONFIG_MSG type) {
/* 175 */     return clientConfig.get(type.text).getString();
/*     */   }
/*     */   public static int getConfigInt(CONFIG_MSG type) {
/* 178 */     return clientConfig.get(type.text).getInt();
/*     */   }
/*     */   public static double getConfigDouble(CONFIG_MSG type) {
/* 181 */     return clientConfig.get(type.text).getDouble();
/*     */   }
/*     */   
/* 184 */   private static int config_messages_sort_idx_cnt = 1;
/*     */   
/* 186 */   public enum CONFIG_MSG { PASSWORD(1, "&c&lПАРОЛЬ для мода&c&l*"),
/* 187 */     ENABLE_BETTER_CHAT(2, "&eУлучшенный чат"),
/* 188 */     ENABLE_BETTER_CHAT_STATISTIC_PREFIX(47, "&eУлучшенный чат &7→ &fСтатистика в начале сообщения"),
/* 189 */     SCOREBOARD_ENGLISH(55, "&eEnglish scoreboard &7(команды на англ.)"),
/* 190 */     AUTO_SPRINT(3, "Авто &dСпринт &7(Вечный бег)"),
/*     */     
/* 192 */     MINIMAP(4, "&6Миникарта"),
/* 193 */     MINIMAP_X(5, "&6Миникарта &7→ &cX &7положение по горизонтали <-->&c*"),
/* 194 */     MINIMAP_Y(6, "&6Миникарта &7→ &bY &7положение по вертикали ||"),
/* 195 */     MINIMAP_SIZE(7, "&6Миникарта &7→ &fРазмер"),
/* 196 */     MINIMAP_SHOW_HEIGHT(8, "&6Миникарта &7→ &fПоказывать разницы в высоте"),
/* 197 */     MINIMAP_HIDE_PLAYERS_ON_SHIFT(49, "&6Миникарта &7→ &fСкрыть игроков на шифте&c*"),
/*     */     
/* 199 */     RADAR(9, "&aРадар"),
/* 200 */     RADAR_PLAYERS(60, "&aРадар &7→ &fРадар игроков&c*"),
/* 201 */     RADAR_ICON(57, "&aРадар &7→ &fПоказывать иконку&c*"),
/* 202 */     RADAR_MESSAGES(10, "&aРадар &7→ &fОтправлять сообщения тиммейтам&c*"),
/* 203 */     RADAR_MESSAGES_WITH_COLORS(11, "&aРадар &7→ &fОтправлять сообщения тиммейтам с &cц&eв&aе&bт&9а&dм&cи &7(только для &fLEGEND&7)"),
/*     */     
/* 205 */     BED_ESP(56, "&bПодсветка кроватей &7(bed ESP)"),
/* 206 */     BED_SCANNER(12, "&bСканер кровати&c*"),
/* 207 */     BED_SCANNER_ANIMATION(13, "&bСканер кровати &7→ &fАнимация по слоям&c*"),
/* 208 */     BED_SCANNER_ANIMATION_DELAY(14, "&bСканер кровати &7→ &fСкорость анимации (миллисекунды)"),
/*     */ 
/*     */     
/* 211 */     PLAYER_FINDER(15, "&9Поиск игроков&c*"),
/*     */     
/* 213 */     ITEM_COUNTER(17, "&fКоличество &aизумрудов &fи &bалмазов &fв углу экрана"),
/*     */     
/* 215 */     BEDWARS_MEOW(18, "&eMeow &7→ &fАвто-Сообщения после &cкила&f, &eсмерти&f, &aкровати &fи &dвыйграша&c*"),
/* 216 */     BEDWARS_MEOW_WITH_COLORS(19, "&eMeow &7→ &fИспользовать цвета &7(только для &fLEGEND&7)"),
/* 217 */     BEDWARS_MEOW_WITH_COLORS_RAINBOW(20, "&eMeow &7→ &fИспользовать &cр&eа&aд&bу&9ж&dн&cы&eй &fрежим &7(только для &fLEGEND&7)"),
/*     */     
/* 219 */     POTION_TRACKER(21, "&aЭффекты &fот зелий &7→ &fВизуализация&c*"),
/* 220 */     POTION_TRACKER_SOUNDS(22, "&aЭффекты &fот зелий &7→ &fЗвуки&c*"),
/*     */     
/* 222 */     DANGER_ALERT(23, "Предупреждение про &6Фаербол &fи &cЛук&c*"),
/* 223 */     DANGER_ALERT_SOUND(24, "Предупреждение про &6Фаербол &fи &cЛук &7→ &fЗвуки"),
/*     */     
/* 225 */     GENERATOR_TIMERS(50, "&6Таймера для генераторов"),
/* 226 */     GENERATOR_TIMERS_ADVANCED(51, "&6Таймера для генераторов &7→ &fПродвинутая информация&c*"),
/* 227 */     GENERATOR_TIMERS_POSITION(52, "&6Таймера для генераторов &7→ &fПозиция таймера&c*"),
/* 228 */     GENERATOR_TIMERS_TIMELINE(53, "&6Таймера для генераторов &7→ &fТаймлайн игры&c*"),
/* 229 */     GENERATOR_TIMERS_TIMELINE_WIDTH(54, "&6Таймера для генераторов &7→ &fТаймлайн игры &7→ &fШирина в &eпроцентах&c*"),
/*     */     
/* 231 */     BETTER_SHOP(25, "&eМагазин &7→ &fУлучшенная версия&c&l*"),
/* 232 */     REMOVE_BUY_MESSAGE(26, "&eМагазин &7→ &fУбрать сообщение при покупке"),
/*     */     
/* 234 */     NAMEPLATE(27, "&fНик от 3-его лица"),
/* 235 */     NAMEPLATE_RAINBOW(28, "&fНик от 3-его лица &7→ &f&cР&eа&aд&bу&9ж&dн&cы&eй &fник"),
/* 236 */     NAMEPLATE_RAINBOW_CONSTANT_COLOR(29, "&fНик от 3-его лица &7→ &fПостоянный цвет &7(в формате hex)&c&l*"),
/*     */     
/* 238 */     CUSTOM_PARTICLES(30, "&fРазноцветные &cп&eа&aр&bт&9и&dк&cл&eы&c*"),
/*     */     
/* 240 */     PARTICLE_TRAIL(31, "&fСлед из &cп&eа&aр&bт&9и&dк&cл&eо&aв &7(во время бега)"),
/* 241 */     PARTICLE_TRAIL_RAINBOW(32, "&fСлед из &cп&eа&aр&bт&9и&dк&cл&eо&aв &7→ &f&cР&eа&aд&bу&9ж&dн&cы&eе &fпартиклы"),
/*     */     
/* 243 */     RAINBOW_SPEED(33, "&fСкорость переливания &cр&eа&aд&bу&9г&dи &f"),
/*     */ 
/*     */     
/* 246 */     RAINBOW_MESSAGE_COMMAND(34, "Разноцветные &cс&eо&aо&bб&9щ&dе&cн&eи&aя &7→ &fКоманда в чате &7(только для &fLEGEND&7)&c*"),
/* 247 */     RAINBOW_MESSAGE_MODE(35, "Разноцветные &cс&eо&aо&bб&9щ&dе&cн&eи&aя &7→ &fРежим&c*"),
/* 248 */     RAINBOW_MESSAGE_GLOBAL(36, "Разноцветные &cс&eо&aо&bб&9щ&dе&cн&eи&aя &7→ &fПисать в глобальный чат"),
/* 249 */     RAINBOW_MESSAGE_COLORS(37, "Разноцветные &cс&eо&aо&bб&9щ&dе&cн&eи&aя &7→ &fЦветовая палитра&c*"),
/* 250 */     RAINBOW_MESSAGE_REPLACE_CHARS(38, "Разноцветные &cс&eо&aо&bб&9щ&dе&cн&eи&aя &7→ &fЗаменить символ"),
/*     */     
/* 252 */     WIN_EMOTE(39, "&cВыйграш &7→ &fзаменить мир другими блоками&c&l*"),
/*     */     
/* 254 */     INVULNERABLE_TIMER(40, "&eВремя неуязвимости игроков&c&l* &7(после спавна)"),
/* 255 */     INVULNERABLE_TIMER_SOUNDS(41, "&eВремя неуязвимости игроков &7→ &fЗвуки"),
/*     */     
/* 257 */     MUTED_PLAYERS(42, "&fЗамутить игроков в лобби&c&l*"),
/*     */     
/* 259 */     BRIDGE_AUTOANGLE_PITCH(43, "&aАвтоУгол для GodBridge &7→ &fНаклон по вертикали &7(45°, &a76°&7)&c*"),
/* 260 */     BRIDGE_AUTOANGLE_MESSAGES(44, "&aАвтоУгол для GodBridge &7→ &fВыводить в чат сообщения"),
/*     */     
/* 262 */     AUTO_WATER_DROP(45, "&bАвто WaterDrop &7(Мод поставит ведро воды под тебя)"),
/* 263 */     RESOURCES_HOLOGRAM(46, "&fГолограмма над ресурсами"),
/*     */     
/* 265 */     MAP_AUTO_SELECTER(48, "&fЛюбимые карты &7(только для &fLEGEND&7)"),
/* 266 */     EMOTIONS_REMOVE_SOUND(58, "&aЭмоции &7→ &fВыключить звук"),
/* 267 */     BRO_PREFIX(59, "&fПрефикс для всех &c(Менять только /bwprefix)");
/*     */     
/*     */     public final int id;
/*     */     
/*     */     public final int sort_idx;
/*     */     
/*     */     public final String text;
/*     */     
/*     */     CONFIG_MSG(int id, String text) {
/* 276 */       this.id = id;
/*     */       
/* 278 */       this.sort_idx = Main.config_messages_sort_idx_cnt;
/*     */       
/*     */       Main.config_messages_sort_idx_cnt++;
/* 281 */       int char_code = 57344 + this.sort_idx;
/* 282 */       String t = "&0" + (char)char_code + "&f" + text;
/* 283 */       this.text = ColorCodesManager.replaceColorCodesInString(t);
/*     */     }
/*     */     
/*     */     public String getName() {
/* 287 */       String s = this.text.substring(3);
/* 288 */       if (s.charAt(s.length() - 1) == '*') s = s.substring(0, s.length() - 3); 
/* 289 */       return s;
/*     */     } }
/*     */ 
/*     */   
/*     */   public static String getConfigSettings() {
/* 294 */     String text = "";
/* 295 */     for (CONFIG_MSG m : CONFIG_MSG.values()) {
/* 296 */       if (clientConfig.get(m.text) != null)
/* 297 */         text = text + m.id + "=;=;=" + clientConfig.get(m.text).getString() + "==;===;=="; 
/*     */     } 
/* 299 */     return text;
/*     */   }
/*     */ 
/*     */   
/*     */   private void initConfig() {
/* 304 */     if (CONFIG_MSG.values() == null || (CONFIG_MSG.values()).length == 0)
/* 305 */       return;  for (CONFIG_MSG m1 : CONFIG_MSG.values()) {
/* 306 */       for (CONFIG_MSG m2 : CONFIG_MSG.values()) {
/* 307 */         if (m1.id == m2.id && !m1.text.equals(m2.text)) {
/* 308 */           for (int i = 0; i < 100; i++) {
/* 309 */             System.out.println("SAME ID " + m1.id + "!!!");
/*     */           }
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 316 */     config.load();
/*     */ 
/*     */     
/* 319 */     Property prop = config.get("client", CONFIG_MSG.ENABLE_BETTER_CHAT.text, true, ColorCodesManager.replaceColorCodesInString("&f&aВключить&7/&cВыключить &fизменение чата"));
/* 320 */     prop = config.get("client", CONFIG_MSG.ENABLE_BETTER_CHAT_STATISTIC_PREFIX.text, false, ColorCodesManager.replaceColorCodesInString("&f&aВключить&7/&cВыключить &fпоказ статистики игрока\n\"&7[&aкол-во килов&7, &aK/D&7, &aWinRate&7]&f\""));
/* 321 */     prop = config.get("client", CONFIG_MSG.SCOREBOARD_ENGLISH.text, false, ColorCodesManager.replaceColorCodesInString("&f&aВключить&7/&cВыключить &fпоказ команд и текста на английском языке (как на Hypixel)\n&bНачни новую игру после включения!"));
/* 322 */     prop = config.get("client", CONFIG_MSG.MUTED_PLAYERS.text, "Arab_yxz361,", ColorCodesManager.replaceColorCodesInString("&fРазделяй ники спамеров &aзапятыми&f, вот так:\n\n&fArab_yxz361,NikitaBoy228,killer1234"));
/* 323 */     prop = config.get("client", CONFIG_MSG.PASSWORD.text, "", ColorCodesManager.replaceColorCodesInString("&fПароль для мода, без него &cпочти ничего не будет работь"));
/* 324 */     prop = config.get("client", CONFIG_MSG.BED_SCANNER.text, true, ColorCodesManager.replaceColorCodesInString("&fСканер кровати!\n&fПри нажатии на кнопку &7(смотри в настройках -> управление) &fсмотри на базу противников (кровать найдеться в радиусе 20 блоков). В чате напишет чем застроена кровать, например есть ли у них обса"));
/* 325 */     prop = config.get("client", CONFIG_MSG.BED_SCANNER_ANIMATION.text, true, ColorCodesManager.replaceColorCodesInString("&fЕсли вкючить это, то при нажатии на кнопку сканера кровати, у тебя кровать будет показываться по слоям! Это экспериментальная опция, если находиться близко к кровати у тебя может лагать и ты застрянешь в блоках. Использовать можно чтоб увидеть с какой стороны лучше начать ломать"));
/* 326 */     prop = config.get("client", CONFIG_MSG.BED_SCANNER_ANIMATION_DELAY.text, 200, ColorCodesManager.replaceColorCodesInString("&fЭто задержка для анимации. То-есть каждый слой будет показываться по &e300 &fмиллисекунд, тоесть &e0.3 &fсекунды. Можешь поменять, но не ставь больше &e1000&f."));
/* 327 */     prop = config.get("client", CONFIG_MSG.BED_ESP.text, true, ColorCodesManager.replaceColorCodesInString("&fЭто типо xray для кроватей (Bed ESP), будет видно их сквозь блоки, очень удобно когда большая застройка"));
/* 328 */     prop = config.get("client", CONFIG_MSG.RADAR.text, true, ColorCodesManager.replaceColorCodesInString("&fЕсли враг подойдет близко к твоей базе, тебе прийдет сообщение со звуком типо:\n\"&eНас рашит &aЗеленый&f\"\nК сожалению, если &cдалеко уйти от базы&f, то работать &cне будет&f!"));
/* 329 */     prop = config.get("client", CONFIG_MSG.RADAR_PLAYERS.text, false, ColorCodesManager.replaceColorCodesInString("&fЕсли &c&lкровать сломана&f, и враг подойдет близко к тебе, тебе прийдет сообщение со звуком типо:\n\"&eРядом &aЗеленый&f\"!"));
/* 330 */     prop = config.get("client", CONFIG_MSG.RADAR_ICON.text, true, ColorCodesManager.replaceColorCodesInString("&fЕсли враг подойдет близко к твоей базе, будт иконка кровати на 1 секунду поцентру сверху, чтоб ты заметил. Точно также будет картинка игрока, если радар игроков включен"));
/* 331 */     prop = config.get("client", CONFIG_MSG.RADAR_MESSAGES.text, false, ColorCodesManager.replaceColorCodesInString("&fСообщение про радар приходит только тебе. Если ты хочешь поделиться с тиммейтами, включи это, и тогда ты напишешь в чат это же сообщение уже своим тиммейтам."));
/* 332 */     prop = config.get("client", CONFIG_MSG.RADAR_MESSAGES_WITH_COLORS.text, false, ColorCodesManager.replaceColorCodesInString("&fТолько для донатеров!\nТут тоже самое, но сообщение тиммейтам будет приходить цветное. Ставь &atrue &fтолько если ты можешь писать всеми цветами (как &bLEGEND&f)"));
/* 333 */     prop = config.get("client", CONFIG_MSG.ITEM_COUNTER.text, true, ColorCodesManager.replaceColorCodesInString("&fПоказывает количество &aизумрудов&f, и &bалмазов &fв правом нижнем углу. Очень удобно"));
/* 334 */     prop = config.get("client", CONFIG_MSG.PLAYER_FINDER.text, true, ColorCodesManager.replaceColorCodesInString("&fПоиск игроков. При нажатии на кнопку &7(смотри в настройках -> управление) &fмод попытается найти всех игроков в зоне видимости, это приблизительно &e60 блоков&f. В чате будет сообщение с ником игрока. Если &aнавести мышкой &fна сообщение, то ты увидешь:\n\n\"&7(&fx&7, &fy&7, &fz&7, &c[дистанция до игрока]&7) &fтип брони и &fпредмет в руке\"\n\n&fКрасная звездочка&c*&f, или &c**&f, означают что у игрока либо алмазка, либо лук, перл или силка. Это как сложность игрока типо\n\n&fЕсли &aНАЖАТЬ &fна сообщение, то твоя голова повернется в сторону этого игрока"));
/*     */     
/* 336 */     prop = config.get("client", CONFIG_MSG.WIN_EMOTE.text, true, ColorCodesManager.replaceColorCodesInString("&fАнимация при выйграше. В конце катки мир вокруг тебя изменится на блоки цвета команды. Если выйграли зеленые, то мир измениться на изумрудные блоки, шерсть и тд =)"));
/* 337 */     prop = config.get("client", CONFIG_MSG.MINIMAP.text, true, ColorCodesManager.replaceColorCodesInString("&fМиникарта, показывает игроков в виде разноцветных стрелок.\n&c&lЖрет около 40FPS (у меня без карты 200, с ней 160). Включайте на свое усмотрение.\n&fВесь мир - серый, так удобнее. Так же показывает числами количество ресурсов. Не показывает меньше &b3-х алмазов&f, если на базе, то < &e12 золота&f и < &f64 железа."));
/* 338 */     prop = config.get("client", CONFIG_MSG.MINIMAP_X.text, "0", ColorCodesManager.replaceColorCodesInString("&fПозиция миникарты (X это <--->, Y это по вертикали)\n &fЕсли &cX &eположительный &7(не меньше 0)&f, то это будет отступ &aслева&f\n &fЕсли &cX &eотрицательный &7(меньше 0)&f, будет отступ &aсправа\n &fЕсли &bY &eположительный &7(не меньше 0)&f, то это будет отступ &aсверху&f\n &fЕсли &bY &eотрицательный &7(меньше 0)&f, будет отступ &aснизу\n\nВот примеры настроек:\n &fЛевый верхний угол:  &cX = 0&7,&bY = 0\n &fПравый верхний угол: &cX = -0&7, &bY = 0\n &fЛевый нижний угол:   &cX = 0&7, &bY = -0\n &fПравый нижний угол:  &cX = -0&7, &bY = -0\n\n &fМиникарта с отступом &c5 &fот левого края, и &b15 &fсвеху&f: &cX = -5&7, &bY = 15"));
/* 339 */     prop = config.get("client", CONFIG_MSG.MINIMAP_Y.text, "0", ColorCodesManager.replaceColorCodesInString("&fПрочитай в \"" + CONFIG_MSG.MINIMAP_X.getName() + "\""));
/* 340 */     prop = config.get("client", CONFIG_MSG.MINIMAP_SIZE.text, 100, ColorCodesManager.replaceColorCodesInString("&fРазмер миникарты &7(от &e50 &7до &e150&7)"));
/* 341 */     prop = config.get("client", CONFIG_MSG.MINIMAP_SHOW_HEIGHT.text, true, ColorCodesManager.replaceColorCodesInString("&fНа карте будут отображаться числа под игроками - это разнице по высоте с тобой (только если ты ниже их)"));
/* 342 */     prop = config.get("client", CONFIG_MSG.MINIMAP_HIDE_PLAYERS_ON_SHIFT.text, false, ColorCodesManager.replaceColorCodesInString("&fТипо это будет &aлегитная &fминикарта, но блин, кому она нужна)"));
/*     */     
/* 344 */     prop = config.get("client", CONFIG_MSG.CUSTOM_PARTICLES.text, false, ColorCodesManager.replaceColorCodesInString("&fПри каждом ударе будет разноцветные партиклы, если враг &aзеленый &f- то &aзеленого &fцвета. Не работает с модом Particle Customizer"));
/* 345 */     prop = config.get("client", CONFIG_MSG.BRIDGE_AUTOANGLE_PITCH.text, 76.0D, ColorCodesManager.replaceColorCodesInString("&fПри нажатии на кнопку &7(смотри в настройках -> управление) &fсмотри на край блока. Твой курсор развернеться в &b(45°, 76°)&f. Вот эти 76° можно поменять. Кому как удобнее для godbridge."));
/* 346 */     prop = config.get("client", CONFIG_MSG.BRIDGE_AUTOANGLE_MESSAGES.text, true, ColorCodesManager.replaceColorCodesInString("&fБудет/Не будет писать в чат каждый раз сообщение"));
/*     */     
/* 348 */     prop = config.get("client", CONFIG_MSG.RAINBOW_MESSAGE_COMMAND.text, "/r", ColorCodesManager.replaceColorCodesInString("&fТОЛЬКО ДЛЯ ДОНАТЕРОВ!\n&fКоманда чтоб создать радужный цвет\nНапример &c/r всем привет! &7= &cВ&6с&eе&aм &bп&9р&dи&cв&6е&eт&a!\n&fЕсть 2 способа поставить &eсвои цвета&b\n&f1 медленный: в конфиге найди &f\"&eЦветовая палитра&f\". Если навести мышкой будут написаны все цветовый кода, от 0 до 9 и &aa &bb &cc &dd &ee &ff. Чтоб создать свою палитру тебе надо просто написать слитно кода, типо &f\"&aa&dd&ee&f\" это &aзелено&7-&dрозово&7-&eжелтый&f. Или &f\"&cc&66&ee&aa&bb&dd&f\" это &cр&6а&eд&aу&bж&dн&cы&6й&f. И в конце можно добавить &f\"&e+&f\" и код для &lжырного&f, &oнаклоненного&f, &nподчёркнутого&f. Типо &f\"&aa&dd&ee&f+l\" это жирным все будет, или например \"&dd&55&f+lno\" это &d&l&n&oвот&f &5&l&n&oтакой&f &d&l&n&oтекст\n&f2 способ это тоже самое, но не надо заходить в конфиг если знаешь на память кода. Команда &b/rs help &fтам все написано"));
/*     */ 
/*     */ 
/*     */     
/* 352 */     String description = "&fСтавь цифру &e0 &fили &e1\n&60 (Mix):\n&e- если текст &bмаленький &e- &cк&6а&eж&aд&bы&9й &cс&6и&eм&aв&bо&9л &cр&6а&eз&aн&bо&9г&dо &cц&6в&eе&aт&bа&9\n";
/* 353 */     description = description + "&e- если текст &bсредний &e- &cк&6аж&eды&aе &b2 &9си&dмв&cол&6а &eра&aзн&bог&9о &dцв&cет&6а\n";
/* 354 */     description = description + "&e- если текст &bбольшой &e- &cкаждое &6слово &eбудет &aразного &bцвета\n";
/* 355 */     description = description + "&61 (Слова):\n&e- &cкаждое &6слово &eвсегда &aбудет &bразного &9цвета";
/* 356 */     prop = config.get("client", CONFIG_MSG.RAINBOW_MESSAGE_MODE.text, 0, ColorCodesManager.replaceColorCodesInString(description));
/* 357 */     prop = config.get("client", CONFIG_MSG.RAINBOW_MESSAGE_GLOBAL.text, false, ColorCodesManager.replaceColorCodesInString("&fДобавляет \"!\" в начало сообщения"));
/* 358 */     prop = config.get("client", CONFIG_MSG.RAINBOW_MESSAGE_COLORS.text, "c6eab9d+l", ColorCodesManager.replaceColorCodesInString("&fТут ты можешь поменять цвета для сообщения. Вводи в правильном порядке, разделяй запятыми, либо сначала цвета, а потом + и цвета, которые будут добавлены ко всем в конец. Например enl,anl,bnl или eab+nl будет &e&n&lт&a&n&lе&b&n&lк&e&n&lс&a&n&lт&f.\n&fВот все цвета: &11 &22 &33 &44 &55 &66 &77 &88 &99 &f(0 - черный) &aa &bb &cc &dd &ee &ff &f&ll (жирный)&r &f&nn (подчеркнутый) &f&mm (зачеркнутый) &f&oo (наклон)"));
/* 359 */     prop = config.get("client", CONFIG_MSG.RAINBOW_MESSAGE_REPLACE_CHARS.text, "і=ы", ColorCodesManager.replaceColorCodesInString("&fЗаменяет 1-й символ на 2-й. Пиши вот так:\n&c[символ для замены]&7=&a[символ на который]"));
/*     */     
/* 361 */     prop = config.get("client", CONFIG_MSG.POTION_TRACKER.text, true, ColorCodesManager.replaceColorCodesInString("&fПоказывает слева все эффекты, типо силка, скорка... и их время"));
/* 362 */     prop = config.get("client", CONFIG_MSG.POTION_TRACKER_SOUNDS.text, true, ColorCodesManager.replaceColorCodesInString("&fКогда меньше 5 секунд на зельке, будет такой звук \"тик\", и потом когда зелька пропадет, будет звук шипения"));
/*     */     
/* 364 */     prop = config.get("client", CONFIG_MSG.DANGER_ALERT.text, true, ColorCodesManager.replaceColorCodesInString("&fЕсли кто-то будем целиться в тебя из лука или фаерболом, то мод напишет в чат и будет звук!"));
/* 365 */     prop = config.get("client", CONFIG_MSG.DANGER_ALERT_SOUND.text, true, ColorCodesManager.replaceColorCodesInString("&fБудет ли пищать мод"));
/*     */     
/* 367 */     prop = config.get("client", CONFIG_MSG.AUTO_SPRINT.text, true, ColorCodesManager.replaceColorCodesInString("&fВечный Control &7(всегда бежишь &eбыстро&7, как нажать W 2 раза)"));
/* 368 */     prop = config.get("client", CONFIG_MSG.AUTO_WATER_DROP.text, false, ColorCodesManager.replaceColorCodesInString("&fЕсли у тебя в слотах есть вода и ты падаешь с высоты, то мод клачнет за тебя"));
/*     */     
/* 370 */     prop = config.get("client", CONFIG_MSG.BEDWARS_MEOW.text, false, ColorCodesManager.replaceColorCodesInString("&fЭто типо &eDexlandMeow&f, только намного лучше. После каждого кила, или сломаной кровати в чат будут писаться сообщения от тебя типо \"&aPlayer&f, ты лох!\"\n\n&f&lСвои сообщения можно добавить командой &e/meow"));
/* 371 */     prop = config.get("client", CONFIG_MSG.BEDWARS_MEOW_WITH_COLORS.text, false, ColorCodesManager.replaceColorCodesInString("&fТолько для ДОНАТЕРОВ\n&fБудет писать в чат эти же сообщения, только используя цвета, вот так:\n\n&fМмм... Какая вкусная кроватка у &aзеленой &fкоманды!"));
/* 372 */     prop = config.get("client", CONFIG_MSG.BEDWARS_MEOW_WITH_COLORS_RAINBOW.text, false, ColorCodesManager.replaceColorCodesInString("&fТолько для ДОНАТЕРОВ\n&fВсе сообщения будут переливаться радугой, вот так:\n\n&cБро, &eтебе &aнадо &bбольше &9тренероваться!\n&fВсе настройки разноцветности берутся из \"" + CONFIG_MSG.RAINBOW_MESSAGE_COLORS.getName() + "\""));
/*     */     
/* 374 */     prop = config.get("client", CONFIG_MSG.REMOVE_BUY_MESSAGE.text, false, ColorCodesManager.replaceColorCodesInString("&fВ магазине не будут отображаться сообщения"));
/* 375 */     prop = config.get("client", CONFIG_MSG.BETTER_SHOP.text, true, ColorCodesManager.replaceColorCodesInString("&fВ магазине предметы, на которых нету ресов, будут невидимыми\n\n&b&lНапиши команду &e/bwbroshop &b&lдля детальной статистики своих покупок!"));
/* 376 */     prop = config.get("client", CONFIG_MSG.PARTICLE_TRAIL.text, false, ColorCodesManager.replaceColorCodesInString("&fБудут спавниться партиклы за тобой во время бега. Во время строительства не будут мешать"));
/* 377 */     prop = config.get("client", CONFIG_MSG.PARTICLE_TRAIL_RAINBOW.text, true, ColorCodesManager.replaceColorCodesInString("&2&ltrue &7- &fВсегда будут спавниться разноцветные партиклы\n&f&4&lfalse &7- &fВо время катки будут партиклы цвета твоей команды"));
/*     */     
/* 379 */     prop = config.get("client", CONFIG_MSG.NAMEPLATE.text, true, ColorCodesManager.replaceColorCodesInString("&fЕсли нажать &eF5&f, то ты увидишь свой ник"));
/* 380 */     prop = config.get("client", CONFIG_MSG.NAMEPLATE_RAINBOW.text, true, ColorCodesManager.replaceColorCodesInString("&fНик будет красиво переливаться радужным &cг&eр&aа&bд&9и&dе&cн&eт&aо&bм"));
/* 381 */     prop = config.get("client", CONFIG_MSG.NAMEPLATE_RAINBOW_CONSTANT_COLOR.text, "", ColorCodesManager.replaceColorCodesInString("&fОставь пустым, если хочешь радужный\nВводи в цвет в таком формате: &a#00ff00&7, или &9#4287f5 &7(можешь загуглить)"));
/*     */     
/* 383 */     prop = config.get("client", CONFIG_MSG.RAINBOW_SPEED.text, 1, ColorCodesManager.replaceColorCodesInString("&fСкорость переливания радуги\n&fСтавь от &e1 &fдо &e100 &7(рекомендую около 10)"));
/*     */     
/* 385 */     prop = config.get("client", CONFIG_MSG.INVULNERABLE_TIMER.text, true, ColorCodesManager.replaceColorCodesInString("&fПосле спавна ты не можешь бить игроков 3 секунды. Над ними будет показываться время"));
/* 386 */     prop = config.get("client", CONFIG_MSG.INVULNERABLE_TIMER_SOUNDS.text, true, ColorCodesManager.replaceColorCodesInString("&fБудет возпроизводиться 3 звука, разной тональности и громкости"));
/*     */     
/* 388 */     prop = config.get("client", CONFIG_MSG.RESOURCES_HOLOGRAM.text, true, ColorCodesManager.replaceColorCodesInString("&fБудет такая надпись над алмазами, изумрудами показывать &eсколько их лежит на земле"));
/* 389 */     prop = config.get("client", CONFIG_MSG.MAP_AUTO_SELECTER.text, "", ColorCodesManager.replaceColorCodesInString("&fМожно заходить в игру &cбыстрым стартом&f, а можно выбирать карту. Тут ты можешь добавить свои &bлюбимые &fкарты, и они буду отображаться &bотдельно &fв &bголубом стекле\n\n&e&lПиши через запятую, вот так:\n\n&fAcropolis, SubwaySurfers, SkyRise\n"));
/*     */     
/* 391 */     prop = config.get("client", CONFIG_MSG.GENERATOR_TIMERS.text, true, ColorCodesManager.replaceColorCodesInString("&fВ углу экрана будут находиться 2 таймера, на &bалмазы &fи &aизумруды"));
/* 392 */     prop = config.get("client", CONFIG_MSG.GENERATOR_TIMERS_ADVANCED.text, false, ColorCodesManager.replaceColorCodesInString("&fБудет добавлен еще 1 таймер на улучшения генераторов на новый уровень"));
/* 393 */     prop = config.get("client", CONFIG_MSG.GENERATOR_TIMERS_POSITION.text, 2, ColorCodesManager.replaceColorCodesInString("&fСтавь от &e1 &fдо &e4&f. Это в каком углу будут таймера\n\n&b1 &7- &eлевый верхний\n&b2 &7- &eправый верхний\n&b3 &7- &eправый нижний\n&b4 &7- &eлевый нижний\n"));
/* 394 */     prop = config.get("client", CONFIG_MSG.GENERATOR_TIMERS_TIMELINE.text, false, ColorCodesManager.replaceColorCodesInString("&fВключи, посмотри. Это временная полоса игры, как компас"));
/* 395 */     prop = config.get("client", CONFIG_MSG.GENERATOR_TIMERS_TIMELINE_WIDTH.text, 80, ColorCodesManager.replaceColorCodesInString("&fСтавь от &e50 &fдо &e100&f. Это ширина таймлайна в процентах от ширины экрана"));
/*     */     
/* 397 */     prop = config.get("client", CONFIG_MSG.EMOTIONS_REMOVE_SOUND.text, false, ColorCodesManager.replaceColorCodesInString("&fТы будешь видеть эмоции, но не будет такого звука \"поп\""));
/* 398 */     prop = config.get("client", CONFIG_MSG.BRO_PREFIX.text, "", ColorCodesManager.replaceColorCodesInString("&fМеняй только &e/bwprefix&f!"));
/*     */ 
/*     */ 
/*     */     
/* 402 */     config.save();
/*     */   }
/*     */   
/*     */   public static void saveConfig() {
/* 406 */     config.save();
/*     */   }
/*     */   
/* 409 */   private static long time_last_update = 0L;
/*     */   @SubscribeEvent
/*     */   public void onConfigChange(ConfigChangedEvent event) throws IllegalAccessException {
/* 412 */     if (this.configChangedEventModIDField.get(event).equals("bedwarsbro")) {
/* 413 */       config.save();
/* 414 */       updateAllBooleans();
/*     */       
/* 416 */       Date date = new Date();
/* 417 */       if (date.getTime() - time_last_update > 1000L) {
/* 418 */         ChatSender.addText("&c&lBedwars&f&lBro: &aобновлен успешно!");
/* 419 */         time_last_update = date.getTime();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void updateAllBooleans() {
/* 425 */     MyChatListener.IS_MOD_ACTIVE = getConfigBool(CONFIG_MSG.ENABLE_BETTER_CHAT);
/*     */     
/* 427 */     myTickEvent.updateHintsBooleans();
/* 428 */     GuiMinimap.updateBooleans();
/* 429 */     generatorTimers.updateBooleans();
/* 430 */     particleController.updateBooleans();
/* 431 */     guiOnScreen.updateBooleans();
/* 432 */     autoSprint.updateBooleans();
/* 433 */     playerFocus.updateBooleans();
/* 434 */     bedwarsMeow.updateBooleans();
/* 435 */     particleTrail.updateBooleans();
/* 436 */     rainbowColorSynchronizer.updateBooleans();
/* 437 */     shopManager.updateBooleans();
/* 438 */     guiBedESP.updateBooleans();
/* 439 */     GuiRadarIcon.updateBooleans();
/* 440 */     customScoreboard.updateBooleans();
/* 441 */     emotesDrawer.updateBooleans();
/*     */     
/* 443 */     if (getPropModLastVersion() != null) SameModePlayers.isActive = getPropModLastVersion().equals("1.69");
/*     */     
/* 445 */     readCommandRainbowMessage();
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void preInitialization(FMLPreInitializationEvent event) {}
/*     */ 
/*     */   
/*     */   static void readCommandRainbowMessage() {
/* 454 */     commandRainbowMessage = new CommandRainbowMessage(getConfigString(CONFIG_MSG.RAINBOW_MESSAGE_COMMAND));
/* 455 */     ClientCommandHandler.instance.func_71560_a((ICommand)commandRainbowMessage);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void postInitialization(FMLPostInitializationEvent event) {
/* 460 */     keybindHandler = new KeybindHandler(this);
/*     */     
/* 462 */     chatListener = new MyChatListener();
/* 463 */     fileManager = new FileManager();
/*     */     
/* 465 */     minimap = new GuiMinimap();
/* 466 */     generatorTimers = new GeneratorTimers();
/* 467 */     playerFocus = new GuiPlayerFocus();
/* 468 */     tntjump = new TNTJump();
/* 469 */     dangerAlert = new DangerAlert();
/* 470 */     guiOnScreen = new GuiOnScreen(this);
/* 471 */     itemTracker = new HintsItemTracker();
/* 472 */     scoreboardManager = new ScoreboardManager();
/* 473 */     customScoreboard = new CustomScoreboard();
/* 474 */     hintsBaseRadar = new HintsBaseRadar();
/* 475 */     hintsPlayerScanner = new HintsPlayerScanner();
/* 476 */     myTickEvent = new OnMyTickEvent();
/* 477 */     particleController = new ParticleController();
/* 478 */     particleTrail = new ParticleTrail();
/* 479 */     bedwarsMeow = new BedwarsMeow();
/* 480 */     autoSprint = new AutoSprint();
/* 481 */     autoWaterDrop = new AutoWaterDrop();
/*     */     
/* 483 */     chatReader = new ChatReader();
/* 484 */     shopManager = new ShopManager();
/* 485 */     loginHandler = new LoginHandler();
/* 486 */     particlesAlwaysSharpness = new ParticlesAlwaysSharpness();
/* 487 */     invulnerableTime = new InvulnerableTime();
/* 488 */     namePlate = new NamePlate();
/* 489 */     draw3DText = new Draw3DText();
/* 490 */     mySerializer = new MySerializer();
/* 491 */     rainbowColorSynchronizer = new RainbowColorSynchronizer();
/* 492 */     namePlateRenderer = new NamePlateRenderer();
/* 493 */     sameModePlayers = new SameModePlayers();
/* 494 */     guiResourceHologram = new GuiResourceHologram();
/* 495 */     playerManipulator = new PlayerManipulator();
/* 496 */     bowAimbot = new BowAimbot();
/* 497 */     aimHelper = new AimHelper();
/* 498 */     cantHitDimChig = new CantHitDimChig();
/* 499 */     guiBedESP = new GuiBedESP();
/* 500 */     guiRadarIcon = new GuiRadarIcon();
/* 501 */     emotesDrawer = new EmotesDrawer();
/* 502 */     emojiGui = new EmojiGui();
/*     */ 
/*     */ 
/*     */     
/* 506 */     MinecraftForge.EVENT_BUS.register(this);
/* 507 */     MinecraftForge.EVENT_BUS.register(keybindHandler);
/* 508 */     MinecraftForge.EVENT_BUS.register(playerFocus);
/* 509 */     MinecraftForge.EVENT_BUS.register(tntjump);
/* 510 */     MinecraftForge.EVENT_BUS.register(chatListener);
/* 511 */     MinecraftForge.EVENT_BUS.register(myTickEvent);
/* 512 */     MinecraftForge.EVENT_BUS.register(guiOnScreen);
/* 513 */     MinecraftForge.EVENT_BUS.register(bedwarsMeow);
/* 514 */     MinecraftForge.EVENT_BUS.register(autoSprint);
/* 515 */     MinecraftForge.EVENT_BUS.register(chatReader);
/* 516 */     MinecraftForge.EVENT_BUS.register(particlesAlwaysSharpness);
/* 517 */     MinecraftForge.EVENT_BUS.register(shopManager);
/* 518 */     MinecraftForge.EVENT_BUS.register(loginHandler);
/* 519 */     MinecraftForge.EVENT_BUS.register(namePlateRenderer);
/* 520 */     MinecraftForge.EVENT_BUS.register(bowAimbot);
/* 521 */     MinecraftForge.EVENT_BUS.register(aimHelper);
/* 522 */     MinecraftForge.EVENT_BUS.register(cantHitDimChig);
/* 523 */     MinecraftForge.EVENT_BUS.register(guiBedESP);
/* 524 */     MinecraftForge.EVENT_BUS.register(guiRadarIcon);
/* 525 */     MinecraftForge.EVENT_BUS.register(emotesDrawer);
/* 526 */     MinecraftForge.EVENT_BUS.register(emojiGui);
/*     */ 
/*     */ 
/*     */     
/* 530 */     commandHintsFinderLookAtPlayer = new CommandHintsFinderLookAtPlayer();
/* 531 */     commandHintsFinderLookAt = new CommandHintsFinderLookAt();
/* 532 */     commandMeow = new CommandMeow(this);
/* 533 */     commandRainbowMessageSetter = new CommandRainbowMessageSetter();
/* 534 */     commandDexlandMeowSpoof = new CommandDexlandMeowSpoof();
/* 535 */     commandManipulate = new CommandManipulate();
/* 536 */     commandBowAimbotToggle = new CommandBowAimbotToggle();
/* 537 */     commandAimHelperToggle = new CommandAimHelperToggle();
/* 538 */     commandBWDiscord = new CommandBWDiscord();
/* 539 */     commandPrefix = new CommandPrefix();
/* 540 */     commandEmote = new CommandEmote();
/* 541 */     commandHistoryShop = new CommandHistoryShop(this);
/* 542 */     commandFindPlayerByName = new CommandFindPlayerByName();
/* 543 */     ClientCommandHandler.instance.func_71560_a((ICommand)commandHintsFinderLookAtPlayer);
/* 544 */     ClientCommandHandler.instance.func_71560_a((ICommand)commandHintsFinderLookAt);
/* 545 */     ClientCommandHandler.instance.func_71560_a((ICommand)commandMeow);
/* 546 */     ClientCommandHandler.instance.func_71560_a((ICommand)commandHistoryShop);
/* 547 */     ClientCommandHandler.instance.func_71560_a((ICommand)commandFindPlayerByName);
/* 548 */     ClientCommandHandler.instance.func_71560_a((ICommand)commandRainbowMessageSetter);
/* 549 */     ClientCommandHandler.instance.func_71560_a((ICommand)commandDexlandMeowSpoof);
/* 550 */     ClientCommandHandler.instance.func_71560_a((ICommand)commandManipulate);
/* 551 */     ClientCommandHandler.instance.func_71560_a((ICommand)commandBowAimbotToggle);
/* 552 */     ClientCommandHandler.instance.func_71560_a((ICommand)commandAimHelperToggle);
/* 553 */     ClientCommandHandler.instance.func_71560_a((ICommand)commandBWDiscord);
/* 554 */     ClientCommandHandler.instance.func_71560_a((ICommand)commandPrefix);
/* 555 */     ClientCommandHandler.instance.func_71560_a((ICommand)commandEmote);
/*     */     
/* 557 */     ClientCommandHandler.instance.func_71560_a((ICommand)new CommandModHelp(this, "/bwbro"));
/* 558 */     ClientCommandHandler.instance.func_71560_a((ICommand)new CommandModHelp(this, "/bedwarswbro"));
/* 559 */     ClientCommandHandler.instance.func_71560_a((ICommand)new CommandModHelp(this, "/bro"));
/*     */     
/* 561 */     readCommandRainbowMessage();
/*     */ 
/*     */     
/* 564 */     baseProps = new BaseProps();
/* 565 */     baseProps.readProps();
/*     */     
/* 567 */     GetHintsPWD.getPWD();
/*     */ 
/*     */     
/* 570 */     MyChatListener.IS_MOD_ACTIVE = getConfigBool(CONFIG_MSG.ENABLE_BETTER_CHAT);
/*     */   }
/*     */   
/*     */   public ConfigCategory getClientConfig() {
/* 574 */     return clientConfig;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getPropModPwd() {
/* 583 */     if (baseProps == null) return null; 
/* 584 */     return baseProps.getModPwd();
/*     */   }
/*     */   public static BaseProps.ENCODING_FORMAT getPropSenderType() {
/* 587 */     if (baseProps == null) return BaseProps.ENCODING_FORMAT.ib8; 
/* 588 */     return baseProps.getSenderType();
/*     */   }
/*     */   public static String getPropModLastVersion() {
/* 591 */     if (baseProps == null) return null; 
/* 592 */     return baseProps.getModLastVersion();
/*     */   }
/*     */   public static String getPropModUpdateLink() {
/* 595 */     if (baseProps == null) return null; 
/* 596 */     return baseProps.getModUpdateLink();
/*     */   }
/*     */   public static String getPropDiscordLink() {
/* 599 */     if (baseProps == null) return null; 
/* 600 */     return baseProps.getDiscordLink();
/*     */   }
/*     */   public static ArrayList<String> getPropModBannedUsers() {
/* 603 */     if (baseProps == null) return new ArrayList<String>(); 
/* 604 */     return baseProps.getModBannedUsers();
/*     */   }
/*     */   public static boolean isPropUserBanned(String player_name) {
/* 607 */     if (baseProps == null) return false; 
/* 608 */     return baseProps.isUserBanned(player_name);
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\Main.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */