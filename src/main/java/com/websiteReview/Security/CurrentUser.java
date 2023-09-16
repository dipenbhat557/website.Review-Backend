package com.websiteReview.Security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import java.lang.annotation.*;

/**
 * Custom Spring Security annotation for accessing the currently authenticated
 * user.
 * This annotation is typically used in controller methods to inject the
 * UserPrincipal
 * representing the authenticated user.
 */
@Target({ ElementType.PARAMETER, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {
}
