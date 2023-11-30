package nextstep.qna;

import nextstep.qna.domain.Answer;
import nextstep.qna.domain.Question;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;

public class TestSetUp {

    public Question Q1;
    public Question Q2;
    public Answer A1;
    public Answer A2;

    @BeforeEach
    public void setUp() {
        Q1 = Question.write(NsUserTest.JAVAJIGI, "title1", "contents1");
        Q2 = Question.write(NsUserTest.SANJIGI, "title2", "contents2");
        A1 = Answer.write(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
        A2 = Answer.write(NsUserTest.SANJIGI, Q1, "Answers Contents2");
    }

}
