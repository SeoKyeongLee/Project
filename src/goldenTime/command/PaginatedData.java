package goldenTime.command;

public class PaginatedData {
	private int currentPage;
    private int totalPages;

    public PaginatedData(int currentPage, int totalPages) {
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
