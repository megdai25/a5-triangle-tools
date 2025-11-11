package triangle;

import org.junit.Test;
import static org.junit.Assert.*;

import triangle.abstractSyntaxTrees.AbstractSyntaxTree;
import triangle.abstractSyntaxTrees.expressions.*;
import triangle.abstractSyntaxTrees.terminals.*;
import triangle.abstractSyntaxTrees.vnames.*;
import triangle.optimiser.ConstantFolder;
import triangle.syntacticAnalyzer.SourcePosition;

public class Task7BooleanFoldingTest {

    private SourcePosition pos() {
        SourcePosition p = new SourcePosition();
        p.start = 0; p.finish = 0;
        return p;
    }

    private BinaryExpression makeBin(String lhs, String op, String rhs) {
        SourcePosition p = pos();
        IntegerLiteral il1 = new IntegerLiteral(lhs, p);
        IntegerLiteral il2 = new IntegerLiteral(rhs, p);
        IntegerExpression e1 = new IntegerExpression(il1, p);
        IntegerExpression e2 = new IntegerExpression(il2, p);
        Operator o = new Operator(op, p);
        return new BinaryExpression(e1, o, e2, p);
    }

    @Test
    public void foldsLessToTrue() {
        ConstantFolder cf = new ConstantFolder();
        BinaryExpression be = makeBin("2", "<", "3");
        AbstractSyntaxTree folded = cf.visitBinaryExpression(be, null);
        assertNotNull(folded);
        assertTrue(folded instanceof VnameExpression);
        Vname v = ((VnameExpression) folded).V;
        assertTrue(v instanceof SimpleVname);
        assertEquals("true", ((SimpleVname) v).I.spelling);
    }

    @Test
    public void foldsNotEqualToFalse() {
        ConstantFolder cf = new ConstantFolder();
        BinaryExpression be = makeBin("7", "\\=", "7"); // Triangle not-equal
        AbstractSyntaxTree folded = cf.visitBinaryExpression(be, null);
        assertNotNull(folded);
        assertTrue(folded instanceof VnameExpression);
        Vname v = ((VnameExpression) folded).V;
        assertTrue(v instanceof SimpleVname);
        assertEquals("false", ((SimpleVname) v).I.spelling);
    }
}
