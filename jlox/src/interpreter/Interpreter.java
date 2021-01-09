package interpreter;

public class Interpreter implements Expr.Visitor<Object> {

    @Override
    public Object visitBinaryExpr(Expr.Binary expr) {
        Object left = evaluate(expr.left);
        // Ternary implementation
        // Doesn't evaluate the right expr since it contains two expressions of which only one will be evaluated
        if (expr.operator.type == TokenType.Q_MARK) {
            Expr ternaryTrueExpr = ((Expr.Binary)expr.left).left;
            Expr ternaryFalseExpr = ((Expr.Binary)expr.left).right;
            if (isTruthy(left)) {
                return evaluate(ternaryTrueExpr);
            }
            else {
                return evaluate(ternaryFalseExpr);
            }
        }
        
        Object right = evaluate(expr.right);

        switch(expr.operator.type) {
            case MINUS:
                return (double)left - (double)right;
            case PLUS:
                if (left instanceof Double && right instanceof Double) {
                    return (double)left + (double)right;
                }

                if (left instanceof String && right instanceof String) {
                    return (String)left + (String)right;
                }
                break;
            case SLASH:
                return (double)left / (double)right;
            case STAR:
                return (double)left * (double)right;
        }

        // Won't be hit (usually)
        return null;
    }

    @Override
    public Object visitGroupingExpr(Expr.Grouping expr) {
        return evaluate(expr.expression);
    }

    @Override
    public Object visitLiteralExpr(Expr.Literal expr) {
        return expr.value;
    }

    @Override
    public Object visitUnaryExpr(Expr.Unary expr) {
        Object right = evaluate(expr.right);

        switch (expr.operator.type) {
            case MINUS:
                return -(double)right;
            case BANG:
                return !isTruthy(right);
        }

        // Won't be hit in theory
        return null;
    }

    private boolean isTruthy(Object object) {
        if (object == null) {
            return false;
        }
        if (object instanceof Boolean) {
            return (boolean) object;
        }
        // TODO make zero falsey
        return true;
    }

    private Object evaluate(Expr expr) {
        return expr.accept(this);
    }

}