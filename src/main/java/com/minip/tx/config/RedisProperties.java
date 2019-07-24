package com.minip.tx.config;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RedisProperties {
    List<String> nodes;
}
