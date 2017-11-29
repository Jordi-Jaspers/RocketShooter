package rocketshooter.view;

import rocketshooter.model.Player;

/**
 * Dit is een klasse voor de view van de speler aan te maken. (=Uiterlijke
 * eigenschappen.)
 *
 * @author Jordi
 */
public class PlayerView extends GameObjectView {

    private Player player;

    /**
     * de view van onze player instellen.
     *
     * @param player de eigenschappen van onze player importeren om te laten
     * zien.
     * @param url van het object, het pad naar de afbeelding.
     *
     */
    public PlayerView(Player player, String url) {
        super(player, url);
    }

    /**
     * returnwaarden geven van onze klasse player.
     *
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

}
