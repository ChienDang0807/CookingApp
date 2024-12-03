package com.chiendang.cooking.api.forgotpassword.utils;

import lombok.Builder;

@Builder
public record MailBody(String to, String subject, String text ) {
}
