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
/*     */ public class CustomScoreboard
/*     */ {
/*     */   private static ScoreboardManager sm;
/*     */   private static boolean isEnglishScoreboard = false;
/*     */   
/*     */   public CustomScoreboard() {
/*  26 */     sm = new ScoreboardManager();
/*  27 */     updateBooleans();
/*     */   }
/*     */   
/*     */   public void updateBooleans() {
/*  31 */     isEnglishScoreboard = Main.getConfigBool(Main.CONFIG_MSG.SCOREBOARD_ENGLISH);
/*     */   }
/*     */   
/*  34 */   private static String[] russianTranslations = new String[] { "Убийств", "Финальных§f убийств", "Сломано к§fроватей" };
/*     */ 
/*     */   
/*  37 */   private static String[] englishTranslations = new String[] { "Kills", "Finals", "Beds" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */     //   52: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   55: pop
/*     */     //   56: ldc 'Слом. кроватей:'
/*     */     //   58: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   61: ifnull -> 753
/*     */     //   64: aload_3
/*     */     //   65: ldc 'DimChig'
/*     */     //   67: invokevirtual contains : (Ljava/lang/CharSequence;)Z
/*     */     //   70: ifeq -> 142
/*     */     //   73: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   76: pop
/*     */     //   77: aload_2
/*     */     //   78: aload_0
/*     */     //   79: invokestatic replaceText : ([Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   82: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   85: pop
/*     */     //   86: ldc 'Донат'
/*     */     //   88: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   91: ifnull -> 112
/*     */     //   94: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   97: pop
/*     */     //   98: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   101: pop
/*     */     //   102: ldc 'Донат'
/*     */     //   104: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   107: ldc ' Донат: &c&l[You&f&lTube]'
/*     */     //   109: invokestatic replaceText : (Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   112: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   115: pop
/*     */     //   116: ldc 'Ник'
/*     */     //   118: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   121: ifnull -> 142
/*     */     //   124: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   127: pop
/*     */     //   128: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   131: pop
/*     */     //   132: ldc 'Ник'
/*     */     //   134: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   137: ldc ' Ник: &a&lDimChig'
/*     */     //   139: invokestatic replaceText : (Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   142: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   145: pop
/*     */     //   146: ldc 'Скрыть: /board'
/*     */     //   148: new java/lang/StringBuilder
/*     */     //   151: dup
/*     */     //   152: invokespecial <init> : ()V
/*     */     //   155: ldc ' '
/*     */     //   157: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   160: aload_1
/*     */     //   161: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   164: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   167: invokestatic replaceText : (Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   170: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   173: pop
/*     */     //   174: ldc 'Убийств:'
/*     */     //   176: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   179: astore #4
/*     */     //   181: aload #4
/*     */     //   183: ifnull -> 235
/*     */     //   186: aload #4
/*     */     //   188: ldc ' '
/*     */     //   190: invokevirtual contains : (Ljava/lang/CharSequence;)Z
/*     */     //   193: ifeq -> 235
/*     */     //   196: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   199: pop
/*     */     //   200: aload #4
/*     */     //   202: new java/lang/StringBuilder
/*     */     //   205: dup
/*     */     //   206: invokespecial <init> : ()V
/*     */     //   209: ldc ' Килов &7▸ &c'
/*     */     //   211: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   214: aload #4
/*     */     //   216: ldc ' '
/*     */     //   218: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
/*     */     //   221: iconst_1
/*     */     //   222: aaload
/*     */     //   223: invokevirtual trim : ()Ljava/lang/String;
/*     */     //   226: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   229: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   232: invokestatic replaceText : (Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   235: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   238: pop
/*     */     //   239: ldc 'Смертей:'
/*     */     //   241: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   244: astore #4
/*     */     //   246: aload #4
/*     */     //   248: ifnull -> 300
/*     */     //   251: aload #4
/*     */     //   253: ldc ': '
/*     */     //   255: invokevirtual contains : (Ljava/lang/CharSequence;)Z
/*     */     //   258: ifeq -> 300
/*     */     //   261: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   264: pop
/*     */     //   265: aload #4
/*     */     //   267: new java/lang/StringBuilder
/*     */     //   270: dup
/*     */     //   271: invokespecial <init> : ()V
/*     */     //   274: ldc ' Смертей &7▸ &e'
/*     */     //   276: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   279: aload #4
/*     */     //   281: ldc ': '
/*     */     //   283: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
/*     */     //   286: iconst_1
/*     */     //   287: aaload
/*     */     //   288: invokevirtual trim : ()Ljava/lang/String;
/*     */     //   291: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   294: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   297: invokestatic replaceText : (Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   300: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   303: pop
/*     */     //   304: ldc 'K/D:'
/*     */     //   306: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   309: astore #4
/*     */     //   311: aload #4
/*     */     //   313: ifnull -> 365
/*     */     //   316: aload #4
/*     */     //   318: ldc ': '
/*     */     //   320: invokevirtual contains : (Ljava/lang/CharSequence;)Z
/*     */     //   323: ifeq -> 365
/*     */     //   326: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   329: pop
/*     */     //   330: aload #4
/*     */     //   332: new java/lang/StringBuilder
/*     */     //   335: dup
/*     */     //   336: invokespecial <init> : ()V
/*     */     //   339: ldc ' K/D &7▸ &a'
/*     */     //   341: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   344: aload #4
/*     */     //   346: ldc ': '
/*     */     //   348: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
/*     */     //   351: iconst_1
/*     */     //   352: aaload
/*     */     //   353: invokevirtual trim : ()Ljava/lang/String;
/*     */     //   356: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   359: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   362: invokestatic replaceText : (Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   365: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   368: pop
/*     */     //   369: ldc 'Игр:'
/*     */     //   371: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   374: astore #4
/*     */     //   376: iconst_m1
/*     */     //   377: istore #5
/*     */     //   379: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   382: pop
/*     */     //   383: ldc 'Игр:'
/*     */     //   385: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   388: ldc ': '
/*     */     //   390: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
/*     */     //   393: iconst_1
/*     */     //   394: aaload
/*     */     //   395: invokevirtual trim : ()Ljava/lang/String;
/*     */     //   398: invokestatic parseInt : (Ljava/lang/String;)I
/*     */     //   401: istore #5
/*     */     //   403: goto -> 408
/*     */     //   406: astore #6
/*     */     //   408: aload #4
/*     */     //   410: ifnull -> 462
/*     */     //   413: aload #4
/*     */     //   415: ldc ': '
/*     */     //   417: invokevirtual contains : (Ljava/lang/CharSequence;)Z
/*     */     //   420: ifeq -> 462
/*     */     //   423: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   426: pop
/*     */     //   427: aload #4
/*     */     //   429: new java/lang/StringBuilder
/*     */     //   432: dup
/*     */     //   433: invokespecial <init> : ()V
/*     */     //   436: ldc ' Каток &7▸ &b'
/*     */     //   438: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   441: aload #4
/*     */     //   443: ldc ': '
/*     */     //   445: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
/*     */     //   448: iconst_1
/*     */     //   449: aaload
/*     */     //   450: invokevirtual trim : ()Ljava/lang/String;
/*     */     //   453: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   456: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   459: invokestatic replaceText : (Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   462: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   465: pop
/*     */     //   466: ldc 'Побед:'
/*     */     //   468: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   471: astore #4
/*     */     //   473: ldc2_w -1.0
/*     */     //   476: dstore #6
/*     */     //   478: new java/lang/StringBuilder
/*     */     //   481: dup
/*     */     //   482: invokespecial <init> : ()V
/*     */     //   485: ldc ' Побед &7▸ &9'
/*     */     //   487: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   490: aload #4
/*     */     //   492: ldc ': '
/*     */     //   494: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
/*     */     //   497: iconst_1
/*     */     //   498: aaload
/*     */     //   499: invokevirtual trim : ()Ljava/lang/String;
/*     */     //   502: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   505: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   508: astore #8
/*     */     //   510: iconst_m1
/*     */     //   511: istore #9
/*     */     //   513: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   516: pop
/*     */     //   517: ldc 'Побед:'
/*     */     //   519: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   522: ldc ': '
/*     */     //   524: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
/*     */     //   527: iconst_1
/*     */     //   528: aaload
/*     */     //   529: invokevirtual trim : ()Ljava/lang/String;
/*     */     //   532: invokestatic parseInt : (Ljava/lang/String;)I
/*     */     //   535: istore #9
/*     */     //   537: goto -> 542
/*     */     //   540: astore #10
/*     */     //   542: iload #5
/*     */     //   544: iconst_m1
/*     */     //   545: if_icmpeq -> 564
/*     */     //   548: iload #9
/*     */     //   550: iconst_m1
/*     */     //   551: if_icmpeq -> 564
/*     */     //   554: iload #9
/*     */     //   556: i2f
/*     */     //   557: iload #5
/*     */     //   559: i2f
/*     */     //   560: fdiv
/*     */     //   561: f2d
/*     */     //   562: dstore #6
/*     */     //   564: dload #6
/*     */     //   566: dconst_0
/*     */     //   567: dcmpl
/*     */     //   568: ifle -> 608
/*     */     //   571: new java/lang/StringBuilder
/*     */     //   574: dup
/*     */     //   575: invokespecial <init> : ()V
/*     */     //   578: aload #8
/*     */     //   580: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   583: ldc '&7 | &fWR &7▸ &9'
/*     */     //   585: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   588: dload #6
/*     */     //   590: ldc2_w 100.0
/*     */     //   593: dmul
/*     */     //   594: d2i
/*     */     //   595: invokevirtual append : (I)Ljava/lang/StringBuilder;
/*     */     //   598: ldc '%'
/*     */     //   600: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   603: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   606: astore #8
/*     */     //   608: aload #4
/*     */     //   610: ifnull -> 634
/*     */     //   613: aload #4
/*     */     //   615: ldc ': '
/*     */     //   617: invokevirtual contains : (Ljava/lang/CharSequence;)Z
/*     */     //   620: ifeq -> 634
/*     */     //   623: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   626: pop
/*     */     //   627: aload #4
/*     */     //   629: aload #8
/*     */     //   631: invokestatic replaceText : (Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   634: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   637: pop
/*     */     //   638: ldc 'Слом. кроватей:'
/*     */     //   640: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   643: astore #4
/*     */     //   645: new java/lang/StringBuilder
/*     */     //   648: dup
/*     */     //   649: invokespecial <init> : ()V
/*     */     //   652: ldc ' Кроватей &7▸ &d'
/*     */     //   654: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   657: aload #4
/*     */     //   659: ldc ': '
/*     */     //   661: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
/*     */     //   664: iconst_1
/*     */     //   665: aaload
/*     */     //   666: invokevirtual trim : ()Ljava/lang/String;
/*     */     //   669: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   672: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   675: astore #8
/*     */     //   677: iconst_m1
/*     */     //   678: istore #10
/*     */     //   680: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   683: pop
/*     */     //   684: ldc 'Слом. кроватей:'
/*     */     //   686: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   689: ldc ': '
/*     */     //   691: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
/*     */     //   694: iconst_1
/*     */     //   695: aaload
/*     */     //   696: invokevirtual trim : ()Ljava/lang/String;
/*     */     //   699: invokestatic parseInt : (Ljava/lang/String;)I
/*     */     //   702: istore #10
/*     */     //   704: goto -> 709
/*     */     //   707: astore #11
/*     */     //   709: iload #10
/*     */     //   711: ifle -> 719
/*     */     //   714: iload #5
/*     */     //   716: ifle -> 719
/*     */     //   719: aload #4
/*     */     //   721: ifnull -> 745
/*     */     //   724: aload #4
/*     */     //   726: ldc ': '
/*     */     //   728: invokevirtual contains : (Ljava/lang/CharSequence;)Z
/*     */     //   731: ifeq -> 745
/*     */     //   734: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   737: pop
/*     */     //   738: aload #4
/*     */     //   740: aload #8
/*     */     //   742: invokestatic replaceText : (Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   745: goto -> 1165
/*     */     //   748: astore #4
/*     */     //   750: goto -> 1165
/*     */     //   753: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   756: pop
/*     */     //   757: ldc 'Старт через: '
/*     */     //   759: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   762: ifnonnull -> 777
/*     */     //   765: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   768: pop
/*     */     //   769: ldc 'Карта: '
/*     */     //   771: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   774: ifnull -> 798
/*     */     //   777: aload_3
/*     */     //   778: ldc 'DimChig'
/*     */     //   780: invokevirtual contains : (Ljava/lang/CharSequence;)Z
/*     */     //   783: ifeq -> 1165
/*     */     //   786: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   789: pop
/*     */     //   790: aload_2
/*     */     //   791: aload_0
/*     */     //   792: invokestatic replaceText : ([Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   795: goto -> 1165
/*     */     //   798: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   801: pop
/*     */     //   802: getstatic com/dimchig/bedwarsbro/CustomScoreboard.russianTranslations : [Ljava/lang/String;
/*     */     //   805: iconst_1
/*     */     //   806: aaload
/*     */     //   807: invokestatic removeColorCodes : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   810: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   813: ifnonnull -> 831
/*     */     //   816: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   819: pop
/*     */     //   820: getstatic com/dimchig/bedwarsbro/CustomScoreboard.englishTranslations : [Ljava/lang/String;
/*     */     //   823: iconst_1
/*     */     //   824: aaload
/*     */     //   825: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   828: ifnull -> 1165
/*     */     //   831: iconst_0
/*     */     //   832: istore #4
/*     */     //   834: iload #4
/*     */     //   836: getstatic com/dimchig/bedwarsbro/CustomScoreboard.team_names : [Ljava/lang/String;
/*     */     //   839: arraylength
/*     */     //   840: if_icmpge -> 1108
/*     */     //   843: getstatic com/dimchig/bedwarsbro/CustomScoreboard.team_names : [Ljava/lang/String;
/*     */     //   846: iload #4
/*     */     //   848: aaload
/*     */     //   849: astore #5
/*     */     //   851: getstatic com/dimchig/bedwarsbro/CustomScoreboard.isEnglishScoreboard : Z
/*     */     //   854: ifeq -> 866
/*     */     //   857: getstatic com/dimchig/bedwarsbro/CustomScoreboard.team_names_english : [Ljava/lang/String;
/*     */     //   860: iload #4
/*     */     //   862: aaload
/*     */     //   863: goto -> 868
/*     */     //   866: aload #5
/*     */     //   868: astore #6
/*     */     //   870: aload #5
/*     */     //   872: invokestatic getTeamColorByName : (Ljava/lang/String;)Lcom/dimchig/bedwarsbro/CustomScoreboard$TEAM_COLOR;
/*     */     //   875: astore #7
/*     */     //   877: new java/lang/StringBuilder
/*     */     //   880: dup
/*     */     //   881: invokespecial <init> : ()V
/*     */     //   884: ldc '&'
/*     */     //   886: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   889: aload #7
/*     */     //   891: invokestatic getCodeByTeamColor : (Lcom/dimchig/bedwarsbro/CustomScoreboard$TEAM_COLOR;)Ljava/lang/String;
/*     */     //   894: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   897: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   900: astore #8
/*     */     //   902: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   905: pop
/*     */     //   906: aload #5
/*     */     //   908: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   911: astore #9
/*     */     //   913: aload #9
/*     */     //   915: ifnonnull -> 921
/*     */     //   918: goto -> 1102
/*     */     //   921: aload #9
/*     */     //   923: invokevirtual trim : ()Ljava/lang/String;
/*     */     //   926: astore #9
/*     */     //   928: aload #5
/*     */     //   930: astore #10
/*     */     //   932: aload #9
/*     */     //   934: ldc ':'
/*     */     //   936: invokevirtual contains : (Ljava/lang/CharSequence;)Z
/*     */     //   939: ifeq -> 967
/*     */     //   942: new java/lang/StringBuilder
/*     */     //   945: dup
/*     */     //   946: invokespecial <init> : ()V
/*     */     //   949: aload #10
/*     */     //   951: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   954: ldc ':'
/*     */     //   956: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   959: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   962: astore #10
/*     */     //   964: goto -> 994
/*     */     //   967: new java/lang/StringBuilder
/*     */     //   970: dup
/*     */     //   971: invokespecial <init> : ()V
/*     */     //   974: aload #10
/*     */     //   976: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   979: aload #8
/*     */     //   981: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   984: ldc ' ▸'
/*     */     //   986: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   989: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   992: astore #10
/*     */     //   994: ldc ''
/*     */     //   996: astore #11
/*     */     //   998: aload #9
/*     */     //   1000: ldc '✗'
/*     */     //   1002: invokevirtual contains : (Ljava/lang/CharSequence;)Z
/*     */     //   1005: ifeq -> 1012
/*     */     //   1008: ldc '&m'
/*     */     //   1010: astore #11
/*     */     //   1012: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   1015: pop
/*     */     //   1016: aload #10
/*     */     //   1018: new java/lang/StringBuilder
/*     */     //   1021: dup
/*     */     //   1022: invokespecial <init> : ()V
/*     */     //   1025: aload #8
/*     */     //   1027: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1030: aload #11
/*     */     //   1032: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1035: aload #6
/*     */     //   1037: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1040: aload #8
/*     */     //   1042: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1045: ldc ' ▸'
/*     */     //   1047: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1050: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   1053: invokestatic replaceColorCodesInString : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   1056: invokestatic hardReplaceText : (Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   1059: getstatic com/dimchig/bedwarsbro/CustomScoreboard.isEnglishScoreboard : Z
/*     */     //   1062: ifeq -> 1102
/*     */     //   1065: iconst_0
/*     */     //   1066: istore #12
/*     */     //   1068: iload #12
/*     */     //   1070: getstatic com/dimchig/bedwarsbro/CustomScoreboard.russianTranslations : [Ljava/lang/String;
/*     */     //   1073: arraylength
/*     */     //   1074: if_icmpge -> 1102
/*     */     //   1077: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   1080: pop
/*     */     //   1081: getstatic com/dimchig/bedwarsbro/CustomScoreboard.russianTranslations : [Ljava/lang/String;
/*     */     //   1084: iload #12
/*     */     //   1086: aaload
/*     */     //   1087: getstatic com/dimchig/bedwarsbro/CustomScoreboard.englishTranslations : [Ljava/lang/String;
/*     */     //   1090: iload #12
/*     */     //   1092: aaload
/*     */     //   1093: invokestatic hardReplaceText : (Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   1096: iinc #12, 1
/*     */     //   1099: goto -> 1068
/*     */     //   1102: iinc #4, 1
/*     */     //   1105: goto -> 834
/*     */     //   1108: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   1111: pop
/*     */     //   1112: ldc '(Вы)'
/*     */     //   1114: invokestatic getText : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   1117: astore #4
/*     */     //   1119: aload #4
/*     */     //   1121: ifnull -> 1135
/*     */     //   1124: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   1127: pop
/*     */     //   1128: ldc '(Вы)'
/*     */     //   1130: ldc '&8←'
/*     */     //   1132: invokestatic hardReplaceText : (Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   1135: aload_3
/*     */     //   1136: ldc 'DimChig'
/*     */     //   1138: invokevirtual contains : (Ljava/lang/CharSequence;)Z
/*     */     //   1141: ifeq -> 1156
/*     */     //   1144: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   1147: pop
/*     */     //   1148: aload_2
/*     */     //   1149: aload_0
/*     */     //   1150: invokestatic replaceText : ([Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   1153: goto -> 1165
/*     */     //   1156: getstatic com/dimchig/bedwarsbro/CustomScoreboard.sm : Lcom/dimchig/bedwarsbro/ScoreboardManager;
/*     */     //   1159: pop
/*     */     //   1160: aload_2
/*     */     //   1161: aload_1
/*     */     //   1162: invokestatic replaceText : ([Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   1165: new java/lang/StringBuilder
/*     */     //   1168: dup
/*     */     //   1169: invokespecial <init> : ()V
/*     */     //   1172: ldc '\\n'
/*     */     //   1174: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1177: aload_1
/*     */     //   1178: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1181: ldc ' &7v&a'
/*     */     //   1183: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1186: ldc '1.69'
/*     */     //   1188: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1191: ldc ' &7(&e/bwbro&7)\\n'
/*     */     //   1193: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1196: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   1199: astore #4
/*     */     //   1201: ldc ''
/*     */     //   1203: astore #5
/*     */     //   1205: invokestatic func_71410_x : ()Lnet/minecraft/client/Minecraft;
/*     */     //   1208: invokevirtual func_147104_D : ()Lnet/minecraft/client/multiplayer/ServerData;
/*     */     //   1211: ifnull -> 1248
/*     */     //   1214: new java/lang/StringBuilder
/*     */     //   1217: dup
/*     */     //   1218: invokespecial <init> : ()V
/*     */     //   1221: ldc '\\n&7Ты играешь на &e'
/*     */     //   1223: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1226: invokestatic func_71410_x : ()Lnet/minecraft/client/Minecraft;
/*     */     //   1229: invokevirtual func_147104_D : ()Lnet/minecraft/client/multiplayer/ServerData;
/*     */     //   1232: getfield field_78845_b : Ljava/lang/String;
/*     */     //   1235: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1238: ldc '\\n'
/*     */     //   1240: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1243: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   1246: astore #5
/*     */     //   1248: invokestatic func_71410_x : ()Lnet/minecraft/client/Minecraft;
/*     */     //   1251: getfield field_71456_v : Lnet/minecraft/client/gui/GuiIngame;
/*     */     //   1254: invokevirtual func_175181_h : ()Lnet/minecraft/client/gui/GuiPlayerTabOverlay;
/*     */     //   1257: new net/minecraft/util/ChatComponentText
/*     */     //   1260: dup
/*     */     //   1261: aload #4
/*     */     //   1263: invokestatic replaceColorCodesInString : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   1266: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   1269: invokevirtual func_175244_b : (Lnet/minecraft/util/IChatComponent;)V
/*     */     //   1272: invokestatic func_71410_x : ()Lnet/minecraft/client/Minecraft;
/*     */     //   1275: getfield field_71456_v : Lnet/minecraft/client/gui/GuiIngame;
/*     */     //   1278: invokevirtual func_175181_h : ()Lnet/minecraft/client/gui/GuiPlayerTabOverlay;
/*     */     //   1281: new net/minecraft/util/ChatComponentText
/*     */     //   1284: dup
/*     */     //   1285: aload #5
/*     */     //   1287: invokestatic replaceColorCodesInString : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   1290: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   1293: invokevirtual func_175248_a : (Lnet/minecraft/util/IChatComponent;)V
/*     */     //   1296: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #43	-> 0
/*     */     //   #44	-> 3
/*     */     //   #45	-> 6
/*     */     //   #47	-> 26
/*     */     //   #48	-> 42
/*     */     //   #51	-> 52
/*     */     //   #53	-> 64
/*     */     //   #54	-> 73
/*     */     //   #55	-> 82
/*     */     //   #56	-> 94
/*     */     //   #58	-> 112
/*     */     //   #59	-> 124
/*     */     //   #63	-> 142
/*     */     //   #66	-> 170
/*     */     //   #67	-> 181
/*     */     //   #69	-> 235
/*     */     //   #70	-> 246
/*     */     //   #72	-> 300
/*     */     //   #73	-> 311
/*     */     //   #75	-> 365
/*     */     //   #76	-> 376
/*     */     //   #78	-> 379
/*     */     //   #79	-> 403
/*     */     //   #80	-> 408
/*     */     //   #82	-> 462
/*     */     //   #83	-> 473
/*     */     //   #84	-> 478
/*     */     //   #85	-> 510
/*     */     //   #87	-> 513
/*     */     //   #88	-> 537
/*     */     //   #89	-> 542
/*     */     //   #90	-> 554
/*     */     //   #93	-> 564
/*     */     //   #95	-> 608
/*     */     //   #98	-> 634
/*     */     //   #99	-> 645
/*     */     //   #100	-> 677
/*     */     //   #102	-> 680
/*     */     //   #103	-> 704
/*     */     //   #104	-> 709
/*     */     //   #108	-> 719
/*     */     //   #109	-> 745
/*     */     //   #111	-> 753
/*     */     //   #113	-> 777
/*     */     //   #114	-> 786
/*     */     //   #116	-> 798
/*     */     //   #119	-> 831
/*     */     //   #120	-> 843
/*     */     //   #121	-> 851
/*     */     //   #122	-> 870
/*     */     //   #123	-> 877
/*     */     //   #124	-> 902
/*     */     //   #125	-> 913
/*     */     //   #126	-> 921
/*     */     //   #127	-> 928
/*     */     //   #128	-> 932
/*     */     //   #129	-> 967
/*     */     //   #131	-> 994
/*     */     //   #132	-> 998
/*     */     //   #135	-> 1012
/*     */     //   #137	-> 1059
/*     */     //   #138	-> 1065
/*     */     //   #139	-> 1077
/*     */     //   #138	-> 1096
/*     */     //   #119	-> 1102
/*     */     //   #144	-> 1108
/*     */     //   #145	-> 1119
/*     */     //   #146	-> 1124
/*     */     //   #149	-> 1135
/*     */     //   #150	-> 1144
/*     */     //   #152	-> 1156
/*     */     //   #158	-> 1165
/*     */     //   #159	-> 1201
/*     */     //   #160	-> 1205
/*     */     //   #161	-> 1214
/*     */     //   #164	-> 1248
/*     */     //   #166	-> 1272
/*     */     //   #167	-> 1296
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   181	564	4	text	Ljava/lang/String;
/*     */     //   379	366	5	games_cnt	I
/*     */     //   478	267	6	win_rate	D
/*     */     //   510	235	8	new_text	Ljava/lang/String;
/*     */     //   513	232	9	wins_cnt	I
/*     */     //   680	65	10	beds_cnt	I
/*     */     //   1068	34	12	j	I
/*     */     //   851	251	5	team_name	Ljava/lang/String;
/*     */     //   870	232	6	team_name_new	Ljava/lang/String;
/*     */     //   877	225	7	team_color	Lcom/dimchig/bedwarsbro/CustomScoreboard$TEAM_COLOR;
/*     */     //   902	200	8	colorcode	Ljava/lang/String;
/*     */     //   913	189	9	source	Ljava/lang/String;
/*     */     //   932	170	10	replace	Ljava/lang/String;
/*     */     //   998	104	11	extraFormatting	Ljava/lang/String;
/*     */     //   834	274	4	i	I
/*     */     //   1119	46	4	text_your_team	Ljava/lang/String;
/*     */     //   3	1294	0	subscribe	Ljava/lang/String;
/*     */     //   6	1291	1	mod_name	Ljava/lang/String;
/*     */     //   26	1271	2	servers	[Ljava/lang/String;
/*     */     //   52	1245	3	mod_player_name	Ljava/lang/String;
/*     */     //   1201	96	4	tab_header_text	Ljava/lang/String;
/*     */     //   1205	92	5	tab_footer_text	Ljava/lang/String;
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   170	745	748	java/lang/Exception
/*     */     //   379	403	406	java/lang/Exception
/*     */     //   513	537	540	java/lang/Exception
/*     */     //   680	704	707	java/lang/Exception
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
/*     */   public enum TEAM_COLOR
/*     */   {
/* 171 */     RED,
/* 172 */     YELLOW,
/* 173 */     GREEN,
/* 174 */     AQUA,
/* 175 */     BLUE,
/* 176 */     PINK,
/* 177 */     GRAY,
/* 178 */     WHITE,
/* 179 */     NONE;
/*     */   }
/*     */   
/*     */   public enum TEAM_STATE {
/* 183 */     BED_ALIVE,
/* 184 */     BED_BROKEN,
/* 185 */     DESTROYED,
/* 186 */     NONE;
/*     */   }
/*     */   
/*     */   public static class BedwarsPlayer {
/*     */     public String name;
/*     */     public CustomScoreboard.BedwarsTeam team;
/*     */     
/*     */     public BedwarsPlayer(String name, CustomScoreboard.BedwarsTeam team) {
/* 194 */       this.name = name;
/* 195 */       this.team = team;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BedwarsTeam {
/*     */     public CustomScoreboard.TEAM_COLOR color;
/*     */     public List<CustomScoreboard.BedwarsPlayer> players;
/*     */     public CustomScoreboard.TEAM_STATE state;
/*     */     
/*     */     public BedwarsTeam(CustomScoreboard.TEAM_COLOR color) {
/* 205 */       this.color = color;
/* 206 */       this.players = new ArrayList<CustomScoreboard.BedwarsPlayer>();
/* 207 */       this.state = CustomScoreboard.TEAM_STATE.NONE;
/*     */     }
/*     */   }
/*     */   
/*     */   public static TEAM_COLOR getTeamColorByCode(String color) {
/* 212 */     if (color.contains("&") || color.contains("§")) color = color.substring(1); 
/* 213 */     if (color.equals("c")) return TEAM_COLOR.RED; 
/* 214 */     if (color.equals("e")) return TEAM_COLOR.YELLOW; 
/* 215 */     if (color.equals("a")) return TEAM_COLOR.GREEN; 
/* 216 */     if (color.equals("b")) return TEAM_COLOR.AQUA; 
/* 217 */     if (color.equals("9")) return TEAM_COLOR.BLUE; 
/* 218 */     if (color.equals("d")) return TEAM_COLOR.PINK; 
/* 219 */     if (color.equals("7")) return TEAM_COLOR.GRAY; 
/* 220 */     if (color.equals("f")) return TEAM_COLOR.WHITE; 
/* 221 */     return TEAM_COLOR.NONE;
/*     */   }
/*     */   
/*     */   public static String getTeamNameByTeamColor(TEAM_COLOR color) {
/* 225 */     if (color == TEAM_COLOR.RED) return team_names[0]; 
/* 226 */     if (color == TEAM_COLOR.YELLOW) return team_names[1]; 
/* 227 */     if (color == TEAM_COLOR.GREEN) return team_names[2]; 
/* 228 */     if (color == TEAM_COLOR.AQUA) return team_names[3]; 
/* 229 */     if (color == TEAM_COLOR.BLUE) return team_names[4]; 
/* 230 */     if (color == TEAM_COLOR.PINK) return team_names[5]; 
/* 231 */     if (color == TEAM_COLOR.GRAY) return team_names[6]; 
/* 232 */     if (color == TEAM_COLOR.WHITE) return team_names[7]; 
/* 233 */     return "-";
/*     */   }
/*     */   
/*     */   public static String getTeamNameSinglePlayerByTeamColor(TEAM_COLOR color) {
/* 237 */     if (color == TEAM_COLOR.RED) return team_names_single_player[0]; 
/* 238 */     if (color == TEAM_COLOR.YELLOW) return team_names_single_player[1]; 
/* 239 */     if (color == TEAM_COLOR.GREEN) return team_names_single_player[2]; 
/* 240 */     if (color == TEAM_COLOR.AQUA) return team_names_single_player[3]; 
/* 241 */     if (color == TEAM_COLOR.BLUE) return team_names_single_player[4]; 
/* 242 */     if (color == TEAM_COLOR.PINK) return team_names_single_player[5]; 
/* 243 */     if (color == TEAM_COLOR.GRAY) return team_names_single_player[6]; 
/* 244 */     if (color == TEAM_COLOR.WHITE) return team_names_single_player[7]; 
/* 245 */     return "-";
/*     */   }
/*     */   
/* 248 */   public static String[] team_names = new String[] { "Красные", "Желтые", "Зеленые", "Голубые", "Синие", "Розовые", "Серые", "Белые" };
/*     */ 
/*     */ 
/*     */   
/* 252 */   public static String[] team_names_english = new String[] { "Red", "Yellow", "Green", "Aqua", "Blue", "Pink", "Gray", "White" };
/*     */ 
/*     */ 
/*     */   
/* 256 */   public static String[] team_names_single_player = new String[] { "Красный", "Желтый", "Зеленый", "Голубый", "Синий", "Розовый", "Серый", "Белый" };
/*     */ 
/*     */ 
/*     */   
/*     */   public static TEAM_COLOR getTeamColorByName(String name) {
/* 261 */     if (name.equals(team_names[0])) return TEAM_COLOR.RED; 
/* 262 */     if (name.equals(team_names[1])) return TEAM_COLOR.YELLOW; 
/* 263 */     if (name.equals(team_names[2])) return TEAM_COLOR.GREEN; 
/* 264 */     if (name.equals(team_names[3])) return TEAM_COLOR.AQUA; 
/* 265 */     if (name.equals(team_names[4])) return TEAM_COLOR.BLUE; 
/* 266 */     if (name.equals(team_names[5])) return TEAM_COLOR.PINK; 
/* 267 */     if (name.equals(team_names[6])) return TEAM_COLOR.GRAY; 
/* 268 */     if (name.equals(team_names[7])) return TEAM_COLOR.WHITE; 
/* 269 */     return TEAM_COLOR.NONE;
/*     */   }
/*     */   
/*     */   public static String getCodeByTeamColor(TEAM_COLOR color) {
/* 273 */     if (color == TEAM_COLOR.RED) return "c"; 
/* 274 */     if (color == TEAM_COLOR.YELLOW) return "e"; 
/* 275 */     if (color == TEAM_COLOR.GREEN) return "a"; 
/* 276 */     if (color == TEAM_COLOR.AQUA) return "b"; 
/* 277 */     if (color == TEAM_COLOR.BLUE) return "9"; 
/* 278 */     if (color == TEAM_COLOR.PINK) return "d"; 
/* 279 */     if (color == TEAM_COLOR.GRAY) return "7"; 
/* 280 */     if (color == TEAM_COLOR.WHITE) return "f"; 
/* 281 */     return "r";
/*     */   }
/*     */   
/*     */   public static List<BedwarsTeam> readBedwarsGame() {
/* 285 */     ArrayList<BedwarsTeam> teams = new ArrayList<BedwarsTeam>();
/* 286 */     ScoreboardManager.readScoreboard();
/*     */     
/* 288 */     for (int i = 0; i < team_names.length; i++) {
/* 289 */       String team_russian = team_names[i];
/* 290 */       String team_english = team_names_english[i];
/* 291 */       String str = ScoreboardManager.getText(team_russian);
/* 292 */       if (str == null) str = ScoreboardManager.getText(team_english); 
/* 293 */       if (str != null) {
/* 294 */         BedwarsTeam team = new BedwarsTeam(getTeamColorByName(team_russian));
/*     */         try {
/* 296 */           String icon = "";
/* 297 */           if (str.contains(":")) {
/* 298 */             icon = str.split(":")[1].trim().split(" ")[0].trim();
/* 299 */           } else if (str.contains("▸")) {
/* 300 */             icon = str.split("▸")[1].trim().split(" ")[0].trim();
/*     */           } 
/*     */ 
/*     */           
/* 304 */           if (icon.codePointAt(0) == 10004) {
/* 305 */             team.state = TEAM_STATE.BED_ALIVE;
/* 306 */           } else if (icon.codePointAt(0) == 10007) {
/* 307 */             team.state = TEAM_STATE.DESTROYED;
/*     */           } else {
/*     */             try {
/* 310 */               Integer.parseInt(icon);
/* 311 */               team.state = TEAM_STATE.BED_BROKEN;
/* 312 */             } catch (NumberFormatException nfe) {
/* 313 */               team.state = TEAM_STATE.NONE;
/*     */             } 
/*     */           } 
/* 316 */           if (team.state != TEAM_STATE.NONE) {
/* 317 */             teams.add(team);
/*     */           }
/*     */         }
/* 320 */         catch (Exception exception) {}
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 327 */     Minecraft mc = Minecraft.func_71410_x();
/* 328 */     if (mc == null || mc.field_71439_g == null) return teams; 
/* 329 */     if (mc.func_147114_u() == null || mc.func_147114_u().func_175106_d() == null) return teams; 
/* 330 */     Collection<NetworkPlayerInfo> players = mc.func_147114_u().func_175106_d();
/* 331 */     if (players == null || players.size() == 0) return teams;
/*     */     
/* 333 */     EntityPlayerSP mod_player = mc.field_71439_g;
/*     */     
/* 335 */     for (NetworkPlayerInfo info : players) {
/* 336 */       if (info.func_178845_a() == null || info.func_178850_i() == null)
/*     */         continue; 
/* 338 */       String player_name = info.func_178845_a().getName();
/* 339 */       String team_name = info.func_178850_i().func_96661_b();
/* 340 */       TEAM_COLOR c = MyChatListener.getEntityTeamColorByTeam(team_name);
/* 341 */       BedwarsTeam team = null;
/* 342 */       for (BedwarsTeam t : teams) {
/* 343 */         if (t.color == c) {
/* 344 */           team = t;
/*     */           break;
/*     */         } 
/*     */       } 
/* 348 */       if (team == null)
/* 349 */         continue;  team.players.add(new BedwarsPlayer(player_name, team));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
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


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\CustomScoreboard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */