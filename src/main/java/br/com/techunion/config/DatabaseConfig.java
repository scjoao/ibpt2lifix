package br.com.techunion.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import br.com.techunion.util.Crypt;

public class DatabaseConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfig.class);
	
	private static final String PROPERTY_FILE = "C:/LIFIX/CONFIG/conexao.properties";
	private static final String PERSISTENCE_UNIT_NAME = "lifixPDVPU";
	private static final String DB_URL = "url";
	private static final String DB_DRIVER = "driver_class";
	private static final String DB_USER = "username";
	private static final String DB_PASSWORD = "senha";
	private static final String DB_PROVIDER_CLASS = "cache_provider";
	
	private static EntityManagerFactory emf = null;

	public static EntityManager getEntityManager() throws Exception {
		if (emf == null){
			emf = configure(PERSISTENCE_UNIT_NAME, getProperties());
		}
		return emf.createEntityManager();
	}
	
    private static EntityManagerFactory configure(String nomePersistence, Map<String, String> mapProperties) throws Exception {
        try {
            return Persistence.createEntityManagerFactory(nomePersistence, mapProperties);
        } catch (Exception e) {
            throw new Exception("Erro na criação EMF", e);
        }
    }

    private static Map<String, String> getProperties() throws FileNotFoundException, IOException {
    	Map<String, String> map = new HashMap<String, String>();
    	Properties props = readProperties();
    	map.put("javax.persistence.jdbc.url", decrypt(props.getProperty(DB_URL)));
    	map.put("javax.persistence.jdbc.driver", decrypt(props.getProperty(DB_DRIVER)));
    	map.put("javax.persistence.jdbc.user", decrypt(props.getProperty(DB_USER)));
    	map.put("javax.persistence.jdbc.password", decrypt(props.getProperty(DB_PASSWORD)));
    	map.put("hibernate.cache.provider_class", decrypt(props.getProperty(DB_PROVIDER_CLASS)));
    	
    	map.put("javax.persistence.schema-generation.database.action", "");
    	return map;
    }

	private static Properties readProperties() throws FileNotFoundException, IOException {
    	LOGGER.trace("//Inicio de Conexão com o Banco de Dados.");
    	
		Properties props = new Properties();
    	props.load(new FileInputStream(PROPERTY_FILE));
    	for (Object key : props.keySet()){
    		LOGGER.debug("CHAVE {} - VALOR {}", key.toString(), Crypt.decrypt(props.get(key).toString()));
    	}
    	LOGGER.trace("//Fim de Conexão com o Banco de Dados.");
    	return props;
    }
    
    private static String decrypt(String property) {
    	return Crypt.decrypt(property);
	}
}
