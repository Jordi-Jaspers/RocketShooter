package rocketshooter.view;

import rocketshooter.model.Player;

/**
 * Dit is een klasse voor de view van de speler aan te maken. (=Uiterlijke
 * eigenschappen.)
 *
 * @author Jordi Jaspers
 * @author Salim Sadala
 */
public class PlayerView extends GameObjectView {

    /**
     * de view van onze player instellen.
     *
     * @param player de eigenschappen van onze player importeren om te laten
     * zien.
     */
    public PlayerView(Player player) {
        super(player);
    }
}
