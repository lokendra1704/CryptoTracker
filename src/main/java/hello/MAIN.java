package hello;

import java.util.ArrayList;

class MAIN {
    public static void main(String[] args) throws Exception {
        // new Developer();

        // For Backend,
        ArrayList<String> str_coins = new ArrayList<String>();
        str_coins.add("BTC");
        str_coins.add("ETH");
        ArrayList<Crypto> DataObjects = Backend.fetchData(str_coins);

    }
}