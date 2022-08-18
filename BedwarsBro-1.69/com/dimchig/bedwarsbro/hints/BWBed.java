/*     */ package com.dimchig.bedwarsbro.hints;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.Main;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BWBed
/*     */ {
/*     */   public int part1_posX;
/*     */   public int part1_posY;
/*     */   public int part1_posZ;
/*     */   public int part2_posX;
/*     */   public int part2_posY;
/*     */   public int part2_posZ;
/*     */   public ArrayList<DefenceLayer> defence;
/*     */   public BWItemsHandler.BWItemColor color;
/*     */   
/*     */   public class DefenceLayer
/*     */   {
/*     */     public int index;
/*     */     public BWItemsHandler.BWItemType type;
/*     */     public float percentage;
/*     */     public ArrayList<BlockPos> arr;
/*     */     public int empty_space_cnt;
/*     */     
/*     */     public DefenceLayer(ArrayList<BlockPos> arr, int index, BWItemsHandler.BWItemType type, float percentage, int empty_space_cnt) {
/*  45 */       this.index = index;
/*  46 */       this.type = type;
/*  47 */       this.percentage = percentage;
/*  48 */       this.arr = arr;
/*  49 */       this.empty_space_cnt = empty_space_cnt;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BWBed(int part1_posX, int part1_posY, int part1_posZ, int part2_posX, int part2_posY, int part2_posZ) {
/*  56 */     this.part1_posX = part1_posX;
/*  57 */     this.part1_posY = part1_posY;
/*  58 */     this.part1_posZ = part1_posZ;
/*  59 */     this.part2_posX = part2_posX;
/*  60 */     this.part2_posY = part2_posY;
/*  61 */     this.part2_posZ = part2_posZ;
/*     */   }
/*     */   
/*     */   public void showLayers(final World world) throws Exception {
/*  65 */     if (this.defence.size() == 0)
/*     */       return; 
/*     */     class Pop { public BlockPos pos;
/*     */       public IBlockState state;
/*     */       
/*     */       public Pop(BlockPos pos, IBlockState state) {
/*  71 */         this.pos = pos;
/*  72 */         this.state = state;
/*     */       } }
/*     */     ;
/*  75 */     final ArrayList<Pop> pops = new ArrayList<Pop>();
/*     */ 
/*     */     
/*  78 */     for (DefenceLayer layer : this.defence) {
/*  79 */       for (BlockPos pos : layer.arr) {
/*  80 */         pops.add(new Pop(pos, world.func_180495_p(pos)));
/*  81 */         setBlock(world, pos, Blocks.field_150350_a);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  87 */     int delay = Main.getConfigInt(Main.CONFIG_MSG.BED_SCANNER_ANIMATION_DELAY);
/*     */     
/*  89 */     for (DefenceLayer layer : this.defence) {
/*  90 */       (new Timer()).schedule(new TimerTask()
/*     */           {
/*     */             public void run()
/*     */             {
/*  94 */               for (BlockPos pos : layer.arr) {
/*  95 */                 for (BWBed.Pop p : pops) {
/*  96 */                   if (p.pos.func_177958_n() == pos.func_177958_n() && p.pos.func_177956_o() == pos.func_177956_o() && p.pos.func_177952_p() == pos.func_177952_p()) {
/*  97 */                     world.func_175654_a(pos, world.func_180495_p(pos).func_177230_c(), 0, 1);
/*  98 */                     if (world.func_180495_p(pos).func_177230_c().func_149732_F().contains("air")) {
/*  99 */                       BWBed.this.setBlock(world, pos, p.state);
/*     */                     }
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             }
/*     */           }(delay * (layer.index + 1)));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlock(World world, BlockPos pos, Block block) {
/* 112 */     setBlock(world, pos, block.func_176223_P());
/*     */   }
/*     */   
/*     */   public void setBlock(World world, BlockPos pos, IBlockState state) {
/* 116 */     world.func_175656_a(pos, state);
/*     */   }
/*     */   
/*     */   public void scanDefence(World world) {
/* 120 */     this.defence = new ArrayList<DefenceLayer>();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 125 */     ArrayList<BlockPos> arr_conected = new ArrayList<BlockPos>();
/* 126 */     arr_conected.add(new BlockPos(this.part1_posX, this.part1_posY, this.part1_posZ));
/* 127 */     arr_conected.add(new BlockPos(this.part2_posX, this.part2_posY, this.part2_posZ));
/*     */     
/* 129 */     ArrayList<BlockPos> arr_ignore = new ArrayList<BlockPos>();
/* 130 */     arr_ignore.addAll(arr_conected);
/*     */     
/* 132 */     EnumDyeColor[] dyeColors = { EnumDyeColor.RED, EnumDyeColor.YELLOW, EnumDyeColor.LIME, EnumDyeColor.LIGHT_BLUE };
/*     */     
/* 134 */     int min_y = this.part1_posY;
/* 135 */     for (int layer = 0; layer <= 5; layer++) {
/* 136 */       ArrayList<BlockPos> arr = scanNearestBlocks(arr_conected, arr_ignore, min_y);
/* 137 */       arr_ignore.addAll(arr);
/* 138 */       arr_conected = arr;
/*     */       
/* 140 */       HashMap<BWItemsHandler.BWItemType, Integer> map = new HashMap<BWItemsHandler.BWItemType, Integer>();
/* 141 */       int cnt_not_air = 0;
/* 142 */       for (BlockPos p : arr) {
/*     */ 
/*     */ 
/*     */         
/* 146 */         Block block = (Minecraft.func_71410_x()).field_71441_e.func_180495_p(p).func_177230_c();
/* 147 */         String name = block.func_149739_a().substring(5);
/* 148 */         if (!block.func_149739_a().contains("air")) {
/* 149 */           cnt_not_air++;
/* 150 */           BWItem item = BWItemsHandler.findItem(name, "");
/* 151 */           if (item == null || item.type == null)
/* 152 */             continue;  if (map != null && map.containsKey(item.type)) {
/* 153 */             map.put(item.type, Integer.valueOf(((Integer)map.get(item.type)).intValue() + 1)); continue;
/* 154 */           }  map.put(item.type, Integer.valueOf(1));
/*     */         } 
/*     */       } 
/*     */       
/* 158 */       BWItemsHandler.BWItemType max_item = null;
/* 159 */       int max_val = -2;
/* 160 */       for (Map.Entry<BWItemsHandler.BWItemType, Integer> entry : map.entrySet()) {
/*     */         
/* 162 */         int v = -1;
/*     */         
/* 164 */         if (entry.getKey() == BWItemsHandler.BWItemType.BLOCK_WOOL) { v = 0; }
/* 165 */         else if (entry.getKey() == BWItemsHandler.BWItemType.BLOCK_WOOD) { v = 1; }
/* 166 */         else if (entry.getKey() == BWItemsHandler.BWItemType.BLOCK_CLAY) { v = 2; }
/* 167 */         else if (entry.getKey() == BWItemsHandler.BWItemType.BLOCK_LADDER) { v = -1; }
/* 168 */         else if (entry.getKey() == BWItemsHandler.BWItemType.BLOCK_ENDSTONE) { v = 3; }
/* 169 */         else if (entry.getKey() == BWItemsHandler.BWItemType.BLOCK_GLASS) { v = 0; }
/* 170 */         else if (entry.getKey() == BWItemsHandler.BWItemType.BLOCK_OBSIDIAN) { v = 4; }
/* 171 */          if (v > max_val) {
/* 172 */           max_val = v;
/* 173 */           max_item = entry.getKey();
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 179 */       float air_treshold = 0.3F;
/* 180 */       if (map.size() == 0 || max_item == null) {
/*     */         break;
/*     */       }
/*     */ 
/*     */       
/* 185 */       BWItemsHandler.BWItemType item_type = max_item;
/* 186 */       this.defence.add(new DefenceLayer(arr, layer, item_type, ((Integer)map.get(max_item)).intValue() / arr.size(), arr.size() - cnt_not_air));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<BlockPos> scanNearestBlocks(ArrayList<BlockPos> connected_positions, ArrayList<BlockPos> ignore_positions, int min_y) {
/* 197 */     ArrayList<BlockPos> arr = new ArrayList<BlockPos>();
/* 198 */     for (BlockPos p : connected_positions) {
/* 199 */       int range = 1;
/* 200 */       for (int yi = -range; yi <= range; yi++) {
/* 201 */         for (int xi = -range; xi <= range; xi++) {
/* 202 */           for (int zi = -range; zi <= range; zi++) {
/* 203 */             int bx = p.func_177958_n() + xi;
/* 204 */             int by = p.func_177956_o() + yi;
/* 205 */             int bz = p.func_177952_p() + zi;
/*     */             
/* 207 */             if (by >= min_y) {
/*     */               
/* 209 */               boolean isIgnored = false;
/* 210 */               for (BlockPos p_ignore : ignore_positions) {
/* 211 */                 if (bx == p_ignore.func_177958_n() && by == p_ignore.func_177956_o() && bz == p_ignore.func_177952_p()) {
/* 212 */                   isIgnored = true;
/*     */                   break;
/*     */                 } 
/*     */               } 
/* 216 */               for (BlockPos p_ignore : arr) {
/* 217 */                 if (bx == p_ignore.func_177958_n() && by == p_ignore.func_177956_o() && bz == p_ignore.func_177952_p()) {
/* 218 */                   isIgnored = true;
/*     */                   
/*     */                   break;
/*     */                 } 
/*     */               } 
/* 223 */               if (isIgnored != true)
/*     */               {
/* 225 */                 if (isBlockConnectsToBlock(bx, by, bz, p.func_177958_n(), p.func_177956_o(), p.func_177952_p()))
/* 226 */                   arr.add(new BlockPos(bx, by, bz));  } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 232 */     return arr;
/*     */   }
/*     */   
/*     */   public static boolean isBlockConnectsToBlock(int x1, int y1, int z1, int x2, int y2, int z2) {
/* 236 */     int distX = Math.abs(x1 - x2);
/* 237 */     int distY = Math.abs(y1 - y2);
/* 238 */     int distZ = Math.abs(z1 - z2);
/* 239 */     if (distX == 1 && distY == 0 && distZ == 0)
/* 240 */       return true; 
/* 241 */     if (distX == 0 && distY == 1 && distZ == 0)
/* 242 */       return true; 
/* 243 */     if (distX == 0 && distY == 0 && distZ == 1) {
/* 244 */       return true;
/*     */     }
/* 246 */     return false;
/*     */   }
/*     */   
/*     */   public String getAnalysis() {
/* 250 */     int defence_layer_count = 0;
/*     */     
/* 252 */     ArrayList<String> defence_blocks = new ArrayList<String>();
/* 253 */     String defence_blocks_string = "";
/*     */     
/* 255 */     HashSet<String> defence_requirements = new HashSet<String>();
/* 256 */     String defence_requirements_string = "";
/* 257 */     String extras = "";
/*     */     
/* 259 */     for (DefenceLayer layer : this.defence) {
/* 260 */       if (layer.type != null && (layer.percentage == 1.0F || (layer.index + 1 < this.defence.size() && layer.percentage > 0.0F && layer.type != BWItemsHandler.BWItemType.BLOCK_OBSIDIAN))) {
/* 261 */         defence_layer_count++;
/*     */         
/* 263 */         String item_name = "&c-";
/* 264 */         if (layer.type == BWItemsHandler.BWItemType.BLOCK_WOOL) { item_name = "&7Шерсть"; }
/* 265 */         else if (layer.type == BWItemsHandler.BWItemType.BLOCK_WOOD) { item_name = "&6Дерево"; }
/* 266 */         else if (layer.type == BWItemsHandler.BWItemType.BLOCK_CLAY) { item_name = "&fБетон"; }
/* 267 */         else if (layer.type == BWItemsHandler.BWItemType.BLOCK_LADDER) { item_name = "&7Лестница"; }
/* 268 */         else if (layer.type == BWItemsHandler.BWItemType.BLOCK_ENDSTONE) { item_name = "&eЭндерняк"; }
/* 269 */         else if (layer.type == BWItemsHandler.BWItemType.BLOCK_GLASS) { item_name = "&7Стекло"; }
/* 270 */         else if (layer.type == BWItemsHandler.BWItemType.BLOCK_OBSIDIAN) { item_name = "&b&lОБСА"; }
/* 271 */          defence_blocks.add(item_name);
/*     */ 
/*     */         
/* 274 */         if (layer.type == BWItemsHandler.BWItemType.BLOCK_ENDSTONE || layer.type == BWItemsHandler.BWItemType.BLOCK_CLAY) {
/* 275 */           if (!defence_requirements.contains("&b&lАлмазная Кирка")) defence_requirements.add("&6Кирка");  continue;
/* 276 */         }  if (layer.type == BWItemsHandler.BWItemType.BLOCK_OBSIDIAN) {
/* 277 */           defence_requirements.add("&b&lАлмазная Кирка");
/* 278 */           defence_requirements.remove("&6Кирка"); continue;
/* 279 */         }  if (layer.type == BWItemsHandler.BWItemType.BLOCK_WOOD) {
/* 280 */           defence_requirements.add("&6Топор"); continue;
/* 281 */         }  if (layer.type == BWItemsHandler.BWItemType.BLOCK_WOOL);
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 286 */       if (layer.index == 0 && layer.type == BWItemsHandler.BWItemType.BLOCK_OBSIDIAN && layer.percentage < 1.0F) {
/* 287 */         defence_layer_count = 1;
/* 288 */         extras = "Не полная защита ОБСОЙ! &8" + (int)(layer.percentage * 100.0F) + "%";
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 294 */     defence_blocks_string = "&7";
/* 295 */     String prev_block = "none";
/* 296 */     int inline_count = 0;
/* 297 */     Collections.reverse(defence_blocks);
/* 298 */     defence_blocks.add("none");
/* 299 */     if (defence_blocks.size() > 0) {
/* 300 */       for (String s : defence_blocks) {
/* 301 */         if (prev_block.equals("none")) {
/* 302 */           prev_block = s;
/* 303 */           inline_count = 1; continue;
/* 304 */         }  if (prev_block.equals(s)) {
/* 305 */           inline_count++; continue;
/*     */         } 
/* 307 */         defence_blocks_string = defence_blocks_string + prev_block;
/* 308 */         if (inline_count > 1) defence_blocks_string = defence_blocks_string + " &7x&c" + inline_count; 
/* 309 */         defence_blocks_string = defence_blocks_string + " &8▸ ";
/* 310 */         inline_count = 1;
/* 311 */         prev_block = s;
/*     */       } 
/*     */     }
/*     */     
/* 315 */     if (defence_blocks_string.length() > 5) {
/* 316 */       defence_blocks_string = defence_blocks_string.substring(0, defence_blocks_string.length() - 5).trim();
/*     */     }
/*     */     
/* 319 */     defence_requirements_string = "";
/* 320 */     for (String s : defence_requirements) defence_requirements_string = defence_requirements_string + s + "&7, "; 
/* 321 */     if (defence_requirements_string.length() > 2) {
/* 322 */       defence_requirements_string = defence_requirements_string.substring(0, defence_requirements_string.length() - 2).trim();
/*     */     }
/*     */     
/* 325 */     String str = "";
/* 326 */     if (defence_layer_count == 0)
/* 327 */     { str = "&fНет защиты"; }
/*     */     
/* 329 */     else if (defence_blocks.size() > 1) { str = str + "" + defence_blocks_string + ""; }
/*     */     
/* 331 */     if (extras.length() > 0) str = str + " &b" + extras; 
/* 332 */     return str;
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\hints\BWBed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */