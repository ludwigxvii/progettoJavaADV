/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wordageddon.classi;

import java.util.Objects;

/**
 *
 * @author ludwi
 */
public class User {
    private String nome;
    private String email;
    private int totalscore;
    private int lastscore;

    public User(String nome, String email, int totalscore, int lastscore) {
        this.nome = nome;
        this.email = email;
        this.totalscore = totalscore;
        this.lastscore = lastscore;
    }

    
    
    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public int getTotalscore() {
        return totalscore;
    }

    public int getLastscore() {
        return lastscore;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.nome);
        hash = 61 * hash + this.totalscore;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.totalscore != other.totalscore) {
            return false;
        }
        return Objects.equals(this.nome, other.nome);
    }

    @Override
    public String toString() {
        return "User{" + "nome=" + nome + ", email=" + email + ", totalscore=" + totalscore + ", lastscore=" + lastscore + '}';
    }
            
}
