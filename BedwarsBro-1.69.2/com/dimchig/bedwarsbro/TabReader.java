/*    */ package com.dimchig.bedwarsbro;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.network.NetworkPlayerInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TabReader
/*    */ {
/*    */   public static List<String> getTabNames() {
/* 14 */     ArrayList<String> arr = new ArrayList<String>();
/*    */     
/*    */     try {
/* 17 */       Collection<NetworkPlayerInfo> players = Minecraft.func_71410_x().func_147114_u().func_175106_d();
/*    */       
/* 19 */       int cnt = 0;
/* 20 */       for (NetworkPlayerInfo info : players) {
/* 21 */         String name = (Minecraft.func_71410_x()).field_71456_v.func_175181_h().func_175243_a(info).trim();
/* 22 */         arr.add(name);
/*    */ 
/*    */         
/* 25 */         if (cnt > 10000)
/* 26 */           break;  cnt++;
/*    */       } 
/* 28 */     } catch (Exception ex) {
/* 29 */       ex.printStackTrace();
/*    */     } 
/*    */     
/* 32 */     return arr;
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\TabReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */