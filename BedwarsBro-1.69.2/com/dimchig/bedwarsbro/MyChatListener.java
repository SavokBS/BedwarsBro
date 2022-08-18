/*      */ package com.dimchig.bedwarsbro;
/*      */ 
/*      */ import com.dimchig.bedwarsbro.hints.BWBed;
/*      */ import com.dimchig.bedwarsbro.hints.BedwarsMeow;
/*      */ import com.dimchig.bedwarsbro.hints.HintsBaseRadar;
/*      */ import com.dimchig.bedwarsbro.hints.HintsBedScanner;
/*      */ import com.dimchig.bedwarsbro.hints.HintsValidator;
/*      */ import com.dimchig.bedwarsbro.hints.LobbyBlockPlacer;
/*      */ import com.dimchig.bedwarsbro.hints.WinEmote;
/*      */ import com.dimchig.bedwarsbro.particles.ParticleController;
/*      */ import java.text.DecimalFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
/*      */ import java.util.List;
/*      */ import java.util.Timer;
/*      */ import java.util.TimerTask;
/*      */ import java.util.regex.Pattern;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.entity.EntityPlayerSP;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.util.ChatComponentText;
/*      */ import net.minecraft.util.IChatComponent;
/*      */ import net.minecraft.util.MovingObjectPosition;
/*      */ import net.minecraftforge.client.event.ClientChatReceivedEvent;
/*      */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*      */ import net.minecraftforge.fml.common.network.FMLNetworkEvent;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MyChatListener
/*      */ {
/*      */   public static boolean IS_MOD_ACTIVE;
/*      */   public static boolean IS_IN_GAME;
/*   45 */   public static long GAME_start_time = -1L;
/*   46 */   public static int GAME_total_death = -1;
/*   47 */   public static int GAME_total_kills = -1;
/*   48 */   public static int GAME_total_beds = -1;
/*      */   public static BWBed GAME_BED;
/*      */   
/*      */   public MyChatListener() {
/*   52 */     IS_MOD_ACTIVE = true;
/*   53 */     IS_IN_GAME = false;
/*   54 */     removeNextMessage = false;
/*   55 */     initChatMessages();
/*      */     
/*   57 */     for (ChatMessage chatMessage : chatMessages) {
/*   58 */       for (ChatMessage chatMessage2 : chatMessages) {
/*   59 */         if (chatMessage.type == chatMessage2.type && !chatMessage.message.equals(chatMessage2.message)) {
/*   60 */           ChatSender.addText("DUBLICATE CHAT MESSAGE - " + chatMessage.type);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*   66 */     meowMessagesQuee = new ArrayList<MsgMeowQuee>();
/*      */   }
/*      */   
/*      */   public static boolean removeNextMessage = false;
/*      */   
/*      */   public static class ChatMessage
/*      */   {
/*      */     public MyChatListener.CHAT_MESSAGE type;
/*      */     public String message;
/*      */     public String[] elements;
/*      */     public String[] element_values;
/*      */     public boolean isMustBeEqual;
/*      */     
/*      */     public ChatMessage(MyChatListener.CHAT_MESSAGE type, String message, String[] elements) {
/*   80 */       init(type, message, elements, false);
/*      */     }
/*      */     
/*      */     public ChatMessage(MyChatListener.CHAT_MESSAGE type, String message, String[] elements, boolean isMustBeEqual) {
/*   84 */       init(type, message, elements, isMustBeEqual);
/*      */     }
/*      */     
/*      */     private void init(MyChatListener.CHAT_MESSAGE type, String message, String[] elements, boolean isMustBeEqual) {
/*   88 */       this.type = type;
/*   89 */       this.message = message.trim();
/*      */       
/*   91 */       ArrayList<String> arr = new ArrayList<String>();
/*      */       
/*   93 */       for (int i = 0; i < elements.length; i++) {
/*   94 */         if (elements[i].length() > 0) {
/*   95 */           arr.add("%" + elements[i].trim() + "%");
/*      */         }
/*      */       } 
/*   98 */       this.elements = arr.<String>toArray(new String[arr.size()]);
/*   99 */       this.element_values = new String[this.elements.length];
/*  100 */       this.isMustBeEqual = isMustBeEqual;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  106 */   public static int TIME_MEOW_MESSAGES_CHECK_FREQUENCY = 5000;
/*      */   public static List<MsgMeowQuee> meowMessagesQuee;
/*      */   
/*      */   public static class MsgMeowQuee {
/*      */     public String text;
/*      */     
/*      */     public MsgMeowQuee(String text, long time, boolean isInGameOnly) {
/*  113 */       this.text = text;
/*  114 */       this.time = time;
/*  115 */       this.isInGameOnly = isInGameOnly;
/*      */     }
/*      */     
/*      */     public long time;
/*      */     public boolean isInGameOnly;
/*      */   }
/*  121 */   public static MyStatistic myStatistic = null;
/*      */   
/*  123 */   public static int bwmanipulator_party_info = -1;
/*      */   
/*      */   public static class MyStatistic
/*      */   {
/*      */     public String player;
/*      */     public int category_kills_cnt;
/*      */     public int category_kills_place;
/*      */     public int category_deathes_cnt;
/*      */     public int category_deathes_place;
/*      */     public int category_games_cnt;
/*      */     public int category_games_place;
/*      */     public int category_wins_cnt;
/*      */     public int category_wins_place;
/*      */     public int category_beds_cnt;
/*      */     public int category_beds_place;
/*      */     public float percentage_kill_to_death;
/*      */     public float percentage_games_to_wins;
/*      */     public int best_ranking;
/*      */     
/*      */     public MyStatistic(String player) {
/*  143 */       this.player = player;
/*  144 */       this.category_kills_cnt = 0;
/*  145 */       this.category_kills_place = 0;
/*  146 */       this.category_deathes_cnt = 0;
/*  147 */       this.category_deathes_place = 0;
/*  148 */       this.category_games_cnt = 0;
/*  149 */       this.category_games_place = 0;
/*  150 */       this.category_wins_cnt = 0;
/*  151 */       this.category_wins_place = 0;
/*  152 */       this.category_beds_cnt = 0;
/*  153 */       this.category_beds_place = 0;
/*      */       
/*  155 */       this.percentage_kill_to_death = -1.0F;
/*  156 */       this.percentage_games_to_wins = -1.0F;
/*  157 */       this.best_ranking = -1;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  163 */   public static String DIMCHIG_NAME = "&c&lD&6&li&e&lm&a&lC&b&lh&9&li&d&lg";
/*  164 */   public static String DIMCHIG_NAME_GOLD = "&e&lDim&6&lChig";
/*      */   public static MyParty myParty;
/*      */   
/*      */   public static class MyParty { public String creator;
/*      */     public ArrayList<String> members;
/*      */     
/*      */     public MyParty(String creator, ArrayList<String> members) {
/*  171 */       this.creator = creator;
/*  172 */       this.members = members;
/*      */     } }
/*      */ 
/*      */   
/*      */   enum CHAT_MESSAGE
/*      */   {
/*  178 */     NONE,
/*  179 */     CHAT_LEFT_GAME,
/*  180 */     CHAT_GAME_STARTED,
/*  181 */     CHAT_BEDWARS_GAME_STARTED_TIPS,
/*  182 */     CHAT_JOINED_MIDGAME,
/*  183 */     CHAT_JOINED_PREGAME,
/*  184 */     CHAT_LEFT_PREGAME,
/*  185 */     CHAT_SUICIDE,
/*  186 */     CHAT_SUICIDE_VOID,
/*  187 */     CHAT_KILLED_BY_VOID,
/*  188 */     CHAT_KILLED_BY_PLAYER,
/*  189 */     CHAT_TEAM_DESTROYED,
/*  190 */     CHAT_TEAM_BED_BROKEN,
/*  191 */     CHAT_TEAM_COLOR_CHOSEN,
/*  192 */     CHAT_TEAM_ALREADY_CHOSEN,
/*  193 */     CHAT_TEAM_IS_FULL,
/*  194 */     CHAT_SHOP_ITEM_BOUGHT,
/*  195 */     CHAT_SHOP_NOT_ENOUGH_RESOURCES,
/*  196 */     CHAT_UPGRADE_BOUGHT,
/*  197 */     CHAT_GENERATOR_DIAMOND_LEVELED_UP,
/*  198 */     CHAT_GENERATOR_EMERALD_LEVELED_UP,
/*  199 */     CHAT_TRAP_ACTIVATED,
/*  200 */     CHAT_SERVER_RESTART,
/*  201 */     CHAT_TELEPORTATION_TO_HUB,
/*  202 */     CHAT_CONNECTING_TO_LOBBY,
/*  203 */     CHAT_HUB_CHAT_PLAYER_MESSAGE,
/*  204 */     CHAT_YOU_WERE_KICKED,
/*  205 */     CHAT_ADS,
/*  206 */     CHAT_GAME_STARTS_IN_SECONDS,
/*  207 */     CHAT_GAME_CHAT_LOCAL,
/*  208 */     CHAT_GAME_CHAT_GLOBAL,
/*  209 */     CHAT_GAME_CHAT_SPECTATOR,
/*  210 */     CHAT_GAME_CHAT_PREGAME,
/*  211 */     CHAT_PREGAME_FASTSTART_REJECT,
/*  212 */     CHAT_LOBBY_DONATER_GREETING,
/*  213 */     CHAT_PLAYER_BANNED,
/*  214 */     CHAT_PREGAME_NOT_ENOUGH_PLAYERS_TO_START,
/*  215 */     CHAT_BEDWARS_END_TEAM_WON,
/*  216 */     CHAT_BEDWARS_END_TOP_KILLER,
/*  217 */     CHAT_BEDWARS_END_TOP_KILLER_UNKNOWN,
/*      */     
/*  219 */     CHAT_LOBBY_PARTY_INVITE,
/*  220 */     CHAT_LOBBY_PARTY_INVITE_REJECTED,
/*  221 */     CHAT_LOBBY_PARTY_WARP,
/*  222 */     CHAT_LOBBY_PARTY_PLAYER_OFFLINE,
/*  223 */     CHAT_LOBBY_PARTY_DISBANDED,
/*  224 */     CHAT_LOBBY_PARTY_ALREADY_IN_PARTY,
/*  225 */     CHAT_LOBBY_PARTY_OFFLINE,
/*  226 */     CHAT_LOBBY_PARTY_YOU_ARE_NOT_IN_PARTY,
/*  227 */     CHAT_LOBBY_PARTY_PLAYER_KICKED,
/*  228 */     CHAT_LOBBY_PARTY_PLAYER_LEFT,
/*  229 */     CHAT_LOBBY_PARTY_CHAT_ENTER_MESSAGE,
/*  230 */     CHAT_LOBBY_PARTY_CHAT_MESSAGE,
/*  231 */     CHAT_LOBBY_PARTY_NO_PERMISSION,
/*  232 */     CHAT_LOBBY_PARTY_DISBAND_REQUEST,
/*  233 */     CHAT_LOBBY_PARTY_NOT_ENOUGH_SPACE,
/*  234 */     CHAT_LOBBY_PARTY_REQUEST_ALREADY_SENT,
/*  235 */     CHAT_LOBBY_PARTY_YOU_WERE_WARPED,
/*  236 */     CHAT_LOBBY_PARTY_OWNER_LEFT,
/*  237 */     CHAT_LOBBY_PARTY_REQUEST,
/*  238 */     CHAT_LOBBY_PARTY_YOU_ACCEPTED_REQUEST,
/*  239 */     CHAT_LOBBY_PARTY_NEW_LEADER,
/*  240 */     CHAT_LOBBY_PARTY_COMMANDS_DONT_WORK_IN_LOBBY,
/*  241 */     CHAT_LOBBY_PARTY_INFO,
/*      */ 
/*      */     
/*  244 */     CHAT_LOBBY_STATS_PLAYER,
/*  245 */     CHAT_LOBBY_STATS_CATEGORY,
/*  246 */     CHAT_PARTY_ON_CREATE,
/*  247 */     CHAT_GAME_CANT_USE_COMMANDS,
/*  248 */     CHAT_GAME_WAIT_SECONDS,
/*  249 */     CHAT_GAME_YOU_CANT_USE_COLORS,
/*  250 */     CHAT_GAME_YOU_CANT_USE_COLOR,
/*  251 */     CHAT_GAME_ANTI_CHEAT_KICK,
/*  252 */     CHAT_HUB_ANTIFLOOD,
/*  253 */     CHAT_YOU_ARE_TELEPORTED_TO_LOBBY_DUE_TO_FULL_ARENA,
/*  254 */     CHAY_YOU_ALREADY_ON_SERVER;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  259 */   public static String SOUND_BED_BROKEN = "random.wood_click";
/*  260 */   public static String SOUND_TEAM_DESTROYED = "fireworks.blast";
/*  261 */   public static String SOUND_GAME_END = "fireworks.twinkle_far";
/*  262 */   public static String SOUND_REJECT = "note.bassattack";
/*  263 */   public static String SOUND_UPGRADE_BOUGHT = "random.anvil_land";
/*  264 */   public static String SOUND_TRAP_ACTIVATED = "note.pling";
/*  265 */   public static String SOUND_PLAYER_STATS = "random.orb";
/*  266 */   public static String SOUND_TEAM_CHOSEN = "random.click";
/*  267 */   public static String SOUND_PARTY_CREATED = "fireworks.twinkle_far";
/*  268 */   public static String SOUND_PARTY_CHAT = "random.orb";
/*  269 */   public static String SOUND_RADAR_FAR = "note.harp";
/*  270 */   public static String SOUND_RADAR_CLOSE = "note.pling";
/*  271 */   public static String SOUND_EMOTE = "random.pop";
/*      */ 
/*      */   
/*  274 */   public static String PREFIX_BEDWARSBRO = "&r&cBedwars&fBro &8▸ &r";
/*      */   
/*  276 */   public static String PREFIX_PARTY = "&r&6&lParty &8▸ &r";
/*  277 */   public static String PREFIX_BED = "&r&c&lBed&8 ▸ &r";
/*  278 */   public static String PREFIX_TEAM = "&r&6&lTeam&8 ▸ &r";
/*  279 */   public static String PREFIX_ANTICHEAT = "&r&6&lAntiCheat&8 ▸ §r";
/*  280 */   public static String PREFIX_UPGRADES = "&r&b&lUpgrades&8 ▸ §r";
/*  281 */   public static String PREFIX_HINT = "&r&a&lHints&8 ▸ §r";
/*  282 */   public static String PREFIX_HINT_BED_SCANNER = "&r&cЗащита &cкровати&8 ▸ §r";
/*  283 */   public static String PREFIX_HINT_RADAR = "&r&a&lRadar&8 ▸ §r";
/*  284 */   public static String PREFIX_HINT_FINDER = "&r&b&lПоиск&8 ▸ §r";
/*  285 */   public static String PREFIX_HINT_RESOURCES_FINDER = "&r&e&lРесурсы&8 ▸ §r";
/*  286 */   public static String PREFIX_ANTIFLOOD = "§6&lАнтиФлуд &8▸ &r";
/*  287 */   public static String PREFIX_DANGER_ALERT = "§c&lОПАСНОСТЬ &8▸ &r";
/*  288 */   public static String PREFIX_TNT_JUMP = "§4&lTNT &8▸ &r";
/*  289 */   public static String PREFIX_WATER_DROP = "§b&lWater Drop &8▸ &r";
/*  290 */   public static String PREFIX_BEDWARS_MEOW = "§e&lMeow &8▸ &r";
/*  291 */   public static String PREFIX_DIMCHIG_JOINED = "§r§c§l› &c[&c&lYou&f&lTube&f] &r";
/*      */   
/*  293 */   public static int GAME_MAP_PLAYERS_MAX_SIZE = 0;
/*      */   
/*      */   public static ArrayList<ChatMessage> chatMessages;
/*      */ 
/*      */   
/*      */   public static void initChatMessages() {
/*  299 */     chatMessages = new ArrayList<ChatMessage>();
/*  300 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_LEFT_GAME, "§r§6§lBedWars §r§8%skip% §r%player% §r§fпокинул игру§r", new String[] { "skip", "player", "" }));
/*      */     
/*  302 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_LEFT_PREGAME, "§r§6§lBedWars §r§8%skip% §r%player% §r§fпокинул игру §r§c%cnt_players_current%/%cnt_players_total%§r", new String[] { "skip", "player", "cnt_players_current", "cnt_players_total" }));
/*      */     
/*  304 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_GAME_STARTED, "§r§eЗащити свою кровать и сломай чужие кровати.§r", new String[] { "", "" }));
/*      */     
/*  306 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_JOINED_PREGAME, "§r§6§lBedWars §r§8%skip% §r%player% §r§fподключился к игре §r§a%cnt_players_current%/%cnt_players_total%§r", new String[] { "skip", "player", "cnt_players_current", "cnt_players_total" }));
/*      */     
/*  308 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_JOINED_MIDGAME, "§r§6§lBedWars §r§8%skip% §r%player% §r§fподключился к игре§r", new String[] { "skip", "player" }));
/*      */     
/*  310 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_SUICIDE, "§r§6§lBedWars §r§8%skip% §r%player% §r§fпогиб.§r", new String[] { "skip", "player", "" }));
/*      */     
/*  312 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_SUICIDE_VOID, "§r§6§lBedWars §r§8%skip% §r%player% §r§fупал в бездну.§r", new String[] { "skip", "player", "" }));
/*      */     
/*  314 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_KILLED_BY_VOID, "§r§6§lBedWars §r§8%skip% §r%victim% §r§fбыл скинут в бездну игроком §r%killer%§r§f.§r", new String[] { "skip", "victim", "killer" }));
/*      */     
/*  316 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_KILLED_BY_PLAYER, "§r§6§lBedWars §r§8%skip% §r%victim% §r§fбыл убит игроком §r%killer%§r§f.§r", new String[] { "skip", "victim", "killer" }));
/*      */     
/*  318 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_TEAM_DESTROYED, "§r§6§lBedWars §r§8%skip% §r§fКоманда §r%team% §r§fуничтожена!§r", new String[] { "skip", "team", "" }));
/*      */     
/*  320 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_TEAM_BED_BROKEN, "§r§6§lBedWars §r§8%skip% §r%player% §r§fразрушил кровать команды §r%team%§r§f!§r", new String[] { "skip", "player", "team" }));
/*      */     
/*  322 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_TEAM_COLOR_CHOSEN, "§r§6§lBedWars §r§8%skip% §r§fВы выбрали команду §r%team%§r§f!§r", new String[] { "skip", "team", "" }));
/*      */     
/*  324 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_TEAM_ALREADY_CHOSEN, "§r§6§lBedWars §r§8%skip% §r§cВы уже выбрали текущую команду.§r", new String[] { "skip", "" }));
/*      */     
/*  326 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_TEAM_IS_FULL, "§r§6§lBedWars §r§8%skip% §r§cДанная команда заполнена.§r", new String[] { "skip", "" }));
/*      */     
/*  328 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_SHOP_ITEM_BOUGHT, "§r§7Вы купили §r§a%item%§r §ax%amount%§r", new String[] { "item", "amount" }));
/*      */     
/*  330 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_SHOP_NOT_ENOUGH_RESOURCES, "§r§6§lBedWars §r§8%skip% §r§cУ Вас недостаточно ресурсов!§r", new String[] { "skip", "", "" }));
/*      */     
/*  332 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_UPGRADE_BOUGHT, "§r§6§lBedWars §r§8%skip% §r§fИгрок §r§f%player% §r§fпрокачал улучшение §r§b%upgrade% §r§fдо уровня §r§b%level%§r§f!", new String[] { "skip", "player", "upgrade", "level" }));
/*      */     
/*  334 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_GENERATOR_DIAMOND_LEVELED_UP, "§r§6§lBedWars §r§8%skip% §r§fГенераторы §r§bалмазов§r§f прокачаны до уровня §r§a%level%§r§f", new String[] { "skip", "level", "" }));
/*      */     
/*  336 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_GENERATOR_EMERALD_LEVELED_UP, "§r§6§lBedWars §r§8%skip% §r§fГенераторы §r§aизумрудов§r§f прокачаны до уровня §r§a%level%§r§f.§r", new String[] { "skip", "level", "" }));
/*      */     
/*  338 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_TRAP_ACTIVATED, "§r§6§lBedWars §r§8%skip% §r§cЛовушка сработала, на вашем острове враг!§r", new String[] { "skip", "", "" }));
/*      */     
/*  340 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_SERVER_RESTART, "§r§cПерезагрузка сервера через §r§c§l%seconds%§r§c секунд!§r", new String[] { "seconds", "" }));
/*      */     
/*  342 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_TELEPORTATION_TO_HUB, "§r§6§lBedWars §r§8%skip% §r§aТелепортация в лобби....§r", new String[] { "skip", "", "" }));
/*      */     
/*  344 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_CONNECTING_TO_LOBBY, "§aПодключение к %lobby%...§r", new String[] { "lobby", "" }));
/*      */     
/*  346 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_HUB_CHAT_PLAYER_MESSAGE, "§r§r%player% §8 §8%skip% §7§r%message%§r", new String[] { "player", "skip", "message" }));
/*      */     
/*  348 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_YOU_WERE_KICKED, "§r§fТы перемещен в лобби §r§8▸ §r§cИзвините, но вас кикнули", new String[] { "", "" }));
/*      */     
/*  350 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_GAME_STARTS_IN_SECONDS, "§r§6§lBedWars §r§8%skip% §r§eДо старта осталось §r§c%seconds%§r§e секунд...§r", new String[] { "skip", "seconds", "" }));
/*      */     
/*  352 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_GAME_CHAT_LOCAL, "§r§r%teamcolor%[⚑] %player% §8%skip% §7§r%message%§r", new String[] { "teamcolor", "player", "skip", "message" }));
/*      */     
/*  354 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_GAME_CHAT_GLOBAL, "§r§r%teamcolor%[Всем] %player% §8%skip% §7§r%message%§r", new String[] { "teamcolor", "player", "skip", "message" }));
/*      */     
/*  356 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_GAME_CHAT_SPECTATOR, "§r§r§7Наблюдатель §8| §f%player% §8%skip% §7§r%message%§r", new String[] { "player", "skip", "message" }));
/*      */     
/*  358 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_GAME_CHAT_PREGAME, "§r§r§f%player% §8→ §7§r§7§7%message%§r", new String[] { "player", "message" }));
/*      */     
/*  360 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_PREGAME_FASTSTART_REJECT, "§cНедостаточно прав для запуска игры.§r", new String[] { "" }));
/*      */     
/*  362 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_LOBBY_DONATER_GREETING, "§r§c§l› §r§fИгрок §r%player% §r§f%greeting%§r", new String[] { "player", "greeting" }));
/*      */     
/*  364 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_PLAYER_BANNED, "§r§6АнтиЧит§r§8 %skip% §rИгрок §r§c%player% §r§fбыл временно §r§cзабанен §r§fпо подозрению в использовании читов.§r", new String[] { "skip", "player" }));
/*      */     
/*  366 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_PREGAME_NOT_ENOUGH_PLAYERS_TO_START, "§r§6§lBedWars §r§8%skip% §r§cНедостаточно игроков для старта.§r", new String[] { "skip" }));
/*      */     
/*  368 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_BEDWARS_END_TEAM_WON, "§r§f                 Победила команда - §r%team%§r", new String[] { "team" }));
/*      */     
/*  370 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_BEDWARS_END_TOP_KILLER, "§r%skip%                      §r%place% §r§7- §r§7%player% §r§7(%kills_cnt%)§r", new String[] { "skip", "place", "player", "kills_cnt" }));
/*      */     
/*  372 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_BEDWARS_END_TOP_KILLER_UNKNOWN, "§r§7                      §r%place% §r§7- §r§cN/A§r", new String[] { "place" }));
/*      */ 
/*      */     
/*  375 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_LOBBY_PARTY_INVITE, "§r§6Party §r§8%skip% §r§fВы отправили приглашение игроку §r§e%player%§r", new String[] { "skip", "player" }));
/*      */     
/*  377 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_LOBBY_PARTY_INVITE_REJECTED, "§r§6Party §r§8%skip% §r§cВаше приглашение в пати игрока §r§c§l%player% §r§cистекло§r", new String[] { "skip", "player" }));
/*      */     
/*  379 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_LOBBY_PARTY_WARP, "§r§6Party §r§8%skip% §r§cВы отправили запрос на перемещение игроков из пати к вам§r", new String[] { "skip", "" }));
/*      */     
/*  381 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_LOBBY_PARTY_PLAYER_OFFLINE, "§r§6Party §r§8%skip% §r§cИгрок §r§c§l%player%§r§c кикнут из пати поскольку он оффлайн %reason%§r", new String[] { "skip", "player", "reason" }));
/*      */     
/*  383 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_LOBBY_PARTY_DISBANDED, "§r§6Party §r§8%skip% §r§cПати расформировано.§r", new String[] { "skip", "" }));
/*      */     
/*  385 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_LOBBY_STATS_PLAYER, "§r§e§lСтатистика §r§8%skip% §r§f%player%§r", new String[] { "skip", "player" }));
/*      */     
/*  387 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_LOBBY_STATS_CATEGORY, "§r§e%skip% §r§f%category%: §r§e%cnt% §r§a(Твое место %place%)§r", new String[] { "skip", "category", "cnt", "place" }));
/*      */ 
/*      */ 
/*      */     
/*  391 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_LOBBY_PARTY_ALREADY_IN_PARTY, "§r§6Party §r§8%skip% §r§cИгрок уже состоит в пати!§r", new String[] { "skip", "" }));
/*      */     
/*  393 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_LOBBY_PARTY_OFFLINE, "§r§6Party §r§8%skip% §r§fИгрок с таким ником не в сети§r", new String[] { "skip", "" }));
/*      */     
/*  395 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_LOBBY_PARTY_YOU_ARE_NOT_IN_PARTY, "§r§6Party §r§8%skip% §r§cВы не состоите в пати§r", new String[] { "skip", "" }));
/*      */     
/*  397 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_LOBBY_PARTY_PLAYER_KICKED, "§r§6Party §r§8%skip% §r§fИгрок §r§e%player%§r§f был кикнут из пати§r", new String[] { "skip", "player" }));
/*      */     
/*  399 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_LOBBY_PARTY_PLAYER_LEFT, "§r§6Party §r§8%skip% §r§fИгрок §r§e%player%§r§f вышел из пати§r", new String[] { "skip", "player" }));
/*      */     
/*  401 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_LOBBY_PARTY_CHAT_ENTER_MESSAGE, "§r§6Party §r§8%skip% §r§cУкажите свое сообщение§r", new String[] { "skip", "" }));
/*      */     
/*  403 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_LOBBY_PARTY_YOU_ACCEPTED_REQUEST, "§r§6Party §r§8%skip% §r§fВы приняли запрос!§r", new String[] { "skip", "" }));
/*      */     
/*  405 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_LOBBY_PARTY_NEW_LEADER, "§r§6Party §r§8%skip% §r§fИгрок §r§e%player% §r§fстал новым создателем пати§r", new String[] { "skip", "player" }));
/*      */     
/*  407 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_LOBBY_PARTY_COMMANDS_DONT_WORK_IN_LOBBY, "§r§6Party §r§8%skip% §r§fКоманды пати работают только в бедрварс лобби/игре.§r", new String[] { "skip", "" }));
/*      */     
/*  409 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_LOBBY_PARTY_CHAT_MESSAGE, "§r§6Party §r§8%skip% §r§e%player% §r§fпишет:§r§7 %message%§r", new String[] { "skip", "player", "message" }));
/*      */     
/*  411 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_LOBBY_PARTY_NO_PERMISSION, "§r§6Party §r§8%skip% §r§cНа это есть права только у создателя пати§r", new String[] { "skip" }));
/*      */     
/*  413 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_LOBBY_PARTY_DISBAND_REQUEST, "§r§6Party §r§8%skip% §r§cСоздатель пати %player% запросил расформирование!§r", new String[] { "skip", "player" }));
/*      */     
/*  415 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_LOBBY_PARTY_NOT_ENOUGH_SPACE, "§r§6Party §r§8%skip% §r§cНа этой арене не хватает слотов для всего пати, выберите другую арену§r", new String[] { "skip" }));
/*      */     
/*  417 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_LOBBY_PARTY_REQUEST_ALREADY_SENT, "§r§6Party §r§8%skip% §r§cЭтому игроку уже отправили запрос, подождите§r", new String[] { "skip" }));
/*      */     
/*  419 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_LOBBY_PARTY_OWNER_LEFT, "§r§6Party §r§8%skip% §r§cСоздатель пати %player% покинул его, пати сейчас будет расфомировано!§r", new String[] { "skip", "player" }));
/*      */     
/*  421 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_LOBBY_PARTY_REQUEST, "§eНажмите сюда §fчтобы присоединиться! У вас есть", new String[] { "" }));
/*      */     
/*  423 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_LOBBY_PARTY_YOU_WERE_WARPED, "§r§6Party §r§8%skip% §r§cВас переместил к себе создатель пати§r", new String[] { "skip" }));
/*      */     
/*  425 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_LOBBY_PARTY_INFO, "▸ Информация о пати", new String[] { "" }));
/*      */ 
/*      */     
/*  428 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_YOU_ARE_TELEPORTED_TO_LOBBY_DUE_TO_FULL_ARENA, "§r§c§l| §r§fТы перемещен в лобби §r§8%skip% §r§cАрена заполнена.§r", new String[] { "skip", "" }));
/*      */     
/*  430 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAY_YOU_ALREADY_ON_SERVER, "§cВы уже на сервере!§r", new String[] { "", "" }));
/*      */     
/*  432 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_PARTY_ON_CREATE, "Используйте §r§e/party ник §r§fчтобы пригласить новых игроков!", new String[] { "", "" }));
/*      */     
/*  434 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_GAME_CANT_USE_COMMANDS, "§r§cНельзя использовать такую команду в игре, для выхода пишите /leave§r", new String[] { "", "" }));
/*      */     
/*  436 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_GAME_WAIT_SECONDS, "§r§c§l| §r§fПожайлуста подождите §r§c%seconds% секунд §r§fсек.§r", new String[] { "seconds", "" }));
/*      */     
/*  438 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_GAME_YOU_CANT_USE_COLORS, "У тебя нет прав использовать цвета в чате", new String[] { "", "" }));
/*      */     
/*  440 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_GAME_YOU_CANT_USE_COLOR, "Вы не можете использовать цвет", new String[] { "", "" }));
/*      */     
/*  442 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_GAME_ANTI_CHEAT_KICK, "§r§6АнтиЧит§r§8 %skip% §rИгрок §r§c%player% §r§fбыл §r§cкикнут §r§fпо подозрению в использовании читов.§r", new String[] { "skip", "player" }));
/*      */     
/*  444 */     chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_HUB_ANTIFLOOD, "§r§6АнтиФлуд§r§8 %skip% §rИгрок §r§c%player% §r§fбыл §r§cзамучен §r§fс причиной: '§r§cПовторение однотипных сообщений§r§f'§r", new String[] { "skip", "player" }));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  449 */     String[] chatMessagesGameStarted = { "§r§f                           §r§c§lКРОВАТНЫЕ ВОЙНЫ§r", "§r§c§l  §r§f                          §r", "§r§e          §r§eПокупай предметы и улучшения для своей команды за§r", "§r§e        §r§eЖелезо, Золото, Алмазы и Изумруды чтобы стать несокрушимыми!§r", "§r§e                 §r§eПобедит только одна, сильнейшая команда!§r", "§r§e  §r§f                §r§a   Вы играете на §r§amc.MineBlaze.ru§r", "§r§e  §r§f                §r§a   Вы играете на §r§amc.DexLand.ru§r" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  458 */     for (String s : chatMessagesGameStarted) { chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_BEDWARS_GAME_STARTED_TIPS, s, new String[] { "" }, true)); }
/*      */ 
/*      */     
/*  461 */     String[] chatMessagesAds = { "Сайт для совершения покупок:", "Привилегии [GOLD]", "Доступно: /sit", "Хотите купить донат, но сомневаетесь?", "Чтобы посмотреть свою или чужую статистику", "Хотите изменить свой ник?", "Хотите быть первым вкурсе всех новостей сервера?", "Хотите выиграть Айфон 11 PRO?", "Помогите нам улучшить сервер", "Хотите получить все команды сервера и опку?", "Покупка кейса производится на сайте", "Не знаете какие возможности есть у доната?", "ВКонтакте - ", "Для чего это нужно (Наведи мышкой)", "Вы случайно вышли из игры и хотите вернуться?", "Чтобы посмотреть топы по всем категориям", "Успейте купить донат по дешевым ценам", "Хочешь купить донат, но сомневаешься?", "Не знаешь какие возможности есть у доната?", "Но ты не готов тратить много денег?", "Хотел бы снимать антигрифер шоу?", "Друзья хвастаются донатом? А ты никто?", "Хочешь купить донат, но не доверяешь?", "Сайт для покупки доната", "Донат покупать только на сайте", "Только сегодня у тебя есть возможность купить донат", "Хочешь получить 85% команд ОЧЕНЬ дешево", "Хочешь быть в курсе новостей сервера?", "Купить донат на нашем сайте можно через:", "На нашем сайте есть оплата с мобильного телефона", "Тебе нужен доступ в любой приват?", "Хочешь показать всем кто тут главный?", "Купить донат на нашем сайте можно через:", "Помогите нам улучшить сервер", "У нас появился новый донат", "Все донат-привилегии и кейсы", "Важно для вашей безопасности" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  502 */     for (String s : chatMessagesAds) { chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_ADS, s, new String[] { "" })); }
/*      */     
/*  504 */     ArrayList<ChatMessage> temp = new ArrayList<ChatMessage>();
/*  505 */     for (ChatMessage m : chatMessages) {
/*  506 */       temp.add(m);
/*      */     }
/*  508 */     chatMessages = new ArrayList<ChatMessage>();
/*  509 */     for (ChatMessage m : temp) {
/*  510 */       if (m.type != CHAT_MESSAGE.NONE) chatMessages.add(m); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static ChatMessage findChatMessage(String str) {
/*  515 */     ChatMessage instance = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  520 */     for (ChatMessage chatMessage : chatMessages) {
/*  521 */       if (chatMessage.elements.length == 0) {
/*  522 */         String message_text = str;
/*  523 */         if (chatMessage.type == CHAT_MESSAGE.CHAT_ADS) message_text = ColorCodesManager.removeColorCodes(message_text); 
/*  524 */         if (chatMessage.isMustBeEqual == true) {
/*  525 */           if (message_text.equals(chatMessage.message))
/*  526 */             return chatMessage; 
/*      */           continue;
/*      */         } 
/*  529 */         if (message_text.contains(chatMessage.message)) {
/*  530 */           return chatMessage;
/*      */         }
/*      */         
/*      */         continue;
/*      */       } 
/*  535 */       ArrayList<String> parts = new ArrayList<String>();
/*      */       
/*  537 */       String s = chatMessage.message;
/*      */       try {
/*  539 */         for (int i = 0; i < chatMessage.elements.length; i++) {
/*  540 */           String part = s.split(Pattern.quote(chatMessage.elements[i]))[0];
/*  541 */           parts.add(part);
/*  542 */           s = s.split(chatMessage.elements[i])[1];
/*  543 */           if (i == chatMessage.elements.length - 1) parts.add(s); 
/*      */         } 
/*  545 */       } catch (Exception ex) {
/*  546 */         ChatSender.addText("ERROR with &a" + chatMessage.type);
/*      */       } 
/*      */       
/*  549 */       boolean isFound = true;
/*  550 */       for (String part : parts) {
/*  551 */         if (!str.contains(part)) {
/*  552 */           isFound = false;
/*      */ 
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  560 */       if (!isFound || parts.size() < 2) {
/*      */         continue;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  567 */         String line = str;
/*  568 */         for (int i = 0; i < parts.size() - 1; i++) {
/*      */           
/*  570 */           line = line.replaceFirst(Pattern.quote(parts.get(i)), "");
/*  571 */           String val = line.split(Pattern.quote((String)parts.get(i + 1)))[0].trim();
/*  572 */           line = line.replaceFirst(Pattern.quote(val), "");
/*      */           
/*  574 */           chatMessage.element_values[i] = val;
/*      */         } 
/*  576 */         return chatMessage;
/*  577 */       } catch (Exception ex) {
/*      */ 
/*      */ 
/*      */         
/*  581 */         if (chatMessage.type == CHAT_MESSAGE.CHAT_GAME_CHAT_GLOBAL) {
/*  582 */           chatMessage.element_values[chatMessage.element_values.length - 1] = "";
/*  583 */           return chatMessage;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  590 */     return null;
/*      */   }
/*      */   
/*      */   @SubscribeEvent
/*      */   public void onJoinServer(FMLNetworkEvent.ClientConnectedToServerEvent e) {
/*  595 */     Main.updateAllBooleans();
/*  596 */     (new Timer()).schedule(new TimerTask()
/*      */         {
/*      */           public void run()
/*      */           {
/*  600 */             if (Minecraft.func_71410_x() == null || (Minecraft.func_71410_x()).field_71456_v == null || (Minecraft.func_71410_x()).field_71456_v.func_146158_b() == null)
/*  601 */               return;  (Minecraft.func_71410_x()).field_71456_v.func_146158_b().func_146231_a();
/*      */           }
/*      */         },  200L);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @SubscribeEvent
/*      */   public void onChat(ClientChatReceivedEvent e) {
/*  612 */     if (Minecraft.func_71410_x() == null)
/*  613 */       return;  if ((Minecraft.func_71410_x()).field_71439_g == null)
/*      */       return; 
/*  615 */     String login_event = "tick";
/*  616 */     if (IS_IN_GAME == true) login_event = "in_game"; 
/*  617 */     Main.loginHandler.handleInGame(login_event);
/*      */     
/*  619 */     if ((Minecraft.func_71410_x()).field_71441_e != null && (Minecraft.func_71410_x()).field_71441_e.field_72995_K) {
/*  620 */       Main.sameModePlayers.handle();
/*      */     }
/*      */ 
/*      */     
/*  624 */     String str = e.message.func_150254_d();
/*  625 */     ChatMessage instance = findChatMessage(str);
/*      */     
/*  627 */     if (instance == null) {
/*      */       
/*  629 */       if (str.contains("Вы упоминаете вредоносный мод!")) {
/*  630 */         setMessageText(e, "&cМод &c&lBedwars&f&lBro &cблокирует всю рекламу на сервере. Поэтому админы хуесосы запретили говрить слова BedwarsBro, и DimChig");
/*      */       }
/*      */       
/*  633 */       if (e.message.func_150260_c().length() == 0) e.message = null;
/*      */       
/*      */       return;
/*      */     } 
/*  637 */     handleReceivedMessage(e, instance);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void sendDelayedGameStats() {
/*  642 */     (new Timer()).schedule(new TimerTask()
/*      */         {
/*      */           public void run()
/*      */           {
/*  646 */             if (MyChatListener.GAME_start_time > 0L) {
/*      */               
/*  648 */               String seconds = "" + (int)(((new Date()).getTime() - MyChatListener.GAME_start_time) / 1000L % 60L);
/*  649 */               if (seconds.length() == 1) seconds = "0" + seconds; 
/*  650 */               int minutes = (int)(((new Date()).getTime() - MyChatListener.GAME_start_time) / 1000L / 60L);
/*      */               
/*  652 */               ChatSender.addText("");
/*  653 */               ChatSender.addText(" &fВремя &8▸ &c" + minutes + ":" + seconds);
/*      */               
/*  655 */               ChatSender.addText(" &fКилов &8▸ &6" + MyChatListener.GAME_total_kills);
/*  656 */               ChatSender.addText(" &fСмертей &8▸ &e" + MyChatListener.GAME_total_death);
/*  657 */               String kdr_string = "100%";
/*  658 */               if (MyChatListener.GAME_total_death > 0) {
/*  659 */                 float kdr = (int)(MyChatListener.GAME_total_kills / MyChatListener.GAME_total_death * 100.0F) / 100.0F;
/*  660 */                 kdr_string = "" + kdr;
/*      */               } 
/*  662 */               ChatSender.addText(" &fK/D &8▸ &a" + kdr_string);
/*      */               
/*  664 */               ChatSender.addText(" &fКроватей &8▸ &b" + MyChatListener.GAME_total_beds);
/*  665 */               ChatSender.addText("");
/*  666 */               MyChatListener.anullateGameStats();
/*      */             } 
/*      */           }
/*      */         },  500L);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static void anullateGameStats() {
/*  675 */     GAME_start_time = -1L;
/*  676 */     GAME_total_kills = -1;
/*  677 */     GAME_total_death = -1;
/*  678 */     GAME_total_beds = -1;
/*      */   }
/*      */   
/*      */   static void initGameStats() {
/*  682 */     GAME_start_time = (new Date()).getTime();
/*  683 */     GAME_total_kills = 0;
/*  684 */     GAME_total_death = 0;
/*  685 */     GAME_total_beds = 0;
/*      */     
/*  687 */     Main.generatorTimers.setStartTimesOnGameStart();
/*      */   }
/*      */   
/*      */   public static void updateScoreboard() {
/*  691 */     CustomScoreboard.updateScoreboard();
/*      */   }
/*      */ 
/*      */   
/*      */   public static String replaceColorCodes(String text) {
/*  696 */     return ColorCodesManager.replaceColorCodesInString(text);
/*      */   }
/*      */   
/*      */   public static void setMessageText(ClientChatReceivedEvent e, String text) {
/*  700 */     if (!IS_MOD_ACTIVE)
/*  701 */       return;  e.message = (IChatComponent)new ChatComponentText(replaceColorCodes(text));
/*      */   }
/*      */   
/*      */   public static void deleteMessage(ClientChatReceivedEvent e) {
/*  705 */     e.message = null;
/*      */   }
/*      */   
/*      */   public static String formatChatPlayerName(IChatComponent message, String player_name, String player_color) {
/*  709 */     return formatChatPlayerName(message, player_name, player_color, false);
/*      */   }
/*      */   
/*      */   public static String formatChatPlayerName(IChatComponent message, String player_name, String player_color, boolean removeColor) {
/*  713 */     player_name = player_name.trim();
/*  714 */     if (player_name.contains(" "))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  724 */       player_name = player_name.split(" ")[1].trim();
/*      */     }
/*      */ 
/*      */     
/*  728 */     while (player_name.charAt(0) == '&') {
/*  729 */       player_name = player_name.substring(2);
/*      */     }
/*      */ 
/*      */     
/*  733 */     String hoverText = getHoverMessage(message);
/*  734 */     if (hoverText.length() > 0 && (hoverText.split(" ")).length == 2 && hoverText.contains("Ник:")) {
/*  735 */       String hoverName = hoverText.split(" ")[1].trim();
/*  736 */       if (!hoverName.equals(player_name)) player_name = hoverName + "*";
/*      */     
/*      */     } 
/*  739 */     if (removeColor == true) {
/*  740 */       player_name = ColorCodesManager.removeColorCodes(player_name);
/*      */     }
/*      */ 
/*      */     
/*  744 */     if (player_name.endsWith("YT")) {
/*  745 */       player_name = "&c[Y&fT] " + player_color + player_name.replace("~", "");
/*      */     }
/*      */     
/*  748 */     return player_name;
/*      */   }
/*      */   
/*      */   public static String getTeamBoldName(String team_name) {
/*  752 */     return team_name.substring(0, 2) + "&l" + team_name.substring(2);
/*      */   }
/*      */   
/*      */   public static String getTeamColor(String team_name) {
/*  756 */     return team_name.substring(0, 2);
/*      */   }
/*      */   
/*      */   public static String getGeneratorDiamondUpgradeTime(String upgrade) {
/*  760 */     int[] levels = { 30, 23, 12 };
/*      */     
/*  762 */     int current_level = -1;
/*  763 */     if (upgrade.contains("II")) { current_level = 2; }
/*  764 */     else if (upgrade.contains("I")) { current_level = 1; }
/*      */     
/*  766 */     if (current_level == -1) {
/*  767 */       return "?";
/*      */     }
/*      */     
/*  770 */     return "" + levels[current_level];
/*      */   }
/*      */   
/*      */   public static String getGeneratorEmeraldUpgradeTime(String upgrade) {
/*  774 */     int[] levels = { 65, 50, 35 };
/*      */     
/*  776 */     int current_level = -1;
/*  777 */     if (upgrade.contains("II")) { current_level = 2; }
/*  778 */     else if (upgrade.contains("I")) { current_level = 1; }
/*      */     
/*  780 */     if (current_level == -1) {
/*  781 */       return "?";
/*      */     }
/*      */     
/*  784 */     return "" + levels[current_level];
/*      */   }
/*      */   
/*      */   public static String upperCaseFirstLetter(String text) {
/*  788 */     if (text.length() <= 1) return text; 
/*  789 */     return text.substring(0, 1).toUpperCase() + text.substring(1);
/*      */   }
/*      */   
/*      */   public static String getTopKillerPlace(String text) {
/*  793 */     String place = "§cN/A";
/*  794 */     if (text.contains("1")) {
/*  795 */       place = "&e#1";
/*  796 */     } else if (text.contains("2")) {
/*  797 */       place = "&f#2";
/*  798 */     } else if (text.contains("3")) {
/*  799 */       place = "&6#3";
/*      */     } 
/*  801 */     return place;
/*      */   }
/*      */   
/*      */   public static String getStatsCategoryName(String text) {
/*  805 */     String category = "§cN/A";
/*  806 */     if (text.contains("Убийств")) {
/*  807 */       category = "&aКилов";
/*  808 */     } else if (text.contains("Смертей")) {
/*  809 */       category = "&cСмертей";
/*  810 */     } else if (text.contains("Игр")) {
/*  811 */       category = "&bКаток";
/*  812 */     } else if (text.contains("Побед")) {
/*  813 */       category = "&eПобед";
/*  814 */     } else if (text.contains("Сломано кроватей")) {
/*  815 */       category = "&dКроватей";
/*      */     } 
/*  817 */     return category;
/*      */   }
/*      */   
/*      */   public static String highLightExtras(String text) {
/*  821 */     text = highLightDiscord(text);
/*  822 */     if (text.charAt(0) == '!') text = text.substring(1);
/*      */     
/*  824 */     assert Minecraft.func_71410_x() == null || (Minecraft.func_71410_x()).field_71439_g == null;
/*  825 */     String player_name = (Minecraft.func_71410_x()).field_71439_g.func_70005_c_();
/*      */     
/*  827 */     if (ColorCodesManager.removeColorCodes(text).contains(player_name)) {
/*  828 */       playSound(SOUND_PLAYER_STATS);
/*      */     }
/*  830 */     return text;
/*      */   }
/*      */   
/*      */   public static String getDiscordFromString(String text) {
/*  834 */     if (text.contains("#")) {
/*      */       
/*  836 */       String[] split = text.split(Pattern.quote("#"));
/*  837 */       if (split.length < 2) return null; 
/*      */       try {
/*  839 */         String nickname = split[0].split(" ")[(split[0].split(" ")).length - 1].trim();
/*  840 */         String hash = split[1].split(" ")[0].substring(0, 4).trim();
/*  841 */         if (hash.length() != 4) return text; 
/*  842 */         int hash_value = Integer.parseInt(hash);
/*  843 */         String discord = nickname + "#" + hash;
/*  844 */         return ColorCodesManager.removeColorCodes(discord);
/*  845 */       } catch (Exception ex) {
/*  846 */         return null;
/*      */       } 
/*      */     } 
/*  849 */     return null;
/*      */   }
/*      */   
/*      */   public static String highLightDiscord(String text) {
/*  853 */     String color = "&9";
/*  854 */     text = text.replace(" дс ", color + " дс &f").replace(" Дс ", color + " Дс &f").replace(" ДС ", color + " ДС &f");
/*      */     
/*  856 */     String discord = getDiscordFromString(text);
/*  857 */     if (discord != null) text = text.replace(discord, color + "" + discord + "&f");
/*      */     
/*  859 */     return text;
/*      */   }
/*      */   
/*      */   public static void playSound(String name) {
/*  863 */     playSound(name, 1.0F);
/*      */   }
/*      */   
/*      */   public static void playSound(String name, float volume) {
/*  867 */     assert Minecraft.func_71410_x() == null || (Minecraft.func_71410_x()).field_71439_g == null;
/*  868 */     (Minecraft.func_71410_x()).field_71439_g.func_85030_a(name, volume, 1.0F);
/*      */   }
/*      */   
/*      */   public static boolean isItFinalKill(String player_name) {
/*  872 */     String name = ColorCodesManager.removeColorCodes(player_name).trim();
/*  873 */     List<CustomScoreboard.BedwarsTeam> teams = CustomScoreboard.readBedwarsGame();
/*  874 */     for (CustomScoreboard.BedwarsTeam t : teams) {
/*  875 */       for (CustomScoreboard.BedwarsPlayer p : t.players) {
/*  876 */         if (p.name.equals(name)) {
/*  877 */           if (t.state != CustomScoreboard.TEAM_STATE.BED_ALIVE) {
/*  878 */             return true;
/*      */           }
/*  880 */           return false;
/*      */         } 
/*      */       } 
/*      */     } 
/*  884 */     return false;
/*      */   }
/*      */   
/*      */   public static void clearGame() {
/*  888 */     GAME_BED = null;
/*  889 */     IS_IN_GAME = false;
/*  890 */     Main.minimap.clearGameBeds();
/*      */   }
/*      */   
/*      */   public static void readPlayerBase() {
/*  894 */     int delay = 2000;
/*  895 */     (new Timer()).schedule(new TimerTask()
/*      */         {
/*      */           public void run()
/*      */           {
/*  899 */             if (Minecraft.func_71410_x() == null || (Minecraft.func_71410_x()).field_71439_g == null)
/*      */               return; 
/*  901 */             (Minecraft.func_71410_x()).field_71439_g.refreshDisplayName();
/*      */             
/*  903 */             Main.namePlateRenderer.printSameUsersInGame();
/*      */             
/*  905 */             EntityPlayerSP entityPlayerSP = (Minecraft.func_71410_x()).field_71439_g;
/*  906 */             ArrayList<BWBed> beds = HintsBedScanner.findBeds(entityPlayerSP.func_180425_c().func_177958_n(), entityPlayerSP.func_180425_c().func_177956_o(), entityPlayerSP.func_180425_c().func_177952_p());
/*  907 */             if (beds.size() == 0) {
/*  908 */               ChatSender.addText(MyChatListener.PREFIX_HINT + "&cБаза не найдена!");
/*      */             } else {
/*      */               
/*  911 */               MyChatListener.GAME_BED = null;
/*  912 */               int min_dist = 999999;
/*  913 */               for (BWBed b : beds) {
/*  914 */                 int dist = (int)Math.sqrt(Math.pow(b.part1_posX - ((EntityPlayer)entityPlayerSP).field_70165_t, 2.0D) + Math.pow(b.part1_posZ - ((EntityPlayer)entityPlayerSP).field_70161_v, 2.0D));
/*  915 */                 if (dist < min_dist) {
/*  916 */                   min_dist = dist;
/*  917 */                   MyChatListener.GAME_BED = b;
/*      */                 } 
/*      */               } 
/*      */               
/*  921 */               if (MyChatListener.GAME_BED == null) {
/*  922 */                 ChatSender.addText(MyChatListener.PREFIX_HINT + "&cБаза не найдена, ошибка!");
/*      */ 
/*      */                 
/*      */                 return;
/*      */               } 
/*      */ 
/*      */               
/*  929 */               Main.hintsBaseRadar.setBase(MyChatListener.GAME_BED.part1_posX, MyChatListener.GAME_BED.part1_posY, MyChatListener.GAME_BED.part1_posZ);
/*      */             } 
/*      */           }
/*      */         }delay);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static String handleTeamDestruction(String player_name) {
/*      */     try {
/*  939 */       String player_team_color = player_name.substring(0, 2);
/*  940 */       CustomScoreboard.TEAM_COLOR team_color = CustomScoreboard.getTeamColorByCode(player_team_color);
/*  941 */       List<CustomScoreboard.BedwarsTeam> teams = CustomScoreboard.readBedwarsGame();
/*  942 */       for (CustomScoreboard.BedwarsTeam t : teams) {
/*  943 */         if (t.color == team_color && (
/*  944 */           t.state == CustomScoreboard.TEAM_STATE.DESTROYED || (t.state == CustomScoreboard.TEAM_STATE.BED_BROKEN && t.players.size() == 1))) {
/*  945 */           playSound(SOUND_TEAM_DESTROYED);
/*  946 */           return "\n" + PREFIX_TEAM + player_team_color + "Уничтожены &l" + CustomScoreboard.getTeamNameByTeamColor(team_color) + "";
/*      */         }
/*      */       
/*      */       } 
/*  950 */     } catch (Exception ex) {
/*  951 */       ex.printStackTrace();
/*      */     } 
/*  953 */     return "";
/*      */   }
/*      */   
/*      */   public static boolean isUserMuted(String player_name) {
/*  957 */     if (Main.getConfigString(Main.CONFIG_MSG.MUTED_PLAYERS) == null) return false; 
/*  958 */     String[] muted_players = Main.getConfigString(Main.CONFIG_MSG.MUTED_PLAYERS).split(",");
/*  959 */     for (String p : muted_players) {
/*  960 */       if (p.length() > 1 && p.equals(player_name)) {
/*  961 */         return true;
/*      */       }
/*      */     } 
/*  964 */     return false;
/*      */   }
/*      */   
/*      */   public static String getStrikeMessageVictim(String text) {
/*  968 */     return text.substring(0, 2) + "&m" + text.substring(2);
/*      */   }
/*      */   
/*      */   public static String getNumberEnding(int cnt, String case1, String case2, String case3) {
/*  972 */     String e = case3;
/*      */     
/*  974 */     char last_num = Integer.toString(cnt).charAt(Integer.toString(cnt).length() - 1);
/*  975 */     switch (Integer.parseInt(Character.toString(last_num))) {
/*      */       case 1:
/*  977 */         e = case1;
/*      */         break;
/*      */       case 2:
/*      */       case 3:
/*      */       case 4:
/*  982 */         e = case2;
/*      */         break;
/*      */       case 0:
/*      */       case 5:
/*      */       case 6:
/*      */       case 7:
/*      */       case 8:
/*      */       case 9:
/*  990 */         e = case3;
/*      */         break;
/*      */     } 
/*      */     
/*  994 */     if (cnt >= 11 && cnt <= 14) {
/*  995 */       e = case3;
/*      */     }
/*  997 */     return e;
/*      */   }
/*      */   
/*      */   public static void addBedwarsMeowMessageToQuee(String s, boolean isInGameOnly) {
/* 1001 */     if (meowMessagesQuee == null) meowMessagesQuee = new ArrayList<MsgMeowQuee>(); 
/* 1002 */     meowMessagesQuee.add(new MsgMeowQuee(s, (new Date()).getTime(), isInGameOnly));
/*      */   }
/*      */ 
/*      */   
/* 1006 */   private static long time_last_meow_message = -1L;
/* 1007 */   private static MsgMeowQuee quee_last_meow_message = null;
/*      */   
/*      */   static void handleBedwarsMeowMessagesQuee() {
/* 1010 */     if (meowMessagesQuee.size() <= 0)
/*      */       return; 
/* 1012 */     long t = (new Date()).getTime();
/* 1013 */     if (time_last_meow_message != -1L && t - time_last_meow_message < TIME_MEOW_MESSAGES_CHECK_FREQUENCY) {
/*      */       return;
/*      */     }
/*      */     
/* 1017 */     time_last_meow_message = t;
/*      */     
/* 1019 */     MsgMeowQuee m = meowMessagesQuee.get(0);
/* 1020 */     if (quee_last_meow_message == null) {
/* 1021 */       quee_last_meow_message = m;
/* 1022 */       quee_last_meow_message.time = t;
/*      */     } 
/*      */     
/* 1025 */     if (t - quee_last_meow_message.time > 16000L) {
/* 1026 */       ChatSender.addText(PREFIX_BEDWARS_MEOW + "&cВ СООБЩЕНИИ ЗАПРЕЩЕННЫЕ СЛОВА:\n &7- &f" + quee_last_meow_message.text);
/* 1027 */       meowMessagesQuee.remove(quee_last_meow_message);
/* 1028 */       quee_last_meow_message = null;
/*      */       
/*      */       return;
/*      */     } 
/* 1032 */     if (m.isInGameOnly && !IS_IN_GAME) {
/* 1033 */       meowMessagesQuee.remove(quee_last_meow_message);
/* 1034 */       quee_last_meow_message = null;
/*      */       
/*      */       return;
/*      */     } 
/* 1038 */     ChatSender.sendText("!" + m.text);
/*      */   }
/*      */   
/*      */   static void checkBedwarsMeowMessagesQueelastMessage(String text) {
/* 1042 */     if (meowMessagesQuee.size() <= 0 || quee_last_meow_message == null)
/*      */       return; 
/* 1044 */     if (ColorCodesManager.removeColorCodes(text).contains(ColorCodesManager.removeColorCodes(quee_last_meow_message.text))) {
/* 1045 */       meowMessagesQuee.remove(quee_last_meow_message);
/* 1046 */       quee_last_meow_message = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   static void handleBedwarsMeowKill(ClientChatReceivedEvent e, String p_killer, String p_victim) {
/* 1051 */     handleBedwarsMeowKillType(e, p_killer, p_victim, BedwarsMeow.MsgCase.KILL);
/*      */   }
/*      */   static void handleBedwarsMeowFinalKill(ClientChatReceivedEvent e, String p_killer, String p_victim) {
/* 1054 */     handleBedwarsMeowKillType(e, p_killer, p_victim, BedwarsMeow.MsgCase.FINAL_KILL);
/*      */   }
/*      */   static void handleBedwarsMeowKillType(ClientChatReceivedEvent e, String p_killer, String p_victim, BedwarsMeow.MsgCase msgcase) {
/* 1057 */     String killer = ColorCodesManager.removeColorCodes(p_killer).trim();
/* 1058 */     String victim = ColorCodesManager.removeColorCodes(p_victim).trim();
/*      */     
/* 1060 */     String mod_player_name = (Minecraft.func_71410_x()).field_71439_g.func_70005_c_();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1067 */     if (msgcase == BedwarsMeow.MsgCase.KILL && mod_player_name.equals(victim)) {
/* 1068 */       GAME_total_death++;
/* 1069 */       String str = Main.bedwarsMeow.getNextMessage(BedwarsMeow.MsgCase.DEATH, p_killer);
/* 1070 */       if (str == null)
/* 1071 */         return;  addBedwarsMeowMessageToQuee(str, true);
/*      */     } 
/*      */ 
/*      */     
/* 1075 */     if (!mod_player_name.equals(killer))
/* 1076 */       return;  GAME_total_kills++;
/* 1077 */     String s = Main.bedwarsMeow.getNextMessage(msgcase, p_victim);
/* 1078 */     if (s == null)
/* 1079 */       return;  addBedwarsMeowMessageToQuee(s, true);
/*      */   }
/*      */ 
/*      */   
/*      */   static void handleBedwarsMeowBed(CustomScoreboard.TEAM_COLOR team_color, String p_player) {
/* 1084 */     String player = ColorCodesManager.removeColorCodes(p_player).trim();
/* 1085 */     if (!(Minecraft.func_71410_x()).field_71439_g.func_70005_c_().equals(player))
/*      */       return; 
/* 1087 */     GAME_total_beds++;
/*      */     
/* 1089 */     boolean isSingleMode = false;
/* 1090 */     List<CustomScoreboard.BedwarsTeam> teams = CustomScoreboard.readBedwarsGame();
/* 1091 */     for (CustomScoreboard.BedwarsTeam t : teams) {
/* 1092 */       if (t.color == team_color) {
/* 1093 */         if (t.players.size() <= 1) isSingleMode = true;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/* 1098 */     BedwarsMeow.MsgCase msgcase = BedwarsMeow.MsgCase.BED_MULTI;
/* 1099 */     if (isSingleMode) msgcase = BedwarsMeow.MsgCase.BED_SINGLE;
/*      */     
/* 1101 */     String s = Main.bedwarsMeow.getNextMessage(msgcase, CustomScoreboard.getCodeByTeamColor(team_color));
/* 1102 */     if (s == null)
/* 1103 */       return;  addBedwarsMeowMessageToQuee(s, true);
/*      */   }
/*      */   
/*      */   static void handleBedwarsMeowWin(String p_team_name) {
/* 1107 */     CustomScoreboard.TEAM_COLOR team_color = CustomScoreboard.getTeamColorByName(p_team_name);
/* 1108 */     CustomScoreboard.TEAM_COLOR mod_team_color = getEntityTeamColor((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g);
/*      */     
/* 1110 */     if (mod_team_color == team_color)
/*      */       return; 
/* 1112 */     String s = Main.bedwarsMeow.getNextMessage(BedwarsMeow.MsgCase.WIN, "");
/* 1113 */     if (s == null)
/* 1114 */       return;  addBedwarsMeowMessageToQuee(s, true);
/*      */   }
/*      */ 
/*      */   
/*      */   static void handleBedwarsMeowGameGreeting() {
/* 1119 */     (new Timer()).schedule(new TimerTask()
/*      */         {
/*      */           public void run()
/*      */           {
/* 1123 */             String s = Main.bedwarsMeow.getNextMessage(BedwarsMeow.MsgCase.GAME_START, "");
/* 1124 */             if (s == null)
/* 1125 */               return;  MyChatListener.addBedwarsMeowMessageToQuee(s, true);
/*      */           }
/*      */         }500L);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void handleReceivedMessage(ClientChatReceivedEvent e, ChatMessage chatMessage) {
/*      */     String victim, new_time, player_name, text, message_hover_text, n, name, p_name, place, player, this_text, split[], category, placeColor;
/*      */     int cnt, place_cnt;
/* 1134 */     String str = e.message.func_150254_d();
/* 1135 */     ChatMessage msg = chatMessage;
/* 1136 */     String[] vals = msg.element_values;
/*      */ 
/*      */ 
/*      */     
/* 1140 */     checkBedwarsMeowMessagesQueelastMessage(e.message.func_150260_c());
/*      */     
/* 1142 */     if (removeNextMessage == true) {
/* 1143 */       removeNextMessage = false;
/* 1144 */       deleteMessage(e);
/*      */       
/*      */       return;
/*      */     } 
/* 1148 */     switch (msg.type) {
/*      */ 
/*      */       
/*      */       case CHAT_LEFT_GAME:
/* 1152 */         victim = vals[1];
/* 1153 */         if (isItFinalKill(victim)) {
/* 1154 */           str = getStrikeMessageVictim(victim) + "&r &7ливнул";
/* 1155 */           str = str + handleTeamDestruction(victim);
/*      */         } else {
/* 1157 */           str = victim + " &r&7ливнул";
/*      */         } 
/* 1159 */         setMessageText(e, str);
/* 1160 */         updateScoreboard();
/*      */         break;
/*      */       case CHAT_GAME_STARTED:
/* 1163 */         str = "§r§f                            &c&lB&6&le&e&ld&a&lW&b&la&9&lr&d&ls\n";
/*      */         
/* 1165 */         FileManager.writeToFile("========", "resourceBedwarslog.txt", true);
/*      */         
/* 1167 */         if (HintsValidator.isBedScannerActive()) readPlayerBase(); 
/* 1168 */         IS_IN_GAME = true;
/*      */         
/* 1170 */         initGameStats();
/*      */         
/* 1172 */         Main.updateAllBooleans();
/*      */         
/* 1174 */         setMessageText(e, str);
/*      */         
/* 1176 */         handleBedwarsMeowGameGreeting();
/*      */         
/* 1178 */         LobbyBlockPlacer.state = false;
/*      */         break;
/*      */       
/*      */       case CHAT_BEDWARS_GAME_STARTED_TIPS:
/* 1182 */         if (IS_MOD_ACTIVE) deleteMessage(e); 
/*      */         break;
/*      */       case CHAT_JOINED_MIDGAME:
/* 1185 */         str = "§r" + vals[1] + " §r§7подключился";
/* 1186 */         setMessageText(e, str);
/* 1187 */         updateScoreboard();
/*      */         break;
/*      */       case CHAT_JOINED_PREGAME:
/* 1190 */         str = "&7" + formatChatPlayerName(e.message, vals[1], "&7") + " &r&7подключился &a" + vals[2] + "&7/&e" + vals[3];
/* 1191 */         setMessageText(e, str);
/* 1192 */         clearGame();
/*      */         break;
/*      */       case CHAT_LEFT_PREGAME:
/* 1195 */         str = "&7" + formatChatPlayerName(e.message, vals[1], "&7") + " &r&7ливнул &c" + vals[2] + "&7/&e" + vals[3];
/* 1196 */         setMessageText(e, str);
/* 1197 */         clearGame();
/*      */         break;
/*      */       case CHAT_SUICIDE:
/* 1200 */         victim = vals[1];
/* 1201 */         if (isItFinalKill(victim)) {
/* 1202 */           str = "&r&f⚔ " + getStrikeMessageVictim(victim);
/* 1203 */           str = str + handleTeamDestruction(victim);
/*      */         } else {
/* 1205 */           str = "&r&f⚔ " + victim;
/*      */           
/* 1207 */           if (ColorCodesManager.removeColorCodes(victim).trim().equals((Minecraft.func_71410_x()).field_71439_g.func_70005_c_())) GAME_total_death++; 
/*      */         } 
/* 1209 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_SUICIDE_VOID:
/* 1212 */         victim = vals[1];
/* 1213 */         if (isItFinalKill(victim)) {
/* 1214 */           str = "&r&f⚔ " + getStrikeMessageVictim(victim);
/* 1215 */           str = str + handleTeamDestruction(victim);
/*      */         } else {
/* 1217 */           str = "&r&f⚔ " + victim;
/*      */           
/* 1219 */           if (ColorCodesManager.removeColorCodes(victim).trim().equals((Minecraft.func_71410_x()).field_71439_g.func_70005_c_())) GAME_total_death++; 
/*      */         } 
/* 1221 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_KILLED_BY_VOID:
/* 1224 */         victim = vals[1];
/* 1225 */         if (isItFinalKill(victim)) {
/* 1226 */           str = "&r" + vals[2] + " &f⚔ &r" + getStrikeMessageVictim(victim);
/* 1227 */           str = str + handleTeamDestruction(victim);
/* 1228 */           setMessageText(e, str);
/*      */           
/* 1230 */           handleBedwarsMeowFinalKill(e, vals[2], victim); break;
/*      */         } 
/* 1232 */         str = "&r" + vals[2] + " &f⚔ &r" + victim;
/* 1233 */         setMessageText(e, str);
/*      */         
/* 1235 */         handleBedwarsMeowKill(e, vals[2], victim);
/*      */         break;
/*      */ 
/*      */       
/*      */       case CHAT_KILLED_BY_PLAYER:
/* 1240 */         victim = vals[1];
/* 1241 */         if (isItFinalKill(victim)) {
/* 1242 */           str = "&r" + vals[2] + " &f⚔ &r" + getStrikeMessageVictim(victim);
/* 1243 */           str = str + handleTeamDestruction(victim);
/*      */           
/* 1245 */           setMessageText(e, str);
/* 1246 */           handleBedwarsMeowFinalKill(e, vals[2], victim);
/*      */           
/* 1248 */           EntityPlayerSP entityPlayerSP = (Minecraft.func_71410_x()).field_71439_g;
/* 1249 */           if (ColorCodesManager.removeColorCodes(vals[2]).equals(entityPlayerSP.func_70005_c_())) {
/*      */ 
/*      */             
/* 1252 */             MovingObjectPosition ray = entityPlayerSP.func_174822_a(3.0D, 1.0F);
/*      */             
/* 1254 */             if (ray == null)
/* 1255 */               break;  int posX = (int)ray.field_72307_f.field_72450_a;
/* 1256 */             int posY = (int)ray.field_72307_f.field_72448_b;
/* 1257 */             int posZ = (int)ray.field_72307_f.field_72449_c;
/* 1258 */             CustomScoreboard.TEAM_COLOR team_color = CustomScoreboard.getTeamColorByCode(victim.substring(0, 2));
/* 1259 */             ParticleController.spawnFinalKillParticles(posX, posY, posZ, team_color);
/*      */           }  break;
/*      */         } 
/* 1262 */         str = "&r" + vals[2] + " &f⚔ &r" + victim;
/*      */         
/* 1264 */         setMessageText(e, str);
/* 1265 */         handleBedwarsMeowKill(e, vals[2], victim);
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case CHAT_TEAM_DESTROYED:
/* 1272 */         if (IS_MOD_ACTIVE) deleteMessage(e);
/*      */         
/*      */         break;
/*      */       
/*      */       case CHAT_TEAM_BED_BROKEN:
/* 1277 */         if (IS_MOD_ACTIVE) {
/* 1278 */           str = PREFIX_BED + getTeamColor(vals[2]) + "Минус " + getTeamBoldName(vals[2]);
/* 1279 */           deleteMessage(e);
/* 1280 */           ChatSender.addHoverText(str, "&7Сломал &f" + vals[1]);
/*      */         } 
/*      */         
/* 1283 */         playSound(SOUND_BED_BROKEN);
/*      */         
/* 1285 */         HintsBaseRadar.checkBedState();
/*      */         
/* 1287 */         handleBedwarsMeowBed(CustomScoreboard.getTeamColorByCode(getTeamColor(vals[2])), vals[1]);
/*      */         break;
/*      */       
/*      */       case CHAT_TEAM_COLOR_CHOSEN:
/* 1291 */         str = "&r" + getTeamColor(vals[1]) + "Выбрана тима " + getTeamBoldName(vals[1]);
/* 1292 */         setMessageText(e, str);
/* 1293 */         playSound(SOUND_TEAM_CHOSEN);
/*      */         break;
/*      */       case CHAT_TEAM_ALREADY_CHOSEN:
/* 1296 */         str = "&cТима уже выбрана!";
/* 1297 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_TEAM_IS_FULL:
/* 1300 */         str = "&cТима заполнена!";
/* 1301 */         setMessageText(e, str);
/* 1302 */         clearGame();
/*      */         break;
/*      */       case CHAT_SHOP_ITEM_BOUGHT:
/* 1305 */         str = "&r&7+ &e" + vals[1] + " &a" + vals[0];
/*      */         
/* 1307 */         FileManager.writeToFile(vals[0] + ";" + vals[1] + ";&;", "resourceBedwarslog.txt", true);
/*      */         
/* 1309 */         IS_IN_GAME = true;
/*      */         
/* 1311 */         if (Main.getConfigBool(Main.CONFIG_MSG.REMOVE_BUY_MESSAGE)) { deleteMessage(e); break; }
/* 1312 */          setMessageText(e, str);
/*      */         break;
/*      */       
/*      */       case CHAT_SHOP_NOT_ENOUGH_RESOURCES:
/* 1316 */         str = "&r&cНет ресов!";
/*      */         
/* 1318 */         if (Main.getConfigBool(Main.CONFIG_MSG.REMOVE_BUY_MESSAGE)) { deleteMessage(e); break; }
/* 1319 */          setMessageText(e, str);
/*      */         break;
/*      */       
/*      */       case CHAT_UPGRADE_BOUGHT:
/* 1323 */         if (IS_MOD_ACTIVE) {
/* 1324 */           str = "&b&l" + vals[2] + " &a" + vals[3] + " &7прокачаны";
/* 1325 */           deleteMessage(e);
/* 1326 */           ChatSender.addHoverText(str, "&7Прокачал &b" + vals[1]);
/*      */         } 
/*      */         
/* 1329 */         playSound(SOUND_UPGRADE_BOUGHT, 0.5F);
/*      */         break;
/*      */       case CHAT_GENERATOR_DIAMOND_LEVELED_UP:
/* 1332 */         new_time = getGeneratorDiamondUpgradeTime(vals[1]);
/* 1333 */         str = "&bАлмазы &7вкачаны до &a" + vals[1] + " &7- каждые &e" + new_time + " &7сек";
/*      */         try {
/* 1335 */           Main.generatorTimers.setMaxTimeDiamonds(Integer.parseInt(new_time));
/* 1336 */         } catch (Exception exception) {}
/*      */         
/* 1338 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_GENERATOR_EMERALD_LEVELED_UP:
/* 1341 */         new_time = getGeneratorEmeraldUpgradeTime(vals[1]);
/* 1342 */         str = "&aИзумруды &7вкачаны до &a" + vals[1] + " &7- каждые &e" + new_time + " &7сек";
/*      */         try {
/* 1344 */           Main.generatorTimers.setMaxTimeEmeralds(Integer.parseInt(new_time));
/* 1345 */         } catch (Exception exception) {}
/*      */         
/* 1347 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_TRAP_ACTIVATED:
/* 1350 */         str = "&c&k===&r &c&lВРАГ НА БАЗЕ &c&k===";
/* 1351 */         str = str + "\n" + str + "\n" + str;
/* 1352 */         setMessageText(e, str);
/* 1353 */         playSound(SOUND_TRAP_ACTIVATED);
/*      */         break;
/*      */       case CHAT_SERVER_RESTART:
/* 1356 */         str = "&cВыход в лобби через &c&l" + vals[0] + " &cсек";
/* 1357 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_TELEPORTATION_TO_HUB:
/* 1360 */         str = "&8ТП в лобби...";
/* 1361 */         clearGame();
/* 1362 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_CONNECTING_TO_LOBBY:
/* 1365 */         IS_IN_GAME = false;
/* 1366 */         str = "&8Подключение к " + vals[0];
/* 1367 */         clearGame();
/* 1368 */         setMessageText(e, str);
/*      */         break;
/*      */       
/*      */       case CHAT_HUB_CHAT_PLAYER_MESSAGE:
/* 1372 */         clearGame();
/*      */         
/* 1374 */         player_name = formatChatPlayerName(e.message, vals[0], "&7");
/* 1375 */         text = vals[2].replaceFirst("§7§7", "");
/* 1376 */         message_hover_text = getHoverMessage(e.message);
/*      */         
/* 1378 */         if (Main.playerManipulator.handleCommand(player_name, vals[2].replaceFirst("§7§7", ""))) {
/* 1379 */           deleteMessage(e);
/*      */           
/*      */           break;
/*      */         } 
/* 1383 */         if (Main.namePlateRenderer.isBroAdmin(player_name) && vals[2].contains("Я автор мода")) {
/* 1384 */           deleteMessage(e);
/* 1385 */           ChatSender.addText(PREFIX_BEDWARSBRO + "&bНа данный момент создатель мода играет под ником &b&l" + player_name + "");
/* 1386 */           playSound(SOUND_PARTY_CHAT);
/*      */           
/*      */           break;
/*      */         } 
/* 1390 */         if (message_hover_text.contains(" ") && Main.emotesDrawer.handleChat(message_hover_text.split(" ")[1].trim(), text)) {
/* 1391 */           deleteMessage(e); break;
/*      */         } 
/* 1393 */         if (Main.emotesDrawer.handleChat(player_name, text)) {
/* 1394 */           deleteMessage(e);
/*      */           
/*      */           break;
/*      */         } 
/* 1398 */         if (IS_MOD_ACTIVE) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1403 */           if (isUserMuted(player_name) == true) {
/* 1404 */             deleteMessage(e);
/*      */             
/*      */             break;
/*      */           } 
/*      */           
/* 1409 */           String discord_string = getDiscordFromString(text);
/* 1410 */           String sender_name = player_name;
/* 1411 */           if (message_hover_text.length() > 0) {
/* 1412 */             String[] arrayOfString = message_hover_text.split(" ");
/* 1413 */             if (arrayOfString.length >= 2) {
/* 1414 */               String donation = ColorCodesManager.removeColorCodes(arrayOfString[0].trim());
/* 1415 */               String donation_color = "&d";
/*      */               
/* 1417 */               if (donation.contains("Игрок")) {
/* 1418 */                 donation = "";
/* 1419 */                 donation_color = "&7";
/* 1420 */               } else if (donation.contains("GOLD")) {
/* 1421 */                 donation_color = "&e";
/* 1422 */               } else if (donation.contains("DIAMOND")) {
/* 1423 */                 donation_color = "&b";
/* 1424 */               } else if (donation.contains("EMERALD")) {
/* 1425 */                 donation_color = "&a";
/* 1426 */               } else if (donation.contains("MAGMA")) {
/* 1427 */                 donation_color = "&6";
/* 1428 */               } else if (donation.contains("LEGEND")) {
/* 1429 */                 donation_color = "&c";
/*      */               } 
/*      */               
/* 1432 */               String battle_stats = "";
/* 1433 */               String hover_data = ColorCodesManager.removeColorCodes(message_hover_text);
/* 1434 */               if (hover_data.contains("Убийств ▸") && hover_data.contains("K/D ▸ ") && Main.getConfigBool(Main.CONFIG_MSG.ENABLE_BETTER_CHAT_STATISTIC_PREFIX)) {
/*      */                 
/*      */                 try {
/*      */                   
/* 1438 */                   int kills = Integer.parseInt(hover_data.split("Убийств ▸")[1].trim().split(" ")[0].trim().replace(",", ""));
/* 1439 */                   int games_cnt = Integer.parseInt(hover_data.split("Игр ▸")[1].trim().split(" ")[0].trim().replace(",", ""));
/* 1440 */                   int wins_cnt = Integer.parseInt(hover_data.split("Побед ▸")[1].trim().split(" ")[0].trim().replace(",", ""));
/*      */ 
/*      */                   
/* 1443 */                   String kills_s = (kills / 1000) + "к";
/* 1444 */                   kills_s = (new DecimalFormat("0.0")).format((kills / 1000.0F)) + "к";
/* 1445 */                   if (kills > 100) {
/* 1446 */                     String kills_color = "&c";
/* 1447 */                     if (kills < 500) { kills_color = "&c"; }
/* 1448 */                     else if (kills < 1000) { kills_color = "&6"; }
/* 1449 */                     else if (kills < 2000) { kills_color = "&e"; }
/* 1450 */                     else if (kills < 5000) { kills_color = "&a"; }
/* 1451 */                     else if (kills < 10000) { kills_color = "&a&l"; }
/* 1452 */                     else if (kills < 20000) { kills_color = "&a&l&n"; }
/* 1453 */                     else if (kills >= 40000) { kills_color = "&b&l&n"; }
/*      */                     
/* 1455 */                     String kdr_color = "&c";
/* 1456 */                     double kdr = Double.parseDouble(hover_data.split("K/D ▸ ")[1].trim().split(" ")[0].split("\n")[0].trim());
/* 1457 */                     if (kdr < 1.0D) { kdr_color = "&c"; }
/* 1458 */                     else if (kdr < 1.5D) { kdr_color = "&6"; }
/* 1459 */                     else if (kdr < 2.0D) { kdr_color = "&e"; }
/* 1460 */                     else if (kdr < 3.0D) { kdr_color = "&a"; }
/* 1461 */                     else if (kdr < 4.0D) { kdr_color = "&a&l"; }
/* 1462 */                     else if (kdr < 5.0D) { kdr_color = "&a&l&n"; }
/* 1463 */                     else if (kdr >= 5.0D) { kdr_color = "&b&l&n"; }
/*      */                     
/* 1465 */                     String wr_color = "&c";
/* 1466 */                     int wr = -1;
/* 1467 */                     if (games_cnt > 0) {
/* 1468 */                       wr = (int)(wins_cnt * 100.0F / games_cnt);
/* 1469 */                       if (wr < 10) { wr_color = "&c"; }
/* 1470 */                       else if (wr < 20) { wr_color = "&6"; }
/* 1471 */                       else if (wr < 30) { wr_color = "&e"; }
/* 1472 */                       else if (wr < 40) { wr_color = "&a"; }
/* 1473 */                       else if (wr < 50) { wr_color = "&a&l"; }
/* 1474 */                       else if (wr < 60) { wr_color = "&a&l&n"; }
/* 1475 */                       else if (wr >= 70) { wr_color = "&b&l&n"; }
/*      */                     
/*      */                     } 
/* 1478 */                     battle_stats = "&7[" + kills_color + kills_s + "&8, " + kdr_color + kdr + ((wr >= 0) ? ("&8, " + wr_color + wr + "%") : "") + "&7]";
/*      */                   } 
/* 1480 */                 } catch (Exception exception) {}
/*      */               }
/*      */ 
/*      */ 
/*      */               
/* 1485 */               String real_name = ColorCodesManager.removeColorCodes(arrayOfString[1].trim());
/*      */               
/* 1487 */               sender_name = ((battle_stats.length() > 0) ? (battle_stats + " ") : "") + donation_color + donation + ((donation.length() > 0) ? " &l" : "") + real_name;
/*      */               
/* 1489 */               String mod_prefix = Main.namePlateRenderer.getPrefixByName(real_name);
/* 1490 */               if (mod_prefix == null) mod_prefix = "";
/*      */               
/* 1492 */               sender_name = mod_prefix + sender_name.trim();
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 1498 */           str = sender_name + "&7 → &f" + highLightExtras(text);
/*      */           
/* 1500 */           if (discord_string != null) {
/* 1501 */             deleteMessage(e);
/* 1502 */             ChatSender.addClickSuggestAndHoverText(str, "&fНажми, чтоб скопировать \"&e" + discord_string + "&f\"", discord_string); break;
/*      */           } 
/* 1504 */           deleteMessage(e);
/* 1505 */           ChatSender.addClickSuggestAndHoverText(str, message_hover_text, ColorCodesManager.removeColorCodes(player_name));
/*      */         } 
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case CHAT_YOU_WERE_KICKED:
/* 1512 */         clearGame();
/* 1513 */         str = "&cТебя кикнули";
/* 1514 */         setMessageText(e, str);
/* 1515 */         playSound(SOUND_REJECT);
/*      */         break;
/*      */       case CHAT_ADS:
/* 1518 */         deleteMessage(e);
/*      */         break;
/*      */       case CHAT_GAME_STARTS_IN_SECONDS:
/* 1521 */         clearGame();
/* 1522 */         str = "&eКатка начнется через &c&l" + vals[1] + "";
/* 1523 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_GAME_CHAT_LOCAL:
/* 1526 */         if (IS_MOD_ACTIVE) {
/* 1527 */           str = "&r" + vals[0] + "<Тиме> " + formatChatPlayerName(e.message, vals[1], vals[0], true) + " &8→ &f" + vals[3];
/* 1528 */           deleteMessage(e);
/* 1529 */           String name2copy = ColorCodesManager.removeColorCodes(vals[1]);
/* 1530 */           if ((name2copy.split(" ")).length == 2) name2copy = name2copy.split(" ")[1].trim().replaceFirst("~", ""); 
/* 1531 */           ChatSender.addClickSuggestAndHoverText(str, vals[1], name2copy);
/*      */         } 
/*      */         break;
/*      */       
/*      */       case CHAT_GAME_CHAT_GLOBAL:
/* 1536 */         n = formatChatPlayerName(e.message, vals[1], vals[0], true);
/* 1537 */         if (n.contains(" ")) n = n.split(" ")[1].trim(); 
/* 1538 */         if (Main.playerManipulator.handleCommand(ColorCodesManager.removeColorCodes(n), vals[3])) {
/* 1539 */           deleteMessage(e);
/*      */           
/*      */           break;
/*      */         } 
/* 1543 */         if (Main.emotesDrawer.handleChat(n.replace("*", "").trim(), vals[3])) {
/* 1544 */           deleteMessage(e);
/*      */           
/*      */           break;
/*      */         } 
/* 1548 */         if (IS_MOD_ACTIVE) {
/* 1549 */           str = "&r" + vals[0] + "" + formatChatPlayerName(e.message, vals[1], vals[0], true) + " &8→ &f" + vals[3];
/* 1550 */           deleteMessage(e);
/* 1551 */           String name2copy = ColorCodesManager.removeColorCodes(vals[1]);
/* 1552 */           if ((name2copy.split(" ")).length == 2) name2copy = name2copy.split(" ")[1].trim().replaceFirst("~", ""); 
/* 1553 */           ChatSender.addClickSuggestAndHoverText(str, vals[1], name2copy);
/*      */         } 
/*      */         break;
/*      */       case CHAT_GAME_CHAT_SPECTATOR:
/* 1557 */         n = formatChatPlayerName(e.message, vals[0], "&7", true);
/*      */         
/* 1559 */         if (n.contains(" ")) n = n.split(" ")[1].trim(); 
/* 1560 */         str = "&7" + n + " &8→ &f" + vals[1];
/*      */         
/* 1562 */         str = "&r&7[Спектатор] " + n + " &8→ &f" + vals[2];
/* 1563 */         setMessageText(e, str);
/*      */         
/* 1565 */         if (Main.emotesDrawer.handleChat(n.replace("*", "").trim(), vals[2])) {
/* 1566 */           deleteMessage(e);
/*      */         }
/*      */         break;
/*      */ 
/*      */       
/*      */       case CHAT_GAME_CHAT_PREGAME:
/* 1572 */         n = formatChatPlayerName(e.message, vals[0], "&7", true);
/*      */         
/* 1574 */         if (n.contains(" ")) n = n.split(" ")[1].trim(); 
/* 1575 */         str = "&7" + n + " &8→ &f" + vals[1];
/* 1576 */         setMessageText(e, str);
/* 1577 */         clearGame();
/*      */         
/* 1579 */         if (Main.playerManipulator.handleCommand(ColorCodesManager.removeColorCodes(n), vals[1])) {
/* 1580 */           deleteMessage(e);
/*      */           
/*      */           break;
/*      */         } 
/* 1584 */         if (Main.emotesDrawer.handleChat(n.replace("*", "").trim(), vals[1])) {
/* 1585 */           deleteMessage(e);
/*      */         }
/*      */         break;
/*      */ 
/*      */       
/*      */       case CHAT_PREGAME_FASTSTART_REJECT:
/* 1591 */         clearGame();
/* 1592 */         str = "&fКупи &c&lд&6&lо&e&lн&a&lа&b&lт &fсначала";
/* 1593 */         setMessageText(e, str);
/* 1594 */         playSound(SOUND_REJECT);
/*      */         break;
/*      */       case CHAT_LOBBY_DONATER_GREETING:
/* 1597 */         clearGame();
/* 1598 */         if (Main.namePlateRenderer.isBroAdmin(vals[0])) {
/* 1599 */           playSound(SOUND_TEAM_DESTROYED);
/*      */         }
/*      */         
/* 1602 */         name = vals[0];
/* 1603 */         if (name.contains(" ")) name = name.split(" ")[0].trim(); 
/* 1604 */         str = "§r§c§l› §r§f" + vals[0] + " &7" + vals[1];
/*      */         
/* 1606 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_PLAYER_BANNED:
/* 1609 */         str = PREFIX_ANTICHEAT + "&fДаун &c&l" + ColorCodesManager.removeColorCodes(vals[1]) + " &fчитер и был &cзабанен&f!";
/* 1610 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_GAME_ANTI_CHEAT_KICK:
/* 1613 */         p_name = ColorCodesManager.removeColorCodes(vals[1]);
/*      */         
/* 1615 */         str = PREFIX_ANTICHEAT + "&fДаун &c&l" + p_name + " &fспалился с читами и был &cкикнут&f!";
/* 1616 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_HUB_ANTIFLOOD:
/* 1619 */         str = PREFIX_ANTIFLOOD + "&cЗамучен &l" + vals[1] + " &cза спам!";
/* 1620 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_PREGAME_NOT_ENOUGH_PLAYERS_TO_START:
/* 1623 */         str = "&cПодожди еще игроков";
/* 1624 */         setMessageText(e, str);
/* 1625 */         playSound(SOUND_REJECT);
/*      */         break;
/*      */       case CHAT_BEDWARS_END_TEAM_WON:
/* 1628 */         str = "§r§f                     " + getTeamBoldName(vals[0]) + " &fпобедили!";
/* 1629 */         if (HintsValidator.isWinEmoteActive()) WinEmote.changeWorldBlocks(CustomScoreboard.getTeamColorByCode(vals[0].substring(0, 2))); 
/* 1630 */         setMessageText(e, str);
/* 1631 */         playSound(SOUND_GAME_END);
/*      */         
/* 1633 */         handleBedwarsMeowWin(vals[0]);
/* 1634 */         sendDelayedGameStats();
/*      */         break;
/*      */       
/*      */       case CHAT_BEDWARS_END_TOP_KILLER:
/* 1638 */         place = getTopKillerPlace(vals[1]);
/*      */         
/* 1640 */         player = vals[2];
/* 1641 */         if (player.equals((Minecraft.func_71410_x()).field_71439_g.func_70005_c_())) {
/* 1642 */           player = "&e&l" + ColorCodesManager.removeColorCodes(player);
/*      */         }
/*      */         try {
/* 1645 */           int kills_cnt = Integer.parseInt(vals[3]);
/* 1646 */           String ending = getNumberEnding(kills_cnt, "", "а", "ов");
/*      */           
/* 1648 */           str = "§r§f                  §r" + place + " &8▸ §7" + player + " &7- &c" + vals[3] + " &fкил" + ending + "";
/* 1649 */           setMessageText(e, str);
/* 1650 */         } catch (Exception exception) {}
/*      */         break;
/*      */       case CHAT_BEDWARS_END_TOP_KILLER_UNKNOWN:
/* 1653 */         place = getTopKillerPlace(vals[0]);
/* 1654 */         str = "§r§f                  §r" + place + " &8▸ §cN/A";
/* 1655 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_YOU_ARE_TELEPORTED_TO_LOBBY_DUE_TO_FULL_ARENA:
/* 1658 */         str = "§rАрена заполнена! Выбери другую";
/* 1659 */         setMessageText(e, str);
/* 1660 */         playSound(SOUND_REJECT);
/*      */         break;
/*      */       case CHAY_YOU_ALREADY_ON_SERVER:
/* 1663 */         str = PREFIX_PARTY + "§cТебя варпнули, но ты уже на сервере!";
/* 1664 */         setMessageText(e, str);
/*      */         break;
/*      */ 
/*      */       
/*      */       case CHAT_LOBBY_PARTY_INVITE:
/* 1669 */         str = PREFIX_PARTY + "&fИнвайт кинут игроку &e" + vals[1];
/* 1670 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_INVITE_REJECTED:
/* 1673 */         str = PREFIX_PARTY + "&fИнвайт на пати &cистек &fигроку &e" + vals[1];
/* 1674 */         setMessageText(e, str);
/* 1675 */         playSound(SOUND_REJECT, 0.7F);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_WARP:
/* 1678 */         str = PREFIX_PARTY + "&fТелепортирую тиммейтов к себе...";
/* 1679 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_PLAYER_OFFLINE:
/* 1682 */         str = PREFIX_PARTY + "&r&cИз пати выгнан &c&l" + vals[1] + " &cза АФК";
/* 1683 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_DISBANDED:
/* 1686 */         str = PREFIX_PARTY + "&cПати разформировано";
/* 1687 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_ALREADY_IN_PARTY:
/* 1690 */         str = PREFIX_PARTY + "&cЭтот игрок уже в пати";
/* 1691 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_OFFLINE:
/* 1694 */         str = PREFIX_PARTY + "&cЭтот игрок не в сети";
/* 1695 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_PARTY_ON_CREATE:
/* 1698 */         str = PREFIX_PARTY + "&a&lПати создано!";
/* 1699 */         setMessageText(e, str);
/* 1700 */         playSound(SOUND_PARTY_CREATED);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_YOU_ARE_NOT_IN_PARTY:
/* 1703 */         str = PREFIX_PARTY + "&cТы не в пати";
/* 1704 */         setMessageText(e, str);
/*      */         
/* 1706 */         if (bwmanipulator_party_info == 1) {
/* 1707 */           bwmanipulator_party_info = -1;
/* 1708 */           deleteMessage(e);
/* 1709 */           removeNextMessage = true;
/* 1710 */           addBedwarsMeowMessageToQuee("Я без пати", false);
/*      */         } 
/*      */         break;
/*      */       
/*      */       case CHAT_LOBBY_PARTY_PLAYER_KICKED:
/* 1715 */         str = PREFIX_PARTY + "&fКикнут &c&l" + vals[1];
/* 1716 */         setMessageText(e, str);
/* 1717 */         playSound(SOUND_REJECT);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_PLAYER_LEFT:
/* 1720 */         str = PREFIX_PARTY + "&fЛивнул &c&l" + vals[1];
/* 1721 */         setMessageText(e, str);
/* 1722 */         playSound(SOUND_REJECT);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_CHAT_ENTER_MESSAGE:
/* 1725 */         str = PREFIX_PARTY + "&fВведи сообщение: &a/pc Всем привет!";
/* 1726 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_CHAT_MESSAGE:
/* 1729 */         player_name = formatChatPlayerName(e.message, vals[1], "&7");
/* 1730 */         this_text = vals[2].trim();
/* 1731 */         str = PREFIX_PARTY + "&e[Chat] &7" + player_name + " &8→ &f" + this_text;
/* 1732 */         setMessageText(e, str);
/*      */         
/* 1734 */         if (Main.playerManipulator.handleCommand(player_name, this_text)) {
/* 1735 */           deleteMessage(e);
/*      */           
/*      */           break;
/*      */         } 
/* 1739 */         if (!ColorCodesManager.removeColorCodes(player_name).equals((Minecraft.func_71410_x()).field_71439_g.func_70005_c_())) {
/* 1740 */           playSound(SOUND_PARTY_CHAT);
/*      */         }
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_NO_PERMISSION:
/* 1744 */         str = PREFIX_PARTY + "&cТы не лидер пати!";
/* 1745 */         setMessageText(e, str);
/* 1746 */         playSound(SOUND_REJECT);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_DISBAND_REQUEST:
/* 1749 */         str = PREFIX_PARTY + "&c&l" + vals[1] + " &cудаляет пати";
/* 1750 */         setMessageText(e, str);
/* 1751 */         playSound(SOUND_REJECT);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_NOT_ENOUGH_SPACE:
/* 1754 */         str = PREFIX_PARTY + "&cНету места на этой арене";
/* 1755 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_REQUEST_ALREADY_SENT:
/* 1758 */         str = PREFIX_PARTY + "&cПодожди, у него уже есть запрос";
/* 1759 */         setMessageText(e, str);
/* 1760 */         playSound(SOUND_REJECT);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_YOU_WERE_WARPED:
/* 1763 */         str = PREFIX_PARTY + "&aТебя варпнул к себе создатель пати!";
/* 1764 */         setMessageText(e, str);
/* 1765 */         playSound(SOUND_PARTY_CHAT);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_INFO:
/* 1768 */         str = PREFIX_PARTY + "&aПати инфо";
/*      */         
/* 1770 */         this_text = e.message.func_150254_d();
/*      */         
/* 1772 */         myParty = null;
/*      */         
/* 1774 */         split = this_text.replace("§r", "").split("↪");
/* 1775 */         if (split != null && split.length == 3 && split[0].contains("Информация о пати")) {
/*      */           
/*      */           try {
/* 1778 */             String creator_line = ColorCodesManager.removeColorCodes(split[1]);
/* 1779 */             String creator_name = creator_line.split("Создатель:")[1].trim();
/* 1780 */             String[] members_line = split[2].split("Участники:§e")[1].trim().split(",");
/* 1781 */             ArrayList<String> members = new ArrayList<String>();
/* 1782 */             for (String m : members_line) {
/* 1783 */               name = m.trim().split(" ")[0];
/* 1784 */               if (!m.contains("§c●") && !name.equals(creator_name))
/* 1785 */                 members.add(name); 
/*      */             } 
/* 1787 */             myParty = new MyParty(creator_name, members);
/*      */             
/* 1789 */             if (bwmanipulator_party_info == 1) {
/* 1790 */               bwmanipulator_party_info = -1;
/* 1791 */               String s = "";
/* 1792 */               for (String m : myParty.members) {
/* 1793 */                 s = s + m + ", ";
/*      */               }
/* 1795 */               if (s.length() > 2) s = s.substring(0, s.length() - 2);
/*      */               
/* 1797 */               deleteMessage(e);
/*      */               
/* 1799 */               removeNextMessage = true;
/* 1800 */               ChatSender.sendText("!Я в пати " + myParty.creator + ", учасники: " + s); break;
/*      */             } 
/* 1802 */             playSound(SOUND_PARTY_CHAT);
/*      */           
/*      */           }
/* 1805 */           catch (Exception exception) {}
/*      */         }
/*      */         break;
/*      */ 
/*      */       
/*      */       case CHAT_LOBBY_PARTY_OWNER_LEFT:
/* 1811 */         str = PREFIX_PARTY + "&cСоздатель пати &l" + vals[1] + " &cливнул, пати будет удалено!";
/* 1812 */         setMessageText(e, str);
/* 1813 */         playSound(SOUND_REJECT);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_REQUEST:
/* 1816 */         playSound(SOUND_PLAYER_STATS);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_YOU_ACCEPTED_REQUEST:
/* 1819 */         str = PREFIX_PARTY + "&aТы принял запрос!";
/* 1820 */         setMessageText(e, str);
/* 1821 */         playSound(SOUND_PARTY_CREATED);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_NEW_LEADER:
/* 1824 */         assert Minecraft.func_71410_x() == null || (Minecraft.func_71410_x()).field_71439_g == null;
/* 1825 */         if (vals[1].equals((Minecraft.func_71410_x()).field_71439_g.func_70005_c_())) {
/* 1826 */           str = PREFIX_PARTY + "&aТеперь ты лидер пати!";
/*      */         } else {
/* 1828 */           str = PREFIX_PARTY + "&fНовый лидер пати: &a&l" + vals[1];
/*      */         } 
/* 1830 */         playSound(SOUND_PARTY_CREATED);
/*      */         
/* 1832 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_COMMANDS_DONT_WORK_IN_LOBBY:
/* 1835 */         str = PREFIX_PARTY + "&cКоманды пати тут не работают";
/* 1836 */         setMessageText(e, str);
/* 1837 */         playSound(SOUND_REJECT);
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case CHAT_LOBBY_STATS_PLAYER:
/* 1843 */         player = vals[1];
/* 1844 */         str = "\n&r&fСтата &e" + player + "&f:";
/* 1845 */         setMessageText(e, str);
/* 1846 */         playSound(SOUND_PLAYER_STATS);
/*      */         
/* 1848 */         myStatistic = new MyStatistic(player);
/*      */         break;
/*      */       case CHAT_LOBBY_STATS_CATEGORY:
/* 1851 */         category = getStatsCategoryName(vals[1]);
/* 1852 */         placeColor = "&0";
/*      */         try {
/* 1854 */           int x = Integer.parseInt(vals[3].trim());
/* 1855 */           placeColor = "&8";
/* 1856 */           if (x < 5000) placeColor = "&7"; 
/* 1857 */           if (x < 2000) placeColor = "&f"; 
/* 1858 */           if (x < 1000) placeColor = "&a"; 
/* 1859 */           if (x < 100) placeColor = "&e&l"; 
/* 1860 */           if (x == 0) placeColor = "&c";
/*      */         
/* 1862 */         } catch (Exception exception) {}
/* 1863 */         str = " &8- &r" + category + "&8 ▸ &f" + vals[2] + "&8. Место - " + placeColor + vals[3];
/*      */         
/* 1865 */         cnt = -1;
/* 1866 */         place_cnt = -1;
/*      */         try {
/* 1868 */           cnt = Integer.parseInt(vals[2].trim());
/* 1869 */           place_cnt = Integer.parseInt(vals[3].trim());
/* 1870 */         } catch (Exception exception) {}
/*      */         
/* 1872 */         if (cnt != -1 && place_cnt != -1)
/*      */         {
/* 1874 */           if (category.contains("Килов")) {
/* 1875 */             myStatistic.category_kills_cnt = cnt;
/* 1876 */             myStatistic.category_kills_place = place_cnt;
/* 1877 */           } else if (category.contains("Смертей")) {
/* 1878 */             myStatistic.category_deathes_cnt = cnt;
/* 1879 */             myStatistic.category_deathes_place = place_cnt;
/* 1880 */           } else if (category.contains("Каток")) {
/* 1881 */             myStatistic.category_games_cnt = cnt;
/* 1882 */             myStatistic.category_games_place = place_cnt;
/* 1883 */           } else if (category.contains("Побед")) {
/* 1884 */             myStatistic.category_wins_cnt = cnt;
/* 1885 */             myStatistic.category_wins_place = place_cnt;
/* 1886 */           } else if (category.contains("Кроватей")) {
/* 1887 */             myStatistic.category_beds_cnt = cnt;
/* 1888 */             myStatistic.category_beds_place = place_cnt;
/* 1889 */             str = str + "\n";
/*      */             
/* 1891 */             int player_mark = 0;
/*      */             
/* 1893 */             if (myStatistic.category_games_cnt > 0) {
/* 1894 */               float percentage = (float)(myStatistic.category_wins_cnt / myStatistic.category_games_cnt * 100.0D);
/*      */               
/* 1896 */               String str1 = "&0";
/* 1897 */               if (percentage < 10.0F) {
/* 1898 */                 player_mark++;
/* 1899 */                 str1 = "&c";
/* 1900 */               } else if (percentage < 20.0F) {
/* 1901 */                 player_mark += 2;
/* 1902 */                 str1 = "&6";
/* 1903 */               } else if (percentage < 30.0F) {
/* 1904 */                 player_mark += 3;
/* 1905 */                 str1 = "&e";
/* 1906 */               } else if (percentage <= 40.0F) {
/* 1907 */                 player_mark += 4;
/* 1908 */                 str1 = "&a";
/*      */               } else {
/* 1910 */                 player_mark += 5;
/* 1911 */                 str1 = "&2";
/*      */               } 
/*      */               
/* 1914 */               str = str + "&8 - &fВинрейт &8▸ " + str1 + (int)percentage + "%\n";
/*      */             } 
/*      */             
/* 1917 */             if (myStatistic.category_deathes_cnt > 0) {
/* 1918 */               float kdr = (float)Math.round(myStatistic.category_kills_cnt / myStatistic.category_deathes_cnt * 100.0D) / 100.0F;
/* 1919 */               String str1 = "&0";
/* 1920 */               if (kdr < 0.7D) {
/* 1921 */                 player_mark++;
/* 1922 */                 str1 = "&c";
/* 1923 */               } else if (kdr < 1.0F) {
/* 1924 */                 player_mark += 2;
/* 1925 */                 str1 = "&6";
/* 1926 */               } else if (kdr < 1.5D) {
/* 1927 */                 player_mark += 3;
/* 1928 */                 str1 = "&e";
/* 1929 */               } else if (kdr <= 2.0F) {
/* 1930 */                 player_mark += 4;
/* 1931 */                 str1 = "&a";
/*      */               } else {
/* 1933 */                 player_mark += 5;
/* 1934 */                 str1 = "&2";
/*      */               } 
/* 1936 */               str = str + "&8 - &fK/D &8▸ " + str1 + kdr + "\n";
/*      */             } 
/*      */             
/* 1939 */             if (myStatistic.category_games_cnt > 0) {
/* 1940 */               float bpg = (float)Math.round(myStatistic.category_beds_cnt / myStatistic.category_games_cnt * 10.0D) / 10.0F;
/* 1941 */               String str1 = "&0";
/* 1942 */               if (bpg < 0.7D) {
/* 1943 */                 player_mark++;
/* 1944 */                 str1 = "&c";
/* 1945 */               } else if (bpg < 1.0F) {
/* 1946 */                 player_mark += 2;
/* 1947 */                 str1 = "&6";
/* 1948 */               } else if (bpg < 1.5D) {
/* 1949 */                 player_mark += 3;
/* 1950 */                 str1 = "&e";
/* 1951 */               } else if (bpg <= 2.0F) {
/* 1952 */                 player_mark += 4;
/* 1953 */                 str1 = "&a";
/*      */               } else {
/* 1955 */                 player_mark += 5;
/* 1956 */                 str1 = "&2";
/*      */               } 
/* 1958 */               str = str + "&8 - &fBeds/Game &8▸ " + str1 + bpg + " шт\n";
/*      */             } 
/*      */             
/* 1961 */             int treshold = 200;
/* 1962 */             if (myStatistic.category_kills_cnt < treshold && myStatistic.category_deathes_cnt < treshold && myStatistic.category_games_cnt < treshold && myStatistic.category_wins_cnt < treshold && myStatistic.category_beds_cnt < treshold)
/*      */             {
/*      */ 
/*      */ 
/*      */               
/* 1967 */               str = str + "&8 - &cНулевый аккаунт\n";
/*      */             }
/*      */             
/* 1970 */             float mark = (Math.round((player_mark / 3 * 10)) / 10);
/* 1971 */             String color = "&f";
/* 1972 */             if (mark < 2.0F) {
/* 1973 */               color = "&c";
/* 1974 */             } else if (mark < 3.0F) {
/* 1975 */               color = "&6";
/* 1976 */             } else if (mark < 4.0F) {
/* 1977 */               color = "&e";
/* 1978 */             } else if (mark < 4.5D) {
/* 1979 */               color = "&a";
/*      */             } else {
/* 1981 */               color = "&2";
/*      */             } 
/*      */ 
/*      */             
/* 1985 */             str = str + "&8 - &fОценка &8▸ " + color + mark + "\n";
/*      */             
/* 1987 */             myStatistic = null;
/*      */           } 
/*      */         }
/* 1990 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_GAME_CANT_USE_COMMANDS:
/* 1993 */         str = "&cНельзя юзать команды во время игры!";
/* 1994 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_GAME_WAIT_SECONDS:
/* 1997 */         str = "&cПодожди " + vals[0] + " сек!";
/* 1998 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_GAME_YOU_CANT_USE_COLORS:
/* 2001 */         str = "&cКупи донат чтоб юзать цвета в чате!";
/* 2002 */         setMessageText(e, str);
/* 2003 */         removeNextMessage = true;
/*      */         break;
/*      */       case CHAT_GAME_YOU_CANT_USE_COLOR:
/* 2006 */         str = "&cТы не можешь юзать такие цвета в чате!";
/* 2007 */         setMessageText(e, str);
/* 2008 */         removeNextMessage = true;
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static CustomScoreboard.TEAM_COLOR getEntityTeamColor(EntityPlayer en) {
/* 2017 */     CustomScoreboard.TEAM_COLOR mod_team_color = CustomScoreboard.TEAM_COLOR.NONE;
/* 2018 */     if (en.func_96124_cp() == null) return CustomScoreboard.TEAM_COLOR.NONE; 
/* 2019 */     String team_name = en.func_96124_cp().func_96661_b();
/* 2020 */     if (team_name == null) return CustomScoreboard.TEAM_COLOR.NONE; 
/* 2021 */     return getEntityTeamColorByTeam(team_name);
/*      */   }
/*      */   
/*      */   public static CustomScoreboard.TEAM_COLOR getEntityTeamColorByTeam(String team_name) {
/* 2025 */     if (team_name.contains("red"))
/* 2026 */       return CustomScoreboard.TEAM_COLOR.RED; 
/* 2027 */     if (team_name.contains("yellow"))
/* 2028 */       return CustomScoreboard.TEAM_COLOR.YELLOW; 
/* 2029 */     if (team_name.contains("green"))
/* 2030 */       return CustomScoreboard.TEAM_COLOR.GREEN; 
/* 2031 */     if (team_name.contains("aqua"))
/* 2032 */       return CustomScoreboard.TEAM_COLOR.AQUA; 
/* 2033 */     if (team_name.contains("blue"))
/* 2034 */       return CustomScoreboard.TEAM_COLOR.BLUE; 
/* 2035 */     if (team_name.contains("light_purple"))
/* 2036 */       return CustomScoreboard.TEAM_COLOR.PINK; 
/* 2037 */     if (team_name.contains("white"))
/* 2038 */       return CustomScoreboard.TEAM_COLOR.WHITE; 
/* 2039 */     if (team_name.contains("gray")) {
/* 2040 */       return CustomScoreboard.TEAM_COLOR.GRAY;
/*      */     }
/* 2042 */     return CustomScoreboard.TEAM_COLOR.NONE;
/*      */   }
/*      */   
/*      */   public static String getHoverMessage(IChatComponent message) {
/* 2046 */     for (IChatComponent component : message.func_150253_a()) {
/* 2047 */       if (component.func_150256_b() == null || component.func_150256_b().func_150210_i() == null || component.func_150256_b().func_150210_i().func_150702_b() == null)
/* 2048 */         continue;  return component.func_150256_b().func_150210_i().func_150702_b().func_150260_c() + "\n";
/*      */     } 
/*      */     
/* 2051 */     return "";
/*      */   }
/*      */ 
/*      */   
/*      */   public static void sendUpdateModMessage() {
/* 2056 */     String link = Main.getPropModUpdateLink();
/* 2057 */     String last_version = Main.getPropModLastVersion();
/* 2058 */     if (link == null || last_version == null)
/*      */       return; 
/* 2060 */     String str = "";
/* 2061 */     str = str + "&8<===============================================>\n";
/* 2062 */     str = str + "                         &cBedwars&fBro &7v1.69.2\n\n";
/* 2063 */     str = str + "                 &fПоявилась &b&lновая версия мода &7v&a" + last_version + "\n";
/* 2064 */     str = str + "                     &fБыло добавлено &e&lбольше опций&f!\n";
/* 2065 */     str = str + "            &fТебе нужно &a&lобновить мод&f, чтоб пользоваться им дальше\n\n";
/* 2066 */     str = str + "                         &d&lНажми на сообщение\n\n";
/* 2067 */     str = str + "                     &7Скачай мод, и положи его в папку\n";
/* 2068 */     str = str + "             &7%appdata%&8/&7Roaming&8/&7.minecraft&8/&7mods\n";
/* 2069 */     str = str + "&8<===============================================>";
/* 2070 */     ChatSender.addLinkAndHoverText(str, "&eНажми&f, чтоб скачать новую версию мода", link);
/*      */   }
/*      */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\MyChatListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */