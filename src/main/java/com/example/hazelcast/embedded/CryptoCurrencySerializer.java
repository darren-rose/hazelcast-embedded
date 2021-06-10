package com.example.hazelcast.embedded;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;

import java.io.IOException;

public class CryptoCurrencySerializer implements StreamSerializer<CryptoCurrency> {

    @Override
    public void write(ObjectDataOutput out, CryptoCurrency object) throws IOException {
        out.writeUTF(object.getName());
        out.writeUTF(object.getTicker());
    }

    @Override
    public CryptoCurrency read(ObjectDataInput in) throws IOException {
        return CryptoCurrency.builder()
                .name(in.readUTF())
                .ticker(in.readUTF())
                .build();
    }

    @Override
    public int getTypeId() {
        return 1;
    }
}