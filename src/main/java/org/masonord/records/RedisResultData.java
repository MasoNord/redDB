package org.masonord.records;

import org.masonord.enums.DataType;

public record RedisResultData(DataType dataType, String data) {}
