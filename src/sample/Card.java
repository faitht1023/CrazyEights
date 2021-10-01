package sample;

import javafx.scene.image.ImageView;

public class Card {

    private int suit;
    private int value;
    private ImageView imageView;
    private int imageViewID;
    private String cardID;

    public Card(ImageView place, String imageID, String card) {
        int cardInt = Integer.parseInt(card);
        suit = cardInt / 100; // gets the suit by dividing the number by 100 w/o remainder, which is either 1: diamonds 2: clubs 3: hearts 4:spades
        value = cardInt % 100; //gets the card's value by finding the remainder when it is divided by 100, which is either 1: ace 2-10: 2-10 11: jack 12:queen 13: king
        imageView = place;
        imageViewID = Integer.parseInt(imageID);
        cardID = card;
        winner = true;
    }

    //getters and setters

    public int getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getImageViewID() {
        return imageViewID;
    }

    public String getCardID() {
        return cardID;
    }

    public boolean getWinner() {
        return winner;
    }

    public void setCard(String cardID) {
        this.cardID = cardID;
    }

}

