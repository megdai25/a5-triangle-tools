package triangle;

import org.junit.Test;
import static org.junit.Assert.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Task5CurlyBlockTest {
    @Test
    public void compilesCurlyBlock() throws Exception {
        Path tri = Files.createTempFile("curly", ".tri");
        Files.write(tri, Arrays.asList(
                "let",
                "  var a : Integer",
                "in",
                "{",
                "  a := 5;",
                "  putint(a)",
                "}"
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