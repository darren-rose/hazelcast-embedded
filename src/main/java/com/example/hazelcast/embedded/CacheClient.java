package com.example.hazelcast.embedded;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.stereotype.Component;

@Component
public class CacheClient {
    public static final String CRYPTO_CURRENCIES = "crypto-currencies";
    private final HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(createConfig());

    public CryptoCurrency put(String number, CryptoCurrency car){
        IMap<String, CryptoCurrency> map = hazelcastInstance.getMap(CRYPTO_CURRENCIES);
        return map.putIfAbsent(number, car);
    }

    public CryptoCurrency get(String key){
        IMap<String, CryptoCurrency> map = hazelcastInstance.getMap(CRYPTO_CURRENCIES);
        return map.get(key);
    }

    public Config createConfig() {
        Config config = new Config();
        config.addMapConfig(mapConfig());
        config.getSerializationConfig().addSerializerConfig(serializerConfig());
        return config;
    }

    private SerializerConfig serializerConfig() {
        return  new SerializerConfig()
                .setImplementation(new CryptoCurrencySerializer())
                .setTypeClass(CryptoCurrency.class);
    }

    private MapConfig mapConfig() {
        MapConfig mapConfig = new MapConfig(CRYPTO_CURRENCIES);
        mapConfig.setTimeToLiveSeconds(360);
        mapConfig.setMaxIdleSeconds(360);
        return mapConfig;
    }
}
