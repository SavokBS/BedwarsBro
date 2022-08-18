/*     */ package com.dimchig.bedwarsbro;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.network.NetworkPlayerInfo;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CustomScoreboard
/*     */ {
/*     */   private static ScoreboardManager sm;
/*     */   private static boolean isEnglishScoreboard = false;
/*     */   
/*     */   public CustomScoreboard() {
/*  27 */     sm = new ScoreboardManager();
/*  28 */     updateBooleans();
/*     */   }
/*     */   
/*     */   public void updateBooleans() {
/*  32 */     isEnglishScoreboard = Main.getConfigBool(Main.CONFIG_MSG.SCOREBOARD_ENGLISH);
/*     */   }
/*     */   
/*  35 */   private static String[] russianTranslations = new String[] { "Убийств", "Финальных§f убийств", "Сломано к§fроватей" };
/*     */ 
/*     */   
/*  38 */   private static String[] englishTranslations = new String[] { "Kills", "Finals", "Beds" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void updateScoreboard() {
/*     */     // Byte code:
/*     */     //   0: ldc '&6Подпишись на &e&lканал&6!'
/*     */     //   2: astore_0
/*     */     //   3: ldc '&7Мод &8▸ &cBedwars&fBro'
/*     */     //   5: astore_1
/*     */     //   6: iconst_3
/*     */     //   7: anewarray java/lang/String
/*     */     //   10: dup
/*     */     //   11: iconst_0
/*     */     //   12: ldc 'MineBlaze'
/*     */     //   14: aastore
/*     */     //   15: dup
/*     */     //   16: iconst_1
/*     */     //   17: ldc 'DexLand'
/*     */     //   19: aastore
/*     */     //   20: dup
/*     */     //   21: iconst_2
/*     */     //   22: ldc 'MasedWorld'
/*     */     //   24: aastore
/*     */     //   25: astore_2
/*     */     //   26: invokestatic func_71410_x : ()Lnet/minecraft/client/Minecraft;
/*     */     //   29: ifnull -> 41
/*     */     //   32: invokestatic func_71410_x : ()Lnet/minecraft/client/Minecraft;
/*     */     //   35: getfield field_71439_g : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   38: ifnonnull -> 42
/*     */     //   41: return
/*     */     //   42: invokestatic func_71410_x : ()Lnet/minecraft/client/Minecraft;
/*     */     //   45: getfield field_71439_g : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   48: invokevirtual func_70005_c_ : ()Ljava/lang/String;
/*     */     //   51: astore_3
/*     */     //   52: getstatic com/dimchig/bedwarsbro/Main.namePlateRenderer : Lcom/dimchig/bedwarsbro/hints/NamePlateRenderer;
/*     */     //   55: aload_3
/*     */     //   56: invokevirtual findBroPlayerByName : (Ljava/lang/String;)Lcom/dimchig/bedwarsbro/SameModePlayers$BroPlayer;
/*     */     //   59: astore #4
/*     */     //   61: aload #4
/*     */     //   63: ifnull -> 79
/*     */     //   66: aload #4
/*     */     //   68: getfield isAdmin : Z
/*     */     //   71: iconst_1
/*     */     //   72: if_icmpne -> 79
/*     */     //   75: iconst_1
/*     */     //   76: goto -> 80
/*     */     //   79: iconst_0
/*     */     //   80: istore #5
/*     */     //   82: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   85: pop
/*     */     //   86: ldc 'Слом. кроватей:'
/*     */     //   88: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   91: ifnull -> 820
/*     */     //   94: iload #5
/*     */     //   96: ifeq -> 209
/*     */     //   99: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   102: pop
/*     */     //   103: aload_2
/*     */     //   104: aload_0
/*     */     //   105: invokestatic replaceText : ([Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   108: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   111: pop
/*     */     //   112: ldc 'Донат'
/*     */     //   114: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   117: ifnull -> 162
/*     */     //   120: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   123: pop
/*     */     //   124: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   127: pop
/*     */     //   128: ldc 'Донат'
/*     */     //   130: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   133: new java/lang/StringBuilder
/*     */     //   136: dup
/*     */     //   137: invokespecial <init> : ()V
/*     */     //   140: ldc ' Донат: '
/*     */     //   142: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   145: getstatic com/dimchig/bedwarsbro/Main.namePlateRenderer : Lcom/dimchig/bedwarsbro/hints/NamePlateRenderer;
/*     */     //   148: ldc 'Admin'
/*     */     //   150: invokevirtual generateColourfulPrefix : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   153: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   156: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   159: invokestatic replaceText : (Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   162: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   165: pop
/*     */     //   166: ldc 'Ник'
/*     */     //   168: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   171: ifnull -> 209
/*     */     //   174: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   177: pop
/*     */     //   178: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   181: pop
/*     */     //   182: ldc 'Ник'
/*     */     //   184: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   187: new java/lang/StringBuilder
/*     */     //   190: dup
/*     */     //   191: invokespecial <init> : ()V
/*     */     //   194: ldc ' Ник: &a&l'
/*     */     //   196: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   199: aload_3
/*     */     //   200: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   203: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   206: invokestatic replaceText : (Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   209: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   212: pop
/*     */     //   213: ldc 'Скрыть: /board'
/*     */     //   215: new java/lang/StringBuilder
/*     */     //   218: dup
/*     */     //   219: invokespecial <init> : ()V
/*     */     //   222: ldc ' '
/*     */     //   224: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   227: aload_1
/*     */     //   228: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   231: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   234: invokestatic replaceText : (Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   237: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   240: pop
/*     */     //   241: ldc 'Убийств:'
/*     */     //   243: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   246: astore #6
/*     */     //   248: aload #6
/*     */     //   250: ifnull -> 302
/*     */     //   253: aload #6
/*     */     //   255: ldc ' '
/*     */     //   257: invokevirtual contains : (Ljava/lang/CharSequence;)Z
/*     */     //   260: ifeq -> 302
/*     */     //   263: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   266: pop
/*     */     //   267: aload #6
/*     */     //   269: new java/lang/StringBuilder
/*     */     //   272: dup
/*     */     //   273: invokespecial <init> : ()V
/*     */     //   276: ldc ' Килов &7▸ &c'
/*     */     //   278: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   281: aload #6
/*     */     //   283: ldc ' '
/*     */     //   285: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
/*     */     //   288: iconst_1
/*     */     //   289: aaload
/*     */     //   290: invokevirtual trim : ()Ljava/lang/String;
/*     */     //   293: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   296: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   299: invokestatic replaceText : (Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   302: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   305: pop
/*     */     //   306: ldc 'Смертей:'
/*     */     //   308: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   311: astore #6
/*     */     //   313: aload #6
/*     */     //   315: ifnull -> 367
/*     */     //   318: aload #6
/*     */     //   320: ldc ': '
/*     */     //   322: invokevirtual contains : (Ljava/lang/CharSequence;)Z
/*     */     //   325: ifeq -> 367
/*     */     //   328: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   331: pop
/*     */     //   332: aload #6
/*     */     //   334: new java/lang/StringBuilder
/*     */     //   337: dup
/*     */     //   338: invokespecial <init> : ()V
/*     */     //   341: ldc ' Смертей &7▸ &e'
/*     */     //   343: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   346: aload #6
/*     */     //   348: ldc ': '
/*     */     //   350: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
/*     */     //   353: iconst_1
/*     */     //   354: aaload
/*     */     //   355: invokevirtual trim : ()Ljava/lang/String;
/*     */     //   358: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   361: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   364: invokestatic replaceText : (Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   367: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   370: pop
/*     */     //   371: ldc 'K/D:'
/*     */     //   373: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   376: astore #6
/*     */     //   378: aload #6
/*     */     //   380: ifnull -> 432
/*     */     //   383: aload #6
/*     */     //   385: ldc ': '
/*     */     //   387: invokevirtual contains : (Ljava/lang/CharSequence;)Z
/*     */     //   390: ifeq -> 432
/*     */     //   393: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   396: pop
/*     */     //   397: aload #6
/*     */     //   399: new java/lang/StringBuilder
/*     */     //   402: dup
/*     */     //   403: invokespecial <init> : ()V
/*     */     //   406: ldc ' K/D &7▸ &a'
/*     */     //   408: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   411: aload #6
/*     */     //   413: ldc ': '
/*     */     //   415: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
/*     */     //   418: iconst_1
/*     */     //   419: aaload
/*     */     //   420: invokevirtual trim : ()Ljava/lang/String;
/*     */     //   423: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   426: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   429: invokestatic replaceText : (Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   432: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   435: pop
/*     */     //   436: ldc 'Игр:'
/*     */     //   438: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   441: astore #6
/*     */     //   443: iconst_m1
/*     */     //   444: istore #7
/*     */     //   446: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   449: pop
/*     */     //   450: ldc 'Игр:'
/*     */     //   452: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   455: ldc ': '
/*     */     //   457: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
/*     */     //   460: iconst_1
/*     */     //   461: aaload
/*     */     //   462: invokevirtual trim : ()Ljava/lang/String;
/*     */     //   465: invokestatic parseInt : (Ljava/lang/String;)I
/*     */     //   468: istore #7
/*     */     //   470: goto -> 475
/*     */     //   473: astore #8
/*     */     //   475: aload #6
/*     */     //   477: ifnull -> 529
/*     */     //   480: aload #6
/*     */     //   482: ldc ': '
/*     */     //   484: invokevirtual contains : (Ljava/lang/CharSequence;)Z
/*     */     //   487: ifeq -> 529
/*     */     //   490: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   493: pop
/*     */     //   494: aload #6
/*     */     //   496: new java/lang/StringBuilder
/*     */     //   499: dup
/*     */     //   500: invokespecial <init> : ()V
/*     */     //   503: ldc ' Каток &7▸ &b'
/*     */     //   505: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   508: aload #6
/*     */     //   510: ldc ': '
/*     */     //   512: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
/*     */     //   515: iconst_1
/*     */     //   516: aaload
/*     */     //   517: invokevirtual trim : ()Ljava/lang/String;
/*     */     //   520: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   523: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   526: invokestatic replaceText : (Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   529: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   532: pop
/*     */     //   533: ldc 'Побед:'
/*     */     //   535: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   538: astore #6
/*     */     //   540: ldc2_w -1.0
/*     */     //   543: dstore #8
/*     */     //   545: new java/lang/StringBuilder
/*     */     //   548: dup
/*     */     //   549: invokespecial <init> : ()V
/*     */     //   552: ldc ' Побед &7▸ &9'
/*     */     //   554: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   557: aload #6
/*     */     //   559: ldc ': '
/*     */     //   561: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
/*     */     //   564: iconst_1
/*     */     //   565: aaload
/*     */     //   566: invokevirtual trim : ()Ljava/lang/String;
/*     */     //   569: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   572: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   575: astore #10
/*     */     //   577: iconst_m1
/*     */     //   578: istore #11
/*     */     //   580: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   583: pop
/*     */     //   584: ldc 'Побед:'
/*     */     //   586: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   589: ldc ': '
/*     */     //   591: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
/*     */     //   594: iconst_1
/*     */     //   595: aaload
/*     */     //   596: invokevirtual trim : ()Ljava/lang/String;
/*     */     //   599: invokestatic parseInt : (Ljava/lang/String;)I
/*     */     //   602: istore #11
/*     */     //   604: goto -> 609
/*     */     //   607: astore #12
/*     */     //   609: iload #7
/*     */     //   611: iconst_m1
/*     */     //   612: if_icmpeq -> 631
/*     */     //   615: iload #11
/*     */     //   617: iconst_m1
/*     */     //   618: if_icmpeq -> 631
/*     */     //   621: iload #11
/*     */     //   623: i2f
/*     */     //   624: iload #7
/*     */     //   626: i2f
/*     */     //   627: fdiv
/*     */     //   628: f2d
/*     */     //   629: dstore #8
/*     */     //   631: dload #8
/*     */     //   633: dconst_0
/*     */     //   634: dcmpl
/*     */     //   635: ifle -> 675
/*     */     //   638: new java/lang/StringBuilder
/*     */     //   641: dup
/*     */     //   642: invokespecial <init> : ()V
/*     */     //   645: aload #10
/*     */     //   647: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   650: ldc '&7 | &fWR &7▸ &9'
/*     */     //   652: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   655: dload #8
/*     */     //   657: ldc2_w 100.0
/*     */     //   660: dmul
/*     */     //   661: d2i
/*     */     //   662: invokevirtual append : (I)Ljava/lang/StringBuilder;
/*     */     //   665: ldc '%'
/*     */     //   667: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   670: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   673: astore #10
/*     */     //   675: aload #6
/*     */     //   677: ifnull -> 701
/*     */     //   680: aload #6
/*     */     //   682: ldc ': '
/*     */     //   684: invokevirtual contains : (Ljava/lang/CharSequence;)Z
/*     */     //   687: ifeq -> 701
/*     */     //   690: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   693: pop
/*     */     //   694: aload #6
/*     */     //   696: aload #10
/*     */     //   698: invokestatic replaceText : (Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   701: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   704: pop
/*     */     //   705: ldc 'Слом. кроватей:'
/*     */     //   707: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   710: astore #6
/*     */     //   712: new java/lang/StringBuilder
/*     */     //   715: dup
/*     */     //   716: invokespecial <init> : ()V
/*     */     //   719: ldc ' Кроватей &7▸ &d'
/*     */     //   721: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   724: aload #6
/*     */     //   726: ldc ': '
/*     */     //   728: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
/*     */     //   731: iconst_1
/*     */     //   732: aaload
/*     */     //   733: invokevirtual trim : ()Ljava/lang/String;
/*     */     //   736: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   739: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   742: astore #10
/*     */     //   744: iconst_m1
/*     */     //   745: istore #12
/*     */     //   747: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   750: pop
/*     */     //   751: ldc 'Слом. кроватей:'
/*     */     //   753: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   756: ldc ': '
/*     */     //   758: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
/*     */     //   761: iconst_1
/*     */     //   762: aaload
/*     */     //   763: invokevirtual trim : ()Ljava/lang/String;
/*     */     //   766: invokestatic parseInt : (Ljava/lang/String;)I
/*     */     //   769: istore #12
/*     */     //   771: goto -> 776
/*     */     //   774: astore #13
/*     */     //   776: iload #12
/*     */     //   778: ifle -> 786
/*     */     //   781: iload #7
/*     */     //   783: ifle -> 786
/*     */     //   786: aload #6
/*     */     //   788: ifnull -> 812
/*     */     //   791: aload #6
/*     */     //   793: ldc ': '
/*     */     //   795: invokevirtual contains : (Ljava/lang/CharSequence;)Z
/*     */     //   798: ifeq -> 812
/*     */     //   801: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   804: pop
/*     */     //   805: aload #6
/*     */     //   807: aload #10
/*     */     //   809: invokestatic replaceText : (Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   812: goto -> 1224
/*     */     //   815: astore #6
/*     */     //   817: goto -> 1224
/*     */     //   820: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   823: pop
/*     */     //   824: ldc 'Старт через: '
/*     */     //   826: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   829: ifnonnull -> 844
/*     */     //   832: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   835: pop
/*     */     //   836: ldc 'Карта: '
/*     */     //   838: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   841: ifnull -> 861
/*     */     //   844: iload #5
/*     */     //   846: ifeq -> 1224
/*     */     //   849: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   852: pop
/*     */     //   853: aload_2
/*     */     //   854: aload_0
/*     */     //   855: invokestatic replaceText : ([Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   858: goto -> 1224
/*     */     //   861: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   864: pop
/*     */     //   865: getstatic com/dimchig/bedwarsbro/CustomScoreboard.russianTranslations : [Ljava/lang/String;
/*     */     //   868: iconst_1
/*     */     //   869: aaload
/*     */     //   870: invokestatic removeColorCodes : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   873: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   876: ifnonnull -> 894
/*     */     //   879: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   882: pop
/*     */     //   883: getstatic com/dimchig/bedwarsbro/CustomScoreboard.englishTranslations : [Ljava/lang/String;
/*     */     //   886: iconst_1
/*     */     //   887: aaload
/*     */     //   888: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   891: ifnull -> 1224
/*     */     //   894: iconst_0
/*     */     //   895: istore #6
/*     */     //   897: iload #6
/*     */     //   899: getstatic com/dimchig/bedwarsbro/CustomScoreboard.team_names : [Ljava/lang/String;
/*     */     //   902: arraylength
/*     */     //   903: if_icmpge -> 1171
/*     */     //   906: getstatic com/dimchig/bedwarsbro/CustomScoreboard.team_names : [Ljava/lang/String;
/*     */     //   909: iload #6
/*     */     //   911: aaload
/*     */     //   912: astore #7
/*     */     //   914: getstatic com/dimchig/bedwarsbro/CustomScoreboard.isEnglishScoreboard : Z
/*     */     //   917: ifeq -> 929
/*     */     //   920: getstatic com/dimchig/bedwarsbro/CustomScoreboard.team_names_english : [Ljava/lang/String;
/*     */     //   923: iload #6
/*     */     //   925: aaload
/*     */     //   926: goto -> 931
/*     */     //   929: aload #7
/*     */     //   931: astore #8
/*     */     //   933: aload #7
/*     */     //   935: invokestatic getTeamColorByName : (Ljava/lang/String;)Lcom/dimchig/bedwarsbro/CustomScoreboard$TEAM_COLOR;
/*     */     //   938: astore #9
/*     */     //   940: new java/lang/StringBuilder
/*     */     //   943: dup
/*     */     //   944: invokespecial <init> : ()V
/*     */     //   947: ldc '&'
/*     */     //   949: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   952: aload #9
/*     */     //   954: invokestatic getCodeByTeamColor : (Lcom/dimchig/bedwarsbro/CustomScoreboard$TEAM_COLOR;)Ljava/lang/String;
/*     */     //   957: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   960: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   963: astore #10
/*     */     //   965: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   968: pop
/*     */     //   969: aload #7
/*     */     //   971: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   974: astore #11
/*     */     //   976: aload #11
/*     */     //   978: ifnonnull -> 984
/*     */     //   981: goto -> 1165
/*     */     //   984: aload #11
/*     */     //   986: invokevirtual trim : ()Ljava/lang/String;
/*     */     //   989: astore #11
/*     */     //   991: aload #7
/*     */     //   993: astore #12
/*     */     //   995: aload #11
/*     */     //   997: ldc ':'
/*     */     //   999: invokevirtual contains : (Ljava/lang/CharSequence;)Z
/*     */     //   1002: ifeq -> 1030
/*     */     //   1005: new java/lang/StringBuilder
/*     */     //   1008: dup
/*     */     //   1009: invokespecial <init> : ()V
/*     */     //   1012: aload #12
/*     */     //   1014: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1017: ldc ':'
/*     */     //   1019: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1022: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   1025: astore #12
/*     */     //   1027: goto -> 1057
/*     */     //   1030: new java/lang/StringBuilder
/*     */     //   1033: dup
/*     */     //   1034: invokespecial <init> : ()V
/*     */     //   1037: aload #12
/*     */     //   1039: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1042: aload #10
/*     */     //   1044: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1047: ldc ' ▸'
/*     */     //   1049: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1052: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   1055: astore #12
/*     */     //   1057: ldc ''
/*     */     //   1059: astore #13
/*     */     //   1061: aload #11
/*     */     //   1063: ldc '✗'
/*     */     //   1065: invokevirtual contains : (Ljava/lang/CharSequence;)Z
/*     */     //   1068: ifeq -> 1075
/*     */     //   1071: ldc '&m'
/*     */     //   1073: astore #13
/*     */     //   1075: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   1078: pop
/*     */     //   1079: aload #12
/*     */     //   1081: new java/lang/StringBuilder
/*     */     //   1084: dup
/*     */     //   1085: invokespecial <init> : ()V
/*     */     //   1088: aload #10
/*     */     //   1090: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1093: aload #13
/*     */     //   1095: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1098: aload #8
/*     */     //   1100: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1103: aload #10
/*     */     //   1105: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1108: ldc ' ▸'
/*     */     //   1110: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1113: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   1116: invokestatic replaceColorCodesInString : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   1119: invokestatic hardReplaceText : (Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   1122: getstatic com/dimchig/bedwarsbro/CustomScoreboard.isEnglishScoreboard : Z
/*     */     //   1125: ifeq -> 1165
/*     */     //   1128: iconst_0
/*     */     //   1129: istore #14
/*     */     //   1131: iload #14
/*     */     //   1133: getstatic com/dimchig/bedwarsbro/CustomScoreboard.russianTranslations : [Ljava/lang/String;
/*     */     //   1136: arraylength
/*     */     //   1137: if_icmpge -> 1165
/*     */     //   1140: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   1143: pop
/*     */     //   1144: getstatic com/dimchig/bedwarsbro/CustomScoreboard.russianTranslations : [Ljava/lang/String;
/*     */     //   1147: iload #14
/*     */     //   1149: aaload
/*     */     //   1150: getstatic com/dimchig/bedwarsbro/CustomScoreboard.englishTranslations : [Ljava/lang/String;
/*     */     //   1153: iload #14
/*     */     //   1155: aaload
/*     */     //   1156: invokestatic hardReplaceText : (Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   1159: iinc #14, 1
/*     */     //   1162: goto -> 1131
/*     */     //   1165: iinc #6, 1
/*     */     //   1168: goto -> 897
/*     */     //   1171: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   1174: pop
/*     */     //   1175: ldc '(Вы)'
/*     */     //   1177: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   1180: astore #6
/*     */     //   1182: aload #6
/*     */     //   1184: ifnull -> 1198
/*     */     //   1187: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   1190: pop
/*     */     //   1191: ldc '(Вы)'
/*     */     //   1193: ldc '&8←'
/*     */     //   1195: invokestatic hardReplaceText : (Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   1198: iload #5
/*     */     //   1200: ifeq -> 1215
/*     */     //   1203: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   1206: pop
/*     */     //   1207: aload_2
/*     */     //   1208: aload_0
/*     */     //   1209: invokestatic replaceText : ([Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   1212: goto -> 1224
/*     */     //   1215: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   1218: pop
/*     */     //   1219: aload_2
/*     */     //   1220: aload_1
/*     */     //   1221: invokestatic replaceText : ([Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   1224: new java/lang/StringBuilder
/*     */     //   1227: dup
/*     */     //   1228: invokespecial <init> : ()V
/*     */     //   1231: ldc '\\n'
/*     */     //   1233: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1236: aload_1
/*     */     //   1237: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1240: ldc ' &7v&a'
/*     */     //   1242: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1245: ldc '1.69.2'
/*     */     //   1247: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1250: ldc ' &7(&e/bwbro&7)\\n'
/*     */     //   1252: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1255: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   1258: astore #6
/*     */     //   1260: ldc ''
/*     */     //   1262: astore #7
/*     */     //   1264: invokestatic func_71410_x : ()Lnet/minecraft/client/Minecraft;
/*     */     //   1267: invokevirtual func_147104_D : ()Lnet/minecraft/client/multiplayer/ServerData;
/*     */     //   1270: ifnull -> 1308
/*     */     //   1273: new java/lang/StringBuilder
/*     */     //   1276: dup
/*     */     //   1277: invokespecial <init> : ()V
/*     */     //   1280: ldc_w '\\n&7Ты играешь на &e'
/*     */     //   1283: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1286: invokestatic func_71410_x : ()Lnet/minecraft/client/Minecraft;
/*     */     //   1289: invokevirtual func_147104_D : ()Lnet/minecraft/client/multiplayer/ServerData;
/*     */     //   1292: getfield field_78845_b : Ljava/lang/String;
/*     */     //   1295: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1298: ldc '\\n'
/*     */     //   1300: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1303: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   1306: astore #7
/*     */     //   1308: invokestatic func_71410_x : ()Lnet/minecraft/client/Minecraft;
/*     */     //   1311: getfield field_71456_v : Lnet/minecraft/client/gui/GuiIngame;
/*     */     //   1314: invokevirtual func_175181_h : ()Lnet/minecraft/client/gui/GuiPlayerTabOverlay;
/*     */     //   1317: new net/minecraft/util/ChatComponentText
/*     */     //   1320: dup
/*     */     //   1321: aload #6
/*     */     //   1323: invokestatic replaceColorCodesInString : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   1326: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   1329: invokevirtual func_175244_b : (Lnet/minecraft/util/IChatComponent;)V
/*     */     //   1332: invokestatic func_71410_x : ()Lnet/minecraft/client/Minecraft;
/*     */     //   1335: getfield field_71456_v : Lnet/minecraft/client/gui/GuiIngame;
/*     */     //   1338: invokevirtual func_175181_h : ()Lnet/minecraft/client/gui/GuiPlayerTabOverlay;
/*     */     //   1341: new net/minecraft/util/ChatComponentText
/*     */     //   1344: dup
/*     */     //   1345: aload #7
/*     */     //   1347: invokestatic replaceColorCodesInString : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   1350: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   1353: invokevirtual func_175248_a : (Lnet/minecraft/util/IChatComponent;)V
/*     */     //   1356: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #44	-> 0
/*     */     //   #45	-> 3
/*     */     //   #46	-> 6
/*     */     //   #48	-> 26
/*     */     //   #49	-> 42
/*     */     //   #51	-> 52
/*     */     //   #52	-> 61
/*     */     //   #54	-> 82
/*     */     //   #56	-> 94
/*     */     //   #57	-> 99
/*     */     //   #58	-> 108
/*     */     //   #59	-> 120
/*     */     //   #61	-> 162
/*     */     //   #62	-> 174
/*     */     //   #66	-> 209
/*     */     //   #69	-> 237
/*     */     //   #70	-> 248
/*     */     //   #72	-> 302
/*     */     //   #73	-> 313
/*     */     //   #75	-> 367
/*     */     //   #76	-> 378
/*     */     //   #78	-> 432
/*     */     //   #79	-> 443
/*     */     //   #81	-> 446
/*     */     //   #82	-> 470
/*     */     //   #83	-> 475
/*     */     //   #85	-> 529
/*     */     //   #86	-> 540
/*     */     //   #87	-> 545
/*     */     //   #88	-> 577
/*     */     //   #90	-> 580
/*     */     //   #91	-> 604
/*     */     //   #92	-> 609
/*     */     //   #93	-> 621
/*     */     //   #96	-> 631
/*     */     //   #98	-> 675
/*     */     //   #101	-> 701
/*     */     //   #102	-> 712
/*     */     //   #103	-> 744
/*     */     //   #105	-> 747
/*     */     //   #106	-> 771
/*     */     //   #107	-> 776
/*     */     //   #111	-> 786
/*     */     //   #112	-> 812
/*     */     //   #114	-> 820
/*     */     //   #116	-> 844
/*     */     //   #117	-> 849
/*     */     //   #119	-> 861
/*     */     //   #122	-> 894
/*     */     //   #123	-> 906
/*     */     //   #124	-> 914
/*     */     //   #125	-> 933
/*     */     //   #126	-> 940
/*     */     //   #127	-> 965
/*     */     //   #128	-> 976
/*     */     //   #129	-> 984
/*     */     //   #130	-> 991
/*     */     //   #131	-> 995
/*     */     //   #132	-> 1030
/*     */     //   #134	-> 1057
/*     */     //   #135	-> 1061
/*     */     //   #138	-> 1075
/*     */     //   #140	-> 1122
/*     */     //   #141	-> 1128
/*     */     //   #142	-> 1140
/*     */     //   #141	-> 1159
/*     */     //   #122	-> 1165
/*     */     //   #147	-> 1171
/*     */     //   #148	-> 1182
/*     */     //   #149	-> 1187
/*     */     //   #152	-> 1198
/*     */     //   #153	-> 1203
/*     */     //   #155	-> 1215
/*     */     //   #161	-> 1224
/*     */     //   #162	-> 1260
/*     */     //   #163	-> 1264
/*     */     //   #164	-> 1273
/*     */     //   #167	-> 1308
/*     */     //   #169	-> 1332
/*     */     //   #170	-> 1356
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   248	564	6	text	Ljava/lang/String;
/*     */     //   446	366	7	games_cnt	I
/*     */     //   545	267	8	win_rate	D
/*     */     //   577	235	10	new_text	Ljava/lang/String;
/*     */     //   580	232	11	wins_cnt	I
/*     */     //   747	65	12	beds_cnt	I
/*     */     //   1131	34	14	j	I
/*     */     //   914	251	7	team_name	Ljava/lang/String;
/*     */     //   933	232	8	team_name_new	Ljava/lang/String;
/*     */     //   940	225	9	team_color	Lcom/dimchig/bedwarsbro/CustomScoreboard$TEAM_COLOR;
/*     */     //   965	200	10	colorcode	Ljava/lang/String;
/*     */     //   976	189	11	source	Ljava/lang/String;
/*     */     //   995	170	12	replace	Ljava/lang/String;
/*     */     //   1061	104	13	extraFormatting	Ljava/lang/String;
/*     */     //   897	274	6	i	I
/*     */     //   1182	42	6	text_your_team	Ljava/lang/String;
/*     */     //   3	1354	0	subscribe	Ljava/lang/String;
/*     */     //   6	1351	1	mod_name	Ljava/lang/String;
/*     */     //   26	1331	2	servers	[Ljava/lang/String;
/*     */     //   52	1305	3	mod_player_name	Ljava/lang/String;
/*     */     //   61	1296	4	bro	Lcom/dimchig/bedwarsbro/SameModePlayers$BroPlayer;
/*     */     //   82	1275	5	isAdmin	Z
/*     */     //   1260	97	6	tab_header_text	Ljava/lang/String;
/*     */     //   1264	93	7	tab_footer_text	Ljava/lang/String;
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   237	812	815	java/lang/Exception
/*     */     //   446	470	473	java/lang/Exception
/*     */     //   580	604	607	java/lang/Exception
/*     */     //   747	771	774	java/lang/Exception
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum TEAM_COLOR
/*     */   {
/* 174 */     RED,
/* 175 */     YELLOW,
/* 176 */     GREEN,
/* 177 */     AQUA,
/* 178 */     BLUE,
/* 179 */     PINK,
/* 180 */     GRAY,
/* 181 */     WHITE,
/* 182 */     NONE;
/*     */   }
/*     */   
/*     */   public enum TEAM_STATE {
/* 186 */     BED_ALIVE,
/* 187 */     BED_BROKEN,
/* 188 */     DESTROYED,
/* 189 */     NONE;
/*     */   }
/*     */   
/*     */   public static class BedwarsPlayer {
/*     */     public String name;
/*     */     public CustomScoreboard.BedwarsTeam team;
/*     */     
/*     */     public BedwarsPlayer(String name, CustomScoreboard.BedwarsTeam team) {
/* 197 */       this.name = name;
/* 198 */       this.team = team;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BedwarsTeam {
/*     */     public CustomScoreboard.TEAM_COLOR color;
/*     */     public List<CustomScoreboard.BedwarsPlayer> players;
/*     */     public CustomScoreboard.TEAM_STATE state;
/*     */     
/*     */     public BedwarsTeam(CustomScoreboard.TEAM_COLOR color) {
/* 208 */       this.color = color;
/* 209 */       this.players = new ArrayList<CustomScoreboard.BedwarsPlayer>();
/* 210 */       this.state = CustomScoreboard.TEAM_STATE.NONE;
/*     */     }
/*     */   }
/*     */   
/*     */   public static TEAM_COLOR getTeamColorByCode(String color) {
/* 215 */     if (color.contains("&") || color.contains("§")) color = color.substring(1); 
/* 216 */     if (color.equals("c")) return TEAM_COLOR.RED; 
/* 217 */     if (color.equals("e")) return TEAM_COLOR.YELLOW; 
/* 218 */     if (color.equals("a")) return TEAM_COLOR.GREEN; 
/* 219 */     if (color.equals("b")) return TEAM_COLOR.AQUA; 
/* 220 */     if (color.equals("9")) return TEAM_COLOR.BLUE; 
/* 221 */     if (color.equals("d")) return TEAM_COLOR.PINK; 
/* 222 */     if (color.equals("7")) return TEAM_COLOR.GRAY; 
/* 223 */     if (color.equals("f")) return TEAM_COLOR.WHITE; 
/* 224 */     return TEAM_COLOR.NONE;
/*     */   }
/*     */   
/*     */   public static String getTeamNameByTeamColor(TEAM_COLOR color) {
/* 228 */     if (color == TEAM_COLOR.RED) return team_names[0]; 
/* 229 */     if (color == TEAM_COLOR.YELLOW) return team_names[1]; 
/* 230 */     if (color == TEAM_COLOR.GREEN) return team_names[2]; 
/* 231 */     if (color == TEAM_COLOR.AQUA) return team_names[3]; 
/* 232 */     if (color == TEAM_COLOR.BLUE) return team_names[4]; 
/* 233 */     if (color == TEAM_COLOR.PINK) return team_names[5]; 
/* 234 */     if (color == TEAM_COLOR.GRAY) return team_names[6]; 
/* 235 */     if (color == TEAM_COLOR.WHITE) return team_names[7]; 
/* 236 */     return "-";
/*     */   }
/*     */   
/*     */   public static String getTeamNameSinglePlayerByTeamColor(TEAM_COLOR color) {
/* 240 */     if (color == TEAM_COLOR.RED) return team_names_single_player[0]; 
/* 241 */     if (color == TEAM_COLOR.YELLOW) return team_names_single_player[1]; 
/* 242 */     if (color == TEAM_COLOR.GREEN) return team_names_single_player[2]; 
/* 243 */     if (color == TEAM_COLOR.AQUA) return team_names_single_player[3]; 
/* 244 */     if (color == TEAM_COLOR.BLUE) return team_names_single_player[4]; 
/* 245 */     if (color == TEAM_COLOR.PINK) return team_names_single_player[5]; 
/* 246 */     if (color == TEAM_COLOR.GRAY) return team_names_single_player[6]; 
/* 247 */     if (color == TEAM_COLOR.WHITE) return team_names_single_player[7]; 
/* 248 */     return "-";
/*     */   }
/*     */   
/* 251 */   public static String[] team_names = new String[] { "Красные", "Желтые", "Зеленые", "Голубые", "Синие", "Розовые", "Серые", "Белые" };
/*     */ 
/*     */ 
/*     */   
/* 255 */   public static String[] team_names_english = new String[] { "Red", "Yellow", "Green", "Aqua", "Blue", "Pink", "Gray", "White" };
/*     */ 
/*     */ 
/*     */   
/* 259 */   public static String[] team_names_single_player = new String[] { "Красный", "Желтый", "Зеленый", "Голубый", "Синий", "Розовый", "Серый", "Белый" };
/*     */ 
/*     */ 
/*     */   
/*     */   public static TEAM_COLOR getTeamColorByName(String name) {
/* 264 */     if (name.equals(team_names[0])) return TEAM_COLOR.RED; 
/* 265 */     if (name.equals(team_names[1])) return TEAM_COLOR.YELLOW; 
/* 266 */     if (name.equals(team_names[2])) return TEAM_COLOR.GREEN; 
/* 267 */     if (name.equals(team_names[3])) return TEAM_COLOR.AQUA; 
/* 268 */     if (name.equals(team_names[4])) return TEAM_COLOR.BLUE; 
/* 269 */     if (name.equals(team_names[5])) return TEAM_COLOR.PINK; 
/* 270 */     if (name.equals(team_names[6])) return TEAM_COLOR.GRAY; 
/* 271 */     if (name.equals(team_names[7])) return TEAM_COLOR.WHITE; 
/* 272 */     return TEAM_COLOR.NONE;
/*     */   }
/*     */   
/*     */   public static String getCodeByTeamColor(TEAM_COLOR color) {
/* 276 */     if (color == TEAM_COLOR.RED) return "c"; 
/* 277 */     if (color == TEAM_COLOR.YELLOW) return "e"; 
/* 278 */     if (color == TEAM_COLOR.GREEN) return "a"; 
/* 279 */     if (color == TEAM_COLOR.AQUA) return "b"; 
/* 280 */     if (color == TEAM_COLOR.BLUE) return "9"; 
/* 281 */     if (color == TEAM_COLOR.PINK) return "d"; 
/* 282 */     if (color == TEAM_COLOR.GRAY) return "7"; 
/* 283 */     if (color == TEAM_COLOR.WHITE) return "f"; 
/* 284 */     return "r";
/*     */   }
/*     */   
/*     */   public static List<BedwarsTeam> readBedwarsGame() {
/* 288 */     ArrayList<BedwarsTeam> teams = new ArrayList<BedwarsTeam>();
/* 289 */     ScoreboardManager.readScoreboard();
/*     */     
/* 291 */     for (int i = 0; i < team_names.length; i++) {
/* 292 */       String team_russian = team_names[i];
/* 293 */       String team_english = team_names_english[i];
/* 294 */       String str = ScoreboardManager.getText(team_russian);
/* 295 */       if (str == null) str = ScoreboardManager.getText(team_english); 
/* 296 */       if (str != null) {
/* 297 */         BedwarsTeam team = new BedwarsTeam(getTeamColorByName(team_russian));
/*     */         try {
/* 299 */           String icon = "";
/* 300 */           if (str.contains(":")) {
/* 301 */             icon = str.split(":")[1].trim().split(" ")[0].trim();
/* 302 */           } else if (str.contains("▸")) {
/* 303 */             icon = str.split("▸")[1].trim().split(" ")[0].trim();
/*     */           } 
/*     */ 
/*     */           
/* 307 */           if (icon.codePointAt(0) == 10004) {
/* 308 */             team.state = TEAM_STATE.BED_ALIVE;
/* 309 */           } else if (icon.codePointAt(0) == 10007) {
/* 310 */             team.state = TEAM_STATE.DESTROYED;
/*     */           } else {
/*     */             try {
/* 313 */               Integer.parseInt(icon);
/* 314 */               team.state = TEAM_STATE.BED_BROKEN;
/* 315 */             } catch (NumberFormatException nfe) {
/* 316 */               team.state = TEAM_STATE.NONE;
/*     */             } 
/*     */           } 
/* 319 */           if (team.state != TEAM_STATE.NONE) {
/* 320 */             teams.add(team);
/*     */           }
/*     */         }
/* 323 */         catch (Exception exception) {}
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 330 */     Minecraft mc = Minecraft.func_71410_x();
/* 331 */     if (mc == null || mc.field_71439_g == null) return teams; 
/* 332 */     if (mc.func_147114_u() == null || mc.func_147114_u().func_175106_d() == null) return teams; 
/* 333 */     Collection<NetworkPlayerInfo> players = mc.func_147114_u().func_175106_d();
/* 334 */     if (players == null || players.size() == 0) return teams;
/*     */     
/* 336 */     EntityPlayerSP mod_player = mc.field_71439_g;
/*     */     
/* 338 */     for (NetworkPlayerInfo info : players) {
/* 339 */       if (info.func_178845_a() == null || info.func_178850_i() == null)
/*     */         continue; 
/* 341 */       String player_name = info.func_178845_a().getName();
/* 342 */       String team_name = info.func_178850_i().func_96661_b();
/* 343 */       TEAM_COLOR c = MyChatListener.getEntityTeamColorByTeam(team_name);
/* 344 */       BedwarsTeam team = null;
/* 345 */       for (BedwarsTeam t : teams) {
/* 346 */         if (t.color == c) {
/* 347 */           team = t;
/*     */           break;
/*     */         } 
/*     */       } 
/* 351 */       if (team == null)
/* 352 */         continue;  team.players.add(new BedwarsPlayer(player_name, team));
/*     */     } 
/*     */ 
/*     */     
/* 356 */     return teams;
/*     */   }
/*     */   
/*     */   public static void printData() {
/*     */     try {
/* 361 */       List<BedwarsTeam> teams = readBedwarsGame();
/* 362 */       (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("teams: " + teams.size()));
/* 363 */       for (BedwarsTeam t : teams) {
/* 364 */         String stateColor = "&a";
/* 365 */         if (t.state == TEAM_STATE.BED_BROKEN) stateColor = "&e"; 
/* 366 */         if (t.state == TEAM_STATE.DESTROYED) stateColor = "&c"; 
/* 367 */         String str = stateColor + t.state + " &" + getCodeByTeamColor(t.color) + t.color + ":\n   &f- ";
/* 368 */         for (BedwarsPlayer p : t.players) {
/* 369 */           str = str + p.name + ", ";
/*     */         }
/* 371 */         (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(ColorCodesManager.replaceColorCodesInString(str)));
/*     */       }
/*     */     
/* 374 */     } catch (Exception ex) {
/* 375 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\CustomScoreboard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */