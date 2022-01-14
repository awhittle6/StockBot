package stockbot;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TeleBotImplementation extends TelegramLongPollingBot{
    @Override
    public void onUpdateReceived(Update update) {
       // TODO
        
    }

    public static void sendMessage(String text){
        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
        String apiToken = "5087841063:AAETw8qoeBwymbYcc_hLfrOUFQz1baVCmiA";
        String chatId = "@Stock_Alerts_AW";
        urlString = String.format(urlString, apiToken, chatId, text);
        System.out.println(urlString);
        URL url;
        try {
            url = new URL(urlString);
            URLConnection conn = url.openConnection();
            StringBuilder sb = new StringBuilder();
            InputStream is = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String inputLine = "";
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
        } catch (MalformedURLException e1) {
            System.out.println("Malformed URL Exception!");
        } catch (IOException e) {
            System.out.println("IO Exception!");
        }
    }


    @Override
    public String getBotUsername() {
        return "aw_stock_bot";
    }

    @Override
    public String getBotToken() {
        return "";
    }
    
    public static void registerBot(){
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new TeleBotImplementation());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    
}
