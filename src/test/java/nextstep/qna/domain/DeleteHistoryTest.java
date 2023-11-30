package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteHistoryTest {

    @Test
    @DisplayName("질문 삭제 이력을 객체로 저장한다.")
    void 질문_삭제_이력을_객체로_저장() {
        Question question = Question.write(NsUserTest.JAVAJIGI, "title1", "contents1");

        assertThat(DeleteHistory.addQuestionDeleteHistory(question, NsUserTest.JAVAJIGI)).isInstanceOf(DeleteHistory.class);
    }

}
