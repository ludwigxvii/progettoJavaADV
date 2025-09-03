package service;

import java.sql.SQLException;
import wordageddon.classi.User;

/** Servizio sottile sopra al DAO. */
public class ScoreService {
    private final ScoreDAO dao = new ScoreDAO();

    /** Da chiamare a fine partita: salva punteggio sessione e aggiorna il totale. */
    public void finishSession(User u, int sessionScore) throws SQLException {
        dao.updateUserScores(u, sessionScore);
    }

    /** Per leggere il totale dal DB (opzionale). */
    public int getTotalScore(User u) throws SQLException {
        return dao.loadTotalScore(u);
    }
}
