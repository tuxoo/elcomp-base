package com.elcompbase.model.error;

import java.time.Instant;

public record ErrorResponse(
        String message,
        String errorTime
) {
}
