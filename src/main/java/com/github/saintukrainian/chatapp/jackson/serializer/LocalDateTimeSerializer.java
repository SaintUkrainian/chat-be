package com.github.saintukrainian.chatapp.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

  @Override
  public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator,
      SerializerProvider serializerProvider) throws IOException {
    jsonGenerator.writeString(
        localDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")));
  }
}
