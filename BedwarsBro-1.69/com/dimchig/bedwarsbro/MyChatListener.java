/*      */ package com.dimchig.bedwarsbro;
/*      */ 
/*      */ import com.dimchig.bedwarsbro.dimchigspecial.HelloDimChig;
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
/*  106 */   public static int TIME_MEOW_MESSAGES_CHECK_FREQUENCY = 500;
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
/*  461 */     String[] chatMessagesAds = { "Сайт для совершения покупок:", "Привилегии [GOLD]", "Доступно: /sit", "Хотите купить донат, но сомневаетесь?", "Чтобы посмотреть свою или чужую статистику", "Хотите изменить свой ник?", "Хотите быть первым вкурсе всех новостей сервера?", "Хотите выиграть Айфон 11 PRO?", "Помогите нам улучшить сервер", "Хотите получить все команды сервера и опку?", "Покупка кейса производится на сайте", "Не знаете какие возможности есть у доната?", "ВКонтакте - ", "Для чего это нужно (Наведи мышкой)", "Вы случайно вышли из игры и хотите вернуться?", "Чтобы посмотреть топы по всем категориям", "Успейте купить донат по дешевым ценам", "Хочешь купить донат, но сомневаешься?", "Не знаешь какие возможности есть у доната?", "Но ты не готов тратить много денег?", "Хотел бы снимать антигрифер шоу?", "Друзья хвастаются донатом? А ты никто?", "Хочешь купить донат, но не доверяешь?", "Сайт для покупки доната", "Донат покупать только на сайте", "Только сегодня у тебя есть возможность купить донат", "Хочешь получить 85% команд ОЧЕНЬ дешево", "Хочешь быть в курсе новостей сервера?", "Купить донат на нашем сайте можно через:", "На нашем сайте есть оплата с мобильного телефона", "Тебе нужен доступ в любой приват?", "Хочешь показать всем кто тут главный?", "Купить донат на нашем сайте можно через:", "Помогите нам улучшить сервер", "У нас появился новый донат", "Все донат-привилегии и кейсы", "Важно для вашей безопасности", "Отправьте данный код в личные сообщения" };
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
/*      */     
/*  503 */     for (String s : chatMessagesAds) { chatMessages.add(new ChatMessage(CHAT_MESSAGE.CHAT_ADS, s, new String[] { "" })); }
/*      */     
/*  505 */     ArrayList<ChatMessage> temp = new ArrayList<ChatMessage>();
/*  506 */     for (ChatMessage m : chatMessages) {
/*  507 */       temp.add(m);
/*      */     }
/*  509 */     chatMessages = new ArrayList<ChatMessage>();
/*  510 */     for (ChatMessage m : temp) {
/*  511 */       if (m.type != CHAT_MESSAGE.NONE) chatMessages.add(m); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static ChatMessage findChatMessage(String str) {
/*  516 */     ChatMessage instance = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  521 */     for (ChatMessage chatMessage : chatMessages) {
/*  522 */       if (chatMessage.elements.length == 0) {
/*  523 */         String message_text = str;
/*  524 */         if (chatMessage.type == CHAT_MESSAGE.CHAT_ADS) message_text = ColorCodesManager.removeColorCodes(message_text); 
/*  525 */         if (chatMessage.isMustBeEqual == true) {
/*  526 */           if (message_text.equals(chatMessage.message))
/*  527 */             return chatMessage; 
/*      */           continue;
/*      */         } 
/*  530 */         if (message_text.contains(chatMessage.message)) {
/*  531 */           return chatMessage;
/*      */         }
/*      */         
/*      */         continue;
/*      */       } 
/*  536 */       ArrayList<String> parts = new ArrayList<String>();
/*      */       
/*  538 */       String s = chatMessage.message;
/*      */       try {
/*  540 */         for (int i = 0; i < chatMessage.elements.length; i++) {
/*  541 */           String part = s.split(Pattern.quote(chatMessage.elements[i]))[0];
/*  542 */           parts.add(part);
/*  543 */           s = s.split(chatMessage.elements[i])[1];
/*  544 */           if (i == chatMessage.elements.length - 1) parts.add(s); 
/*      */         } 
/*  546 */       } catch (Exception ex) {
/*  547 */         ChatSender.addText("ERROR with &a" + chatMessage.type);
/*      */       } 
/*      */       
/*  550 */       boolean isFound = true;
/*  551 */       for (String part : parts) {
/*  552 */         if (!str.contains(part)) {
/*  553 */           isFound = false;
/*      */ 
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  561 */       if (!isFound || parts.size() < 2) {
/*      */         continue;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  568 */         String line = str;
/*  569 */         for (int i = 0; i < parts.size() - 1; i++) {
/*      */           
/*  571 */           line = line.replaceFirst(Pattern.quote(parts.get(i)), "");
/*  572 */           String val = line.split(Pattern.quote((String)parts.get(i + 1)))[0].trim();
/*  573 */           line = line.replaceFirst(Pattern.quote(val), "");
/*      */           
/*  575 */           chatMessage.element_values[i] = val;
/*      */         } 
/*  577 */         return chatMessage;
/*  578 */       } catch (Exception ex) {
/*      */ 
/*      */ 
/*      */         
/*  582 */         if (chatMessage.type == CHAT_MESSAGE.CHAT_GAME_CHAT_GLOBAL) {
/*  583 */           chatMessage.element_values[chatMessage.element_values.length - 1] = "";
/*  584 */           return chatMessage;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  591 */     return null;
/*      */   }
/*      */   
/*      */   @SubscribeEvent
/*      */   public void onJoinServer(FMLNetworkEvent.ClientConnectedToServerEvent e) {
/*  596 */     Main.updateAllBooleans();
/*  597 */     (new Timer()).schedule(new TimerTask()
/*      */         {
/*      */           public void run()
/*      */           {
/*  601 */             if (Minecraft.func_71410_x() == null || (Minecraft.func_71410_x()).field_71456_v == null || (Minecraft.func_71410_x()).field_71456_v.func_146158_b() == null)
/*  602 */               return;  (Minecraft.func_71410_x()).field_71456_v.func_146158_b().func_146231_a();
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
/*  613 */     if (Minecraft.func_71410_x() == null)
/*  614 */       return;  if ((Minecraft.func_71410_x()).field_71439_g == null)
/*      */       return; 
/*  616 */     String login_event = "tick";
/*  617 */     if (IS_IN_GAME == true) login_event = "in_game"; 
/*  618 */     Main.loginHandler.handleInGame(login_event);
/*      */     
/*  620 */     if ((Minecraft.func_71410_x()).field_71441_e != null && (Minecraft.func_71410_x()).field_71441_e.field_72995_K) {
/*  621 */       Main.sameModePlayers.handle();
/*      */     }
/*      */ 
/*      */     
/*  625 */     String str = e.message.func_150254_d();
/*  626 */     ChatMessage instance = findChatMessage(str);
/*      */     
/*  628 */     if (instance == null) {
/*  629 */       if (e.message.func_150260_c().length() == 0) e.message = null;
/*      */       
/*      */       return;
/*      */     } 
/*  633 */     handleReceivedMessage(e, instance);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void sendDelayedGameStats() {
/*  638 */     (new Timer()).schedule(new TimerTask()
/*      */         {
/*      */           public void run()
/*      */           {
/*  642 */             if (MyChatListener.GAME_start_time > 0L) {
/*      */               
/*  644 */               String seconds = "" + (int)(((new Date()).getTime() - MyChatListener.GAME_start_time) / 1000L % 60L);
/*  645 */               if (seconds.length() == 1) seconds = "0" + seconds; 
/*  646 */               int minutes = (int)(((new Date()).getTime() - MyChatListener.GAME_start_time) / 1000L / 60L);
/*      */               
/*  648 */               ChatSender.addText("");
/*  649 */               ChatSender.addText(" &fВремя &8▸ &c" + minutes + ":" + seconds);
/*      */               
/*  651 */               ChatSender.addText(" &fКилов &8▸ &6" + MyChatListener.GAME_total_kills);
/*  652 */               ChatSender.addText(" &fСмертей &8▸ &e" + MyChatListener.GAME_total_death);
/*  653 */               String kdr_string = "100%";
/*  654 */               if (MyChatListener.GAME_total_death > 0) {
/*  655 */                 float kdr = MyChatListener.GAME_total_kills / MyChatListener.GAME_total_death * 100.0F / 100.0F;
/*  656 */                 kdr_string = "" + kdr;
/*      */               } 
/*  658 */               ChatSender.addText(" &fK/D &8▸ &a" + kdr_string);
/*      */               
/*  660 */               ChatSender.addText(" &fКроватей &8▸ &b" + MyChatListener.GAME_total_beds);
/*  661 */               ChatSender.addText("");
/*  662 */               MyChatListener.anullateGameStats();
/*      */             } 
/*      */           }
/*      */         },  500L);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static void anullateGameStats() {
/*  671 */     GAME_start_time = -1L;
/*  672 */     GAME_total_kills = -1;
/*  673 */     GAME_total_death = -1;
/*  674 */     GAME_total_beds = -1;
/*      */   }
/*      */   
/*      */   static void initGameStats() {
/*  678 */     GAME_start_time = (new Date()).getTime();
/*  679 */     GAME_total_kills = 0;
/*  680 */     GAME_total_death = 0;
/*  681 */     GAME_total_beds = 0;
/*      */     
/*  683 */     Main.generatorTimers.setStartTimesOnGameStart();
/*      */   }
/*      */   
/*      */   public static void updateScoreboard() {
/*  687 */     CustomScoreboard.updateScoreboard();
/*      */   }
/*      */ 
/*      */   
/*      */   public static String replaceColorCodes(String text) {
/*  692 */     return ColorCodesManager.replaceColorCodesInString(text);
/*      */   }
/*      */   
/*      */   public static void setMessageText(ClientChatReceivedEvent e, String text) {
/*  696 */     if (!IS_MOD_ACTIVE)
/*  697 */       return;  e.message = (IChatComponent)new ChatComponentText(replaceColorCodes(text));
/*      */   }
/*      */   
/*      */   public static void deleteMessage(ClientChatReceivedEvent e) {
/*  701 */     e.message = null;
/*      */   }
/*      */   
/*      */   public static String formatChatPlayerName(IChatComponent message, String player_name, String player_color) {
/*  705 */     return formatChatPlayerName(message, player_name, player_color, false);
/*      */   }
/*      */   
/*      */   public static String formatChatPlayerName(IChatComponent message, String player_name, String player_color, boolean removeColor) {
/*  709 */     player_name = player_name.trim();
/*  710 */     if (player_name.contains(" "))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  720 */       player_name = player_name.split(" ")[1].trim();
/*      */     }
/*      */ 
/*      */     
/*  724 */     while (player_name.charAt(0) == '&') {
/*  725 */       player_name = player_name.substring(2);
/*      */     }
/*      */ 
/*      */     
/*  729 */     String hoverText = getHoverMessage(message);
/*  730 */     if (hoverText.length() > 0 && (hoverText.split(" ")).length == 2 && hoverText.contains("Ник:")) {
/*  731 */       String hoverName = hoverText.split(" ")[1].trim();
/*  732 */       if (!hoverName.equals(player_name)) player_name = hoverName + "*";
/*      */     
/*      */     } 
/*  735 */     if (removeColor == true) {
/*  736 */       player_name = ColorCodesManager.removeColorCodes(player_name);
/*      */     }
/*      */     
/*  739 */     if (player_name.replace("*", "").equals("DimChig")) player_name = "DimChigYT";
/*      */     
/*  741 */     if (player_name.contains("DimChigYT")) {
/*  742 */       player_name = player_name.replace("DimChigYT", "&c[Y&fT] " + player_color + "DimChig").replace("~", "");
/*      */     }
/*      */     
/*  745 */     return player_name;
/*      */   }
/*      */   
/*      */   public static String getTeamBoldName(String team_name) {
/*  749 */     return team_name.substring(0, 2) + "&l" + team_name.substring(2);
/*      */   }
/*      */   
/*      */   public static String getTeamColor(String team_name) {
/*  753 */     return team_name.substring(0, 2);
/*      */   }
/*      */   
/*      */   public static String getGeneratorDiamondUpgradeTime(String upgrade) {
/*  757 */     int[] levels = { 30, 23, 12 };
/*      */     
/*  759 */     int current_level = -1;
/*  760 */     if (upgrade.contains("II")) { current_level = 2; }
/*  761 */     else if (upgrade.contains("I")) { current_level = 1; }
/*      */     
/*  763 */     if (current_level == -1) {
/*  764 */       return "?";
/*      */     }
/*      */     
/*  767 */     return "" + levels[current_level];
/*      */   }
/*      */   
/*      */   public static String getGeneratorEmeraldUpgradeTime(String upgrade) {
/*  771 */     int[] levels = { 65, 50, 35 };
/*      */     
/*  773 */     int current_level = -1;
/*  774 */     if (upgrade.contains("II")) { current_level = 2; }
/*  775 */     else if (upgrade.contains("I")) { current_level = 1; }
/*      */     
/*  777 */     if (current_level == -1) {
/*  778 */       return "?";
/*      */     }
/*      */     
/*  781 */     return "" + levels[current_level];
/*      */   }
/*      */   
/*      */   public static String upperCaseFirstLetter(String text) {
/*  785 */     if (text.length() <= 1) return text; 
/*  786 */     return text.substring(0, 1).toUpperCase() + text.substring(1);
/*      */   }
/*      */   
/*      */   public static String getTopKillerPlace(String text) {
/*  790 */     String place = "§cN/A";
/*  791 */     if (text.contains("1")) {
/*  792 */       place = "&e#1";
/*  793 */     } else if (text.contains("2")) {
/*  794 */       place = "&f#2";
/*  795 */     } else if (text.contains("3")) {
/*  796 */       place = "&6#3";
/*      */     } 
/*  798 */     return place;
/*      */   }
/*      */   
/*      */   public static String getStatsCategoryName(String text) {
/*  802 */     String category = "§cN/A";
/*  803 */     if (text.contains("Убийств")) {
/*  804 */       category = "&aКилов";
/*  805 */     } else if (text.contains("Смертей")) {
/*  806 */       category = "&cСмертей";
/*  807 */     } else if (text.contains("Игр")) {
/*  808 */       category = "&bКаток";
/*  809 */     } else if (text.contains("Побед")) {
/*  810 */       category = "&eПобед";
/*  811 */     } else if (text.contains("Сломано кроватей")) {
/*  812 */       category = "&dКроватей";
/*      */     } 
/*  814 */     return category;
/*      */   }
/*      */   
/*      */   public static String highLightExtras(String text) {
/*  818 */     text = highLightDiscord(text);
/*  819 */     if (text.charAt(0) == '!') text = text.substring(1);
/*      */     
/*  821 */     assert Minecraft.func_71410_x() == null || (Minecraft.func_71410_x()).field_71439_g == null;
/*  822 */     String player_name = (Minecraft.func_71410_x()).field_71439_g.func_70005_c_();
/*      */     
/*  824 */     if (ColorCodesManager.removeColorCodes(text).contains(player_name)) {
/*  825 */       playSound(SOUND_PLAYER_STATS);
/*      */     }
/*  827 */     return text;
/*      */   }
/*      */   
/*      */   public static String getDiscordFromString(String text) {
/*  831 */     if (text.contains("#")) {
/*      */       
/*  833 */       String[] split = text.split(Pattern.quote("#"));
/*  834 */       if (split.length < 2) return null; 
/*      */       try {
/*  836 */         String nickname = split[0].split(" ")[(split[0].split(" ")).length - 1].trim();
/*  837 */         String hash = split[1].split(" ")[0].substring(0, 4).trim();
/*  838 */         if (hash.length() != 4) return text; 
/*  839 */         int hash_value = Integer.parseInt(hash);
/*  840 */         String discord = nickname + "#" + hash;
/*  841 */         return ColorCodesManager.removeColorCodes(discord);
/*  842 */       } catch (Exception ex) {
/*  843 */         return null;
/*      */       } 
/*      */     } 
/*  846 */     return null;
/*      */   }
/*      */   
/*      */   public static String highLightDiscord(String text) {
/*  850 */     String color = "&9";
/*  851 */     text = text.replace(" дс ", color + " дс &f").replace(" Дс ", color + " Дс &f").replace(" ДС ", color + " ДС &f");
/*      */     
/*  853 */     String discord = getDiscordFromString(text);
/*  854 */     if (discord != null) text = text.replace(discord, color + "" + discord + "&f");
/*      */     
/*  856 */     return text;
/*      */   }
/*      */   
/*      */   public static void playSound(String name) {
/*  860 */     playSound(name, 1.0F);
/*      */   }
/*      */   
/*      */   public static void playSound(String name, float volume) {
/*  864 */     assert Minecraft.func_71410_x() == null || (Minecraft.func_71410_x()).field_71439_g == null;
/*  865 */     (Minecraft.func_71410_x()).field_71439_g.func_85030_a(name, volume, 1.0F);
/*      */   }
/*      */   
/*      */   public static boolean isItFinalKill(String player_name) {
/*  869 */     String name = ColorCodesManager.removeColorCodes(player_name).trim();
/*  870 */     List<CustomScoreboard.BedwarsTeam> teams = CustomScoreboard.readBedwarsGame();
/*  871 */     for (CustomScoreboard.BedwarsTeam t : teams) {
/*  872 */       for (CustomScoreboard.BedwarsPlayer p : t.players) {
/*  873 */         if (p.name.equals(name)) {
/*  874 */           if (t.state != CustomScoreboard.TEAM_STATE.BED_ALIVE) {
/*  875 */             return true;
/*      */           }
/*  877 */           return false;
/*      */         } 
/*      */       } 
/*      */     } 
/*  881 */     return false;
/*      */   }
/*      */   
/*      */   public static void clearGame() {
/*  885 */     GAME_BED = null;
/*  886 */     IS_IN_GAME = false;
/*  887 */     Main.minimap.clearGameBeds();
/*      */   }
/*      */   
/*      */   public static void readPlayerBase() {
/*  891 */     int delay = 2000;
/*  892 */     (new Timer()).schedule(new TimerTask()
/*      */         {
/*      */           public void run()
/*      */           {
/*  896 */             if (Minecraft.func_71410_x() == null || (Minecraft.func_71410_x()).field_71439_g == null)
/*      */               return; 
/*  898 */             (Minecraft.func_71410_x()).field_71439_g.refreshDisplayName();
/*      */             
/*  900 */             Main.namePlateRenderer.printSameUsersInGame();
/*      */             
/*  902 */             EntityPlayerSP entityPlayerSP = (Minecraft.func_71410_x()).field_71439_g;
/*  903 */             ArrayList<BWBed> beds = HintsBedScanner.findBeds(entityPlayerSP.func_180425_c().func_177958_n(), entityPlayerSP.func_180425_c().func_177956_o(), entityPlayerSP.func_180425_c().func_177952_p());
/*  904 */             if (beds.size() == 0) {
/*  905 */               ChatSender.addText(MyChatListener.PREFIX_HINT + "&cБаза не найдена!");
/*      */             } else {
/*      */               
/*  908 */               MyChatListener.GAME_BED = null;
/*  909 */               int min_dist = 999999;
/*  910 */               for (BWBed b : beds) {
/*  911 */                 int dist = (int)Math.sqrt(Math.pow(b.part1_posX - ((EntityPlayer)entityPlayerSP).field_70165_t, 2.0D) + Math.pow(b.part1_posZ - ((EntityPlayer)entityPlayerSP).field_70161_v, 2.0D));
/*  912 */                 if (dist < min_dist) {
/*  913 */                   min_dist = dist;
/*  914 */                   MyChatListener.GAME_BED = b;
/*      */                 } 
/*      */               } 
/*      */               
/*  918 */               if (MyChatListener.GAME_BED == null) {
/*  919 */                 ChatSender.addText(MyChatListener.PREFIX_HINT + "&cБаза не найдена, ошибка!");
/*      */ 
/*      */                 
/*      */                 return;
/*      */               } 
/*      */ 
/*      */               
/*  926 */               Main.hintsBaseRadar.setBase(MyChatListener.GAME_BED.part1_posX, MyChatListener.GAME_BED.part1_posY, MyChatListener.GAME_BED.part1_posZ);
/*      */             } 
/*      */           }
/*      */         }delay);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static String handleTeamDestruction(String player_name) {
/*      */     try {
/*  936 */       String player_team_color = player_name.substring(0, 2);
/*  937 */       CustomScoreboard.TEAM_COLOR team_color = CustomScoreboard.getTeamColorByCode(player_team_color);
/*  938 */       List<CustomScoreboard.BedwarsTeam> teams = CustomScoreboard.readBedwarsGame();
/*  939 */       for (CustomScoreboard.BedwarsTeam t : teams) {
/*  940 */         if (t.color == team_color && (
/*  941 */           t.state == CustomScoreboard.TEAM_STATE.DESTROYED || (t.state == CustomScoreboard.TEAM_STATE.BED_BROKEN && t.players.size() == 1))) {
/*  942 */           playSound(SOUND_TEAM_DESTROYED);
/*  943 */           return "\n" + PREFIX_TEAM + player_team_color + "Уничтожены &l" + CustomScoreboard.getTeamNameByTeamColor(team_color) + "";
/*      */         }
/*      */       
/*      */       } 
/*  947 */     } catch (Exception ex) {
/*  948 */       ex.printStackTrace();
/*      */     } 
/*  950 */     return "";
/*      */   }
/*      */   
/*      */   public static boolean isUserMuted(String player_name) {
/*  954 */     if (Main.getConfigString(Main.CONFIG_MSG.MUTED_PLAYERS) == null) return false; 
/*  955 */     String[] muted_players = Main.getConfigString(Main.CONFIG_MSG.MUTED_PLAYERS).split(",");
/*  956 */     for (String p : muted_players) {
/*  957 */       if (p.length() > 1 && p.equals(player_name)) {
/*  958 */         return true;
/*      */       }
/*      */     } 
/*  961 */     return false;
/*      */   }
/*      */   
/*      */   public static String getStrikeMessageVictim(String text) {
/*  965 */     return text.substring(0, 2) + "&m" + text.substring(2);
/*      */   }
/*      */   
/*      */   public static String getNumberEnding(int cnt, String case1, String case2, String case3) {
/*  969 */     String e = case3;
/*      */     
/*  971 */     char last_num = Integer.toString(cnt).charAt(Integer.toString(cnt).length() - 1);
/*  972 */     switch (Integer.parseInt(Character.toString(last_num))) {
/*      */       case 1:
/*  974 */         e = case1;
/*      */         break;
/*      */       case 2:
/*      */       case 3:
/*      */       case 4:
/*  979 */         e = case2;
/*      */         break;
/*      */       case 0:
/*      */       case 5:
/*      */       case 6:
/*      */       case 7:
/*      */       case 8:
/*      */       case 9:
/*  987 */         e = case3;
/*      */         break;
/*      */     } 
/*      */     
/*  991 */     if (cnt >= 11 && cnt <= 14) {
/*  992 */       e = case3;
/*      */     }
/*  994 */     return e;
/*      */   }
/*      */   
/*      */   public static void addBedwarsMeowMessageToQuee(String s, boolean isInGameOnly) {
/*  998 */     if (meowMessagesQuee == null) meowMessagesQuee = new ArrayList<MsgMeowQuee>(); 
/*  999 */     meowMessagesQuee.add(new MsgMeowQuee(s, (new Date()).getTime(), isInGameOnly));
/*      */   }
/*      */ 
/*      */   
/* 1003 */   private static long time_last_meow_message = -1L;
/* 1004 */   private static MsgMeowQuee quee_last_meow_message = null;
/*      */   
/*      */   static void handleBedwarsMeowMessagesQuee() {
/* 1007 */     TIME_MEOW_MESSAGES_CHECK_FREQUENCY = 1000;
/*      */     
/* 1009 */     if (meowMessagesQuee.size() <= 0)
/*      */       return; 
/* 1011 */     long t = (new Date()).getTime();
/* 1012 */     if (time_last_meow_message != -1L && t - time_last_meow_message < TIME_MEOW_MESSAGES_CHECK_FREQUENCY) {
/*      */       return;
/*      */     }
/*      */     
/* 1016 */     time_last_meow_message = t;
/*      */     
/* 1018 */     MsgMeowQuee m = meowMessagesQuee.get(0);
/* 1019 */     if (quee_last_meow_message == null) {
/* 1020 */       quee_last_meow_message = m;
/* 1021 */       quee_last_meow_message.time = t;
/*      */     } 
/*      */     
/* 1024 */     if (t - quee_last_meow_message.time > 4000L) {
/* 1025 */       ChatSender.addText(PREFIX_BEDWARS_MEOW + "&cВ СООБЩЕНИИ ЗАПРЕЩЕННЫЕ СЛОВА:\n &7- &f" + quee_last_meow_message.text);
/* 1026 */       meowMessagesQuee.remove(quee_last_meow_message);
/* 1027 */       quee_last_meow_message = null;
/*      */       
/*      */       return;
/*      */     } 
/* 1031 */     if (m.isInGameOnly && !IS_IN_GAME) {
/* 1032 */       meowMessagesQuee.remove(quee_last_meow_message);
/* 1033 */       quee_last_meow_message = null;
/*      */       
/*      */       return;
/*      */     } 
/* 1037 */     ChatSender.sendText("!" + m.text);
/*      */   }
/*      */   
/*      */   static void checkBedwarsMeowMessagesQueelastMessage(String text) {
/* 1041 */     if (meowMessagesQuee.size() <= 0 || quee_last_meow_message == null)
/*      */       return; 
/* 1043 */     if (ColorCodesManager.removeColorCodes(text).contains(ColorCodesManager.removeColorCodes(quee_last_meow_message.text))) {
/* 1044 */       meowMessagesQuee.remove(quee_last_meow_message);
/* 1045 */       quee_last_meow_message = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   static void handleBedwarsMeowKill(ClientChatReceivedEvent e, String p_killer, String p_victim) {
/* 1050 */     handleBedwarsMeowKillType(e, p_killer, p_victim, BedwarsMeow.MsgCase.KILL);
/*      */   }
/*      */   static void handleBedwarsMeowFinalKill(ClientChatReceivedEvent e, String p_killer, String p_victim) {
/* 1053 */     handleBedwarsMeowKillType(e, p_killer, p_victim, BedwarsMeow.MsgCase.FINAL_KILL);
/*      */   }
/*      */   static void handleBedwarsMeowKillType(ClientChatReceivedEvent e, String p_killer, String p_victim, BedwarsMeow.MsgCase msgcase) {
/* 1056 */     String killer = ColorCodesManager.removeColorCodes(p_killer).trim();
/* 1057 */     String victim = ColorCodesManager.removeColorCodes(p_victim).trim();
/*      */     
/* 1059 */     String mod_player_name = (Minecraft.func_71410_x()).field_71439_g.func_70005_c_();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1066 */     if (msgcase == BedwarsMeow.MsgCase.KILL && mod_player_name.equals(victim)) {
/* 1067 */       GAME_total_death++;
/* 1068 */       String str = Main.bedwarsMeow.getNextMessage(BedwarsMeow.MsgCase.DEATH, p_killer);
/* 1069 */       if (str == null)
/* 1070 */         return;  addBedwarsMeowMessageToQuee(str, true);
/*      */     } 
/*      */ 
/*      */     
/* 1074 */     if (!mod_player_name.equals(killer))
/* 1075 */       return;  GAME_total_kills++;
/* 1076 */     String s = Main.bedwarsMeow.getNextMessage(msgcase, p_victim);
/* 1077 */     if (s == null)
/* 1078 */       return;  addBedwarsMeowMessageToQuee(s, true);
/*      */   }
/*      */ 
/*      */   
/*      */   static void handleBedwarsMeowBed(CustomScoreboard.TEAM_COLOR team_color, String p_player) {
/* 1083 */     String player = ColorCodesManager.removeColorCodes(p_player).trim();
/* 1084 */     if (!(Minecraft.func_71410_x()).field_71439_g.func_70005_c_().equals(player))
/*      */       return; 
/* 1086 */     GAME_total_beds++;
/*      */     
/* 1088 */     boolean isSingleMode = false;
/* 1089 */     List<CustomScoreboard.BedwarsTeam> teams = CustomScoreboard.readBedwarsGame();
/* 1090 */     for (CustomScoreboard.BedwarsTeam t : teams) {
/* 1091 */       if (t.color == team_color) {
/* 1092 */         if (t.players.size() <= 1) isSingleMode = true;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/* 1097 */     BedwarsMeow.MsgCase msgcase = BedwarsMeow.MsgCase.BED_MULTI;
/* 1098 */     if (isSingleMode) msgcase = BedwarsMeow.MsgCase.BED_SINGLE;
/*      */     
/* 1100 */     String s = Main.bedwarsMeow.getNextMessage(msgcase, CustomScoreboard.getCodeByTeamColor(team_color));
/* 1101 */     if (s == null)
/* 1102 */       return;  addBedwarsMeowMessageToQuee(s, true);
/*      */   }
/*      */   
/*      */   static void handleBedwarsMeowWin(String p_team_name) {
/* 1106 */     CustomScoreboard.TEAM_COLOR team_color = CustomScoreboard.getTeamColorByName(p_team_name);
/* 1107 */     CustomScoreboard.TEAM_COLOR mod_team_color = getEntityTeamColor((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g);
/*      */     
/* 1109 */     if (mod_team_color == team_color)
/*      */       return; 
/* 1111 */     String s = Main.bedwarsMeow.getNextMessage(BedwarsMeow.MsgCase.WIN, "");
/* 1112 */     if (s == null)
/* 1113 */       return;  addBedwarsMeowMessageToQuee(s, true);
/*      */   }
/*      */ 
/*      */   
/*      */   static void handleBedwarsMeowGameGreeting() {
/* 1118 */     (new Timer()).schedule(new TimerTask()
/*      */         {
/*      */           public void run()
/*      */           {
/* 1122 */             String s = Main.bedwarsMeow.getNextMessage(BedwarsMeow.MsgCase.GAME_START, "");
/* 1123 */             if (s == null)
/* 1124 */               return;  MyChatListener.addBedwarsMeowMessageToQuee(s, true);
/*      */           }
/*      */         }500L);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void handleReceivedMessage(ClientChatReceivedEvent e, ChatMessage chatMessage) {
/*      */     String victim, new_time, player_name, text, message_hover_text, n, p_name, place, player, this_text, split[], category, placeColor;
/*      */     int cnt, place_cnt;
/* 1133 */     String str = e.message.func_150254_d();
/* 1134 */     ChatMessage msg = chatMessage;
/* 1135 */     String[] vals = msg.element_values;
/*      */ 
/*      */ 
/*      */     
/* 1139 */     checkBedwarsMeowMessagesQueelastMessage(e.message.func_150260_c());
/*      */     
/* 1141 */     if (removeNextMessage == true) {
/* 1142 */       removeNextMessage = false;
/* 1143 */       deleteMessage(e);
/*      */       
/*      */       return;
/*      */     } 
/* 1147 */     switch (msg.type) {
/*      */ 
/*      */       
/*      */       case CHAT_LEFT_GAME:
/* 1151 */         victim = vals[1];
/* 1152 */         if (isItFinalKill(victim)) {
/* 1153 */           str = getStrikeMessageVictim(victim) + "&r &7ливнул";
/* 1154 */           str = str + handleTeamDestruction(victim);
/*      */         } else {
/* 1156 */           str = victim + " &r&7ливнул";
/*      */         } 
/* 1158 */         setMessageText(e, str);
/* 1159 */         updateScoreboard();
/*      */         break;
/*      */       case CHAT_GAME_STARTED:
/* 1162 */         str = "§r§f                            &c&lB&6&le&e&ld&a&lW&b&la&9&lr&d&ls\n";
/*      */         
/* 1164 */         FileManager.writeToFile("========", "resourceBedwarslog.txt", true);
/*      */         
/* 1166 */         if (HintsValidator.isBedScannerActive()) readPlayerBase(); 
/* 1167 */         IS_IN_GAME = true;
/*      */         
/* 1169 */         initGameStats();
/*      */         
/* 1171 */         Main.updateAllBooleans();
/*      */         
/* 1173 */         setMessageText(e, str);
/*      */         
/* 1175 */         handleBedwarsMeowGameGreeting();
/*      */         
/* 1177 */         LobbyBlockPlacer.state = false;
/*      */         break;
/*      */       
/*      */       case CHAT_BEDWARS_GAME_STARTED_TIPS:
/* 1181 */         if (IS_MOD_ACTIVE) deleteMessage(e); 
/*      */         break;
/*      */       case CHAT_JOINED_MIDGAME:
/* 1184 */         str = "§r" + vals[1] + " §r§7подключился";
/* 1185 */         setMessageText(e, str);
/* 1186 */         updateScoreboard();
/*      */         break;
/*      */       case CHAT_JOINED_PREGAME:
/* 1189 */         str = "&7" + formatChatPlayerName(e.message, vals[1], "&7") + " &r&7подключился &a" + vals[2] + "&7/&e" + vals[3];
/* 1190 */         setMessageText(e, str);
/* 1191 */         clearGame();
/*      */         break;
/*      */       case CHAT_LEFT_PREGAME:
/* 1194 */         str = "&7" + formatChatPlayerName(e.message, vals[1], "&7") + " &r&7ливнул &c" + vals[2] + "&7/&e" + vals[3];
/* 1195 */         setMessageText(e, str);
/* 1196 */         clearGame();
/*      */         break;
/*      */       case CHAT_SUICIDE:
/* 1199 */         victim = vals[1];
/* 1200 */         if (isItFinalKill(victim)) {
/* 1201 */           str = "&r&f⚔ " + getStrikeMessageVictim(victim);
/* 1202 */           str = str + handleTeamDestruction(victim);
/*      */         } else {
/* 1204 */           str = "&r&f⚔ " + victim;
/*      */           
/* 1206 */           if (ColorCodesManager.removeColorCodes(victim).trim().equals((Minecraft.func_71410_x()).field_71439_g.func_70005_c_())) GAME_total_death++; 
/*      */         } 
/* 1208 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_SUICIDE_VOID:
/* 1211 */         victim = vals[1];
/* 1212 */         if (isItFinalKill(victim)) {
/* 1213 */           str = "&r&f⚔ " + getStrikeMessageVictim(victim);
/* 1214 */           str = str + handleTeamDestruction(victim);
/*      */         } else {
/* 1216 */           str = "&r&f⚔ " + victim;
/*      */           
/* 1218 */           if (ColorCodesManager.removeColorCodes(victim).trim().equals((Minecraft.func_71410_x()).field_71439_g.func_70005_c_())) GAME_total_death++; 
/*      */         } 
/* 1220 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_KILLED_BY_VOID:
/* 1223 */         victim = vals[1];
/* 1224 */         if (isItFinalKill(victim)) {
/* 1225 */           str = "&r" + vals[2] + " &f⚔ &r" + getStrikeMessageVictim(victim);
/* 1226 */           str = str + handleTeamDestruction(victim);
/* 1227 */           setMessageText(e, str);
/*      */           
/* 1229 */           handleBedwarsMeowFinalKill(e, vals[2], victim); break;
/*      */         } 
/* 1231 */         str = "&r" + vals[2] + " &f⚔ &r" + victim;
/* 1232 */         setMessageText(e, str);
/*      */         
/* 1234 */         handleBedwarsMeowKill(e, vals[2], victim);
/*      */         break;
/*      */ 
/*      */       
/*      */       case CHAT_KILLED_BY_PLAYER:
/* 1239 */         victim = vals[1];
/* 1240 */         if (isItFinalKill(victim)) {
/* 1241 */           str = "&r" + vals[2] + " &f⚔ &r" + getStrikeMessageVictim(victim);
/* 1242 */           str = str + handleTeamDestruction(victim);
/*      */           
/* 1244 */           setMessageText(e, str);
/* 1245 */           handleBedwarsMeowFinalKill(e, vals[2], victim);
/*      */           
/* 1247 */           EntityPlayerSP entityPlayerSP = (Minecraft.func_71410_x()).field_71439_g;
/* 1248 */           if (ColorCodesManager.removeColorCodes(vals[2]).equals(entityPlayerSP.func_70005_c_())) {
/*      */ 
/*      */             
/* 1251 */             MovingObjectPosition ray = entityPlayerSP.func_174822_a(3.0D, 1.0F);
/*      */             
/* 1253 */             if (ray == null)
/* 1254 */               break;  int posX = (int)ray.field_72307_f.field_72450_a;
/* 1255 */             int posY = (int)ray.field_72307_f.field_72448_b;
/* 1256 */             int posZ = (int)ray.field_72307_f.field_72449_c;
/* 1257 */             CustomScoreboard.TEAM_COLOR team_color = CustomScoreboard.getTeamColorByCode(victim.substring(0, 2));
/* 1258 */             ParticleController.spawnFinalKillParticles(posX, posY, posZ, team_color);
/*      */           }  break;
/*      */         } 
/* 1261 */         str = "&r" + vals[2] + " &f⚔ &r" + victim;
/*      */         
/* 1263 */         setMessageText(e, str);
/* 1264 */         handleBedwarsMeowKill(e, vals[2], victim);
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case CHAT_TEAM_DESTROYED:
/* 1271 */         if (IS_MOD_ACTIVE) deleteMessage(e);
/*      */         
/*      */         break;
/*      */       
/*      */       case CHAT_TEAM_BED_BROKEN:
/* 1276 */         if (IS_MOD_ACTIVE) {
/* 1277 */           str = PREFIX_BED + getTeamColor(vals[2]) + "Минус " + getTeamBoldName(vals[2]);
/* 1278 */           deleteMessage(e);
/* 1279 */           ChatSender.addHoverText(str, "&7Сломал &f" + vals[1]);
/*      */         } 
/*      */         
/* 1282 */         playSound(SOUND_BED_BROKEN);
/*      */         
/* 1284 */         HintsBaseRadar.checkBedState();
/*      */         
/* 1286 */         handleBedwarsMeowBed(CustomScoreboard.getTeamColorByCode(getTeamColor(vals[2])), vals[1]);
/*      */         break;
/*      */       
/*      */       case CHAT_TEAM_COLOR_CHOSEN:
/* 1290 */         str = "&r" + getTeamColor(vals[1]) + "Выбрана тима " + getTeamBoldName(vals[1]);
/* 1291 */         setMessageText(e, str);
/* 1292 */         playSound(SOUND_TEAM_CHOSEN);
/*      */         break;
/*      */       case CHAT_TEAM_ALREADY_CHOSEN:
/* 1295 */         str = "&cТима уже выбрана!";
/* 1296 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_TEAM_IS_FULL:
/* 1299 */         str = "&cТима заполнена!";
/* 1300 */         setMessageText(e, str);
/* 1301 */         clearGame();
/*      */         break;
/*      */       case CHAT_SHOP_ITEM_BOUGHT:
/* 1304 */         str = "&r&7+ &e" + vals[1] + " &a" + vals[0];
/*      */         
/* 1306 */         FileManager.writeToFile(vals[0] + ";" + vals[1] + ";&;", "resourceBedwarslog.txt", true);
/*      */         
/* 1308 */         IS_IN_GAME = true;
/*      */         
/* 1310 */         if (Main.getConfigBool(Main.CONFIG_MSG.REMOVE_BUY_MESSAGE)) { deleteMessage(e); break; }
/* 1311 */          setMessageText(e, str);
/*      */         break;
/*      */       
/*      */       case CHAT_SHOP_NOT_ENOUGH_RESOURCES:
/* 1315 */         str = "&r&cНет ресов!";
/*      */         
/* 1317 */         if (Main.getConfigBool(Main.CONFIG_MSG.REMOVE_BUY_MESSAGE)) { deleteMessage(e); break; }
/* 1318 */          setMessageText(e, str);
/*      */         break;
/*      */       
/*      */       case CHAT_UPGRADE_BOUGHT:
/* 1322 */         if (IS_MOD_ACTIVE) {
/* 1323 */           str = "&b&l" + vals[2] + " &a" + vals[3] + " &7прокачаны";
/* 1324 */           deleteMessage(e);
/* 1325 */           ChatSender.addHoverText(str, "&7Прокачал &b" + vals[1]);
/*      */         } 
/*      */         
/* 1328 */         playSound(SOUND_UPGRADE_BOUGHT, 0.5F);
/*      */         break;
/*      */       case CHAT_GENERATOR_DIAMOND_LEVELED_UP:
/* 1331 */         new_time = getGeneratorDiamondUpgradeTime(vals[1]);
/* 1332 */         str = "&bАлмазы &7вкачаны до &a" + vals[1] + " &7- каждые &e" + new_time + " &7сек";
/*      */         try {
/* 1334 */           Main.generatorTimers.setMaxTimeDiamonds(Integer.parseInt(new_time));
/* 1335 */         } catch (Exception exception) {}
/*      */         
/* 1337 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_GENERATOR_EMERALD_LEVELED_UP:
/* 1340 */         new_time = getGeneratorEmeraldUpgradeTime(vals[1]);
/* 1341 */         str = "&aИзумруды &7вкачаны до &a" + vals[1] + " &7- каждые &e" + new_time + " &7сек";
/*      */         try {
/* 1343 */           Main.generatorTimers.setMaxTimeEmeralds(Integer.parseInt(new_time));
/* 1344 */         } catch (Exception exception) {}
/*      */         
/* 1346 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_TRAP_ACTIVATED:
/* 1349 */         str = "&c&k===&r &c&lВРАГ НА БАЗЕ &c&k===";
/* 1350 */         str = str + "\n" + str + "\n" + str;
/* 1351 */         setMessageText(e, str);
/* 1352 */         playSound(SOUND_TRAP_ACTIVATED);
/*      */         break;
/*      */       case CHAT_SERVER_RESTART:
/* 1355 */         str = "&cВыход в лобби через &c&l" + vals[0] + " &cсек";
/* 1356 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_TELEPORTATION_TO_HUB:
/* 1359 */         str = "&8ТП в лобби...";
/* 1360 */         clearGame();
/* 1361 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_CONNECTING_TO_LOBBY:
/* 1364 */         IS_IN_GAME = false;
/* 1365 */         str = "&8Подключение к " + vals[0];
/* 1366 */         clearGame();
/* 1367 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_HUB_CHAT_PLAYER_MESSAGE:
/* 1370 */         clearGame();
/*      */         
/* 1372 */         player_name = formatChatPlayerName(e.message, vals[0], "&7");
/* 1373 */         text = vals[2].replaceFirst("§7§7", "");
/* 1374 */         message_hover_text = getHoverMessage(e.message);
/*      */         
/* 1376 */         if (Main.playerManipulator.handleCommand(player_name, vals[2].replaceFirst("§7§7", ""))) {
/* 1377 */           deleteMessage(e);
/*      */           
/*      */           break;
/*      */         } 
/* 1381 */         if (message_hover_text.contains(" ") && Main.emotesDrawer.handleChat(message_hover_text.split(" ")[1].trim(), text)) {
/* 1382 */           deleteMessage(e); break;
/*      */         } 
/* 1384 */         if (Main.emotesDrawer.handleChat(player_name, text)) {
/* 1385 */           deleteMessage(e);
/*      */           
/*      */           break;
/*      */         } 
/* 1389 */         if (IS_MOD_ACTIVE) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1394 */           if (isUserMuted(player_name) == true) {
/* 1395 */             deleteMessage(e);
/*      */             
/*      */             break;
/*      */           } 
/*      */           
/* 1400 */           String discord_string = getDiscordFromString(text);
/* 1401 */           String sender_name = player_name;
/* 1402 */           if (message_hover_text.length() > 0) {
/* 1403 */             String[] arrayOfString = message_hover_text.split(" ");
/* 1404 */             if (arrayOfString.length >= 2) {
/* 1405 */               String donation = ColorCodesManager.removeColorCodes(arrayOfString[0].trim());
/* 1406 */               String donation_color = "&d";
/*      */               
/* 1408 */               if (donation.contains("Игрок")) {
/* 1409 */                 donation = "";
/* 1410 */                 donation_color = "&7";
/* 1411 */               } else if (donation.contains("GOLD")) {
/* 1412 */                 donation_color = "&e";
/* 1413 */               } else if (donation.contains("DIAMOND")) {
/* 1414 */                 donation_color = "&b";
/* 1415 */               } else if (donation.contains("EMERALD")) {
/* 1416 */                 donation_color = "&a";
/* 1417 */               } else if (donation.contains("MAGMA")) {
/* 1418 */                 donation_color = "&6";
/* 1419 */               } else if (donation.contains("LEGEND")) {
/* 1420 */                 donation_color = "&c";
/*      */               } 
/*      */               
/* 1423 */               String battle_stats = "";
/* 1424 */               String hover_data = ColorCodesManager.removeColorCodes(message_hover_text);
/* 1425 */               if (hover_data.contains("Убийств ▸") && hover_data.contains("K/D ▸ ") && Main.getConfigBool(Main.CONFIG_MSG.ENABLE_BETTER_CHAT_STATISTIC_PREFIX)) {
/*      */                 
/*      */                 try {
/*      */                   
/* 1429 */                   int kills = Integer.parseInt(hover_data.split("Убийств ▸")[1].trim().split(" ")[0].trim().replace(",", ""));
/* 1430 */                   int games_cnt = Integer.parseInt(hover_data.split("Игр ▸")[1].trim().split(" ")[0].trim().replace(",", ""));
/* 1431 */                   int wins_cnt = Integer.parseInt(hover_data.split("Побед ▸")[1].trim().split(" ")[0].trim().replace(",", ""));
/*      */ 
/*      */                   
/* 1434 */                   String kills_s = (kills / 1000) + "к";
/* 1435 */                   kills_s = (new DecimalFormat("0.0")).format((kills / 1000.0F)) + "к";
/* 1436 */                   if (kills > 100) {
/* 1437 */                     String kills_color = "&c";
/* 1438 */                     if (kills < 500) { kills_color = "&c"; }
/* 1439 */                     else if (kills < 1000) { kills_color = "&6"; }
/* 1440 */                     else if (kills < 2000) { kills_color = "&e"; }
/* 1441 */                     else if (kills < 5000) { kills_color = "&a"; }
/* 1442 */                     else if (kills < 10000) { kills_color = "&a&l"; }
/* 1443 */                     else if (kills < 20000) { kills_color = "&a&l&n"; }
/* 1444 */                     else if (kills >= 40000) { kills_color = "&b&l&n"; }
/*      */                     
/* 1446 */                     String kdr_color = "&c";
/* 1447 */                     double kdr = Double.parseDouble(hover_data.split("K/D ▸ ")[1].trim().split(" ")[0].split("\n")[0].trim());
/* 1448 */                     if (kdr < 1.0D) { kdr_color = "&c"; }
/* 1449 */                     else if (kdr < 1.5D) { kdr_color = "&6"; }
/* 1450 */                     else if (kdr < 2.0D) { kdr_color = "&e"; }
/* 1451 */                     else if (kdr < 3.0D) { kdr_color = "&a"; }
/* 1452 */                     else if (kdr < 4.0D) { kdr_color = "&a&l"; }
/* 1453 */                     else if (kdr < 5.0D) { kdr_color = "&a&l&n"; }
/* 1454 */                     else if (kdr >= 5.0D) { kdr_color = "&b&l&n"; }
/*      */                     
/* 1456 */                     String wr_color = "&c";
/* 1457 */                     int wr = -1;
/* 1458 */                     if (games_cnt > 0) {
/* 1459 */                       wr = (int)(wins_cnt * 100.0F / games_cnt);
/* 1460 */                       if (wr < 10) { wr_color = "&c"; }
/* 1461 */                       else if (wr < 20) { wr_color = "&6"; }
/* 1462 */                       else if (wr < 30) { wr_color = "&e"; }
/* 1463 */                       else if (wr < 40) { wr_color = "&a"; }
/* 1464 */                       else if (wr < 50) { wr_color = "&a&l"; }
/* 1465 */                       else if (wr < 60) { wr_color = "&a&l&n"; }
/* 1466 */                       else if (wr >= 70) { wr_color = "&b&l&n"; }
/*      */                     
/*      */                     } 
/* 1469 */                     battle_stats = "&7[" + kills_color + kills_s + "&8, " + kdr_color + kdr + ((wr >= 0) ? ("&8, " + wr_color + wr + "%") : "") + "&7]";
/*      */                   } 
/* 1471 */                 } catch (Exception exception) {}
/*      */               }
/*      */ 
/*      */ 
/*      */               
/* 1476 */               String real_name = ColorCodesManager.removeColorCodes(arrayOfString[1].trim());
/*      */               
/* 1478 */               sender_name = ((battle_stats.length() > 0) ? (battle_stats + " ") : "") + donation_color + donation + ((donation.length() > 0) ? " &l" : "") + real_name;
/*      */               
/* 1480 */               String mod_prefix = Main.namePlateRenderer.getPrefixByName(real_name);
/* 1481 */               if (mod_prefix == null) mod_prefix = "";
/*      */               
/* 1483 */               sender_name = mod_prefix + sender_name.trim();
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 1489 */           str = sender_name + "&7 → &f" + highLightExtras(text);
/*      */           
/* 1491 */           if (discord_string != null) {
/* 1492 */             deleteMessage(e);
/* 1493 */             ChatSender.addClickSuggestAndHoverText(str, "&fНажми, чтоб скопировать \"&e" + discord_string + "&f\"", discord_string); break;
/*      */           } 
/* 1495 */           deleteMessage(e);
/* 1496 */           ChatSender.addClickSuggestAndHoverText(str, message_hover_text, ColorCodesManager.removeColorCodes(player_name));
/*      */         } 
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case CHAT_YOU_WERE_KICKED:
/* 1503 */         clearGame();
/* 1504 */         str = "&cТебя кикнули";
/* 1505 */         setMessageText(e, str);
/* 1506 */         playSound(SOUND_REJECT);
/*      */         break;
/*      */       case CHAT_ADS:
/* 1509 */         deleteMessage(e);
/*      */         break;
/*      */       case CHAT_GAME_STARTS_IN_SECONDS:
/* 1512 */         clearGame();
/* 1513 */         str = "&eКатка начнется через &c&l" + vals[1] + "";
/* 1514 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_GAME_CHAT_LOCAL:
/* 1517 */         if (IS_MOD_ACTIVE) {
/* 1518 */           str = "&r" + vals[0] + "<Тиме> " + formatChatPlayerName(e.message, vals[1], vals[0], true) + " &8→ &f" + vals[3];
/* 1519 */           deleteMessage(e);
/* 1520 */           String name2copy = ColorCodesManager.removeColorCodes(vals[1]);
/* 1521 */           if ((name2copy.split(" ")).length == 2) name2copy = name2copy.split(" ")[1].trim().replaceFirst("~", ""); 
/* 1522 */           ChatSender.addClickSuggestAndHoverText(str, vals[1], name2copy);
/*      */         } 
/*      */         break;
/*      */       
/*      */       case CHAT_GAME_CHAT_GLOBAL:
/* 1527 */         n = formatChatPlayerName(e.message, vals[1], vals[0], true);
/* 1528 */         if (n.contains(" ")) n = n.split(" ")[1].trim(); 
/* 1529 */         if (Main.playerManipulator.handleCommand(ColorCodesManager.removeColorCodes(n), vals[3])) {
/* 1530 */           deleteMessage(e);
/*      */           
/*      */           break;
/*      */         } 
/* 1534 */         if (Main.emotesDrawer.handleChat(n.replace("*", "").trim(), vals[3])) {
/* 1535 */           deleteMessage(e);
/*      */           
/*      */           break;
/*      */         } 
/* 1539 */         if (IS_MOD_ACTIVE) {
/* 1540 */           str = "&r" + vals[0] + "" + formatChatPlayerName(e.message, vals[1], vals[0], true) + " &8→ &f" + vals[3];
/* 1541 */           deleteMessage(e);
/* 1542 */           String name2copy = ColorCodesManager.removeColorCodes(vals[1]);
/* 1543 */           if ((name2copy.split(" ")).length == 2) name2copy = name2copy.split(" ")[1].trim().replaceFirst("~", ""); 
/* 1544 */           ChatSender.addClickSuggestAndHoverText(str, vals[1], name2copy);
/*      */         } 
/*      */         break;
/*      */       case CHAT_GAME_CHAT_SPECTATOR:
/* 1548 */         n = formatChatPlayerName(e.message, vals[0], "&7", true);
/*      */         
/* 1550 */         if (n.contains(" ")) n = n.split(" ")[1].trim(); 
/* 1551 */         str = "&7" + n + " &8→ &f" + vals[1];
/*      */         
/* 1553 */         str = "&r&7[Спектатор] " + n + " &8→ &f" + vals[2];
/* 1554 */         setMessageText(e, str);
/*      */         
/* 1556 */         if (Main.emotesDrawer.handleChat(n.replace("*", "").trim(), vals[2])) {
/* 1557 */           deleteMessage(e);
/*      */         }
/*      */         break;
/*      */ 
/*      */       
/*      */       case CHAT_GAME_CHAT_PREGAME:
/* 1563 */         n = formatChatPlayerName(e.message, vals[0], "&7", true);
/*      */         
/* 1565 */         if (n.contains(" ")) n = n.split(" ")[1].trim(); 
/* 1566 */         str = "&7" + n + " &8→ &f" + vals[1];
/* 1567 */         setMessageText(e, str);
/* 1568 */         clearGame();
/*      */         
/* 1570 */         if (Main.emotesDrawer.handleChat(n.replace("*", "").trim(), vals[1])) {
/* 1571 */           deleteMessage(e);
/*      */         }
/*      */         break;
/*      */ 
/*      */       
/*      */       case CHAT_PREGAME_FASTSTART_REJECT:
/* 1577 */         clearGame();
/* 1578 */         str = "&fКупи &c&lд&6&lо&e&lн&a&lа&b&lт &fсначала";
/* 1579 */         setMessageText(e, str);
/* 1580 */         playSound(SOUND_REJECT);
/*      */         break;
/*      */       case CHAT_LOBBY_DONATER_GREETING:
/* 1583 */         clearGame();
/* 1584 */         if (vals[0].contains(" DimChig")) {
/* 1585 */           str = PREFIX_DIMCHIG_JOINED + "&a&lDimChig &fзашел на сервак!";
/* 1586 */           playSound(SOUND_TEAM_DESTROYED);
/*      */           
/* 1588 */           HelloDimChig.handleGreeting();
/*      */         } else {
/*      */           
/* 1591 */           String name = vals[0];
/* 1592 */           if (name.contains(" ")) name = name.split(" ")[0].trim(); 
/* 1593 */           str = "§r§c§l› §r§f" + vals[0] + " &7" + vals[1];
/*      */         } 
/* 1595 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_PLAYER_BANNED:
/* 1598 */         str = PREFIX_ANTICHEAT + "&fДаун &c&l" + ColorCodesManager.removeColorCodes(vals[1]) + " &fчитер и был &cзабанен&f!";
/* 1599 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_GAME_ANTI_CHEAT_KICK:
/* 1602 */         p_name = ColorCodesManager.removeColorCodes(vals[1]);
/*      */         
/* 1604 */         str = PREFIX_ANTICHEAT + "&fДаун &c&l" + p_name + " &fспалился с читами и был &cкикнут&f!";
/* 1605 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_HUB_ANTIFLOOD:
/* 1608 */         str = PREFIX_ANTIFLOOD + "&cЗамучен &l" + vals[1] + " &cза спам!";
/* 1609 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_PREGAME_NOT_ENOUGH_PLAYERS_TO_START:
/* 1612 */         str = "&cПодожди еще игроков";
/* 1613 */         setMessageText(e, str);
/* 1614 */         playSound(SOUND_REJECT);
/*      */         break;
/*      */       case CHAT_BEDWARS_END_TEAM_WON:
/* 1617 */         str = "§r§f                     " + getTeamBoldName(vals[0]) + " &fпобедили!";
/* 1618 */         if (HintsValidator.isWinEmoteActive()) WinEmote.changeWorldBlocks(CustomScoreboard.getTeamColorByCode(vals[0].substring(0, 2))); 
/* 1619 */         setMessageText(e, str);
/* 1620 */         playSound(SOUND_GAME_END);
/*      */         
/* 1622 */         handleBedwarsMeowWin(vals[0]);
/* 1623 */         sendDelayedGameStats();
/*      */         break;
/*      */       
/*      */       case CHAT_BEDWARS_END_TOP_KILLER:
/* 1627 */         place = getTopKillerPlace(vals[1]);
/*      */         
/* 1629 */         player = vals[2];
/* 1630 */         if (player.equals("DimChig")) {
/* 1631 */           player = DIMCHIG_NAME_GOLD;
/*      */         }
/*      */         try {
/* 1634 */           int kills_cnt = Integer.parseInt(vals[3]);
/* 1635 */           String ending = getNumberEnding(kills_cnt, "", "а", "ов");
/*      */           
/* 1637 */           str = "§r§f                  §r" + place + " &8▸ §7" + player + " - &c" + vals[3] + " &fкил" + ending + "";
/* 1638 */           setMessageText(e, str);
/* 1639 */         } catch (Exception exception) {}
/*      */         break;
/*      */       case CHAT_BEDWARS_END_TOP_KILLER_UNKNOWN:
/* 1642 */         place = getTopKillerPlace(vals[0]);
/* 1643 */         str = "§r§f                  §r" + place + " &8▸ §cN/A";
/* 1644 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_YOU_ARE_TELEPORTED_TO_LOBBY_DUE_TO_FULL_ARENA:
/* 1647 */         str = "§rАрена заполнена! Выбери другую";
/* 1648 */         setMessageText(e, str);
/* 1649 */         playSound(SOUND_REJECT);
/*      */         break;
/*      */       case CHAY_YOU_ALREADY_ON_SERVER:
/* 1652 */         str = PREFIX_PARTY + "§cТебя варпнули, но ты уже на сервере!";
/* 1653 */         setMessageText(e, str);
/*      */         break;
/*      */ 
/*      */       
/*      */       case CHAT_LOBBY_PARTY_INVITE:
/* 1658 */         str = PREFIX_PARTY + "&fИнвайт кинут игроку &e" + vals[1];
/* 1659 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_INVITE_REJECTED:
/* 1662 */         str = PREFIX_PARTY + "&fИнвайт на пати &cистек &fигроку &e" + vals[1];
/* 1663 */         setMessageText(e, str);
/* 1664 */         playSound(SOUND_REJECT, 0.7F);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_WARP:
/* 1667 */         str = PREFIX_PARTY + "&fТелепортирую тиммейтов к себе...";
/* 1668 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_PLAYER_OFFLINE:
/* 1671 */         str = PREFIX_PARTY + "&r&cИз пати выгнан &c&l" + vals[1] + " &cза АФК";
/* 1672 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_DISBANDED:
/* 1675 */         str = PREFIX_PARTY + "&cПати разформировано";
/* 1676 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_ALREADY_IN_PARTY:
/* 1679 */         str = PREFIX_PARTY + "&cЭтот чел уже в пати";
/* 1680 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_OFFLINE:
/* 1683 */         str = PREFIX_PARTY + "&cЭтот чел не в сети";
/* 1684 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_PARTY_ON_CREATE:
/* 1687 */         str = PREFIX_PARTY + "&a&lПати создано!";
/* 1688 */         setMessageText(e, str);
/* 1689 */         playSound(SOUND_PARTY_CREATED);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_YOU_ARE_NOT_IN_PARTY:
/* 1692 */         str = PREFIX_PARTY + "&cТы не в пати";
/* 1693 */         setMessageText(e, str);
/*      */         
/* 1695 */         if (bwmanipulator_party_info == 1) {
/* 1696 */           bwmanipulator_party_info = -1;
/* 1697 */           deleteMessage(e);
/* 1698 */           removeNextMessage = true;
/* 1699 */           addBedwarsMeowMessageToQuee("Я без пати", false);
/*      */         } 
/*      */         break;
/*      */       
/*      */       case CHAT_LOBBY_PARTY_PLAYER_KICKED:
/* 1704 */         str = PREFIX_PARTY + "&fКикнут &c&l" + vals[1];
/* 1705 */         setMessageText(e, str);
/* 1706 */         playSound(SOUND_REJECT);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_PLAYER_LEFT:
/* 1709 */         str = PREFIX_PARTY + "&fЛивнул &c&l" + vals[1];
/* 1710 */         setMessageText(e, str);
/* 1711 */         playSound(SOUND_REJECT);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_CHAT_ENTER_MESSAGE:
/* 1714 */         str = PREFIX_PARTY + "&fВведи сообщение: &a/pc Всем привет!";
/* 1715 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_CHAT_MESSAGE:
/* 1718 */         player_name = formatChatPlayerName(e.message, vals[1], "&7");
/* 1719 */         this_text = vals[2].trim();
/* 1720 */         str = PREFIX_PARTY + "&e[Chat] &7" + player_name + " &8→ &f" + this_text;
/* 1721 */         setMessageText(e, str);
/*      */         
/* 1723 */         if (Main.playerManipulator.handleCommand(player_name, this_text)) {
/* 1724 */           deleteMessage(e);
/*      */           
/*      */           break;
/*      */         } 
/* 1728 */         if (!ColorCodesManager.removeColorCodes(player_name).equals((Minecraft.func_71410_x()).field_71439_g.func_70005_c_())) {
/* 1729 */           playSound(SOUND_PARTY_CHAT);
/*      */         }
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_NO_PERMISSION:
/* 1733 */         str = PREFIX_PARTY + "&cТы не лидер пати!";
/* 1734 */         setMessageText(e, str);
/* 1735 */         playSound(SOUND_REJECT);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_DISBAND_REQUEST:
/* 1738 */         str = PREFIX_PARTY + "&c&l" + vals[1] + " &cудаляет пати";
/* 1739 */         setMessageText(e, str);
/* 1740 */         playSound(SOUND_REJECT);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_NOT_ENOUGH_SPACE:
/* 1743 */         str = PREFIX_PARTY + "&cНету места на этой арене";
/* 1744 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_REQUEST_ALREADY_SENT:
/* 1747 */         str = PREFIX_PARTY + "&cПодожди, у него уже есть запрос";
/* 1748 */         setMessageText(e, str);
/* 1749 */         playSound(SOUND_REJECT);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_YOU_WERE_WARPED:
/* 1752 */         str = PREFIX_PARTY + "&aТебя варпнул к себе создатель пати!";
/* 1753 */         setMessageText(e, str);
/* 1754 */         playSound(SOUND_PARTY_CHAT);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_INFO:
/* 1757 */         str = PREFIX_PARTY + "&aПати инфо";
/*      */         
/* 1759 */         this_text = e.message.func_150254_d();
/*      */         
/* 1761 */         myParty = null;
/*      */         
/* 1763 */         split = this_text.replace("§r", "").split("↪");
/* 1764 */         if (split != null && split.length == 3 && split[0].contains("Информация о пати")) {
/*      */           
/*      */           try {
/* 1767 */             String creator_line = ColorCodesManager.removeColorCodes(split[1]);
/* 1768 */             String creator_name = creator_line.split("Создатель:")[1].trim();
/* 1769 */             String[] members_line = split[2].split("Участники:§e")[1].trim().split(",");
/* 1770 */             ArrayList<String> members = new ArrayList<String>();
/* 1771 */             for (String m : members_line) {
/* 1772 */               String name = m.trim().split(" ")[0];
/* 1773 */               if (!m.contains("§c●") && !name.equals(creator_name))
/* 1774 */                 members.add(name); 
/*      */             } 
/* 1776 */             myParty = new MyParty(creator_name, members);
/*      */             
/* 1778 */             if (bwmanipulator_party_info == 1) {
/* 1779 */               bwmanipulator_party_info = -1;
/* 1780 */               String s = "";
/* 1781 */               for (String m : myParty.members) {
/* 1782 */                 s = s + m + ", ";
/*      */               }
/* 1784 */               if (s.length() > 2) s = s.substring(0, s.length() - 2);
/*      */               
/* 1786 */               deleteMessage(e);
/*      */               
/* 1788 */               removeNextMessage = true;
/* 1789 */               ChatSender.sendText("!Я в пати " + myParty.creator + ", учасники: " + s); break;
/*      */             } 
/* 1791 */             playSound(SOUND_PARTY_CHAT);
/*      */           
/*      */           }
/* 1794 */           catch (Exception exception) {}
/*      */         }
/*      */         break;
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
/*      */       case CHAT_LOBBY_PARTY_OWNER_LEFT:
/* 1809 */         str = PREFIX_PARTY + "&cСоздатель пати &l" + vals[1] + " &cливнул, пати будет удалено!";
/* 1810 */         setMessageText(e, str);
/* 1811 */         playSound(SOUND_REJECT);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_REQUEST:
/* 1814 */         playSound(SOUND_PLAYER_STATS);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_YOU_ACCEPTED_REQUEST:
/* 1817 */         str = PREFIX_PARTY + "&aТы принял запрос!";
/* 1818 */         setMessageText(e, str);
/* 1819 */         playSound(SOUND_PARTY_CREATED);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_NEW_LEADER:
/* 1822 */         assert Minecraft.func_71410_x() == null || (Minecraft.func_71410_x()).field_71439_g == null;
/* 1823 */         if (vals[1].equals((Minecraft.func_71410_x()).field_71439_g.func_70005_c_())) {
/* 1824 */           str = PREFIX_PARTY + "&aТеперь ты лидер пати!";
/*      */         } else {
/* 1826 */           str = PREFIX_PARTY + "&fНовый лидер пати: &a&l" + vals[1];
/*      */         } 
/* 1828 */         playSound(SOUND_PARTY_CREATED);
/*      */         
/* 1830 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_LOBBY_PARTY_COMMANDS_DONT_WORK_IN_LOBBY:
/* 1833 */         str = PREFIX_PARTY + "&cКоманды пати тут не работают";
/* 1834 */         setMessageText(e, str);
/* 1835 */         playSound(SOUND_REJECT);
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case CHAT_LOBBY_STATS_PLAYER:
/* 1841 */         player = vals[1];
/* 1842 */         if (player.equals("DimChig")) player = DIMCHIG_NAME_GOLD; 
/* 1843 */         str = "\n&r&fСтата &e" + player + "&f:";
/* 1844 */         setMessageText(e, str);
/* 1845 */         playSound(SOUND_PLAYER_STATS);
/*      */         
/* 1847 */         myStatistic = new MyStatistic(player);
/*      */         break;
/*      */       case CHAT_LOBBY_STATS_CATEGORY:
/* 1850 */         category = getStatsCategoryName(vals[1]);
/* 1851 */         placeColor = "&0";
/*      */         try {
/* 1853 */           int x = Integer.parseInt(vals[3].trim());
/* 1854 */           placeColor = "&8";
/* 1855 */           if (x < 5000) placeColor = "&7"; 
/* 1856 */           if (x < 2000) placeColor = "&f"; 
/* 1857 */           if (x < 1000) placeColor = "&a"; 
/* 1858 */           if (x < 100) placeColor = "&e&l"; 
/* 1859 */           if (x == 0) placeColor = "&c";
/*      */         
/* 1861 */         } catch (Exception exception) {}
/* 1862 */         str = " &8- &r" + category + "&8 ▸ &f" + vals[2] + "&8. Место - " + placeColor + vals[3];
/*      */         
/* 1864 */         cnt = -1;
/* 1865 */         place_cnt = -1;
/*      */         try {
/* 1867 */           cnt = Integer.parseInt(vals[2].trim());
/* 1868 */           place_cnt = Integer.parseInt(vals[3].trim());
/* 1869 */         } catch (Exception exception) {}
/*      */         
/* 1871 */         if (cnt != -1 && place_cnt != -1)
/*      */         {
/* 1873 */           if (category.contains("Килов")) {
/* 1874 */             myStatistic.category_kills_cnt = cnt;
/* 1875 */             myStatistic.category_kills_place = place_cnt;
/* 1876 */           } else if (category.contains("Смертей")) {
/* 1877 */             myStatistic.category_deathes_cnt = cnt;
/* 1878 */             myStatistic.category_deathes_place = place_cnt;
/* 1879 */           } else if (category.contains("Каток")) {
/* 1880 */             myStatistic.category_games_cnt = cnt;
/* 1881 */             myStatistic.category_games_place = place_cnt;
/* 1882 */           } else if (category.contains("Побед")) {
/* 1883 */             myStatistic.category_wins_cnt = cnt;
/* 1884 */             myStatistic.category_wins_place = place_cnt;
/* 1885 */           } else if (category.contains("Кроватей")) {
/* 1886 */             myStatistic.category_beds_cnt = cnt;
/* 1887 */             myStatistic.category_beds_place = place_cnt;
/* 1888 */             str = str + "\n";
/*      */             
/* 1890 */             int player_mark = 0;
/*      */             
/* 1892 */             if (myStatistic.category_games_cnt > 0) {
/* 1893 */               float percentage = (float)(myStatistic.category_wins_cnt / myStatistic.category_games_cnt * 100.0D);
/*      */               
/* 1895 */               String str1 = "&0";
/* 1896 */               if (percentage < 10.0F) {
/* 1897 */                 player_mark++;
/* 1898 */                 str1 = "&c";
/* 1899 */               } else if (percentage < 20.0F) {
/* 1900 */                 player_mark += 2;
/* 1901 */                 str1 = "&6";
/* 1902 */               } else if (percentage < 30.0F) {
/* 1903 */                 player_mark += 3;
/* 1904 */                 str1 = "&e";
/* 1905 */               } else if (percentage <= 40.0F) {
/* 1906 */                 player_mark += 4;
/* 1907 */                 str1 = "&a";
/*      */               } else {
/* 1909 */                 player_mark += 5;
/* 1910 */                 str1 = "&2";
/*      */               } 
/*      */               
/* 1913 */               str = str + "&8 - &fВинрейт &8▸ " + str1 + (int)percentage + "%\n";
/*      */             } 
/*      */             
/* 1916 */             if (myStatistic.category_deathes_cnt > 0) {
/* 1917 */               float kdr = (float)Math.round(myStatistic.category_kills_cnt / myStatistic.category_deathes_cnt * 100.0D) / 100.0F;
/* 1918 */               String str1 = "&0";
/* 1919 */               if (kdr < 0.7D) {
/* 1920 */                 player_mark++;
/* 1921 */                 str1 = "&c";
/* 1922 */               } else if (kdr < 1.0F) {
/* 1923 */                 player_mark += 2;
/* 1924 */                 str1 = "&6";
/* 1925 */               } else if (kdr < 1.5D) {
/* 1926 */                 player_mark += 3;
/* 1927 */                 str1 = "&e";
/* 1928 */               } else if (kdr <= 2.0F) {
/* 1929 */                 player_mark += 4;
/* 1930 */                 str1 = "&a";
/*      */               } else {
/* 1932 */                 player_mark += 5;
/* 1933 */                 str1 = "&2";
/*      */               } 
/* 1935 */               str = str + "&8 - &fK/D &8▸ " + str1 + kdr + "\n";
/*      */             } 
/*      */             
/* 1938 */             if (myStatistic.category_games_cnt > 0) {
/* 1939 */               float bpg = (float)Math.round(myStatistic.category_beds_cnt / myStatistic.category_games_cnt * 10.0D) / 10.0F;
/* 1940 */               String str1 = "&0";
/* 1941 */               if (bpg < 0.7D) {
/* 1942 */                 player_mark++;
/* 1943 */                 str1 = "&c";
/* 1944 */               } else if (bpg < 1.0F) {
/* 1945 */                 player_mark += 2;
/* 1946 */                 str1 = "&6";
/* 1947 */               } else if (bpg < 1.5D) {
/* 1948 */                 player_mark += 3;
/* 1949 */                 str1 = "&e";
/* 1950 */               } else if (bpg <= 2.0F) {
/* 1951 */                 player_mark += 4;
/* 1952 */                 str1 = "&a";
/*      */               } else {
/* 1954 */                 player_mark += 5;
/* 1955 */                 str1 = "&2";
/*      */               } 
/* 1957 */               str = str + "&8 - &fBeds/Game &8▸ " + str1 + bpg + " шт\n";
/*      */             } 
/*      */             
/* 1960 */             int treshold = 200;
/* 1961 */             if (myStatistic.category_kills_cnt < treshold && myStatistic.category_deathes_cnt < treshold && myStatistic.category_games_cnt < treshold && myStatistic.category_wins_cnt < treshold && myStatistic.category_beds_cnt < treshold)
/*      */             {
/*      */ 
/*      */ 
/*      */               
/* 1966 */               str = str + "&8 - &cНулевый аккаунт\n";
/*      */             }
/*      */             
/* 1969 */             float mark = (Math.round((player_mark / 3 * 10)) / 10);
/* 1970 */             String color = "&f";
/* 1971 */             if (mark < 2.0F) {
/* 1972 */               color = "&c";
/* 1973 */             } else if (mark < 3.0F) {
/* 1974 */               color = "&6";
/* 1975 */             } else if (mark < 4.0F) {
/* 1976 */               color = "&e";
/* 1977 */             } else if (mark < 4.5D) {
/* 1978 */               color = "&a";
/*      */             } else {
/* 1980 */               color = "&2";
/*      */             } 
/*      */ 
/*      */             
/* 1984 */             str = str + "&8 - &fОценка &8▸ " + color + mark + "\n";
/*      */             
/* 1986 */             myStatistic = null;
/*      */           } 
/*      */         }
/* 1989 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_GAME_CANT_USE_COMMANDS:
/* 1992 */         str = "&cНельзя юзать команды во время игры!";
/* 1993 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_GAME_WAIT_SECONDS:
/* 1996 */         str = "&cПодожди " + vals[0] + " сек!";
/* 1997 */         setMessageText(e, str);
/*      */         break;
/*      */       case CHAT_GAME_YOU_CANT_USE_COLORS:
/* 2000 */         str = "&cКупи донат чтоб юзать цвета в чате!";
/* 2001 */         setMessageText(e, str);
/* 2002 */         removeNextMessage = true;
/*      */         break;
/*      */       case CHAT_GAME_YOU_CANT_USE_COLOR:
/* 2005 */         str = "&cТы не можешь юзать такие цвета в чате!";
/* 2006 */         setMessageText(e, str);
/* 2007 */         removeNextMessage = true;
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
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
/* 2062 */     str = str + "                         &cBedwars&fBro &7v1.69\n\n";
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


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\MyChatListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */