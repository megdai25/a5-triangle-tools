package triangle;

import org.junit.Test;
import static org.junit.Assert.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Task4LoopWhileTest {
    @Test
    public void compilesLoopWhileSyntax() throws Exception {
        Path tri = Files.createTempFile("loopwhile", ".tri");
        Files.write(tri, Arrays.asList(
            "let",
            "  var a : Integer",
            "in",
            "begin",
            "  a := 0;",
            "  loop a := a + 1",
            "  while a < 3",
            "  do putint(a);",
            "end"
        ), StandardCharsets.US_ASCII);

        Path out = Files.createTempFile("out", ".tam");

        triangle.Compiler.main(new String[]{
            "-objectName", out.toString(),
            tri.toString()
        });

        assertTrue(Files.exists(out));
        assertTrue(Files.size(out) > 0);
    }
}