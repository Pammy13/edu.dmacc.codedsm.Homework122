package edu.dmacc.codedsm.Homework12;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.*;

import static sun.security.krb5.internal.crypto.Nonce.value;

public class Card {

    public int value;
    public String suit;

    public static class DeckRandomizer {

        private static final Random random = new Random();

        public static List<Card> chooseRandomCards(Map<String, List<Integer>> deck, int numberOfCards) {
            List<Card> chosenCards = new ArrayList<>();
            Set<String> suits = deck.keySet();

            for (int i = 0; i < numberOfCards; i++) {
                Card chosenSuitAndCard = getCard(deck, suits);
                while (chosenCards.contains(chosenSuitAndCard)) {
                    chosenSuitAndCard = getCard(deck, suits);
                }
                chosenCards.add(chosenSuitAndCard);
            }

            return chosenCards;
        }

        private static String getChosenSuit(Set<String> suits) {
            int suitCount = suits.size();
            return suits.toArray(new String[suitCount])[random.nextInt(suitCount)];
        }

        private static Card getCard(Map<String, List<Integer>> deck, Set<String> suits) {
            return getCard(deck, getChosenSuit(suits), checkIfDeckIsExhausted(deck), random, suits);
        }

        private static boolean checkIfDeckIsExhausted(Map<String, List<Integer>> deck) {
            Collection<List<Integer>> values = deck.values();
            int numOfCards = 0;
            for (List<Integer> value : values) {
                numOfCards = numOfCards + value.size();
            }
            return numOfCards == 0;
        }

    }
}
