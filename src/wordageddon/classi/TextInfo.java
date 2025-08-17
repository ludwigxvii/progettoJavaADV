/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wordageddon.classi;

import java.io.Serializable;

/**
 *
 * @author ludwi
 */
public class TextInfo implements Serializable {
    private String nome;
    private String filename;
    private DifficultState diff;
    private LangState lang;
    private String filestopw;

    public TextInfo(String nome, String filename, DifficultState diff, LangState lang, String filestopw) {
        this.nome = nome;
        this.filename = filename;
        this.diff = diff;
        this.lang = lang;
        this.filestopw = filestopw;
    }

    public String getNome() {
        return nome;
    }

    public String getFilename() {
        return filename;
    }

    public DifficultState getDiff() {
        return diff;
    }

    public LangState getLang() {
        return lang;
    }

    public String getFilestopw() {
        return filestopw;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setDiff(DifficultState diff) {
        this.diff = diff;
    }

    public void setLang(LangState lang) {
        this.lang = lang;
    }

    public void setFilestopw(String filestopw) {
        this.filestopw = filestopw;
    }
    
    
    
}

