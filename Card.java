public class Card implements Comparable<Card> {
    private final int id;
    private final String name;
    private final Rank rank;
    private final int price;

    public Card(int id, String name, Rank rank, int price) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Rank getRank() {
        return rank;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public int compareTo(Card other) {
        // Higher rarity should be shown first.
        int rankCompare = Integer.compare(other.rank.ordinal(), this.rank.ordinal());
        if (rankCompare != 0) {
            return rankCompare;
        }

        int nameCompare = this.name.compareTo(other.name);
        if (nameCompare != 0) {
            return nameCompare;
        }

        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + rank + " | " + price;
    }
}
