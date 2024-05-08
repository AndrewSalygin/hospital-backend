package com.andrewsalygin.dto.card;

public record CardEditInfo (
    Long cardId,
    Integer complexity,
    Long statusId,
    Long userId
) {

}
