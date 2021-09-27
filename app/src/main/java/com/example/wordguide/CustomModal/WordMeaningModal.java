package com.example.wordguide.CustomModal;

public class WordMeaningModal {
    String meaning,type,synonym,antonym;

    public WordMeaningModal(String meaning, String type, String synonym, String antonym) {
        this.meaning = meaning;
        this.type = type;
        this.synonym = synonym;
        this.antonym = antonym;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

    public String getAntonym() {
        return antonym;
    }

    public void setAntonym(String antonym) {
        this.antonym = antonym;
    }
}
