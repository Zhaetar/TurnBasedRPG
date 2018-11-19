/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turnbasedrpg;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import turnbasedrpg.moves.Combat;
import turnbasedrpg.moves.Pokemon;
import turnbasedrpg.moves.PokemonList;

/**
 *
 * @author matheus.oliveira
 */
public final class Player extends JFrame {

    private final Container contentPane;
    private final JTextArea combatInfo;
    private final JTextArea combatLog;

    private Pokemon playerPokemon;
    private Pokemon enemyPokemon;

    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;

    private JButton p1;
    private JButton p2;
    private JButton p3;
    private JButton p4;

    private JLabel b1desc;
    private JLabel b2desc;
    private JLabel b3desc;
    private JLabel b4desc;
    private JLabel playerImage;
    private JLabel enemyImage;

    private final JPanel sprites;
    private final JPanel buttons;
    private final JPanel buttonsDesc;

    private boolean buttonsEnabled;

    private ClientSideConnection clientSideConnection;

    public Player(int width, int height) throws IOException, URISyntaxException {
        // Configura as variavéis
        contentPane = this.getContentPane();
        combatLog = new JTextArea();
        combatInfo = new JTextArea();
        sprites = new JPanel();
        buttons = new JPanel();
        buttonsDesc = new JPanel();

        configureButtonsPokemonPicker();
    }

    public void configureButtonsPokemonPicker() throws IOException, URISyntaxException {
        // Imagem do jogador
        URI img3 = getClass().getResource("/turnbasedrpg/pokemon/6.png").toURI();
        BufferedImage myPicture3 = ImageIO.read(new File((img3)));
        p1 = new JButton(new ImageIcon(myPicture3));
        p1.setMinimumSize(new Dimension(80, 80));

        // Imagem do jogador
        URI img4 = getClass().getResource("/turnbasedrpg/pokemon/9.png").toURI();
        BufferedImage myPicture4 = ImageIO.read(new File((img4)));
        p2 = new JButton(new ImageIcon(myPicture4));
        p2.setMinimumSize(new Dimension(80, 80));

        // Imagem do jogador
        URI img5 = getClass().getResource("/turnbasedrpg/pokemon/1.png").toURI();
        BufferedImage myPicture5 = ImageIO.read(new File((img5)));
        p3 = new JButton(new ImageIcon(myPicture5));
        p3.setMinimumSize(new Dimension(80, 80));

        // ID dos pokémon
        p1.setName("6");
        p2.setName("9");
        p3.setName("10");

        setUpPokemonChooserButtons();
    }

