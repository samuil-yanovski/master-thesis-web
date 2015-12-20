package models;
import com.avaje.ebean.config.ServerConfig;     
import com.avaje.ebean.event.ServerConfigStartup;     
import com.avaje.ebean.config.EncryptKey;       
import com.avaje.ebean.config.EncryptKeyManager; 

public class CustomServerConfigStartup implements ServerConfigStartup { 

    @Override 
    public void onStart(ServerConfig serverConfig) {     
        serverConfig.setEncryptKeyManager(new BasicEncryptKeyManager());     
    }     
}