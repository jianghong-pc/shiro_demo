package com.qianmi.demo.config;

import com.fasterxml.jackson.core.JsonGenerator;
import net.logstash.logback.decorate.JsonGeneratorDecorator;

public class NonEscapingJsonGeneratorDecorator implements JsonGeneratorDecorator {

    @Override
    public JsonGenerator decorate(JsonGenerator generator) {
        return generator.disable(JsonGenerator.Feature.ESCAPE_NON_ASCII);
    }
}
