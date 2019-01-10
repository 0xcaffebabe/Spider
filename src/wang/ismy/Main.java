package wang.ismy;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class Main {

    public static void main(String[] args){
      System.err.println(getWebContent("http://www.qq.com"));
    }

    public static String getWebContent(String url){
        String ret="";
        try {
            URL URL=new URL(url);
            URLConnection conn=URL.openConnection();

            BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String str=null;
            while((str=br.readLine())!=null){
                ret+=str+"\n";
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String encode=getMiddle(ret,"charset=",">");
       encode=getEncoding(encode);
        try {
            ret=new String(ret.getBytes(),"utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ret;
    }
    public static String getMiddle(String str,String before,String after) {
        int pre=str.indexOf(before)+before.length();
        int next=str.indexOf(after,pre);
        return str.substring(pre, next);
    }
    public static String getEncoding(String str) {
       str=str.toUpperCase();
      str=str.substring(0,3);
       if(str.equals("UTF")){
           return "UTF8";
       }else if(str.equals("GBK")){
           return "GBK";
       }else {
           return "UTF8";
       }
    }

}
