package actions.press_on_image.actions_spring;

import org.springframework.context.annotation.Bean;

import java.awt.*;

public class ActionConfigSpring {
    @Bean
    public StepAside stepAside() throws AWTException {
        return new StepAside();
    }
}
