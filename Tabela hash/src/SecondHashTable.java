public class SecondHashTable<K,V> {
    private Node<K,V>[] buckets;
    private int capacidade;
    private int colisoes ;
    private int quantidade_nomes ;

    public SecondHashTable(int capacidade){
        this.capacidade = capacidade;
        buckets = new Node[capacidade];
    }

    private int polynomialHash(K key) {
        int hash = 0;
        int prime = 31;

        if (key instanceof String) {
            String strKey = (String) key;
            for (int i = 0; i < strKey.length(); i++) {
                hash = prime * hash + strKey.charAt(i);
            }
        } else {

            hash = key.hashCode();
        }

        return Math.abs(hash % capacidade);
    }

    public void put(K key, V value){
        quantidade_nomes ++;
        int index = polynomialHash(key);
        Node <K, V> newNode = new Node<>(key,value);

        if (buckets[index] == null){
            buckets[index] = newNode;
        }
        else{
            colisoes ++;
            Node<K,V> current = buckets[index];
            while(true){
                if(current.key.equals(key)){
                    current.value = value;
                    return;

                }
                if (current.next == null){
                    current.next = newNode;
                    return;

                }
                current = current.next;
            }

        }
    }
    public void printDistribution() {
        for (int i = 0; i < capacidade; i++) {
            Node<K, V> current = buckets[i];
            int count = 0;
            while (current != null) {
                count++;
                current = current.next;
            }
            System.out.println("Bucket " + i + ": " + count + " elementos");
        }
    }

    public V get(K key){
        int index = polynomialHash(key);
        Node<K,V> current = buckets[index];
        while(current != null){
            if(current.key.equals(key)){
                return current.value;

            }
            current = current.next;

        }
        return null;
    }

    public void remove(K key){
        int index = polynomialHash(key);
        Node<K,V> current = buckets[index];
        Node<K,V> prev = null;

        while (current != null){
            if(current.key.equals(key)){
                if (prev == null){
                    buckets[index] = current.next;
                }else{
                    prev.next = current.next;
                }
                return;
            }
            prev = current;
            current = current.next;
        }
    }
    public int getColisoes(){
        return this.colisoes;
    }

    public int getQuantidade_nomes(){
        return this.quantidade_nomes;
    }

}

