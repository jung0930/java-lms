package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = Question.write(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = Question.write(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("삭제할 경우 질문의 삭제 상태를 변경")
    void 질문_삭제_상태_변경() {
        Q1.delete();
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("로그인 사용자와 질문자가 같은 경우 삭제 가능하다.")
    void 로그인_사용자와_질문자가_같은_경우_삭제_가능() throws CannotDeleteException {
        Q1.deleteByUser(NsUserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("로그인 사용자와 질문자가 다른 경우 예외를 반환한다.")
    void 로그인_사용자와_질문자가_다른_경우_예외_반환() {
        assertThatThrownBy(() -> Q1.deleteByUser(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문에 답변을 추가할 수 있다.")
    void 질문_답변_추가() {
        Q2.writeAnswer(AnswerTest.A1);
        Q2.writeAnswer(AnswerTest.A2);

        assertThat(Q2.sizeOfAnswers()).isEqualTo(2);
    }

    @Test
    @DisplayName("답변이 있을 경우 질문자와 답변글의 모든 답변자가 같으면 질문과 답변의 삭제 상태를 변경한다.")
    void 질문자와_답변자가_같으면_질문과_답변_삭제_상태_변경() throws CannotDeleteException {
        Question Q1_1 = Question.write(NsUserTest.JAVAJIGI, "title1", "contents1");
        Q1_1.writeAnswer(Answer.write(NsUserTest.JAVAJIGI, Q1_1, "Answers Contents1"));
        Q1_1.writeAnswer(Answer.write(NsUserTest.JAVAJIGI, Q1_1, "Answers Contents2"));
        Q1_1.deleteByUser(NsUserTest.JAVAJIGI);

        Question Q2_1 = Question.writeOfDeleted(NsUserTest.JAVAJIGI, "title1", "contents1");
        Q2_1.writeAnswer(Answer.writeOfDeleted(NsUserTest.JAVAJIGI, Q2_1, "Answers Contents1"));
        Q2_1.writeAnswer(Answer.writeOfDeleted(NsUserTest.JAVAJIGI, Q2_1, "Answers Contents2"));

        assertThat(Q1_1).isEqualTo(Q2_1);
    }

    @Test
    @DisplayName("답변이 있는 질문 삭제 시 질문자와 답변글의 모든 답변자가 같지 않은 경우 예외를 반환한다.")
    void 질문자와_답변자가_다른_경우_예외_반환() throws CannotDeleteException {
        Question Q1_1 = Question.write(NsUserTest.JAVAJIGI, "title1", "contents1");
        Q1_1.writeAnswer(Answer.write(NsUserTest.SANJIGI, Q1_1, "Answers Contents1"));
        Q1_1.writeAnswer(Answer.write(NsUserTest.JAVAJIGI, Q1_1, "Answers Contents2"));

        assertThatThrownBy(() -> Q1_1.deleteByUser(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

}
