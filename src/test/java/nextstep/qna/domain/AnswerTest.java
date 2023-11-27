package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = Answer.of(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = Answer.of(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("삭제를 할 경우 답변의 삭제 상태를 변경한다.")
    void 답변_상태_변경() {
        A1.delete();
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문자와 답변자가 같은 경우 삭제가 가능하다.")
    void 질문자와_답변자가_같으면_답변_삭제() throws CannotDeleteException {
        Question question = Question.of(NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer answer = Answer.of(NsUserTest.JAVAJIGI, question, "Answers Contents1");

        answer.deleteByUser(NsUserTest.JAVAJIGI);
        assertThat(answer.isDeleted()).isTrue();
    }

}
