/*    */ package com.dimchig.bedwarsbro.particles;
/*    */ 
/*    */ import net.minecraft.client.particle.EntityCrit2FX;
/*    */ import net.minecraft.world.World;
/*    */ import scala.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ParticleSharpnessEffect
/*    */   extends EntityCrit2FX
/*    */ {
/*    */   public ParticleSharpnessEffect(World parWorld, double parX, double parY, double parZ) {
/* 13 */     super(parWorld, parX, parY, parZ, 0.0D, 0.0D, 0.0D);
/* 14 */     Random rnd = new Random();
/* 15 */     this.field_70547_e = 7 + rnd.nextInt(3);
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\particles\ParticleSharpnessEffect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */