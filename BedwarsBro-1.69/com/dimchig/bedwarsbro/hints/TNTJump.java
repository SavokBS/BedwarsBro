/*     */ package com.dimchig.bedwarsbro.hints;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.ChatSender;
/*     */ import com.dimchig.bedwarsbro.Main;
/*     */ import com.dimchig.bedwarsbro.MyChatListener;
/*     */ import com.google.common.base.Predicate;
/*     */ import java.awt.Color;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraft.entity.item.EntityTNTPrimed;
/*     */ import net.minecraft.util.Vec3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TNTJump
/*     */   extends Gui
/*     */ {
/*  30 */   private final DecimalFormat timeFormatter = new DecimalFormat("0.0");
/*  31 */   private static EntityTNTPrimed tntForJump = null;
/*  32 */   private static int tntForJumpState = -1;
/*  33 */   private float TIME_STRAIGHT_FOR_RUN = 1.1F;
/*  34 */   private float TIME_STRAIGHT_FOR_JUMP = 0.5F;
/*  35 */   private float TIME_DIAGONAL_FOR_RUN = 1.2F;
/*  36 */   private float TIME_DIAGONAL_FOR_JUMP = 0.4F;
/*  37 */   private float TIME_UP_FOR_JUMP = 0.05F;
/*     */   
/*  39 */   private float TIME_FOR_RUN = this.TIME_STRAIGHT_FOR_RUN;
/*  40 */   private float TIME_FOR_JUMP = this.TIME_STRAIGHT_FOR_JUMP;
/*  41 */   private int unpress_keys_counter = -1;
/*     */   
/*  43 */   private double max_height = -1.0D;
/*  44 */   private int last_fuse = -1;
/*     */   
/*     */   public void draw(Vec3 playerPos, float partialTicks) {
/*  47 */     Minecraft mc = Minecraft.func_71410_x();
/*  48 */     EntityPlayerSP player = mc.field_71439_g;
/*     */ 
/*     */ 
/*     */     
/*  52 */     if (this.unpress_keys_counter >= 0) {
/*  53 */       this.unpress_keys_counter--;
/*     */ 
/*     */       
/*  56 */       if (this.unpress_keys_counter == 0) {
/*  57 */         pressUpKey((Minecraft.func_71410_x()).field_71474_y.field_74314_A);
/*  58 */         pressUpKey((Minecraft.func_71410_x()).field_71474_y.field_151444_V);
/*  59 */         this.unpress_keys_counter = -1;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  65 */     List<EntityTNTPrimed> entities = mc.field_71441_e.func_175644_a(EntityTNTPrimed.class, new Predicate<EntityTNTPrimed>()
/*     */         {
/*     */           public boolean apply(EntityTNTPrimed input)
/*     */           {
/*  69 */             return true;
/*     */           }
/*     */         });
/*     */     
/*  73 */     if (entities == null || entities.size() == 0)
/*  74 */       return;  for (EntityTNTPrimed en : entities) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  79 */       String text = "" + formatTNTTime((en.field_70516_a - partialTicks) / 20.0F);
/*     */       
/*  81 */       double x = en.field_70165_t;
/*  82 */       double y = en.field_70163_u + en.field_70131_O + 0.7D;
/*  83 */       double z = en.field_70161_v;
/*  84 */       float green = Math.min(en.field_70516_a / 50.0F, 1.0F);
/*  85 */       Color color = new Color(1.0F - green, green, 0.0F);
/*     */       
/*  87 */       Main.draw3DText.drawText(playerPos, new Vec3(x, y, z), player, text, color.getRGB());
/*     */       
/*  89 */       if (en.field_70516_a != this.last_fuse) {
/*     */         
/*  91 */         if ((this.last_fuse == -1 && en.field_70516_a > 70) || (en.field_70516_a > 0 && en.field_70516_a % 20 == 0)) {
/*     */           
/*  93 */           float pitch = 1.5F - en.field_70516_a / 20.0F * 0.2F;
/*  94 */           (Minecraft.func_71410_x()).field_71441_e.func_72980_b(x, y, z, "note.hat", 1.0F, pitch, false);
/*     */         } 
/*     */         
/*  97 */         if (en.field_70516_a == 0) {
/*  98 */           this.last_fuse = -1;
/*     */         }
/*     */         
/* 101 */         this.last_fuse = en.field_70516_a;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 108 */     if (tntForJump != null) {
/* 109 */       if (tntForJumpState == 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 127 */         double dX = player.field_70165_t - tntForJump.field_70165_t;
/* 128 */         double dZ = player.field_70161_v - tntForJump.field_70161_v;
/* 129 */         double angle = Math.toDegrees(Math.atan2(dZ, dX));
/* 130 */         double new_angle = 0.0D;
/* 131 */         int[] angles = { -180, -135, -90, -45, 0, 45, 90, 135, 180 };
/* 132 */         double min_dist = 9999.0D;
/* 133 */         for (int a : angles) {
/* 134 */           double d = Math.abs(angle - a);
/* 135 */           if (d < min_dist) {
/* 136 */             min_dist = d;
/* 137 */             new_angle = a;
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 142 */         angle = Math.toRadians(new_angle);
/* 143 */         double distance = 3.0D;
/*     */         
/* 145 */         if (tntForJump.func_70011_f(player.field_70165_t, tntForJump.field_70163_u, player.field_70161_v) <= 1.0D) {
/* 146 */           distance = 0.0D;
/* 147 */           angle = 0.0D;
/*     */         } 
/*     */ 
/*     */         
/* 151 */         double new_x = tntForJump.field_70165_t + distance * Math.cos(angle);
/* 152 */         double new_z = tntForJump.field_70161_v + distance * Math.sin(angle);
/*     */ 
/*     */         
/* 155 */         float speed = 0.004F;
/* 156 */         double dirX = (player.field_70165_t < new_x) ? 1.0D : -1.0D;
/* 157 */         double dirZ = (player.field_70161_v < new_z) ? 1.0D : -1.0D;
/*     */ 
/*     */         
/* 160 */         double dist = tntForJump.func_70011_f(player.field_70165_t, tntForJump.field_70163_u, player.field_70161_v);
/*     */         
/* 162 */         if (dirX != 0.0D || dirZ != 0.0D || dist != distance) {
/* 163 */           player.func_70107_b(player.field_70165_t + dirX * speed, player.field_70163_u, player.field_70161_v + dirZ * speed);
/*     */         }
/*     */         
/* 166 */         if (Math.abs(player.field_70165_t - new_x) <= speed && Math.abs(player.field_70161_v - new_z) <= speed) {
/* 167 */           player.func_70107_b(new_x, player.field_70163_u, new_z);
/* 168 */           dirX = 0.0D;
/* 169 */           dirZ = 0.0D;
/*     */         } 
/*     */         
/* 172 */         this.TIME_FOR_RUN = this.TIME_STRAIGHT_FOR_RUN;
/* 173 */         this.TIME_FOR_JUMP = this.TIME_STRAIGHT_FOR_JUMP;
/* 174 */         if (new_angle % 90.0D != 0.0D) {
/* 175 */           this.TIME_FOR_RUN = this.TIME_DIAGONAL_FOR_RUN;
/* 176 */           this.TIME_FOR_JUMP = this.TIME_DIAGONAL_FOR_JUMP;
/*     */         } 
/* 178 */         if (distance == 0.0D) {
/*     */           
/* 180 */           this.TIME_FOR_RUN = -1.0F;
/* 181 */           this.TIME_FOR_JUMP = this.TIME_UP_FOR_JUMP;
/*     */         } else {
/* 183 */           lookAtNearestTNT();
/*     */         } 
/*     */ 
/*     */         
/* 187 */         if (distance == 0.0D) {
/* 188 */           if (tntForJump.field_70516_a / 20.0F <= this.TIME_FOR_JUMP) {
/* 189 */             pressDownKey((Minecraft.func_71410_x()).field_71474_y.field_74314_A);
/* 190 */             tntForJumpState = -1;
/* 191 */             tntForJump = null;
/* 192 */             this.unpress_keys_counter = 300;
/* 193 */             this.max_height = player.field_70163_u;
/*     */           } 
/* 195 */         } else if (tntForJump.field_70516_a / 20.0F < this.TIME_FOR_RUN) {
/* 196 */           dist = tntForJump.func_70011_f(player.field_70165_t, tntForJump.field_70163_u, player.field_70161_v);
/* 197 */           if (Math.abs(dist - distance) > 0.1D) {
/* 198 */             ChatSender.addText(MyChatListener.PREFIX_TNT_JUMP + "&cПлохой тайминг");
/* 199 */             tntForJumpState = -1;
/* 200 */             tntForJump = null;
/*     */             
/*     */             return;
/*     */           } 
/* 204 */           pressDownKey((Minecraft.func_71410_x()).field_71474_y.field_151444_V);
/* 205 */           pressDownKey((Minecraft.func_71410_x()).field_71474_y.field_74351_w);
/*     */           
/* 207 */           tntForJumpState = 1;
/*     */         } 
/* 209 */       } else if (tntForJumpState == 1) {
/* 210 */         if (tntForJump.field_70516_a / 20.0F < this.TIME_FOR_JUMP) {
/* 211 */           pressDownKey((Minecraft.func_71410_x()).field_71474_y.field_74314_A);
/* 212 */           tntForJumpState = -1;
/*     */         } 
/* 214 */       } else if (tntForJumpState == -1) {
/*     */         
/* 216 */         tntForJump = null;
/* 217 */         this.unpress_keys_counter = 80;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void pressDownKey(KeyBinding key) {
/* 224 */     KeyBinding.func_74510_a(key.func_151463_i(), true);
/*     */   }
/*     */   
/*     */   void pressUpKey(KeyBinding key) {
/* 228 */     KeyBinding.func_74510_a(key.func_151463_i(), false);
/*     */   }
/*     */   
/*     */   public static void lookAtNearestTNT() {
/* 232 */     List<EntityTNTPrimed> entities = (Minecraft.func_71410_x()).field_71441_e.func_175644_a(EntityTNTPrimed.class, new Predicate<EntityTNTPrimed>()
/*     */         {
/*     */           public boolean apply(EntityTNTPrimed input)
/*     */           {
/* 236 */             return true;
/*     */           }
/*     */         });
/* 239 */     EntityPlayerSP player = (Minecraft.func_71410_x()).field_71439_g;
/* 240 */     if (entities == null || entities.size() == 0)
/* 241 */       return;  EntityTNTPrimed tnt = null;
/* 242 */     double min_dist = 999999.0D;
/* 243 */     for (EntityTNTPrimed en : entities) {
/* 244 */       double x = en.field_70165_t;
/* 245 */       double y = en.field_70163_u;
/* 246 */       double z = en.field_70161_v;
/* 247 */       double dist = en.func_70011_f(player.field_70165_t, player.field_70163_u, player.field_70161_v);
/* 248 */       if (dist < min_dist) {
/* 249 */         min_dist = dist;
/* 250 */         tnt = en;
/*     */       } 
/*     */     } 
/* 253 */     if (tnt == null)
/*     */       return; 
/* 255 */     HintsFinder.lookAtPlayer(player.field_70165_t, player.field_70163_u + player.func_70047_e(), player.field_70161_v, tnt.field_70165_t, tnt.field_70163_u, tnt.field_70161_v);
/* 256 */     tntForJump = tnt;
/* 257 */     tntForJumpState = 0;
/*     */   }
/*     */   
/*     */   String formatTNTTime(float time) {
/* 261 */     String str = "";
/* 262 */     if (time < 0.0F) time = 0.0F; 
/* 263 */     return this.timeFormatter.format(time);
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\hints\TNTJump.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */