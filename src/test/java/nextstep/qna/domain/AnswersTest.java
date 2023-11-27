package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AnswersTest {

    @Test
    @DisplayName("답변들(Answers)을 삭제를 할 경우 답변(Answer)의 삭제 상태를 변경한다.")
    void 답변들의_답변_상태_변경() {
        Answers answers = Answers.init(QuestionTest.Q1);
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A2);

        answers.deleteByUser(NsUserTest.JAVAJIGI);

        assertAll(
                () -> assertThat(AnswerTest.A1.isDeleted()).isTrue(),
                () -> assertThat(AnswerTest.A2.isDeleted()).isTrue()
        );
    }

}