    public void configureButtons() throws IOException, URISyntaxException {
        // Configura os botões
        b1 = new JButton(this.playerPokemon.getPokemonMove(0).getName());
        b2 = new JButton(this.playerPokemon.getPokemonMove(1).getName());
        b3 = new JButton(this.playerPokemon.getPokemonMove(2).getName());
        b4 = new JButton(this.playerPokemon.getPokemonMove(3).getName());
        b1desc = new JLabel(setUpButton(0), SwingConstants.LEFT);
        b2desc = new JLabel(setUpButton(1), SwingConstants.LEFT);
        b3desc = new JLabel(setUpButton(2), SwingConstants.LEFT);
        b4desc = new JLabel(setUpButton(3), SwingConstants.LEFT);
        b1desc.setVerticalAlignment(SwingConstants.TOP);
        b2desc.setVerticalAlignment(SwingConstants.TOP);
        b3desc.setVerticalAlignment(SwingConstants.TOP);
        b4desc.setVerticalAlignment(SwingConstants.TOP);

        // ID dos botões
        b1.setName("0");
        b2.setName("1");
        b3.setName("2");
        b4.setName("3");

        // Cor dos botões
        b1.setBackground(this.playerPokemon.getPokemonMove(0).getColor());
        b2.setBackground(this.playerPokemon.getPokemonMove(1).getColor());
        b3.setBackground(this.playerPokemon.getPokemonMove(2).getColor());
        b4.setBackground(this.playerPokemon.getPokemonMove(3).getColor());
        b1.setForeground(Color.WHITE);
        b2.setForeground(Color.WHITE);
        b3.setForeground(Color.WHITE);
        b4.setForeground(Color.WHITE);

        b1desc.setBackground(this.playerPokemon.getPokemonMove(0).getColor());
        b2desc.setBackground(this.playerPokemon.getPokemonMove(1).getColor());
        b3desc.setBackground(this.playerPokemon.getPokemonMove(2).getColor());
        b4desc.setBackground(this.playerPokemon.getPokemonMove(3).getColor());
        b1desc.setForeground(Color.WHITE);
        b2desc.setForeground(Color.WHITE);
        b3desc.setForeground(Color.WHITE);
        b4desc.setForeground(Color.WHITE);
        b1desc.setOpaque(true);
        b2desc.setOpaque(true);
        b3desc.setOpaque(true);
        b4desc.setOpaque(true);

        // Imagem do jogador
        URI img = getClass().getResource("/turnbasedrpg/pokemon/back/" + playerPokemon.getNumber() + ".png").toURI();
        BufferedImage myPicture = ImageIO.read(new File((img)));
        playerImage = new JLabel(new ImageIcon(myPicture));
        playerImage.setMinimumSize(new Dimension(80, 80));

        // Imagem do oponente (ovo)
        URI img2 = getClass().getResource("/turnbasedrpg/pokemon/egg.png").toURI();
        BufferedImage myPicture2 = ImageIO.read(new File((img2)));
        enemyImage = new JLabel(new ImageIcon(myPicture2));
        enemyImage.setMinimumSize(new Dimension(80, 80));

        connectToServer();
        if (clientSideConnection.isAlive()) {
            setUpGUI();
            setUpButtons();
        } else {
            JOptionPane.showMessageDialog(null, "Conexão recusada. Tente novamente em breve.");
        }
    }

    public String setUpButton(int id) {
        return ("<html>"
                + "<p style='font-size: 8px'><b>Tipo</b>: "
                + this.playerPokemon.getPokemonMove(id).getTypeName() + "</p>"
                + "<p style='font-size: 8px'><b>Descrição</b>: "
                + this.playerPokemon.getPokemonMove(id).getDesc() + "</p>"
                + "</html>");
    }

    public void setUpGUI() {
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

        contentPane.remove(p1);
        contentPane.remove(p2);
        contentPane.remove(p3);

        sprites.setLayout(new GridLayout(1, 4));
        sprites.add(playerImage);
        sprites.add(enemyImage);
        contentPane.add(sprites);

        // Adiciona mensagens na caixa de mensagens
        combatLog.setText("Iniciando jogo...");
        combatLog.setWrapStyleWord(true);
        combatLog.setLineWrap(true);
        combatLog.setEditable(false);

        combatInfo.setWrapStyleWord(true);
        combatInfo.setLineWrap(true);
        combatInfo.setEditable(false);

        // Adiciona os botões na tela
        buttons.setLayout(new GridLayout(1, 4));
        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);
        buttons.add(b4);
        contentPane.add(buttons);

        // Descrição dos botões
        buttonsDesc.setLayout(new GridLayout(1, 4));
        buttonsDesc.add(b1desc);
        buttonsDesc.add(b2desc);
        buttonsDesc.add(b3desc);
        buttonsDesc.add(b4desc);
        contentPane.add(buttonsDesc);

        contentPane.add(combatLog);
        contentPane.add(combatInfo);

        combatLog.setText("Conectado como jogador #" + clientSideConnection.getPlayerID());
        buttonsEnabled = true;
        toggleButtons();

