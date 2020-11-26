package pos.machine;

import java.util.*;
import java.util.stream.Collectors;

public class PosMachine {
    static List<ItemInfo> ITEM_INFOS = ItemDataLoader.loadAllItemInfos();
    private List<ItemInfoDetails> itemInfoDetails;
    private int total;


    public String printReceipt(List<String> barcodes) {
        ArrayList<ItemInfoDetails> itemInfoDetails = getItemInfo(barcodes);
        calculateSubTotalOfItem(itemInfoDetails);
        String receipt = "***<store earning no money>Receipt***\n";

        for (ItemInfoDetails itemInfoDetail : this.itemInfoDetails) {
            receipt += String.format("Name: %s, Quantity: %d, Unit price: %d (yuan), Subtotal: %d (yuan)\n", itemInfoDetail.getName(), itemInfoDetail.getQuantity(), itemInfoDetail.getUnitPrice(), itemInfoDetail.getSubtotal());
        }
        receipt += "----------------------\n";
        receipt += "Total: " + total + " (yuan)\n";
        receipt += "**********************";
        return receipt;
    }


    public static ArrayList<ItemInfoDetails> getItemInfo(List<String> barcodes) {
        ArrayList<ItemInfoDetails> itemsInformation = new ArrayList<>();
        List<String> uniqueBarcodes = barcodes.stream().distinct().collect(Collectors.toList());
        uniqueBarcodes.forEach(barcode -> itemsInformation.add(getItemDetails(barcode)));
        return countQuantityOfItems(barcodes, itemsInformation);
    }
    private static ItemInfoDetails getItemDetails(String barcode) {
        ItemInfo item = ITEM_INFOS.stream().filter(ItemInfo -> ItemInfo.getBarcode().equals(barcode)).findFirst().get();
        return new ItemInfoDetails(item.getBarcode(), item.getName(), item.getPrice());
    }


    private static ArrayList<ItemInfoDetails> countQuantityOfItems(List<String> barcodes, ArrayList<ItemInfoDetails> itemInfoDetails) {
        itemInfoDetails.forEach(itemInfo -> {
            itemInfo.setQuantity(Collections.frequency(barcodes, itemInfo.getBarcode()));
        });
        return itemInfoDetails;
    }

    public void calculateSubTotalOfItem(List<ItemInfoDetails> itemInfoDetails) {

        this.itemInfoDetails= itemInfoDetails;
        total = itemInfoDetails.stream().mapToInt(ItemInfoDetails::getSubtotal).sum();
    }

}
