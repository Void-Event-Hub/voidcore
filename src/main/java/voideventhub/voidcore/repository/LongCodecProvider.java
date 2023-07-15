package voideventhub.voidcore.repository;

import org.bson.codecs.Codec;
import org.bson.codecs.LongCodec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

public class LongCodecProvider implements CodecProvider {
    @Override
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        if (clazz == Long.class || clazz == long.class) {
            return (Codec<T>) new LongCodec();
        }
        return null;
    }
}
