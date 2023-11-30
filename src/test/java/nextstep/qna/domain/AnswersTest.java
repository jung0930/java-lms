package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class AnswersTest {

    @Test
    @DisplayName("답변을 추가할 수 있다.")
    void 답변_추가() {
        Answers answers = Answers.init();
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A2);

        assertThat(answers.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("답변들(Answers)을 삭제를 할 경우 답변(Answer)의 삭제 상태를 변경한다.")
    void 답변들의_답변_상태_변경() {
        Answers answers = Answers.init();
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A2);

        answers.delete();

        assertAll(
                () -> assertThat(AnswerTest.A1.isDeleted()).isTrue(),
                () -> assertThat(AnswerTest.A2.isDeleted()).isTrue()
        );
    }

    @Test
    @DisplayName("질문자와 답변자가 같은 경우 답변의 삭제 상태를 변경한다.")
    void 질문자와_답변자가_같은_경우_답변들의_답변_상태_변경() throws CannotDeleteException {
        Answers answers = Answers.init();
        answers.add(AnswerTest.A1);

        answers.deleteByUser(NsUserTest.JAVAJIGI);

        assertAll(
                () -> assertThat(AnswerTest.A1.isDeleted()).isTrue()
        );
    }

    @Test
    @DisplayName("질문자와 답변자가 다른 답변이 있는 경우 예외를 반환한다.")
    void 질문자와_답변자가_다른_답변이_있는_경우_예외_반환() {
        Answers answers = Answers.init();
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A2);

        assertThatThrownBy(() -> answers.deleteByUser(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

}
