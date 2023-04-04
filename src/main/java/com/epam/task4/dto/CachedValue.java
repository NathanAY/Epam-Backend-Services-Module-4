package com.epam.task4.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CachedValue {

    int attempts;

    LocalDateTime blockedTimestamp;
}
