package com.dexa.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Converter(autoApply = true)
public class TimeZoneConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    private static final ZoneId UTC_ZONE = ZoneId.of("UTC");
    private static final ZoneId JAKARTA_ZONE = ZoneId.of("Asia/Jakarta");

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
        if (localDateTime == null)
            return null;
        return Timestamp.valueOf(localDateTime.atZone(JAKARTA_ZONE).toLocalDateTime());
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
        if (dbData == null)
            return null;
        return dbData.toLocalDateTime();
    }
}
