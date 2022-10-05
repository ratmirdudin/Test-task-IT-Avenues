package com.ratmirdudin.userservice.domains.enums;

import java.util.Arrays;

public enum Status {
    Online,
    Offline;

    public static Status findCaseInsensitive(String str) {
        return Arrays.stream(Status.values())
                .filter(status -> str.equalsIgnoreCase(status.name()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No constant found"));
    }
}
