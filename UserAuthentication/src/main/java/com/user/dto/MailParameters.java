package com.user.dto;

import java.util.List;

import lombok.Builder;
import lombok.NonNull;
@Builder
public record MailParameters(@NonNull String to,
									                         @NonNull String subject,
									                         @NonNull String body,
									                         boolean isHtml) {
}
