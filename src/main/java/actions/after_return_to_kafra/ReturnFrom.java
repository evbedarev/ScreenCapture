package actions.after_return_to_kafra;

import actions.kafra_actions.get_resources.GetResources;
import actions.kafra_actions.put_loot.PutLoot;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.move_by_card.MoveToLocation;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class ReturnFrom implements ReturnToKafra {
    MoveToLocation moveToLocation;
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("main");
    PutLoot putLoot = new PutLoot();
    GetResources getResources;
    Mouse mouse = Mouse.getInstance();
    Keys keys = Keys.getInstance();

    protected ReturnFrom() throws AWTException {
    }
    @Override
    public abstract void startAction() throws Exception;
}
