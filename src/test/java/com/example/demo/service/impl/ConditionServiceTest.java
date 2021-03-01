package com.example.demo.service.impl;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.length;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.demo.entity.Alert;
import com.example.demo.model.Condition;

class ConditionServiceTest {

    public static final Condition<Alert> TITLE_NOT_EMPTY_CONDITION = alert -> isNotEmpty(alert.getTitle());
    public static final Condition<Alert> TITLE_HAS_LENGTH_MORE_THEN_5_CONDITION = alert -> length(alert.getTitle()) > 5;
    private static final List<Condition<Alert>> CONDITIONS = List.of(
            TITLE_NOT_EMPTY_CONDITION,
            TITLE_HAS_LENGTH_MORE_THEN_5_CONDITION
    );

    private final ConditionService conditionService = new ConditionService(CONDITIONS);

    private static Alert makeAlert(String title) {
        var alert = new Alert();
        alert.setTitle(title);
        return alert;
    }

    @Test
    void shouldReturnFalseForEmptyTitle() {
        var alert = makeAlert("   ");
        assertFalse(conditionService.test(alert));
    }

    @Test
    void shouldReturnFalseForTitleWithLengthLessThenOrEqualFive() {
        var alert = makeAlert("Title");
        assertFalse(conditionService.test(alert));
    }

    @Test
    void shouldReturnTrueForNonEmptyTitleWithLengthGreaterThenFive() {
        var alert = makeAlert("Non empty title with length greater then 5");
        assertTrue(conditionService.test(alert));
    }
}
