/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

/**
 *
 * @author abhi
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class sms {



        public static String sendSms() {
            try {
                // Construct data
                String apiKey = "G30FZsY0wQk-rSo8CnuJvYoibGM5ePneIvY378N7Wu";
                String message = "java sms testing";
                String sender = "test name";
                String numbers = "+number";

                // Send data
                HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
                String data = apiKey + numbers + message + sender;
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                conn.getOutputStream().write(data.getBytes("UTF-8"));
                final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                final StringBuffer stringBuffer = new StringBuffer();
                String line;
                while ((line = rd.readLine()) != null) {
                    stringBuffer.append(line);
                }
                rd.close();

                return stringBuffer.toString();
            } catch (Exception e) {
                System.out.println("Error SMS " + e);
                return "Error " + e;
            }
        }
        
       public static void main(String[] args) {
        
           System.out.println(sendSms());
           
    }

}
