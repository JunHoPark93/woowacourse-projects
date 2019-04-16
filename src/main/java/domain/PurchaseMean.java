package domain;

public class PurchaseMean {
    private int purchaseMean;

    public PurchaseMean(int purchaseMean) {
        if (purchaseMean != 1 && purchaseMean != 0) {
            throw new IllegalArgumentException("1 또는 2를 입력하세요");
        }
        this.purchaseMean = purchaseMean;
    }

    public int getPurchaseMean() {
        return purchaseMean;
    }
}
