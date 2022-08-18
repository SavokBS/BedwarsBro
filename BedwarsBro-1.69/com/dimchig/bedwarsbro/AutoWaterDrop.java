/*     */ package com.dimchig.bedwarsbro;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.hints.HintsFinder;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.multiplayer.WorldClient;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class AutoWaterDrop
/*     */ {
/*     */   static Minecraft mc;
/*     */   boolean isPressed = false;
/*  25 */   private int delay_cnt = -1; boolean isWaterDropStarted = false; KeyBinding keyUse;
/*     */   
/*     */   public AutoWaterDrop() {
/*  28 */     mc = Minecraft.func_71410_x();
/*  29 */     this.isPressed = false;
/*  30 */     this.keyUse = mc.field_71474_y.field_74313_G;
/*     */   }
/*     */   
/*     */   public void check(EntityPlayerSP player, Vec3 pos) {
/*  34 */     if (player.field_70181_x < -1.0D) {
/*     */ 
/*     */       
/*  37 */       WorldClient worldClient = mc.field_71441_e;
/*  38 */       double dist = -1.0D;
/*  39 */       for (int y = (int)pos.field_72448_b - 1; y > 0; y--) {
/*  40 */         if (worldClient.func_180495_p(new BlockPos((int)pos.field_72450_a, y, (int)pos.field_72449_c)).func_177230_c() != Blocks.field_150350_a) {
/*  41 */           dist = pos.field_72448_b - y - 1.0D;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*  46 */       if (dist == -1.0D)
/*     */         return; 
/*  48 */       boolean hasWater = false;
/*  49 */       if (player.field_71071_by != null && player.field_71071_by.field_70462_a != null) {
/*  50 */         if (player.field_71071_by.func_70448_g() == null || player.field_71071_by.func_70448_g().func_77973_b() != Items.field_151131_as) {
/*  51 */           for (int i = 0; i < InventoryPlayer.func_70451_h(); i++) {
/*  52 */             ItemStack stack = player.field_71071_by.field_70462_a[i];
/*  53 */             if (stack != null) {
/*  54 */               Item item = stack.func_77973_b();
/*  55 */               if (item != null && item == Items.field_151131_as) {
/*  56 */                 if (dist < 20.0D) player.field_71071_by.func_70453_c(1); 
/*  57 */                 hasWater = true; break;
/*     */               } 
/*     */             } 
/*     */           } 
/*  61 */         } else if (player.field_71071_by.func_70448_g() != null && player.field_71071_by.func_70448_g().func_77973_b() == Items.field_151131_as) {
/*  62 */           hasWater = true;
/*     */         } 
/*     */       }
/*     */       
/*  66 */       if (!hasWater)
/*     */         return; 
/*  68 */       if (!this.isWaterDropStarted) {
/*  69 */         this.isWaterDropStarted = true;
/*  70 */         this.isPressed = false;
/*  71 */         ChatSender.addText(MyChatListener.PREFIX_WATER_DROP + "&fАктивирован");
/*     */       } 
/*     */ 
/*     */       
/*  75 */       if (dist < 10.0D) {
/*  76 */         HintsFinder.rotateTo((Entity)player, player.field_70177_z, 90.0F);
/*     */         
/*  78 */         MovingObjectPosition object = mc.field_71476_x;
/*  79 */         if (object.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && object.field_178784_b == EnumFacing.UP) {
/*  80 */           placeWater();
/*     */ 
/*     */         
/*     */         }
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/*  89 */     else if (mc.field_71439_g.field_70122_E && mc.field_71439_g.field_70181_x > -0.05D) {
/*     */       
/*  91 */       if (this.isWaterDropStarted) {
/*  92 */         this.isWaterDropStarted = false;
/*  93 */         placeWater();
/*  94 */         this.isPressed = false;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void placeWater() {
/* 104 */     if (mc.field_71439_g.func_71045_bC() == null)
/* 105 */       return;  this.isPressed = true;
/* 106 */     mc.field_71442_b.func_78769_a((EntityPlayer)mc.field_71439_g, (World)mc.field_71441_e, mc.field_71439_g.func_71045_bC());
/*     */   }
/*     */   
/*     */   void pressUp() {}
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\AutoWaterDrop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */