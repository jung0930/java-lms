package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteHistoriesTest {

    @Test
    @DisplayName("질문과 답변 삭제 이력을 객체로 저장한다.")
    void 질문과_답변_삭제_이력을_객체로_저장() throws CannotDeleteException {
        Question Q1 = Question.write(NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer A1 = Answer.write(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
        Answer A2 = Answer.write(NsUserTest.JAVAJIGI, Q1, "Answers Contents2");

        Q1.writeAnswer(A1);
        Q1.writeAnswer(A2);

        DeleteHistories deleteHistories = Q1.deleteByUser(NsUserTest.JAVAJIGI);

        assertThat(deleteHistories).isEqualTo(
                DeleteHistories.from(Arrays.asList(
                        DeleteHistory.addQuestionDeleteHistory(Q1, NsUserTest.JAVAJIGI),
                        DeleteHistory.addAnswerDeleteHistory(A1, NsUserTest.JAVAJIGI),
                        DeleteHistory.addAnswerDeleteHistory(A2, NsUserTest.JAVAJIGI)
                ))
        );
    }

}
