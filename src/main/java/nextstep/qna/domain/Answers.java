package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Answers {

    private Question question;
    private List<Answer> answers;

    private Answers() {
    }

    private Answers(Question question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public static Answers init(Question question) {
        return new Answers(question, new ArrayList<>());
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void delete() {
        for (Answer answer : answers) {
            answer.delete();
        }
    }

    public void deleteByUser(NsUser user) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.deleteByUser(user);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answers answers1 = (Answers) o;
        return Objects.equals(question, answers1.question) && Objects.equals(answers, answers1.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answers);
    }

}
