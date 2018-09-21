import character_cast.CastCharacter;
import character_cast.CastCharacterPriest;
import key_and_mouse.Mouse;
import logic.characters.Character;
import logic.characters.Characters;
import main.Prop;

public class TestFollowTheCharacter {
    public static void main(String[] args) throws Exception {
        Prop.initialize();
        Mouse mouse = Mouse.getInstance();
        Characters character = new Character();
        CastCharacter castCharacter = new CastCharacterPriest(0);
        castCharacter.begin();

        while (true) {
            Thread.sleep(500);
            character.findCharacter();
            castCharacter.cast();
        }
    }
}
