package pos.machine;

public class ItemInfoDetails {
    private final String barcode;
    private final String name;
    private final int unitPrice;
    private int quantity=0;
    private int subtotal=0;

    public ItemInfoDetails(String barcode, String name, int unitPrice) {
        this.barcode = barcode;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.subtotal = this.unitPrice * quantity;
    }
    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSubtotal() {
        return subtotal;
    }
}

