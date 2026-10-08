import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ConnectionManager {
    private static final String SERVER = "netsrv.cim.rhul.ac.uk";
    private static final int PORT = 1812;

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public boolean connect(String username, String password) {
        try {
            socket = new Socket(SERVER, PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println(username.toLowerCase());
            out.println(password);

            String response = in.readLine();
            return response != null && response.contains("successfully");
        } catch (IOException e) {
            close();
            return false;
        }
    }

    public List<Card> readCards() throws IOException {
        List<Card> cards = new ArrayList<>();
        String line;

        while ((line = in.readLine()) != null) {
            if (line.equals("OK")) {
                break;
            }

            if (line.equals("CARD")) {
                int id = Integer.parseInt(in.readLine());
                String name = in.readLine();
                Rank rank = Rank.valueOf(in.readLine());
                int price = Integer.parseInt(in.readLine());

                cards.add(new Card(id, name, rank, price));
            }
        }

        return cards;
    }

    public List<Card> getCards() throws IOException {
        out.println("CARDS");
        return readCards();
    }

    public List<Card> getOffers() throws IOException {
        out.println("OFFERS");
        return readCards();
    }

    public int getCredits() throws IOException {
        out.println("CREDITS");

        String response = in.readLine();
        int credits = Integer.parseInt(response);

        in.readLine(); // OK
        return credits;
    }

    public boolean buy(int id) throws IOException {
        out.println("BUY " + id);
        return "OK".equals(in.readLine());
    }

    public boolean sell(int id, int price) throws IOException {
        out.println("SELL " + id + " " + price);
        return "OK".equals(in.readLine());
    }

    public void close() {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException ignored) {
                // The connection is already being closed.
            }
        }
    }
}
