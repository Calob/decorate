package com.dec.classes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Identity {
//    位权值数�?
    private static byte[] Wi=new byte[17];
//    身份证前部分字符�?
    private static final byte fPart = 6;
//    身份证算法求模关键�?
    private static final byte fMod = 11;
//    旧身份证长度
    private static final byte oldIDLen = 15;
//    新身份证长度
    private static final byte newIDLen = 18;
//    新身份证年份标志
    private static final String yearFlag = "19";
//    校验码串 
    private static final String CheckCode="10X98765432"; 
//  设置身份证的年月日格�?
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static void setWiBuffer(){
        for(int i=0;i<Wi.length;i++){    
            int k = (int) Math.pow(2, (Wi.length-i));
            Wi[i] = (byte)(k % fMod);
        }
    }
    
    //获取新身份证的最后一�?�?���?
    private static String getCheckFlag(String idCard){
        int sum = 0;
        //进行加权求和 
        for(int i=0; i<17; i++){        
            sum += Integer.parseInt(idCard.substring(i,i+1)) * Wi[i];
        }
        //取模运算，得到模�?
        byte iCode = (byte) (sum % fMod); 
        return CheckCode.substring(iCode,iCode+1);
    }
    
    //判断串长度的合法�?
    private static int checkLength(final String idCard){
        boolean right = (idCard.length() == oldIDLen) || (idCard.length() == newIDLen);
       if(right == false)
    	   return -1;
        if(idCard.length() == newIDLen){      	
            return 1;           
        }else if(idCard.length() == oldIDLen){      	
            return 2;           
        }
        return 0;
    }
    
    //获取时间�?
    private static String getIDDate(final String idCard,boolean newIDFlag){
        String dateStr = "";
        if(newIDFlag) {
        	dateStr = idCard.substring(fPart,fPart+8);
        }
        else {
        	dateStr = yearFlag + idCard.substring(fPart,fPart+6);
            System.out.println(dateStr);
        }
        return dateStr;
    }
    
    private static boolean checkDate(final String dateSource) throws java.text.ParseException{
        String dateStr = dateSource.substring(0,4)+"-"+dateSource.substring(4,6)+"-"+dateSource.substring(6,8);
        System.out.println(dateStr);
        DateFormat df = Identity.dateFormat;
        df.setLenient(false);
        try {
            Date date= df.parse(dateStr);
            return (date!=null);
        } catch (Exception e) {
            return false;
        }
    }
    
    //旧身份证转换成新身份证号�?
    public static String getNewIDCard(final String oldIDCard) throws ParseException{
        //初始化方�?
        Identity.setWiBuffer();
        if(!checkIDCard(oldIDCard)){
            return oldIDCard;
        }
        String newIDCard = oldIDCard.substring(0, fPart);
        newIDCard += yearFlag;
        newIDCard += oldIDCard.substring(fPart, oldIDCard.length());
        String ch = getCheckFlag(newIDCard);
        newIDCard += ch;
        return newIDCard;
    }
    
    //新身份证转换成旧身份证号�?
    public static String getOldIDCard(final String newIDCard) throws ParseException{
        //初始化方�?
        Identity.setWiBuffer();
        if(!checkIDCard(newIDCard)){
            return newIDCard;
        }
        String oldIDCard = newIDCard.substring(0,fPart)+
                    newIDCard.substring(fPart+yearFlag.length(),newIDCard.length()-1);
        return oldIDCard;
    }
    
    //判断身份证号码的合法�?
    public static boolean checkIDCard(final String idCard) throws ParseException{
        //初始化方�?
        Identity.setWiBuffer();
        boolean isNew=false;
        //String message = "";
       
        if (checkLength(idCard) == -1){
            //message = "ID长度异常";
            return false;
        } else if(checkLength(idCard) == 1){
        	isNew = true;
        }else if(checkLength(idCard) == 2){
        	isNew = false;
        }
        String idDate = getIDDate(idCard, isNew);
        if(!checkDate(idDate)){
            //message = "ID时间异常";
            return false;
        }
        if(isNew){
            String checkFlag = getCheckFlag(idCard);
            String theFlag = idCard.substring(idCard.length()-1,idCard.length());
            if(!checkFlag.equals(theFlag)){
                return false;
            }
        }
        return true;
    }
    
}