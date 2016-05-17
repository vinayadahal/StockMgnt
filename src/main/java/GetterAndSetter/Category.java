package GetterAndSetter;

public class Category {

    private long id;
    private String categoryName;

    public void setId(long category_id) {
        this.id = category_id;
    }

    public void setName(String category) {
        this.categoryName = category;
    }

    public long getId() {
        return id;
    }

    public String getCategory() {
        return categoryName;
    }
}
