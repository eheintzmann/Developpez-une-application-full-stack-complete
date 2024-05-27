package com.openclassrooms.mddapi.configuration.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Slf4j
public class ApiInstantSerializer extends JsonSerializer<Instant> {
    private static final String DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss";

    private static final DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern(DATE_TIME_FORMAT)
            .withZone(ZoneId.from(ZoneOffset.UTC)
            );

    @Override
    public void serialize(
            Instant value,
            JsonGenerator gen,
            SerializerProvider serializers) throws IOException {

        gen.writeString(formatter.format(value));
    }
}
