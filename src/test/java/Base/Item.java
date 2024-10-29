package Base;

public class Item {

    private String itemTitle;
    private String itemDescription;
    private String itemPrice;
    private String itemImage;

    public Item(String itemTitle, String itemDescription, String itemPrice, String itemImage) {
        this.itemTitle = itemTitle;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemImage = itemImage;
    }

    public Item(Item i) {
        this.itemTitle = i.itemTitle;
        this.itemDescription = i.itemDescription;
        this.itemPrice = i.itemPrice;
        this.itemImage = i.itemImage;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public String getItemImage() {
        return itemImage;
    }

    @Override
    public String toString() {
        return itemTitle + "\n" + itemDescription + "\n" + itemPrice + "\n"  + itemImage;
    }
}
