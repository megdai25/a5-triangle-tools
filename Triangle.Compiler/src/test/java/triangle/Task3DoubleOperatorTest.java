package triangle;

import org.junit.Test;
import static org.junit.Assert.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Task3DoubleOperatorTest {

    @Test
    public void compilesDoubleOperatorProgram() throws Exception {
        Path tri = Files.createTempFile("double", ".tri");
        Files.write(
                tri,
                Arrays.asList(
                        "let",
                        "  var a : Integer",
                        "in",
                        "begin",
                        "  a := 5;",
                        "  a**;",
                        "  putint(a);",
                        "end"
                ),
                StandardCharsets.US_ASCII
        );

        Path out = Files.createTempFile("out", ".tam");

        triangle.Compiler.main(new String[]{
                "-objectName", out.toString(),
                tri.toString()
        });

        assertTrue(Files.exists(out));
        assertTrue(Files.size(out) > 0);
    }
}