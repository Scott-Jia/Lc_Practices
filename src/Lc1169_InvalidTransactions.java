import java.util.*;

class Lc1169_InvalidTransactions {

    // create a transaction class for easier handling
    class Transaction{
        String name;
        int time;
        int amount;
        String city;
        public Transaction(String line) {
            String[] words = line.split(",");
            name = words[0];
            time = Integer.parseInt(words[1]);
            amount = Integer.parseInt(words[2]);
            city = words[3];
        }
    }


    public List<String> invalidTransactions(String[] transactions) {
        List<String> result = new ArrayList<>();
        if (transactions == null || transactions.length < 2) return result;

        // a map to mapping the name and transaction with this name
        Map<String, List<Transaction>> map = new HashMap<>();

        // first traverse, fill out the map, the Map<String, List<Transaction>>
        for (String str : transactions) {
            Transaction curr_trans = new Transaction(str);
            map.putIfAbsent(curr_trans.name, new ArrayList<Transaction>());
            map.get(curr_trans.name).add(curr_trans);
        }

        // second traverse, check each str(transaction) one by one
        // add the invalid element to result list
        for (String str: transactions) {
            Transaction curr_trans = new Transaction(str);
            if (! isValid(curr_trans, map.get(curr_trans.name)))
                result.add(str);
        }

        return result;
    }

    // helper function to check if the transaction is valid
    // by traversing the list of transaction under that trans name
    public boolean isValid(Transaction trans, List<Transaction> list) {
        if (trans.amount > 1000) return false;

        for (Transaction ta : list) {
            if ( Math.abs(ta.time - trans.time) <= 60 && !ta.city.equals(trans.city))
                return false;
        }

        return true;
    }
}
