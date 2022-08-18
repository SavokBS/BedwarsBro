/*    */ package com.dimchig.bedwarsbro.hints;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BWItem
/*    */ {
/*    */   public String local_name;
/*    */   public String display_name;
/*    */   public BWItemsHandler.BWItemType type;
/*    */   public BWItemsHandler.BWItemToolLevel toolLevel;
/*    */   public BWItemsHandler.BWItemColor color;
/*    */   public BWItemsHandler.BWItemArmourLevel armourLevel;
/*    */   
/*    */   public BWItem(String local_name, String display_name, BWItemsHandler.BWItemType type, BWItemsHandler.BWItemToolLevel toolLevel, BWItemsHandler.BWItemArmourLevel armourLevel) {
/* 17 */     this.local_name = local_name;
/* 18 */     this.display_name = display_name;
/* 19 */     this.type = type;
/* 20 */     this.toolLevel = toolLevel;
/* 21 */     this.armourLevel = armourLevel;
/*    */     
/* 23 */     this.color = BWItemsHandler.BWItemColor.NONE;
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\hints\BWItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */