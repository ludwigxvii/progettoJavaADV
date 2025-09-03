package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import wordageddon.classi.User;

/**
 * DAO per la gestione dei punteggi. Usa lo stesso approccio di LoginController:
 * DriverManager con URL/utente/password.
 */
public class ScoreDAO {

    // Usa gli stessi parametri che hai in LoginController
    private static final String URL  = "jdbc:postgresql://localhost:5432/wordageddon";
    private static final String USER = "javaus";
    private static final String PASS = "jv2025";

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ignore) { }
        return DriverManager.getConnection(URL, USER, PASS);
    }

    /** Aggiorna lastscore e somma al totalscore dell’utente. */
    public void updateUserScores(User u, int sessionScore) throws SQLException {
        if (u == null) return;
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "UPDATE public.wordageddonusers " +
                     "SET lastscore = ?, totalscore = totalscore + ? " +
                     "WHERE username = ?")) {
            ps.setInt(1, sessionScore);
            ps.setInt(2, sessionScore);
            ps.setString(3, u.getNome()); // 'nome' è lo username nella tua User
            ps.executeUpdate();
        }
    }

    /** Legge il totale corrente dal DB. Utile per stampare il totale aggiornato. */
    public int loadTotalScore(User u) throws SQLException {
        if (u == null) return 0;
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT totalscore FROM public.wordageddonusers WHERE username = ?")) {
            ps.setString(1, u.getNome());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return 0;
    }
}
