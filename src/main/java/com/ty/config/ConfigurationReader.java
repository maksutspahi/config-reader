package com.ty.config;

public interface ConfigurationReader {
    <T> T getValue(String key);
}
