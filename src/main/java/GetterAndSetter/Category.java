package GetterAndSetter;

public class Category {

    private String id;
    private String categoryName;

    public void setId(String category_id) {
        this.id = category_id;
    }

    public void setName(String category) {
        this.categoryName = category;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return categoryName;
    }
}
