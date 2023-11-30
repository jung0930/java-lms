package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public final class DeleteHistory {

    private Long id;

    private ContentType contentType;

    private Long contentId;

    private NsUser deletedBy;

    private LocalDateTime createdDate = LocalDateTime.now();

    private DeleteHistory() {
    }

    private DeleteHistory(final ContentType contentType, final Long contentId, final NsUser deletedBy, final LocalDateTime createdDate) {
        this.contentType = contentType;
        this.contentId = contentId;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
    }

    public static DeleteHistory addQuestionDeleteHistory(final Question question, final NsUser deletedBy) {
        return new DeleteHistory(ContentType.QUESTION, question.getId(), deletedBy, LocalDateTime.now());
    }

    public static DeleteHistory addAnswerDeleteHistory(final Answer answer, final NsUser deletedBy) {
        return new DeleteHistory(ContentType.ANSWER, answer.getId(), deletedBy, LocalDateTime.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistory that = (DeleteHistory) o;
        return Objects.equals(id, that.id) &&
                contentType == that.contentType &&
                Objects.equals(contentId, that.contentId) &&
                Objects.equals(deletedBy, that.deletedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contentType, contentId, deletedBy);
    }

}
