/*     */ package com.dimchig.bedwarsbro.gui;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.ChatSender;
/*     */ import com.dimchig.bedwarsbro.CustomScoreboard;
/*     */ import com.dimchig.bedwarsbro.Main;
/*     */ import com.dimchig.bedwarsbro.MyChatListener;
/*     */ import com.dimchig.bedwarsbro.hints.BWBed;
/*     */ import com.dimchig.bedwarsbro.hints.HintsValidator;
/*     */ import com.dimchig.bedwarsbro.particles.ParticleController;
/*     */ import java.awt.Color;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockBed;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.multiplayer.WorldClient;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.boss.EntityDragon;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EntitySelectors;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiMinimap
/*     */   extends Gui
/*     */ {
/*     */   public static boolean isActive = false;
/*     */   public static boolean isHidePlayersOnShift = false;
/*     */   private static int offset;
/*     */   private static int topX;
/*     */   private static int topY;
/*     */   private static int botX;
/*     */   private static int botY;
/*     */   public static int map_size;
/*     */   private static boolean show_heights;
/*  63 */   private ResourceLocation resourceLoc_enemy = new ResourceLocation("bedwarschatmod:textures/gui/minimap_icons.png");
/*     */   private static Minecraft mc;
/*     */   private TextureManager textureManager;
/*  66 */   private int tick_cnt = 0;
/*  67 */   private final DecimalFormat timeFormatter = new DecimalFormat("0.0");
/*     */   
/*  69 */   public static ArrayList<MyBed> bedsFound = new ArrayList<MyBed>();
/*  70 */   private static long time_last_bed_scanned = 0L;
/*  71 */   private static long TIME_BED_SCAN = 1000L; private int minimap_scan_z_value; private int minimap_scan_z_step; private int minimap_scan_range;
/*     */   private boolean minimap_scan_is_active;
/*     */   
/*     */   public static class MyBed { public BlockPos pos;
/*     */     public BlockPos pos_feet;
/*     */     public long t;
/*     */     public boolean isPlayersBed;
/*     */     public ArrayList<BlockPos> obsidianPoses;
/*     */     
/*     */     public MyBed(BlockPos pos, BlockPos pos_feet, long t, boolean isPlayersBed) {
/*  81 */       this.pos = pos;
/*  82 */       this.pos_feet = pos_feet;
/*  83 */       this.t = t;
/*  84 */       this.isPlayersBed = isPlayersBed;
/*  85 */       this.obsidianPoses = new ArrayList<BlockPos>();
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void updateSizes() {
/*     */     try {
/* 101 */       map_size = Main.getConfigInt(Main.CONFIG_MSG.MINIMAP_SIZE);
/*     */       
/* 103 */       ScaledResolution sr = new ScaledResolution(mc);
/* 104 */       int screen_width = sr.func_78326_a();
/* 105 */       int screen_height = sr.func_78328_b();
/*     */       
/* 107 */       String ox = Main.getConfigString(Main.CONFIG_MSG.MINIMAP_X);
/* 108 */       int offsetX = Integer.parseInt(ox.replace("-", ""));
/* 109 */       if (ox.startsWith("-")) offsetX = screen_width - offsetX - map_size;
/*     */       
/* 111 */       String oy = Main.getConfigString(Main.CONFIG_MSG.MINIMAP_Y);
/* 112 */       int offsetY = Integer.parseInt(oy.replace("-", ""));
/* 113 */       if (oy.startsWith("-")) offsetY = screen_height - offsetY - map_size;
/*     */       
/* 115 */       show_heights = Main.getConfigBool(Main.CONFIG_MSG.MINIMAP_SHOW_HEIGHT);
/*     */       
/* 117 */       topX = offsetX;
/* 118 */       topY = offsetY;
/* 119 */       botX = topX + map_size;
/* 120 */       botY = topY + map_size;
/* 121 */     } catch (Exception ex) {
/* 122 */       ChatSender.addText("&aMinimap: &cОшибка в config!");
/* 123 */       map_size = 100;
/* 124 */       topX = 0;
/* 125 */       topY = 0;
/* 126 */       botX = topX + map_size;
/* 127 */       botY = topY + map_size;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void updateBooleans() {
/* 132 */     isActive = HintsValidator.isMinimapActive();
/* 133 */     isHidePlayersOnShift = Main.getConfigBool(Main.CONFIG_MSG.MINIMAP_HIDE_PLAYERS_ON_SHIFT);
/* 134 */     updateSizes();
/*     */   }
/*     */   
/*     */   public static class Pos { public double x;
/*     */     public double y;
/*     */     public double z;
/*     */     
/*     */     public Pos(double x, double y, double z) {
/* 142 */       this.x = x;
/* 143 */       this.y = y;
/* 144 */       this.z = z;
/*     */     } }
/*     */   
/*     */   public static class PosItem {
/*     */     double x;
/*     */     double y;
/*     */     double z;
/*     */     int type;
/*     */     int cnt;
/*     */     
/*     */     public PosItem(double x, double y, double z, int type, int cnt) {
/* 155 */       this.x = x;
/* 156 */       this.y = y;
/* 157 */       this.z = z;
/* 158 */       this.type = type;
/* 159 */       this.cnt = cnt;
/*     */     }
/*     */   }
/*     */   
/*     */   public void clearGameBeds() {
/* 164 */     if (bedsFound == null) bedsFound = new ArrayList<MyBed>(); 
/* 165 */     bedsFound.clear();
/*     */   }
/*     */   
/* 168 */   public GuiMinimap() { this.minimap_scan_z_value = 0;
/* 169 */     this.minimap_scan_z_step = 2;
/* 170 */     this.minimap_scan_range = 75;
/* 171 */     this.minimap_scan_is_active = false; mc = Minecraft.func_71410_x(); this.textureManager = mc.func_110434_K();
/*     */     updateBooleans();
/*     */     bedsFound = new ArrayList<MyBed>();
/* 174 */     updateSizes(); } public void myScan() { if (!this.minimap_scan_is_active)
/* 175 */       return;  if (bedsFound == null) bedsFound = new ArrayList<MyBed>();
/*     */     
/* 177 */     if (!MyChatListener.IS_IN_GAME)
/*     */       return; 
/* 179 */     BWBed bed = MyChatListener.GAME_BED;
/* 180 */     if (bed == null)
/*     */       return; 
/* 182 */     int range = this.minimap_scan_range;
/*     */ 
/*     */ 
/*     */     
/* 186 */     if (this.minimap_scan_z_value >= range) {
/* 187 */       this.minimap_scan_is_active = false;
/* 188 */       this.minimap_scan_z_value = -range;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 193 */     int min_z = this.minimap_scan_z_value;
/* 194 */     this.minimap_scan_z_value = Math.min(min_z + this.minimap_scan_z_step, range);
/* 195 */     int max_z = this.minimap_scan_z_value;
/*     */     
/* 197 */     if (bed.part1_posY == 71 || bed.part1_posY == 69) {
/* 198 */       scanArea(-range, range, min_z, max_z, 71);
/* 199 */       scanArea(-range, range, min_z, max_z, 69);
/*     */     } else {
/* 201 */       scanArea(-range, range, min_z, max_z, bed.part1_posY);
/*     */     }  }
/*     */ 
/*     */   
/*     */   public void scanArea(int min_x, int max_x, int min_z, int max_z, int y_level) {
/* 206 */     int px = (int)Math.floor(mc.field_71439_g.field_70165_t);
/* 207 */     int pz = (int)Math.floor(mc.field_71439_g.field_70161_v);
/* 208 */     int py = y_level;
/*     */     
/* 210 */     long t = (new Date()).getTime();
/*     */     
/* 212 */     BlockPos pos = new BlockPos(px + min_x, py, pz + min_z);
/* 213 */     WorldClient worldClient = mc.field_71441_e;
/* 214 */     BWBed player_bed = MyChatListener.GAME_BED;
/* 215 */     for (int zi = min_z; zi < max_z; zi++) {
/* 216 */       for (int xi = min_x; xi <= max_x; xi++) {
/* 217 */         pos = pos.func_177972_a(EnumFacing.EAST);
/*     */         
/* 219 */         IBlockState state = worldClient.func_180495_p(pos);
/* 220 */         if (state != null) {
/* 221 */           Block b = state.func_177230_c();
/* 222 */           if (b == Blocks.field_150324_C && state.func_177229_b((IProperty)BlockBed.field_176472_a) == BlockBed.EnumPartType.HEAD) {
/*     */ 
/*     */             
/* 225 */             double x = pos.func_177958_n();
/* 226 */             double y = pos.func_177956_o();
/* 227 */             double z = pos.func_177952_p();
/* 228 */             double x2 = x;
/* 229 */             double y2 = y;
/* 230 */             double z2 = z;
/*     */             
/* 232 */             EnumFacing facing = (EnumFacing)worldClient.func_180495_p(new BlockPos(x, y, z)).func_177229_b((IProperty)BlockBed.field_176387_N);
/* 233 */             switch (facing) { case EAST:
/* 234 */                 x--; x2++; z2++; break;
/* 235 */               case NORTH: z2 += 2.0D; x2++; break;
/* 236 */               case SOUTH: z--; z2++; x2++; break;
/* 237 */               case WEST: z2++; x2 += 2.0D;
/*     */                 break; }
/*     */ 
/*     */ 
/*     */             
/* 242 */             x2--;
/* 243 */             z2--;
/* 244 */             MyBed bed = new MyBed(new BlockPos(x, y, z), new BlockPos(x2, y, z2), t, false);
/*     */ 
/*     */             
/* 247 */             if (player_bed != null && ((
/* 248 */               player_bed.part1_posX == pos.func_177958_n() && player_bed.part1_posZ == pos.func_177952_p()) || (player_bed.part2_posX == pos.func_177958_n() && player_bed.part2_posZ == pos.func_177952_p()))) bed.isPlayersBed = true;
/*     */ 
/*     */ 
/*     */             
/* 252 */             BlockPos[] arr = { new BlockPos(x - 1.0D, y, z), new BlockPos(x + 1.0D, y, z), new BlockPos(x, y, z - 1.0D), new BlockPos(x, y, z + 1.0D), new BlockPos(x2 - 1.0D, y2, z2), new BlockPos(x2 + 1.0D, y2, z2), new BlockPos(x2, y2, z2 - 1.0D), new BlockPos(x2, y2, z2 + 1.0D), new BlockPos(x, y + 1.0D, z), new BlockPos(x2, y2 + 1.0D, z2) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 265 */             for (BlockPos pp : arr) {
/* 266 */               GL11.glColor4f(0.0F, 1.0F, 1.0F, 1.0F);
/* 267 */               int block_x = pp.func_177958_n();
/* 268 */               int block_y = pp.func_177956_o();
/* 269 */               int block_z = pp.func_177952_p();
/* 270 */               if ((block_x != x || block_y != y || block_z != z) && (block_x != x2 || block_y != y2 || block_z != z2)) {
/*     */                 
/* 272 */                 IBlockState block_state = worldClient.func_180495_p(pp);
/* 273 */                 if (block_state != null) {
/* 274 */                   Block block = block_state.func_177230_c();
/* 275 */                   if (block != null && 
/* 276 */                     block == Blocks.field_150343_Z)
/* 277 */                     bed.obsidianPoses.add(pp); 
/*     */                 } 
/*     */               } 
/* 280 */             }  bedsFound.add(bed);
/*     */           } 
/*     */         } 
/* 283 */       }  pos = pos.func_177972_a(EnumFacing.SOUTH);
/* 284 */       pos = pos.func_177967_a(EnumFacing.EAST, -max_x - max_x - 1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void draw(Minecraft mc) {
/* 291 */     if (!isActive)
/* 292 */       return;  if (this.textureManager == null) this.textureManager = mc.func_110434_K();
/*     */ 
/*     */     
/* 295 */     int color_bg = getColor("00000011");
/* 296 */     int color_bg2 = getColor("000000aa");
/* 297 */     int color_dot = getColor("ff0000ff");
/* 298 */     int color_player = getColor("00ff00ff");
/* 299 */     int dot_size = 2;
/*     */ 
/*     */ 
/*     */     
/* 303 */     EntityPlayerSP player = mc.field_71439_g;
/* 304 */     Pos playerPos = new Pos(player.field_70165_t, player.field_70163_u, player.field_70161_v);
/* 305 */     float player_angle = player.field_70177_z;
/* 306 */     double player_angle_radians = Math.toRadians((180.0F - player_angle));
/* 307 */     double player_angle_cos = Math.cos(player_angle_radians);
/* 308 */     double player_angle_sin = Math.sin(player_angle_radians);
/*     */     
/* 310 */     int scaling = 60;
/*     */     
/* 312 */     float enemy_angle = 45.0F;
/*     */     
/* 314 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 315 */     WorldRenderer worldrenderer = tessellator.func_178180_c();
/* 316 */     GlStateManager.func_179094_E();
/* 317 */     GlStateManager.func_179152_a(0.2F, 0.2F, 0.2F);
/*     */     
/* 319 */     worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
/* 320 */     GlStateManager.func_179090_x();
/* 321 */     GlStateManager.func_179131_c(0.5F, 0.5F, 0.5F, 1.0F);
/*     */     
/* 323 */     int chunk_size = 11;
/* 324 */     for (int i = 0; i < chunk_size; i++) {
/* 325 */       for (int j = 0; j < chunk_size; j++) {
/* 326 */         drawChunk(worldrenderer, new BlockPos(player.field_70165_t + (16 * (i - (chunk_size + 1) / 2)), player.field_70163_u, player.field_70161_v + (16 * (j - (chunk_size + 1) / 2))), playerPos, scaling, player_angle_cos, player_angle_sin);
/*     */       }
/*     */     } 
/*     */     
/* 330 */     tessellator.func_78381_a();
/* 331 */     GlStateManager.func_179098_w();
/* 332 */     GlStateManager.func_179121_F();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 337 */     GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 338 */     List<EntityPlayer> entities = mc.field_71441_e.field_73010_i;
/*     */ 
/*     */ 
/*     */     
/* 342 */     for (EntityPlayer en : entities) {
/* 343 */       if (en == null || en.func_70005_c_() == null || en.func_145748_c_() == null || 
/* 344 */         en.func_70005_c_().equals(player.func_70005_c_()) || (
/* 345 */         isHidePlayersOnShift == true && en.func_70093_af()))
/* 346 */         continue;  Pos blockPos = new Pos(en.field_70165_t, en.field_70163_u, en.field_70161_v);
/* 347 */       double motionY = en.field_70167_r - en.field_70163_u;
/* 348 */       if (MyChatListener.GAME_BED != null && en.field_70163_u < (MyChatListener.GAME_BED.part1_posY - 5) && motionY > 1.0D) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */       
/* 353 */       int texture_offset_x = 2;
/* 354 */       int texture_offset_y = 2;
/*     */       
/* 356 */       CustomScoreboard.TEAM_COLOR team_color = MyChatListener.getEntityTeamColor(en);
/*     */       
/* 358 */       if (team_color == CustomScoreboard.TEAM_COLOR.RED) {
/* 359 */         texture_offset_x = 0;
/* 360 */         texture_offset_y = 0;
/* 361 */       } else if (team_color == CustomScoreboard.TEAM_COLOR.YELLOW) {
/* 362 */         texture_offset_x = 1;
/* 363 */         texture_offset_y = 0;
/* 364 */       } else if (team_color == CustomScoreboard.TEAM_COLOR.GREEN) {
/* 365 */         texture_offset_x = 2;
/* 366 */         texture_offset_y = 0;
/* 367 */       } else if (team_color == CustomScoreboard.TEAM_COLOR.AQUA) {
/* 368 */         texture_offset_x = 0;
/* 369 */         texture_offset_y = 1;
/* 370 */       } else if (team_color == CustomScoreboard.TEAM_COLOR.BLUE) {
/* 371 */         texture_offset_x = 1;
/* 372 */         texture_offset_y = 1;
/* 373 */       } else if (team_color == CustomScoreboard.TEAM_COLOR.PINK) {
/* 374 */         texture_offset_x = 2;
/* 375 */         texture_offset_y = 1;
/* 376 */       } else if (team_color == CustomScoreboard.TEAM_COLOR.WHITE) {
/* 377 */         texture_offset_x = 0;
/* 378 */         texture_offset_y = 2;
/* 379 */       } else if (team_color == CustomScoreboard.TEAM_COLOR.GRAY) {
/* 380 */         texture_offset_x = 1;
/* 381 */         texture_offset_y = 2;
/* 382 */       } else if (team_color == CustomScoreboard.TEAM_COLOR.NONE) {
/* 383 */         texture_offset_x = 2;
/* 384 */         texture_offset_y = 2;
/*     */       } 
/*     */ 
/*     */       
/* 388 */       mc.field_71446_o.func_110577_a(this.resourceLoc_enemy);
/* 389 */       drawPoint(blockPos, playerPos, scaling, player_angle, en.field_70177_z, dot_size, team_color, texture_offset_x, texture_offset_y, false);
/*     */     } 
/*     */     
/* 392 */     List<EntityDragon> dragons = mc.field_71441_e.func_175644_a(EntityDragon.class, EntitySelectors.field_94557_a);
/* 393 */     if (dragons != null && dragons.size() > 0) {
/* 394 */       for (EntityDragon dragon : dragons) {
/* 395 */         Pos blockPos = new Pos(dragon.field_70165_t, dragon.field_70163_u, dragon.field_70161_v);
/* 396 */         mc.field_71446_o.func_110577_a(this.resourceLoc_enemy);
/* 397 */         drawPoint(blockPos, playerPos, scaling, player_angle, 180.0F + dragon.field_70177_z, dot_size, CustomScoreboard.TEAM_COLOR.NONE, 2, 1, false);
/* 398 */         GlStateManager.func_179094_E();
/* 399 */         float text_size = 0.5F;
/* 400 */         GlStateManager.func_179152_a(text_size, text_size, text_size);
/* 401 */         drawTextOnMap(blockPos, playerPos, scaling, text_size, player_angle, "Dragon", getColor("ff00ffff"), 0.0D, -9.0D);
/*     */         
/* 403 */         String health_s = "" + (int)(dragon.func_110143_aJ() / 2.0F) + "%";
/* 404 */         drawTextOnMap(blockPos, playerPos, scaling, text_size, player_angle, health_s, getColor("ff0000ff"), 0.0D, 5.0D);
/* 405 */         GlStateManager.func_179121_F();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 410 */     GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 411 */     mc.field_71446_o.func_110577_a(this.resourceLoc_enemy);
/* 412 */     drawPoint(playerPos, playerPos, scaling, player_angle, player_angle, dot_size, CustomScoreboard.TEAM_COLOR.NONE, 0, 0, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 419 */     GlStateManager.func_179094_E();
/* 420 */     GlStateManager.func_179152_a(0.5F, 0.5F, 0.5F);
/*     */ 
/*     */ 
/*     */     
/* 424 */     List<Entity> items = mc.field_71441_e.field_72996_f;
/* 425 */     int cnt_emerald = 0;
/* 426 */     int cnt_diamond = 0;
/*     */     
/* 428 */     EntityItem item_max_emerads = null;
/* 429 */     EntityItem item_max_diamonds = null;
/* 430 */     ArrayList<PosItem> itemsPos = new ArrayList<PosItem>();
/* 431 */     for (Entity en : items) {
/* 432 */       if (en instanceof EntityItem) {
/* 433 */         EntityItem itemEntity = (EntityItem)en;
/* 434 */         Item item = itemEntity.func_92059_d().func_77973_b();
/* 435 */         if (item == null)
/*     */           continue; 
/* 437 */         if (MyChatListener.GAME_BED != null && en.field_70163_u < (MyChatListener.GAME_BED.part1_posY - 15)) {
/*     */           continue;
/*     */         }
/*     */         
/* 441 */         int item_type = -1;
/* 442 */         if (item == Items.field_151166_bC) {
/* 443 */           item_type = 0;
/* 444 */         } else if (item == Items.field_151045_i) {
/* 445 */           item_type = 1;
/* 446 */         } else if (item == Items.field_151043_k) {
/* 447 */           item_type = 2;
/* 448 */         } else if (item == Items.field_151042_j) {
/* 449 */           item_type = 3;
/*     */         } 
/* 451 */         if (item_type != -1) {
/* 452 */           int cnt = (itemEntity.func_92059_d()).field_77994_a;
/*     */ 
/*     */           
/* 455 */           boolean isFound = false;
/* 456 */           for (PosItem p : itemsPos) {
/* 457 */             if (p.type != item_type)
/* 458 */               continue;  double dist = Math.sqrt(Math.pow(p.x - en.field_70165_t, 2.0D) + Math.pow(p.z - en.field_70161_v, 2.0D));
/* 459 */             if (dist < 3.0D) {
/* 460 */               p.cnt += cnt;
/* 461 */               isFound = true;
/*     */               break;
/*     */             } 
/*     */           } 
/* 465 */           if (!isFound) itemsPos.add(new PosItem(en.field_70165_t, en.field_70163_u, en.field_70161_v, item_type, cnt));
/*     */         
/*     */         } 
/*     */       } 
/*     */     } 
/* 470 */     for (PosItem p : itemsPos) {
/* 471 */       if ((p.type == 1 && p.cnt < 4) || (
/* 472 */         p.type == 2 && p.cnt < 12) || (
/* 473 */         p.type == 3 && p.cnt < 64))
/*     */         continue; 
/* 475 */       drawItemResouce(new Pos(p.x, p.y, p.z), playerPos, scaling, player_angle, p.type, p.cnt);
/*     */     } 
/*     */     
/* 478 */     GlStateManager.func_179121_F();
/*     */ 
/*     */ 
/*     */     
/* 482 */     long t = (new Date()).getTime();
/* 483 */     if (t - time_last_bed_scanned > TIME_BED_SCAN) {
/* 484 */       this.minimap_scan_is_active = true;
/* 485 */       time_last_bed_scanned = t;
/*     */     } 
/*     */     
/* 488 */     myScan();
/*     */ 
/*     */     
/* 491 */     if (bedsFound.size() > 0) {
/*     */       
/* 493 */       Iterator<MyBed> iterator = bedsFound.iterator();
/* 494 */       while (iterator.hasNext()) {
/* 495 */         MyBed b = iterator.next();
/* 496 */         if (t - b.t > 1200L) iterator.remove();
/*     */         
/* 498 */         mc.field_71446_o.func_110577_a(this.resourceLoc_enemy);
/* 499 */         drawBed(b.pos, playerPos, scaling, player_angle, dot_size, b.isPlayersBed);
/*     */         
/* 501 */         String text = "OBBY";
/* 502 */         String additional = "" + (int)(b.obsidianPoses.size() / 8.0F * 100.0F) + "%";
/*     */         
/* 504 */         if (b.obsidianPoses.size() == 0) {
/*     */           continue;
/*     */         }
/* 507 */         GlStateManager.func_179094_E();
/* 508 */         float text_size = 0.5F;
/* 509 */         GlStateManager.func_179152_a(text_size, text_size, text_size);
/*     */         
/* 511 */         drawTextOnMap(new Pos(b.pos.func_177958_n(), b.pos.func_177956_o(), b.pos.func_177952_p()), playerPos, scaling, text_size, player_angle, text, getColor("bb2affff"), 0.0D, 3.0D);
/*     */         
/* 513 */         if (b.obsidianPoses.size() < 8) {
/* 514 */           drawTextOnMap(new Pos(b.pos.func_177958_n(), b.pos.func_177956_o(), b.pos.func_177952_p()), playerPos, scaling, text_size, player_angle, additional, getColor("d787fcff"), 13.0D, 3.0D);
/*     */         }
/* 516 */         GlStateManager.func_179121_F();
/*     */       } 
/*     */     } 
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
/*     */   void drawItemResouce(Pos pos, Pos playerPos, int scaling, float player_angle, int item_idx, int item_count) {
/* 548 */     if (item_count <= 0)
/* 549 */       return;  int color = getColor("ffffffff");
/* 550 */     double offsetX = 0.0D;
/* 551 */     double offsetZ = 0.0D;
/* 552 */     if (item_idx == 0) {
/*     */       
/* 554 */       color = getColor("00ff00ff");
/* 555 */     } else if (item_idx == 1) {
/*     */       
/* 557 */       color = getColor("00ffffff");
/* 558 */     } else if (item_idx == 2) {
/*     */       
/* 560 */       color = getColor("ffea00ff");
/* 561 */       offsetZ = -4.0D;
/* 562 */     } else if (item_idx == 3) {
/*     */       
/* 564 */       color = getColor("e0e0e0ff");
/* 565 */       offsetZ = 2.0D;
/*     */     } 
/*     */     
/* 568 */     String text = "" + item_count;
/* 569 */     if (item_count > 64) {
/* 570 */       text = "" + this.timeFormatter.format((item_count / 64.0F)) + "s";
/*     */     }
/*     */     
/* 573 */     drawTextOnMap(pos, playerPos, scaling, 0.5F, player_angle, text, color, offsetX, offsetZ);
/*     */   }
/*     */ 
/*     */   
/*     */   void drawTextOnMap(Pos pos, Pos playerPos, int scaling, float matrix_scale, float player_angle, String text, int color, double offsetX, double offsetZ) {
/* 578 */     double deltaX = pos.x - playerPos.x;
/* 579 */     double deltaY = pos.y - playerPos.y;
/* 580 */     double deltaZ = pos.z - playerPos.z;
/*     */     
/* 582 */     float multiplier = 1.0F / matrix_scale;
/*     */     
/* 584 */     int cx = (int)(((topX + botX) / 2) * multiplier);
/* 585 */     int cy = (int)(((topY + botY) / 2) * multiplier);
/*     */ 
/*     */     
/* 588 */     float scaling_coef = map_size / (scaling * 2) * multiplier;
/* 589 */     float screenDeltaX = (float)(deltaX * scaling_coef);
/* 590 */     float screenDeltaZ = (float)(deltaZ * scaling_coef);
/*     */     
/* 592 */     double x1 = screenDeltaX;
/* 593 */     double z1 = screenDeltaZ;
/* 594 */     double angle = Math.toRadians((180.0F - player_angle));
/* 595 */     screenDeltaX = (float)(x1 * Math.cos(angle) - z1 * Math.sin(angle)) + cx;
/* 596 */     screenDeltaZ = (float)(x1 * Math.sin(angle) + z1 * Math.cos(angle)) + cy;
/*     */     
/* 598 */     screenDeltaX = (float)(screenDeltaX + offsetX * scaling_coef);
/* 599 */     screenDeltaZ = (float)(screenDeltaZ + offsetZ * scaling_coef);
/*     */     
/* 601 */     if (Math.abs(screenDeltaX - cx) > (map_size / 2) * multiplier)
/* 602 */       return;  if (Math.abs(screenDeltaZ - cy) > (map_size / 2) * multiplier)
/*     */       return; 
/* 604 */     func_73732_a(mc.field_71466_p, text, (int)screenDeltaX, (int)screenDeltaZ, color);
/*     */   }
/*     */   
/*     */   public void drawChunk(WorldRenderer worldrenderer, BlockPos chunkPos, Pos playerPos, int scaling, double player_angle_cos, double player_angle_sin) {
/* 608 */     Chunk chunk = mc.field_71441_e.func_175726_f(chunkPos);
/*     */ 
/*     */     
/* 611 */     int[] heights = chunk.func_177445_q();
/*     */ 
/*     */ 
/*     */     
/* 615 */     for (int x = 0; x < 16; x++) {
/* 616 */       for (int z = 0; z < 16; z++) {
/* 617 */         int idx = x * 16 + z;
/* 618 */         int h = heights[idx];
/* 619 */         Pos pos = new Pos((z + chunk.field_76635_g * 16), h, (x + chunk.field_76647_h * 16));
/* 620 */         if (h > 0) {
/* 621 */           drawBlock(worldrenderer, pos, playerPos, scaling, player_angle_cos, player_angle_sin);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawBlock(WorldRenderer worldrenderer, Pos pos, Pos playerPos, int scaling, double cos, double sin) {
/* 629 */     double deltaX = pos.x + 0.5D - playerPos.x;
/* 630 */     double deltaY = pos.y + 0.5D - playerPos.y;
/* 631 */     double deltaZ = pos.z + 0.5D - playerPos.z;
/*     */ 
/*     */ 
/*     */     
/* 635 */     int multiplier = 5;
/*     */     
/* 637 */     int cx = (topX + botX) / 2 * multiplier;
/* 638 */     int cy = (topY + botY) / 2 * multiplier;
/*     */ 
/*     */ 
/*     */     
/* 642 */     float scaling_coef = map_size / (scaling * 2) * multiplier;
/* 643 */     float screenDeltaX = (float)(deltaX * scaling_coef);
/* 644 */     float screenDeltaZ = (float)(deltaZ * scaling_coef);
/*     */ 
/*     */ 
/*     */     
/* 648 */     float dot_size = scaling_coef + 4.0F;
/* 649 */     double x1 = screenDeltaX;
/* 650 */     double z1 = screenDeltaZ;
/*     */ 
/*     */ 
/*     */     
/* 654 */     screenDeltaX = (float)(x1 * cos - z1 * sin) + cx;
/* 655 */     screenDeltaZ = (float)(x1 * sin + z1 * cos) + cy;
/*     */ 
/*     */ 
/*     */     
/* 659 */     if (Math.abs(screenDeltaX - cx) > (map_size / 2 * multiplier))
/* 660 */       return;  if (Math.abs(screenDeltaZ - cy) > (map_size / 2 * multiplier)) {
/*     */       return;
/*     */     }
/*     */     
/* 664 */     double topX1 = (screenDeltaX - dot_size / 2.0F);
/* 665 */     double topY1 = (screenDeltaZ - dot_size / 2.0F);
/* 666 */     double topX2 = (screenDeltaX - dot_size / 2.0F);
/* 667 */     double topY2 = (screenDeltaZ + dot_size / 2.0F);
/* 668 */     double topX3 = (screenDeltaX + dot_size / 2.0F);
/* 669 */     double topY3 = (screenDeltaZ + dot_size / 2.0F);
/* 670 */     double topX4 = (screenDeltaX + dot_size / 2.0F);
/* 671 */     double topY4 = (screenDeltaZ - dot_size / 2.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 699 */     worldrenderer.func_181662_b(topX1, topY1, 0.0D).func_181675_d();
/* 700 */     worldrenderer.func_181662_b(topX2, topY2, 0.0D).func_181675_d();
/* 701 */     worldrenderer.func_181662_b(topX3, topY3, 0.0D).func_181675_d();
/* 702 */     worldrenderer.func_181662_b(topX4, topY4, 0.0D).func_181675_d();
/*     */   }
/*     */ 
/*     */   
/*     */   private double max(double a, double b) {
/* 707 */     if (a > b) return a; 
/* 708 */     return b;
/*     */   }
/*     */   
/*     */   private double min(double a, double b) {
/* 712 */     if (a < b) return a; 
/* 713 */     return b;
/*     */   }
/*     */   
/*     */   private void drawPoint(Pos pos, Pos playerPos, int scaling, float player_angle, float enemy_angle, int dot_size, CustomScoreboard.TEAM_COLOR team_color, int texture_offset_x, int texture_offset_y, boolean isMainPlayer) {
/* 717 */     dot_size = 6;
/*     */     
/* 719 */     if (isMainPlayer) {
/* 720 */       int i = topX + map_size / 2;
/* 721 */       int j = topY + map_size / 2;
/*     */       
/* 723 */       GlStateManager.func_179094_E();
/*     */       
/* 725 */       GlStateManager.func_179109_b(i, j, 0.0F);
/* 726 */       GlStateManager.func_179152_a(0.3F, 0.3F, 0.3F);
/* 727 */       GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 728 */       func_73729_b(-7, -7, 243, 243, 13, 13);
/* 729 */       GlStateManager.func_179121_F();
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/* 736 */     double deltaX = pos.x - playerPos.x;
/* 737 */     double deltaY = pos.y - playerPos.y;
/* 738 */     double deltaZ = pos.z - playerPos.z;
/*     */ 
/*     */     
/* 741 */     int cx = (topX + botX) / 2;
/* 742 */     int cy = (topY + botY) / 2;
/*     */     
/* 744 */     float scaling_coef = map_size / (scaling * 2);
/* 745 */     float screenDeltaX = (float)(deltaX * scaling_coef);
/* 746 */     float screenDeltaZ = (float)(deltaZ * scaling_coef);
/*     */ 
/*     */ 
/*     */     
/* 750 */     double x1 = screenDeltaX;
/* 751 */     double z1 = screenDeltaZ;
/* 752 */     double angle = Math.toRadians((180.0F - player_angle));
/*     */     
/* 754 */     double x2 = x1 * Math.cos(angle) - z1 * Math.sin(angle);
/* 755 */     double y2 = x1 * Math.sin(angle) + z1 * Math.cos(angle);
/*     */     
/* 757 */     screenDeltaX = (float)x2;
/* 758 */     screenDeltaZ = (float)y2;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 763 */     if (Math.abs(screenDeltaX) > (map_size / 2) || Math.abs(screenDeltaZ) > (map_size / 2)) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 768 */     enemy_angle -= player_angle;
/*     */ 
/*     */     
/*     */     try {
/* 772 */       GlStateManager.func_179094_E();
/* 773 */       GlStateManager.func_179109_b(cx + screenDeltaX, cy + screenDeltaZ, 0.0F);
/* 774 */       GlStateManager.func_179114_b(enemy_angle, 0.0F, 0.0F, 1.0F);
/* 775 */       GlStateManager.func_179152_a(0.07F, 0.07F, 0.07F);
/* 776 */       GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 777 */       func_73729_b(-40, -40, 80 * texture_offset_x, 80 * texture_offset_y, 80, 80);
/* 778 */       GlStateManager.func_179121_F();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 783 */       if (show_heights && team_color != CustomScoreboard.TEAM_COLOR.NONE) {
/* 784 */         GlStateManager.func_179094_E();
/* 785 */         GlStateManager.func_179152_a(0.5F, 0.5F, 0.5F);
/*     */         
/* 787 */         int height_difference = (int)(pos.y - playerPos.y);
/*     */         
/* 789 */         double speedY = mc.field_71439_g.field_70163_u - mc.field_71439_g.field_70167_r;
/* 790 */         if (height_difference >= 5 && speedY > -1.0D) {
/* 791 */           Color color = ParticleController.getParticleColorForTeam(team_color);
/* 792 */           if (team_color == CustomScoreboard.TEAM_COLOR.GRAY) color = new Color(1.0F, 1.0F, 1.0F, 1.0F); 
/* 793 */           drawTextOnMap(pos, playerPos, scaling, 0.5F, player_angle, "" + height_difference, color.getRGB(), 0.0D, 3.0D);
/*     */         } 
/*     */         
/* 796 */         GlStateManager.func_179121_F();
/*     */       }
/*     */     
/* 799 */     } catch (Exception ex) {
/* 800 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void drawBed(BlockPos pos, Pos playerPos, int scaling, float player_angle, int dot_size, boolean isPlayersBed) {
/* 805 */     dot_size = 6;
/*     */     
/* 807 */     double deltaX = pos.func_177958_n() - playerPos.x + 0.5D;
/* 808 */     double deltaY = pos.func_177956_o() - playerPos.y;
/* 809 */     double deltaZ = pos.func_177952_p() - playerPos.z + 0.5D;
/*     */ 
/*     */     
/* 812 */     int cx = (topX + botX) / 2;
/* 813 */     int cy = (topY + botY) / 2;
/*     */     
/* 815 */     float scaling_coef = map_size / (scaling * 2);
/* 816 */     float screenDeltaX = (float)(deltaX * scaling_coef);
/* 817 */     float screenDeltaZ = (float)(deltaZ * scaling_coef);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 823 */     double x1 = screenDeltaX;
/* 824 */     double z1 = screenDeltaZ;
/* 825 */     double angle = Math.toRadians((180.0F - player_angle));
/*     */     
/* 827 */     double x2 = x1 * Math.cos(angle) - z1 * Math.sin(angle);
/* 828 */     double y2 = x1 * Math.sin(angle) + z1 * Math.cos(angle);
/*     */     
/* 830 */     screenDeltaX = (float)x2;
/* 831 */     screenDeltaZ = (float)y2;
/*     */     
/* 833 */     if (Math.abs(screenDeltaX) > (map_size / 2) || Math.abs(screenDeltaZ) > (map_size / 2)) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 855 */     GlStateManager.func_179094_E();
/*     */     
/* 857 */     GlStateManager.func_179109_b(cx + screenDeltaX, cy + screenDeltaZ, 0.0F);
/* 858 */     GlStateManager.func_179114_b(0.0F, 0.0F, 0.0F, 1.0F);
/* 859 */     GlStateManager.func_179152_a(0.25F, 0.25F, 0.25F);
/* 860 */     GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 861 */     func_73729_b(-7, -7, 230 + (isPlayersBed ? -13 : 0), 243, 13, 13);
/* 862 */     GlStateManager.func_179121_F();
/*     */   }
/*     */ 
/*     */   
/*     */   private int getColor(String hexColor) {
/* 867 */     Color colorNoAlpha = Color.decode("0x" + hexColor.substring(0, 6));
/* 868 */     int alpha = Integer.parseInt(hexColor.substring(6, 8), 16);
/* 869 */     return (new Color(colorNoAlpha.getRed(), colorNoAlpha.getGreen(), colorNoAlpha.getBlue(), alpha)).getRGB();
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\gui\GuiMinimap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */