package crypto.middleware.model;

import java.util.ArrayList;

public class CryptoCurrencyList {
    public ArrayList<CryptoCurrency> cryptos = new ArrayList<>();

    public CryptoCurrencyList() {
        //Empty Constructor
    }

    public void addCrypto(CryptoCurrency crypto) {
        cryptos.add(crypto);
    }
}
