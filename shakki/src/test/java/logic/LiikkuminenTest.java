package logic;

import variants.*;
import org.junit.Test;
import org.junit.Assert;
import components.Nappula;
import java.util.Arrays;
import logic.liikkuminen.Liikkuminen;
import logic.parser.Parser;
import static org.junit.Assert.*;

public class LiikkuminenTest {

    @Test
    public void eiVoiLiikkua() {
        Game peli = new Game(new Standard());

        assertFalse(Liikkuminen.koitaSiirtya(Parser.parseAlgebraic("d2"), Parser.parseAlgebraic("d5"), peli.getLauta(), null));
        assertFalse(Liikkuminen.koitaSiirtya(Parser.parseAlgebraic("d3"), Parser.parseAlgebraic("d5"), peli.getLauta(), null));
        peli.applyHistory("1. e4 e5\n"
                + "2. Nf3 Nf6\n"
                + "3. Ne5 Ne4\n"
                + "4. f3 Nf6\n"
                + "5. Bc4 Bc5");
        assertFalse(Liikkuminen.koitaSiirtya(Parser.parseAlgebraic("e1"), Parser.parseAlgebraic("g1"), peli.getLauta(), null));

    }

}
