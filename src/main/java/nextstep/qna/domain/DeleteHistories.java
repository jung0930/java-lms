package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class DeleteHistories {

    private List<DeleteHistory> deleteHistories;

    private DeleteHistories() {
    }

    private DeleteHistories(final List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public static DeleteHistories init() {
        return new DeleteHistories(new ArrayList<>());
    }

    public static DeleteHistories from(final List<DeleteHistory> deleteHistories) {
        return new DeleteHistories(deleteHistories);
    }

    public void addDeleteHistory(final DeleteHistory deleteHistory) {
        this.deleteHistories.add(deleteHistory);
    }

    public DeleteHistories addDeleteHistorys(final DeleteHistories deleteHistorys) {
        for (DeleteHistory deleteHistory : deleteHistorys.deleteHistories) {
            this.deleteHistories.add(deleteHistory);
        }
        return this;
    }

    public int size() {
        return deleteHistories.size();
    }

    public List<DeleteHistory> get() {
        return this.deleteHistories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistories that = (DeleteHistories) o;
        return Objects.equals(deleteHistories, that.deleteHistories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deleteHistories);
    }

}
