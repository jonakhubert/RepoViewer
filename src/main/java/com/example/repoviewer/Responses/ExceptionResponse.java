package com.example.repoviewer.Responses;

import java.time.ZonedDateTime;

public record ExceptionResponse(ZonedDateTime timestamp, int httpStatus, String message) {}
