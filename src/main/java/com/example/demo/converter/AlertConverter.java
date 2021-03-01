package com.example.demo.converter;

import static java.util.stream.StreamSupport.stream;

import org.apache.camel.Converter;
import org.apache.camel.TypeConverters;
import org.apache.camel.spi.TypeConverterRegistry;
import org.springframework.stereotype.Component;

import com.example.demo.dto.AlertDto;
import com.example.demo.entity.Alert;

@Component
public class AlertConverter implements TypeConverters {

    private static Object lookupAndConvert(TypeConverterRegistry registry, Class<?> componentType, Object v) {
        var converter = registry.lookup(componentType, v.getClass());
        return converter.convertTo(componentType, v);
    }

    @Converter
    public Alert convert(Long source) {
        return new Alert(source);
    }

    @Converter
    public Alert convert(AlertDto source) {
        var alert = new Alert(source.getId());
        alert.setTitle(source.getTitle());
        alert.setTarget(source.getTarget());
        return alert;
    }

    @Converter
    public AlertDto convert(Alert source) {
        var alert = new AlertDto();
        alert.setId(source.getId());
        alert.setTitle(source.getTitle());
        alert.setTarget(source.getTarget());
        return alert;
    }

    @Converter(fallback = true)
    public <T> T convertTo(Class<T> type, Object value, TypeConverterRegistry registry) {
        if (type.isArray() && Iterable.class.isAssignableFrom(value.getClass())) {
            var componentType = type.getComponentType();
            return ((T) stream(((Iterable<T>) value).spliterator(), false)
                    .map(v -> lookupAndConvert(registry, componentType, v))
                    .toArray());
        }
        return null;
    }
}
