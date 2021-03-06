/*
 * Copyright (c) 2015, 2016, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package com.oracle.js.parser.ir;

import java.util.Collections;
import java.util.List;

import com.oracle.js.parser.ir.visitor.NodeVisitor;
import com.oracle.js.parser.ir.visitor.TranslatorNodeVisitor;

/**
 * IR for CoverParenthesizedExpressionAndArrowParameterList, used only during parsing.
 */
public class ExpressionList extends Expression {
    private final List<? extends Expression> expressions;

    /**
     * Constructor.
     *
     * @param token token
     * @param finish finish
     * @param expressions expression
     */
    public ExpressionList(final long token, final int finish, final List<? extends Expression> expressions) {
        super(token, finish);
        this.expressions = expressions;
    }

    /**
     * Get the list of expressions.
     */
    public List<? extends Expression> getExpressions() {
        return Collections.unmodifiableList(expressions);
    }

    @Override
    public Node accept(final NodeVisitor<? extends LexicalContext> visitor) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <R> R accept(TranslatorNodeVisitor<? extends LexicalContext, R> visitor) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void toString(StringBuilder sb, boolean printType) {
        sb.append("(");
        boolean first = true;
        for (Expression expression : expressions) {
            if (first) {
                first = false;
            } else {
                sb.append(", ");
            }
            expression.toString(sb, printType);
        }
        sb.append(")");
    }
}
