package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public final class Question {

    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private Answers answers;

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    private Question() {
    }

    private Question(final NsUser writer, final String title, final String contents, final boolean deleted) {
        this(0L, writer, title, contents, deleted);
    }

    private Question(final Long id, final NsUser writer, final String title, final String contents, final boolean deleted) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.answers = Answers.init();
        this.deleted = deleted;
    }

    public static Question write(final NsUser writer, final String title, final String contents) {
        return new Question(writer, title, contents, false);
    }

    public static Question writeOfDeleted(final NsUser writer, final String title, final String contents) {
        return new Question(writer, title, contents, true);
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }

    public boolean isOwner(final NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void delete() {
        this.deleted = true;
    }

    public DeleteHistories deleteByUser(final NsUser user) {
        DeleteHistories deleteHistories = DeleteHistories.init();

        if (!isOwner(user)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }

        delete();
        deleteHistories = deleteHistories.addDeleteHistory(DeleteHistory.addQuestionDeleteHistory(this, user));
        deleteHistories.addDeleteHistorys(answers.deleteByUser(user));

        return deleteHistories;
    }

    public void writeAnswer(final Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public int sizeOfAnswers() {
        return answers.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return deleted == question.deleted && Objects.equals(id, question.id) && Objects.equals(title, question.title) && Objects.equals(contents, question.contents) && Objects.equals(writer, question.writer) && Objects.equals(answers, question.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, contents, writer, answers, deleted);
    }

}