        this.setVisible(true);
    }

    public void setUpInitialGUI() {
        this.setSize(500, 350);
        this.setTitle("Escolha seu pokémon");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane.setLayout(new GridLayout(2, 2));

        contentPane.add(p1);
        contentPane.add(p2);
        contentPane.add(p3);
        this.setVisible(true);
    }

    public void setUpPokemonChooserButtons() {
        ActionListener actionListener;

        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JButton button = (JButton) event.getSource();
                PokemonList pokemonGetter = new PokemonList();

                Pokemon Pokemon = pokemonGetter.getPokemonByID(button.getName());
                setPlayerPokemon(Pokemon);
                try {
                    configureButtons();
                } catch (IOException | URISyntaxException ex) {
                    Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        p1.addActionListener(actionListener);
        p2.addActionListener(actionListener);
        p3.addActionListener(actionListener);
    }

    public void setUpButtons() {
        ActionListener actionListener;

        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JButton button = (JButton) event.getSource();

                // Desabilita os botões
                buttonsEnabled = false;
                toggleButtons();

                // Envia qual ataque foi utilizado para o servidor
                clientSideConnection.sendButtonNum(Integer.parseInt(button.getName()));

                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            updateTurn();
                        } catch (ClassNotFoundException | IOException | URISyntaxException ex) {
                            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                t.start();
            }
        };

        b1.addActionListener(actionListener);
        b2.addActionListener(actionListener);
        b3.addActionListener(actionListener);
        b4.addActionListener(actionListener);

    }

    public void toggleButtons() {
        b1.setEnabled(buttonsEnabled);
        b2.setEnabled(buttonsEnabled);
        b3.setEnabled(buttonsEnabled);
        b4.setEnabled(buttonsEnabled);
    }

    public void updateTurn() throws ClassNotFoundException, IOException, URISyntaxException {
        Combat combat = clientSideConnection.receiveCombat();
        setPlayerPokemon((this.clientSideConnection.getPlayerID() == 1) ? combat.getPlayer1() : combat.getPlayer2());
        setEnemyPokemon((this.clientSideConnection.getPlayerID() == 1) ? combat.getPlayer2() : combat.getPlayer1());

        // Atualiza a imagem do oponente
        URI img = getClass().getResource("/turnbasedrpg/pokemon/" + getEnemyPokemon().getNumber() + ".png").toURI();
        BufferedImage enemyImageLocal = ImageIO.read(new File((img)));
        this.enemyImage.setIcon(new ImageIcon(enemyImageLocal));

        combatInfo.setText("Você: [" + getPlayerPokemon().getHealthValue()
                + "/" + getPlayerPokemon().getMaxHealthValue() + "]"
                + "\nOponente: [" + getEnemyPokemon().getHealthValue()
                + "/" + getEnemyPokemon().getMaxHealthValue() + "]");

        combatLog.setText(combat.getFirstMessage());
        if (combat.getSecondMessage()!=null) {
            combatLog.setText(combatLog.getText() + "\n" + combat.getFirstMessage());
        }
        if (combat.getWinnerID()!=0) {
            if (combat.getWinnerID() == clientSideConnection.getPlayerID()) {
                combatLog.setText(combatLog.getText()
                        + "\nVocê derrotou " + getEnemyPokemon().getName() + "!");
            } else {
                combatLog.setText(combatLog.getText()
                        + "\nVocê foi derrotado por " + getEnemyPokemon().getName() + "!");
            }
        } else {
            // Habilita botões
            buttonsEnabled = true;
            toggleButtons();
        }
    }

    public boolean connectToServer() {
        clientSideConnection = new ClientSideConnection(this.playerPokemon);

        return (clientSideConnection != null);
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        Player p = new Player(500, 100);
        p.setUpInitialGUI();
    }

    public Pokemon getPlayerPokemon() {
        return playerPokemon;
    }

    public void setPlayerPokemon(Pokemon playerPokemon) {
        this.playerPokemon = playerPokemon;
    }

    public Pokemon getEnemyPokemon() {
        return enemyPokemon;
    }

    public void setEnemyPokemon(Pokemon enemyPokemon) {
        this.enemyPokemon = enemyPokemon;
    }

}
