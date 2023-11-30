package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;
import nextstep.qna.exception.NotFoundException;
import nextstep.qna.exception.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public final class Answer {
    private Long id;

    private NsUser writer;

    private Question question;

    private String contents;

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    private Answer() {
    }

    private Answer(final NsUser writer, final Question question, final String contents, final boolean deleted) {
        this(null, writer, question, contents, deleted);
    }

    private Answer(final Long id, final NsUser writer, final Question question, final String contents, final boolean deleted) {
        this.id = id;
        if (writer == null) {
            throw new UnAuthorizedException();
        }

        if (question == null) {
            throw new NotFoundException();
        }

        this.writer = writer;
        this.question = question;
        this.contents = contents;
        this.deleted = deleted;
    }

    public static Answer write(final NsUser writer, final Question question, final String contents) {
        return new Answer(writer, question, contents, false);
    }

    public static Answer writeOfDeleted(final NsUser writer, final Question question, final String contents) {
        return new Answer(writer, question, contents, true);
    }

    public Long getId() {
        return id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isOwner(final NsUser writer) {
        return this.writer.equals(writer);
    }

    public NsUser getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public void toQuestion(final Question question) {
        this.question = question;
    }

    public void delete() {
        this.deleted = true;
    }

    public DeleteHistory deleteByUser(final NsUser user) throws CannotDeleteException {
        if (!isOwner(user)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
        delete();

        return DeleteHistory.addAnswerDeleteHistory(this, user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return deleted == answer.deleted && Objects.equals(id, answer.id) && Objects.equals(writer, answer.writer) && Objects.equals(contents, answer.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, writer, contents, deleted);
    }

}
