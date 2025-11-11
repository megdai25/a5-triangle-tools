package triangle.optimiser;

import triangle.abstractSyntaxTrees.AbstractSyntaxTree;
import triangle.abstractSyntaxTrees.Program;
import triangle.abstractSyntaxTrees.commands.CallCommand;
import triangle.abstractSyntaxTrees.expressions.CharacterExpression;
import triangle.abstractSyntaxTrees.expressions.IntegerExpression;

public class StatsVisitor extends ConstantFolder {

    private int integerCount = 0;
    private int characterCount = 0;

    public int getIntegerCount() { return integerCount; }
    public int getCharacterCount() { return characterCount; }

    // Count integer expressions
    @Override
    public AbstractSyntaxTree visitIntegerExpression(IntegerExpression ast, Void arg) {
        integerCount++;
        return super.visitIntegerExpression(ast, arg);
    }

    // Count character expressions
    @Override
    public AbstractSyntaxTree visitCharacterExpression(CharacterExpression ast, Void arg) {
        characterCount++;
        return super.visitCharacterExpression(ast, arg);
    }

    // Traverse into procedure call arguments
    @Override
    public AbstractSyntaxTree visitCallCommand(CallCommand ast, Void arg) {
        if (ast.APS != null) ast.APS.visit(this);
        if (ast.I   != null) ast.I.visit(this);
        return null; // ConstantFolder doesn't traverse here; we've done it.
    }

    @Override
    public AbstractSyntaxTree visitProgram(Program ast, Void arg) {
        return super.visitProgram(ast, arg);
    }
}