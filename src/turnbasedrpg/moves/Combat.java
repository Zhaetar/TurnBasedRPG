/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turnbasedrpg.moves;

import java.util.Random;

/**
 *
 * @author matheus.oliveira
 */
public class Combat {
    Pokemon player1;
    Pokemon player2;
    
    public Combat() {
    }
    
    public boolean isReady() {
        if (player1 == null || player2 == null) {
            return false;
        } else {
            return true;
        }
    }
    
//    public int turnMade(int player1Move, int player2Move) {;;
//        Pokemon firstPlayer = calculateSpeedPriority();
//    }
    
    public Pokemon calculateSpeedPriority() {
        if (player1.getSpeedValue() > player2.getSpeedValue()) {
            return player1;
        } else {
            return player2;
        }
    }
    
    public int calculateDamage(int moveID, int player) {
        Pokemon attacker = (player == 1) ? player1 : player2;
        Pokemon defender = (player == 1) ? player2 : player1;
        Moves move = attacker.getPokemonMove(moveID);
        
        Random rand = new Random();
        int randomNumber = rand.nextInt(16) + 85;

        //  Fórmula de calculo de dano: Damage = ((((2 * Level / 5 + 2) * AttackStat * AttackPower / DefenseStat) / 50) + 2) * STAB * Weakness/Resistance * RandomNumber / 100
        int category = move.getFgCategory();
        int damage = ((((2 * attacker.getLevel() / 5 + 2) * attacker.getAttackValue(category) * move.getPower() / defender.getDefenseValue(category)) / 50) + 2);
                /** STAB * Weakness/Resistance *  * randomNumber / 100;;*/

        int newHealth = defender.getHealthValue() - damage;
        if (newHealth < 0) {
            newHealth = 0;
        }
        
        defender.setHealthValue(newHealth);
        
        return damage;
    }

    public Pokemon getPlayer1() {
        return player1;
    }

    public Pokemon getPlayer2() {
        return player2;
    }

    public void setPlayer1(Pokemon player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Pokemon player2) {
        this.player2 = player2;
    }
    
    
}
