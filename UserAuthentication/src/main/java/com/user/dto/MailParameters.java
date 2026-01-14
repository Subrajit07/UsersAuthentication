package com.user.dto;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record MailParameters(@NonNull String to, @NonNull String subject, @NonNull String body, boolean isHtml) {
}
