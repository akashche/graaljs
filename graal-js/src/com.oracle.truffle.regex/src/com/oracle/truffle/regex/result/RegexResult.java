/*
 * Copyright (c) 2014, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.oracle.truffle.regex.result;

import com.oracle.truffle.api.interop.ForeignAccess;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.regex.RegexObject;
import com.oracle.truffle.regex.RegexLanguageObject;
import com.oracle.truffle.regex.runtime.RegexResultEndArrayObject;
import com.oracle.truffle.regex.runtime.RegexResultMessageResolutionForeign;
import com.oracle.truffle.regex.runtime.RegexResultStartArrayObject;

public abstract class RegexResult implements RegexLanguageObject {

    public static final RegexResult NO_MATCH = new RegexResult(null, "NULL", 0) {
    };

    private final RegexObject regex;
    private final Object input;
    private final int groupCount;
    private final RegexResultStartArrayObject startArrayObject;
    private final RegexResultEndArrayObject endArrayObject;

    public RegexResult(RegexObject regex, Object input, int groupCount) {
        this.regex = regex;
        this.input = input;
        this.groupCount = groupCount;
        startArrayObject = new RegexResultStartArrayObject(this);
        endArrayObject = new RegexResultEndArrayObject(this);
    }

    public final RegexObject getCompiledRegex() {
        return regex;
    }

    public final Object getInput() {
        return input;
    }

    public final int getGroupCount() {
        return groupCount;
    }

    public final RegexResultStartArrayObject getStartArrayObject() {
        return startArrayObject;
    }

    public final RegexResultEndArrayObject getEndArrayObject() {
        return endArrayObject;
    }

    public static boolean isInstance(TruffleObject object) {
        return object instanceof RegexResult;
    }

    @Override
    public ForeignAccess getForeignAccess() {
        return RegexResultMessageResolutionForeign.ACCESS;
    }
}
