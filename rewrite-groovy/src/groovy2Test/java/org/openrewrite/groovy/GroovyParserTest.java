/*
 * Copyright 2025 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openrewrite.groovy;

import org.junit.jupiter.api.Test;
import org.openrewrite.test.RewriteTest;

import static org.openrewrite.groovy.Assertions.groovy;

class GroovyParserTest implements RewriteTest {

    @Test
    void shouldNotTreatDivisionAsDelimiter() {
        rewriteRun(
                groovy(
                        """
                        def x = (1 / 1) * 2
                        System.out.println("test")
                        """
                )
        );
    }

    @Test
    void shouldHandleUsingSlashyStringsWithDivision() {
        rewriteRun(
                groovy(
                        """
                        def x = (Integer.parseInt(/3/) / Integer.parseInt(/2/)) * Integer.parseInt(/5/)
                        System.out.println("test")
                        """
                )
        );
    }

    @Test
    void shouldBeAbleToParseParenthesisedConstructorCallExpressions() {
        rewriteRun(
                groovy(
                        """
                        (new BigDecimal(10))
                        """
                )
        );
    }

    @Test
    void shouldBeAbleToParseParenthesisedSlashyStringConstantExpressions() {
        rewriteRun(
                groovy(
                        """
                        ((/test/))
                        """
                )
        );
    }

    @Test
    void shouldBeAbleToParseParenthesisedQuotedConstantExpressions() {
        rewriteRun(
                groovy(
                        """
                        (("test"))
                        """
                )
        );
    }

    @Test
    void shouldBeAbleToParseParenthesisedIntegerConstantExpressions() {
        rewriteRun(
                groovy(
                        """
                        (100)
                        """
                )
        );
    }

}
