package logic.kill_monster;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class SpringConfigMonsters {
    @Bean
    public List<KillMonster> monstersBeachDun02() throws AWTException {
        return Stream.of(new Megalith(),new StalacticGolem()).collect(Collectors.toList());
    }
    @Bean
    public List<KillMonster> monstersBeachDun03() throws AWTException {
        return Stream.of(new Nereid(), new TharaFrog(), new Hydra()).collect(Collectors.toList());
    }
    @Bean
    public List<KillMonster> monstersCmdField02() throws AWTException {
        return Stream.of(new Seal(), new Galapago()).collect(Collectors.toList());
    }
    @Bean
    public List<KillMonster> monstersCmdField04() throws AWTException {
        return Stream.of(new SeaOtterBoss(),new SeaOtter(), new Galapago()).collect(Collectors.toList());
    }
    @Bean
    public List<KillMonster> monstersEinDun01() throws AWTException {
        return Stream.of(new Pitman(), new Porcellio(), new Noxous()).collect(Collectors.toList());
    }
    @Bean
    public List<KillMonster> monstersGefField03() throws AWTException {
        return Stream.of(new Orc(), new OrcLady()).collect(Collectors.toList());
    }
    @Bean
    public List<KillMonster> monstersGefField05() throws AWTException {
        return Stream.of(new ThiefBug(), new Creamy(), new Smokie()).collect(Collectors.toList());
    }
    @Bean
    public List<KillMonster> monstersGefField08() throws AWTException {
        return Stream.of(new Petite(), new SwiftPetite(), new Mantis()).collect(Collectors.toList());
    }
    @Bean
    public List<KillMonster> monstersGlChurch() throws AWTException {
        return Stream.of(new WraithDeath(), new EvilDruid(), new Wraith(), new Mimic()).collect(Collectors.toList());
    }
    @Bean
    public List<KillMonster> monstersHerbLocation01() throws AWTException {
        return Stream.of(new GreenPlant(), new BluePlant(), new ShiningPlant()).collect(Collectors.toList());
    }
    @Bean
    public List<KillMonster> monstersInSphinx3() throws AWTException {
        return Stream.of(new Pasana(), new Marduk(), new Mimic()).collect(Collectors.toList());
    }
    @Bean
    public List<KillMonster> monstersMocField11() throws AWTException {
        return Stream.of(new Scorpion(), new BabyDesertWolf(), new Condor()).collect(Collectors.toList());
    }
    @Bean
    public List<KillMonster> monstersMocField12() throws AWTException {
        return Stream.of(new Picky(), new PecoPecoEgg(), new Condor(), new Drops()).collect(Collectors.toList());
    }
    @Bean
    public List<KillMonster> monstersPayField12() throws AWTException {
        return Stream.of(new Bigfoot(), new Caramel(), new Creamy()).collect(Collectors.toList());
    }
    @Bean
    public List<KillMonster> monstersPrtField07() throws AWTException {
        return Stream.of(new Rocker(), new SavageBaby()).collect(Collectors.toList());
    }
    @Bean
    public List<KillMonster> monstersPrtField08() throws AWTException {
        return Stream.of(new Poring()).collect(Collectors.toList());
    }
    @Bean
    public List<KillMonster> monstersYunField04() throws AWTException {
        return Stream.of(new Harpy()).collect(Collectors.toList());
    }
    @Bean
    public List<KillMonster> monstersYunField07() throws AWTException {
        return Stream.of(new Harpy(), new Goat()).collect(Collectors.toList());
    }
    @Bean
    public List<KillMonster> monstersYunField08() throws AWTException {
        return Stream.of(new Goat(), new GrandPecoPeco(), new Geographer()).collect(Collectors.toList());
    }
    @Bean
    public List<KillMonster> monstersYunField11() throws AWTException {
        return Stream.of(new Goat()).collect(Collectors.toList());
    }
    @Bean
    public List<KillMonster> monstersYunField12() throws AWTException {
        return Stream.of(new Mantis(), new Geographer()).collect(Collectors.toList());
    }

}
