package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Answers {

    private List<Answer> answers;

    private Answers() {
    }

    private Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public static Answers init() {
        return new Answers(new ArrayList<>());
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public int size() {
        return answers.size();
    }

    public void delete() {
        for (Answer answer : answers) {
            answer.delete();
        }
    }

    public DeleteHistories deleteByUser(NsUser user) {
        DeleteHistories deleteHistories = DeleteHistories.init();

        for (Answer answer : answers) {
            deleteHistories = deleteHistories.addDeleteHistory(answer.deleteByUser(user));
        }

        return deleteHistories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answers answers1 = (Answers) o;
        return Objects.equals(answers, answers1.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answers);
    }

}
