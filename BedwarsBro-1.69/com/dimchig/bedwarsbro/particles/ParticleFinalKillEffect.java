/*    */ package com.dimchig.bedwarsbro.particles;
/*    */ 
/*    */ import net.minecraft.client.particle.EntityAuraFX;
/*    */ import net.minecraft.world.World;
/*    */ import scala.util.Random;
/*    */ 
/*    */ public class ParticleFinalKillEffect extends EntityAuraFX {
/*    */   public ParticleFinalKillEffect(World parWorld, double parX, double parY, double parZ) {
/*  9 */     super(parWorld, parX, parY, parZ, 0.0D, 0.0D, 0.0D);
/* 10 */     Random rnd = new Random();
/*    */     
/* 12 */     func_70536_a(65);
/* 13 */     this.field_70544_f = 1.2F;
/* 14 */     func_70538_b(1.0F, 1.0F, 1.0F);
/* 15 */     this.field_70547_e = 50 + rnd.nextInt(30);
/* 16 */     this.field_70545_g = 1000.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\particles\ParticleFinalKillEffect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */