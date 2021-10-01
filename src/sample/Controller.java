package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    @FXML
    private ImageView card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11, card12, card13, card14, card15, card16, card17, card18, card19, card20, card21, card22, mainPile, inPlayCard;
    @FXML
    private TextArea msg_text;
    @FXML
    private TextField cardChoice;
    @FXML private ComboBox suitPicker;

    //Played will hold the cards that have been played and playable will just hold the "deck", which will be shuffled.
    //cardObjects just holds all of the card objects for value checking
    ArrayList<String> played;
    ArrayList<String> playable;
    ArrayList<Card> cardObjects;
    ObservableList<String> list = FXCollections.observableArrayList("♦️", "♣️", "♥️", "♠️");

    //refCard is the card in play in the middle. It is set this way for quick reference in this controller class.
    int refCard;
    //canPlay is a boolean that prevents a player from submitting multiple plays per turn. (forces the next turn to occur)
    boolean canPlay;


    @Override
    public void initialize(URL url, ResourceBundle resources) {
        load();
    }

    //Here is the initial setup
    private void load() {

        msg_text.setText("Welcome to Crazy Eights! Click the deal button to begin."); // controls the text shown in the TextArea
        mainPile.setVisible(true); //shows the "draw pile" image

        playable = new ArrayList<>();
        Collections.addAll(playable, "101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "111", "112", "113", "201", "202", "203", "204", "205", "206", "207", "208", "209", "210", "211", "212", "213", "301", "302", "303", "304", "305", "306", "307", "308", "309", "310", "311", "312", "313", "401", "402", "403", "404", "405", "406", "407", "408", "409", "410", "411", "412", "413");
        played = new ArrayList<>();
        cardObjects = new ArrayList<>();
        suitPicker.setItems(list);


    }

    //This is what happens when the deal button is pressed. The deck is shuffled and 8 cards are dealt to the player. Respective card objects are also created.
    @FXML
    private void onDeal(ActionEvent event) {

        played.clear();

        Collections.shuffle(playable);
        setCards(playable.get(0), inPlayCard);
        refCard = Integer.parseInt(playable.get(0));
        Card ref_Card = new Card(inPlayCard, "0", playable.get(0));

        setCards(playable.get(1), card1);
        Card card_1 = new Card(card1, "1", playable.get(1));

        setCards(playable.get(2), card2);
        Card card_2 = new Card(card2, "2", playable.get(2));

        setCards(playable.get(3), card3);
        Card card_3 = new Card(card3, "3", playable.get(3));

        setCards(playable.get(4), card4);
        Card card_4 = new Card(card4, "4", playable.get(4));

        setCards(playable.get(5), card5);
        Card card_5 = new Card(card5, "5", playable.get(5));

        setCards(playable.get(6), card6);
        Card card_6 = new Card(card6, "6", playable.get(6));

        setCards(playable.get(7), card7);
        Card card_7 = new Card(card7, "7", playable.get(7));

        setCards(playable.get(8), card8);
        Card card_8 = new Card(card8, "8", playable.get(8));

        Collections.addAll(cardObjects, ref_Card, card_1, card_2, card_3, card_4, card_5, card_6, card_7, card_8);

        //these are set invisible during the initial deal
        card9.setVisible(false);
        card10.setVisible(false);
        card11.setVisible(false);
        card12.setVisible(false);
        card13.setVisible(false);
        card14.setVisible(false);
        card15.setVisible(false);
        card16.setVisible(false);
        card17.setVisible(false);
        card18.setVisible(false);
        card19.setVisible(false);
        card20.setVisible(false);
        card21.setVisible(false);
        card22.setVisible(false);

        canPlay = true;

        msg_text.setText("Cards dealt. Ready to begin!");
    }

    //This is what happens when the play button is clicked.
    @FXML
    private void onPlay(ActionEvent event) {
        int cardChosen = Integer.parseInt(cardChoice.getText()); //gets the chosen card
        msg_text.setText(" ");

        if (canPlay) {
            if (!(cardChosen == 0)) {
                for (Card selectedCard : cardObjects) {
                    if (selectedCard.getImageViewID() == cardChosen) {
                        if ((refCard / 100 == selectedCard.getSuit()) || (refCard % 100 == selectedCard.getValue()) || (selectedCard.getValue() == 8)) {
                            setCards(selectedCard.getCardID(), inPlayCard);
                            refCard = Integer.parseInt(selectedCard.getCardID());
                            cardObjects.get(0).setCard(selectedCard.getCardID());
                            selectedCard.getImageView().setVisible(false);
                            canPlay = false;
                        } else {
                            msg_text.setText("Please pick a valid card.");
                        }
                    }
                }
            } else {
                msg_text.setText("Please pick a valid card.");
            }
        } else {
            msg_text.setText("You have already played! Select next.");
        }

    }

    @FXML
    private void changeSuit(ActionEvent event) {
        int convert;
        if(refCard % 100 == 8) {
            if (suitPicker.getSelectionModel().getSelectedItem() == "♦️") {
                convert = 100 + (refCard % 100);
                refCard = convert;
            } else if (suitPicker.getSelectionModel().getSelectedItem() == "♣️") {
                convert = 200 + (refCard % 100);
                refCard = convert;
            } else if (suitPicker.getSelectionModel().getSelectedItem() == "♥️") {
                convert = 300 + (refCard % 100);
                refCard = convert;
            } else if (suitPicker.getSelectionModel().getSelectedItem() == "♠️️") {
                convert = 400 + (refCard % 100);
                refCard = convert;
            }
        } else {
            msg_text.setText("You can only select a suit after you play an eight.");
        }
    }

    //This is what happens when the Next button is clicked. The next playable card is put to the top of the deck.
    @FXML
    private void onNext(ActionEvent event) {
        msg_text.setText("");
        for (String inHand : playable) {
            if (played.contains(inHand)) {
                System.out.println(inHand + " says 'not me!'");
            } else {
                if (((Integer.parseInt(inHand) / 100) == refCard / 100) || ((Integer.parseInt(inHand) % 100) == refCard % 100)) {
                    refCard = Integer.parseInt(inHand);
                    setCards(inHand, inPlayCard);
                    cardObjects.get(0).setCard(inHand);
                    canPlay = true;
                    System.out.println(inHand + " was played.");
                    break;
                } else {
                    System.out.println(inHand + " is unplayable.");
                }
            }
        }
        if (played.size() == 52) {
            msg_text.setText("Game over. No more cards left."); // If all cards are played, then game over.
        }
    }

    //This is what happens when the Draw button is clicked. A card is drawn and replaces an invisible card space.
    @FXML
    private void onDraw(ActionEvent event) {
        msg_text.setText("Card drawn.");
        for (String inHand : playable) {
            if (played.contains(inHand)) {
                System.out.println(inHand + " was already played");
            } else if (played.size() == 52) {
                msg_text.setText("Game over. No more cards left.");
            } else {
                if (!card1.isVisible()) {
                    setCards(inHand, card1);
                    Card card_1 = new Card(card1, "1", inHand);
                    cardObjects.add(card_1);
                    card2.setVisible(true);

                } else if (!card2.isVisible()) {
                    setCards(inHand, card2);
                    Card card_2 = new Card(card2, "2", inHand);
                    cardObjects.add(card_2);
                    card2.setVisible(true);

                } else if (!card3.isVisible()) {
                    setCards(inHand, card3);
                    Card card_3 = new Card(card3, "3", inHand);
                    cardObjects.add(card_3);
                    card3.setVisible(true);

                } else if (!card4.isVisible()) {
                    setCards(inHand, card4);
                    Card card_4 = new Card(card4, "4", inHand);
                    cardObjects.add(card_4);
                    card4.setVisible(true);

                } else if (!card5.isVisible()) {
                    setCards(inHand, card5);
                    Card card_5 = new Card(card5, "5", inHand);
                    cardObjects.add(card_5);
                    card5.setVisible(true);

                } else if (!card6.isVisible()) {
                    setCards(inHand, card6);
                    Card card_6 = new Card(card6, "6", inHand);
                    cardObjects.add(card_6);
                    card6.setVisible(true);

                } else if (!card7.isVisible()) {
                    setCards(inHand, card7);
                    Card card_7 = new Card(card7, "7", inHand);
                    cardObjects.add(card_7);
                    card7.setVisible(true);

                } else if (!card8.isVisible()) {
                    setCards(inHand, card8);
                    Card card_8 = new Card(card8, "8", inHand);
                    cardObjects.add(card_8);
                    card8.setVisible(true);

                } else if (!card9.isVisible()) {
                    setCards(inHand, card9);
                    Card card_9 = new Card(card9, "9", inHand);
                    cardObjects.add(card_9);
                    card9.setVisible(true);

                } else if (!card10.isVisible()) {
                    setCards(inHand, card10);
                    Card card_10 = new Card(card10, "10", inHand);
                    cardObjects.add(card_10);
                    card10.setVisible(true);

                } else if (!card11.isVisible()) {
                    setCards(inHand, card11);
                    Card card_11 = new Card(card11, "11", inHand);
                    cardObjects.add(card_11);
                    card11.setVisible(true);

                } else if (!card12.isVisible()) {
                    setCards(inHand, card12);
                    Card card_12 = new Card(card12, "12", inHand);
                    cardObjects.add(card_12);
                    card12.setVisible(true);

                } else if (!card13.isVisible()) {
                    setCards(inHand, card13);
                    Card card_13 = new Card(card13, "13", inHand);
                    cardObjects.add(card_13);
                    card13.setVisible(true);

                } else if (!card14.isVisible()) {
                    setCards(inHand, card14);
                    Card card_14 = new Card(card14, "14", inHand);
                    cardObjects.add(card_14);
                    card14.setVisible(true);

                } else if (!card15.isVisible()) {
                    setCards(inHand, card15);
                    Card card_15 = new Card(card15, "15", inHand);
                    cardObjects.add(card_15);
                    card15.setVisible(true);

                } else if (!card16.isVisible()) {
                    setCards(inHand, card16);
                    Card card_16 = new Card(card16, "16", inHand);
                    cardObjects.add(card_16);
                    card16.setVisible(true);

                } else if (!card17.isVisible()) {
                    setCards(inHand, card17);
                    Card card_17 = new Card(card17, "17", inHand);
                    cardObjects.add(card_17);
                    card17.setVisible(true);

                } else if (!card18.isVisible()) {
                    setCards(inHand, card18);
                    Card card_18 = new Card(card18, "18", inHand);
                    cardObjects.add(card_18);
                    card18.setVisible(true);

                } else if (!card19.isVisible()) {
                    setCards(inHand, card19);
                    Card card_19 = new Card(card19, "19", inHand);
                    cardObjects.add(card_19);
                    card19.setVisible(true);

                } else if (!card20.isVisible()) {
                    setCards(inHand, card20);
                    Card card_20 = new Card(card20, "20", inHand);
                    cardObjects.add(card_20);
                    card20.setVisible(true);

                } else if (!card21.isVisible()) {
                    setCards(inHand, card21);
                    Card card_21 = new Card(card21, "21", inHand);
                    cardObjects.add(card_21);
                    card21.setVisible(true);

                } else if (!card22.isVisible()) {
                    setCards(inHand, card22);
                    Card card_22 = new Card(card22, "22", inHand);
                    cardObjects.add(card_22);
                    card22.setVisible(true);
                } else if (!card1.isVisible() && !card2.isVisible() && !card3.isVisible() && !card4.isVisible() && !card5.isVisible() && !card6.isVisible() && !card7.isVisible() && !card8.isVisible() && !card9.isVisible() && !card10.isVisible() && !card11.isVisible() && !card12.isVisible() && !card13.isVisible() && !card15.isVisible() && !card16.isVisible() && !card14.isVisible() && !card17.isVisible() && !card18.isVisible() && !card19.isVisible() && !card20.isVisible() && !card21.isVisible() && !card22.isVisible()) {
                    msg_text.setText("Game over. You have won!!!"); // if there are no cards left in the player's hand, they have won!
                }
                break;
            }
        }

    }

    //this is the setCards method where the imageview is set to the appropriate image.
    public void setCards(String hand, ImageView imageView) {
        switch (hand) {
            case "101":
                imageView.setImage(new Image("/sample/img/ace_of_diamonds.png"));
                break;
            case "102":
                imageView.setImage(new Image("/sample/img/two_of_diamonds.png"));
                break;
            case "103":
                imageView.setImage(new Image("/sample/img/three_of_diamonds.png"));
                break;
            case "104":
                imageView.setImage(new Image("/sample/img/four_of_diamonds.png"));
                break;
            case "105":
                imageView.setImage(new Image("/sample/img/five_of_diamonds.png"));
                break;
            case "106":
                imageView.setImage(new Image("/sample/img/six_of_diamonds.png"));
                break;
            case "107":
                imageView.setImage(new Image("/sample/img/seven_of_diamonds.png"));
                break;
            case "108":
                imageView.setImage(new Image("/sample/img/eight_of_diamonds.png"));
                break;
            case "109":
                imageView.setImage(new Image("/sample/img/nine_of_diamonds.png"));
                break;
            case "110":
                imageView.setImage(new Image("/sample/img/ten_of_diamonds.png"));
                break;
            case "111":
                imageView.setImage(new Image("/sample/img/jack_of_diamonds.png"));
                break;
            case "112":
                imageView.setImage(new Image("/sample/img/queen_of_diamonds.png"));
                break;
            case "113":
                imageView.setImage(new Image("/sample/img/king_of_diamonds.png"));
                break;
            case "201":
                imageView.setImage(new Image("/sample/img/ace_of_clubs.png"));
                break;
            case "202":
                imageView.setImage(new Image("/sample/img/two_of_clubs.png"));
                break;
            case "203":
                imageView.setImage(new Image("/sample/img/three_of_clubs.png"));
                break;
            case "204":
                imageView.setImage(new Image("/sample/img/four_of_clubs.png"));
                break;
            case "205":
                imageView.setImage(new Image("/sample/img/five_of_clubs.png"));
                break;
            case "206":
                imageView.setImage(new Image("/sample/img/six_of_clubs.png"));
                break;
            case "207":
                imageView.setImage(new Image("/sample/img/seven_of_clubs.png"));
                break;
            case "208":
                imageView.setImage(new Image("/sample/img/eight_of_clubs.png"));
                break;
            case "209":
                imageView.setImage(new Image("/sample/img/nine_of_clubs.png"));
                break;
            case "210":
                imageView.setImage(new Image("/sample/img/ten_of_clubs.png"));
                break;
            case "211":
                imageView.setImage(new Image("/sample/img/jack_of_clubs.png"));
                break;
            case "212":
                imageView.setImage(new Image("/sample/img/queen_of_clubs.png"));
                break;
            case "213":
                imageView.setImage(new Image("/sample/img/king_of_clubs.png"));
                break;
            case "301":
                imageView.setImage(new Image("/sample/img/ace_of_hearts.png"));
                break;
            case "302":
                imageView.setImage(new Image("/sample/img/two_of_hearts.png"));
                break;
            case "303":
                imageView.setImage(new Image("/sample/img/three_of_hearts.png"));
                break;
            case "304":
                imageView.setImage(new Image("/sample/img/four_of_hearts.png"));
                break;
            case "305":
                imageView.setImage(new Image("/sample/img/five_of_hearts.png"));
                break;
            case "306":
                imageView.setImage(new Image("/sample/img/six_of_hearts.png"));
                break;
            case "307":
                imageView.setImage(new Image("/sample/img/seven_of_hearts.png"));
                break;
            case "308":
                imageView.setImage(new Image("/sample/img/eight_of_hearts.png"));
                break;
            case "309":
                imageView.setImage(new Image("/sample/img/nine_of_hearts.png"));
                break;
            case "310":
                imageView.setImage(new Image("/sample/img/ten_of_hearts.png"));
                break;
            case "311":
                imageView.setImage(new Image("/sample/img/jack_of_hearts.png"));
                break;
            case "312":
                imageView.setImage(new Image("/sample/img/queen_of_hearts.png"));
                break;
            case "313":
                imageView.setImage(new Image("/sample/img/king_of_hearts.png"));
                break;
            case "401":
                imageView.setImage(new Image("/sample/img/ace_of_spades.png"));
                break;
            case "402":
                imageView.setImage(new Image("/sample/img/two_of_spades.png"));
                break;
            case "403":
                imageView.setImage(new Image("/sample/img/three_of_spades.png"));
                break;
            case "404":
                imageView.setImage(new Image("/sample/img/four_of_spades.png"));
                break;
            case "405":
                imageView.setImage(new Image("/sample/img/five_of_spades.png"));
                break;
            case "406":
                imageView.setImage(new Image("/sample/img/six_of_spades.png"));
                break;
            case "407":
                imageView.setImage(new Image("/sample/img/seven_of_spades.png"));
                break;
            case "408":
                imageView.setImage(new Image("/sample/img/eight_of_spades.png"));
                break;
            case "409":
                imageView.setImage(new Image("/sample/img/nine_of_spades.png"));
                break;
            case "410":
                imageView.setImage(new Image("/sample/img/ten_of_spades.png"));
                break;
            case "411":
                imageView.setImage(new Image("/sample/img/jack_of_spades.png"));
                break;
            case "412":
                imageView.setImage(new Image("/sample/img/queen_of_spades.png"));
                break;
            case "413":
                imageView.setImage(new Image("/sample/img/king_of_spades.png"));
                break;
        }
        imageView.setVisible(true);
        played.add(hand);
    }

}
