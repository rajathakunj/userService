package com.leaning.userApp.dao.specification;

import org.springframework.data.jpa.domain.Specification;

import com.leaning.userApp.dao.entity.User;

/**
 * @author rajatha.kunj
 */
public class UserSpecification {
        private UserSpecification() {}

        public static Specification<User> textInAllColumns(String text) {
            if (text.isEmpty()) {
                text = "%";
            }
            final String finalText = text;
            return (root, cq, builder) -> builder.and(builder.or(builder.like(root.get("firstName"), finalText),
                    (builder.like(root.get("lastName"), finalText)),
                    (builder.like(root.get("personalNumber"), finalText)),
                    builder.like(root.get("userId").as(String.class), finalText)));
        }
}
