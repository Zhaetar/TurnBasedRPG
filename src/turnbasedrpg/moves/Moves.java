/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turnbasedrpg.moves;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author matheus.oliveira
 */
public class Moves implements Serializable {

    /**
     * Move name
     */
    public String name = "";

    /**
     * Move description
     */
    public String desc = "";

    /**
     * Move power, defaults 10
     */
    public int power = 10;

    /**
     * Move power points, defaults 20
     */
    public int pp = 20;

    // Accuracy
    public int accuracy = 100;

    // type
    public int type = 1;

    // Category (1. Physical, 2. Special, 3. Status)
    public int category = 1;

    // Does it changes a pokémon status? (1 - Yes (enemy), 2 - Yes (self), 0 - No)
    public int statChange = 0;

    // What stat does it change? (1 - Attack, 2 - Special Attack, 3 - Defense, 4 - Special Defense, 5 - Speed, 6 - Accuracy, 7 - Avoidance)
    public int statChangeType = 1;

    // By how much?
    public int statChangePower = 1;

    // Does it afflict a status change? (1 - Yes, 2 - No)
    public int statusChange = 2;

    // Which one? (1 - Confusion, 2 - Burn, 3 - Frozen, 4 - Sleep, 5 - Poison)
    public int statusChangeType = 1;

    public String getTypeName() {

        switch (getType()) {
            case 1:
                return "Normal";
            case 2:
                return "Water";
            case 3:
                return "Fire";
            case 4:
                return "Grass";
            case 5:
                return "Flying";
            case 6:
                return "Steel";
            case 7:
                return "Fairy";
            case 8:
                return "Psychic";
            case 9:
                return "Ghost";
            case 10:
                return "Dark";
            case 11:
                return "Bug";
            case 12:
                return "Fighting";
            case 13:
                return "Electric";
            case 14:
                return "Ice";
            case 15:
                return "Rock";
            case 16:
                return "Ground";
            case 17:
                return "Poison";
            case 18:
                return "Dragon";
            default:
                return "Normal";
        }
    }

    public Color getColor() {
        switch (getType()) {
            case 1: // Normal
                return Color.decode("#A8A77A");
            case 2: // Water
                return Color.decode("#6390F0");
            case 3: // Fire
                return Color.decode("#EE8130");
            case 4: // Grass
                return Color.decode("#7AC74C");
            case 5: // Flying
                return Color.decode("#A98FF3");
            case 6: // Steel
                return Color.decode("#B7B7CE");
            case 7: // Fairy
                return Color.decode("#D685AD");
            case 8: // Psychic
                return Color.decode("#F95587");
            case 9: // Ghost
                return Color.decode("#735797");
            case 10: // Dark
                return Color.decode("#B7B7CE");
            case 11: // Bug
                return Color.decode("#A6B91A");
            case 12: // Fighting
                return Color.decode("#C22E28");
            case 13: // Electric
                return Color.decode("#F7D02C");
            case 14: // Ice
                return Color.decode("#96D9D6");
            case 15: // Rock
                return Color.decode("#B6A136");
            case 16: // Ground
                return Color.decode("#E2BF65");
            case 17: // Poison
                return Color.decode("#A33EA1");
            case 18: // Dragon
                return Color.decode("#6F35FC");
            default:
                return Color.decode("#A8A77A");
        }
    }

    public String getStatChangeName() {    // What stat does it change? (1 - Attack, 2 - Special Attack, 3 - Defense, 4 - Special Defense, 5 - Speed, 6 - Accuracy, 7 - Avoidance)
        switch (getStatChangeType()) {
            case 1:
                return "Physical attack";
            case 2:
                return "Special attack";
            case 3:
                return "Physical Defense";
            case 4:
                return "Special defense";
            default:
                return "Speed";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPP() {
        return pp;
    }

    public void setPP(int pp) {
        this.pp = pp;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getStatChange() {
        return statChange;
    }

    public void setStatChange(int statChange) {
        this.statChange = statChange;
    }

    public int getStatChangeType() {
        return statChangeType;
    }

    public void setStatChangeType(int statChangeType) {
        this.statChangeType = statChangeType;
    }

    public int getStatChangePower() {
        return statChangePower;
    }

    public void setStatChangePower(int statChangePower) {
        this.statChangePower = statChangePower;
    }

    public int getStatusChange() {
        return statusChange;
    }

    public void setStatusChange(int statusChange) {
        this.statusChange = statusChange;
    }

    public int getStatusChangeType() {
        return statusChangeType;
    }

    public void setStatusChangeType(int statusChangeType) {
        this.statusChangeType = statusChangeType;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPp() {
        return pp;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }

}
