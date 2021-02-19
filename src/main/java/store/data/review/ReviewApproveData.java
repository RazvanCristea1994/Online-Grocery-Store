package store.data.review;

public class ReviewApproveData {

    private Long id;
    private boolean approved;

    public ReviewApproveData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
